package com.google.android.gms.common.util;

public class MurmurHash3 {
    public static int murmurhash3_x86_32(byte[] arr_b, int v, int v1, int v2) {
        int v7;
        int v4;
        for(int v3 = v; true; v3 += 4) {
            v4 = (v1 & -4) + v;
            if(v3 >= v4) {
                break;
            }
            int v5 = (arr_b[v3] & 0xFF | (arr_b[v3 + 1] & 0xFF) << 8 | (arr_b[v3 + 2] & 0xFF) << 16 | arr_b[v3 + 3] << 24) * 0xCC9E2D51;
            int v6 = v2 ^ (v5 >>> 17 | v5 << 15) * 461845907;
            v2 = (v6 >>> 19 | v6 << 13) * 5 - 430675100;
        }
        switch(v1 & 3) {
            case 1: {
                v7 = (arr_b[v4] & 0xFF) * 0xCC9E2D51;
                v2 ^= (v7 >>> 17 | v7 << 15) * 461845907;
                break;
            }
            case 2: {
                v7 = (arr_b[v4] & 0xFF | (arr_b[v4 + 1] & 0xFF) << 8) * 0xCC9E2D51;
                v2 ^= (v7 >>> 17 | v7 << 15) * 461845907;
                break;
            }
            case 3: {
                v7 = (arr_b[v4] & 0xFF | ((arr_b[v4 + 2] & 0xFF) << 16 | (arr_b[v4 + 1] & 0xFF) << 8)) * 0xCC9E2D51;
                v2 ^= (v7 >>> 17 | v7 << 15) * 461845907;
            }
        }
        int v8 = (v2 ^ v1 ^ (v2 ^ v1) >>> 16) * -2048144789;
        int v9 = (v8 ^ v8 >>> 13) * -1028477387;
        return v9 ^ v9 >>> 16;
    }
}

