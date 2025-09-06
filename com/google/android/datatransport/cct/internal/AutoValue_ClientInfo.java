package com.google.android.datatransport.cct.internal;

final class AutoValue_ClientInfo extends ClientInfo {
    static final class Builder extends com.google.android.datatransport.cct.internal.ClientInfo.Builder {
        private AndroidClientInfo androidClientInfo;
        private ClientType clientType;

        @Override  // com.google.android.datatransport.cct.internal.ClientInfo$Builder
        public ClientInfo build() {
            return new AutoValue_ClientInfo(this.clientType, this.androidClientInfo, null);
        }

        @Override  // com.google.android.datatransport.cct.internal.ClientInfo$Builder
        public com.google.android.datatransport.cct.internal.ClientInfo.Builder setAndroidClientInfo(AndroidClientInfo androidClientInfo0) {
            this.androidClientInfo = androidClientInfo0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.ClientInfo$Builder
        public com.google.android.datatransport.cct.internal.ClientInfo.Builder setClientType(ClientType clientInfo$ClientType0) {
            this.clientType = clientInfo$ClientType0;
            return this;
        }
    }

    private final AndroidClientInfo androidClientInfo;
    private final ClientType clientType;

    private AutoValue_ClientInfo(ClientType clientInfo$ClientType0, AndroidClientInfo androidClientInfo0) {
        this.clientType = clientInfo$ClientType0;
        this.androidClientInfo = androidClientInfo0;
    }

    AutoValue_ClientInfo(ClientType clientInfo$ClientType0, AndroidClientInfo androidClientInfo0, com.google.android.datatransport.cct.internal.AutoValue_ClientInfo.1 autoValue_ClientInfo$10) {
        this(clientInfo$ClientType0, androidClientInfo0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof ClientInfo) {
            ClientType clientInfo$ClientType0 = this.clientType;
            if(clientInfo$ClientType0 == null) {
                if(((ClientInfo)object0).getClientType() == null) {
                    return this.androidClientInfo == null ? ((ClientInfo)object0).getAndroidClientInfo() == null : this.androidClientInfo.equals(((ClientInfo)object0).getAndroidClientInfo());
                }
            }
            else if(clientInfo$ClientType0.equals(((ClientInfo)object0).getClientType())) {
                return this.androidClientInfo == null ? ((ClientInfo)object0).getAndroidClientInfo() == null : this.androidClientInfo.equals(((ClientInfo)object0).getAndroidClientInfo());
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.ClientInfo
    public AndroidClientInfo getAndroidClientInfo() {
        return this.androidClientInfo;
    }

    @Override  // com.google.android.datatransport.cct.internal.ClientInfo
    public ClientType getClientType() {
        return this.clientType;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.clientType == null ? 0 : this.clientType.hashCode();
        AndroidClientInfo androidClientInfo0 = this.androidClientInfo;
        if(androidClientInfo0 != null) {
            v = androidClientInfo0.hashCode();
        }
        return (v1 ^ 1000003) * 1000003 ^ v;
    }

    @Override
    public String toString() {
        return "ClientInfo{clientType=" + this.clientType + ", androidClientInfo=" + this.androidClientInfo + "}";
    }

    class com.google.android.datatransport.cct.internal.AutoValue_ClientInfo.1 {
    }

}

