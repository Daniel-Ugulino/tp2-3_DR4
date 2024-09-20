package com.example.pedidoService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private String bookTitle;
    private int bookId;
    private int quantity;
    private double totalPrice;
}
