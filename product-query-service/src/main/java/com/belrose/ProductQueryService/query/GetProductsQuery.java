package com.belrose.ProductQueryService.query;

import com.belrose.ProductQueryService.model.Product;
import com.belrose.ProductQueryService.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetProductsQuery {

    private ProductRepository productRepository;

    public GetProductsQuery(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .switchIfEmpty(Flux.empty());
    }
}
