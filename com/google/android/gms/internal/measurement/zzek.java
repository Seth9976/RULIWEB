package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzek extends zzlb implements zzmj {
    private static final zzek zza;
    private int zzd;
    private int zze;
    private String zzf;
    private zzli zzg;
    private boolean zzh;
    private zzer zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;

    static {
        zzek zzek0 = new zzek();
        zzek.zza = zzek0;
        zzlb.zzbO(zzek.class, zzek0);
    }

    private zzek() {
        this.zzf = "";
        this.zzg = zzek.zzbH();
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zze;
    }

    public static zzej zzc() {
        return (zzej)zzek.zza.zzbA();
    }

    static zzek zzd() {
        return zzek.zza;
    }

    public final zzem zze(int v) {
        return (zzem)this.zzg.get(v);
    }

    public final zzer zzf() {
        return this.zzi == null ? zzer.zzb() : this.zzi;
    }

    public final String zzg() [...] // 潜在的解密器

    public final List zzh() {
        return this.zzg;
    }

    static void zzi(zzek zzek0, String s) {
        zzek0.zzd |= 2;
        zzek0.zzf = s;
    }

    static void zzj(zzek zzek0, int v, zzem zzem0) {
        zzem0.getClass();
        zzli zzli0 = zzek0.zzg;
        if(!zzli0.zzc()) {
            zzek0.zzg = zzlb.zzbI(zzli0);
        }
        zzek0.zzg.set(v, zzem0);
    }

    public final boolean zzk() {
        return this.zzj;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzek.zzbL(zzek.zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001B\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", zzem.class, "zzh", "zzi", "zzj", "zzk", "zzl"});
                }
                case 3: {
                    return new zzek();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzej(null);
                        }
                        case 5: {
                            return zzek.zza;
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

    public final boolean zzm() {
        return this.zzk;
    }

    public final boolean zzn() {
        return this.zzl;
    }

    public final boolean zzo() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzp() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzq() {
        return (this.zzd & 0x40) != 0;
    }
}

