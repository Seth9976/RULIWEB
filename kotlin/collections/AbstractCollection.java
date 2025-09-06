package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\b\'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\u0016\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000BJ\u0016\u0010\f\u001A\u00020\t2\f\u0010\r\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016J\b\u0010\u000E\u001A\u00020\tH\u0016J\u000F\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H¦\u0002J\u0015\u0010\u0011\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012H\u0015¢\u0006\u0002\u0010\u0014J\'\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u00150\u0012\"\u0004\b\u0001\u0010\u00152\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u00150\u0012H\u0014¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001A\u00020\u0019H\u0016R\u0012\u0010\u0004\u001A\u00020\u0005X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u001A"}, d2 = {"Lkotlin/collections/AbstractCollection;", "E", "", "()V", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "isEmpty", "iterator", "", "toArray", "", "", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractCollection implements Collection, KMappedMarker {
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
        if(this instanceof Collection && this.isEmpty()) {
            return false;
        }
        for(Object object1: this) {
            if(Intrinsics.areEqual(object1, object0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
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

    public abstract int getSize();

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public abstract Iterator iterator();

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
        return CollectionsKt.joinToString$default(this, ", ", "[", "]", 0, null, new Function1() {
            {
                AbstractCollection.this = abstractCollection0;
                super(1);
            }

            public final CharSequence invoke(Object object0) {
                return object0 == AbstractCollection.this ? "(this Collection)" : String.valueOf(object0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        }, 24, null);
    }
}

