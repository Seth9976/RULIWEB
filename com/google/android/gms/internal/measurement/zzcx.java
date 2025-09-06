package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.measurement.internal.zzfv;

final class zzcx extends zzdu {
    final String zza;
    final String zzb;
    final Context zzc;
    final Bundle zzd;
    final zzef zze;

    zzcx(zzef zzef0, String s, String s1, Context context0, Bundle bundle0) {
        this.zze = zzef0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = context0;
        this.zzd = bundle0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    public final void zza() {
        String s2;
        String s1;
        String s;
        try {
            if(zzef.zzR(this.zze, this.zza, this.zzb)) {
                s = this.zzb;
                s1 = this.zza;
                s2 = zzef.zzj(this.zze);
            }
            else {
                s2 = null;
                s1 = null;
                s = null;
            }
            Preconditions.checkNotNull(this.zzc);
            zzcc zzcc0 = this.zze.zzf(this.zzc, true);
            zzef.zzs(this.zze, zzcc0);
            if(zzef.zze(this.zze) == null) {
                Log.w(zzef.zzj(this.zze), "Failed to connect to measurement client.");
                return;
            }
            int v = DynamiteModule.getLocalVersion(this.zzc, "com.google.android.gms.measurement.dynamite");
            int v1 = DynamiteModule.getRemoteVersion(this.zzc, "com.google.android.gms.measurement.dynamite");
            String s3 = zzfv.zza(this.zzc);
            zzcl zzcl0 = new zzcl(79000L, ((long)Math.max(v, v1)), v1 < v, s2, s1, s, this.zzd, s3);
            ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zze))).initialize(ObjectWrapper.wrap(this.zzc), zzcl0, this.zzh);
        }
        catch(Exception exception0) {
            zzef.zzt(this.zze, exception0, true, false);
        }
    }
}

