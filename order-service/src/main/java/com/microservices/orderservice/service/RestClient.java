package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Class for sending requests to Rests
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class RestClient {

    private final WebClient webClient;

    /***
     * GET request to InventoryService for checking sku-codes in the stock
     *
     * @param skuCodeList list of sku-codes
     * @return array of responses
     */
    protected InventoryResponse[] isInStock(List<String> skuCodeList) {
        return webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
    }

}
