package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\t\u001A\u00020\nH\u0096\u0002J\b\u0010\u000E\u001A\u00020\u0003H\u0016R\u000E\u0010\b\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\r¨\u0006\u000F"}, d2 = {"Lkotlin/ranges/CharProgressionIterator;", "Lkotlin/collections/CharIterator;", "first", "", "last", "step", "", "(CCI)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextChar", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class CharProgressionIterator extends CharIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    public CharProgressionIterator(char c, char c1, int v) {
        this.step = v;
        this.finalElement = c1;
        boolean z = v <= 0 ? Intrinsics.compare(c, c1) >= 0 : Intrinsics.compare(c, c1) <= 0;
        this.hasNext = z;
        if(!z) {
            c = c1;
        }
        this.next = c;
    }

    public final int getStep() {
        return this.step;
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override  // kotlin.collections.CharIterator
    public char nextChar() {
        int v = this.next;
        if(v == this.finalElement) {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            this.hasNext = false;
            return (char)v;
        }
        this.next = this.step + v;
        return (char)v;
    }
}

