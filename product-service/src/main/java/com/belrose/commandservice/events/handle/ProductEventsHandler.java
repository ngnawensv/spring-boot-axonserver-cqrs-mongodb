package com.belrose.commandservice.events.handle;

import com.belrose.commandservice.events.ProductCreatedEvent;
import com.belrose.commandservice.model.Product;
import com.belrose.commandservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
@Slf4j
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product).subscribe();
                /*.onErrorMap(throwable -> {
                    // Transforming any error into a custom exception
                    log.error("Error to save product");
                    return new Exception(String.format("Failed to save product.Cause %s", throwable));
                });*/
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
