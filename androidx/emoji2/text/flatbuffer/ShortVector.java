package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class ShortVector extends BaseVector {
    public ShortVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 2, byteBuffer0);
        return this;
    }

    public short get(int v) {
        return this.bb.getShort(this.__element(v));
    }

    public int getAsUnsigned(int v) {
        return this.get(v) & 0xFFFF;
    }
}

