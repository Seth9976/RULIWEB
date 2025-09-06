package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00C0\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0004J\b\u0010\u001D\u001A\u00020\u001EH\u0002J\b\u0010\u001F\u001A\u00020\u0011H\u0002J\u0014\u0010 \u001A\u00020\u001E2\n\u0010!\u001A\u00060\u0002j\u0002`\u0003H\u0016J\r\u0010\"\u001A\u00020\u001EH\u0000\u00A2\u0006\u0002\b#J$\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\b2\n\u0010\'\u001A\u00060\u0002j\u0002`\u00032\u0006\u0010(\u001A\u00020)H\u0016J\b\u0010*\u001A\u00020\u0015H\u0002J\u0018\u0010+\u001A\u00020\u001E2\u0006\u0010,\u001A\u00020\b2\u0006\u0010-\u001A\u00020.H\u0014J\b\u0010/\u001A\u00020\u001EH\u0016J\b\u00100\u001A\u00020\u001EH\u0016J\b\u00101\u001A\u00020\u001EH\u0002J\u000E\u00102\u001A\u00020\u001E2\u0006\u00103\u001A\u00020\bR\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0006X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0006X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0006X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0006X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0086T\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001A\u0004\u0018\u00010\u0011X\u0082\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0004R\u000E\u0010\u0013\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001A\u00020\u00158BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0016R\u0014\u0010\u0017\u001A\u00020\u00158BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001A\u00020\u00158@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001A\u001A\u00020\u00118TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u001C\u00A8\u00064"}, d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopImplBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE_MS", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "THREAD_NAME", "", "_thread", "Ljava/lang/Thread;", "get_thread$annotations", "debugStatus", "isShutDown", "", "()Z", "isShutdownRequested", "isThreadPresent", "isThreadPresent$kotlinx_coroutines_core", "thread", "getThread", "()Ljava/lang/Thread;", "acknowledgeShutdownIfNeeded", "", "createThreadSync", "enqueue", "task", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "context", "Lkotlin/coroutines/CoroutineContext;", "notifyStartup", "reschedule", "now", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "run", "shutdown", "shutdownError", "shutdownForTests", "timeout", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
    private static final int ACTIVE = 1;
    private static final long DEFAULT_KEEP_ALIVE_MS = 1000L;
    private static final int FRESH = 0;
    public static final DefaultExecutor INSTANCE = null;
    private static final long KEEP_ALIVE_NANOS = 0L;
    private static final int SHUTDOWN = 4;
    private static final int SHUTDOWN_ACK = 3;
    private static final int SHUTDOWN_REQ = 2;
    public static final String THREAD_NAME = "kotlinx.coroutines.DefaultExecutor";
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    static {
        DefaultExecutor defaultExecutor0 = new DefaultExecutor();
        DefaultExecutor.INSTANCE = defaultExecutor0;
        EventLoop.incrementUseCount$default(defaultExecutor0, false, 1, null);
        DefaultExecutor.KEEP_ALIVE_NANOS = TimeUnit.MILLISECONDS.toNanos(1000L);
    }

    private final void acknowledgeShutdownIfNeeded() {
        synchronized(this) {
        }
    }

    private final Thread createThreadSync() {
        synchronized(this) {
            Thread thread0 = DefaultExecutor._thread;
            if(thread0 == null) {
                thread0 = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                DefaultExecutor._thread = thread0;
                thread0.setDaemon(true);
                thread0.start();
            }
            return thread0;
        }
    }

    @Override  // kotlinx.coroutines.EventLoopImplBase
    public void enqueue(Runnable runnable0) {
        if(this.isShutDown()) {
            this.shutdownError();
        }
        super.enqueue(runnable0);
    }

    public final void ensureStarted$kotlinx_coroutines_core() {
        synchronized(this) {
            DefaultExecutor.debugStatus = 0;
            this.createThreadSync();
            while(DefaultExecutor.debugStatus == 0) {
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                this.wait();
            }
        }
    }

    @Override  // kotlinx.coroutines.EventLoopImplPlatform
    protected Thread getThread() {
        return DefaultExecutor._thread == null ? this.createThreadSync() : DefaultExecutor._thread;
    }

    private static void get_thread$annotations() {
    }

    @Override  // kotlinx.coroutines.EventLoopImplBase
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        return this.scheduleInvokeOnTimeout(v, runnable0);
    }

    private final boolean isShutDown() {
        return DefaultExecutor.debugStatus == 4;
    }

    private final boolean isShutdownRequested() [...] // 潜在的解密器

    public final boolean isThreadPresent$kotlinx_coroutines_core() {
        return DefaultExecutor._thread != null;
    }

    private final boolean notifyStartup() {
        synchronized(this) {
            DefaultExecutor.debugStatus = 1;
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.notifyAll();
            return true;
        }
    }

    @Override  // kotlinx.coroutines.EventLoopImplPlatform
    protected void reschedule(long v, DelayedTask eventLoopImplBase$DelayedTask0) {
        this.shutdownError();
    }

    @Override
    public void run() {
        Unit unit0;
        long v2;
        long v1;
        ThreadLocalEventLoop.INSTANCE.setEventLoop$kotlinx_coroutines_core(this);
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.registerTimeLoopThread();
        }
        try {
            if(!this.notifyStartup()) {
                goto label_5;
            }
            goto label_13;
        }
        catch(Throwable throwable0) {
            goto label_46;
        }
    label_5:
        DefaultExecutor._thread = null;
        this.acknowledgeShutdownIfNeeded();
        AbstractTimeSource abstractTimeSource1 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource1 != null) {
            abstractTimeSource1.unregisterTimeLoopThread();
        }
        if(this.isEmpty()) {
            return;
        }
        this.getThread();
        return;
    label_13:
        long v = 0x7FFFFFFFFFFFFFFFL;
        while(true) {
            try {
            label_14:
                Thread.interrupted();
                v1 = this.processNextEvent();
                if(v1 == 0x7FFFFFFFFFFFFFFFL) {
                    AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.getTimeSource();
                    v2 = abstractTimeSource2 == null ? System.nanoTime() : abstractTimeSource2.nanoTime();
                    if(v == 0x7FFFFFFFFFFFFFFFL) {
                        goto label_22;
                    }
                    goto label_23;
                }
                else {
                    v = 0x7FFFFFFFFFFFFFFFL;
                }
                goto label_35;
            }
            catch(Throwable throwable0) {
                break;
            }
        label_22:
            v = DefaultExecutor.KEEP_ALIVE_NANOS + v2;
        label_23:
            long v3 = v - v2;
            if(v3 <= 0L) {
                DefaultExecutor._thread = null;
                this.acknowledgeShutdownIfNeeded();
                AbstractTimeSource abstractTimeSource3 = AbstractTimeSourceKt.getTimeSource();
                if(abstractTimeSource3 != null) {
                    abstractTimeSource3.unregisterTimeLoopThread();
                }
                if(!this.isEmpty()) {
                    this.getThread();
                    return;
                }
                return;
            }
            try {
                v1 = RangesKt.coerceAtMost(0x7FFFFFFFFFFFFFFFL, v3);
            label_35:
                if(v1 <= 0L) {
                    goto label_14;
                }
                AbstractTimeSource abstractTimeSource4 = AbstractTimeSourceKt.getTimeSource();
                if(abstractTimeSource4 == null) {
                    unit0 = null;
                }
                else {
                    abstractTimeSource4.parkNanos(this, v1);
                    unit0 = Unit.INSTANCE;
                }
                if(unit0 != null) {
                    goto label_14;
                }
                LockSupport.parkNanos(this, v1);
                goto label_14;
            }
            catch(Throwable throwable0) {
            }
            break;
        }
    label_46:
        DefaultExecutor._thread = null;
        this.acknowledgeShutdownIfNeeded();
        AbstractTimeSource abstractTimeSource5 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource5 != null) {
            abstractTimeSource5.unregisterTimeLoopThread();
        }
        if(!this.isEmpty()) {
            this.getThread();
        }
        throw throwable0;
    }

    @Override  // kotlinx.coroutines.EventLoopImplBase
    public void shutdown() {
        DefaultExecutor.debugStatus = 4;
        super.shutdown();
    }

    private final void shutdownError() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    public final void shutdownForTests(long v) {
        Unit unit0;
        synchronized(this) {
            long v2 = System.currentTimeMillis();
            DefaultExecutor.debugStatus = 2;
            while(DefaultExecutor._thread != null) {
                Thread thread0 = DefaultExecutor._thread;
                if(thread0 != null) {
                    AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
                    if(abstractTimeSource0 == null) {
                        unit0 = null;
                    }
                    else {
                        abstractTimeSource0.unpark(thread0);
                        unit0 = Unit.INSTANCE;
                    }
                    if(unit0 == null) {
                        LockSupport.unpark(thread0);
                    }
                }
                if(v2 + v - System.currentTimeMillis() <= 0L) {
                    break;
                }
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                this.wait(v);
            }
            DefaultExecutor.debugStatus = 0;
        }
    }
}

