package com.customer.service;

import com.customer.controller.dto.CustomerRequestDto;
import com.customer.controller.dto.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);
}
