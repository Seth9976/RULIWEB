package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import kotlin.jvm.functions.Function4;

public final class FrameworkSQLiteDatabase..ExternalSyntheticLambda1 implements SQLiteDatabase.CursorFactory {
    public final Function4 f$0;

    public FrameworkSQLiteDatabase..ExternalSyntheticLambda1(Function4 function40) {
        this.f$0 = function40;
    }

    @Override  // android.database.sqlite.SQLiteDatabase$CursorFactory
    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) {
        return FrameworkSQLiteDatabase.$r8$lambda$nsMcCVLiqxDRAAOcFblmRGCM9fk(this.f$0, sQLiteDatabase0, sQLiteCursorDriver0, s, sQLiteQuery0);
    }
}

