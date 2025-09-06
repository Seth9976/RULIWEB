package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class zzae implements zzal, zzap, Iterable {
    final SortedMap zza;
    final Map zzb;

    public zzae() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    public zzae(List list0) {
        if(list0 != null) {
            for(int v = 0; v < list0.size(); ++v) {
                this.zzq(v, ((zzap)list0.get(v)));
            }
        }
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzae)) {
            return false;
        }
        if(this.zzc() != ((zzae)object0).zzc()) {
            return false;
        }
        if(this.zza.isEmpty()) {
            return ((zzae)object0).zza.isEmpty();
        }
        for(int v = (int)(((Integer)this.zza.firstKey())); v <= ((int)(((Integer)this.zza.lastKey()))); ++v) {
            if(!this.zze(v).equals(((zzae)object0).zze(v))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode() * 0x1F;
    }

    @Override
    public final Iterator iterator() {
        return new zzad(this);
    }

    @Override
    public final String toString() {
        return this.zzj(",");
    }

    public final int zzb() {
        return this.zza.size();
    }

    // 去混淆评级： 高(200)
    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        return "concat".equals(s) || "every".equals(s) || "filter".equals(s) || "forEach".equals(s) || "indexOf".equals(s) || "join".equals(s) || "lastIndexOf".equals(s) || "map".equals(s) || "pop".equals(s) || "push".equals(s) || "reduce".equals(s) || "reduceRight".equals(s) || "reverse".equals(s) || "shift".equals(s) || "slice".equals(s) || "some".equals(s) || "sort".equals(s) || "splice".equals(s) || "toString".equals(s) || "unshift".equals(s) ? zzbb.zza(s, this, zzg0, list0) : zzaj.zza(this, new zzat(s), zzg0, list0);
    }

    // 去混淆评级： 低(20)
    public final int zzc() {
        return this.zza.isEmpty() ? 0 : ((int)(((Integer)this.zza.lastKey()))) + 1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        zzap zzap0 = new zzae();
        for(Object object0: this.zza.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(map$Entry0.getValue() instanceof zzal) {
                Integer integer0 = (Integer)map$Entry0.getKey();
                zzap zzap1 = (zzap)map$Entry0.getValue();
                zzap0.zza.put(integer0, zzap1);
            }
            else {
                Integer integer1 = (Integer)map$Entry0.getKey();
                zzap zzap2 = ((zzap)map$Entry0.getValue()).zzd();
                zzap0.zza.put(integer1, zzap2);
            }
        }
        return zzap0;
    }

    public final zzap zze(int v) {
        if(v >= this.zzc()) {
            throw new IndexOutOfBoundsException("Attempting to get element outside of current array");
        }
        if(this.zzs(v)) {
            zzap zzap0 = (zzap)this.zza.get(v);
            return zzap0 == null ? zzae.zzf : zzap0;
        }
        return zzae.zzf;
    }

    @Override  // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String s) {
        if("length".equals(s)) {
            return new zzah(((double)this.zzc()));
        }
        if(this.zzt(s)) {
            zzap zzap0 = (zzap)this.zzb.get(s);
            return zzap0 == null ? zzae.zzf : zzap0;
        }
        return zzae.zzf;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if(this.zza.size() == 1) {
            return this.zze(0).zzh();
        }
        return this.zza.size() > 0 ? NaN : 0.0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zzj(",");
    }

    public final String zzj(String s) [...] // 潜在的解密器

    public final Iterator zzk() {
        return this.zza.keySet().iterator();
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzac(this, this.zza.keySet().iterator(), this.zzb.keySet().iterator());
    }

    public final List zzm() {
        List list0 = new ArrayList(this.zzc());
        for(int v = 0; v < this.zzc(); ++v) {
            list0.add(this.zze(v));
        }
        return list0;
    }

    public final void zzn() {
        this.zza.clear();
    }

    public final void zzo(int v, zzap zzap0) {
        if(v < 0) {
            throw new IllegalArgumentException("Invalid value index: " + v);
        }
        if(v >= this.zzc()) {
            this.zzq(v, zzap0);
            return;
        }
        for(int v1 = (int)(((Integer)this.zza.lastKey())); v1 >= v; --v1) {
            Integer integer0 = v1;
            zzap zzap1 = (zzap)this.zza.get(integer0);
            if(zzap1 != null) {
                this.zzq(v1 + 1, zzap1);
                this.zza.remove(integer0);
            }
        }
        this.zzq(v, zzap0);
    }

    public final void zzp(int v) {
        int v1 = (int)(((Integer)this.zza.lastKey()));
        if(v <= v1 && v >= 0) {
            this.zza.remove(v);
            if(v == v1) {
                Integer integer0 = (int)(v - 1);
                if(!this.zza.containsKey(integer0) && v - 1 >= 0) {
                    this.zza.put(integer0, zzap.zzf);
                }
            }
            else {
                while(true) {
                    ++v;
                    if(v > ((int)(((Integer)this.zza.lastKey())))) {
                        break;
                    }
                    Integer integer1 = v;
                    zzap zzap0 = (zzap)this.zza.get(integer1);
                    if(zzap0 != null) {
                        this.zza.put(((int)(v - 1)), zzap0);
                        this.zza.remove(integer1);
                    }
                }
            }
        }
    }

    @RequiresNonNull({"elements"})
    public final void zzq(int v, zzap zzap0) {
        if(v > 0x7ED4) {
            throw new IllegalStateException("Array too large");
        }
        if(v < 0) {
            throw new IndexOutOfBoundsException("Out of bounds index: " + v);
        }
        if(zzap0 == null) {
            this.zza.remove(v);
            return;
        }
        this.zza.put(v, zzap0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String s, zzap zzap0) {
        if(zzap0 == null) {
            this.zzb.remove(s);
            return;
        }
        this.zzb.put(s, zzap0);
    }

    public final boolean zzs(int v) {
        if(v < 0 || v > ((int)(((Integer)this.zza.lastKey())))) {
            throw new IndexOutOfBoundsException("Out of bounds index: " + v);
        }
        return this.zza.containsKey(v);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String s) {
        return "length".equals(s) || this.zzb.containsKey(s);
    }
}

