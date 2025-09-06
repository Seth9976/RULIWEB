package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class DoubleVector extends BaseVector {
    public DoubleVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 8, byteBuffer0);
        return this;
    }

    public double get(int v) {
        return this.bb.getDouble(this.__element(v));
    }
}

