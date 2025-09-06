package com.google.android.material.transition.platform;

import android.graphics.RectF;

class FitModeEvaluators {
    private static final FitModeEvaluator HEIGHT;
    private static final FitModeEvaluator WIDTH;

    static {
        FitModeEvaluators.WIDTH = new FitModeEvaluator() {
            @Override  // com.google.android.material.transition.platform.FitModeEvaluator
            public void applyMask(RectF rectF0, float f, FitModeResult fitModeResult0) {
                rectF0.bottom -= Math.abs(fitModeResult0.currentEndHeight - fitModeResult0.currentStartHeight) * f;
            }

            @Override  // com.google.android.material.transition.platform.FitModeEvaluator
            public FitModeResult evaluate(float f, float f1, float f2, float f3, float f4, float f5, float f6) {
                float f7 = TransitionUtils.lerp(f3, f5, f1, f2, f, true);
                return new FitModeResult(f7 / f3, f7 / f5, f7, f4 * (f7 / f3), f7, f6 * (f7 / f5));
            }

            @Override  // com.google.android.material.transition.platform.FitModeEvaluator
            public boolean shouldMaskStartBounds(FitModeResult fitModeResult0) {
                return fitModeResult0.currentStartHeight > fitModeResult0.currentEndHeight;
            }
        };
        FitModeEvaluators.HEIGHT = new FitModeEvaluator() {
            @Override  // com.google.android.material.transition.platform.FitModeEvaluator
            public void applyMask(RectF rectF0, float f, FitModeResult fitModeResult0) {
                float f1 = Math.abs(fitModeResult0.currentEndWidth - fitModeResult0.currentStartWidth) / 2.0f * f;
                rectF0.left += f1;
                rectF0.right -= f1;
            }

            @Override  // com.google.android.material.transition.platform.FitModeEvaluator
            public FitModeResult evaluate(float f, float f1, float f2, float f3, float f4, float f5, float f6) {
                float f7 = TransitionUtils.lerp(f4, f6, f1, f2, f, true);
                return new FitModeResult(f7 / f4, f7 / f6, f3 * (f7 / f4), f7, f5 * (f7 / f6), f7);
            }

            @Override  // com.google.android.material.transition.platform.FitModeEvaluator
            public boolean shouldMaskStartBounds(FitModeResult fitModeResult0) {
                return fitModeResult0.currentStartWidth > fitModeResult0.currentEndWidth;
            }
        };
    }

    static FitModeEvaluator get(int v, boolean z, RectF rectF0, RectF rectF1) {
        switch(v) {
            case 0: {
                return FitModeEvaluators.shouldAutoFitToWidth(z, rectF0, rectF1) ? FitModeEvaluators.WIDTH : FitModeEvaluators.HEIGHT;
            }
            case 1: {
                return FitModeEvaluators.WIDTH;
            }
            case 2: {
                return FitModeEvaluators.HEIGHT;
            }
            default: {
                throw new IllegalArgumentException("Invalid fit mode: " + v);
            }
        }
    }

    private static boolean shouldAutoFitToWidth(boolean z, RectF rectF0, RectF rectF1) {
        float f = rectF0.width();
        float f1 = rectF0.height();
        float f2 = rectF1.width();
        float f3 = rectF1.height();
        return z ? f3 * f / f2 >= f1 : f2 * f1 / f >= f3;
    }
}

