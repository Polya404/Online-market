package com.market.back.services;

import com.market.back.dto.OrderDTO;
import com.market.back.models.Order;
import com.market.back.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(orderDTO.getUser());
        order.setProductIds(orderDTO.getProductIds());
        orderRepository.save(order);
        // save order in PostgreSQL

        // Отправьте сообщение в Kafka при создании заказа
         kafkaProducerService.sendOrderUpdate(order.toJson());
    }
}

