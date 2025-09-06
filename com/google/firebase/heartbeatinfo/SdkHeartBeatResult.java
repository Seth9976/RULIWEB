package com.google.firebase.heartbeatinfo;

public abstract class SdkHeartBeatResult implements Comparable {
    public int compareTo(SdkHeartBeatResult sdkHeartBeatResult0) {
        return this.getMillis() >= sdkHeartBeatResult0.getMillis() ? 1 : -1;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((SdkHeartBeatResult)object0));
    }

    public static SdkHeartBeatResult create(String s, long v) {
        return new AutoValue_SdkHeartBeatResult(s, v);
    }

    public abstract long getMillis();

    public abstract String getSdkName();
}

