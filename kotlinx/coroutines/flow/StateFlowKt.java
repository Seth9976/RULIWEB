package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001A\u001F\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001A\u0002H\u0005¢\u0006\u0002\u0010\u0007\u001A6\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00050\t\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0000\u001A2\u0010\u0011\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00042\u0012\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00050\u0013H\u0086\b¢\u0006\u0002\u0010\u0014\u001A-\u0010\u0015\u001A\u00020\u0016\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00042\u0012\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00050\u0013H\u0086\b\u001A2\u0010\u0017\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00042\u0012\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00050\u0013H\u0086\b¢\u0006\u0002\u0010\u0014\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"NONE", "Lkotlinx/coroutines/internal/Symbol;", "PENDING", "MutableStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "T", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;", "fuseStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/StateFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "getAndUpdate", "function", "Lkotlin/Function1;", "(Lkotlinx/coroutines/flow/MutableStateFlow;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "update", "", "updateAndGet", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class StateFlowKt {
    private static final Symbol NONE;
    private static final Symbol PENDING;

    static {
        StateFlowKt.NONE = new Symbol("NONE");
        StateFlowKt.PENDING = new Symbol("PENDING");
    }

    public static final MutableStateFlow MutableStateFlow(Object object0) {
        if(object0 == null) {
            object0 = NullSurrogateKt.NULL;
        }
        return new StateFlowImpl(object0);
    }

    public static final Flow fuseStateFlow(StateFlow stateFlow0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return (v >= 0 && v < 2 || v == -2) && bufferOverflow0 == BufferOverflow.DROP_OLDEST ? stateFlow0 : SharedFlowKt.fuseSharedFlow(stateFlow0, coroutineContext0, v, bufferOverflow0);
    }

    public static final Object getAndUpdate(MutableStateFlow mutableStateFlow0, Function1 function10) {
        Object object0;
        do {
            object0 = mutableStateFlow0.getValue();
        }
        while(!mutableStateFlow0.compareAndSet(object0, function10.invoke(object0)));
        return object0;
    }

    public static final void update(MutableStateFlow mutableStateFlow0, Function1 function10) {
        do {
            Object object0 = mutableStateFlow0.getValue();
        }
        while(!mutableStateFlow0.compareAndSet(object0, function10.invoke(object0)));
    }

    public static final Object updateAndGet(MutableStateFlow mutableStateFlow0, Function1 function10) {
        Object object1;
        do {
            Object object0 = mutableStateFlow0.getValue();
            object1 = function10.invoke(object0);
        }
        while(!mutableStateFlow0.compareAndSet(object0, object1));
        return object1;
    }
}

