package com.google.android.material.internal;

final class FadeThroughUtils {
    static final float THRESHOLD_ALPHA = 0.5f;

    static void calculateFadeOutAndInAlphas(float f, float[] arr_f) {
        if(f <= 0.5f) {
            arr_f[0] = 1.0f - f * 2.0f;
            arr_f[1] = 0.0f;
            return;
        }
        arr_f[0] = 0.0f;
        arr_f[1] = f * 2.0f - 1.0f;
    }
}

