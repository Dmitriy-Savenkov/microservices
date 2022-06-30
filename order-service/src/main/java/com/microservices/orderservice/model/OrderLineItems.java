package com.microservices.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/***
 * Items which an order contains
 */

@Entity
@Table(name = "order_line_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
