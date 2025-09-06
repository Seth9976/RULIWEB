package com.google.android.gms.internal.fido;

import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzax extends zzau {
    static final zzax zza;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private static final Object[] zzd;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] arr_object = new Object[0];
        zzax.zzd = arr_object;
        zzax.zza = new zzax(arr_object, 0, arr_object, 0, 0);
    }

    zzax(Object[] arr_object, int v, Object[] arr_object1, int v1, int v2) {
        this.zzb = arr_object;
        this.zze = v;
        this.zzc = arr_object1;
        this.zzf = v1;
        this.zzg = v2;
    }

    @Override
    public final boolean contains(@CheckForNull Object object0) {
        Object[] arr_object = this.zzc;
        if(object0 != null && arr_object.length != 0) {
            for(int v = zzap.zza(object0.hashCode()); true; v = v1 + 1) {
                int v1 = v & this.zzf;
                Object object1 = arr_object[v1];
                if(object1 == null) {
                    return false;
                }
                if(object1.equals(object0)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    public final int hashCode() {
        return this.zze;
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    public final Iterator iterator() {
        return this.zzg().zzh(0);
    }

    @Override
    public final int size() {
        return this.zzg;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzb, 0, arr_object, 0, this.zzg);
        return this.zzg;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zzb() {
        return this.zzg;
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    public final zzaz zzd() {
        return this.zzg().zzh(0);
    }

    @Override  // com.google.android.gms.internal.fido.zzaq
    final Object[] zze() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    final zzat zzh() {
        return zzat.zzg(this.zzb, this.zzg);
    }

    @Override  // com.google.android.gms.internal.fido.zzau
    final boolean zzj() {
        return true;
    }
}

