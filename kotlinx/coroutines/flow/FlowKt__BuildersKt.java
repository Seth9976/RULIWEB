package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\u001C\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AK\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004\u00A2\u0006\u0002\b\t\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\n\u001AK\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004\u00A2\u0006\u0002\b\t\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\n\u001A\u0012\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001AK\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004\u00A2\u0006\u0002\b\t\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\n\u001A\u001F\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0010\u001A\u0002H\u0002\u00A2\u0006\u0002\u0010\u0011\u001A+\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0012\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0013\"\u0002H\u0002\u00A2\u0006\u0002\u0010\u0014\u001A\u001C\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0016\u001A!\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0013\u00A2\u0006\u0002\u0010\u0014\u001A\u0010\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00170\u0001*\u00020\u0018\u001A\u0010\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00190\u0001*\u00020\u001A\u001A\u001C\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001B\u001A\u001C\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001C\u001A\u0010\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00170\u0001*\u00020\u001D\u001A\u0010\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00190\u0001*\u00020\u001E\u001A\u001C\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001F\u001A4\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0 \u00F8\u0001\u0000\u00A2\u0006\u0002\u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\""}, d2 = {"callbackFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "channelFlow", "emptyFlow", "flow", "Lkotlinx/coroutines/flow/FlowCollector;", "flowOf", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "elements", "", "([Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "asFlow", "Lkotlin/Function0;", "", "", "", "", "", "", "Lkotlin/ranges/IntRange;", "Lkotlin/ranges/LongRange;", "Lkotlin/sequences/Sequence;", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__BuildersKt {
    public static final Flow asFlow(Iterable iterable0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Iterator iterator0;
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.3.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.3.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.3.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector0;
                        iterator0 = iterable0.iterator();
                        break;
                    }
                    case 1: {
                        iterator0 = (Iterator)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.L$1 = iterator0;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10.label = 1;
                    if(flowCollector1.emit(object2, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$10) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(Iterator iterator0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Iterator iterator0;
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.4.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.4.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.4.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector0;
                        iterator0 = iterator0;
                        break;
                    }
                    case 1: {
                        iterator0 = (Iterator)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.L$1 = iterator0;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10.label = 1;
                    if(flowCollector1.emit(object2, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4$10) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(Function0 function00) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Object object0 = flowCollector0.emit(function00.invoke(), continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(Function1 function10) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.2.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.2.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.2.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            Object L$0;
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        Object L$0;
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.L$0 = flowCollector0;
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.label = 1;
                        object0 = function10.invoke(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.L$0;
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
                flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.L$0 = null;
                flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10.label = 2;
                return flowCollector0.emit(object0, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$10) == object1 ? object1 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(IntRange intRange0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Iterator iterator0;
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.9.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.9.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.9.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector0;
                        iterator0 = intRange0.iterator();
                        break;
                    }
                    case 1: {
                        iterator0 = (Iterator)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Integer integer0 = Boxing.boxInt(((IntIterator)iterator0).nextInt());
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.L$1 = iterator0;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10.label = 1;
                    if(flowCollector1.emit(integer0, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$10) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(LongRange longRange0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Iterator iterator0;
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.10.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.10.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.10.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector0;
                        iterator0 = longRange0.iterator();
                        break;
                    }
                    case 1: {
                        iterator0 = (Iterator)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Long long0 = Boxing.boxLong(((LongIterator)iterator0).nextLong());
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.L$1 = iterator0;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10.label = 1;
                    if(flowCollector1.emit(long0, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10$10) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(Sequence sequence0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Iterator iterator0;
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.5.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.5.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.5.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector0;
                        iterator0 = sequence0.iterator();
                        break;
                    }
                    case 1: {
                        iterator0 = (Iterator)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.L$1 = iterator0;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10.label = 1;
                    if(flowCollector1.emit(object2, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5$10) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(int[] arr_v) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                int v1;
                int v;
                FlowCollector flowCollector1;
                int[] arr_v;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.7.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.7.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.7.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            int I$0;
                            int I$1;
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        int I$0;
                        int I$1;
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        arr_v = arr_v;
                        flowCollector1 = flowCollector0;
                        v = arr_v.length;
                        v1 = 0;
                        goto label_25;
                    }
                    case 1: {
                        v = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.I$1;
                        v1 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.I$0;
                        arr_v = (int[])flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    ++v1;
                label_25:
                    if(v1 >= v) {
                        break;
                    }
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.L$1 = arr_v;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.I$0 = v1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.I$1 = v;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10.label = 1;
                    if(flowCollector1.emit(Boxing.boxInt(arr_v[v1]), flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$10) != object1) {
                        continue;
                    }
                    return object1;
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(long[] arr_v) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                int v1;
                int v;
                FlowCollector flowCollector1;
                long[] arr_v;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.8.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.8.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.8.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            int I$0;
                            int I$1;
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        int I$0;
                        int I$1;
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        arr_v = arr_v;
                        flowCollector1 = flowCollector0;
                        v = arr_v.length;
                        v1 = 0;
                        goto label_25;
                    }
                    case 1: {
                        v = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.I$1;
                        v1 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.I$0;
                        arr_v = (long[])flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    ++v1;
                label_25:
                    if(v1 >= v) {
                        break;
                    }
                    Long long0 = Boxing.boxLong(arr_v[v1]);
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.L$1 = arr_v;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.I$0 = v1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.I$1 = v;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10.label = 1;
                    if(flowCollector1.emit(long0, flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$10) != object1) {
                        continue;
                    }
                    return object1;
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow asFlow(Object[] arr_object) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                int v1;
                int v;
                FlowCollector flowCollector1;
                Object[] arr_object;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.6.1 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.6.1) {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.6.1)continuation0;
                    if((flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            int I$0;
                            int I$1;
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
                        flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        int I$0;
                        int I$1;
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
                Object object0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        arr_object = arr_object;
                        flowCollector1 = flowCollector0;
                        v = arr_object.length;
                        v1 = 0;
                        goto label_25;
                    }
                    case 1: {
                        v = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.I$1;
                        v1 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.I$0;
                        arr_object = (Object[])flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.L$1;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    ++v1;
                label_25:
                    if(v1 >= v) {
                        break;
                    }
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.L$0 = flowCollector1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.L$1 = arr_object;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.I$0 = v1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.I$1 = v;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10.label = 1;
                    if(flowCollector1.emit(arr_object[v1], flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$10) != object1) {
                        continue;
                    }
                    return object1;
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow callbackFlow(Function2 function20) {
        return new CallbackFlowBuilder(function20, null, 0, null, 14, null);
    }

    public static final Flow channelFlow(Function2 function20) {
        return new ChannelFlowBuilder(function20, null, 0, null, 14, null);
    }

    public static final Flow emptyFlow() {
        return (FlowCollector flowCollector0, Continuation continuation0) -> Unit.INSTANCE;
    }

    public static final Flow flow(Function2 function20) {
        return new SafeFlow(function20);
    }

    public static final Flow flowOf(Object object0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Object object0 = flowCollector0.emit(object0, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow flowOf(Object[] arr_object) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.1 flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$10;
                int v1;
                FlowCollector flowCollector1;
                int v;
                kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.1.1 flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.1.1) {
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                            int I$0;
                            int I$1;
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
                        flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                        int I$0;
                        int I$1;
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
                Object object0 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        v = 0;
                        flowCollector1 = flowCollector0;
                        v1 = arr_object.length;
                        flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$10 = this;
                        goto label_25;
                    }
                    case 1: {
                        v1 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.I$1;
                        v = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.I$0;
                        FlowCollector flowCollector2 = (FlowCollector)flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$10 = (kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.1)flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        flowCollector1 = flowCollector2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(true) {
                    ++v;
                label_25:
                    if(v >= v1) {
                        break;
                    }
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.L$0 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$10;
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.L$1 = flowCollector1;
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.I$0 = v;
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.I$1 = v1;
                    flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10.label = 1;
                    if(flowCollector1.emit(flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$10.$elements$inlined[v], flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$10) != object1) {
                        continue;
                    }
                    return object1;
                }
                return Unit.INSTANCE;
            }
        };
    }
}

