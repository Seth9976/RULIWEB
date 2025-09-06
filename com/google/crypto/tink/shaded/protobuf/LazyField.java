package com.google.crypto.tink.shaded.protobuf;

import java.util.Iterator;
import java.util.Map.Entry;

public class LazyField extends LazyFieldLite {
    static class LazyEntry implements Map.Entry {
        private Map.Entry entry;

        private LazyEntry(Map.Entry map$Entry0) {
            this.entry = map$Entry0;
        }

        LazyEntry(Map.Entry map$Entry0, com.google.crypto.tink.shaded.protobuf.LazyField.1 lazyField$10) {
            this(map$Entry0);
        }

        public LazyField getField() {
            return (LazyField)this.entry.getValue();
        }

        @Override
        public Object getKey() {
            return this.entry.getKey();
        }

        @Override
        public Object getValue() {
            LazyField lazyField0 = (LazyField)this.entry.getValue();
            return lazyField0 == null ? null : lazyField0.getValue();
        }

        @Override
        public Object setValue(Object object0) {
            if(!(object0 instanceof MessageLite)) {
                throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
            }
            return ((LazyField)this.entry.getValue()).setValue(((MessageLite)object0));
        }
    }

    static class LazyIterator implements Iterator {
        private Iterator iterator;

        public LazyIterator(Iterator iterator0) {
            this.iterator = iterator0;
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public Object next() {
            return this.next();
        }

        public Map.Entry next() {
            Object object0 = this.iterator.next();
            return ((Map.Entry)object0).getValue() instanceof LazyField ? new LazyEntry(((Map.Entry)object0), null) : ((Map.Entry)object0);
        }

        @Override
        public void remove() {
            this.iterator.remove();
        }
    }

    private final MessageLite defaultInstance;

    public LazyField(MessageLite messageLite0, ExtensionRegistryLite extensionRegistryLite0, ByteString byteString0) {
        super(extensionRegistryLite0, byteString0);
        this.defaultInstance = messageLite0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyFieldLite
    public boolean containsDefaultInstance() {
        return super.containsDefaultInstance() || this.value == this.defaultInstance;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyFieldLite
    public boolean equals(Object object0) {
        return this.getValue().equals(object0);
    }

    public MessageLite getValue() {
        return this.getValue(this.defaultInstance);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.LazyFieldLite
    public int hashCode() {
        return this.getValue().hashCode();
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    class com.google.crypto.tink.shaded.protobuf.LazyField.1 {
    }

}

