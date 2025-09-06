package com.google.android.gms.internal.measurement;

import javax.annotation.CheckForNull;

final class zzik extends zzii {
    private final Object zza;

    zzik(Object object0) {
        this.zza = object0;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(@CheckForNull Object object0) {
        return object0 instanceof zzik ? this.zza.equals(((zzik)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode() + 0x598DF91C;
    }

    @Override
    public final String toString() {
        return "Optional.of(" + this.zza.toString() + ")";
    }

    @Override  // com.google.android.gms.internal.measurement.zzii
    public final Object zza() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzii
    public final boolean zzb() {
        return true;
    }
}

