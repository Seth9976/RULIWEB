package com.google.android.gms.measurement.internal;

import android.util.Log;

final class zzeq implements Runnable {
    final int zza;
    final String zzb;
    final Object zzc;
    final Object zzd;
    final Object zze;
    final zzet zzf;

    zzeq(zzet zzet0, int v, String s, Object object0, Object object1, Object object2) {
        this.zzf = zzet0;
        this.zza = v;
        this.zzb = s;
        this.zzc = object0;
        this.zzd = object1;
        this.zze = object2;
        super();
    }

    @Override
    public final void run() {
        zzfi zzfi0 = this.zzf.zzt.zzm();
        if(zzfi0.zzy()) {
            zzet zzet0 = this.zzf;
            if(zzet.zza(zzet0) == 0) {
                if(zzet0.zzt.zzf().zzy()) {
                    zzet.zzt(this.zzf, 'C');
                }
                else {
                    zzet.zzt(this.zzf, 'c');
                }
            }
            zzet zzet1 = this.zzf;
            if(zzet.zzb(zzet1) < 0L) {
                zzet1.zzt.zzf().zzh();
                zzet.zzs(zzet1, 79000L);
            }
            String s = zzet.zzo(true, this.zzb, this.zzc, this.zzd, this.zze);
            String s1 = "2" + "01VDIWEA?".charAt(this.zza) + zzet.zza(this.zzf) + zzet.zzb(this.zzf) + ":" + s;
            if(s1.length() > 0x400) {
                s1 = this.zzb.substring(0, 0x400);
            }
            zzfg zzfg0 = zzfi0.zzb;
            if(zzfg0 != null) {
                zzfg0.zzb(s1, 1L);
            }
            return;
        }
        Log.println(6, this.zzf.zzr(), "Persisted config not initialized. Not logging error/warn");
    }
}

