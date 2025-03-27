package com.example.monitoring.service;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class ComputerResourceMonitor {
    public double getComputerCpuUsage(){
        //CPU 사용량 %형태로 바꾸기 위해서 100 곱했음
        OperatingSystemMXBean osBean=(OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        return osBean.getSystemCpuLoad()*100;
        //getSystemCpuLoad() : Returns the "recent cpu usage" for the whole system
        //전체 시스템 cpu 사용량임, process 아님
    }
    public double getComputerFreeMemory(){
        //memory free
        //1024들로 나눠주는 건 바이트 형식으로 나오기 때문에 GB로 나타내기 위해서임
        //소수로 안나눠주면 소숫점이 없어짐
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getFreePhysicalMemorySize()/1024.0/1024.0/1024.0;
    }
    public double getComputerTotalMemory(){
        //total memory
        //1024들로 나눠주는 건 바이트 형식으로 나오기 때문에 GB로 나타내기 위해서임
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getTotalPhysicalMemorySize()/1024.0/1024.0/1024.0;
        //getTotalPhysicalMemorySize() : Returns the total amount of physical memory in bytes
    }
    public double getComputerMemoryUsage(){
        //사용중인 메모리
        return getComputerTotalMemory()-getComputerFreeMemory();
        //getFreePhysicalMemorySize() : Returns the amount of free physical memory in bytes.
    }

}
/**package com.example.monitoring.service;

import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class ComputerResourceMonitor {
    public static void main(String [] args) throws Exception {
        try{
            OperatingSystemMXBean mxBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

            File root = null;

            for (int i = 0; i < 100; i++) {

                root = new File("/");

                System.out.println("====================================");
                System.out.println("CPU : " + String.format("%.2f" , mxBean.getSystemCpuLoad() * 100));
                System.out.println("Memory Free : " + String.format("%.2f", (double)mxBean.getFreePhysicalMemorySize()/1024/1024/1024));
                System.out.println("Memory Total : " + String.format("%.2f" , (double)mxBean.getTotalPhysicalMemorySize()/1024/1024/1024));
                System.out.println("Disk Free : " + Math.round(root.getUsableSpace() / Math.pow(1024 , 3)));
                System.out.println("Disk Total : " + Math.round(root.getTotalSpace() / Math.pow(1024 , 3)));
                System.out.println("====================================");

                // 10초마다 사용량 조회
                Thread.sleep(10000);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
*/