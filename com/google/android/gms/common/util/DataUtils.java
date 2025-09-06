package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

public final class DataUtils {
    public static void copyStringToBuffer(String s, CharArrayBuffer charArrayBuffer0) {
        if(TextUtils.isEmpty(s)) {
            charArrayBuffer0.sizeCopied = 0;
            return;
        }
        if(charArrayBuffer0.data == null || charArrayBuffer0.data.length < s.length()) {
            charArrayBuffer0.data = s.toCharArray();
        }
        charArrayBuffer0.sizeCopied = s.length();
    }

    public static byte[] loadImageBytes(Bitmap bitmap0) {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        bitmap0.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream0);
        return byteArrayOutputStream0.toByteArray();
    }
}

