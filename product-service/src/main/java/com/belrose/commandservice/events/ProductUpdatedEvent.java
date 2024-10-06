package com.belrose.commandservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdatedEvent {

    private String id;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
