package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.InventoryResponse;
import com.microservices.orderservice.dto.OrderLineItemsDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private RestClient requestSender;

    private OrderService service;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    private List<OrderLineItemsDto> lineItemsDtoList;
    private InventoryResponse[] inventoryResponseArray;

    @BeforeEach
    void init() {
        service = new OrderService(repository, requestSender);

        OrderLineItemsDto dto1 = OrderLineItemsDto.builder()
                .id(1000L)
                .skuCode(UUID.randomUUID().toString())
                .price(BigDecimal.valueOf(1345L))
                .quantity(5)
                .build();

        OrderLineItemsDto dto2 = OrderLineItemsDto.builder()
                .id(2L)
                .skuCode(UUID.randomUUID().toString())
                .price(BigDecimal.valueOf(600L))
                .quantity(2)
                .build();

        InventoryResponse response = new InventoryResponse(dto1.getSkuCode(), true);
        InventoryResponse response2 = new InventoryResponse(dto2.getSkuCode(), true);

        lineItemsDtoList = List.of(dto1, dto2);
        inventoryResponseArray = new InventoryResponse[]{response, response2};
    }

    @Test
    @DisplayName("Should save an order to PostgresDB")
    void shouldSaveAnOrder() {
        OrderRequest request = new OrderRequest(lineItemsDtoList);
        List<String> skuCodeList = lineItemsDtoList.stream().map(OrderLineItemsDto::getSkuCode).collect(Collectors.toList());

        Mockito.when(requestSender.isInStock(skuCodeList)).thenReturn(inventoryResponseArray);

        service.createOrder(request);

        Mockito.verify(repository, Mockito.times(1)).save(orderArgumentCaptor.capture());
        Assertions.assertThat(orderArgumentCaptor.getValue().getOrderLineItemsList().get(0).getPrice()).isEqualTo(BigDecimal.valueOf(1345L));
    }
}
