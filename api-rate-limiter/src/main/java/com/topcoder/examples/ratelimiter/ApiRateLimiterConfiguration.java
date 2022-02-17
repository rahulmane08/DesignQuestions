package com.topcoder.examples.ratelimiter;

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
        long durationInMillis = Long.parseLong(
                System.getProperty("window.duration.millis", String.valueOf(60_000))); // default = 1min
        return new SlidingWindowPolicy(limit, durationInMillis);
    }
}
