package com.example.monitoring;
import com.example.monitoring.service.ResourceMonitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class ResourceMonitorTest {

    @Test
    void testGetContainerResources() {
        // 테스트할 컨테이너 ID (실제로 실행 중인 컨테이너 ID로 바꿔야 함!)
        String containerId = "913c988a365b";

        // ResourceMonitor 인스턴스 생성
        ResourceMonitor resourceMonitor = new ResourceMonitor();

        // 컨테이너 리소스 사용량 가져오기
        Map<String, String> resources = resourceMonitor.getContainerResources(containerId);

        // 결과 검증
        assertNotNull(resources, "결과가 null이 아님");
        assertTrue(resources.containsKey("CPU"), "CPU 사용량이 포함되어 있어야 함");
        assertTrue(resources.containsKey("Memory"), "Memory 사용량이 포함되어 있어야 함");

        // 결과 출력 (테스트 실행 시 확인)
        System.out.println("CPU 사용량: " + resources.get("CPU"));
        System.out.println("메모리 사용량: " + resources.get("Memory"));
    }
}
