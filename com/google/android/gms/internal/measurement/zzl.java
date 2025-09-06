package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

public final class zzl extends zzam {
    private final zzab zzb;

    public zzl(zzab zzab0) {
        this.zzb = zzab0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzam
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        int v;
        switch(s) {
            case "getEventName": {
                zzh.zzh("getEventName", 0, list0);
                return new zzat(this.zzb.zzb().zzd());
            }
            case "getParamValue": {
                zzh.zzh("getParamValue", 1, list0);
                String s1 = zzg0.zzb(((zzap)list0.get(0))).zzi();
                return zzi.zzb(this.zzb.zzb().zzc(s1));
            }
            case "getParams": {
                zzh.zzh("getParams", 0, list0);
                Map map0 = this.zzb.zzb().zze();
                zzap zzap0 = new zzam();
                for(Object object0: map0.keySet()) {
                    ((zzam)zzap0).zzr(((String)object0), zzi.zzb(map0.get(((String)object0))));
                }
                return zzap0;
            }
            case "getTimestamp": {
                zzh.zzh("getTimestamp", 0, list0);
                return new zzah(((double)this.zzb.zzb().zza()));
            }
            case "setEventName": {
                zzh.zzh("setEventName", 1, list0);
                zzap zzap1 = zzg0.zzb(((zzap)list0.get(0)));
                if(zzl.zzf.equals(zzap1) || zzl.zzg.equals(zzap1)) {
                    throw new IllegalArgumentException("Illegal event name");
                }
                String s2 = zzap1.zzi();
                this.zzb.zzb().zzf(s2);
                return new zzat(zzap1.zzi());
            }
            case "setParamValue": {
                v = 5;
                break;
            }
            default: {
                v = -1;
                break;
            }
        }
        if(v != 5) {
            return super.zzbU(s, zzg0, list0);
        }
        zzh.zzh("setParamValue", 2, list0);
        String s3 = zzg0.zzb(((zzap)list0.get(0))).zzi();
        zzap zzap2 = zzg0.zzb(((zzap)list0.get(1)));
        Object object1 = zzh.zzf(zzap2);
        this.zzb.zzb().zzg(s3, object1);
        return zzap2;
    }
}

