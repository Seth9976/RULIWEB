package com.google.android.material.transition;

class FadeModeResult {
    final int endAlpha;
    final boolean endOnTop;
    final int startAlpha;

    private FadeModeResult(int v, int v1, boolean z) {
        this.startAlpha = v;
        this.endAlpha = v1;
        this.endOnTop = z;
    }

    static FadeModeResult endOnTop(int v, int v1) {
        return new FadeModeResult(v, v1, true);
    }

    static FadeModeResult startOnTop(int v, int v1) {
        return new FadeModeResult(v, v1, false);
    }
}

