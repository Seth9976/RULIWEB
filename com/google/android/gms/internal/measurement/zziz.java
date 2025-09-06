package com.google.android.gms.internal.measurement;

import java.util.List;
import javax.annotation.CheckForNull;

final class zziz extends zzja {
    final transient int zza;
    final transient int zzb;
    final zzja zzc;

    zziz(zzja zzja0, int v, int v1) {
        this.zzc = zzja0;
        super();
        this.zza = v;
        this.zzb = v1;
    }

    @Override
    public final Object get(int v) {
        zzij.zza(v, this.zzb, "index");
        return this.zzc.get(v + this.zza);
    }

    @Override
    public final int size() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzja
    public final List subList(int v, int v1) {
        return this.zzf(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override  // com.google.android.gms.internal.measurement.zzja
    public final zzja zzf(int v, int v1) {
        zzij.zzc(v, v1, this.zzb);
        return this.zzc.zzf(v + this.zza, v1 + this.zza);
    }
}

