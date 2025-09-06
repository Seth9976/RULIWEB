package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class BooleanVector extends BaseVector {
    public BooleanVector __assign(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, 1, byteBuffer0);
        return this;
    }

    public boolean get(int v) {
        return this.bb.get(this.__element(v)) != 0;
    }
}

