package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.annotations.Encodable.Field;
import java.util.List;

public abstract class LogRequest {
    public static abstract class Builder {
        public abstract LogRequest build();

        public abstract Builder setClientInfo(ClientInfo arg1);

        public abstract Builder setLogEvents(List arg1);

        abstract Builder setLogSource(Integer arg1);

        abstract Builder setLogSourceName(String arg1);

        public abstract Builder setQosTier(QosTier arg1);

        public abstract Builder setRequestTimeMs(long arg1);

        public abstract Builder setRequestUptimeMs(long arg1);

        public Builder setSource(int v) {
            return this.setLogSource(v);
        }

        public Builder setSource(String s) {
            return this.setLogSourceName(s);
        }
    }

    public static Builder builder() {
        return new com.google.android.datatransport.cct.internal.AutoValue_LogRequest.Builder();
    }

    public abstract ClientInfo getClientInfo();

    @Field(name = "logEvent")
    public abstract List getLogEvents();

    public abstract Integer getLogSource();

    public abstract String getLogSourceName();

    public abstract QosTier getQosTier();

    public abstract long getRequestTimeMs();

    public abstract long getRequestUptimeMs();
}

