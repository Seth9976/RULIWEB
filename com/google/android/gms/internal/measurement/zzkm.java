package com.google.android.gms.internal.measurement;

final class zzkm {
    private final Object zza;
    private final int zzb;

    zzkm(Object object0, int v) {
        this.zza = object0;
        this.zzb = v;
    }

    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zzkm ? this.zza == ((zzkm)object0).zza && this.zzb == ((zzkm)object0).zzb : false;
    }

    @Override
    public final int hashCode() {
        return System.identityHashCode(this.zza) * 0xFFFF + this.zzb;
    }
}

