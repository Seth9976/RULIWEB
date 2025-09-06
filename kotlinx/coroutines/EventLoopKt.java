package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.scheduling.CoroutineScheduler.Worker;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\u001A\b\u0010\u0000\u001A\u00020\u0001H\u0000\u001A\u0019\u0010\u0002\u001A\u00020\u00032\u000E\b\u0004\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\b\u001A\b\u0010\u0006\u001A\u00020\u0007H\u0007\u001A\b\u0010\b\u001A\u00020\u0007H\u0001\u001A\f\u0010\t\u001A\u00020\n*\u00020\u000BH\u0001¨\u0006\f"}, d2 = {"createEventLoop", "Lkotlinx/coroutines/EventLoop;", "platformAutoreleasePool", "", "block", "Lkotlin/Function0;", "processNextEventInCurrentThread", "", "runSingleTaskFromCurrentSystemDispatcher", "isIoDispatcherThread", "", "Ljava/lang/Thread;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class EventLoopKt {
    public static final EventLoop createEventLoop() {
        return new BlockingEventLoop(Thread.currentThread());
    }

    public static final boolean isIoDispatcherThread(Thread thread0) {
        return thread0 instanceof Worker ? ((Worker)thread0).isIo() : false;
    }

    public static final void platformAutoreleasePool(Function0 function00) {
        function00.invoke();
    }

    public static final long processNextEventInCurrentThread() {
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        return eventLoop0 == null ? 0x7FFFFFFFFFFFFFFFL : eventLoop0.processNextEvent();
    }

    public static final long runSingleTaskFromCurrentSystemDispatcher() {
        Thread thread0 = Thread.currentThread();
        if(!(thread0 instanceof Worker)) {
            throw new IllegalStateException("Expected CoroutineScheduler.Worker, but got " + thread0);
        }
        return ((Worker)thread0).runSingleTask();
    }
}

