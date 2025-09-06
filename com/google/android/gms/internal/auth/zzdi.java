package com.google.android.gms.internal.auth;

import javax.annotation.CheckForNull;

final class zzdi extends zzdh {
    private final Object zza;

    zzdi(Object object0) {
        this.zza = object0;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(@CheckForNull Object object0) {
        return object0 instanceof zzdi ? this.zza.equals(((zzdi)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode() + 0x598DF91C;
    }

    @Override
    public final String toString() {
        return "Optional.of(" + this.zza.toString() + ")";
    }

    @Override  // com.google.android.gms.internal.auth.zzdh
    public final Object zza() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.auth.zzdh
    public final boolean zzb() {
        return true;
    }
}

