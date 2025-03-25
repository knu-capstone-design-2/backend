/* package com.example.monitoring.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monitoring.repository.KafkaResourceMetricsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafakaComsumerService {

    @Autowired
    private KafkaResourceMetricsRepository resourceMetricsRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    // kafka로부터 "hostMetrics" 토픽 데이터를 받음
    @KafkaListener(topics = "hostMetrics", groupId = "monitoring")
    public void cousumeHostMetrics(String data) {
        System.out.println("Host Metrics : " + data);
        saveMetrics(data, "host", null);
    }

    // kafka로부터 "containerMetrics" 토픽 데이터를 받음
    @KafkaListener(topics = "containerMetrics", groupId = "monitoring")
    public void cousumeContainerMetrics(String data) {
        System.out.println("Container Metrics : " + data);

        String containerId = extractContainerId(data);
        saveMetrics(data, "host", containerId);
    }

} */
