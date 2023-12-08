package com.market.back.services;

import com.market.back.dto.OrderDTO;

public interface OrderService {
    void createOrder(OrderDTO orderDTO);
}
