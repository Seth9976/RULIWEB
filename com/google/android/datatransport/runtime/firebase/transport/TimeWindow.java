package com.google.android.datatransport.runtime.firebase.transport;

public final class TimeWindow {
    public static final class Builder {
        private long end_ms_;
        private long start_ms_;

        Builder() {
            this.start_ms_ = 0L;
            this.end_ms_ = 0L;
        }

        public TimeWindow build() {
            return new TimeWindow(this.start_ms_, this.end_ms_);
        }

        public Builder setEndMs(long v) {
            this.end_ms_ = v;
            return this;
        }

        public Builder setStartMs(long v) {
            this.start_ms_ = v;
            return this;
        }
    }

    private static final TimeWindow DEFAULT_INSTANCE;
    private final long end_ms_;
    private final long start_ms_;

    static {
        TimeWindow.DEFAULT_INSTANCE = new Builder().build();
    }

    TimeWindow(long v, long v1) {
        this.start_ms_ = v;
        this.end_ms_ = v1;
    }

    public static TimeWindow getDefaultInstance() {
        return TimeWindow.DEFAULT_INSTANCE;
    }

    public long getEndMs() {
        return this.end_ms_;
    }

    public long getStartMs() {
        return this.start_ms_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

