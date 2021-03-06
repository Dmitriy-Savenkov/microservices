package com.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 * DTO of Order class
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    /**
     * List of Items included in an order
     */
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
