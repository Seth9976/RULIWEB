package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\u0018\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u0018\u0010\u0013\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0014H\u0016J\u0018\u0010\u0015\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0016H\u0016J\u0010\u0010\u0017\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\u0018\u0010\u0018\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0004H\u0016J\b\u0010\u0019\u001A\u00020\u000EH\u0016J\t\u0010\u001A\u001A\u00020\u000EH\u0096\u0001J\b\u0010\u001B\u001A\u00020\u000EH\u0016J\b\u0010\u001C\u001A\u00020\u0016H\u0016J\b\u0010\u001D\u001A\u00020\u0010H\u0016J\u001A\u0010\u001E\u001A\u00020\u000E2\u0006\u0010\u001F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\fH\u0002J\b\u0010 \u001A\u00020\u0016H\u0016J\n\u0010!\u001A\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/room/QueryInterceptorStatement;", "Landroidx/sqlite/db/SupportSQLiteStatement;", "delegate", "sqlStatement", "", "queryCallbackExecutor", "Ljava/util/concurrent/Executor;", "queryCallback", "Landroidx/room/RoomDatabase$QueryCallback;", "(Landroidx/sqlite/db/SupportSQLiteStatement;Ljava/lang/String;Ljava/util/concurrent/Executor;Landroidx/room/RoomDatabase$QueryCallback;)V", "bindArgsCache", "", "", "bindBlob", "", "index", "", "value", "", "bindDouble", "", "bindLong", "", "bindNull", "bindString", "clearBindings", "close", "execute", "executeInsert", "executeUpdateDelete", "saveArgsToCache", "bindIndex", "simpleQueryForLong", "simpleQueryForString", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class QueryInterceptorStatement implements SupportSQLiteStatement, AutoCloseable {
    private final List bindArgsCache;
    private final SupportSQLiteStatement delegate;
    private final QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;
    private final String sqlStatement;

    // 检测为 Lambda 实现
    public static void $r8$lambda$0JPyqR6Q_rg0_PLLwppZoQ8UItA(QueryInterceptorStatement queryInterceptorStatement0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$BQW3zRVgGgV24eEGxxT69QhA-RY(QueryInterceptorStatement queryInterceptorStatement0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$HAZ35I4PG8_j29pEkM4jGPQHthQ(QueryInterceptorStatement queryInterceptorStatement0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$ioBB5xIamV6sfAY_SDXVsvG6_uk(QueryInterceptorStatement queryInterceptorStatement0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$vNe1N2zlZCqfqG5kv2o-Vt0njSo(QueryInterceptorStatement queryInterceptorStatement0) [...]

    public QueryInterceptorStatement(SupportSQLiteStatement supportSQLiteStatement0, String s, Executor executor0, QueryCallback roomDatabase$QueryCallback0) {
        Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "delegate");
        Intrinsics.checkNotNullParameter(s, "sqlStatement");
        Intrinsics.checkNotNullParameter(executor0, "queryCallbackExecutor");
        Intrinsics.checkNotNullParameter(roomDatabase$QueryCallback0, "queryCallback");
        super();
        this.delegate = supportSQLiteStatement0;
        this.sqlStatement = s;
        this.queryCallbackExecutor = executor0;
        this.queryCallback = roomDatabase$QueryCallback0;
        this.bindArgsCache = new ArrayList();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int v, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "value");
        this.saveArgsToCache(v, arr_b);
        this.delegate.bindBlob(v, arr_b);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int v, double f) {
        this.saveArgsToCache(v, f);
        this.delegate.bindDouble(v, f);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int v, long v1) {
        this.saveArgsToCache(v, v1);
        this.delegate.bindLong(v, v1);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int v) {
        this.saveArgsToCache(v, null);
        this.delegate.bindNull(v);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int v, String s) {
        Intrinsics.checkNotNullParameter(s, "value");
        this.saveArgsToCache(v, s);
        this.delegate.bindString(v, s);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        this.bindArgsCache.clear();
        this.delegate.clearBindings();
    }

    @Override
    public void close() {
        this.delegate.close();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public void execute() {
        QueryInterceptorStatement..ExternalSyntheticLambda3 queryInterceptorStatement$$ExternalSyntheticLambda30 = () -> QueryInterceptorStatement.execute$lambda$0(this);
        this.queryCallbackExecutor.execute(queryInterceptorStatement$$ExternalSyntheticLambda30);
        this.delegate.execute();
    }

    private static final void execute$lambda$0(QueryInterceptorStatement queryInterceptorStatement0) {
        Intrinsics.checkNotNullParameter(queryInterceptorStatement0, "this$0");
        queryInterceptorStatement0.queryCallback.onQuery(queryInterceptorStatement0.sqlStatement, queryInterceptorStatement0.bindArgsCache);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public long executeInsert() {
        QueryInterceptorStatement..ExternalSyntheticLambda1 queryInterceptorStatement$$ExternalSyntheticLambda10 = () -> QueryInterceptorStatement.executeInsert$lambda$2(this);
        this.queryCallbackExecutor.execute(queryInterceptorStatement$$ExternalSyntheticLambda10);
        return this.delegate.executeInsert();
    }

    private static final void executeInsert$lambda$2(QueryInterceptorStatement queryInterceptorStatement0) {
        Intrinsics.checkNotNullParameter(queryInterceptorStatement0, "this$0");
        queryInterceptorStatement0.queryCallback.onQuery(queryInterceptorStatement0.sqlStatement, queryInterceptorStatement0.bindArgsCache);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public int executeUpdateDelete() {
        QueryInterceptorStatement..ExternalSyntheticLambda4 queryInterceptorStatement$$ExternalSyntheticLambda40 = () -> QueryInterceptorStatement.executeUpdateDelete$lambda$1(this);
        this.queryCallbackExecutor.execute(queryInterceptorStatement$$ExternalSyntheticLambda40);
        return this.delegate.executeUpdateDelete();
    }

    private static final void executeUpdateDelete$lambda$1(QueryInterceptorStatement queryInterceptorStatement0) {
        Intrinsics.checkNotNullParameter(queryInterceptorStatement0, "this$0");
        queryInterceptorStatement0.queryCallback.onQuery(queryInterceptorStatement0.sqlStatement, queryInterceptorStatement0.bindArgsCache);
    }

    private final void saveArgsToCache(int v, Object object0) {
        if(v - 1 >= this.bindArgsCache.size()) {
            int v1 = this.bindArgsCache.size();
            for(int v2 = 0; v2 < v - 1 - v1 + 1; ++v2) {
                this.bindArgsCache.add(null);
            }
        }
        this.bindArgsCache.set(v - 1, object0);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public long simpleQueryForLong() {
        QueryInterceptorStatement..ExternalSyntheticLambda0 queryInterceptorStatement$$ExternalSyntheticLambda00 = () -> QueryInterceptorStatement.simpleQueryForLong$lambda$3(this);
        this.queryCallbackExecutor.execute(queryInterceptorStatement$$ExternalSyntheticLambda00);
        return this.delegate.simpleQueryForLong();
    }

    private static final void simpleQueryForLong$lambda$3(QueryInterceptorStatement queryInterceptorStatement0) {
        Intrinsics.checkNotNullParameter(queryInterceptorStatement0, "this$0");
        queryInterceptorStatement0.queryCallback.onQuery(queryInterceptorStatement0.sqlStatement, queryInterceptorStatement0.bindArgsCache);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public String simpleQueryForString() {
        QueryInterceptorStatement..ExternalSyntheticLambda2 queryInterceptorStatement$$ExternalSyntheticLambda20 = () -> QueryInterceptorStatement.simpleQueryForString$lambda$4(this);
        this.queryCallbackExecutor.execute(queryInterceptorStatement$$ExternalSyntheticLambda20);
        return this.delegate.simpleQueryForString();
    }

    private static final void simpleQueryForString$lambda$4(QueryInterceptorStatement queryInterceptorStatement0) {
        Intrinsics.checkNotNullParameter(queryInterceptorStatement0, "this$0");
        queryInterceptorStatement0.queryCallback.onQuery(queryInterceptorStatement0.sqlStatement, queryInterceptorStatement0.bindArgsCache);
    }
}

