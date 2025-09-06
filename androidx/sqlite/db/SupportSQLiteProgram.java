package androidx.sqlite.db;

import java.io.Closeable;
import kotlin.Metadata;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&J\u0018\u0010\b\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\tH&J\u0018\u0010\n\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u000BH&J\u0010\u0010\f\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0018\u0010\r\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u000EH&J\b\u0010\u000F\u001A\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteProgram;", "Ljava/io/Closeable;", "bindBlob", "", "index", "", "value", "", "bindDouble", "", "bindLong", "", "bindNull", "bindString", "", "clearBindings", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SupportSQLiteProgram extends Closeable {
    void bindBlob(int arg1, byte[] arg2);

    void bindDouble(int arg1, double arg2);

    void bindLong(int arg1, long arg2);

    void bindNull(int arg1);

    void bindString(int arg1, String arg2);

    void clearBindings();
}

