package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u001D\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u001F\u0010\u0010\u001A\u00020\u00112\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010\u0014J&\u0010\u0015\u001A\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u001CH\u0016R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00000\nX\u0096\u0005¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u0012\u0010\r\u001A\u00028\u0000X\u0096\u0005¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000F\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001D"}, d2 = {"Lkotlinx/coroutines/flow/ReadonlyStateFlow;", "T", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "flow", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlinx/coroutines/Job;)V", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "value", "getValue", "()Ljava/lang/Object;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ReadonlyStateFlow implements CancellableFlow, StateFlow, FusibleFlow {
    private final StateFlow $$delegate_0;
    private final Job job;

    public ReadonlyStateFlow(StateFlow stateFlow0, Job job0) {
        this.job = job0;
        this.$$delegate_0 = stateFlow0;
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        return this.$$delegate_0.collect(flowCollector0, continuation0);
    }

    @Override  // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow fuse(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return StateFlowKt.fuseStateFlow(this, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow
    public List getReplayCache() {
        return this.$$delegate_0.getReplayCache();
    }

    @Override  // kotlinx.coroutines.flow.StateFlow
    public Object getValue() {
        return this.$$delegate_0.getValue();
    }
}

