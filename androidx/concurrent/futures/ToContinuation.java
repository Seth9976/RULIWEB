package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001A\u00020\rH\u0016R\u0017\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u000E"}, d2 = {"Landroidx/concurrent/futures/ToContinuation;", "T", "Ljava/lang/Runnable;", "futureToObserve", "Lcom/google/common/util/concurrent/ListenableFuture;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlinx/coroutines/CancellableContinuation;)V", "getContinuation", "()Lkotlinx/coroutines/CancellableContinuation;", "getFutureToObserve", "()Lcom/google/common/util/concurrent/ListenableFuture;", "run", "", "concurrent-futures-ktx"}, k = 1, mv = {1, 1, 16})
final class ToContinuation implements Runnable {
    private final CancellableContinuation continuation;
    private final ListenableFuture futureToObserve;

    public ToContinuation(ListenableFuture listenableFuture0, CancellableContinuation cancellableContinuation0) {
        Intrinsics.checkParameterIsNotNull(listenableFuture0, "futureToObserve");
        Intrinsics.checkParameterIsNotNull(cancellableContinuation0, "continuation");
        super();
        this.futureToObserve = listenableFuture0;
        this.continuation = cancellableContinuation0;
    }

    public final CancellableContinuation getContinuation() {
        return this.continuation;
    }

    public final ListenableFuture getFutureToObserve() {
        return this.futureToObserve;
    }

    @Override
    public void run() {
        if(this.futureToObserve.isCancelled()) {
            DefaultImpls.cancel$default(this.continuation, null, 1, null);
            return;
        }
        try {
            Object object0 = Result.constructor-impl(AbstractResolvableFuture.getUninterruptibly(this.futureToObserve));
            this.continuation.resumeWith(object0);
        }
        catch(ExecutionException executionException0) {
            Object object1 = Result.constructor-impl(ResultKt.createFailure(ListenableFutureKt.nonNullCause(executionException0)));
            this.continuation.resumeWith(object1);
        }
    }
}

