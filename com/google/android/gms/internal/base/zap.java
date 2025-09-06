package com.google.android.gms.internal.base;

import android.os.Build.VERSION;

public final class zap {
    public static final int zaa;

    static {
        zap.zaa = Build.VERSION.SDK_INT < 0x1F ? 0 : 0x2000000;
    }
}

