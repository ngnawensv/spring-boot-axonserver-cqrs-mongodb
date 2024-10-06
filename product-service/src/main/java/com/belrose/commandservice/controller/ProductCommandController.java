package com.belrose.commandservice.controller;

import com.belrose.commandservice.command.CreateProductCommand;
import com.belrose.commandservice.dto.ProductDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/vi/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;
    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductDto productDto) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                        .id(UUID.randomUUID().toString())
                        .sku(UUID.randomUUID().toString())
                        .name(productDto.getName())
                        .price(productDto.getPrice())
                        .quantity(productDto.getQuantity())
                        .build();
        return commandGateway.sendAndWait(createProductCommand);
    }
}
