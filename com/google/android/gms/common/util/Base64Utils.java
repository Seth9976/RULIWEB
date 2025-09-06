package com.google.android.gms.common.util;

import android.util.Base64;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public final class Base64Utils {
    public static byte[] decode(String s) {
        return s == null ? null : Base64.decode(s, 0);
    }

    public static byte[] decodeUrlSafe(String s) {
        return s == null ? null : Base64.decode(s, 10);
    }

    @ResultIgnorabilityUnspecified
    public static byte[] decodeUrlSafeNoPadding(String s) {
        return s == null ? null : Base64.decode(s, 11);
    }

    public static String encode(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(arr_b, 0);
    }

    public static String encodeUrlSafe(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(arr_b, 10);
    }

    public static String encodeUrlSafeNoPadding(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(arr_b, 11);
    }
}

