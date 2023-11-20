package com.veersa.ems.controller.systemInfoController;

import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Profile("dev")
@RestController
@RequestMapping("/system")
public class SystemInfo {

    @GetMapping(value = "/view-system-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object > systemInfo()
    {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object > map = new HashMap<>();
        map.put("Total memory: ", runtime.totalMemory());
        map.put("Available processor: ", runtime.availableProcessors());
        map.put("Max memory: ", runtime.maxMemory());
        map.put("Free memory: ", runtime.freeMemory());

        return map;

    }
}
