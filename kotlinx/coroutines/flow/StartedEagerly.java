package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001A\u00020\nH\u0016¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/flow/StartedEagerly;", "Lkotlinx/coroutines/flow/SharingStarted;", "()V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StartedEagerly implements SharingStarted {
    @Override  // kotlinx.coroutines.flow.SharingStarted
    public Flow command(StateFlow stateFlow0) {
        return FlowKt.flowOf(SharingCommand.START);
    }

    @Override
    public String toString() {
        return "SharingStarted.Eagerly";
    }
}

