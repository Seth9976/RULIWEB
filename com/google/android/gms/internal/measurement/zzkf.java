package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzkf extends zzki {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    zzkf(byte[] arr_b, int v, int v1) {
        super(null);
        if(arr_b == null) {
            throw new NullPointerException("buffer");
        }
        if((arr_b.length - v1 | v1) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", ((int)arr_b.length), 0, v1));
        }
        this.zzc = arr_b;
        this.zze = 0;
        this.zzd = v1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final int zza() {
        return this.zzd - this.zze;
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzb(byte b) throws IOException {
        try {
            int v = this.zze;
            this.zze = v + 1;
            this.zzc[v] = b;
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
        }
    }

    public final void zzc(byte[] arr_b, int v, int v1) throws IOException {
        try {
            System.arraycopy(arr_b, 0, this.zzc, this.zze, v1);
            this.zze += v1;
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, v1), indexOutOfBoundsException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzd(int v, boolean z) throws IOException {
        this.zzq(v << 3);
        this.zzb(((byte)z));
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zze(int v, zzka zzka0) throws IOException {
        this.zzq(v << 3 | 2);
        this.zzq(zzka0.zzd());
        zzka0.zzh(this);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzf(int v, int v1) throws IOException {
        this.zzq(v << 3 | 5);
        this.zzg(v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzg(int v) throws IOException {
        try {
            int v1 = this.zze;
            this.zze = v1 + 1;
            this.zzc[v1] = (byte)(v & 0xFF);
            this.zze = v1 + 2;
            this.zzc[v1 + 1] = (byte)(v >> 8 & 0xFF);
            this.zze = v1 + 3;
            this.zzc[v1 + 2] = (byte)(v >> 16 & 0xFF);
            this.zze = v1 + 4;
            this.zzc[v1 + 3] = (byte)(v >> 24 & 0xFF);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzh(int v, long v1) throws IOException {
        this.zzq(v << 3 | 1);
        this.zzi(v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzi(long v) throws IOException {
        try {
            int v1 = this.zze;
            this.zze = v1 + 1;
            this.zzc[v1] = (byte)(((int)v) & 0xFF);
            this.zze = v1 + 2;
            this.zzc[v1 + 1] = (byte)(((int)(v >> 8)) & 0xFF);
            this.zze = v1 + 3;
            this.zzc[v1 + 2] = (byte)(((int)(v >> 16)) & 0xFF);
            this.zze = v1 + 4;
            this.zzc[v1 + 3] = (byte)(((int)(v >> 24)) & 0xFF);
            this.zze = v1 + 5;
            this.zzc[v1 + 4] = (byte)(((int)(v >> 0x20)) & 0xFF);
            this.zze = v1 + 6;
            this.zzc[v1 + 5] = (byte)(((int)(v >> 40)) & 0xFF);
            this.zze = v1 + 7;
            this.zzc[v1 + 6] = (byte)(((int)(v >> 0x30)) & 0xFF);
            this.zze = v1 + 8;
            this.zzc[v1 + 7] = (byte)(((int)(v >> 56)) & 0xFF);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzj(int v, int v1) throws IOException {
        this.zzq(v << 3);
        this.zzk(v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzk(int v) throws IOException {
        if(v >= 0) {
            this.zzq(v);
            return;
        }
        this.zzs(((long)v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzl(byte[] arr_b, int v, int v1) throws IOException {
        this.zzc(arr_b, 0, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzm(int v, String s) throws IOException {
        this.zzq(v << 3 | 2);
        this.zzn(s);
    }

    public final void zzn(String s) throws IOException {
        int v;
        try {
            v = this.zze;
            int v1 = zzkf.zzx(s.length() * 3);
            int v2 = zzkf.zzx(s.length());
            if(v2 == v1) {
                int v3 = v + v2;
                this.zze = v3;
                int v4 = zznz.zzb(s, this.zzc, v3, this.zzd - v3);
                this.zze = v;
                this.zzq(v4 - v - v2);
                this.zze = v4;
                return;
            }
            this.zzq(zznz.zzc(s));
            this.zze = zznz.zzb(s, this.zzc, this.zze, this.zzd - this.zze);
        }
        catch(zzny zzny0) {
            this.zze = v;
            this.zzB(s, zzny0);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(indexOutOfBoundsException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzo(int v, int v1) throws IOException {
        this.zzq(v << 3 | v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzp(int v, int v1) throws IOException {
        this.zzq(v << 3);
        this.zzq(v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzq(int v) throws IOException {
        try {
            while(true) {
                if((v & 0xFFFFFF80) == 0) {
                    int v1 = this.zze;
                    this.zze = v1 + 1;
                    this.zzc[v1] = (byte)v;
                    return;
                }
                int v2 = this.zze;
                this.zze = v2 + 1;
                this.zzc[v2] = (byte)(v & 0x7F | 0x80);
                v >>>= 7;
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzr(int v, long v1) throws IOException {
        this.zzq(v << 3);
        this.zzs(v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzki
    public final void zzs(long v) throws IOException {
        try {
            while(true) {
                if((v & 0xFFFFFFFFFFFFFF80L) == 0L) {
                    int v1 = this.zze;
                    this.zze = v1 + 1;
                    this.zzc[v1] = (byte)(((int)v));
                    return;
                }
                int v2 = this.zze;
                this.zze = v2 + 1;
                this.zzc[v2] = (byte)(((int)v) & 0x7F | 0x80);
                v >>>= 7;
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzkg(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
        }
    }
}

