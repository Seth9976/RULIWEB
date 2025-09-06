package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004BK\u00129\u0010\u0005\u001A5\b\u0001\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00A2\u0006\u0002\b\b\u0012\u0006\u0010\t\u001A\u00028\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\nJ\u0019\u0010\u0015\u001A\u00028\u00012\u0006\u0010\t\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0016Jc\u0010\u0017\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000429\u0010\u0018\u001A5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00A2\u0006\u0002\b\b2\u000E\u0010\u000B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0002\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0019J\u001E\u0010\u001A\u001A\u00020\u001B2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001CJ\u000B\u0010\u001D\u001A\u00028\u0001\u00A2\u0006\u0002\u0010\u001EJ5\u0010\u0015\u001A\u0002H\u001F\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010\u001F*\u000E\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\u001F0!2\u0006\u0010\t\u001A\u0002H H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"R\u0018\u0010\u000B\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FRF\u0010\u0010\u001A5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00A2\u0006\u0002\b\bX\u0082\u000E\u00F8\u0001\u0000\u00A2\u0006\u0004\n\u0002\u0010\u0011R\u001E\u0010\u0012\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0013X\u0082\u000E\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\t\u001A\u0004\u0018\u00010\u0007X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00A8\u0006#"}, d2 = {"Lkotlin/DeepRecursiveScopeImpl;", "T", "R", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function3;", "", "Lkotlin/ExtensionFunctionType;", "value", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)V", "cont", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "function", "Lkotlin/jvm/functions/Function3;", "result", "Lkotlin/Result;", "Ljava/lang/Object;", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "crossFunctionCompletion", "currentFunction", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resumeWith", "", "(Ljava/lang/Object;)V", "runCallLoop", "()Ljava/lang/Object;", "S", "U", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class DeepRecursiveScopeImpl extends DeepRecursiveScope implements Continuation {
    private Continuation cont;
    private Function3 function;
    private Object result;
    private Object value;

    public DeepRecursiveScopeImpl(Function3 function30, Object object0) {
        Intrinsics.checkNotNullParameter(function30, "block");
        super(null);
        this.function = function30;
        this.value = object0;
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        this.cont = this;
        this.result = DeepRecursiveKt.UNDEFINED_RESULT;
    }

    @Override  // kotlin.DeepRecursiveScope
    public Object callRecursive(Object object0, Continuation continuation0) {
        Intrinsics.checkNotNull(continuation0, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        this.cont = continuation0;
        this.value = object0;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1;
    }

    @Override  // kotlin.DeepRecursiveScope
    public Object callRecursive(DeepRecursiveFunction deepRecursiveFunction0, Object object0, Continuation continuation0) {
        Function3 function30 = deepRecursiveFunction0.getBlock$kotlin_stdlib();
        Intrinsics.checkNotNull(function30, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.coroutines.SuspendFunction2<kotlin.DeepRecursiveScope<*, *>, kotlin.Any?, kotlin.Any?>{ kotlin.DeepRecursiveKt.DeepRecursiveFunctionBlock }");
        Function3 function31 = this.function;
        if(function30 == function31) {
            Intrinsics.checkNotNull(continuation0, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            this.cont = continuation0;
        }
        else {
            this.function = function30;
            Intrinsics.checkNotNull(continuation0, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            this.cont = this.crossFunctionCompletion(function31, continuation0);
        }
        this.value = object0;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1;
    }

    private final Continuation crossFunctionCompletion(Function3 function30, Continuation continuation0) {
        return new Continuation() {
            @Override  // kotlin.coroutines.Continuation
            public CoroutineContext getContext() {
                return this;
            }

            @Override  // kotlin.coroutines.Continuation
            public void resumeWith(Object object0) {
                function30.function = continuation0;
                function30.cont = this.$cont$inlined;
                function30.result = object0;
            }
        };
    }

    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        this.cont = null;
        this.result = object0;
    }

    public final Object runCallLoop() {
        Object object2;
        Object object0;
        while(true) {
            object0 = this.result;
            Continuation continuation0 = this.cont;
            if(continuation0 == null) {
                break;
            }
            if(Result.equals-impl0(DeepRecursiveKt.UNDEFINED_RESULT, object0)) {
                try {
                    Function3 function30 = this.function;
                    Object object1 = this.value;
                    object2 = function30 instanceof BaseContinuationImpl ? ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function30, 3)).invoke(this, object1, continuation0) : IntrinsicsKt.wrapWithContinuationImpl(function30, this, object1, continuation0);
                }
                catch(Throwable throwable0) {
                    continuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
                    continue;
                }
                if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    continue;
                }
                continuation0.resumeWith(object2);
            }
            else {
                this.result = DeepRecursiveKt.UNDEFINED_RESULT;
                continuation0.resumeWith(object0);
            }
        }
        ResultKt.throwOnFailure(object0);
        return object0;
    }
}

