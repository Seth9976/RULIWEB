package com.google.gson.internal;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap extends AbstractMap implements Serializable {
    static final class AvlBuilder {
        private int leavesSkipped;
        private int leavesToSkip;
        private int size;
        private Node stack;

        void add(Node linkedHashTreeMap$Node0) {
            linkedHashTreeMap$Node0.right = null;
            linkedHashTreeMap$Node0.parent = null;
            linkedHashTreeMap$Node0.left = null;
            linkedHashTreeMap$Node0.height = 1;
            int v = this.leavesToSkip;
            if(v > 0) {
                int v1 = this.size;
                if((v1 & 1) == 0) {
                    this.size = v1 + 1;
                    this.leavesToSkip = v - 1;
                    ++this.leavesSkipped;
                }
            }
            linkedHashTreeMap$Node0.parent = this.stack;
            this.stack = linkedHashTreeMap$Node0;
            int v2 = this.size;
            this.size = v2 + 1;
            int v3 = this.leavesToSkip;
            if(v3 > 0 && (v2 + 1 & 1) == 0) {
                this.size = v2 + 2;
                this.leavesToSkip = v3 - 1;
                ++this.leavesSkipped;
            }
            for(int v4 = 4; (this.size & v4 - 1) == v4 - 1; v4 *= 2) {
                int v5 = this.leavesSkipped;
                if(v5 == 0) {
                    Node linkedHashTreeMap$Node1 = this.stack;
                    Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node1.parent;
                    Node linkedHashTreeMap$Node3 = linkedHashTreeMap$Node2.parent;
                    linkedHashTreeMap$Node2.parent = linkedHashTreeMap$Node3.parent;
                    this.stack = linkedHashTreeMap$Node2;
                    linkedHashTreeMap$Node2.left = linkedHashTreeMap$Node3;
                    linkedHashTreeMap$Node2.right = linkedHashTreeMap$Node1;
                    linkedHashTreeMap$Node2.height = linkedHashTreeMap$Node1.height + 1;
                    linkedHashTreeMap$Node3.parent = linkedHashTreeMap$Node2;
                    linkedHashTreeMap$Node1.parent = linkedHashTreeMap$Node2;
                }
                else {
                    switch(v5) {
                        case 1: {
                            Node linkedHashTreeMap$Node4 = this.stack;
                            this.stack = linkedHashTreeMap$Node4.parent;
                            linkedHashTreeMap$Node4.parent.right = linkedHashTreeMap$Node4;
                            linkedHashTreeMap$Node4.parent.height = linkedHashTreeMap$Node4.height + 1;
                            linkedHashTreeMap$Node4.parent = linkedHashTreeMap$Node4.parent;
                            this.leavesSkipped = 0;
                            break;
                        }
                        case 2: {
                            this.leavesSkipped = 0;
                        }
                    }
                }
            }
        }

        void reset(int v) {
            this.leavesToSkip = Integer.highestOneBit(v) * 2 - 1 - v;
            this.size = 0;
            this.leavesSkipped = 0;
            this.stack = null;
        }

        Node root() {
            Node linkedHashTreeMap$Node0 = this.stack;
            if(linkedHashTreeMap$Node0.parent != null) {
                throw new IllegalStateException();
            }
            return linkedHashTreeMap$Node0;
        }
    }

    static class AvlIterator {
        private Node stackTop;

        public Node next() {
            Node linkedHashTreeMap$Node4;
            Node linkedHashTreeMap$Node0 = this.stackTop;
            if(linkedHashTreeMap$Node0 == null) {
                return null;
            }
            Node linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0.parent;
            linkedHashTreeMap$Node0.parent = null;
            for(Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node0.right; true; linkedHashTreeMap$Node2 = linkedHashTreeMap$Node1.left) {
                Node linkedHashTreeMap$Node3 = linkedHashTreeMap$Node1;
                linkedHashTreeMap$Node1 = linkedHashTreeMap$Node2;
                linkedHashTreeMap$Node4 = linkedHashTreeMap$Node3;
                if(linkedHashTreeMap$Node1 == null) {
                    break;
                }
                linkedHashTreeMap$Node1.parent = linkedHashTreeMap$Node4;
            }
            this.stackTop = linkedHashTreeMap$Node4;
            return linkedHashTreeMap$Node0;
        }

        void reset(Node linkedHashTreeMap$Node0) {
            Node linkedHashTreeMap$Node1 = null;
            while(linkedHashTreeMap$Node0 != null) {
                linkedHashTreeMap$Node0.parent = linkedHashTreeMap$Node1;
                linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0;
                linkedHashTreeMap$Node0 = linkedHashTreeMap$Node0.left;
            }
            this.stackTop = linkedHashTreeMap$Node1;
        }
    }

    final class EntrySet extends AbstractSet {
        @Override
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return object0 instanceof Map.Entry && LinkedHashTreeMap.this.findByEntry(((Map.Entry)object0)) != null;
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
            Node linkedHashTreeMap$Node0 = LinkedHashTreeMap.this.findByEntry(((Map.Entry)object0));
            if(linkedHashTreeMap$Node0 == null) {
                return false;
            }
            LinkedHashTreeMap.this.removeInternal(linkedHashTreeMap$Node0, true);
            return true;
        }

        @Override
        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    final class KeySet extends AbstractSet {
        @Override
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return LinkedHashTreeMap.this.containsKey(object0);
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
            return LinkedHashTreeMap.this.removeInternalByKey(object0) != null;
        }

        @Override
        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    abstract class LinkedTreeMapIterator implements Iterator {
        int expectedModCount;
        Node lastReturned;
        Node next;

        LinkedTreeMapIterator() {
            this.next = linkedHashTreeMap0.header.next;
            this.lastReturned = null;
            this.expectedModCount = linkedHashTreeMap0.modCount;
        }

        @Override
        public final boolean hasNext() {
            return this.next != LinkedHashTreeMap.this.header;
        }

        final Node nextNode() {
            Node linkedHashTreeMap$Node0 = this.next;
            if(linkedHashTreeMap$Node0 == LinkedHashTreeMap.this.header) {
                throw new NoSuchElementException();
            }
            if(LinkedHashTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.next = linkedHashTreeMap$Node0.next;
            this.lastReturned = linkedHashTreeMap$Node0;
            return linkedHashTreeMap$Node0;
        }

        @Override
        public final void remove() {
            Node linkedHashTreeMap$Node0 = this.lastReturned;
            if(linkedHashTreeMap$Node0 == null) {
                throw new IllegalStateException();
            }
            LinkedHashTreeMap.this.removeInternal(linkedHashTreeMap$Node0, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedHashTreeMap.this.modCount;
        }
    }

    static final class Node implements Map.Entry {
        final int hash;
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
            this.hash = -1;
            this.prev = this;
            this.next = this;
        }

        Node(Node linkedHashTreeMap$Node0, Object object0, int v, Node linkedHashTreeMap$Node1, Node linkedHashTreeMap$Node2) {
            this.parent = linkedHashTreeMap$Node0;
            this.key = object0;
            this.hash = v;
            this.height = 1;
            this.next = linkedHashTreeMap$Node1;
            this.prev = linkedHashTreeMap$Node2;
            linkedHashTreeMap$Node2.next = this;
            linkedHashTreeMap$Node1.prev = this;
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
            Node linkedHashTreeMap$Node0 = this.left;
            Node linkedHashTreeMap$Node1 = this;
            while(linkedHashTreeMap$Node0 != null) {
                linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0;
                linkedHashTreeMap$Node0 = linkedHashTreeMap$Node0.left;
            }
            return linkedHashTreeMap$Node1;
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
            Node linkedHashTreeMap$Node0 = this.right;
            Node linkedHashTreeMap$Node1 = this;
            while(linkedHashTreeMap$Node0 != null) {
                linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0;
                linkedHashTreeMap$Node0 = linkedHashTreeMap$Node0.right;
            }
            return linkedHashTreeMap$Node1;
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
    int size;
    Node[] table;
    int threshold;

    static {
        LinkedHashTreeMap.NATURAL_ORDER = new Comparator() {
            public int compare(Comparable comparable0, Comparable comparable1) {
                return comparable0.compareTo(comparable1);
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Comparable)object0), ((Comparable)object1));
            }
        };
    }

    public LinkedHashTreeMap() {
        this(LinkedHashTreeMap.NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator comparator0) {
        this.size = 0;
        this.modCount = 0;
        if(comparator0 == null) {
            comparator0 = LinkedHashTreeMap.NATURAL_ORDER;
        }
        this.comparator = comparator0;
        this.header = new Node();
        this.table = new Node[16];
        this.threshold = 12;
    }

    @Override
    public void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
        ++this.modCount;
        Node linkedHashTreeMap$Node0 = this.header;
        for(Node linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0.next; linkedHashTreeMap$Node1 != linkedHashTreeMap$Node0; linkedHashTreeMap$Node1 = linkedHashTreeMap$Node2) {
            Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node1.next;
            linkedHashTreeMap$Node1.prev = null;
            linkedHashTreeMap$Node1.next = null;
        }
        linkedHashTreeMap$Node0.prev = linkedHashTreeMap$Node0;
        linkedHashTreeMap$Node0.next = linkedHashTreeMap$Node0;
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.findByObject(object0) != null;
    }

    private void doubleCapacity() {
        Node[] arr_linkedHashTreeMap$Node = LinkedHashTreeMap.doubleCapacity(this.table);
        this.table = arr_linkedHashTreeMap$Node;
        this.threshold = arr_linkedHashTreeMap$Node.length / 2 + arr_linkedHashTreeMap$Node.length / 4;
    }

    static Node[] doubleCapacity(Node[] arr_linkedHashTreeMap$Node) {
        Node linkedHashTreeMap$Node2;
        Node[] arr_linkedHashTreeMap$Node1 = new Node[arr_linkedHashTreeMap$Node.length * 2];
        AvlIterator linkedHashTreeMap$AvlIterator0 = new AvlIterator();
        AvlBuilder linkedHashTreeMap$AvlBuilder0 = new AvlBuilder();
        AvlBuilder linkedHashTreeMap$AvlBuilder1 = new AvlBuilder();
        for(int v = 0; v < arr_linkedHashTreeMap$Node.length; ++v) {
            Node linkedHashTreeMap$Node0 = arr_linkedHashTreeMap$Node[v];
            if(linkedHashTreeMap$Node0 != null) {
                linkedHashTreeMap$AvlIterator0.reset(linkedHashTreeMap$Node0);
                int v1 = 0;
                int v2 = 0;
                Node linkedHashTreeMap$Node1;
                while((linkedHashTreeMap$Node1 = linkedHashTreeMap$AvlIterator0.next()) != null) {
                    if((linkedHashTreeMap$Node1.hash & arr_linkedHashTreeMap$Node.length) == 0) {
                        ++v1;
                    }
                    else {
                        ++v2;
                    }
                }
                linkedHashTreeMap$AvlBuilder0.reset(v1);
                linkedHashTreeMap$AvlBuilder1.reset(v2);
                linkedHashTreeMap$AvlIterator0.reset(linkedHashTreeMap$Node0);
                while(true) {
                    linkedHashTreeMap$Node2 = null;
                    Node linkedHashTreeMap$Node3 = linkedHashTreeMap$AvlIterator0.next();
                    if(linkedHashTreeMap$Node3 == null) {
                        break;
                    }
                    if((linkedHashTreeMap$Node3.hash & arr_linkedHashTreeMap$Node.length) == 0) {
                        linkedHashTreeMap$AvlBuilder0.add(linkedHashTreeMap$Node3);
                    }
                    else {
                        linkedHashTreeMap$AvlBuilder1.add(linkedHashTreeMap$Node3);
                    }
                }
                arr_linkedHashTreeMap$Node1[v] = v1 <= 0 ? null : linkedHashTreeMap$AvlBuilder0.root();
                int v3 = v + arr_linkedHashTreeMap$Node.length;
                if(v2 > 0) {
                    linkedHashTreeMap$Node2 = linkedHashTreeMap$AvlBuilder1.root();
                }
                arr_linkedHashTreeMap$Node1[v3] = linkedHashTreeMap$Node2;
            }
        }
        return arr_linkedHashTreeMap$Node1;
    }

    @Override
    public Set entrySet() {
        Set set0 = this.entrySet;
        if(set0 != null) {
            return set0;
        }
        EntrySet linkedHashTreeMap$EntrySet0 = new EntrySet(this);
        this.entrySet = linkedHashTreeMap$EntrySet0;
        return linkedHashTreeMap$EntrySet0;
    }

    // 去混淆评级： 低(20)
    private boolean equal(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    Node find(Object object0, boolean z) {
        Node linkedHashTreeMap$Node3;
        int v3;
        Comparator comparator0 = this.comparator;
        Node[] arr_linkedHashTreeMap$Node = this.table;
        int v = LinkedHashTreeMap.secondaryHash(object0.hashCode());
        int v1 = arr_linkedHashTreeMap$Node.length - 1 & v;
        Node linkedHashTreeMap$Node0 = arr_linkedHashTreeMap$Node[v1];
        if(linkedHashTreeMap$Node0 == null) {
            v3 = 0;
        }
        else {
            Comparable comparable0 = comparator0 == LinkedHashTreeMap.NATURAL_ORDER ? ((Comparable)object0) : null;
            while(true) {
                int v2 = comparable0 == null ? comparator0.compare(object0, linkedHashTreeMap$Node0.key) : comparable0.compareTo(linkedHashTreeMap$Node0.key);
                if(v2 == 0) {
                    return linkedHashTreeMap$Node0;
                }
                Node linkedHashTreeMap$Node1 = v2 >= 0 ? linkedHashTreeMap$Node0.right : linkedHashTreeMap$Node0.left;
                if(linkedHashTreeMap$Node1 == null) {
                    v3 = v2;
                    break;
                }
                linkedHashTreeMap$Node0 = linkedHashTreeMap$Node1;
            }
        }
        if(!z) {
            return null;
        }
        Node linkedHashTreeMap$Node2 = this.header;
        if(linkedHashTreeMap$Node0 == null) {
            if(comparator0 == LinkedHashTreeMap.NATURAL_ORDER && !(object0 instanceof Comparable)) {
                throw new ClassCastException(object0.getClass().getName() + " is not Comparable");
            }
            linkedHashTreeMap$Node3 = new Node(null, object0, v, linkedHashTreeMap$Node2, linkedHashTreeMap$Node2.prev);
            arr_linkedHashTreeMap$Node[v1] = linkedHashTreeMap$Node3;
        }
        else {
            linkedHashTreeMap$Node3 = new Node(linkedHashTreeMap$Node0, object0, v, linkedHashTreeMap$Node2, linkedHashTreeMap$Node2.prev);
            if(v3 < 0) {
                linkedHashTreeMap$Node0.left = linkedHashTreeMap$Node3;
            }
            else {
                linkedHashTreeMap$Node0.right = linkedHashTreeMap$Node3;
            }
            this.rebalance(linkedHashTreeMap$Node0, true);
        }
        int v4 = this.size;
        this.size = v4 + 1;
        if(v4 > this.threshold) {
            this.doubleCapacity();
        }
        ++this.modCount;
        return linkedHashTreeMap$Node3;
    }

    Node findByEntry(Map.Entry map$Entry0) {
        Node linkedHashTreeMap$Node0 = this.findByObject(map$Entry0.getKey());
        return linkedHashTreeMap$Node0 == null || !this.equal(linkedHashTreeMap$Node0.value, map$Entry0.getValue()) ? null : linkedHashTreeMap$Node0;
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
        Node linkedHashTreeMap$Node0 = this.findByObject(object0);
        return linkedHashTreeMap$Node0 == null ? null : linkedHashTreeMap$Node0.value;
    }

    @Override
    public Set keySet() {
        Set set0 = this.keySet;
        if(set0 != null) {
            return set0;
        }
        KeySet linkedHashTreeMap$KeySet0 = new KeySet(this);
        this.keySet = linkedHashTreeMap$KeySet0;
        return linkedHashTreeMap$KeySet0;
    }

    @Override
    public Object put(Object object0, Object object1) {
        if(object0 == null) {
            throw new NullPointerException("key == null");
        }
        Node linkedHashTreeMap$Node0 = this.find(object0, true);
        Object object2 = linkedHashTreeMap$Node0.value;
        linkedHashTreeMap$Node0.value = object1;
        return object2;
    }

    private void readObject(ObjectInputStream objectInputStream0) throws IOException {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    private void rebalance(Node linkedHashTreeMap$Node0, boolean z) {
    alab1:
        while(linkedHashTreeMap$Node0 != null) {
            Node linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0.left;
            Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node0.right;
            int v = 0;
            int v1 = linkedHashTreeMap$Node1 == null ? 0 : linkedHashTreeMap$Node1.height;
            int v2 = linkedHashTreeMap$Node2 == null ? 0 : linkedHashTreeMap$Node2.height;
            int v3 = v1 - v2;
            if(v3 == -2) {
                Node linkedHashTreeMap$Node3 = linkedHashTreeMap$Node2.left;
                int v4 = linkedHashTreeMap$Node2.right == null ? 0 : linkedHashTreeMap$Node2.right.height;
                if(linkedHashTreeMap$Node3 != null) {
                    v = linkedHashTreeMap$Node3.height;
                }
                if(v - v4 != -1 && (v - v4 != 0 || z)) {
                    this.rotateRight(linkedHashTreeMap$Node2);
                }
                this.rotateLeft(linkedHashTreeMap$Node0);
                if(z) {
                    return;
                }
            }
            else {
                switch(v3) {
                    case 0: {
                        linkedHashTreeMap$Node0.height = v1 + 1;
                        if(z) {
                            return;
                        }
                        break;
                    }
                    case 2: {
                        Node linkedHashTreeMap$Node4 = linkedHashTreeMap$Node1.left;
                        int v5 = linkedHashTreeMap$Node1.right == null ? 0 : linkedHashTreeMap$Node1.right.height;
                        if(linkedHashTreeMap$Node4 != null) {
                            v = linkedHashTreeMap$Node4.height;
                        }
                        if(v - v5 != 1 && (v - v5 != 0 || z)) {
                            this.rotateLeft(linkedHashTreeMap$Node1);
                        }
                        this.rotateRight(linkedHashTreeMap$Node0);
                        if(z) {
                            return;
                        }
                        break;
                    }
                    default: {
                        linkedHashTreeMap$Node0.height = Math.max(v1, v2) + 1;
                        if(!z) {
                            break alab1;
                        }
                    }
                }
            }
            linkedHashTreeMap$Node0 = linkedHashTreeMap$Node0.parent;
        }
    }

    @Override
    public Object remove(Object object0) {
        Node linkedHashTreeMap$Node0 = this.removeInternalByKey(object0);
        return linkedHashTreeMap$Node0 == null ? null : linkedHashTreeMap$Node0.value;
    }

    void removeInternal(Node linkedHashTreeMap$Node0, boolean z) {
        int v1;
        if(z) {
            linkedHashTreeMap$Node0.prev.next = linkedHashTreeMap$Node0.next;
            linkedHashTreeMap$Node0.next.prev = linkedHashTreeMap$Node0.prev;
            linkedHashTreeMap$Node0.prev = null;
            linkedHashTreeMap$Node0.next = null;
        }
        Node linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0.left;
        Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node0.right;
        Node linkedHashTreeMap$Node3 = linkedHashTreeMap$Node0.parent;
        int v = 0;
        if(linkedHashTreeMap$Node1 != null && linkedHashTreeMap$Node2 != null) {
            Node linkedHashTreeMap$Node4 = linkedHashTreeMap$Node1.height <= linkedHashTreeMap$Node2.height ? linkedHashTreeMap$Node2.first() : linkedHashTreeMap$Node1.last();
            this.removeInternal(linkedHashTreeMap$Node4, false);
            Node linkedHashTreeMap$Node5 = linkedHashTreeMap$Node0.left;
            if(linkedHashTreeMap$Node5 == null) {
                v1 = 0;
            }
            else {
                v1 = linkedHashTreeMap$Node5.height;
                linkedHashTreeMap$Node4.left = linkedHashTreeMap$Node5;
                linkedHashTreeMap$Node5.parent = linkedHashTreeMap$Node4;
                linkedHashTreeMap$Node0.left = null;
            }
            Node linkedHashTreeMap$Node6 = linkedHashTreeMap$Node0.right;
            if(linkedHashTreeMap$Node6 != null) {
                v = linkedHashTreeMap$Node6.height;
                linkedHashTreeMap$Node4.right = linkedHashTreeMap$Node6;
                linkedHashTreeMap$Node6.parent = linkedHashTreeMap$Node4;
                linkedHashTreeMap$Node0.right = null;
            }
            linkedHashTreeMap$Node4.height = Math.max(v1, v) + 1;
            this.replaceInParent(linkedHashTreeMap$Node0, linkedHashTreeMap$Node4);
            return;
        }
        if(linkedHashTreeMap$Node1 != null) {
            this.replaceInParent(linkedHashTreeMap$Node0, linkedHashTreeMap$Node1);
            linkedHashTreeMap$Node0.left = null;
        }
        else if(linkedHashTreeMap$Node2 == null) {
            this.replaceInParent(linkedHashTreeMap$Node0, null);
        }
        else {
            this.replaceInParent(linkedHashTreeMap$Node0, linkedHashTreeMap$Node2);
            linkedHashTreeMap$Node0.right = null;
        }
        this.rebalance(linkedHashTreeMap$Node3, false);
        --this.size;
        ++this.modCount;
    }

    Node removeInternalByKey(Object object0) {
        Node linkedHashTreeMap$Node0 = this.findByObject(object0);
        if(linkedHashTreeMap$Node0 != null) {
            this.removeInternal(linkedHashTreeMap$Node0, true);
        }
        return linkedHashTreeMap$Node0;
    }

    private void replaceInParent(Node linkedHashTreeMap$Node0, Node linkedHashTreeMap$Node1) {
        Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node0.parent;
        linkedHashTreeMap$Node0.parent = null;
        if(linkedHashTreeMap$Node1 != null) {
            linkedHashTreeMap$Node1.parent = linkedHashTreeMap$Node2;
        }
        if(linkedHashTreeMap$Node2 != null) {
            if(linkedHashTreeMap$Node2.left == linkedHashTreeMap$Node0) {
                linkedHashTreeMap$Node2.left = linkedHashTreeMap$Node1;
                return;
            }
            linkedHashTreeMap$Node2.right = linkedHashTreeMap$Node1;
            return;
        }
        this.table[linkedHashTreeMap$Node0.hash & this.table.length - 1] = linkedHashTreeMap$Node1;
    }

    private void rotateLeft(Node linkedHashTreeMap$Node0) {
        Node linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0.left;
        Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node0.right;
        Node linkedHashTreeMap$Node3 = linkedHashTreeMap$Node2.left;
        Node linkedHashTreeMap$Node4 = linkedHashTreeMap$Node2.right;
        linkedHashTreeMap$Node0.right = linkedHashTreeMap$Node3;
        if(linkedHashTreeMap$Node3 != null) {
            linkedHashTreeMap$Node3.parent = linkedHashTreeMap$Node0;
        }
        this.replaceInParent(linkedHashTreeMap$Node0, linkedHashTreeMap$Node2);
        linkedHashTreeMap$Node2.left = linkedHashTreeMap$Node0;
        linkedHashTreeMap$Node0.parent = linkedHashTreeMap$Node2;
        int v = 0;
        linkedHashTreeMap$Node0.height = Math.max((linkedHashTreeMap$Node1 == null ? 0 : linkedHashTreeMap$Node1.height), (linkedHashTreeMap$Node3 == null ? 0 : linkedHashTreeMap$Node3.height)) + 1;
        int v1 = linkedHashTreeMap$Node0.height;
        if(linkedHashTreeMap$Node4 != null) {
            v = linkedHashTreeMap$Node4.height;
        }
        linkedHashTreeMap$Node2.height = Math.max(v1, v) + 1;
    }

    private void rotateRight(Node linkedHashTreeMap$Node0) {
        Node linkedHashTreeMap$Node1 = linkedHashTreeMap$Node0.left;
        Node linkedHashTreeMap$Node2 = linkedHashTreeMap$Node0.right;
        Node linkedHashTreeMap$Node3 = linkedHashTreeMap$Node1.left;
        Node linkedHashTreeMap$Node4 = linkedHashTreeMap$Node1.right;
        linkedHashTreeMap$Node0.left = linkedHashTreeMap$Node4;
        if(linkedHashTreeMap$Node4 != null) {
            linkedHashTreeMap$Node4.parent = linkedHashTreeMap$Node0;
        }
        this.replaceInParent(linkedHashTreeMap$Node0, linkedHashTreeMap$Node1);
        linkedHashTreeMap$Node1.right = linkedHashTreeMap$Node0;
        linkedHashTreeMap$Node0.parent = linkedHashTreeMap$Node1;
        int v = 0;
        linkedHashTreeMap$Node0.height = Math.max((linkedHashTreeMap$Node2 == null ? 0 : linkedHashTreeMap$Node2.height), (linkedHashTreeMap$Node4 == null ? 0 : linkedHashTreeMap$Node4.height)) + 1;
        int v1 = linkedHashTreeMap$Node0.height;
        if(linkedHashTreeMap$Node3 != null) {
            v = linkedHashTreeMap$Node3.height;
        }
        linkedHashTreeMap$Node1.height = Math.max(v1, v) + 1;
    }

    private static int secondaryHash(int v) {
        int v1 = v ^ (v >>> 20 ^ v >>> 12);
        return v1 >>> 4 ^ (v1 >>> 7 ^ v1);
    }

    @Override
    public int size() {
        return this.size;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}

