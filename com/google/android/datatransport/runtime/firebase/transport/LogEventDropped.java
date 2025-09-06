package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.proto.ProtoEnum;

public final class LogEventDropped {
    public static final class Builder {
        private long events_dropped_count_;
        private Reason reason_;

        Builder() {
            this.events_dropped_count_ = 0L;
            this.reason_ = Reason.REASON_UNKNOWN;
        }

        public LogEventDropped build() {
            return new LogEventDropped(this.events_dropped_count_, this.reason_);
        }

        public Builder setEventsDroppedCount(long v) {
            this.events_dropped_count_ = v;
            return this;
        }

        public Builder setReason(Reason logEventDropped$Reason0) {
            this.reason_ = logEventDropped$Reason0;
            return this;
        }
    }

    public static enum Reason implements ProtoEnum {
        REASON_UNKNOWN(0),
        MESSAGE_TOO_OLD(1),
        CACHE_FULL(2),
        PAYLOAD_TOO_BIG(3),
        MAX_RETRIES_REACHED(4),
        INVALID_PAYLOD(5),
        SERVER_ERROR(6);

        private final int number_;

        private Reason(int v1) {
            this.number_ = v1;
        }

        @Override  // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    private static final LogEventDropped DEFAULT_INSTANCE;
    private final long events_dropped_count_;
    private final Reason reason_;

    static {
        LogEventDropped.DEFAULT_INSTANCE = new Builder().build();
    }

    LogEventDropped(long v, Reason logEventDropped$Reason0) {
        this.events_dropped_count_ = v;
        this.reason_ = logEventDropped$Reason0;
    }

    public static LogEventDropped getDefaultInstance() {
        return LogEventDropped.DEFAULT_INSTANCE;
    }

    public long getEventsDroppedCount() {
        return this.events_dropped_count_;
    }

    public Reason getReason() {
        return this.reason_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

