package androidx.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import java.io.Closeable;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\b\u0010!\u001A\u00020\"H&J\b\u0010#\u001A\u00020\"H&J\u0010\u0010$\u001A\u00020\"2\u0006\u0010%\u001A\u00020&H&J\u0010\u0010\'\u001A\u00020\"2\u0006\u0010%\u001A\u00020&H&J\u0010\u0010(\u001A\u00020)2\u0006\u0010*\u001A\u00020\u0005H&J3\u0010+\u001A\u00020\u001C2\u0006\u0010,\u001A\u00020\u00052\b\u0010-\u001A\u0004\u0018\u00010\u00052\u0012\u0010.\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000100\u0018\u00010/H&\u00A2\u0006\u0002\u00101J\b\u00102\u001A\u00020\"H\'J\b\u00103\u001A\u00020\tH&J\b\u00104\u001A\u00020\"H&J+\u00105\u001A\u00020\"2\u0006\u0010*\u001A\u00020\u00052\u0014\b\u0001\u00106\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000100\u0018\u00010/H\u0016\u00A2\u0006\u0002\u00107J\u0010\u00108\u001A\u00020\"2\u0006\u0010*\u001A\u00020\u0005H&J\'\u00108\u001A\u00020\"2\u0006\u0010*\u001A\u00020\u00052\u0010\u00106\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001000/H&\u00A2\u0006\u0002\u00107J\b\u00109\u001A\u00020\tH&J \u0010:\u001A\u00020\u00112\u0006\u0010,\u001A\u00020\u00052\u0006\u0010;\u001A\u00020\u001C2\u0006\u0010<\u001A\u00020=H&J\u0010\u0010>\u001A\u00020\t2\u0006\u0010?\u001A\u00020\u001CH&J\u0010\u0010@\u001A\u00020A2\u0006\u0010@\u001A\u00020BH&J\u001A\u0010@\u001A\u00020A2\u0006\u0010@\u001A\u00020B2\b\u0010C\u001A\u0004\u0018\u00010DH\'J\u0010\u0010@\u001A\u00020A2\u0006\u0010@\u001A\u00020\u0005H&J\'\u0010@\u001A\u00020A2\u0006\u0010@\u001A\u00020\u00052\u0010\u00106\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001000/H&\u00A2\u0006\u0002\u0010EJ\u0010\u0010F\u001A\u00020\"2\u0006\u0010G\u001A\u00020\tH\'J\u0010\u0010H\u001A\u00020\"2\u0006\u0010I\u001A\u00020JH&J\u0010\u0010K\u001A\u00020\"2\u0006\u0010L\u001A\u00020\u001CH&J\u0010\u0010M\u001A\u00020\u00112\u0006\u0010N\u001A\u00020\u0011H&J\b\u0010O\u001A\u00020\"H&JC\u0010P\u001A\u00020\u001C2\u0006\u0010,\u001A\u00020\u00052\u0006\u0010;\u001A\u00020\u001C2\u0006\u0010<\u001A\u00020=2\b\u0010-\u001A\u0004\u0018\u00010\u00052\u0012\u0010.\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000100\u0018\u00010/H&\u00A2\u0006\u0002\u0010QJ\b\u0010R\u001A\u00020\tH&J\u0010\u0010R\u001A\u00020\t2\u0006\u0010S\u001A\u00020\u0011H&R(\u0010\u0002\u001A\u0016\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00038fX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001A\u00020\tX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\b\u0010\nR\u0012\u0010\u000B\u001A\u00020\tX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000B\u0010\nR\u0014\u0010\f\u001A\u00020\t8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\nR\u0012\u0010\r\u001A\u00020\tX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\r\u0010\nR\u0012\u0010\u000E\u001A\u00020\tX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\nR\u0014\u0010\u000F\u001A\u00020\t8gX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\nR\u0012\u0010\u0010\u001A\u00020\u0011X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001A\u00020\u0011X\u00A6\u000E\u00A2\u0006\f\u001A\u0004\b\u0015\u0010\u0013\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001A\u0004\u0018\u00010\u0005X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u001AR\u0018\u0010\u001B\u001A\u00020\u001CX\u00A6\u000E\u00A2\u0006\f\u001A\u0004\b\u001D\u0010\u001E\"\u0004\b\u001F\u0010 \u00F8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00A8\u0006T\u00C0\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteDatabase;", "Ljava/io/Closeable;", "attachedDbs", "", "Landroid/util/Pair;", "", "getAttachedDbs", "()Ljava/util/List;", "isDatabaseIntegrityOk", "", "()Z", "isDbLockedByCurrentThread", "isExecPerConnectionSQLSupported", "isOpen", "isReadOnly", "isWriteAheadLoggingEnabled", "maximumSize", "", "getMaximumSize", "()J", "pageSize", "getPageSize", "setPageSize", "(J)V", "path", "getPath", "()Ljava/lang/String;", "version", "", "getVersion", "()I", "setVersion", "(I)V", "beginTransaction", "", "beginTransactionNonExclusive", "beginTransactionWithListener", "transactionListener", "Landroid/database/sqlite/SQLiteTransactionListener;", "beginTransactionWithListenerNonExclusive", "compileStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sql", "delete", "table", "whereClause", "whereArgs", "", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I", "disableWriteAheadLogging", "enableWriteAheadLogging", "endTransaction", "execPerConnectionSQL", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "execSQL", "inTransaction", "insert", "conflictAlgorithm", "values", "Landroid/content/ContentValues;", "needUpgrade", "newVersion", "query", "Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "cancellationSignal", "Landroid/os/CancellationSignal;", "(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;", "setForeignKeyConstraintsEnabled", "enabled", "setLocale", "locale", "Ljava/util/Locale;", "setMaxSqlCacheSize", "cacheSize", "setMaximumSize", "numBytes", "setTransactionSuccessful", "update", "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "yieldIfContendedSafely", "sleepAfterYieldDelayMillis", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    void beginTransactionNonExclusive();

    void beginTransactionWithListener(SQLiteTransactionListener arg1);

    void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener arg1);

    SupportSQLiteStatement compileStatement(String arg1);

    int delete(String arg1, String arg2, Object[] arg3);

    void disableWriteAheadLogging();

    boolean enableWriteAheadLogging();

    void endTransaction();

    void execPerConnectionSQL(String arg1, Object[] arg2);

    void execSQL(String arg1) throws SQLException;

    void execSQL(String arg1, Object[] arg2) throws SQLException;

    List getAttachedDbs();

    long getMaximumSize();

    long getPageSize();

    String getPath();

    int getVersion();

    boolean inTransaction();

    long insert(String arg1, int arg2, ContentValues arg3) throws SQLException;

    boolean isDatabaseIntegrityOk();

    boolean isDbLockedByCurrentThread();

    boolean isExecPerConnectionSQLSupported();

    boolean isOpen();

    boolean isReadOnly();

    boolean isWriteAheadLoggingEnabled();

    boolean needUpgrade(int arg1);

    Cursor query(SupportSQLiteQuery arg1);

    Cursor query(SupportSQLiteQuery arg1, CancellationSignal arg2);

    Cursor query(String arg1);

    Cursor query(String arg1, Object[] arg2);

    void setForeignKeyConstraintsEnabled(boolean arg1);

    void setLocale(Locale arg1);

    void setMaxSqlCacheSize(int arg1);

    long setMaximumSize(long arg1);

    void setPageSize(long arg1);

    void setTransactionSuccessful();

    void setVersion(int arg1);

    int update(String arg1, int arg2, ContentValues arg3, String arg4, Object[] arg5);

    boolean yieldIfContendedSafely();

    boolean yieldIfContendedSafely(long arg1);
}

