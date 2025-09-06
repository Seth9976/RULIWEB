package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgk extends zzlb implements zzmj {
    private static final zzgk zza;
    private int zzd;
    private int zze;
    private zzlh zzf;

    static {
        zzgk zzgk0 = new zzgk();
        zzgk.zza = zzgk0;
        zzlb.zzbO(zzgk.class, zzgk0);
    }

    private zzgk() {
        this.zzf = zzgk.zzbF();
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze;
    }

    public final long zzc(int v) {
        return this.zzf.zza(v);
    }

    public static zzgj zzd() {
        return (zzgj)zzgk.zza.zzbA();
    }

    static zzgk zze() {
        return zzgk.zza;
    }

    public final List zzf() {
        return this.zzf;
    }

    static void zzg(zzgk zzgk0, int v) {
        zzgk0.zzd |= 1;
        zzgk0.zze = v;
    }

    static void zzh(zzgk zzgk0, Iterable iterable0) {
        zzlh zzlh0 = zzgk0.zzf;
        if(!zzlh0.zzc()) {
            zzgk0.zzf = zzlb.zzbG(zzlh0);
        }
        zzjk.zzbw(iterable0, zzgk0.zzf);
    }

    public final boolean zzi() {
        return (this.zzd & 1) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzgk.zzbL(zzgk.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zzd", "zze", "zzf"});
                }
                case 3: {
                    return new zzgk();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzgj(null);
                        }
                        case 5: {
                            return zzgk.zza;
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

