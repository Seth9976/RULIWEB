package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public class ByteBufferUtil {
    public static int getSizePrefix(ByteBuffer byteBuffer0) {
        return byteBuffer0.getInt(byteBuffer0.position());
    }

    public static ByteBuffer removeSizePrefix(ByteBuffer byteBuffer0) {
        ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
        byteBuffer1.position(byteBuffer1.position() + 4);
        return byteBuffer1;
    }
}

