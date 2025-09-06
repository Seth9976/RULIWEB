package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

final class zzhn implements zzhk {
    private static zzhn zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    private zzhn() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzhn(Context context0) {
        this.zzb = context0;
        zzhm zzhm0 = new zzhm(this, null);
        this.zzc = zzhm0;
        context0.getContentResolver().registerContentObserver(zzha.zza, true, zzhm0);
    }

    static zzhn zza(Context context0) {
        synchronized(zzhn.class) {
            if(zzhn.zza == null) {
                zzhn.zza = PermissionChecker.checkSelfPermission(context0, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzhn(context0) : new zzhn();
            }
            return zzhn.zza;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhk
    @Nullable
    public final Object zzb(String s) {
        return this.zzc(s);
    }

    @Nullable
    public final String zzc(String s) {
        if(this.zzb != null && !zzhb.zza(this.zzb)) {
            try {
                return (String)zzhi.zza(() -> zzha.zza(this.zzb.getContentResolver(), s, null));
            }
            catch(IllegalStateException | SecurityException | NullPointerException illegalStateException0) {
                Log.e("GservicesLoader", "Unable to read GServices for: " + s, illegalStateException0);
            }
        }
        return null;
    }

    // 检测为 Lambda 实现
    final String zzd(String s) [...]

    static void zze() {
        synchronized(zzhn.class) {
            zzhn zzhn0 = zzhn.zza;
            if(zzhn0 != null) {
                Context context0 = zzhn0.zzb;
                if(context0 != null && zzhn0.zzc != null) {
                    context0.getContentResolver().unregisterContentObserver(zzhn.zza.zzc);
                }
            }
            zzhn.zza = null;
        }
    }
}

