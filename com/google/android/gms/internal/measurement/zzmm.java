package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzmm implements zzmt {
    private final zzmi zza;
    private final zznk zzb;
    private final boolean zzc;
    private final zzko zzd;

    private zzmm(zznk zznk0, zzko zzko0, zzmi zzmi0) {
        this.zzb = zznk0;
        this.zzc = zzko0.zzc(zzmi0);
        this.zzd = zzko0;
        this.zza = zzmi0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final int zza(Object object0) {
        Object object1 = this.zzb.zzd(object0);
        int v = this.zzb.zzb(object1);
        if(!this.zzc) {
            return v;
        }
        this.zzd.zza(object0);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final int zzb(Object object0) {
        int v = this.zzb.zzd(object0).hashCode();
        if(!this.zzc) {
            return v;
        }
        this.zzd.zza(object0);
        throw null;
    }

    static zzmm zzc(zznk zznk0, zzko zzko0, zzmi zzmi0) {
        return new zzmm(zznk0, zzko0, zzmi0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final Object zze() {
        zzmi zzmi0 = this.zza;
        return zzmi0 instanceof zzlb ? ((zzlb)zzmi0).zzbD() : zzmi0.zzbJ().zzaF();
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzf(Object object0) {
        this.zzb.zzg(object0);
        this.zzd.zzb(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzg(Object object0, Object object1) {
        zzmv.zzB(this.zzb, object0, object1);
        if(!this.zzc) {
            return;
        }
        this.zzd.zza(object1);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzh(Object object0, byte[] arr_b, int v, int v1, zzjn zzjn0) throws IOException {
        if(((zzlb)object0).zzc == zznl.zzc()) {
            ((zzlb)object0).zzc = zznl.zzf();
        }
        zzky zzky0 = (zzky)object0;
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzi(Object object0, zzoc zzoc0) throws IOException {
        this.zzd.zza(object0);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final boolean zzj(Object object0, Object object1) {
        if(!this.zzb.zzd(object0).equals(this.zzb.zzd(object1))) {
            return false;
        }
        if(!this.zzc) {
            return true;
        }
        this.zzd.zza(object0);
        this.zzd.zza(object1);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final boolean zzk(Object object0) {
        this.zzd.zza(object0);
        throw null;
    }
}

