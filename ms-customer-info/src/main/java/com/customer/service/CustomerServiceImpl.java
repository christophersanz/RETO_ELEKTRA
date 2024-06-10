package com.customer.service;

import com.customer.controller.dto.CustomerResponseDto;
import com.customer.entity.Customer;
import com.customer.exceptions.GenericClientException;
import com.customer.mapper.CustomerMapper;
import com.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        log.info("Buscando cliente con id: {}", id);
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            log.info("No se encontró el cliente con el id: {}", id);
            throw new GenericClientException("No se encontró el cliente con el id: " + id, HttpStatus.NOT_FOUND);
        }
        log.info("Cliente encontrado: {}", customer);
        return CustomerMapper.toCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        log.info("Buscando todos los clientes");
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerResponseDto).toList();
    }
}
