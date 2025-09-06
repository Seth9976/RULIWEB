package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzfx extends zzlb implements zzmj {
    private static final zzfx zza;
    private int zzd;
    private String zze;
    private String zzf;
    private long zzg;
    private float zzh;
    private double zzi;
    private zzli zzj;

    static {
        zzfx zzfx0 = new zzfx();
        zzfx.zza = zzfx0;
        zzlb.zzbO(zzfx.class, zzfx0);
    }

    private zzfx() {
        this.zze = "";
        this.zzf = "";
        this.zzj = zzfx.zzbH();
    }

    public final double zza() {
        return this.zzi;
    }

    public final float zzb() {
        return this.zzh;
    }

    public final int zzc() {
        return this.zzj.size();
    }

    public final long zzd() {
        return this.zzg;
    }

    public static zzfw zze() {
        return (zzfw)zzfx.zza.zzbA();
    }

    static zzfx zzf() {
        return zzfx.zza;
    }

    public final String zzg() [...] // 潜在的解密器

    public final String zzh() [...] // 潜在的解密器

    public final List zzi() {
        return this.zzj;
    }

    static void zzj(zzfx zzfx0, String s) {
        s.getClass();
        zzfx0.zzd |= 1;
        zzfx0.zze = s;
    }

    static void zzk(zzfx zzfx0, String s) {
        s.getClass();
        zzfx0.zzd |= 2;
        zzfx0.zzf = s;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzfx.zzbL(zzfx.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001B", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzfx.class});
                }
                case 3: {
                    return new zzfx();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfw(null);
                        }
                        case 5: {
                            return zzfx.zza;
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

    static void zzm(zzfx zzfx0) {
        zzfx0.zzd &= -3;
        zzfx0.zzf = "";
    }

    static void zzn(zzfx zzfx0, long v) {
        zzfx0.zzd |= 4;
        zzfx0.zzg = v;
    }

    static void zzo(zzfx zzfx0) {
        zzfx0.zzd &= -5;
        zzfx0.zzg = 0L;
    }

    static void zzp(zzfx zzfx0, double f) {
        zzfx0.zzd |= 16;
        zzfx0.zzi = f;
    }

    static void zzq(zzfx zzfx0) {
        zzfx0.zzd &= -17;
        zzfx0.zzi = 0.0;
    }

    static void zzr(zzfx zzfx0, zzfx zzfx1) {
        zzfx1.getClass();
        zzfx0.zzz();
        zzfx0.zzj.add(zzfx1);
    }

    static void zzs(zzfx zzfx0, Iterable iterable0) {
        zzfx0.zzz();
        zzjk.zzbw(iterable0, zzfx0.zzj);
    }

    static void zzt(zzfx zzfx0) {
        zzfx0.zzj = zzfx.zzbH();
    }

    public final boolean zzu() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzw() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzx() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzy() {
        return (this.zzd & 2) != 0;
    }

    private final void zzz() {
        zzli zzli0 = this.zzj;
        if(!zzli0.zzc()) {
            this.zzj = zzlb.zzbI(zzli0);
        }
    }
}

