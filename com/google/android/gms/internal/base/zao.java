package com.google.android.gms.internal.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public final class zao extends ContextCompat {
    // 去混淆评级： 中等(60)
    @ResultIgnorabilityUnspecified
    @Deprecated
    public static Intent zaa(Context context0, BroadcastReceiver broadcastReceiver0, IntentFilter intentFilter0) {
        return context0.registerReceiver(broadcastReceiver0, intentFilter0, 2);
    }
}

