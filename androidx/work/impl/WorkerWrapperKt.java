package androidx.work.impl;

import androidx.work.DirectExecutor;
import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\u001A!\u0010\u0002\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0002¢\u0006\u0002\u0010\u0006\u001A&\u0010\u0007\u001A\u0002H\b\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t2\u0006\u0010\n\u001A\u00020\u000BH\u0087@¢\u0006\u0002\u0010\f\u001A\f\u0010\r\u001A\u00020\u000E*\u00020\u000FH\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"TAG", "", "getUninterruptibly", "V", "future", "Ljava/util/concurrent/Future;", "(Ljava/util/concurrent/Future;)Ljava/lang/Object;", "awaitWithin", "T", "Lcom/google/common/util/concurrent/ListenableFuture;", "worker", "Landroidx/work/ListenableWorker;", "(Lcom/google/common/util/concurrent/ListenableFuture;Landroidx/work/ListenableWorker;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nonNullCause", "", "Ljava/util/concurrent/ExecutionException;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerWrapperKt {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-WorkerWrapper", "tagWithPrefix(\"WorkerWrapper\")");
        WorkerWrapperKt.TAG = "WM-WorkerWrapper";
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器

    public static final Object access$getUninterruptibly(Future future0) {
        return WorkerWrapperKt.getUninterruptibly(future0);
    }

    public static final Throwable access$nonNullCause(ExecutionException executionException0) {
        return WorkerWrapperKt.nonNullCause(executionException0);
    }

    public static final Object awaitWithin(ListenableFuture listenableFuture0, ListenableWorker listenableWorker0, Continuation continuation0) {
        try {
            if(listenableFuture0.isDone()) {
                return WorkerWrapperKt.getUninterruptibly(listenableFuture0);
            }
        }
        catch(ExecutionException executionException0) {
            throw WorkerWrapperKt.nonNullCause(executionException0);
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        listenableFuture0.addListener(new ToContinuation(listenableFuture0, cancellableContinuationImpl0), DirectExecutor.INSTANCE);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(listenableWorker0, listenableFuture0) {
            final ListenableFuture $this_awaitWithin;
            final ListenableWorker $worker;

            {
                this.$worker = listenableWorker0;
                this.$this_awaitWithin = listenableFuture0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                if(throwable0 instanceof WorkerStoppedException) {
                    int v = ((WorkerStoppedException)throwable0).getReason();
                    this.$worker.stop(v);
                }
                this.$this_awaitWithin.cancel(false);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Object getUninterruptibly(Future future0) {
        Object object0;
        boolean z = false;
        while(true) {
            try {
                object0 = future0.get();
                break;
            }
            catch(InterruptedException unused_ex) {
                z = true;
            }
            catch(Throwable throwable0) {
                if(z) {
                    Thread.currentThread().interrupt();
                }
                throw throwable0;
            }
        }
        if(z) {
            Thread.currentThread().interrupt();
        }
        return object0;
    }

    private static final Throwable nonNullCause(ExecutionException executionException0) {
        Throwable throwable0 = executionException0.getCause();
        Intrinsics.checkNotNull(throwable0);
        return throwable0;
    }
}

