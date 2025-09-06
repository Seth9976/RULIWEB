package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0004\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00012B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001A\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001B\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001A\u00020\u000F2\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001A\u0010\u0017\u001A\u00020\u000F2\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001A\u0010\u001BJ\u001E\u0010\u001C\u001A\u00020\u00022\u0006\u0010\u001D\u001A\u00020\u0004H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001E\u0010\u001FJ\u0010\u0010 \u001A\u00020\u0004HÖ\u0001¢\u0006\u0004\b!\u0010\u000BJ\u000F\u0010\"\u001A\u00020\u000FH\u0016¢\u0006\u0004\b#\u0010$J\u0019\u0010%\u001A\b\u0012\u0004\u0012\u00020\u00020&H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\'\u0010(J#\u0010)\u001A\u00020*2\u0006\u0010\u001D\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001A\u00020/HÖ\u0001¢\u0006\u0004\b0\u00101R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0016\u0010\u0007\u001A\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u0088\u0001\u0007\u0092\u0001\u00020\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u00063"}, d2 = {"Lkotlin/UShortArray;", "", "Lkotlin/UShort;", "size", "", "constructor-impl", "(I)[S", "storage", "", "([S)[S", "getSize-impl", "([S)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-xj2QHRw", "([SS)Z", "containsAll", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "equals", "other", "", "equals-impl", "([SLjava/lang/Object;)Z", "get", "index", "get-Mh2AYeg", "([SI)S", "hashCode", "hashCode-impl", "isEmpty", "isEmpty-impl", "([S)Z", "iterator", "", "iterator-impl", "([S)Ljava/util/Iterator;", "set", "", "value", "set-01HTLdE", "([SIS)V", "toString", "", "toString-impl", "([S)Ljava/lang/String;", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class UShortArray implements Collection, KMappedMarker {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001A\u00020\tH\u0096\u0002J\u0016\u0010\n\u001A\u00020\u0002H\u0096\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000B\u0010\fR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000ø\u0001\u0001\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlin/UShortArray$Iterator;", "", "Lkotlin/UShort;", "array", "", "([S)V", "index", "", "hasNext", "", "next", "next-Mh2AYeg", "()S", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Iterator implements java.util.Iterator, KMappedMarker {
        private final short[] array;
        private int index;

        public Iterator(short[] arr_v) {
            Intrinsics.checkNotNullParameter(arr_v, "array");
            super();
            this.array = arr_v;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override
        public Object next() {
            return UShort.box-impl(this.next-Mh2AYeg());
        }

        public short next-Mh2AYeg() {
            int v = this.index;
            short[] arr_v = this.array;
            if(v >= arr_v.length) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            this.index = v + 1;
            return UShort.constructor-impl(arr_v[v]);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    private final short[] storage;

    private UShortArray(short[] arr_v) {
        this.storage = arr_v;
    }

    @Override
    public boolean add(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add-xj2QHRw(short v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection collection0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static final UShortArray box-impl(short[] arr_v) {
        return new UShortArray(arr_v);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static short[] constructor-impl(int v) {
        return UShortArray.constructor-impl(new short[v]);
    }

    public static short[] constructor-impl(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "storage");
        return arr_v;
    }

    @Override
    public final boolean contains(Object object0) {
        return object0 instanceof UShort ? this.contains-xj2QHRw(((UShort)object0).unbox-impl()) : false;
    }

    public static boolean contains-xj2QHRw(short[] arr_v, short v) {
        return ArraysKt.contains(arr_v, v);
    }

    public boolean contains-xj2QHRw(short v) {
        return UShortArray.contains-xj2QHRw(this.storage, v);
    }

    @Override
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        return UShortArray.containsAll-impl(this.storage, collection0);
    }

    public static boolean containsAll-impl(short[] arr_v, Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        if(collection0.isEmpty()) {
            return true;
        }
        for(Object object0: collection0) {
            if(!(object0 instanceof UShort) || !ArraysKt.contains(arr_v, ((UShort)object0).unbox-impl())) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object0) {
        return UShortArray.equals-impl(this.storage, object0);
    }

    // 去混淆评级： 低(20)
    public static boolean equals-impl(short[] arr_v, Object object0) {
        return object0 instanceof UShortArray ? Intrinsics.areEqual(arr_v, ((UShortArray)object0).unbox-impl()) : false;
    }

    public static final boolean equals-impl0(short[] arr_v, short[] arr_v1) {
        return Intrinsics.areEqual(arr_v, arr_v1);
    }

    public static final short get-Mh2AYeg(short[] arr_v, int v) {
        return UShort.constructor-impl(arr_v[v]);
    }

    public int getSize() {
        return UShortArray.getSize-impl(this.storage);
    }

    public static int getSize-impl(short[] arr_v) {
        return arr_v.length;
    }

    public static void getStorage$annotations() {
    }

    @Override
    public int hashCode() {
        return UShortArray.hashCode-impl(this.storage);
    }

    public static int hashCode-impl(short[] arr_v) {
        return Arrays.hashCode(arr_v);
    }

    @Override
    public boolean isEmpty() {
        return UShortArray.isEmpty-impl(this.storage);
    }

    public static boolean isEmpty-impl(short[] arr_v) {
        return arr_v.length == 0;
    }

    @Override
    public java.util.Iterator iterator() {
        return UShortArray.iterator-impl(this.storage);
    }

    public static java.util.Iterator iterator-impl(short[] arr_v) {
        return new Iterator(arr_v);
    }

    @Override
    public boolean remove(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean removeAll(Collection collection0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean retainAll(Collection collection0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static final void set-01HTLdE(short[] arr_v, int v, short v1) {
        arr_v[v] = v1;
    }

    @Override
    public int size() {
        return this.getSize();
    }

    @Override
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override
    public Object[] toArray(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        return CollectionToArray.toArray(this, arr_object);
    }

    @Override
    public String toString() {
        return UShortArray.toString-impl(this.storage);
    }

    public static String toString-impl(short[] arr_v) {
        return "UShortArray(storage=" + Arrays.toString(arr_v) + ')';
    }

    public final short[] unbox-impl() {
        return this.storage;
    }
}

