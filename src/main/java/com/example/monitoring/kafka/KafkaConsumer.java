package com.example.monitoring.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
    
    @KafkaListener(topics = "exam-topic", groupId = "foo")
    public void consume(String message) throws IOException {
        log.info("Consumed message : {}", message);
    }
}