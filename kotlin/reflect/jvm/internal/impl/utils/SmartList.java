package kotlin.reflect.jvm.internal.impl.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class SmartList extends AbstractList implements RandomAccess {
    static class EmptyIterator implements Iterator {
        private static final EmptyIterator INSTANCE;

        static {
            EmptyIterator.INSTANCE = new EmptyIterator();
        }

        public static EmptyIterator getInstance() {
            return EmptyIterator.INSTANCE;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new IllegalStateException();
        }
    }

    class SingletonIterator extends SingletonIteratorBase {
        private final int myInitialModCount;

        public SingletonIterator() {
            super(null);
            this.myInitialModCount = smartList0.modCount;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.utils.SmartList$SingletonIteratorBase
        protected void checkCoModification() {
            if(SmartList.this.modCount != this.myInitialModCount) {
                throw new ConcurrentModificationException("ModCount: " + SmartList.this.modCount + "; expected: " + this.myInitialModCount);
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.utils.SmartList$SingletonIteratorBase
        protected Object getElement() {
            return SmartList.this.myElem;
        }

        @Override
        public void remove() {
            this.checkCoModification();
            SmartList.this.clear();
        }
    }

    static abstract class SingletonIteratorBase implements Iterator {
        private boolean myVisited;

        private SingletonIteratorBase() {
        }

        SingletonIteratorBase(kotlin.reflect.jvm.internal.impl.utils.SmartList.1 smartList$10) {
        }

        protected abstract void checkCoModification();

        protected abstract Object getElement();

        @Override
        public final boolean hasNext() {
            return !this.myVisited;
        }

        @Override
        public final Object next() {
            if(this.myVisited) {
                throw new NoSuchElementException();
            }
            this.myVisited = true;
            this.checkCoModification();
            return this.getElement();
        }
    }

    private Object myElem;
    private int mySize;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 2 || v == 3 || v == 5 || v == 6 || v == 7 ? 2 : 3)];
        switch(v) {
            case 4: {
                arr_object[0] = "a";
                break;
            }
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 7: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            }
            default: {
                arr_object[0] = "elements";
            }
        }
        switch(v) {
            case 2: 
            case 3: {
                arr_object[1] = "iterator";
                break;
            }
            case 5: 
            case 6: 
            case 7: {
                arr_object[1] = "toArray";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
            }
        }
        switch(v) {
            case 4: {
                arr_object[2] = "toArray";
                break;
            }
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 7: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 2 || v == 3 || v == 5 || v == 6 || v == 7 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 2 || v == 3 || v == 5 || v == 6 || v == 7 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    @Override
    public void add(int v, Object object0) {
        if(v >= 0) {
            int v1 = this.mySize;
            if(v <= v1) {
                switch(v1) {
                    case 0: {
                        this.myElem = object0;
                        break;
                    }
                    case 1: {
                        if(v == 0) {
                            this.myElem = new Object[]{object0, this.myElem};
                        }
                        else {
                        label_10:
                            Object[] arr_object = new Object[v1 + 1];
                            if(v1 == 1) {
                                arr_object[0] = this.myElem;
                            }
                            else {
                                Object[] arr_object1 = (Object[])this.myElem;
                                System.arraycopy(arr_object1, 0, arr_object, 0, v);
                                System.arraycopy(arr_object1, v, arr_object, v + 1, this.mySize - v);
                            }
                            arr_object[v] = object0;
                            this.myElem = arr_object;
                        }
                        break;
                    }
                    default: {
                        goto label_10;
                    }
                }
                ++this.mySize;
                ++this.modCount;
                return;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + v + ", Size: " + this.mySize);
    }

    @Override
    public boolean add(Object object0) {
        int v = this.mySize;
        if(v == 0) {
            this.myElem = object0;
        }
        else if(v == 1) {
            this.myElem = new Object[]{this.myElem, object0};
        }
        else {
            Object[] arr_object = (Object[])this.myElem;
            if(v >= arr_object.length) {
                Object[] arr_object1 = new Object[(arr_object.length * 3 / 2 + 1 >= v + 1 ? arr_object.length * 3 / 2 + 1 : v + 1)];
                this.myElem = arr_object1;
                System.arraycopy(arr_object, 0, arr_object1, 0, arr_object.length);
                arr_object = arr_object1;
            }
            arr_object[this.mySize] = object0;
        }
        ++this.mySize;
        ++this.modCount;
        return true;
    }

    @Override
    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        ++this.modCount;
    }

    @Override
    public Object get(int v) {
        if(v >= 0) {
            int v1 = this.mySize;
            if(v < v1) {
                return v1 == 1 ? this.myElem : ((Object[])this.myElem)[v];
            }
        }
        throw new IndexOutOfBoundsException("Index: " + v + ", Size: " + this.mySize);
    }

    @Override
    public Iterator iterator() {
        int v = this.mySize;
        if(v == 0) {
            Iterator iterator0 = EmptyIterator.getInstance();
            if(iterator0 == null) {
                SmartList.$$$reportNull$$$0(2);
            }
            return iterator0;
        }
        if(v == 1) {
            return new SingletonIterator(this);
        }
        Iterator iterator1 = super.iterator();
        if(iterator1 == null) {
            SmartList.$$$reportNull$$$0(3);
        }
        return iterator1;
    }

    @Override
    public Object remove(int v) {
        Object object0;
        if(v >= 0) {
            int v1 = this.mySize;
            if(v < v1) {
                if(v1 == 1) {
                    object0 = this.myElem;
                    this.myElem = null;
                }
                else {
                    Object[] arr_object = (Object[])this.myElem;
                    Object object1 = arr_object[v];
                    if(v1 == 2) {
                        this.myElem = arr_object[1 - v];
                    }
                    else {
                        int v2 = v1 - v - 1;
                        if(v2 > 0) {
                            System.arraycopy(arr_object, v + 1, arr_object, v, v2);
                        }
                        arr_object[this.mySize - 1] = null;
                    }
                    object0 = object1;
                }
                --this.mySize;
                ++this.modCount;
                return object0;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + v + ", Size: " + this.mySize);
    }

    @Override
    public Object set(int v, Object object0) {
        if(v >= 0) {
            int v1 = this.mySize;
            if(v < v1) {
                if(v1 == 1) {
                    Object object1 = this.myElem;
                    this.myElem = object0;
                    return object1;
                }
                Object[] arr_object = (Object[])this.myElem;
                Object object2 = arr_object[v];
                arr_object[v] = object0;
                return object2;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + v + ", Size: " + this.mySize);
    }

    @Override
    public int size() {
        return this.mySize;
    }

    @Override
    public void sort(Comparator comparator0) {
        int v = this.mySize;
        if(v >= 2) {
            Arrays.sort(((Object[])this.myElem), 0, v, comparator0);
        }
    }

    @Override
    public Object[] toArray(Object[] arr_object) {
        if(arr_object == null) {
            SmartList.$$$reportNull$$$0(4);
        }
        int v = this.mySize;
        if(v == 1) {
            if(arr_object.length != 0) {
                arr_object[0] = this.myElem;
                goto label_17;
            }
            Object[] arr_object1 = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), 1);
            arr_object1[0] = this.myElem;
            return arr_object1;
        }
        else {
            if(arr_object.length < v) {
                Object[] arr_object2 = Arrays.copyOf(((Object[])this.myElem), v, arr_object.getClass());
                if(arr_object2 == null) {
                    SmartList.$$$reportNull$$$0(6);
                }
                return arr_object2;
            }
            if(v != 0) {
                System.arraycopy(this.myElem, 0, arr_object, 0, v);
            }
        }
    label_17:
        int v1 = this.mySize;
        if(arr_object.length > v1) {
            arr_object[v1] = null;
        }
        if(arr_object == null) {
            SmartList.$$$reportNull$$$0(7);
        }
        return arr_object;
    }

    class kotlin.reflect.jvm.internal.impl.utils.SmartList.1 {
    }

}

