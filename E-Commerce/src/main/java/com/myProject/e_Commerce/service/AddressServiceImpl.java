package com.myProject.e_Commerce.service;

import com.myProject.e_Commerce.exception.NoAddressFoundException;
import com.myProject.e_Commerce.exception.ResourceNotFoundException;
import com.myProject.e_Commerce.model.Address;
import com.myProject.e_Commerce.model.User;
import com.myProject.e_Commerce.payload.AddressDTO;
import com.myProject.e_Commerce.repositories.AddressRepository;
import com.myProject.e_Commerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public AddressDTO addAddress(AddressDTO addressDTO, User user) {
        Address address= modelMapper.map(addressDTO,Address.class);
      /*Why taking list here bcs we have realtion ship with user now so if we update Address it should update with the linked user also*/
       //Update address against user
        List<Address> addressList=user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {

        List<Address> allAddress = addressRepository.findAll();
        if(allAddress.isEmpty())
            throw new NoAddressFoundException("No address added yet!");
        else
        return allAddress.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
        List<Address> allAddress = user.getAddresses();
        if(allAddress.isEmpty())
            throw new NoAddressFoundException("No address added for the user "+user.getUserName() + " yet!");
        else
        return allAddress.stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressesById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address","AddressId",addressId));
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    @Autowired
    UserRepository userRepository;
    @Override
    public AddressDTO UpdateAddress(AddressDTO addressDTO, Long addressId) {
      Address addressFromDB=addressRepository.findById(addressId)
              .orElseThrow(()->new NoAddressFoundException("No address with  addressId "+addressId+" found"));
        addressFromDB.setBuildingName(addressDTO.getBuildingName());
        addressFromDB.setStreet(addressDTO.getStreet());
        addressFromDB.setCity(addressDTO.getCity());
        addressFromDB.setState(addressDTO.getState());
        addressFromDB.setCountry(addressDTO.getCountry());
        addressFromDB.setPincode(addressDTO.getPincode());

        Address updatedAddress = addressRepository.save(addressFromDB);

        /*Updating the User’s Address List*/
        User user= addressFromDB.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);

        return modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
     Address addressFromDB =addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        User user= addressFromDB.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDB);
        return "Address deleted Successfully from addressId: "+addressId;
    }
}
