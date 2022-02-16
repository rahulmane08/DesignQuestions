package com.topcoder.examples.apiratelimiter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractLimiterPolicy implements LimiterPolicy {
    protected final int limit;
}
