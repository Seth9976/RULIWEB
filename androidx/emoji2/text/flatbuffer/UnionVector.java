package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class UnionVector extends BaseVector {
    public UnionVector __assign(int v, int v1, ByteBuffer byteBuffer0) {
        this.__reset(v, v1, byteBuffer0);
        return this;
    }

    public Table get(Table table0, int v) {
        return Table.__union(table0, this.__element(v), this.bb);
    }
}

