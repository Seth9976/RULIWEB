package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A0\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\u00062\b\b\u0002\u0010\b\u001A\u00020\t\u001A6\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u00040\u000B\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\tH\u0000\u001A#\u0010\u0010\u001A\u0004\u0018\u00010\u0011*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00122\u0006\u0010\u0013\u001A\u00020\u0014H\u0002¢\u0006\u0002\u0010\u0015\u001A+\u0010\u0016\u001A\u00020\u0017*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00122\u0006\u0010\u0013\u001A\u00020\u00142\b\u0010\u0018\u001A\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010\u0019\"\u0010\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"NO_VALUE", "Lkotlinx/coroutines/internal/Symbol;", "MutableSharedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "T", "replay", "", "extraBufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "fuseSharedFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharedFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "getBufferAt", "", "", "index", "", "([Ljava/lang/Object;J)Ljava/lang/Object;", "setBufferAt", "", "item", "([Ljava/lang/Object;JLjava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SharedFlowKt {
    public static final Symbol NO_VALUE;

    static {
        SharedFlowKt.NO_VALUE = new Symbol("NO_VALUE");
    }

    public static final MutableSharedFlow MutableSharedFlow(int v, int v1, BufferOverflow bufferOverflow0) {
        if(v < 0) {
            throw new IllegalArgumentException(("replay cannot be negative, but was " + v).toString());
        }
        if(v1 < 0) {
            throw new IllegalArgumentException(("extraBufferCapacity cannot be negative, but was " + v1).toString());
        }
        if(v <= 0 && v1 <= 0 && bufferOverflow0 != BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + bufferOverflow0).toString());
        }
        return new SharedFlowImpl(v, (v1 + v >= 0 ? v1 + v : 0x7FFFFFFF), bufferOverflow0);
    }

    public static MutableSharedFlow MutableSharedFlow$default(int v, int v1, BufferOverflow bufferOverflow0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = 0;
        }
        if((v2 & 4) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        return SharedFlowKt.MutableSharedFlow(v, v1, bufferOverflow0);
    }

    public static final Flow fuseSharedFlow(SharedFlow sharedFlow0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return (v == -3 || v == 0) && bufferOverflow0 == BufferOverflow.SUSPEND ? sharedFlow0 : new ChannelFlowOperatorImpl(sharedFlow0, coroutineContext0, v, bufferOverflow0);
    }

    private static final Object getBufferAt(Object[] arr_object, long v) {
        return arr_object[arr_object.length - 1 & ((int)v)];
    }

    private static final void setBufferAt(Object[] arr_object, long v, Object object0) {
        arr_object[arr_object.length - 1 & ((int)v)] = object0;
    }
}

