package com.google.firebase.messaging;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

final class ByteStreams {
    static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark;

        LimitedInputStream(InputStream inputStream0, long v) {
            super(inputStream0);
            this.mark = -1L;
            this.left = v;
        }

        @Override
        public int available() throws IOException {
            return (int)Math.min(this.in.available(), this.left);
        }

        @Override
        public void mark(int v) {
            synchronized(this) {
                this.in.mark(v);
                this.mark = this.left;
            }
        }

        @Override
        public int read() throws IOException {
            if(this.left == 0L) {
                return -1;
            }
            int v = this.in.read();
            if(v != -1) {
                --this.left;
            }
            return v;
        }

        @Override
        public int read(byte[] arr_b, int v, int v1) throws IOException {
            long v2 = this.left;
            if(v2 == 0L) {
                return -1;
            }
            int v3 = this.in.read(arr_b, v, ((int)Math.min(v1, v2)));
            if(v3 != -1) {
                this.left -= (long)v3;
            }
            return v3;
        }

        @Override
        public void reset() throws IOException {
            synchronized(this) {
                if(this.in.markSupported()) {
                    if(this.mark == -1L) {
                        throw new IOException("Mark not set");
                    }
                    this.in.reset();
                    this.left = this.mark;
                    return;
                }
            }
            throw new IOException("Mark not supported");
        }

        @Override
        public long skip(long v) throws IOException {
            long v1 = this.in.skip(Math.min(v, this.left));
            this.left -= v1;
            return v1;
        }
    }

    private static final int BUFFER_SIZE = 0x2000;
    private static final int MAX_ARRAY_LEN = 0x7FFFFFF7;
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;

    private static byte[] combineBuffers(Queue queue0, int v) {
        if(queue0.isEmpty()) {
            return new byte[0];
        }
        byte[] arr_b = (byte[])queue0.remove();
        if(arr_b.length == v) {
            return arr_b;
        }
        int v1 = v - arr_b.length;
        byte[] arr_b1 = Arrays.copyOf(arr_b, v);
        while(v1 > 0) {
            byte[] arr_b2 = (byte[])queue0.remove();
            int v2 = Math.min(v1, arr_b2.length);
            System.arraycopy(arr_b2, 0, arr_b1, v - v1, v2);
            v1 -= v2;
        }
        return arr_b1;
    }

    static byte[] createBuffer() {
        return new byte[0x2000];
    }

    public static InputStream limit(InputStream inputStream0, long v) {
        return new LimitedInputStream(inputStream0, v);
    }

    private static int saturatedCast(long v) {
        if(v > 0x7FFFFFFFL) {
            return 0x7FFFFFFF;
        }
        return v >= 0xFFFFFFFF80000000L ? ((int)v) : 0x80000000;
    }

    public static byte[] toByteArray(InputStream inputStream0) throws IOException {
        return ByteStreams.toByteArrayInternal(inputStream0, new ArrayDeque(20), 0);
    }

    private static byte[] toByteArrayInternal(InputStream inputStream0, Queue queue0, int v) throws IOException {
        for(int v1 = Math.min(0x2000, Math.max(0x80, Integer.highestOneBit(v) * 2)); v < 0x7FFFFFF7; v1 = ByteStreams.saturatedCast(((long)v1) * ((long)(v1 >= 0x1000 ? 2 : 4)))) {
            int v2 = Math.min(v1, 0x7FFFFFF7 - v);
            byte[] arr_b = new byte[v2];
            queue0.add(arr_b);
            int v3 = 0;
            while(v3 < v2) {
                int v4 = inputStream0.read(arr_b, v3, v2 - v3);
                if(v4 == -1) {
                    return ByteStreams.combineBuffers(queue0, v);
                }
                v3 += v4;
                v += v4;
            }
        }
        if(inputStream0.read() != -1) {
            throw new OutOfMemoryError("input is too large to fit in a byte array");
        }
        return ByteStreams.combineBuffers(queue0, 0x7FFFFFF7);
    }
}

