package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class FloatVector extends BaseVector {
    public FloatVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 4, byteBuffer0);
        return this;
    }

    public float get(int v) {
        return this.bb.getFloat(this.__element(v));
    }
}

