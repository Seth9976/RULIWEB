package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u0011\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0002H\u0096\u0002J\u0016\u0010\u000F\u001A\u00020\r2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0016J\u0013\u0010\u0012\u001A\u00020\r2\b\u0010\u0013\u001A\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001A\u00020\tH\u0016J\b\u0010\u0016\u001A\u00020\rH\u0016J\u000F\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0096\u0002J\b\u0010\u0019\u001A\u00020\u0014H\u0002J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000B¨\u0006\u001C"}, d2 = {"Lkotlin/collections/EmptySet;", "", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "serialVersionUID", "", "size", "", "getSize", "()I", "contains", "", "element", "containsAll", "elements", "", "equals", "other", "", "hashCode", "isEmpty", "iterator", "", "readResolve", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class EmptySet implements Serializable, Set, KMappedMarker {
    public static final EmptySet INSTANCE = null;
    private static final long serialVersionUID = 0x2F46B01576D7E2F4L;

    static {
        EmptySet.INSTANCE = new EmptySet();
    }

    @Override
    public boolean add(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection collection0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public final boolean contains(Object object0) {
        return object0 instanceof Void ? this.contains(((Void)object0)) : false;
    }

    public boolean contains(Void void0) {
        Intrinsics.checkNotNullParameter(void0, "element");
        return false;
    }

    @Override
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        return collection0.isEmpty();
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Set && ((Set)object0).isEmpty();
    }

    public int getSize() [...] // Inlined contents

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }

    private final Object readResolve() {
        return EmptySet.INSTANCE;
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

    @Override
    public final int size() {
        return 0;
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
        return "[]";
    }
}

