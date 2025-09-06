package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.Volatile;
import kotlinx.coroutines.NotCompleted;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u001F\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\r\u0010\u0012\u001A\u00020\rH\u0000¢\u0006\u0002\b\u0013J\"\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\b2\b\u0010\u0017\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001A\u00020\u001AH&J\u0006\u0010\u001B\u001A\u00020\u0015J\r\u0010\u001C\u001A\u00020\rH\u0000¢\u0006\u0002\b\u001DR\t\u0010\n\u001A\u00020\u000BX\u0082\u0004R\u0010\u0010\u0004\u001A\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\u000ER\u0012\u0010\u000F\u001A\u00020\bX¦\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011¨\u0006\u001E"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "Lkotlinx/coroutines/NotCompleted;", "id", "", "prev", "pointers", "", "(JLkotlinx/coroutines/internal/Segment;I)V", "cleanedAndPointers", "Lkotlinx/atomicfu/AtomicInt;", "isRemoved", "", "()Z", "numberOfSlots", "getNumberOfSlots", "()I", "decPointers", "decPointers$kotlinx_coroutines_core", "onCancellation", "", "index", "cause", "", "context", "Lkotlin/coroutines/CoroutineContext;", "onSlotCleaned", "tryIncPointers", "tryIncPointers$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class Segment extends ConcurrentLinkedListNode implements NotCompleted {
    @Volatile
    private volatile int cleanedAndPointers;
    private static final AtomicIntegerFieldUpdater cleanedAndPointers$FU;
    public final long id;

    static {
        Segment.cleanedAndPointers$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");
    }

    public Segment(long v, Segment segment0, int v1) {
        super(segment0);
        this.id = v;
        this.cleanedAndPointers = v1 << 16;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return Segment.cleanedAndPointers$FU.addAndGet(this, 0xFFFF0000) == this.getNumberOfSlots() && !this.isTail();
    }

    public abstract int getNumberOfSlots();

    @Override  // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean isRemoved() {
        return Segment.cleanedAndPointers$FU.get(this) == this.getNumberOfSlots() && !this.isTail();
    }

    public abstract void onCancellation(int arg1, Throwable arg2, CoroutineContext arg3);

    public final void onSlotCleaned() {
        if(Segment.cleanedAndPointers$FU.incrementAndGet(this) == this.getNumberOfSlots()) {
            this.remove();
        }
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = Segment.cleanedAndPointers$FU;
        do {
            int v = atomicIntegerFieldUpdater0.get(this);
            if(v == this.getNumberOfSlots() && !this.isTail()) {
                return false;
            }
        }
        while(!atomicIntegerFieldUpdater0.compareAndSet(this, v, v + 0x10000));
        return true;
    }
}

