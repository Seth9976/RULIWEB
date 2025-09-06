package kotlinx.coroutines.debug.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001A\u0004\u0018\u00010\nX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0017\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000E¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001A\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\fR\u0019\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000E8G¢\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0011R\u0013\u0010\u0015\u001A\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001A\u00020\u001A¢\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001CR\u0011\u0010\u001D\u001A\u00020\u001E¢\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 ¨\u0006!"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "", "source", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCreationStackBottom$kotlinx_coroutines_core", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "lastObservedFrame", "getLastObservedFrame", "lastObservedStackTrace", "lastObservedThread", "Ljava/lang/Thread;", "getLastObservedThread", "()Ljava/lang/Thread;", "sequenceNumber", "", "getSequenceNumber", "()J", "state", "", "getState", "()Ljava/lang/String;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DebugCoroutineInfo {
    private final CoroutineContext context;
    private final CoroutineStackFrame creationStackBottom;
    private final List creationStackTrace;
    private final CoroutineStackFrame lastObservedFrame;
    private final List lastObservedStackTrace;
    private final Thread lastObservedThread;
    private final long sequenceNumber;
    private final String state;

    public DebugCoroutineInfo(DebugCoroutineInfoImpl debugCoroutineInfoImpl0, CoroutineContext coroutineContext0) {
        this.context = coroutineContext0;
        this.creationStackBottom = debugCoroutineInfoImpl0.getCreationStackBottom$kotlinx_coroutines_core();
        this.sequenceNumber = debugCoroutineInfoImpl0.sequenceNumber;
        this.creationStackTrace = debugCoroutineInfoImpl0.getCreationStackTrace();
        this.state = debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core();
        this.lastObservedThread = debugCoroutineInfoImpl0.lastObservedThread;
        this.lastObservedFrame = debugCoroutineInfoImpl0.getLastObservedFrame$kotlinx_coroutines_core();
        this.lastObservedStackTrace = debugCoroutineInfoImpl0.lastObservedStackTrace$kotlinx_coroutines_core();
    }

    public final CoroutineContext getContext() {
        return this.context;
    }

    public final CoroutineStackFrame getCreationStackBottom$kotlinx_coroutines_core() {
        return this.creationStackBottom;
    }

    public final List getCreationStackTrace() {
        return this.creationStackTrace;
    }

    public final CoroutineStackFrame getLastObservedFrame() {
        return this.lastObservedFrame;
    }

    public final Thread getLastObservedThread() {
        return this.lastObservedThread;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public final String getState() {
        return this.state;
    }

    public final List lastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }
}

