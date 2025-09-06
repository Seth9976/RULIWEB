package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

final class zzh {
    private long zzA;
    private long zzB;
    private long zzC;
    private long zzD;
    private String zzE;
    private boolean zzF;
    private long zzG;
    private long zzH;
    private final zzgd zza;
    private final String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private String zzj;
    private long zzk;
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private boolean zzp;
    private String zzq;
    private Boolean zzr;
    private long zzs;
    private List zzt;
    private String zzu;
    private boolean zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    zzh(zzgd zzgd0, String s) {
        Preconditions.checkNotNull(zzgd0);
        Preconditions.checkNotEmpty(s);
        this.zza = zzgd0;
        this.zzb = s;
        zzgd0.zzaB().zzg();
    }

    public final String zzA() {
        this.zza.zzaB().zzg();
        return this.zzd;
    }

    public final String zzB() {
        this.zza.zzaB().zzg();
        return this.zzE;
    }

    public final String zzC() {
        this.zza.zzaB().zzg();
        return this.zze;
    }

    public final String zzD() {
        this.zza.zzaB().zzg();
        return this.zzu;
    }

    public final List zzE() {
        this.zza.zzaB().zzg();
        return this.zzt;
    }

    public final void zzF() {
        this.zza.zzaB().zzg();
        this.zzF = false;
    }

    public final void zzG() {
        this.zza.zzaB().zzg();
        long v = this.zzg + 1L;
        if(v > 0x7FFFFFFFL) {
            this.zza.zzaA().zzk().zzb("Bundle index overflow. appId", zzet.zzn(this.zzb));
            v = 0L;
        }
        this.zzF = true;
        this.zzg = v;
    }

    public final void zzH(String s) {
        this.zza.zzaB().zzg();
        if(TextUtils.isEmpty(s)) {
            s = null;
        }
        this.zzF |= true ^ zzg.zza(this.zzq, s);
        this.zzq = s;
    }

    public final void zzI(boolean z) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzp != z;
        this.zzp = z;
    }

    public final void zzJ(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzc, s);
        this.zzc = s;
    }

    public final void zzK(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzl, s);
        this.zzl = s;
    }

    public final void zzL(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzj, s);
        this.zzj = s;
    }

    public final void zzM(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzk != v;
        this.zzk = v;
    }

    public final void zzN(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzG != v;
        this.zzG = v;
    }

    public final void zzO(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzB != v;
        this.zzB = v;
    }

    public final void zzP(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzC != v;
        this.zzC = v;
    }

    public final void zzQ(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzA != v;
        this.zzA = v;
    }

    public final void zzR(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzz != v;
        this.zzz = v;
    }

    public final void zzS(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzD != v;
        this.zzD = v;
    }

    public final void zzT(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzy != v;
        this.zzy = v;
    }

    public final void zzU(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzn != v;
        this.zzn = v;
    }

    public final void zzV(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzs != v;
        this.zzs = v;
    }

    public final void zzW(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzH != v;
        this.zzH = v;
    }

    public final void zzX(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzf, s);
        this.zzf = s;
    }

    public final void zzY(String s) {
        this.zza.zzaB().zzg();
        if(TextUtils.isEmpty(s)) {
            s = null;
        }
        this.zzF |= true ^ zzg.zza(this.zzd, s);
        this.zzd = s;
    }

    public final void zzZ(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzm != v;
        this.zzm = v;
    }

    public final long zza() {
        this.zza.zzaB().zzg();
        return 0L;
    }

    public final void zzaa(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzE, s);
        this.zzE = s;
    }

    public final void zzab(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzi != v;
        this.zzi = v;
    }

    public final void zzac(long v) {
        boolean z = true;
        Preconditions.checkArgument(v >= 0L);
        this.zza.zzaB().zzg();
        boolean z1 = this.zzF;
        if(this.zzg == v) {
            z = false;
        }
        this.zzF = z1 | z;
        this.zzg = v;
    }

    public final void zzad(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzh != v;
        this.zzh = v;
    }

    public final void zzae(boolean z) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzo != z;
        this.zzo = z;
    }

    public final void zzaf(Boolean boolean0) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzr, boolean0);
        this.zzr = boolean0;
    }

    public final void zzag(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zze, s);
        this.zze = s;
    }

    public final void zzah(List list0) {
        this.zza.zzaB().zzg();
        if(!zzg.zza(this.zzt, list0)) {
            this.zzF = true;
            this.zzt = list0 == null ? null : new ArrayList(list0);
        }
    }

    public final void zzai(String s) {
        this.zza.zzaB().zzg();
        this.zzF |= !zzg.zza(this.zzu, s);
        this.zzu = s;
    }

    public final void zzaj(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzx != v;
        this.zzx = v;
    }

    public final void zzak(boolean z) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzv != z;
        this.zzv = z;
    }

    public final void zzal(long v) {
        this.zza.zzaB().zzg();
        this.zzF |= this.zzw != v;
        this.zzw = v;
    }

    public final boolean zzam() {
        this.zza.zzaB().zzg();
        return this.zzp;
    }

    public final boolean zzan() {
        this.zza.zzaB().zzg();
        return this.zzo;
    }

    public final boolean zzao() {
        this.zza.zzaB().zzg();
        return this.zzF;
    }

    public final boolean zzap() {
        this.zza.zzaB().zzg();
        return this.zzv;
    }

    public final long zzb() {
        this.zza.zzaB().zzg();
        return this.zzk;
    }

    public final long zzc() {
        this.zza.zzaB().zzg();
        return this.zzG;
    }

    public final long zzd() {
        this.zza.zzaB().zzg();
        return this.zzB;
    }

    public final long zze() {
        this.zza.zzaB().zzg();
        return this.zzC;
    }

    public final long zzf() {
        this.zza.zzaB().zzg();
        return this.zzA;
    }

    public final long zzg() {
        this.zza.zzaB().zzg();
        return this.zzz;
    }

    public final long zzh() {
        this.zza.zzaB().zzg();
        return this.zzD;
    }

    public final long zzi() {
        this.zza.zzaB().zzg();
        return this.zzy;
    }

    public final long zzj() {
        this.zza.zzaB().zzg();
        return this.zzn;
    }

    public final long zzk() {
        this.zza.zzaB().zzg();
        return this.zzs;
    }

    public final long zzl() {
        this.zza.zzaB().zzg();
        return this.zzH;
    }

    public final long zzm() {
        this.zza.zzaB().zzg();
        return this.zzm;
    }

    public final long zzn() {
        this.zza.zzaB().zzg();
        return this.zzi;
    }

    public final long zzo() {
        this.zza.zzaB().zzg();
        return this.zzg;
    }

    public final long zzp() {
        this.zza.zzaB().zzg();
        return this.zzh;
    }

    public final long zzq() {
        this.zza.zzaB().zzg();
        return this.zzx;
    }

    public final long zzr() {
        this.zza.zzaB().zzg();
        return this.zzw;
    }

    public final Boolean zzs() {
        this.zza.zzaB().zzg();
        return this.zzr;
    }

    public final String zzt() {
        this.zza.zzaB().zzg();
        return this.zzq;
    }

    public final String zzu() {
        this.zza.zzaB().zzg();
        String s = this.zzE;
        this.zzaa(null);
        return s;
    }

    public final String zzv() {
        this.zza.zzaB().zzg();
        return this.zzb;
    }

    public final String zzw() {
        this.zza.zzaB().zzg();
        return this.zzc;
    }

    public final String zzx() {
        this.zza.zzaB().zzg();
        return this.zzl;
    }

    public final String zzy() {
        this.zza.zzaB().zzg();
        return this.zzj;
    }

    public final String zzz() {
        this.zza.zzaB().zzg();
        return this.zzf;
    }
}

