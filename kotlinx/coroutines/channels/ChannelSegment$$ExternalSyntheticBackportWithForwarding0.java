package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ChannelSegment..ExternalSyntheticBackportWithForwarding0 {
    public static boolean m(AtomicReferenceArray atomicReferenceArray0, int v, Object object0, Object object1) {
        do {
            if(atomicReferenceArray0.compareAndSet(v, object0, object1)) {
                return true;
            }
        }
        while(atomicReferenceArray0.get(v) == object0);
        return false;
    }
}

