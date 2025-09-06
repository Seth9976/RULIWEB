package com.google.android.gms.internal.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzer extends zzdr implements zzez, zzge, RandomAccess {
    private static final zzer zza;
    private float[] zzb;
    private int zzc;

    static {
        zzer.zza = new zzer(new float[0], 0, false);
    }

    zzer() {
        this(new float[10], 0, true);
    }

    private zzer(float[] arr_f, int v, boolean z) {
        super(z);
        this.zzb = arr_f;
        this.zzc = v;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final void add(int v, Object object0) {
        float f = (float)(((Float)object0));
        this.zza();
        if(v >= 0) {
            int v1 = this.zzc;
            if(v <= v1) {
                float[] arr_f = this.zzb;
                if(v1 < arr_f.length) {
                    System.arraycopy(arr_f, v, arr_f, v + 1, v1 - v);
                }
                else {
                    float[] arr_f1 = new float[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_f, 0, arr_f1, 0, v);
                    System.arraycopy(this.zzb, v, arr_f1, v + 1, this.zzc - v);
                    this.zzb = arr_f1;
                }
                this.zzb[v] = f;
                ++this.zzc;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.zzf(v));
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final boolean add(Object object0) {
        this.zze(((float)(((Float)object0))));
        return true;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final boolean addAll(Collection collection0) {
        this.zza();
        collection0.getClass();
        if(!(collection0 instanceof zzer)) {
            return super.addAll(collection0);
        }
        int v = ((zzer)collection0).zzc;
        if(v == 0) {
            return false;
        }
        int v1 = this.zzc;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        float[] arr_f = this.zzb;
        if(v2 > arr_f.length) {
            this.zzb = Arrays.copyOf(arr_f, v2);
        }
        System.arraycopy(((zzer)collection0).zzb, 0, this.zzb, this.zzc, ((zzer)collection0).zzc);
        this.zzc = v2;
        ++this.modCount;
        return true;
    }

    @Override
    public final boolean contains(Object object0) {
        return this.indexOf(object0) != -1;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzer)) {
            return super.equals(object0);
        }
        if(this.zzc != ((zzer)object0).zzc) {
            return false;
        }
        float[] arr_f = ((zzer)object0).zzb;
        for(int v = 0; v < this.zzc; ++v) {
            if(Float.floatToIntBits(this.zzb[v]) != Float.floatToIntBits(arr_f[v])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final Object get(int v) {
        this.zzg(v);
        return (float)this.zzb[v];
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final int hashCode() {
        int v1 = 1;
        for(int v = 0; v < this.zzc; ++v) {
            v1 = v1 * 0x1F + Float.floatToIntBits(this.zzb[v]);
        }
        return v1;
    }

    @Override
    public final int indexOf(Object object0) {
        if(!(object0 instanceof Float)) {
            return -1;
        }
        float f = (float)(((Float)object0));
        int v = this.zzc;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.zzb[v1] == f) {
                return v1;
            }
        }
        return -1;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final Object remove(int v) {
        this.zza();
        this.zzg(v);
        float[] arr_f = this.zzb;
        float f = arr_f[v];
        int v1 = this.zzc;
        if(v < v1 - 1) {
            System.arraycopy(arr_f, v + 1, arr_f, v, v1 - v - 1);
        }
        --this.zzc;
        ++this.modCount;
        return f;
    }

    @Override
    protected final void removeRange(int v, int v1) {
        this.zza();
        if(v1 < v) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzb, v1, this.zzb, v, this.zzc - v1);
        this.zzc -= v1 - v;
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final Object set(int v, Object object0) {
        this.zza();
        this.zzg(v);
        float[] arr_f = this.zzb;
        float f = arr_f[v];
        arr_f[v] = (float)(((Float)object0));
        return f;
    }

    @Override
    public final int size() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.internal.auth.zzez
    public final zzez zzd(int v) {
        if(v < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzer(Arrays.copyOf(this.zzb, v), this.zzc, true);
    }

    public final void zze(float f) {
        this.zza();
        int v = this.zzc;
        float[] arr_f = this.zzb;
        if(v == arr_f.length) {
            float[] arr_f1 = new float[v * 3 / 2 + 1];
            System.arraycopy(arr_f, 0, arr_f1, 0, v);
            this.zzb = arr_f1;
        }
        int v1 = this.zzc;
        this.zzc = v1 + 1;
        this.zzb[v1] = f;
    }

    private final String zzf(int v) {
        return "Index:" + v + ", Size:" + this.zzc;
    }

    private final void zzg(int v) {
        if(v < 0 || v >= this.zzc) {
            throw new IndexOutOfBoundsException(this.zzf(v));
        }
    }
}

