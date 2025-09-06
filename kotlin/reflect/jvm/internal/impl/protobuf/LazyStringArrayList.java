package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractList implements RandomAccess, LazyStringList {
    public static final LazyStringList EMPTY;
    private final List list;

    static {
        LazyStringArrayList.EMPTY = new LazyStringArrayList().getUnmodifiableView();
    }

    public LazyStringArrayList() {
        this.list = new ArrayList();
    }

    public LazyStringArrayList(LazyStringList lazyStringList0) {
        this.list = new ArrayList(lazyStringList0.size());
        this.addAll(lazyStringList0);
    }

    @Override
    public void add(int v, Object object0) {
        this.add(v, ((String)object0));
    }

    public void add(int v, String s) {
        this.list.add(v, s);
        ++this.modCount;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public void add(ByteString byteString0) {
        this.list.add(byteString0);
        ++this.modCount;
    }

    @Override
    public boolean addAll(int v, Collection collection0) {
        if(collection0 instanceof LazyStringList) {
            collection0 = ((LazyStringList)collection0).getUnderlyingElements();
        }
        boolean z = this.list.addAll(v, collection0);
        ++this.modCount;
        return z;
    }

    @Override
    public boolean addAll(Collection collection0) {
        return this.addAll(this.size(), collection0);
    }

    private static ByteString asByteString(Object object0) {
        if(object0 instanceof ByteString) {
            return (ByteString)object0;
        }
        return object0 instanceof String ? ByteString.copyFromUtf8(((String)object0)) : ByteString.copyFrom(((byte[])object0));
    }

    private static String asString(Object object0) {
        if(object0 instanceof String) {
            return (String)object0;
        }
        return object0 instanceof ByteString ? ((ByteString)object0).toStringUtf8() : Internal.toStringUtf8(((byte[])object0));
    }

    @Override
    public void clear() {
        this.list.clear();
        ++this.modCount;
    }

    @Override
    public Object get(int v) {
        return this.get(v);
    }

    public String get(int v) {
        Object object0 = this.list.get(v);
        if(object0 instanceof String) {
            return (String)object0;
        }
        if(object0 instanceof ByteString) {
            String s = ((ByteString)object0).toStringUtf8();
            if(((ByteString)object0).isValidUtf8()) {
                this.list.set(v, s);
            }
            return s;
        }
        String s1 = Internal.toStringUtf8(((byte[])object0));
        if(Internal.isValidUtf8(((byte[])object0))) {
            this.list.set(v, s1);
        }
        return s1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public ByteString getByteString(int v) {
        Object object0 = this.list.get(v);
        ByteString byteString0 = LazyStringArrayList.asByteString(object0);
        if(byteString0 != object0) {
            this.list.set(v, byteString0);
        }
        return byteString0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public List getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return new UnmodifiableLazyStringList(this);
    }

    @Override
    public Object remove(int v) {
        return this.remove(v);
    }

    public String remove(int v) {
        Object object0 = this.list.remove(v);
        ++this.modCount;
        return LazyStringArrayList.asString(object0);
    }

    @Override
    public Object set(int v, Object object0) {
        return this.set(v, ((String)object0));
    }

    public String set(int v, String s) {
        return LazyStringArrayList.asString(this.list.set(v, s));
    }

    @Override
    public int size() {
        return this.list.size();
    }
}

