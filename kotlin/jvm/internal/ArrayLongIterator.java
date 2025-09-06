package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001A\u00020\bH\u0096\u0002J\b\u0010\t\u001A\u00020\nH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/jvm/internal/ArrayLongIterator;", "Lkotlin/collections/LongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextLong", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ArrayLongIterator extends LongIterator {
    private final long[] array;
    private int index;

    public ArrayLongIterator(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        super();
        this.array = arr_v;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override  // kotlin.collections.LongIterator
    public long nextLong() {
        try {
            int v = this.index;
            this.index = v + 1;
            return this.array[v];
        }
        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
            --this.index;
            throw new NoSuchElementException(arrayIndexOutOfBoundsException0.getMessage());
        }
    }
}

