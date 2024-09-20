package com.example.pedidoService.Controller;

import com.example.pedidoService.DTO.PedidoDTO;
import com.example.pedidoService.Service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class PedidoController {

    @Autowired
    private PedidoService orderService;

    @GetMapping
    public List<PedidoDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getOrderById(@PathVariable Long id) {
        Optional<PedidoDTO> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public PedidoDTO createOrder(@RequestBody PedidoDTO bookOrderDTO) throws JsonProcessingException {
        return orderService.saveOrder(bookOrderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
