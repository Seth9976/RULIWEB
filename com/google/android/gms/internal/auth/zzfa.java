package com.google.android.gms.internal.auth;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzfa {
    static final Charset zza;
    static final Charset zzb;
    static final Charset zzc;
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzej zzf;

    static {
        zzfa.zza = Charset.forName("US-ASCII");
        zzfa.zzb = Charset.forName("UTF-8");
        zzfa.zzc = Charset.forName("ISO-8859-1");
        byte[] arr_b = new byte[0];
        zzfa.zzd = arr_b;
        zzfa.zze = ByteBuffer.wrap(arr_b);
        zzeh zzeh0 = new zzeh(arr_b, 0, 0, false, null);
        try {
            zzeh0.zza(0);
            zzfa.zzf = zzeh0;
        }
        catch(zzfb zzfb0) {
            throw new IllegalArgumentException(zzfb0);
        }
    }

    // 去混淆评级： 低(20)
    public static int zza(boolean z) {
        return z ? 0x4CF : 0x4D5;
    }

    static int zzb(int v, byte[] arr_b, int v1, int v2) {
        for(int v3 = 0; v3 < v2; ++v3) {
            v = v * 0x1F + arr_b[v3];
        }
        return v;
    }

    static Object zzc(Object object0, String s) {
        if(object0 == null) {
            throw new NullPointerException("messageType");
        }
        return object0;
    }

    public static String zzd(byte[] arr_b) {
        return new String(arr_b, zzfa.zzb);
    }
}

