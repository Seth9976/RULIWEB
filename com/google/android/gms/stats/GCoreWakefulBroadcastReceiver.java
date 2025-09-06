package com.google.android.gms.stats;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;

public abstract class GCoreWakefulBroadcastReceiver extends WakefulBroadcastReceiver {
    public static boolean completeWakefulIntent(Context context0, Intent intent0) {
        return intent0 == null ? false : WakefulBroadcastReceiver.completeWakefulIntent(intent0);
    }
}

