package com.google.android.datatransport.cct.internal;

final class AutoValue_NetworkConnectionInfo extends NetworkConnectionInfo {
    static final class Builder extends com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder {
        private MobileSubtype mobileSubtype;
        private NetworkType networkType;

        @Override  // com.google.android.datatransport.cct.internal.NetworkConnectionInfo$Builder
        public NetworkConnectionInfo build() {
            return new AutoValue_NetworkConnectionInfo(this.networkType, this.mobileSubtype, null);
        }

        @Override  // com.google.android.datatransport.cct.internal.NetworkConnectionInfo$Builder
        public com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder setMobileSubtype(MobileSubtype networkConnectionInfo$MobileSubtype0) {
            this.mobileSubtype = networkConnectionInfo$MobileSubtype0;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.NetworkConnectionInfo$Builder
        public com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder setNetworkType(NetworkType networkConnectionInfo$NetworkType0) {
            this.networkType = networkConnectionInfo$NetworkType0;
            return this;
        }
    }

    private final MobileSubtype mobileSubtype;
    private final NetworkType networkType;

    private AutoValue_NetworkConnectionInfo(NetworkType networkConnectionInfo$NetworkType0, MobileSubtype networkConnectionInfo$MobileSubtype0) {
        this.networkType = networkConnectionInfo$NetworkType0;
        this.mobileSubtype = networkConnectionInfo$MobileSubtype0;
    }

    AutoValue_NetworkConnectionInfo(NetworkType networkConnectionInfo$NetworkType0, MobileSubtype networkConnectionInfo$MobileSubtype0, com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo.1 autoValue_NetworkConnectionInfo$10) {
        this(networkConnectionInfo$NetworkType0, networkConnectionInfo$MobileSubtype0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof NetworkConnectionInfo) {
            NetworkType networkConnectionInfo$NetworkType0 = this.networkType;
            if(networkConnectionInfo$NetworkType0 == null) {
                if(((NetworkConnectionInfo)object0).getNetworkType() == null) {
                    return this.mobileSubtype == null ? ((NetworkConnectionInfo)object0).getMobileSubtype() == null : this.mobileSubtype.equals(((NetworkConnectionInfo)object0).getMobileSubtype());
                }
            }
            else if(networkConnectionInfo$NetworkType0.equals(((NetworkConnectionInfo)object0).getNetworkType())) {
                return this.mobileSubtype == null ? ((NetworkConnectionInfo)object0).getMobileSubtype() == null : this.mobileSubtype.equals(((NetworkConnectionInfo)object0).getMobileSubtype());
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    public MobileSubtype getMobileSubtype() {
        return this.mobileSubtype;
    }

    @Override  // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    public NetworkType getNetworkType() {
        return this.networkType;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.networkType == null ? 0 : this.networkType.hashCode();
        MobileSubtype networkConnectionInfo$MobileSubtype0 = this.mobileSubtype;
        if(networkConnectionInfo$MobileSubtype0 != null) {
            v = networkConnectionInfo$MobileSubtype0.hashCode();
        }
        return (v1 ^ 1000003) * 1000003 ^ v;
    }

    @Override
    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.networkType + ", mobileSubtype=" + this.mobileSubtype + "}";
    }

    class com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo.1 {
    }

}

