package com.customer.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.customer.controller.dto.CustomerResponseDto;
import com.customer.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Long customerId = 1L;
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customerId);
        customerResponseDto.setNombre("Christopher Sanz");

        when(customerService.getCustomerById(customerId)).thenReturn(customerResponseDto);

        mockMvc.perform(get("/api/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.nombre").value("Christopher Sanz"));

        verify(customerService, times(1)).getCustomerById(customerId);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        CustomerResponseDto customer1 = new CustomerResponseDto();
        customer1.setId(1L);
        customer1.setNombre("Christopher Sanz");

        CustomerResponseDto customer2 = new CustomerResponseDto();
        customer2.setId(2L);
        customer2.setNombre("Jose Perez");

        List<CustomerResponseDto> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Christopher Sanz"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nombre").value("Jose Perez"));

        verify(customerService, times(1)).getAllCustomers();
    }
}