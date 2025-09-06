package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\b\u0010\u0000\u001A\u0004\u0018\u00010\u0001\u001A\u000E\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\u0006\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\b\u001A\u0010\u0010\t\u001A\u00020\b2\u0006\u0010\n\u001A\u00020\u000BH\u0002\u001A \u0010\f\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H\u0007\u001A(\u0010\f\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0001\u001A\u000E\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016¨\u0006\u0017"}, d2 = {"createCancellationSignal", "Landroid/os/CancellationSignal;", "dropFtsSyncTriggers", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "foreignKeyCheck", "tableName", "", "processForeignKeyCheckFailure", "cursor", "Landroid/database/Cursor;", "query", "Landroidx/room/RoomDatabase;", "sqLiteQuery", "Landroidx/sqlite/db/SupportSQLiteQuery;", "maybeCopy", "", "signal", "readVersion", "", "databaseFile", "Ljava/io/File;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DBUtil {
    public static final CancellationSignal createCancellationSignal() {
        return Api16Impl.createCancellationSignal();
    }

    public static final void dropFtsSyncTriggers(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        List list0 = CollectionsKt.createListBuilder();
        Closeable closeable0 = supportSQLiteDatabase0.query("SELECT name FROM sqlite_master WHERE type = \'trigger\'");
        while(true) {
            try {
                if(!((Cursor)closeable0).moveToNext()) {
                    break;
                }
                list0.add(((Cursor)closeable0).getString(0));
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
        }
        CloseableKt.closeFinally(closeable0, null);
        for(Object object0: CollectionsKt.build(list0)) {
            String s = (String)object0;
            Intrinsics.checkNotNullExpressionValue(s, "triggerName");
            if(StringsKt.startsWith$default(s, "room_fts_content_sync_", false, 2, null)) {
                supportSQLiteDatabase0.execSQL("DROP TRIGGER IF EXISTS " + s);
            }
        }
    }

    public static final void foreignKeyCheck(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        Intrinsics.checkNotNullParameter(s, "tableName");
        Closeable closeable0 = supportSQLiteDatabase0.query("PRAGMA foreign_key_check(`" + s + "`)");
        try {
            if(((Cursor)closeable0).getCount() > 0) {
                throw new SQLiteConstraintException(DBUtil.processForeignKeyCheckFailure(((Cursor)closeable0)));
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    private static final String processForeignKeyCheckFailure(Cursor cursor0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = cursor0.getCount();
        Map map0 = new LinkedHashMap();
        while(cursor0.moveToNext()) {
            if(cursor0.isFirst()) {
                stringBuilder0.append("Foreign key violation(s) detected in \'");
                stringBuilder0.append(cursor0.getString(0));
                stringBuilder0.append("\'.\n");
            }
            String s = cursor0.getString(3);
            if(!map0.containsKey(s)) {
                Intrinsics.checkNotNullExpressionValue(s, "constraintIndex");
                String s1 = cursor0.getString(2);
                Intrinsics.checkNotNullExpressionValue(s1, "cursor.getString(2)");
                map0.put(s, s1);
            }
        }
        stringBuilder0.append("Number of different violations discovered: ");
        stringBuilder0.append(map0.keySet().size());
        stringBuilder0.append("\nNumber of rows in violation: ");
        stringBuilder0.append(v);
        stringBuilder0.append("\nViolation(s) detected in the following constraint(s):\n");
        for(Object object0: map0.entrySet()) {
            String s2 = (String)((Map.Entry)object0).getKey();
            String s3 = (String)((Map.Entry)object0).getValue();
            stringBuilder0.append("\tParent Table = ");
            stringBuilder0.append(s3);
            stringBuilder0.append(", Foreign Key Constraint Index = ");
            stringBuilder0.append(s2);
            stringBuilder0.append("\n");
        }
        String s4 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s4, "StringBuilder().apply(builderAction).toString()");
        return s4;
    }

    @Deprecated(message = "This is only used in the generated code and shouldn\'t be called directly.")
    public static final Cursor query(RoomDatabase roomDatabase0, SupportSQLiteQuery supportSQLiteQuery0, boolean z) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "db");
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "sqLiteQuery");
        return DBUtil.query(roomDatabase0, supportSQLiteQuery0, z, null);
    }

    public static final Cursor query(RoomDatabase roomDatabase0, SupportSQLiteQuery supportSQLiteQuery0, boolean z, CancellationSignal cancellationSignal0) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "db");
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "sqLiteQuery");
        Cursor cursor0 = roomDatabase0.query(supportSQLiteQuery0, cancellationSignal0);
        if(z && cursor0 instanceof AbstractWindowedCursor) {
            int v = ((AbstractWindowedCursor)cursor0).getCount();
            int v1 = ((AbstractWindowedCursor)cursor0).hasWindow() ? ((AbstractWindowedCursor)cursor0).getWindow().getNumRows() : v;
            return Build.VERSION.SDK_INT >= 23 && v1 >= v ? cursor0 : CursorUtil.copyAndClose(cursor0);
        }
        return cursor0;
    }

    public static final int readVersion(File file0) throws IOException {
        int v;
        Intrinsics.checkNotNullParameter(file0, "databaseFile");
        Closeable closeable0 = new FileInputStream(file0).getChannel();
        try {
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(4);
            ((FileChannel)closeable0).tryLock(60L, 4L, true);
            ((FileChannel)closeable0).position(60L);
            if(((FileChannel)closeable0).read(byteBuffer0) != 4) {
                throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
            }
            byteBuffer0.rewind();
            v = byteBuffer0.getInt();
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return v;
    }
}

