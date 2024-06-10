package com.customer.service;

import com.customer.controller.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto getCustomerById(Long id);

    List<CustomerResponseDto> getAllCustomers();
}
