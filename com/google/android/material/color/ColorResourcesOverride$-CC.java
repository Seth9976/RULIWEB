package com.google.android.material.color;

import android.os.Build.VERSION;

public final class ColorResourcesOverride.-CC {
    public static ColorResourcesOverride getInstance() {
        if(30 <= Build.VERSION.SDK_INT && Build.VERSION.SDK_INT <= 33) {
            return ResourcesLoaderColorResourcesOverride.getInstance();
        }
        return Build.VERSION.SDK_INT < 34 ? null : ResourcesLoaderColorResourcesOverride.getInstance();
    }
}

