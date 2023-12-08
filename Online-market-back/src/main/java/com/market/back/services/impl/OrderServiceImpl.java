package com.market.back.services.impl;

import com.market.back.dto.OrderDTO;
import com.market.back.models.Order;
import com.market.back.repositories.OrderRepository;
import com.market.back.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(orderDTO.getUser());
        order.setProductIds(orderDTO.getProductIds());
        orderRepository.save(order);

        // Отправьте сообщение в Kafka при создании заказа
         kafkaProducerService.sendOrderUpdate(order.toJson());
    }
}

