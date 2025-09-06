package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzey extends zzlb implements zzmj {
    private static final zzey zza;
    private int zzd;
    private int zze;
    private String zzf;
    private boolean zzg;
    private zzli zzh;

    static {
        zzey zzey0 = new zzey();
        zzey.zza = zzey0;
        zzlb.zzbO(zzey.class, zzey0);
    }

    private zzey() {
        this.zzf = "";
        this.zzh = zzlb.zzbH();
    }

    public final int zza() {
        return this.zzh.size();
    }

    static zzey zzb() {
        return zzey.zza;
    }

    public static zzey zzc() {
        return zzey.zza;
    }

    public final String zzd() [...] // 潜在的解密器

    public final List zze() {
        return this.zzh;
    }

    public final boolean zzf() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 1) != 0;
    }

    public final int zzj() {
        int v = zzex.zza(this.zze);
        return v == 0 ? 1 : v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlb
    protected final Object zzl(int v, Object object0, Object object1) {
        if(v - 1 != 0) {
            switch(v - 1) {
                case 2: {
                    return zzey.zzbL(zzey.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001A", new Object[]{"zzd", "zze", zzew.zza, "zzf", "zzg", "zzh"});
                }
                case 3: {
                    return new zzey();
                }
                default: {
                    switch(v - 1) {
                        case 4: {
                            return new zzeu(null);
                        }
                        case 5: {
                            return zzey.zza;
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

