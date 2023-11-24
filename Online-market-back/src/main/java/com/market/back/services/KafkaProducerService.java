package com.market.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderUpdate(String orderJson) {
        kafkaTemplate.send("order-updates", orderJson);
    }
}

