package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;

@CheckReturnValue
abstract class AllocatedBuffer {
    public abstract byte[] array();

    public abstract int arrayOffset();

    public abstract boolean hasArray();

    public abstract boolean hasNioBuffer();

    public abstract int limit();

    public abstract ByteBuffer nioBuffer();

    public abstract int position();

    public abstract AllocatedBuffer position(int arg1);

    public abstract int remaining();

    public static AllocatedBuffer wrap(ByteBuffer byteBuffer0) {
        Internal.checkNotNull(byteBuffer0, "buffer");
        return new AllocatedBuffer() {
            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public byte[] array() {
                return byteBuffer0.array();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int arrayOffset() {
                return byteBuffer0.arrayOffset();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public boolean hasArray() {
                return byteBuffer0.hasArray();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public boolean hasNioBuffer() {
                return true;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int limit() {
                return byteBuffer0.limit();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public ByteBuffer nioBuffer() {
                return byteBuffer0;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int position() {
                return byteBuffer0.position();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public AllocatedBuffer position(int v) {
                byteBuffer0.position(v);
                return this;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int remaining() {
                return byteBuffer0.remaining();
            }
        };
    }

    public static AllocatedBuffer wrap(byte[] arr_b) {
        return AllocatedBuffer.wrapNoCheck(arr_b, 0, arr_b.length);
    }

    public static AllocatedBuffer wrap(byte[] arr_b, int v, int v1) {
        if(v < 0 || v1 < 0 || v + v1 > arr_b.length) {
            throw new IndexOutOfBoundsException(String.format("bytes.length=%d, offset=%d, length=%d", ((int)arr_b.length), v, v1));
        }
        return AllocatedBuffer.wrapNoCheck(arr_b, v, v1);
    }

    private static AllocatedBuffer wrapNoCheck(byte[] arr_b, int v, int v1) {
        return new AllocatedBuffer() {
            private int position;

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public byte[] array() {
                return arr_b;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int arrayOffset() {
                return v;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public boolean hasArray() {
                return true;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public boolean hasNioBuffer() {
                return false;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int limit() {
                return v1;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public ByteBuffer nioBuffer() {
                throw new UnsupportedOperationException();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int position() {
                return this.position;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public AllocatedBuffer position(int v) {
                if(v < 0 || v > v1) {
                    throw new IllegalArgumentException("Invalid position: " + v);
                }
                this.position = v;
                return this;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.AllocatedBuffer
            public int remaining() {
                return v1 - this.position;
            }
        };
    }
}

