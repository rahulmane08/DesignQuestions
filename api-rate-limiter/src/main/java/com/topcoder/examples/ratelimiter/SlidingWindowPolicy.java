package com.topcoder.examples.ratelimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SlidingWindowPolicy extends AbstractLimiterPolicy {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Queue<Long> requestQueue = new ConcurrentLinkedQueue<>();

    public SlidingWindowPolicy(int limit, long windowTime) {
        super(limit, windowTime);
    }

    @Override
    public boolean limit() {

        long currentRequestTs = System.currentTimeMillis();
        if (isUnderLimit(currentRequestTs)) {
            // window is not full, request can be served.
            return true;
        }

        // window is currently full
        return adjustWindow(currentRequestTs);
    }

    private boolean adjustWindow(long currentRequestTs) {
        try {
            lock.writeLock().lock();
            long windowFloorTs = currentRequestTs - windowTime;
            while (!requestQueue.isEmpty() && requestQueue.peek() < windowFloorTs) {
                requestQueue.poll();
            }
            if (requestQueue.size() <= limit) {
                requestQueue.offer(currentRequestTs);
                return true;
            }
        } finally {
            if (lock.isWriteLockedByCurrentThread()) {
                lock.writeLock().unlock();
            }
        }
        return false;
    }

    private boolean isUnderLimit(long currentRequestTs) {
        // read the size of the queue if its less than the limit
        try {
            lock.readLock().lock();
            if (requestQueue.size() <= limit) {
                requestQueue.offer(currentRequestTs);
                return true;
            }
        } finally {
            lock.readLock().unlock();
        }
        return false;
    }


}
