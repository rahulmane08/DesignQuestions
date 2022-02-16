package com.topcoder.examples.apiratelimiter;

public interface LimiterPolicy {
    boolean limit();
}
