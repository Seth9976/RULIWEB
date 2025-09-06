package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzff extends zzlb implements zzmj {
    private static final zzff zza;
    private int zzd;
    private long zze;
    private String zzf;
    private int zzg;
    private zzli zzh;
    private zzli zzi;
    private zzli zzj;
    private String zzk;
    private boolean zzl;
    private zzli zzm;
    private zzli zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;

    static {
        zzff zzff0 = new zzff();
        zzff.zza = zzff0;
        zzlb.zzbO(zzff.class, zzff0);
    }

    private zzff() {
        this.zzf = "";
        this.zzh = zzff.zzbH();
        this.zzi = zzff.zzbH();
        this.zzj = zzff.zzbH();
        this.zzk = "";
        this.zzm = zzff.zzbH();
        this.zzn = zzff.zzbH();
        this.zzo = "";
        this.zzp = "";
        this.zzq = "";
        this.zzr = "";
    }

    public final int zza() {
        return this.zzm.size();
    }

    public final int zzb() {
        return this.zzi.size();
    }

    public final long zzc() {
        return this.zze;
    }

    public final zzfd zzd(int v) {
        return (zzfd)this.zzi.get(v);
    }

    public static zzfe zze() {
        return (zzfe)zzff.zza.zzbA();
    }

    static zzff zzf() {
        return zzff.zza;
    }

    public static zzff zzg() {
        return zzff.zza;
    }

    public final String zzh() [...] // 潜在的解密器

    public final String zzi() [...] // 潜在的解密器

    public final String zzj() [...] // 潜在的解密器

    public final String zzk() [...] // 潜在的解密器

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzff.zzbL(zzff.zza, "\u0001\u000E\u0000\u0001\u0001\u000E\u000E\u0000\u0005\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001B\u0005\u001B\u0006\u001B\u0007ဈ\u0003\bဇ\u0004\t\u001B\n\u001B\u000Bဈ\u0005\fဈ\u0006\rဈ\u0007\u000Eဈ\b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzfj.class, "zzi", zzfd.class, "zzj", zzei.class, "zzk", "zzl", "zzm", zzgt.class, "zzn", zzfb.class, "zzo", "zzp", "zzq", "zzr"});
                }
                case 3: {
                    return new zzff();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfe(null);
                        }
                        case 5: {
                            return zzff.zza;
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

    public final List zzm() {
        return this.zzj;
    }

    public final List zzn() {
        return this.zzn;
    }

    public final List zzo() {
        return this.zzm;
    }

    public final List zzp() {
        return this.zzh;
    }

    static void zzq(zzff zzff0, int v, zzfd zzfd0) {
        zzfd0.getClass();
        zzli zzli0 = zzff0.zzi;
        if(!zzli0.zzc()) {
            zzff0.zzi = zzlb.zzbI(zzli0);
        }
        zzff0.zzi.set(v, zzfd0);
    }

    static void zzr(zzff zzff0) {
        zzff0.zzj = zzff.zzbH();
    }

    public final boolean zzs() {
        return this.zzl;
    }

    public final boolean zzt() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 1) != 0;
    }
}

