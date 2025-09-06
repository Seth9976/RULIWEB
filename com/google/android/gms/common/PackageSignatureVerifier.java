package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;

@CheckReturnValue
@RestrictedInheritance(allowedOnPath = ".*javatests.*/com/google/android/gms/common/.*", explanation = "Sub classing of GMS Core\'s APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
public class PackageSignatureVerifier {
    static volatile zzac zza;
    private static zzad zzb;

    public PackageVerificationResult queryPackageSignatureVerified(Context context0, String s) {
        boolean z = GooglePlayServicesUtilLight.honorsDebugCertificates(context0);
        PackageSignatureVerifier.zza(context0);
        if(!zzn.zzf()) {
            throw new zzae();
        }
        String s1 = s + (z ? "-1" : "-0");
        if(PackageSignatureVerifier.zza != null && zzac.zzb(PackageSignatureVerifier.zza).equals(s1)) {
            return zzac.zza(PackageSignatureVerifier.zza);
        }
        PackageSignatureVerifier.zza(context0);
        zzx zzx0 = zzn.zzc(s, z, false, false);
        if(zzx0.zza) {
            PackageSignatureVerifier.zza = new zzac(s1, PackageVerificationResult.zzd(s, zzx0.zzd));
            return zzac.zza(PackageSignatureVerifier.zza);
        }
        Preconditions.checkNotNull(zzx0.zzb);
        return PackageVerificationResult.zza(s, zzx0.zzb, zzx0.zzc);
    }

    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(Context context0, String s) {
        try {
            PackageVerificationResult packageVerificationResult0 = this.queryPackageSignatureVerified(context0, s);
            packageVerificationResult0.zzb();
            return packageVerificationResult0;
        }
        catch(SecurityException securityException0) {
            PackageVerificationResult packageVerificationResult1 = this.queryPackageSignatureVerified(context0, s);
            if(packageVerificationResult1.zzc()) {
                Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", securityException0);
            }
            return packageVerificationResult1;
        }
    }

    private static zzad zza(Context context0) {
        synchronized(PackageSignatureVerifier.class) {
            if(PackageSignatureVerifier.zzb == null) {
                PackageSignatureVerifier.zzb = new zzad(context0);
            }
            return PackageSignatureVerifier.zzb;
        }
    }
}

