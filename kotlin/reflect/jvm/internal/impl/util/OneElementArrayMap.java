package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

public final class OneElementArrayMap extends ArrayMap {
    private final int index;
    private final Object value;

    public OneElementArrayMap(Object object0, int v) {
        Intrinsics.checkNotNullParameter(object0, "value");
        super(null);
        this.value = object0;
        this.index = v;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public Object get(int v) {
        return v == this.index ? this.value : null;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public int getSize() {
        return 1;
    }

    public final Object getValue() {
        return this.value;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public Iterator iterator() {
        return new Object() {
            private boolean notVisited;

            {
                this.notVisited = true;
            }

            @Override
            public boolean hasNext() {
                return this.notVisited;
            }

            @Override
            public Object next() {
                if(!this.notVisited) {
                    throw new NoSuchElementException();
                }
                this.notVisited = false;
                return OneElementArrayMap.this.getValue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public void set(int v, Object object0) {
        Intrinsics.checkNotNullParameter(object0, "value");
        throw new IllegalStateException();
    }
}

