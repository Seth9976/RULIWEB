package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Callable;

public final class zzc {
    final zzf zza;
    zzg zzb;
    final zzab zzc;
    private final zzz zzd;

    public zzc() {
        zzf zzf0 = new zzf();
        super();
        this.zza = zzf0;
        this.zzb = zzf0.zzb.zza();
        this.zzc = new zzab();
        this.zzd = new zzz();
        zza zza0 = () -> new zzv(this.zzd);
        zzf0.zzd.zza("internal.registerCallback", zza0);
        zzb zzb0 = new zzb(this);
        zzf0.zzd.zza("internal.eventLogger", zzb0);
    }

    public final zzab zza() {
        return this.zzc;
    }

    // 检测为 Lambda 实现
    final zzai zzb() throws Exception [...]

    public final void zzc(zzgt zzgt0) throws zzd {
        zzai zzai0;
        try {
            this.zzb = this.zza.zzb.zza();
            zzg zzg0 = this.zzb;
            zzgy[] arr_zzgy = (zzgy[])zzgt0.zzc().toArray(new zzgy[0]);
            if(this.zza.zza(zzg0, arr_zzgy) instanceof zzag) {
                throw new IllegalStateException("Program loading failed");
            }
            Iterator iterator0 = zzgt0.zza().zzd().iterator();
            while(true) {
            label_6:
                if(!iterator0.hasNext()) {
                    return;
                }
                Object object0 = iterator0.next();
                for(Object object1: ((zzgr)object0).zzc()) {
                    zzap zzap0 = this.zza.zza(this.zzb, new zzgy[]{((zzgy)object1)});
                    if(!(zzap0 instanceof zzam)) {
                        throw new IllegalArgumentException("Invalid rule definition");
                    }
                    zzg zzg1 = this.zzb;
                    if(zzg1.zzh("")) {
                        zzap zzap1 = zzg1.zzd("");
                        if(!(zzap1 instanceof zzai)) {
                            throw new IllegalStateException("Invalid function name: ");
                        }
                        zzai0 = (zzai)zzap1;
                    }
                    else {
                        zzai0 = null;
                    }
                    if(zzai0 == null) {
                        throw new IllegalStateException("Rule function is undefined: ");
                    }
                    zzai0.zza(this.zzb, Collections.singletonList(zzap0));
                }
            }
        }
        catch(Throwable throwable0) {
            throw new zzd(throwable0);
        }
        goto label_6;
    }

    public final void zzd(String s, Callable callable0) {
        this.zza.zzd.zza(s, callable0);
    }

    public final boolean zze(zzaa zzaa0) throws zzd {
        try {
            this.zzc.zzd(zzaa0);
            zzah zzah0 = new zzah(0.0);
            this.zza.zzc.zzg("runtime.counter", zzah0);
            zzg zzg0 = this.zzb.zza();
            this.zzd.zzb(zzg0, this.zzc);
            return !this.zzg() && !this.zzf() ? false : true;
        }
        catch(Throwable throwable0) {
            throw new zzd(throwable0);
        }
    }

    public final boolean zzf() {
        return !this.zzc.zzc().isEmpty();
    }

    public final boolean zzg() {
        return !this.zzc.zzb().equals(this.zzc.zza());
    }
}

