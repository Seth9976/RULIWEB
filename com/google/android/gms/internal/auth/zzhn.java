package com.google.android.gms.internal.auth;

final class zzhn {
    public static final int zza;
    private static final zzhl zzb;

    static {
        zzhn.zzb = new zzhm();
    }

    static int zza(byte[] arr_b, int v, int v1) {
        int v2 = arr_b[v - 1];
        switch(v1 - v) {
            case 0: {
                return v2 <= -12 ? v2 : -1;
            }
            case 1: {
                int v3 = arr_b[v];
                return v2 <= -12 && v3 <= -65 ? v3 << 8 ^ v2 : -1;
            }
            case 2: {
                int v4 = arr_b[v];
                int v5 = arr_b[v + 1];
                return v2 <= -12 && v4 <= -65 && v5 <= -65 ? v5 << 16 ^ (v4 << 8 ^ v2) : -1;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    static boolean zzb(byte[] arr_b) {
        return zzhn.zzb.zzb(arr_b, 0, arr_b.length);
    }

    static boolean zzc(byte[] arr_b, int v, int v1) {
        return zzhn.zzb.zzb(arr_b, v, v1);
    }
}

