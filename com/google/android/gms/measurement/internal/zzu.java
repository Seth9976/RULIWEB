package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfq;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgj;
import com.google.android.gms.internal.measurement.zzgk;
import com.google.android.gms.internal.measurement.zzoy;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzu {
    final zzaa zza;
    private String zzb;
    private boolean zzc;
    private zzgi zzd;
    private BitSet zze;
    private BitSet zzf;
    private Map zzg;
    private Map zzh;

    zzu(zzaa zzaa0, String s, zzgi zzgi0, BitSet bitSet0, BitSet bitSet1, Map map0, Map map1, zzt zzt0) {
        this.zza = zzaa0;
        super();
        this.zzb = s;
        this.zze = bitSet0;
        this.zzf = bitSet1;
        this.zzg = map0;
        this.zzh = new ArrayMap();
        for(Object object0: map1.keySet()) {
            ArrayList arrayList0 = new ArrayList();
            arrayList0.add(((Long)map1.get(((Integer)object0))));
            this.zzh.put(((Integer)object0), arrayList0);
        }
        this.zzc = false;
        this.zzd = zzgi0;
    }

    zzu(zzaa zzaa0, String s, zzt zzt0) {
        this.zza = zzaa0;
        super();
        this.zzb = s;
        this.zzc = true;
        this.zze = new BitSet();
        this.zzf = new BitSet();
        this.zzg = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    final zzfp zza(int v) {
        List list0;
        Iterable iterable0;
        zzfo zzfo0 = zzfp.zzb();
        zzfo0.zza(v);
        zzfo0.zzc(this.zzc);
        zzgi zzgi0 = this.zzd;
        if(zzgi0 != null) {
            zzfo0.zzd(zzgi0);
        }
        zzgh zzgh0 = zzgi.zze();
        zzgh0.zzb(zzlj.zzs(this.zze));
        zzgh0.zzd(zzlj.zzs(this.zzf));
        Map map0 = this.zzg;
        if(map0 == null) {
            iterable0 = null;
        }
        else {
            ArrayList arrayList0 = new ArrayList(map0.size());
            for(Object object0: this.zzg.keySet()) {
                int v1 = (int)(((Integer)object0));
                Long long0 = (Long)this.zzg.get(((Integer)object0));
                if(long0 != null) {
                    zzfq zzfq0 = zzfr.zzc();
                    zzfq0.zzb(v1);
                    zzfq0.zza(((long)long0));
                    arrayList0.add(((zzfr)zzfq0.zzaD()));
                }
            }
            iterable0 = arrayList0;
        }
        if(iterable0 != null) {
            zzgh0.zza(iterable0);
        }
        Map map1 = this.zzh;
        if(map1 == null) {
            list0 = Collections.EMPTY_LIST;
        }
        else {
            ArrayList arrayList1 = new ArrayList(map1.size());
            for(Object object1: this.zzh.keySet()) {
                zzgj zzgj0 = zzgk.zzd();
                zzgj0.zzb(((int)(((Integer)object1))));
                List list1 = (List)this.zzh.get(((Integer)object1));
                if(list1 != null) {
                    Collections.sort(list1);
                    zzgj0.zza(list1);
                }
                arrayList1.add(((zzgk)zzgj0.zzaD()));
            }
            list0 = arrayList1;
        }
        zzgh0.zzc(list0);
        zzfo0.zzb(zzgh0);
        return (zzfp)zzfo0.zzaD();
    }

    static BitSet zzb(zzu zzu0) {
        return zzu0.zze;
    }

    final void zzc(zzy zzy0) {
        int v = zzy0.zza();
        Boolean boolean0 = zzy0.zzd;
        if(boolean0 != null) {
            BitSet bitSet0 = this.zzf;
            boolean0.booleanValue();
            bitSet0.set(v, true);
        }
        Boolean boolean1 = zzy0.zze;
        if(boolean1 != null) {
            this.zze.set(v, boolean1.booleanValue());
        }
        if(zzy0.zzf != null) {
            Integer integer0 = v;
            Long long0 = (Long)this.zzg.get(integer0);
            long v1 = (long)zzy0.zzf;
            if(long0 == null || v1 / 1000L > ((long)long0)) {
                this.zzg.put(integer0, ((long)(v1 / 1000L)));
            }
        }
        if(zzy0.zzg != null) {
            Integer integer1 = v;
            List list0 = (List)this.zzh.get(integer1);
            if(list0 == null) {
                list0 = new ArrayList();
                this.zzh.put(integer1, list0);
            }
            if(zzy0.zzc()) {
                list0.clear();
            }
            zzoy.zzc();
            if(this.zza.zzt.zzf().zzs(this.zzb, zzeg.zzY) && zzy0.zzb()) {
                list0.clear();
            }
            zzoy.zzc();
            if(this.zza.zzt.zzf().zzs(this.zzb, zzeg.zzY)) {
                Long long1 = (long)(((long)zzy0.zzg) / 1000L);
                if(!list0.contains(long1)) {
                    list0.add(long1);
                }
            }
            else {
                list0.add(((long)(((long)zzy0.zzg) / 1000L)));
            }
        }
    }
}

