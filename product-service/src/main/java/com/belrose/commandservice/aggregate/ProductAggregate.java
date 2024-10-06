package com.belrose.commandservice.aggregate;

import com.belrose.commandservice.command.CreateProductCommand;
import com.belrose.commandservice.command.DeleteProductCommand;
import com.belrose.commandservice.command.UpdateProductCommand;
import com.belrose.commandservice.events.ProductCreatedEvent;
import com.belrose.commandservice.events.ProductDeletedEvent;
import com.belrose.commandservice.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        //You can perform all the validations
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.id = productCreatedEvent.getId();
        this.sku = productCreatedEvent.getSku();
        this.quantity = productCreatedEvent.getQuantity();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();
    }

    @CommandHandler
    public void handle(UpdateProductCommand command) {
        // Handle update logic
    }
    @EventSourcingHandler
    public void on(ProductUpdatedEvent event) {
        // Handle product update event
    }

    @CommandHandler
    public void handle(DeleteProductCommand command) {
        // Handle deletion logic
    }
    @EventSourcingHandler
    public void on(ProductDeletedEvent event) {
        // Handle product deletion event
    }
}
