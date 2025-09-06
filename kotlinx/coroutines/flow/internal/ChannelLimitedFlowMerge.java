package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B7\u0012\u0012\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u0012\b\b\u0002\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\u001F\u0010\r\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J&\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0014J\u0016\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010\u000F\u001A\u00020\u0015H\u0016R\u001A\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelLimitedFlowMerge;", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flows", "", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Ljava/lang/Iterable;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collectTo", "", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "produceImpl", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelLimitedFlowMerge extends ChannelFlow {
    private final Iterable flows;

    public ChannelLimitedFlowMerge(Iterable iterable0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        super(coroutineContext0, v, bufferOverflow0);
        this.flows = iterable0;
    }

    public ChannelLimitedFlowMerge(Iterable iterable0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 4) != 0) {
            v = -2;
        }
        if((v1 & 8) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        this(iterable0, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(ProducerScope producerScope0, Continuation continuation0) {
        SendingCollector sendingCollector0 = new SendingCollector(producerScope0);
        for(Object object0: this.flows) {
            BuildersKt__Builders_commonKt.launch$default(producerScope0, null, null, new Function2(sendingCollector0, null) {
                final SendingCollector $collector;
                final Flow $flow;
                int label;

                {
                    this.$flow = flow0;
                    this.$collector = sendingCollector0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    return new kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge.collectTo.2.1(this.$flow, this.$collector, continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                }

                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                    return ((kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge.collectTo.2.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            this.label = 1;
                            return this.$flow.collect(this.$collector, this) == object1 ? object1 : Unit.INSTANCE;
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
            }, 3, null);
        }
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow create(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return new ChannelLimitedFlowMerge(this.flows, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public ReceiveChannel produceImpl(CoroutineScope coroutineScope0) {
        return ProduceKt.produce(coroutineScope0, this.context, this.capacity, this.getCollectToFun$kotlinx_coroutines_core());
    }
}

