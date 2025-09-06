package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zaa extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private int zab;
    private Intent zac;

    static {
        zaa.CREATOR = new zab();
    }

    public zaa() {
        this(2, 0, null);
    }

    zaa(int v, int v1, Intent intent0) {
        this.zaa = v;
        this.zab = v1;
        this.zac = intent0;
    }

    @Override  // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zab == 0 ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel0, 2, this.zab);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zac, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

