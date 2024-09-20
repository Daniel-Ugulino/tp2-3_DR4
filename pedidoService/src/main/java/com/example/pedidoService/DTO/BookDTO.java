package com.example.pedidoService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private double price;
}
