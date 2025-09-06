package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import com.google.android.gms.measurement.internal.zzil;
import java.util.List;
import java.util.Map;

final class zzb extends zzd {
    private final zzil zza;

    public zzb(zzil zzil0) {
        super(null);
        Preconditions.checkNotNull(zzil0);
        this.zza = zzil0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final int zza(String s) {
        return this.zza.zza(s);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final long zzb() {
        return this.zza.zzb();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Boolean zzc() {
        return (Boolean)this.zza.zzg(4);
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Double zzd() {
        return (Double)this.zza.zzg(2);
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Integer zze() {
        return (Integer)this.zza.zzg(3);
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Long zzf() {
        return (Long)this.zza.zzg(1);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final Object zzg(int v) {
        return this.zza.zzg(v);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzh() {
        return this.zza.zzh();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzi() {
        return this.zza.zzi();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzj() {
        return this.zza.zzj();
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final String zzk() {
        return this.zza.zzk();
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final String zzl() {
        return (String)this.zza.zzg(0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final List zzm(String s, String s1) {
        return this.zza.zzm(s, s1);
    }

    @Override  // com.google.android.gms.measurement.zzd
    public final Map zzn(boolean z) {
        return this.zza.zzo(null, null, z);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final Map zzo(String s, String s1, boolean z) {
        return this.zza.zzo(s, s1, z);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzp(String s) {
        this.zza.zzp(s);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzq(String s, String s1, Bundle bundle0) {
        this.zza.zzq(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzr(String s) {
        this.zza.zzr(s);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzs(String s, String s1, Bundle bundle0) {
        this.zza.zzs(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzt(String s, String s1, Bundle bundle0, long v) {
        this.zza.zzt(s, s1, bundle0, v);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzu(zzhg zzhg0) {
        this.zza.zzu(zzhg0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzv(Bundle bundle0) {
        this.zza.zzv(bundle0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzw(zzhf zzhf0) {
        this.zza.zzw(zzhf0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzil
    public final void zzx(zzhg zzhg0) {
        this.zza.zzx(zzhg0);
    }
}

