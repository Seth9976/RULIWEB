package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\u001A!\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001A\f\u0010\u0004\u001A\u00020\u0005*\u00020\u0006H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"await", "T", "Lcom/google/common/util/concurrent/ListenableFuture;", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nonNullCause", "", "Ljava/util/concurrent/ExecutionException;", "concurrent-futures-ktx"}, k = 2, mv = {1, 1, 16})
public final class ListenableFutureKt {
    public static final Object await(ListenableFuture listenableFuture0, Continuation continuation0) {
        try {
            if(listenableFuture0.isDone()) {
                return AbstractResolvableFuture.getUninterruptibly(listenableFuture0);
            }
        }
        catch(ExecutionException executionException0) {
            throw ListenableFutureKt.nonNullCause(executionException0);
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        listenableFuture0.addListener(new ToContinuation(listenableFuture0, cancellableContinuationImpl0), DirectExecutor.INSTANCE);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(listenableFuture0) {
            final ListenableFuture $this_await$inlined;

            {
                this.$this_await$inlined = listenableFuture0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                this.$this_await$inlined.cancel(false);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Throwable nonNullCause(ExecutionException executionException0) {
        Throwable throwable0 = executionException0.getCause();
        if(throwable0 == null) {
            Intrinsics.throwNpe();
        }
        return throwable0;
    }
}

