package com.google.android.datatransport.cct.internal;

public abstract class LogEvent {
    public static abstract class Builder {
        public abstract LogEvent build();

        public abstract Builder setEventCode(Integer arg1);

        public abstract Builder setEventTimeMs(long arg1);

        public abstract Builder setEventUptimeMs(long arg1);

        public abstract Builder setNetworkConnectionInfo(NetworkConnectionInfo arg1);

        abstract Builder setSourceExtension(byte[] arg1);

        abstract Builder setSourceExtensionJsonProto3(String arg1);

        public abstract Builder setTimezoneOffsetSeconds(long arg1);
    }

    private static Builder builder() {
        return new com.google.android.datatransport.cct.internal.AutoValue_LogEvent.Builder();
    }

    public abstract Integer getEventCode();

    public abstract long getEventTimeMs();

    public abstract long getEventUptimeMs();

    public abstract NetworkConnectionInfo getNetworkConnectionInfo();

    public abstract byte[] getSourceExtension();

    public abstract String getSourceExtensionJsonProto3();

    public abstract long getTimezoneOffsetSeconds();

    public static Builder jsonBuilder(String s) {
        return LogEvent.builder().setSourceExtensionJsonProto3(s);
    }

    public static Builder protoBuilder(byte[] arr_b) {
        return LogEvent.builder().setSourceExtension(arr_b);
    }
}

