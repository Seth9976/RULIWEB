package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u00000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A[\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u0002H\u00022\b\b\u0002\u0010\u0006\u001A\u00020\u00072\"\u0010\b\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\tH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A\u001E\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00010\r\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u000EH\u0000\u001A&\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0010\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00102\u0006\u0010\u0011\u001A\u00020\u0004H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"withContextUndispatched", "T", "V", "newContext", "Lkotlin/coroutines/CoroutineContext;", "value", "countOrElement", "", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asChannelFlow", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "Lkotlinx/coroutines/flow/Flow;", "withUndispatchedContextCollector", "Lkotlinx/coroutines/flow/FlowCollector;", "emitContext", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelFlowKt {
    public static final ChannelFlow asChannelFlow(Flow flow0) {
        ChannelFlow channelFlow0 = flow0 instanceof ChannelFlow ? ((ChannelFlow)flow0) : null;
        return channelFlow0 == null ? new ChannelFlowOperatorImpl(flow0, null, 0, null, 14, null) : channelFlow0;
    }

    public static final Object withContextUndispatched(CoroutineContext coroutineContext0, Object object0, Object object1, Function2 function20, Continuation continuation0) {
        Object object3;
        Object object2 = ThreadContextKt.updateThreadContext(coroutineContext0, object1);
        try {
            Continuation continuation1 = new StackFrameContinuation(continuation0, coroutineContext0);
            object3 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, continuation1);
        }
        finally {
            ThreadContextKt.restoreThreadContext(coroutineContext0, object2);
        }
        if(object3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object3;
    }

    public static Object withContextUndispatched$default(CoroutineContext coroutineContext0, Object object0, Object object1, Function2 function20, Continuation continuation0, int v, Object object2) {
        if((v & 4) != 0) {
            object1 = ThreadContextKt.threadContextElements(coroutineContext0);
        }
        return ChannelFlowKt.withContextUndispatched(coroutineContext0, object0, object1, function20, continuation0);
    }

    private static final FlowCollector withUndispatchedContextCollector(FlowCollector flowCollector0, CoroutineContext coroutineContext0) {
        return (flowCollector0 instanceof SendingCollector ? true : flowCollector0 instanceof NopCollector) ? flowCollector0 : new UndispatchedContextCollector(flowCollector0, coroutineContext0);
    }
}

