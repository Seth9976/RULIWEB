package androidx.window.layout;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0010\u0010\u0006\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&¨\u0006\b"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculator;", "", "computeCurrentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "activity", "Landroid/app/Activity;", "computeMaximumWindowMetrics", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface WindowMetricsCalculator {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001A\u00020\u0005H\u0007J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0007J\b\u0010\u000B\u001A\u00020\bH\u0007R\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculator$Companion;", "", "()V", "decorator", "Lkotlin/Function1;", "Landroidx/window/layout/WindowMetricsCalculator;", "getOrCreate", "overrideDecorator", "", "overridingDecorator", "Landroidx/window/layout/WindowMetricsCalculatorDecorator;", "reset", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static Function1 decorator;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.decorator = WindowMetricsCalculator.Companion.decorator.1.INSTANCE;
        }

        @JvmStatic
        public final WindowMetricsCalculator getOrCreate() {
            return (WindowMetricsCalculator)Companion.decorator.invoke(WindowMetricsCalculatorCompat.INSTANCE);
        }

        @JvmStatic
        public final void overrideDecorator(WindowMetricsCalculatorDecorator windowMetricsCalculatorDecorator0) {
            Intrinsics.checkNotNullParameter(windowMetricsCalculatorDecorator0, "overridingDecorator");
            Companion.decorator = new Function1() {
                {
                    super(1, object0, WindowMetricsCalculatorDecorator.class, "decorate", "decorate(Landroidx/window/layout/WindowMetricsCalculator;)Landroidx/window/layout/WindowMetricsCalculator;", 0);
                }

                public final WindowMetricsCalculator invoke(WindowMetricsCalculator windowMetricsCalculator0) {
                    Intrinsics.checkNotNullParameter(windowMetricsCalculator0, "p0");
                    return ((WindowMetricsCalculatorDecorator)this.receiver).decorate(windowMetricsCalculator0);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((WindowMetricsCalculator)object0));
                }
            };
        }

        @JvmStatic
        public final void reset() {
            Companion.decorator = androidx.window.layout.WindowMetricsCalculator.Companion.reset.1.INSTANCE;

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/window/layout/WindowMetricsCalculator;", "it", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
            final class androidx.window.layout.WindowMetricsCalculator.Companion.reset.1 extends Lambda implements Function1 {
                public static final androidx.window.layout.WindowMetricsCalculator.Companion.reset.1 INSTANCE;

                static {
                    androidx.window.layout.WindowMetricsCalculator.Companion.reset.1.INSTANCE = new androidx.window.layout.WindowMetricsCalculator.Companion.reset.1();
                }

                androidx.window.layout.WindowMetricsCalculator.Companion.reset.1() {
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

        }
    }

    public static final Companion Companion;

    static {
        WindowMetricsCalculator.Companion = Companion.$$INSTANCE;
    }

    WindowMetrics computeCurrentWindowMetrics(Activity arg1);

    WindowMetrics computeMaximumWindowMetrics(Activity arg1);
}

