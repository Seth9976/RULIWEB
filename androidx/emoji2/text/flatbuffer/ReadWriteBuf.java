package androidx.emoji2.text.flatbuffer;

interface ReadWriteBuf extends ReadBuf {
    @Override  // androidx.emoji2.text.flatbuffer.ReadBuf
    int limit();

    void put(byte arg1);

    void put(byte[] arg1, int arg2, int arg3);

    void putBoolean(boolean arg1);

    void putDouble(double arg1);

    void putFloat(float arg1);

    void putInt(int arg1);

    void putLong(long arg1);

    void putShort(short arg1);

    boolean requestCapacity(int arg1);

    void set(int arg1, byte arg2);

    void set(int arg1, byte[] arg2, int arg3, int arg4);

    void setBoolean(int arg1, boolean arg2);

    void setDouble(int arg1, double arg2);

    void setFloat(int arg1, float arg2);

    void setInt(int arg1, int arg2);

    void setLong(int arg1, long arg2);

    void setShort(int arg1, short arg2);

    int writePosition();
}

