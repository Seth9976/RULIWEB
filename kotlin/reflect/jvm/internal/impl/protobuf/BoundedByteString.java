package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Iterator;
import java.util.NoSuchElementException;

class BoundedByteString extends LiteralByteString {
    class BoundedByteIterator implements ByteIterator {
        private final int limit;
        private int position;

        private BoundedByteIterator() {
            int v = boundedByteString0.getOffsetIntoBytes();
            this.position = v;
            this.limit = v + boundedByteString0.size();
        }

        BoundedByteIterator(kotlin.reflect.jvm.internal.impl.protobuf.BoundedByteString.1 boundedByteString$10) {
        }

        @Override
        public boolean hasNext() {
            return this.position < this.limit;
        }

        public Byte next() {
            return this.nextByte();
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString$ByteIterator
        public byte nextByte() {
            if(this.position >= this.limit) {
                throw new NoSuchElementException();
            }
            int v = this.position;
            this.position = v + 1;
            return BoundedByteString.this.bytes[v];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final int bytesLength;
    private final int bytesOffset;

    BoundedByteString(byte[] arr_b, int v, int v1) {
        super(arr_b);
        if(v < 0) {
            throw new IllegalArgumentException("Offset too small: " + v);
        }
        if(v1 < 0) {
            throw new IllegalArgumentException("Length too small: " + v);
        }
        if(((long)v) + ((long)v1) > ((long)arr_b.length)) {
            throw new IllegalArgumentException("Offset+Length too large: " + v + "+" + v1);
        }
        this.bytesOffset = v;
        this.bytesLength = v1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public byte byteAt(int v) {
        if(v < 0) {
            throw new ArrayIndexOutOfBoundsException("Index too small: " + v);
        }
        if(v >= this.size()) {
            throw new ArrayIndexOutOfBoundsException("Index too large: " + v + ", " + this.size());
        }
        return this.bytes[this.bytesOffset + v];
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    protected void copyToInternal(byte[] arr_b, int v, int v1, int v2) {
        System.arraycopy(this.bytes, this.getOffsetIntoBytes() + v, arr_b, v1, v2);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    protected int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public Iterator iterator() {
        return this.iterator();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public ByteIterator iterator() {
        return new BoundedByteIterator(this, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public int size() {
        return this.bytesLength;
    }

    class kotlin.reflect.jvm.internal.impl.protobuf.BoundedByteString.1 {
    }

}

