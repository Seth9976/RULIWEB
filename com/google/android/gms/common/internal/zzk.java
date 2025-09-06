package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    Bundle zza;
    Feature[] zzb;
    int zzc;
    ConnectionTelemetryConfiguration zzd;

    static {
        zzk.CREATOR = new zzl();
    }

    public zzk() {
    }

    zzk(Bundle bundle0, Feature[] arr_feature, int v, ConnectionTelemetryConfiguration connectionTelemetryConfiguration0) {
        this.zza = bundle0;
        this.zzb = arr_feature;
        this.zzc = v;
        this.zzd = connectionTelemetryConfiguration0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBundle(parcel0, 1, this.zza, false);
        SafeParcelWriter.writeTypedArray(parcel0, 2, this.zzb, v, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzc);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzd, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

