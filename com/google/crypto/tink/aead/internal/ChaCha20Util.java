package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

final class ChaCha20Util {
    static final int BLOCK_SIZE_IN_BYTES = 0x40;
    static final int BLOCK_SIZE_IN_INTS = 16;
    static final int KEY_SIZE_IN_BYTES = 0x20;
    static final int KEY_SIZE_IN_INTS = 8;
    private static final int[] SIGMA;

    static {
        ChaCha20Util.SIGMA = ChaCha20Util.toIntArray(new byte[]{101, 120, 0x70, 97, 110, 100, 0x20, 51, 50, 45, 98, 0x79, 0x74, 101, 0x20, 107});
    }

    static void quarterRound(int[] arr_v, int v, int v1, int v2, int v3) {
        int v4 = arr_v[v] + arr_v[v1];
        arr_v[v] = v4;
        int v5 = ChaCha20Util.rotateLeft(v4 ^ arr_v[v3], 16);
        arr_v[v3] = v5;
        int v6 = arr_v[v2] + v5;
        arr_v[v2] = v6;
        int v7 = ChaCha20Util.rotateLeft(arr_v[v1] ^ v6, 12);
        arr_v[v1] = v7;
        int v8 = arr_v[v] + v7;
        arr_v[v] = v8;
        int v9 = ChaCha20Util.rotateLeft(arr_v[v3] ^ v8, 8);
        arr_v[v3] = v9;
        int v10 = arr_v[v2] + v9;
        arr_v[v2] = v10;
        arr_v[v1] = ChaCha20Util.rotateLeft(arr_v[v1] ^ v10, 7);
    }

    private static int rotateLeft(int x, int y) {
        return x >>> -y | x << y;
    }

    static void setSigmaAndKey(int[] arr_v, int[] arr_v1) {
        System.arraycopy(ChaCha20Util.SIGMA, 0, arr_v, 0, ChaCha20Util.SIGMA.length);
        System.arraycopy(arr_v1, 0, arr_v, ChaCha20Util.SIGMA.length, 8);
    }

    static void shuffleState(int[] arr_v) {
        for(int v = 0; v < 10; ++v) {
            ChaCha20Util.quarterRound(arr_v, 0, 4, 8, 12);
            ChaCha20Util.quarterRound(arr_v, 1, 5, 9, 13);
            ChaCha20Util.quarterRound(arr_v, 2, 6, 10, 14);
            ChaCha20Util.quarterRound(arr_v, 3, 7, 11, 15);
            ChaCha20Util.quarterRound(arr_v, 0, 5, 10, 15);
            ChaCha20Util.quarterRound(arr_v, 1, 6, 11, 12);
            ChaCha20Util.quarterRound(arr_v, 2, 7, 8, 13);
            ChaCha20Util.quarterRound(arr_v, 3, 4, 9, 14);
        }
    }

    static int[] toIntArray(byte[] arr_b) {
        IntBuffer intBuffer0 = ByteBuffer.wrap(arr_b).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] arr_v = new int[intBuffer0.remaining()];
        intBuffer0.get(arr_v);
        return arr_v;
    }
}

