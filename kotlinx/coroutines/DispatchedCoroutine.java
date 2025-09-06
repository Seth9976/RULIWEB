package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001D\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0014J\u0012\u0010\u000E\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0014J\u000F\u0010\u000F\u001A\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001A\u00020\u0012H\u0002J\b\u0010\u0013\u001A\u00020\u0012H\u0002R\u000B\u0010\b\u001A\u00020\t8\u0006X\u0087\u0004¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/DispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "afterCompletion", "", "state", "", "afterResume", "getResult", "getResult$kotlinx_coroutines_core", "tryResume", "", "trySuspend", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DispatchedCoroutine extends ScopeCoroutine {
    @Volatile
    private volatile int _decision;
    private static final AtomicIntegerFieldUpdater _decision$FU;

    static {
        DispatchedCoroutine._decision$FU = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision");
    }

    public DispatchedCoroutine(CoroutineContext coroutineContext0, Continuation continuation0) {
        super(coroutineContext0, continuation0);
    }

    @Override  // kotlinx.coroutines.internal.ScopeCoroutine
    protected void afterCompletion(Object object0) {
        this.afterResume(object0);
    }

    @Override  // kotlinx.coroutines.internal.ScopeCoroutine
    protected void afterResume(Object object0) {
        if(this.tryResume()) {
            return;
        }
        DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(object0, this.uCont), null, 2, null);
    }

    public final Object getResult$kotlinx_coroutines_core() {
        if(this.trySuspend()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object object0 = JobSupportKt.unboxState(this.getState$kotlinx_coroutines_core());
        if(object0 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally)object0).cause;
        }
        return object0;
    }

    public static final AtomicIntegerFieldUpdater get_decision$FU() {
        return DispatchedCoroutine._decision$FU;
    }

    private final void loop$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicIntegerFieldUpdater0.get(object0));
        }
    }

    private final boolean tryResume() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = DispatchedCoroutine._decision$FU;
    alab1:
        while(true) {
            switch(atomicIntegerFieldUpdater0.get(this)) {
                case 0: {
                    if(!DispatchedCoroutine._decision$FU.compareAndSet(this, 0, 2)) {
                        break;
                    }
                    break alab1;
                }
                case 1: {
                    return false;
                }
                default: {
                    throw new IllegalStateException("Already resumed");
                }
            }
        }
        return true;
    }

    private final boolean trySuspend() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = DispatchedCoroutine._decision$FU;
    alab1:
        while(true) {
            switch(atomicIntegerFieldUpdater0.get(this)) {
                case 0: {
                    if(!DispatchedCoroutine._decision$FU.compareAndSet(this, 0, 1)) {
                        break;
                    }
                    break alab1;
                }
                case 2: {
                    return false;
                }
                default: {
                    throw new IllegalStateException("Already suspended");
                }
            }
        }
        return true;
    }
}

