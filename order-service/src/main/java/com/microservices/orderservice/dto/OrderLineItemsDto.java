package com.microservices.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/***
 * DTO of Items which an order contains
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    /**
     * Id
     */
    private Long id;

    /**
     * Stock keeping unit code
     */
    private String skuCode;

    /**
     * Price
     */
    private BigDecimal price;

    /**
     * Quantity
     */
    private Integer quantity;
}
