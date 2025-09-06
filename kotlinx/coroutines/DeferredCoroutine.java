package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\r\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000EJ\r\u0010\u000F\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010R\u001A\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00000\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/DeferredCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/Deferred;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Z)V", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
class DeferredCoroutine extends AbstractCoroutine implements Deferred {
    public DeferredCoroutine(CoroutineContext coroutineContext0, boolean z) {
        super(coroutineContext0, true, z);
    }

    @Override  // kotlinx.coroutines.Deferred
    public Object await(Continuation continuation0) {
        return this.awaitInternal(continuation0);
    }

    @Override  // kotlinx.coroutines.Deferred
    public Object getCompleted() {
        return this.getCompletedInternal$kotlinx_coroutines_core();
    }

    @Override  // kotlinx.coroutines.Deferred
    public SelectClause1 getOnAwait() {
        SelectClause1 selectClause10 = this.getOnAwaitInternal();
        Intrinsics.checkNotNull(selectClause10, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectClause1<T of kotlinx.coroutines.DeferredCoroutine>");
        return selectClause10;
    }
}

