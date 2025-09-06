package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0004\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00012B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001A\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001B\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001A\u00020\u000F2\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001A\u0010\u0017\u001A\u00020\u000F2\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001A\u0010\u001BJ\u001E\u0010\u001C\u001A\u00020\u00022\u0006\u0010\u001D\u001A\u00020\u0004H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001E\u0010\u001FJ\u0010\u0010 \u001A\u00020\u0004HÖ\u0001¢\u0006\u0004\b!\u0010\u000BJ\u000F\u0010\"\u001A\u00020\u000FH\u0016¢\u0006\u0004\b#\u0010$J\u0019\u0010%\u001A\b\u0012\u0004\u0012\u00020\u00020&H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\'\u0010(J#\u0010)\u001A\u00020*2\u0006\u0010\u001D\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001A\u00020/HÖ\u0001¢\u0006\u0004\b0\u00101R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0016\u0010\u0007\u001A\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u0088\u0001\u0007\u0092\u0001\u00020\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u00063"}, d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "equals-impl", "([BLjava/lang/Object;)Z", "get", "index", "get-w2LRezQ", "([BI)B", "hashCode", "hashCode-impl", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "", "iterator-impl", "([B)Ljava/util/Iterator;", "set", "", "value", "set-VurrAj0", "([BIB)V", "toString", "", "toString-impl", "([B)Ljava/lang/String;", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class UByteArray implements Collection, KMappedMarker {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001A\u00020\tH\u0096\u0002J\u0016\u0010\n\u001A\u00020\u0002H\u0096\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000B\u0010\fR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000ø\u0001\u0001\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlin/UByteArray$Iterator;", "", "Lkotlin/UByte;", "array", "", "([B)V", "index", "", "hasNext", "", "next", "next-w2LRezQ", "()B", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Iterator implements java.util.Iterator, KMappedMarker {
        private final byte[] array;
        private int index;

        public Iterator(byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "array");
            super();
            this.array = arr_b;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override
        public Object next() {
            return UByte.box-impl(this.next-w2LRezQ());
        }

        public byte next-w2LRezQ() {
            int v = this.index;
            byte[] arr_b = this.array;
            if(v >= arr_b.length) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            this.index = v + 1;
            return UByte.constructor-impl(arr_b[v]);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    private final byte[] storage;

    private UByteArray(byte[] arr_b) {
        this.storage = arr_b;
    }

    @Override
    public boolean add(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add-7apg3OU(byte b) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection collection0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static final UByteArray box-impl(byte[] arr_b) {
        return new UByteArray(arr_b);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static byte[] constructor-impl(int v) {
        return UByteArray.constructor-impl(new byte[v]);
    }

    public static byte[] constructor-impl(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "storage");
        return arr_b;
    }

    @Override
    public final boolean contains(Object object0) {
        return object0 instanceof UByte ? this.contains-7apg3OU(((UByte)object0).unbox-impl()) : false;
    }

    public static boolean contains-7apg3OU(byte[] arr_b, byte b) {
        return ArraysKt.contains(arr_b, b);
    }

    public boolean contains-7apg3OU(byte b) {
        return UByteArray.contains-7apg3OU(this.storage, b);
    }

    @Override
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        return UByteArray.containsAll-impl(this.storage, collection0);
    }

    public static boolean containsAll-impl(byte[] arr_b, Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        if(collection0.isEmpty()) {
            return true;
        }
        for(Object object0: collection0) {
            if(!(object0 instanceof UByte) || !ArraysKt.contains(arr_b, ((UByte)object0).unbox-impl())) {
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
        return UByteArray.equals-impl(this.storage, object0);
    }

    // 去混淆评级： 低(20)
    public static boolean equals-impl(byte[] arr_b, Object object0) {
        return object0 instanceof UByteArray ? Intrinsics.areEqual(arr_b, ((UByteArray)object0).unbox-impl()) : false;
    }

    public static final boolean equals-impl0(byte[] arr_b, byte[] arr_b1) {
        return Intrinsics.areEqual(arr_b, arr_b1);
    }

    public static final byte get-w2LRezQ(byte[] arr_b, int v) {
        return UByte.constructor-impl(arr_b[v]);
    }

    public int getSize() {
        return UByteArray.getSize-impl(this.storage);
    }

    public static int getSize-impl(byte[] arr_b) {
        return arr_b.length;
    }

    public static void getStorage$annotations() {
    }

    @Override
    public int hashCode() {
        return UByteArray.hashCode-impl(this.storage);
    }

    public static int hashCode-impl(byte[] arr_b) {
        return Arrays.hashCode(arr_b);
    }

    @Override
    public boolean isEmpty() {
        return UByteArray.isEmpty-impl(this.storage);
    }

    public static boolean isEmpty-impl(byte[] arr_b) {
        return arr_b.length == 0;
    }

    @Override
    public java.util.Iterator iterator() {
        return UByteArray.iterator-impl(this.storage);
    }

    public static java.util.Iterator iterator-impl(byte[] arr_b) {
        return new Iterator(arr_b);
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

    public static final void set-VurrAj0(byte[] arr_b, int v, byte b) {
        arr_b[v] = b;
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
        return UByteArray.toString-impl(this.storage);
    }

    public static String toString-impl(byte[] arr_b) {
        return "UByteArray(storage=" + Arrays.toString(arr_b) + ')';
    }

    public final byte[] unbox-impl() {
        return this.storage;
    }
}

