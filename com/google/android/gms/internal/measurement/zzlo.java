package com.google.android.gms.internal.measurement;

public class zzlo {
    protected volatile zzmi zza;
    private static final zzkn zzb;
    private volatile zzka zzc;

    static {
        zzlo.zzb = zzkn.zza;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzlo)) {
            return false;
        }
        zzmi zzmi0 = this.zza;
        zzmi zzmi1 = ((zzlo)object0).zza;
        if(zzmi0 == null && zzmi1 == null) {
            return this.zzb().equals(((zzlo)object0).zzb());
        }
        if(zzmi0 != null && zzmi1 != null) {
            return zzmi0.equals(zzmi1);
        }
        if(zzmi0 != null) {
            ((zzlo)object0).zzc(zzmi0.zzbV());
            return zzmi0.equals(((zzlo)object0).zza);
        }
        this.zzc(zzmi1.zzbV());
        return this.zza.equals(zzmi1);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if(this.zzc != null) {
            return ((zzjx)this.zzc).zza.length;
        }
        return this.zza == null ? 0 : this.zza.zzbz();
    }

    public final zzka zzb() {
        if(this.zzc != null) {
            return this.zzc;
        }
        synchronized(this) {
            if(this.zzc != null) {
                return this.zzc;
            }
            this.zzc = this.zza == null ? zzka.zzb : this.zza.zzbv();
            return this.zzc;
        }
    }

    protected final void zzc(zzmi zzmi0) {
        if(this.zza == null) {
            synchronized(this) {
                if(this.zza == null) {
                    this.zza = zzmi0;
                    this.zzc = zzka.zzb;
                }
            }
        }
    }
}

