package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

public abstract class zzjb extends zziw implements Set {
    @CheckForNull
    private transient zzja zza;

    @Override
    public final boolean equals(@CheckForNull Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof zzjb && this.zzj() && ((zzjb)object0).zzj() && this.hashCode() != object0.hashCode()) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof Set) {
            Set set0 = (Set)object0;
            try {
                return this.size() != set0.size() || !this.containsAll(set0) ? false : true;
            }
            catch(NullPointerException | ClassCastException unused_ex) {
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int v = 0;
        for(Object object0: this) {
            v += (object0 == null ? 0 : object0.hashCode());
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public Iterator iterator() {
        return this.zzd();
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public abstract zzjh zzd();

    static int zzf(int v) {
        int v1 = Math.max(v, 2);
        if(v1 < 0x2CCCCCCC) {
            int v2 = Integer.highestOneBit(v1 - 1);
            do {
                v2 += v2;
            }
            while(((double)v2) * 0.7 < ((double)v1));
            return v2;
        }
        if(v1 >= 0x40000000) {
            throw new IllegalArgumentException("collection too large");
        }
        return 0x40000000;
    }

    public final zzja zzg() {
        zzja zzja0 = this.zza;
        if(zzja0 == null) {
            zzja0 = this.zzh();
            this.zza = zzja0;
        }
        return zzja0;
    }

    zzja zzh() {
        Object[] arr_object = this.toArray();
        return zzja.zzg(arr_object, arr_object.length);
    }

    @SafeVarargs
    public static zzjb zzi(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object[] arr_object) {
        Object[] arr_object1 = new Object[15];
        arr_object1[0] = "_in";
        arr_object1[1] = "_xa";
        arr_object1[2] = "_xu";
        arr_object1[3] = "_aq";
        arr_object1[4] = "_aa";
        arr_object1[5] = "_ai";
        System.arraycopy(arr_object, 0, arr_object1, 6, 9);
        return zzjb.zzk(15, arr_object1);
    }

    boolean zzj() {
        return false;
    }

    private static zzjb zzk(int v, Object[] arr_object) {
        switch(v) {
            case 0: {
                return zzjf.zza;
            }
            case 1: {
                Object object3 = arr_object[0];
                object3.getClass();
                return new zzjg(object3);
            }
            default: {
                int v1 = zzjb.zzf(v);
                Object[] arr_object1 = new Object[v1];
                int v3 = 0;
                int v4 = 0;
                for(int v2 = 0; v2 < v; ++v2) {
                    Object object0 = arr_object[v2];
                    zzjd.zza(object0, v2);
                    int v5 = object0.hashCode();
                    for(int v6 = zzit.zza(v5); true; ++v6) {
                        int v7 = v6 & v1 - 1;
                        Object object1 = arr_object1[v7];
                        if(object1 == null) {
                            arr_object[v4] = object0;
                            arr_object1[v7] = object0;
                            v3 += v5;
                            ++v4;
                            break;
                        }
                        if(object1.equals(object0)) {
                            break;
                        }
                    }
                }
                Arrays.fill(arr_object, v4, v, null);
                if(v4 == 1) {
                    Object object2 = arr_object[0];
                    object2.getClass();
                    return new zzjg(object2);
                }
                if(zzjb.zzf(v4) < v1 / 2) {
                    return zzjb.zzk(v4, arr_object);
                }
                if(v4 < 10) {
                    arr_object = Arrays.copyOf(arr_object, v4);
                }
                return new zzjf(arr_object, v3, arr_object1, v1 - 1, v4);
            }
        }
    }
}

