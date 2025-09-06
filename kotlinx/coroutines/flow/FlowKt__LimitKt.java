package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.IntRef;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AV\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000323\b\u0004\u0010\u0004\u001A-\b\u0001\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u0005H\u0080H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\f\u001A$\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000E\u001A\u00020\u000F\u001AH\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u0005\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0011\u001A+\u0010\u0012\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00132\u0006\u0010\b\u001A\u0002H\u0002H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0014\u0010\u0015\u001A$\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000E\u001A\u00020\u000F\u001AH\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u0005\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0011\u001Ap\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00190\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0019*\b\u0012\u0004\u0012\u0002H\u00020\u00032D\b\u0001\u0010\u001A\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u0013\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u001B\u00A2\u0006\u0002\b\u001C\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001D\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u001E"}, d2 = {"collectWhile", "", "T", "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "drop", "count", "", "dropWhile", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "emitAbort", "Lkotlinx/coroutines/flow/FlowCollector;", "emitAbort$FlowKt__LimitKt", "(Lkotlinx/coroutines/flow/FlowCollector;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "take", "takeWhile", "transformWhile", "R", "transform", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__LimitKt {
    public static final Object collectWhile(Flow flow0, Function2 function20, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1 flowKt__LimitKt$collectWhile$collector$10;
        kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1 flowKt__LimitKt$collectWhile$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1) {
            flowKt__LimitKt$collectWhile$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1)continuation0;
            if((flowKt__LimitKt$collectWhile$10.label & 0x80000000) == 0) {
                flowKt__LimitKt$collectWhile$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt__LimitKt.collectWhile(null, null, this);
                    }
                };
            }
            else {
                flowKt__LimitKt$collectWhile$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__LimitKt$collectWhile$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt__LimitKt.collectWhile(null, null, this);
                }
            };
        }
        Object object0 = flowKt__LimitKt$collectWhile$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__LimitKt$collectWhile$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1 flowKt__LimitKt$collectWhile$collector$11 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1 flowKt__LimitKt$collectWhile$collector$10;
                        kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1.emit.1 flowKt__LimitKt$collectWhile$collector$1$emit$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1.emit.1) {
                            flowKt__LimitKt$collectWhile$collector$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1.emit.1)continuation0;
                            if((flowKt__LimitKt$collectWhile$collector$1$emit$10.label & 0x80000000) == 0) {
                                flowKt__LimitKt$collectWhile$collector$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__LimitKt$collectWhile$collector$1$emit$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__LimitKt$collectWhile$collector$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__LimitKt$collectWhile$collector$1$emit$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__LimitKt$collectWhile$collector$1$emit$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                flowKt__LimitKt$collectWhile$collector$1$emit$10.L$0 = this;
                                flowKt__LimitKt$collectWhile$collector$1$emit$10.label = 1;
                                object1 = function20.invoke(object0, flowKt__LimitKt$collectWhile$collector$1$emit$10);
                                if(object1 == object2) {
                                    return object2;
                                }
                                flowKt__LimitKt$collectWhile$collector$10 = this;
                                break;
                            }
                            case 1: {
                                flowKt__LimitKt$collectWhile$collector$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1)flowKt__LimitKt$collectWhile$collector$1$emit$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        if(!((Boolean)object1).booleanValue()) {
                            throw new AbortFlowException(flowKt__LimitKt$collectWhile$collector$10);
                        }
                        return Unit.INSTANCE;
                    }

                    public Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1.emit.1(this, continuation0);
                        if(!((Boolean)function20.invoke(object0, continuation0)).booleanValue()) {
                            throw new AbortFlowException(this);
                        }
                        return Unit.INSTANCE;
                    }
                };
                try {
                    flowKt__LimitKt$collectWhile$10.L$0 = flowKt__LimitKt$collectWhile$collector$11;
                    flowKt__LimitKt$collectWhile$10.label = 1;
                    return flow0.collect(flowKt__LimitKt$collectWhile$collector$11, flowKt__LimitKt$collectWhile$10) == object1 ? object1 : Unit.INSTANCE;
                }
                catch(AbortFlowException abortFlowException0) {
                    flowKt__LimitKt$collectWhile$collector$10 = flowKt__LimitKt$collectWhile$collector$11;
                }
                FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$collectWhile$collector$10);
                return Unit.INSTANCE;
            }
            case 1: {
                flowKt__LimitKt$collectWhile$collector$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1)flowKt__LimitKt$collectWhile$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                }
                catch(AbortFlowException abortFlowException0) {
                    FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$collectWhile$collector$10);
                }
                return Unit.INSTANCE;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    private static final Object collectWhile$$forInline(Flow flow0, Function2 function20, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1 flowKt__LimitKt$collectWhile$collector$10 = new kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1(function20);
        try {
            flow0.collect(flowKt__LimitKt$collectWhile$collector$10, continuation0);
        }
        catch(AbortFlowException abortFlowException0) {
            FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$collectWhile$collector$10);
        }
        return Unit.INSTANCE;
    }

    public static final Flow drop(Flow flow0, int v) {
        if(v < 0) {
            throw new IllegalArgumentException(("Drop count should be non-negative, but had " + v).toString());
        }
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1 flowKt__LimitKt$drop$2$10 = new kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1(new IntRef(), v, flowCollector0);
                Object object0 = flow0.collect(flowKt__LimitKt$drop$2$10, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1(IntRef ref$IntRef0, int v, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1.emit.1 flowKt__LimitKt$drop$2$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1.emit.1) {
                    flowKt__LimitKt$drop$2$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.drop.2.1.emit.1)continuation0;
                    if((flowKt__LimitKt$drop$2$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__LimitKt$drop$2$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__LimitKt$drop$2$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__LimitKt$drop$2$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__LimitKt$drop$2$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__LimitKt$drop$2$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        if(this.$skipped.element >= this.$count) {
                            flowKt__LimitKt$drop$2$1$emit$10.label = 1;
                            return this.$this_unsafeFlow.emit(object0, flowKt__LimitKt$drop$2$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                        }
                        ++this.$skipped.element;
                        return Unit.INSTANCE;
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

    public static final Flow dropWhile(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1 flowKt__LimitKt$dropWhile$1$10 = new kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1(new BooleanRef(), flowCollector0, function20);
                Object object0 = flow0.collect(flowKt__LimitKt$dropWhile$1$10, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1(BooleanRef ref$BooleanRef0, FlowCollector flowCollector0, Function2 function20) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1 flowKt__LimitKt$dropWhile$1$10;
                kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1.emit.1 flowKt__LimitKt$dropWhile$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1.emit.1) {
                    flowKt__LimitKt$dropWhile$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1.emit.1)continuation0;
                    if((flowKt__LimitKt$dropWhile$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__LimitKt$dropWhile$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__LimitKt$dropWhile$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__LimitKt$dropWhile$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__LimitKt$dropWhile$1$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__LimitKt$dropWhile$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        if(this.$matched.element) {
                            flowKt__LimitKt$dropWhile$1$1$emit$10.label = 1;
                            return this.$this_unsafeFlow.emit(object0, flowKt__LimitKt$dropWhile$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                        }
                        flowKt__LimitKt$dropWhile$1$1$emit$10.L$0 = this;
                        flowKt__LimitKt$dropWhile$1$1$emit$10.L$1 = object0;
                        flowKt__LimitKt$dropWhile$1$1$emit$10.label = 2;
                        object1 = this.$predicate.invoke(object0, flowKt__LimitKt$dropWhile$1$1$emit$10);
                        if(object1 == object2) {
                            return object2;
                        }
                        flowKt__LimitKt$dropWhile$1$10 = this;
                        break;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object1);
                        return Unit.INSTANCE;
                    }
                    case 2: {
                        object0 = flowKt__LimitKt$dropWhile$1$1$emit$10.L$1;
                        flowKt__LimitKt$dropWhile$1$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile.1.1)flowKt__LimitKt$dropWhile$1$1$emit$10.L$0;
                        ResultKt.throwOnFailure(object1);
                        break;
                    }
                    case 3: {
                        ResultKt.throwOnFailure(object1);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                if(!((Boolean)object1).booleanValue()) {
                    flowKt__LimitKt$dropWhile$1$10.$matched.element = true;
                    flowKt__LimitKt$dropWhile$1$1$emit$10.L$0 = null;
                    flowKt__LimitKt$dropWhile$1$1$emit$10.L$1 = null;
                    flowKt__LimitKt$dropWhile$1$1$emit$10.label = 3;
                    return flowKt__LimitKt$dropWhile$1$10.$this_unsafeFlow.emit(object0, flowKt__LimitKt$dropWhile$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
        }

    }

    private static final Object emitAbort$FlowKt__LimitKt(FlowCollector flowCollector0, Object object0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1 flowKt__LimitKt$emitAbort$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1) {
            flowKt__LimitKt$emitAbort$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1)continuation0;
            if((flowKt__LimitKt$emitAbort$10.label & 0x80000000) == 0) {
                flowKt__LimitKt$emitAbort$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt__LimitKt.emitAbort$FlowKt__LimitKt(null, null, this);
                    }
                };
            }
            else {
                flowKt__LimitKt$emitAbort$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__LimitKt$emitAbort$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt__LimitKt.emitAbort$FlowKt__LimitKt(null, null, this);
                }
            };
        }
        Object object1 = flowKt__LimitKt$emitAbort$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__LimitKt$emitAbort$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                flowKt__LimitKt$emitAbort$10.L$0 = flowCollector0;
                flowKt__LimitKt$emitAbort$10.label = 1;
                if(flowCollector0.emit(object0, flowKt__LimitKt$emitAbort$10) == object2) {
                    return object2;
                }
                break;
            }
            case 1: {
                flowCollector0 = (FlowCollector)flowKt__LimitKt$emitAbort$10.L$0;
                ResultKt.throwOnFailure(object1);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        throw new AbortFlowException(flowCollector0);
    }

    public static final Flow take(Flow flow0, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException(("Requested element count " + v + " should be positive").toString());
        }
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.take..inlined.unsafeFlow.1.1 flowKt__LimitKt$take$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.take..inlined.unsafeFlow.1.1) {
                    flowKt__LimitKt$take$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.take..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__LimitKt$take$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__LimitKt$take$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        IntRef ref$IntRef0 = new IntRef();
                        try {
                            kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1 flowKt__LimitKt$take$2$10 = new kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1(ref$IntRef0, v, flowCollector0);
                            flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.L$0 = flowCollector0;
                            flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.label = 1;
                            if(flow0.collect(flowKt__LimitKt$take$2$10, flowKt__LimitKt$take$$inlined$unsafeFlow$1$10) == object1) {
                                return object1;
                            }
                        }
                        catch(AbortFlowException abortFlowException0) {
                            FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowCollector0);
                        }
                        return Unit.INSTANCE;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)flowKt__LimitKt$take$$inlined$unsafeFlow$1$10.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                        }
                        catch(AbortFlowException abortFlowException0) {
                            FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowCollector0);
                        }
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1(IntRef ref$IntRef0, int v, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1.emit.1 flowKt__LimitKt$take$2$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1.emit.1) {
                    flowKt__LimitKt$take$2$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.take.2.1.emit.1)continuation0;
                    if((flowKt__LimitKt$take$2$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__LimitKt$take$2$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__LimitKt$take$2$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__LimitKt$take$2$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__LimitKt$take$2$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__LimitKt$take$2$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        ++this.$consumed.element;
                        if(this.$consumed.element < this.$count) {
                            flowKt__LimitKt$take$2$1$emit$10.label = 1;
                            return this.$this_unsafeFlow.emit(object0, flowKt__LimitKt$take$2$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                        }
                        flowKt__LimitKt$take$2$1$emit$10.label = 2;
                        return FlowKt__LimitKt.emitAbort$FlowKt__LimitKt(this.$this_unsafeFlow, object0, flowKt__LimitKt$take$2$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object1);
                        return Unit.INSTANCE;
                    }
                    case 2: {
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

    public static final Flow takeWhile(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1 flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10;
                kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile..inlined.unsafeFlow.1.1 flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile..inlined.unsafeFlow.1.1) {
                    flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1 flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$11 = new kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1(function20, flowCollector0);
                        try {
                            flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.L$0 = flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$11;
                            flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.label = 1;
                            return flow0.collect(flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$11, flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10) == object1 ? object1 : Unit.INSTANCE;
                        }
                        catch(AbortFlowException abortFlowException0) {
                            flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10 = flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$11;
                        }
                        FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10);
                        return Unit.INSTANCE;
                    }
                    case 1: {
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1)flowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$10.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                        }
                        catch(AbortFlowException abortFlowException0) {
                            FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10);
                        }
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        };

        @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__LimitKt$collectWhile$collector$1", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public final class kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1 implements FlowCollector {
            public kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1(Function2 function20, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1 flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10;
                Object object5;
                Object object4;
                kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1.1 flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1.1) {
                    flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1.1)continuation0;
                    if((flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.label & 0x80000000) == 0) {
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                boolean z = true;
                switch(flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$0 = this;
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$1 = object0;
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.label = 1;
                        Object object3 = this.$predicate$inlined.invoke(object0, flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10);
                        if(object3 == object2) {
                            return object2;
                        }
                        object4 = object3;
                        object5 = object0;
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10 = this;
                        goto label_29;
                    }
                    case 1: {
                        Object object6 = flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$1;
                        kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1 flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$11 = (kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1)flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$0;
                        ResultKt.throwOnFailure(object1);
                        object5 = object6;
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10 = flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$11;
                        object4 = object1;
                    label_29:
                        if(((Boolean)object4).booleanValue()) {
                            flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$0 = flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10;
                            flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$1 = null;
                            flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.label = 2;
                            if(flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10.$this_unsafeFlow$inlined.emit(object5, flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10) == object2) {
                                return object2;
                            }
                        }
                        else {
                            z = false;
                        }
                        break;
                    }
                    case 2: {
                        flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile.lambda.6..inlined.collectWhile.1)flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$10.L$0;
                        ResultKt.throwOnFailure(object1);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                if(!z) {
                    throw new AbortFlowException(flowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$10);
                }
                return Unit.INSTANCE;
            }
        }

    }

    public static final Flow transformWhile(Flow flow0, Function3 function30) {
        return FlowKt.flow(new Function2(flow0, function30, null) {
            final Flow $this_transformWhile;
            final Function3 $transform;
            private Object L$0;
            int label;

            {
                this.$this_transformWhile = flow0;
                this.$transform = function30;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1 flowKt__LimitKt$transformWhile$10 = new kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1(this.$this_transformWhile, this.$transform, continuation0);
                flowKt__LimitKt$transformWhile$10.L$0 = object0;
                return flowKt__LimitKt$transformWhile$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1 flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1 flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$11 = new FlowCollector() {
                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public Object emit(Object object0, Continuation continuation0) {
                                kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1 flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10;
                                kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1.1 flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10;
                                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1.1) {
                                    flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1.1)continuation0;
                                    if((flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.label & 0x80000000) == 0) {
                                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.label ^= 0x80000000;
                                    }
                                }
                                else {
                                    flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                Object object1 = flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.result;
                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object1);
                                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.L$0 = this;
                                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.label = 1;
                                        object1 = ((FlowCollector)this.L$0).invoke(this.$$this$flow$inlined, object0, flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10);
                                        if(object1 == object2) {
                                            return object2;
                                        }
                                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10 = this;
                                        break;
                                    }
                                    case 1: {
                                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1)flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$10.L$0;
                                        ResultKt.throwOnFailure(object1);
                                        break;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                                if(!((Boolean)object1).booleanValue()) {
                                    throw new AbortFlowException(flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        try {
                            this.L$0 = flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$11;
                            this.label = 1;
                            return this.$this_transformWhile.collect(flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$11, this) == object1 ? object1 : Unit.INSTANCE;
                        }
                        catch(AbortFlowException abortFlowException0) {
                            flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10 = flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$11;
                        }
                        FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10);
                        return Unit.INSTANCE;
                    }
                    case 1: {
                        flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10 = (kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1.invokeSuspend..inlined.collectWhile.1)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                        }
                        catch(AbortFlowException abortFlowException0) {
                            FlowExceptions_commonKt.checkOwnership(abortFlowException0, flowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$10);
                        }
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        });
    }
}

