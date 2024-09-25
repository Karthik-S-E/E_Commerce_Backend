package com.myProject.e_Commerce.controller;

import com.myProject.e_Commerce.model.User;
import com.myProject.e_Commerce.payload.AddressDTO;
import com.myProject.e_Commerce.service.AddressService;
import com.myProject.e_Commerce.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
@Autowired
    AuthUtil authUtil;
    @Autowired
    AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO)
    {
        User user = authUtil.loggedInUser();
        AddressDTO addressDTO1 = addressService.addAddress(addressDTO,user);
        return new ResponseEntity<AddressDTO>(addressDTO1, HttpStatus.CREATED);
    }
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddress()
    {
      List<AddressDTO> addressDTOS= addressService.getAddresses();
        return  new ResponseEntity<List<AddressDTO>>(addressDTOS,HttpStatus.OK);
    }
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAllAddressById(@PathVariable Long addressId)
    {
        AddressDTO addressDTOS= addressService.getAddressesById(addressId);
        return  new ResponseEntity<AddressDTO>(addressDTOS,HttpStatus.OK);
    }
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserSpecificAddress()
    {
        User user=authUtil.loggedInUser();
        List<AddressDTO> addressDTOS= addressService.getUserAddresses(user);
        if(addressDTOS.isEmpty())
            throw new RuntimeException("Address not found for "+user.getUserName());
        else
        return  new ResponseEntity<List<AddressDTO>>(addressDTOS,HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO,@PathVariable Long addressId)
    {
        AddressDTO updatedAddress = addressService.UpdateAddress(addressDTO,addressId);
        return new ResponseEntity<AddressDTO>(updatedAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/addresses/{addressId}")
    public String deleteAddress(@PathVariable Long addressId)
    {
        String updatedAddress = addressService.deleteAddress(addressId);
        return updatedAddress;
    }

}
