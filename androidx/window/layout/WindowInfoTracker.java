package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.window.extensions.layout.WindowLayoutComponent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0016\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001A\u00020\u0006H&¨\u0006\b"}, d2 = {"Landroidx/window/layout/WindowInfoTracker;", "", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/layout/WindowLayoutInfo;", "activity", "Landroid/app/Activity;", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface WindowInfoTracker {
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0007J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\bH\u0007J\b\u0010\u0010\u001A\u00020\u000EH\u0007J\u0015\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u000B\u001A\u00020\fH\u0000¢\u0006\u0002\b\u0013R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/layout/WindowInfoTracker$Companion;", "", "()V", "DEBUG", "", "TAG", "", "decorator", "Landroidx/window/layout/WindowInfoTrackerDecorator;", "getOrCreate", "Landroidx/window/layout/WindowInfoTracker;", "context", "Landroid/content/Context;", "overrideDecorator", "", "overridingDecorator", "reset", "windowBackend", "Landroidx/window/layout/WindowBackend;", "windowBackend$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final boolean DEBUG;
        private static final String TAG;
        private static WindowInfoTrackerDecorator decorator;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.TAG = Reflection.getOrCreateKotlinClass(WindowInfoTracker.class).getSimpleName();
            Companion.decorator = EmptyDecorator.INSTANCE;
        }

        @JvmStatic
        public final WindowInfoTracker getOrCreate(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            WindowBackend windowBackend0 = this.windowBackend$window_release(context0);
            WindowInfoTrackerImpl windowInfoTrackerImpl0 = new WindowInfoTrackerImpl(WindowMetricsCalculatorCompat.INSTANCE, windowBackend0);
            return Companion.decorator.decorate(windowInfoTrackerImpl0);
        }

        @JvmStatic
        public final void overrideDecorator(WindowInfoTrackerDecorator windowInfoTrackerDecorator0) {
            Intrinsics.checkNotNullParameter(windowInfoTrackerDecorator0, "overridingDecorator");
            Companion.decorator = windowInfoTrackerDecorator0;
        }

        @JvmStatic
        public final void reset() {
            Companion.decorator = EmptyDecorator.INSTANCE;
        }

        public final WindowBackend windowBackend$window_release(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            ExtensionWindowLayoutInfoBackend extensionWindowLayoutInfoBackend0 = null;
            try {
                WindowLayoutComponent windowLayoutComponent0 = SafeWindowLayoutComponentProvider.INSTANCE.getWindowLayoutComponent();
                if(windowLayoutComponent0 != null) {
                    extensionWindowLayoutInfoBackend0 = new ExtensionWindowLayoutInfoBackend(windowLayoutComponent0);
                }
            }
            catch(Throwable unused_ex) {
                if(Companion.DEBUG) {
                    Log.d(Companion.TAG, "Failed to load WindowExtensions");
                }
            }
            return extensionWindowLayoutInfoBackend0 == null ? SidecarWindowBackend.Companion.getInstance(context0) : extensionWindowLayoutInfoBackend0;
        }
    }

    public static final Companion Companion;

    static {
        WindowInfoTracker.Companion = Companion.$$INSTANCE;
    }

    Flow windowLayoutInfo(Activity arg1);
}

