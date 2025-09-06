package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public class Strings {
    private static final Pattern zza;

    static {
        Strings.zza = Pattern.compile("\\$\\{(.*?)\\}");
    }

    // 去混淆评级： 低(20)
    public static String emptyToNull(String s) {
        return TextUtils.isEmpty(s) ? null : s;
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = false)
    public static boolean isEmptyOrWhitespace(String s) {
        return s == null || s.trim().isEmpty();
    }
}

