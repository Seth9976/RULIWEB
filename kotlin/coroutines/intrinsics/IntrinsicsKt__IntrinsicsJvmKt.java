package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001AF\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u00012\u001C\b\u0004\u0010\u0005\u001A\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0083\b\u00A2\u0006\u0002\b\b\u001A\'\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0002\u00A2\u0006\u0002\b\n\u001AD\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\f\u001A]\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000E\u00A2\u0006\u0002\b\u000F2\u0006\u0010\u0010\u001A\u0002H\r2\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0011\u001A\u001E\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001AA\u0010\u0013\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014\u001AZ\u0010\u0013\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000E\u00A2\u0006\u0002\b\u000F2\u0006\u0010\u0010\u001A\u0002H\r2\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001An\u0010\u0013\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0016\"\u0004\b\u0002\u0010\u0003*)\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0017\u00A2\u0006\u0002\b\u000F2\u0006\u0010\u0010\u001A\u0002H\r2\u0006\u0010\u0018\u001A\u0002H\u00162\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0081\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0019\u001A@\u0010\u001A\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014\u001AY\u0010\u001A\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000E\u00A2\u0006\u0002\b\u000F2\u0006\u0010\u0010\u001A\u0002H\r2\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001Am\u0010\u001A\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0016\"\u0004\b\u0002\u0010\u0003*)\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0017\u00A2\u0006\u0002\b\u000F2\u0006\u0010\u0010\u001A\u0002H\r2\u0006\u0010\u0018\u001A\u0002H\u00162\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u001B"}, d2 = {"createCoroutineFromSuspendFunction", "Lkotlin/coroutines/Continuation;", "", "T", "completion", "block", "Lkotlin/Function1;", "", "createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt", "createSimpleCoroutineForSuspendFunction", "createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt", "createCoroutineUnintercepted", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "intercepted", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "P", "Lkotlin/Function3;", "param", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wrapWithContinuationImpl", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/coroutines/intrinsics/IntrinsicsKt")
class IntrinsicsKt__IntrinsicsJvmKt {
    private static final Continuation createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(Continuation continuation0, Function1 function10) {
        CoroutineContext coroutineContext0 = continuation0.getContext();
        return coroutineContext0 == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(continuation0) {
            private int label;

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                switch(this.label) {
                    case 0: {
                        this.label = 1;
                        ResultKt.throwOnFailure(object0);
                        return function10.invoke(this);
                    }
                    case 1: {
                        this.label = 2;
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                }
            }
        } : new ContinuationImpl(continuation0, coroutineContext0) {
            private int label;

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                switch(this.label) {
                    case 0: {
                        this.label = 1;
                        ResultKt.throwOnFailure(object0);
                        return function10.invoke(this);
                    }
                    case 1: {
                        this.label = 2;
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                }
            }
        };
    }

    public static final Continuation createCoroutineUnintercepted(Function1 function10, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function10, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        Continuation continuation1 = DebugProbesKt.probeCoroutineCreated(continuation0);
        if(function10 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl)function10).create(continuation1);
        }
        CoroutineContext coroutineContext0 = continuation1.getContext();
        return coroutineContext0 == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(continuation1) {
            private int label;

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                switch(this.label) {
                    case 0: {
                        this.label = 1;
                        ResultKt.throwOnFailure(object0);
                        Intrinsics.checkNotNull(function10, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                        return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function10, 1)).invoke(this);
                    }
                    case 1: {
                        this.label = 2;
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                }
            }
        } : new ContinuationImpl(continuation1, coroutineContext0) {
            private int label;

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                switch(this.label) {
                    case 0: {
                        this.label = 1;
                        ResultKt.throwOnFailure(object0);
                        Intrinsics.checkNotNull(function10, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                        return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function10, 1)).invoke(this);
                    }
                    case 1: {
                        this.label = 2;
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                }
            }
        };
    }

    public static final Continuation createCoroutineUnintercepted(Function2 function20, Object object0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function20, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        Continuation continuation1 = DebugProbesKt.probeCoroutineCreated(continuation0);
        if(function20 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl)function20).create(object0, continuation1);
        }
        CoroutineContext coroutineContext0 = continuation1.getContext();
        return coroutineContext0 == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(continuation1) {
            private int label;

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                switch(this.label) {
                    case 0: {
                        this.label = 1;
                        ResultKt.throwOnFailure(object0);
                        Intrinsics.checkNotNull(function20, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                        return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, this);
                    }
                    case 1: {
                        this.label = 2;
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                }
            }
        } : new ContinuationImpl(continuation1, coroutineContext0) {
            private int label;

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                switch(this.label) {
                    case 0: {
                        this.label = 1;
                        ResultKt.throwOnFailure(object0);
                        Intrinsics.checkNotNull(function20, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                        return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, this);
                    }
                    case 1: {
                        this.label = 2;
                        ResultKt.throwOnFailure(object0);
                        return object0;
                    }
                    default: {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                }
            }
        };
    }

    private static final Continuation createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(Continuation continuation0) {
        CoroutineContext coroutineContext0 = continuation0.getContext();
        return coroutineContext0 == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(continuation0) {
            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                ResultKt.throwOnFailure(object0);
                return object0;
            }
        } : new ContinuationImpl(continuation0, coroutineContext0) {
            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object object0) {
                ResultKt.throwOnFailure(object0);
                return object0;
            }
        };
    }

    public static final Continuation intercepted(Continuation continuation0) {
        Intrinsics.checkNotNullParameter(continuation0, "<this>");
        ContinuationImpl continuationImpl0 = continuation0 instanceof ContinuationImpl ? ((ContinuationImpl)continuation0) : null;
        if(continuationImpl0 != null) {
            Continuation continuation1 = continuationImpl0.intercepted();
            return continuation1 == null ? continuation0 : continuation1;
        }
        return continuation0;
    }

    private static final Object startCoroutineUninterceptedOrReturn(Function1 function10, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function10, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        return function10 instanceof BaseContinuationImpl ? ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function10, 1)).invoke(continuation0) : IntrinsicsKt.wrapWithContinuationImpl(function10, continuation0);
    }

    private static final Object startCoroutineUninterceptedOrReturn(Function2 function20, Object object0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function20, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        return function20 instanceof BaseContinuationImpl ? ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, continuation0) : IntrinsicsKt.wrapWithContinuationImpl(function20, object0, continuation0);
    }

    private static final Object startCoroutineUninterceptedOrReturn(Function3 function30, Object object0, Object object1, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function30, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        return function30 instanceof BaseContinuationImpl ? ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function30, 3)).invoke(object0, object1, continuation0) : IntrinsicsKt.wrapWithContinuationImpl(function30, object0, object1, continuation0);
    }

    public static final Object wrapWithContinuationImpl(Function1 function10, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function10, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        Continuation continuation1 = IntrinsicsKt__IntrinsicsJvmKt.createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(continuation0));
        return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function10, 1)).invoke(continuation1);
    }

    public static final Object wrapWithContinuationImpl(Function2 function20, Object object0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function20, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        Continuation continuation1 = IntrinsicsKt__IntrinsicsJvmKt.createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(continuation0));
        return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, continuation1);
    }

    public static final Object wrapWithContinuationImpl(Function3 function30, Object object0, Object object1, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function30, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        Continuation continuation1 = IntrinsicsKt__IntrinsicsJvmKt.createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(continuation0));
        return ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function30, 3)).invoke(object0, object1, continuation1);
    }
}

