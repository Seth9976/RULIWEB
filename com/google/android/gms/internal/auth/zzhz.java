package com.google.android.gms.internal.auth;

import android.util.Base64;

public final class zzhz implements zzhx {
    public static final zzdc zza;
    public static final zzdc zzb;
    public static final zzdc zzc;
    public static final zzdc zzd;
    public static final zzdc zze;
    public static final zzdc zzf;
    public static final zzdc zzg;
    public static final zzdc zzh;
    public static final zzdc zzi;
    public static final zzdc zzj;
    public static final zzdc zzk;
    public static final zzdc zzl;
    public static final zzdc zzm;

    static {
        zzcz zzcz0 = new zzcz(zzcr.zza("com.google.android.gms.auth_account")).zzb().zza();
        zzhz.zza = zzcz0.zzc("getTokenRefactor__account_data_service_sample_percentage", 0.0);
        zzhz.zzb = zzcz0.zze("getTokenRefactor__account_data_service_tokenAPI_usable", true);
        zzhz.zzc = zzcz0.zzd("getTokenRefactor__account_manager_timeout_seconds", 20L);
        zzhz.zzd = zzcz0.zzd("getTokenRefactor__android_id_shift", 0L);
        try {
            zzhz.zze = zzcz0.zzf("getTokenRefactor__blocked_packages", zzhs.zzp(Base64.decode("ChNjb20uYW5kcm9pZC52ZW5kaW5nCiBjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5tZWV0aW5ncwohY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubWVzc2FnaW5n", 3)), zzhy.zza);
        }
        catch(Exception exception0) {
            throw new AssertionError(exception0);
        }
        zzhz.zzf = zzcz0.zze("getTokenRefactor__chimera_get_token_evolved", true);
        zzhz.zzg = zzcz0.zzd("getTokenRefactor__clear_token_timeout_seconds", 20L);
        zzhz.zzh = zzcz0.zzd("getTokenRefactor__default_task_timeout_seconds", 20L);
        zzhz.zzi = zzcz0.zze("getTokenRefactor__gaul_accounts_api_evolved", false);
        zzhz.zzj = zzcz0.zze("getTokenRefactor__gaul_token_api_evolved", false);
        zzhz.zzk = zzcz0.zzd("getTokenRefactor__get_token_timeout_seconds", 120L);
        zzhz.zzl = zzcz0.zze("getTokenRefactor__gms_account_authenticator_evolved", true);
        zzhz.zzm = zzcz0.zzc("getTokenRefactor__gms_account_authenticator_sample_percentage", 0.0);
    }

    @Override  // com.google.android.gms.internal.auth.zzhx
    public final zzhs zza() {
        return (zzhs)zzhz.zze.zzb();
    }

    @Override  // com.google.android.gms.internal.auth.zzhx
    public final boolean zzb() {
        return ((Boolean)zzhz.zzi.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.auth.zzhx
    public final boolean zzc() {
        return ((Boolean)zzhz.zzj.zzb()).booleanValue();
    }
}

