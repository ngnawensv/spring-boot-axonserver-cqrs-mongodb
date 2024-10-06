package com.belrose.commandservice.config;

import com.belrose.commandservice.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Autowired
    public void configure(EventProcessingConfigurer configure) {
        configure.registerListenerInvocationErrorHandler(
                "product",
                configuration -> new ProductServiceEventsErrorHandler()
        );
    }
}
