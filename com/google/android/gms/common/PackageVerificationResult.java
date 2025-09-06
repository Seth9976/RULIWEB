package com.google.android.gms.common;

import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
public class PackageVerificationResult {
    private final String zza;
    private final boolean zzb;
    @Nullable
    private final String zzc;
    @Nullable
    private final Throwable zzd;

    private PackageVerificationResult(String s, int v, boolean z, @Nullable String s1, @Nullable Throwable throwable0) {
        this.zza = s;
        this.zzb = z;
        this.zzc = s1;
        this.zzd = throwable0;
    }

    public static PackageVerificationResult zza(String s, String s1, @Nullable Throwable throwable0) {
        return new PackageVerificationResult(s, 1, false, s1, throwable0);
    }

    public final void zzb() {
        if(!this.zzb) {
            String s = "PackageVerificationRslt: " + this.zzc;
            throw this.zzd == null ? new SecurityException(s) : new SecurityException(s, this.zzd);
        }
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public static PackageVerificationResult zzd(String s, int v) {
        return new PackageVerificationResult(s, v, true, null, null);
    }
}

