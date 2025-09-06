package androidx.sqlite.db.framework;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;

public final class FrameworkSQLiteOpenHelper.OpenHelper..ExternalSyntheticLambda0 implements DatabaseErrorHandler {
    public final Callback f$0;
    public final DBRefHolder f$1;

    public FrameworkSQLiteOpenHelper.OpenHelper..ExternalSyntheticLambda0(Callback supportSQLiteOpenHelper$Callback0, DBRefHolder frameworkSQLiteOpenHelper$DBRefHolder0) {
        this.f$0 = supportSQLiteOpenHelper$Callback0;
        this.f$1 = frameworkSQLiteOpenHelper$DBRefHolder0;
    }

    @Override  // android.database.DatabaseErrorHandler
    public final void onCorruption(SQLiteDatabase sQLiteDatabase0) {
        OpenHelper.$r8$lambda$HkATHkNnDWiWKR94KFirzR7ST-8(this.f$0, this.f$1, sQLiteDatabase0);
    }
}

