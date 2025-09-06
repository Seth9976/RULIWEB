package androidx.emoji2.text.flatbuffer;

interface ReadBuf {
    byte[] data();

    byte get(int arg1);

    boolean getBoolean(int arg1);

    double getDouble(int arg1);

    float getFloat(int arg1);

    int getInt(int arg1);

    long getLong(int arg1);

    short getShort(int arg1);

    String getString(int arg1, int arg2);

    int limit();
}

