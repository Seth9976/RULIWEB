package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzgd;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import com.google.android.gms.measurement.internal.zzik;
import com.google.android.gms.measurement.internal.zzlk;
import java.util.List;
import java.util.Map;

final class zza extends zzd {
    private final zzgd zza;
    private final zzik zzb;

    public zza(zzgd zzgd0) {
        super(null);
        Preconditions.checkNotNull(zzgd0);
        this.zza = zzgd0;
        this.zzb = zzgd0.zzq();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final int zza(String s) {
        this.zzb.zzh(s);
        return 25;
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final long zzb() {
        return this.zza.zzv().zzq();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Boolean zzc() {
        return this.zzb.zzi();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Double zzd() {
        return this.zzb.zzj();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Integer zze() {
        return this.zzb.zzl();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Long zzf() {
        return this.zzb.zzm();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final Object zzg(int v) {
        switch(v) {
            case 0: {
                return this.zzb.zzr();
            }
            case 1: {
                return this.zzb.zzm();
            }
            case 2: {
                return this.zzb.zzj();
            }
            case 3: {
                return this.zzb.zzl();
            }
            default: {
                return this.zzb.zzi();
            }
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzh() {
        return this.zzb.zzo();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzi() {
        return this.zzb.zzp();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzj() {
        return this.zzb.zzq();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzk() {
        return this.zzb.zzo();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final String zzl() {
        return this.zzb.zzr();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final List zzm(String s, String s1) {
        return this.zzb.zzs(s, s1);
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Map zzn(boolean z) {
        List list0 = this.zzb.zzt(z);
        Map map0 = new ArrayMap(list0.size());
        for(Object object0: list0) {
            zzlk zzlk0 = (zzlk)object0;
            Object object1 = zzlk0.zza();
            if(object1 != null) {
                map0.put(zzlk0.zzb, object1);
            }
        }
        return map0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final Map zzo(String s, String s1, boolean z) {
        return this.zzb.zzu(s, s1, z);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzp(String s) {
        this.zza.zzd().zzd(s, this.zza.zzax().elapsedRealtime());
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzq(String s, String s1, Bundle bundle0) {
        this.zza.zzq().zzA(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzr(String s) {
        this.zza.zzd().zze(s, this.zza.zzax().elapsedRealtime());
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzs(String s, String s1, Bundle bundle0) {
        this.zzb.zzD(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzt(String s, String s1, Bundle bundle0, long v) {
        this.zzb.zzE(s, s1, bundle0, true, false, v);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzu(zzhg zzhg0) {
        this.zzb.zzJ(zzhg0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzv(Bundle bundle0) {
        this.zzb.zzP(bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzw(zzhf zzhf0) {
        this.zzb.zzT(zzhf0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzx(zzhg zzhg0) {
        this.zzb.zzZ(zzhg0);
    }
}

