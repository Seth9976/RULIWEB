package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000F\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000F\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001A\u00020\f2\u0006\u0010\u0012\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001A\u00020\f2\u0006\u0010\u0015\u001A\u00020\u0016H\u0016J\r\u0010\u0017\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018R\u001A\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0014\u0010\u000B\u001A\u00020\f8PX\u0090\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CompletableDeferredImpl;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableDeferred;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onCancelComplete", "", "getOnCancelComplete$kotlinx_coroutines_core", "()Z", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "getCompleted", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class CompletableDeferredImpl extends JobSupport implements CompletableDeferred {
    public CompletableDeferredImpl(Job job0) {
        super(true);
        this.initParentJob(job0);
    }

    @Override  // kotlinx.coroutines.Deferred
    public Object await(Continuation continuation0) {
        return this.awaitInternal(continuation0);
    }

    @Override  // kotlinx.coroutines.CompletableDeferred
    public boolean complete(Object object0) {
        return this.makeCompleting$kotlinx_coroutines_core(object0);
    }

    @Override  // kotlinx.coroutines.CompletableDeferred
    public boolean completeExceptionally(Throwable throwable0) {
        return this.makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(throwable0, false, 2, null));
    }

    @Override  // kotlinx.coroutines.Deferred
    public Object getCompleted() {
        return this.getCompletedInternal$kotlinx_coroutines_core();
    }

    @Override  // kotlinx.coroutines.Deferred
    public SelectClause1 getOnAwait() {
        SelectClause1 selectClause10 = this.getOnAwaitInternal();
        Intrinsics.checkNotNull(selectClause10, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectClause1<T of kotlinx.coroutines.CompletableDeferredImpl>");
        return selectClause10;
    }

    @Override  // kotlinx.coroutines.JobSupport
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}

