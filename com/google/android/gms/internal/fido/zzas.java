package com.google.android.gms.internal.fido;

import java.util.List;
import javax.annotation.CheckForNull;

final class zzas extends zzat {
    final transient int zza;
    final transient int zzb;
    final zzat zzc;

    zzas(zzat zzat0, int v, int v1) {
        this.zzc = zzat0;
        super();
        this.zza = v;
        this.zzb = v1;
    }

    @Override
    public final Object get(int v) {
        zzam.zza(v, this.zzb, "index");
        return this.zzc.get(v + this.zza);
    }

    @Override
    public final int size() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.fido.zzat
    public final List subList(int v, int v1) {
        return this.zzf(v, v1);
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override  // com.google.android.gms.internal.fido.zzat
    public final zzat zzf(int v, int v1) {
        zzam.zze(v, v1, this.zzb);
        return this.zzc.zzf(v + this.zza, v1 + this.zza);
    }
}

