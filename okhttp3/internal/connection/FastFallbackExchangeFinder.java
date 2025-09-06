package okhttp3.internal.connection;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import jeb.synthetic.FIN;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001A\u0010\u0013\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u00020\u0016H\u0002J\b\u0010\u0017\u001A\u00020\u0018H\u0002J\b\u0010\u0019\u001A\u00020\u001AH\u0016J\n\u0010\u001B\u001A\u0004\u0018\u00010\u000BH\u0002R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\t\u001A\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000B0\u000B0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001A\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Lokhttp3/internal/connection/FastFallbackExchangeFinder;", "Lokhttp3/internal/connection/ExchangeFinder;", "routePlanner", "Lokhttp3/internal/connection/RoutePlanner;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(Lokhttp3/internal/connection/RoutePlanner;Lokhttp3/internal/concurrent/TaskRunner;)V", "connectDelayNanos", "", "connectResults", "Ljava/util/concurrent/BlockingQueue;", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "kotlin.jvm.PlatformType", "nextTcpConnectAtNanos", "getRoutePlanner", "()Lokhttp3/internal/connection/RoutePlanner;", "tcpConnectsInFlight", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "awaitTcpConnect", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "cancelInFlightConnects", "", "find", "Lokhttp3/internal/connection/RealConnection;", "launchTcpConnect", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class FastFallbackExchangeFinder implements ExchangeFinder {
    private final long connectDelayNanos;
    private final BlockingQueue connectResults;
    private long nextTcpConnectAtNanos;
    private final RoutePlanner routePlanner;
    private final TaskRunner taskRunner;
    private final CopyOnWriteArrayList tcpConnectsInFlight;

    public FastFallbackExchangeFinder(RoutePlanner routePlanner0, TaskRunner taskRunner0) {
        Intrinsics.checkNotNullParameter(routePlanner0, "routePlanner");
        Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
        super();
        this.routePlanner = routePlanner0;
        this.taskRunner = taskRunner0;
        this.connectDelayNanos = TimeUnit.MILLISECONDS.toNanos(0xFAL);
        this.nextTcpConnectAtNanos = 0x8000000000000000L;
        this.tcpConnectsInFlight = new CopyOnWriteArrayList();
        this.connectResults = taskRunner0.getBackend().decorate(new LinkedBlockingDeque());
    }

    private final ConnectResult awaitTcpConnect(long v, TimeUnit timeUnit0) {
        if(this.tcpConnectsInFlight.isEmpty()) {
            return null;
        }
        ConnectResult routePlanner$ConnectResult0 = (ConnectResult)this.connectResults.poll(v, timeUnit0);
        if(routePlanner$ConnectResult0 == null) {
            return null;
        }
        this.tcpConnectsInFlight.remove(routePlanner$ConnectResult0.getPlan());
        return routePlanner$ConnectResult0;
    }

    private final void cancelInFlightConnects() {
        for(Object object0: this.tcpConnectsInFlight) {
            ((Plan)object0).cancel();
            Plan routePlanner$Plan0 = ((Plan)object0).retry();
            if(routePlanner$Plan0 != null) {
                this.getRoutePlanner().getDeferredPlans().addLast(routePlanner$Plan0);
            }
        }
        this.tcpConnectsInFlight.clear();
    }

    @Override  // okhttp3.internal.connection.ExchangeFinder
    public RealConnection find() {
        ConnectResult routePlanner$ConnectResult0;
        long v3;
        int v;
        IOException iOException0 = null;
        while(true) {
            v = FIN.finallyOpen$NT();
            while(true) {
                if(this.tcpConnectsInFlight.isEmpty() && !RoutePlanner.-CC.hasNext$default(this.getRoutePlanner(), null, 1, null)) {
                    FIN.finallyCodeBegin$NT(v);
                    this.cancelInFlightConnects();
                    FIN.finallyCodeEnd$NT(v);
                    Intrinsics.checkNotNull(iOException0);
                    throw iOException0;
                }
                if(this.getRoutePlanner().isCanceled()) {
                    break;
                }
                long v1 = this.taskRunner.getBackend().nanoTime();
                long v2 = this.nextTcpConnectAtNanos - v1;
                if(this.tcpConnectsInFlight.isEmpty() || v2 <= 0L) {
                    routePlanner$ConnectResult0 = this.launchTcpConnect();
                    v3 = this.connectDelayNanos;
                    this.nextTcpConnectAtNanos = v1 + v3;
                }
                else {
                    v3 = v2;
                    routePlanner$ConnectResult0 = null;
                }
                if(routePlanner$ConnectResult0 != null) {
                    goto label_25;
                }
                ConnectResult routePlanner$ConnectResult1 = this.awaitTcpConnect(v3, TimeUnit.NANOSECONDS);
                if(routePlanner$ConnectResult1 != null) {
                    routePlanner$ConnectResult0 = routePlanner$ConnectResult1;
                    goto label_25;
                }
            }
            break;
        label_25:
            if(routePlanner$ConnectResult0.isSuccess()) {
                this.cancelInFlightConnects();
                if(!routePlanner$ConnectResult0.getPlan().isReady()) {
                    routePlanner$ConnectResult0 = routePlanner$ConnectResult0.getPlan().connectTlsEtc();
                }
                if(routePlanner$ConnectResult0.isSuccess()) {
                    RealConnection realConnection0 = routePlanner$ConnectResult0.getPlan().handleSuccess();
                    FIN.finallyExec$NT(v);
                    return realConnection0;
                }
            }
            Throwable throwable0 = routePlanner$ConnectResult0.getThrowable();
            if(throwable0 != null) {
                if(throwable0 instanceof IOException) {
                    if(iOException0 == null) {
                        iOException0 = (IOException)throwable0;
                    }
                    else {
                        ExceptionsKt.addSuppressed(iOException0, throwable0);
                    }
                    goto label_43;
                }
                FIN.finallyExec$NT(v);
                throw throwable0;
            }
        label_43:
            Plan routePlanner$Plan0 = routePlanner$ConnectResult0.getNextPlan();
            if(routePlanner$Plan0 != null) {
                this.getRoutePlanner().getDeferredPlans().addFirst(routePlanner$Plan0);
            }
        }
        FIN.finallyExec$NT(v);
        throw new IOException("Canceled");
    }

    @Override  // okhttp3.internal.connection.ExchangeFinder
    public RoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }

    private final ConnectResult launchTcpConnect() {
        Plan routePlanner$Plan0;
        if(RoutePlanner.-CC.hasNext$default(this.getRoutePlanner(), null, 1, null)) {
            try {
                routePlanner$Plan0 = this.getRoutePlanner().plan();
            }
            catch(Throwable throwable0) {
                routePlanner$Plan0 = new FailedPlan(throwable0);
            }
            if(routePlanner$Plan0.isReady()) {
                return new ConnectResult(routePlanner$Plan0, null, null, 6, null);
            }
            if(routePlanner$Plan0 instanceof FailedPlan) {
                return ((FailedPlan)routePlanner$Plan0).getResult();
            }
            this.tcpConnectsInFlight.add(routePlanner$Plan0);
            TaskQueue.schedule$default(this.taskRunner.newQueue(), new Task(routePlanner$Plan0, this) {
                final Plan $plan;

                {
                    this.$plan = routePlanner$Plan0;
                    FastFallbackExchangeFinder.this = fastFallbackExchangeFinder0;
                    super(s, false, 2, null);
                }

                @Override  // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    ConnectResult routePlanner$ConnectResult0;
                    try {
                        routePlanner$ConnectResult0 = this.$plan.connectTcp();
                    }
                    catch(Throwable throwable0) {
                        routePlanner$ConnectResult0 = new ConnectResult(this.$plan, null, throwable0, 2, null);
                    }
                    if(FastFallbackExchangeFinder.this.tcpConnectsInFlight.contains(this.$plan)) {
                        FastFallbackExchangeFinder.this.connectResults.put(routePlanner$ConnectResult0);
                    }
                    return -1L;
                }
            }, 0L, 2, null);
        }
        return null;
    }
}

