package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

public final class zzao extends zzai implements zzal {
    protected final List zza;
    protected final List zzb;
    protected zzg zzc;

    private zzao(zzao zzao0) {
        super(zzao0.zzd);
        ArrayList arrayList0 = new ArrayList(zzao0.zza.size());
        this.zza = arrayList0;
        arrayList0.addAll(zzao0.zza);
        ArrayList arrayList1 = new ArrayList(zzao0.zzb.size());
        this.zzb = arrayList1;
        arrayList1.addAll(zzao0.zzb);
        this.zzc = zzao0.zzc;
    }

    public zzao(String s, List list0, List list1, zzg zzg0) {
        super(s);
        this.zza = new ArrayList();
        this.zzc = zzg0;
        if(!list0.isEmpty()) {
            for(Object object0: list0) {
                String s1 = ((zzap)object0).zzi();
                this.zza.add(s1);
            }
        }
        this.zzb = new ArrayList(list1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        zzg zzg1 = this.zzc.zza();
        for(int v = 0; v < this.zza.size(); ++v) {
            if(v < list0.size()) {
                zzg1.zze(((String)this.zza.get(v)), zzg0.zzb(((zzap)list0.get(v))));
            }
            else {
                zzg1.zze(((String)this.zza.get(v)), zzao.zzf);
            }
        }
        for(Object object0: this.zzb) {
            zzap zzap0 = (zzap)object0;
            zzap zzap1 = zzg1.zzb(zzap0);
            if(zzap1 instanceof zzaq) {
                zzap1 = zzg1.zzb(zzap0);
            }
            if(zzap1 instanceof zzag) {
                return ((zzag)zzap1).zzb();
            }
            if(false) {
                break;
            }
        }
        return zzap.zzf;
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zzd() {
        return new zzao(this);
    }
}

