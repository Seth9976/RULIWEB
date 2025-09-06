package kotlin.reflect.jvm.internal.impl.platform;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

public class TargetPlatform implements Collection, KMappedMarker {
    private final Set componentPlatforms;

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
    public final boolean contains(Object object0) {
        return object0 instanceof SimplePlatform ? this.contains(((SimplePlatform)object0)) : false;
    }

    public boolean contains(SimplePlatform simplePlatform0) {
        Intrinsics.checkNotNullParameter(simplePlatform0, "element");
        return this.componentPlatforms.contains(simplePlatform0);
    }

    @Override
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        return this.componentPlatforms.containsAll(collection0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof TargetPlatform ? Intrinsics.areEqual(this.componentPlatforms, ((TargetPlatform)object0).componentPlatforms) : false;
    }

    public final Set getComponentPlatforms() {
        return this.componentPlatforms;
    }

    public int getSize() {
        return this.componentPlatforms.size();
    }

    @Override
    public int hashCode() {
        return this.componentPlatforms.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return this.componentPlatforms.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return this.componentPlatforms.iterator();
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
    public boolean removeIf(Predicate predicate0) {
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
        return PlatformUtilKt.getPresentableDescription(this);
    }
}

