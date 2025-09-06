package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class LongVector extends BaseVector {
    public LongVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 8, byteBuffer0);
        return this;
    }

    public long get(int v) {
        return this.bb.getLong(this.__element(v));
    }
}

