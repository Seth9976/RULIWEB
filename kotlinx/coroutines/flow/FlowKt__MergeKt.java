package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.ChannelFlowMerge;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A7\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u000B0\n\"\u0004\b\u0000\u0010\u000B2\u001E\u0010\f\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u000B0\n0\r\"\b\u0012\u0004\u0012\u0002H\u000B0\n\u00A2\u0006\u0002\u0010\u000E\u001Ae\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000B\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000B0\n27\u0010\u0011\u001A3\b\u0001\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0018\u001Ah\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000B\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000B0\n29\b\u0005\u0010\u0011\u001A3\b\u0001\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0018\u001Ao\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000B\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000B0\n2\b\b\u0002\u0010\u001B\u001A\u00020\u000127\u0010\u0011\u001A3\b\u0001\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A$\u0010\u001D\u001A\b\u0012\u0004\u0012\u0002H\u000B0\n\"\u0004\b\u0000\u0010\u000B*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000B0\n0\nH\u0007\u001A.\u0010\u001E\u001A\b\u0012\u0004\u0012\u0002H\u000B0\n\"\u0004\b\u0000\u0010\u000B*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000B0\n0\n2\b\b\u0002\u0010\u001B\u001A\u00020\u0001H\u0007\u001Aa\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000B\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000B0\n23\b\u0001\u0010\u0011\u001A-\b\u0001\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0018\u001A\"\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u000B0\n\"\u0004\b\u0000\u0010\u000B*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000B0\n0 \u001Ar\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u000B\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u000B0\n2D\b\u0001\u0010\u0011\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\"\u00A2\u0006\u0002\b%H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010&\"\u001C\u0010\u0000\u001A\u00020\u00018\u0006X\u0087\u0004\u00A2\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001A\u00020\u00078\u0006X\u0087T\u00A2\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\'"}, d2 = {"DEFAULT_CONCURRENCY", "", "getDEFAULT_CONCURRENCY$annotations", "()V", "getDEFAULT_CONCURRENCY", "()I", "DEFAULT_CONCURRENCY_PROPERTY_NAME", "", "getDEFAULT_CONCURRENCY_PROPERTY_NAME$annotations", "merge", "Lkotlinx/coroutines/flow/Flow;", "T", "flows", "", "([Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;", "flatMapConcat", "R", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flatMapLatest", "flatMapMerge", "concurrency", "(Lkotlinx/coroutines/flow/Flow;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flattenConcat", "flattenMerge", "mapLatest", "", "transformLatest", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__MergeKt {
    private static final int DEFAULT_CONCURRENCY;

    static {
        FlowKt__MergeKt.DEFAULT_CONCURRENCY = SystemPropsKt.systemProp("kotlinx.coroutines.flow.defaultConcurrency", 16, 1, 0x7FFFFFFF);
    }

    public static final Flow flatMapConcat(Flow flow0, Function2 function20) {
        return FlowKt.flattenConcat(new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapConcat..inlined.map.1.2 flowKt__MergeKt$flatMapConcat$$inlined$map$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapConcat..inlined.map.1.2.1 flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapConcat..inlined.map.1.2.1) {
                            flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10 = (kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapConcat..inlined.map.1.2.1)continuation0;
                            if((flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.label & 0x80000000) == 0) {
                                flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$transform$inlined$1;
                                flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.L$0 = flowCollector0;
                                flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.label = 1;
                                Object object3 = this.$transform$inlined.invoke(object0, flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object1 = object3;
                                flowCollector1 = flowCollector0;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            case 2: {
                                ResultKt.throwOnFailure(object1);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.L$0 = null;
                        flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10.label = 2;
                        return flowCollector1.emit(object1, flowKt__MergeKt$flatMapConcat$$inlined$map$1$2$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__MergeKt$flatMapConcat$$inlined$map$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        });
    }

    public static final Flow flatMapLatest(Flow flow0, Function2 function20) {
        return FlowKt.transformLatest(flow0, new Function3(function20, null) {
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$transform = function20;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), object1, ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapLatest.1 flowKt__MergeKt$flatMapLatest$10 = new kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapLatest.1(this.$transform, continuation0);
                flowKt__MergeKt$flatMapLatest$10.L$0 = flowCollector0;
                flowKt__MergeKt$flatMapLatest$10.L$1 = object0;
                return flowKt__MergeKt$flatMapLatest$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        this.L$0 = flowCollector0;
                        this.label = 1;
                        object0 = this.$transform.invoke(this.L$1, this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)this.L$0;
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
                this.L$0 = null;
                this.label = 2;
                return FlowKt.emitAll(flowCollector0, ((Flow)object0), this) == object1 ? object1 : Unit.INSTANCE;
            }

            public final Object invokeSuspend$$forInline(Object object0) {
                FlowKt.emitAll(((FlowCollector)this.L$0), ((Flow)this.$transform.invoke(this.L$1, this)), this);
                return Unit.INSTANCE;
            }
        });
    }

    public static final Flow flatMapMerge(Flow flow0, int v, Function2 function20) {
        return FlowKt.flattenMerge(new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapMerge..inlined.map.1.2 flowKt__MergeKt$flatMapMerge$$inlined$map$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapMerge..inlined.map.1.2.1 flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapMerge..inlined.map.1.2.1) {
                            flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10 = (kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapMerge..inlined.map.1.2.1)continuation0;
                            if((flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.label & 0x80000000) == 0) {
                                flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$transform$inlined$1;
                                flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.L$0 = flowCollector0;
                                flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.label = 1;
                                Object object3 = this.$transform$inlined.invoke(object0, flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object1 = object3;
                                flowCollector1 = flowCollector0;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.L$0;
                                ResultKt.throwOnFailure(object1);
                                break;
                            }
                            case 2: {
                                ResultKt.throwOnFailure(object1);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.L$0 = null;
                        flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10.label = 2;
                        return flowCollector1.emit(object1, flowKt__MergeKt$flatMapMerge$$inlined$map$1$2$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__MergeKt$flatMapMerge$$inlined$map$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        }, v);
    }

    public static Flow flatMapMerge$default(Flow flow0, int v, Function2 function20, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = FlowKt__MergeKt.DEFAULT_CONCURRENCY;
        }
        return FlowKt.flatMapMerge(flow0, v, function20);
    }

    public static final Flow flattenConcat(Flow flow0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                FlowCollector flowCollector1 = new kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat.1.1(flowCollector0);
                Object object0 = flow0.collect(flowCollector1, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008A@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "value", "Lkotlinx/coroutines/flow/Flow;", "emit", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat.1.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat.1.1(FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object object0, Continuation continuation0) {
                return this.emit(((Flow)object0), continuation0);
            }

            public final Object emit(Flow flow0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat.1.1.emit.1 flowKt__MergeKt$flattenConcat$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat.1.1.emit.1) {
                    flowKt__MergeKt$flattenConcat$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat.1.1.emit.1)continuation0;
                    if((flowKt__MergeKt$flattenConcat$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__MergeKt$flattenConcat$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__MergeKt$flattenConcat$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__MergeKt$flattenConcat$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__MergeKt$flattenConcat$1$1$emit$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__MergeKt$flattenConcat$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowKt__MergeKt$flattenConcat$1$1$emit$10.label = 1;
                        return FlowKt.emitAll(this.$this_unsafeFlow, flow0, flowKt__MergeKt$flattenConcat$1$1$emit$10) == object1 ? object1 : Unit.INSTANCE;
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
        }

    }

    public static final Flow flattenMerge(Flow flow0, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException(("Expected positive concurrency level, but had " + v).toString());
        }
        return v == 1 ? FlowKt.flattenConcat(flow0) : new ChannelFlowMerge(flow0, v, null, 0, null, 28, null);
    }

    public static Flow flattenMerge$default(Flow flow0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = FlowKt__MergeKt.DEFAULT_CONCURRENCY;
        }
        return FlowKt.flattenMerge(flow0, v);
    }

    public static final int getDEFAULT_CONCURRENCY() [...] // 潜在的解密器

    public static void getDEFAULT_CONCURRENCY$annotations() {
    }

    public static void getDEFAULT_CONCURRENCY_PROPERTY_NAME$annotations() {
    }

    public static final Flow mapLatest(Flow flow0, Function2 function20) {
        return FlowKt.transformLatest(flow0, new Function3(function20, null) {
            final Function2 $transform;
            private Object L$0;
            Object L$1;
            int label;

            {
                this.$transform = function20;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), object1, ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__MergeKt.mapLatest.1 flowKt__MergeKt$mapLatest$10 = new kotlinx.coroutines.flow.FlowKt__MergeKt.mapLatest.1(this.$transform, continuation0);
                flowKt__MergeKt$mapLatest$10.L$0 = flowCollector0;
                flowKt__MergeKt$mapLatest$10.L$1 = object0;
                return flowKt__MergeKt$mapLatest$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        this.L$0 = flowCollector0;
                        this.label = 1;
                        object0 = this.$transform.invoke(this.L$1, this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        flowCollector0 = (FlowCollector)this.L$0;
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
                this.L$0 = null;
                this.label = 2;
                return flowCollector0.emit(object0, this) == object1 ? object1 : Unit.INSTANCE;
            }
        });
    }

    public static final Flow merge(Iterable iterable0) {
        return new ChannelLimitedFlowMerge(iterable0, null, 0, null, 14, null);
    }

    public static final Flow merge(Flow[] arr_flow) {
        return FlowKt.merge(ArraysKt.asIterable(arr_flow));
    }

    public static final Flow transformLatest(Flow flow0, Function3 function30) {
        return new ChannelFlowTransformLatest(function30, flow0, null, 0, null, 28, null);
    }
}

