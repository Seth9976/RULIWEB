package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;

public final class zzas extends AbstractSafeParcelable implements Iterable {
    public static final Parcelable.Creator CREATOR;
    private final Bundle zza;

    static {
        zzas.CREATOR = new zzat();
    }

    zzas(Bundle bundle0) {
        this.zza = bundle0;
    }

    @Override
    public final Iterator iterator() {
        return new zzar(this);
    }

    @Override
    public final String toString() {
        return this.zza.toString();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBundle(parcel0, 2, this.zzc(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final int zza() {
        return this.zza.size();
    }

    static Bundle zzb(zzas zzas0) {
        return zzas0.zza;
    }

    public final Bundle zzc() {
        return new Bundle(this.zza);
    }

    final Double zzd(String s) {
        return this.zza.getDouble("value");
    }

    final Long zze(String s) {
        return this.zza.getLong("value");
    }

    final Object zzf(String s) {
        return this.zza.get(s);
    }

    final String zzg(String s) {
        return this.zza.getString(s);
    }
}

