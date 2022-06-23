package com.microservices.productservice.service;


import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.model.Product;
import com.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Service for product
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /***
     * Create a new product
     * @param productRequest dto
     */
    public void createProduct(ProductRequest productRequest) {
        productRepository.save(MapperUtils.mapProductRequestToProduct(productRequest));
        log.info("Product {} was saved", productRequest.getName());
    }

    /***
     * Create a new product
     * @return List<ProductResponse> dto list
     */
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(MapperUtils::mapProductToProductResponse).collect(Collectors.toList());
    }
}
