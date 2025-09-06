package com.google.crypto.tink.shaded.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractProtobufList implements LazyStringList, RandomAccess {
    static class ByteArrayListView extends AbstractList implements RandomAccess {
        private final LazyStringArrayList list;

        ByteArrayListView(LazyStringArrayList lazyStringArrayList0) {
            this.list = lazyStringArrayList0;
        }

        @Override
        public void add(int v, Object object0) {
            this.add(v, ((byte[])object0));
        }

        public void add(int v, byte[] arr_b) {
            this.list.add(v, arr_b);
            ++this.modCount;
        }

        @Override
        public Object get(int v) {
            return this.get(v);
        }

        public byte[] get(int v) {
            return this.list.getByteArray(v);
        }

        @Override
        public Object remove(int v) {
            return this.remove(v);
        }

        public byte[] remove(int v) {
            String s = this.list.remove(v);
            ++this.modCount;
            return LazyStringArrayList.asByteArray(s);
        }

        @Override
        public Object set(int v, Object object0) {
            return this.set(v, ((byte[])object0));
        }

        public byte[] set(int v, byte[] arr_b) {
            Object object0 = this.list.setAndReturn(v, arr_b);
            ++this.modCount;
            return LazyStringArrayList.asByteArray(object0);
        }

        @Override
        public int size() {
            return this.list.size();
        }
    }

    static class ByteStringListView extends AbstractList implements RandomAccess {
        private final LazyStringArrayList list;

        ByteStringListView(LazyStringArrayList lazyStringArrayList0) {
            this.list = lazyStringArrayList0;
        }

        public void add(int v, ByteString byteString0) {
            this.list.add(v, byteString0);
            ++this.modCount;
        }

        @Override
        public void add(int v, Object object0) {
            this.add(v, ((ByteString)object0));
        }

        public ByteString get(int v) {
            return this.list.getByteString(v);
        }

        @Override
        public Object get(int v) {
            return this.get(v);
        }

        public ByteString remove(int v) {
            String s = this.list.remove(v);
            ++this.modCount;
            return LazyStringArrayList.asByteString(s);
        }

        @Override
        public Object remove(int v) {
            return this.remove(v);
        }

        public ByteString set(int v, ByteString byteString0) {
            Object object0 = this.list.setAndReturn(v, byteString0);
            ++this.modCount;
            return LazyStringArrayList.asByteString(object0);
        }

        @Override
        public Object set(int v, Object object0) {
            return this.set(v, ((ByteString)object0));
        }

        @Override
        public int size() {
            return this.list.size();
        }
    }

    public static final LazyStringList EMPTY;
    private static final LazyStringArrayList EMPTY_LIST;
    private final List list;

    static {
        LazyStringArrayList lazyStringArrayList0 = new LazyStringArrayList();
        LazyStringArrayList.EMPTY_LIST = lazyStringArrayList0;
        lazyStringArrayList0.makeImmutable();
        LazyStringArrayList.EMPTY = lazyStringArrayList0;
    }

    public LazyStringArrayList() {
        this(10);
    }

    public LazyStringArrayList(int v) {
        this(new ArrayList(v));
    }

    public LazyStringArrayList(LazyStringList lazyStringList0) {
        this.list = new ArrayList(lazyStringList0.size());
        this.addAll(lazyStringList0);
    }

    private LazyStringArrayList(ArrayList arrayList0) {
        this.list = arrayList0;
    }

    public LazyStringArrayList(List list0) {
        this(new ArrayList(list0));
    }

    private void add(int v, ByteString byteString0) {
        this.ensureIsMutable();
        this.list.add(v, byteString0);
        ++this.modCount;
    }

    private void add(int v, byte[] arr_b) {
        this.ensureIsMutable();
        this.list.add(v, arr_b);
        ++this.modCount;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public void add(int v, Object object0) {
        this.add(v, ((String)object0));
    }

    public void add(int v, String s) {
        this.ensureIsMutable();
        this.list.add(v, s);
        ++this.modCount;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void add(ByteString byteString0) {
        this.ensureIsMutable();
        this.list.add(byteString0);
        ++this.modCount;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void add(byte[] arr_b) {
        this.ensureIsMutable();
        this.list.add(arr_b);
        ++this.modCount;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean add(Object object0) {
        return super.add(object0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean addAll(int v, Collection collection0) {
        this.ensureIsMutable();
        if(collection0 instanceof LazyStringList) {
            collection0 = ((LazyStringList)collection0).getUnderlyingElements();
        }
        boolean z = this.list.addAll(v, collection0);
        ++this.modCount;
        return z;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean addAll(Collection collection0) {
        return this.addAll(this.size(), collection0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public boolean addAllByteArray(Collection collection0) {
        this.ensureIsMutable();
        boolean z = this.list.addAll(collection0);
        ++this.modCount;
        return z;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public boolean addAllByteString(Collection collection0) {
        this.ensureIsMutable();
        boolean z = this.list.addAll(collection0);
        ++this.modCount;
        return z;
    }

    private static byte[] asByteArray(Object object0) {
        if(object0 instanceof byte[]) {
            return (byte[])object0;
        }
        return object0 instanceof String ? Internal.toByteArray(((String)object0)) : ((ByteString)object0).toByteArray();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public List asByteArrayList() {
        return new ByteArrayListView(this);
    }

    private static ByteString asByteString(Object object0) {
        if(object0 instanceof ByteString) {
            return (ByteString)object0;
        }
        return object0 instanceof String ? ByteString.copyFromUtf8(((String)object0)) : ByteString.copyFrom(((byte[])object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ProtocolStringList
    public List asByteStringList() {
        return new ByteStringListView(this);
    }

    private static String asString(Object object0) {
        if(object0 instanceof String) {
            return (String)object0;
        }
        return object0 instanceof ByteString ? ((ByteString)object0).toStringUtf8() : Internal.toStringUtf8(((byte[])object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public void clear() {
        this.ensureIsMutable();
        this.list.clear();
        ++this.modCount;
    }

    static LazyStringArrayList emptyList() {
        return LazyStringArrayList.EMPTY_LIST;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean equals(Object object0) {
        return super.equals(object0);
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

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public byte[] getByteArray(int v) {
        Object object0 = this.list.get(v);
        byte[] arr_b = LazyStringArrayList.asByteArray(object0);
        if(arr_b != object0) {
            this.list.set(v, arr_b);
        }
        return arr_b;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public ByteString getByteString(int v) {
        Object object0 = this.list.get(v);
        ByteString byteString0 = LazyStringArrayList.asByteString(object0);
        if(byteString0 != object0) {
            this.list.set(v, byteString0);
        }
        return byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public Object getRaw(int v) {
        return this.list.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public List getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return this.isModifiable() ? new UnmodifiableLazyStringList(this) : this;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public int hashCode() {
        return super.hashCode();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean isModifiable() {
        return super.isModifiable();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void mergeFrom(LazyStringList lazyStringList0) {
        this.ensureIsMutable();
        for(Object object0: lazyStringList0.getUnderlyingElements()) {
            if(object0 instanceof byte[]) {
                byte[] arr_b = Arrays.copyOf(((byte[])object0), ((byte[])object0).length);
                this.list.add(arr_b);
            }
            else {
                this.list.add(object0);
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$ProtobufList
    public ProtobufList mutableCopyWithCapacity(int v) {
        return this.mutableCopyWithCapacity(v);
    }

    public LazyStringArrayList mutableCopyWithCapacity(int v) {
        if(v < this.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList0 = new ArrayList(v);
        arrayList0.addAll(this.list);
        return new LazyStringArrayList(arrayList0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object remove(int v) {
        return this.remove(v);
    }

    public String remove(int v) {
        this.ensureIsMutable();
        Object object0 = this.list.remove(v);
        ++this.modCount;
        return LazyStringArrayList.asString(object0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean remove(Object object0) {
        return super.remove(object0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean removeAll(Collection collection0) {
        return super.removeAll(collection0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public boolean retainAll(Collection collection0) {
        return super.retainAll(collection0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList
    public Object set(int v, Object object0) {
        return this.set(v, ((String)object0));
    }

    public String set(int v, String s) {
        this.ensureIsMutable();
        return LazyStringArrayList.asString(this.list.set(v, s));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void set(int v, ByteString byteString0) {
        this.setAndReturn(v, byteString0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void set(int v, byte[] arr_b) {
        this.setAndReturn(v, arr_b);
    }

    private Object setAndReturn(int v, ByteString byteString0) {
        this.ensureIsMutable();
        return this.list.set(v, byteString0);
    }

    private Object setAndReturn(int v, byte[] arr_b) {
        this.ensureIsMutable();
        return this.list.set(v, arr_b);
    }

    @Override
    public int size() {
        return this.list.size();
    }
}

