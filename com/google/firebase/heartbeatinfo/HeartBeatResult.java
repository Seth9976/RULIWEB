package com.google.firebase.heartbeatinfo;

import java.util.List;

public abstract class HeartBeatResult {
    public static HeartBeatResult create(String s, List list0) {
        return new AutoValue_HeartBeatResult(s, list0);
    }

    public abstract List getUsedDates();

    public abstract String getUserAgent();
}

