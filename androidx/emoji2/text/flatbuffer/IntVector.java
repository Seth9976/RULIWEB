package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class IntVector extends BaseVector {
    public IntVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 4, byteBuffer0);
        return this;
    }

    public int get(int v) {
        return this.bb.getInt(this.__element(v));
    }

    public long getAsUnsigned(int v) {
        return ((long)this.get(v)) & 0xFFFFFFFFL;
    }
}

