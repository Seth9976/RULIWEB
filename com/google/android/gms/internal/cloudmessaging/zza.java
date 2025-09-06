package com.google.android.gms.internal.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public final class zza {
    public static final int zza;

    static {
        zza.zza = Build.VERSION.SDK_INT >= 0x1F || Build.VERSION.SDK_INT >= 30 && Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 83 && Build.VERSION.CODENAME.charAt(0) <= 90 ? 0x2000000 : 0;
    }

    public static PendingIntent zza(Context context0, int v, Intent intent0, int v1) {
        return PendingIntent.getBroadcast(context0, 0, intent0, v1);
    }
}

