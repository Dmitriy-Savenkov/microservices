package com.microservices.inventoryservice.controller;


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
    @DisplayName("Should find an Item and return HttpStatus 200 - OK when making GET request to endpoint - /api/inventory/Nokia")
    void shouldSaveAnOrder() throws Exception {
        Mockito.when(service.isInStock("Nokia")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/Nokia"))
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/Samsung"))
                .andExpect(MockMvcResultMatchers.content().string("false"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
