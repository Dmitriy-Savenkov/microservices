package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    /**
     * Search if a skuCode in a stock
     * @param skuCode Stock keeping unit code
     * @return true/false
     */
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return repository.findBySkuCode(skuCode).isPresent();
    }
}
