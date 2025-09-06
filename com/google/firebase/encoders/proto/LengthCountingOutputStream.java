package com.google.firebase.encoders.proto;

import java.io.OutputStream;

final class LengthCountingOutputStream extends OutputStream {
    private long length;

    LengthCountingOutputStream() {
        this.length = 0L;
    }

    long getLength() {
        return this.length;
    }

    @Override
    public void write(int v) {
        ++this.length;
    }

    @Override
    public void write(byte[] arr_b) {
        this.length += (long)arr_b.length;
    }

    @Override
    public void write(byte[] arr_b, int v, int v1) {
        if(v < 0 || v > arr_b.length || v1 < 0 || (v + v1 > arr_b.length || v + v1 < 0)) {
            throw new IndexOutOfBoundsException();
        }
        this.length += (long)v1;
    }
}

