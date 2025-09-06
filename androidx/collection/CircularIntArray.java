package androidx.collection;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0003J\u000E\u0010\u0012\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0003J\u0006\u0010\u0013\u001A\u00020\u0010J\b\u0010\u0014\u001A\u00020\u0010H\u0002J\u0011\u0010\u0015\u001A\u00020\u00032\u0006\u0010\u0016\u001A\u00020\u0003H\u0086\u0002J\u0006\u0010\u0017\u001A\u00020\u0018J\u0006\u0010\u0019\u001A\u00020\u0003J\u0006\u0010\u001A\u001A\u00020\u0003J\u000E\u0010\u001B\u001A\u00020\u00102\u0006\u0010\u001C\u001A\u00020\u0003J\u000E\u0010\u001D\u001A\u00020\u00102\u0006\u0010\u001C\u001A\u00020\u0003J\u0006\u0010\u001E\u001A\u00020\u0003R\u000E\u0010\u0005\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001A\u00020\u00038F¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u000E\u0010\u000B\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001A\u00020\u00038F¢\u0006\u0006\u001A\u0004\b\r\u0010\nR\u000E\u0010\u000E\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Landroidx/collection/CircularIntArray;", "", "minCapacity", "", "(I)V", "capacityBitmask", "elements", "", "first", "getFirst", "()I", "head", "last", "getLast", "tail", "addFirst", "", "element", "addLast", "clear", "doubleCapacity", "get", "index", "isEmpty", "", "popFirst", "popLast", "removeFromEnd", "count", "removeFromStart", "size", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CircularIntArray {
    private int capacityBitmask;
    private int[] elements;
    private int head;
    private int tail;

    public CircularIntArray() {
        this(0, 1, null);
    }

    public CircularIntArray(int v) {
        if(v < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if(v > 0x40000000) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        if(Integer.bitCount(v) != 1) {
            v = Integer.highestOneBit(v - 1) << 1;
        }
        this.capacityBitmask = v - 1;
        this.elements = new int[v];
    }

    public CircularIntArray(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 8;
        }
        this(v);
    }

    public final void addFirst(int v) {
        int v1 = this.head - 1 & this.capacityBitmask;
        this.head = v1;
        this.elements[v1] = v;
        if(v1 == this.tail) {
            this.doubleCapacity();
        }
    }

    public final void addLast(int v) {
        int v1 = this.tail;
        this.elements[v1] = v;
        int v2 = this.capacityBitmask & v1 + 1;
        this.tail = v2;
        if(v2 == this.head) {
            this.doubleCapacity();
        }
    }

    public final void clear() {
        this.tail = this.head;
    }

    private final void doubleCapacity() {
        int[] arr_v = this.elements;
        int v = this.head;
        int v1 = arr_v.length - v;
        int v2 = arr_v.length << 1;
        if(v2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] arr_v1 = new int[v2];
        ArraysKt.copyInto(arr_v, arr_v1, 0, v, arr_v.length);
        ArraysKt.copyInto(this.elements, arr_v1, v1, 0, this.head);
        this.elements = arr_v1;
        this.head = 0;
        this.tail = arr_v.length;
        this.capacityBitmask = v2 - 1;
    }

    public final int get(int v) {
        if(v < 0 || v >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.elements[this.capacityBitmask & this.head + v];
    }

    public final int getFirst() {
        int v = this.head;
        if(v == this.tail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.elements[v];
    }

    public final int getLast() {
        int v = this.tail;
        if(this.head == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.elements[v - 1 & this.capacityBitmask];
    }

    public final boolean isEmpty() {
        return this.head == this.tail;
    }

    public final int popFirst() {
        int v = this.head;
        if(v == this.tail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.head = v + 1 & this.capacityBitmask;
        return this.elements[v];
    }

    public final int popLast() {
        int v = this.tail;
        if(this.head == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v1 = this.capacityBitmask & v - 1;
        this.tail = v1;
        return this.elements[v1];
    }

    public final void removeFromEnd(int v) {
        if(v <= 0) {
            return;
        }
        if(v > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.tail = this.capacityBitmask & this.tail - v;
    }

    public final void removeFromStart(int v) {
        if(v <= 0) {
            return;
        }
        if(v > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.head = this.capacityBitmask & this.head + v;
    }

    public final int size() {
        return this.tail - this.head & this.capacityBitmask;
    }
}

