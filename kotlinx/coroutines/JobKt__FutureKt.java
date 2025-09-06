package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u001A\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004\u001A\u0018\u0010\u0005\u001A\u00020\u0006*\u00020\u00072\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, d2 = {"cancelFutureOnCancellation", "", "Lkotlinx/coroutines/CancellableContinuation;", "future", "Ljava/util/concurrent/Future;", "cancelFutureOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/JobKt")
final class JobKt__FutureKt {
    public static final void cancelFutureOnCancellation(CancellableContinuation cancellableContinuation0, Future future0) {
        cancellableContinuation0.invokeOnCancellation(new CancelFutureOnCancel(future0));
    }

    public static final DisposableHandle cancelFutureOnCompletion(Job job0, Future future0) {
        return job0.invokeOnCompletion(new CancelFutureOnCompletion(future0));
    }
}

