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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B?\u0012\u0012\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\b\u0012\b\b\u0002\u0010\t\u001A\u00020\u0006\u0012\b\b\u0002\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\b\u0010\r\u001A\u00020\u000EH\u0014J\u001F\u0010\u000F\u001A\u00020\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J&\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000BH\u0014J\u0016\u0010\u0015\u001A\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0011\u001A\u00020\u0017H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowMerge;", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "concurrency", "", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/flow/Flow;ILkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "additionalToStringProps", "", "collectTo", "", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelFlowMerge extends ChannelFlow {
    private final int concurrency;
    private final Flow flow;

    public ChannelFlowMerge(Flow flow0, int v, CoroutineContext coroutineContext0, int v1, BufferOverflow bufferOverflow0) {
        super(coroutineContext0, v1, bufferOverflow0);
        this.flow = flow0;
        this.concurrency = v;
    }

    public ChannelFlowMerge(Flow flow0, int v, CoroutineContext coroutineContext0, int v1, BufferOverflow bufferOverflow0, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v2 & 4) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v2 & 16) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        this(flow0, v, coroutineContext0, ((v2 & 8) == 0 ? v1 : -2), bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected String additionalToStringProps() {
        return "concurrency=" + this.concurrency;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(ProducerScope producerScope0, Continuation continuation0) {
        Semaphore semaphore0 = SemaphoreKt.Semaphore$default(this.concurrency, 0, 2, null);
        SendingCollector sendingCollector0 = new SendingCollector(producerScope0);
        FlowCollector flowCollector0 = new FlowCollector() {
            @Override  // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object object0, Continuation continuation0) {
                return this.emit(((Flow)object0), continuation0);
            }

            public final Object emit(Flow flow0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2 channelFlowMerge$collectTo$20;
                kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2.emit.1 channelFlowMerge$collectTo$2$emit$10;
                if(continuation0 instanceof kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2.emit.1) {
                    channelFlowMerge$collectTo$2$emit$10 = (kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2.emit.1)continuation0;
                    if((channelFlowMerge$collectTo$2$emit$10.label & 0x80000000) == 0) {
                        channelFlowMerge$collectTo$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                        channelFlowMerge$collectTo$2$emit$10.label ^= 0x80000000;
                    }
                }
                else {
                    channelFlowMerge$collectTo$2$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
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
                Object object0 = channelFlowMerge$collectTo$2$emit$10.result;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(channelFlowMerge$collectTo$2$emit$10.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Job job0 = semaphore0;
                        if(job0 != null) {
                            JobKt.ensureActive(job0);
                        }
                        channelFlowMerge$collectTo$2$emit$10.L$0 = this;
                        channelFlowMerge$collectTo$2$emit$10.L$1 = flow0;
                        channelFlowMerge$collectTo$2$emit$10.label = 1;
                        if(producerScope0.acquire(channelFlowMerge$collectTo$2$emit$10) == object1) {
                            return object1;
                        }
                        channelFlowMerge$collectTo$20 = this;
                        break;
                    }
                    case 1: {
                        flow0 = (Flow)channelFlowMerge$collectTo$2$emit$10.L$1;
                        channelFlowMerge$collectTo$20 = (kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2)channelFlowMerge$collectTo$2$emit$10.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2.1 channelFlowMerge$collectTo$2$10 = new Function2(channelFlowMerge$collectTo$20.$collector, channelFlowMerge$collectTo$20.$semaphore, null) {
                    final SendingCollector $collector;
                    final Flow $inner;
                    final Semaphore $semaphore;
                    int label;

                    {
                        this.$inner = flow0;
                        this.$collector = sendingCollector0;
                        this.$semaphore = semaphore0;
                        super(2, continuation0);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object object0, Continuation continuation0) {
                        return new kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2.1(this.$inner, this.$collector, this.$semaphore, continuation0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                    }

                    public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                        return ((kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object0);
                                try {
                                    this.label = 1;
                                    if(this.$inner.collect(this.$collector, this) == object1) {
                                        return object1;
                                    label_7:
                                        ResultKt.throwOnFailure(object0);
                                    }
                                    break;
                                }
                                catch(Throwable throwable0) {
                                    this.$semaphore.release();
                                    throw throwable0;
                                }
                            }
                            case 1: {
                                goto label_7;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                        this.$semaphore.release();
                        return Unit.INSTANCE;
                    }
                };
                BuildersKt__Builders_commonKt.launch$default(channelFlowMerge$collectTo$20.$scope, null, null, channelFlowMerge$collectTo$2$10, 3, null);
                return Unit.INSTANCE;
            }
        };
        Object object0 = this.flow.collect(flowCollector0, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow create(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return new ChannelFlowMerge(this.flow, this.concurrency, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public ReceiveChannel produceImpl(CoroutineScope coroutineScope0) {
        return ProduceKt.produce(coroutineScope0, this.context, this.capacity, this.getCollectToFun$kotlinx_coroutines_core());
    }
}

