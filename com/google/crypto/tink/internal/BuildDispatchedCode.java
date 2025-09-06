package com.google.crypto.tink.internal;

import android.os.Build.VERSION;
import javax.annotation.Nullable;

final class BuildDispatchedCode {
    @Nullable
    public static Integer getApiLevel() {
        return Build.VERSION.SDK_INT;
    }
}

