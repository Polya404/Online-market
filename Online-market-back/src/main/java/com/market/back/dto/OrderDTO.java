package com.market.back.dto;

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
}
