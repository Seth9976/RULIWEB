package com.google.android.material.transition.platform;

class FitModeResult {
    final float currentEndHeight;
    final float currentEndWidth;
    final float currentStartHeight;
    final float currentStartWidth;
    final float endScale;
    final float startScale;

    FitModeResult(float f, float f1, float f2, float f3, float f4, float f5) {
        this.startScale = f;
        this.endScale = f1;
        this.currentStartWidth = f2;
        this.currentStartHeight = f3;
        this.currentEndWidth = f4;
        this.currentEndHeight = f5;
    }
}

