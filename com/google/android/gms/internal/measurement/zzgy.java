package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgy extends zzlb implements zzmj {
    private static final zzgy zza;
    private int zzd;
    private int zze;
    private zzli zzf;
    private String zzg;
    private String zzh;
    private boolean zzi;
    private double zzj;

    static {
        zzgy zzgy0 = new zzgy();
        zzgy.zza = zzgy0;
        zzlb.zzbO(zzgy.class, zzgy0);
    }

    private zzgy() {
        this.zzf = zzgy.zzbH();
        this.zzg = "";
        this.zzh = "";
    }

    public final double zza() {
        return this.zzj;
    }

    static zzgy zzb() {
        return zzgy.zza;
    }

    public final String zzc() [...] // 潜在的解密器

    public final String zzd() [...] // 潜在的解密器

    public final List zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        return this.zzi;
    }

    public final boolean zzg() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 4) != 0;
    }

    public final int zzj() {
        int v = zzgx.zza(this.zze);
        return v == 0 ? 1 : v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgy.zzbL(zzgy.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001B\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zzd", "zze", zzgw.zza, "zzf", zzgy.class, "zzg", "zzh", "zzi", "zzj"});
                }
                case 3: {
                    return new zzgy();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgu(null);
                        }
                        case 5: {
                            return zzgy.zza;
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
}

