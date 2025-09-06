package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzki extends zzjq {
    zzkj zza;
    public static final int zzb;
    private static final Logger zzc;
    private static final boolean zzd;

    static {
        zzki.zzc = Logger.getLogger("com.google.android.gms.internal.measurement.zzki");
        zzki.zzd = false;
    }

    private zzki() {
    }

    zzki(zzkh zzkh0) {
    }

    public final void zzA() {
        if(this.zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzB(String s, zzny zzny0) throws IOException {
        zzki.zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzny0);
        byte[] arr_b = s.getBytes(zzlj.zzb);
        try {
            this.zzq(arr_b.length);
            this.zzl(arr_b, 0, arr_b.length);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(indexOutOfBoundsException0);
        }
    }

    static boolean zzC() [...] // 潜在的解密器

    public abstract int zza();

    public abstract void zzb(byte arg1) throws IOException;

    public abstract void zzd(int arg1, boolean arg2) throws IOException;

    public abstract void zze(int arg1, zzka arg2) throws IOException;

    public abstract void zzf(int arg1, int arg2) throws IOException;

    public abstract void zzg(int arg1) throws IOException;

    public abstract void zzh(int arg1, long arg2) throws IOException;

    public abstract void zzi(long arg1) throws IOException;

    public abstract void zzj(int arg1, int arg2) throws IOException;

    public abstract void zzk(int arg1) throws IOException;

    public abstract void zzl(byte[] arg1, int arg2, int arg3) throws IOException;

    public abstract void zzm(int arg1, String arg2) throws IOException;

    public abstract void zzo(int arg1, int arg2) throws IOException;

    public abstract void zzp(int arg1, int arg2) throws IOException;

    public abstract void zzq(int arg1) throws IOException;

    public abstract void zzr(int arg1, long arg2) throws IOException;

    public abstract void zzs(long arg1) throws IOException;

    @Deprecated
    static int zzt(int v, zzmi zzmi0, zzmt zzmt0) {
        int v1 = zzki.zzx(v << 3);
        return v1 + v1 + ((zzjk)zzmi0).zzbu(zzmt0);
    }

    public static int zzu(int v) {
        return v < 0 ? 10 : zzki.zzx(v);
    }

    static int zzv(zzmi zzmi0, zzmt zzmt0) {
        int v = ((zzjk)zzmi0).zzbu(zzmt0);
        return zzki.zzx(v) + v;
    }

    public static int zzw(String s) {
        int v;
        try {
            v = zznz.zzc(s);
            return zzki.zzx(v) + v;
        }
        catch(zzny unused_ex) {
            v = s.getBytes(zzlj.zzb).length;
            return zzki.zzx(v) + v;
        }
    }

    public static int zzx(int v) [...] // 潜在的解密器

    public static int zzy(long v) {
        int v1;
        if((0xFFFFFFFFFFFFFF80L & v) == 0L) {
            return 1;
        }
        if(v < 0L) {
            return 10;
        }
        if((0xFFFFFFF800000000L & v) == 0L) {
            v1 = 2;
        }
        else {
            v >>>= 28;
            v1 = 6;
        }
        if((0xFFFFFFFFFFE00000L & v) != 0L) {
            v >>>= 14;
            v1 += 2;
        }
        return (v & 0xFFFFFFFFFFFFC000L) == 0L ? v1 : v1 + 1;
    }

    public static zzki zzz(byte[] arr_b, int v, int v1) {
        return new zzkf(arr_b, 0, v1);
    }
}

