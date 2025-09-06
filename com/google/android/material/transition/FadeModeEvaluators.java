package com.google.android.material.transition;

class FadeModeEvaluators {
    private static final FadeModeEvaluator CROSS;
    private static final FadeModeEvaluator IN;
    private static final FadeModeEvaluator OUT;
    private static final FadeModeEvaluator THROUGH;

    static {
        FadeModeEvaluators.IN = new FadeModeEvaluator() {
            @Override  // com.google.android.material.transition.FadeModeEvaluator
            public FadeModeResult evaluate(float f, float f1, float f2, float f3) {
                return FadeModeResult.endOnTop(0xFF, TransitionUtils.lerp(0, 0xFF, f1, f2, f));
            }
        };
        FadeModeEvaluators.OUT = new FadeModeEvaluator() {
            @Override  // com.google.android.material.transition.FadeModeEvaluator
            public FadeModeResult evaluate(float f, float f1, float f2, float f3) {
                return FadeModeResult.startOnTop(TransitionUtils.lerp(0xFF, 0, f1, f2, f), 0xFF);
            }
        };
        FadeModeEvaluators.CROSS = new FadeModeEvaluator() {
            @Override  // com.google.android.material.transition.FadeModeEvaluator
            public FadeModeResult evaluate(float f, float f1, float f2, float f3) {
                return FadeModeResult.startOnTop(TransitionUtils.lerp(0xFF, 0, f1, f2, f), TransitionUtils.lerp(0, 0xFF, f1, f2, f));
            }
        };
        FadeModeEvaluators.THROUGH = new FadeModeEvaluator() {
            @Override  // com.google.android.material.transition.FadeModeEvaluator
            public FadeModeResult evaluate(float f, float f1, float f2, float f3) {
                float f4 = (f2 - f1) * f3 + f1;
                return FadeModeResult.startOnTop(TransitionUtils.lerp(0xFF, 0, f1, f4, f), TransitionUtils.lerp(0, 0xFF, f4, f2, f));
            }
        };
    }

    static FadeModeEvaluator get(int v, boolean z) {
        switch(v) {
            case 0: {
                return z ? FadeModeEvaluators.IN : FadeModeEvaluators.OUT;
            }
            case 1: {
                return z ? FadeModeEvaluators.OUT : FadeModeEvaluators.IN;
            }
            case 2: {
                return FadeModeEvaluators.CROSS;
            }
            case 3: {
                return FadeModeEvaluators.THROUGH;
            }
            default: {
                throw new IllegalArgumentException("Invalid fade mode: " + v);
            }
        }
    }
}

