package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001B\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\rH\u0014J\u0006\u0010\u0011\u001A\u00020\tJ\u0018\u0010\u0012\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\rR\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\"\u0010\n\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r0\f0\u000BX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/UndispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "threadLocalIsSet", "", "threadStateToRecover", "Ljava/lang/ThreadLocal;", "Lkotlin/Pair;", "", "afterResume", "", "state", "clearThreadContext", "saveThreadContext", "oldValue", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class UndispatchedCoroutine extends ScopeCoroutine {
    private volatile boolean threadLocalIsSet;
    private final ThreadLocal threadStateToRecover;

    public UndispatchedCoroutine(CoroutineContext coroutineContext0, Continuation continuation0) {
        super((coroutineContext0.get(UndispatchedMarker.INSTANCE) == null ? coroutineContext0.plus(UndispatchedMarker.INSTANCE) : coroutineContext0), continuation0);
        this.threadStateToRecover = new ThreadLocal();
        if(!(continuation0.getContext().get(ContinuationInterceptor.Key) instanceof CoroutineDispatcher)) {
            Object object0 = ThreadContextKt.updateThreadContext(coroutineContext0, null);
            ThreadContextKt.restoreThreadContext(coroutineContext0, object0);
            this.saveThreadContext(coroutineContext0, object0);
        }
    }

    @Override  // kotlinx.coroutines.internal.ScopeCoroutine
    protected void afterResume(Object object0) {
        if(this.threadLocalIsSet) {
            Pair pair0 = (Pair)this.threadStateToRecover.get();
            if(pair0 != null) {
                ThreadContextKt.restoreThreadContext(((CoroutineContext)pair0.component1()), pair0.component2());
            }
            this.threadStateToRecover.remove();
        }
        Object object1 = CompletionStateKt.recoverResult(object0, this.uCont);
        Continuation continuation0 = this.uCont;
        CoroutineContext coroutineContext0 = continuation0.getContext();
        UndispatchedCoroutine undispatchedCoroutine0 = null;
        Object object2 = ThreadContextKt.updateThreadContext(coroutineContext0, null);
        if(object2 != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutine0 = CoroutineContextKt.updateUndispatchedCompletion(continuation0, coroutineContext0, object2);
        }
        try {
            this.uCont.resumeWith(object1);
        }
        catch(Throwable throwable0) {
            if(undispatchedCoroutine0 == null || undispatchedCoroutine0.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object2);
            }
            throw throwable0;
        }
        if(undispatchedCoroutine0 != null && !undispatchedCoroutine0.clearThreadContext()) {
            return;
        }
        ThreadContextKt.restoreThreadContext(coroutineContext0, object2);
    }

    public final boolean clearThreadContext() {
        int v = !this.threadLocalIsSet || this.threadStateToRecover.get() != null ? 0 : 1;
        this.threadStateToRecover.remove();
        return v ^ 1;
    }

    public final void saveThreadContext(CoroutineContext coroutineContext0, Object object0) {
        this.threadLocalIsSet = true;
        Pair pair0 = TuplesKt.to(coroutineContext0, object0);
        this.threadStateToRecover.set(pair0);
    }
}

