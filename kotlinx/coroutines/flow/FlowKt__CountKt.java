package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref.IntRef;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0002\u001A!\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001AE\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0005\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000B"}, d2 = {"count", "", "T", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__CountKt {
    public static final Object count(Flow flow0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__CountKt.count.1 flowKt__CountKt$count$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__CountKt.count.1) {
            flowKt__CountKt$count$10 = (kotlinx.coroutines.flow.FlowKt__CountKt.count.1)continuation0;
            if((flowKt__CountKt$count$10.label & 0x80000000) == 0) {
                flowKt__CountKt$count$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.count(null, this);
                    }
                };
            }
            else {
                flowKt__CountKt$count$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__CountKt$count$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.count(null, this);
                }
            };
        }
        Object object0 = flowKt__CountKt$count$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__CountKt$count$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                IntRef ref$IntRef0 = new IntRef();
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        ++ref$IntRef0.element;
                        return Unit.INSTANCE;
                    }
                };
                flowKt__CountKt$count$10.L$0 = ref$IntRef0;
                flowKt__CountKt$count$10.label = 1;
                return flow0.collect(flowCollector0, flowKt__CountKt$count$10) == object1 ? object1 : Boxing.boxInt(ref$IntRef0.element);
            }
            case 1: {
                IntRef ref$IntRef1 = (IntRef)flowKt__CountKt$count$10.L$0;
                ResultKt.throwOnFailure(object0);
                return Boxing.boxInt(ref$IntRef1.element);
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public static final Object count(Flow flow0, Function2 function20, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__CountKt.count.3 flowKt__CountKt$count$30;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__CountKt.count.3) {
            flowKt__CountKt$count$30 = (kotlinx.coroutines.flow.FlowKt__CountKt.count.3)continuation0;
            if((flowKt__CountKt$count$30.label & 0x80000000) == 0) {
                flowKt__CountKt$count$30 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.count(null, null, this);
                    }
                };
            }
            else {
                flowKt__CountKt$count$30.label ^= 0x80000000;
            }
        }
        else {
            flowKt__CountKt$count$30 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.count(null, null, this);
                }
            };
        }
        Object object0 = flowKt__CountKt$count$30.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__CountKt$count$30.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                IntRef ref$IntRef0 = new IntRef();
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__CountKt.count.4 flowKt__CountKt$count$40;
                        kotlinx.coroutines.flow.FlowKt__CountKt.count.4.emit.1 flowKt__CountKt$count$4$emit$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__CountKt.count.4.emit.1) {
                            flowKt__CountKt$count$4$emit$10 = (kotlinx.coroutines.flow.FlowKt__CountKt.count.4.emit.1)continuation0;
                            if((flowKt__CountKt$count$4$emit$10.label & 0x80000000) == 0) {
                                flowKt__CountKt$count$4$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__CountKt$count$4$emit$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__CountKt$count$4$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__CountKt$count$4$emit$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__CountKt$count$4$emit$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                flowKt__CountKt$count$4$emit$10.L$0 = this;
                                flowKt__CountKt$count$4$emit$10.label = 1;
                                object1 = function20.invoke(object0, flowKt__CountKt$count$4$emit$10);
                                if(object1 == object2) {
                                    return object2;
                                }
                                flowKt__CountKt$count$40 = this;
                                break;
                            }
                            case 1: {
                                flowKt__CountKt$count$40 = (kotlinx.coroutines.flow.FlowKt__CountKt.count.4)flowKt__CountKt$count$4$emit$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        if(((Boolean)object1).booleanValue()) {
                            ++flowKt__CountKt$count$40.$i.element;
                        }
                        return Unit.INSTANCE;
                    }
                };
                flowKt__CountKt$count$30.L$0 = ref$IntRef0;
                flowKt__CountKt$count$30.label = 1;
                return flow0.collect(flowCollector0, flowKt__CountKt$count$30) == object1 ? object1 : Boxing.boxInt(ref$IntRef0.element);
            }
            case 1: {
                IntRef ref$IntRef1 = (IntRef)flowKt__CountKt$count$30.L$0;
                ResultKt.throwOnFailure(object0);
                return Boxing.boxInt(ref$IntRef1.element);
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }
}

