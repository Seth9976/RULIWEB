package com.google.android.gms.internal.auth;

public final class zzhv implements zzhu {
    public static final zzdc zza;
    public static final zzdc zzb;
    public static final zzdc zzc;
    public static final zzdc zzd;
    public static final zzdc zze;

    static {
        zzcz zzcz0 = new zzcz(zzcr.zza("com.google.android.gms.auth_account")).zzb().zza();
        zzhv.zza = zzcz0.zze("Aang__create_auth_exception_with_pending_intent", false);
        zzhv.zzb = zzcz0.zze("Aang__enable_add_account_restrictions", false);
        zzhv.zzc = zzcz0.zze("Aang__log_missing_gaia_id_event", true);
        zzhv.zzd = zzcz0.zze("Aang__log_obfuscated_gaiaid_status", true);
        zzhv.zze = zzcz0.zze("Aang__switch_clear_token_to_aang", false);
    }

    @Override  // com.google.android.gms.internal.auth.zzhu
    public final boolean zza() {
        return ((Boolean)zzhv.zza.zzb()).booleanValue();
    }
}

