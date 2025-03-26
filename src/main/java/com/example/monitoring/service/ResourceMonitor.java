package com.example.monitoring.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ResourceMonitor {

    public Map<String, String> getContainerResources(String containerId) {
        Map<String, String> resourceUsage = new HashMap<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("docker", "stats", containerId, "--no-stream", "--format", "{{.CPUPerc}} {{.MemUsage}}");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            reader.close();

            if (line != null && !line.isEmpty()) {
                String[] usageData = line.split(" ");
                if (usageData.length >= 2) {
                    resourceUsage.put("CPU", usageData[0]); // "5.32%"
                    resourceUsage.put("Memory", usageData[1] + " " + usageData[2]); // "50MiB / 2GiB"
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to get container resources: " + e.getMessage());
        }
        return resourceUsage;
    }

    /**
     * 특정 컨테이너의 CPU 사용량을 double 값으로 반환하는 메서드
     * @param containerId 모니터링할 컨테이너의 ID
     * @return CPU 사용량 (예: 5.32)
     */
    public double getContainerCpuUsage(String containerId) {
        Map<String, String> resources = getContainerResources(containerId);
        if (resources.containsKey("CPU")) {
            String cpuString = resources.get("CPU").replace("%", ""); // "%" 제거
            try {
                return Double.parseDouble(cpuString); // 문자열을 double로 변환
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse CPU usage: " + cpuString);
            }
        }
        return -1.0; // CPU 사용량을 가져오지 못했을 경우 기본값
    }
}