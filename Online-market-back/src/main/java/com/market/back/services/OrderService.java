package com.market.back.services;

import com.market.back.dto.OrderDTO;
import com.market.back.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDTO createOrder(OrderDTO order);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
}
