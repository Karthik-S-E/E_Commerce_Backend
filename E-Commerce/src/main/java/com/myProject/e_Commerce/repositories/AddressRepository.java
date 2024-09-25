package com.myProject.e_Commerce.repositories;

import com.myProject.e_Commerce.model.Address;
import com.myProject.e_Commerce.model.CartItem;
import com.myProject.e_Commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {


}
