package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class TelemetryData extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final int zaa;
    @Nullable
    private List zab;

    static {
        TelemetryData.CREATOR = new zaab();
    }

    public TelemetryData(int v, @Nullable List list0) {
        this.zaa = v;
        this.zab = list0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeTypedList(parcel0, 2, this.zab, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final int zaa() {
        return this.zaa;
    }

    public final List zab() {
        return this.zab;
    }

    public final void zac(MethodInvocation methodInvocation0) {
        if(this.zab == null) {
            this.zab = new ArrayList();
        }
        this.zab.add(methodInvocation0);
    }
}

