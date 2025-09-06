package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\t\u001A\u00020\n2\n\u0010\u000B\u001A\u0006\u0012\u0002\b\u00030\u0002H\u0016J\'\u0010\f\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\r2\n\u0010\u000B\u001A\u0006\u0012\u0002\b\u00030\u0002H\u0016¢\u0006\u0002\u0010\u000ER\u001A\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001A\u00020\b8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowSlot;", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "Lkotlinx/coroutines/flow/SharedFlowImpl;", "()V", "cont", "Lkotlin/coroutines/Continuation;", "", "index", "", "allocateLocked", "", "flow", "freeLocked", "", "(Lkotlinx/coroutines/flow/SharedFlowImpl;)[Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SharedFlowSlot extends AbstractSharedFlowSlot {
    public Continuation cont;
    public long index;

    public SharedFlowSlot() {
        this.index = -1L;
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(Object object0) {
        return this.allocateLocked(((SharedFlowImpl)object0));
    }

    public boolean allocateLocked(SharedFlowImpl sharedFlowImpl0) {
        if(this.index >= 0L) {
            return false;
        }
        this.index = sharedFlowImpl0.updateNewCollectorIndexLocked$kotlinx_coroutines_core();
        return true;
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public Continuation[] freeLocked(Object object0) {
        return this.freeLocked(((SharedFlowImpl)object0));
    }

    public Continuation[] freeLocked(SharedFlowImpl sharedFlowImpl0) {
        long v = this.index;
        this.index = -1L;
        this.cont = null;
        return sharedFlowImpl0.updateCollectorIndexLocked$kotlinx_coroutines_core(v);
    }
}

