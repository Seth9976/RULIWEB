package com.google.android.gms.auth.api.signin.internal;

public class HashAccumulator {
    private int zaa;

    public HashAccumulator() {
        this.zaa = 1;
    }

    public HashAccumulator addObject(Object object0) {
        this.zaa = this.zaa * 0x1F + (object0 == null ? 0 : object0.hashCode());
        return this;
    }

    public int hash() {
        return this.zaa;
    }

    public final HashAccumulator zaa(boolean z) {
        this.zaa = this.zaa * 0x1F + ((int)z);
        return this;
    }
}

