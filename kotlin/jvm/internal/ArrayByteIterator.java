package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ByteIterator;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001A\u00020\bH\u0096\u0002J\b\u0010\t\u001A\u00020\nH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/jvm/internal/ArrayByteIterator;", "Lkotlin/collections/ByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextByte", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ArrayByteIterator extends ByteIterator {
    private final byte[] array;
    private int index;

    public ArrayByteIterator(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        super();
        this.array = arr_b;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override  // kotlin.collections.ByteIterator
    public byte nextByte() {
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

