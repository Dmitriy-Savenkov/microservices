package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.dto.InventoryResponse;
import com.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Inventory entity
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    /**
     * Search if a skuCode in a stock
     *
     * @param skuCode Stock keeping unit code
     * @return true/false
     */
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Searching for sku-codes {}", StringUtils.join(skuCode, ", "));
        return repository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build())
                .collect(Collectors.toList());
    }
}
