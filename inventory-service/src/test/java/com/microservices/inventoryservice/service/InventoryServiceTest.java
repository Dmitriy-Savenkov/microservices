package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Tests for service layer
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InventoryServiceTest {

    @Autowired
    private InventoryRepository repository;

    private static InventoryService service;

    @BeforeEach
    void init() {
        service = new InventoryService(repository);

        Inventory inventory = Inventory.builder()
                .id(1L)
                .skuCode("Test Entity")
                .quantity(1)
                .build();

        repository.save(inventory);
    }

    @Test
    @DisplayName("Service should find an item in the stock")
    void shouldSaveAnOrder() {
        Assertions.assertFalse(service.isInStock("Nokia"));
        Assertions.assertTrue(service.isInStock("Test Entity"));
    }
}
