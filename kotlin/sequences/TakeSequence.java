package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001B\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001A\u00020\u0006H\u0016J\u000F\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BH\u0096\u0002J\u0016\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001A\u00020\u0006H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/TakeSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TakeSequence implements DropTakeSequence, Sequence {
    private final int count;
    private final Sequence sequence;

    public TakeSequence(Sequence sequence0, int v) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        super();
        this.sequence = sequence0;
        this.count = v;
        if(v < 0) {
            throw new IllegalArgumentException(("count must be non-negative, but was " + v + '.').toString());
        }
    }

    @Override  // kotlin.sequences.DropTakeSequence
    public Sequence drop(int v) {
        return v >= this.count ? SequencesKt.emptySequence() : new SubSequence(this.sequence, v, this.count);
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private final Iterator iterator;
            private int left;

            {
                this.left = takeSequence0.count;
                this.iterator = takeSequence0.sequence.iterator();
            }

            public final Iterator getIterator() {
                return this.iterator;
            }

            public final int getLeft() {
                return this.left;
            }

            @Override
            public boolean hasNext() {
                return this.left > 0 && this.iterator.hasNext();
            }

            @Override
            public Object next() {
                int v = this.left;
                if(v == 0) {
                    throw new NoSuchElementException();
                }
                this.left = v - 1;
                return this.iterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final void setLeft(int v) {
                this.left = v;
            }
        };
    }

    @Override  // kotlin.sequences.DropTakeSequence
    public Sequence take(int v) {
        return v < this.count ? new TakeSequence(this.sequence, v) : this;
    }
}

