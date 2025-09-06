package androidx.collection;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0016\u001A\u00020\u0012J\b\u0010\u0017\u001A\u00020\u0012H\u0002J\u0016\u0010\u0018\u001A\u00028\u00002\u0006\u0010\u0019\u001A\u00020\u0004H\u0086\u0002¢\u0006\u0002\u0010\u001AJ\u0006\u0010\u001B\u001A\u00020\u001CJ\u000B\u0010\u001D\u001A\u00028\u0000¢\u0006\u0002\u0010\fJ\u000B\u0010\u001E\u001A\u00028\u0000¢\u0006\u0002\u0010\fJ\u000E\u0010\u001F\u001A\u00020\u00122\u0006\u0010 \u001A\u00020\u0004J\u000E\u0010!\u001A\u00020\u00122\u0006\u0010 \u001A\u00020\u0004J\u0006\u0010\"\u001A\u00020\u0004R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\bX\u0082\u000E¢\u0006\u0004\n\u0002\u0010\tR\u0011\u0010\n\u001A\u00028\u00008F¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u000E\u001A\u00028\u00008F¢\u0006\u0006\u001A\u0004\b\u000F\u0010\fR\u000E\u0010\u0010\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/collection/CircularArray;", "E", "", "minCapacity", "", "(I)V", "capacityBitmask", "elements", "", "[Ljava/lang/Object;", "first", "getFirst", "()Ljava/lang/Object;", "head", "last", "getLast", "tail", "addFirst", "", "element", "(Ljava/lang/Object;)V", "addLast", "clear", "doubleCapacity", "get", "index", "(I)Ljava/lang/Object;", "isEmpty", "", "popFirst", "popLast", "removeFromEnd", "count", "removeFromStart", "size", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CircularArray {
    private int capacityBitmask;
    private Object[] elements;
    private int head;
    private int tail;

    public CircularArray() {
        this(0, 1, null);
    }

    public CircularArray(int v) {
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
        this.elements = new Object[v];
    }

    public CircularArray(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 8;
        }
        this(v);
    }

    public final void addFirst(Object object0) {
        int v = this.head - 1 & this.capacityBitmask;
        this.head = v;
        this.elements[v] = object0;
        if(v == this.tail) {
            this.doubleCapacity();
        }
    }

    public final void addLast(Object object0) {
        int v = this.tail;
        this.elements[v] = object0;
        int v1 = this.capacityBitmask & v + 1;
        this.tail = v1;
        if(v1 == this.head) {
            this.doubleCapacity();
        }
    }

    public final void clear() {
        this.removeFromStart(this.size());
    }

    private final void doubleCapacity() {
        Object[] arr_object = this.elements;
        int v = this.head;
        int v1 = arr_object.length - v;
        int v2 = arr_object.length << 1;
        if(v2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] arr_object1 = new Object[v2];
        ArraysKt.copyInto(arr_object, arr_object1, 0, v, arr_object.length);
        ArraysKt.copyInto(this.elements, arr_object1, v1, 0, this.head);
        this.elements = arr_object1;
        this.head = 0;
        this.tail = arr_object.length;
        this.capacityBitmask = v2 - 1;
    }

    public final Object get(int v) {
        if(v < 0 || v >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object object0 = this.elements[this.capacityBitmask & this.head + v];
        Intrinsics.checkNotNull(object0);
        return object0;
    }

    public final Object getFirst() {
        int v = this.head;
        if(v == this.tail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object object0 = this.elements[v];
        Intrinsics.checkNotNull(object0);
        return object0;
    }

    public final Object getLast() {
        int v = this.tail;
        if(this.head == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object object0 = this.elements[v - 1 & this.capacityBitmask];
        Intrinsics.checkNotNull(object0);
        return object0;
    }

    public final boolean isEmpty() {
        return this.head == this.tail;
    }

    public final Object popFirst() {
        int v = this.head;
        if(v == this.tail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object[] arr_object = this.elements;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        this.head = v + 1 & this.capacityBitmask;
        return object0;
    }

    public final Object popLast() {
        int v = this.tail;
        if(this.head == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v1 = this.capacityBitmask & v - 1;
        Object[] arr_object = this.elements;
        Object object0 = arr_object[v1];
        arr_object[v1] = null;
        this.tail = v1;
        return object0;
    }

    public final void removeFromEnd(int v) {
        if(v > 0) {
            if(v > this.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int v1 = this.tail;
            int v2 = v >= v1 ? 0 : v1 - v;
            for(int v3 = v2; v3 < v1; ++v3) {
                this.elements[v3] = null;
            }
            int v4 = this.tail - v2;
            int v5 = v - v4;
            this.tail -= v4;
            if(v5 > 0) {
                int v6 = this.elements.length;
                this.tail = v6;
                int v7 = v6 - v5;
                for(int v8 = v7; v8 < v6; ++v8) {
                    this.elements[v8] = null;
                }
                this.tail = v7;
            }
        }
    }

    public final void removeFromStart(int v) {
        if(v > 0) {
            if(v > this.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int v1 = this.elements.length;
            int v2 = this.head;
            if(v < v1 - v2) {
                v1 = v2 + v;
            }
            while(v2 < v1) {
                this.elements[v2] = null;
                ++v2;
            }
            int v3 = v1 - this.head;
            int v4 = v - v3;
            this.head = this.capacityBitmask & this.head + v3;
            if(v4 > 0) {
                for(int v5 = 0; v5 < v4; ++v5) {
                    this.elements[v5] = null;
                }
                this.head = v4;
            }
        }
    }

    public final int size() {
        return this.tail - this.head & this.capacityBitmask;
    }
}

