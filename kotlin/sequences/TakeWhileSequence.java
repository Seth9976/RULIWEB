package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\'\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000F\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R\u001A\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/sequences/TakeWhileSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TakeWhileSequence implements Sequence {
    private final Function1 predicate;
    private final Sequence sequence;

    public TakeWhileSequence(Sequence sequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "sequence");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        super();
        this.sequence = sequence0;
        this.predicate = function10;
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private final Iterator iterator;
            private Object nextItem;
            private int nextState;

            {
                this.iterator = takeWhileSequence0.sequence.iterator();
                this.nextState = -1;
            }

            private final void calcNext() {
                if(this.iterator.hasNext()) {
                    Object object0 = this.iterator.next();
                    if(((Boolean)TakeWhileSequence.this.predicate.invoke(object0)).booleanValue()) {
                        this.nextState = 1;
                        this.nextItem = object0;
                        return;
                    }
                }
                this.nextState = 0;
            }

            public final Iterator getIterator() {
                return this.iterator;
            }

            public final Object getNextItem() {
                return this.nextItem;
            }

            public final int getNextState() {
                return this.nextState;
            }

            @Override
            public boolean hasNext() {
                if(this.nextState == -1) {
                    this.calcNext();
                }
                return this.nextState == 1;
            }

            @Override
            public Object next() {
                if(this.nextState == -1) {
                    this.calcNext();
                }
                if(this.nextState == 0) {
                    throw new NoSuchElementException();
                }
                Object object0 = this.nextItem;
                this.nextItem = null;
                this.nextState = -1;
                return object0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final void setNextItem(Object object0) {
                this.nextItem = object0;
            }

            public final void setNextState(int v) {
                this.nextState = v;
            }
        };
    }
}

