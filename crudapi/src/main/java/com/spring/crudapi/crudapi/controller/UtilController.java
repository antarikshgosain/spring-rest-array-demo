package com.spring.crudapi.crudapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

@Slf4j
@RestController
public class UtilController {

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping(value = "/health")
    public String getHealth(){
        log.info("--> UtilController.getHealth() reached");
        return "Health - OK";
    }

    @GetMapping(value = "/server-info")
    public Map<String, String> getServerInfo() {
        log.info("--> UtilController.getServerInfo() reached");

        Map<String, String> deviceInfo = new HashMap<>();

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes != null) {
                    StringBuilder macAddress = new StringBuilder();
                    for (byte b : macBytes) {
                        macAddress.append(String.format("%02X:", b));
                    }
                    if (macAddress.length() > 0) {
                        macAddress.deleteCharAt(macAddress.length() - 1);
                    }
                    deviceInfo.put("Device MAC Address", macAddress.toString());
                }

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress()) {
                        deviceInfo.put("Device IP Address", inetAddress.getHostAddress());
                    }
                }
            }
            // Get the Spring server port
            int serverPort = serverProperties.getPort();
            deviceInfo.put("Active Server Port", String.valueOf(serverPort));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceInfo;
    }

    @GetMapping(value = "/hardware-info")
    public Map<String, Object> getHardwareInfo() {
        log.info("--> UtilController.getHardwareInfo() reached");
        Map<String, Object> systemInfo = new HashMap<>();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hardware = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        GlobalMemory memory = hardware.getMemory();
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();

        // CPU usage
        double cpuUsage = calculateCpuUsage(hardware);
        systemInfo.put("CPU Usage (%)", cpuUsage * 10);

        // RAM usage (in MB)
        long totalMemory = memory.getTotal();
        long usedMemory = totalMemory - memory.getAvailable();
        systemInfo.put("RAM Total (MB)", FormatUtil.formatBytes(totalMemory));
        systemInfo.put("RAM Used (MB)", FormatUtil.formatBytes(usedMemory));
        Double ramTotal = Double.valueOf(FormatUtil.formatBytes(totalMemory).split(" ")[0]);
        Double ramUsed = Double.valueOf(FormatUtil.formatBytes(usedMemory).split(" ")[0]);
        systemInfo.put("RAM Usage (%)", (ramUsed / ramTotal) * 100 );

        // Disk information
        List<Map<String, Object>> diskInfoList = new ArrayList<>();
        for (OSFileStore fileStore : fileStores) {
            Map<String, Object> diskInfo = new HashMap<>();
            String drive = fileStore.getName();
            long totalSpace = fileStore.getTotalSpace();
            long usableSpace = fileStore.getUsableSpace();
            long usedSpace = totalSpace - usableSpace;
            double diskUsage = (double) usedSpace / totalSpace * 100;

            diskInfo.put("Drive", drive);
            diskInfo.put("Total Space", FormatUtil.formatBytes(totalSpace));
            diskInfo.put("Usable Space", FormatUtil.formatBytes(usableSpace));
            diskInfo.put("Used Space (MB)", FormatUtil.formatBytes(usedSpace));
            diskInfo.put("Disk Usage (%)", diskUsage);

            diskInfoList.add(diskInfo);
        }
        systemInfo.put("Disk Information", diskInfoList);

        return systemInfo;
    }

    private double calculateCpuUsage(HardwareAbstractionLayer hardware) {
        // Initialize the tick arrays
        long[] prevTicks = hardware.getProcessor().getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }
        long[] ticks = hardware.getProcessor().getSystemCpuLoadTicks();

        // Calculate CPU usage
        long prevIdle = prevTicks[oshi.hardware.CentralProcessor.TickType.IDLE.getIndex()];
        long idle = ticks[oshi.hardware.CentralProcessor.TickType.IDLE.getIndex()];

        long prevTotal = 0;
        long total = 0;
        for (int i = 0; i < prevTicks.length; i++) {
            prevTotal += prevTicks[i];
            total += ticks[i];
        }

        long totalDelta = total - prevTotal;
        long idleDelta = idle - prevIdle;

        return (1.0 - ((double) idleDelta / totalDelta)) * 100.0;
    }



}
