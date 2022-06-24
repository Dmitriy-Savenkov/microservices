package com.microservices.inventoryservice.repository;

import com.microservices.inventoryservice.model.Inventory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/***
 * DB tests
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InventoryRepositoryTest {

    @Autowired
    private InventoryRepository repository;

    @Test
    @DisplayName("Repo should successfully find an item in the stock")
    void shouldSaveAnOrder() {
        Inventory inventory = Inventory.builder()
                .id(1L)
                .skuCode("Test Entity")
                .quantity(1)
                .build();

        Inventory savedInventory = repository.save(inventory);
        Assertions.assertThat(savedInventory).usingRecursiveComparison().ignoringFields("id").isEqualTo(inventory);
    }
}
