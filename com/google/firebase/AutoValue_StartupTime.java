package com.google.firebase;

final class AutoValue_StartupTime extends StartupTime {
    private final long elapsedRealtime;
    private final long epochMillis;
    private final long uptimeMillis;

    AutoValue_StartupTime(long v, long v1, long v2) {
        this.epochMillis = v;
        this.elapsedRealtime = v1;
        this.uptimeMillis = v2;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof StartupTime) {
            long v = ((StartupTime)object0).getEpochMillis();
            if(this.epochMillis == v) {
                long v1 = ((StartupTime)object0).getElapsedRealtime();
                if(this.elapsedRealtime == v1) {
                    long v2 = ((StartupTime)object0).getUptimeMillis();
                    return this.uptimeMillis == v2;
                }
            }
        }
        return false;
    }

    @Override  // com.google.firebase.StartupTime
    public long getElapsedRealtime() {
        return this.elapsedRealtime;
    }

    @Override  // com.google.firebase.StartupTime
    public long getEpochMillis() {
        return this.epochMillis;
    }

    @Override  // com.google.firebase.StartupTime
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    @Override
    public int hashCode() {
        return ((int)(this.uptimeMillis ^ this.uptimeMillis >>> 0x20)) ^ ((((int)(this.epochMillis ^ this.epochMillis >>> 0x20)) ^ 1000003) * 1000003 ^ ((int)(this.elapsedRealtime ^ this.elapsedRealtime >>> 0x20))) * 1000003;
    }

    @Override
    public String toString() {
        return "StartupTime{epochMillis=" + this.epochMillis + ", elapsedRealtime=" + this.elapsedRealtime + ", uptimeMillis=" + this.uptimeMillis + "}";
    }
}

