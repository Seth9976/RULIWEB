package com.google.android.gms.internal.auth;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

final class zzco implements zzcl {
    private static zzco zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    private zzco() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzco(Context context0) {
        this.zzb = context0;
        zzcn zzcn0 = new zzcn(this, null);
        this.zzc = zzcn0;
        context0.getContentResolver().registerContentObserver(zzcb.zza, true, zzcn0);
    }

    static zzco zza(Context context0) {
        synchronized(zzco.class) {
            if(zzco.zza == null) {
                zzco.zza = PermissionChecker.checkSelfPermission(context0, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzco(context0) : new zzco();
            }
            return zzco.zza;
        }
    }

    @Override  // com.google.android.gms.internal.auth.zzcl
    @Nullable
    public final Object zzb(String s) {
        return this.zzc(s);
    }

    @Nullable
    public final String zzc(String s) {
        if(this.zzb != null && !zzcc.zza(this.zzb)) {
            try {
                return (String)zzcj.zza(() -> zzcb.zza(this.zzb.getContentResolver(), s, null));
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
        synchronized(zzco.class) {
            zzco zzco0 = zzco.zza;
            if(zzco0 != null) {
                Context context0 = zzco0.zzb;
                if(context0 != null && zzco0.zzc != null) {
                    context0.getContentResolver().unregisterContentObserver(zzco.zza.zzc);
                }
            }
            zzco.zza = null;
        }
    }
}

