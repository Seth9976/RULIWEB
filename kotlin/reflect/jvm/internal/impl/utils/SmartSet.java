package kotlin.reflect.jvm.internal.impl.utils;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;

public final class SmartSet extends AbstractSet {
    static final class ArrayIterator implements Iterator, KMutableIterator {
        private final Iterator arrayIterator;

        public ArrayIterator(Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "array");
            super();
            this.arrayIterator = ArrayIteratorKt.iterator(arr_object);
        }

        @Override
        public boolean hasNext() {
            return this.arrayIterator.hasNext();
        }

        @Override
        public Object next() {
            return this.arrayIterator.next();
        }

        public Void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            this.remove();
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final SmartSet create() {
            return new SmartSet(null);
        }

        @JvmStatic
        public final SmartSet create(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "set");
            SmartSet smartSet0 = new SmartSet(null);
            smartSet0.addAll(collection0);
            return smartSet0;
        }
    }

    static final class SingletonIterator implements Iterator, KMutableIterator {
        private final Object element;
        private boolean hasNext;

        public SingletonIterator(Object object0) {
            this.element = object0;
            this.hasNext = true;
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public Object next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            this.hasNext = false;
            return this.element;
        }

        public Void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            this.remove();
        }
    }

    public static final Companion Companion;
    private Object data;
    private int size;

    static {
        SmartSet.Companion = new Companion(null);
    }

    private SmartSet() {
    }

    public SmartSet(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    @Override
    public boolean add(Object object0) {
        LinkedHashSet linkedHashSet0;
        switch(this.size()) {
            case 0: {
                this.data = object0;
                break;
            }
            case 1: {
                if(Intrinsics.areEqual(this.data, object0)) {
                    return false;
                }
                this.data = new Object[]{this.data, object0};
                break;
            }
            default: {
                if(this.size() < 5) {
                    Object object1 = this.data;
                    Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
                    if(ArraysKt.contains(((Object[])object1), object0)) {
                        return false;
                    }
                    if(this.size() == 4) {
                        linkedHashSet0 = SetsKt.linkedSetOf(Arrays.copyOf(((Object[])object1), ((Object[])object1).length));
                        linkedHashSet0.add(object0);
                    }
                    else {
                        linkedHashSet0 = Arrays.copyOf(((Object[])object1), this.size() + 1);
                        Intrinsics.checkNotNullExpressionValue(linkedHashSet0, "copyOf(this, newSize)");
                        linkedHashSet0[linkedHashSet0.length - 1] = object0;
                    }
                    this.data = linkedHashSet0;
                }
                else {
                    Object object2 = this.data;
                    Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
                    if(!TypeIntrinsics.asMutableSet(object2).add(object0)) {
                        return false;
                    }
                }
            }
        }
        this.setSize(this.size() + 1);
        return true;
    }

    @Override
    public void clear() {
        this.data = null;
        this.setSize(0);
    }

    @Override
    public boolean contains(Object object0) {
        switch(this.size()) {
            case 0: {
                return false;
            }
            case 1: {
                return Intrinsics.areEqual(this.data, object0);
            }
            default: {
                if(this.size() < 5) {
                    Object object1 = this.data;
                    Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
                    return ArraysKt.contains(((Object[])object1), object0);
                }
                Object object2 = this.data;
                Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.collections.Set<T of org.jetbrains.kotlin.utils.SmartSet>");
                return ((Set)object2).contains(object0);
            }
        }
    }

    @JvmStatic
    public static final SmartSet create() {
        return SmartSet.Companion.create();
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator iterator() {
        switch(this.size()) {
            case 0: {
                return Collections.EMPTY_SET.iterator();
            }
            case 1: {
                return new SingletonIterator(this.data);
            }
            default: {
                if(this.size() < 5) {
                    Object object0 = this.data;
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
                    return new ArrayIterator(((Object[])object0));
                }
                Object object1 = this.data;
                Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
                return TypeIntrinsics.asMutableSet(object1).iterator();
            }
        }
    }

    public void setSize(int v) {
        this.size = v;
    }

    @Override
    public final int size() {
        return this.getSize();
    }
}

