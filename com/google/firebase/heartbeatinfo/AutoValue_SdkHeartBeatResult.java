package com.google.firebase.heartbeatinfo;

final class AutoValue_SdkHeartBeatResult extends SdkHeartBeatResult {
    private final long millis;
    private final String sdkName;

    AutoValue_SdkHeartBeatResult(String s, long v) {
        if(s == null) {
            throw new NullPointerException("Null sdkName");
        }
        this.sdkName = s;
        this.millis = v;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof SdkHeartBeatResult) {
            String s = ((SdkHeartBeatResult)object0).getSdkName();
            if(this.sdkName.equals(s)) {
                long v = ((SdkHeartBeatResult)object0).getMillis();
                return this.millis == v;
            }
        }
        return false;
    }

    @Override  // com.google.firebase.heartbeatinfo.SdkHeartBeatResult
    public long getMillis() {
        return this.millis;
    }

    @Override  // com.google.firebase.heartbeatinfo.SdkHeartBeatResult
    public String getSdkName() {
        return this.sdkName;
    }

    @Override
    public int hashCode() {
        return (this.sdkName.hashCode() ^ 1000003) * 1000003 ^ ((int)(this.millis ^ this.millis >>> 0x20));
    }

    @Override
    public String toString() {
        return "SdkHeartBeatResult{sdkName=" + this.sdkName + ", millis=" + this.millis + "}";
    }
}

