package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\b\b\'\u0018\u0000 \u001C*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0004\u001C\u001D\u001E\u001FB\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u0013\u0010\t\u001A\u00020\n2\b\u0010\u000B\u001A\u0004\u0018\u00010\fH\u0096\u0002J\u0016\u0010\r\u001A\u00028\u00002\u0006\u0010\u000E\u001A\u00020\u0006H¦\u0002¢\u0006\u0002\u0010\u000FJ\b\u0010\u0010\u001A\u00020\u0006H\u0016J\u0015\u0010\u0011\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u000F\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0096\u0002J\u0015\u0010\u0016\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u000E\u0010\u0017\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u0016\u0010\u0017\u001A\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\u000E\u001A\u00020\u0006H\u0016J\u001E\u0010\u0019\u001A\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u001A\u001A\u00020\u00062\u0006\u0010\u001B\u001A\u00020\u0006H\u0016R\u0012\u0010\u0005\u001A\u00020\u0006X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lkotlin/collections/AbstractList;", "E", "Lkotlin/collections/AbstractCollection;", "", "()V", "size", "", "getSize", "()I", "equals", "", "other", "", "get", "index", "(I)Ljava/lang/Object;", "hashCode", "indexOf", "element", "(Ljava/lang/Object;)I", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "Companion", "IteratorImpl", "ListIteratorImpl", "SubList", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractList extends AbstractCollection implements List, KMappedMarker {
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\tJ\u001D\u0010\n\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\fJ\u001D\u0010\r\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\u000EJ%\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\u0012J%\u0010\u0013\u001A\u00020\u00142\n\u0010\u0015\u001A\u0006\u0012\u0002\b\u00030\u00162\n\u0010\u0017\u001A\u0006\u0012\u0002\b\u00030\u0016H\u0000¢\u0006\u0002\b\u0018J\u0019\u0010\u0019\u001A\u00020\u00062\n\u0010\u0015\u001A\u0006\u0012\u0002\b\u00030\u0016H\u0000¢\u0006\u0002\b\u001A¨\u0006\u001B"}, d2 = {"Lkotlin/collections/AbstractList$Companion;", "", "()V", "checkBoundsIndexes", "", "startIndex", "", "endIndex", "size", "checkBoundsIndexes$kotlin_stdlib", "checkElementIndex", "index", "checkElementIndex$kotlin_stdlib", "checkPositionIndex", "checkPositionIndex$kotlin_stdlib", "checkRangeIndexes", "fromIndex", "toIndex", "checkRangeIndexes$kotlin_stdlib", "orderedEquals", "", "c", "", "other", "orderedEquals$kotlin_stdlib", "orderedHashCode", "orderedHashCode$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final void checkBoundsIndexes$kotlin_stdlib(int v, int v1, int v2) {
            if(v < 0 || v1 > v2) {
                throw new IndexOutOfBoundsException("startIndex: " + v + ", endIndex: " + v1 + ", size: " + v2);
            }
            if(v > v1) {
                throw new IllegalArgumentException("startIndex: " + v + " > endIndex: " + v1);
            }
        }

        public final void checkElementIndex$kotlin_stdlib(int v, int v1) {
            if(v < 0 || v >= v1) {
                throw new IndexOutOfBoundsException("index: " + v + ", size: " + v1);
            }
        }

        public final void checkPositionIndex$kotlin_stdlib(int v, int v1) {
            if(v < 0 || v > v1) {
                throw new IndexOutOfBoundsException("index: " + v + ", size: " + v1);
            }
        }

        public final void checkRangeIndexes$kotlin_stdlib(int v, int v1, int v2) {
            if(v < 0 || v1 > v2) {
                throw new IndexOutOfBoundsException("fromIndex: " + v + ", toIndex: " + v1 + ", size: " + v2);
            }
            if(v > v1) {
                throw new IllegalArgumentException("fromIndex: " + v + " > toIndex: " + v1);
            }
        }

        public final boolean orderedEquals$kotlin_stdlib(Collection collection0, Collection collection1) {
            Intrinsics.checkNotNullParameter(collection0, "c");
            Intrinsics.checkNotNullParameter(collection1, "other");
            if(collection0.size() != collection1.size()) {
                return false;
            }
            Iterator iterator0 = collection1.iterator();
            for(Object object0: collection0) {
                Object object1 = iterator0.next();
                if(!Intrinsics.areEqual(object0, object1)) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        public final int orderedHashCode$kotlin_stdlib(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "c");
            int v = 1;
            for(Object object0: collection0) {
                v = v * 0x1F + (object0 == null ? 0 : object0.hashCode());
            }
            return v;
        }
    }

    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0092\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\t\u001A\u00020\nH\u0096\u0002J\u000E\u0010\u000B\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\fR\u001A\u0010\u0003\u001A\u00020\u0004X\u0084\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlin/collections/AbstractList$IteratorImpl;", "", "(Lkotlin/collections/AbstractList;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    class IteratorImpl implements Iterator, KMappedMarker {
        private int index;

        protected final int getIndex() {
            return this.index;
        }

        @Override
        public boolean hasNext() {
            return this.index < AbstractList.this.size();
        }

        @Override
        public Object next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }
            int v = this.index;
            this.index = v + 1;
            return AbstractList.this.get(v);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        protected final void setIndex(int v) {
            this.index = v;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010*\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\b\u0092\u0004\u0018\u00002\f0\u0001R\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\u0005H\u0016J\r\u0010\n\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000BJ\b\u0010\f\u001A\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lkotlin/collections/AbstractList$ListIteratorImpl;", "Lkotlin/collections/AbstractList$IteratorImpl;", "Lkotlin/collections/AbstractList;", "", "index", "", "(Lkotlin/collections/AbstractList;I)V", "hasPrevious", "", "nextIndex", "previous", "()Ljava/lang/Object;", "previousIndex", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    class ListIteratorImpl extends IteratorImpl implements ListIterator, KMappedMarker {
        public ListIteratorImpl(int v) {
            AbstractList.Companion.checkPositionIndex$kotlin_stdlib(v, abstractList0.size());
            this.setIndex(v);
        }

        @Override
        public void add(Object object0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean hasPrevious() {
            return this.getIndex() > 0;
        }

        @Override
        public int nextIndex() {
            return this.getIndex();
        }

        @Override
        public Object previous() {
            if(!this.hasPrevious()) {
                throw new NoSuchElementException();
            }
            this.setIndex(this.getIndex() - 1);
            int v = this.getIndex();
            return AbstractList.this.get(v);
        }

        @Override
        public int previousIndex() {
            return this.getIndex() - 1;
        }

        @Override
        public void set(Object object0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B#\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\u0007¢\u0006\u0002\u0010\tJ\u0016\u0010\u000E\u001A\u00028\u00012\u0006\u0010\u000F\u001A\u00020\u0007H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u000E\u0010\n\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000B\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lkotlin/collections/AbstractList$SubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "list", "fromIndex", "", "toIndex", "(Lkotlin/collections/AbstractList;II)V", "_size", "size", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class SubList extends AbstractList implements RandomAccess {
        private int _size;
        private final int fromIndex;
        private final AbstractList list;

        public SubList(AbstractList abstractList0, int v, int v1) {
            Intrinsics.checkNotNullParameter(abstractList0, "list");
            super();
            this.list = abstractList0;
            this.fromIndex = v;
            AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(v, v1, abstractList0.size());
            this._size = v1 - v;
        }

        @Override  // kotlin.collections.AbstractList
        public Object get(int v) {
            AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this._size);
            return this.list.get(this.fromIndex + v);
        }

        @Override  // kotlin.collections.AbstractList
        public int getSize() {
            return this._size;
        }
    }

    public static final Companion Companion;

    static {
        AbstractList.Companion = new Companion(null);
    }

    @Override
    public void add(int v, Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(int v, Collection collection0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof List ? AbstractList.Companion.orderedEquals$kotlin_stdlib(this, ((Collection)object0)) : false;
    }

    @Override
    public abstract Object get(int arg1);

    @Override  // kotlin.collections.AbstractCollection
    public abstract int getSize();

    @Override
    public int hashCode() {
        return AbstractList.Companion.orderedHashCode$kotlin_stdlib(this);
    }

    @Override
    public int indexOf(Object object0) {
        int v = 0;
        for(Object object1: this) {
            if(Intrinsics.areEqual(object1, object0)) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    @Override  // kotlin.collections.AbstractCollection
    public Iterator iterator() {
        return new IteratorImpl(this);
    }

    @Override
    public int lastIndexOf(Object object0) {
        ListIterator listIterator0 = this.listIterator(this.size());
        while(listIterator0.hasPrevious()) {
            if(Intrinsics.areEqual(listIterator0.previous(), object0)) {
                return listIterator0.nextIndex();
            }
            if(false) {
                break;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl(this, 0);
    }

    @Override
    public ListIterator listIterator(int v) {
        return new ListIteratorImpl(this, v);
    }

    @Override
    public Object remove(int v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public Object set(int v, Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public List subList(int v, int v1) {
        return new SubList(this, v, v1);
    }
}

