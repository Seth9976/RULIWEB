package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001F\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0012\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0014J\u000B\u0010\u0011\u001A\u00028\u0000¢\u0006\u0002\u0010\u0012R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001A\u00020\u000B8TX\u0094\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\f¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/BlockingCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "blockedThread", "Ljava/lang/Thread;", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Thread;Lkotlinx/coroutines/EventLoop;)V", "isScopedCoroutine", "", "()Z", "afterCompletion", "", "state", "", "joinBlocking", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class BlockingCoroutine extends AbstractCoroutine {
    private final Thread blockedThread;
    private final EventLoop eventLoop;

    public BlockingCoroutine(CoroutineContext coroutineContext0, Thread thread0, EventLoop eventLoop0) {
        super(coroutineContext0, true, true);
        this.blockedThread = thread0;
        this.eventLoop = eventLoop0;
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected void afterCompletion(Object object0) {
        Unit unit0;
        if(!Intrinsics.areEqual(Thread.currentThread(), this.blockedThread)) {
            Thread thread0 = this.blockedThread;
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
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected boolean isScopedCoroutine() {
        return true;
    }

    public final Object joinBlocking() {
        Unit unit0;
        int v;
        CompletedExceptionally completedExceptionally0;
        AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource0 != null) {
            abstractTimeSource0.registerTimeLoopThread();
        }
        try {
            EventLoop eventLoop0 = this.eventLoop;
            completedExceptionally0 = null;
            if(eventLoop0 != null) {
                EventLoop.incrementUseCount$default(eventLoop0, false, 1, null);
            }
            v = FIN.finallyOpen$NT();
            while(true) {
                if(Thread.interrupted()) {
                    InterruptedException interruptedException0 = new InterruptedException();
                    this.cancelCoroutine(interruptedException0);
                    FIN.finallyExec$NT(v);
                    throw interruptedException0;
                }
                long v1 = this.eventLoop == null ? 0x7FFFFFFFFFFFFFFFL : this.eventLoop.processNextEvent();
                if(this.isCompleted()) {
                    break;
                }
                AbstractTimeSource abstractTimeSource1 = AbstractTimeSourceKt.getTimeSource();
                if(abstractTimeSource1 == null) {
                    unit0 = null;
                }
                else {
                    abstractTimeSource1.parkNanos(this, v1);
                    unit0 = Unit.INSTANCE;
                }
                if(unit0 == null) {
                    LockSupport.parkNanos(this, v1);
                }
            }
            FIN.finallyCodeBegin$NT(v);
            EventLoop eventLoop1 = this.eventLoop;
            if(eventLoop1 != null) {
                EventLoop.decrementUseCount$default(eventLoop1, false, 1, null);
            }
        }
        catch(Throwable throwable0) {
            AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.getTimeSource();
            if(abstractTimeSource2 != null) {
                abstractTimeSource2.unregisterTimeLoopThread();
            }
            throw throwable0;
        }
        FIN.finallyCodeEnd$NT(v);
        AbstractTimeSource abstractTimeSource3 = AbstractTimeSourceKt.getTimeSource();
        if(abstractTimeSource3 != null) {
            abstractTimeSource3.unregisterTimeLoopThread();
        }
        Object object0 = JobSupportKt.unboxState(this.getState$kotlinx_coroutines_core());
        if(object0 instanceof CompletedExceptionally) {
            completedExceptionally0 = (CompletedExceptionally)object0;
        }
        if(completedExceptionally0 != null) {
            throw completedExceptionally0.cause;
        }
        return object0;
    }
}

