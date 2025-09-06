package com.google.android.gms.common.util;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public final class CrashUtils {
    private static final String[] zza;

    static {
        CrashUtils.zza = new String[]{"android.", "com.android.", "dalvik.", "java.", "javax."};
    }

    @ResultIgnorabilityUnspecified
    public static boolean addDynamiteErrorToDropBox(Context context0, Throwable throwable0) {
        try {
            Preconditions.checkNotNull(context0);
            Preconditions.checkNotNull(throwable0);
        }
        catch(Exception exception0) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", exception0);
        }
        return false;
    }
}

