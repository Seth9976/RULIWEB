package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgi extends zzlb implements zzmj {
    private static final zzgi zza;
    private zzlh zzd;
    private zzlh zze;
    private zzli zzf;
    private zzli zzg;

    static {
        zzgi zzgi0 = new zzgi();
        zzgi.zza = zzgi0;
        zzlb.zzbO(zzgi.class, zzgi0);
    }

    private zzgi() {
        this.zzd = zzgi.zzbF();
        this.zze = zzgi.zzbF();
        this.zzf = zzgi.zzbH();
        this.zzg = zzgi.zzbH();
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze.size();
    }

    public final int zzc() {
        return this.zzg.size();
    }

    public final int zzd() {
        return this.zzd.size();
    }

    public static zzgh zze() {
        return (zzgh)zzgi.zza.zzbA();
    }

    static zzgi zzf() {
        return zzgi.zza;
    }

    public static zzgi zzg() {
        return zzgi.zza;
    }

    public final List zzh() {
        return this.zzf;
    }

    public final List zzi() {
        return this.zze;
    }

    public final List zzj() {
        return this.zzg;
    }

    public final List zzk() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgi.zzbL(zzgi.zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001B\u0004\u001B", new Object[]{"zzd", "zze", "zzf", zzfr.class, "zzg", zzgk.class});
                }
                case 3: {
                    return new zzgi();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgh(null);
                        }
                        case 5: {
                            return zzgi.zza;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }
        }
        return (byte)1;
    }

    static void zzm(zzgi zzgi0, Iterable iterable0) {
        zzlh zzlh0 = zzgi0.zzd;
        if(!zzlh0.zzc()) {
            zzgi0.zzd = zzlb.zzbG(zzlh0);
        }
        zzjk.zzbw(iterable0, zzgi0.zzd);
    }

    static void zzn(zzgi zzgi0) {
        zzgi0.zzd = zzgi.zzbF();
    }

    static void zzo(zzgi zzgi0, Iterable iterable0) {
        zzlh zzlh0 = zzgi0.zze;
        if(!zzlh0.zzc()) {
            zzgi0.zze = zzlb.zzbG(zzlh0);
        }
        zzjk.zzbw(iterable0, zzgi0.zze);
    }

    static void zzp(zzgi zzgi0) {
        zzgi0.zze = zzgi.zzbF();
    }

    static void zzq(zzgi zzgi0, Iterable iterable0) {
        zzli zzli0 = zzgi0.zzf;
        if(!zzli0.zzc()) {
            zzgi0.zzf = zzlb.zzbI(zzli0);
        }
        zzjk.zzbw(iterable0, zzgi0.zzf);
    }

    static void zzr(zzgi zzgi0) {
        zzgi0.zzf = zzgi.zzbH();
    }

    static void zzs(zzgi zzgi0, Iterable iterable0) {
        zzli zzli0 = zzgi0.zzg;
        if(!zzli0.zzc()) {
            zzgi0.zzg = zzlb.zzbI(zzli0);
        }
        zzjk.zzbw(iterable0, zzgi0.zzg);
    }

    static void zzt(zzgi zzgi0) {
        zzgi0.zzg = zzgi.zzbH();
    }
}

