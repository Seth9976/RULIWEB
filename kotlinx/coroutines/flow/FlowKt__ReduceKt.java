package kotlinx.coroutines.flow;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u001A!\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0003\u001AE\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001A#\u0010\n\u001A\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0003\u001AG\u0010\n\u001A\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001Ay\u0010\u000B\u001A\u0002H\f\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\f*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\r\u001A\u0002H\f2H\b\u0004\u0010\u000E\u001AB\b\u0001\u0012\u0013\u0012\u0011H\f\u00A2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u0011H\u0001\u00A2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000FH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014\u001A!\u0010\u0015\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0003\u001A#\u0010\u0016\u001A\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0003\u001As\u0010\u0017\u001A\u0002H\u0018\"\u0004\b\u0000\u0010\u0018\"\b\b\u0001\u0010\u0001*\u0002H\u0018*\b\u0012\u0004\u0012\u0002H\u00010\u00022F\u0010\u000E\u001AB\b\u0001\u0012\u0013\u0012\u0011H\u0018\u00A2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0001\u00A2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000FH\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001A\u001A!\u0010\u001B\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0003\u001A#\u0010\u001C\u001A\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u001D"}, d2 = {"first", "T", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstOrNull", "fold", "R", "initial", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "acc", "value", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastOrNull", "reduce", "S", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "single", "singleOrNull", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__ReduceKt {
    public static final Object first(Flow flow0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.1 flowKt__ReduceKt$first$$inlined$collectWhile$11;
        AbortFlowException abortFlowException1;
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1 flowKt__ReduceKt$first$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1) {
            flowKt__ReduceKt$first$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1)continuation0;
            if((flowKt__ReduceKt$first$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$first$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.first(null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$first$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$first$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.first(null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$first$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$first$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.1 flowKt__ReduceKt$first$$inlined$collectWhile$10 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object object0, Continuation continuation0) {
                        ref$ObjectRef0.element = object0;
                        throw new AbortFlowException(this);
                    }
                };
                try {
                    flowKt__ReduceKt$first$10.L$0 = ref$ObjectRef0;
                    flowKt__ReduceKt$first$10.L$1 = flowKt__ReduceKt$first$$inlined$collectWhile$10;
                    flowKt__ReduceKt$first$10.label = 1;
                    if(flow0.collect(flowKt__ReduceKt$first$$inlined$collectWhile$10, flowKt__ReduceKt$first$10) == object1) {
                        return object1;
                    }
                }
                catch(AbortFlowException abortFlowException0) {
                    ref$ObjectRef1 = ref$ObjectRef0;
                    abortFlowException1 = abortFlowException0;
                    flowKt__ReduceKt$first$$inlined$collectWhile$11 = flowKt__ReduceKt$first$$inlined$collectWhile$10;
                    FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$first$$inlined$collectWhile$11);
                    break;
                }
                ref$ObjectRef1 = ref$ObjectRef0;
                break;
            }
            case 1: {
                flowKt__ReduceKt$first$$inlined$collectWhile$11 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.1)flowKt__ReduceKt$first$10.L$1;
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$first$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(AbortFlowException abortFlowException1) {
                }
                FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$first$$inlined$collectWhile$11);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(ref$ObjectRef1.element == NullSurrogateKt.NULL) {
            throw new NoSuchElementException("Expected at least one element");
        }
        return ref$ObjectRef1.element;
    }

    public static final Object first(Flow flow0, Function2 function20, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2 flowKt__ReduceKt$first$$inlined$collectWhile$21;
        AbortFlowException abortFlowException1;
        ObjectRef ref$ObjectRef1;
        Function2 function21;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.first.3 flowKt__ReduceKt$first$30;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.first.3) {
            flowKt__ReduceKt$first$30 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.first.3)continuation0;
            if((flowKt__ReduceKt$first$30.label & 0x80000000) == 0) {
                flowKt__ReduceKt$first$30 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.first(null, null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$first$30.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$first$30 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.first(null, null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$first$30.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$first$30.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2 flowKt__ReduceKt$first$$inlined$collectWhile$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2 flowKt__ReduceKt$first$$inlined$collectWhile$20;
                        kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2.1 flowKt__ReduceKt$first$$inlined$collectWhile$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2.1) {
                            flowKt__ReduceKt$first$$inlined$collectWhile$2$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2.1)continuation0;
                            if((flowKt__ReduceKt$first$$inlined$collectWhile$2$10.label & 0x80000000) == 0) {
                                flowKt__ReduceKt$first$$inlined$collectWhile$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                    Object L$0;
                                    Object L$1;
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
                                flowKt__ReduceKt$first$$inlined$collectWhile$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__ReduceKt$first$$inlined$collectWhile$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                Object L$0;
                                Object L$1;
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
                        Object object1 = flowKt__ReduceKt$first$$inlined$collectWhile$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__ReduceKt$first$$inlined$collectWhile$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                flowKt__ReduceKt$first$$inlined$collectWhile$2$10.L$0 = this;
                                flowKt__ReduceKt$first$$inlined$collectWhile$2$10.L$1 = object0;
                                flowKt__ReduceKt$first$$inlined$collectWhile$2$10.label = 1;
                                object1 = function20.invoke(object0, flowKt__ReduceKt$first$$inlined$collectWhile$2$10);
                                if(object1 == object2) {
                                    return object2;
                                }
                                flowKt__ReduceKt$first$$inlined$collectWhile$20 = this;
                                break;
                            }
                            case 1: {
                                object0 = flowKt__ReduceKt$first$$inlined$collectWhile$2$10.L$1;
                                flowKt__ReduceKt$first$$inlined$collectWhile$20 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2)flowKt__ReduceKt$first$$inlined$collectWhile$2$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        if(!((Boolean)object1).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        flowKt__ReduceKt$first$$inlined$collectWhile$20.$result$inlined.element = object0;
                        throw new AbortFlowException(flowKt__ReduceKt$first$$inlined$collectWhile$20);
                    }
                };
                try {
                    flowKt__ReduceKt$first$30.L$0 = function20;
                    flowKt__ReduceKt$first$30.L$1 = ref$ObjectRef0;
                    flowKt__ReduceKt$first$30.L$2 = flowKt__ReduceKt$first$$inlined$collectWhile$20;
                    flowKt__ReduceKt$first$30.label = 1;
                    if(flow0.collect(flowKt__ReduceKt$first$$inlined$collectWhile$20, flowKt__ReduceKt$first$30) == object1) {
                        return object1;
                    }
                }
                catch(AbortFlowException abortFlowException0) {
                    function21 = function20;
                    ref$ObjectRef1 = ref$ObjectRef0;
                    abortFlowException1 = abortFlowException0;
                    flowKt__ReduceKt$first$$inlined$collectWhile$21 = flowKt__ReduceKt$first$$inlined$collectWhile$20;
                    FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$first$$inlined$collectWhile$21);
                    break;
                }
                function21 = function20;
                ref$ObjectRef1 = ref$ObjectRef0;
                break;
            }
            case 1: {
                flowKt__ReduceKt$first$$inlined$collectWhile$21 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2)flowKt__ReduceKt$first$30.L$2;
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$first$30.L$1;
                function21 = (Function2)flowKt__ReduceKt$first$30.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                catch(AbortFlowException abortFlowException1) {
                }
                FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$first$$inlined$collectWhile$21);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(ref$ObjectRef1.element == NullSurrogateKt.NULL) {
            throw new NoSuchElementException("Expected at least one element matching the predicate " + function21);
        }
        return ref$ObjectRef1.element;
    }

    public static final Object firstOrNull(Flow flow0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.1 flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$11;
        AbortFlowException abortFlowException1;
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1 flowKt__ReduceKt$firstOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1) {
            flowKt__ReduceKt$firstOrNull$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1)continuation0;
            if((flowKt__ReduceKt$firstOrNull$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$firstOrNull$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.firstOrNull(null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$firstOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$firstOrNull$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.firstOrNull(null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$firstOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$firstOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.1 flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$10 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object object0, Continuation continuation0) {
                        ref$ObjectRef0.element = object0;
                        throw new AbortFlowException(this);
                    }
                };
                try {
                    flowKt__ReduceKt$firstOrNull$10.L$0 = ref$ObjectRef0;
                    flowKt__ReduceKt$firstOrNull$10.L$1 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$10;
                    flowKt__ReduceKt$firstOrNull$10.label = 1;
                    return flow0.collect(flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$10, flowKt__ReduceKt$firstOrNull$10) == object1 ? object1 : ref$ObjectRef0.element;
                }
                catch(AbortFlowException abortFlowException0) {
                    ref$ObjectRef1 = ref$ObjectRef0;
                    abortFlowException1 = abortFlowException0;
                    flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$11 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$10;
                    FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$11);
                    return ref$ObjectRef1.element;
                }
            }
            case 1: {
                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$11 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.1)flowKt__ReduceKt$firstOrNull$10.L$1;
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$firstOrNull$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    return ref$ObjectRef1.element;
                }
                catch(AbortFlowException abortFlowException1) {
                }
                FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$11);
                return ref$ObjectRef1.element;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public static final Object firstOrNull(Flow flow0, Function2 function20, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2 flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$21;
        AbortFlowException abortFlowException1;
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3 flowKt__ReduceKt$firstOrNull$30;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3) {
            flowKt__ReduceKt$firstOrNull$30 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3)continuation0;
            if((flowKt__ReduceKt$firstOrNull$30.label & 0x80000000) == 0) {
                flowKt__ReduceKt$firstOrNull$30 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.firstOrNull(null, null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$firstOrNull$30.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$firstOrNull$30 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.firstOrNull(null, null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$firstOrNull$30.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$firstOrNull$30.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2 flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2 flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20;
                        kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2.1 flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2.1) {
                            flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2.1)continuation0;
                            if((flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.label & 0x80000000) == 0) {
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                    Object L$0;
                                    Object L$1;
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
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                Object L$0;
                                Object L$1;
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
                        Object object1 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.L$0 = this;
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.L$1 = object0;
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.label = 1;
                                object1 = function20.invoke(object0, flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10);
                                if(object1 == object2) {
                                    return object2;
                                }
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20 = this;
                                break;
                            }
                            case 1: {
                                object0 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.L$1;
                                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2)flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        if(!((Boolean)object1).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20.$result$inlined.element = object0;
                        throw new AbortFlowException(flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20);
                    }
                };
                try {
                    flowKt__ReduceKt$firstOrNull$30.L$0 = ref$ObjectRef0;
                    flowKt__ReduceKt$firstOrNull$30.L$1 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20;
                    flowKt__ReduceKt$firstOrNull$30.label = 1;
                    return flow0.collect(flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20, flowKt__ReduceKt$firstOrNull$30) == object1 ? object1 : ref$ObjectRef0.element;
                }
                catch(AbortFlowException abortFlowException0) {
                    ref$ObjectRef1 = ref$ObjectRef0;
                    abortFlowException1 = abortFlowException0;
                    flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$21 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$20;
                    FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$21);
                    return ref$ObjectRef1.element;
                }
            }
            case 1: {
                flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$21 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2)flowKt__ReduceKt$firstOrNull$30.L$1;
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$firstOrNull$30.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    return ref$ObjectRef1.element;
                }
                catch(AbortFlowException abortFlowException1) {
                }
                FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$21);
                return ref$ObjectRef1.element;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public static final Object fold(Flow flow0, Object object0, Function3 function30, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1 flowKt__ReduceKt$fold$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1) {
            flowKt__ReduceKt$fold$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1)continuation0;
            if((flowKt__ReduceKt$fold$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$fold$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt__ReduceKt.fold(null, null, null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$fold$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$fold$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt__ReduceKt.fold(null, null, null, this);
                }
            };
        }
        Object object1 = flowKt__ReduceKt$fold$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$fold$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = object0;
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        ObjectRef ref$ObjectRef1;
                        kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2.emit.1 flowKt__ReduceKt$fold$2$emit$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2.emit.1) {
                            flowKt__ReduceKt$fold$2$emit$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2.emit.1)continuation0;
                            if((flowKt__ReduceKt$fold$2$emit$10.label & 0x80000000) == 0) {
                                flowKt__ReduceKt$fold$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__ReduceKt$fold$2$emit$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__ReduceKt$fold$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__ReduceKt$fold$2$emit$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__ReduceKt$fold$2$emit$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                ObjectRef ref$ObjectRef0 = ref$ObjectRef0;
                                flowKt__ReduceKt$fold$2$emit$10.L$0 = ref$ObjectRef0;
                                flowKt__ReduceKt$fold$2$emit$10.label = 1;
                                Object object3 = function30.invoke(ref$ObjectRef0.element, object0, flowKt__ReduceKt$fold$2$emit$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object1 = object3;
                                ref$ObjectRef1 = ref$ObjectRef0;
                                break;
                            }
                            case 1: {
                                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$fold$2$emit$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        ref$ObjectRef1.element = object1;
                        return Unit.INSTANCE;
                    }

                    public final Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2.emit.1(this, continuation0);
                        ref$ObjectRef0.element = function30.invoke(ref$ObjectRef0.element, object0, continuation0);
                        return Unit.INSTANCE;
                    }
                };
                flowKt__ReduceKt$fold$10.L$0 = ref$ObjectRef0;
                flowKt__ReduceKt$fold$10.label = 1;
                return flow0.collect(flowCollector0, flowKt__ReduceKt$fold$10) == object2 ? object2 : ref$ObjectRef0.element;
            }
            case 1: {
                ObjectRef ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$fold$10.L$0;
                ResultKt.throwOnFailure(object1);
                return ref$ObjectRef1.element;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    private static final Object fold$$forInline(Flow flow0, Object object0, Function3 function30, Continuation continuation0) {
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        ref$ObjectRef0.element = object0;
        flow0.collect(new kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2(ref$ObjectRef0, function30), continuation0);
        return ref$ObjectRef0.element;
    }

    public static final Object last(Flow flow0, Continuation continuation0) {
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1 flowKt__ReduceKt$last$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1) {
            flowKt__ReduceKt$last$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1)continuation0;
            if((flowKt__ReduceKt$last$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$last$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.last(null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$last$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$last$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.last(null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$last$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$last$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        ref$ObjectRef0.element = object0;
                        return Unit.INSTANCE;
                    }
                };
                flowKt__ReduceKt$last$10.L$0 = ref$ObjectRef0;
                flowKt__ReduceKt$last$10.label = 1;
                if(flow0.collect(flowCollector0, flowKt__ReduceKt$last$10) == object1) {
                    return object1;
                }
                ref$ObjectRef1 = ref$ObjectRef0;
                break;
            }
            case 1: {
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$last$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(ref$ObjectRef1.element == NullSurrogateKt.NULL) {
            throw new NoSuchElementException("Expected at least one element");
        }
        return ref$ObjectRef1.element;
    }

    public static final Object lastOrNull(Flow flow0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1 flowKt__ReduceKt$lastOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1) {
            flowKt__ReduceKt$lastOrNull$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1)continuation0;
            if((flowKt__ReduceKt$lastOrNull$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$lastOrNull$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.lastOrNull(null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$lastOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$lastOrNull$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.lastOrNull(null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$lastOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$lastOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        ref$ObjectRef0.element = object0;
                        return Unit.INSTANCE;
                    }
                };
                flowKt__ReduceKt$lastOrNull$10.L$0 = ref$ObjectRef0;
                flowKt__ReduceKt$lastOrNull$10.label = 1;
                return flow0.collect(flowCollector0, flowKt__ReduceKt$lastOrNull$10) == object1 ? object1 : ref$ObjectRef0.element;
            }
            case 1: {
                ObjectRef ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$lastOrNull$10.L$0;
                ResultKt.throwOnFailure(object0);
                return ref$ObjectRef1.element;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public static final Object reduce(Flow flow0, Function3 function30, Continuation continuation0) {
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.1 flowKt__ReduceKt$reduce$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.1) {
            flowKt__ReduceKt$reduce$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.1)continuation0;
            if((flowKt__ReduceKt$reduce$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$reduce$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.reduce(null, null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$reduce$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$reduce$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.reduce(null, null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$reduce$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$reduce$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        ObjectRef ref$ObjectRef0;
                        kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.2.emit.1 flowKt__ReduceKt$reduce$2$emit$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.2.emit.1) {
                            flowKt__ReduceKt$reduce$2$emit$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.2.emit.1)continuation0;
                            if((flowKt__ReduceKt$reduce$2$emit$10.label & 0x80000000) == 0) {
                                flowKt__ReduceKt$reduce$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__ReduceKt$reduce$2$emit$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__ReduceKt$reduce$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__ReduceKt$reduce$2$emit$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__ReduceKt$reduce$2$emit$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                ref$ObjectRef0 = ref$ObjectRef0;
                                if(ref$ObjectRef0.element != NullSurrogateKt.NULL) {
                                    flowKt__ReduceKt$reduce$2$emit$10.L$0 = ref$ObjectRef0;
                                    flowKt__ReduceKt$reduce$2$emit$10.label = 1;
                                    Object object3 = function30.invoke(ref$ObjectRef0.element, object0, flowKt__ReduceKt$reduce$2$emit$10);
                                    if(object3 == object2) {
                                        return object2;
                                    }
                                    object0 = object3;
                                }
                                break;
                            }
                            case 1: {
                                ObjectRef ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$reduce$2$emit$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                ref$ObjectRef0 = ref$ObjectRef1;
                                object0 = object1;
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        ref$ObjectRef0.element = object0;
                        return Unit.INSTANCE;
                    }
                };
                flowKt__ReduceKt$reduce$10.L$0 = ref$ObjectRef0;
                flowKt__ReduceKt$reduce$10.label = 1;
                if(flow0.collect(flowCollector0, flowKt__ReduceKt$reduce$10) == object1) {
                    return object1;
                }
                ref$ObjectRef1 = ref$ObjectRef0;
                break;
            }
            case 1: {
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$reduce$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(ref$ObjectRef1.element == NullSurrogateKt.NULL) {
            throw new NoSuchElementException("Empty flow can\'t be reduced");
        }
        return ref$ObjectRef1.element;
    }

    public static final Object single(Flow flow0, Continuation continuation0) {
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1 flowKt__ReduceKt$single$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1) {
            flowKt__ReduceKt$single$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1)continuation0;
            if((flowKt__ReduceKt$single$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$single$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.single(null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$single$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$single$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.single(null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$single$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$single$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        if(ref$ObjectRef0.element != NullSurrogateKt.NULL) {
                            throw new IllegalArgumentException("Flow has more than one element");
                        }
                        ref$ObjectRef0.element = object0;
                        return Unit.INSTANCE;
                    }
                };
                flowKt__ReduceKt$single$10.L$0 = ref$ObjectRef0;
                flowKt__ReduceKt$single$10.label = 1;
                if(flow0.collect(flowCollector0, flowKt__ReduceKt$single$10) == object1) {
                    return object1;
                }
                ref$ObjectRef1 = ref$ObjectRef0;
                break;
            }
            case 1: {
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$single$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        if(ref$ObjectRef1.element == NullSurrogateKt.NULL) {
            throw new NoSuchElementException("Flow is empty");
        }
        return ref$ObjectRef1.element;
    }

    public static final Object singleOrNull(Flow flow0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull..inlined.collectWhile.1 flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$11;
        AbortFlowException abortFlowException1;
        ObjectRef ref$ObjectRef1;
        kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1 flowKt__ReduceKt$singleOrNull$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1) {
            flowKt__ReduceKt$singleOrNull$10 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1)continuation0;
            if((flowKt__ReduceKt$singleOrNull$10.label & 0x80000000) == 0) {
                flowKt__ReduceKt$singleOrNull$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.singleOrNull(null, this);
                    }
                };
            }
            else {
                flowKt__ReduceKt$singleOrNull$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__ReduceKt$singleOrNull$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.singleOrNull(null, this);
                }
            };
        }
        Object object0 = flowKt__ReduceKt$singleOrNull$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__ReduceKt$singleOrNull$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull..inlined.collectWhile.1 flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$10 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object object0, Continuation continuation0) {
                        if(ref$ObjectRef0.element == NullSurrogateKt.NULL) {
                            ref$ObjectRef0.element = object0;
                            return Unit.INSTANCE;
                        }
                        ref$ObjectRef0.element = NullSurrogateKt.NULL;
                        throw new AbortFlowException(this);
                    }
                };
                try {
                    flowKt__ReduceKt$singleOrNull$10.L$0 = ref$ObjectRef0;
                    flowKt__ReduceKt$singleOrNull$10.L$1 = flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$10;
                    flowKt__ReduceKt$singleOrNull$10.label = 1;
                    if(flow0.collect(flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$10, flowKt__ReduceKt$singleOrNull$10) == object1) {
                        return object1;
                    }
                    return ref$ObjectRef0.element == NullSurrogateKt.NULL ? null : ref$ObjectRef0.element;
                }
                catch(AbortFlowException abortFlowException0) {
                    ref$ObjectRef1 = ref$ObjectRef0;
                    abortFlowException1 = abortFlowException0;
                    flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$11 = flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$10;
                    FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$11);
                    return ref$ObjectRef1.element == NullSurrogateKt.NULL ? null : ref$ObjectRef1.element;
                }
            }
            case 1: {
                flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$11 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull..inlined.collectWhile.1)flowKt__ReduceKt$singleOrNull$10.L$1;
                ref$ObjectRef1 = (ObjectRef)flowKt__ReduceKt$singleOrNull$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    return ref$ObjectRef1.element == NullSurrogateKt.NULL ? null : ref$ObjectRef1.element;
                }
                catch(AbortFlowException abortFlowException1) {
                }
                FlowExceptions_commonKt.checkOwnership(abortFlowException1, flowKt__ReduceKt$singleOrNull$$inlined$collectWhile$11);
                return ref$ObjectRef1.element == NullSurrogateKt.NULL ? null : ref$ObjectRef1.element;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }
}

