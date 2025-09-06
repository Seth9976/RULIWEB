package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class ByteString implements Iterable {
    public interface ByteIterator extends Iterator {
        byte nextByte();
    }

    public static final class Output extends OutputStream {
        private static final byte[] EMPTY_BYTE_ARRAY;
        private byte[] buffer;
        private int bufferPos;
        private final ArrayList flushedBuffers;
        private int flushedBuffersTotalBytes;
        private final int initialCapacity;

        static {
            Output.EMPTY_BYTE_ARRAY = new byte[0];
        }

        Output(int v) {
            if(v < 0) {
                throw new IllegalArgumentException("Buffer size < 0");
            }
            this.initialCapacity = v;
            this.flushedBuffers = new ArrayList();
            this.buffer = new byte[v];
        }

        private byte[] copyArray(byte[] arr_b, int v) {
            byte[] arr_b1 = new byte[v];
            System.arraycopy(arr_b, 0, arr_b1, 0, Math.min(arr_b.length, v));
            return arr_b1;
        }

        private void flushFullBuffer(int v) {
            LiteralByteString literalByteString0 = new LiteralByteString(this.buffer);
            this.flushedBuffers.add(literalByteString0);
            int v1 = this.flushedBuffersTotalBytes + this.buffer.length;
            this.flushedBuffersTotalBytes = v1;
            this.buffer = new byte[Math.max(this.initialCapacity, Math.max(v, v1 >>> 1))];
            this.bufferPos = 0;
        }

        private void flushLastBuffer() {
            int v = this.bufferPos;
            byte[] arr_b = this.buffer;
            if(v >= arr_b.length) {
                LiteralByteString literalByteString1 = new LiteralByteString(this.buffer);
                this.flushedBuffers.add(literalByteString1);
                this.buffer = Output.EMPTY_BYTE_ARRAY;
            }
            else if(v > 0) {
                LiteralByteString literalByteString0 = new LiteralByteString(this.copyArray(arr_b, v));
                this.flushedBuffers.add(literalByteString0);
            }
            this.flushedBuffersTotalBytes += this.bufferPos;
            this.bufferPos = 0;
        }

        public int size() {
            synchronized(this) {
            }
            return this.flushedBuffersTotalBytes + this.bufferPos;
        }

        public ByteString toByteString() {
            synchronized(this) {
                this.flushLastBuffer();
                return ByteString.copyFrom(this.flushedBuffers);
            }
        }

        @Override
        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.size());
        }

        @Override
        public void write(int v) {
            synchronized(this) {
                if(this.bufferPos == this.buffer.length) {
                    this.flushFullBuffer(1);
                }
                int v2 = this.bufferPos;
                this.bufferPos = v2 + 1;
                this.buffer[v2] = (byte)v;
            }
        }

        @Override
        public void write(byte[] arr_b, int v, int v1) {
            synchronized(this) {
                byte[] arr_b1 = this.buffer;
                int v3 = this.bufferPos;
                if(v1 <= arr_b1.length - v3) {
                    System.arraycopy(arr_b, v, arr_b1, v3, v1);
                    this.bufferPos += v1;
                }
                else {
                    int v4 = arr_b1.length - v3;
                    System.arraycopy(arr_b, v, arr_b1, v3, v4);
                    int v5 = v1 - v4;
                    this.flushFullBuffer(v5);
                    System.arraycopy(arr_b, v + v4, this.buffer, 0, v5);
                    this.bufferPos = v5;
                }
            }
        }
    }

    static final boolean $assertionsDisabled;
    public static final ByteString EMPTY;

    static {
        ByteString.EMPTY = new LiteralByteString(new byte[0]);
    }

    private static ByteString balancedConcat(Iterator iterator0, int v) {
        return v == 1 ? iterator0.next() : ByteString.balancedConcat(iterator0, v >>> 1).concat(ByteString.balancedConcat(iterator0, v - (v >>> 1)));
    }

    public ByteString concat(ByteString byteString0) {
        int v = this.size();
        int v1 = byteString0.size();
        if(((long)v) + ((long)v1) >= 0x7FFFFFFFL) {
            throw new IllegalArgumentException("ByteString would be too long: " + v + "+" + v1);
        }
        return RopeByteString.concatenate(this, byteString0);
    }

    public static ByteString copyFrom(Iterable iterable0) {
        Collection collection0;
        if(!(iterable0 instanceof Collection)) {
            collection0 = new ArrayList();
            for(Object object0: iterable0) {
                collection0.add(((ByteString)object0));
            }
            return collection0.isEmpty() ? ByteString.EMPTY : ByteString.balancedConcat(collection0.iterator(), collection0.size());
        }
        collection0 = (Collection)iterable0;
        return collection0.isEmpty() ? ByteString.EMPTY : ByteString.balancedConcat(collection0.iterator(), collection0.size());
    }

    public static ByteString copyFrom(byte[] arr_b) {
        return ByteString.copyFrom(arr_b, 0, arr_b.length);
    }

    public static ByteString copyFrom(byte[] arr_b, int v, int v1) {
        byte[] arr_b1 = new byte[v1];
        System.arraycopy(arr_b, v, arr_b1, 0, v1);
        return new LiteralByteString(arr_b1);
    }

    public static ByteString copyFromUtf8(String s) {
        try {
            return new LiteralByteString(s.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException("UTF-8 not supported?", unsupportedEncodingException0);
        }
    }

    public void copyTo(byte[] arr_b, int v, int v1, int v2) {
        if(v < 0) {
            throw new IndexOutOfBoundsException("Source offset < 0: " + v);
        }
        if(v1 < 0) {
            throw new IndexOutOfBoundsException("Target offset < 0: " + v1);
        }
        if(v2 < 0) {
            throw new IndexOutOfBoundsException("Length < 0: " + v2);
        }
        int v3 = v + v2;
        if(v3 > this.size()) {
            throw new IndexOutOfBoundsException("Source end offset < 0: " + v3);
        }
        int v4 = v1 + v2;
        if(v4 > arr_b.length) {
            throw new IndexOutOfBoundsException("Target end offset < 0: " + v4);
        }
        if(v2 > 0) {
            this.copyToInternal(arr_b, v, v1, v2);
        }
    }

    protected abstract void copyToInternal(byte[] arg1, int arg2, int arg3, int arg4);

    protected abstract int getTreeDepth();

    protected abstract boolean isBalanced();

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public abstract boolean isValidUtf8();

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    public abstract ByteIterator iterator();

    public abstract CodedInputStream newCodedInput();

    public static Output newOutput() {
        return new Output(0x80);
    }

    protected abstract int partialHash(int arg1, int arg2, int arg3);

    protected abstract int partialIsValidUtf8(int arg1, int arg2, int arg3);

    protected abstract int peekCachedHashCode();

    public abstract int size();

    public byte[] toByteArray() {
        int v = this.size();
        if(v == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] arr_b = new byte[v];
        this.copyToInternal(arr_b, 0, 0, v);
        return arr_b;
    }

    @Override
    public String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.size());
    }

    public abstract String toString(String arg1) throws UnsupportedEncodingException;

    public String toStringUtf8() {
        try {
            return this.toString("UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException("UTF-8 not supported?", unsupportedEncodingException0);
        }
    }

    void writeTo(OutputStream outputStream0, int v, int v1) throws IOException {
        if(v < 0) {
            throw new IndexOutOfBoundsException("Source offset < 0: " + v);
        }
        if(v1 < 0) {
            throw new IndexOutOfBoundsException("Length < 0: " + v1);
        }
        int v2 = v + v1;
        if(v2 > this.size()) {
            throw new IndexOutOfBoundsException("Source end offset exceeded: " + v2);
        }
        if(v1 > 0) {
            this.writeToInternal(outputStream0, v, v1);
        }
    }

    abstract void writeToInternal(OutputStream arg1, int arg2, int arg3) throws IOException;
}

