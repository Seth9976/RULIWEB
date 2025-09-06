package com.google.android.gms.common.util;

import android.os.Build.VERSION;
import androidx.core.os.BuildCompat;

public final class PlatformVersion {
    public static boolean isAtLeastHoneycomb() {
        return true;
    }

    public static boolean isAtLeastHoneycombMR1() {
        return true;
    }

    public static boolean isAtLeastIceCreamSandwich() [...] // Inlined contents

    public static boolean isAtLeastIceCreamSandwichMR1() {
        return true;
    }

    public static boolean isAtLeastJellyBean() [...] // Inlined contents

    public static boolean isAtLeastJellyBeanMR1() {
        return true;
    }

    public static boolean isAtLeastJellyBeanMR2() [...] // Inlined contents

    public static boolean isAtLeastKitKat() [...] // Inlined contents

    public static boolean isAtLeastKitKatWatch() [...] // Inlined contents

    public static boolean isAtLeastLollipop() [...] // Inlined contents

    public static boolean isAtLeastLollipopMR1() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeastN() [...] // 潜在的解密器

    public static boolean isAtLeastO() [...] // 潜在的解密器

    public static boolean isAtLeastP() [...] // 潜在的解密器

    public static boolean isAtLeastQ() [...] // 潜在的解密器

    public static boolean isAtLeastR() [...] // 潜在的解密器

    public static boolean isAtLeastS() [...] // 潜在的解密器

    public static boolean isAtLeastSv2() {
        return Build.VERSION.SDK_INT >= 0x20;
    }

    public static boolean isAtLeastT() [...] // 潜在的解密器

    // 去混淆评级： 低(30)
    public static boolean isAtLeastU() {
        return BuildCompat.isAtLeastU();
    }

    public static boolean isAtLeastV() {
        return PlatformVersion.isAtLeastU() ? BuildCompat.isAtLeastV() : false;
    }
}

