package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.dto.InventoryResponse;
import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
        InventoryResponse responseFalse = new InventoryResponse("Nokia", true);
        InventoryResponse response = new InventoryResponse("Test Entity", true);

        List<InventoryResponse> inStock = service.isInStock(List.of("Nokia", "Test Entity"));

        Assertions.assertThat(inStock.get(0)).isNotEqualTo(responseFalse);
        Assertions.assertThat(inStock.get(0)).isEqualTo(response);
    }
}
