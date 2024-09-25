package com.myProject.e_Commerce.service;

import com.myProject.e_Commerce.model.User;
import com.myProject.e_Commerce.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO addAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddresses();

    AddressDTO getAddressesById(Long addressId);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO UpdateAddress(AddressDTO addressDTO, Long addressId);

    String deleteAddress(Long addressId);
}
