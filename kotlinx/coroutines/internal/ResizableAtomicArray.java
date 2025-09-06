package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001A\u00020\u0004J\u0018\u0010\t\u001A\u0004\u0018\u00018\u00002\u0006\u0010\n\u001A\u00020\u0004H\u0086\u0002¢\u0006\u0002\u0010\u000BJ\u001D\u0010\f\u001A\u00020\r2\u0006\u0010\n\u001A\u00020\u00042\b\u0010\u000E\u001A\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u000FR\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/internal/ResizableAtomicArray;", "T", "", "initialLength", "", "(I)V", "array", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "currentLength", "get", "index", "(I)Ljava/lang/Object;", "setSynchronized", "", "value", "(ILjava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ResizableAtomicArray {
    private volatile AtomicReferenceArray array;

    public ResizableAtomicArray(int v) {
        this.array = new AtomicReferenceArray(v);
    }

    public final int currentLength() {
        return this.array.length();
    }

    public final Object get(int v) {
        AtomicReferenceArray atomicReferenceArray0 = this.array;
        return v >= atomicReferenceArray0.length() ? null : atomicReferenceArray0.get(v);
    }

    public final void setSynchronized(int v, Object object0) {
        AtomicReferenceArray atomicReferenceArray0 = this.array;
        int v1 = atomicReferenceArray0.length();
        if(v < v1) {
            atomicReferenceArray0.set(v, object0);
            return;
        }
        AtomicReferenceArray atomicReferenceArray1 = new AtomicReferenceArray(RangesKt.coerceAtLeast(v + 1, v1 * 2));
        for(int v2 = 0; v2 < v1; ++v2) {
            atomicReferenceArray1.set(v2, atomicReferenceArray0.get(v2));
        }
        atomicReferenceArray1.set(v, object0);
        this.array = atomicReferenceArray1;
    }
}

