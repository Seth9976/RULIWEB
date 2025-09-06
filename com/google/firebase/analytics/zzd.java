package com.google.firebase.analytics;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzef;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import com.google.android.gms.measurement.internal.zzil;
import java.util.List;
import java.util.Map;

final class zzd implements zzil {
    final zzef zza;

    zzd(zzef zzef0) {
        this.zza = zzef0;
        super();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final int zza(String s) {
        return this.zza.zza(s);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final long zzb() {
        return this.zza.zzb();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final Object zzg(int v) {
        return this.zza.zzi(v);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzh() {
        return this.zza.zzm();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzi() {
        return this.zza.zzn();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzj() {
        return this.zza.zzo();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzk() {
        return this.zza.zzp();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final List zzm(String s, String s1) {
        return this.zza.zzq(s, s1);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final Map zzo(String s, String s1, boolean z) {
        return this.zza.zzr(s, s1, z);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzp(String s) {
        this.zza.zzv(s);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzq(String s, String s1, Bundle bundle0) {
        this.zza.zzw(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzr(String s) {
        this.zza.zzx(s);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzs(String s, String s1, Bundle bundle0) {
        this.zza.zzz(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzt(String s, String s1, Bundle bundle0, long v) {
        this.zza.zzA(s, s1, bundle0, v);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzu(zzhg zzhg0) {
        this.zza.zzC(zzhg0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzv(Bundle bundle0) {
        this.zza.zzE(bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzw(zzhf zzhf0) {
        this.zza.zzK(zzhf0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzx(zzhg zzhg0) {
        this.zza.zzP(zzhg0);
    }
}

