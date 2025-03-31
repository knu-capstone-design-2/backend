package com.example.monitoring.kafka;

import com.example.monitoring.service.kafka.producer.KafkaProducerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class KafkaServiceTest {

    @MockitoBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Test
    @DisplayName("KafkaProducerService sends message to Kafka")
    void sendMessageTest() {
        String topic = "test-topic";
        String message = "Hello Kafka";

        kafkaProducerService.sendMessage(topic, message);

        verify(kafkaTemplate, times(1)).send(topic, message);
    }
}
