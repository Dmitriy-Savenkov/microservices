package com.microservices.inventoryservice.controller;


import com.microservices.inventoryservice.dto.InventoryResponse;
import com.microservices.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.util.List;

/**
 * Test for Controller layer
 */

@WebMvcTest(controllers = InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService service;

    @Test
    @DisplayName("Should find an Item and return HttpStatus 200 - OK when making GET request to endpoint - /api/inventory?skuCode=Nokia&skuCode=Samsung")
    void shouldFindAnItemInStock() throws Exception {
        InventoryResponse response = new InventoryResponse("Nokia", true);
        InventoryResponse response2 = new InventoryResponse("Samsung", true);

        Mockito.when(service.isInStock(List.of("Nokia", "Samsung"))).thenReturn(List.of(response, response2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory?skuCode=Nokia&skuCode=Samsung"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)).value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].skuCode", Matchers.is("Nokia")).value("Nokia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].skuCode", Matchers.is("Samsung")).value("Samsung"));
    }
}
