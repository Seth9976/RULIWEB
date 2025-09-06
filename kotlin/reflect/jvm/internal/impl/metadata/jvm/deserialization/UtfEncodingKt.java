package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.Intrinsics;

public final class UtfEncodingKt {
    public static final byte[] stringsToBytes(String[] arr_s) {
        Intrinsics.checkNotNullParameter(arr_s, "strings");
        int v1 = 0;
        for(int v = 0; v < arr_s.length; ++v) {
            v1 += arr_s[v].length();
        }
        byte[] arr_b = new byte[v1];
        for(int v2 = 0; v2 < arr_s.length; ++v2) {
            String s = arr_s[v2];
            int v4 = s.length();
            int v5 = 0;
            for(int v3 = 0; v5 < v4; ++v3) {
                arr_b[v3] = (byte)s.charAt(v5);
                ++v5;
            }
        }
        return arr_b;
    }
}

