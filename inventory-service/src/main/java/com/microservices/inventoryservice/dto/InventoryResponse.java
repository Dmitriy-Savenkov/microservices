package com.microservices.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * DTO for response from Stock
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

    /**
     * Stock keeping unit code
     */
    private String skuCode;

    /**
     * Is in stock or not
     */
    private boolean isInStock;
}
