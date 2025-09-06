package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.time.Clock;
import java.util.Map;

final class AutoValue_SchedulerConfig extends SchedulerConfig {
    private final Clock clock;
    private final Map values;

    AutoValue_SchedulerConfig(Clock clock0, Map map0) {
        if(clock0 == null) {
            throw new NullPointerException("Null clock");
        }
        this.clock = clock0;
        if(map0 == null) {
            throw new NullPointerException("Null values");
        }
        this.values = map0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof SchedulerConfig) {
            Clock clock0 = ((SchedulerConfig)object0).getClock();
            if(this.clock.equals(clock0)) {
                Map map0 = ((SchedulerConfig)object0).getValues();
                return this.values.equals(map0);
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    Clock getClock() {
        return this.clock;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    Map getValues() {
        return this.values;
    }

    @Override
    public int hashCode() {
        return (this.clock.hashCode() ^ 1000003) * 1000003 ^ this.values.hashCode();
    }

    @Override
    public String toString() {
        return "SchedulerConfig{clock=" + this.clock + ", values=" + this.values + "}";
    }
}

