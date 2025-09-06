package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.ChannelSegment..ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.Segment;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001F\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J%\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00062\b\u0010\u0011\u001A\u0004\u0018\u00010\n2\b\u0010\u0012\u001A\u0004\u0018\u00010\nH\u0086\bJ\u0013\u0010\u0013\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001A\u00020\u0006H\u0086\bJ\u001D\u0010\u0014\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001A\u00020\u00062\b\u0010\u0012\u001A\u0004\u0018\u00010\nH\u0086\bJ\"\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0010\u001A\u00020\u00062\b\u0010\u0017\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001A\u00020\u001AH\u0016J\u001B\u0010\u001B\u001A\u00020\u00162\u0006\u0010\u0010\u001A\u00020\u00062\b\u0010\u0012\u001A\u0004\u0018\u00010\nH\u0086\bJ\b\u0010\u001C\u001A\u00020\u001DH\u0016R\u000E\u0010\b\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tR\u0014\u0010\u000B\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\u001E"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "Lkotlinx/coroutines/internal/Segment;", "id", "", "prev", "pointers", "", "(JLkotlinx/coroutines/sync/SemaphoreSegment;I)V", "acquirers", "Lkotlinx/atomicfu/AtomicArray;", "", "numberOfSlots", "getNumberOfSlots", "()I", "cas", "", "index", "expected", "value", "get", "getAndSet", "onCancellation", "", "cause", "", "context", "Lkotlin/coroutines/CoroutineContext;", "set", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class SemaphoreSegment extends Segment {
    private final AtomicReferenceArray acquirers;

    public SemaphoreSegment(long v, SemaphoreSegment semaphoreSegment0, int v1) {
        super(v, semaphoreSegment0, v1);
        this.acquirers = new AtomicReferenceArray(16);
    }

    public final boolean cas(int v, Object object0, Object object1) {
        return ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.getAcquirers(), v, object0, object1);
    }

    public final Object get(int v) {
        return this.getAcquirers().get(v);
    }

    public final AtomicReferenceArray getAcquirers() {
        return this.acquirers;
    }

    public final Object getAndSet(int v, Object object0) {
        return this.getAcquirers().getAndSet(v, object0);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlinx.coroutines.internal.Segment
    public int getNumberOfSlots() {
        return 16;
    }

    @Override  // kotlinx.coroutines.internal.Segment
    public void onCancellation(int v, Throwable throwable0, CoroutineContext coroutineContext0) {
        this.getAcquirers().set(v, SemaphoreKt.CANCELLED);
        this.onSlotCleaned();
    }

    public final void set(int v, Object object0) {
        this.getAcquirers().set(v, object0);
    }

    @Override
    public String toString() {
        return "SemaphoreSegment[id=" + this.id + ", hashCode=" + this.hashCode() + ']';
    }
}

