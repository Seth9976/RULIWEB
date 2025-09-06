package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap extends AbstractMap {
    static class EmptySet {
        private static final Iterable ITERABLE;
        private static final Iterator ITERATOR;

        static {
            EmptySet.ITERATOR = new Iterator() {
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
                    throw new UnsupportedOperationException();
                }
            };
            EmptySet.ITERABLE = new Iterable() {
                @Override
                public Iterator iterator() {
                    return EmptySet.ITERATOR;
                }
            };
        }

        static Iterable iterable() {
            return EmptySet.ITERABLE;
        }
    }

    class Entry implements Comparable, Map.Entry {
        private final Comparable key;
        private Object value;

        Entry(Comparable comparable0, Object object0) {
            this.key = comparable0;
            this.value = object0;
        }

        Entry(Map.Entry map$Entry0) {
            this(((Comparable)map$Entry0.getKey()), map$Entry0.getValue());
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((Entry)object0));
        }

        public int compareTo(Entry smallSortedMap$Entry0) {
            return this.getKey().compareTo(smallSortedMap$Entry0.getKey());
        }

        private boolean equals(Object object0, Object object1) {
            return object0 == null ? object1 == null : object0.equals(object1);
        }

        @Override
        public boolean equals(Object object0) {
            if(object0 == this) {
                return true;
            }
            if(!(object0 instanceof Map.Entry)) {
                return false;
            }
            Object object1 = ((Map.Entry)object0).getKey();
            return this.equals(this.key, object1) && this.equals(this.value, ((Map.Entry)object0).getValue());
        }

        public Comparable getKey() {
            return this.key;
        }

        @Override
        public Object getKey() {
            return this.getKey();
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public int hashCode() {
            int v = 0;
            int v1 = this.key == null ? 0 : this.key.hashCode();
            Object object0 = this.value;
            if(object0 != null) {
                v = object0.hashCode();
            }
            return v1 ^ v;
        }

        @Override
        public Object setValue(Object object0) {
            SmallSortedMap.this.checkMutable();
            Object object1 = this.value;
            this.value = object0;
            return object1;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    class EntryIterator implements Iterator {
        private Iterator lazyOverflowIterator;
        private boolean nextCalledBeforeRemove;
        private int pos;

        private EntryIterator() {
            this.pos = -1;
        }

        EntryIterator(kotlin.reflect.jvm.internal.impl.protobuf.SmallSortedMap.1 smallSortedMap$10) {
        }

        private Iterator getOverflowIterator() {
            if(this.lazyOverflowIterator == null) {
                this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
            }
            return this.lazyOverflowIterator;
        }

        @Override
        public boolean hasNext() {
            return this.pos + 1 < SmallSortedMap.this.entryList.size() || this.getOverflowIterator().hasNext();
        }

        @Override
        public Object next() {
            return this.next();
        }

        public Map.Entry next() {
            this.nextCalledBeforeRemove = true;
            int v = this.pos + 1;
            this.pos = v;
            return v < SmallSortedMap.this.entryList.size() ? ((Map.Entry)SmallSortedMap.this.entryList.get(this.pos)) : this.getOverflowIterator().next();
        }

        @Override
        public void remove() {
            if(!this.nextCalledBeforeRemove) {
                throw new IllegalStateException("remove() was called before next()");
            }
            this.nextCalledBeforeRemove = false;
            SmallSortedMap.this.checkMutable();
            if(this.pos < SmallSortedMap.this.entryList.size()) {
                int v = this.pos;
                this.pos = v - 1;
                SmallSortedMap.this.removeArrayEntryAt(v);
                return;
            }
            this.getOverflowIterator().remove();
        }
    }

    class EntrySet extends AbstractSet {
        private EntrySet() {
        }

        EntrySet(kotlin.reflect.jvm.internal.impl.protobuf.SmallSortedMap.1 smallSortedMap$10) {
        }

        @Override
        public boolean add(Object object0) {
            return this.add(((Map.Entry)object0));
        }

        public boolean add(Map.Entry map$Entry0) {
            if(!this.contains(map$Entry0)) {
                Comparable comparable0 = (Comparable)map$Entry0.getKey();
                Object object0 = map$Entry0.getValue();
                SmallSortedMap.this.put(comparable0, object0);
                return true;
            }
            return false;
        }

        @Override
        public void clear() {
            SmallSortedMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            Object object1 = ((Map.Entry)object0).getKey();
            Object object2 = SmallSortedMap.this.get(object1);
            Object object3 = ((Map.Entry)object0).getValue();
            return object2 == object3 || object2 != null && object2.equals(object3);
        }

        @Override
        public Iterator iterator() {
            return new EntryIterator(SmallSortedMap.this, null);
        }

        @Override
        public boolean remove(Object object0) {
            if(this.contains(((Map.Entry)object0))) {
                Object object1 = ((Map.Entry)object0).getKey();
                SmallSortedMap.this.remove(object1);
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            return SmallSortedMap.this.size();
        }
    }

    private List entryList;
    private boolean isImmutable;
    private volatile EntrySet lazyEntrySet;
    private final int maxArraySize;
    private Map overflowEntries;

    private SmallSortedMap(int v) {
        this.maxArraySize = v;
        this.entryList = Collections.EMPTY_LIST;
        this.overflowEntries = Collections.EMPTY_MAP;
    }

    SmallSortedMap(int v, kotlin.reflect.jvm.internal.impl.protobuf.SmallSortedMap.1 smallSortedMap$10) {
        this(v);
    }

    private int binarySearchInArray(Comparable comparable0) {
        int v = this.entryList.size();
        int v1 = v - 1;
        if(v1 >= 0) {
            int v2 = comparable0.compareTo(((Entry)this.entryList.get(v1)).getKey());
            if(v2 > 0) {
                return -(v + 1);
            }
            if(v2 == 0) {
                return v1;
            }
        }
        int v3 = 0;
        while(v3 <= v1) {
            int v4 = (v3 + v1) / 2;
            int v5 = comparable0.compareTo(((Entry)this.entryList.get(v4)).getKey());
            if(v5 < 0) {
                v1 = v4 - 1;
                continue;
            }
            if(v5 > 0) {
                v3 = v4 + 1;
                continue;
            }
            return v4;
        }
        return -(v3 + 1);
    }

    private void checkMutable() {
        if(this.isImmutable) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void clear() {
        this.checkMutable();
        if(!this.entryList.isEmpty()) {
            this.entryList.clear();
        }
        if(!this.overflowEntries.isEmpty()) {
            this.overflowEntries.clear();
        }
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.binarySearchInArray(((Comparable)object0)) >= 0 || this.overflowEntries.containsKey(((Comparable)object0));
    }

    private void ensureEntryArrayMutable() {
        this.checkMutable();
        if(this.entryList.isEmpty() && !(this.entryList instanceof ArrayList)) {
            this.entryList = new ArrayList(this.maxArraySize);
        }
    }

    @Override
    public Set entrySet() {
        if(this.lazyEntrySet == null) {
            this.lazyEntrySet = new EntrySet(this, null);
        }
        return this.lazyEntrySet;
    }

    @Override
    public Object get(Object object0) {
        int v = this.binarySearchInArray(((Comparable)object0));
        return v < 0 ? this.overflowEntries.get(((Comparable)object0)) : ((Entry)this.entryList.get(v)).getValue();
    }

    public Map.Entry getArrayEntryAt(int v) {
        return (Map.Entry)this.entryList.get(v);
    }

    public int getNumArrayEntries() {
        return this.entryList.size();
    }

    public Iterable getOverflowEntries() {
        return this.overflowEntries.isEmpty() ? EmptySet.iterable() : this.overflowEntries.entrySet();
    }

    private SortedMap getOverflowEntriesMutable() {
        this.checkMutable();
        if(this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap)) {
            this.overflowEntries = new TreeMap();
        }
        return (SortedMap)this.overflowEntries;
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public void makeImmutable() {
        if(!this.isImmutable) {
            this.overflowEntries = this.overflowEntries.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.overflowEntries);
            this.isImmutable = true;
        }
    }

    static SmallSortedMap newFieldMap(int v) {
        return new SmallSortedMap(v) {
            {
                super(v, null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.SmallSortedMap
            public void makeImmutable() {
                if(!this.isImmutable()) {
                    for(int v = 0; v < this.getNumArrayEntries(); ++v) {
                        Map.Entry map$Entry0 = this.getArrayEntryAt(v);
                        if(((FieldDescriptorLite)map$Entry0.getKey()).isRepeated()) {
                            map$Entry0.setValue(Collections.unmodifiableList(((List)map$Entry0.getValue())));
                        }
                    }
                    for(Object object0: this.getOverflowEntries()) {
                        Map.Entry map$Entry1 = (Map.Entry)object0;
                        if(((FieldDescriptorLite)map$Entry1.getKey()).isRepeated()) {
                            map$Entry1.setValue(Collections.unmodifiableList(((List)map$Entry1.getValue())));
                        }
                    }
                }
                super.makeImmutable();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.SmallSortedMap
            public Object put(Object object0, Object object1) {
                return super.put(((FieldDescriptorLite)object0), object1);
            }
        };
    }

    public Object put(Comparable comparable0, Object object0) {
        this.checkMutable();
        int v = this.binarySearchInArray(comparable0);
        if(v >= 0) {
            return ((Entry)this.entryList.get(v)).setValue(object0);
        }
        this.ensureEntryArrayMutable();
        if(-(v + 1) >= this.maxArraySize) {
            return this.getOverflowEntriesMutable().put(comparable0, object0);
        }
        int v1 = this.maxArraySize;
        if(this.entryList.size() == v1) {
            Entry smallSortedMap$Entry0 = (Entry)this.entryList.remove(v1 - 1);
            this.getOverflowEntriesMutable().put(smallSortedMap$Entry0.getKey(), smallSortedMap$Entry0.getValue());
        }
        this.entryList.add(-(v + 1), new Entry(this, comparable0, object0));
        return null;
    }

    @Override
    public Object put(Object object0, Object object1) {
        return this.put(((Comparable)object0), object1);
    }

    @Override
    public Object remove(Object object0) {
        this.checkMutable();
        int v = this.binarySearchInArray(((Comparable)object0));
        if(v >= 0) {
            return this.removeArrayEntryAt(v);
        }
        return this.overflowEntries.isEmpty() ? null : this.overflowEntries.remove(((Comparable)object0));
    }

    private Object removeArrayEntryAt(int v) {
        this.checkMutable();
        Object object0 = ((Entry)this.entryList.remove(v)).getValue();
        if(!this.overflowEntries.isEmpty()) {
            Iterator iterator0 = this.getOverflowEntriesMutable().entrySet().iterator();
            List list0 = this.entryList;
            Object object1 = iterator0.next();
            list0.add(new Entry(this, ((Map.Entry)object1)));
            iterator0.remove();
        }
        return object0;
    }

    @Override
    public int size() {
        return this.entryList.size() + this.overflowEntries.size();
    }
}

