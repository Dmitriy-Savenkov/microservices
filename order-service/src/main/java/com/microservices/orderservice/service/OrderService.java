package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.InventoryResponse;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/***
 * Service for orders
 */

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestClient restClient;

    /**
     * Create and save an order
     */
    public String createOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
                .map(MapperUtils::convertDtoToEntity)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItemsList)
                .build();

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());

        // Call inventory service to check the stock
        List<InventoryResponse> inventoryResponsesList = Arrays.asList(restClient.isInStock(skuCodes));
        boolean allProductsInStock = inventoryResponsesList.stream().anyMatch(item -> !item.isInStock());

        if (allProductsInStock) {
            orderRepository.save(order);
            String status = String.format("The order %d was successfully created", order.getId());
            log.info(status);
            return status;
        } else {
            List<String> absentItems = getAbsentItemCodes(skuCodes, inventoryResponsesList);
            String errorStatus = String.format("The stock does not have next items: %s", StringUtils.join(absentItems, ", "));
            log.info(errorStatus);
            return errorStatus;
        }
    }

    /***
     * Get the list of sku-codes of absent items
     *
     * @param allSkuCodes all requested sku-codes
     * @param itemsFromStock items which are in the stock (answer from inventory service)
     * @return list of absent sku-codes
     */
    private List<String> getAbsentItemCodes(List<String> allSkuCodes, List<InventoryResponse> itemsFromStock) {
        List<String> skuCodeFromStock = itemsFromStock.stream().map(InventoryResponse::getSkuCode).collect(Collectors.toList());
        return allSkuCodes.stream()
                .filter(skuCode -> !skuCodeFromStock.contains(skuCode))
                .collect(Collectors.toList());
    }
}
