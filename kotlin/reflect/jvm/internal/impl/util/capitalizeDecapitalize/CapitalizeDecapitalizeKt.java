package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CapitalizeDecapitalizeKt {
    public static final String capitalizeAsciiOnly(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        if(s.length() == 0) {
            return s;
        }
        int v = s.charAt(0);
        if(97 <= v && v < 0x7B) {
            String s1 = s.substring(1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
            return Character.toUpperCase(((char)v)) + s1;
        }
        return s;
    }

    public static final String decapitalizeAsciiOnly(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        if(s.length() == 0) {
            return s;
        }
        int v = s.charAt(0);
        if(65 <= v && v < 91) {
            String s1 = s.substring(1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
            return Character.toLowerCase(((char)v)) + s1;
        }
        return s;
    }

    public static final String decapitalizeSmartForCompiler(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        if(s.length() == 0) {
            return s;
        }
        if(!CapitalizeDecapitalizeKt.isUpperCaseCharAt(s, 0, z)) {
            return s;
        }
        if(s.length() != 1 && CapitalizeDecapitalizeKt.isUpperCaseCharAt(s, 1, z)) {
            Object object0 = null;
            for(Object object1: StringsKt.getIndices(s)) {
                if(!CapitalizeDecapitalizeKt.isUpperCaseCharAt(s, ((Number)object1).intValue(), z)) {
                    object0 = object1;
                    break;
                }
            }
            if(((Integer)object0) != null) {
                int v = (int)(((Integer)object0));
                String s1 = s.substring(0, v - 1);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
                String s2 = s.substring(v - 1);
                Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
                return CapitalizeDecapitalizeKt.toLowerCase(s1, z) + s2;
            }
            return CapitalizeDecapitalizeKt.toLowerCase(s, z);
        }
        if(z) {
            return CapitalizeDecapitalizeKt.decapitalizeAsciiOnly(s);
        }
        if(s.length() > 0) {
            String s3 = s.substring(1);
            Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
            return Character.toLowerCase(s.charAt(0)) + s3;
        }
        return s;
    }

    private static final boolean isUpperCaseCharAt(String s, int v, boolean z) {
        int v1 = s.charAt(v);
        return z ? 65 <= v1 && v1 < 91 : Character.isUpperCase(((char)v1));
    }

    private static final String toLowerCase(String s, boolean z) {
        if(z) {
            return CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(s);
        }
        String s1 = s.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return s1;
    }

    public static final String toLowerCaseAsciiOnly(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        StringBuilder stringBuilder0 = new StringBuilder(s.length());
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            stringBuilder0.append(((char)(65 > v2 || v2 >= 91 ? s.charAt(v1) : Character.toLowerCase(((char)v2)))));
        }
        String s1 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "builder.toString()");
        return s1;
    }
}

