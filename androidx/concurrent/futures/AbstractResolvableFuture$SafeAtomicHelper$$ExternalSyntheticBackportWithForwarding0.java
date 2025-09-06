package androidx.concurrent.futures;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public final class AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0 {
    public static boolean m(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Object object0, Object object1, Object object2) {
        do {
            if(atomicReferenceFieldUpdater0.compareAndSet(object0, object1, object2)) {
                return true;
            }
        }
        while(atomicReferenceFieldUpdater0.get(object0) == object1);
        return false;
    }
}

