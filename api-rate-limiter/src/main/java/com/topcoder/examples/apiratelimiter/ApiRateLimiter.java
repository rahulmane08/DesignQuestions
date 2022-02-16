package com.topcoder.examples.apiratelimiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@AllArgsConstructor
public class ApiRateLimiter extends HandlerInterceptorAdapter {
    private final AbstractLimiterPolicy policy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (policy.limit()) {
            return true;
        }
        return false;
    }
}
