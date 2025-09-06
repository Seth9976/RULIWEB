package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0018\u0010\u0005\u001A\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\bJ\u000F\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0005\u001A\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/sequences/TransformingIndexedSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function2;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TransformingIndexedSequence implements Sequence {
    private final Sequence sequence;
    private final Function2 transformer;

    public TransformingIndexedSequence(Sequence sequence0, Function2 function20) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        Intrinsics.checkNotNullParameter(function20, "transformer");
        super();
        this.sequence = sequence0;
        this.transformer = function20;
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private int index;
            private final Iterator iterator;

            {
                this.iterator = transformingIndexedSequence0.sequence.iterator();
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
                Function2 function20 = TransformingIndexedSequence.this.transformer;
                int v = this.index;
                this.index = v + 1;
                if(v < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Object object0 = this.iterator.next();
                return function20.invoke(v, object0);
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

