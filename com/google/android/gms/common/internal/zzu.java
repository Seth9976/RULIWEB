package com.google.android.gms.common.internal;

import android.net.Uri;

public final class zzu {
    private static final Uri zza;
    private static final Uri zzb;

    static {
        Uri uri0 = Uri.parse("https://plus.google.com/");
        zzu.zza = uri0;
        zzu.zzb = uri0.buildUpon().appendPath("circles").appendPath("find").build();
    }
}

