package com.example.pedidoService.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("pedido-exchange");
    }

    @Bean
    public Queue pedidoQueue() {
        return new Queue("pedido-queue", true);
    }

    @Bean
    public Binding pedidoBinding() {
        return BindingBuilder.bind(pedidoQueue()).to(exchange()).with("pedido-routing-key");
    }
}
