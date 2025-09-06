package com.google.android.datatransport.cct.internal;

import java.util.Arrays;

final class AutoValue_LogEvent extends LogEvent {
    static final class Builder extends com.google.android.datatransport.cct.internal.LogEvent.Builder {
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        public LogEvent build() {
            String s = this.eventTimeMs == null ? " eventTimeMs" : "";
            if(this.eventUptimeMs == null) {
                s = s + " eventUptimeMs";
            }
            if(this.timezoneOffsetSeconds == null) {
                s = s + " timezoneOffsetSeconds";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_LogEvent(((long)this.eventTimeMs), this.eventCode, ((long)this.eventUptimeMs), this.sourceExtension, this.sourceExtensionJsonProto3, ((long)this.timezoneOffsetSeconds), this.networkConnectionInfo, null);
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        public com.google.android.datatransport.cct.internal.LogEvent.Builder setEventCode(Integer integer0) {
            this.eventCode = integer0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        public com.google.android.datatransport.cct.internal.LogEvent.Builder setEventTimeMs(long v) {
            this.eventTimeMs = v;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        public com.google.android.datatransport.cct.internal.LogEvent.Builder setEventUptimeMs(long v) {
            this.eventUptimeMs = v;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        public com.google.android.datatransport.cct.internal.LogEvent.Builder setNetworkConnectionInfo(NetworkConnectionInfo networkConnectionInfo0) {
            this.networkConnectionInfo = networkConnectionInfo0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        com.google.android.datatransport.cct.internal.LogEvent.Builder setSourceExtension(byte[] arr_b) {
            this.sourceExtension = arr_b;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        com.google.android.datatransport.cct.internal.LogEvent.Builder setSourceExtensionJsonProto3(String s) {
            this.sourceExtensionJsonProto3 = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.LogEvent$Builder
        public com.google.android.datatransport.cct.internal.LogEvent.Builder setTimezoneOffsetSeconds(long v) {
            this.timezoneOffsetSeconds = v;
            return this;
        }
    }

    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    private AutoValue_LogEvent(long v, Integer integer0, long v1, byte[] arr_b, String s, long v2, NetworkConnectionInfo networkConnectionInfo0) {
        this.eventTimeMs = v;
        this.eventCode = integer0;
        this.eventUptimeMs = v1;
        this.sourceExtension = arr_b;
        this.sourceExtensionJsonProto3 = s;
        this.timezoneOffsetSeconds = v2;
        this.networkConnectionInfo = networkConnectionInfo0;
    }

    AutoValue_LogEvent(long v, Integer integer0, long v1, byte[] arr_b, String s, long v2, NetworkConnectionInfo networkConnectionInfo0, com.google.android.datatransport.cct.internal.AutoValue_LogEvent.1 autoValue_LogEvent$10) {
        this(v, integer0, v1, arr_b, s, v2, networkConnectionInfo0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof LogEvent) {
            long v = ((LogEvent)object0).getEventTimeMs();
            if(this.eventTimeMs == v) {
                Integer integer0 = this.eventCode;
                if(integer0 != null) {
                    if(integer0.equals(((LogEvent)object0).getEventCode())) {
                    label_10:
                        long v1 = ((LogEvent)object0).getEventUptimeMs();
                        if(this.eventUptimeMs == v1) {
                            byte[] arr_b = ((LogEvent)object0) instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent)(((LogEvent)object0))).sourceExtension : ((LogEvent)object0).getSourceExtension();
                            if(Arrays.equals(this.sourceExtension, arr_b)) {
                                String s = this.sourceExtensionJsonProto3;
                                if(s != null) {
                                    if(s.equals(((LogEvent)object0).getSourceExtensionJsonProto3())) {
                                    label_19:
                                        long v2 = ((LogEvent)object0).getTimezoneOffsetSeconds();
                                        if(this.timezoneOffsetSeconds == v2) {
                                            return this.networkConnectionInfo == null ? ((LogEvent)object0).getNetworkConnectionInfo() == null : this.networkConnectionInfo.equals(((LogEvent)object0).getNetworkConnectionInfo());
                                        }
                                    }
                                }
                                else if(((LogEvent)object0).getSourceExtensionJsonProto3() == null) {
                                    goto label_19;
                                }
                            }
                        }
                    }
                }
                else if(((LogEvent)object0).getEventCode() == null) {
                    goto label_10;
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public Integer getEventCode() {
        return this.eventCode;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    @Override  // com.google.android.datatransport.cct.internal.LogEvent
    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = Arrays.hashCode(this.sourceExtension);
        int v2 = ((((((((int)(this.eventTimeMs ^ this.eventTimeMs >>> 0x20)) ^ 1000003) * 1000003 ^ (this.eventCode == null ? 0 : this.eventCode.hashCode())) * 1000003 ^ ((int)(this.eventUptimeMs ^ this.eventUptimeMs >>> 0x20))) * 1000003 ^ v1) * 1000003 ^ (this.sourceExtensionJsonProto3 == null ? 0 : this.sourceExtensionJsonProto3.hashCode())) * 1000003 ^ ((int)(this.timezoneOffsetSeconds >>> 0x20 ^ this.timezoneOffsetSeconds))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo0 = this.networkConnectionInfo;
        if(networkConnectionInfo0 != null) {
            v = networkConnectionInfo0.hashCode();
        }
        return v2 ^ v;
    }

    @Override
    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + "}";
    }

    class com.google.android.datatransport.cct.internal.AutoValue_LogEvent.1 {
    }

}

