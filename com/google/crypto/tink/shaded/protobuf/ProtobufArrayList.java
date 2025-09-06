package com.google.crypto.tink.shaded.protobuf;

import java.util.Arrays;
import java.util.RandomAccess;

final class ProtobufArrayList extends AbstractProtobufList implements RandomAccess {
    private static final ProtobufArrayList EMPTY_LIST;
    private Object[] array;
    private int size;

    static {
        ProtobufArrayList protobufArrayList0 = new ProtobufArrayList(new Object[0], 0);
        ProtobufArrayList.EMPTY_LIST = protobufArrayList0;
        protobufArrayList0.makeImmutable();
    }

    ProtobufArrayList() {
        this(new Object[10], 0);
    }

    private ProtobufArrayList(Object[] arr_object, int v) {
        this.array = arr_object;
        this.size = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public void add(int v, Object object0) {
        this.ensureIsMutable();
        if(v >= 0) {
            int v1 = this.size;
            if(v <= v1) {
                Object[] arr_object = this.array;
                if(v1 < arr_object.length) {
                    System.arraycopy(arr_object, v, arr_object, v + 1, v1 - v);
                }
                else {
                    Object[] arr_object1 = ProtobufArrayList.createArray(v1 * 3 / 2 + 1);
                    System.arraycopy(this.array, 0, arr_object1, 0, v);
                    System.arraycopy(this.array, v, arr_object1, v + 1, this.size - v);
                    this.array = arr_object1;
                }
                this.array[v] = object0;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(v));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean add(Object object0) {
        this.ensureIsMutable();
        int v = this.size;
        Object[] arr_object = this.array;
        if(v == arr_object.length) {
            this.array = Arrays.copyOf(arr_object, v * 3 / 2 + 1);
        }
        int v1 = this.size;
        this.size = v1 + 1;
        this.array[v1] = object0;
        ++this.modCount;
        return true;
    }

    private static Object[] createArray(int v) {
        return new Object[v];
    }

    public static ProtobufArrayList emptyList() {
        return ProtobufArrayList.EMPTY_LIST;
    }

    private void ensureIndexInRange(int v) {
        if(v < 0 || v >= this.size) {
            throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(v));
        }
    }

    @Override
    public Object get(int v) {
        this.ensureIndexInRange(v);
        return this.array[v];
    }

    private String makeOutOfBoundsExceptionMessage(int v) {
        return "Index:" + v + ", Size:" + this.size;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$ProtobufList
    public ProtobufList mutableCopyWithCapacity(int v) {
        return this.mutableCopyWithCapacity(v);
    }

    public ProtobufArrayList mutableCopyWithCapacity(int v) {
        if(v < this.size) {
            throw new IllegalArgumentException();
        }
        return new ProtobufArrayList(Arrays.copyOf(this.array, v), this.size);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object remove(int v) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        Object[] arr_object = this.array;
        Object object0 = arr_object[v];
        int v1 = this.size;
        if(v < v1 - 1) {
            System.arraycopy(arr_object, v + 1, arr_object, v, v1 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return object0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object set(int v, Object object0) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        Object[] arr_object = this.array;
        Object object1 = arr_object[v];
        arr_object[v] = object0;
        ++this.modCount;
        return object1;
    }

    @Override
    public int size() {
        return this.size;
    }
}

