package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteProgram;
import androidx.sqlite.db.SupportSQLiteProgram;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\fH\u0016J\u0018\u0010\r\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u000EH\u0016J\u0010\u0010\u000F\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0011H\u0016J\b\u0010\u0012\u001A\u00020\u0006H\u0016J\b\u0010\u0013\u001A\u00020\u0006H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteProgram;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "delegate", "Landroid/database/sqlite/SQLiteProgram;", "(Landroid/database/sqlite/SQLiteProgram;)V", "bindBlob", "", "index", "", "value", "", "bindDouble", "", "bindLong", "", "bindNull", "bindString", "", "clearBindings", "close", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class FrameworkSQLiteProgram implements SupportSQLiteProgram, AutoCloseable {
    private final SQLiteProgram delegate;

    public FrameworkSQLiteProgram(SQLiteProgram sQLiteProgram0) {
        Intrinsics.checkNotNullParameter(sQLiteProgram0, "delegate");
        super();
        this.delegate = sQLiteProgram0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int v, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "value");
        this.delegate.bindBlob(v, arr_b);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int v, double f) {
        this.delegate.bindDouble(v, f);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int v, long v1) {
        this.delegate.bindLong(v, v1);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int v) {
        this.delegate.bindNull(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int v, String s) {
        Intrinsics.checkNotNullParameter(s, "value");
        this.delegate.bindString(v, s);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        this.delegate.clearBindings();
    }

    @Override
    public void close() {
        this.delegate.close();
    }
}

