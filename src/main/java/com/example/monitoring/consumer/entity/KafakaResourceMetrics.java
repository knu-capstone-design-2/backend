package com.example.monitoring.consumer.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ResourceMetrics")
@Getter
@Setter
public class KafakaResourceMetrics {

    @Id  // set key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // increase ID automatically
    private Long id;

    private String sourceType;  // "host" or "container"
    private String containerId;    // container; id, host; NULL

    //추후 추가 (멘토님과 상의 후)
    private double cpuUsage;
    private double memoryUsage;
    //private double cache;

    private LocalDateTime timeStamp;

    
    public KafakaResourceMetrics() {
        this.cpuUsage = 0;
        this.memoryUsage = 0;
        this.timeStamp = LocalDateTime.now();
    }

    public String toString() {
        return "source type : " + sourceType +
         "\n[cpuUsage : " + cpuUsage + ", memoryUsage : " + memoryUsage + "]";
    }

}
