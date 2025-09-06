package androidx.window.layout;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/window/layout/WindowMetricsCalculator;", "it", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
final class WindowMetricsCalculator.Companion.decorator.1 extends Lambda implements Function1 {
    public static final WindowMetricsCalculator.Companion.decorator.1 INSTANCE;

    static {
        WindowMetricsCalculator.Companion.decorator.1.INSTANCE = new WindowMetricsCalculator.Companion.decorator.1();
    }

    WindowMetricsCalculator.Companion.decorator.1() {
        super(1);
    }

    public final WindowMetricsCalculator invoke(WindowMetricsCalculator windowMetricsCalculator0) {
        Intrinsics.checkNotNullParameter(windowMetricsCalculator0, "it");
        return windowMetricsCalculator0;
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((WindowMetricsCalculator)object0));
    }
}

