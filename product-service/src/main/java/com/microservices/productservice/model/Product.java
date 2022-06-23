package com.microservices.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/***
 * Product entity
 */

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    /**
     * Id
     */
    @Id
    private String id;

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


