package kotlinx.coroutines.future;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001A\u0010\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0005\u001A\u001C\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001A!\u0010\b\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001A[\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\r2\b\b\u0002\u0010\u000E\u001A\u00020\u000F2\'\u0010\u0010\u001A#\b\u0001\u0012\u0004\u0012\u00020\u000B\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001A\u0018\u0010\u0016\u001A\u00020\u0004*\u00020\u00052\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u0001H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"asCompletableFuture", "Ljava/util/concurrent/CompletableFuture;", "T", "Lkotlinx/coroutines/Deferred;", "", "Lkotlinx/coroutines/Job;", "asDeferred", "Ljava/util/concurrent/CompletionStage;", "await", "(Ljava/util/concurrent/CompletionStage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "future", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "start", "Lkotlinx/coroutines/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Ljava/util/concurrent/CompletableFuture;", "setupCancellation", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FutureKt {
    // 检测为 Lambda 实现
    public static Object $r8$lambda$CVsR10o-YJP4IN5741Ey9yO9s50(Function2 function20, Object object0, Throwable throwable0) [...]

    // 检测为 Lambda 实现
    public static Unit $r8$lambda$nhDb5E9NjAZTF31felWqlfP4Z1A(Job job0, Object object0, Throwable throwable0) [...]

    public static final CompletableFuture asCompletableFuture(Deferred deferred0) {
        CompletableFuture completableFuture0 = Platform..ExternalSyntheticApiModelOutline0.m();
        FutureKt.setupCancellation(deferred0, completableFuture0);
        deferred0.invokeOnCompletion(new Function1(completableFuture0, deferred0) {
            final CompletableFuture $future;
            final Deferred $this_asCompletableFuture;

            {
                this.$future = completableFuture0;
                this.$this_asCompletableFuture = deferred0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                try {
                    Object object0 = this.$this_asCompletableFuture.getCompleted();
                    this.$future.complete(object0);
                }
                catch(Throwable throwable1) {
                    Platform..ExternalSyntheticApiModelOutline0.m(this.$future, throwable1);
                }
            }
        });
        return completableFuture0;
    }

    public static final CompletableFuture asCompletableFuture(Job job0) {
        CompletableFuture completableFuture0 = Platform..ExternalSyntheticApiModelOutline0.m();
        FutureKt.setupCancellation(job0, completableFuture0);
        job0.invokeOnCompletion(new Function1(completableFuture0) {
            final CompletableFuture $future;

            {
                this.$future = completableFuture0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                if(throwable0 == null) {
                    this.$future.complete(Unit.INSTANCE);
                    return;
                }
                Platform..ExternalSyntheticApiModelOutline0.m(this.$future, throwable0);
            }
        });
        return completableFuture0;
    }

    public static final Deferred asDeferred(CompletionStage completionStage0) {
        CompletableFuture completableFuture0 = Platform..ExternalSyntheticApiModelOutline0.m(completionStage0);
        if(Platform..ExternalSyntheticApiModelOutline0.m(completableFuture0)) {
            try {
                return CompletableDeferredKt.CompletableDeferred(Platform..ExternalSyntheticApiModelOutline0.m(completableFuture0));
            }
            catch(Throwable throwable0) {
                ExecutionException executionException0 = throwable0 instanceof ExecutionException ? ((ExecutionException)throwable0) : null;
                if(executionException0 != null) {
                    Throwable throwable1 = executionException0.getCause();
                    if(throwable1 != null) {
                        throwable0 = throwable1;
                    }
                }
                CompletableDeferred completableDeferred0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                completableDeferred0.completeExceptionally(throwable0);
                return completableDeferred0;
            }
        }
        CompletableDeferred completableDeferred1 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        Platform..ExternalSyntheticApiModelOutline0.m(completionStage0, (Object object0, Throwable throwable0) -> FutureKt.asDeferred$lambda$4(new kotlinx.coroutines.future.FutureKt.asDeferred.2(completableDeferred1), object0, throwable0));
        JobKt.cancelFutureOnCompletion(completableDeferred1, completableFuture0);
        return completableDeferred1;

        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001A\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\u0004\b\u0000\u0010\u00032\u000E\u0010\u0004\u001A\n \u0002*\u0004\u0018\u0001H\u0003H\u00032\u000E\u0010\u0005\u001A\n \u0002*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "T", "value", "exception", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Throwable;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.future.FutureKt.asDeferred.2 extends Lambda implements Function2 {
            final CompletableDeferred $result;

            kotlinx.coroutines.future.FutureKt.asDeferred.2(CompletableDeferred completableDeferred0) {
                this.$result = completableDeferred0;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Throwable)object1));
            }

            public final Object invoke(Object object0, Throwable throwable0) {
                try {
                    if(throwable0 == null) {
                        return Boolean.valueOf(this.$result.complete(object0));
                    }
                    CompletableDeferred completableDeferred0 = this.$result;
                    CompletionException completionException0 = Platform..ExternalSyntheticApiModelOutline0.m(throwable0) ? ((CompletionException)throwable0) : null;
                    if(completionException0 != null) {
                        Throwable throwable2 = Platform..ExternalSyntheticApiModelOutline0.m(completionException0);
                        if(throwable2 != null) {
                            throwable0 = throwable2;
                        }
                    }
                    return Boolean.valueOf(completableDeferred0.completeExceptionally(throwable0));
                }
                catch(Throwable throwable1) {
                    CoroutineExceptionHandlerKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, throwable1);
                    return Unit.INSTANCE;
                }
            }
        }

    }

    private static final Object asDeferred$lambda$4(Function2 function20, Object object0, Throwable throwable0) {
        return function20.invoke(object0, throwable0);
    }

    public static final Object await(CompletionStage completionStage0, Continuation continuation0) {
        CompletableFuture completableFuture0 = Platform..ExternalSyntheticApiModelOutline0.m(completionStage0);
        if(Platform..ExternalSyntheticApiModelOutline0.m(completableFuture0)) {
            try {
                return Platform..ExternalSyntheticApiModelOutline0.m(completableFuture0);
            }
            catch(ExecutionException executionException0) {
                Throwable throwable0 = executionException0.getCause();
                if(throwable0 == null) {
                    throwable0 = executionException0;
                }
                throw throwable0;
            }
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        ContinuationHandler continuationHandler0 = new ContinuationHandler(cancellableContinuationImpl0);
        Platform..ExternalSyntheticApiModelOutline0.m(completionStage0, continuationHandler0);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(completableFuture0, continuationHandler0) {
            final ContinuationHandler $consumer;
            final CompletableFuture $future;

            {
                this.$future = completableFuture0;
                this.$consumer = continuationHandler0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                Platform..ExternalSyntheticApiModelOutline0.m(this.$future, false);
                this.$consumer.cont = null;
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final CompletableFuture future(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        CoroutineContext coroutineContext1 = CoroutineContextKt.newCoroutineContext(coroutineScope0, coroutineContext0);
        CompletableFuture completableFuture0 = Platform..ExternalSyntheticApiModelOutline0.m();
        CompletableFutureCoroutine completableFutureCoroutine0 = new CompletableFutureCoroutine(coroutineContext1, completableFuture0);
        Platform..ExternalSyntheticApiModelOutline0.m(completableFuture0, completableFutureCoroutine0);
        completableFutureCoroutine0.start(coroutineStart0, completableFutureCoroutine0, function20);
        return completableFuture0;
    }

    public static CompletableFuture future$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v & 2) != 0) {
            coroutineStart0 = CoroutineStart.DEFAULT;
        }
        return FutureKt.future(coroutineScope0, coroutineContext0, coroutineStart0, function20);
    }

    private static final void setupCancellation(Job job0, CompletableFuture completableFuture0) {
        Platform..ExternalSyntheticApiModelOutline0.m(completableFuture0, (Object object0, Throwable throwable0) -> FutureKt.setupCancellation$lambda$2(job0, object0, throwable0));
    }

    private static final Unit setupCancellation$lambda$2(Job job0, Object object0, Throwable throwable0) {
        CancellationException cancellationException0 = null;
        if(throwable0 != null) {
            if(throwable0 instanceof CancellationException) {
                cancellationException0 = (CancellationException)throwable0;
            }
            if(cancellationException0 == null) {
                cancellationException0 = ExceptionsKt.CancellationException("CompletableFuture was completed exceptionally", throwable0);
            }
        }
        job0.cancel(cancellationException0);
        return Unit.INSTANCE;
    }
}

