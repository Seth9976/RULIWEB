package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzab {
    private zzaa zza;
    private zzaa zzb;
    private final List zzc;

    public zzab() {
        this.zza = new zzaa("", 0L, null);
        this.zzb = new zzaa("", 0L, null);
        this.zzc = new ArrayList();
    }

    public zzab(zzaa zzaa0) {
        this.zza = zzaa0;
        this.zzb = zzaa0.zzb();
        this.zzc = new ArrayList();
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        zzab zzab0 = new zzab(this.zza.zzb());
        for(Object object0: this.zzc) {
            zzaa zzaa0 = ((zzaa)object0).zzb();
            zzab0.zzc.add(zzaa0);
        }
        return zzab0;
    }

    public final zzaa zza() {
        return this.zza;
    }

    public final zzaa zzb() {
        return this.zzb;
    }

    public final List zzc() {
        return this.zzc;
    }

    public final void zzd(zzaa zzaa0) {
        this.zza = zzaa0;
        this.zzb = zzaa0.zzb();
        this.zzc.clear();
    }

    public final void zze(String s, long v, Map map0) {
        zzaa zzaa0 = new zzaa(s, v, map0);
        this.zzc.add(zzaa0);
    }

    public final void zzf(zzaa zzaa0) {
        this.zzb = zzaa0;
    }
}

