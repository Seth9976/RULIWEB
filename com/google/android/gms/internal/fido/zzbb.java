package com.google.android.gms.internal.fido;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

final class zzbb {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    private final char[] zzf;
    private final byte[] zzg;
    private final boolean zzh;

    zzbb(String s, char[] arr_c) {
        byte[] arr_b = new byte[0x80];
        Arrays.fill(arr_b, -1);
        for(int v = 0; v < arr_c.length; ++v) {
            int v1 = arr_c[v];
            boolean z = true;
            zzam.zzd(v1 < 0x80, "Non-ASCII character: %s", ((char)v1));
            if(arr_b[v1] != -1) {
                z = false;
            }
            zzam.zzd(z, "Duplicate character: %s", ((char)v1));
            arr_b[v1] = (byte)v;
        }
        this(s, arr_c, arr_b, false);
    }

    private zzbb(String s, char[] arr_c, byte[] arr_b, boolean z) {
        int v;
        this.zze = s;
        arr_c.getClass();
        this.zzf = arr_c;
        try {
            v = zzbh.zzb(arr_c.length, RoundingMode.UNNECESSARY);
            this.zzb = v;
        }
        catch(ArithmeticException arithmeticException0) {
            throw new IllegalArgumentException("Illegal alphabet length " + arr_c.length, arithmeticException0);
        }
        int v1 = Integer.numberOfTrailingZeros(v);
        int v2 = 1 << 3 - v1;
        this.zzc = v2;
        this.zzd = v >> v1;
        this.zza = arr_c.length - 1;
        this.zzg = arr_b;
        boolean[] arr_z = new boolean[v2];
        for(int v3 = 0; v3 < this.zzd; ++v3) {
            arr_z[zzbh.zza(v3 * 8, this.zzb, RoundingMode.CEILING)] = true;
        }
        this.zzh = false;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(@CheckForNull Object object0) {
        return object0 instanceof zzbb && Arrays.equals(this.zzf, ((zzbb)object0).zzf);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(this.zzf) + 0x4D5;
    }

    @Override
    public final String toString() {
        return this.zze;
    }

    final char zza(int v) {
        return this.zzf[v];
    }

    public final boolean zzb(char c) {
        return this.zzg[61] != -1;
    }
}

