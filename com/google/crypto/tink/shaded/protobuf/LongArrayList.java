package com.google.crypto.tink.shaded.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class LongArrayList extends AbstractProtobufList implements LongList, PrimitiveNonBoxingCollection, RandomAccess {
    private static final LongArrayList EMPTY_LIST;
    private long[] array;
    private int size;

    static {
        LongArrayList longArrayList0 = new LongArrayList(new long[0], 0);
        LongArrayList.EMPTY_LIST = longArrayList0;
        longArrayList0.makeImmutable();
    }

    LongArrayList() {
        this(new long[10], 0);
    }

    private LongArrayList(long[] arr_v, int v) {
        this.array = arr_v;
        this.size = v;
    }

    public void add(int v, Long long0) {
        this.addLong(v, ((long)long0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public void add(int v, Object object0) {
        this.add(v, ((Long)object0));
    }

    public boolean add(Long long0) {
        this.addLong(((long)long0));
        return true;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean add(Object object0) {
        return this.add(((Long)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean addAll(Collection collection0) {
        this.ensureIsMutable();
        Internal.checkNotNull(collection0);
        if(!(collection0 instanceof LongArrayList)) {
            return super.addAll(collection0);
        }
        int v = ((LongArrayList)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        long[] arr_v = this.array;
        if(v2 > arr_v.length) {
            this.array = Arrays.copyOf(arr_v, v2);
        }
        System.arraycopy(((LongArrayList)collection0).array, 0, this.array, this.size, ((LongArrayList)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    private void addLong(int v, long v1) {
        this.ensureIsMutable();
        if(v >= 0) {
            int v2 = this.size;
            if(v <= v2) {
                long[] arr_v = this.array;
                if(v2 < arr_v.length) {
                    System.arraycopy(arr_v, v, arr_v, v + 1, v2 - v);
                }
                else {
                    long[] arr_v1 = new long[v2 * 3 / 2 + 1];
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

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$LongList
    public void addLong(long v) {
        this.ensureIsMutable();
        int v1 = this.size;
        long[] arr_v = this.array;
        if(v1 == arr_v.length) {
            long[] arr_v1 = new long[v1 * 3 / 2 + 1];
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

    public static LongArrayList emptyList() {
        return LongArrayList.EMPTY_LIST;
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
        if(!(object0 instanceof LongArrayList)) {
            return super.equals(object0);
        }
        if(this.size != ((LongArrayList)object0).size) {
            return false;
        }
        long[] arr_v = ((LongArrayList)object0).array;
        for(int v = 0; v < this.size; ++v) {
            if(this.array[v] != arr_v[v]) {
                return false;
            }
        }
        return true;
    }

    public Long get(int v) {
        return this.getLong(v);
    }

    @Override
    public Object get(int v) {
        return this.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$LongList
    public long getLong(int v) {
        this.ensureIndexInRange(v);
        return this.array[v];
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + Internal.hashLong(this.array[v1]);
        }
        return v;
    }

    @Override
    public int indexOf(Object object0) {
        if(!(object0 instanceof Long)) {
            return -1;
        }
        long v = (long)(((Long)object0));
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

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$LongList
    public LongList mutableCopyWithCapacity(int v) {
        if(v < this.size) {
            throw new IllegalArgumentException();
        }
        return new LongArrayList(Arrays.copyOf(this.array, v), this.size);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$ProtobufList
    public ProtobufList mutableCopyWithCapacity(int v) {
        return this.mutableCopyWithCapacity(v);
    }

    public Long remove(int v) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        long[] arr_v = this.array;
        long v1 = arr_v[v];
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

    public Long set(int v, Long long0) {
        return this.setLong(v, ((long)long0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object set(int v, Object object0) {
        return this.set(v, ((Long)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$LongList
    public long setLong(int v, long v1) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        long[] arr_v = this.array;
        long v2 = arr_v[v];
        arr_v[v] = v1;
        return v2;
    }

    @Override
    public int size() {
        return this.size;
    }
}

