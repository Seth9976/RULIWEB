package androidx.emoji2.text.flatbuffer;

import java.util.Arrays;

public class ArrayReadWriteBuf implements ReadWriteBuf {
    private byte[] buffer;
    private int writePos;

    public ArrayReadWriteBuf() {
        this(10);
    }

    public ArrayReadWriteBuf(int v) {
        this(new byte[v]);
    }

    public ArrayReadWriteBuf(byte[] arr_b) {
        this.buffer = arr_b;
        this.writePos = 0;
    }

    public ArrayReadWriteBuf(byte[] arr_b, int v) {
        this.buffer = arr_b;
        this.writePos = v;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte[] data() {
        return this.buffer;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte get(int v) {
        return this.buffer[v];
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public boolean getBoolean(int v) {
        return this.buffer[v] != 0;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public double getDouble(int v) {
        return Double.longBitsToDouble(this.getLong(v));
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public float getFloat(int v) {
        return Float.intBitsToFloat(this.getInt(v));
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public int getInt(int v) {
        return this.buffer[v] & 0xFF | (this.buffer[v + 3] << 24 | (this.buffer[v + 2] & 0xFF) << 16 | (this.buffer[v + 1] & 0xFF) << 8);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public long getLong(int v) {
        return ((long)this.buffer[v + 7]) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (0xFFL & ((long)this.buffer[v + 6])) << 0x30);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public short getShort(int v) {
        return (short)(this.buffer[v] & 0xFF | this.buffer[v + 1] << 8);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    public String getString(int v, int v1) {
        return Utf8Safe.decodeUtf8Array(this.buffer, v, v1);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public int limit() {
        return this.writePos;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte b) {
        this.set(this.writePos, b);
        ++this.writePos;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte[] arr_b, int v, int v1) {
        this.set(this.writePos, arr_b, v, v1);
        this.writePos += v1;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putBoolean(boolean z) {
        this.setBoolean(this.writePos, z);
        ++this.writePos;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putDouble(double f) {
        this.setDouble(this.writePos, f);
        this.writePos += 8;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putFloat(float f) {
        this.setFloat(this.writePos, f);
        this.writePos += 4;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putInt(int v) {
        this.setInt(this.writePos, v);
        this.writePos += 4;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putLong(long v) {
        this.setLong(this.writePos, v);
        this.writePos += 8;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putShort(short v) {
        this.setShort(this.writePos, v);
        this.writePos += 2;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public boolean requestCapacity(int v) {
        byte[] arr_b = this.buffer;
        if(arr_b.length > v) {
            return true;
        }
        this.buffer = Arrays.copyOf(arr_b, arr_b.length + (arr_b.length >> 1));
        return true;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int v, byte b) {
        this.requestCapacity(v + 1);
        this.buffer[v] = b;
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int v, byte[] arr_b, int v1, int v2) {
        this.requestCapacity(v2 - v1 + v);
        System.arraycopy(arr_b, v1, this.buffer, v, v2);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setBoolean(int v, boolean z) {
        this.set(v, ((byte)z));
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setDouble(int v, double f) {
        this.requestCapacity(v + 8);
        long v1 = Double.doubleToRawLongBits(f);
        byte[] arr_b = this.buffer;
        arr_b[v] = (byte)(((int)v1) & 0xFF);
        arr_b[v + 1] = (byte)(((int)v1) >> 8 & 0xFF);
        arr_b[v + 2] = (byte)(((int)v1) >> 16 & 0xFF);
        arr_b[v + 3] = (byte)(((int)v1) >> 24 & 0xFF);
        arr_b[v + 4] = (byte)(((int)(v1 >> 0x20)) & 0xFF);
        arr_b[v + 5] = (byte)(((int)(v1 >> 0x20)) >> 8 & 0xFF);
        arr_b[v + 6] = (byte)(((int)(v1 >> 0x20)) >> 16 & 0xFF);
        arr_b[v + 7] = (byte)(((int)(v1 >> 0x20)) >> 24 & 0xFF);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setFloat(int v, float f) {
        this.requestCapacity(v + 4);
        int v1 = Float.floatToRawIntBits(f);
        byte[] arr_b = this.buffer;
        arr_b[v] = (byte)(v1 & 0xFF);
        arr_b[v + 1] = (byte)(v1 >> 8 & 0xFF);
        arr_b[v + 2] = (byte)(v1 >> 16 & 0xFF);
        arr_b[v + 3] = (byte)(v1 >> 24 & 0xFF);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setInt(int v, int v1) {
        this.requestCapacity(v + 4);
        byte[] arr_b = this.buffer;
        arr_b[v] = (byte)(v1 & 0xFF);
        arr_b[v + 1] = (byte)(v1 >> 8 & 0xFF);
        arr_b[v + 2] = (byte)(v1 >> 16 & 0xFF);
        arr_b[v + 3] = (byte)(v1 >> 24 & 0xFF);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setLong(int v, long v1) {
        this.requestCapacity(v + 8);
        byte[] arr_b = this.buffer;
        arr_b[v] = (byte)(((int)v1) & 0xFF);
        arr_b[v + 1] = (byte)(((int)v1) >> 8 & 0xFF);
        arr_b[v + 2] = (byte)(((int)v1) >> 16 & 0xFF);
        arr_b[v + 3] = (byte)(((int)v1) >> 24 & 0xFF);
        arr_b[v + 4] = (byte)(((int)(v1 >> 0x20)) & 0xFF);
        arr_b[v + 5] = (byte)(((int)(v1 >> 0x20)) >> 8 & 0xFF);
        arr_b[v + 6] = (byte)(((int)(v1 >> 0x20)) >> 16 & 0xFF);
        arr_b[v + 7] = (byte)(((int)(v1 >> 0x20)) >> 24 & 0xFF);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setShort(int v, short v1) {
        this.requestCapacity(v + 2);
        byte[] arr_b = this.buffer;
        arr_b[v] = (byte)(v1 & 0xFF);
        arr_b[v + 1] = (byte)(v1 >> 8 & 0xFF);
    }

    @Override  // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public int writePosition() {
        return this.writePos;
    }
}

