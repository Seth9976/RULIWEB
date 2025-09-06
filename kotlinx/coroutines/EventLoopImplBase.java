package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import jeb.synthetic.FIN;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u00002\u00020\u00012\u00020\u0002:\u00043456B\u0005\u00A2\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001A\u00020\u0017H\u0002J\u0010\u0010\u0018\u001A\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001AH\u0002J\u001A\u0010\u001B\u001A\u00020\u00172\u0006\u0010\u001C\u001A\u00020\u001D2\n\u0010\u001E\u001A\u00060\u0019j\u0002`\u001AJ\u0014\u0010\u001F\u001A\u00020\u00172\n\u0010 \u001A\u00060\u0019j\u0002`\u001AH\u0016J\u0014\u0010!\u001A\u00020\f2\n\u0010 \u001A\u00060\u0019j\u0002`\u001AH\u0002J\b\u0010\"\u001A\u00020\u0013H\u0016J\b\u0010#\u001A\u00020\u0017H\u0002J\b\u0010$\u001A\u00020\u0017H\u0004J\u0016\u0010%\u001A\u00020\u00172\u0006\u0010&\u001A\u00020\u00132\u0006\u0010\'\u001A\u00020(J\u0018\u0010)\u001A\u00020*2\u0006\u0010&\u001A\u00020\u00132\u0006\u0010\'\u001A\u00020(H\u0002J\u001C\u0010+\u001A\u00020,2\u0006\u0010-\u001A\u00020\u00132\n\u0010\u001E\u001A\u00060\u0019j\u0002`\u001AH\u0004J\u001E\u0010.\u001A\u00020\u00172\u0006\u0010-\u001A\u00020\u00132\f\u0010/\u001A\b\u0012\u0004\u0012\u00020\u001700H\u0016J\u0010\u00101\u001A\u00020\f2\u0006\u0010 \u001A\u00020(H\u0002J\b\u00102\u001A\u00020\u0017H\u0016R\u0011\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004R\t\u0010\u0007\u001A\u00020\bX\u0082\u0004R\u0011\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005X\u0082\u0004R$\u0010\r\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\f8B@BX\u0082\u000E\u00A2\u0006\f\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u0014\u0010\u0011\u001A\u00020\f8TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u000ER\u0014\u0010\u0012\u001A\u00020\u00138TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015\u00A8\u00067"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/Delay;", "()V", "_delayed", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "_isCompleted", "Lkotlinx/atomicfu/AtomicBoolean;", "_queue", "", "value", "", "isCompleted", "()Z", "setCompleted", "(Z)V", "isEmpty", "nextTime", "", "getNextTime", "()J", "closeQueue", "", "dequeue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "enqueue", "task", "enqueueImpl", "processNextEvent", "rescheduleAllDelayed", "resetAll", "schedule", "now", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "scheduleImpl", "", "scheduleInvokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldUnpark", "shutdown", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "DelayedTaskQueue", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001B\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001A\u00020\u0006H\u0016J\b\u0010\t\u001A\u00020\nH\u0016R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuation cont;

        public DelayedResumeTask(long v, CancellableContinuation cancellableContinuation0) {
            super(v);
            this.cont = cancellableContinuation0;
        }

        @Override
        public void run() {
            this.cont.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override  // kotlinx.coroutines.EventLoopImplBase$DelayedTask
        public String toString() {
            return super.toString() + this.cont;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\n\u0010\u0004\u001A\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001A\u00020\tH\u0016J\b\u0010\n\u001A\u00020\u000BH\u0016R\u0012\u0010\u0004\u001A\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public DelayedRunnableTask(long v, Runnable runnable0) {
            super(v);
            this.block = runnable0;
        }

        @Override
        public void run() {
            this.block.run();
        }

        @Override  // kotlinx.coroutines.EventLoopImplBase$DelayedTask
        public String toString() {
            return super.toString() + this.block;
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u00052\u00060\u0006j\u0002`\u0007B\r\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\u0019\u001A\u00020\u00142\u0006\u0010\u001A\u001A\u00020\u0000H\u0096\u0002J\u0006\u0010\u001B\u001A\u00020\u001CJ\u001E\u0010\u001D\u001A\u00020\u00142\u0006\u0010\u001E\u001A\u00020\t2\u0006\u0010\u001F\u001A\u00020 2\u0006\u0010!\u001A\u00020\"J\u000E\u0010#\u001A\u00020$2\u0006\u0010\u001E\u001A\u00020\tJ\b\u0010%\u001A\u00020&H\u0016R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R0\u0010\u000E\u001A\b\u0012\u0002\b\u0003\u0018\u00010\r2\f\u0010\f\u001A\b\u0012\u0002\b\u0003\u0018\u00010\r8V@VX\u0096\u000E¢\u0006\f\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001A\u0010\u0013\u001A\u00020\u0014X\u0096\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0012\u0010\b\u001A\u00020\t8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\'"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "nanoTime", "", "(J)V", "_heap", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "dispose", "", "scheduleTask", "now", "delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class DelayedTask implements Comparable, Runnable, DisposableHandle, ThreadSafeHeapNode {
        private volatile Object _heap;
        private int index;
        public long nanoTime;

        public DelayedTask(long v) {
            this.nanoTime = v;
            this.index = -1;
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((DelayedTask)object0));
        }

        public int compareTo(DelayedTask eventLoopImplBase$DelayedTask0) {
            int v = Long.compare(this.nanoTime - eventLoopImplBase$DelayedTask0.nanoTime, 0L);
            if(v > 0) {
                return 1;
            }
            return v >= 0 ? 0 : -1;
        }

        @Override  // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            synchronized(this) {
                Object object0 = this._heap;
                if(object0 == EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
                    return;
                }
                DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0 = object0 instanceof DelayedTaskQueue ? ((DelayedTaskQueue)object0) : null;
                if(eventLoopImplBase$DelayedTaskQueue0 != null) {
                    eventLoopImplBase$DelayedTaskQueue0.remove(this);
                }
                this._heap = EventLoop_commonKt.access$getDISPOSED_TASK$p();
            }
        }

        // 去混淆评级： 低(20)
        @Override  // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap getHeap() {
            return this._heap instanceof ThreadSafeHeap ? ((ThreadSafeHeap)this._heap) : null;
        }

        @Override  // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        public final int scheduleTask(long v, DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0, EventLoopImplBase eventLoopImplBase0) {
            synchronized(this) {
                if(this._heap == EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
                    return 2;
                }
                synchronized(eventLoopImplBase$DelayedTaskQueue0) {
                    DelayedTask eventLoopImplBase$DelayedTask0 = (DelayedTask)eventLoopImplBase$DelayedTaskQueue0.firstImpl();
                    if(eventLoopImplBase0.isCompleted()) {
                        return 1;
                    }
                    if(eventLoopImplBase$DelayedTask0 == null) {
                        eventLoopImplBase$DelayedTaskQueue0.timeNow = v;
                    }
                    else {
                        long v3 = eventLoopImplBase$DelayedTask0.nanoTime;
                        if(v3 - v < 0L) {
                            v = v3;
                        }
                        if(v - eventLoopImplBase$DelayedTaskQueue0.timeNow > 0L) {
                            eventLoopImplBase$DelayedTaskQueue0.timeNow = v;
                        }
                    }
                    if(this.nanoTime - eventLoopImplBase$DelayedTaskQueue0.timeNow < 0L) {
                        this.nanoTime = eventLoopImplBase$DelayedTaskQueue0.timeNow;
                    }
                    eventLoopImplBase$DelayedTaskQueue0.addImpl(this);
                    return 0;
                }
            }
        }

        @Override  // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(ThreadSafeHeap threadSafeHeap0) {
            if(this._heap == EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            this._heap = threadSafeHeap0;
        }

        @Override  // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int v) {
            this.index = v;
        }

        public final boolean timeToExecute(long v) {
            return v - this.nanoTime >= 0L;
        }

        @Override
        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0003\u001A\u00020\u00048\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeNow", "", "(J)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class DelayedTaskQueue extends ThreadSafeHeap {
        public long timeNow;

        public DelayedTaskQueue(long v) {
            this.timeNow = v;
        }
    }

    @Volatile
    private volatile Object _delayed;
    private static final AtomicReferenceFieldUpdater _delayed$FU;
    @Volatile
    private volatile int _isCompleted;
    private static final AtomicIntegerFieldUpdater _isCompleted$FU;
    @Volatile
    private volatile Object _queue;
    private static final AtomicReferenceFieldUpdater _queue$FU;

    static {
        EventLoopImplBase._queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
        EventLoopImplBase._delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
        EventLoopImplBase._isCompleted$FU = AtomicIntegerFieldUpdater.newUpdater(EventLoopImplBase.class, "_isCompleted");
    }

    public EventLoopImplBase() {
        this._isCompleted = 0;
    }

    private final void closeQueue() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = EventLoopImplBase._queue$FU;
        do {
            while(true) {
                object0 = atomicReferenceFieldUpdater0.get(this);
                if(object0 != null) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, null, EventLoop_commonKt.access$getCLOSED_EMPTY$p())) {
                    return;
                }
            }
            if(object0 instanceof LockFreeTaskQueueCore) {
                ((LockFreeTaskQueueCore)object0).close();
                return;
            }
            if(object0 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                break;
            }
            LockFreeTaskQueueCore lockFreeTaskQueueCore0 = new LockFreeTaskQueueCore(8, true);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
            lockFreeTaskQueueCore0.addLast(((Runnable)object0));
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, object0, lockFreeTaskQueueCore0));
    }

    @Override  // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long v, Continuation continuation0) {
        return DefaultImpls.delay(this, v, continuation0);
    }

    private final Runnable dequeue() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = EventLoopImplBase._queue$FU;
        while(true) {
            object0 = atomicReferenceFieldUpdater0.get(this);
            if(object0 == null) {
                return null;
            }
            if(object0 instanceof LockFreeTaskQueueCore) {
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                Object object1 = ((LockFreeTaskQueueCore)object0).removeFirstOrNull();
                if(object1 != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    return (Runnable)object1;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore0 = ((LockFreeTaskQueueCore)object0).next();
                AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, object0, lockFreeTaskQueueCore0);
            }
            else {
                if(object0 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                    return null;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, object0, null)) {
                    break;
                }
            }
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
        return (Runnable)object0;
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.enqueue(runnable0);
    }

    public void enqueue(Runnable runnable0) {
        if(this.enqueueImpl(runnable0)) {
            this.unpark();
            return;
        }
        DefaultExecutor.INSTANCE.enqueue(runnable0);
    }

    private final boolean enqueueImpl(Runnable runnable0) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = EventLoopImplBase._queue$FU;
        while(true) {
        alab1:
            while(true) {
                while(true) {
                label_1:
                    object0 = atomicReferenceFieldUpdater0.get(this);
                    if(this.isCompleted()) {
                        return false;
                    }
                    if(object0 != null) {
                        break;
                    }
                    if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, null, runnable0)) {
                        return true;
                    }
                }
                if(!(object0 instanceof LockFreeTaskQueueCore)) {
                    goto label_19;
                }
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                int v = ((LockFreeTaskQueueCore)object0).addLast(runnable0);
                switch(v) {
                    case 0: {
                        return true;
                    label_14:
                        if(v != 2) {
                            break;
                        }
                        break alab1;
                    }
                    case 1: {
                        goto label_16;
                    }
                    default: {
                        goto label_14;
                    }
                }
            }
            return false;
        label_16:
            LockFreeTaskQueueCore lockFreeTaskQueueCore0 = ((LockFreeTaskQueueCore)object0).next();
            AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, object0, lockFreeTaskQueueCore0);
            goto label_1;
        label_19:
            if(object0 == EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                return false;
            }
            LockFreeTaskQueueCore lockFreeTaskQueueCore1 = new LockFreeTaskQueueCore(8, true);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
            lockFreeTaskQueueCore1.addLast(((Runnable)object0));
            lockFreeTaskQueueCore1.addLast(runnable0);
            if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(EventLoopImplBase._queue$FU, this, object0, lockFreeTaskQueueCore1)) {
                goto label_1;
            }
            break;
        }
        return true;
    }

    @Override  // kotlinx.coroutines.EventLoop
    protected long getNextTime() {
        if(super.getNextTime() == 0L) {
            return 0L;
        }
        Object object0 = EventLoopImplBase._queue$FU.get(this);
        if(object0 != null) {
            if(!(object0 instanceof LockFreeTaskQueueCore)) {
                return object0 == EventLoop_commonKt.access$getCLOSED_EMPTY$p() ? 0x7FFFFFFFFFFFFFFFL : 0L;
            }
            else if(!((LockFreeTaskQueueCore)object0).isEmpty()) {
                return 0L;
            }
        }
        DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)EventLoopImplBase._delayed$FU.get(this);
        if(eventLoopImplBase$DelayedTaskQueue0 != null) {
            DelayedTask eventLoopImplBase$DelayedTask0 = (DelayedTask)eventLoopImplBase$DelayedTaskQueue0.peek();
            if(eventLoopImplBase$DelayedTask0 != null) {
                long v = eventLoopImplBase$DelayedTask0.nanoTime;
                AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
                return abstractTimeSource0 == null ? RangesKt.coerceAtLeast(v - System.nanoTime(), 0L) : RangesKt.coerceAtLeast(v - abstractTimeSource0.nanoTime(), 0L);
            }
        }
        return 0x7FFFFFFFFFFFFFFFL;
    }

    @Override  // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        return DefaultImpls.invokeOnTimeout(this, v, runnable0, coroutineContext0);
    }

    private final boolean isCompleted() {
        return EventLoopImplBase._isCompleted$FU.get(this) != 0;
    }

    @Override  // kotlinx.coroutines.EventLoop
    protected boolean isEmpty() {
        if(!this.isUnconfinedQueueEmpty()) {
            return false;
        }
        DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)EventLoopImplBase._delayed$FU.get(this);
        if(eventLoopImplBase$DelayedTaskQueue0 != null && !eventLoopImplBase$DelayedTaskQueue0.isEmpty()) {
            return false;
        }
        Object object0 = EventLoopImplBase._queue$FU.get(this);
        if(object0 == null) {
            return true;
        }
        return object0 instanceof LockFreeTaskQueueCore ? ((LockFreeTaskQueueCore)object0).isEmpty() : object0 == EventLoop_commonKt.access$getCLOSED_EMPTY$p();
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    @Override  // kotlinx.coroutines.EventLoop
    public long processNextEvent() {
        if(this.processUnconfinedEvent()) {
            return 0L;
        }
        DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)EventLoopImplBase._delayed$FU.get(this);
        if(eventLoopImplBase$DelayedTaskQueue0 != null && !eventLoopImplBase$DelayedTaskQueue0.isEmpty()) {
            AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
            long v = abstractTimeSource0 == null ? System.nanoTime() : abstractTimeSource0.nanoTime();
            while(true) {
                __monitor_enter(eventLoopImplBase$DelayedTaskQueue0);
                int v1 = FIN.finallyOpen$NT();
                ThreadSafeHeapNode threadSafeHeapNode0 = null;
                ThreadSafeHeapNode threadSafeHeapNode1 = eventLoopImplBase$DelayedTaskQueue0.firstImpl();
                if(threadSafeHeapNode1 == null) {
                    FIN.finallyExec$NT(v1);
                }
                else {
                    if((((DelayedTask)threadSafeHeapNode1).timeToExecute(v) ? this.enqueueImpl(((DelayedTask)threadSafeHeapNode1)) : false)) {
                        threadSafeHeapNode0 = eventLoopImplBase$DelayedTaskQueue0.removeAtImpl(0);
                    }
                    FIN.finallyCodeBegin$NT(v1);
                    __monitor_exit(eventLoopImplBase$DelayedTaskQueue0);
                    FIN.finallyCodeEnd$NT(v1);
                }
                if(((DelayedTask)threadSafeHeapNode0) == null) {
                    break;
                }
            }
        }
        Runnable runnable0 = this.dequeue();
        if(runnable0 != null) {
            runnable0.run();
            return 0L;
        }
        return this.getNextTime();
    }

    private final void rescheduleAllDelayed() {
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        long v = abstractTimeSource0 == null ? System.nanoTime() : abstractTimeSource0.nanoTime();
        DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0;
        while((eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)EventLoopImplBase._delayed$FU.get(this)) != null) {
            DelayedTask eventLoopImplBase$DelayedTask0 = (DelayedTask)eventLoopImplBase$DelayedTaskQueue0.removeFirstOrNull();
            if(eventLoopImplBase$DelayedTask0 == null) {
                break;
            }
            this.reschedule(v, eventLoopImplBase$DelayedTask0);
        }
    }

    protected final void resetAll() {
        EventLoopImplBase._queue$FU.set(this, null);
        EventLoopImplBase._delayed$FU.set(this, null);
    }

    public final void schedule(long v, DelayedTask eventLoopImplBase$DelayedTask0) {
        switch(this.scheduleImpl(v, eventLoopImplBase$DelayedTask0)) {
            case 0: {
                if(this.shouldUnpark(eventLoopImplBase$DelayedTask0)) {
                    this.unpark();
                }
                break;
            }
            case 1: {
                this.reschedule(v, eventLoopImplBase$DelayedTask0);
                return;
            }
            case 2: {
                break;
            }
            default: {
                throw new IllegalStateException("unexpected result");
            }
        }
    }

    private final int scheduleImpl(long v, DelayedTask eventLoopImplBase$DelayedTask0) {
        if(this.isCompleted()) {
            return 1;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = EventLoopImplBase._delayed$FU;
        DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)atomicReferenceFieldUpdater0.get(this);
        if(eventLoopImplBase$DelayedTaskQueue0 == null) {
            AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, null, new DelayedTaskQueue(v));
            Object object0 = atomicReferenceFieldUpdater0.get(this);
            Intrinsics.checkNotNull(object0);
            eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)object0;
        }
        return eventLoopImplBase$DelayedTask0.scheduleTask(v, eventLoopImplBase$DelayedTaskQueue0, this);
    }

    protected final DisposableHandle scheduleInvokeOnTimeout(long v, Runnable runnable0) {
        long v1 = EventLoop_commonKt.delayToNanos(v);
        if(v1 < 0x3FFFFFFFFFFFFFFFL) {
            AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
            long v2 = abstractTimeSource0 == null ? System.nanoTime() : abstractTimeSource0.nanoTime();
            DelayedRunnableTask eventLoopImplBase$DelayedRunnableTask0 = new DelayedRunnableTask(v1 + v2, runnable0);
            this.schedule(v2, eventLoopImplBase$DelayedRunnableTask0);
            return eventLoopImplBase$DelayedRunnableTask0;
        }
        return NonDisposableHandle.INSTANCE;
    }

    @Override  // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long v, CancellableContinuation cancellableContinuation0) {
        long v1 = EventLoop_commonKt.delayToNanos(v);
        if(v1 < 0x3FFFFFFFFFFFFFFFL) {
            AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
            long v2 = abstractTimeSource0 == null ? System.nanoTime() : abstractTimeSource0.nanoTime();
            DelayedResumeTask eventLoopImplBase$DelayedResumeTask0 = new DelayedResumeTask(this, v1 + v2, cancellableContinuation0);
            this.schedule(v2, eventLoopImplBase$DelayedResumeTask0);
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuation0, eventLoopImplBase$DelayedResumeTask0);
        }
    }

    private final void setCompleted(boolean z) {
        EventLoopImplBase._isCompleted$FU.set(this, ((int)z));
    }

    private final boolean shouldUnpark(DelayedTask eventLoopImplBase$DelayedTask0) {
        DelayedTaskQueue eventLoopImplBase$DelayedTaskQueue0 = (DelayedTaskQueue)EventLoopImplBase._delayed$FU.get(this);
        return (eventLoopImplBase$DelayedTaskQueue0 == null ? null : ((DelayedTask)eventLoopImplBase$DelayedTaskQueue0.peek())) == eventLoopImplBase$DelayedTask0;
    }

    @Override  // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        this.setCompleted(true);
        this.closeQueue();
        while(this.processNextEvent() <= 0L) {
        }
        this.rescheduleAllDelayed();
    }
}

