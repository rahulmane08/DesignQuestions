package com.topcoder.examples.ratelimiter;

public interface LimiterPolicy {
    boolean limit();
}
