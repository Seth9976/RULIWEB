package com.google.android.gms.internal.auth;

import java.io.IOException;

final class zzgb implements zzgi {
    private final zzfx zza;
    private final zzgz zzb;
    private final zzem zzc;

    private zzgb(zzgz zzgz0, zzem zzem0, zzfx zzfx0) {
        this.zzb = zzgz0;
        this.zzc = zzem0;
        this.zza = zzfx0;
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final int zza(Object object0) {
        return this.zzb.zzb(object0).hashCode();
    }

    static zzgb zzb(zzgz zzgz0, zzem zzem0, zzfx zzfx0) {
        return new zzgb(zzgz0, zzem0, zzfx0);
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final Object zzd() {
        zzfx zzfx0 = this.zza;
        return zzfx0 instanceof zzev ? ((zzev)zzfx0).zzc() : ((zzet)((zzev)zzfx0).zzn(5, null, null)).zzd();
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final void zze(Object object0) {
        this.zzb.zze(object0);
        this.zzc.zzb(object0);
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final void zzf(Object object0, Object object1) {
        zzgk.zzd(this.zzb, object0, object1);
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final void zzg(Object object0, byte[] arr_b, int v, int v1, zzdt zzdt0) throws IOException {
        if(((zzev)object0).zzc == zzha.zza()) {
            ((zzev)object0).zzc = zzha.zzd();
        }
        zzeu zzeu0 = (zzeu)object0;
        throw null;
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final boolean zzh(Object object0, Object object1) {
        return this.zzb.zzb(object0).equals(this.zzb.zzb(object1));
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final boolean zzi(Object object0) {
        this.zzc.zza(object0);
        throw null;
    }
}

