package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzah {
    private static final Object zza;
    private static boolean zzb;
    private static String zzc;
    private static int zzd;

    static {
        zzah.zza = new Object();
    }

    public static int zza(Context context0) {
        zzah.zzc(context0);
        return zzah.zzd;
    }

    public static String zzb(Context context0) {
        zzah.zzc(context0);
        return zzah.zzc;
    }

    private static void zzc(Context context0) {
        synchronized(zzah.zza) {
            if(zzah.zzb) {
                return;
            }
            zzah.zzb = true;
            PackageManagerWrapper packageManagerWrapper0 = Wrappers.packageManager(context0);
            try {
                Bundle bundle0 = packageManagerWrapper0.getApplicationInfo("com.ruliweb.www.ruliapp", 0x80).metaData;
                if(bundle0 == null) {
                    return;
                }
                zzah.zzc = bundle0.getString("com.google.app.id");
                zzah.zzd = bundle0.getInt("com.google.android.gms.version");
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.wtf("MetadataValueReader", "This should never happen.", packageManager$NameNotFoundException0);
            }
        }
    }
}

