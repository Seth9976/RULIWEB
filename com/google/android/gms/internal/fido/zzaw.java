package com.google.android.gms.internal.fido;

final class zzaw extends zzat {
    static final zzat zza;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zzaw.zza = new zzaw(new Object[0], 0);
    }

    zzaw(Object[] arr_object, int v) {
        this.zzb = arr_object;
        this.zzc = v;
    }

    @Override
    public final Object get(int v) {
        zzam.zza(v, this.zzc, "index");
        Object object0 = this.zzb[v];
        object0.getClass();
        return object0;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.fido.zzat
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzb, 0, arr_object, 0, this.zzc);
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zzb() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final Object[] zze() {
        return this.zzb;
    }
}

