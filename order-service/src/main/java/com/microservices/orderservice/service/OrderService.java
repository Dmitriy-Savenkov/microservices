package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.OrderLineItemsDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/***
 * Service for orders
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * Create and save an order
     */
    public void createOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItemsList)
                .build();

        orderRepository.save(order);
        log.info("The order {} was successfully created", order.getId());
    }

    /**
     * Convert dto to original entity
     * @param dto OrderLineItemsDto
     * @return OrderLineItems entity
     */
    private OrderLineItems convertDtoToEntity(OrderLineItemsDto dto) {
        return OrderLineItems.builder()
                .id(dto.getId())
                .skuCode(dto.getSkuCode())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}
