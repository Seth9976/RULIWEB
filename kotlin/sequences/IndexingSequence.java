package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0007H\u0096\u0002R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/sequences/IndexingSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/collections/IndexedValue;", "sequence", "(Lkotlin/sequences/Sequence;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class IndexingSequence implements Sequence {
    private final Sequence sequence;

    public IndexingSequence(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        super();
        this.sequence = sequence0;
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private int index;
            private final Iterator iterator;

            {
                this.iterator = indexingSequence0.sequence.iterator();
            }

            public final int getIndex() {
                return this.index;
            }

            public final Iterator getIterator() {
                return this.iterator;
            }

            @Override
            public boolean hasNext() {
                return this.iterator.hasNext();
            }

            @Override
            public Object next() {
                return this.next();
            }

            public IndexedValue next() {
                int v = this.index;
                this.index = v + 1;
                if(v < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Object object0 = this.iterator.next();
                return new IndexedValue(v, object0);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }
}

