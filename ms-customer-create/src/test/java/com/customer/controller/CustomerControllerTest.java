package com.customer.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.customer.controller.dto.CustomerResponseDto;
import com.customer.service.CustomerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
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
    public void testCreateCustomer() throws Exception {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(1L);
        customerResponseDto.setNombre("Christopher");

        when(customerService.createCustomer(any())).thenReturn(customerResponseDto);

        CustomerResponseDto customerRequestDto = new CustomerResponseDto();
        customerRequestDto.setNombre("Christopher");
        customerRequestDto.setApellidoMaterno("Sánchez");
        customerRequestDto.setApellidoPaterno("Calderón");
        customerRequestDto.setSexo("M");
        customerRequestDto.setCorreo("s.christopher3@gmail.com");
        customerRequestDto.setTelefono("1962296846");


        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Christopher"));

        verify(customerService, times(1)).createCustomer(any());
    }

}