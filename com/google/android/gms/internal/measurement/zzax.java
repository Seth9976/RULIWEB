package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class zzax {
    final Map zza;
    final zzbj zzb;

    public zzax() {
        this.zza = new HashMap();
        this.zzb = new zzbj();
        this.zzb(new zzav());
        this.zzb(new zzay());
        this.zzb(new zzaz());
        this.zzb(new zzbc());
        this.zzb(new zzbh());
        this.zzb(new zzbi());
        this.zzb(new zzbk());
    }

    public final zzap zza(zzg zzg0, zzap zzap0) {
        zzh.zzc(zzg0);
        if(zzap0 instanceof zzaq) {
            ArrayList arrayList0 = ((zzaq)zzap0).zzc();
            String s = ((zzaq)zzap0).zzb();
            return this.zza.containsKey(s) ? ((zzaw)this.zza.get(s)).zza(s, zzg0, arrayList0) : this.zzb.zza(s, zzg0, arrayList0);
        }
        return zzap0;
    }

    final void zzb(zzaw zzaw0) {
        for(Object object0: zzaw0.zza) {
            String s = ((zzbl)object0).zzb().toString();
            this.zza.put(s, zzaw0);
        }
    }
}

