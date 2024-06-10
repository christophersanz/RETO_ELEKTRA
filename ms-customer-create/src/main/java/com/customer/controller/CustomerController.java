package com.customer.controller;

import com.customer.controller.dto.CustomerResponseDto;
import com.customer.controller.dto.CustomerRequestDto;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto savedCustomer = customerService.createCustomer(customerRequestDto);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

}