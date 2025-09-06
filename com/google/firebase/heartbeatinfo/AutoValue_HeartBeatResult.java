package com.google.firebase.heartbeatinfo;

import java.util.List;

final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final List usedDates;
    private final String userAgent;

    AutoValue_HeartBeatResult(String s, List list0) {
        if(s == null) {
            throw new NullPointerException("Null userAgent");
        }
        this.userAgent = s;
        if(list0 == null) {
            throw new NullPointerException("Null usedDates");
        }
        this.usedDates = list0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof HeartBeatResult) {
            String s = ((HeartBeatResult)object0).getUserAgent();
            if(this.userAgent.equals(s)) {
                List list0 = ((HeartBeatResult)object0).getUsedDates();
                return this.usedDates.equals(list0);
            }
        }
        return false;
    }

    @Override  // com.google.firebase.heartbeatinfo.HeartBeatResult
    public List getUsedDates() {
        return this.usedDates;
    }

    @Override  // com.google.firebase.heartbeatinfo.HeartBeatResult
    public String getUserAgent() {
        return this.userAgent;
    }

    @Override
    public int hashCode() {
        return (this.userAgent.hashCode() ^ 1000003) * 1000003 ^ this.usedDates.hashCode();
    }

    @Override
    public String toString() {
        return "HeartBeatResult{userAgent=" + this.userAgent + ", usedDates=" + this.usedDates + "}";
    }
}

