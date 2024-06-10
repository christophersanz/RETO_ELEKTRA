package com.customer.mapper;

import com.customer.controller.dto.CustomerRequestDto;
import com.customer.controller.dto.CustomerResponseDto;
import com.customer.entity.Customer;

public class CustomerMapper {

    public static Customer convertToCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setNombre(customerRequestDto.getNombre());
        customer.setApellidoPaterno(customerRequestDto.getApellidoPaterno());
        customer.setApellidoMaterno(customerRequestDto.getApellidoMaterno());
        customer.setFechaNacimiento(customerRequestDto.getFechaNacimiento());
        customer.setSexo(customerRequestDto.getSexo());
        customer.setCorreo(customerRequestDto.getCorreo());
        customer.setTelefono(customerRequestDto.getTelefono());
        return customer;
    }

    public static CustomerResponseDto toCustomerResponseDto(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setNombre(customer.getNombre());
        customerResponseDto.setApellidoPaterno(customer.getApellidoPaterno());
        customerResponseDto.setApellidoMaterno(customer.getApellidoMaterno());
        customerResponseDto.setFechaNacimiento(customer.getFechaNacimiento());
        customerResponseDto.setSexo(String.valueOf(customer.getSexo()));
        customerResponseDto.setCorreo(customer.getCorreo());
        customerResponseDto.setTelefono(customer.getTelefono());
        return customerResponseDto;
    }

}
