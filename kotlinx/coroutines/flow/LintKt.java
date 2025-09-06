package kotlinx.coroutines.flow;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0094\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0001\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010#\n\u0002\b\u0002\u001A\"\u0010\u000B\u001A\u00020\f*\u0006\u0012\u0002\b\u00030\u00022\u0010\b\u0002\u0010\r\u001A\n\u0018\u00010\u000Ej\u0004\u0018\u0001`\u000FH\u0007\u001A\u001E\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0007\u001Am\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132D\b\b\u0010\u0015\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0002\u0012\u0013\u0012\u00110\u0017\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u0016\u00A2\u0006\u0002\b\u001CH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001D\u001A\u001E\u0010\u001E\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u001FH\u0007\u001A!\u0010 \u001A\u00020!\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0087H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"\u001A\u001E\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u001FH\u0007\u001A&\u0010$\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010%\u001A\u00020\u0001H\u0007\u001Af\u0010&\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\b\b\u0002\u0010\'\u001A\u00020(23\b\n\u0010)\u001A-\b\u0001\u0012\u0013\u0012\u00110\u0017\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0*H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001A\u0082\u0001\u0010,\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132Y\b\b\u0010)\u001AS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0002\u0012\u0013\u0012\u00110\u0017\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110(\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001A\u0012\u0006\u0012\u0004\u0018\u00010\u001B0-\u00A2\u0006\u0002\b\u001CH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010/\u001A\'\u00100\u001A\b\u0012\u0004\u0012\u0002H\u001201\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0087H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"\u001A/\u00100\u001A\u000202\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\f\u00103\u001A\b\u0012\u0004\u0012\u0002H\u001204H\u0087H\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001A\'\u00106\u001A\b\u0012\u0004\u0012\u0002H\u001207\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0087H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"\u001A/\u00106\u001A\u000202\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\f\u00103\u001A\b\u0012\u0004\u0012\u0002H\u001208H\u0087H\u00F8\u0001\u0000\u00A2\u0006\u0002\u00109\"\"\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0005\u0010\u0006\"\"\u0010\u0007\u001A\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\t\u0010\u0004\u001A\u0004\b\u0007\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006:"}, d2 = {"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/flow/FlowCollector;", "getCoroutineContext$annotations", "(Lkotlinx/coroutines/flow/FlowCollector;)V", "getCoroutineContext", "(Lkotlinx/coroutines/flow/FlowCollector;)Lkotlin/coroutines/CoroutineContext;", "isActive", "", "isActive$annotations", "(Lkotlinx/coroutines/flow/FlowCollector;)Z", "cancel", "", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancellable", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlinx/coroutines/flow/SharedFlow;", "catch", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "conflate", "Lkotlinx/coroutines/flow/StateFlow;", "count", "", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distinctUntilChanged", "flowOn", "context", "retry", "retries", "", "predicate", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/SharedFlow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "toList", "", "", "destination", "", "(Lkotlinx/coroutines/flow/SharedFlow;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSet", "", "", "(Lkotlinx/coroutines/flow/SharedFlow;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LintKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "cancel() is resolved into the extension of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext().cancel() instead or specify the receiver of cancel() explicitly", replaceWith = @ReplaceWith(expression = "currentCoroutineContext().cancel(cause)", imports = {}))
    public static final void cancel(FlowCollector flowCollector0, CancellationException cancellationException0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    public static void cancel$default(FlowCollector flowCollector0, CancellationException cancellationException0, int v, Object object0) {
        if((v & 1) != 0) {
            cancellationException0 = null;
        }
        LintKt.cancel(flowCollector0, cancellationException0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying \'cancellable\' to a SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final Flow cancellable(SharedFlow sharedFlow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "SharedFlow never completes, so this operator typically has not effect, it can only catch exceptions from \'onSubscribe\' operator", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    private static final Flow catch(SharedFlow sharedFlow0, Function3 function30) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.catch>");
        return FlowKt.catch(sharedFlow0, function30);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying \'conflate\' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final Flow conflate(StateFlow stateFlow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "SharedFlow never completes, so this terminal operation never completes.")
    private static final Object count(SharedFlow sharedFlow0, Continuation continuation0) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.count>");
        return FlowKt.count(sharedFlow0, continuation0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying \'distinctUntilChanged\' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final Flow distinctUntilChanged(StateFlow stateFlow0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying \'flowOn\' to SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final Flow flowOn(SharedFlow sharedFlow0, CoroutineContext coroutineContext0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    public static final CoroutineContext getCoroutineContext(FlowCollector flowCollector0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "coroutineContext is resolved into the property of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext() instead or specify the receiver of coroutineContext explicitly", replaceWith = @ReplaceWith(expression = "currentCoroutineContext()", imports = {}))
    public static void getCoroutineContext$annotations(FlowCollector flowCollector0) {
    }

    public static final boolean isActive(FlowCollector flowCollector0) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "isActive is resolved into the extension of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext().isActive or cancellable() operator instead or specify the receiver of isActive explicitly. Additionally, flow {} builder emissions are cancellable by default.", replaceWith = @ReplaceWith(expression = "currentCoroutineContext().isActive", imports = {}))
    public static void isActive$annotations(FlowCollector flowCollector0) {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "SharedFlow never completes, so this operator has no effect.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    private static final Flow retry(SharedFlow sharedFlow0, long v, Function2 function20) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.retry>");
        return FlowKt.retry(sharedFlow0, v, function20);
    }

    static Flow retry$default(SharedFlow sharedFlow0, long v, Function2 function20, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0x7FFFFFFFFFFFFFFFL;
        }
        if((v1 & 2) != 0) {
            function20 = new Function2(null) {
                int label;

                {
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    return new kotlinx.coroutines.flow.LintKt.retry.1(continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((Throwable)object0), ((Continuation)object1));
                }

                public final Object invoke(Throwable throwable0, Continuation continuation0) {
                    return ((kotlinx.coroutines.flow.LintKt.retry.1)this.create(throwable0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    if(this.label != 0) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                    ResultKt.throwOnFailure(object0);
                    return Boxing.boxBoolean(true);
                }
            };
        }
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.retry>");
        return FlowKt.retry(sharedFlow0, v, function20);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "SharedFlow never completes, so this operator has no effect.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    private static final Flow retryWhen(SharedFlow sharedFlow0, Function4 function40) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.retryWhen>");
        return FlowKt.retryWhen(sharedFlow0, function40);
    }

    private static final Object toList(SharedFlow sharedFlow0, List list0, Continuation continuation0) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.toList>");
        FlowKt.toList(sharedFlow0, list0, continuation0);
        throw new IllegalStateException("this code is supposed to be unreachable");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "SharedFlow never completes, so this terminal operation never completes.")
    private static final Object toList(SharedFlow sharedFlow0, Continuation continuation0) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.toList>");
        return FlowKt__CollectionKt.toList$default(sharedFlow0, null, continuation0, 1, null);
    }

    private static final Object toSet(SharedFlow sharedFlow0, Set set0, Continuation continuation0) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.toSet>");
        FlowKt.toSet(sharedFlow0, set0, continuation0);
        throw new IllegalStateException("this code is supposed to be unreachable");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "SharedFlow never completes, so this terminal operation never completes.")
    private static final Object toSet(SharedFlow sharedFlow0, Continuation continuation0) {
        Intrinsics.checkNotNull(sharedFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<T of kotlinx.coroutines.flow.LintKt.toSet>");
        return FlowKt__CollectionKt.toSet$default(sharedFlow0, null, continuation0, 1, null);
    }
}

