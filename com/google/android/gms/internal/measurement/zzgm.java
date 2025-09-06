package com.google.android.gms.internal.measurement;

public final class zzgm extends zzlb implements zzmj {
    private static final zzgm zza;
    private int zzd;
    private long zze;
    private String zzf;
    private String zzg;
    private long zzh;
    private float zzi;
    private double zzj;

    static {
        zzgm zzgm0 = new zzgm();
        zzgm.zza = zzgm0;
        zzlb.zzbO(zzgm.class, zzgm0);
    }

    private zzgm() {
        this.zzf = "";
        this.zzg = "";
    }

    public final double zza() {
        return this.zzj;
    }

    public final long zzb() {
        return this.zzh;
    }

    public final long zzc() {
        return this.zze;
    }

    public static zzgl zzd() {
        return (zzgl)zzgm.zza.zzbA();
    }

    static zzgm zze() {
        return zzgm.zza;
    }

    public final String zzf() [...] // 潜在的解密器

    public final String zzg() [...] // 潜在的解密器

    static void zzh(zzgm zzgm0, long v) {
        zzgm0.zzd |= 1;
        zzgm0.zze = v;
    }

    static void zzi(zzgm zzgm0, String s) {
        s.getClass();
        zzgm0.zzd |= 2;
        zzgm0.zzf = s;
    }

    static void zzj(zzgm zzgm0, String s) {
        s.getClass();
        zzgm0.zzd |= 4;
        zzgm0.zzg = s;
    }

    static void zzk(zzgm zzgm0) {
        zzgm0.zzd &= -5;
        zzgm0.zzg = "";
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgm.zzbL(zzgm.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
                }
                case 3: {
                    return new zzgm();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgl(null);
                        }
                        case 5: {
                            return zzgm.zza;
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

    static void zzm(zzgm zzgm0, long v) {
        zzgm0.zzd |= 8;
        zzgm0.zzh = v;
    }

    static void zzn(zzgm zzgm0) {
        zzgm0.zzd &= -9;
        zzgm0.zzh = 0L;
    }

    static void zzo(zzgm zzgm0, double f) {
        zzgm0.zzd |= 0x20;
        zzgm0.zzj = f;
    }

    static void zzp(zzgm zzgm0) {
        zzgm0.zzd &= -33;
        zzgm0.zzj = 0.0;
    }

    public final boolean zzq() {
        return (this.zzd & 0x20) != 0;
    }

    public final boolean zzr() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzs() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzt() {
        return (this.zzd & 4) != 0;
    }
}

