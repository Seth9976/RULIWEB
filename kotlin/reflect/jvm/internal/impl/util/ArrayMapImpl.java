package kotlin.reflect.jvm.internal.impl.util;

import java.util.Arrays;
import java.util.Iterator;
import kotlin.collections.AbstractIterator;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ArrayMapImpl extends ArrayMap {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private Object[] data;
    private int size;

    static {
        ArrayMapImpl.Companion = new Companion(null);
    }

    public ArrayMapImpl() {
        this(new Object[20], 0);
    }

    private ArrayMapImpl(Object[] arr_object, int v) {
        super(null);
        this.data = arr_object;
        this.size = v;
    }

    private final void ensureCapacity(int v) {
        Object[] arr_object = this.data;
        if(arr_object.length <= v) {
            Object[] arr_object1 = Arrays.copyOf(arr_object, arr_object.length * 2);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            this.data = arr_object1;
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public Object get(int v) {
        return ArraysKt.getOrNull(this.data, v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public int getSize() {
        return this.size;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public Iterator iterator() {
        return new AbstractIterator() {
            private int index;

            {
                this.index = -1;
            }

            @Override  // kotlin.collections.AbstractIterator
            protected void computeNext() {
                do {
                    int v = this.index + 1;
                    this.index = v;
                }
                while(v < ArrayMapImpl.this.data.length && ArrayMapImpl.this.data[this.index] == null);
                if(this.index >= ArrayMapImpl.this.data.length) {
                    this.done();
                    return;
                }
                Object object0 = ArrayMapImpl.this.data[this.index];
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of org.jetbrains.kotlin.util.ArrayMapImpl");
                this.setNext(object0);
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.ArrayMap
    public void set(int v, Object object0) {
        Intrinsics.checkNotNullParameter(object0, "value");
        this.ensureCapacity(v);
        if(this.data[v] == null) {
            this.size = this.getSize() + 1;
        }
        this.data[v] = object0;
    }
}

