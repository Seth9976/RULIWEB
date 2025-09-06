package com.google.android.gms.internal.auth;

import java.util.List;

public final class zzhs extends zzev implements zzfy {
    private static final zzhs zzb;
    private zzez zzd;

    static {
        zzhs zzhs0 = new zzhs();
        zzhs.zzb = zzhs0;
        zzev.zzk(zzhs.class, zzhs0);
    }

    private zzhs() {
        this.zzd = zzev.zzf();
    }

    @Override  // com.google.android.gms.internal.auth.zzev
    protected final Object zzn(int v, Object object0, Object object1) {
        switch(v - 1) {
            case 0: {
                return (byte)1;
            }
            case 2: {
                return zzhs.zzh(zzhs.zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001A", new Object[]{"zzd"});
            }
            case 3: {
                return new zzhs();
            }
            case 4: {
                return new zzhr(null);
            }
            case 5: {
                return zzhs.zzb;
            }
            default: {
                return null;
            }
        }
    }

    static zzhs zzo() {
        return zzhs.zzb;
    }

    public static zzhs zzp(byte[] arr_b) throws zzfb {
        return (zzhs)zzev.zzd(zzhs.zzb, arr_b);
    }

    public final List zzq() {
        return this.zzd;
    }
}

