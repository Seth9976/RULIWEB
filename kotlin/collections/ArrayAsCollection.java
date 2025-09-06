package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001D\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001A\u00020\u00062\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016J\b\u0010\u0015\u001A\u00020\u0006H\u0016J\u000F\u0010\u0016\u001A\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0096\u0002J\u0015\u0010\u0018\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00190\u0004¢\u0006\u0002\u0010\u000ER\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\bR\u0014\u0010\t\u001A\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u001B\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004¢\u0006\n\n\u0002\u0010\u000F\u001A\u0004\b\r\u0010\u000E¨\u0006\u001A"}, d2 = {"Lkotlin/collections/ArrayAsCollection;", "T", "", "values", "", "isVarargs", "", "([Ljava/lang/Object;Z)V", "()Z", "size", "", "getSize", "()I", "getValues", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "contains", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "isEmpty", "iterator", "", "toArray", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ArrayAsCollection implements Collection, KMappedMarker {
    private final boolean isVarargs;
    private final Object[] values;

    public ArrayAsCollection(Object[] arr_object, boolean z) {
        Intrinsics.checkNotNullParameter(arr_object, "values");
        super();
        this.values = arr_object;
        this.isVarargs = z;
    }

    @Override
    public boolean add(Object object0) {
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
    public boolean contains(Object object0) {
        return ArraysKt.contains(this.values, object0);
    }

    @Override
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        if(collection0.isEmpty()) {
            return true;
        }
        for(Object object0: collection0) {
            if(!this.contains(object0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public int getSize() {
        return this.values.length;
    }

    public final Object[] getValues() {
        return this.values;
    }

    @Override
    public boolean isEmpty() {
        return this.values.length == 0;
    }

    public final boolean isVarargs() {
        return this.isVarargs;
    }

    @Override
    public Iterator iterator() {
        return ArrayIteratorKt.iterator(this.values);
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
        return this.getSize();
    }

    @Override
    public final Object[] toArray() {
        return CollectionsKt.copyToArrayOfAny(this.values, this.isVarargs);
    }

    @Override
    public Object[] toArray(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        return CollectionToArray.toArray(this, arr_object);
    }
}

