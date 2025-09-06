package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public final class zzw extends zzai {
    final Map zza;
    private final zzj zzb;

    public zzw(zzj zzj0) {
        super("require");
        this.zza = new HashMap();
        this.zzb = zzj0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        zzap zzap0;
        zzh.zzh("require", 1, list0);
        String s = zzg0.zzb(((zzap)list0.get(0))).zzi();
        if(this.zza.containsKey(s)) {
            return (zzap)this.zza.get(s);
        }
        zzj zzj0 = this.zzb;
        if(zzj0.zza.containsKey(s)) {
            Callable callable0 = (Callable)zzj0.zza.get(s);
            try {
                zzap0 = (zzap)callable0.call();
            }
            catch(Exception unused_ex) {
                throw new IllegalStateException("Failed to create API implementation: " + s);
            }
        }
        else {
            zzap0 = zzap.zzf;
        }
        if(zzap0 instanceof zzai) {
            this.zza.put(s, ((zzai)zzap0));
        }
        return zzap0;
    }
}

