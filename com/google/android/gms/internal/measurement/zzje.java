package com.google.android.gms.internal.measurement;

final class zzje extends zzja {
    static final zzja zza;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zzje.zza = new zzje(new Object[0], 0);
    }

    zzje(Object[] arr_object, int v) {
        this.zzb = arr_object;
        this.zzc = v;
    }

    @Override
    public final Object get(int v) {
        zzij.zza(v, this.zzc, "index");
        Object object0 = this.zzb[v];
        object0.getClass();
        return object0;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zzja
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzb, 0, arr_object, 0, this.zzc);
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zzb() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    final Object[] zze() {
        return this.zzb;
    }
}

