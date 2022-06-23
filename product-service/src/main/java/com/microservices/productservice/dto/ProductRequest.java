package com.microservices.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/***
 * DTO for product creation
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Price
     */
    private BigDecimal price;
}
