package kotlinx.coroutines.debug.internal;

import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000E\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000B\u001A\u0004\b\t\u0010\nR\u0013\u0010\f\u001A\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0017\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001A\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u000FR\u0013\u0010\u0017\u001A\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u000FR\u0013\u0010\u0019\u001A\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u000FR\u0011\u0010\u001B\u001A\u00020\b¢\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001DR\u0011\u0010\u001E\u001A\u00020\r¢\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010\u000F¨\u0006 "}, d2 = {"Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "Ljava/io/Serializable;", "source", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/CoroutineContext;)V", "coroutineId", "", "getCoroutineId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "dispatcher", "", "getDispatcher", "()Ljava/lang/String;", "lastObservedStackTrace", "", "Ljava/lang/StackTraceElement;", "getLastObservedStackTrace", "()Ljava/util/List;", "lastObservedThreadName", "getLastObservedThreadName", "lastObservedThreadState", "getLastObservedThreadState", "name", "getName", "sequenceNumber", "getSequenceNumber", "()J", "state", "getState", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DebuggerInfo implements Serializable {
    private final Long coroutineId;
    private final String dispatcher;
    private final List lastObservedStackTrace;
    private final String lastObservedThreadName;
    private final String lastObservedThreadState;
    private final String name;
    private final long sequenceNumber;
    private final String state;

    public DebuggerInfo(DebugCoroutineInfoImpl debugCoroutineInfoImpl0, CoroutineContext coroutineContext0) {
        String s1;
        CoroutineId coroutineId0 = (CoroutineId)coroutineContext0.get(CoroutineId.Key);
        String s = null;
        this.coroutineId = coroutineId0 == null ? null : coroutineId0.getId();
        ContinuationInterceptor continuationInterceptor0 = (ContinuationInterceptor)coroutineContext0.get(ContinuationInterceptor.Key);
        this.dispatcher = continuationInterceptor0 == null ? null : continuationInterceptor0.toString();
        CoroutineName coroutineName0 = (CoroutineName)coroutineContext0.get(CoroutineName.Key);
        this.name = coroutineName0 == null ? null : coroutineName0.getName();
        this.state = debugCoroutineInfoImpl0.getState$kotlinx_coroutines_core();
        Thread thread0 = debugCoroutineInfoImpl0.lastObservedThread;
        if(thread0 == null) {
            s1 = null;
        }
        else {
            Thread.State thread$State0 = thread0.getState();
            s1 = thread$State0 == null ? null : thread$State0.toString();
        }
        this.lastObservedThreadState = s1;
        Thread thread1 = debugCoroutineInfoImpl0.lastObservedThread;
        if(thread1 != null) {
            s = thread1.getName();
        }
        this.lastObservedThreadName = s;
        this.lastObservedStackTrace = debugCoroutineInfoImpl0.lastObservedStackTrace$kotlinx_coroutines_core();
        this.sequenceNumber = debugCoroutineInfoImpl0.sequenceNumber;
    }

    public final Long getCoroutineId() {
        return this.coroutineId;
    }

    public final String getDispatcher() {
        return this.dispatcher;
    }

    public final List getLastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }

    public final String getLastObservedThreadName() {
        return this.lastObservedThreadName;
    }

    public final String getLastObservedThreadState() {
        return this.lastObservedThreadState;
    }

    public final String getName() {
        return this.name;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public final String getState() {
        return this.state;
    }
}

