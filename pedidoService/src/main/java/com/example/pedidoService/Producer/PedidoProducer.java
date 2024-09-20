package com.example.pedidoService.Producer;

import com.example.pedidoService.DTO.PedidoBookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(PedidoBookDTO pedidoDTO) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
            "pedido-exchange",
            "pedido-routing-key",
            objectMapper.writeValueAsString(pedidoDTO)
    );
    }
}
