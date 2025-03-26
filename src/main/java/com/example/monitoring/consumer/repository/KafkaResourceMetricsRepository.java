package com.example.monitoring.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.monitoring.consumer.entity.KafakaResourceMetrics;

/// -------------------------------------------------------
///     - Jpa를 사용해 db 저장/조회
///     - kafka에서 받은 데이터를 db에 저장함
/// -------------------------------------------------------

@Repository
public interface KafkaResourceMetricsRepository extends JpaRepository<KafakaResourceMetrics, Long>{

    
} 
