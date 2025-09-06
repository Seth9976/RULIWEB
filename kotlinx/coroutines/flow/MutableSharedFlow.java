package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0019\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\b\u0010\r\u001A\u00020\nH\'J\u0015\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u000B\u001A\u00028\u0000H&¢\u0006\u0002\u0010\u0010R\u0018\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/flow/MutableSharedFlow;", "T", "Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlinx/coroutines/flow/FlowCollector;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetReplayCache", "tryEmit", "", "(Ljava/lang/Object;)Z", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface MutableSharedFlow extends FlowCollector, SharedFlow {
    @Override  // kotlinx.coroutines.flow.FlowCollector
    Object emit(Object arg1, Continuation arg2);

    StateFlow getSubscriptionCount();

    void resetReplayCache();

    boolean tryEmit(Object arg1);
}

