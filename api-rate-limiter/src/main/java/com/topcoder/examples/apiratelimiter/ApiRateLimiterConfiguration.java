package com.topcoder.examples.apiratelimiter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiRateLimiterConfiguration {

    @Bean("apiRateLimiter")
    public ApiRateLimiter apiRateLimiter(LimiterPolicy limiterPolicy) {
        return new ApiRateLimiter(limiterPolicy);
    }

    @Bean("limiterPolicy")
    public LimiterPolicy limiterPolicy() {
        int limit = Integer.parseInt(System.getProperty("rate.limit", "200"));
        return new SlidingWindowPolicy(limit);
    }
}
