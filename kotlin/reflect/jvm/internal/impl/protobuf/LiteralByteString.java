package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class LiteralByteString extends ByteString {
    class LiteralByteIterator implements ByteIterator {
        private final int limit;
        private int position;

        private LiteralByteIterator() {
            this.position = 0;
            this.limit = literalByteString0.size();
        }

        LiteralByteIterator(kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString.1 literalByteString$10) {
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
            try {
                int v = this.position;
                this.position = v + 1;
                return LiteralByteString.this.bytes[v];
            }
            catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
                throw new NoSuchElementException(arrayIndexOutOfBoundsException0.getMessage());
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected final byte[] bytes;
    private int hash;

    LiteralByteString(byte[] arr_b) {
        this.hash = 0;
        this.bytes = arr_b;
    }

    public byte byteAt(int v) {
        return this.bytes[v];
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected void copyToInternal(byte[] arr_b, int v, int v1, int v2) {
        System.arraycopy(this.bytes, v, arr_b, v1, v2);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof ByteString)) {
            return false;
        }
        if(this.size() != ((ByteString)object0).size()) {
            return false;
        }
        if(this.size() == 0) {
            return true;
        }
        if(object0 instanceof LiteralByteString) {
            return this.equalsRange(((LiteralByteString)object0), 0, this.size());
        }
        if(!(object0 instanceof RopeByteString)) {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + object0.getClass());
        }
        return object0.equals(this);
    }

    boolean equalsRange(LiteralByteString literalByteString0, int v, int v1) {
        if(v1 > literalByteString0.size()) {
            throw new IllegalArgumentException("Length too large: " + v1 + this.size());
        }
        if(v + v1 > literalByteString0.size()) {
            throw new IllegalArgumentException("Ran off end of other: " + v + ", " + v1 + ", " + literalByteString0.size());
        }
        byte[] arr_b = this.bytes;
        byte[] arr_b1 = literalByteString0.bytes;
        int v2 = this.getOffsetIntoBytes();
        int v3 = this.getOffsetIntoBytes();
        for(int v4 = literalByteString0.getOffsetIntoBytes() + v; v3 < v2 + v1; ++v4) {
            if(arr_b[v3] != arr_b1[v4]) {
                return false;
            }
            ++v3;
        }
        return true;
    }

    protected int getOffsetIntoBytes() {
        return 0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int getTreeDepth() {
        return 0;
    }

    static int hashCode(int v, byte[] arr_b, int v1, int v2) {
        for(int v3 = v1; v3 < v1 + v2; ++v3) {
            v = v * 0x1F + arr_b[v3];
        }
        return v;
    }

    @Override
    public int hashCode() {
        int v = this.hash;
        if(v == 0) {
            int v1 = this.size();
            v = this.partialHash(v1, 0, v1);
            if(v == 0) {
                v = 1;
            }
            this.hash = v;
        }
        return v;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected boolean isBalanced() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public boolean isValidUtf8() {
        int v = this.getOffsetIntoBytes();
        return Utf8.isValidUtf8(this.bytes, v, this.size() + v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public Iterator iterator() {
        return this.iterator();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public ByteIterator iterator() {
        return new LiteralByteIterator(this, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int partialHash(int v, int v1, int v2) {
        return LiteralByteString.hashCode(v, this.bytes, this.getOffsetIntoBytes() + v1, v2);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int partialIsValidUtf8(int v, int v1, int v2) {
        int v3 = this.getOffsetIntoBytes();
        return Utf8.partialIsValidUtf8(v, this.bytes, v3 + v1, v2 + (v3 + v1));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int peekCachedHashCode() {
        return this.hash;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public int size() {
        return this.bytes.length;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public String toString(String s) throws UnsupportedEncodingException {
        return new String(this.bytes, this.getOffsetIntoBytes(), this.size(), s);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    void writeToInternal(OutputStream outputStream0, int v, int v1) throws IOException {
        outputStream0.write(this.bytes, this.getOffsetIntoBytes() + v, v1);
    }

    class kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString.1 {
    }

}

