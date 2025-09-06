package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ArrayMap extends SimpleArrayMap implements Map {
    final class EntrySet extends AbstractSet {
        @Override
        public Iterator iterator() {
            return new MapIterator(ArrayMap.this);
        }

        @Override
        public int size() {
            return ArrayMap.this.size();
        }
    }

    final class KeyIterator extends IndexBasedArrayIterator {
        KeyIterator() {
            super(arrayMap0.size());
        }

        @Override  // androidx.collection.IndexBasedArrayIterator
        protected Object elementAt(int v) {
            return ArrayMap.this.keyAt(v);
        }

        @Override  // androidx.collection.IndexBasedArrayIterator
        protected void removeAt(int v) {
            ArrayMap.this.removeAt(v);
        }
    }

    final class KeySet implements Set {
        @Override
        public boolean add(Object object0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection collection0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            ArrayMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return ArrayMap.this.containsKey(object0);
        }

        @Override
        public boolean containsAll(Collection collection0) {
            return ArrayMap.this.containsAll(collection0);
        }

        @Override
        public boolean equals(Object object0) {
            return ArrayMap.equalsSetHelper(this, object0);
        }

        @Override
        public int hashCode() {
            int v = ArrayMap.this.size() - 1;
            int v1 = 0;
            while(v >= 0) {
                Object object0 = ArrayMap.this.keyAt(v);
                v1 += (object0 == null ? 0 : object0.hashCode());
                --v;
            }
            return v1;
        }

        @Override
        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @Override
        public Iterator iterator() {
            return new KeyIterator(ArrayMap.this);
        }

        @Override
        public boolean remove(Object object0) {
            int v = ArrayMap.this.indexOfKey(object0);
            if(v >= 0) {
                ArrayMap.this.removeAt(v);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection collection0) {
            return ArrayMap.this.removeAll(collection0);
        }

        @Override
        public boolean retainAll(Collection collection0) {
            return ArrayMap.this.retainAll(collection0);
        }

        @Override
        public int size() {
            return ArrayMap.this.size();
        }

        @Override
        public Object[] toArray() {
            int v = ArrayMap.this.size();
            Object[] arr_object = new Object[v];
            for(int v1 = 0; v1 < v; ++v1) {
                arr_object[v1] = ArrayMap.this.keyAt(v1);
            }
            return arr_object;
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            int v = this.size();
            if(arr_object.length < v) {
                arr_object = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), v);
            }
            for(int v1 = 0; v1 < v; ++v1) {
                arr_object[v1] = ArrayMap.this.keyAt(v1);
            }
            if(arr_object.length > v) {
                arr_object[v] = null;
            }
            return arr_object;
        }
    }

    final class MapIterator implements Iterator, Map.Entry {
        int mEnd;
        boolean mEntryValid;
        int mIndex;

        MapIterator() {
            this.mEnd = arrayMap0.size() - 1;
            this.mIndex = -1;
        }

        @Override
        public boolean equals(Object object0) {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return object0 instanceof Map.Entry ? ContainerHelpersKt.equal(((Map.Entry)object0).getKey(), ArrayMap.this.keyAt(this.mIndex)) && ContainerHelpersKt.equal(((Map.Entry)object0).getValue(), ArrayMap.this.valueAt(this.mIndex)) : false;
        }

        @Override
        public Object getKey() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return ArrayMap.this.keyAt(this.mIndex);
        }

        @Override
        public Object getValue() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return ArrayMap.this.valueAt(this.mIndex);
        }

        @Override
        public boolean hasNext() {
            return this.mIndex < this.mEnd;
        }

        @Override
        public int hashCode() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            Object object0 = ArrayMap.this.keyAt(this.mIndex);
            Object object1 = ArrayMap.this.valueAt(this.mIndex);
            int v = 0;
            int v1 = object0 == null ? 0 : object0.hashCode();
            if(object1 != null) {
                v = object1.hashCode();
            }
            return v1 ^ v;
        }

        @Override
        public Object next() {
            return this.next();
        }

        public Map.Entry next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }
            ++this.mIndex;
            this.mEntryValid = true;
            return this;
        }

        @Override
        public void remove() {
            if(!this.mEntryValid) {
                throw new IllegalStateException();
            }
            ArrayMap.this.removeAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
        }

        @Override
        public Object setValue(Object object0) {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return ArrayMap.this.setValueAt(this.mIndex, object0);
        }

        @Override
        public String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }

    final class ValueCollection implements Collection {
        @Override
        public boolean add(Object object0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection collection0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            ArrayMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return ArrayMap.this.__restricted$indexOfValue(object0) >= 0;
        }

        @Override
        public boolean containsAll(Collection collection0) {
            for(Object object0: collection0) {
                if(!this.contains(object0)) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @Override
        public Iterator iterator() {
            return new ValueIterator(ArrayMap.this);
        }

        @Override
        public boolean remove(Object object0) {
            int v = ArrayMap.this.__restricted$indexOfValue(object0);
            if(v >= 0) {
                ArrayMap.this.removeAt(v);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection collection0) {
            int v = ArrayMap.this.size();
            boolean z = false;
            for(int v1 = 0; v1 < v; ++v1) {
                if(collection0.contains(ArrayMap.this.valueAt(v1))) {
                    ArrayMap.this.removeAt(v1);
                    --v1;
                    --v;
                    z = true;
                }
            }
            return z;
        }

        @Override
        public boolean retainAll(Collection collection0) {
            int v = ArrayMap.this.size();
            boolean z = false;
            for(int v1 = 0; v1 < v; ++v1) {
                if(!collection0.contains(ArrayMap.this.valueAt(v1))) {
                    ArrayMap.this.removeAt(v1);
                    --v1;
                    --v;
                    z = true;
                }
            }
            return z;
        }

        @Override
        public int size() {
            return ArrayMap.this.size();
        }

        @Override
        public Object[] toArray() {
            int v = ArrayMap.this.size();
            Object[] arr_object = new Object[v];
            for(int v1 = 0; v1 < v; ++v1) {
                arr_object[v1] = ArrayMap.this.valueAt(v1);
            }
            return arr_object;
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            int v = this.size();
            if(arr_object.length < v) {
                arr_object = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), v);
            }
            for(int v1 = 0; v1 < v; ++v1) {
                arr_object[v1] = ArrayMap.this.valueAt(v1);
            }
            if(arr_object.length > v) {
                arr_object[v] = null;
            }
            return arr_object;
        }
    }

    final class ValueIterator extends IndexBasedArrayIterator {
        ValueIterator() {
            super(arrayMap0.size());
        }

        @Override  // androidx.collection.IndexBasedArrayIterator
        protected Object elementAt(int v) {
            return ArrayMap.this.valueAt(v);
        }

        @Override  // androidx.collection.IndexBasedArrayIterator
        protected void removeAt(int v) {
            ArrayMap.this.removeAt(v);
        }
    }

    EntrySet mEntrySet;
    KeySet mKeySet;
    ValueCollection mValues;

    public ArrayMap() {
    }

    public ArrayMap(int v) {
        super(v);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap0) {
        super(simpleArrayMap0);
    }

    public boolean containsAll(Collection collection0) {
        for(Object object0: collection0) {
            if(!this.containsKey(object0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override  // androidx.collection.SimpleArrayMap
    public boolean containsKey(Object object0) {
        return super.containsKey(object0);
    }

    @Override  // androidx.collection.SimpleArrayMap
    public boolean containsValue(Object object0) {
        return super.containsValue(object0);
    }

    @Override
    public Set entrySet() {
        EntrySet arrayMap$EntrySet0 = this.mEntrySet;
        if(arrayMap$EntrySet0 == null) {
            arrayMap$EntrySet0 = new EntrySet(this);
            this.mEntrySet = arrayMap$EntrySet0;
        }
        return arrayMap$EntrySet0;
    }

    static boolean equalsSetHelper(Set set0, Object object0) {
        if(set0 == object0) {
            return true;
        }
        if(object0 instanceof Set) {
            Set set1 = (Set)object0;
            try {
                if(set0.size() == set1.size() && set0.containsAll(set1)) {
                    return true;
                }
            }
            catch(NullPointerException | ClassCastException unused_ex) {
            }
        }
        return false;
    }

    @Override  // androidx.collection.SimpleArrayMap
    public Object get(Object object0) {
        return super.get(object0);
    }

    @Override
    public Set keySet() {
        KeySet arrayMap$KeySet0 = this.mKeySet;
        if(arrayMap$KeySet0 == null) {
            arrayMap$KeySet0 = new KeySet(this);
            this.mKeySet = arrayMap$KeySet0;
        }
        return arrayMap$KeySet0;
    }

    @Override
    public void putAll(Map map0) {
        this.ensureCapacity(this.size() + map0.size());
        for(Object object0: map0.entrySet()) {
            this.put(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
        }
    }

    @Override  // androidx.collection.SimpleArrayMap
    public Object remove(Object object0) {
        return super.remove(object0);
    }

    public boolean removeAll(Collection collection0) {
        int v = this.size();
        for(Object object0: collection0) {
            this.remove(object0);
        }
        return v != this.size();
    }

    public boolean retainAll(Collection collection0) {
        int v = this.size();
        for(int v1 = this.size() - 1; v1 >= 0; --v1) {
            if(!collection0.contains(this.keyAt(v1))) {
                this.removeAt(v1);
            }
        }
        return v != this.size();
    }

    @Override
    public Collection values() {
        ValueCollection arrayMap$ValueCollection0 = this.mValues;
        if(arrayMap$ValueCollection0 == null) {
            arrayMap$ValueCollection0 = new ValueCollection(this);
            this.mValues = arrayMap$ValueCollection0;
        }
        return arrayMap$ValueCollection0;
    }
}

