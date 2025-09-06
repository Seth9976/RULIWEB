package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcl;

public final class zzhi {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzcl zzg;
    boolean zzh;
    final Long zzi;
    String zzj;

    public zzhi(Context context0, zzcl zzcl0, Long long0) {
        this.zzh = true;
        Preconditions.checkNotNull(context0);
        Context context1 = context0.getApplicationContext();
        Preconditions.checkNotNull(context1);
        this.zza = context1;
        this.zzi = long0;
        if(zzcl0 != null) {
            this.zzg = zzcl0;
            this.zzb = zzcl0.zzf;
            this.zzc = zzcl0.zze;
            this.zzd = zzcl0.zzd;
            this.zzh = zzcl0.zzc;
            this.zzf = zzcl0.zzb;
            this.zzj = zzcl0.zzh;
            Bundle bundle0 = zzcl0.zzg;
            if(bundle0 != null) {
                this.zze = Boolean.valueOf(bundle0.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}

