package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build.VERSION;

public final class CursorWindowCompat {
    static class Api28Impl {
        static CursorWindow createCursorWindow(String s, long v) {
            return new CursorWindow(s, v);
        }
    }

    public static CursorWindow create(String s, long v) {
        return Build.VERSION.SDK_INT < 28 ? new CursorWindow(s) : Api28Impl.createCursorWindow(s, v);
    }
}

