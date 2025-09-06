package com.google.crypto.tink.internal;

import com.google.crypto.tink.util.Bytes;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Objects;
import javax.annotation.Nullable;

public final class Util {
    public static final Charset UTF_8;

    static {
        Util.UTF_8 = Charset.forName("UTF-8");
    }

    @Nullable
    public static Integer getAndroidApiLevel() {
        return Util.isAndroid() ? BuildDispatchedCode.getApiLevel() : null;
    }

    // 去混淆评级： 低(20)
    public static boolean isAndroid() {
        return Objects.equals("The Android Project", "The Android Project");
    }

    public static int randKeyId() {
        SecureRandom secureRandom0 = new SecureRandom();
        byte[] arr_b = new byte[4];
        int v;
        for(v = 0; v == 0; v = (arr_b[0] & 0x7F) << 24 | (arr_b[1] & 0xFF) << 16 | (arr_b[2] & 0xFF) << 8 | arr_b[3] & 0xFF) {
            secureRandom0.nextBytes(arr_b);
        }
        return v;
    }

    private static final byte toByteFromPrintableAscii(char c) {
        if(c < 33 || c > 0x7E) {
            throw new TinkBugException("Not a printable ASCII character: " + c);
        }
        return (byte)c;
    }

    public static final Bytes toBytesFromPrintableAscii(String s) {
        byte[] arr_b = new byte[s.length()];
        for(int v = 0; v < s.length(); ++v) {
            arr_b[v] = Util.toByteFromPrintableAscii(s.charAt(v));
        }
        return Bytes.copyFrom(arr_b);
    }
}

