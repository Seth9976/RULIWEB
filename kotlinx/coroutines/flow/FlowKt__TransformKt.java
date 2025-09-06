package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001AM\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012$\b\u0004\u0010\u0003\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\b\u001A\u001F\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0006\b\u0000\u0010\n\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0001H\u0086\b\u001A,\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\n0\u0001\"\b\b\u0000\u0010\n*\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00012\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\n0\f\u001AM\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012$\b\u0004\u0010\u0003\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\b\u001A\"\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0007*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\u001Ab\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u000123\b\u0004\u0010\u0010\u001A-\b\u0001\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\b\u001Ah\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\n*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00020\u000125\b\u0004\u0010\u0010\u001A/\b\u0001\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\b\u001AH\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u0016\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\b\u001A|\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0019\u001A\u0002H\n2H\b\u0001\u0010\u001A\u001AB\b\u0001\u0012\u0013\u0012\u0011H\n\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001B\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001D\u001Al\u0010\u001E\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012F\u0010\u001A\u001AB\b\u0001\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001B\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001F\u001A|\u0010 \u001A\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0019\u001A\u0002H\n2H\b\u0001\u0010\u001A\u001AB\b\u0001\u0012\u0013\u0012\u0011H\n\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001B\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001D\u001A\"\u0010!\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\"0\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006#"}, d2 = {"filter", "Lkotlinx/coroutines/flow/Flow;", "T", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "filterIsInstance", "R", "klass", "Lkotlin/reflect/KClass;", "filterNot", "filterNotNull", "map", "transform", "Lkotlin/ParameterName;", "name", "value", "mapNotNull", "onEach", "action", "", "runningFold", "initial", "operation", "Lkotlin/Function3;", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "runningReduce", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "scan", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__TransformKt {
    public static final Flow filter(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2 flowKt__TransformKt$filter$$inlined$unsafeTransform$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        Object object4;
                        kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2.1 flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2.1) {
                            flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2.1)continuation0;
                            if((flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$predicate$inlined;
                                flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.L$0 = object0;
                                flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.L$1 = flowCollector0;
                                flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.label = 1;
                                Object object3 = this.$predicate$inlined.invoke(object0, flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object4 = object0;
                                flowCollector1 = flowCollector0;
                                object1 = object3;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.L$1;
                                object4 = flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.L$0;
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
                        if(((Boolean)object1).booleanValue()) {
                            flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.L$0 = null;
                            flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.L$1 = null;
                            flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10.label = 2;
                            if(flowCollector1.emit(object4, flowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$10) == object2) {
                                return object2;
                            }
                        }
                        return Unit.INSTANCE;
                    }

                    public final Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2.1(this, continuation0);
                        FlowCollector flowCollector0 = this.$predicate$inlined;
                        if(((Boolean)this.$predicate$inlined.invoke(object0, continuation0)).booleanValue()) {
                            flowCollector0.emit(object0, continuation0);
                        }
                        return Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__TransformKt$filter$$inlined$unsafeTransform$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.1(kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1 flowKt__TransformKt$filter$$inlined$unsafeTransform$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.1(this, continuation0);
                kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2 flowKt__TransformKt$filter$$inlined$unsafeTransform$1$20 = new kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1.2(flowCollector0, function20);
                flow0.collect(flowKt__TransformKt$filter$$inlined$unsafeTransform$1$20, continuation0);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow filterIsInstance(Flow flow0) {
        Intrinsics.needClassReification();
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Intrinsics.needClassReification();
                FlowCollector flowCollector1 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.2.1 flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.2.1) {
                            flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.2.1)continuation0;
                            if((flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$this_unsafeFlow;
                                Intrinsics.reifiedOperationMarker(3, "R");
                                if(object0 instanceof Object) {
                                    flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10.label = 1;
                                    if(flowCollector0.emit(object0, flowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$10) == object2) {
                                        return object2;
                                    }
                                }
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

                    public final Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.2.1(this, continuation0);
                        FlowCollector flowCollector0 = this.$this_unsafeFlow;
                        Intrinsics.reifiedOperationMarker(3, "R");
                        if(object0 instanceof Object) {
                            flowCollector0.emit(object0, continuation0);
                        }
                        return Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowCollector1, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.1(kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1 flowKt__TransformKt$filterIsInstance$$inlined$filter$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.1(this, continuation0);
                Intrinsics.needClassReification();
                FlowCollector flowCollector1 = new kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.1.2(flowCollector0);
                flow0.collect(flowCollector1, continuation0);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow filterIsInstance(Flow flow0, KClass kClass0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.2.2 flowKt__TransformKt$filterIsInstance$$inlined$filter$2$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.2.2.1 flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.2.2.1) {
                            flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.2.2.1)continuation0;
                            if((flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$klass$inlined;
                                if(this.$klass$inlined.isInstance(object0)) {
                                    flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10.label = 1;
                                    if(flowCollector0.emit(object0, flowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$10) == object2) {
                                        return object2;
                                    }
                                }
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
                };
                Object object0 = flow0.collect(flowKt__TransformKt$filterIsInstance$$inlined$filter$2$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow filterNot(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2 flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        Object object4;
                        kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2.1 flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2.1) {
                            flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2.1)continuation0;
                            if((flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$predicate$inlined;
                                flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.L$0 = object0;
                                flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.L$1 = flowCollector0;
                                flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.label = 1;
                                Object object3 = this.$predicate$inlined.invoke(object0, flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object4 = object0;
                                flowCollector1 = flowCollector0;
                                object1 = object3;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.L$1;
                                object4 = flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.L$0;
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
                        if(!((Boolean)object1).booleanValue()) {
                            flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.L$0 = null;
                            flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.L$1 = null;
                            flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10.label = 2;
                            if(flowCollector1.emit(object4, flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$2$10) == object2) {
                                return object2;
                            }
                        }
                        return Unit.INSTANCE;
                    }

                    public final Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2.1(this, continuation0);
                        FlowCollector flowCollector0 = this.$predicate$inlined;
                        if(!((Boolean)this.$predicate$inlined.invoke(object0, continuation0)).booleanValue()) {
                            flowCollector0.emit(object0, continuation0);
                        }
                        return Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.1(kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1 flowKt__TransformKt$filterNot$$inlined$unsafeTransform$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.1(this, continuation0);
                kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2 flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$20 = new kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1.2(flowCollector0, function20);
                flow0.collect(flowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$20, continuation0);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow filterNotNull(Flow flow0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                FlowCollector flowCollector1 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        kotlinx.coroutines.flow.FlowKt__TransformKt.filterNotNull..inlined.unsafeTransform.1.2.1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.filterNotNull..inlined.unsafeTransform.1.2.1) {
                            flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.filterNotNull..inlined.unsafeTransform.1.2.1)continuation0;
                            if((flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$this_unsafeFlow;
                                if(object0 != null) {
                                    flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10.label = 1;
                                    if(flowCollector0.emit(object0, flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$10) == object2) {
                                        return object2;
                                    }
                                }
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
                };
                Object object0 = flow0.collect(flowCollector1, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow map(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2 flowKt__TransformKt$map$$inlined$unsafeTransform$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2.1 flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2.1) {
                            flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2.1)continuation0;
                            if((flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$transform$inlined$1;
                                flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.L$0 = flowCollector0;
                                flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.label = 1;
                                Object object3 = this.$transform$inlined.invoke(object0, flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object1 = object3;
                                flowCollector1 = flowCollector0;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.L$0;
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
                        flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.L$0 = null;
                        flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10.label = 2;
                        return flowCollector1.emit(object1, flowKt__TransformKt$map$$inlined$unsafeTransform$1$2$10) == object2 ? object2 : Unit.INSTANCE;
                    }

                    public final Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2.1(this, continuation0);
                        Object object1 = this.$transform$inlined.invoke(object0, continuation0);
                        this.$transform$inlined$1.emit(object1, continuation0);
                        return Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__TransformKt$map$$inlined$unsafeTransform$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.1(kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1 flowKt__TransformKt$map$$inlined$unsafeTransform$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.1(this, continuation0);
                kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2 flowKt__TransformKt$map$$inlined$unsafeTransform$1$20 = new kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1.2(flowCollector0, function20);
                flow0.collect(flowKt__TransformKt$map$$inlined$unsafeTransform$1$20, continuation0);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow mapNotNull(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2 flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2.1 flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2.1) {
                            flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2.1)continuation0;
                            if((flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$transform$inlined$1;
                                flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.L$0 = flowCollector0;
                                flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.label = 1;
                                Object object3 = this.$transform$inlined.invoke(object0, flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10);
                                if(object3 == object2) {
                                    return object2;
                                }
                                object1 = object3;
                                flowCollector1 = flowCollector0;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.L$0;
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
                        if(object1 != null) {
                            flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.L$0 = null;
                            flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10.label = 2;
                            if(flowCollector1.emit(object1, flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2$10) == object2) {
                                return object2;
                            }
                        }
                        return Unit.INSTANCE;
                    }

                    public final Object emit$$forInline(Object object0, Continuation continuation0) {
                        new kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2.1(this, continuation0);
                        FlowCollector flowCollector0 = this.$transform$inlined$1;
                        Object object1 = this.$transform$inlined.invoke(object0, continuation0);
                        if(object1 != null) {
                            flowCollector0.emit(object1, continuation0);
                        }
                        return Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.1(kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1 flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.1(this, continuation0);
                kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2 flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$20 = new kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1.2(flowCollector0, function20);
                flow0.collect(flowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$20, continuation0);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Flow onEach(Flow flow0, Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.onEach..inlined.unsafeTransform.1.2 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$20 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        FlowCollector flowCollector1;
                        Object object3;
                        kotlinx.coroutines.flow.FlowKt__TransformKt.onEach..inlined.unsafeTransform.1.2.1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10;
                        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.onEach..inlined.unsafeTransform.1.2.1) {
                            flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.onEach..inlined.unsafeTransform.1.2.1)continuation0;
                            if((flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.label & 0x80000000) == 0) {
                                flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                                flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        Object object1 = flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$action$inlined;
                                flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.L$0 = object0;
                                flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.L$1 = flowCollector0;
                                flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.label = 1;
                                if(this.$action$inlined.invoke(object0, flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10) == object2) {
                                    return object2;
                                }
                                object3 = object0;
                                flowCollector1 = flowCollector0;
                                break;
                            }
                            case 1: {
                                flowCollector1 = (FlowCollector)flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.L$1;
                                object3 = flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.L$0;
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
                        flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.L$0 = null;
                        flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.L$1 = null;
                        flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10.label = 2;
                        return flowCollector1.emit(object3, flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$10) == object2 ? object2 : Unit.INSTANCE;
                    }
                };
                Object object0 = flow0.collect(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1$20, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };
    }

    public static final Flow runningFold(Flow flow0, Object object0, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                ObjectRef ref$ObjectRef1;
                FlowCollector flowCollector1;
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold..inlined.unsafeFlow.1 flowKt__TransformKt$runningFold$$inlined$unsafeFlow$10;
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold..inlined.unsafeFlow.1.1 flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold..inlined.unsafeFlow.1.1) {
                    flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold..inlined.unsafeFlow.1.1)continuation0;
                    if((flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.label & 0x80000000) == 0) {
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        ObjectRef ref$ObjectRef0 = new ObjectRef();
                        ref$ObjectRef0.element = object0;
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$0 = this;
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$1 = flowCollector0;
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$2 = ref$ObjectRef0;
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.label = 1;
                        if(flowCollector0.emit(ref$ObjectRef0.element, flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10) == object1) {
                            return object1;
                        }
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$10 = this;
                        flowCollector1 = flowCollector0;
                        ref$ObjectRef1 = ref$ObjectRef0;
                        break;
                    }
                    case 1: {
                        ref$ObjectRef1 = (ObjectRef)flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$2;
                        flowCollector1 = (FlowCollector)flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$1;
                        flowKt__TransformKt$runningFold$$inlined$unsafeFlow$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold..inlined.unsafeFlow.1)flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$0;
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
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1 flowKt__TransformKt$runningFold$1$10 = new kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1(ref$ObjectRef1, flowKt__TransformKt$runningFold$$inlined$unsafeFlow$10.$operation$inlined, flowCollector1);
                flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$0 = null;
                flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$1 = null;
                flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.L$2 = null;
                flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10.label = 2;
                return flowKt__TransformKt$runningFold$$inlined$unsafeFlow$10.$this_runningFold$inlined.collect(flowKt__TransformKt$runningFold$1$10, flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$10) == object1 ? object1 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1(ObjectRef ref$ObjectRef0, Function3 function30, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1 flowKt__TransformKt$runningFold$1$10;
                ObjectRef ref$ObjectRef1;
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1.emit.1 flowKt__TransformKt$runningFold$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1.emit.1) {
                    flowKt__TransformKt$runningFold$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1.emit.1)continuation0;
                    if((flowKt__TransformKt$runningFold$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__TransformKt$runningFold$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__TransformKt$runningFold$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__TransformKt$runningFold$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__TransformKt$runningFold$1$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__TransformKt$runningFold$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        ObjectRef ref$ObjectRef0 = this.$accumulator;
                        flowKt__TransformKt$runningFold$1$1$emit$10.L$0 = this;
                        flowKt__TransformKt$runningFold$1$1$emit$10.L$1 = ref$ObjectRef0;
                        flowKt__TransformKt$runningFold$1$1$emit$10.label = 1;
                        Object object3 = this.$operation.invoke(ref$ObjectRef0.element, object0, flowKt__TransformKt$runningFold$1$1$emit$10);
                        if(object3 == object2) {
                            return object2;
                        }
                        object1 = object3;
                        ref$ObjectRef1 = ref$ObjectRef0;
                        flowKt__TransformKt$runningFold$1$10 = this;
                        break;
                    }
                    case 1: {
                        ref$ObjectRef1 = (ObjectRef)flowKt__TransformKt$runningFold$1$1$emit$10.L$1;
                        flowKt__TransformKt$runningFold$1$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold.1.1)flowKt__TransformKt$runningFold$1$1$emit$10.L$0;
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
                ref$ObjectRef1.element = object1;
                flowKt__TransformKt$runningFold$1$1$emit$10.L$0 = null;
                flowKt__TransformKt$runningFold$1$1$emit$10.L$1 = null;
                flowKt__TransformKt$runningFold$1$1$emit$10.label = 2;
                return flowKt__TransformKt$runningFold$1$10.$this_unsafeFlow.emit(flowKt__TransformKt$runningFold$1$10.$accumulator.element, flowKt__TransformKt$runningFold$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
            }
        }

    }

    public static final Flow runningReduce(Flow flow0, Function3 function30) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                ObjectRef ref$ObjectRef0 = new ObjectRef();
                ref$ObjectRef0.element = NullSurrogateKt.NULL;
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1 flowKt__TransformKt$runningReduce$1$10 = new kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1(ref$ObjectRef0, function30, flowCollector0);
                Object object0 = flow0.collect(flowKt__TransformKt$runningReduce$1$10, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1(ObjectRef ref$ObjectRef0, Function3 function30, FlowCollector flowCollector0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1 flowKt__TransformKt$runningReduce$1$10;
                ObjectRef ref$ObjectRef0;
                kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1.emit.1 flowKt__TransformKt$runningReduce$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1.emit.1) {
                    flowKt__TransformKt$runningReduce$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1.emit.1)continuation0;
                    if((flowKt__TransformKt$runningReduce$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__TransformKt$runningReduce$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__TransformKt$runningReduce$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__TransformKt$runningReduce$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__TransformKt$runningReduce$1$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__TransformKt$runningReduce$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        ref$ObjectRef0 = this.$accumulator;
                        if(ref$ObjectRef0.element == NullSurrogateKt.NULL) {
                            flowKt__TransformKt$runningReduce$1$10 = this;
                        }
                        else {
                            flowKt__TransformKt$runningReduce$1$1$emit$10.L$0 = this;
                            flowKt__TransformKt$runningReduce$1$1$emit$10.L$1 = ref$ObjectRef0;
                            flowKt__TransformKt$runningReduce$1$1$emit$10.label = 1;
                            Object object3 = this.$operation.invoke(this.$accumulator.element, object0, flowKt__TransformKt$runningReduce$1$1$emit$10);
                            if(object3 == object2) {
                                return object2;
                            }
                            flowKt__TransformKt$runningReduce$1$10 = this;
                            object0 = object3;
                        }
                        break;
                    }
                    case 1: {
                        ObjectRef ref$ObjectRef1 = (ObjectRef)flowKt__TransformKt$runningReduce$1$1$emit$10.L$1;
                        flowKt__TransformKt$runningReduce$1$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce.1.1)flowKt__TransformKt$runningReduce$1$1$emit$10.L$0;
                        ResultKt.throwOnFailure(object1);
                        ref$ObjectRef0 = ref$ObjectRef1;
                        object0 = object1;
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
                ref$ObjectRef0.element = object0;
                flowKt__TransformKt$runningReduce$1$1$emit$10.L$0 = null;
                flowKt__TransformKt$runningReduce$1$1$emit$10.L$1 = null;
                flowKt__TransformKt$runningReduce$1$1$emit$10.label = 2;
                return flowKt__TransformKt$runningReduce$1$10.$this_unsafeFlow.emit(flowKt__TransformKt$runningReduce$1$10.$accumulator.element, flowKt__TransformKt$runningReduce$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
            }
        }

    }

    public static final Flow scan(Flow flow0, Object object0, Function3 function30) {
        return FlowKt.runningFold(flow0, object0, function30);
    }

    public static final Flow withIndex(Flow flow0) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                FlowCollector flowCollector1 = new kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex.1.1(flowCollector0, new IntRef());
                Object object0 = flow0.collect(flowCollector1, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u0002H\u0002H\u008A@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex.1.1 implements FlowCollector {
            kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex.1.1(FlowCollector flowCollector0, IntRef ref$IntRef0) {
            }

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex.1.1.emit.1 flowKt__TransformKt$withIndex$1$1$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex.1.1.emit.1) {
                    flowKt__TransformKt$withIndex$1$1$emit$10 = (kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex.1.1.emit.1)continuation0;
                    if((flowKt__TransformKt$withIndex$1$1$emit$10.label & 0x80000000) == 0) {
                        flowKt__TransformKt$withIndex$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        flowKt__TransformKt$withIndex$1$1$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    flowKt__TransformKt$withIndex$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object1 = flowKt__TransformKt$withIndex$1$1$emit$10.result;
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(flowKt__TransformKt$withIndex$1$1$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object1);
                        FlowCollector flowCollector0 = this.$this_unsafeFlow;
                        int v = this.$index.element;
                        this.$index.element = v + 1;
                        if(v < 0) {
                            throw new ArithmeticException("Index overflow has happened");
                        }
                        IndexedValue indexedValue0 = new IndexedValue(v, object0);
                        flowKt__TransformKt$withIndex$1$1$emit$10.label = 1;
                        return flowCollector0.emit(indexedValue0, flowKt__TransformKt$withIndex$1$1$emit$10) == object2 ? object2 : Unit.INSTANCE;
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
}

