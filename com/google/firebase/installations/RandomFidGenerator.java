package com.google.firebase.installations;

import android.util.Base64;
import java.nio.charset.Charset;
import java.util.UUID;

public class RandomFidGenerator {
    private static final byte FID_4BIT_PREFIX = 0;
    private static final int FID_LENGTH = 22;
    private static final byte REMOVE_PREFIX_MASK;

    static {
        RandomFidGenerator.FID_4BIT_PREFIX = 0x70;
        RandomFidGenerator.REMOVE_PREFIX_MASK = 15;
    }

    // 去混淆评级： 中等(50)
    public String createRandomFid() [...] // 潜在的解密器

    private static String encodeFidBase64UrlSafe(byte[] arr_b) {
        return new String(Base64.encode(arr_b, 11), Charset.defaultCharset()).substring(0, 22);
    }

    private static byte[] getBytesFromUUID(UUID uUID0, byte[] arr_b) [...] // 潜在的解密器
}

