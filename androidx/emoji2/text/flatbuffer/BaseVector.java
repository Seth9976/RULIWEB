package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public class BaseVector {
    protected ByteBuffer bb;
    private int element_size;
    private int length;
    private int vector;

    protected int __element(int v) {
        return this.vector + v * this.element_size;
    }

    protected void __reset(int v, int v1, ByteBuffer byteBuffer0) {
        this.bb = byteBuffer0;
        if(byteBuffer0 != null) {
            this.vector = v;
            this.length = byteBuffer0.getInt(v - 4);
            this.element_size = v1;
            return;
        }
        this.vector = 0;
        this.length = 0;
        this.element_size = 0;
    }

    protected int __vector() {
        return this.vector;
    }

    public int length() {
        return this.length;
    }

    public void reset() {
        this.__reset(0, 0, null);
    }
}

