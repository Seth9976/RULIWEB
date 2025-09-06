package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class ByteVector extends BaseVector {
    public ByteVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 1, byteBuffer0);
        return this;
    }

    public byte get(int v) {
        return this.bb.get(this.__element(v));
    }

    public int getAsUnsigned(int v) {
        return this.get(v) & 0xFF;
    }
}

