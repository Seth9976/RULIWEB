package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.RealCall.AsyncCall;
import okhttp3.internal.connection.RealCall;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004B\u0005\u00A2\u0006\u0002\u0010\u0005J\u0006\u0010\u001E\u001A\u00020\u001FJ\u0019\u0010 \u001A\u00020\u001F2\n\u0010!\u001A\u00060\u001AR\u00020\u001BH\u0000\u00A2\u0006\u0002\b\"J\u0015\u0010#\u001A\u00020\u001F2\u0006\u0010!\u001A\u00020\u001BH\u0000\u00A2\u0006\u0002\b$J\r\u0010\u0002\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b%J\u0016\u0010&\u001A\b\u0018\u00010\u001AR\u00020\u001B2\u0006\u0010\'\u001A\u00020(H\u0002J)\u0010)\u001A\u00020\u001F\"\u0004\b\u0000\u0010*2\f\u0010+\u001A\b\u0012\u0004\u0012\u0002H*0,2\u0006\u0010!\u001A\u0002H*H\u0002\u00A2\u0006\u0002\u0010-J\u0015\u0010)\u001A\u00020\u001F2\u0006\u0010!\u001A\u00020\u001BH\u0000\u00A2\u0006\u0002\b.J\u0019\u0010)\u001A\u00020\u001F2\n\u0010!\u001A\u00060\u001AR\u00020\u001BH\u0000\u00A2\u0006\u0002\b.J\b\u0010/\u001A\u000200H\u0002J\f\u00101\u001A\b\u0012\u0004\u0012\u00020302J\u0006\u00104\u001A\u00020\u0010J\f\u00105\u001A\b\u0012\u0004\u0012\u00020302J\u0006\u00106\u001A\u00020\u0010R\u0011\u0010\u0002\u001A\u00020\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u0002\u0010\u0006R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R*\u0010\n\u001A\u0004\u0018\u00010\t2\b\u0010\b\u001A\u0004\u0018\u00010\t8F@FX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER&\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u000F\u001A\u00020\u00108F@FX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R&\u0010\u0015\u001A\u00020\u00102\u0006\u0010\u0015\u001A\u00020\u00108F@FX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u0018\u0010\u0018\u001A\f\u0012\b\u0012\u00060\u001AR\u00020\u001B0\u0019X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0018\u0010\u001C\u001A\f\u0012\b\u0012\u00060\u001AR\u00020\u001B0\u0019X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u001B0\u0019X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u00067"}, d2 = {"Lokhttp3/Dispatcher;", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Ljava/util/concurrent/ExecutorService;)V", "()V", "()Ljava/util/concurrent/ExecutorService;", "executorServiceOrNull", "<set-?>", "Ljava/lang/Runnable;", "idleCallback", "getIdleCallback", "()Ljava/lang/Runnable;", "setIdleCallback", "(Ljava/lang/Runnable;)V", "maxRequests", "", "getMaxRequests", "()I", "setMaxRequests", "(I)V", "maxRequestsPerHost", "getMaxRequestsPerHost", "setMaxRequestsPerHost", "readyAsyncCalls", "Ljava/util/ArrayDeque;", "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "runningAsyncCalls", "runningSyncCalls", "cancelAll", "", "enqueue", "call", "enqueue$okhttp", "executed", "executed$okhttp", "-deprecated_executorService", "findExistingCallWithHost", "host", "", "finished", "T", "calls", "Ljava/util/Deque;", "(Ljava/util/Deque;Ljava/lang/Object;)V", "finished$okhttp", "promoteAndExecute", "", "queuedCalls", "", "Lokhttp3/Call;", "queuedCallsCount", "runningCalls", "runningCallsCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Dispatcher {
    private ExecutorService executorServiceOrNull;
    private Runnable idleCallback;
    private int maxRequests;
    private int maxRequestsPerHost;
    private final ArrayDeque readyAsyncCalls;
    private final ArrayDeque runningAsyncCalls;
    private final ArrayDeque runningSyncCalls;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "executorService", imports = {}))
    public final ExecutorService -deprecated_executorService() {
        return this.executorService();
    }

    public Dispatcher() {
        this.maxRequests = 0x40;
        this.maxRequestsPerHost = 5;
        this.readyAsyncCalls = new ArrayDeque();
        this.runningAsyncCalls = new ArrayDeque();
        this.runningSyncCalls = new ArrayDeque();
    }

    public Dispatcher(ExecutorService executorService0) {
        Intrinsics.checkNotNullParameter(executorService0, "executorService");
        this();
        this.executorServiceOrNull = executorService0;
    }

    public final void cancelAll() {
        synchronized(this) {
            for(Object object0: this.readyAsyncCalls) {
                ((AsyncCall)object0).getCall().cancel();
            }
            for(Object object1: this.runningAsyncCalls) {
                ((AsyncCall)object1).getCall().cancel();
            }
            for(Object object2: this.runningSyncCalls) {
                ((RealCall)object2).cancel();
            }
        }
    }

    public final void enqueue$okhttp(AsyncCall realCall$AsyncCall0) {
        Intrinsics.checkNotNullParameter(realCall$AsyncCall0, "call");
        synchronized(this) {
            this.readyAsyncCalls.add(realCall$AsyncCall0);
            if(!realCall$AsyncCall0.getCall().getForWebSocket()) {
                AsyncCall realCall$AsyncCall1 = this.findExistingCallWithHost(realCall$AsyncCall0.getHost());
                if(realCall$AsyncCall1 != null) {
                    realCall$AsyncCall0.reuseCallsPerHostFrom(realCall$AsyncCall1);
                }
            }
        }
        this.promoteAndExecute();
    }

    public final void executed$okhttp(RealCall realCall0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(realCall0, "call");
            this.runningSyncCalls.add(realCall0);
        }
    }

    public final ExecutorService executorService() {
        synchronized(this) {
            if(this.executorServiceOrNull == null) {
                this.executorServiceOrNull = new ThreadPoolExecutor(0, 0x7FFFFFFF, 60L, TimeUnit.SECONDS, new SynchronousQueue(), _UtilJvmKt.threadFactory((_UtilJvmKt.okHttpName + " Dispatcher"), false));
            }
            ExecutorService executorService0 = this.executorServiceOrNull;
            Intrinsics.checkNotNull(executorService0);
            return executorService0;
        }
    }

    private final AsyncCall findExistingCallWithHost(String s) {
        for(Object object0: this.runningAsyncCalls) {
            AsyncCall realCall$AsyncCall0 = (AsyncCall)object0;
            if(Intrinsics.areEqual(realCall$AsyncCall0.getHost(), s)) {
                return realCall$AsyncCall0;
            }
            if(false) {
                break;
            }
        }
        for(Object object1: this.readyAsyncCalls) {
            AsyncCall realCall$AsyncCall1 = (AsyncCall)object1;
            if(Intrinsics.areEqual(realCall$AsyncCall1.getHost(), s)) {
                return realCall$AsyncCall1;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    private final void finished(Deque deque0, Object object0) {
        synchronized(this) {
            if(deque0.remove(object0)) {
                Runnable runnable0 = this.idleCallback;
                if(!this.promoteAndExecute() && runnable0 != null) {
                    runnable0.run();
                }
                return;
            }
        }
        throw new AssertionError("Call wasn\'t in-flight!");
    }

    public final void finished$okhttp(AsyncCall realCall$AsyncCall0) {
        Intrinsics.checkNotNullParameter(realCall$AsyncCall0, "call");
        realCall$AsyncCall0.getCallsPerHost().decrementAndGet();
        this.finished(this.runningAsyncCalls, realCall$AsyncCall0);
    }

    public final void finished$okhttp(RealCall realCall0) {
        Intrinsics.checkNotNullParameter(realCall0, "call");
        this.finished(this.runningSyncCalls, realCall0);
    }

    public final Runnable getIdleCallback() {
        synchronized(this) {
        }
        return this.idleCallback;
    }

    public final int getMaxRequests() {
        synchronized(this) {
        }
        return this.maxRequests;
    }

    public final int getMaxRequestsPerHost() {
        synchronized(this) {
        }
        return this.maxRequestsPerHost;
    }

    private final boolean promoteAndExecute() {
        boolean z;
        int v;
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13428 MUST NOT hold lock on " + this);
        }
        List list0 = new ArrayList();
        synchronized(this) {
            Iterator iterator0 = this.readyAsyncCalls.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator0, "readyAsyncCalls.iterator()");
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                AsyncCall realCall$AsyncCall0 = (AsyncCall)object0;
                if(this.runningAsyncCalls.size() >= this.maxRequests) {
                    break;
                }
                if(realCall$AsyncCall0.getCallsPerHost().get() < this.maxRequestsPerHost) {
                    iterator0.remove();
                    realCall$AsyncCall0.getCallsPerHost().incrementAndGet();
                    Intrinsics.checkNotNullExpressionValue(realCall$AsyncCall0, "asyncCall");
                    list0.add(realCall$AsyncCall0);
                    this.runningAsyncCalls.add(realCall$AsyncCall0);
                }
            }
            v = 0;
            z = this.runningCallsCount() > 0;
        }
        if(this.executorService().isShutdown()) {
            int v1 = list0.size();
            while(v < v1) {
                AsyncCall realCall$AsyncCall1 = (AsyncCall)list0.get(v);
                realCall$AsyncCall1.getCallsPerHost().decrementAndGet();
                synchronized(this) {
                    this.runningAsyncCalls.remove(realCall$AsyncCall1);
                }
                AsyncCall.failRejected$okhttp$default(realCall$AsyncCall1, null, 1, null);
                ++v;
            }
            Runnable runnable0 = this.idleCallback;
            if(runnable0 != null) {
                runnable0.run();
                return z;
            }
        }
        else {
            int v2 = list0.size();
            while(v < v2) {
                ((AsyncCall)list0.get(v)).executeOn(this.executorService());
                ++v;
            }
        }
        return z;
    }

    public final List queuedCalls() {
        synchronized(this) {
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.readyAsyncCalls, 10));
            for(Object object0: this.readyAsyncCalls) {
                arrayList0.add(((AsyncCall)object0).getCall());
            }
            List list0 = Collections.unmodifiableList(arrayList0);
            Intrinsics.checkNotNullExpressionValue(list0, "unmodifiableList(readyAsyncCalls.map { it.call })");
            return list0;
        }
    }

    public final int queuedCallsCount() {
        synchronized(this) {
        }
        return this.readyAsyncCalls.size();
    }

    public final List runningCalls() {
        synchronized(this) {
            Collection collection0 = this.runningSyncCalls;
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.runningAsyncCalls, 10));
            for(Object object0: this.runningAsyncCalls) {
                arrayList0.add(((AsyncCall)object0).getCall());
            }
            List list0 = Collections.unmodifiableList(CollectionsKt.plus(collection0, arrayList0));
            Intrinsics.checkNotNullExpressionValue(list0, "unmodifiableList(running…yncCalls.map { it.call })");
            return list0;
        }
    }

    public final int runningCallsCount() {
        synchronized(this) {
        }
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }

    public final void setIdleCallback(Runnable runnable0) {
        synchronized(this) {
            this.idleCallback = runnable0;
        }
    }

    public final void setMaxRequests(int v) {
        if(v < 1) {
            throw new IllegalArgumentException(("max < 1: " + v).toString());
        }
        synchronized(this) {
            this.maxRequests = v;
        }
        this.promoteAndExecute();
    }

    public final void setMaxRequestsPerHost(int v) {
        if(v < 1) {
            throw new IllegalArgumentException(("max < 1: " + v).toString());
        }
        synchronized(this) {
            this.maxRequestsPerHost = v;
        }
        this.promoteAndExecute();
    }
}

