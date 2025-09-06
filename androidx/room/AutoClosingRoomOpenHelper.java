package androidx.room;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import androidx.sqlite.db.SupportSQLiteCompat.Api19Impl;
import androidx.sqlite.db.SupportSQLiteCompat.Api23Impl;
import androidx.sqlite.db.SupportSQLiteCompat.Api29Impl;
import androidx.sqlite.db.SupportSQLiteDatabase.-CC;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0003\u001A\u001B\u001CB\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0001\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\u0011\u0010\u0017\u001A\u00020\u00162\u0006\u0010\u0018\u001A\u00020\u0019H\u0097\u0001R\u0010\u0010\u0004\u001A\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u0004\u0018\u00010\nX\u0096\u0005¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u0014\u0010\u0003\u001A\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0014\u0010\u000F\u001A\u00020\u00108WX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001A\u00020\u00108WX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0012¨\u0006\u001D"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DelegatingOpenHelper;", "delegate", "autoCloser", "Landroidx/room/AutoCloser;", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;Landroidx/room/AutoCloser;)V", "autoClosingDb", "Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "databaseName", "", "getDatabaseName", "()Ljava/lang/String;", "getDelegate", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "readableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getReadableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "getWritableDatabase", "close", "", "setWriteAheadLoggingEnabled", "enabled", "", "AutoClosingSupportSQLiteDatabase", "AutoClosingSupportSqliteStatement", "KeepAliveCursor", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AutoClosingRoomOpenHelper implements DelegatingOpenHelper, SupportSQLiteOpenHelper, AutoCloseable {
    @Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000B\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\b\u0010$\u001A\u00020%H\u0016J\b\u0010&\u001A\u00020%H\u0016J\u0010\u0010\'\u001A\u00020%2\u0006\u0010(\u001A\u00020)H\u0016J\u0010\u0010*\u001A\u00020%2\u0006\u0010(\u001A\u00020)H\u0016J\b\u0010+\u001A\u00020%H\u0016J\u0010\u0010,\u001A\u00020-2\u0006\u0010.\u001A\u00020\bH\u0016J3\u0010/\u001A\u00020\u001F2\u0006\u00100\u001A\u00020\b2\b\u00101\u001A\u0004\u0018\u00010\b2\u0012\u00102\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000104\u0018\u000103H\u0016\u00A2\u0006\u0002\u00105J\b\u00106\u001A\u00020%H\u0016J\b\u00107\u001A\u00020\fH\u0016J\b\u00108\u001A\u00020%H\u0016J\u0010\u00109\u001A\u00020%2\u0006\u0010.\u001A\u00020\bH\u0016J\'\u00109\u001A\u00020%2\u0006\u0010.\u001A\u00020\b2\u0010\u0010:\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010403H\u0016\u00A2\u0006\u0002\u0010;J\b\u0010<\u001A\u00020\fH\u0016J \u0010=\u001A\u00020\u00132\u0006\u00100\u001A\u00020\b2\u0006\u0010>\u001A\u00020\u001F2\u0006\u0010?\u001A\u00020@H\u0016J\u0010\u0010A\u001A\u00020\f2\u0006\u0010B\u001A\u00020\u001FH\u0016J\u0006\u0010C\u001A\u00020%J\u0010\u0010D\u001A\u00020E2\u0006\u0010D\u001A\u00020FH\u0016J\u001A\u0010D\u001A\u00020E2\u0006\u0010D\u001A\u00020F2\b\u0010G\u001A\u0004\u0018\u00010HH\u0017J\u0010\u0010D\u001A\u00020E2\u0006\u0010D\u001A\u00020\bH\u0016J\'\u0010D\u001A\u00020E2\u0006\u0010D\u001A\u00020\b2\u0010\u0010:\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010403H\u0016\u00A2\u0006\u0002\u0010IJ\u0010\u0010J\u001A\u00020%2\u0006\u0010K\u001A\u00020\fH\u0017J\u0010\u0010L\u001A\u00020%2\u0006\u0010M\u001A\u00020NH\u0016J\u0010\u0010O\u001A\u00020%2\u0006\u0010P\u001A\u00020\u001FH\u0016J\u0010\u0010Q\u001A\u00020\u00132\u0006\u0010\u0016\u001A\u00020\u0013H\u0016J\b\u0010R\u001A\u00020%H\u0016JC\u0010S\u001A\u00020\u001F2\u0006\u00100\u001A\u00020\b2\u0006\u0010>\u001A\u00020\u001F2\u0006\u0010?\u001A\u00020@2\b\u00101\u001A\u0004\u0018\u00010\b2\u0012\u00102\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u000104\u0018\u000103H\u0016\u00A2\u0006\u0002\u0010TJ\b\u0010U\u001A\u00020\fH\u0016J\u0010\u0010U\u001A\u00020\f2\u0006\u0010V\u001A\u00020\u0013H\u0016R(\u0010\u0005\u001A\u0016\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u00068VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000B\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000B\u0010\rR\u0014\u0010\u000E\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\rR\u0014\u0010\u000F\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\rR\u0014\u0010\u0010\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001A\u00020\f8WX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001A\u00020\u00138VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001A\u00020\u00132\u0006\u0010\u0016\u001A\u00020\u00138V@VX\u0096\u000E\u00A2\u0006\f\u001A\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u001AR\u0016\u0010\u001B\u001A\u0004\u0018\u00010\b8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u001DR$\u0010\u001E\u001A\u00020\u001F2\u0006\u0010\u001E\u001A\u00020\u001F8V@VX\u0096\u000E\u00A2\u0006\f\u001A\u0004\b \u0010!\"\u0004\b\"\u0010#\u00A8\u0006W"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "autoCloser", "Landroidx/room/AutoCloser;", "(Landroidx/room/AutoCloser;)V", "attachedDbs", "", "Landroid/util/Pair;", "", "getAttachedDbs", "()Ljava/util/List;", "isDatabaseIntegrityOk", "", "()Z", "isDbLockedByCurrentThread", "isOpen", "isReadOnly", "isWriteAheadLoggingEnabled", "maximumSize", "", "getMaximumSize", "()J", "numBytes", "pageSize", "getPageSize", "setPageSize", "(J)V", "path", "getPath", "()Ljava/lang/String;", "version", "", "getVersion", "()I", "setVersion", "(I)V", "beginTransaction", "", "beginTransactionNonExclusive", "beginTransactionWithListener", "transactionListener", "Landroid/database/sqlite/SQLiteTransactionListener;", "beginTransactionWithListenerNonExclusive", "close", "compileStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sql", "delete", "table", "whereClause", "whereArgs", "", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I", "disableWriteAheadLogging", "enableWriteAheadLogging", "endTransaction", "execSQL", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "inTransaction", "insert", "conflictAlgorithm", "values", "Landroid/content/ContentValues;", "needUpgrade", "newVersion", "pokeOpen", "query", "Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "cancellationSignal", "Landroid/os/CancellationSignal;", "(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;", "setForeignKeyConstraintsEnabled", "enabled", "setLocale", "locale", "Ljava/util/Locale;", "setMaxSqlCacheSize", "cacheSize", "setMaximumSize", "setTransactionSuccessful", "update", "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "yieldIfContendedSafely", "sleepAfterYieldDelayMillis", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase, AutoCloseable {
        private final AutoCloser autoCloser;

        public AutoClosingSupportSQLiteDatabase(AutoCloser autoCloser0) {
            Intrinsics.checkNotNullParameter(autoCloser0, "autoCloser");
            super();
            this.autoCloser = autoCloser0;
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void beginTransaction() {
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.incrementCountAndEnsureDbIsOpen();
            try {
                supportSQLiteDatabase0.beginTransaction();
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void beginTransactionNonExclusive() {
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.incrementCountAndEnsureDbIsOpen();
            try {
                supportSQLiteDatabase0.beginTransactionNonExclusive();
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener0) {
            Intrinsics.checkNotNullParameter(sQLiteTransactionListener0, "transactionListener");
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.incrementCountAndEnsureDbIsOpen();
            try {
                supportSQLiteDatabase0.beginTransactionWithListener(sQLiteTransactionListener0);
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener0) {
            Intrinsics.checkNotNullParameter(sQLiteTransactionListener0, "transactionListener");
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.incrementCountAndEnsureDbIsOpen();
            try {
                supportSQLiteDatabase0.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener0);
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override
        public void close() throws IOException {
            this.autoCloser.closeDatabaseIfOpen();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public SupportSQLiteStatement compileStatement(String s) {
            Intrinsics.checkNotNullParameter(s, "sql");
            return new AutoClosingSupportSqliteStatement(s, this.autoCloser);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public int delete(String s, String s1, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(s, "table");
            Function1 function10 = new Function1(s1, arr_object) {
                final String $table;
                final Object[] $whereArgs;
                final String $whereClause;

                {
                    this.$table = s;
                    this.$whereClause = s1;
                    this.$whereArgs = arr_object;
                    super(1);
                }

                public final Integer invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    return supportSQLiteDatabase0.delete(this.$table, this.$whereClause, this.$whereArgs);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            return ((Number)this.autoCloser.executeRefCountingFunction(function10)).intValue();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void disableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean enableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void endTransaction() {
            if(this.autoCloser.getDelegateDatabase$room_runtime_release() == null) {
                throw new IllegalStateException("End transaction called but delegateDb is null");
            }
            try {
                SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.getDelegateDatabase$room_runtime_release();
                Intrinsics.checkNotNull(supportSQLiteDatabase0);
                supportSQLiteDatabase0.endTransaction();
            }
            finally {
                this.autoCloser.decrementCountAndScheduleClose();
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void execPerConnectionSQL(String s, Object[] arr_object) {
            SupportSQLiteDatabase.-CC.$default$execPerConnectionSQL(this, s, arr_object);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void execSQL(String s) throws SQLException {
            Intrinsics.checkNotNullParameter(s, "sql");
            Function1 function10 = new Function1() {
                final String $sql;

                {
                    this.$sql = s;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.execSQL(this.$sql);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void execSQL(String s, Object[] arr_object) throws SQLException {
            Intrinsics.checkNotNullParameter(s, "sql");
            Intrinsics.checkNotNullParameter(arr_object, "bindArgs");
            Function1 function10 = new Function1(arr_object) {
                final Object[] $bindArgs;
                final String $sql;

                {
                    this.$sql = s;
                    this.$bindArgs = arr_object;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.execSQL(this.$sql, this.$bindArgs);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public List getAttachedDbs() {
            return (List)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.attachedDbs.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0016\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u00012\u0006\u0010\u0004\u001A\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Landroid/util/Pair;", "", "obj", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.attachedDbs.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.attachedDbs.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.attachedDbs.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.attachedDbs.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.attachedDbs.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }

                public final List invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "obj");
                    return supportSQLiteDatabase0.getAttachedDbs();
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public long getMaximumSize() {
            return ((Number)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.maximumSize.1.INSTANCE)).longValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.maximumSize.1 extends PropertyReference1Impl {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.maximumSize.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.maximumSize.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.maximumSize.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.maximumSize.1() {
                    super(SupportSQLiteDatabase.class, "maximumSize", "getMaximumSize()J", 0);
                }

                @Override  // kotlin.jvm.internal.PropertyReference1Impl
                public Object get(Object object0) {
                    return ((SupportSQLiteDatabase)object0).getMaximumSize();
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public long getPageSize() {
            return ((Number)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pageSize.1.INSTANCE)).longValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pageSize.1 extends MutablePropertyReference1Impl {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pageSize.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pageSize.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pageSize.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pageSize.1() {
                    super(SupportSQLiteDatabase.class, "pageSize", "getPageSize()J", 0);
                }

                @Override  // kotlin.jvm.internal.MutablePropertyReference1Impl
                public Object get(Object object0) {
                    return ((SupportSQLiteDatabase)object0).getPageSize();
                }

                @Override  // kotlin.jvm.internal.MutablePropertyReference1Impl
                public void set(Object object0, Object object1) {
                    ((SupportSQLiteDatabase)object0).setPageSize(((Number)object1).longValue());
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public String getPath() {
            return (String)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.path.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.path.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.path.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.path.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.path.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.path.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }

                public final String invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "obj");
                    return supportSQLiteDatabase0.getPath();
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public int getVersion() {
            return ((Number)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.version.1.INSTANCE)).intValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.version.1 extends MutablePropertyReference1Impl {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.version.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.version.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.version.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.version.1() {
                    super(SupportSQLiteDatabase.class, "version", "getVersion()I", 0);
                }

                @Override  // kotlin.jvm.internal.MutablePropertyReference1Impl
                public Object get(Object object0) {
                    return ((SupportSQLiteDatabase)object0).getVersion();
                }

                @Override  // kotlin.jvm.internal.MutablePropertyReference1Impl
                public void set(Object object0, Object object1) {
                    ((SupportSQLiteDatabase)object0).setVersion(((Number)object1).intValue());
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean inTransaction() {
            return this.autoCloser.getDelegateDatabase$room_runtime_release() == null ? false : ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.inTransaction.1.INSTANCE)).booleanValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.inTransaction.1 extends FunctionReferenceImpl implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.inTransaction.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.inTransaction.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.inTransaction.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.inTransaction.1() {
                    super(1, SupportSQLiteDatabase.class, "inTransaction", "inTransaction()Z", 0);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "p0");
                    return Boolean.valueOf(supportSQLiteDatabase0.inTransaction());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public long insert(String s, int v, ContentValues contentValues0) throws SQLException {
            Intrinsics.checkNotNullParameter(s, "table");
            Intrinsics.checkNotNullParameter(contentValues0, "values");
            Function1 function10 = new Function1(v, contentValues0) {
                final int $conflictAlgorithm;
                final String $table;
                final ContentValues $values;

                {
                    this.$table = s;
                    this.$conflictAlgorithm = v;
                    this.$values = contentValues0;
                    super(1);
                }

                public final Long invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    return supportSQLiteDatabase0.insert(this.$table, this.$conflictAlgorithm, this.$values);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            return ((Number)this.autoCloser.executeRefCountingFunction(function10)).longValue();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isDatabaseIntegrityOk() {
            return ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDatabaseIntegrityOk.1.INSTANCE)).booleanValue();

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDatabaseIntegrityOk.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDatabaseIntegrityOk.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDatabaseIntegrityOk.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDatabaseIntegrityOk.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDatabaseIntegrityOk.1() {
                    super(1);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "obj");
                    return Boolean.valueOf(supportSQLiteDatabase0.isDatabaseIntegrityOk());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isDbLockedByCurrentThread() {
            return this.autoCloser.getDelegateDatabase$room_runtime_release() == null ? false : ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDbLockedByCurrentThread.1.INSTANCE)).booleanValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDbLockedByCurrentThread.1 extends PropertyReference1Impl {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDbLockedByCurrentThread.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDbLockedByCurrentThread.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDbLockedByCurrentThread.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isDbLockedByCurrentThread.1() {
                    super(SupportSQLiteDatabase.class, "isDbLockedByCurrentThread", "isDbLockedByCurrentThread()Z", 0);
                }

                @Override  // kotlin.jvm.internal.PropertyReference1Impl
                public Object get(Object object0) {
                    return Boolean.valueOf(((SupportSQLiteDatabase)object0).isDbLockedByCurrentThread());
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isExecPerConnectionSQLSupported() {
            return false;
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isOpen() {
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.getDelegateDatabase$room_runtime_release();
            return supportSQLiteDatabase0 == null ? false : supportSQLiteDatabase0.isOpen();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isReadOnly() {
            return ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isReadOnly.1.INSTANCE)).booleanValue();

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isReadOnly.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isReadOnly.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isReadOnly.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isReadOnly.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isReadOnly.1() {
                    super(1);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "obj");
                    return Boolean.valueOf(supportSQLiteDatabase0.isReadOnly());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isWriteAheadLoggingEnabled.1.INSTANCE)).booleanValue();

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isWriteAheadLoggingEnabled.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isWriteAheadLoggingEnabled.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isWriteAheadLoggingEnabled.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isWriteAheadLoggingEnabled.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.isWriteAheadLoggingEnabled.1() {
                    super(1);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    return Boolean.valueOf(supportSQLiteDatabase0.isWriteAheadLoggingEnabled());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean needUpgrade(int v) {
            Function1 function10 = new Function1() {
                final int $newVersion;

                {
                    this.$newVersion = v;
                    super(1);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    return Boolean.valueOf(supportSQLiteDatabase0.needUpgrade(this.$newVersion));
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            return ((Boolean)this.autoCloser.executeRefCountingFunction(function10)).booleanValue();
        }

        public final void pokeOpen() {
            this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pokeOpen.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pokeOpen.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pokeOpen.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pokeOpen.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pokeOpen.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.pokeOpen.1() {
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "it");
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public Cursor query(SupportSQLiteQuery supportSQLiteQuery0) {
            Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery0), this.autoCloser);
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public Cursor query(SupportSQLiteQuery supportSQLiteQuery0, CancellationSignal cancellationSignal0) {
            Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery0, cancellationSignal0), this.autoCloser);
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public Cursor query(String s) {
            Intrinsics.checkNotNullParameter(s, "query");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(s), this.autoCloser);
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public Cursor query(String s, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(s, "query");
            Intrinsics.checkNotNullParameter(arr_object, "bindArgs");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(s, arr_object), this.autoCloser);
            }
            catch(Throwable throwable0) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw throwable0;
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void setForeignKeyConstraintsEnabled(boolean z) {
            Function1 function10 = new Function1() {
                final boolean $enabled;

                {
                    this.$enabled = z;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.setForeignKeyConstraintsEnabled(this.$enabled);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void setLocale(Locale locale0) {
            Intrinsics.checkNotNullParameter(locale0, "locale");
            Function1 function10 = new Function1() {
                final Locale $locale;

                {
                    this.$locale = locale0;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.setLocale(this.$locale);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void setMaxSqlCacheSize(int v) {
            Function1 function10 = new Function1() {
                final int $cacheSize;

                {
                    this.$cacheSize = v;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.setMaxSqlCacheSize(this.$cacheSize);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public long setMaximumSize(long v) {
            Function1 function10 = new Function1() {
                final long $numBytes;

                {
                    this.$numBytes = v;
                    super(1);
                }

                public final Long invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    return supportSQLiteDatabase0.setMaximumSize(this.$numBytes);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            return ((Number)this.autoCloser.executeRefCountingFunction(function10)).longValue();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void setPageSize(long v) {
            Function1 function10 = new Function1() {
                final long $numBytes;

                {
                    this.$numBytes = v;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.setPageSize(this.$numBytes);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void setTransactionSuccessful() {
            Unit unit0;
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if(supportSQLiteDatabase0 == null) {
                unit0 = null;
            }
            else {
                supportSQLiteDatabase0.setTransactionSuccessful();
                unit0 = Unit.INSTANCE;
            }
            if(unit0 == null) {
                throw new IllegalStateException("setTransactionSuccessful called but delegateDb is null");
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public void setVersion(int v) {
            Function1 function10 = new Function1() {
                final int $version;

                {
                    this.$version = v;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    supportSQLiteDatabase0.setVersion(this.$version);
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            this.autoCloser.executeRefCountingFunction(function10);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public int update(String s, int v, ContentValues contentValues0, String s1, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(s, "table");
            Intrinsics.checkNotNullParameter(contentValues0, "values");
            androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.update.1 autoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$10 = new Function1(v, contentValues0, s1, arr_object) {
                final int $conflictAlgorithm;
                final String $table;
                final ContentValues $values;
                final Object[] $whereArgs;
                final String $whereClause;

                {
                    this.$table = s;
                    this.$conflictAlgorithm = v;
                    this.$values = contentValues0;
                    this.$whereClause = s1;
                    this.$whereArgs = arr_object;
                    super(1);
                }

                public final Integer invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    return supportSQLiteDatabase0.update(this.$table, this.$conflictAlgorithm, this.$values, this.$whereClause, this.$whereArgs);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            return ((Number)this.autoCloser.executeRefCountingFunction(autoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$10)).intValue();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean yieldIfContendedSafely() {
            return ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.1.INSTANCE)).booleanValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.1 extends FunctionReferenceImpl implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.1() {
                    super(1, SupportSQLiteDatabase.class, "yieldIfContendedSafely", "yieldIfContendedSafely()Z", 0);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "p0");
                    return Boolean.valueOf(supportSQLiteDatabase0.yieldIfContendedSafely());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteDatabase
        public boolean yieldIfContendedSafely(long v) {
            return ((Boolean)this.autoCloser.executeRefCountingFunction(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.2.INSTANCE)).booleanValue();

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.2 extends FunctionReferenceImpl implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.2 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.2.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.2();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.yieldIfContendedSafely.2() {
                    super(1, SupportSQLiteDatabase.class, "yieldIfContendedSafely", "yieldIfContendedSafely()Z", 0);
                }

                public final Boolean invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "p0");
                    return Boolean.valueOf(supportSQLiteDatabase0.yieldIfContendedSafely());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            }

        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\u0018\u0010\u0011\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0012H\u0016J\u0018\u0010\u0013\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0014H\u0016J\u0010\u0010\u0015\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J\u0018\u0010\u0016\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0003H\u0016J\b\u0010\u0017\u001A\u00020\fH\u0016J\b\u0010\u0018\u001A\u00020\fH\u0016J\u0010\u0010\u0019\u001A\u00020\f2\u0006\u0010\u001A\u001A\u00020\u0001H\u0002J\b\u0010\u001B\u001A\u00020\fH\u0016J\b\u0010\u001C\u001A\u00020\u0014H\u0016J\'\u0010\u001D\u001A\u0002H\u001E\"\u0004\b\u0000\u0010\u001E2\u0012\u0010\u001F\u001A\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u001E0 H\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001A\u00020\u000EH\u0016J\u001A\u0010#\u001A\u00020\f2\u0006\u0010$\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\tH\u0002J\b\u0010%\u001A\u00020\u0014H\u0016J\n\u0010&\u001A\u0004\u0018\u00010\u0003H\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u00010\t0\bj\n\u0012\u0006\u0012\u0004\u0018\u00010\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\'"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement;", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sql", "", "autoCloser", "Landroidx/room/AutoCloser;", "(Ljava/lang/String;Landroidx/room/AutoCloser;)V", "binds", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "bindBlob", "", "index", "", "value", "", "bindDouble", "", "bindLong", "", "bindNull", "bindString", "clearBindings", "close", "doBinds", "supportSQLiteStatement", "execute", "executeInsert", "executeSqliteStatementWithRefCount", "T", "block", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "executeUpdateDelete", "saveBinds", "bindIndex", "simpleQueryForLong", "simpleQueryForString", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class AutoClosingSupportSqliteStatement implements SupportSQLiteStatement, AutoCloseable {
        private final AutoCloser autoCloser;
        private final ArrayList binds;
        private final String sql;

        public AutoClosingSupportSqliteStatement(String s, AutoCloser autoCloser0) {
            Intrinsics.checkNotNullParameter(s, "sql");
            Intrinsics.checkNotNullParameter(autoCloser0, "autoCloser");
            super();
            this.sql = s;
            this.autoCloser = autoCloser0;
            this.binds = new ArrayList();
        }

        @Override  // androidx.sqlite.db.SupportSQLiteProgram
        public void bindBlob(int v, byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "value");
            this.saveBinds(v, arr_b);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteProgram
        public void bindDouble(int v, double f) {
            this.saveBinds(v, f);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteProgram
        public void bindLong(int v, long v1) {
            this.saveBinds(v, v1);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteProgram
        public void bindNull(int v) {
            this.saveBinds(v, null);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteProgram
        public void bindString(int v, String s) {
            Intrinsics.checkNotNullParameter(s, "value");
            this.saveBinds(v, s);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteProgram
        public void clearBindings() {
            this.binds.clear();
        }

        @Override
        public void close() throws IOException {
        }

        private final void doBinds(SupportSQLiteStatement supportSQLiteStatement0) {
            Iterator iterator0 = this.binds.iterator();
            for(int v = 0; iterator0.hasNext(); ++v) {
                iterator0.next();
                if(v < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Object object0 = this.binds.get(v);
                if(object0 == null) {
                    supportSQLiteStatement0.bindNull(v + 1);
                }
                else if(object0 instanceof Long) {
                    supportSQLiteStatement0.bindLong(v + 1, ((Number)object0).longValue());
                }
                else if(object0 instanceof Double) {
                    supportSQLiteStatement0.bindDouble(v + 1, ((Number)object0).doubleValue());
                }
                else if(object0 instanceof String) {
                    supportSQLiteStatement0.bindString(v + 1, ((String)object0));
                }
                else if(object0 instanceof byte[]) {
                    supportSQLiteStatement0.bindBlob(v + 1, ((byte[])object0));
                }
            }
        }

        @Override  // androidx.sqlite.db.SupportSQLiteStatement
        public void execute() {
            this.executeSqliteStatementWithRefCount(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.execute.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "statement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.execute.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.execute.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.execute.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.execute.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.execute.1() {
                    super(1);
                }

                public final Object invoke(SupportSQLiteStatement supportSQLiteStatement0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "statement");
                    supportSQLiteStatement0.execute();
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteStatement)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteStatement
        public long executeInsert() {
            return ((Number)this.executeSqliteStatementWithRefCount(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeInsert.1.INSTANCE)).longValue();

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteStatement;)Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeInsert.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeInsert.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeInsert.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeInsert.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeInsert.1() {
                    super(1);
                }

                public final Long invoke(SupportSQLiteStatement supportSQLiteStatement0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "obj");
                    return supportSQLiteStatement0.executeInsert();
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteStatement)object0));
                }
            }

        }

        private final Object executeSqliteStatementWithRefCount(Function1 function10) {
            Function1 function11 = new Function1(function10) {
                final Function1 $block;

                {
                    AutoClosingSupportSqliteStatement.this = autoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement0;
                    this.$block = function10;
                    super(1);
                }

                public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    SupportSQLiteStatement supportSQLiteStatement0 = supportSQLiteDatabase0.compileStatement(AutoClosingSupportSqliteStatement.this.sql);
                    AutoClosingSupportSqliteStatement.this.doBinds(supportSQLiteStatement0);
                    return this.$block.invoke(supportSQLiteStatement0);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteDatabase)object0));
                }
            };
            return this.autoCloser.executeRefCountingFunction(function11);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteStatement
        public int executeUpdateDelete() {
            return ((Number)this.executeSqliteStatementWithRefCount(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeUpdateDelete.1.INSTANCE)).intValue();

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteStatement;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeUpdateDelete.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeUpdateDelete.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeUpdateDelete.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeUpdateDelete.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.executeUpdateDelete.1() {
                    super(1);
                }

                public final Integer invoke(SupportSQLiteStatement supportSQLiteStatement0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "obj");
                    return supportSQLiteStatement0.executeUpdateDelete();
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteStatement)object0));
                }
            }

        }

        private final void saveBinds(int v, Object object0) {
            if(v - 1 >= this.binds.size()) {
                int v1 = this.binds.size();
                if(v1 <= v - 1) {
                    while(true) {
                        this.binds.add(null);
                        if(v1 == v - 1) {
                            break;
                        }
                        ++v1;
                    }
                }
            }
            this.binds.set(v - 1, object0);
        }

        @Override  // androidx.sqlite.db.SupportSQLiteStatement
        public long simpleQueryForLong() {
            return ((Number)this.executeSqliteStatementWithRefCount(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForLong.1.INSTANCE)).longValue();

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteStatement;)Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForLong.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForLong.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForLong.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForLong.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForLong.1() {
                    super(1);
                }

                public final Long invoke(SupportSQLiteStatement supportSQLiteStatement0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "obj");
                    return supportSQLiteStatement0.simpleQueryForLong();
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteStatement)object0));
                }
            }

        }

        @Override  // androidx.sqlite.db.SupportSQLiteStatement
        public String simpleQueryForString() {
            return (String)this.executeSqliteStatementWithRefCount(androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForString.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForString.1 extends Lambda implements Function1 {
                public static final androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForString.1 INSTANCE;

                static {
                    androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForString.1.INSTANCE = new androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForString.1();
                }

                androidx.room.AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.simpleQueryForString.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((SupportSQLiteStatement)object0));
                }

                public final String invoke(SupportSQLiteStatement supportSQLiteStatement0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "obj");
                    return supportSQLiteStatement0.simpleQueryForString();
                }
            }

        }
    }

    @Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001A\u00020\u0007H\u0016J!\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n2\u000E\u0010\u000B\u001A\n \r*\u0004\u0018\u00010\f0\fH\u0096\u0001J\t\u0010\u000E\u001A\u00020\u0007H\u0097\u0001J\u0019\u0010\u000F\u001A\n \r*\u0004\u0018\u00010\u00100\u00102\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\t\u0010\u0011\u001A\u00020\nH\u0096\u0001J\u0019\u0010\u0012\u001A\u00020\n2\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\u0019\u0010\u0014\u001A\u00020\n2\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\u0019\u0010\u0015\u001A\n \r*\u0004\u0018\u00010\u00130\u00132\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J4\u0010\u0016\u001A(\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00130\u0013 \r*\u0014\u0012\u000E\b\u0001\u0012\n \r*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00170\u0017H\u0096\u0001\u00A2\u0006\u0002\u0010\u0018J\t\u0010\u0019\u001A\u00020\nH\u0096\u0001J\u0011\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\u0011\u0010\u001C\u001A\n \r*\u0004\u0018\u00010\u001D0\u001DH\u0096\u0001J\u0011\u0010\u001E\u001A\u00020\u001F2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\u0011\u0010 \u001A\u00020\n2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\u0011\u0010!\u001A\u00020\"2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\b\u0010#\u001A\u00020$H\u0017J\u000E\u0010%\u001A\b\u0012\u0004\u0012\u00020$0&H\u0017J\t\u0010\'\u001A\u00020\nH\u0096\u0001J\u0011\u0010(\u001A\u00020)2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\u0019\u0010*\u001A\n \r*\u0004\u0018\u00010\u00130\u00132\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\u0011\u0010+\u001A\u00020\n2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\t\u0010,\u001A\u00020-H\u0096\u0001J\t\u0010.\u001A\u00020-H\u0096\u0001J\t\u0010/\u001A\u00020-H\u0096\u0001J\t\u00100\u001A\u00020-H\u0096\u0001J\t\u00101\u001A\u00020-H\u0096\u0001J\t\u00102\u001A\u00020-H\u0096\u0001J\u0011\u00103\u001A\u00020-2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\u0011\u00104\u001A\u00020-2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\t\u00105\u001A\u00020-H\u0096\u0001J\t\u00106\u001A\u00020-H\u0096\u0001J\t\u00107\u001A\u00020-H\u0096\u0001J\u0011\u00108\u001A\u00020-2\u0006\u0010\t\u001A\u00020\nH\u0096\u0001J\t\u00109\u001A\u00020-H\u0096\u0001J\u0019\u0010:\u001A\u00020\u00072\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010;0;H\u0096\u0001J\u0019\u0010<\u001A\u00020\u00072\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010=0=H\u0096\u0001J\t\u0010>\u001A\u00020-H\u0097\u0001J!\u0010?\u001A\n \r*\u0004\u0018\u00010\u001D0\u001D2\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010\u001D0\u001DH\u0096\u0001J\u0010\u0010@\u001A\u00020\u00072\u0006\u0010A\u001A\u00020\u001DH\u0017J)\u0010B\u001A\u00020\u00072\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010C0C2\u000E\u0010\u000B\u001A\n \r*\u0004\u0018\u00010$0$H\u0096\u0001J\u001E\u0010D\u001A\u00020\u00072\u0006\u0010E\u001A\u00020C2\f\u0010F\u001A\b\u0012\u0004\u0012\u00020$0&H\u0017J\u0019\u0010G\u001A\u00020\u00072\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010;0;H\u0096\u0001J\u0019\u0010H\u001A\u00020\u00072\u000E\u0010\t\u001A\n \r*\u0004\u0018\u00010=0=H\u0096\u0001R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006I"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper$KeepAliveCursor;", "Landroid/database/Cursor;", "delegate", "autoCloser", "Landroidx/room/AutoCloser;", "(Landroid/database/Cursor;Landroidx/room/AutoCloser;)V", "close", "", "copyStringToBuffer", "p0", "", "p1", "Landroid/database/CharArrayBuffer;", "kotlin.jvm.PlatformType", "deactivate", "getBlob", "", "getColumnCount", "getColumnIndex", "", "getColumnIndexOrThrow", "getColumnName", "getColumnNames", "", "()[Ljava/lang/String;", "getCount", "getDouble", "", "getExtras", "Landroid/os/Bundle;", "getFloat", "", "getInt", "getLong", "", "getNotificationUri", "Landroid/net/Uri;", "getNotificationUris", "", "getPosition", "getShort", "", "getString", "getType", "getWantsAllOnMoveCalls", "", "isAfterLast", "isBeforeFirst", "isClosed", "isFirst", "isLast", "isNull", "move", "moveToFirst", "moveToLast", "moveToNext", "moveToPosition", "moveToPrevious", "registerContentObserver", "Landroid/database/ContentObserver;", "registerDataSetObserver", "Landroid/database/DataSetObserver;", "requery", "respond", "setExtras", "extras", "setNotificationUri", "Landroid/content/ContentResolver;", "setNotificationUris", "cr", "uris", "unregisterContentObserver", "unregisterDataSetObserver", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class KeepAliveCursor implements Cursor, AutoCloseable {
        private final AutoCloser autoCloser;
        private final Cursor delegate;

        public KeepAliveCursor(Cursor cursor0, AutoCloser autoCloser0) {
            Intrinsics.checkNotNullParameter(cursor0, "delegate");
            Intrinsics.checkNotNullParameter(autoCloser0, "autoCloser");
            super();
            this.delegate = cursor0;
            this.autoCloser = autoCloser0;
        }

        @Override  // android.database.Cursor
        public void close() {
            this.delegate.close();
            this.autoCloser.decrementCountAndScheduleClose();
        }

        @Override  // android.database.Cursor
        public void copyStringToBuffer(int v, CharArrayBuffer charArrayBuffer0) {
            this.delegate.copyStringToBuffer(v, charArrayBuffer0);
        }

        @Override  // android.database.Cursor
        @Deprecated(message = "Deprecated in Java")
        public void deactivate() {
            this.delegate.deactivate();
        }

        @Override  // android.database.Cursor
        public byte[] getBlob(int v) {
            return this.delegate.getBlob(v);
        }

        @Override  // android.database.Cursor
        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        @Override  // android.database.Cursor
        public int getColumnIndex(String s) {
            return this.delegate.getColumnIndex(s);
        }

        @Override  // android.database.Cursor
        public int getColumnIndexOrThrow(String s) {
            return this.delegate.getColumnIndexOrThrow(s);
        }

        @Override  // android.database.Cursor
        public String getColumnName(int v) {
            return this.delegate.getColumnName(v);
        }

        @Override  // android.database.Cursor
        public String[] getColumnNames() {
            return this.delegate.getColumnNames();
        }

        @Override  // android.database.Cursor
        public int getCount() {
            return this.delegate.getCount();
        }

        @Override  // android.database.Cursor
        public double getDouble(int v) {
            return this.delegate.getDouble(v);
        }

        @Override  // android.database.Cursor
        public Bundle getExtras() {
            return this.delegate.getExtras();
        }

        @Override  // android.database.Cursor
        public float getFloat(int v) {
            return this.delegate.getFloat(v);
        }

        @Override  // android.database.Cursor
        public int getInt(int v) {
            return this.delegate.getInt(v);
        }

        @Override  // android.database.Cursor
        public long getLong(int v) {
            return this.delegate.getLong(v);
        }

        @Override  // android.database.Cursor
        public Uri getNotificationUri() {
            return Api19Impl.getNotificationUri(this.delegate);
        }

        @Override  // android.database.Cursor
        public List getNotificationUris() {
            return Api29Impl.getNotificationUris(this.delegate);
        }

        @Override  // android.database.Cursor
        public int getPosition() {
            return this.delegate.getPosition();
        }

        @Override  // android.database.Cursor
        public short getShort(int v) {
            return this.delegate.getShort(v);
        }

        @Override  // android.database.Cursor
        public String getString(int v) {
            return this.delegate.getString(v);
        }

        @Override  // android.database.Cursor
        public int getType(int v) {
            return this.delegate.getType(v);
        }

        @Override  // android.database.Cursor
        public boolean getWantsAllOnMoveCalls() {
            return this.delegate.getWantsAllOnMoveCalls();
        }

        @Override  // android.database.Cursor
        public boolean isAfterLast() {
            return this.delegate.isAfterLast();
        }

        @Override  // android.database.Cursor
        public boolean isBeforeFirst() {
            return this.delegate.isBeforeFirst();
        }

        @Override  // android.database.Cursor
        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        @Override  // android.database.Cursor
        public boolean isFirst() {
            return this.delegate.isFirst();
        }

        @Override  // android.database.Cursor
        public boolean isLast() {
            return this.delegate.isLast();
        }

        @Override  // android.database.Cursor
        public boolean isNull(int v) {
            return this.delegate.isNull(v);
        }

        @Override  // android.database.Cursor
        public boolean move(int v) {
            return this.delegate.move(v);
        }

        @Override  // android.database.Cursor
        public boolean moveToFirst() {
            return this.delegate.moveToFirst();
        }

        @Override  // android.database.Cursor
        public boolean moveToLast() {
            return this.delegate.moveToLast();
        }

        @Override  // android.database.Cursor
        public boolean moveToNext() {
            return this.delegate.moveToNext();
        }

        @Override  // android.database.Cursor
        public boolean moveToPosition(int v) {
            return this.delegate.moveToPosition(v);
        }

        @Override  // android.database.Cursor
        public boolean moveToPrevious() {
            return this.delegate.moveToPrevious();
        }

        @Override  // android.database.Cursor
        public void registerContentObserver(ContentObserver contentObserver0) {
            this.delegate.registerContentObserver(contentObserver0);
        }

        @Override  // android.database.Cursor
        public void registerDataSetObserver(DataSetObserver dataSetObserver0) {
            this.delegate.registerDataSetObserver(dataSetObserver0);
        }

        @Override  // android.database.Cursor
        @Deprecated(message = "Deprecated in Java")
        public boolean requery() {
            return this.delegate.requery();
        }

        @Override  // android.database.Cursor
        public Bundle respond(Bundle bundle0) {
            return this.delegate.respond(bundle0);
        }

        @Override  // android.database.Cursor
        public void setExtras(Bundle bundle0) {
            Intrinsics.checkNotNullParameter(bundle0, "extras");
            Api23Impl.setExtras(this.delegate, bundle0);
        }

        @Override  // android.database.Cursor
        public void setNotificationUri(ContentResolver contentResolver0, Uri uri0) {
            this.delegate.setNotificationUri(contentResolver0, uri0);
        }

        @Override  // android.database.Cursor
        public void setNotificationUris(ContentResolver contentResolver0, List list0) {
            Intrinsics.checkNotNullParameter(contentResolver0, "cr");
            Intrinsics.checkNotNullParameter(list0, "uris");
            Api29Impl.setNotificationUris(this.delegate, contentResolver0, list0);
        }

        @Override  // android.database.Cursor
        public void unregisterContentObserver(ContentObserver contentObserver0) {
            this.delegate.unregisterContentObserver(contentObserver0);
        }

        @Override  // android.database.Cursor
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver0) {
            this.delegate.unregisterDataSetObserver(dataSetObserver0);
        }
    }

    public final AutoCloser autoCloser;
    private final AutoClosingSupportSQLiteDatabase autoClosingDb;
    private final SupportSQLiteOpenHelper delegate;

    public AutoClosingRoomOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper0, AutoCloser autoCloser0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper0, "delegate");
        Intrinsics.checkNotNullParameter(autoCloser0, "autoCloser");
        super();
        this.delegate = supportSQLiteOpenHelper0;
        this.autoCloser = autoCloser0;
        autoCloser0.init(this.getDelegate());
        this.autoClosingDb = new AutoClosingSupportSQLiteDatabase(autoCloser0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void close() {
        this.autoClosingDb.close();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.delegate.getDatabaseName();
    }

    @Override  // androidx.room.DelegatingOpenHelper
    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getReadableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.delegate.setWriteAheadLoggingEnabled(z);
    }
}

