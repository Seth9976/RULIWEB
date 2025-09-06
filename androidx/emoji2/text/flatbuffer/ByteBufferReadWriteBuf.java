package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferReadWriteBuf implements ReadWriteBuf {
    private final ByteBuffer buffer;

    public ByteBufferReadWriteBuf(ByteBuffer byteBuffer0) {
        this.buffer = byteBuffer0;
        byteBuffer0.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte[] data() {
        return this.buffer.array();
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte get(int v) {
        return this.buffer.get(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public boolean getBoolean(int v) {
        return this.get(v) != 0;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public double getDouble(int v) {
        return this.buffer.getDouble(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public float getFloat(int v) {
        return this.buffer.getFloat(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public int getInt(int v) {
        return this.buffer.getInt(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public long getLong(int v) {
        return this.buffer.getLong(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public short getShort(int v) {
        return this.buffer.getShort(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public String getString(int v, int v1) {
        return Utf8Safe.decodeUtf8Buffer(this.buffer, v, v1);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public int limit() {
        return this.buffer.limit();
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte b) {
        this.buffer.put(b);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte[] arr_b, int v, int v1) {
        this.buffer.put(arr_b, v, v1);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putBoolean(boolean z) {
        this.buffer.put(((byte)z));
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putDouble(double f) {
        this.buffer.putDouble(f);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putFloat(float f) {
        this.buffer.putFloat(f);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putInt(int v) {
        this.buffer.putInt(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putLong(long v) {
        this.buffer.putLong(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putShort(short v) {
        this.buffer.putShort(v);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public boolean requestCapacity(int v) {
        return v <= this.buffer.limit();
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int v, byte b) {
        this.buffer.put(v, b);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int v, byte[] arr_b, int v1, int v2) {
        this.buffer.position(v);
        this.buffer.put(arr_b, v1, v2);
        this.buffer.position(this.buffer.position());
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setBoolean(int v, boolean z) {
        this.set(v, ((byte)z));
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setDouble(int v, double f) {
        this.buffer.putDouble(v, f);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setFloat(int v, float f) {
        this.buffer.putFloat(v, f);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setInt(int v, int v1) {
        this.buffer.putInt(v, v1);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setLong(int v, long v1) {
        this.buffer.putLong(v, v1);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setShort(int v, short v1) {
        this.buffer.putShort(v, v1);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public int writePosition() {
        return this.buffer.position();
    }
}

