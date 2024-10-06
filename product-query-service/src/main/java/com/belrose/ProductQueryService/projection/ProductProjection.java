package com.belrose.ProductQueryService.projection;

import com.belrose.ProductQueryService.model.Product;
import com.belrose.ProductQueryService.query.GetProductsQuery;
import com.belrose.ProductQueryService.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<Product> handle(GetProductsQuery getProductsQuery) {
        List<Product> products =
                productRepository.findAll().collectList().block();

        assert products != null;
       return products.stream()
                        .map(product -> Product
                                .builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build())
                        .collect(Collectors.toList());
    }
}
