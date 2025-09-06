package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u001C\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A;\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"transaction", "T", "Landroid/database/sqlite/SQLiteDatabase;", "exclusive", "", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroid/database/sqlite/SQLiteDatabase;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SQLiteDatabaseKt {
    public static final Object transaction(SQLiteDatabase sQLiteDatabase0, boolean z, Function1 function10) {
        if(z) {
            sQLiteDatabase0.beginTransaction();
        }
        else {
            sQLiteDatabase0.beginTransactionNonExclusive();
        }
        try {
            Object object0 = function10.invoke(sQLiteDatabase0);
            sQLiteDatabase0.setTransactionSuccessful();
            return object0;
        }
        finally {
            sQLiteDatabase0.endTransaction();
        }
    }

    public static Object transaction$default(SQLiteDatabase sQLiteDatabase0, boolean z, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            z = true;
        }
        if(z) {
            sQLiteDatabase0.beginTransaction();
        }
        else {
            sQLiteDatabase0.beginTransactionNonExclusive();
        }
        try {
            Object object1 = function10.invoke(sQLiteDatabase0);
            sQLiteDatabase0.setTransactionSuccessful();
            return object1;
        }
        finally {
            sQLiteDatabase0.endTransaction();
        }
    }
}

