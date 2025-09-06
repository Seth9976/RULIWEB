package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000B\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0002B5\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u000E\u0010\u0005\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000E\u0010\u0006\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\nJ)\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\t2\b\u0010\u0017\u001A\u0004\u0018\u00010\u00102\b\u0010\u0018\u001A\u0004\u0018\u00010\u0010H\u0000\u00A2\u0006\u0002\b\u0019J\u0015\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u0016\u001A\u00020\tH\u0000\u00A2\u0006\u0002\b\u001CJ!\u0010\u001D\u001A\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001A\u00020\t2\b\u0010\u001E\u001A\u0004\u0018\u00010\u0010H\u0000\u00A2\u0006\u0002\b\u001FJ\u0017\u0010 \u001A\u00028\u00002\u0006\u0010\u0016\u001A\u00020\tH\u0000\u00A2\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001A\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001A\u00020\tH\u0000\u00A2\u0006\u0002\b$J\"\u0010%\u001A\u00020\u001B2\u0006\u0010\u0016\u001A\u00020\t2\b\u0010&\u001A\u0004\u0018\u00010\'2\u0006\u0010(\u001A\u00020)H\u0016J\u0016\u0010*\u001A\u00020\u001B2\u0006\u0010\u0016\u001A\u00020\t2\u0006\u0010+\u001A\u00020\u0015J\u0017\u0010,\u001A\u00028\u00002\u0006\u0010\u0016\u001A\u00020\tH\u0000\u00A2\u0006\u0004\b-\u0010\"J\u001A\u0010.\u001A\u00020\u001B2\u0006\u0010\u0016\u001A\u00020\t2\b\u0010/\u001A\u0004\u0018\u00010\u0010H\u0002J\u001F\u00100\u001A\u00020\u001B2\u0006\u0010\u0016\u001A\u00020\t2\b\u0010/\u001A\u0004\u0018\u00010\u0010H\u0000\u00A2\u0006\u0002\b1J\u001F\u00102\u001A\u00020\u001B2\u0006\u0010\u0016\u001A\u00020\t2\u0006\u00103\u001A\u00028\u0000H\u0000\u00A2\u0006\u0004\b4\u00105R\u0016\u0010\u000B\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u00078F\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\rR\u0011\u0010\u000E\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000FX\u0082\u0004R\u0014\u0010\u0011\u001A\u00020\t8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013\u00A8\u00066"}, d2 = {"Lkotlinx/coroutines/channels/ChannelSegment;", "E", "Lkotlinx/coroutines/internal/Segment;", "id", "", "prev", "channel", "Lkotlinx/coroutines/channels/BufferedChannel;", "pointers", "", "(JLkotlinx/coroutines/channels/ChannelSegment;Lkotlinx/coroutines/channels/BufferedChannel;I)V", "_channel", "getChannel", "()Lkotlinx/coroutines/channels/BufferedChannel;", "data", "Lkotlinx/atomicfu/AtomicArray;", "", "numberOfSlots", "getNumberOfSlots", "()I", "casState", "", "index", "from", "to", "casState$kotlinx_coroutines_core", "cleanElement", "", "cleanElement$kotlinx_coroutines_core", "getAndSetState", "update", "getAndSetState$kotlinx_coroutines_core", "getElement", "getElement$kotlinx_coroutines_core", "(I)Ljava/lang/Object;", "getState", "getState$kotlinx_coroutines_core", "onCancellation", "cause", "", "context", "Lkotlin/coroutines/CoroutineContext;", "onCancelledRequest", "receiver", "retrieveElement", "retrieveElement$kotlinx_coroutines_core", "setElementLazy", "value", "setState", "setState$kotlinx_coroutines_core", "storeElement", "element", "storeElement$kotlinx_coroutines_core", "(ILjava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelSegment extends Segment {
    private final BufferedChannel _channel;
    private final AtomicReferenceArray data;

    public ChannelSegment(long v, ChannelSegment channelSegment0, BufferedChannel bufferedChannel0, int v1) {
        super(v, channelSegment0, v1);
        this._channel = bufferedChannel0;
        this.data = new AtomicReferenceArray(BufferedChannelKt.SEGMENT_SIZE * 2);
    }

    public final boolean casState$kotlinx_coroutines_core(int v, Object object0, Object object1) {
        return ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.data, v * 2 + 1, object0, object1);
    }

    public final void cleanElement$kotlinx_coroutines_core(int v) {
        this.setElementLazy(v, null);
    }

    public final Object getAndSetState$kotlinx_coroutines_core(int v, Object object0) {
        return this.data.getAndSet(v * 2 + 1, object0);
    }

    public final BufferedChannel getChannel() {
        Intrinsics.checkNotNull(this._channel);
        return this._channel;
    }

    public final Object getElement$kotlinx_coroutines_core(int v) {
        return this.data.get(v * 2);
    }

    @Override  // kotlinx.coroutines.internal.Segment
    public int getNumberOfSlots() {
        return BufferedChannelKt.SEGMENT_SIZE;
    }

    public final Object getState$kotlinx_coroutines_core(int v) {
        return this.data.get(v * 2 + 1);
    }

    @Override  // kotlinx.coroutines.internal.Segment
    public void onCancellation(int v, Throwable throwable0, CoroutineContext coroutineContext0) {
        Object object1;
        int v1 = v < BufferedChannelKt.SEGMENT_SIZE ? 0 : 1;
        if(v1 != 0) {
            v -= BufferedChannelKt.SEGMENT_SIZE;
        }
        Object object0 = this.getElement$kotlinx_coroutines_core(v);
        while(true) {
            do {
                object1 = this.getState$kotlinx_coroutines_core(v);
                if(object1 instanceof Waiter || object1 instanceof WaiterEB) {
                    goto label_16;
                }
                if(object1 == BufferedChannelKt.INTERRUPTED_SEND || object1 == BufferedChannelKt.INTERRUPTED_RCV) {
                    goto label_10;
                }
            }
            while(object1 == BufferedChannelKt.RESUMING_BY_EB || object1 == BufferedChannelKt.RESUMING_BY_RCV);
            if(object1 != BufferedChannelKt.DONE_RCV && object1 != BufferedChannelKt.BUFFERED && object1 != BufferedChannelKt.getCHANNEL_CLOSED()) {
                throw new IllegalStateException(("unexpected state: " + object1).toString());
            }
            break;
        label_10:
            this.cleanElement$kotlinx_coroutines_core(v);
            if(v1 == 0) {
                break;
            }
            Function1 function10 = this.getChannel().onUndeliveredElement;
            if(function10 == null) {
                break;
            }
            OnUndeliveredElementKt.callUndeliveredElement(function10, object0, coroutineContext0);
            return;
        label_16:
            if(this.casState$kotlinx_coroutines_core(v, object1, (v1 == 0 ? BufferedChannelKt.INTERRUPTED_RCV : BufferedChannelKt.INTERRUPTED_SEND))) {
                this.cleanElement$kotlinx_coroutines_core(v);
                this.onCancelledRequest(v, ((boolean)(v1 ^ 1)));
                if(v1 == 0) {
                    break;
                }
                Function1 function11 = this.getChannel().onUndeliveredElement;
                if(function11 == null) {
                    break;
                }
                OnUndeliveredElementKt.callUndeliveredElement(function11, object0, coroutineContext0);
                return;
            }
        }
    }

    public final void onCancelledRequest(int v, boolean z) {
        if(z) {
            this.getChannel().waitExpandBufferCompletion$kotlinx_coroutines_core(this.id * ((long)BufferedChannelKt.SEGMENT_SIZE) + ((long)v));
        }
        this.onSlotCleaned();
    }

    public final Object retrieveElement$kotlinx_coroutines_core(int v) {
        Object object0 = this.getElement$kotlinx_coroutines_core(v);
        this.cleanElement$kotlinx_coroutines_core(v);
        return object0;
    }

    private final void setElementLazy(int v, Object object0) {
        this.data.lazySet(v * 2, object0);
    }

    public final void setState$kotlinx_coroutines_core(int v, Object object0) {
        this.data.set(v * 2 + 1, object0);
    }

    public final void storeElement$kotlinx_coroutines_core(int v, Object object0) {
        this.setElementLazy(v, object0);
    }
}

