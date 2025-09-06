package com.google.android.gms.internal.common;

import javax.annotation.CheckForNull;
import org.jspecify.nullness.NullMarked;

@NullMarked
public final class zzr extends zzp {
    // 去混淆评级： 低(20)
    public static boolean zza(@CheckForNull Object object0, @CheckForNull Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }
}

