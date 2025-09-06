package com.google.android.gms.internal.common;

import org.jspecify.nullness.NullMarked;

@NullMarked
final class zzai extends zzag {
    static final zzag zza;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zzai.zza = new zzai(new Object[0], 0);
    }

    zzai(Object[] arr_object, int v) {
        this.zzb = arr_object;
        this.zzc = v;
    }

    @Override
    public final Object get(int v) {
        zzs.zza(v, this.zzc, "index");
        Object object0 = this.zzb[v];
        object0.getClass();
        return object0;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.common.zzag
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzb, 0, arr_object, 0, this.zzc);
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final int zzb() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final boolean zzf() {
        return false;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    final Object[] zzg() {
        return this.zzb;
    }
}

