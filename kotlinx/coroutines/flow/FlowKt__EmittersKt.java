package kotlinx.coroutines.flow;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlinx.coroutines.flow.internal.SafeCollector;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001A\u0010\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0000\u001As\u0010\u0003\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00022D\u0010\u0005\u001A@\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00A2\u0006\u0002\b\r2\b\u0010\n\u001A\u0004\u0018\u00010\u0007H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000E\u0010\u000F\u001Aj\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0011\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00112D\u0010\u0005\u001A@\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00A2\u0006\u0002\b\r\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0012\u001AS\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0011\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00112-\u0010\u0005\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0014\u00A2\u0006\u0002\b\r\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001AS\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0011\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00112-\u0010\u0005\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0014\u00A2\u0006\u0002\b\r\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001As\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00180\u0011\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00040\u00112D\b\u0005\u0010\u0017\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u0002\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00A2\u0006\u0002\b\rH\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0012\u001As\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00180\u0011\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00040\u00112D\b\u0005\u0010\u0017\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u0002\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00A2\u0006\u0002\b\rH\u0081\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u001B"}, d2 = {"ensureActive", "", "Lkotlinx/coroutines/flow/FlowCollector;", "invokeSafely", "T", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "invokeSafely$FlowKt__EmittersKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/jvm/functions/Function3;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCompletion", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "onEmpty", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "onStart", "transform", "R", "value", "unsafeTransform", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__EmittersKt {
    public static final void ensureActive(FlowCollector flowCollector0) {
        if(flowCollector0 instanceof ThrowingCollector) {
            throw ((ThrowingCollector)flowCollector0).e;
        }
    }

    private static final Object invokeSafely$FlowKt__EmittersKt(FlowCollector flowCollector0, Function3 function30, Throwable throwable0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1 flowKt__EmittersKt$invokeSafely$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1) {
            flowKt__EmittersKt$invokeSafely$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1)continuation0;
            if((flowKt__EmittersKt$invokeSafely$10.label & 0x80000000) == 0) {
                flowKt__EmittersKt$invokeSafely$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(null, null, null, this);
                    }
                };
            }
            else {
                flowKt__EmittersKt$invokeSafely$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__EmittersKt$invokeSafely$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(null, null, null, this);
                }
            };
        }
        Object object0 = flowKt__EmittersKt$invokeSafely$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__EmittersKt$invokeSafely$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    flowKt__EmittersKt$invokeSafely$10.L$0 = throwable0;
                    flowKt__EmittersKt$invokeSafely$10.label = 1;
                    if(function30.invoke(flowCollector0, throwable0, flowKt__EmittersKt$invokeSafely$10) == object1) {
                        return object1;
                    }
                }
                catch(Throwable throwable1) {
                    goto label_22;
                }
                return Unit.INSTANCE;
            }
            case 1: {
                throwable0 = (Throwable)flowKt__EmittersKt$invokeSafely$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    return Unit.INSTANCE;
                }
                catch(Throwable throwable1) {
                label_22:
                    if(throwable0 != null && throwable0 != throwable1) {
                        ExceptionsKt.addSuppressed(throwable1, throwable0);
                    }
                    throw throwable1;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public static final Flow onCompletion(Flow flow0, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                SafeCollector safeCollector1;
                Throwable throwable2;
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onCompletion..inlined.unsafeFlow.1 flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$10;
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onCompletion..inlined.unsafeFlow.1.1 flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.onCompletion..inlined.unsafeFlow.1.1) {
                    flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onCompletion..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$0 = this;
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$1 = flowCollector0;
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.label = 1;
                            if(flow0.collect(flowCollector0, flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10) == object1) {
                                return object1;
                            }
                        }
                        catch(Throwable throwable0) {
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$10 = this;
                            goto label_28;
                        }
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$10 = this;
                        goto label_34;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onCompletion..inlined.unsafeFlow.1)flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            goto label_34;
                        }
                        catch(Throwable throwable0) {
                        }
                    label_28:
                        FlowCollector flowCollector1 = new ThrowingCollector(throwable0);
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$0 = throwable0;
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$1 = null;
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.label = 2;
                        if(FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(flowCollector1, flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$10.$action$inlined, throwable0, flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10) != object1) {
                            throw throwable0;
                        }
                        return object1;
                    label_34:
                        SafeCollector safeCollector0 = new SafeCollector(flowCollector0, flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.getContext());
                        try {
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$0 = safeCollector0;
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$1 = null;
                            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.label = 3;
                            if(flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$10.$action$inlined.invoke(safeCollector0, null, flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10) != object1) {
                                safeCollector1 = safeCollector0;
                                break;
                            }
                            return object1;
                        }
                        catch(Throwable throwable1) {
                            throwable2 = throwable1;
                            safeCollector1 = safeCollector0;
                            safeCollector1.releaseIntercepted();
                            throw throwable2;
                        }
                        safeCollector1 = safeCollector0;
                        break;
                    }
                    case 2: {
                        throwable0 = (Throwable)flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        throw throwable0;
                    }
                    case 3: {
                        safeCollector1 = (SafeCollector)flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$10.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            break;
                        }
                        catch(Throwable throwable2) {
                        }
                        safeCollector1.releaseIntercepted();
                        throw throwable2;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                safeCollector1.releaseIntercepted();
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow onEmpty(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                SafeCollector safeCollector0;
                BooleanRef ref$BooleanRef1;
                FlowCollector flowCollector2;
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty..inlined.unsafeFlow.1 flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$10;
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty..inlined.unsafeFlow.1.1 flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty..inlined.unsafeFlow.1.1) {
                    flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        BooleanRef ref$BooleanRef0 = new BooleanRef();
                        ref$BooleanRef0.element = true;
                        FlowCollector flowCollector1 = new kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty.1.1(ref$BooleanRef0, flowCollector0);
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$0 = this;
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$1 = flowCollector0;
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$2 = ref$BooleanRef0;
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.label = 1;
                        if(flow0.collect(flowCollector1, flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10) == object1) {
                            return object1;
                        }
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$10 = this;
                        flowCollector2 = flowCollector0;
                        ref$BooleanRef1 = ref$BooleanRef0;
                        goto label_29;
                    }
                    case 1: {
                        ref$BooleanRef1 = (BooleanRef)flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$2;
                        flowCollector2 = (FlowCollector)flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty..inlined.unsafeFlow.1)flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$0;
                        ResultKt.throwOnFailure(object0);
                    label_29:
                        if(ref$BooleanRef1.element) {
                            safeCollector0 = new SafeCollector(flowCollector2, flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.getContext());
                            try {
                                flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$0 = safeCollector0;
                                flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$1 = null;
                                flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$2 = null;
                                flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.label = 2;
                                if(flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$10.$action$inlined.invoke(safeCollector0, flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10) == object1) {
                                    return object1;
                                }
                            }
                            catch(Throwable throwable0) {
                                safeCollector0.releaseIntercepted();
                                throw throwable0;
                            }
                            safeCollector0.releaseIntercepted();
                            return Unit.INSTANCE;
                        }
                        break;
                    }
                    case 2: {
                        safeCollector0 = (SafeCollector)flowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$10.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                        }
                        catch(Throwable throwable0) {
                            safeCollector0.releaseIntercepted();
                            throw throwable0;
                        }
                        safeCollector0.releaseIntercepted();
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                return Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "it", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty.1.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty.1.1(BooleanRef ref$BooleanRef0, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty.1.1.emit.1 flowKt__EmittersKt$onEmpty$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty.1.1.emit.1) {
                    flowKt__EmittersKt$onEmpty$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty.1.1.emit.1)continuation0;
                    if((flowKt__EmittersKt$onEmpty$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__EmittersKt$onEmpty$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__EmittersKt$onEmpty$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__EmittersKt$onEmpty$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__EmittersKt$onEmpty$1$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__EmittersKt$onEmpty$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        this.$isEmpty.element = false;
                        flowKt__EmittersKt$onEmpty$1$1$emit$10.label = 1;
                        return this.$this_unsafeFlow.emit(object0, flowKt__EmittersKt$onEmpty$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object1);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }

    }

    public static final Flow onStart(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onStart..inlined.unsafeFlow.1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$10;
                SafeCollector safeCollector1;
                Throwable throwable1;
                kotlinx.coroutines.flow.FlowKt__EmittersKt.onStart..inlined.unsafeFlow.1.1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.onStart..inlined.unsafeFlow.1.1) {
                    flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onStart..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SafeCollector safeCollector0 = new SafeCollector(flowCollector0, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.getContext());
                        try {
                            flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$0 = this;
                            flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$1 = flowCollector0;
                            flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$2 = safeCollector0;
                            flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.label = 1;
                            if(function20.invoke(safeCollector0, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10) == object1) {
                                return object1;
                            }
                        }
                        catch(Throwable throwable0) {
                            throwable1 = throwable0;
                            safeCollector1 = safeCollector0;
                            safeCollector1.releaseIntercepted();
                            throw throwable1;
                        }
                        flowKt__EmittersKt$onStart$$inlined$unsafeFlow$10 = this;
                        flowCollector1 = flowCollector0;
                        safeCollector1 = safeCollector0;
                        break;
                    }
                    case 1: {
                        safeCollector1 = (SafeCollector)flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$2;
                        flowCollector1 = (FlowCollector)flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__EmittersKt$onStart$$inlined$unsafeFlow$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.onStart..inlined.unsafeFlow.1)flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            break;
                        }
                        catch(Throwable throwable1) {
                        }
                        safeCollector1.releaseIntercepted();
                        throw throwable1;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                safeCollector1.releaseIntercepted();
                flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$0 = null;
                flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$1 = null;
                flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.L$2 = null;
                flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10.label = 2;
                return flowKt__EmittersKt$onStart$$inlined$unsafeFlow$10.$this_onStart$inlined.collect(flowCollector1, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$10) == object1 ? object1 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow transform(Flow flow0, Function3 function30) {
        return FlowKt.flow(new Function2(flow0, function30, null) {
            final Flow $this_transform;
            final Function3 $transform;
            private Object L$0;
            int label;

            {
                this.$this_transform = flow0;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1 flowKt__EmittersKt$transform$10 = new kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1(this.$this_transform, this.$transform, continuation0);
                flowKt__EmittersKt$transform$10.L$0 = object0;
                return flowKt__EmittersKt$transform$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1 flowKt__EmittersKt$transform$1$10 = new FlowCollector() {
                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public final Object emit(Object object0, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1.emit.1 flowKt__EmittersKt$transform$1$1$emit$10;
                                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1.emit.1) {
                                    flowKt__EmittersKt$transform$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1.emit.1)continuation0;
                                    if((flowKt__EmittersKt$transform$1$1$emit$10.label & 0x80000000) == 0) {
                                        flowKt__EmittersKt$transform$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                        flowKt__EmittersKt$transform$1$1$emit$10.label ^= 0x80000000;
                                    }
                                }
                                else {
                                    flowKt__EmittersKt$transform$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                Object object1 = flowKt__EmittersKt$transform$1$1$emit$10.result;
                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(flowKt__EmittersKt$transform$1$1$emit$10.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object1);
                                        flowKt__EmittersKt$transform$1$1$emit$10.label = 1;
                                        return ((FlowCollector)this.L$0).invoke(this.$$this$flow, object0, flowKt__EmittersKt$transform$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object1);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }

                            public final Object emit$$forInline(Object object0, Continuation continuation0) {
                                new kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1.emit.1(this, continuation0);
                                ((FlowCollector)this.L$0).invoke(this.$$this$flow, object0, continuation0);
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return this.$this_transform.collect(flowKt__EmittersKt$transform$1$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1 flowKt__EmittersKt$transform$1$10 = new kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1.1(this.$transform, ((FlowCollector)this.L$0));
                this.$this_transform.collect(flowKt__EmittersKt$transform$1$10, this);
                return Unit.INSTANCE;
            }
        });
    }

    public static final Flow unsafeTransform(Flow flow0, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1 flowKt__EmittersKt$unsafeTransform$1$10 = new kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1(function30, flowCollector0);
                Object object0 = flow0.collect(flowKt__EmittersKt$unsafeTransform$1$10, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform..inlined.unsafeFlow.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform..inlined.unsafeFlow.1.1(kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform..inlined.unsafeFlow.1 flowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform..inlined.unsafeFlow.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform..inlined.unsafeFlow.1.1(this, continuation0);
                kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1 flowKt__EmittersKt$unsafeTransform$1$10 = new kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1(function30, flowCollector0);
                flow0.collect(flowKt__EmittersKt$unsafeTransform$1$10, continuation0);
                return Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1 implements FlowCollector {
            public kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1(Function3 function30, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1.emit.1 flowKt__EmittersKt$unsafeTransform$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1.emit.1) {
                    flowKt__EmittersKt$unsafeTransform$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1.emit.1)continuation0;
                    if((flowKt__EmittersKt$unsafeTransform$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__EmittersKt$unsafeTransform$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__EmittersKt$unsafeTransform$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__EmittersKt$unsafeTransform$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__EmittersKt$unsafeTransform$1$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__EmittersKt$unsafeTransform$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        flowKt__EmittersKt$unsafeTransform$1$1$emit$10.label = 1;
                        return this.$transform.invoke(this.$this_unsafeFlow, object0, flowKt__EmittersKt$unsafeTransform$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object1);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }

            public final Object emit$$forInline(Object object0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform.1.1.emit.1(this, continuation0);
                this.$transform.invoke(this.$this_unsafeFlow, object0, continuation0);
                return Unit.INSTANCE;
            }
        }

    }
}

