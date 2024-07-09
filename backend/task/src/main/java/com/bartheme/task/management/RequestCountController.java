package com.bartheme.task.management;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("api/v1/management")
@RequiredArgsConstructor
public class RequestCountController {
    private final RequestCounterFilter requestCounterFilter;

    @GetMapping("/request/count")
    public Map<String, Map<String, AtomicInteger>> getRequestCount() {
        return requestCounterFilter.getRequestCountMap();
    }
}