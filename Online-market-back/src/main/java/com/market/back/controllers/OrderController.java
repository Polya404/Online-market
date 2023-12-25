package com.market.back.controllers;

import com.market.back.dto.OrderDTO;
import com.market.back.models.Order;
import com.market.back.models.User;
import com.market.back.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid OrderDTO order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        orderService.createOrder(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
//    @PostMapping
//    public ResponseEntity<OrderDTO> createOrder(OrderDTO order){
//        return ResponseEntity.ok(orderService.createOrder(order));
//    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

}
