package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B\'\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J3\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\t0\u0003\"\u0004\b\u0002\u0010\t2\u0018\u0010\n\u001A\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000B0\u0006H\u0000¢\u0006\u0002\b\fJ\u000F\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00010\u000BH\u0096\u0002R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/TransformingSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "flatten", "E", "iterator", "", "flatten$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TransformingSequence implements Sequence {
    private final Sequence sequence;
    private final Function1 transformer;

    public TransformingSequence(Sequence sequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        Intrinsics.checkNotNullParameter(function10, "transformer");
        super();
        this.sequence = sequence0;
        this.transformer = function10;
    }

    public final Sequence flatten$kotlin_stdlib(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "iterator");
        return new FlatteningSequence(this.sequence, this.transformer, function10);
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private final Iterator iterator;

            {
                this.iterator = transformingSequence0.sequence.iterator();
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
                Function1 function10 = TransformingSequence.this.transformer;
                Object object0 = this.iterator.next();
                return function10.invoke(object0);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }
}

