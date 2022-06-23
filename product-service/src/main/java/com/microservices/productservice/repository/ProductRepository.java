package com.microservices.productservice.repository;

import com.microservices.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/***
 * Product repo
 */

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
