package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001D\u0012\u0006\u0010\u0003\u001A\u00020\u0001\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001A\u00020\u0016H\u0096\u0001J\u0011\u0010\u0017\u001A\u00020\u00162\u0006\u0010\u0018\u001A\u00020\u0019H\u0097\u0001R\u0014\u0010\t\u001A\u0004\u0018\u00010\nX\u0096\u0005¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u0014\u0010\u0003\u001A\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000F\u001A\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001A\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0012¨\u0006\u001A"}, d2 = {"Landroidx/room/QueryInterceptorOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DelegatingOpenHelper;", "delegate", "queryCallbackExecutor", "Ljava/util/concurrent/Executor;", "queryCallback", "Landroidx/room/RoomDatabase$QueryCallback;", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;Ljava/util/concurrent/Executor;Landroidx/room/RoomDatabase$QueryCallback;)V", "databaseName", "", "getDatabaseName", "()Ljava/lang/String;", "getDelegate", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "readableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getReadableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "getWritableDatabase", "close", "", "setWriteAheadLoggingEnabled", "enabled", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class QueryInterceptorOpenHelper implements DelegatingOpenHelper, SupportSQLiteOpenHelper, AutoCloseable {
    private final SupportSQLiteOpenHelper delegate;
    private final QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;

    public QueryInterceptorOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper0, Executor executor0, QueryCallback roomDatabase$QueryCallback0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper0, "delegate");
        Intrinsics.checkNotNullParameter(executor0, "queryCallbackExecutor");
        Intrinsics.checkNotNullParameter(roomDatabase$QueryCallback0, "queryCallback");
        super();
        this.delegate = supportSQLiteOpenHelper0;
        this.queryCallbackExecutor = executor0;
        this.queryCallback = roomDatabase$QueryCallback0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void close() {
        this.delegate.close();
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
        return new QueryInterceptorDatabase(this.getDelegate().getReadableDatabase(), this.queryCallbackExecutor, this.queryCallback);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        return new QueryInterceptorDatabase(this.getDelegate().getWritableDatabase(), this.queryCallbackExecutor, this.queryCallback);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.delegate.setWriteAheadLoggingEnabled(z);
    }
}

