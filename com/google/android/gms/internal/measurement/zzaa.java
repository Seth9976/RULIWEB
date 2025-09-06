package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;

public final class zzaa {
    private String zza;
    private final long zzb;
    private final Map zzc;

    public zzaa(String s, long v, Map map0) {
        this.zza = s;
        this.zzb = v;
        HashMap hashMap0 = new HashMap();
        this.zzc = hashMap0;
        if(map0 != null) {
            hashMap0.putAll(map0);
        }
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        return this.zzb();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzaa)) {
            return false;
        }
        if(this.zzb != ((zzaa)object0).zzb) {
            return false;
        }
        return this.zza.equals(((zzaa)object0).zza) ? this.zzc.equals(((zzaa)object0).zzc) : false;
    }

    @Override
    public final int hashCode() {
        return (this.zza.hashCode() * 0x1F + ((int)(this.zzb ^ this.zzb >>> 0x20))) * 0x1F + this.zzc.hashCode();
    }

    @Override
    public final String toString() {
        return "Event{name=\'" + this.zza + "\', timestamp=" + this.zzb + ", params=" + this.zzc.toString() + "}";
    }

    public final long zza() {
        return this.zzb;
    }

    public final zzaa zzb() {
        String s = this.zza;
        HashMap hashMap0 = new HashMap(this.zzc);
        return new zzaa(s, this.zzb, hashMap0);
    }

    // 去混淆评级： 低(20)
    public final Object zzc(String s) {
        return this.zzc.containsKey(s) ? this.zzc.get(s) : null;
    }

    public final String zzd() {
        return this.zza;
    }

    public final Map zze() {
        return this.zzc;
    }

    public final void zzf(String s) {
        this.zza = s;
    }

    public final void zzg(String s, Object object0) {
        if(object0 == null) {
            this.zzc.remove(s);
            return;
        }
        this.zzc.put(s, object0);
    }
}

