package androidx.room;

import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelperFactory;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "delegate", "autoCloser", "Landroidx/room/AutoCloser;", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/AutoCloser;)V", "create", "Landroidx/room/AutoClosingRoomOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AutoClosingRoomOpenHelperFactory implements Factory {
    private final AutoCloser autoCloser;
    private final Factory delegate;

    public AutoClosingRoomOpenHelperFactory(Factory supportSQLiteOpenHelper$Factory0, AutoCloser autoCloser0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "delegate");
        Intrinsics.checkNotNullParameter(autoCloser0, "autoCloser");
        super();
        this.delegate = supportSQLiteOpenHelper$Factory0;
        this.autoCloser = autoCloser0;
    }

    public AutoClosingRoomOpenHelper create(Configuration supportSQLiteOpenHelper$Configuration0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Configuration0, "configuration");
        return new AutoClosingRoomOpenHelper(this.delegate.create(supportSQLiteOpenHelper$Configuration0), this.autoCloser);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Factory
    public SupportSQLiteOpenHelper create(Configuration supportSQLiteOpenHelper$Configuration0) {
        return this.create(supportSQLiteOpenHelper$Configuration0);
    }
}

