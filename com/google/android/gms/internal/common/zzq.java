package com.google.android.gms.internal.common;

import javax.annotation.CheckForNull;
import org.jspecify.nullness.NullMarked;

@NullMarked
public final class zzq {
    static final CharSequence zza(@CheckForNull Object object0, String s) {
        object0.getClass();
        return object0 instanceof CharSequence ? ((CharSequence)object0) : object0.toString();
    }
}

