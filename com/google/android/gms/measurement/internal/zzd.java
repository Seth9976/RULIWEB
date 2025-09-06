package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

public final class zzd extends zze {
    private final Map zza;
    private final Map zzb;
    private long zzc;

    public zzd(zzgd zzgd0) {
        super(zzgd0);
        this.zzb = new ArrayMap();
        this.zza = new ArrayMap();
    }

    static void zza(zzd zzd0, String s, long v) {
        zzd0.zzg();
        Preconditions.checkNotEmpty(s);
        if(zzd0.zzb.isEmpty()) {
            zzd0.zzc = v;
        }
        Integer integer0 = (Integer)zzd0.zzb.get(s);
        if(integer0 != null) {
            zzd0.zzb.put(s, ((int)(((int)integer0) + 1)));
            return;
        }
        if(zzd0.zzb.size() >= 100) {
            zzd0.zzt.zzaA().zzk().zza("Too many ads visible");
            return;
        }
        zzd0.zzb.put(s, 1);
        zzd0.zza.put(s, v);
    }

    static void zzb(zzd zzd0, String s, long v) {
        zzd0.zzg();
        Preconditions.checkNotEmpty(s);
        Integer integer0 = (Integer)zzd0.zzb.get(s);
        if(integer0 != null) {
            zzir zzir0 = zzd0.zzt.zzs().zzj(false);
            int v1 = (int)integer0;
            if(v1 - 1 == 0) {
                zzd0.zzb.remove(s);
                Long long0 = (Long)zzd0.zza.get(s);
                if(long0 == null) {
                    zzd0.zzt.zzaA().zzd().zza("First ad unit exposure time was never set");
                }
                else {
                    zzd0.zza.remove(s);
                    zzd0.zzi(s, v - ((long)long0), zzir0);
                }
                if(zzd0.zzb.isEmpty()) {
                    long v2 = zzd0.zzc;
                    if(v2 == 0L) {
                        zzd0.zzt.zzaA().zzd().zza("First ad exposure time was never set");
                        return;
                    }
                    zzd0.zzh(v - v2, zzir0);
                    zzd0.zzc = 0L;
                }
                return;
            }
            zzd0.zzb.put(s, ((int)(v1 - 1)));
            return;
        }
        zzd0.zzt.zzaA().zzd().zzb("Call to endAdUnitExposure for unknown ad unit id", s);
    }

    static void zzc(zzd zzd0, long v) {
        zzd0.zzj(v);
    }

    public final void zzd(String s, long v) {
        if(s != null && s.length() != 0) {
            this.zzt.zzaB().zzp(new zza(this, s, v));
            return;
        }
        this.zzt.zzaA().zzd().zza("Ad unit id must be a non-empty string");
    }

    public final void zze(String s, long v) {
        if(s != null && s.length() != 0) {
            this.zzt.zzaB().zzp(new zzb(this, s, v));
            return;
        }
        this.zzt.zzaA().zzd().zza("Ad unit id must be a non-empty string");
    }

    public final void zzf(long v) {
        zzir zzir0 = this.zzt.zzs().zzj(false);
        for(Object object0: this.zza.keySet()) {
            this.zzi(((String)object0), v - ((long)(((Long)this.zza.get(((String)object0))))), zzir0);
        }
        if(!this.zza.isEmpty()) {
            this.zzh(v - this.zzc, zzir0);
        }
        this.zzj(v);
    }

    private final void zzh(long v, zzir zzir0) {
        if(zzir0 == null) {
            this.zzt.zzaA().zzj().zza("Not logging ad exposure. No active activity");
            return;
        }
        if(v < 1000L) {
            this.zzt.zzaA().zzj().zzb("Not logging ad exposure. Less than 1000 ms. exposure", v);
            return;
        }
        Bundle bundle0 = new Bundle();
        bundle0.putLong("_xt", v);
        zzlp.zzK(zzir0, bundle0, true);
        this.zzt.zzq().zzG("am", "_xa", bundle0);
    }

    private final void zzi(String s, long v, zzir zzir0) {
        if(zzir0 == null) {
            this.zzt.zzaA().zzj().zza("Not logging ad unit exposure. No active activity");
            return;
        }
        if(v < 1000L) {
            this.zzt.zzaA().zzj().zzb("Not logging ad unit exposure. Less than 1000 ms. exposure", v);
            return;
        }
        Bundle bundle0 = new Bundle();
        bundle0.putString("_ai", s);
        bundle0.putLong("_xt", v);
        zzlp.zzK(zzir0, bundle0, true);
        this.zzt.zzq().zzG("am", "_xu", bundle0);
    }

    private final void zzj(long v) {
        for(Object object0: this.zza.keySet()) {
            this.zza.put(((String)object0), v);
        }
        if(!this.zza.isEmpty()) {
            this.zzc = v;
        }
    }
}

