package com.google.crypto.tink.monitoring;

import com.google.errorprone.annotations.Immutable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Immutable
public final class MonitoringAnnotations {
    public static final class Builder {
        private HashMap builderEntries;

        public Builder() {
            this.builderEntries = new HashMap();
        }

        public Builder add(String s, String s1) {
            HashMap hashMap0 = this.builderEntries;
            if(hashMap0 == null) {
                throw new IllegalStateException("add cannot be called after build()");
            }
            hashMap0.put(s, s1);
            return this;
        }

        public Builder addAll(Map map0) {
            HashMap hashMap0 = this.builderEntries;
            if(hashMap0 == null) {
                throw new IllegalStateException("addAll cannot be called after build()");
            }
            hashMap0.putAll(map0);
            return this;
        }

        public MonitoringAnnotations build() {
            if(this.builderEntries == null) {
                throw new IllegalStateException("cannot call build() twice");
            }
            MonitoringAnnotations monitoringAnnotations0 = new MonitoringAnnotations(Collections.unmodifiableMap(this.builderEntries), null);
            this.builderEntries = null;
            return monitoringAnnotations0;
        }
    }

    public static final MonitoringAnnotations EMPTY;
    private final Map entries;

    static {
        MonitoringAnnotations.EMPTY = MonitoringAnnotations.newBuilder().build();
    }

    private MonitoringAnnotations(Map map0) {
        this.entries = map0;
    }

    MonitoringAnnotations(Map map0, com.google.crypto.tink.monitoring.MonitoringAnnotations.1 monitoringAnnotations$10) {
        this(map0);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof MonitoringAnnotations ? this.entries.equals(((MonitoringAnnotations)object0).entries) : false;
    }

    @Override
    public int hashCode() {
        return this.entries.hashCode();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Map toMap() {
        return this.entries;
    }

    @Override
    public String toString() {
        return this.entries.toString();
    }

    class com.google.crypto.tink.monitoring.MonitoringAnnotations.1 {
    }

}

