package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final String zza;
    private final boolean zzb;
    private final boolean zzc;
    private final Context zzd;
    private final boolean zze;
    private final boolean zzf;

    static {
        zzo.CREATOR = new zzp();
    }

    zzo(String s, boolean z, boolean z1, IBinder iBinder0, boolean z2, boolean z3) {
        this.zza = s;
        this.zzb = z;
        this.zzc = z1;
        this.zzd = (Context)ObjectWrapper.unwrap(Stub.asInterface(iBinder0));
        this.zze = z2;
        this.zzf = z3;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zza, false);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzc);
        SafeParcelWriter.writeIBinder(parcel0, 4, ((IBinder)ObjectWrapper.wrap(this.zzd)), false);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.zze);
        SafeParcelWriter.writeBoolean(parcel0, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

