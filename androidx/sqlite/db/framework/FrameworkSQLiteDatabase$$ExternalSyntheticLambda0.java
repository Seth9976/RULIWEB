package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

public final class FrameworkSQLiteDatabase..ExternalSyntheticLambda0 implements SQLiteDatabase.CursorFactory {
    public final SupportSQLiteQuery f$0;

    public FrameworkSQLiteDatabase..ExternalSyntheticLambda0(SupportSQLiteQuery supportSQLiteQuery0) {
        this.f$0 = supportSQLiteQuery0;
    }

    @Override  // android.database.sqlite.SQLiteDatabase$CursorFactory
    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) {
        return FrameworkSQLiteDatabase.$r8$lambda$mxkMCHH54EI3Mb052ZTb9xnn0ow(this.f$0, sQLiteDatabase0, sQLiteCursorDriver0, s, sQLiteQuery0);
    }
}

