package com.customer.service;

import com.customer.controller.dto.CustomerResponseDto;
import com.customer.entity.Customer;
import com.customer.exceptions.GenericClientException;
import com.customer.controller.dto.CustomerRequestDto;
import com.customer.mapper.CustomerMapper;
import com.customer.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @SneakyThrows
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        log.info("Validando si ya existe una persona con el mismo nombre...");
        if (customerRepository.findByNombre(customerRequestDto.getNombre()).isPresent()) {
            throw new GenericClientException("Ya existe una persona con el mismo nombre registrado", HttpStatus.CONFLICT);
        }

        log.info("Registrando persona...");
        Customer customer = CustomerMapper.convertToCustomer(customerRequestDto);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Persona registrada correctamente!");

        return CustomerMapper.toCustomerResponseDto(savedCustomer);
    }

}
