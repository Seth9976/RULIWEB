package com.google.android.material.color.utilities;

public final class DislikeAnalyzer {
    private DislikeAnalyzer() {
        throw new UnsupportedOperationException();
    }

    // 去混淆评级： 低(20)
    public static Hct fixIfDisliked(Hct hct0) {
        return DislikeAnalyzer.isDisliked(hct0) ? Hct.from(hct0.getHue(), hct0.getChroma(), 70.0) : hct0;
    }

    // 去混淆评级： 低(30)
    public static boolean isDisliked(Hct hct0) {
        return ((double)Math.round(hct0.getHue())) >= 90.0 && ((double)Math.round(hct0.getHue())) <= 111.0 && ((double)Math.round(hct0.getChroma())) > 16.0 && ((double)Math.round(hct0.getTone())) < 65.0;
    }
}

