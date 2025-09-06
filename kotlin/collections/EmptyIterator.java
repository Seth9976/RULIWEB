package kotlin.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010*\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\t\u0010\u0004\u001A\u00020\u0005H\u0096\u0002J\b\u0010\u0006\u001A\u00020\u0005H\u0016J\t\u0010\u0007\u001A\u00020\u0002H\u0096\u0002J\b\u0010\b\u001A\u00020\tH\u0016J\b\u0010\n\u001A\u00020\u0002H\u0016J\b\u0010\u000B\u001A\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lkotlin/collections/EmptyIterator;", "", "", "()V", "hasNext", "", "hasPrevious", "next", "nextIndex", "", "previous", "previousIndex", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class EmptyIterator implements ListIterator, KMappedMarker {
    public static final EmptyIterator INSTANCE;

    static {
        EmptyIterator.INSTANCE = new EmptyIterator();
    }

    @Override
    public void add(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void add(Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Object next() {
        return this.next();
    }

    public Void next() {
        throw new NoSuchElementException();
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public Object previous() {
        return this.previous();
    }

    public Void previous() {
        throw new NoSuchElementException();
    }

    @Override
    public int previousIndex() {
        return -1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void set(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void set(Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

