package com.market.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ProductServiceImpl productService;

    @KafkaListener(topics = "order-updates")
    public void handleOrderUpdate(String orderJson) {
        // Разберите сообщение и выполните обновление данных в MongoDB
        // Возможно, вам потребуется сериализация и десериализация данных
        // и обновление данных в MongoDB
        // Например: productService.updateProductFromOrder(orderJson);
    }
}

