package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzft extends zzlb implements zzmj {
    private static final zzft zza;
    private int zzd;
    private zzli zze;
    private String zzf;
    private long zzg;
    private long zzh;
    private int zzi;

    static {
        zzft zzft0 = new zzft();
        zzft.zza = zzft0;
        zzlb.zzbO(zzft.class, zzft0);
    }

    private zzft() {
        this.zze = zzft.zzbH();
        this.zzf = "";
    }

    public final int zza() {
        return this.zzi;
    }

    public final int zzb() {
        return this.zze.size();
    }

    public final long zzc() {
        return this.zzh;
    }

    public final long zzd() {
        return this.zzg;
    }

    public static zzfs zze() {
        return (zzfs)zzft.zza.zzbA();
    }

    static zzft zzf() {
        return zzft.zza;
    }

    public final zzfx zzg(int v) {
        return (zzfx)this.zze.get(v);
    }

    public final String zzh() [...] // 潜在的解密器

    public final List zzi() {
        return this.zze;
    }

    static void zzj(zzft zzft0, int v, zzfx zzfx0) {
        zzfx0.getClass();
        zzft0.zzv();
        zzft0.zze.set(v, zzfx0);
    }

    static void zzk(zzft zzft0, zzfx zzfx0) {
        zzfx0.getClass();
        zzft0.zzv();
        zzft0.zze.add(zzfx0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzft.zzbL(zzft.zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001B\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zzd", "zze", zzfx.class, "zzf", "zzg", "zzh", "zzi"});
                }
                case 3: {
                    return new zzft();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzfs(null);
                        }
                        case 5: {
                            return zzft.zza;
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

    static void zzm(zzft zzft0, Iterable iterable0) {
        zzft0.zzv();
        zzjk.zzbw(iterable0, zzft0.zze);
    }

    static void zzn(zzft zzft0) {
        zzft0.zze = zzft.zzbH();
    }

    static void zzo(zzft zzft0, int v) {
        zzft0.zzv();
        zzft0.zze.remove(v);
    }

    static void zzp(zzft zzft0, String s) {
        s.getClass();
        zzft0.zzd |= 1;
        zzft0.zzf = s;
    }

    static void zzq(zzft zzft0, long v) {
        zzft0.zzd |= 2;
        zzft0.zzg = v;
    }

    static void zzr(zzft zzft0, long v) {
        zzft0.zzd |= 4;
        zzft0.zzh = v;
    }

    public final boolean zzs() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzt() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 2) != 0;
    }

    private final void zzv() {
        zzli zzli0 = this.zze;
        if(!zzli0.zzc()) {
            this.zze = zzlb.zzbI(zzli0);
        }
    }
}

