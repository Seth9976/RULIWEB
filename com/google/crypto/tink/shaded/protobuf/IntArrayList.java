package com.google.crypto.tink.shaded.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class IntArrayList extends AbstractProtobufList implements IntList, PrimitiveNonBoxingCollection, RandomAccess {
    private static final IntArrayList EMPTY_LIST;
    private int[] array;
    private int size;

    static {
        IntArrayList intArrayList0 = new IntArrayList(new int[0], 0);
        IntArrayList.EMPTY_LIST = intArrayList0;
        intArrayList0.makeImmutable();
    }

    IntArrayList() {
        this(new int[10], 0);
    }

    private IntArrayList(int[] arr_v, int v) {
        this.array = arr_v;
        this.size = v;
    }

    public void add(int v, Integer integer0) {
        this.addInt(v, ((int)integer0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public void add(int v, Object object0) {
        this.add(v, ((Integer)object0));
    }

    public boolean add(Integer integer0) {
        this.addInt(((int)integer0));
        return true;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean add(Object object0) {
        return this.add(((Integer)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean addAll(Collection collection0) {
        this.ensureIsMutable();
        Internal.checkNotNull(collection0);
        if(!(collection0 instanceof IntArrayList)) {
            return super.addAll(collection0);
        }
        int v = ((IntArrayList)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        int[] arr_v = this.array;
        if(v2 > arr_v.length) {
            this.array = Arrays.copyOf(arr_v, v2);
        }
        System.arraycopy(((IntArrayList)collection0).array, 0, this.array, this.size, ((IntArrayList)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    private void addInt(int v, int v1) {
        this.ensureIsMutable();
        if(v >= 0) {
            int v2 = this.size;
            if(v <= v2) {
                int[] arr_v = this.array;
                if(v2 < arr_v.length) {
                    System.arraycopy(arr_v, v, arr_v, v + 1, v2 - v);
                }
                else {
                    int[] arr_v1 = new int[v2 * 3 / 2 + 1];
                    System.arraycopy(arr_v, 0, arr_v1, 0, v);
                    System.arraycopy(this.array, v, arr_v1, v + 1, this.size - v);
                    this.array = arr_v1;
                }
                this.array[v] = v1;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(v));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$IntList
    public void addInt(int v) {
        this.ensureIsMutable();
        int v1 = this.size;
        int[] arr_v = this.array;
        if(v1 == arr_v.length) {
            int[] arr_v1 = new int[v1 * 3 / 2 + 1];
            System.arraycopy(arr_v, 0, arr_v1, 0, v1);
            this.array = arr_v1;
        }
        int v2 = this.size;
        this.size = v2 + 1;
        this.array[v2] = v;
    }

    @Override
    public boolean contains(Object object0) {
        return this.indexOf(object0) != -1;
    }

    public static IntArrayList emptyList() {
        return IntArrayList.EMPTY_LIST;
    }

    private void ensureIndexInRange(int v) {
        if(v < 0 || v >= this.size) {
            throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(v));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof IntArrayList)) {
            return super.equals(object0);
        }
        if(this.size != ((IntArrayList)object0).size) {
            return false;
        }
        int[] arr_v = ((IntArrayList)object0).array;
        for(int v = 0; v < this.size; ++v) {
            if(this.array[v] != arr_v[v]) {
                return false;
            }
        }
        return true;
    }

    public Integer get(int v) {
        return this.getInt(v);
    }

    @Override
    public Object get(int v) {
        return this.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$IntList
    public int getInt(int v) {
        this.ensureIndexInRange(v);
        return this.array[v];
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + this.array[v1];
        }
        return v;
    }

    @Override
    public int indexOf(Object object0) {
        if(!(object0 instanceof Integer)) {
            return -1;
        }
        int v = (int)(((Integer)object0));
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            if(this.array[v2] == v) {
                return v2;
            }
        }
        return -1;
    }

    private String makeOutOfBoundsExceptionMessage(int v) {
        return "Index:" + v + ", Size:" + this.size;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$IntList
    public IntList mutableCopyWithCapacity(int v) {
        if(v < this.size) {
            throw new IllegalArgumentException();
        }
        return new IntArrayList(Arrays.copyOf(this.array, v), this.size);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$ProtobufList
    public ProtobufList mutableCopyWithCapacity(int v) {
        return this.mutableCopyWithCapacity(v);
    }

    public Integer remove(int v) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        int[] arr_v = this.array;
        int v1 = arr_v[v];
        int v2 = this.size;
        if(v < v2 - 1) {
            System.arraycopy(arr_v, v + 1, arr_v, v, v2 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return v1;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object remove(int v) {
        return this.remove(v);
    }

    @Override
    protected void removeRange(int v, int v1) {
        this.ensureIsMutable();
        if(v1 < v) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.array, v1, this.array, v, this.size - v1);
        this.size -= v1 - v;
        ++this.modCount;
    }

    public Integer set(int v, Integer integer0) {
        return this.setInt(v, ((int)integer0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object set(int v, Object object0) {
        return this.set(v, ((Integer)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$IntList
    public int setInt(int v, int v1) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        int[] arr_v = this.array;
        int v2 = arr_v[v];
        arr_v[v] = v1;
        return v2;
    }

    @Override
    public int size() {
        return this.size;
    }
}

