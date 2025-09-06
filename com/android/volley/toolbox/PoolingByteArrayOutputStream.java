package com.android.volley.toolbox;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PoolingByteArrayOutputStream extends ByteArrayOutputStream implements AutoCloseable {
    private static final int DEFAULT_SIZE = 0x100;
    private final ByteArrayPool mPool;

    public PoolingByteArrayOutputStream(ByteArrayPool byteArrayPool0) {
        this(byteArrayPool0, 0x100);
    }

    public PoolingByteArrayOutputStream(ByteArrayPool byteArrayPool0, int v) {
        this.mPool = byteArrayPool0;
        this.buf = byteArrayPool0.getBuf(Math.max(v, 0x100));
    }

    @Override
    public void close() throws IOException {
        this.mPool.returnBuf(this.buf);
        this.buf = null;
        super.close();
    }

    private void expand(int v) {
        if(this.count + v <= this.buf.length) {
            return;
        }
        byte[] arr_b = this.mPool.getBuf((this.count + v) * 2);
        System.arraycopy(this.buf, 0, arr_b, 0, this.count);
        this.mPool.returnBuf(this.buf);
        this.buf = arr_b;
    }

    @Override
    public void finalize() {
        this.mPool.returnBuf(this.buf);
    }

    @Override
    public void write(int v) {
        synchronized(this) {
            this.expand(1);
            super.write(v);
        }
    }

    @Override
    public void write(byte[] arr_b, int v, int v1) {
        synchronized(this) {
            this.expand(v1);
            super.write(arr_b, v, v1);
        }
    }
}

