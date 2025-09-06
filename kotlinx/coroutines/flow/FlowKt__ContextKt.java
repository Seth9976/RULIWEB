package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow.DefaultImpls;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A\u0015\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0002¢\u0006\u0002\b\u0004\u001A(\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001A\u00020\tH\u0007\u001A0\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001A\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\u000B\u001A\u001C\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006\u001A\u001C\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006\u001A$\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u0002\u001A\u00020\u0003¨\u0006\u000F"}, d2 = {"checkFlowContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "checkFlowContext$FlowKt__ContextKt", "buffer", "Lkotlinx/coroutines/flow/Flow;", "T", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "cancellable", "conflate", "flowOn", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__ContextKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.4.0, binary compatibility with earlier versions")
    public static final Flow buffer(Flow flow0, int v) {
        return FlowKt__ContextKt.buffer$default(flow0, v, null, 2, null);
    }

    public static final Flow buffer(Flow flow0, int v, BufferOverflow bufferOverflow0) {
        int v1;
        if(v < 0 && (v != -2 && v != -1)) {
            throw new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was " + v).toString());
        }
        if(v == -1 && bufferOverflow0 != BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        }
        if(v == -1) {
            bufferOverflow0 = BufferOverflow.DROP_OLDEST;
            v1 = 0;
        }
        else {
            v1 = v;
        }
        return flow0 instanceof FusibleFlow ? DefaultImpls.fuse$default(((FusibleFlow)flow0), null, v1, bufferOverflow0, 1, null) : new ChannelFlowOperatorImpl(flow0, null, v1, bufferOverflow0, 2, null);
    }

    public static Flow buffer$default(Flow flow0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = -2;
        }
        return FlowKt__ContextKt.buffer(flow0, v);
    }

    public static Flow buffer$default(Flow flow0, int v, BufferOverflow bufferOverflow0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = -2;
        }
        if((v1 & 2) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        return FlowKt.buffer(flow0, v, bufferOverflow0);
    }

    public static final Flow cancellable(Flow flow0) {
        return flow0 instanceof CancellableFlow ? flow0 : new CancellableFlowImpl(flow0);
    }

    private static final void checkFlowContext$FlowKt__ContextKt(CoroutineContext coroutineContext0) {
        if(coroutineContext0.get(Job.Key) != null) {
            throw new IllegalArgumentException(("Flow context cannot contain job in it. Had " + coroutineContext0).toString());
        }
    }

    public static final Flow conflate(Flow flow0) {
        return FlowKt__ContextKt.buffer$default(flow0, -1, null, 2, null);
    }

    public static final Flow flowOn(Flow flow0, CoroutineContext coroutineContext0) {
        FlowKt__ContextKt.checkFlowContext$FlowKt__ContextKt(coroutineContext0);
        if(Intrinsics.areEqual(coroutineContext0, EmptyCoroutineContext.INSTANCE)) {
            return flow0;
        }
        return flow0 instanceof FusibleFlow ? DefaultImpls.fuse$default(((FusibleFlow)flow0), coroutineContext0, 0, null, 6, null) : new ChannelFlowOperatorImpl(flow0, coroutineContext0, 0, null, 12, null);
    }
}

