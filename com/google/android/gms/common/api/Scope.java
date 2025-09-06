package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    private final String zzb;

    static {
        Scope.CREATOR = new zza();
    }

    Scope(int v, String s) {
        Preconditions.checkNotEmpty(s, "scopeUri must not be null or empty");
        this.zza = v;
        this.zzb = s;
    }

    public Scope(String s) {
        this(1, s);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof Scope ? this.zzb.equals(((Scope)object0).zzb) : false;
    }

    public String getScopeUri() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        return this.zzb.hashCode();
    }

    @Override
    public String toString() {
        return this.zzb;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeString(parcel0, 2, this.getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

