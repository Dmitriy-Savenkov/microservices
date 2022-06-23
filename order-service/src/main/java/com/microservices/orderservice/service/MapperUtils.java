package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.OrderLineItemsDto;
import com.microservices.orderservice.model.OrderLineItems;

/***
 * Class for mapping entities
 */

public class MapperUtils {

    private MapperUtils() {
    }

    /**
     * Mapping Entity to DTO
     *
     * @param dto OrderLineItemsDto
     * @return OrderLineItems entity
     */
    public static OrderLineItems convertDtoToEntity(OrderLineItemsDto dto) {
        return OrderLineItems.builder()
                .id(dto.getId())
                .skuCode(dto.getSkuCode())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}

