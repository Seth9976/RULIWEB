package com.google.android.gms.common.stats;

import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.text.TextUtils;

@Deprecated
public class StatsUtils {
    public static String getEventKey(PowerManager.WakeLock powerManager$WakeLock0, String s) {
        String s1 = String.valueOf(((long)Process.myPid()) << 0x20 | ((long)System.identityHashCode(powerManager$WakeLock0)));
        if(TextUtils.isEmpty(s)) {
            s = "";
        }
        return s1 + s;
    }
}

