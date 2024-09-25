package com.myProject.e_Commerce.repositories;


import com.myProject.e_Commerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}