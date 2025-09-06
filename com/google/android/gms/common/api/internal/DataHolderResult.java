package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class DataHolderResult implements Releasable, Result {
    protected final DataHolder mDataHolder;
    protected final Status mStatus;

    protected DataHolderResult(DataHolder dataHolder0) {
        this(dataHolder0, new Status(dataHolder0.getStatusCode()));
    }

    protected DataHolderResult(DataHolder dataHolder0, Status status0) {
        this.mStatus = status0;
        this.mDataHolder = dataHolder0;
    }

    @Override  // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.mStatus;
    }

    @Override  // com.google.android.gms.common.api.Releasable
    public void release() {
        DataHolder dataHolder0 = this.mDataHolder;
        if(dataHolder0 != null) {
            dataHolder0.close();
        }
    }
}

