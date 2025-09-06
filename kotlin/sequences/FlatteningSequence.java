package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004BA\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0018\u0010\b\u001A\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u000F\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00020\tH\u0096\u0002R \u0010\b\u001A\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/sequences/FlatteningSequence;", "T", "R", "E", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "iterator", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class FlatteningSequence implements Sequence {
    private final Function1 iterator;
    private final Sequence sequence;
    private final Function1 transformer;

    public FlatteningSequence(Sequence sequence0, Function1 function10, Function1 function11) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        Intrinsics.checkNotNullParameter(function10, "transformer");
        Intrinsics.checkNotNullParameter(function11, "iterator");
        super();
        this.sequence = sequence0;
        this.transformer = function10;
        this.iterator = function11;
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private Iterator itemIterator;
            private final Iterator iterator;

            {
                this.iterator = flatteningSequence0.sequence.iterator();
            }

            private final boolean ensureItemIterator() {
                if(this.itemIterator != null && !this.itemIterator.hasNext()) {
                    this.itemIterator = null;
                }
                while(this.itemIterator == null) {
                    if(!this.iterator.hasNext()) {
                        return false;
                    }
                    Object object0 = this.iterator.next();
                    Iterator iterator0 = (Iterator)FlatteningSequence.this.iterator.invoke(FlatteningSequence.this.transformer.invoke(object0));
                    if(iterator0.hasNext()) {
                        this.itemIterator = iterator0;
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
                return true;
            }

            public final Iterator getItemIterator() {
                return this.itemIterator;
            }

            public final Iterator getIterator() {
                return this.iterator;
            }

            @Override
            public boolean hasNext() {
                return this.ensureItemIterator();
            }

            @Override
            public Object next() {
                if(!this.ensureItemIterator()) {
                    throw new NoSuchElementException();
                }
                Iterator iterator0 = this.itemIterator;
                Intrinsics.checkNotNull(iterator0);
                return iterator0.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final void setItemIterator(Iterator iterator0) {
                this.itemIterator = iterator0;
            }
        };
    }
}

