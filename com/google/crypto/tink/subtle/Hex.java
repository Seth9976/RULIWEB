package com.google.crypto.tink.subtle;

public final class Hex {
    public static byte[] decode(String s) [...] // 潜在的解密器

    public static String encode(byte[] arr_b) {
        StringBuilder stringBuilder0 = new StringBuilder(arr_b.length * 2);
        for(int v = 0; v < arr_b.length; ++v) {
            int v1 = arr_b[v] & 0xFF;
            stringBuilder0.append("0123456789abcdef".charAt(v1 / 16));
            stringBuilder0.append("0123456789abcdef".charAt(v1 % 16));
        }
        return stringBuilder0.toString();
    }
}

