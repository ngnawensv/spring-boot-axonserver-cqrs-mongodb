package com.belrose.ProductQueryService.repository;


import com.belrose.ProductQueryService.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
