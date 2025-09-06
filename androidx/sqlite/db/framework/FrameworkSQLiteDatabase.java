package androidx.sqlite.db.framework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.text.TextUtils;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 \\2\u00020\u0001:\u0002[\\B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\b\u0010\'\u001A\u00020(H\u0016J\b\u0010)\u001A\u00020(H\u0016J\u0010\u0010*\u001A\u00020(2\u0006\u0010+\u001A\u00020,H\u0016J\u0010\u0010-\u001A\u00020(2\u0006\u0010+\u001A\u00020,H\u0016J\b\u0010.\u001A\u00020(H\u0016J\u0010\u0010/\u001A\u0002002\u0006\u00101\u001A\u00020\bH\u0016J3\u00102\u001A\u00020!2\u0006\u00103\u001A\u00020\b2\b\u00104\u001A\u0004\u0018\u00010\b2\u0012\u00105\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000107\u0018\u000106H\u0016\u00A2\u0006\u0002\u00108J\b\u00109\u001A\u00020(H\u0017J\b\u0010:\u001A\u00020\fH\u0016J\b\u0010;\u001A\u00020(H\u0016J)\u0010<\u001A\u00020(2\u0006\u00101\u001A\u00020\b2\u0012\u0010=\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000107\u0018\u000106H\u0016\u00A2\u0006\u0002\u0010>J\u0010\u0010?\u001A\u00020(2\u0006\u00101\u001A\u00020\bH\u0016J\'\u0010?\u001A\u00020(2\u0006\u00101\u001A\u00020\b2\u0010\u0010=\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010706H\u0016\u00A2\u0006\u0002\u0010>J\b\u0010@\u001A\u00020\fH\u0016J \u0010A\u001A\u00020\u00142\u0006\u00103\u001A\u00020\b2\u0006\u0010B\u001A\u00020!2\u0006\u0010C\u001A\u00020DH\u0016J\u000E\u0010E\u001A\u00020\f2\u0006\u0010F\u001A\u00020\u0003J\u0010\u0010G\u001A\u00020\f2\u0006\u0010H\u001A\u00020!H\u0016J\u0010\u0010I\u001A\u00020J2\u0006\u0010I\u001A\u00020KH\u0016J\u001A\u0010I\u001A\u00020J2\u0006\u0010I\u001A\u00020K2\b\u0010L\u001A\u0004\u0018\u00010MH\u0017J\u0010\u0010I\u001A\u00020J2\u0006\u0010I\u001A\u00020\bH\u0016J\'\u0010I\u001A\u00020J2\u0006\u0010I\u001A\u00020\b2\u0010\u0010=\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010706H\u0016\u00A2\u0006\u0002\u0010NJ\u0010\u0010O\u001A\u00020(2\u0006\u0010P\u001A\u00020\fH\u0017J\u0010\u0010Q\u001A\u00020(2\u0006\u0010R\u001A\u00020SH\u0016J\u0010\u0010T\u001A\u00020(2\u0006\u0010U\u001A\u00020!H\u0016J\u0010\u0010\u0018\u001A\u00020\u00142\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\b\u0010V\u001A\u00020(H\u0016JC\u0010W\u001A\u00020!2\u0006\u00103\u001A\u00020\b2\u0006\u0010B\u001A\u00020!2\u0006\u0010C\u001A\u00020D2\b\u00104\u001A\u0004\u0018\u00010\b2\u0012\u00105\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000107\u0018\u000106H\u0016\u00A2\u0006\u0002\u0010XJ\b\u0010Y\u001A\u00020\fH\u0016J\u0010\u0010Y\u001A\u00020\f2\u0006\u0010Z\u001A\u00020\u0014H\u0016R(\u0010\u0005\u001A\u0016\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u00068VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000B\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000B\u0010\rR\u0014\u0010\u000E\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\rR\u0014\u0010\u000F\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\rR\u0014\u0010\u0010\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001A\u00020\f8WX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\rR$\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0013\u001A\u00020\u00148V@VX\u0096\u000E\u00A2\u0006\f\u001A\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001A\u001A\u00020\u00142\u0006\u0010\u0013\u001A\u00020\u00148V@VX\u0096\u000E\u00A2\u0006\f\u001A\u0004\b\u001B\u0010\u0017\"\u0004\b\u001C\u0010\u0019R\u0016\u0010\u001D\u001A\u0004\u0018\u00010\b8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001E\u0010\u001FR$\u0010\"\u001A\u00020!2\u0006\u0010 \u001A\u00020!8V@VX\u0096\u000E\u00A2\u0006\f\u001A\u0004\b#\u0010$\"\u0004\b%\u0010&\u00A8\u0006]"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "delegate", "Landroid/database/sqlite/SQLiteDatabase;", "(Landroid/database/sqlite/SQLiteDatabase;)V", "attachedDbs", "", "Landroid/util/Pair;", "", "getAttachedDbs", "()Ljava/util/List;", "isDatabaseIntegrityOk", "", "()Z", "isDbLockedByCurrentThread", "isExecPerConnectionSQLSupported", "isOpen", "isReadOnly", "isWriteAheadLoggingEnabled", "numBytes", "", "maximumSize", "getMaximumSize", "()J", "setMaximumSize", "(J)V", "pageSize", "getPageSize", "setPageSize", "path", "getPath", "()Ljava/lang/String;", "value", "", "version", "getVersion", "()I", "setVersion", "(I)V", "beginTransaction", "", "beginTransactionNonExclusive", "beginTransactionWithListener", "transactionListener", "Landroid/database/sqlite/SQLiteTransactionListener;", "beginTransactionWithListenerNonExclusive", "close", "compileStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sql", "delete", "table", "whereClause", "whereArgs", "", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I", "disableWriteAheadLogging", "enableWriteAheadLogging", "endTransaction", "execPerConnectionSQL", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "execSQL", "inTransaction", "insert", "conflictAlgorithm", "values", "Landroid/content/ContentValues;", "isDelegate", "sqLiteDatabase", "needUpgrade", "newVersion", "query", "Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "cancellationSignal", "Landroid/os/CancellationSignal;", "(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;", "setForeignKeyConstraintsEnabled", "enabled", "setLocale", "locale", "Ljava/util/Locale;", "setMaxSqlCacheSize", "cacheSize", "setTransactionSuccessful", "update", "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "yieldIfContendedSafely", "sleepAfterYieldDelayMillis", "Api30Impl", "Companion", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class FrameworkSQLiteDatabase implements SupportSQLiteDatabase, AutoCloseable {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0012\u0010\t\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u000B¨\u0006\f"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase$Api30Impl;", "", "()V", "execPerConnectionSQL", "", "sQLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "sql", "", "bindArgs", "", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/Object;)V", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE;

        static {
            Api30Impl.INSTANCE = new Api30Impl();
        }

        public final void execPerConnectionSQL(SQLiteDatabase sQLiteDatabase0, String s, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sQLiteDatabase");
            Intrinsics.checkNotNullParameter(s, "sql");
            sQLiteDatabase0.execPerConnectionSQL(s, arr_object);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0018\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase$Companion;", "", "()V", "CONFLICT_VALUES", "", "", "[Ljava/lang/String;", "EMPTY_STRING_ARRAY", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final String[] CONFLICT_VALUES;
    public static final Companion Companion;
    private static final String[] EMPTY_STRING_ARRAY;
    private final SQLiteDatabase delegate;

    // 检测为 Lambda 实现
    public static Cursor $r8$lambda$mxkMCHH54EI3Mb052ZTb9xnn0ow(SupportSQLiteQuery supportSQLiteQuery0, SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) [...]

    // 检测为 Lambda 实现
    public static Cursor $r8$lambda$nsMcCVLiqxDRAAOcFblmRGCM9fk(Function4 function40, SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) [...]

    static {
        FrameworkSQLiteDatabase.Companion = new Companion(null);
        FrameworkSQLiteDatabase.CONFLICT_VALUES = new String[]{"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
        FrameworkSQLiteDatabase.EMPTY_STRING_ARRAY = new String[0];
    }

    public FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase0, "delegate");
        super();
        this.delegate = sQLiteDatabase0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransaction() {
        this.delegate.beginTransaction();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionNonExclusive() {
        this.delegate.beginTransactionNonExclusive();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener0) {
        Intrinsics.checkNotNullParameter(sQLiteTransactionListener0, "transactionListener");
        this.delegate.beginTransactionWithListener(sQLiteTransactionListener0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener0) {
        Intrinsics.checkNotNullParameter(sQLiteTransactionListener0, "transactionListener");
        this.delegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener0);
    }

    @Override
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public SupportSQLiteStatement compileStatement(String s) {
        Intrinsics.checkNotNullParameter(s, "sql");
        SQLiteStatement sQLiteStatement0 = this.delegate.compileStatement(s);
        Intrinsics.checkNotNullExpressionValue(sQLiteStatement0, "delegate.compileStatement(sql)");
        return new FrameworkSQLiteStatement(sQLiteStatement0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public int delete(String s, String s1, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "table");
        StringBuilder stringBuilder0 = new StringBuilder("DELETE FROM ");
        stringBuilder0.append(s);
        if(s1 != null && s1.length() != 0) {
            stringBuilder0.append(" WHERE ");
            stringBuilder0.append(s1);
        }
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
        SupportSQLiteStatement supportSQLiteStatement0 = this.compileStatement(s2);
        SimpleSQLiteQuery.Companion.bind(supportSQLiteStatement0, arr_object);
        return supportSQLiteStatement0.executeUpdateDelete();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void disableWriteAheadLogging() {
        Api16Impl.disableWriteAheadLogging(this.delegate);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean enableWriteAheadLogging() {
        return this.delegate.enableWriteAheadLogging();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void endTransaction() {
        this.delegate.endTransaction();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void execPerConnectionSQL(String s, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "sql");
        if(Build.VERSION.SDK_INT < 30) {
            throw new UnsupportedOperationException("execPerConnectionSQL is not supported on a SDK version lower than 30, current version is: " + Build.VERSION.SDK_INT);
        }
        Api30Impl.INSTANCE.execPerConnectionSQL(this.delegate, s, arr_object);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(String s) throws SQLException {
        Intrinsics.checkNotNullParameter(s, "sql");
        this.delegate.execSQL(s);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(String s, Object[] arr_object) throws SQLException {
        Intrinsics.checkNotNullParameter(s, "sql");
        Intrinsics.checkNotNullParameter(arr_object, "bindArgs");
        this.delegate.execSQL(s, arr_object);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public List getAttachedDbs() {
        return this.delegate.getAttachedDbs();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public long getMaximumSize() {
        return this.delegate.getMaximumSize();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public long getPageSize() {
        return this.delegate.getPageSize();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public String getPath() {
        return this.delegate.getPath();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public int getVersion() {
        return this.delegate.getVersion();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public long insert(String s, int v, ContentValues contentValues0) throws SQLException {
        Intrinsics.checkNotNullParameter(s, "table");
        Intrinsics.checkNotNullParameter(contentValues0, "values");
        return this.delegate.insertWithOnConflict(s, null, contentValues0, v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isDatabaseIntegrityOk() {
        return this.delegate.isDatabaseIntegrityOk();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isDbLockedByCurrentThread() {
        return this.delegate.isDbLockedByCurrentThread();
    }

    public final boolean isDelegate(SQLiteDatabase sQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sqLiteDatabase");
        return Intrinsics.areEqual(this.delegate, sQLiteDatabase0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isExecPerConnectionSQLSupported() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isOpen() {
        return this.delegate.isOpen();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isReadOnly() {
        return this.delegate.isReadOnly();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isWriteAheadLoggingEnabled() {
        return Api16Impl.isWriteAheadLoggingEnabled(this.delegate);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean needUpgrade(int v) {
        return this.delegate.needUpgrade(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(SupportSQLiteQuery supportSQLiteQuery0) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
        FrameworkSQLiteDatabase..ExternalSyntheticLambda1 frameworkSQLiteDatabase$$ExternalSyntheticLambda10 = (SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) -> FrameworkSQLiteDatabase.query$lambda$0(new androidx.sqlite.db.framework.FrameworkSQLiteDatabase.query.cursorFactory.1(supportSQLiteQuery0), sQLiteDatabase0, sQLiteCursorDriver0, s, sQLiteQuery0);
        String s = supportSQLiteQuery0.getSql();
        Cursor cursor0 = this.delegate.rawQueryWithFactory(frameworkSQLiteDatabase$$ExternalSyntheticLambda10, s, FrameworkSQLiteDatabase.EMPTY_STRING_ARRAY, null);
        Intrinsics.checkNotNullExpressionValue(cursor0, "delegate.rawQueryWithFac…EMPTY_STRING_ARRAY, null)");
        return cursor0;

        @Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\u0010\u0004\u001A\u0004\u0018\u00010\u00052\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\b\u0010\b\u001A\u0004\u0018\u00010\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "Landroid/database/sqlite/SQLiteCursor;", "<anonymous parameter 0>", "Landroid/database/sqlite/SQLiteDatabase;", "masterQuery", "Landroid/database/sqlite/SQLiteCursorDriver;", "editTable", "", "sqLiteQuery", "Landroid/database/sqlite/SQLiteQuery;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.sqlite.db.framework.FrameworkSQLiteDatabase.query.cursorFactory.1 extends Lambda implements Function4 {
            final SupportSQLiteQuery $query;

            androidx.sqlite.db.framework.FrameworkSQLiteDatabase.query.cursorFactory.1(SupportSQLiteQuery supportSQLiteQuery0) {
                this.$query = supportSQLiteQuery0;
                super(4);
            }

            public final SQLiteCursor invoke(SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) {
                Intrinsics.checkNotNull(sQLiteQuery0);
                FrameworkSQLiteProgram frameworkSQLiteProgram0 = new FrameworkSQLiteProgram(sQLiteQuery0);
                this.$query.bindTo(frameworkSQLiteProgram0);
                return new SQLiteCursor(sQLiteCursorDriver0, s, sQLiteQuery0);
            }

            @Override  // kotlin.jvm.functions.Function4
            public Object invoke(Object object0, Object object1, Object object2, Object object3) {
                return this.invoke(((SQLiteDatabase)object0), ((SQLiteCursorDriver)object1), ((String)object2), ((SQLiteQuery)object3));
            }
        }

    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(SupportSQLiteQuery supportSQLiteQuery0, CancellationSignal cancellationSignal0) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
        String s = supportSQLiteQuery0.getSql();
        Intrinsics.checkNotNull(cancellationSignal0);
        FrameworkSQLiteDatabase..ExternalSyntheticLambda0 frameworkSQLiteDatabase$$ExternalSyntheticLambda00 = (SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) -> FrameworkSQLiteDatabase.query$lambda$1(supportSQLiteQuery0, sQLiteDatabase0, sQLiteCursorDriver0, s, sQLiteQuery0);
        return Api16Impl.rawQueryWithFactory(this.delegate, s, FrameworkSQLiteDatabase.EMPTY_STRING_ARRAY, null, cancellationSignal0, frameworkSQLiteDatabase$$ExternalSyntheticLambda00);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(String s) {
        Intrinsics.checkNotNullParameter(s, "query");
        return this.query(new SimpleSQLiteQuery(s));
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(String s, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "query");
        Intrinsics.checkNotNullParameter(arr_object, "bindArgs");
        return this.query(new SimpleSQLiteQuery(s, arr_object));
    }

    private static final Cursor query$lambda$0(Function4 function40, SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) {
        Intrinsics.checkNotNullParameter(function40, "$tmp0");
        return (Cursor)function40.invoke(sQLiteDatabase0, sQLiteCursorDriver0, s, sQLiteQuery0);
    }

    private static final Cursor query$lambda$1(SupportSQLiteQuery supportSQLiteQuery0, SQLiteDatabase sQLiteDatabase0, SQLiteCursorDriver sQLiteCursorDriver0, String s, SQLiteQuery sQLiteQuery0) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "$query");
        Intrinsics.checkNotNull(sQLiteQuery0);
        supportSQLiteQuery0.bindTo(new FrameworkSQLiteProgram(sQLiteQuery0));
        return new SQLiteCursor(sQLiteCursorDriver0, s, sQLiteQuery0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void setForeignKeyConstraintsEnabled(boolean z) {
        Api16Impl.setForeignKeyConstraintsEnabled(this.delegate, z);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void setLocale(Locale locale0) {
        Intrinsics.checkNotNullParameter(locale0, "locale");
        this.delegate.setLocale(locale0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void setMaxSqlCacheSize(int v) {
        this.delegate.setMaxSqlCacheSize(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public long setMaximumSize(long v) {
        this.delegate.setMaximumSize(v);
        return this.delegate.getMaximumSize();
    }

    public void setMaximumSize(long v) {
        this.delegate.setMaximumSize(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void setPageSize(long v) {
        this.delegate.setPageSize(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public void setVersion(int v) {
        this.delegate.setVersion(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public int update(String s, int v, ContentValues contentValues0, String s1, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "table");
        Intrinsics.checkNotNullParameter(contentValues0, "values");
        if(contentValues0.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        int v1 = contentValues0.size();
        int v2 = arr_object == null ? v1 : arr_object.length + v1;
        Object[] arr_object1 = new Object[v2];
        StringBuilder stringBuilder0 = new StringBuilder("UPDATE ");
        stringBuilder0.append(FrameworkSQLiteDatabase.CONFLICT_VALUES[v]);
        stringBuilder0.append(s);
        stringBuilder0.append(" SET ");
        int v3 = 0;
        for(Object object0: contentValues0.keySet()) {
            stringBuilder0.append((v3 <= 0 ? "" : ","));
            stringBuilder0.append(((String)object0));
            arr_object1[v3] = contentValues0.get(((String)object0));
            stringBuilder0.append("=?");
            ++v3;
        }
        if(arr_object != null) {
            for(int v4 = v1; v4 < v2; ++v4) {
                arr_object1[v4] = arr_object[v4 - v1];
            }
        }
        if(!TextUtils.isEmpty(s1)) {
            stringBuilder0.append(" WHERE ");
            stringBuilder0.append(s1);
        }
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
        SupportSQLiteStatement supportSQLiteStatement0 = this.compileStatement(s2);
        SimpleSQLiteQuery.Companion.bind(supportSQLiteStatement0, arr_object1);
        return supportSQLiteStatement0.executeUpdateDelete();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely() {
        return this.delegate.yieldIfContendedSafely();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely(long v) {
        return this.delegate.yieldIfContendedSafely(v);
    }
}

