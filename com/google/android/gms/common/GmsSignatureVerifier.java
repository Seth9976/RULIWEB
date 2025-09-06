package com.google.android.gms.common;

import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.HashMap;

@RestrictedInheritance(allowedOnPath = ".*javatests/com/google/android/gmscore/integ/client/common/robolectric/.*", explanation = "Sub classing of GMS Core\'s APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
public class GmsSignatureVerifier {
    private static final zzab zza;
    private static final zzab zzb;
    private static final HashMap zzc;

    static {
        zzz zzz0 = new zzz();
        zzz0.zzd("com.google.android.gms");
        zzz0.zza(204200000L);
        zzz0.zzc(zzag.zzn(zzn.zzd.zzf(), zzn.zzb.zzf()));
        zzz0.zzb(zzag.zzn(zzn.zzc.zzf(), zzn.zza.zzf()));
        GmsSignatureVerifier.zza = zzz0.zze();
        zzz zzz1 = new zzz();
        zzz1.zzd("com.android.vending");
        zzz1.zza(82240000L);
        zzz1.zzc(zzag.zzm(zzn.zzd.zzf()));
        zzz1.zzb(zzag.zzm(zzn.zzc.zzf()));
        GmsSignatureVerifier.zzb = zzz1.zze();
        GmsSignatureVerifier.zzc = new HashMap();
    }
}

