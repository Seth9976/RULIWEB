package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0014J\b\u0010\r\u001A\u00020\bH\u0004R\u0012\u0010\u0003\u001A\u00020\u0004X¤\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/EventLoop;", "()V", "thread", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "reschedule", "", "now", "", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "unpark", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class EventLoopImplPlatform extends EventLoop {
    protected abstract Thread getThread();

    protected void reschedule(long v, DelayedTask eventLoopImplBase$DelayedTask0) {
        DefaultExecutor.INSTANCE.schedule(v, eventLoopImplBase$DelayedTask0);
    }

    protected final void unpark() {
        Unit unit0;
        Thread thread0 = this.getThread();
        if(Thread.currentThread() != thread0) {
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
}

