package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzap {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final zzas zzf;

    zzap(zzgd zzgd0, String s, String s1, String s2, long v, long v1, Bundle bundle0) {
        Preconditions.checkNotEmpty(s1);
        zzas zzas0;
        Preconditions.checkNotEmpty(s2);
        this.zza = s1;
        this.zzb = s2;
        if(TextUtils.isEmpty(s)) {
            s = null;
        }
        this.zzc = s;
        this.zzd = v;
        this.zze = v1;
        if(v1 != 0L && v1 > v) {
            zzgd0.zzaA().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzet.zzn(s1));
        }
        if(bundle0 == null || bundle0.isEmpty()) {
            zzas0 = new zzas(new Bundle());
        }
        else {
            Bundle bundle1 = new Bundle(bundle0);
            Iterator iterator0 = bundle1.keySet().iterator();
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                String s3 = (String)object0;
                if(s3 == null) {
                    zzgd0.zzaA().zzd().zza("Param name can\'t be null");
                    iterator0.remove();
                }
                else {
                    Object object1 = zzgd0.zzv().zzA(s3, bundle1.get(s3));
                    if(object1 == null) {
                        zzgd0.zzaA().zzk().zzb("Param value can\'t be null", zzgd0.zzj().zze(s3));
                        iterator0.remove();
                    }
                    else {
                        zzgd0.zzv().zzP(bundle1, s3, object1);
                    }
                }
            }
            zzas0 = new zzas(bundle1);
        }
        this.zzf = zzas0;
    }

    private zzap(zzgd zzgd0, String s, String s1, String s2, long v, long v1, zzas zzas0) {
        Preconditions.checkNotEmpty(s1);
        Preconditions.checkNotEmpty(s2);
        Preconditions.checkNotNull(zzas0);
        this.zza = s1;
        this.zzb = s2;
        if(TextUtils.isEmpty(s)) {
            s = null;
        }
        this.zzc = s;
        this.zzd = v;
        this.zze = v1;
        if(v1 != 0L && v1 > v) {
            zzgd0.zzaA().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzet.zzn(s1), zzet.zzn(s2));
        }
        this.zzf = zzas0;
    }

    @Override
    public final String toString() {
        return "Event{appId=\'" + this.zza + "\', name=\'" + this.zzb + "\', params=" + this.zzf.toString() + "}";
    }

    final zzap zza(zzgd zzgd0, long v) {
        return new zzap(zzgd0, this.zzc, this.zza, this.zzb, this.zzd, v, this.zzf);
    }
}

