package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u00042\u00060\u0005j\u0002`\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0002H\u0096\u0002J\u0016\u0010\u0011\u001A\u00020\u000F2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0016J\u0013\u0010\u0014\u001A\u00020\u000F2\b\u0010\u0015\u001A\u0004\u0018\u00010\u0016H\u0096\u0002J\u0011\u0010\u0017\u001A\u00020\u00022\u0006\u0010\u0018\u001A\u00020\u000BH\u0096\u0002J\b\u0010\u0019\u001A\u00020\u000BH\u0016J\u0010\u0010\u001A\u001A\u00020\u000B2\u0006\u0010\u0010\u001A\u00020\u0002H\u0016J\b\u0010\u001B\u001A\u00020\u000FH\u0016J\u000F\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00020\u001DH\u0096\u0002J\u0010\u0010\u001E\u001A\u00020\u000B2\u0006\u0010\u0010\u001A\u00020\u0002H\u0016J\u000E\u0010\u001F\u001A\b\u0012\u0004\u0012\u00020\u00020 H\u0016J\u0016\u0010\u001F\u001A\b\u0012\u0004\u0012\u00020\u00020 2\u0006\u0010\u0018\u001A\u00020\u000BH\u0016J\b\u0010!\u001A\u00020\u0016H\u0002J\u001E\u0010\"\u001A\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010#\u001A\u00020\u000B2\u0006\u0010$\u001A\u00020\u000BH\u0016J\b\u0010%\u001A\u00020&H\u0016R\u000E\u0010\b\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001A\u00020\u000B8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\'"}, d2 = {"Lkotlin/collections/EmptyList;", "", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "()V", "serialVersionUID", "", "size", "", "getSize", "()I", "contains", "", "element", "containsAll", "elements", "", "equals", "other", "", "get", "index", "hashCode", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "readResolve", "subList", "fromIndex", "toIndex", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class EmptyList implements Serializable, List, RandomAccess, KMappedMarker {
    public static final EmptyList INSTANCE = null;
    private static final long serialVersionUID = 0x996FC7D0A7E06032L;

    static {
        EmptyList.INSTANCE = new EmptyList();
    }

    @Override
    public void add(int v, Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void add(int v, Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean add(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(int v, Collection collection0) {
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
        return object0 instanceof List && ((List)object0).isEmpty();
    }

    @Override
    public Object get(int v) {
        return this.get(v);
    }

    public Void get(int v) {
        throw new IndexOutOfBoundsException("Empty list doesn\'t contain element at index " + v + '.');
    }

    public int getSize() [...] // Inlined contents

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public final int indexOf(Object object0) {
        return object0 instanceof Void ? this.indexOf(((Void)object0)) : -1;
    }

    public int indexOf(Void void0) {
        Intrinsics.checkNotNullParameter(void0, "element");
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }

    @Override
    public final int lastIndexOf(Object object0) {
        return object0 instanceof Void ? this.lastIndexOf(((Void)object0)) : -1;
    }

    public int lastIndexOf(Void void0) {
        Intrinsics.checkNotNullParameter(void0, "element");
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return EmptyIterator.INSTANCE;
    }

    @Override
    public ListIterator listIterator(int v) {
        if(v != 0) {
            throw new IndexOutOfBoundsException("Index: " + v);
        }
        return EmptyIterator.INSTANCE;
    }

    private final Object readResolve() {
        return EmptyList.INSTANCE;
    }

    @Override
    public Object remove(int v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Void remove(int v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
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
    public Object set(int v, Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Void set(int v, Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public final int size() {
        return 0;
    }

    @Override
    public List subList(int v, int v1) {
        if(v != 0 || v1 != 0) {
            throw new IndexOutOfBoundsException("fromIndex: " + v + ", toIndex: " + v1);
        }
        return this;
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

