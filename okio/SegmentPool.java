package okio;

import androidx.lifecycle.LifecycleKt..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000EH\u0002J\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0006H\u0007J\b\u0010\u0014\u001A\u00020\u0006H\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u000B\u0010\tR\u001E\u0010\f\u001A\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000E0\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000F¨\u0006\u0015"}, d2 = {"Lokio/SegmentPool;", "", "()V", "HASH_BUCKET_COUNT", "", "LOCK", "Lokio/Segment;", "MAX_SIZE", "getMAX_SIZE", "()I", "byteCount", "getByteCount", "hashBuckets", "", "Ljava/util/concurrent/atomic/AtomicReference;", "[Ljava/util/concurrent/atomic/AtomicReference;", "firstRef", "recycle", "", "segment", "take", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SegmentPool {
    private static final int HASH_BUCKET_COUNT;
    public static final SegmentPool INSTANCE;
    private static final Segment LOCK;
    private static final int MAX_SIZE;
    private static final AtomicReference[] hashBuckets;

    static {
        SegmentPool.INSTANCE = new SegmentPool();
        SegmentPool.MAX_SIZE = 0x10000;
        SegmentPool.LOCK = new Segment(new byte[0], 0, 0, false, false);
        int v1 = Integer.highestOneBit(Runtime.getRuntime().availableProcessors() * 2 - 1);
        SegmentPool.HASH_BUCKET_COUNT = v1;
        AtomicReference[] arr_atomicReference = new AtomicReference[v1];
        for(int v = 0; v < v1; ++v) {
            arr_atomicReference[v] = new AtomicReference();
        }
        SegmentPool.hashBuckets = arr_atomicReference;
    }

    private final AtomicReference firstRef() {
        return SegmentPool.hashBuckets[((int)(Thread.currentThread().getId() & ((long)SegmentPool.HASH_BUCKET_COUNT) - 1L))];
    }

    public final int getByteCount() {
        Segment segment0 = (Segment)this.firstRef().get();
        return segment0 == null ? 0 : segment0.limit;
    }

    public final int getMAX_SIZE() {
        return SegmentPool.MAX_SIZE;
    }

    @JvmStatic
    public static final void recycle(Segment segment0) {
        Intrinsics.checkNotNullParameter(segment0, "segment");
        if(segment0.next != null || segment0.prev != null) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if(!segment0.shared) {
            AtomicReference atomicReference0 = SegmentPool.INSTANCE.firstRef();
            Segment segment1 = (Segment)atomicReference0.get();
            if(segment1 != SegmentPool.LOCK) {
                int v = segment1 == null ? 0 : segment1.limit;
                if(v < SegmentPool.MAX_SIZE) {
                    segment0.next = segment1;
                    segment0.pos = 0;
                    segment0.limit = v + 0x2000;
                    if(!LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(atomicReference0, segment1, segment0)) {
                        segment0.next = null;
                    }
                }
            }
        }
    }

    @JvmStatic
    public static final Segment take() {
        AtomicReference atomicReference0 = SegmentPool.INSTANCE.firstRef();
        Segment segment0 = (Segment)atomicReference0.getAndSet(SegmentPool.LOCK);
        if(segment0 == SegmentPool.LOCK) {
            return new Segment();
        }
        if(segment0 == null) {
            atomicReference0.set(null);
            return new Segment();
        }
        atomicReference0.set(segment0.next);
        segment0.next = null;
        segment0.limit = 0;
        return segment0;
    }
}

