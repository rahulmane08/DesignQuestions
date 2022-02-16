package com.topcoder.examples.ratelimiter;

public class SlidingWindowPolicy extends AbstractLimiterPolicy {
    public SlidingWindowPolicy(int limit) {
        super(limit);
    }

    @Override
    public boolean limit() {
        return false;
    }
}
