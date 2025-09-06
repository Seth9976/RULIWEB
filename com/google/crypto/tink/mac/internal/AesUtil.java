package com.google.crypto.tink.mac.internal;

import java.util.Arrays;

public final class AesUtil {
    public static final int BLOCK_SIZE = 16;

    public static byte[] cmacPad(byte[] arr_b) {
        if(arr_b.length >= 16) {
            throw new IllegalArgumentException("x must be smaller than a block.");
        }
        byte[] arr_b1 = Arrays.copyOf(arr_b, 16);
        arr_b1[arr_b.length] = (byte)0x80;
        return arr_b1;
    }

    public static byte[] dbl(byte[] arr_b) {
        if(arr_b.length != 16) {
            throw new IllegalArgumentException("value must be a block.");
        }
        byte[] arr_b1 = new byte[16];
        for(int v = 0; v < 16; ++v) {
            byte b = (byte)(arr_b[v] << 1 & 0xFE);
            arr_b1[v] = b;
            if(v < 15) {
                arr_b1[v] = (byte)(((byte)(arr_b[v + 1] >> 7 & 1)) | b);
            }
        }
        arr_b1[15] = (byte)(((byte)(arr_b[0] >> 7 & 0x87)) ^ arr_b1[15]);
        return arr_b1;
    }
}

