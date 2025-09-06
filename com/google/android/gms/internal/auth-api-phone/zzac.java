package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.Feature;

public final class zzac {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature[] zze;

    static {
        Feature feature0 = new Feature("sms_code_autofill", 2L);
        zzac.zza = feature0;
        Feature feature1 = new Feature("sms_code_browser", 2L);
        zzac.zzb = feature1;
        Feature feature2 = new Feature("sms_retrieve", 1L);
        zzac.zzc = feature2;
        Feature feature3 = new Feature("user_consent", 3L);
        zzac.zzd = feature3;
        zzac.zze = new Feature[]{feature0, feature1, feature2, feature3};
    }
}

