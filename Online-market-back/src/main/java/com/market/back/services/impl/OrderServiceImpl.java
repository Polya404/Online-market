package com.market.back.services.impl;

import com.market.back.dto.OrderDTO;
import com.market.back.repositories.OrderRepository;
import com.market.back.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public OrderDTO createOrder(OrderDTO order) {
        kafkaProducerService.sendOrderUpdate(order.toJson());
        return OrderDTO.fromEntity(orderRepository.save(OrderDTO.toEntity(order)));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return OrderDTO.fromEntity(orderRepository.findById(id).orElseThrow());
    }
}

