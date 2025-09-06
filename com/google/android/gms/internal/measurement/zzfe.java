package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

public final class zzfe extends zzkx implements zzmj {
    private zzfe() {
        super(zzff.zzf());
    }

    zzfe(zzez zzez0) {
        super(zzff.zzf());
    }

    public final int zza() {
        return ((zzff)this.zza).zzb();
    }

    public final zzfd zzb(int v) {
        return ((zzff)this.zza).zzd(v);
    }

    public final zzfe zzc() {
        this.zzaH();
        zzff.zzr(((zzff)this.zza));
        return this;
    }

    public final zzfe zzd(int v, zzfc zzfc0) {
        this.zzaH();
        zzff.zzq(((zzff)this.zza), v, ((zzfd)zzfc0.zzaD()));
        return this;
    }

    // 去混淆评级： 低(20)
    public final String zze() [...] // 潜在的解密器

    public final List zzf() {
        return Collections.unmodifiableList(((zzff)this.zza).zzm());
    }

    public final List zzg() {
        return Collections.unmodifiableList(((zzff)this.zza).zzn());
    }
}

