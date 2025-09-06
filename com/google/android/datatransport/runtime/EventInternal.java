package com.google.android.datatransport.runtime;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class EventInternal {
    public static abstract class Builder {
        public final Builder addMetadata(String s, int v) {
            this.getAutoMetadata().put(s, String.valueOf(v));
            return this;
        }

        public final Builder addMetadata(String s, long v) {
            this.getAutoMetadata().put(s, String.valueOf(v));
            return this;
        }

        public final Builder addMetadata(String s, String s1) {
            this.getAutoMetadata().put(s, s1);
            return this;
        }

        public abstract EventInternal build();

        protected abstract Map getAutoMetadata();

        protected abstract Builder setAutoMetadata(Map arg1);

        public abstract Builder setCode(Integer arg1);

        public abstract Builder setEncodedPayload(EncodedPayload arg1);

        public abstract Builder setEventMillis(long arg1);

        public abstract Builder setTransportName(String arg1);

        public abstract Builder setUptimeMillis(long arg1);
    }

    public static Builder builder() {
        return new com.google.android.datatransport.runtime.AutoValue_EventInternal.Builder().setAutoMetadata(new HashMap());
    }

    public final String get(String s) {
        String s1 = (String)this.getAutoMetadata().get(s);
        return s1 == null ? "" : s1;
    }

    protected abstract Map getAutoMetadata();

    public abstract Integer getCode();

    public abstract EncodedPayload getEncodedPayload();

    public abstract long getEventMillis();

    public final int getInteger(String s) {
        String s1 = (String)this.getAutoMetadata().get(s);
        return s1 == null ? 0 : ((int)Integer.valueOf(s1));
    }

    public final long getLong(String s) {
        String s1 = (String)this.getAutoMetadata().get(s);
        return s1 == null ? 0L : ((long)Long.valueOf(s1));
    }

    public final Map getMetadata() {
        return Collections.unmodifiableMap(this.getAutoMetadata());
    }

    public final String getOrDefault(String s, String s1) {
        String s2 = (String)this.getAutoMetadata().get(s);
        return s2 == null ? s1 : s2;
    }

    @Deprecated
    public byte[] getPayload() {
        return this.getEncodedPayload().getBytes();
    }

    public abstract String getTransportName();

    public abstract long getUptimeMillis();

    public Builder toBuilder() {
        return new com.google.android.datatransport.runtime.AutoValue_EventInternal.Builder().setTransportName(this.getTransportName()).setCode(this.getCode()).setEncodedPayload(this.getEncodedPayload()).setEventMillis(this.getEventMillis()).setUptimeMillis(this.getUptimeMillis()).setAutoMetadata(new HashMap(this.getAutoMetadata()));
    }
}

