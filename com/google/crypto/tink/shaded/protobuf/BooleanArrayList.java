package com.google.crypto.tink.shaded.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class BooleanArrayList extends AbstractProtobufList implements BooleanList, PrimitiveNonBoxingCollection, RandomAccess {
    private static final BooleanArrayList EMPTY_LIST;
    private boolean[] array;
    private int size;

    static {
        BooleanArrayList booleanArrayList0 = new BooleanArrayList(new boolean[0], 0);
        BooleanArrayList.EMPTY_LIST = booleanArrayList0;
        booleanArrayList0.makeImmutable();
    }

    BooleanArrayList() {
        this(new boolean[10], 0);
    }

    private BooleanArrayList(boolean[] arr_z, int v) {
        this.array = arr_z;
        this.size = v;
    }

    public void add(int v, Boolean boolean0) {
        this.addBoolean(v, boolean0.booleanValue());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public void add(int v, Object object0) {
        this.add(v, ((Boolean)object0));
    }

    public boolean add(Boolean boolean0) {
        this.addBoolean(boolean0.booleanValue());
        return true;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean add(Object object0) {
        return this.add(((Boolean)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean addAll(Collection collection0) {
        this.ensureIsMutable();
        Internal.checkNotNull(collection0);
        if(!(collection0 instanceof BooleanArrayList)) {
            return super.addAll(collection0);
        }
        int v = ((BooleanArrayList)collection0).size;
        if(v == 0) {
            return false;
        }
        int v1 = this.size;
        if(0x7FFFFFFF - v1 < v) {
            throw new OutOfMemoryError();
        }
        int v2 = v1 + v;
        boolean[] arr_z = this.array;
        if(v2 > arr_z.length) {
            this.array = Arrays.copyOf(arr_z, v2);
        }
        System.arraycopy(((BooleanArrayList)collection0).array, 0, this.array, this.size, ((BooleanArrayList)collection0).size);
        this.size = v2;
        ++this.modCount;
        return true;
    }

    private void addBoolean(int v, boolean z) {
        this.ensureIsMutable();
        if(v >= 0) {
            int v1 = this.size;
            if(v <= v1) {
                boolean[] arr_z = this.array;
                if(v1 < arr_z.length) {
                    System.arraycopy(arr_z, v, arr_z, v + 1, v1 - v);
                }
                else {
                    boolean[] arr_z1 = new boolean[v1 * 3 / 2 + 1];
                    System.arraycopy(arr_z, 0, arr_z1, 0, v);
                    System.arraycopy(this.array, v, arr_z1, v + 1, this.size - v);
                    this.array = arr_z1;
                }
                this.array[v] = z;
                ++this.size;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException(this.makeOutOfBoundsExceptionMessage(v));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$BooleanList
    public void addBoolean(boolean z) {
        this.ensureIsMutable();
        int v = this.size;
        boolean[] arr_z = this.array;
        if(v == arr_z.length) {
            boolean[] arr_z1 = new boolean[v * 3 / 2 + 1];
            System.arraycopy(arr_z, 0, arr_z1, 0, v);
            this.array = arr_z1;
        }
        int v1 = this.size;
        this.size = v1 + 1;
        this.array[v1] = z;
    }

    @Override
    public boolean contains(Object object0) {
        return this.indexOf(object0) != -1;
    }

    public static BooleanArrayList emptyList() {
        return BooleanArrayList.EMPTY_LIST;
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
        if(!(object0 instanceof BooleanArrayList)) {
            return super.equals(object0);
        }
        if(this.size != ((BooleanArrayList)object0).size) {
            return false;
        }
        boolean[] arr_z = ((BooleanArrayList)object0).array;
        for(int v = 0; v < this.size; ++v) {
            if(this.array[v] != arr_z[v]) {
                return false;
            }
        }
        return true;
    }

    public Boolean get(int v) {
        return Boolean.valueOf(this.getBoolean(v));
    }

    @Override
    public Object get(int v) {
        return this.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$BooleanList
    public boolean getBoolean(int v) {
        this.ensureIndexInRange(v);
        return this.array[v];
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public int hashCode() {
        int v = 1;
        for(int v1 = 0; v1 < this.size; ++v1) {
            v = v * 0x1F + Internal.hashBoolean(this.array[v1]);
        }
        return v;
    }

    @Override
    public int indexOf(Object object0) {
        if(!(object0 instanceof Boolean)) {
            return -1;
        }
        boolean z = ((Boolean)object0).booleanValue();
        int v = this.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.array[v1] == z) {
                return v1;
            }
        }
        return -1;
    }

    private String makeOutOfBoundsExceptionMessage(int v) {
        return "Index:" + v + ", Size:" + this.size;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$BooleanList
    public BooleanList mutableCopyWithCapacity(int v) {
        if(v < this.size) {
            throw new IllegalArgumentException();
        }
        return new BooleanArrayList(Arrays.copyOf(this.array, v), this.size);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$ProtobufList
    public ProtobufList mutableCopyWithCapacity(int v) {
        return this.mutableCopyWithCapacity(v);
    }

    public Boolean remove(int v) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        boolean[] arr_z = this.array;
        boolean z = arr_z[v];
        int v1 = this.size;
        if(v < v1 - 1) {
            System.arraycopy(arr_z, v + 1, arr_z, v, v1 - v - 1);
        }
        --this.size;
        ++this.modCount;
        return Boolean.valueOf(z);
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

    public Boolean set(int v, Boolean boolean0) {
        return Boolean.valueOf(this.setBoolean(v, boolean0.booleanValue()));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object set(int v, Object object0) {
        return this.set(v, ((Boolean)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$BooleanList
    public boolean setBoolean(int v, boolean z) {
        this.ensureIsMutable();
        this.ensureIndexInRange(v);
        boolean[] arr_z = this.array;
        boolean z1 = arr_z[v];
        arr_z[v] = z;
        return z1;
    }

    @Override
    public int size() {
        return this.size;
    }
}

