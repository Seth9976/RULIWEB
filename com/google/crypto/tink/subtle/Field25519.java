package com.google.crypto.tink.subtle;

import java.util.Arrays;

final class Field25519 {
    private static final int[] EXPAND_SHIFT = null;
    private static final int[] EXPAND_START = null;
    static final int FIELD_LEN = 0x20;
    static final int LIMB_CNT = 10;
    private static final int[] MASK = null;
    private static final int[] SHIFT = null;
    private static final long TWO_TO_25 = 0x2000000L;
    private static final long TWO_TO_26 = 0x4000000L;

    static {
        Field25519.EXPAND_START = new int[]{0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
        Field25519.EXPAND_SHIFT = new int[]{0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
        Field25519.MASK = new int[]{0x3FFFFFF, 0x1FFFFFF};
        Field25519.SHIFT = new int[]{26, 25};
    }

    static byte[] contract(long[] arr_v) {
        long[] arr_v1 = Arrays.copyOf(arr_v, 10);
        for(int v1 = 0; v1 < 2; ++v1) {
            int v3 = 0;
            while(v3 < 9) {
                long v4 = arr_v1[v3];
                int v5 = Field25519.SHIFT[v3 & 1];
                int v6 = -((int)((v4 >> 0x1F & v4) >> v5));
                arr_v1[v3] = v4 + ((long)(v6 << v5));
                ++v3;
                arr_v1[v3] -= (long)v6;
            }
            int v7 = -((int)((arr_v1[9] >> 0x1F & arr_v1[9]) >> 25));
            arr_v1[9] += (long)(v7 << 25);
            arr_v1[0] -= (long)(v7 * 19);
        }
        int v8 = -((int)((arr_v1[0] >> 0x1F & arr_v1[0]) >> 26));
        arr_v1[0] += (long)(v8 << 26);
        arr_v1[1] -= (long)v8;
        for(int v9 = 0; v9 < 2; ++v9) {
            int v10 = 0;
            while(v10 < 9) {
                long v11 = arr_v1[v10];
                int v12 = (int)(v11 >> Field25519.SHIFT[v10 & 1]);
                arr_v1[v10] = v11 & ((long)Field25519.MASK[v10 & 1]);
                ++v10;
                arr_v1[v10] += (long)v12;
            }
        }
        int v13 = (int)(arr_v1[9] >> 25);
        arr_v1[9] &= 0x1FFFFFFL;
        long v14 = arr_v1[0] + ((long)(v13 * 19));
        arr_v1[0] = v14;
        int v15 = ~(((int)v14) - 0x3FFFFED >> 0x1F);
        for(int v16 = 1; v16 < 10; ++v16) {
            v15 &= Field25519.eq(((int)arr_v1[v16]), Field25519.MASK[v16 & 1]);
        }
        arr_v1[0] -= (long)(0x3FFFFED & v15);
        arr_v1[1] -= (long)(0x1FFFFFF & v15);
        for(int v2 = 2; v2 < 10; v2 += 2) {
            arr_v1[v2] -= (long)(0x3FFFFFF & v15);
            arr_v1[v2 + 1] -= (long)(0x1FFFFFF & v15);
        }
        for(int v17 = 0; v17 < 10; ++v17) {
            arr_v1[v17] <<= Field25519.EXPAND_SHIFT[v17];
        }
        byte[] arr_b = new byte[0x20];
        for(int v = 0; v < 10; ++v) {
            int v18 = Field25519.EXPAND_START[v];
            long v19 = (long)arr_b[v18];
            long v20 = arr_v1[v];
            arr_b[v18] = (byte)(((int)(v19 | v20 & 0xFFL)));
            arr_b[v18 + 1] = (byte)(((int)(((long)arr_b[v18 + 1]) | v20 >> 8 & 0xFFL)));
            arr_b[v18 + 2] = (byte)(((int)(((long)arr_b[v18 + 2]) | v20 >> 16 & 0xFFL)));
            arr_b[v18 + 3] = (byte)(((int)(((long)arr_b[v18 + 3]) | v20 >> 24 & 0xFFL)));
        }
        return arr_b;
    }

    // 去混淆评级： 低(20)
    private static int eq(int a, int b) {
        return 0;
    }

    static long[] expand(byte[] arr_b) {
        long[] arr_v = new long[10];
        for(int v = 0; v < 10; ++v) {
            int v1 = Field25519.EXPAND_START[v];
            arr_v[v] = (((long)(arr_b[v1] & 0xFF)) | ((long)(arr_b[v1 + 1] & 0xFF)) << 8 | ((long)(arr_b[v1 + 2] & 0xFF)) << 16 | ((long)(arr_b[v1 + 3] & 0xFF)) << 24) >> Field25519.EXPAND_SHIFT[v] & ((long)Field25519.MASK[v & 1]);
        }
        return arr_v;
    }

    private static int gte(int a, int b) [...] // Inlined contents

    static void inverse(long[] arr_v, long[] arr_v1) {
        long[] arr_v2 = new long[10];
        long[] arr_v3 = new long[10];
        long[] arr_v4 = new long[10];
        long[] arr_v5 = new long[10];
        long[] arr_v6 = new long[10];
        long[] arr_v7 = new long[10];
        long[] arr_v8 = new long[10];
        long[] arr_v9 = new long[10];
        long[] arr_v10 = new long[10];
        long[] arr_v11 = new long[10];
        Field25519.square(arr_v2, arr_v1);
        Field25519.square(arr_v11, arr_v2);
        Field25519.square(arr_v10, arr_v11);
        Field25519.mult(arr_v3, arr_v10, arr_v1);
        Field25519.mult(arr_v4, arr_v3, arr_v2);
        Field25519.square(arr_v10, arr_v4);
        Field25519.mult(arr_v5, arr_v10, arr_v3);
        Field25519.square(arr_v10, arr_v5);
        Field25519.square(arr_v11, arr_v10);
        Field25519.square(arr_v10, arr_v11);
        Field25519.square(arr_v11, arr_v10);
        Field25519.square(arr_v10, arr_v11);
        Field25519.mult(arr_v6, arr_v10, arr_v5);
        Field25519.square(arr_v10, arr_v6);
        Field25519.square(arr_v11, arr_v10);
        for(int v1 = 2; v1 < 10; v1 += 2) {
            Field25519.square(arr_v10, arr_v11);
            Field25519.square(arr_v11, arr_v10);
        }
        Field25519.mult(arr_v7, arr_v11, arr_v6);
        Field25519.square(arr_v10, arr_v7);
        Field25519.square(arr_v11, arr_v10);
        for(int v2 = 2; v2 < 20; v2 += 2) {
            Field25519.square(arr_v10, arr_v11);
            Field25519.square(arr_v11, arr_v10);
        }
        Field25519.mult(arr_v10, arr_v11, arr_v7);
        Field25519.square(arr_v11, arr_v10);
        Field25519.square(arr_v10, arr_v11);
        for(int v3 = 2; v3 < 10; v3 += 2) {
            Field25519.square(arr_v11, arr_v10);
            Field25519.square(arr_v10, arr_v11);
        }
        Field25519.mult(arr_v8, arr_v10, arr_v6);
        Field25519.square(arr_v10, arr_v8);
        Field25519.square(arr_v11, arr_v10);
        for(int v4 = 2; v4 < 50; v4 += 2) {
            Field25519.square(arr_v10, arr_v11);
            Field25519.square(arr_v11, arr_v10);
        }
        Field25519.mult(arr_v9, arr_v11, arr_v8);
        Field25519.square(arr_v11, arr_v9);
        Field25519.square(arr_v10, arr_v11);
        for(int v5 = 2; v5 < 100; v5 += 2) {
            Field25519.square(arr_v11, arr_v10);
            Field25519.square(arr_v10, arr_v11);
        }
        Field25519.mult(arr_v11, arr_v10, arr_v9);
        Field25519.square(arr_v10, arr_v11);
        Field25519.square(arr_v11, arr_v10);
        for(int v = 2; v < 50; v += 2) {
            Field25519.square(arr_v10, arr_v11);
            Field25519.square(arr_v11, arr_v10);
        }
        Field25519.mult(arr_v10, arr_v11, arr_v8);
        Field25519.square(arr_v11, arr_v10);
        Field25519.square(arr_v10, arr_v11);
        Field25519.square(arr_v11, arr_v10);
        Field25519.square(arr_v10, arr_v11);
        Field25519.square(arr_v11, arr_v10);
        Field25519.mult(arr_v, arr_v11, arr_v4);
    }

    static void mult(long[] arr_v, long[] arr_v1, long[] arr_v2) {
        long[] arr_v3 = new long[19];
        Field25519.product(arr_v3, arr_v1, arr_v2);
        Field25519.reduce(arr_v3, arr_v);
    }

    static void product(long[] arr_v, long[] arr_v1, long[] arr_v2) {
        arr_v[0] = arr_v1[0] * arr_v2[0];
        long v = arr_v1[0];
        long v1 = arr_v2[1] * v;
        long v2 = arr_v1[1];
        long v3 = arr_v2[0];
        arr_v[1] = v1 + v2 * v3;
        long v4 = arr_v1[1];
        long v5 = arr_v2[1];
        arr_v[2] = v4 * 2L * v5 + arr_v2[2] * v + arr_v1[2] * v3;
        long v6 = arr_v2[2];
        long v7 = arr_v1[2];
        arr_v[3] = v4 * v6 + v7 * v5 + arr_v2[3] * v + arr_v1[3] * v3;
        long v8 = arr_v2[3];
        long v9 = arr_v1[3];
        arr_v[4] = v7 * v6 + (v4 * v8 + v9 * v5) * 2L + arr_v2[4] * v + arr_v1[4] * v3;
        long v10 = arr_v2[4];
        long v11 = arr_v1[4];
        arr_v[5] = v7 * v8 + v9 * v6 + v4 * v10 + v11 * v5 + arr_v2[5] * v + arr_v1[5] * v3;
        long v12 = arr_v2[5];
        long v13 = arr_v1[5];
        arr_v[6] = (v9 * v8 + v4 * v12 + v13 * v5) * 2L + v7 * v10 + v11 * v6 + arr_v2[6] * v + arr_v1[6] * v3;
        long v14 = arr_v2[6];
        long v15 = arr_v1[6];
        arr_v[7] = v9 * v10 + v11 * v8 + v7 * v12 + v13 * v6 + v4 * v14 + v15 * v5 + arr_v2[7] * v + arr_v1[7] * v3;
        long v16 = arr_v2[7];
        long v17 = arr_v1[7];
        arr_v[8] = v11 * v10 + (v9 * v12 + v13 * v8 + v4 * v16 + v17 * v5) * 2L + v7 * v14 + v15 * v6 + arr_v2[8] * v + arr_v1[8] * v3;
        long v18 = arr_v2[8];
        long v19 = arr_v1[8];
        arr_v[9] = v11 * v12 + v13 * v10 + v9 * v14 + v15 * v8 + v7 * v16 + v17 * v6 + v4 * v18 + v19 * v5 + v * arr_v2[9] + arr_v1[9] * v3;
        long v20 = arr_v2[9];
        long v21 = arr_v1[9];
        arr_v[10] = (v13 * v12 + v9 * v16 + v17 * v8 + v4 * v20 + v5 * v21) * 2L + v11 * v14 + v15 * v10 + v7 * v18 + v19 * v6;
        arr_v[11] = v13 * v14 + v15 * v12 + v11 * v16 + v17 * v10 + v9 * v18 + v19 * v8 + v7 * v20 + v6 * v21;
        arr_v[12] = v15 * v14 + (v13 * v16 + v17 * v12 + v9 * v20 + v8 * v21) * 2L + v11 * v18 + v19 * v10;
        arr_v[13] = v15 * v16 + v17 * v14 + v13 * v18 + v19 * v12 + v11 * v20 + v10 * v21;
        arr_v[14] = (v17 * v16 + v13 * v20 + v12 * v21) * 2L + v15 * v18 + v19 * v14;
        arr_v[15] = v17 * v18 + v19 * v16 + v15 * v20 + v14 * v21;
        arr_v[16] = v19 * v18 + (v17 * v20 + v16 * v21) * 2L;
        arr_v[17] = v19 * v20 + v18 * v21;
        arr_v[18] = v21 * 2L * v20;
    }

    static void reduce(long[] arr_v, long[] arr_v1) {
        if(arr_v.length != 19) {
            long[] arr_v2 = new long[19];
            System.arraycopy(arr_v, 0, arr_v2, 0, arr_v.length);
            arr_v = arr_v2;
        }
        Field25519.reduceSizeByModularReduction(arr_v);
        Field25519.reduceCoefficients(arr_v);
        System.arraycopy(arr_v, 0, arr_v1, 0, 10);
    }

    static void reduceCoefficients(long[] arr_v) {
        arr_v[10] = 0L;
        int v = 0;
        while(v < 10) {
            long v1 = arr_v[v];
            arr_v[v] = v1 - (v1 / 0x4000000L << 26);
            long v2 = arr_v[v + 1] + v1 / 0x4000000L;
            arr_v[v + 1] = v2;
            arr_v[v + 1] = v2 - (v2 / 0x2000000L << 25);
            v += 2;
            arr_v[v] += v2 / 0x2000000L;
        }
        long v3 = arr_v[0];
        long v4 = arr_v[10];
        arr_v[0] = v3 + (v4 << 4);
        arr_v[0] = v3 + 18 * v4;
        long v5 = v3 + 19 * v4;
        arr_v[0] = v5;
        arr_v[10] = 0L;
        arr_v[0] = v5 - (v5 / 0x4000000L << 26);
        arr_v[1] += v5 / 0x4000000L;
    }

    static void reduceSizeByModularReduction(long[] arr_v) {
        long v = arr_v[8];
        long v1 = arr_v[18];
        arr_v[8] = v + (v1 << 4);
        arr_v[8] = v + 18 * v1;
        arr_v[8] = v + 19 * v1;
        long v2 = arr_v[7];
        long v3 = arr_v[17];
        arr_v[7] = v2 + (v3 << 4);
        arr_v[7] = v2 + 18 * v3;
        arr_v[7] = v2 + 19 * v3;
        long v4 = arr_v[6];
        long v5 = arr_v[16];
        arr_v[6] = v4 + (v5 << 4);
        arr_v[6] = v4 + 18 * v5;
        arr_v[6] = v4 + 19 * v5;
        long v6 = arr_v[5];
        long v7 = arr_v[15];
        arr_v[5] = v6 + (v7 << 4);
        arr_v[5] = v6 + 18 * v7;
        arr_v[5] = v6 + 19 * v7;
        long v8 = arr_v[4];
        long v9 = arr_v[14];
        arr_v[4] = v8 + (v9 << 4);
        arr_v[4] = v8 + 18 * v9;
        arr_v[4] = v8 + 19 * v9;
        long v10 = arr_v[3];
        long v11 = arr_v[13];
        arr_v[3] = v10 + (v11 << 4);
        arr_v[3] = v10 + 18 * v11;
        arr_v[3] = v10 + 19 * v11;
        long v12 = arr_v[2];
        long v13 = arr_v[12];
        arr_v[2] = v12 + (v13 << 4);
        arr_v[2] = v12 + 18 * v13;
        arr_v[2] = v12 + 19 * v13;
        long v14 = arr_v[1];
        long v15 = arr_v[11];
        arr_v[1] = v14 + (v15 << 4);
        arr_v[1] = v14 + 18 * v15;
        arr_v[1] = v14 + 19 * v15;
        long v16 = arr_v[0];
        long v17 = arr_v[10];
        arr_v[0] = v16 + (v17 << 4);
        arr_v[0] = v16 + 18 * v17;
        arr_v[0] = v16 + 19 * v17;
    }

    static void scalarProduct(long[] arr_v, long[] arr_v1, long v) {
        for(int v1 = 0; v1 < 10; ++v1) {
            arr_v[v1] = arr_v1[v1] * v;
        }
    }

    static void square(long[] arr_v, long[] arr_v1) {
        long[] arr_v2 = new long[19];
        Field25519.squareInner(arr_v2, arr_v1);
        Field25519.reduce(arr_v2, arr_v);
    }

    private static void squareInner(long[] arr_v, long[] arr_v1) {
        long v = arr_v1[0];
        arr_v[0] = v * v;
        long v1 = arr_v1[0];
        arr_v[1] = v1 * 2L * arr_v1[1];
        long v2 = arr_v1[1];
        arr_v[2] = (v2 * v2 + arr_v1[2] * v1) * 2L;
        long v3 = arr_v1[2];
        arr_v[3] = (v2 * v3 + arr_v1[3] * v1) * 2L;
        long v4 = arr_v1[3];
        arr_v[4] = v3 * v3 + v2 * 4L * v4 + v1 * 2L * arr_v1[4];
        long v5 = arr_v1[4];
        arr_v[5] = (v3 * v4 + v2 * v5 + arr_v1[5] * v1) * 2L;
        long v6 = v4 * v4 + v3 * v5 + arr_v1[6] * v1;
        long v7 = arr_v1[5];
        arr_v[6] = (v6 + v2 * 2L * v7) * 2L;
        long v8 = arr_v1[6];
        arr_v[7] = (v4 * v5 + v3 * v7 + v2 * v8 + arr_v1[7] * v1) * 2L;
        long v9 = v3 * v8 + arr_v1[8] * v1;
        long v10 = arr_v1[7];
        arr_v[8] = v5 * v5 + (v9 + (v2 * v10 + v4 * v7) * 2L) * 2L;
        long v11 = arr_v1[8];
        arr_v[9] = (v5 * v7 + v4 * v8 + v3 * v10 + v2 * v11 + v1 * arr_v1[9]) * 2L;
        long v12 = arr_v1[9];
        arr_v[10] = (v7 * v7 + v5 * v8 + v3 * v11 + (v4 * v10 + v2 * v12) * 2L) * 2L;
        arr_v[11] = (v7 * v8 + v5 * v10 + v4 * v11 + v3 * v12) * 2L;
        arr_v[12] = v8 * v8 + (v5 * v11 + (v7 * v10 + v4 * v12) * 2L) * 2L;
        arr_v[13] = (v8 * v10 + v7 * v11 + v5 * v12) * 2L;
        arr_v[14] = (v10 * v10 + v8 * v11 + v7 * 2L * v12) * 2L;
        arr_v[15] = (v10 * v11 + v8 * v12) * 2L;
        arr_v[16] = v11 * v11 + v10 * 4L * v12;
        arr_v[17] = v11 * 2L * v12;
        arr_v[18] = 2L * v12 * v12;
    }

    static void sub(long[] arr_v, long[] arr_v1) {
        Field25519.sub(arr_v, arr_v1, arr_v);
    }

    static void sub(long[] arr_v, long[] arr_v1, long[] arr_v2) {
        for(int v = 0; v < 10; ++v) {
            arr_v[v] = arr_v1[v] - arr_v2[v];
        }
    }

    static void sum(long[] arr_v, long[] arr_v1) {
        Field25519.sum(arr_v, arr_v, arr_v1);
    }

    static void sum(long[] arr_v, long[] arr_v1, long[] arr_v2) {
        for(int v = 0; v < 10; ++v) {
            arr_v[v] = arr_v1[v] + arr_v2[v];
        }
    }
}

