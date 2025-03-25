package com.example.monitoring.consumer.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.shaded.io.opentelemetry.proto.metrics.v1.ResourceMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.monitoring.consumer.entity.*;
import com.example.monitoring.consumer.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafakaComsumerService {

    @Autowired
    private KafkaResourceMetricsRepository resourceMetricsRepository = null;

    
    public KafakaComsumerService(KafkaResourceMetricsRepository resourceMetricsRepository) {
        this.resourceMetricsRepository = resourceMetricsRepository;
    }

    private ObjectMapper objectMapper = new ObjectMapper();
    

    /// -------------------------------------------------------
    ///     get topic data - "hostMetrics" from kafka
    /// -------------------------------------------------------
    @KafkaListener(topics = "hostMetrics", groupId = "group1", concurrency = "1")
    public void cousumeHostMetrics(ConsumerRecord<String,String> record) throws IOException 
    {
        String message = record.toString();
        
        System.out.println("Host Metrics : " + message);
        log.info("Consumed message : {}", message);

        saveMetrics(message, "host", null);
    }


    /// -------------------------------------------------------
    ///     get topic data - "containerMetrics" from kafka
    /// -------------------------------------------------------
    @KafkaListener(topics = "containerMetrics", groupId = "group1", concurrency = "1")
    public void cousumeContainerMetrics(ConsumerRecord<String,String> record) throws IOException 
    {
        String message = record.toString();

        System.out.println("Container Metrics : " + message);
        log.info("Consumed message : {}", message);

        String containerId = extractContainerId(message);
        saveMetrics(message, "host", containerId);
    }


    /// -------------------------------------------------------
    ///     JSON 데이터를 객체로 변환 후 DB에 저장 (임시)
    /// -------------------------------------------------------
    private void saveMetrics(String message, String sourceType, String containerId) {
        try {
            KafakaResourceMetrics metrics = objectMapper.readValue(message, KafakaResourceMetrics.class);
            metrics.setSourceType(sourceType);
            metrics.setContainerId(containerId);
            
            resourceMetricsRepository.save(metrics);
            
            System.out.println("Metrics saved to DB: " + metrics.toString());

        } catch (Exception e) {
            System.err.println("Error parsing message: " + e.getMessage());
        }
    }


    /// -------------------------------------------------------
    ///     extract container-id from json data
    /// -------------------------------------------------------
    private String extractContainerId(String message) {
        try {
            return objectMapper.readTree(message).get("containerId").asText();
        } catch (Exception e) {
            return null;
        }
    }

} 