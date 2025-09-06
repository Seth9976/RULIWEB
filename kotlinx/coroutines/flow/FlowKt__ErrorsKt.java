package kotlinx.coroutines.flow;

import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001Ah\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012B\u0010\u0003\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000B0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004\u00A2\u0006\u0002\b\r\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000E\u001A1\u0010\u000F\u001A\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0080@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0011\u001A\u0019\u0010\u0012\u001A\u00020\u0013*\u00020\u00062\u0006\u0010\u0014\u001A\u00020\u0015H\u0002\u00A2\u0006\u0002\b\u0016\u001A\u001B\u0010\u0017\u001A\u00020\u0013*\u00020\u00062\b\u0010\u0018\u001A\u0004\u0018\u00010\u0006H\u0002\u00A2\u0006\u0002\b\u0019\u001Ac\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001B\u001A\u00020\u001C23\b\u0002\u0010\u001D\u001A-\b\u0001\u0012\u0013\u0012\u00110\u0006\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001E\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001F\u001A}\u0010 \u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012W\u0010\u001D\u001AS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u001C\u00A2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0!\u00A2\u0006\u0002\b\r\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010#\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006$"}, d2 = {"catch", "Lkotlinx/coroutines/flow/Flow;", "T", "action", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "catchImpl", "collector", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCancellationCause", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "isCancellationCause$FlowKt__ErrorsKt", "isSameExceptionAs", "other", "isSameExceptionAs$FlowKt__ErrorsKt", "retry", "retries", "", "predicate", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__ErrorsKt {
    public static final Flow catch(Flow flow0, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ErrorsKt.catch..inlined.unsafeFlow.1 flowKt__ErrorsKt$catch$$inlined$unsafeFlow$10;
                kotlinx.coroutines.flow.FlowKt__ErrorsKt.catch..inlined.unsafeFlow.1.1 flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.catch..inlined.unsafeFlow.1.1) {
                    flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.catch..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            Object L$0;
                            Object L$1;
                            int label;
                            Object result;

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                this.result = object0;
                                this.label |= 0x80000000;
                                return continuation0.collect(null, this);
                            }
                        };
                    }
                    else {
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        Object L$0;
                        Object L$1;
                        int label;
                        Object result;

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            this.result = object0;
                            this.label |= 0x80000000;
                            return continuation0.collect(null, this);
                        }
                    };
                }
                Object object0 = flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.L$0 = this;
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.L$1 = flowCollector0;
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.label = 1;
                        object0 = FlowKt.catchImpl(flow0, flowCollector0, flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10);
                        if(object0 == object1) {
                            return object1;
                        }
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$10 = this;
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__ErrorsKt$catch$$inlined$unsafeFlow$10 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.catch..inlined.unsafeFlow.1)flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                if(((Throwable)object0) != null) {
                    flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.L$0 = null;
                    flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.L$1 = null;
                    flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10.label = 2;
                    if(flowKt__ErrorsKt$catch$$inlined$unsafeFlow$10.$action$inlined.invoke(flowCollector0, ((Throwable)object0), flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1$10) == object1) {
                        return object1;
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Object catchImpl(Flow flow0, FlowCollector flowCollector0, Continuation continuation0) {
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1 flowKt__ErrorsKt$catchImpl$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1) {
            flowKt__ErrorsKt$catchImpl$10 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1)continuation0;
            if((flowKt__ErrorsKt$catchImpl$10.label & 0x80000000) == 0) {
                flowKt__ErrorsKt$catchImpl$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.catchImpl(null, null, this);
                    }
                };
            }
            else {
                flowKt__ErrorsKt$catchImpl$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ErrorsKt$catchImpl$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.catchImpl(null, null, this);
                }
            };
        }
        Object object0 = flowKt__ErrorsKt$catchImpl$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ErrorsKt$catchImpl$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                try {
                    FlowCollector flowCollector1 = new FlowCollector() {
                        @Override  // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object object0, Continuation continuation0) {
                            kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.2 flowKt__ErrorsKt$catchImpl$20;
                            kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.2.emit.1 flowKt__ErrorsKt$catchImpl$2$emit$10;
                            if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.2.emit.1) {
                                flowKt__ErrorsKt$catchImpl$2$emit$10 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.2.emit.1)continuation0;
                                if((flowKt__ErrorsKt$catchImpl$2$emit$10.label & 0x80000000) == 0) {
                                    flowKt__ErrorsKt$catchImpl$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                        Object L$0;
                                        int label;
                                        Object result;

                                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object object0) {
                                            this.result = object0;
                                            this.label |= 0x80000000;
                                            return continuation0.emit(null, this);
                                        }
                                    };
                                }
                                else {
                                    flowKt__ErrorsKt$catchImpl$2$emit$10.label ^= 0x80000000;
                                }
                            }
                            else {
                                flowKt__ErrorsKt$catchImpl$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                    Object L$0;
                                    int label;
                                    Object result;

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object object0) {
                                        this.result = object0;
                                        this.label |= 0x80000000;
                                        return continuation0.emit(null, this);
                                    }
                                };
                            }
                            Object object1 = flowKt__ErrorsKt$catchImpl$2$emit$10.result;
                            Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(flowKt__ErrorsKt$catchImpl$2$emit$10.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object1);
                                    try {
                                        flowKt__ErrorsKt$catchImpl$2$emit$10.L$0 = this;
                                        flowKt__ErrorsKt$catchImpl$2$emit$10.label = 1;
                                        return flowCollector0.emit(object0, flowKt__ErrorsKt$catchImpl$2$emit$10) == object2 ? object2 : Unit.INSTANCE;
                                    }
                                    catch(Throwable throwable0) {
                                        flowKt__ErrorsKt$catchImpl$20 = this;
                                        flowKt__ErrorsKt$catchImpl$20.$fromDownstream.element = throwable0;
                                        throw throwable0;
                                    }
                                }
                                case 1: {
                                    flowKt__ErrorsKt$catchImpl$20 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.2)flowKt__ErrorsKt$catchImpl$2$emit$10.L$0;
                                    try {
                                        ResultKt.throwOnFailure(object1);
                                        return Unit.INSTANCE;
                                    }
                                    catch(Throwable throwable0) {
                                    }
                                    flowKt__ErrorsKt$catchImpl$20.$fromDownstream.element = throwable0;
                                    throw throwable0;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                        }
                    };
                    flowKt__ErrorsKt$catchImpl$10.L$0 = ref$ObjectRef0;
                    flowKt__ErrorsKt$catchImpl$10.label = 1;
                    return flow0.collect(flowCollector1, flowKt__ErrorsKt$catchImpl$10) == object1 ? object1 : null;
                }
                catch(Throwable object2) {
                    ref$ObjectRef1 = ref$ObjectRef0;
                    goto label_28;
                }
                return object1;
            }
            case 1: {
                ref$ObjectRef1 = (ObjectRef)flowKt__ErrorsKt$catchImpl$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    return null;
                }
                catch(Throwable object2) {
                }
            label_28:
                Throwable throwable0 = (Throwable)ref$ObjectRef1.element;
                if(FlowKt__ErrorsKt.isSameExceptionAs$FlowKt__ErrorsKt(((Throwable)object2), throwable0) || FlowKt__ErrorsKt.isCancellationCause$FlowKt__ErrorsKt(((Throwable)object2), flowKt__ErrorsKt$catchImpl$10.getContext())) {
                    throw object2;
                }
                if(throwable0 == null) {
                    return object2;
                }
                if(object2 instanceof CancellationException) {
                    ExceptionsKt.addSuppressed(throwable0, ((Throwable)object2));
                    throw throwable0;
                }
                ExceptionsKt.addSuppressed(((Throwable)object2), throwable0);
                throw object2;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable throwable0, CoroutineContext coroutineContext0) {
        Job job0 = (Job)coroutineContext0.get(Job.Key);
        return job0 == null || !job0.isCancelled() ? false : FlowKt__ErrorsKt.isSameExceptionAs$FlowKt__ErrorsKt(throwable0, job0.getCancellationException());
    }

    // 去混淆评级： 中等(70)
    private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable throwable0, Throwable throwable1) {
        return throwable1 != null && Intrinsics.areEqual(throwable1, throwable0);
    }

    public static final Flow retry(Flow flow0, long v, Function2 function20) {
        if(v <= 0L) {
            throw new IllegalArgumentException(("Expected positive amount of retries, but had " + v).toString());
        }
        return FlowKt.retryWhen(flow0, new Function4(v, function20, null) {
            final Function2 $predicate;
            final long $retries;
            long J$0;
            Object L$0;
            int label;

            {
                this.$retries = v;
                this.$predicate = function20;
                super(4, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function4
            public Object invoke(Object object0, Object object1, Object object2, Object object3) {
                return this.invoke(((FlowCollector)object0), ((Throwable)object1), ((Number)object2).longValue(), ((Continuation)object3));
            }

            public final Object invoke(FlowCollector flowCollector0, Throwable throwable0, long v, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.3 flowKt__ErrorsKt$retry$30 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.3(this.$retries, this.$predicate, continuation0);
                flowKt__ErrorsKt$retry$30.L$0 = throwable0;
                flowKt__ErrorsKt$retry$30.J$0 = v;
                return flowKt__ErrorsKt$retry$30.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                boolean z = true;
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Throwable throwable0 = (Throwable)this.L$0;
                        if(this.J$0 >= this.$retries) {
                            return Boxing.boxBoolean(false);
                        }
                        this.label = 1;
                        object0 = this.$predicate.invoke(throwable0, this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                if(!((Boolean)object0).booleanValue()) {
                    z = false;
                }
                return Boxing.boxBoolean(z);
            }
        });
    }

    public static Flow retry$default(Flow flow0, long v, Function2 function20, int v1, Object object0) {
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
                    return new kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.1(continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((Throwable)object0), ((Continuation)object1));
                }

                public final Object invoke(Throwable throwable0, Continuation continuation0) {
                    return ((kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.1)this.create(throwable0, continuation0)).invokeSuspend(Unit.INSTANCE);
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
        return FlowKt.retry(flow0, v, function20);
    }

    public static final Flow retryWhen(Flow flow0, Function4 function40) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Throwable throwable0;
                kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1 flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11;
                FlowCollector flowCollector1;
                int v1;
                kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1 flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$10;
                long v;
                kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1.1 flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1.1) {
                    flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            int I$0;
                            long J$0;
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            Object result;

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                this.result = object0;
                                this.label |= 0x80000000;
                                return continuation0.collect(null, this);
                            }
                        };
                    }
                    else {
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        int I$0;
                        long J$0;
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        int label;
                        Object result;

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            this.result = object0;
                            this.label |= 0x80000000;
                            return continuation0.collect(null, this);
                        }
                    };
                }
                Object object0 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        v = 0L;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$10 = this;
                        goto label_34;
                    }
                    case 1: {
                        v1 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.I$0;
                        v = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.J$0;
                        flowCollector1 = (FlowCollector)flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1)flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_46;
                    }
                    case 2: {
                        v = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.J$0;
                        throwable0 = (Throwable)flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$2;
                        flowCollector1 = (FlowCollector)flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1)flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            label_27:
                while(((Boolean)object0).booleanValue()) {
                    ++v;
                    v1 = 1;
                    while(true) {
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$10 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11;
                        if(v1 == 0) {
                            return Unit.INSTANCE;
                        }
                        flowCollector0 = flowCollector1;
                    label_34:
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$0 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$10;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$1 = flowCollector0;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$2 = null;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.J$0 = v;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.I$0 = 0;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.label = 1;
                        Object object2 = FlowKt.catchImpl(flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$10.$this_retryWhen$inlined, flowCollector0, flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10);
                        if(object2 == object1) {
                            break;
                        }
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$10;
                        object0 = object2;
                        flowCollector1 = flowCollector0;
                        v1 = 0;
                    label_46:
                        if(((Throwable)object0) == null) {
                            continue;
                        }
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$0 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$1 = flowCollector1;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.L$2 = (Throwable)object0;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.J$0 = v;
                        flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10.label = 2;
                        Object object3 = flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$11.$predicate$inlined.invoke(flowCollector1, ((Throwable)object0), Boxing.boxLong(v), flowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$10);
                        if(object3 == object1) {
                            break;
                        }
                        Throwable throwable1 = (Throwable)object0;
                        object0 = object3;
                        throwable0 = throwable1;
                        continue label_27;
                    }
                    return object1;
                }
                throw throwable0;
            }
        };
    }
}

