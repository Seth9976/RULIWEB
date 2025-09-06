package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

public abstract class ArrayMap implements Iterable, KMappedMarker {
    private ArrayMap() {
    }

    public ArrayMap(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public abstract Object get(int arg1);

    public abstract int getSize();

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract void set(int arg1, Object arg2);
}

