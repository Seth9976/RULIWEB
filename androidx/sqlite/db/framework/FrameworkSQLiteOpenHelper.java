package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl;
import androidx.sqlite.db.SupportSQLiteCompat.Api21Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0003\"#$B5\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u0012\b\b\u0002\u0010\n\u001A\u00020\t¢\u0006\u0002\u0010\u000BJ\b\u0010\u001E\u001A\u00020\u001FH\u0016J\u0010\u0010 \u001A\u00020\u001F2\u0006\u0010!\u001A\u00020\tH\u0017R\u000E\u0010\n\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001A\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u001B\u0010\u000F\u001A\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\u001A\u0004\b\u0013\u0010\u0014*\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00100\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001A\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0019\u0010\u001AR\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001B\u001A\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001C\u0010\u001AR\u000E\u0010\u001D\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "context", "Landroid/content/Context;", "name", "", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "useNoBackupDirectory", "", "allowDataLossOnRecovery", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;ZZ)V", "databaseName", "getDatabaseName", "()Ljava/lang/String;", "delegate", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "getDelegate$delegate", "(Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper;)Ljava/lang/Object;", "getDelegate", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "lazyDelegate", "Lkotlin/Lazy;", "readableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getReadableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "getWritableDatabase", "writeAheadLoggingEnabled", "close", "", "setWriteAheadLoggingEnabled", "enabled", "Companion", "DBRefHolder", "OpenHelper", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper, AutoCloseable {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$Companion;", "", "()V", "TAG", "", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000F\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u001C\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "", "db", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "(Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;)V", "getDb", "()Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "setDb", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class DBRefHolder {
        private FrameworkSQLiteDatabase db;

        public DBRefHolder(FrameworkSQLiteDatabase frameworkSQLiteDatabase0) {
            this.db = frameworkSQLiteDatabase0;
        }

        public final FrameworkSQLiteDatabase getDb() {
            return this.db;
        }

        public final void setDb(FrameworkSQLiteDatabase frameworkSQLiteDatabase0) {
            this.db = frameworkSQLiteDatabase0;
        }
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u0000 /2\u00020\u0001:\u0003-./B/\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001A\u00020\u001AH\u0016J\u000E\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u001D\u001A\u00020\u000BJ\u000E\u0010\u001E\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020!J\u0010\u0010\"\u001A\u00020!2\u0006\u0010\u001D\u001A\u00020\u000BH\u0002J\u0010\u0010#\u001A\u00020!2\u0006\u0010\u001D\u001A\u00020\u000BH\u0002J\u0010\u0010$\u001A\u00020\u001A2\u0006\u0010%\u001A\u00020!H\u0016J\u0010\u0010&\u001A\u00020\u001A2\u0006\u0010 \u001A\u00020!H\u0016J \u0010\'\u001A\u00020\u001A2\u0006\u0010%\u001A\u00020!2\u0006\u0010(\u001A\u00020)2\u0006\u0010*\u001A\u00020)H\u0016J\u0010\u0010+\u001A\u00020\u001A2\u0006\u0010%\u001A\u00020!H\u0016J \u0010,\u001A\u00020\u001A2\u0006\u0010 \u001A\u00020!2\u0006\u0010(\u001A\u00020)2\u0006\u0010*\u001A\u00020)H\u0016R\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u000E\u0010\u0015\u001A\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "name", "", "dbRef", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "allowDataLossOnRecovery", "", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;Z)V", "getAllowDataLossOnRecovery", "()Z", "getCallback", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "getContext", "()Landroid/content/Context;", "getDbRef", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "lock", "Landroidx/sqlite/util/ProcessLock;", "migrated", "opened", "close", "", "getSupportDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "writable", "getWrappedDb", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "sqLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "getWritableOrReadableDatabase", "innerGetDatabase", "onConfigure", "db", "onCreate", "onDowngrade", "oldVersion", "", "newVersion", "onOpen", "onUpgrade", "CallbackException", "CallbackName", "Companion", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class OpenHelper extends SQLiteOpenHelper implements AutoCloseable {
        @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001A\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\f"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "callbackName", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "cause", "", "(Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;Ljava/lang/Throwable;)V", "getCallbackName", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "getCause", "()Ljava/lang/Throwable;", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        static final class CallbackException extends RuntimeException {
            private final CallbackName callbackName;
            private final Throwable cause;

            public CallbackException(CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName0, Throwable throwable0) {
                Intrinsics.checkNotNullParameter(frameworkSQLiteOpenHelper$OpenHelper$CallbackName0, "callbackName");
                Intrinsics.checkNotNullParameter(throwable0, "cause");
                super(throwable0);
                this.callbackName = frameworkSQLiteOpenHelper$OpenHelper$CallbackName0;
                this.cause = throwable0;
            }

            public final CallbackName getCallbackName() {
                return this.callbackName;
            }

            @Override
            public Throwable getCause() {
                return this.cause;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "", "(Ljava/lang/String;I)V", "ON_CONFIGURE", "ON_CREATE", "ON_UPGRADE", "ON_DOWNGRADE", "ON_OPEN", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static enum CallbackName {
            ON_CONFIGURE,
            ON_CREATE,
            ON_UPGRADE,
            ON_DOWNGRADE,
            ON_OPEN;

            private static final CallbackName[] $values() [...] // Inlined contents
        }

        @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b¨\u0006\t"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$Companion;", "", "()V", "getWrappedDb", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "refHolder", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "sqLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.Companion {
            private androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.Companion() {
            }

            public androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final FrameworkSQLiteDatabase getWrappedDb(DBRefHolder frameworkSQLiteOpenHelper$DBRefHolder0, SQLiteDatabase sQLiteDatabase0) {
                Intrinsics.checkNotNullParameter(frameworkSQLiteOpenHelper$DBRefHolder0, "refHolder");
                Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sqLiteDatabase");
                FrameworkSQLiteDatabase frameworkSQLiteDatabase0 = frameworkSQLiteOpenHelper$DBRefHolder0.getDb();
                if(frameworkSQLiteDatabase0 != null && frameworkSQLiteDatabase0.isDelegate(sQLiteDatabase0)) {
                    return frameworkSQLiteDatabase0;
                }
                FrameworkSQLiteDatabase frameworkSQLiteDatabase1 = new FrameworkSQLiteDatabase(sQLiteDatabase0);
                frameworkSQLiteOpenHelper$DBRefHolder0.setDb(frameworkSQLiteDatabase1);
                return frameworkSQLiteDatabase1;
            }
        }

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[CallbackName.values().length];
                try {
                    arr_v[CallbackName.ON_CONFIGURE.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[CallbackName.ON_CREATE.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[CallbackName.ON_UPGRADE.ordinal()] = 3;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[CallbackName.ON_DOWNGRADE.ordinal()] = 4;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[CallbackName.ON_OPEN.ordinal()] = 5;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        public static final androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.Companion Companion;
        private final boolean allowDataLossOnRecovery;
        private final Callback callback;
        private final Context context;
        private final DBRefHolder dbRef;
        private final ProcessLock lock;
        private boolean migrated;
        private boolean opened;

        // 检测为 Lambda 实现
        public static void $r8$lambda$HkATHkNnDWiWKR94KFirzR7ST-8(Callback supportSQLiteOpenHelper$Callback0, DBRefHolder frameworkSQLiteOpenHelper$DBRefHolder0, SQLiteDatabase sQLiteDatabase0) [...]

        static {
            OpenHelper.Companion = new androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.Companion(null);
        }

        public OpenHelper(Context context0, String s, DBRefHolder frameworkSQLiteOpenHelper$DBRefHolder0, Callback supportSQLiteOpenHelper$Callback0, boolean z) {
            Intrinsics.checkNotNullParameter(context0, "context");
            String s1;
            Intrinsics.checkNotNullParameter(frameworkSQLiteOpenHelper$DBRefHolder0, "dbRef");
            Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "callback");
            FrameworkSQLiteOpenHelper.OpenHelper..ExternalSyntheticLambda0 frameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda00 = (SQLiteDatabase sQLiteDatabase0) -> OpenHelper._init_$lambda$0(supportSQLiteOpenHelper$Callback0, frameworkSQLiteOpenHelper$DBRefHolder0, sQLiteDatabase0);
            super(context0, s, null, supportSQLiteOpenHelper$Callback0.version, frameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda00);
            this.context = context0;
            this.dbRef = frameworkSQLiteOpenHelper$DBRefHolder0;
            this.callback = supportSQLiteOpenHelper$Callback0;
            this.allowDataLossOnRecovery = z;
            if(s == null) {
                s1 = "e2653333-45b9-44b8-8561-5634402b13d6";
                Intrinsics.checkNotNullExpressionValue("e2653333-45b9-44b8-8561-5634402b13d6", "randomUUID().toString()");
            }
            else {
                s1 = s;
            }
            this.lock = new ProcessLock(s1, context0.getCacheDir(), false);
        }

        private static final void _init_$lambda$0(Callback supportSQLiteOpenHelper$Callback0, DBRefHolder frameworkSQLiteOpenHelper$DBRefHolder0, SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "$callback");
            Intrinsics.checkNotNullParameter(frameworkSQLiteOpenHelper$DBRefHolder0, "$dbRef");
            Intrinsics.checkNotNullExpressionValue(sQLiteDatabase0, "dbObj");
            supportSQLiteOpenHelper$Callback0.onCorruption(OpenHelper.Companion.getWrappedDb(frameworkSQLiteOpenHelper$DBRefHolder0, sQLiteDatabase0));
        }

        @Override  // android.database.sqlite.SQLiteOpenHelper
        public void close() {
            try {
                ProcessLock.lock$default(this.lock, false, 1, null);
                super.close();
                this.dbRef.setDb(null);
                this.opened = false;
            }
            finally {
                this.lock.unlock();
            }
        }

        public final boolean getAllowDataLossOnRecovery() {
            return this.allowDataLossOnRecovery;
        }

        public final Callback getCallback() {
            return this.callback;
        }

        public final Context getContext() {
            return this.context;
        }

        public final DBRefHolder getDbRef() {
            return this.dbRef;
        }

        public final SupportSQLiteDatabase getSupportDatabase(boolean z) {
            try {
                boolean z1 = !this.opened && this.getDatabaseName() != null;
                this.lock.lock(z1);
                this.migrated = false;
                return this.getWrappedDb(this.innerGetDatabase(z));
            }
            finally {
                this.lock.unlock();
            }
        }

        public final FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sqLiteDatabase");
            return OpenHelper.Companion.getWrappedDb(this.dbRef, sQLiteDatabase0);
        }

        private final SQLiteDatabase getWritableOrReadableDatabase(boolean z) {
            if(z) {
                SQLiteDatabase sQLiteDatabase0 = super.getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(sQLiteDatabase0, "{\n                super.…eDatabase()\n            }");
                return sQLiteDatabase0;
            }
            SQLiteDatabase sQLiteDatabase1 = super.getReadableDatabase();
            Intrinsics.checkNotNullExpressionValue(sQLiteDatabase1, "{\n                super.…eDatabase()\n            }");
            return sQLiteDatabase1;
        }

        private final SQLiteDatabase innerGetDatabase(boolean z) {
            String s = this.getDatabaseName();
            if(s != null && !this.opened) {
                File file0 = this.context.getDatabasePath(s).getParentFile();
                if(file0 != null) {
                    file0.mkdirs();
                    if(!file0.isDirectory()) {
                        Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + file0);
                    }
                }
            }
            try {
                return this.getWritableOrReadableDatabase(z);
            }
            catch(Throwable unused_ex) {
                super.close();
                try {
                    Thread.sleep(500L);
                }
                catch(InterruptedException unused_ex) {
                }
                try {
                    return this.getWritableOrReadableDatabase(z);
                }
                catch(Throwable throwable0) {
                }
            }
            super.close();
            if(throwable0 instanceof CallbackException) {
                Throwable throwable1 = ((CallbackException)throwable0).getCause();
                switch(WhenMappings.$EnumSwitchMapping$0[((CallbackException)throwable0).getCallbackName().ordinal()]) {
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: {
                        throw throwable1;
                    label_18:
                        if(!(throwable1 instanceof SQLiteException)) {
                            throw throwable1;
                        }
                        goto label_21;
                    }
                    default: {
                        goto label_18;
                    }
                }
            }
            if(throwable0 instanceof SQLiteException && s != null && this.allowDataLossOnRecovery) {
            label_21:
                this.context.deleteDatabase(s);
                try {
                    return this.getWritableOrReadableDatabase(z);
                }
                catch(CallbackException frameworkSQLiteOpenHelper$OpenHelper$CallbackException0) {
                    throw frameworkSQLiteOpenHelper$OpenHelper$CallbackException0.getCause();
                }
            }
            throw throwable0;
        }

        @Override  // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "db");
            if(!this.migrated) {
                int v = sQLiteDatabase0.getVersion();
                if(this.callback.version != v) {
                    sQLiteDatabase0.setMaxSqlCacheSize(1);
                }
            }
            try {
                SupportSQLiteDatabase supportSQLiteDatabase0 = this.getWrappedDb(sQLiteDatabase0);
                this.callback.onConfigure(supportSQLiteDatabase0);
            }
            catch(Throwable throwable0) {
                throw new CallbackException(CallbackName.ON_CONFIGURE, throwable0);
            }
        }

        @Override  // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sqLiteDatabase");
            try {
                SupportSQLiteDatabase supportSQLiteDatabase0 = this.getWrappedDb(sQLiteDatabase0);
                this.callback.onCreate(supportSQLiteDatabase0);
            }
            catch(Throwable throwable0) {
                throw new CallbackException(CallbackName.ON_CREATE, throwable0);
            }
        }

        @Override  // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "db");
            this.migrated = true;
            try {
                SupportSQLiteDatabase supportSQLiteDatabase0 = this.getWrappedDb(sQLiteDatabase0);
                this.callback.onDowngrade(supportSQLiteDatabase0, v, v1);
            }
            catch(Throwable throwable0) {
                throw new CallbackException(CallbackName.ON_DOWNGRADE, throwable0);
            }
        }

        @Override  // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "db");
            if(!this.migrated) {
                try {
                    SupportSQLiteDatabase supportSQLiteDatabase0 = this.getWrappedDb(sQLiteDatabase0);
                    this.callback.onOpen(supportSQLiteDatabase0);
                }
                catch(Throwable throwable0) {
                    throw new CallbackException(CallbackName.ON_OPEN, throwable0);
                }
            }
            this.opened = true;
        }

        @Override  // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sqLiteDatabase");
            this.migrated = true;
            try {
                SupportSQLiteDatabase supportSQLiteDatabase0 = this.getWrappedDb(sQLiteDatabase0);
                this.callback.onUpgrade(supportSQLiteDatabase0, v, v1);
            }
            catch(Throwable throwable0) {
                throw new CallbackException(CallbackName.ON_UPGRADE, throwable0);
            }
        }
    }

    public static final Companion Companion = null;
    private static final String TAG = "SupportSQLite";
    private final boolean allowDataLossOnRecovery;
    private final Callback callback;
    private final Context context;
    private final Lazy lazyDelegate;
    private final String name;
    private final boolean useNoBackupDirectory;
    private boolean writeAheadLoggingEnabled;

    static {
        FrameworkSQLiteOpenHelper.Companion = new Companion(null);
    }

    public FrameworkSQLiteOpenHelper(Context context0, String s, Callback supportSQLiteOpenHelper$Callback0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "callback");
        this(context0, s, supportSQLiteOpenHelper$Callback0, false, false, 24, null);
    }

    public FrameworkSQLiteOpenHelper(Context context0, String s, Callback supportSQLiteOpenHelper$Callback0, boolean z) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "callback");
        this(context0, s, supportSQLiteOpenHelper$Callback0, z, false, 16, null);
    }

    public FrameworkSQLiteOpenHelper(Context context0, String s, Callback supportSQLiteOpenHelper$Callback0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "callback");
        super();
        this.context = context0;
        this.name = s;
        this.callback = supportSQLiteOpenHelper$Callback0;
        this.useNoBackupDirectory = z;
        this.allowDataLossOnRecovery = z1;
        this.lazyDelegate = LazyKt.lazy(new Function0() {
            {
                FrameworkSQLiteOpenHelper.this = frameworkSQLiteOpenHelper0;
                super(0);
            }

            public final OpenHelper invoke() {
                OpenHelper frameworkSQLiteOpenHelper$OpenHelper0;
                if(Build.VERSION.SDK_INT < 23 || FrameworkSQLiteOpenHelper.this.name == null || !FrameworkSQLiteOpenHelper.this.useNoBackupDirectory) {
                    frameworkSQLiteOpenHelper$OpenHelper0 = new OpenHelper(FrameworkSQLiteOpenHelper.this.context, FrameworkSQLiteOpenHelper.this.name, new DBRefHolder(null), FrameworkSQLiteOpenHelper.this.callback, FrameworkSQLiteOpenHelper.this.allowDataLossOnRecovery);
                }
                else {
                    File file0 = new File(Api21Impl.getNoBackupFilesDir(FrameworkSQLiteOpenHelper.this.context), FrameworkSQLiteOpenHelper.this.name);
                    frameworkSQLiteOpenHelper$OpenHelper0 = new OpenHelper(FrameworkSQLiteOpenHelper.this.context, file0.getAbsolutePath(), new DBRefHolder(null), FrameworkSQLiteOpenHelper.this.callback, FrameworkSQLiteOpenHelper.this.allowDataLossOnRecovery);
                }
                Api16Impl.setWriteAheadLoggingEnabled(frameworkSQLiteOpenHelper$OpenHelper0, FrameworkSQLiteOpenHelper.this.writeAheadLoggingEnabled);
                return frameworkSQLiteOpenHelper$OpenHelper0;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    public FrameworkSQLiteOpenHelper(Context context0, String s, Callback supportSQLiteOpenHelper$Callback0, boolean z, boolean z1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            z = false;
        }
        this(context0, s, supportSQLiteOpenHelper$Callback0, z, ((v & 16) == 0 ? z1 : false));
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void close() {
        if(this.lazyDelegate.isInitialized()) {
            this.getDelegate().close();
        }
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.name;
    }

    private final OpenHelper getDelegate() {
        return (OpenHelper)this.lazyDelegate.getValue();
    }

    private static Object getDelegate$delegate(FrameworkSQLiteOpenHelper frameworkSQLiteOpenHelper0) {
        return frameworkSQLiteOpenHelper0.lazyDelegate;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getReadableDatabase() {
        return this.getDelegate().getSupportDatabase(false);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        return this.getDelegate().getSupportDatabase(true);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        if(this.lazyDelegate.isInitialized()) {
            Api16Impl.setWriteAheadLoggingEnabled(this.getDelegate(), z);
        }
        this.writeAheadLoggingEnabled = z;
    }
}

