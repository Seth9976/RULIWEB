package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

public final class TimeModule_UptimeClockFactory implements Factory {
    static final class InstanceHolder {
        private static final TimeModule_UptimeClockFactory INSTANCE;

        static {
            InstanceHolder.INSTANCE = new TimeModule_UptimeClockFactory();
        }

        static TimeModule_UptimeClockFactory access$000() {
            return InstanceHolder.INSTANCE;
        }
    }

    public static TimeModule_UptimeClockFactory create() {
        return InstanceHolder.access$000();
    }

    public Clock get() {
        return TimeModule_UptimeClockFactory.uptimeClock();
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static Clock uptimeClock() {
        return (Clock)Preconditions.checkNotNull(TimeModule.uptimeClock(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

