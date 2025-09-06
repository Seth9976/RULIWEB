package com.google.android.gms.common.util;

public class Hex {
    private static final char[] zza;
    private static final char[] zzb;

    static {
        Hex.zza = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Hex.zzb = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public static String bytesToStringLowercase(byte[] arr_b) {
        char[] arr_c = new char[arr_b.length + arr_b.length];
        int v1 = 0;
        for(int v = 0; v < arr_b.length; ++v) {
            int v2 = arr_b[v];
            arr_c[v1] = Hex.zzb[(v2 & 0xFF) >>> 4];
            arr_c[v1 + 1] = Hex.zzb[v2 & 15];
            v1 += 2;
        }
        return new String(arr_c);
    }

    public static String bytesToStringUppercase(byte[] arr_b) {
        return Hex.bytesToStringUppercase(arr_b, false);
    }

    public static String bytesToStringUppercase(byte[] arr_b, boolean z) {
        StringBuilder stringBuilder0 = new StringBuilder(arr_b.length + arr_b.length);
        for(int v = 0; v < arr_b.length && (!z || v != arr_b.length - 1 || (arr_b[v] & 0xFF) != 0); ++v) {
            stringBuilder0.append(Hex.zza[(arr_b[v] & 0xF0) >>> 4]);
            stringBuilder0.append(Hex.zza[arr_b[v] & 15]);
        }
        return stringBuilder0.toString();
    }

    public static byte[] stringToBytes(String s) throws IllegalArgumentException {
        int v = s.length();
        if(v % 2 != 0) {
            throw new IllegalArgumentException("Hex string has odd number of characters");
        }
        byte[] arr_b = new byte[v / 2];
        for(int v1 = 0; v1 < v; v1 += 2) {
            arr_b[v1 / 2] = (byte)Integer.parseInt(s.substring(v1, v1 + 2), 16);
        }
        return arr_b;
    }
}

