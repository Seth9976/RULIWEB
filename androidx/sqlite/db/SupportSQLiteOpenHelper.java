package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.util.Pair;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0011\u0012\u0013J\b\u0010\f\u001A\u00020\rH&J\u0010\u0010\u000E\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0010H\'R\u0014\u0010\u0002\u001A\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001A\u00020\u0007X¦\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u0012\u0010\n\u001A\u00020\u0007X¦\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Ljava/io/Closeable;", "databaseName", "", "getDatabaseName", "()Ljava/lang/String;", "readableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getReadableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "getWritableDatabase", "close", "", "setWriteAheadLoggingEnabled", "enabled", "", "Callback", "Configuration", "Factory", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SupportSQLiteOpenHelper extends Closeable {
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0002J\u0010\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u0010\u0010\f\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u0010\u0010\r\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000BH&J \u0010\u000E\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u00032\u0006\u0010\u0010\u001A\u00020\u0003H\u0016J\u0010\u0010\u0011\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000BH\u0016J \u0010\u0012\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u00032\u0006\u0010\u0010\u001A\u00020\u0003H&R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "", "version", "", "(I)V", "deleteDatabaseFile", "", "fileName", "", "onConfigure", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "onCorruption", "onCreate", "onDowngrade", "oldVersion", "newVersion", "onOpen", "onUpgrade", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Callback {
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback$Companion;", "", "()V", "TAG", "", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final Companion Companion = null;
        private static final String TAG = "SupportSQLite";
        public final int version;

        static {
            Callback.Companion = new Companion(null);
        }

        public Callback(int v) {
            this.version = v;
        }

        private final void deleteDatabaseFile(String s) {
            if(!StringsKt.equals(s, ":memory:", true)) {
                int v = s.length() - 1;
                int v1 = 0;
                boolean z = false;
                while(v1 <= v) {
                    boolean z1 = Intrinsics.compare(s.charAt((z ? v : v1)), 0x20) <= 0;
                    if(z) {
                        if(!z1) {
                            break;
                        }
                        --v;
                    }
                    else if(z1) {
                        ++v1;
                    }
                    else {
                        z = true;
                    }
                }
                if(s.subSequence(v1, v + 1).toString().length() == 0) {
                    return;
                }
                Log.w("SupportSQLite", "deleting the database file: " + s);
                try {
                    Api16Impl.deleteDatabase(new File(s));
                }
                catch(Exception exception0) {
                    Log.w("SupportSQLite", "delete failed: ", exception0);
                }
            }
        }

        public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }

        public void onCorruption(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
            Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + supportSQLiteDatabase0 + ".path");
            if(supportSQLiteDatabase0.isOpen()) {
                try {
                    try {
                        List list0 = null;
                        list0 = supportSQLiteDatabase0.getAttachedDbs();
                    }
                    catch(SQLiteException unused_ex) {
                    }
                    try {
                        supportSQLiteDatabase0.close();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                catch(Throwable throwable0) {
                    if(list0 == null) {
                        String s1 = supportSQLiteDatabase0.getPath();
                        if(s1 != null) {
                            this.deleteDatabaseFile(s1);
                        }
                    }
                    else {
                        for(Object object0: list0) {
                            Object object1 = ((Pair)object0).second;
                            Intrinsics.checkNotNullExpressionValue(object1, "p.second");
                            this.deleteDatabaseFile(((String)object1));
                        }
                    }
                    throw throwable0;
                }
                if(list0 != null) {
                    for(Object object2: list0) {
                        Object object3 = ((Pair)object2).second;
                        Intrinsics.checkNotNullExpressionValue(object3, "p.second");
                        this.deleteDatabaseFile(((String)object3));
                    }
                    return;
                }
                String s2 = supportSQLiteDatabase0.getPath();
                if(s2 != null) {
                    this.deleteDatabaseFile(s2);
                }
            }
            else {
                String s = supportSQLiteDatabase0.getPath();
                if(s != null) {
                    this.deleteDatabaseFile(s);
                }
            }
        }

        public abstract void onCreate(SupportSQLiteDatabase arg1);

        public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase0, int v, int v1) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
            throw new SQLiteException("Can\'t downgrade database from version " + v + " to " + v1);
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }

        public abstract void onUpgrade(SupportSQLiteDatabase arg1, int arg2, int arg3);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0002\f\rB3\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u0012\b\b\u0002\u0010\n\u001A\u00020\t¢\u0006\u0002\u0010\u000BR\u0010\u0010\n\u001A\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001A\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "", "context", "Landroid/content/Context;", "name", "", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "useNoBackupDirectory", "", "allowDataLossOnRecovery", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;ZZ)V", "Builder", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Configuration {
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00002\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\b\u0010\f\u001A\u00020\rH\u0016J\u0010\u0010\u0007\u001A\u00020\u00002\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0012\u0010\t\u001A\u00020\u00002\b\u0010\t\u001A\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000E\u001A\u00020\u00002\u0006\u0010\u000B\u001A\u00020\u0006H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "allowDataLossOnRecovery", "", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "name", "", "useNoBackupDirectory", "build", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "noBackupDirectory", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static class Builder {
            private boolean allowDataLossOnRecovery;
            private Callback callback;
            private final Context context;
            private String name;
            private boolean useNoBackupDirectory;

            public Builder(Context context0) {
                Intrinsics.checkNotNullParameter(context0, "context");
                super();
                this.context = context0;
            }

            public Builder allowDataLossOnRecovery(boolean z) {
                this.allowDataLossOnRecovery = z;
                return this;
            }

            public Configuration build() {
                Callback supportSQLiteOpenHelper$Callback0 = this.callback;
                if(supportSQLiteOpenHelper$Callback0 == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                if(this.useNoBackupDirectory && (this.name == null || this.name.length() == 0)) {
                    throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                }
                return new Configuration(this.context, this.name, supportSQLiteOpenHelper$Callback0, this.useNoBackupDirectory, this.allowDataLossOnRecovery);
            }

            public Builder callback(Callback supportSQLiteOpenHelper$Callback0) {
                Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "callback");
                this.callback = supportSQLiteOpenHelper$Callback0;
                return this;
            }

            public Builder name(String s) {
                this.name = s;
                return this;
            }

            public Builder noBackupDirectory(boolean z) {
                this.useNoBackupDirectory = z;
                return this;
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Companion;", "", "()V", "builder", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder;", "context", "Landroid/content/Context;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Companion {
            private androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Companion() {
            }

            public androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            @JvmStatic
            public final Builder builder(Context context0) {
                Intrinsics.checkNotNullParameter(context0, "context");
                return new Builder(context0);
            }
        }

        public static final androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Companion Companion;
        public final boolean allowDataLossOnRecovery;
        public final Callback callback;
        public final Context context;
        public final String name;
        public final boolean useNoBackupDirectory;

        static {
            Configuration.Companion = new androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Companion(null);
        }

        public Configuration(Context context0, String s, Callback supportSQLiteOpenHelper$Callback0, boolean z, boolean z1) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Callback0, "callback");
            super();
            this.context = context0;
            this.name = s;
            this.callback = supportSQLiteOpenHelper$Callback0;
            this.useNoBackupDirectory = z;
            this.allowDataLossOnRecovery = z1;
        }

        public Configuration(Context context0, String s, Callback supportSQLiteOpenHelper$Callback0, boolean z, boolean z1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 8) != 0) {
                z = false;
            }
            this(context0, s, supportSQLiteOpenHelper$Callback0, z, ((v & 16) == 0 ? z1 : false));
        }

        @JvmStatic
        public static final Builder builder(Context context0) {
            return Configuration.Companion.builder(context0);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "", "create", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration arg1);
    }

    @Override
    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean arg1);
}

