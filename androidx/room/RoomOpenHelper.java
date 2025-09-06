package androidx.room;

import android.database.Cursor;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import java.io.Closeable;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0017\u0018\u0000 \u00192\u00020\u0001:\u0003\u0019\u001A\u001BB\u001F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\t\u001A\u00020\u0007\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0010\u0010\u000F\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0010\u0010\u0010\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J\u0010\u0010\u0011\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J \u0010\u0012\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0014H\u0016J\u0010\u0010\u0016\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J \u0010\u0017\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0014H\u0016J\u0010\u0010\u0018\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002R\u0010\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Landroidx/room/RoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "configuration", "Landroidx/room/DatabaseConfiguration;", "delegate", "Landroidx/room/RoomOpenHelper$Delegate;", "legacyHash", "", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;)V", "identityHash", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;Ljava/lang/String;)V", "checkIdentity", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "createMasterTableIfNotExists", "onConfigure", "onCreate", "onDowngrade", "oldVersion", "", "newVersion", "onOpen", "onUpgrade", "updateIdentity", "Companion", "Delegate", "ValidationResult", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class RoomOpenHelper extends Callback {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u0015\u0010\b\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/room/RoomOpenHelper$Companion;", "", "()V", "hasEmptySchema", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "hasEmptySchema$room_runtime_release", "hasRoomMasterTable", "hasRoomMasterTable$room_runtime_release", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final boolean hasEmptySchema$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase0) {
            boolean z = false;
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
            Closeable closeable0 = supportSQLiteDatabase0.query("SELECT count(*) FROM sqlite_master WHERE name != \'android_metadata\'");
            try {
                if(((Cursor)closeable0).moveToFirst() && ((Cursor)closeable0).getInt(0) == 0) {
                    z = true;
                }
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
            return z;
        }

        public final boolean hasRoomMasterTable$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase0) {
            boolean z = false;
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
            Closeable closeable0 = supportSQLiteDatabase0.query("SELECT 1 FROM sqlite_master WHERE type = \'table\' AND name=\'room_master_table\'");
            try {
                if(((Cursor)closeable0).moveToFirst() && ((Cursor)closeable0).getInt(0) != 0) {
                    z = true;
                }
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
            return z;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH&J\u0010\u0010\t\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH&J\u0010\u0010\n\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH&J\u0010\u0010\u000B\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH&J\u0010\u0010\f\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\r\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0015R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/room/RoomOpenHelper$Delegate;", "", "version", "", "(I)V", "createAllTables", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "dropAllTables", "onCreate", "onOpen", "onPostMigrate", "onPreMigrate", "onValidateSchema", "Landroidx/room/RoomOpenHelper$ValidationResult;", "validateMigration", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Delegate {
        public final int version;

        public Delegate(int v) {
            this.version = v;
        }

        public abstract void createAllTables(SupportSQLiteDatabase arg1);

        public abstract void dropAllTables(SupportSQLiteDatabase arg1);

        public abstract void onCreate(SupportSQLiteDatabase arg1);

        public abstract void onOpen(SupportSQLiteDatabase arg1);

        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }

        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }

        public ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
            this.validateMigration(supportSQLiteDatabase0);
            return new ValidationResult(true, null);
        }

        @Deprecated(message = "Use [onValidateSchema(SupportSQLiteDatabase)]")
        protected void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
            throw new UnsupportedOperationException("validateMigration is deprecated");
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001A\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/room/RoomOpenHelper$ValidationResult;", "", "isValid", "", "expectedFoundMsg", "", "(ZLjava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String s) {
            this.isValid = z;
            this.expectedFoundMsg = s;
        }
    }

    public static final Companion Companion;
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    static {
        RoomOpenHelper.Companion = new Companion(null);
    }

    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration0, Delegate roomOpenHelper$Delegate0, String s) {
        Intrinsics.checkNotNullParameter(databaseConfiguration0, "configuration");
        Intrinsics.checkNotNullParameter(roomOpenHelper$Delegate0, "delegate");
        Intrinsics.checkNotNullParameter(s, "legacyHash");
        this(databaseConfiguration0, roomOpenHelper$Delegate0, "", s);
    }

    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration0, Delegate roomOpenHelper$Delegate0, String s, String s1) {
        Intrinsics.checkNotNullParameter(databaseConfiguration0, "configuration");
        Intrinsics.checkNotNullParameter(roomOpenHelper$Delegate0, "delegate");
        Intrinsics.checkNotNullParameter(s, "identityHash");
        Intrinsics.checkNotNullParameter(s1, "legacyHash");
        super(roomOpenHelper$Delegate0.version);
        this.configuration = databaseConfiguration0;
        this.delegate = roomOpenHelper$Delegate0;
        this.identityHash = s;
        this.legacyHash = s1;
    }

    private final void checkIdentity(SupportSQLiteDatabase supportSQLiteDatabase0) {
        String s;
        if(RoomOpenHelper.Companion.hasRoomMasterTable$room_runtime_release(supportSQLiteDatabase0)) {
            Closeable closeable0 = supportSQLiteDatabase0.query(new SimpleSQLiteQuery("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
            try {
                s = ((Cursor)closeable0).moveToFirst() ? ((Cursor)closeable0).getString(0) : null;
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
            if(!Intrinsics.areEqual(this.identityHash, s) && !Intrinsics.areEqual(this.legacyHash, s)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you\'ve changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: " + this.identityHash + ", found: " + s);
            }
            return;
        }
        ValidationResult roomOpenHelper$ValidationResult0 = this.delegate.onValidateSchema(supportSQLiteDatabase0);
        if(!roomOpenHelper$ValidationResult0.isValid) {
            throw new IllegalStateException("Pre-packaged database has an invalid schema: " + roomOpenHelper$ValidationResult0.expectedFoundMsg);
        }
        this.delegate.onPostMigrate(supportSQLiteDatabase0);
        this.updateIdentity(supportSQLiteDatabase0);
    }

    private final void createMasterTableIfNotExists(SupportSQLiteDatabase supportSQLiteDatabase0) {
        supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        super.onConfigure(supportSQLiteDatabase0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
    public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        boolean z = RoomOpenHelper.Companion.hasEmptySchema$room_runtime_release(supportSQLiteDatabase0);
        this.delegate.createAllTables(supportSQLiteDatabase0);
        if(!z) {
            ValidationResult roomOpenHelper$ValidationResult0 = this.delegate.onValidateSchema(supportSQLiteDatabase0);
            if(!roomOpenHelper$ValidationResult0.isValid) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + roomOpenHelper$ValidationResult0.expectedFoundMsg);
            }
        }
        this.updateIdentity(supportSQLiteDatabase0);
        this.delegate.onCreate(supportSQLiteDatabase0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase0, int v, int v1) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        this.onUpgrade(supportSQLiteDatabase0, v, v1);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        super.onOpen(supportSQLiteDatabase0);
        this.checkIdentity(supportSQLiteDatabase0);
        this.delegate.onOpen(supportSQLiteDatabase0);
        this.configuration = null;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
    public void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase0, int v, int v1) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        DatabaseConfiguration databaseConfiguration0 = this.configuration;
        if(databaseConfiguration0 != null) {
            List list0 = databaseConfiguration0.migrationContainer.findMigrationPath(v, v1);
            if(list0 != null) {
                this.delegate.onPreMigrate(supportSQLiteDatabase0);
                for(Object object0: list0) {
                    ((Migration)object0).migrate(supportSQLiteDatabase0);
                }
                ValidationResult roomOpenHelper$ValidationResult0 = this.delegate.onValidateSchema(supportSQLiteDatabase0);
                if(!roomOpenHelper$ValidationResult0.isValid) {
                    throw new IllegalStateException("Migration didn\'t properly handle: " + roomOpenHelper$ValidationResult0.expectedFoundMsg);
                }
                this.delegate.onPostMigrate(supportSQLiteDatabase0);
                this.updateIdentity(supportSQLiteDatabase0);
                return;
            }
        }
        if(this.configuration == null || this.configuration.isMigrationRequired(v, v1)) {
            throw new IllegalStateException("A migration from " + v + " to " + v1 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
        }
        this.delegate.dropAllTables(supportSQLiteDatabase0);
        this.delegate.createAllTables(supportSQLiteDatabase0);
    }

    private final void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase0) {
        this.createMasterTableIfNotExists(supportSQLiteDatabase0);
        supportSQLiteDatabase0.execSQL(RoomMasterTable.createInsertQuery(this.identityHash));
    }
}

