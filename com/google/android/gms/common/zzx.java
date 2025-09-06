package com.google.android.gms.common;

import android.util.Log;
import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzx {
    final boolean zza;
    @Nullable
    final String zzb;
    @Nullable
    final Throwable zzc;
    final int zzd;
    private static final zzx zze;

    static {
        zzx.zze = new zzx(true, 3, 1, null, null);
    }

    private zzx(boolean z, int v, int v1, @Nullable String s, @Nullable Throwable throwable0) {
        this.zza = z;
        this.zzd = v;
        this.zzb = s;
        this.zzc = throwable0;
    }

    zzx(boolean z, int v, int v1, String s, Throwable throwable0, zzw zzw0) {
        this(false, 1, 5, null, null);
    }

    @Nullable
    String zza() {
        return this.zzb;
    }

    @Deprecated
    static zzx zzb() {
        return zzx.zze;
    }

    static zzx zzc(String s) {
        return new zzx(false, 1, 5, s, null);
    }

    static zzx zzd(String s, Throwable throwable0) {
        return new zzx(false, 1, 5, s, throwable0);
    }

    final void zze() {
        if(!this.zza && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if(this.zzc != null) {
                Log.d("GoogleCertificatesRslt", this.zza(), this.zzc);
                return;
            }
            Log.d("GoogleCertificatesRslt", this.zza());
        }
    }

    static zzx zzf(int v) {
        return new zzx(true, v, 1, null, null);
    }

    static zzx zzg(int v, int v1, String s, @Nullable Throwable throwable0) {
        return new zzx(false, v, v1, s, throwable0);
    }
}

