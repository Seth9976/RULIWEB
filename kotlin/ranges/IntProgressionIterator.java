package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001A\u00020\tH\u0096\u0002J\b\u0010\r\u001A\u00020\u0003H\u0016R\u000E\u0010\u0007\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u000E"}, d2 = {"Lkotlin/ranges/IntProgressionIterator;", "Lkotlin/collections/IntIterator;", "first", "", "last", "step", "(III)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextInt", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class IntProgressionIterator extends IntIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    public IntProgressionIterator(int v, int v1, int v2) {
        this.step = v2;
        this.finalElement = v1;
        boolean z = v2 <= 0 ? v >= v1 : v <= v1;
        this.hasNext = z;
        if(!z) {
            v = v1;
        }
        this.next = v;
    }

    public final int getStep() {
        return this.step;
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override  // kotlin.collections.IntIterator
    public int nextInt() {
        int v = this.next;
        if(v == this.finalElement) {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            this.hasNext = false;
            return v;
        }
        this.next = this.step + v;
        return v;
    }
}

