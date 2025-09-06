package com.google.android.material.resources;

@Deprecated
public class TextAppearanceConfig {
    private static boolean shouldLoadFontSynchronously;

    public static void setShouldLoadFontSynchronously(boolean z) {
        TextAppearanceConfig.shouldLoadFontSynchronously = z;
    }

    public static boolean shouldLoadFontSynchronously() [...] // 潜在的解密器
}

