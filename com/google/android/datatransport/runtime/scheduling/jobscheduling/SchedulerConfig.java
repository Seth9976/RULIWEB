package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo.Builder;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class SchedulerConfig {
    public static class Builder {
        private Clock clock;
        private Map values;

        public Builder() {
            this.values = new HashMap();
        }

        public Builder addConfig(Priority priority0, ConfigValue schedulerConfig$ConfigValue0) {
            this.values.put(priority0, schedulerConfig$ConfigValue0);
            return this;
        }

        public SchedulerConfig build() {
            if(this.clock == null) {
                throw new NullPointerException("missing required property: clock");
            }
            if(this.values.keySet().size() < Priority.values().length) {
                throw new IllegalStateException("Not all priorities have been configured");
            }
            Map map0 = this.values;
            this.values = new HashMap();
            return SchedulerConfig.create(this.clock, map0);
        }

        public Builder setClock(Clock clock0) {
            this.clock = clock0;
            return this;
        }
    }

    public static abstract class ConfigValue {
        public static abstract class com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder {
            public abstract ConfigValue build();

            public abstract com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setDelta(long arg1);

            public abstract com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setFlags(Set arg1);

            public abstract com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long arg1);
        }

        public static com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder builder() {
            return new com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue.Builder().setFlags(Collections.EMPTY_SET);
        }

        abstract long getDelta();

        abstract Set getFlags();

        abstract long getMaxAllowedDelay();
    }

    public static enum Flag {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING;

    }

    private static final long BACKOFF_LOG_BASE = 10000L;
    private static final long ONE_SECOND = 1000L;
    private static final long THIRTY_SECONDS = 30000L;
    private static final long TWENTY_FOUR_HOURS = 86400000L;

    private long adjustedExponentialBackoff(int v, long v1) {
        return v1 <= 1L ? ((long)(Math.pow(3.0, v - 1) * ((double)v1) * Math.max(1.0, 9.21034 / Math.log(2L * ((long)(v - 1)))))) : ((long)(Math.pow(3.0, v - 1) * ((double)v1) * Math.max(1.0, 9.21034 / Math.log(v1 * ((long)(v - 1))))));
    }

    public static Builder builder() {
        return new Builder();
    }

    public JobInfo.Builder configureJob(JobInfo.Builder jobInfo$Builder0, Priority priority0, long v, int v1) {
        jobInfo$Builder0.setMinimumLatency(this.getScheduleDelay(priority0, v, v1));
        this.populateFlags(jobInfo$Builder0, ((ConfigValue)this.getValues().get(priority0)).getFlags());
        return jobInfo$Builder0;
    }

    static SchedulerConfig create(Clock clock0, Map map0) {
        return new AutoValue_SchedulerConfig(clock0, map0);
    }

    abstract Clock getClock();

    public static SchedulerConfig getDefault(Clock clock0) {
        Builder schedulerConfig$Builder0 = SchedulerConfig.builder();
        ConfigValue schedulerConfig$ConfigValue0 = ConfigValue.builder().setDelta(30000L).setMaxAllowedDelay(86400000L).build();
        Builder schedulerConfig$Builder1 = schedulerConfig$Builder0.addConfig(Priority.DEFAULT, schedulerConfig$ConfigValue0);
        ConfigValue schedulerConfig$ConfigValue1 = ConfigValue.builder().setDelta(1000L).setMaxAllowedDelay(86400000L).build();
        Builder schedulerConfig$Builder2 = schedulerConfig$Builder1.addConfig(Priority.HIGHEST, schedulerConfig$ConfigValue1);
        ConfigValue schedulerConfig$ConfigValue2 = ConfigValue.builder().setDelta(86400000L).setMaxAllowedDelay(86400000L).setFlags(SchedulerConfig.immutableSetOf(new Flag[]{Flag.DEVICE_IDLE})).build();
        return schedulerConfig$Builder2.addConfig(Priority.VERY_LOW, schedulerConfig$ConfigValue2).setClock(clock0).build();
    }

    public Set getFlags(Priority priority0) {
        return ((ConfigValue)this.getValues().get(priority0)).getFlags();
    }

    public long getScheduleDelay(Priority priority0, long v, int v1) {
        long v2 = this.getClock().getTime();
        ConfigValue schedulerConfig$ConfigValue0 = (ConfigValue)this.getValues().get(priority0);
        return Math.min(Math.max(this.adjustedExponentialBackoff(v1, schedulerConfig$ConfigValue0.getDelta()), v - v2), schedulerConfig$ConfigValue0.getMaxAllowedDelay());
    }

    abstract Map getValues();

    private static Set immutableSetOf(Object[] arr_object) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(arr_object)));
    }

    private void populateFlags(JobInfo.Builder jobInfo$Builder0, Set set0) {
        if(set0.contains(Flag.NETWORK_UNMETERED)) {
            jobInfo$Builder0.setRequiredNetworkType(2);
        }
        else {
            jobInfo$Builder0.setRequiredNetworkType(1);
        }
        if(set0.contains(Flag.DEVICE_CHARGING)) {
            jobInfo$Builder0.setRequiresCharging(true);
        }
        if(set0.contains(Flag.DEVICE_IDLE)) {
            jobInfo$Builder0.setRequiresDeviceIdle(true);
        }
    }
}

