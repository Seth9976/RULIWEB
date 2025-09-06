package kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\u000F\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R(\u0010\u0005\u001A\u001C\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000 \u0007*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/sequences/ConstrainedOnceSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/sequences/Sequence;)V", "sequenceRef", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class ConstrainedOnceSequence implements Sequence {
    private final AtomicReference sequenceRef;

    public ConstrainedOnceSequence(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        super();
        this.sequenceRef = new AtomicReference(sequence0);
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        Sequence sequence0 = (Sequence)this.sequenceRef.getAndSet(null);
        if(sequence0 == null) {
            throw new IllegalStateException("This sequence can be consumed only once.");
        }
        return sequence0.iterator();
    }
}

