package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.provider.Settings.Global;

public class AnimatorDurationScaleProvider {
    private static float defaultSystemAnimatorDurationScale = 1.0f;

    static {
    }

    public float getSystemAnimatorDurationScale(ContentResolver contentResolver0) {
        return Settings.Global.getFloat(contentResolver0, "animator_duration_scale", 1.0f);
    }

    public static void setDefaultSystemAnimatorDurationScale(float f) {
        AnimatorDurationScaleProvider.defaultSystemAnimatorDurationScale = f;
    }
}

