package com.google.android.gms.common.internal;

import android.net.Uri.Builder;
import android.net.Uri;

public final class ResourceUtils {
    private static final Uri zza;

    static {
        ResourceUtils.zza = new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
    }
}

