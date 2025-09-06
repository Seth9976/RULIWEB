package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzph;

final class zzkn {
    protected long zza;
    protected long zzb;
    final zzkp zzc;
    private final zzan zzd;

    public zzkn(zzkp zzkp0) {
        this.zzc = zzkp0;
        super();
        this.zzd = new zzkm(this, zzkp0.zzt);
        long v = zzkp0.zzt.zzax().elapsedRealtime();
        this.zza = v;
        this.zzb = v;
    }

    final void zza() {
        this.zzd.zzb();
        this.zza = 0L;
        this.zzb = 0L;
    }

    final void zzb(long v) {
        this.zzd.zzb();
    }

    final void zzc(long v) {
        this.zzc.zzg();
        this.zzd.zzb();
        this.zza = v;
        this.zzb = v;
    }

    public final boolean zzd(boolean z, boolean z1, long v) {
        this.zzc.zzg();
        this.zzc.zza();
        zzph.zzc();
        if(!this.zzc.zzt.zzf().zzs(null, zzeg.zzaf)) {
            zzfi zzfi1 = this.zzc.zzt.zzm();
            long v2 = this.zzc.zzt.zzax().currentTimeMillis();
            zzfi1.zzj.zzb(v2);
        }
        else if(this.zzc.zzt.zzJ()) {
            zzfi zzfi0 = this.zzc.zzt.zzm();
            long v1 = this.zzc.zzt.zzax().currentTimeMillis();
            zzfi0.zzj.zzb(v1);
        }
        long v3 = v - this.zza;
        if(!z && v3 < 1000L) {
            this.zzc.zzt.zzaA().zzj().zzb("Screen exposed for less than 1000 ms. Event not sent. time", v3);
            return false;
        }
        if(!z1) {
            v3 = v - this.zzb;
            this.zzb = v;
        }
        this.zzc.zzt.zzaA().zzj().zzb("Recording user engagement, ms", v3);
        Bundle bundle0 = new Bundle();
        bundle0.putLong("_et", v3);
        boolean z2 = this.zzc.zzt.zzf().zzu();
        zzlp.zzK(this.zzc.zzt.zzs().zzj(!z2), bundle0, true);
        if(!z1) {
            this.zzc.zzt.zzq().zzG("auto", "_e", bundle0);
        }
        this.zza = v;
        this.zzd.zzb();
        this.zzd.zzd(3600000L);
        return true;
    }
}

