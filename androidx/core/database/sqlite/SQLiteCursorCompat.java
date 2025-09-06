package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build.VERSION;

public final class SQLiteCursorCompat {
    static class Api28Impl {
        static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor0, boolean z) {
            sQLiteCursor0.setFillWindowForwardOnly(z);
        }
    }

    public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor0, boolean z) {
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setFillWindowForwardOnly(sQLiteCursor0, z);
        }
    }
}

