package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010(\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u000F\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001D\u0012\u000E\u0010\b\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0006\u0010\u000B\u001A\u00020\u0006¢\u0006\u0002\u0010\fJ\u0013\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00028\u0000¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0018\u001A\u00020\u0006J\u0016\u0010\u0019\u001A\u00028\u00002\u0006\u0010\u001A\u001A\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u001BJ\u0006\u0010\u001C\u001A\u00020\u001DJ\u000F\u0010\u001E\u001A\b\u0012\u0004\u0012\u00028\u00000\u001FH\u0096\u0002J\u000E\u0010 \u001A\u00020\u00142\u0006\u0010!\u001A\u00020\u0006J\u0015\u0010\"\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0014¢\u0006\u0002\u0010#J\'\u0010\"\u001A\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0001\u0010\u00012\f\u0010$\u001A\b\u0012\u0004\u0012\u0002H\u00010\tH\u0014¢\u0006\u0002\u0010%J\u0015\u0010&\u001A\u00020\u0006*\u00020\u00062\u0006\u0010!\u001A\u00020\u0006H\u0082\bR\u0018\u0010\b\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001E\u0010\u000F\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u0006@RX\u0096\u000E¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u000E\u0010\u0012\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\'"}, d2 = {"Lkotlin/collections/RingBuffer;", "T", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "capacity", "", "(I)V", "buffer", "", "", "filledSize", "([Ljava/lang/Object;I)V", "[Ljava/lang/Object;", "<set-?>", "size", "getSize", "()I", "startIndex", "add", "", "element", "(Ljava/lang/Object;)V", "expanded", "maxCapacity", "get", "index", "(I)Ljava/lang/Object;", "isFull", "", "iterator", "", "removeFirst", "n", "toArray", "()[Ljava/lang/Object;", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "forward", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class RingBuffer extends AbstractList implements RandomAccess {
    private final Object[] buffer;
    private final int capacity;
    private int size;
    private int startIndex;

    public RingBuffer(int v) {
        this(new Object[v], 0);
    }

    public RingBuffer(Object[] arr_object, int v) {
        Intrinsics.checkNotNullParameter(arr_object, "buffer");
        super();
        this.buffer = arr_object;
        if(v < 0) {
            throw new IllegalArgumentException(("ring buffer filled size should not be negative but it is " + v).toString());
        }
        if(v > arr_object.length) {
            throw new IllegalArgumentException(("ring buffer filled size: " + v + " cannot be larger than the buffer size: " + arr_object.length).toString());
        }
        this.capacity = arr_object.length;
        this.size = v;
    }

    public final void add(Object object0) {
        if(this.isFull()) {
            throw new IllegalStateException("ring buffer is full");
        }
        this.buffer[(this.startIndex + this.size()) % this.capacity] = object0;
        this.size = this.size() + 1;
    }

    public final RingBuffer expanded(int v) {
        int v1 = RangesKt.coerceAtMost(this.capacity + (this.capacity >> 1) + 1, v);
        if(this.startIndex == 0) {
            Object[] arr_object = Arrays.copyOf(this.buffer, v1);
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
            return new RingBuffer(arr_object, this.size());
        }
        return new RingBuffer(this.toArray(new Object[v1]), this.size());
    }

    private final int forward(int v, int v1) {
        return (v + v1) % this.capacity;
    }

    @Override  // kotlin.collections.AbstractList
    public Object get(int v) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this.size());
        return this.buffer[(this.startIndex + v) % this.capacity];
    }

    @Override  // kotlin.collections.AbstractList
    public int getSize() {
        return this.size;
    }

    public final boolean isFull() {
        return this.size() == this.capacity;
    }

    @Override  // kotlin.collections.AbstractList
    public Iterator iterator() {
        return new AbstractIterator() {
            private int count;
            private int index;

            {
                this.count = ringBuffer0.size();
                this.index = ringBuffer0.startIndex;
            }

            @Override  // kotlin.collections.AbstractIterator
            protected void computeNext() {
                if(this.count == 0) {
                    this.done();
                    return;
                }
                this.setNext(RingBuffer.this.buffer[this.index]);
                this.index = (this.index + 1) % RingBuffer.this.capacity;
                --this.count;
            }
        };
    }

    public final void removeFirst(int v) {
        if(v < 0) {
            throw new IllegalArgumentException(("n shouldn\'t be negative but it is " + v).toString());
        }
        if(v > this.size()) {
            throw new IllegalArgumentException(("n shouldn\'t be greater than the buffer size: n = " + v + ", size = " + this.size()).toString());
        }
        if(v > 0) {
            int v1 = this.startIndex;
            int v2 = (v1 + v) % this.capacity;
            if(v1 > v2) {
                ArraysKt.fill(this.buffer, null, v1, this.capacity);
                ArraysKt.fill(this.buffer, null, 0, v2);
            }
            else {
                ArraysKt.fill(this.buffer, null, v1, v2);
            }
            this.startIndex = v2;
            this.size = this.size() - v;
        }
    }

    @Override  // kotlin.collections.AbstractCollection
    public Object[] toArray() {
        return this.toArray(new Object[this.size()]);
    }

    @Override  // kotlin.collections.AbstractCollection
    public Object[] toArray(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        if(arr_object.length < this.size()) {
            arr_object = Arrays.copyOf(arr_object, this.size());
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
        }
        int v = this.size();
        int v1 = this.startIndex;
        int v3 = 0;
        while(v3 < v && v1 < this.capacity) {
            arr_object[v3] = this.buffer[v1];
            ++v3;
            ++v1;
        }
        for(int v2 = 0; v3 < v; ++v2) {
            arr_object[v3] = this.buffer[v2];
            ++v3;
        }
        if(arr_object.length > this.size()) {
            arr_object[this.size()] = null;
        }
        return arr_object;
    }
}

