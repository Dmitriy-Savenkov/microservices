package com.microservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.orderservice.dto.OrderLineItemsDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    private ObjectMapper mapper;
    private List<OrderLineItemsDto> orderLineItemsDtoList;

    @BeforeEach
    void init() {
        mapper = new ObjectMapper();
        orderLineItemsDtoList = List.of(
                OrderLineItemsDto.builder()
                        .id(1000L)
                        .skuCode(UUID.randomUUID().toString())
                        .price(BigDecimal.valueOf(1345L))
                        .quantity(5)
                        .build());
    }

    @Test
    @DisplayName("Should save an order and return HttpStatus 201 - Created when making GET request to endpoint - /api/orders")
    void shouldSaveAnOrder() throws Exception {
        OrderRequest request = new OrderRequest(orderLineItemsDtoList);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
