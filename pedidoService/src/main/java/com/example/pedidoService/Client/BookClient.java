package com.example.pedidoService.Client;

import com.example.pedidoService.DTO.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", url = "http://localhost:8082/book")
public interface BookClient {
    @GetMapping("{id}")
    BookDTO getBookById(@PathVariable("id") Long id);
}
