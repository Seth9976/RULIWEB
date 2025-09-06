package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003Bx\u0012B\u0010\u0004\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000B0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\r\u0012\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u000F\u0012\b\b\u0002\u0010\u0010\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001A\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001A\u00020\u0015ø\u0001\u0000¢\u0006\u0002\u0010\u0016J&\u0010\u0018\u001A\b\u0012\u0004\u0012\u00028\u00010\u00192\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u0015H\u0014J\u001F\u0010\u001A\u001A\u00020\u000B2\f\u0010\u001B\u001A\b\u0012\u0004\u0012\u00028\u00010\u0006H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001CRO\u0010\u0004\u001A>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000B0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\rX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001D"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowTransformLatest;", "T", "R", "Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "transform", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlin/jvm/functions/Function3;Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "Lkotlin/jvm/functions/Function3;", "create", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flowCollect", "collector", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelFlowTransformLatest extends ChannelFlowOperator {
    private final Function3 transform;

    public ChannelFlowTransformLatest(Function3 function30, Flow flow0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        super(flow0, coroutineContext0, v, bufferOverflow0);
        this.transform = function30;
    }

    public ChannelFlowTransformLatest(Function3 function30, Flow flow0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 4) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 16) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        this(function30, flow0, coroutineContext0, ((v1 & 8) == 0 ? v : -2), bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow create(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return new ChannelFlowTransformLatest(this.transform, this.flow, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    protected Object flowCollect(FlowCollector flowCollector0, Continuation continuation0) {
        Object object0 = CoroutineScopeKt.coroutineScope(new Function2(flowCollector0, null) {
            final FlowCollector $collector;
            private Object L$0;
            int label;

            {
                ChannelFlowTransformLatest.this = channelFlowTransformLatest0;
                this.$collector = flowCollector0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3 channelFlowTransformLatest$flowCollect$30 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3(ChannelFlowTransformLatest.this, this.$collector, continuation0);
                channelFlowTransformLatest$flowCollect$30.L$0 = object0;
                return channelFlowTransformLatest$flowCollect$30;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        ObjectRef ref$ObjectRef0 = new ObjectRef();
                        Flow flow0 = ChannelFlowTransformLatest.this.flow;
                        kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1 channelFlowTransformLatest$flowCollect$3$10 = new FlowCollector() {
                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public final Object emit(Object object0, Continuation continuation0) {
                                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1 channelFlowTransformLatest$flowCollect$3$10;
                                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1.emit.1 channelFlowTransformLatest$flowCollect$3$1$emit$10;
                                if(continuation0 instanceof kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1.emit.1) {
                                    channelFlowTransformLatest$flowCollect$3$1$emit$10 = (kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1.emit.1)continuation0;
                                    if((channelFlowTransformLatest$flowCollect$3$1$emit$10.label & 0x80000000) == 0) {
                                        channelFlowTransformLatest$flowCollect$3$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                            Object L$0;
                                            Object L$1;
                                            Object L$2;
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
                                        channelFlowTransformLatest$flowCollect$3$1$emit$10.label ^= 0x80000000;
                                    }
                                }
                                else {
                                    channelFlowTransformLatest$flowCollect$3$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                        Object L$0;
                                        Object L$1;
                                        Object L$2;
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
                                Object object1 = channelFlowTransformLatest$flowCollect$3$1$emit$10.result;
                                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(channelFlowTransformLatest$flowCollect$3$1$emit$10.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object1);
                                        Job job0 = (Job)coroutineScope0.element;
                                        if(job0 != null) {
                                            job0.cancel(new ChildCancelledException());
                                            channelFlowTransformLatest$flowCollect$3$1$emit$10.L$0 = this;
                                            channelFlowTransformLatest$flowCollect$3$1$emit$10.L$1 = object0;
                                            channelFlowTransformLatest$flowCollect$3$1$emit$10.L$2 = job0;
                                            channelFlowTransformLatest$flowCollect$3$1$emit$10.label = 1;
                                            if(job0.join(channelFlowTransformLatest$flowCollect$3$1$emit$10) == object2) {
                                                return object2;
                                            }
                                        }
                                        channelFlowTransformLatest$flowCollect$3$10 = this;
                                        break;
                                    }
                                    case 1: {
                                        Job job1 = (Job)channelFlowTransformLatest$flowCollect$3$1$emit$10.L$2;
                                        object0 = channelFlowTransformLatest$flowCollect$3$1$emit$10.L$1;
                                        channelFlowTransformLatest$flowCollect$3$10 = (kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1)channelFlowTransformLatest$flowCollect$3$1$emit$10.L$0;
                                        ResultKt.throwOnFailure(object1);
                                        break;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1.2 channelFlowTransformLatest$flowCollect$3$1$20 = new Function2(channelFlowTransformLatest$flowCollect$3$10.$collector, object0, null) {
                                    final FlowCollector $collector;
                                    final Object $value;
                                    int label;

                                    {
                                        ChannelFlowTransformLatest.this = channelFlowTransformLatest0;
                                        this.$collector = flowCollector0;
                                        this.$value = object0;
                                        super(2, continuation0);
                                    }

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation create(Object object0, Continuation continuation0) {
                                        return new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1.2(ChannelFlowTransformLatest.this, this.$collector, this.$value, continuation0);
                                    }

                                    @Override  // kotlin.jvm.functions.Function2
                                    public Object invoke(Object object0, Object object1) {
                                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                    }

                                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                        return ((kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.flowCollect.3.1.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object object0) {
                                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch(this.label) {
                                            case 0: {
                                                ResultKt.throwOnFailure(object0);
                                                this.label = 1;
                                                return ChannelFlowTransformLatest.this.transform.invoke(this.$collector, this.$value, this) == object1 ? object1 : Unit.INSTANCE;
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
                                };
                                channelFlowTransformLatest$flowCollect$3$10.$previousFlow.element = BuildersKt__Builders_commonKt.launch$default(channelFlowTransformLatest$flowCollect$3$10.$$this$coroutineScope, null, CoroutineStart.UNDISPATCHED, channelFlowTransformLatest$flowCollect$3$1$20, 1, null);
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return flow0.collect(channelFlowTransformLatest$flowCollect$3$10, this) == object1 ? object1 : Unit.INSTANCE;
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
        }, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }
}

