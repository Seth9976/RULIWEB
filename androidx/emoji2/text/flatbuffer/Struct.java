package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public class Struct {
    protected ByteBuffer bb;
    protected int bb_pos;

    public void __reset() {
        this.__reset(0, null);
    }

    protected void __reset(int v, ByteBuffer byteBuffer0) {
        this.bb = byteBuffer0;
        if(byteBuffer0 != null) {
            this.bb_pos = v;
            return;
        }
        this.bb_pos = 0;
    }
}

