package com.example.monitoring.controller;

import com.example.monitoring.service.kafka.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message, @RequestParam String topic) {
        kafkaProducerService.sendMessage(topic, message);

        return "Topic: " + topic + ", " + "Message: " + message;
    }
}
