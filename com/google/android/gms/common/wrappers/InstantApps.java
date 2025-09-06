package com.google.android.gms.common.wrappers;

import android.content.Context;

public class InstantApps {
    private static Context zza;
    private static Boolean zzb;

    public static boolean isInstantApp(Context context0) {
        synchronized(InstantApps.class) {
            Context context1 = context0.getApplicationContext();
            Context context2 = InstantApps.zza;
            if(context2 != null) {
                Boolean boolean0 = InstantApps.zzb;
                if(boolean0 != null && context2 == context1) {
                    return boolean0.booleanValue();
                }
            }
            InstantApps.zzb = Boolean.valueOf(context1.getPackageManager().isInstantApp());
            InstantApps.zza = context1;
            return InstantApps.zzb.booleanValue();
        }
    }
}

