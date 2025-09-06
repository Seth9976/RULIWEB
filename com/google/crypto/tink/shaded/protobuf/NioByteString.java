package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

final class NioByteString extends LeafByteString {
    private final ByteBuffer buffer;

    NioByteString(ByteBuffer byteBuffer0) {
        Internal.checkNotNull(byteBuffer0, "buffer");
        this.buffer = byteBuffer0.slice().order(ByteOrder.nativeOrder());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return this.buffer.asReadOnlyBuffer();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public List asReadOnlyByteBufferList() {
        return Collections.singletonList(this.asReadOnlyByteBuffer());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte byteAt(int v) {
        try {
            return this.buffer.get(v);
        }
        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
            throw arrayIndexOutOfBoundsException0;
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new ArrayIndexOutOfBoundsException(indexOutOfBoundsException0.getMessage());
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer0) {
        byteBuffer0.put(this.buffer.slice());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected void copyToInternal(byte[] arr_b, int v, int v1, int v2) {
        ByteBuffer byteBuffer0 = this.buffer.slice();
        byteBuffer0.position(v);
        byteBuffer0.get(arr_b, v1, v2);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
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
        if(object0 instanceof NioByteString) {
            return this.buffer.equals(((NioByteString)object0).buffer);
        }
        if(object0 instanceof RopeByteString) {
            return object0.equals(this);
        }
        ByteBuffer byteBuffer0 = ((ByteString)object0).asReadOnlyByteBuffer();
        return this.buffer.equals(byteBuffer0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString$LeafByteString
    boolean equalsRange(ByteString byteString0, int v, int v1) {
        return this.substring(0, v1).equals(byteString0.substring(v, v1 + v));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte internalByteAt(int v) {
        return this.byteAt(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isValidUtf8() {
        return Utf8.isValidUtf8(this.buffer);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this.buffer, true);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public InputStream newInput() {
        return new InputStream() {
            private final ByteBuffer buf;

            {
                this.buf = nioByteString0.buffer.slice();
            }

            @Override
            public int available() throws IOException {
                return this.buf.remaining();
            }

            @Override
            public void mark(int v) {
                this.buf.mark();
            }

            @Override
            public boolean markSupported() {
                return true;
            }

            @Override
            public int read() throws IOException {
                return this.buf.hasRemaining() ? this.buf.get() & 0xFF : -1;
            }

            @Override
            public int read(byte[] arr_b, int v, int v1) throws IOException {
                if(!this.buf.hasRemaining()) {
                    return -1;
                }
                int v2 = Math.min(v1, this.buf.remaining());
                this.buf.get(arr_b, v, v2);
                return v2;
            }

            @Override
            public void reset() throws IOException {
                try {
                    this.buf.reset();
                }
                catch(InvalidMarkException invalidMarkException0) {
                    throw new IOException(invalidMarkException0);
                }
            }
        };
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected int partialHash(int v, int v1, int v2) {
        for(int v3 = v1; v3 < v1 + v2; ++v3) {
            v = v * 0x1F + this.buffer.get(v3);
        }
        return v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected int partialIsValidUtf8(int v, int v1, int v2) {
        return Utf8.partialIsValidUtf8(v, this.buffer, v1, v2 + v1);
    }

    private void readObject(ObjectInputStream objectInputStream0) throws IOException {
        throw new InvalidObjectException("NioByteString instances are not to be serialized directly");
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public int size() {
        return this.buffer.remaining();
    }

    private ByteBuffer slice(int v, int v1) {
        if(v < this.buffer.position() || v1 > this.buffer.limit() || v > v1) {
            throw new IllegalArgumentException(String.format("Invalid indices [%d, %d]", v, v1));
        }
        ByteBuffer byteBuffer0 = this.buffer.slice();
        byteBuffer0.position(v - this.buffer.position());
        byteBuffer0.limit(v1 - this.buffer.position());
        return byteBuffer0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteString substring(int v, int v1) {
        try {
            return new NioByteString(this.slice(v, v1));
        }
        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
            throw arrayIndexOutOfBoundsException0;
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new ArrayIndexOutOfBoundsException(indexOutOfBoundsException0.getMessage());
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected String toStringInternal(Charset charset0) {
        if(this.buffer.hasArray()) {
            return new String(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), this.buffer.remaining(), charset0);
        }
        byte[] arr_b = this.toByteArray();
        return new String(arr_b, 0, arr_b.length, charset0);
    }

    private Object writeReplace() {
        return ByteString.copyFrom(this.buffer.slice());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    void writeTo(ByteOutput byteOutput0) throws IOException {
        byteOutput0.writeLazy(this.buffer.slice());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(OutputStream outputStream0) throws IOException {
        outputStream0.write(this.toByteArray());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    void writeToInternal(OutputStream outputStream0, int v, int v1) throws IOException {
        if(this.buffer.hasArray()) {
            int v2 = this.buffer.arrayOffset();
            outputStream0.write(this.buffer.array(), v2 + this.buffer.position() + v, v1);
            return;
        }
        ByteBufferWriter.write(this.slice(v, v1 + v), outputStream0);
    }
}

