package com.google.android.datatransport.cct.internal;

public abstract class ClientInfo {
    public static abstract class Builder {
        public abstract ClientInfo build();

        public abstract Builder setAndroidClientInfo(AndroidClientInfo arg1);

        public abstract Builder setClientType(ClientType arg1);
    }

    public static enum ClientType {
        UNKNOWN(0),
        ANDROID_FIREBASE(23);

        private final int value;

        private ClientType(int v1) {
            this.value = v1;
        }
    }

    public static Builder builder() {
        return new com.google.android.datatransport.cct.internal.AutoValue_ClientInfo.Builder();
    }

    public abstract AndroidClientInfo getAndroidClientInfo();

    public abstract ClientType getClientType();
}

