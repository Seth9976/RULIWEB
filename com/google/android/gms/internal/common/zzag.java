package com.google.android.gms.internal.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import org.jspecify.nullness.NullMarked;

@NullMarked
public abstract class zzag extends zzac implements List, RandomAccess {
    private static final zzak zza;

    static {
        zzag.zza = new zzae(zzai.zza, 0);
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

    @Override
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
                if(!zzr.zza(this.get(v1), ((List)object0).get(v1))) {
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
            if(!zzr.zza(object1, object2)) {
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

    @Override  // com.google.android.gms.internal.common.zzac
    public final Iterator iterator() {
        return this.zzo(0);
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
        return this.zzo(0);
    }

    @Override
    public final ListIterator listIterator(int v) {
        return this.zzo(v);
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
        return this.zzh(v, v1);
    }

    @Override  // com.google.android.gms.internal.common.zzac
    int zza(Object[] arr_object, int v) {
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_object[v2] = this.get(v2);
        }
        return v1;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    @Deprecated
    public final zzag zzd() {
        return this;
    }

    @Override  // com.google.android.gms.internal.common.zzac
    public final zzaj zze() {
        return this.zzo(0);
    }

    public zzag zzh(int v, int v1) {
        zzs.zzc(v, v1, this.size());
        int v2 = v1 - v;
        if(v2 == this.size()) {
            return this;
        }
        return v2 == 0 ? zzai.zza : new zzaf(this, v, v2);
    }

    static zzag zzi(Object[] arr_object, int v) {
        return v == 0 ? zzai.zza : new zzai(arr_object, v);
    }

    public static zzag zzj(Iterable iterable0) {
        iterable0.getClass();
        if(iterable0 instanceof Collection) {
            return zzag.zzk(((Collection)iterable0));
        }
        Iterator iterator0 = iterable0.iterator();
        if(!iterator0.hasNext()) {
            return zzai.zza;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return zzag.zzm(object0);
        }
        zzad zzad0 = new zzad(4);
        zzad0.zzb(object0);
        zzad0.zzc(iterator0);
        zzad0.zzc = true;
        return zzag.zzi(zzad0.zza, zzad0.zzb);
    }

    public static zzag zzk(Collection collection0) {
        if(collection0 instanceof zzac) {
            zzag zzag0 = ((zzac)collection0).zzd();
            if(zzag0.zzf()) {
                Object[] arr_object = zzag0.toArray();
                return zzag.zzi(arr_object, arr_object.length);
            }
            return zzag0;
        }
        Object[] arr_object1 = collection0.toArray();
        zzah.zza(arr_object1, arr_object1.length);
        return zzag.zzi(arr_object1, arr_object1.length);
    }

    public static zzag zzl() {
        return zzai.zza;
    }

    public static zzag zzm(Object object0) {
        Object[] arr_object = {object0};
        zzah.zza(arr_object, 1);
        return zzag.zzi(arr_object, 1);
    }

    public static zzag zzn(Object object0, Object object1) {
        Object[] arr_object = {object0, object1};
        zzah.zza(arr_object, 2);
        return zzag.zzi(arr_object, 2);
    }

    public final zzak zzo(int v) {
        zzs.zzb(v, this.size(), "index");
        return this.isEmpty() ? zzag.zza : new zzae(this, v);
    }
}

