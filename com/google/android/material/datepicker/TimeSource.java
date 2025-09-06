package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

class TimeSource {
    private static final TimeSource SYSTEM_TIME_SOURCE;
    private final Long fixedTimeMs;
    private final TimeZone fixedTimeZone;

    static {
        TimeSource.SYSTEM_TIME_SOURCE = new TimeSource(null, null);
    }

    private TimeSource(Long long0, TimeZone timeZone0) {
        this.fixedTimeMs = long0;
        this.fixedTimeZone = timeZone0;
    }

    static TimeSource fixed(long v) {
        return new TimeSource(v, null);
    }

    static TimeSource fixed(long v, TimeZone timeZone0) {
        return new TimeSource(v, timeZone0);
    }

    Calendar now() {
        return this.now(this.fixedTimeZone);
    }

    Calendar now(TimeZone timeZone0) {
        Calendar calendar0 = timeZone0 == null ? Calendar.getInstance() : Calendar.getInstance(timeZone0);
        Long long0 = this.fixedTimeMs;
        if(long0 != null) {
            calendar0.setTimeInMillis(((long)long0));
        }
        return calendar0;
    }

    static TimeSource system() {
        return TimeSource.SYSTEM_TIME_SOURCE;
    }
}

