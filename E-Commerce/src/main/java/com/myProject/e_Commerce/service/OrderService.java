package com.myProject.e_Commerce.service;

import com.myProject.e_Commerce.payload.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage) ;

}
