package com.google.firebase.installations.time;

public class SystemClock implements Clock {
    private static SystemClock singleton;

    @Override  // com.google.firebase.installations.time.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static SystemClock getInstance() {
        if(SystemClock.singleton == null) {
            SystemClock.singleton = new SystemClock();
        }
        return SystemClock.singleton;
    }
}

