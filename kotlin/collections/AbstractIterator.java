package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001A\u00020\tH$J\b\u0010\n\u001A\u00020\tH\u0004J\t\u0010\u000B\u001A\u00020\fH\u0096\u0002J\u000E\u0010\r\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000EJ\u0015\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0010\u001A\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001A\u00020\fH\u0002R\u0012\u0010\u0004\u001A\u0004\u0018\u00018\u0000X\u0082\u000E¢\u0006\u0004\n\u0002\u0010\u0005R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlin/collections/AbstractIterator;", "T", "", "()V", "nextValue", "Ljava/lang/Object;", "state", "Lkotlin/collections/State;", "computeNext", "", "done", "hasNext", "", "next", "()Ljava/lang/Object;", "setNext", "value", "(Ljava/lang/Object;)V", "tryToComputeNext", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractIterator implements Iterator, KMappedMarker {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[State.values().length];
            try {
                arr_v[State.Done.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.Ready.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private Object nextValue;
    private State state;

    public AbstractIterator() {
        this.state = State.NotReady;
    }

    protected abstract void computeNext();

    protected final void done() {
        this.state = State.Done;
    }

    @Override
    public boolean hasNext() {
        if(this.state != State.Failed) {
            switch(WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
                case 1: {
                    return false;
                }
                case 2: {
                    return true;
                }
                default: {
                    return this.tryToComputeNext();
                }
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    @Override
    public Object next() {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NotReady;
        return this.nextValue;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected final void setNext(Object object0) {
        this.nextValue = object0;
        this.state = State.Ready;
    }

    private final boolean tryToComputeNext() {
        this.state = State.Failed;
        this.computeNext();
        return this.state == State.Ready;
    }
}

