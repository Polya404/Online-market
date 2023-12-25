package com.market.back.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.back.models.Order;
import com.market.back.models.User;
import lombok.*;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private User user;
    private Set<Long> productIds;

    public static OrderDTO fromEntity(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.id = order.getId();
        orderDTO.user = order.getUser();
        orderDTO.productIds = order.getProductIds();
        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUser(orderDTO.getUser());
        order.setProductIds(orderDTO.getProductIds());
        return order;
    }

    @SneakyThrows
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
