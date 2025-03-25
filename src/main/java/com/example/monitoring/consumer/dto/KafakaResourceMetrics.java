/* package com.example.monitoring.consumer.dto;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kafkaResourceMetrics")
@Getter
@Setter
public class KafakaResourceMetrics {

    private Long id;

    private String sourceType;  //"host" or "container"
    private String contatinerId;    // container; id, host; NULL

    //추후 추가 (멘토님과 상의 후)
    private double cpuUsage;
    private double memoryUsage;

    private LocalDateTime timeStamp;


    public KafakaResourceMetrics() {
        this.timeStamp = LocalDateTime.now();
    }

}
 */