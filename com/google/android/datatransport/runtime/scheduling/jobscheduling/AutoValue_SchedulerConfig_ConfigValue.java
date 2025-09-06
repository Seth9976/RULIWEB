package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import java.util.Set;

final class AutoValue_SchedulerConfig_ConfigValue extends ConfigValue {
    static final class Builder extends com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder {
        private Long delta;
        private Set flags;
        private Long maxAllowedDelay;

        @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue$Builder
        public ConfigValue build() {
            String s = this.delta == null ? " delta" : "";
            if(this.maxAllowedDelay == null) {
                s = s + " maxAllowedDelay";
            }
            if(this.flags == null) {
                s = s + " flags";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_SchedulerConfig_ConfigValue(((long)this.delta), ((long)this.maxAllowedDelay), this.flags, null);
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue$Builder
        public com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setDelta(long v) {
            this.delta = v;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue$Builder
        public com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setFlags(Set set0) {
            if(set0 == null) {
                throw new NullPointerException("Null flags");
            }
            this.flags = set0;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue$Builder
        public com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long v) {
            this.maxAllowedDelay = v;
            return this;
        }
    }

    private final long delta;
    private final Set flags;
    private final long maxAllowedDelay;

    private AutoValue_SchedulerConfig_ConfigValue(long v, long v1, Set set0) {
        this.delta = v;
        this.maxAllowedDelay = v1;
        this.flags = set0;
    }

    AutoValue_SchedulerConfig_ConfigValue(long v, long v1, Set set0, com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue.1 autoValue_SchedulerConfig_ConfigValue$10) {
        this(v, v1, set0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof ConfigValue) {
            long v = ((ConfigValue)object0).getDelta();
            if(this.delta == v) {
                long v1 = ((ConfigValue)object0).getMaxAllowedDelay();
                if(this.maxAllowedDelay == v1) {
                    Set set0 = ((ConfigValue)object0).getFlags();
                    return this.flags.equals(set0);
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue
    long getDelta() {
        return this.delta;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue
    Set getFlags() {
        return this.flags;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$ConfigValue
    long getMaxAllowedDelay() {
        return this.maxAllowedDelay;
    }

    @Override
    public int hashCode() {
        return this.flags.hashCode() ^ ((((int)(this.delta ^ this.delta >>> 0x20)) ^ 1000003) * 1000003 ^ ((int)(this.maxAllowedDelay ^ this.maxAllowedDelay >>> 0x20))) * 1000003;
    }

    @Override
    public String toString() {
        return "ConfigValue{delta=" + this.delta + ", maxAllowedDelay=" + this.maxAllowedDelay + ", flags=" + this.flags + "}";
    }

    class com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue.1 {
    }

}

