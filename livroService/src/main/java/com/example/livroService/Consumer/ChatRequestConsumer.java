package com.example.livroService.Consumer;

import com.example.livroService.DTO.PedidoBookDTO;
import com.example.livroService.Service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatRequestConsumer {
    @Autowired
    BookService bookService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "chat-queue")
    public void receiveMessage(String message) throws JsonProcessingException {
        PedidoBookDTO pedidoBookDTO = objectMapper.readValue(message, PedidoBookDTO.class);
        bookService.updateBookAmount(pedidoBookDTO);
    }
}
