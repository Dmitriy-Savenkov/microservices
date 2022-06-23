package com.microservices.orderservice.repository;

import com.microservices.orderservice.DataBaseContainer;
import com.microservices.orderservice.dto.OrderLineItemsDto;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/***
 * DB tests
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest extends DataBaseContainer {

    @Autowired
    private OrderRepository repository;

    @Test
    @DisplayName("Repo should successfully save an order")
    void shouldSaveAnOrder() {
        OrderLineItems dto1 = OrderLineItems.builder()
                .id(6L)
                .skuCode(UUID.randomUUID().toString())
                .price(BigDecimal.valueOf(1345L))
                .quantity(5)
                .build();

        Order order = new Order(1L, "10", List.of(dto1));
        Order savedOrder = repository.save(order);


        Assertions.assertThat(savedOrder.getOrderLineItemsList()).usingRecursiveComparison().ignoringFields("id").isEqualTo(order.getOrderLineItemsList());
        Assertions.assertThat(savedOrder).usingRecursiveComparison().ignoringFields("orderLineItemsList").isEqualTo(order);
    }
}
