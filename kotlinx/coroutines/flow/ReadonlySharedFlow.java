package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u001D\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u001F\u0010\r\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010\u0011J&\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0019H\u0016R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00000\nX\u0096\u0005¢\u0006\u0006\u001A\u0004\b\u000B\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001A"}, d2 = {"Lkotlinx/coroutines/flow/ReadonlySharedFlow;", "T", "Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "flow", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlinx/coroutines/Job;)V", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ReadonlySharedFlow implements CancellableFlow, SharedFlow, FusibleFlow {
    private final SharedFlow $$delegate_0;
    private final Job job;

    public ReadonlySharedFlow(SharedFlow sharedFlow0, Job job0) {
        this.job = job0;
        this.$$delegate_0 = sharedFlow0;
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        return this.$$delegate_0.collect(flowCollector0, continuation0);
    }

    @Override  // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow fuse(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return SharedFlowKt.fuseSharedFlow(this, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow
    public List getReplayCache() {
        return this.$$delegate_0.getReplayCache();
    }
}

