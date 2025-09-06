package androidx.room;

import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/room/QueryInterceptorOpenHelperFactory;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "delegate", "queryCallbackExecutor", "Ljava/util/concurrent/Executor;", "queryCallback", "Landroidx/room/RoomDatabase$QueryCallback;", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Ljava/util/concurrent/Executor;Landroidx/room/RoomDatabase$QueryCallback;)V", "create", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class QueryInterceptorOpenHelperFactory implements Factory {
    private final Factory delegate;
    private final QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;

    public QueryInterceptorOpenHelperFactory(Factory supportSQLiteOpenHelper$Factory0, Executor executor0, QueryCallback roomDatabase$QueryCallback0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "delegate");
        Intrinsics.checkNotNullParameter(executor0, "queryCallbackExecutor");
        Intrinsics.checkNotNullParameter(roomDatabase$QueryCallback0, "queryCallback");
        super();
        this.delegate = supportSQLiteOpenHelper$Factory0;
        this.queryCallbackExecutor = executor0;
        this.queryCallback = roomDatabase$QueryCallback0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Factory
    public SupportSQLiteOpenHelper create(Configuration supportSQLiteOpenHelper$Configuration0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Configuration0, "configuration");
        return new QueryInterceptorOpenHelper(this.delegate.create(supportSQLiteOpenHelper$Configuration0), this.queryCallbackExecutor, this.queryCallback);
    }
}

