package com.example.pedidoService.Service;

import com.example.pedidoService.DTO.PedidoBookDTO;
import com.example.pedidoService.DTO.PedidoDTO;
import com.example.pedidoService.Model.Pedido;
import com.example.pedidoService.Producer.PedidoProducer;
import com.example.pedidoService.Repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository orderRepository;
    @Autowired
    private PedidoProducer pedidoProducer;

    public List<PedidoDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PedidoDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::convertToDTO);
    }

    public PedidoDTO saveOrder(PedidoDTO orderDTO) throws JsonProcessingException {
        Pedido order = convertToEntity(orderDTO);
        Pedido savedOrder = orderRepository.save(order);
        PedidoBookDTO pedidoBookDTO = new PedidoBookDTO();
        pedidoBookDTO.setQuantity(orderDTO.getQuantity());
        pedidoBookDTO.setBookId(orderDTO.getBookId());
        pedidoProducer.sendMessage(pedidoBookDTO);
        return convertToDTO(savedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private PedidoDTO convertToDTO(Pedido order) {
        PedidoDTO orderDTO = new PedidoDTO();
        orderDTO.setId(order.getId());
        orderDTO.setBookTitle(order.getBookTitle());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTotalPrice(order.getTotalPrice());
        return orderDTO;
    }

    private Pedido convertToEntity(PedidoDTO orderDTO) {
        Pedido order = new Pedido();
        order.setId(orderDTO.getId());
        order.setBookTitle(orderDTO.getBookTitle());
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalPrice(orderDTO.getTotalPrice());
        return order;
    }
}
