package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B+\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\u001F\u0010\r\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00010\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001F\u0010\u0012\u001A\u00020\u000E2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\'\u0010\u0016\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00010\u00102\u0006\u0010\u0017\u001A\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001F\u0010\u0019\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00010\u0010H¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u0016\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u00058\u0004X\u0085\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001C"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "S", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectWithContextUndispatched", "newContext", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flowCollect", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ChannelFlowOperator extends ChannelFlow {
    protected final Flow flow;

    public ChannelFlowOperator(Flow flow0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        super(coroutineContext0, v, bufferOverflow0);
        this.flow = flow0;
    }

    public static final Object access$collectWithContextUndispatched(ChannelFlowOperator channelFlowOperator0, FlowCollector flowCollector0, CoroutineContext coroutineContext0, Continuation continuation0) {
        return channelFlowOperator0.collectWithContextUndispatched(flowCollector0, coroutineContext0, continuation0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        return ChannelFlowOperator.collect$suspendImpl(this, flowCollector0, continuation0);
    }

    static Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator0, FlowCollector flowCollector0, Continuation continuation0) {
        if(channelFlowOperator0.capacity == -3) {
            CoroutineContext coroutineContext0 = continuation0.getContext();
            CoroutineContext coroutineContext1 = CoroutineContextKt.newCoroutineContext(coroutineContext0, channelFlowOperator0.context);
            if(Intrinsics.areEqual(coroutineContext1, coroutineContext0)) {
                Object object0 = channelFlowOperator0.flowCollect(flowCollector0, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
            if(Intrinsics.areEqual(coroutineContext1.get(ContinuationInterceptor.Key), coroutineContext0.get(ContinuationInterceptor.Key))) {
                Object object1 = channelFlowOperator0.collectWithContextUndispatched(flowCollector0, coroutineContext1, continuation0);
                return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
            }
        }
        Object object2 = channelFlowOperator0.super.collect(flowCollector0, continuation0);
        return object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object2 : Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(ProducerScope producerScope0, Continuation continuation0) {
        return ChannelFlowOperator.collectTo$suspendImpl(this, producerScope0, continuation0);
    }

    static Object collectTo$suspendImpl(ChannelFlowOperator channelFlowOperator0, ProducerScope producerScope0, Continuation continuation0) {
        Object object0 = channelFlowOperator0.flowCollect(new SendingCollector(producerScope0), continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private final Object collectWithContextUndispatched(FlowCollector flowCollector0, CoroutineContext coroutineContext0, Continuation continuation0) {
        Object object0 = ChannelFlowKt.withContextUndispatched$default(coroutineContext0, ChannelFlowKt.withUndispatchedContextCollector(flowCollector0, continuation0.getContext()), null, new Function2(null) {
            Object L$0;
            int label;

            {
                ChannelFlowOperator.this = channelFlowOperator0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.ChannelFlowOperator.collectWithContextUndispatched.2 channelFlowOperator$collectWithContextUndispatched$20 = new kotlinx.coroutines.flow.internal.ChannelFlowOperator.collectWithContextUndispatched.2(ChannelFlowOperator.this, continuation0);
                channelFlowOperator$collectWithContextUndispatched$20.L$0 = object0;
                return channelFlowOperator$collectWithContextUndispatched$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.ChannelFlowOperator.collectWithContextUndispatched.2)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return ChannelFlowOperator.this.flowCollect(((FlowCollector)this.L$0), this) == object1 ? object1 : Unit.INSTANCE;
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
        }, continuation0, 4, null);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    protected abstract Object flowCollect(FlowCollector arg1, Continuation arg2);

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return this.flow + " -> " + super.toString();
    }
}

