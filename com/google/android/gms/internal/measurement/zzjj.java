package com.google.android.gms.internal.measurement;

public abstract class zzjj implements zzmh {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.zzav();
    }

    public abstract zzjj zzav();

    public zzjj zzaw(byte[] arr_b, int v, int v1) throws zzll {
        throw null;
    }

    public zzjj zzax(byte[] arr_b, int v, int v1, zzkn zzkn0) throws zzll {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmh
    public final zzmh zzay(byte[] arr_b) throws zzll {
        return this.zzaw(arr_b, 0, arr_b.length);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmh
    public final zzmh zzaz(byte[] arr_b, zzkn zzkn0) throws zzll {
        return this.zzax(arr_b, 0, arr_b.length, zzkn0);
    }
}

