package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u000B\u001A\u00020\fH\u0096\u0002J\u000E\u0010\r\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000ER\u0019\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\n\n\u0002\u0010\b\u001A\u0004\b\u0006\u0010\u0007R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lkotlin/jvm/internal/ArrayIterator;", "T", "", "array", "", "([Ljava/lang/Object;)V", "getArray", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "index", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ArrayIterator implements Iterator, KMappedMarker {
    private final Object[] array;
    private int index;

    public ArrayIterator(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        super();
        this.array = arr_object;
    }

    public final Object[] getArray() {
        return this.array;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override
    public Object next() {
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

