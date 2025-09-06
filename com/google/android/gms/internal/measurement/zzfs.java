package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

public final class zzfs extends zzkx implements zzmj {
    private zzfs() {
        super(zzft.zzf());
    }

    zzfs(zzfk zzfk0) {
        super(zzft.zzf());
    }

    public final int zza() {
        return ((zzft)this.zza).zzb();
    }

    public final long zzb() {
        return ((zzft)this.zza).zzc();
    }

    public final long zzc() {
        return ((zzft)this.zza).zzd();
    }

    public final zzfs zzd(Iterable iterable0) {
        this.zzaH();
        zzft.zzm(((zzft)this.zza), iterable0);
        return this;
    }

    public final zzfs zze(zzfw zzfw0) {
        this.zzaH();
        zzft.zzk(((zzft)this.zza), ((zzfx)zzfw0.zzaD()));
        return this;
    }

    public final zzfs zzf(zzfx zzfx0) {
        this.zzaH();
        zzft.zzk(((zzft)this.zza), zzfx0);
        return this;
    }

    public final zzfs zzg() {
        this.zzaH();
        zzft.zzn(((zzft)this.zza));
        return this;
    }

    public final zzfs zzh(int v) {
        this.zzaH();
        zzft.zzo(((zzft)this.zza), v);
        return this;
    }

    public final zzfs zzi(String s) {
        this.zzaH();
        zzft.zzp(((zzft)this.zza), s);
        return this;
    }

    public final zzfs zzj(int v, zzfw zzfw0) {
        this.zzaH();
        zzft.zzj(((zzft)this.zza), v, ((zzfx)zzfw0.zzaD()));
        return this;
    }

    public final zzfs zzk(int v, zzfx zzfx0) {
        this.zzaH();
        zzft.zzj(((zzft)this.zza), v, zzfx0);
        return this;
    }

    public final zzfs zzl(long v) {
        this.zzaH();
        zzft.zzr(((zzft)this.zza), v);
        return this;
    }

    public final zzfs zzm(long v) {
        this.zzaH();
        zzft.zzq(((zzft)this.zza), v);
        return this;
    }

    public final zzfx zzn(int v) {
        return ((zzft)this.zza).zzg(v);
    }

    // 去混淆评级： 低(20)
    public final String zzo() [...] // 潜在的解密器

    public final List zzp() {
        return Collections.unmodifiableList(((zzft)this.zza).zzi());
    }

    public final boolean zzq() {
        return ((zzft)this.zza).zzu();
    }
}

