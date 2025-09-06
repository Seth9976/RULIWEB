package okhttp3.internal.connection;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.platform.Platform;

@Metadata(d1 = {"\u0000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u000E\u0018\u0000 )2\u00020\u0001:\u0001)B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ8\u0010\u0014\u001A\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001A2\u000E\u0010\u001B\u001A\n\u0012\u0004\u0012\u00020\u001D\u0018\u00010\u001C2\u0006\u0010\u001E\u001A\u00020\u0016J\u000E\u0010\u001F\u001A\u00020\u00072\u0006\u0010 \u001A\u00020\u0007J\u000E\u0010!\u001A\u00020\u00162\u0006\u0010\"\u001A\u00020\u0012J\u0006\u0010#\u001A\u00020\u0005J\u0006\u0010$\u001A\u00020%J\u0006\u0010&\u001A\u00020\u0005J\u0018\u0010\'\u001A\u00020\u00052\u0006\u0010\"\u001A\u00020\u00122\u0006\u0010 \u001A\u00020\u0007H\u0002J\u000E\u0010(\u001A\u00020%2\u0006\u0010\"\u001A\u00020\u0012R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u00020\u000EX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000FR\u0014\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(Lokhttp3/internal/concurrent/TaskRunner;IJLjava/util/concurrent/TimeUnit;)V", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupTask$1;", "connections", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lokhttp3/internal/connection/RealConnection;", "keepAliveDurationNs", "callAcquirePooledConnection", "doExtensiveHealthChecks", "", "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "routes", "", "Lokhttp3/Route;", "requireMultiplexed", "cleanup", "now", "connectionBecameIdle", "connection", "connectionCount", "evictAll", "", "idleConnectionCount", "pruneAndGetAllocationCount", "put", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RealConnectionPool {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "()V", "get", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final RealConnectionPool get(ConnectionPool connectionPool0) {
            Intrinsics.checkNotNullParameter(connectionPool0, "connectionPool");
            return connectionPool0.getDelegate$okhttp();
        }
    }

    public static final Companion Companion;
    private final TaskQueue cleanupQueue;
    private final okhttp3.internal.connection.RealConnectionPool.cleanupTask.1 cleanupTask;
    private final ConcurrentLinkedQueue connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    static {
        RealConnectionPool.Companion = new Companion(null);
    }

    public RealConnectionPool(TaskRunner taskRunner0, int v, long v1, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
        Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
        super();
        this.maxIdleConnections = v;
        this.keepAliveDurationNs = timeUnit0.toNanos(v1);
        this.cleanupQueue = taskRunner0.newQueue();
        this.cleanupTask = new Task(_UtilJvmKt.okHttpName + " ConnectionPool") {
            {
                RealConnectionPool.this = realConnectionPool0;
                super(s, false, 2, null);
            }

            @Override  // okhttp3.internal.concurrent.Task
            public long runOnce() {
                return RealConnectionPool.this.cleanup(System.nanoTime());
            }
        };
        this.connections = new ConcurrentLinkedQueue();
        if(v1 <= 0L) {
            throw new IllegalArgumentException(("keepAliveDuration <= 0: " + v1).toString());
        }
    }

    public final RealConnection callAcquirePooledConnection(boolean z, Address address0, RealCall realCall0, List list0, boolean z1) {
        Socket socket0;
        Intrinsics.checkNotNullParameter(address0, "address");
        Intrinsics.checkNotNullParameter(realCall0, "call");
        Iterator iterator0 = this.connections.iterator();
        while(iterator0.hasNext()) {
            boolean z2 = false;
            Object object0 = iterator0.next();
            RealConnection realConnection0 = (RealConnection)object0;
            Intrinsics.checkNotNullExpressionValue(realConnection0, "connection");
            synchronized(realConnection0) {
                if((!z1 || realConnection0.isMultiplexed$okhttp()) && realConnection0.isEligible$okhttp(address0, list0)) {
                    realCall0.acquireConnectionNoEvents(realConnection0);
                    z2 = true;
                }
            }
            if(z2) {
                if(realConnection0.isHealthy(z)) {
                    return realConnection0;
                }
                synchronized(realConnection0) {
                    realConnection0.setNoNewExchanges(true);
                    socket0 = realCall0.releaseConnectionNoEvents$okhttp();
                }
                if(socket0 != null) {
                    _UtilJvmKt.closeQuietly(socket0);
                }
            }
        }
        return null;
    }

    public final long cleanup(long v) {
        int v1 = 0;
        long v2 = 0x8000000000000000L;
        RealConnection realConnection0 = null;
        int v3 = 0;
        for(Object object0: this.connections) {
            RealConnection realConnection1 = (RealConnection)object0;
            Intrinsics.checkNotNullExpressionValue(realConnection1, "connection");
            synchronized(realConnection1) {
                if(this.pruneAndGetAllocationCount(realConnection1, v) > 0) {
                    ++v3;
                }
                else {
                    ++v1;
                    long v5 = v - realConnection1.getIdleAtNs();
                    if(v5 > v2) {
                        realConnection0 = realConnection1;
                        v2 = v5;
                    }
                }
            }
        }
        long v6 = this.keepAliveDurationNs;
        if(v2 < v6 && v1 <= this.maxIdleConnections) {
            if(v1 > 0) {
                return v6 - v2;
            }
            return v3 <= 0 ? -1L : v6;
        }
        Intrinsics.checkNotNull(realConnection0);
        synchronized(realConnection0) {
            if(!realConnection0.getCalls().isEmpty()) {
                return 0L;
            }
            if(realConnection0.getIdleAtNs() + v2 != v) {
                return 0L;
            }
            realConnection0.setNoNewExchanges(true);
            this.connections.remove(realConnection0);
        }
        _UtilJvmKt.closeQuietly(realConnection0.socket());
        if(this.connections.isEmpty()) {
            this.cleanupQueue.cancelAll();
        }
        return 0L;
    }

    public final boolean connectionBecameIdle(RealConnection realConnection0) {
        Intrinsics.checkNotNullParameter(realConnection0, "connection");
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(realConnection0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13472 MUST hold lock on " + realConnection0);
        }
        if(!realConnection0.getNoNewExchanges() && this.maxIdleConnections != 0) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return false;
        }
        realConnection0.setNoNewExchanges(true);
        this.connections.remove(realConnection0);
        if(this.connections.isEmpty()) {
            this.cleanupQueue.cancelAll();
        }
        return true;
    }

    public final int connectionCount() {
        return this.connections.size();
    }

    public final void evictAll() {
        Socket socket0;
        Iterator iterator0 = this.connections.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator0, "connections.iterator()");
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            RealConnection realConnection0 = (RealConnection)object0;
            Intrinsics.checkNotNullExpressionValue(realConnection0, "connection");
            synchronized(realConnection0) {
                if(realConnection0.getCalls().isEmpty()) {
                    iterator0.remove();
                    realConnection0.setNoNewExchanges(true);
                    socket0 = realConnection0.socket();
                }
                else {
                    socket0 = null;
                }
            }
            if(socket0 != null) {
                _UtilJvmKt.closeQuietly(socket0);
            }
        }
        if(this.connections.isEmpty()) {
            this.cleanupQueue.cancelAll();
        }
    }

    public final int idleConnectionCount() {
        Iterable iterable0 = this.connections;
        int v = 0;
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return 0;
        }
        for(Object object0: iterable0) {
            Intrinsics.checkNotNullExpressionValue(((RealConnection)object0), "it");
            synchronized(((RealConnection)object0)) {
            }
            if(((RealConnection)object0).getCalls().isEmpty()) {
                ++v;
                if(v < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return v;
    }

    private final int pruneAndGetAllocationCount(RealConnection realConnection0, long v) {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(realConnection0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13535 MUST hold lock on " + realConnection0);
        }
        List list0 = realConnection0.getCalls();
        int v1 = 0;
        while(v1 < list0.size()) {
            Reference reference0 = (Reference)list0.get(v1);
            if(reference0.get() == null) {
                Intrinsics.checkNotNull(reference0, "null cannot be cast to non-null type okhttp3.internal.connection.RealCall.CallReference");
                Platform.Companion.get().logCloseableLeak("A connection to " + realConnection0.route().address().url() + " was leaked. Did you forget to close a response body?", ((CallReference)reference0).getCallStackTrace());
                list0.remove(v1);
                realConnection0.setNoNewExchanges(true);
                if(list0.isEmpty()) {
                    realConnection0.setIdleAtNs(v - this.keepAliveDurationNs);
                    return 0;
                }
                if(false) {
                    break;
                }
            }
            else {
                ++v1;
            }
        }
        return list0.size();
    }

    public final void put(RealConnection realConnection0) {
        Intrinsics.checkNotNullParameter(realConnection0, "connection");
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(realConnection0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13494 MUST hold lock on " + realConnection0);
        }
        this.connections.add(realConnection0);
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
    }
}

