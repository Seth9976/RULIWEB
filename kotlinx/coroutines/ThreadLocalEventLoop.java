package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadLocalKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000F\u0010\n\u001A\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\u000BJ\r\u0010\f\u001A\u00020\rH\u0000¢\u0006\u0002\b\u000EJ\u0015\u0010\u000F\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\u0004H\u0000¢\u0006\u0002\b\u0010R\u0014\u0010\u0003\u001A\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\"\u0010\u0007\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\bj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/ThreadLocalEventLoop;", "", "()V", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "getEventLoop$kotlinx_coroutines_core", "()Lkotlinx/coroutines/EventLoop;", "ref", "Ljava/lang/ThreadLocal;", "Lkotlinx/coroutines/internal/CommonThreadLocal;", "currentOrNull", "currentOrNull$kotlinx_coroutines_core", "resetEventLoop", "", "resetEventLoop$kotlinx_coroutines_core", "setEventLoop", "setEventLoop$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ThreadLocalEventLoop {
    public static final ThreadLocalEventLoop INSTANCE;
    private static final ThreadLocal ref;

    static {
        ThreadLocalEventLoop.INSTANCE = new ThreadLocalEventLoop();
        ThreadLocalEventLoop.ref = ThreadLocalKt.commonThreadLocal(new Symbol("ThreadLocalEventLoop"));
    }

    public final EventLoop currentOrNull$kotlinx_coroutines_core() {
        return (EventLoop)ThreadLocalEventLoop.ref.get();
    }

    public final EventLoop getEventLoop$kotlinx_coroutines_core() {
        ThreadLocal threadLocal0 = ThreadLocalEventLoop.ref;
        EventLoop eventLoop0 = (EventLoop)threadLocal0.get();
        if(eventLoop0 == null) {
            eventLoop0 = EventLoopKt.createEventLoop();
            threadLocal0.set(eventLoop0);
        }
        return eventLoop0;
    }

    public final void resetEventLoop$kotlinx_coroutines_core() {
        ThreadLocalEventLoop.ref.set(null);
    }

    public final void setEventLoop$kotlinx_coroutines_core(EventLoop eventLoop0) {
        ThreadLocalEventLoop.ref.set(eventLoop0);
    }
}

