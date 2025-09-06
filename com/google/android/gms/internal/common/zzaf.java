package com.google.android.gms.internal.common;

import java.util.List;
import javax.annotation.CheckForNull;

final class zzaf extends zzag {
    final transient int zza;
    final transient int zzb;
    final zzag zzc;

    zzaf(zzag zzag0, int v, int v1) {
        this.zzc = zzag0;
        super();
        this.zza = v;
        this.zzb = v1;
    }

    @Override
    public final Object get(int v) {
        zzs.zza(v, this.zzb, "index");
        return this.zzc.get(v + this.zza);
    }

    @Override
    public final int size() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.common.zzag
    public final List subList(int v, int v1) {
        return this.zzh(v, v1);
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final boolean zzf() {
        return true;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    @CheckForNull
    final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override  // com.google.android.gms.internal.common.zzag
    public final zzag zzh(int v, int v1) {
        zzs.zzc(v, v1, this.zzb);
        return this.zzc.zzh(v + this.zza, v1 + this.zza);
    }
}

