package com.google.android.gms.internal.measurement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

public abstract class zzja extends zziw implements List, RandomAccess {
    private static final zzji zza;
    public static final int zzd;

    static {
        zzja.zza = new zziy(zzje.zza, 0);
    }

    @Override
    @Deprecated
    public final void add(int v, Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean addAll(int v, Collection collection0) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public final boolean contains(@CheckForNull Object object0) {
        return this.indexOf(object0) >= 0;
    }

    @Override
    public final boolean equals(@CheckForNull Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof List)) {
            return false;
        }
        int v = this.size();
        if(v != ((List)object0).size()) {
            return false;
        }
        if(((List)object0) instanceof RandomAccess) {
            for(int v1 = 0; v1 < v; ++v1) {
                if(!zzih.zza(this.get(v1), ((List)object0).get(v1))) {
                    return false;
                }
            }
            return true;
        }
        Iterator iterator0 = this.iterator();
        Iterator iterator1 = ((List)object0).iterator();
        while(iterator0.hasNext()) {
            if(!iterator1.hasNext()) {
                return false;
            }
            Object object1 = iterator0.next();
            Object object2 = iterator1.next();
            if(!zzih.zza(object1, object2)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return !iterator1.hasNext();
    }

    @Override
    public final int hashCode() {
        int v = this.size();
        int v2 = 1;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 = v2 * 0x1F + this.get(v1).hashCode();
        }
        return v2;
    }

    @Override
    public final int indexOf(@CheckForNull Object object0) {
        if(object0 == null) {
            return -1;
        }
        int v = this.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(object0.equals(this.get(v1))) {
                return v1;
            }
        }
        return -1;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public final Iterator iterator() {
        return this.zzk(0);
    }

    @Override
    public final int lastIndexOf(@CheckForNull Object object0) {
        if(object0 == null) {
            return -1;
        }
        for(int v = this.size() - 1; v >= 0; --v) {
            if(object0.equals(this.get(v))) {
                return v;
            }
        }
        return -1;
    }

    @Override
    public final ListIterator listIterator() {
        return this.zzk(0);
    }

    @Override
    public final ListIterator listIterator(int v) {
        return this.zzk(v);
    }

    @Override
    @Deprecated
    public final Object remove(int v) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final Object set(int v, Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int v, int v1) {
        return this.zzf(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    int zza(Object[] arr_object, int v) {
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_object[v2] = this.get(v2);
        }
        return v1;
    }

    @Override  // com.google.android.gms.internal.measurement.zziw
    public final zzjh zzd() {
        return this.zzk(0);
    }

    public zzja zzf(int v, int v1) {
        zzij.zzc(v, v1, this.size());
        int v2 = v1 - v;
        if(v2 == this.size()) {
            return this;
        }
        return v2 == 0 ? zzje.zza : new zziz(this, v, v2);
    }

    static zzja zzg(Object[] arr_object, int v) {
        return v == 0 ? zzje.zza : new zzje(arr_object, v);
    }

    public static zzja zzh(Object object0, Object object1) {
        Object[] arr_object = {object0, object1};
        zzjd.zzb(arr_object, 2);
        return zzja.zzg(arr_object, 2);
    }

    public static zzja zzi(Object object0, Object object1, Object object2) {
        Object[] arr_object = {"auto", "app", "am"};
        zzjd.zzb(arr_object, 3);
        return zzja.zzg(arr_object, 3);
    }

    public static zzja zzj(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6) {
        Object[] arr_object = {"_e", "_f", "_iap", "_s", "_au", "_ui", "_cd"};
        zzjd.zzb(arr_object, 7);
        return zzja.zzg(arr_object, 7);
    }

    public final zzji zzk(int v) {
        zzij.zzb(v, this.size(), "index");
        return this.isEmpty() ? zzja.zza : new zziy(this, v);
    }
}

