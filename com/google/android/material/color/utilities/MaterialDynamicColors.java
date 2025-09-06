package com.google.android.material.color.utilities;

public final class MaterialDynamicColors {
    public DynamicColor background() {
        return new DynamicColor("background", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 6.0 : 98.0), true, null, null, null, null);
    }

    public DynamicColor controlActivated() {
        return DynamicColor.fromPalette("control_activated", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 30.0 : 90.0));
    }

    public DynamicColor controlHighlight() {
        return new DynamicColor("control_highlight", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 100.0 : 0.0), false, null, null, null, null, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 0.2 : 0.12));
    }

    public DynamicColor controlNormal() {
        return DynamicColor.fromPalette("control_normal", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 80.0 : 30.0));
    }

    public DynamicColor error() {
        return new DynamicColor("error", (DynamicScheme dynamicScheme0) -> dynamicScheme0.errorPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 80.0 : 40.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(3.0, 4.5, 7.0, 11.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.errorContainer(), this.error(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor errorContainer() {
        return new DynamicColor("error_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.errorPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 30.0 : 90.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.errorContainer(), this.error(), 15.0, TonePolarity.NEARER, false));
    }

    static double findDesiredChromaByTone(double f, double f1, double f2, boolean z) {
        Hct hct0 = Hct.from(f, f1, f2);
        if(hct0.getChroma() < f1) {
            for(double f3 = hct0.getChroma(); hct0.getChroma() < f1; f3 = Math.max(f3, hct1.getChroma())) {
                f2 += (z ? -1.0 : 1.0);
                Hct hct1 = Hct.from(f, f1, f2);
                if(f3 > hct1.getChroma() || Math.abs(hct1.getChroma() - f1) < 0.4) {
                    return f2;
                }
                if(Math.abs(hct1.getChroma() - f1) < Math.abs(hct0.getChroma() - f1)) {
                    hct0 = hct1;
                }
            }
        }
        return f2;
    }

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    public DynamicColor highestSurface(DynamicScheme dynamicScheme0) [...]

    public DynamicColor inverseOnSurface() {
        return new DynamicColor("inverse_on_surface", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 20.0 : 95.0), false, (DynamicScheme dynamicScheme0) -> this.inverseSurface(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor inversePrimary() {
        return new DynamicColor("inverse_primary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 40.0 : 80.0), false, (DynamicScheme dynamicScheme0) -> this.inverseSurface(), null, new ContrastCurve(3.0, 4.5, 7.0, 11.0), null);
    }

    public DynamicColor inverseSurface() {
        return new DynamicColor("inverse_surface", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 90.0 : 20.0), false, null, null, null, null);
    }

    private static boolean isFidelity(DynamicScheme dynamicScheme0) {
        return dynamicScheme0.variant == Variant.FIDELITY || dynamicScheme0.variant == Variant.CONTENT;
    }

    private static boolean isMonochrome(DynamicScheme dynamicScheme0) {
        return dynamicScheme0.variant == Variant.MONOCHROME;
    }

    // 检测为 Lambda 实现
    static TonalPalette lambda$background$10(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$background$11(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$controlActivated$145(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$controlActivated$146(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$controlHighlight$149(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$controlHighlight$150(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$controlHighlight$151(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$controlNormal$147(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$controlNormal$148(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$error$91(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$error$92(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$error$93$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$errorContainer$97(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$errorContainer$98(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$errorContainer$99$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$inverseOnSurface$39(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$inverseOnSurface$40(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$inverseOnSurface$41$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$inversePrimary$64(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$inversePrimary$65(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$inversePrimary$66$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$inverseSurface$37(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$inverseSurface$38(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$neutralPaletteKeyColor$6(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$neutralPaletteKeyColor$7(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$neutralVariantPaletteKeyColor$8(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$neutralVariantPaletteKeyColor$9(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onBackground$12(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onBackground$13(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onBackground$14$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onError$94(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onError$95(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onError$96$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onErrorContainer$100(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onErrorContainer$101(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onErrorContainer$102$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onPrimary$55(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$onPrimary$56(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onPrimary$57$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onPrimaryContainer$61(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    Double lambda$onPrimaryContainer$62$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onPrimaryContainer$63$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onPrimaryFixed$109(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onPrimaryFixed$110(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onPrimaryFixed$111$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onPrimaryFixed$112$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onPrimaryFixedVariant$113(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onPrimaryFixedVariant$114(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onPrimaryFixedVariant$115$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onPrimaryFixedVariant$116$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onSecondary$70(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$onSecondary$71(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onSecondary$72$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onSecondaryContainer$76(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    Double lambda$onSecondaryContainer$77$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onSecondaryContainer$78$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onSecondaryFixed$123(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$onSecondaryFixed$124(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onSecondaryFixed$125$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onSecondaryFixed$126$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onSecondaryFixedVariant$127(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onSecondaryFixedVariant$128(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onSecondaryFixedVariant$129$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onSecondaryFixedVariant$130$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onSurface$31(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onSurface$32(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onSurfaceVariant$35(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onSurfaceVariant$36(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onTertiary$82(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$onTertiary$83(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onTertiary$84$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onTertiaryContainer$88(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    Double lambda$onTertiaryContainer$89$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onTertiaryContainer$90$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onTertiaryFixed$137(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onTertiaryFixed$138(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onTertiaryFixed$139$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onTertiaryFixed$140$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$onTertiaryFixedVariant$141(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$onTertiaryFixedVariant$142(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onTertiaryFixedVariant$143$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    DynamicColor lambda$onTertiaryFixedVariant$144$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$outline$42(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$outline$43(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$outlineVariant$44(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$outlineVariant$45(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$primary$52(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$primary$53(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$primary$54$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$primaryContainer$58(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$primaryContainer$59(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$primaryContainer$60$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$primaryFixed$103(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$primaryFixed$104(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$primaryFixed$105$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$primaryFixedDim$106(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$primaryFixedDim$107(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$primaryFixedDim$108$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$primaryPaletteKeyColor$0(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$primaryPaletteKeyColor$1(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$scrim$48(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$scrim$49(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$secondary$67(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$secondary$68(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$secondary$69$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$secondaryContainer$73(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$secondaryContainer$74(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$secondaryContainer$75$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$secondaryFixed$117(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$secondaryFixed$118(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$secondaryFixed$119$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$secondaryFixedDim$120(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$secondaryFixedDim$121(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$secondaryFixedDim$122$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$secondaryPaletteKeyColor$2(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$secondaryPaletteKeyColor$3(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$shadow$46(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$shadow$47(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surface$15(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surface$16(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceBright$19(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceBright$20(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceContainer$25(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceContainer$26(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceContainerHigh$27(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceContainerHigh$28(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceContainerHighest$29(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceContainerHighest$30(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceContainerLow$23(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceContainerLow$24(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceContainerLowest$21(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceContainerLowest$22(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceDim$17(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceDim$18(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceTint$50(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceTint$51(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$surfaceVariant$33(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$surfaceVariant$34(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$tertiary$79(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$tertiary$80(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$tertiary$81$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$tertiaryContainer$85(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$tertiaryContainer$86(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$tertiaryContainer$87$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$tertiaryFixed$131(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$tertiaryFixed$132(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$tertiaryFixed$133$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$tertiaryFixedDim$134(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$tertiaryFixedDim$135(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    ToneDeltaPair lambda$tertiaryFixedDim$136$com-google-android-material-color-utilities-MaterialDynamicColors(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$tertiaryPaletteKeyColor$4(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static Double lambda$tertiaryPaletteKeyColor$5(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$textHintInverse$160(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$textHintInverse$161(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$textPrimaryInverse$152(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$textPrimaryInverse$153(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$textPrimaryInverseDisableOnly$156(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$textPrimaryInverseDisableOnly$157(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$textSecondaryAndTertiaryInverse$154(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$textSecondaryAndTertiaryInverse$155(DynamicScheme dynamicScheme0) [...]

    // 检测为 Lambda 实现
    static TonalPalette lambda$textSecondaryAndTertiaryInverseDisabled$158(DynamicScheme dynamicScheme0) [...]

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static Double lambda$textSecondaryAndTertiaryInverseDisabled$159(DynamicScheme dynamicScheme0) [...]

    public DynamicColor neutralPaletteKeyColor() {
        return DynamicColor.fromPalette("neutral_palette_key_color", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette.getKeyColor().getTone());
    }

    public DynamicColor neutralVariantPaletteKeyColor() {
        return DynamicColor.fromPalette("neutral_variant_palette_key_color", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette.getKeyColor().getTone());
    }

    public DynamicColor onBackground() {
        return new DynamicColor("on_background", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 90.0 : 10.0), false, (DynamicScheme dynamicScheme0) -> this.background(), null, new ContrastCurve(3.0, 3.0, 4.5, 7.0), null);
    }

    public DynamicColor onError() {
        return new DynamicColor("on_error", (DynamicScheme dynamicScheme0) -> dynamicScheme0.errorPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 20.0 : 100.0), false, (DynamicScheme dynamicScheme0) -> this.error(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onErrorContainer() {
        return new DynamicColor("on_error_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.errorPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 90.0 : 10.0), false, (DynamicScheme dynamicScheme0) -> this.errorContainer(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onPrimary() {
        return new DynamicColor("on_primary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 10.0 : 90.0;
            }
            return dynamicScheme0.isDark ? 20.0 : 100.0;
        }, false, (DynamicScheme dynamicScheme0) -> this.primary(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onPrimaryContainer() {
        return new DynamicColor("on_primary_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isFidelity(dynamicScheme0)) {
                return DynamicColor.foregroundTone(((double)(((Double)this.primaryContainer().tone.apply(dynamicScheme0)))), 4.5);
            }
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 0.0 : 100.0;
            }
            return dynamicScheme0.isDark ? 90.0 : 10.0;
        }, false, (DynamicScheme dynamicScheme0) -> this.primaryContainer(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onPrimaryFixed() {
        return new DynamicColor("on_primary_fixed", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 100.0 : 10.0), false, (DynamicScheme dynamicScheme0) -> this.primaryFixedDim(), (DynamicScheme dynamicScheme0) -> this.primaryFixed(), new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onPrimaryFixedVariant() {
        return new DynamicColor("on_primary_fixed_variant", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 90.0 : 30.0), false, (DynamicScheme dynamicScheme0) -> this.primaryFixedDim(), (DynamicScheme dynamicScheme0) -> this.primaryFixed(), new ContrastCurve(3.0, 4.5, 7.0, 11.0), null);
    }

    public DynamicColor onSecondary() {
        return new DynamicColor("on_secondary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> {
            double f = 100.0;
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                if(dynamicScheme0.isDark) {
                    f = 10.0;
                }
                return f;
            }
            if(dynamicScheme0.isDark) {
                f = 20.0;
            }
            return f;
        }, false, (DynamicScheme dynamicScheme0) -> this.secondary(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onSecondaryContainer() {
        return new DynamicColor("on_secondary_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(!MaterialDynamicColors.isFidelity(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 90.0 : 10.0;
            }
            return DynamicColor.foregroundTone(((double)(((Double)this.secondaryContainer().tone.apply(dynamicScheme0)))), 4.5);
        }, false, (DynamicScheme dynamicScheme0) -> this.secondaryContainer(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onSecondaryFixed() {
        return new DynamicColor("on_secondary_fixed", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> 10.0, false, (DynamicScheme dynamicScheme0) -> this.secondaryFixedDim(), (DynamicScheme dynamicScheme0) -> this.secondaryFixed(), new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onSecondaryFixedVariant() {
        return new DynamicColor("on_secondary_fixed_variant", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 25.0 : 30.0), false, (DynamicScheme dynamicScheme0) -> this.secondaryFixedDim(), (DynamicScheme dynamicScheme0) -> this.secondaryFixed(), new ContrastCurve(3.0, 4.5, 7.0, 11.0), null);
    }

    public DynamicColor onSurface() {
        return new DynamicColor("on_surface", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 90.0 : 10.0), false, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onSurfaceVariant() {
        return new DynamicColor("on_surface_variant", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 80.0 : 30.0), false, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(3.0, 4.5, 7.0, 11.0), null);
    }

    public DynamicColor onTertiary() {
        return new DynamicColor("on_tertiary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 10.0 : 90.0;
            }
            return dynamicScheme0.isDark ? 20.0 : 100.0;
        }, false, (DynamicScheme dynamicScheme0) -> this.tertiary(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onTertiaryContainer() {
        return new DynamicColor("on_tertiary_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 0.0 : 100.0;
            }
            if(!MaterialDynamicColors.isFidelity(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 90.0 : 10.0;
            }
            return DynamicColor.foregroundTone(((double)(((Double)this.tertiaryContainer().tone.apply(dynamicScheme0)))), 4.5);
        }, false, (DynamicScheme dynamicScheme0) -> this.tertiaryContainer(), null, new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onTertiaryFixed() {
        return new DynamicColor("on_tertiary_fixed", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 100.0 : 10.0), false, (DynamicScheme dynamicScheme0) -> this.tertiaryFixedDim(), (DynamicScheme dynamicScheme0) -> this.tertiaryFixed(), new ContrastCurve(4.5, 7.0, 11.0, 21.0), null);
    }

    public DynamicColor onTertiaryFixedVariant() {
        return new DynamicColor("on_tertiary_fixed_variant", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 90.0 : 30.0), false, (DynamicScheme dynamicScheme0) -> this.tertiaryFixedDim(), (DynamicScheme dynamicScheme0) -> this.tertiaryFixed(), new ContrastCurve(3.0, 4.5, 7.0, 11.0), null);
    }

    public DynamicColor outline() {
        return new DynamicColor("outline", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 60.0 : 50.0), false, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.5, 3.0, 4.5, 7.0), null);
    }

    public DynamicColor outlineVariant() {
        return new DynamicColor("outline_variant", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 30.0 : 80.0), false, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), null);
    }

    static double performAlbers(Hct hct0, DynamicScheme dynamicScheme0) {
        Hct hct1 = hct0.inViewingConditions(MaterialDynamicColors.viewingConditionsForAlbers(dynamicScheme0));
        return !DynamicColor.tonePrefersLightForeground(hct0.getTone()) || DynamicColor.toneAllowsLightForeground(hct1.getTone()) ? DynamicColor.enableLightForeground(hct1.getTone()) : DynamicColor.enableLightForeground(hct0.getTone());
    }

    public DynamicColor primary() {
        return new DynamicColor("primary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 100.0 : 0.0;
            }
            return dynamicScheme0.isDark ? 80.0 : 40.0;
        }, true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(3.0, 4.5, 7.0, 11.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.primaryContainer(), this.primary(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor primaryContainer() {
        return new DynamicColor("primary_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isFidelity(dynamicScheme0)) {
                return MaterialDynamicColors.performAlbers(dynamicScheme0.sourceColorHct, dynamicScheme0);
            }
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 85.0 : 25.0;
            }
            return dynamicScheme0.isDark ? 30.0 : 90.0;
        }, true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.primaryContainer(), this.primary(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor primaryFixed() {
        return new DynamicColor("primary_fixed", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 40.0 : 90.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.primaryFixed(), this.primaryFixedDim(), 10.0, TonePolarity.LIGHTER, true));
    }

    public DynamicColor primaryFixedDim() {
        return new DynamicColor("primary_fixed_dim", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 30.0 : 80.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.primaryFixed(), this.primaryFixedDim(), 10.0, TonePolarity.LIGHTER, true));
    }

    public DynamicColor primaryPaletteKeyColor() {
        return DynamicColor.fromPalette("primary_palette_key_color", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette.getKeyColor().getTone());
    }

    public DynamicColor scrim() {
        return new DynamicColor("scrim", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> 0.0, false, null, null, null, null);
    }

    public DynamicColor secondary() {
        return new DynamicColor("secondary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 80.0 : 40.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(3.0, 4.5, 7.0, 11.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.secondaryContainer(), this.secondary(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor secondaryContainer() {
        return new DynamicColor("secondary_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> {
            double f = 30.0;
            double f1 = dynamicScheme0.isDark ? 30.0 : 90.0;
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                if(!dynamicScheme0.isDark) {
                    f = 85.0;
                }
                return f;
            }
            if(!MaterialDynamicColors.isFidelity(dynamicScheme0)) {
                return f1;
            }
            double f2 = MaterialDynamicColors.findDesiredChromaByTone(dynamicScheme0.secondaryPalette.getHue(), dynamicScheme0.secondaryPalette.getChroma(), f1, !dynamicScheme0.isDark);
            return MaterialDynamicColors.performAlbers(dynamicScheme0.secondaryPalette.getHct(f2), dynamicScheme0);
        }, true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.secondaryContainer(), this.secondary(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor secondaryFixed() {
        return new DynamicColor("secondary_fixed", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 80.0 : 90.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.secondaryFixed(), this.secondaryFixedDim(), 10.0, TonePolarity.LIGHTER, true));
    }

    public DynamicColor secondaryFixedDim() {
        return new DynamicColor("secondary_fixed_dim", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 70.0 : 80.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.secondaryFixed(), this.secondaryFixedDim(), 10.0, TonePolarity.LIGHTER, true));
    }

    public DynamicColor secondaryPaletteKeyColor() {
        return DynamicColor.fromPalette("secondary_palette_key_color", (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette, (DynamicScheme dynamicScheme0) -> dynamicScheme0.secondaryPalette.getKeyColor().getTone());
    }

    public DynamicColor shadow() {
        return new DynamicColor("shadow", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> 0.0, false, null, null, null, null);
    }

    public DynamicColor surface() {
        return new DynamicColor("surface", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 6.0 : 98.0), true, null, null, null, null);
    }

    public DynamicColor surfaceBright() {
        return new DynamicColor("surface_bright", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 24.0 : 98.0), true, null, null, null, null);
    }

    public DynamicColor surfaceContainer() {
        return new DynamicColor("surface_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 12.0 : 94.0), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerHigh() {
        return new DynamicColor("surface_container_high", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 17.0 : 92.0), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerHighest() {
        return new DynamicColor("surface_container_highest", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 22.0 : 90.0), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerLow() {
        return new DynamicColor("surface_container_low", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 10.0 : 96.0), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerLowest() {
        return new DynamicColor("surface_container_lowest", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 4.0 : 100.0), true, null, null, null, null);
    }

    public DynamicColor surfaceDim() {
        return new DynamicColor("surface_dim", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 6.0 : 87.0), true, null, null, null, null);
    }

    public DynamicColor surfaceTint() {
        return new DynamicColor("surface_tint", (DynamicScheme dynamicScheme0) -> dynamicScheme0.primaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 80.0 : 40.0), true, null, null, null, null);
    }

    public DynamicColor surfaceVariant() {
        return new DynamicColor("surface_variant", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 30.0 : 90.0), true, null, null, null, null);
    }

    public DynamicColor tertiary() {
        return new DynamicColor("tertiary", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 90.0 : 25.0;
            }
            return dynamicScheme0.isDark ? 80.0 : 40.0;
        }, true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(3.0, 4.5, 7.0, 11.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.tertiaryContainer(), this.tertiary(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor tertiaryContainer() {
        return new DynamicColor("tertiary_container", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> {
            if(MaterialDynamicColors.isMonochrome(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 60.0 : 49.0;
            }
            if(!MaterialDynamicColors.isFidelity(dynamicScheme0)) {
                return dynamicScheme0.isDark ? 30.0 : 90.0;
            }
            double f = MaterialDynamicColors.performAlbers(dynamicScheme0.tertiaryPalette.getHct(dynamicScheme0.sourceColorHct.getTone()), dynamicScheme0);
            return DislikeAnalyzer.fixIfDisliked(dynamicScheme0.tertiaryPalette.getHct(f)).getTone();
        }, true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.tertiaryContainer(), this.tertiary(), 15.0, TonePolarity.NEARER, false));
    }

    public DynamicColor tertiaryFixed() {
        return new DynamicColor("tertiary_fixed", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 40.0 : 90.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.tertiaryFixed(), this.tertiaryFixedDim(), 10.0, TonePolarity.LIGHTER, true));
    }

    public DynamicColor tertiaryFixedDim() {
        return new DynamicColor("tertiary_fixed_dim", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (MaterialDynamicColors.isMonochrome(dynamicScheme0) ? 30.0 : 80.0), true, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? this.surfaceBright() : this.surfaceDim()), null, new ContrastCurve(1.0, 1.0, 3.0, 7.0), (DynamicScheme dynamicScheme0) -> new ToneDeltaPair(this.tertiaryFixed(), this.tertiaryFixedDim(), 10.0, TonePolarity.LIGHTER, true));
    }

    public DynamicColor tertiaryPaletteKeyColor() {
        return DynamicColor.fromPalette("tertiary_palette_key_color", (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette, (DynamicScheme dynamicScheme0) -> dynamicScheme0.tertiaryPalette.getKeyColor().getTone());
    }

    public DynamicColor textHintInverse() {
        return DynamicColor.fromPalette("text_hint_inverse", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 10.0 : 90.0));
    }

    public DynamicColor textPrimaryInverse() {
        return DynamicColor.fromPalette("text_primary_inverse", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 10.0 : 90.0));
    }

    public DynamicColor textPrimaryInverseDisableOnly() {
        return DynamicColor.fromPalette("text_primary_inverse_disable_only", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 10.0 : 90.0));
    }

    public DynamicColor textSecondaryAndTertiaryInverse() {
        return DynamicColor.fromPalette("text_secondary_and_tertiary_inverse", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralVariantPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 30.0 : 80.0));
    }

    public DynamicColor textSecondaryAndTertiaryInverseDisabled() {
        return DynamicColor.fromPalette("text_secondary_and_tertiary_inverse_disabled", (DynamicScheme dynamicScheme0) -> dynamicScheme0.neutralPalette, (DynamicScheme dynamicScheme0) -> // 去混淆评级： 低(20)
        (dynamicScheme0.isDark ? 10.0 : 90.0));
    }

    // 去混淆评级： 低(20)
    private static ViewingConditions viewingConditionsForAlbers(DynamicScheme dynamicScheme0) {
        return dynamicScheme0.isDark ? ViewingConditions.defaultWithBackgroundLstar(30.0) : ViewingConditions.defaultWithBackgroundLstar(80.0);
    }
}

