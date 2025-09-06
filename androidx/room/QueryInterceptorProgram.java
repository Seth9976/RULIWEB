package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016J\u0018\u0010\u000E\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000FH\u0016J\u0018\u0010\u0010\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u0011H\u0016J\u0010\u0010\u0012\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u0018\u0010\u0013\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u0014H\u0016J\b\u0010\u0015\u001A\u00020\tH\u0016J\b\u0010\u0016\u001A\u00020\tH\u0016J\u001A\u0010\u0017\u001A\u00020\t2\u0006\u0010\u0018\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0005H\u0002R\u001C\u0010\u0003\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/room/QueryInterceptorProgram;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "()V", "bindArgsCache", "", "", "getBindArgsCache$room_runtime_release", "()Ljava/util/List;", "bindBlob", "", "index", "", "value", "", "bindDouble", "", "bindLong", "", "bindNull", "bindString", "", "clearBindings", "close", "saveArgsToCache", "bindIndex", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class QueryInterceptorProgram implements SupportSQLiteProgram, AutoCloseable {
    private final List bindArgsCache;

    public QueryInterceptorProgram() {
        this.bindArgsCache = new ArrayList();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int v, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "value");
        this.saveArgsToCache(v, arr_b);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int v, double f) {
        this.saveArgsToCache(v, f);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int v, long v1) {
        this.saveArgsToCache(v, v1);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int v) {
        this.saveArgsToCache(v, null);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int v, String s) {
        Intrinsics.checkNotNullParameter(s, "value");
        this.saveArgsToCache(v, s);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        this.bindArgsCache.clear();
    }

    @Override
    public void close() {
    }

    public final List getBindArgsCache$room_runtime_release() {
        return this.bindArgsCache;
    }

    private final void saveArgsToCache(int v, Object object0) {
        if(v - 1 >= this.bindArgsCache.size()) {
            int v1 = this.bindArgsCache.size();
            if(v1 <= v - 1) {
                while(true) {
                    this.bindArgsCache.add(null);
                    if(v1 == v - 1) {
                        break;
                    }
                    ++v1;
                }
            }
        }
        this.bindArgsCache.set(v - 1, object0);
    }
}

