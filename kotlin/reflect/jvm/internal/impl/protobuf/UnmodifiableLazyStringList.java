package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList implements RandomAccess, LazyStringList {
    private final LazyStringList list;

    public UnmodifiableLazyStringList(LazyStringList lazyStringList0) {
        this.list = lazyStringList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public void add(ByteString byteString0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(int v) {
        return this.get(v);
    }

    public String get(int v) {
        return (String)this.list.get(v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public ByteString getByteString(int v) {
        return this.list.getByteString(v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public List getUnderlyingElements() {
        return this.list.getUnderlyingElements();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return this;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Iterator iter;

            {
                this.iter = unmodifiableLazyStringList0.list.iterator();
            }

            @Override
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override
            public Object next() {
                return this.next();
            }

            public String next() {
                return this.iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator listIterator(int v) {
        return new ListIterator() {
            ListIterator iter;

            {
                int v = v;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.iter = unmodifiableLazyStringList0.list.listIterator(v);
            }

            @Override
            public void add(Object object0) {
                this.add(((String)object0));
            }

            public void add(String s) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return this.iter.hasPrevious();
            }

            @Override
            public Object next() {
                return this.next();
            }

            public String next() {
                return (String)this.iter.next();
            }

            @Override
            public int nextIndex() {
                return this.iter.nextIndex();
            }

            @Override
            public Object previous() {
                return this.previous();
            }

            public String previous() {
                return (String)this.iter.previous();
            }

            @Override
            public int previousIndex() {
                return this.iter.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(Object object0) {
                this.set(((String)object0));
            }

            public void set(String s) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public int size() {
        return this.list.size();
    }
}

