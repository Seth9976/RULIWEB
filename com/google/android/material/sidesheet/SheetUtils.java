package com.google.android.material.sidesheet;

final class SheetUtils {
    static boolean isSwipeMostlyHorizontal(float f, float f1) {
        return Math.abs(f) > Math.abs(f1);
    }
}

