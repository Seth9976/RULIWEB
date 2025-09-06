package com.google.android.gms.internal.fido;

import java.io.IOException;

public abstract class zzbf {
    private static final zzbf zza;
    private static final zzbf zzb;
    private static final zzbf zzc;
    private static final zzbf zzd;
    private static final zzbf zze;

    static {
        zzbf.zza = new zzbd("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", Character.valueOf('='));
        zzbf.zzb = new zzbd("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", Character.valueOf('='));
        zzbf.zzc = new zzbe("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", Character.valueOf('='));
        zzbf.zzd = new zzbe("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", Character.valueOf('='));
        zzbf.zze = new zzbc("base16()", "0123456789ABCDEF");
    }

    abstract void zza(Appendable arg1, byte[] arg2, int arg3, int arg4) throws IOException;

    abstract int zzb(int arg1);

    public static zzbf zzd() {
        return zzbf.zze;
    }

    public final String zze(byte[] arr_b, int v, int v1) {
        zzam.zze(0, v1, arr_b.length);
        StringBuilder stringBuilder0 = new StringBuilder(this.zzb(v1));
        try {
            this.zza(stringBuilder0, arr_b, 0, v1);
            return stringBuilder0.toString();
        }
        catch(IOException iOException0) {
            throw new AssertionError(iOException0);
        }
    }
}

