package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.SendingCollector;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\b\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\b\b\u0002\u0010\u000B\u001A\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010\u0010\u001A\u00020\u0011H\u0014J\u001F\u0010\u0012\u001A\u00020\u00132\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001F\u0010\u0017\u001A\u00020\u00132\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001AJ&\u0010\u001B\u001A\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0014J\u000E\u0010\u001C\u001A\b\u0012\u0004\u0012\u00028\u00000\u001DH\u0016J\b\u0010\u001E\u001A\u00020\u0013H\u0002J\u0016\u0010\u001F\u001A\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0018\u001A\u00020 H\u0016R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\t\u0010\u000E\u001A\u00020\u000FX\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lkotlinx/coroutines/flow/ChannelAsFlow;", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "channel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "consume", "", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "additionalToStringProps", "", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "dropChannelOperators", "Lkotlinx/coroutines/flow/Flow;", "markConsumed", "produceImpl", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ChannelAsFlow extends ChannelFlow {
    private final ReceiveChannel channel;
    private final boolean consume;
    @Volatile
    private volatile int consumed;
    private static final AtomicIntegerFieldUpdater consumed$FU;

    static {
        ChannelAsFlow.consumed$FU = AtomicIntegerFieldUpdater.newUpdater(ChannelAsFlow.class, "consumed");
    }

    public ChannelAsFlow(ReceiveChannel receiveChannel0, boolean z, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        super(coroutineContext0, v, bufferOverflow0);
        this.channel = receiveChannel0;
        this.consume = z;
        this.consumed = 0;
    }

    public ChannelAsFlow(ReceiveChannel receiveChannel0, boolean z, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 4) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v1 & 16) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        this(receiveChannel0, z, coroutineContext0, ((v1 & 8) == 0 ? v : -3), bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected String additionalToStringProps() {
        return "channel=" + this.channel;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        if(this.capacity == -3) {
            this.markConsumed();
            Object object0 = FlowKt__ChannelsKt.access$emitAllImpl$FlowKt__ChannelsKt(flowCollector0, this.channel, this.consume, continuation0);
            return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
        }
        Object object1 = super.collect(flowCollector0, continuation0);
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(ProducerScope producerScope0, Continuation continuation0) {
        Object object0 = FlowKt__ChannelsKt.access$emitAllImpl$FlowKt__ChannelsKt(new SendingCollector(producerScope0), this.channel, this.consume, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow create(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return new ChannelAsFlow(this.channel, this.consume, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public Flow dropChannelOperators() {
        return new ChannelAsFlow(this.channel, this.consume, null, 0, null, 28, null);
    }

    private final void markConsumed() {
        if(this.consume && ChannelAsFlow.consumed$FU.getAndSet(this, 1) != 0) {
            throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once");
        }
    }

    @Override  // kotlinx.coroutines.flow.internal.ChannelFlow
    public ReceiveChannel produceImpl(CoroutineScope coroutineScope0) {
        this.markConsumed();
        return this.capacity == -3 ? this.channel : super.produceImpl(coroutineScope0);
    }
}

