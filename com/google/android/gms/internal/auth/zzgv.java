package com.google.android.gms.internal.auth;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzgv extends AbstractMap {
    private final int zza;
    private List zzb;
    private Map zzc;
    private boolean zzd;
    private volatile zzgt zze;
    private Map zzf;

    zzgv(int v, zzgu zzgu0) {
        this.zza = v;
        this.zzb = Collections.EMPTY_LIST;
        this.zzc = Collections.EMPTY_MAP;
        this.zzf = Collections.EMPTY_MAP;
    }

    @Override
    public final void clear() {
        this.zzn();
        if(!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if(!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    @Override
    public final boolean containsKey(Object object0) {
        return this.zzk(((Comparable)object0)) >= 0 || this.zzc.containsKey(((Comparable)object0));
    }

    @Override
    public final Set entrySet() {
        if(this.zze == null) {
            this.zze = new zzgt(this, null);
        }
        return this.zze;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzgv)) {
            return super.equals(object0);
        }
        int v = this.size();
        if(v != ((zzgv)object0).size()) {
            return false;
        }
        int v1 = this.zzb();
        if(v1 == ((zzgv)object0).zzb()) {
            for(int v2 = 0; v2 < v1; ++v2) {
                if(!this.zzg(v2).equals(((zzgv)object0).zzg(v2))) {
                    return false;
                }
            }
            return v1 == v ? true : this.zzc.equals(((zzgv)object0).zzc);
        }
        return this.entrySet().equals(((zzgv)object0).entrySet());
    }

    @Override
    public final Object get(Object object0) {
        int v = this.zzk(((Comparable)object0));
        return v < 0 ? this.zzc.get(((Comparable)object0)) : ((zzgp)this.zzb.get(v)).getValue();
    }

    @Override
    public final int hashCode() {
        int v = this.zzb();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += ((zzgp)this.zzb.get(v1)).hashCode();
        }
        return this.zzc.size() <= 0 ? v2 : v2 + this.zzc.hashCode();
    }

    @Override
    public final Object put(Object object0, Object object1) {
        return this.zze(((Comparable)object0), object1);
    }

    @Override
    public final Object remove(Object object0) {
        this.zzn();
        int v = this.zzk(((Comparable)object0));
        if(v >= 0) {
            return this.zzl(v);
        }
        return this.zzc.isEmpty() ? null : this.zzc.remove(((Comparable)object0));
    }

    @Override
    public final int size() {
        return this.zzb.size() + this.zzc.size();
    }

    public void zza() {
        if(!this.zzd) {
            this.zzc = this.zzc.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.zzc);
            this.zzf = this.zzf.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.zzf);
            this.zzd = true;
        }
    }

    public final int zzb() {
        return this.zzb.size();
    }

    public final Iterable zzc() {
        return this.zzc.isEmpty() ? zzgo.zza() : this.zzc.entrySet();
    }

    public final Object zze(Comparable comparable0, Object object0) {
        this.zzn();
        int v = this.zzk(comparable0);
        if(v >= 0) {
            return ((zzgp)this.zzb.get(v)).setValue(object0);
        }
        this.zzn();
        if(this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        if(-(v + 1) >= this.zza) {
            return this.zzm().put(comparable0, object0);
        }
        int v1 = this.zza;
        if(this.zzb.size() == v1) {
            zzgp zzgp0 = (zzgp)this.zzb.remove(v1 - 1);
            this.zzm().put(zzgp0.zza(), zzgp0.getValue());
        }
        this.zzb.add(-(v + 1), new zzgp(this, comparable0, object0));
        return null;
    }

    public final Map.Entry zzg(int v) {
        return (Map.Entry)this.zzb.get(v);
    }

    public final boolean zzj() {
        return this.zzd;
    }

    private final int zzk(Comparable comparable0) {
        int v = this.zzb.size();
        int v1 = v - 1;
        int v2 = 0;
        if(v1 >= 0) {
            int v3 = comparable0.compareTo(((zzgp)this.zzb.get(v1)).zza());
            if(v3 > 0) {
                return -(v + 1);
            }
            if(v3 == 0) {
                return v1;
            }
        }
        while(v2 <= v1) {
            int v4 = (v2 + v1) / 2;
            int v5 = comparable0.compareTo(((zzgp)this.zzb.get(v4)).zza());
            if(v5 < 0) {
                v1 = v4 - 1;
                continue;
            }
            if(v5 > 0) {
                v2 = v4 + 1;
                continue;
            }
            return v4;
        }
        return -(v2 + 1);
    }

    private final Object zzl(int v) {
        this.zzn();
        Object object0 = ((zzgp)this.zzb.remove(v)).getValue();
        if(!this.zzc.isEmpty()) {
            Iterator iterator0 = this.zzm().entrySet().iterator();
            List list0 = this.zzb;
            Object object1 = iterator0.next();
            list0.add(new zzgp(this, ((Comparable)((Map.Entry)object1).getKey()), ((Map.Entry)object1).getValue()));
            iterator0.remove();
        }
        return object0;
    }

    private final SortedMap zzm() {
        this.zzn();
        if(this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap0 = new TreeMap();
            this.zzc = treeMap0;
            this.zzf = treeMap0.descendingMap();
        }
        return (SortedMap)this.zzc;
    }

    private final void zzn() {
        if(this.zzd) {
            throw new UnsupportedOperationException();
        }
    }
}

