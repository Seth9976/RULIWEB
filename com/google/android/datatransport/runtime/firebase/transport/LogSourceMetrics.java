package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.annotations.Encodable.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LogSourceMetrics {
    public static final class Builder {
        private List log_event_dropped_;
        private String log_source_;

        Builder() {
            this.log_source_ = "";
            this.log_event_dropped_ = new ArrayList();
        }

        public Builder addLogEventDropped(LogEventDropped logEventDropped0) {
            this.log_event_dropped_.add(logEventDropped0);
            return this;
        }

        public LogSourceMetrics build() {
            return new LogSourceMetrics(this.log_source_, Collections.unmodifiableList(this.log_event_dropped_));
        }

        public Builder setLogEventDroppedList(List list0) {
            this.log_event_dropped_ = list0;
            return this;
        }

        public Builder setLogSource(String s) {
            this.log_source_ = s;
            return this;
        }
    }

    private static final LogSourceMetrics DEFAULT_INSTANCE;
    private final List log_event_dropped_;
    private final String log_source_;

    static {
        LogSourceMetrics.DEFAULT_INSTANCE = new Builder().build();
    }

    LogSourceMetrics(String s, List list0) {
        this.log_source_ = s;
        this.log_event_dropped_ = list0;
    }

    public static LogSourceMetrics getDefaultInstance() {
        return LogSourceMetrics.DEFAULT_INSTANCE;
    }

    @Field(name = "logEventDropped")
    public List getLogEventDroppedList() {
        return this.log_event_dropped_;
    }

    public String getLogSource() {
        return this.log_source_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

