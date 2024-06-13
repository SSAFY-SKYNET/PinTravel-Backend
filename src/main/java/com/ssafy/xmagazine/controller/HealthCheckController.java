package com.ssafy.xmagazine.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Value("${server.env}")
    private String env;

    @Value("${server.port}")
    private String port;

    @Value("${server.serverAddress}")
    private String serverAddress;

    @Value("${server.serverName}")
    private String serverName;

    @GetMapping("/hc")
    public ResponseEntity<String> healthCheck() {
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("status", "OK");
        responseData.put("port", port);
        responseData.put("serverAddress", serverAddress);
        responseData.put("serverName", serverName);
        return ResponseEntity.ok(responseData.toString());
    }

    @GetMapping("env")
    public ResponseEntity<String> getEnv() {
        return ResponseEntity.ok(env);
    }

}
