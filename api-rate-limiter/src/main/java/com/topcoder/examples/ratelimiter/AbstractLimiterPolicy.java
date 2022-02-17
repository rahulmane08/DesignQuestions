package com.topcoder.examples.ratelimiter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractLimiterPolicy implements LimiterPolicy {
    protected final int limit;
    protected final long windowTime;
}
