package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u0007H\u0016R\u001D\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u000E"}, d2 = {"Landroidx/room/migration/MigrationImpl;", "Landroidx/room/migration/Migration;", "startVersion", "", "endVersion", "migrateCallback", "Lkotlin/Function1;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "", "(IILkotlin/jvm/functions/Function1;)V", "getMigrateCallback", "()Lkotlin/jvm/functions/Function1;", "migrate", "db", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class MigrationImpl extends Migration {
    private final Function1 migrateCallback;

    public MigrationImpl(int v, int v1, Function1 function10) {
        super(v, v1);
        this.migrateCallback = function10;
    }

    public final Function1 getMigrateCallback() {
        return this.migrateCallback;
    }

    @Override  // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase0) {
        this.migrateCallback.invoke(supportSQLiteDatabase0);
    }
}

