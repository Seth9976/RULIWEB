package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u000E\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0014\u0010\u0006\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007¢\u0006\u0002\u0010\bJ\u000F\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u0016\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\u0006\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/sequences/GeneratorSequence;", "T", "", "Lkotlin/sequences/Sequence;", "getInitialValue", "Lkotlin/Function0;", "getNextValue", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class GeneratorSequence implements Sequence {
    private final Function0 getInitialValue;
    private final Function1 getNextValue;

    public GeneratorSequence(Function0 function00, Function1 function10) {
        Intrinsics.checkNotNullParameter(function00, "getInitialValue");
        Intrinsics.checkNotNullParameter(function10, "getNextValue");
        super();
        this.getInitialValue = function00;
        this.getNextValue = function10;
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new Object() {
            private Object nextItem;
            private int nextState;

            {
                this.nextState = -2;
            }

            private final void calcNext() {
                Object object0;
                if(this.nextState == -2) {
                    object0 = GeneratorSequence.this.getInitialValue.invoke();
                }
                else {
                    Object object1 = this.nextItem;
                    Intrinsics.checkNotNull(object1);
                    object0 = GeneratorSequence.this.getNextValue.invoke(object1);
                }
                this.nextItem = object0;
                this.nextState = object0 == null ? 0 : 1;
            }

            public final Object getNextItem() {
                return this.nextItem;
            }

            public final int getNextState() {
                return this.nextState;
            }

            @Override
            public boolean hasNext() {
                if(this.nextState < 0) {
                    this.calcNext();
                }
                return this.nextState == 1;
            }

            @Override
            public Object next() {
                if(this.nextState < 0) {
                    this.calcNext();
                }
                if(this.nextState == 0) {
                    throw new NoSuchElementException();
                }
                Object object0 = this.nextItem;
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
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

