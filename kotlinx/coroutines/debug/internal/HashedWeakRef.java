package kotlinx.coroutines.debug.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001D\u0012\u0006\u0010\u0003\u001A\u00028\u0000\u0012\u000E\u0010\u0004\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0010\u0010\u0007\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "T", "Ljava/lang/ref/WeakReference;", "ref", "queue", "Ljava/lang/ref/ReferenceQueue;", "(Ljava/lang/Object;Ljava/lang/ref/ReferenceQueue;)V", "hash", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class HashedWeakRef extends WeakReference {
    public final int hash;

    public HashedWeakRef(Object object0, ReferenceQueue referenceQueue0) {
        super(object0, referenceQueue0);
        this.hash = object0 == null ? 0 : object0.hashCode();
    }
}

