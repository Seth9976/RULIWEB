package com.google.android.gms.auth;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

public final class CookieUtil {
    // 去混淆评级： 低(25)
    public static String getCookieUrl(String s, Boolean boolean0) {
        Preconditions.checkNotEmpty(s);
        return CookieUtil.zza(boolean0) ? "https://" + s : "http://" + s;
    }

    public static String getCookieValue(String s, String s1, String s2, String s3, Boolean boolean0, Boolean boolean1, Long long0) {
        if(s == null) {
            s = "";
        }
        StringBuilder stringBuilder0 = new StringBuilder(s);
        stringBuilder0.append('=');
        if(!TextUtils.isEmpty(s1)) {
            stringBuilder0.append(s1);
        }
        if(CookieUtil.zza(boolean0)) {
            stringBuilder0.append(";HttpOnly");
        }
        if(CookieUtil.zza(boolean1)) {
            stringBuilder0.append(";Secure");
        }
        if(!TextUtils.isEmpty(s2)) {
            stringBuilder0.append(";Domain=");
            stringBuilder0.append(s2);
        }
        if(!TextUtils.isEmpty(s3)) {
            stringBuilder0.append(";Path=");
            stringBuilder0.append(s3);
        }
        if(long0 != null && ((long)long0) > 0L) {
            stringBuilder0.append(";Max-Age=");
            stringBuilder0.append(long0);
        }
        if(!TextUtils.isEmpty(null)) {
            stringBuilder0.append(";Priority=null");
        }
        if(!TextUtils.isEmpty(null)) {
            stringBuilder0.append(";SameSite=null");
        }
        return stringBuilder0.toString();
    }

    private static boolean zza(Boolean boolean0) [...] // 潜在的解密器
}

