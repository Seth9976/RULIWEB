package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzg {
    public final zzg zza;
    final zzax zzb;
    final Map zzc;
    final Map zzd;

    public zzg(zzg zzg0, zzax zzax0) {
        this.zzc = new HashMap();
        this.zzd = new HashMap();
        this.zza = zzg0;
        this.zzb = zzax0;
    }

    public final zzg zza() {
        return new zzg(this, this.zzb);
    }

    public final zzap zzb(zzap zzap0) {
        return this.zzb.zza(this, zzap0);
    }

    public final zzap zzc(zzae zzae0) {
        zzap zzap0 = zzap.zzf;
        Iterator iterator0 = zzae0.zzk();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            zzap zzap1 = zzae0.zze(((int)(((Integer)object0))));
            zzap0 = this.zzb.zza(this, zzap1);
            if(zzap0 instanceof zzag) {
                break;
            }
        }
        return zzap0;
    }

    public final zzap zzd(String s) {
        if(this.zzc.containsKey(s)) {
            return (zzap)this.zzc.get(s);
        }
        zzg zzg0 = this.zza;
        if(zzg0 == null) {
            throw new IllegalArgumentException(String.format("%s is not defined", s));
        }
        return zzg0.zzd(s);
    }

    public final void zze(String s, zzap zzap0) {
        if(this.zzd.containsKey(s)) {
            return;
        }
        if(zzap0 == null) {
            this.zzc.remove(s);
            return;
        }
        this.zzc.put(s, zzap0);
    }

    public final void zzf(String s, zzap zzap0) {
        this.zze(s, zzap0);
        this.zzd.put(s, Boolean.TRUE);
    }

    public final void zzg(String s, zzap zzap0) {
        if(!this.zzc.containsKey(s) && (this.zza != null && this.zza.zzh(s))) {
            this.zza.zzg(s, zzap0);
            return;
        }
        if(this.zzd.containsKey(s)) {
            return;
        }
        if(zzap0 == null) {
            this.zzc.remove(s);
            return;
        }
        this.zzc.put(s, zzap0);
    }

    public final boolean zzh(String s) {
        if(this.zzc.containsKey(s)) {
            return true;
        }
        return this.zza == null ? false : this.zza.zzh(s);
    }
}

