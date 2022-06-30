package com.microservices.inventoryservice.repository;

import com.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Checking a sku-code in DB
     *
     * @param skuCode sku-code
     * @return inventory
     */
    Optional<Inventory> findBySkuCode(String skuCode);

    /**
     * Checking the list of sku-code in DB
     *
     * @param skuCode list of sku-codes
     * @return list of inventories
     */
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
