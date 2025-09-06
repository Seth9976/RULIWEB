package com.google.gson.internal;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap extends AbstractMap implements Serializable {
    class EntrySet extends AbstractSet {
        @Override
        public void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return object0 instanceof Map.Entry && LinkedTreeMap.this.findByEntry(((Map.Entry)object0)) != null;
        }

        @Override
        public Iterator iterator() {
            return new LinkedTreeMapIterator() {
                @Override
                public Object next() {
                    return this.next();
                }

                public Map.Entry next() {
                    return this.nextNode();
                }
            };
        }

        @Override
        public boolean remove(Object object0) {
            if(!(object0 instanceof Map.Entry)) {
                return false;
            }
            Node linkedTreeMap$Node0 = LinkedTreeMap.this.findByEntry(((Map.Entry)object0));
            if(linkedTreeMap$Node0 == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(linkedTreeMap$Node0, true);
            return true;
        }

        @Override
        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    final class KeySet extends AbstractSet {
        @Override
        public void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return LinkedTreeMap.this.containsKey(object0);
        }

        @Override
        public Iterator iterator() {
            return new LinkedTreeMapIterator() {
                @Override
                public Object next() {
                    return this.nextNode().key;
                }
            };
        }

        @Override
        public boolean remove(Object object0) {
            return LinkedTreeMap.this.removeInternalByKey(object0) != null;
        }

        @Override
        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    abstract class LinkedTreeMapIterator implements Iterator {
        int expectedModCount;
        Node lastReturned;
        Node next;

        LinkedTreeMapIterator() {
            this.next = linkedTreeMap0.header.next;
            this.lastReturned = null;
            this.expectedModCount = linkedTreeMap0.modCount;
        }

        @Override
        public final boolean hasNext() {
            return this.next != LinkedTreeMap.this.header;
        }

        final Node nextNode() {
            Node linkedTreeMap$Node0 = this.next;
            if(linkedTreeMap$Node0 == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            }
            if(LinkedTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.next = linkedTreeMap$Node0.next;
            this.lastReturned = linkedTreeMap$Node0;
            return linkedTreeMap$Node0;
        }

        @Override
        public final void remove() {
            Node linkedTreeMap$Node0 = this.lastReturned;
            if(linkedTreeMap$Node0 == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(linkedTreeMap$Node0, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }
    }

    static final class Node implements Map.Entry {
        int height;
        final Object key;
        Node left;
        Node next;
        Node parent;
        Node prev;
        Node right;
        Object value;

        Node() {
            this.key = null;
            this.prev = this;
            this.next = this;
        }

        Node(Node linkedTreeMap$Node0, Object object0, Node linkedTreeMap$Node1, Node linkedTreeMap$Node2) {
            this.parent = linkedTreeMap$Node0;
            this.key = object0;
            this.height = 1;
            this.next = linkedTreeMap$Node1;
            this.prev = linkedTreeMap$Node2;
            linkedTreeMap$Node2.next = this;
            linkedTreeMap$Node1.prev = this;
        }

        @Override
        public boolean equals(Object object0) {
            if(object0 instanceof Map.Entry) {
                Object object1 = this.key;
                if(object1 == null) {
                    if(((Map.Entry)object0).getKey() == null) {
                        return this.value == null ? ((Map.Entry)object0).getValue() == null : this.value.equals(((Map.Entry)object0).getValue());
                    }
                }
                else if(object1.equals(((Map.Entry)object0).getKey())) {
                    return this.value == null ? ((Map.Entry)object0).getValue() == null : this.value.equals(((Map.Entry)object0).getValue());
                }
            }
            return false;
        }

        public Node first() {
            Node linkedTreeMap$Node0 = this.left;
            Node linkedTreeMap$Node1 = this;
            while(linkedTreeMap$Node0 != null) {
                linkedTreeMap$Node1 = linkedTreeMap$Node0;
                linkedTreeMap$Node0 = linkedTreeMap$Node0.left;
            }
            return linkedTreeMap$Node1;
        }

        @Override
        public Object getKey() {
            return this.key;
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

        public Node last() {
            Node linkedTreeMap$Node0 = this.right;
            Node linkedTreeMap$Node1 = this;
            while(linkedTreeMap$Node0 != null) {
                linkedTreeMap$Node1 = linkedTreeMap$Node0;
                linkedTreeMap$Node0 = linkedTreeMap$Node0.right;
            }
            return linkedTreeMap$Node1;
        }

        @Override
        public Object setValue(Object object0) {
            Object object1 = this.value;
            this.value = object0;
            return object1;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    static final boolean $assertionsDisabled;
    private static final Comparator NATURAL_ORDER;
    Comparator comparator;
    private EntrySet entrySet;
    final Node header;
    private KeySet keySet;
    int modCount;
    Node root;
    int size;

    static {
        LinkedTreeMap.NATURAL_ORDER = new Comparator() {
            public int compare(Comparable comparable0, Comparable comparable1) {
                return comparable0.compareTo(comparable1);
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Comparable)object0), ((Comparable)object1));
            }
        };
    }

    public LinkedTreeMap() {
        this(LinkedTreeMap.NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator comparator0) {
        this.size = 0;
        this.modCount = 0;
        this.header = new Node();
        if(comparator0 == null) {
            comparator0 = LinkedTreeMap.NATURAL_ORDER;
        }
        this.comparator = comparator0;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
        ++this.modCount;
        this.header.prev = this.header;
        this.header.next = this.header;
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.findByObject(object0) != null;
    }

    @Override
    public Set entrySet() {
        Set set0 = this.entrySet;
        if(set0 != null) {
            return set0;
        }
        EntrySet linkedTreeMap$EntrySet0 = new EntrySet(this);
        this.entrySet = linkedTreeMap$EntrySet0;
        return linkedTreeMap$EntrySet0;
    }

    // 去混淆评级： 低(20)
    private boolean equal(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    Node find(Object object0, boolean z) {
        Node linkedTreeMap$Node3;
        int v;
        Comparator comparator0 = this.comparator;
        Node linkedTreeMap$Node0 = this.root;
        if(linkedTreeMap$Node0 == null) {
            v = 0;
        }
        else {
            Comparable comparable0 = comparator0 == LinkedTreeMap.NATURAL_ORDER ? ((Comparable)object0) : null;
            while(true) {
                v = comparable0 == null ? comparator0.compare(object0, linkedTreeMap$Node0.key) : comparable0.compareTo(linkedTreeMap$Node0.key);
                if(v == 0) {
                    return linkedTreeMap$Node0;
                }
                Node linkedTreeMap$Node1 = v >= 0 ? linkedTreeMap$Node0.right : linkedTreeMap$Node0.left;
                if(linkedTreeMap$Node1 == null) {
                    break;
                }
                linkedTreeMap$Node0 = linkedTreeMap$Node1;
            }
        }
        if(!z) {
            return null;
        }
        Node linkedTreeMap$Node2 = this.header;
        if(linkedTreeMap$Node0 == null) {
            if(comparator0 == LinkedTreeMap.NATURAL_ORDER && !(object0 instanceof Comparable)) {
                throw new ClassCastException(object0.getClass().getName() + " is not Comparable");
            }
            linkedTreeMap$Node3 = new Node(null, object0, linkedTreeMap$Node2, linkedTreeMap$Node2.prev);
            this.root = linkedTreeMap$Node3;
        }
        else {
            linkedTreeMap$Node3 = new Node(linkedTreeMap$Node0, object0, linkedTreeMap$Node2, linkedTreeMap$Node2.prev);
            if(v < 0) {
                linkedTreeMap$Node0.left = linkedTreeMap$Node3;
            }
            else {
                linkedTreeMap$Node0.right = linkedTreeMap$Node3;
            }
            this.rebalance(linkedTreeMap$Node0, true);
        }
        ++this.size;
        ++this.modCount;
        return linkedTreeMap$Node3;
    }

    Node findByEntry(Map.Entry map$Entry0) {
        Node linkedTreeMap$Node0 = this.findByObject(map$Entry0.getKey());
        return linkedTreeMap$Node0 == null || !this.equal(linkedTreeMap$Node0.value, map$Entry0.getValue()) ? null : linkedTreeMap$Node0;
    }

    Node findByObject(Object object0) {
        if(object0 != null) {
            try {
                return this.find(object0, false);
            }
            catch(ClassCastException unused_ex) {
            }
        }
        return null;
    }

    @Override
    public Object get(Object object0) {
        Node linkedTreeMap$Node0 = this.findByObject(object0);
        return linkedTreeMap$Node0 == null ? null : linkedTreeMap$Node0.value;
    }

    @Override
    public Set keySet() {
        Set set0 = this.keySet;
        if(set0 != null) {
            return set0;
        }
        KeySet linkedTreeMap$KeySet0 = new KeySet(this);
        this.keySet = linkedTreeMap$KeySet0;
        return linkedTreeMap$KeySet0;
    }

    @Override
    public Object put(Object object0, Object object1) {
        if(object0 == null) {
            throw new NullPointerException("key == null");
        }
        Node linkedTreeMap$Node0 = this.find(object0, true);
        Object object2 = linkedTreeMap$Node0.value;
        linkedTreeMap$Node0.value = object1;
        return object2;
    }

    private void readObject(ObjectInputStream objectInputStream0) throws IOException {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    private void rebalance(Node linkedTreeMap$Node0, boolean z) {
    alab1:
        while(linkedTreeMap$Node0 != null) {
            Node linkedTreeMap$Node1 = linkedTreeMap$Node0.left;
            Node linkedTreeMap$Node2 = linkedTreeMap$Node0.right;
            int v = 0;
            int v1 = linkedTreeMap$Node1 == null ? 0 : linkedTreeMap$Node1.height;
            int v2 = linkedTreeMap$Node2 == null ? 0 : linkedTreeMap$Node2.height;
            int v3 = v1 - v2;
            if(v3 == -2) {
                Node linkedTreeMap$Node3 = linkedTreeMap$Node2.left;
                int v4 = linkedTreeMap$Node2.right == null ? 0 : linkedTreeMap$Node2.right.height;
                if(linkedTreeMap$Node3 != null) {
                    v = linkedTreeMap$Node3.height;
                }
                if(v - v4 != -1 && (v - v4 != 0 || z)) {
                    this.rotateRight(linkedTreeMap$Node2);
                }
                this.rotateLeft(linkedTreeMap$Node0);
                if(z) {
                    return;
                }
            }
            else {
                switch(v3) {
                    case 0: {
                        linkedTreeMap$Node0.height = v1 + 1;
                        if(z) {
                            return;
                        }
                        break;
                    }
                    case 2: {
                        Node linkedTreeMap$Node4 = linkedTreeMap$Node1.left;
                        int v5 = linkedTreeMap$Node1.right == null ? 0 : linkedTreeMap$Node1.right.height;
                        if(linkedTreeMap$Node4 != null) {
                            v = linkedTreeMap$Node4.height;
                        }
                        if(v - v5 != 1 && (v - v5 != 0 || z)) {
                            this.rotateLeft(linkedTreeMap$Node1);
                        }
                        this.rotateRight(linkedTreeMap$Node0);
                        if(z) {
                            return;
                        }
                        break;
                    }
                    default: {
                        linkedTreeMap$Node0.height = Math.max(v1, v2) + 1;
                        if(!z) {
                            break alab1;
                        }
                    }
                }
            }
            linkedTreeMap$Node0 = linkedTreeMap$Node0.parent;
        }
    }

    @Override
    public Object remove(Object object0) {
        Node linkedTreeMap$Node0 = this.removeInternalByKey(object0);
        return linkedTreeMap$Node0 == null ? null : linkedTreeMap$Node0.value;
    }

    void removeInternal(Node linkedTreeMap$Node0, boolean z) {
        int v1;
        if(z) {
            linkedTreeMap$Node0.prev.next = linkedTreeMap$Node0.next;
            linkedTreeMap$Node0.next.prev = linkedTreeMap$Node0.prev;
        }
        Node linkedTreeMap$Node1 = linkedTreeMap$Node0.left;
        Node linkedTreeMap$Node2 = linkedTreeMap$Node0.right;
        Node linkedTreeMap$Node3 = linkedTreeMap$Node0.parent;
        int v = 0;
        if(linkedTreeMap$Node1 != null && linkedTreeMap$Node2 != null) {
            Node linkedTreeMap$Node4 = linkedTreeMap$Node1.height <= linkedTreeMap$Node2.height ? linkedTreeMap$Node2.first() : linkedTreeMap$Node1.last();
            this.removeInternal(linkedTreeMap$Node4, false);
            Node linkedTreeMap$Node5 = linkedTreeMap$Node0.left;
            if(linkedTreeMap$Node5 == null) {
                v1 = 0;
            }
            else {
                v1 = linkedTreeMap$Node5.height;
                linkedTreeMap$Node4.left = linkedTreeMap$Node5;
                linkedTreeMap$Node5.parent = linkedTreeMap$Node4;
                linkedTreeMap$Node0.left = null;
            }
            Node linkedTreeMap$Node6 = linkedTreeMap$Node0.right;
            if(linkedTreeMap$Node6 != null) {
                v = linkedTreeMap$Node6.height;
                linkedTreeMap$Node4.right = linkedTreeMap$Node6;
                linkedTreeMap$Node6.parent = linkedTreeMap$Node4;
                linkedTreeMap$Node0.right = null;
            }
            linkedTreeMap$Node4.height = Math.max(v1, v) + 1;
            this.replaceInParent(linkedTreeMap$Node0, linkedTreeMap$Node4);
            return;
        }
        if(linkedTreeMap$Node1 != null) {
            this.replaceInParent(linkedTreeMap$Node0, linkedTreeMap$Node1);
            linkedTreeMap$Node0.left = null;
        }
        else if(linkedTreeMap$Node2 == null) {
            this.replaceInParent(linkedTreeMap$Node0, null);
        }
        else {
            this.replaceInParent(linkedTreeMap$Node0, linkedTreeMap$Node2);
            linkedTreeMap$Node0.right = null;
        }
        this.rebalance(linkedTreeMap$Node3, false);
        --this.size;
        ++this.modCount;
    }

    Node removeInternalByKey(Object object0) {
        Node linkedTreeMap$Node0 = this.findByObject(object0);
        if(linkedTreeMap$Node0 != null) {
            this.removeInternal(linkedTreeMap$Node0, true);
        }
        return linkedTreeMap$Node0;
    }

    private void replaceInParent(Node linkedTreeMap$Node0, Node linkedTreeMap$Node1) {
        Node linkedTreeMap$Node2 = linkedTreeMap$Node0.parent;
        linkedTreeMap$Node0.parent = null;
        if(linkedTreeMap$Node1 != null) {
            linkedTreeMap$Node1.parent = linkedTreeMap$Node2;
        }
        if(linkedTreeMap$Node2 != null) {
            if(linkedTreeMap$Node2.left == linkedTreeMap$Node0) {
                linkedTreeMap$Node2.left = linkedTreeMap$Node1;
                return;
            }
            linkedTreeMap$Node2.right = linkedTreeMap$Node1;
            return;
        }
        this.root = linkedTreeMap$Node1;
    }

    private void rotateLeft(Node linkedTreeMap$Node0) {
        Node linkedTreeMap$Node1 = linkedTreeMap$Node0.left;
        Node linkedTreeMap$Node2 = linkedTreeMap$Node0.right;
        Node linkedTreeMap$Node3 = linkedTreeMap$Node2.left;
        Node linkedTreeMap$Node4 = linkedTreeMap$Node2.right;
        linkedTreeMap$Node0.right = linkedTreeMap$Node3;
        if(linkedTreeMap$Node3 != null) {
            linkedTreeMap$Node3.parent = linkedTreeMap$Node0;
        }
        this.replaceInParent(linkedTreeMap$Node0, linkedTreeMap$Node2);
        linkedTreeMap$Node2.left = linkedTreeMap$Node0;
        linkedTreeMap$Node0.parent = linkedTreeMap$Node2;
        int v = 0;
        linkedTreeMap$Node0.height = Math.max((linkedTreeMap$Node1 == null ? 0 : linkedTreeMap$Node1.height), (linkedTreeMap$Node3 == null ? 0 : linkedTreeMap$Node3.height)) + 1;
        int v1 = linkedTreeMap$Node0.height;
        if(linkedTreeMap$Node4 != null) {
            v = linkedTreeMap$Node4.height;
        }
        linkedTreeMap$Node2.height = Math.max(v1, v) + 1;
    }

    private void rotateRight(Node linkedTreeMap$Node0) {
        Node linkedTreeMap$Node1 = linkedTreeMap$Node0.left;
        Node linkedTreeMap$Node2 = linkedTreeMap$Node0.right;
        Node linkedTreeMap$Node3 = linkedTreeMap$Node1.left;
        Node linkedTreeMap$Node4 = linkedTreeMap$Node1.right;
        linkedTreeMap$Node0.left = linkedTreeMap$Node4;
        if(linkedTreeMap$Node4 != null) {
            linkedTreeMap$Node4.parent = linkedTreeMap$Node0;
        }
        this.replaceInParent(linkedTreeMap$Node0, linkedTreeMap$Node1);
        linkedTreeMap$Node1.right = linkedTreeMap$Node0;
        linkedTreeMap$Node0.parent = linkedTreeMap$Node1;
        int v = 0;
        linkedTreeMap$Node0.height = Math.max((linkedTreeMap$Node2 == null ? 0 : linkedTreeMap$Node2.height), (linkedTreeMap$Node4 == null ? 0 : linkedTreeMap$Node4.height)) + 1;
        int v1 = linkedTreeMap$Node0.height;
        if(linkedTreeMap$Node3 != null) {
            v = linkedTreeMap$Node3.height;
        }
        linkedTreeMap$Node1.height = Math.max(v1, v) + 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}

