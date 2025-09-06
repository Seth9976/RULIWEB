package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

public class TestClock implements Clock {
    private final AtomicLong timestamp;

    public TestClock(long v) {
        this.timestamp = new AtomicLong(v);
    }

    public void advance(long v) {
        if(v < 0L) {
            throw new IllegalArgumentException("cannot advance time backwards.");
        }
        this.timestamp.addAndGet(v);
    }

    @Override  // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return this.timestamp.get();
    }

    public void tick() {
        this.advance(1L);
    }
}

