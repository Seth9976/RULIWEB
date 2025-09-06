package androidx.window.layout;

import android.content.Context;
import kotlin.jvm.JvmStatic;

public final class WindowInfoTracker.-CC {
    static {
    }

    @JvmStatic
    public static WindowInfoTracker getOrCreate(Context context0) {
        return WindowInfoTracker.Companion.getOrCreate(context0);
    }

    @JvmStatic
    public static void overrideDecorator(WindowInfoTrackerDecorator windowInfoTrackerDecorator0) {
        WindowInfoTracker.Companion.overrideDecorator(windowInfoTrackerDecorator0);
    }

    @JvmStatic
    public static void reset() {
        WindowInfoTracker.Companion.reset();
    }
}

