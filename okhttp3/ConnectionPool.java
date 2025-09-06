package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealConnectionPool;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bB\u0007\b\u0016¢\u0006\u0002\u0010\tB\u000F\b\u0000\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\u0006\u0010\u000F\u001A\u00020\u0003J\u0006\u0010\u0010\u001A\u00020\u0011J\u0006\u0010\u0012\u001A\u00020\u0003R\u0014\u0010\n\u001A\u00020\u000BX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u0013"}, d2 = {"Lokhttp3/ConnectionPool;", "", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(IJLjava/util/concurrent/TimeUnit;)V", "()V", "delegate", "Lokhttp3/internal/connection/RealConnectionPool;", "(Lokhttp3/internal/connection/RealConnectionPool;)V", "getDelegate$okhttp", "()Lokhttp3/internal/connection/RealConnectionPool;", "connectionCount", "evictAll", "", "idleConnectionCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class ConnectionPool {
    private final RealConnectionPool delegate;

    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    public ConnectionPool(int v, long v1, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
        this(new RealConnectionPool(TaskRunner.INSTANCE, v, v1, timeUnit0));
    }

    public ConnectionPool(RealConnectionPool realConnectionPool0) {
        Intrinsics.checkNotNullParameter(realConnectionPool0, "delegate");
        super();
        this.delegate = realConnectionPool0;
    }

    public final int connectionCount() {
        return this.delegate.connectionCount();
    }

    public final void evictAll() {
        this.delegate.evictAll();
    }

    public final RealConnectionPool getDelegate$okhttp() {
        return this.delegate;
    }

    public final int idleConnectionCount() {
        return this.delegate.idleConnectionCount();
    }
}

