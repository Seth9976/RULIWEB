package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

public abstract class zzef implements Serializable, Iterable {
    private static final Comparator zza;
    public static final zzef zzb;
    private int zzc;
    private static final zzee zzd;

    static {
        zzef.zzb = new zzec(zzfa.zzd);
        zzef.zzd = new zzee(null);
        zzef.zza = new zzdx();
    }

    zzef() {
        this.zzc = 0;
    }

    @Override
    public abstract boolean equals(Object arg1);

    @Override
    public final int hashCode() {
        int v = this.zzc;
        if(v == 0) {
            int v1 = this.zzd();
            v = this.zze(v1, 0, v1);
            if(v == 0) {
                v = 1;
            }
            this.zzc = v;
        }
        return v;
    }

    @Override
    public final Iterator iterator() {
        return new zzdw(this);
    }

    @Override
    public final String toString() {
        Locale locale0 = Locale.ROOT;
        String s = Integer.toHexString(System.identityHashCode(this));
        Integer integer0 = this.zzd();
        return this.zzd() > 50 ? String.format(locale0, "<ByteString@%s size=%d contents=\"%s\">", s, integer0, zzgx.zza(this.zzf(0, 0x2F)) + "...") : String.format(locale0, "<ByteString@%s size=%d contents=\"%s\">", s, integer0, zzgx.zza(this));
    }

    public abstract byte zza(int arg1);

    abstract byte zzb(int arg1);

    public abstract int zzd();

    protected abstract int zze(int arg1, int arg2, int arg3);

    public abstract zzef zzf(int arg1, int arg2);

    protected abstract String zzg(Charset arg1);

    public abstract boolean zzh();

    static int zzi(int v, int v1, int v2) {
        int v3 = v1 - v;
        if((v | v1 | v3 | v2 - v1) < 0) {
            if(v < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + v + " < 0");
            }
            throw v1 >= v ? new IndexOutOfBoundsException("End index: " + v1 + " >= " + v2) : new IndexOutOfBoundsException("Beginning index larger than ending index: " + v + ", " + v1);
        }
        return v3;
    }

    protected final int zzj() {
        return this.zzc;
    }

    public static zzef zzk(byte[] arr_b, int v, int v1) {
        zzef.zzi(v, v + v1, arr_b.length);
        byte[] arr_b1 = new byte[v1];
        System.arraycopy(arr_b, v, arr_b1, 0, v1);
        return new zzec(arr_b1);
    }

    public final String zzl(Charset charset0) {
        return this.zzd() == 0 ? "" : this.zzg(charset0);
    }
}

