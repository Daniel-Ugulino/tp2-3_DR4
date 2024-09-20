package com.example.livroService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoBookDTO {
    private Long BookId;
    private int quantity;
}
