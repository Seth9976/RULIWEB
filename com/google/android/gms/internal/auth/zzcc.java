package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

public final class zzcc {
    private static UserManager zza;
    private static volatile boolean zzb;

    static {
        zzcc.zzb = false;
    }

    public static boolean zza(Context context0) {
        boolean z;
        if(!zzcc.zzb) {
            synchronized(zzcc.class) {
                if(zzcc.zzb) {
                    return false;
                }
                int v1 = 1;
                while(true) {
                    if(zzcc.zza == null) {
                        zzcc.zza = (UserManager)context0.getSystemService(UserManager.class);
                    }
                    UserManager userManager0 = zzcc.zza;
                    if(userManager0 == null) {
                        z = true;
                        goto label_28;
                    }
                    try {
                        if(!userManager0.isUserUnlocked() && userManager0.isUserRunning(Process.myUserHandle())) {
                            goto label_25;
                        }
                        break;
                    }
                    catch(NullPointerException nullPointerException0) {
                        Log.w("DirectBootUtils", "Failed to check if user is unlocked.", nullPointerException0);
                        zzcc.zza = null;
                        ++v1;
                    }
                }
                z = true;
                goto label_26;
            label_25:
                z = false;
            label_26:
                if(z) {
                    zzcc.zza = null;
                }
            label_28:
                if(z) {
                    zzcc.zzb = true;
                }
                return !z;
            }
        }
        return false;
    }

    public static boolean zzb() [...] // 潜在的解密器
}

