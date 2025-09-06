package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.Set;
import javax.annotation.Nullable;

@CheckReturnValue
@RestrictedInheritance(allowedOnPath = ".*java.*/com/google/android/gms/common/testing/.*", explanation = "Sub classing of GMS Core\'s APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
public class GoogleSignatureVerifier {
    @Nullable
    private static GoogleSignatureVerifier zza;
    @Nullable
    private static volatile Set zzb;
    private final Context zzc;
    private volatile String zzd;

    public GoogleSignatureVerifier(Context context0) {
        this.zzc = context0.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context0) {
        Preconditions.checkNotNull(context0);
        synchronized(GoogleSignatureVerifier.class) {
            if(GoogleSignatureVerifier.zza == null) {
                zzn.zze(context0);
                GoogleSignatureVerifier.zza = new GoogleSignatureVerifier(context0);
            }
            return GoogleSignatureVerifier.zza;
        }
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo0) {
        if(packageInfo0 == null) {
            return false;
        }
        if(GoogleSignatureVerifier.zzb(packageInfo0, false)) {
            return true;
        }
        if(GoogleSignatureVerifier.zzb(packageInfo0, true)) {
            if(GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzc)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren\'t accepted on this build.");
        }
        return false;
    }

    public boolean isPackageGoogleSigned(@Nullable String s) {
        zzx zzx0 = this.zzc(s, false, false);
        zzx0.zze();
        return zzx0.zza;
    }

    public boolean isUidGoogleSigned(int v) {
        zzx zzx0;
        String[] arr_s = this.zzc.getPackageManager().getPackagesForUid(v);
        if(arr_s == null || arr_s.length == 0) {
            zzx0 = zzx.zzc("no pkgs");
        }
        else {
            zzx0 = null;
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                zzx0 = this.zzc(arr_s[v1], false, false);
                if(zzx0.zza) {
                    zzx0.zze();
                    return zzx0.zza;
                }
            }
            Preconditions.checkNotNull(zzx0);
        }
        zzx0.zze();
        return zzx0.zza;
    }

    @Nullable
    static final zzj zza(PackageInfo packageInfo0, zzj[] arr_zzj) {
        if(packageInfo0.signatures != null) {
            if(packageInfo0.signatures.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            zzk zzk0 = new zzk(packageInfo0.signatures[0].toByteArray());
            for(int v = 0; v < arr_zzj.length; ++v) {
                if(arr_zzj[v].equals(zzk0)) {
                    return arr_zzj[v];
                }
            }
        }
        return null;
    }

    public static final boolean zzb(PackageInfo packageInfo0, boolean z) {
        if(z) {
            if(packageInfo0 == null) {
                throw new NullPointerException();
            }
            if("com.android.vending".equals(packageInfo0.packageName) || "com.google.android.gms".equals(packageInfo0.packageName)) {
                if(packageInfo0.applicationInfo != null && (packageInfo0.applicationInfo.flags & 0x81) != 0) {
                    return packageInfo0 == null || packageInfo0.signatures == null ? false : GoogleSignatureVerifier.zza(packageInfo0, zzm.zza) != null;
                }
                return packageInfo0 == null || packageInfo0.signatures == null ? false : GoogleSignatureVerifier.zza(packageInfo0, new zzj[]{zzm.zza[0]}) != null;
            }
        }
        return packageInfo0 == null || packageInfo0.signatures == null ? false : (z ? GoogleSignatureVerifier.zza(packageInfo0, zzm.zza) : GoogleSignatureVerifier.zza(packageInfo0, new zzj[]{zzm.zza[0]})) != null;
    }

    private final zzx zzc(@Nullable String s, boolean z, boolean z1) {
        PackageInfo packageInfo0;
        zzx zzx0;
        if(s == null) {
            return zzx.zzc("null pkg");
        }
        if(!s.equals(this.zzd)) {
            if(zzn.zzg()) {
                zzx0 = zzn.zzb(s, GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzc), false, false);
            }
            else {
                try {
                    packageInfo0 = this.zzc.getPackageManager().getPackageInfo(s, 0x40);
                }
                catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                    return zzx.zzd(("no pkg " + s), packageManager$NameNotFoundException0);
                }
                boolean z2 = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzc);
                if(packageInfo0 == null) {
                    zzx0 = zzx.zzc("null pkg");
                }
                else if(packageInfo0.signatures == null || packageInfo0.signatures.length != 1) {
                    zzx0 = zzx.zzc("single cert required");
                }
                else {
                    zzk zzk0 = new zzk(packageInfo0.signatures[0].toByteArray());
                    String s1 = packageInfo0.packageName;
                    zzx zzx1 = zzn.zza(s1, zzk0, z2, false);
                    zzx0 = !zzx1.zza || packageInfo0.applicationInfo == null || (packageInfo0.applicationInfo.flags & 2) == 0 || !zzn.zza(s1, zzk0, false, true).zza ? zzx1 : zzx.zzc("debuggable release cert app rejected");
                }
            }
            if(zzx0.zza) {
                this.zzd = s;
            }
            return zzx0;
        }
        return zzx.zzb();
    }
}

