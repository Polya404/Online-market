package com.market.back.controllers;

import com.market.back.models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    @PostMapping
    public ResponseEntity<Order> processOrder(Order order){
        return ResponseEntity.ok(order);
    }

}
