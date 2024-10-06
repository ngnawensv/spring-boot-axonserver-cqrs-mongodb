package com.belrose.ProductQueryService.controller;

import com.belrose.ProductQueryService.model.Product;
import com.belrose.ProductQueryService.query.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;
    private  final GetProductsQuery getProductsQuery;

    public ProductQueryController(QueryGateway queryGateway, GetProductsQuery getProductsQuery) {
        this.queryGateway = queryGateway;
        this.getProductsQuery = getProductsQuery;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(Product.class))
                .join();
    }
}
