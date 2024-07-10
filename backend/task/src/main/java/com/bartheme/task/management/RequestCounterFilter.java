package com.bartheme.task.management;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@WebFilter(urlPatterns = "/api/v1/*")
public class RequestCounterFilter implements Filter {

    private final Map<String, Map<String, AtomicInteger>> requestCountMap = new HashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        requestCountMap.putIfAbsent(method, new HashMap<>());
        requestCountMap.get(method).putIfAbsent(requestURI, new AtomicInteger(0));
        requestCountMap.get(method).get(requestURI).incrementAndGet();

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public Map<String, Map<String, AtomicInteger>> getRequestCountMap() {
        return requestCountMap;
    }
}
