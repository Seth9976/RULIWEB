package androidx.window.layout;

import kotlin.jvm.JvmStatic;

public final class WindowMetricsCalculator.-CC {
    static {
    }

    @JvmStatic
    public static WindowMetricsCalculator getOrCreate() {
        return WindowMetricsCalculator.Companion.getOrCreate();
    }

    @JvmStatic
    public static void overrideDecorator(WindowMetricsCalculatorDecorator windowMetricsCalculatorDecorator0) {
        WindowMetricsCalculator.Companion.overrideDecorator(windowMetricsCalculatorDecorator0);
    }

    @JvmStatic
    public static void reset() {
        WindowMetricsCalculator.Companion.reset();
    }
}

