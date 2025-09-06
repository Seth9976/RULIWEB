package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;

public class BooleanResult implements Result {
    private final Status zaa;
    private final boolean zab;

    public BooleanResult(Status status0, boolean z) {
        this.zaa = (Status)Preconditions.checkNotNull(status0, "Status must not be null");
        this.zab = z;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        return object0 instanceof BooleanResult ? this.zaa.equals(((BooleanResult)object0).zaa) && this.zab == ((BooleanResult)object0).zab : false;
    }

    @Override  // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zaa;
    }

    public boolean getValue() {
        return this.zab;
    }

    @Override
    public final int hashCode() {
        return (this.zaa.hashCode() + 0x20F) * 0x1F + this.zab;
    }
}

