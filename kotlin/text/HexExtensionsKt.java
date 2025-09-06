package kotlin.text;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0004\u001A \u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\b2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000BH\u0002\u001A@\u0010\r\u001A\u00020\u000B2\u0006\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u000B2\u0006\u0010\u0010\u001A\u00020\u000B2\u0006\u0010\u0011\u001A\u00020\u000B2\u0006\u0010\u0012\u001A\u00020\u000B2\u0006\u0010\u0013\u001A\u00020\u000B2\u0006\u0010\u0014\u001A\u00020\u000BH\u0000\u001A@\u0010\u0015\u001A\u00020\u000B2\u0006\u0010\u0016\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u000B2\u0006\u0010\u0010\u001A\u00020\u000B2\u0006\u0010\u0011\u001A\u00020\u000B2\u0006\u0010\u0012\u001A\u00020\u000B2\u0006\u0010\u0013\u001A\u00020\u000B2\u0006\u0010\u0014\u001A\u00020\u000BH\u0000\u001A \u0010\u0017\u001A\u00020\b2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\u000BH\u0002\u001A,\u0010\u0018\u001A\u00020\u000B*\u00020\u00052\u0006\u0010\u0019\u001A\u00020\u00052\u0006\u0010\u001A\u001A\u00020\u000B2\u0006\u0010\u001B\u001A\u00020\u000B2\u0006\u0010\u001C\u001A\u00020\u0005H\u0002\u001A,\u0010\u001D\u001A\u00020\u001E*\u00020\u00052\u0006\u0010\u001F\u001A\u00020\u000B2\u0006\u0010\u001B\u001A\u00020\u000B2\u0006\u0010 \u001A\u00020\u000B2\u0006\u0010!\u001A\u00020\"H\u0002\u001A\u001C\u0010#\u001A\u00020\u000B*\u00020\u00052\u0006\u0010\u001A\u001A\u00020\u000B2\u0006\u0010\u001B\u001A\u00020\u000BH\u0002\u001A\u0014\u0010$\u001A\u00020\u000B*\u00020\u00052\u0006\u0010\u001A\u001A\u00020\u000BH\u0002\u001A*\u0010%\u001A\u00020&*\u00020\u00052\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0003\u001A\u0016\u0010%\u001A\u00020&*\u00020\u00052\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A*\u0010)\u001A\u00020**\u00020\u00052\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0003\u001A\u0016\u0010)\u001A\u00020**\u00020\u00052\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A*\u0010+\u001A\u00020\u000B*\u00020\u00052\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0003\u001A\u0016\u0010+\u001A\u00020\u000B*\u00020\u00052\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A*\u0010,\u001A\u00020\b*\u00020\u00052\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0003\u001A\u0016\u0010,\u001A\u00020\b*\u00020\u00052\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A0\u0010-\u001A\u00020\b*\u00020\u00052\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\u0006\u0010\'\u001A\u00020(2\u0006\u0010 \u001A\u00020\u000BH\u0003\u001A*\u0010.\u001A\u00020/*\u00020\u00052\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0003\u001A\u0016\u0010.\u001A\u00020/*\u00020\u00052\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A\u0016\u00100\u001A\u00020\u0005*\u00020&2\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A*\u00100\u001A\u00020\u0005*\u00020*2\b\b\u0002\u0010\u001F\u001A\u00020\u000B2\b\b\u0002\u0010\u001B\u001A\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A\u0016\u00100\u001A\u00020\u0005*\u00020*2\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A\u0016\u00100\u001A\u00020\u0005*\u00020\u000B2\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A\u0016\u00100\u001A\u00020\u0005*\u00020\b2\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A\u0016\u00100\u001A\u00020\u0005*\u00020/2\b\b\u0002\u0010\'\u001A\u00020(H\u0007\u001A\u001C\u00101\u001A\u00020\u0005*\u00020\b2\u0006\u0010\'\u001A\u00020(2\u0006\u00102\u001A\u00020\u000BH\u0003\"\u0016\u0010\u0000\u001A\u00020\u00018\u0002X\u0083\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0005X\u0082T\u00A2\u0006\u0002\n\u0000\u00A8\u00063"}, d2 = {"HEX_DIGITS_TO_DECIMAL", "", "getHEX_DIGITS_TO_DECIMAL$annotations", "()V", "LOWER_CASE_HEX_DIGITS", "", "UPPER_CASE_HEX_DIGITS", "charsPerSet", "", "charsPerElement", "elementsPerSet", "", "elementSeparatorLength", "formattedStringLength", "totalBytes", "bytesPerLine", "bytesPerGroup", "groupSeparatorLength", "byteSeparatorLength", "bytePrefixLength", "byteSuffixLength", "parsedByteArrayMaxSize", "stringLength", "wholeElementsPerSet", "checkContainsAt", "part", "index", "endIndex", "partName", "checkHexLength", "", "startIndex", "maxDigits", "requireMaxLength", "", "checkNewLineAt", "decimalFromHexDigitAt", "hexToByte", "", "format", "Lkotlin/text/HexFormat;", "hexToByteArray", "", "hexToInt", "hexToLong", "hexToLongImpl", "hexToShort", "", "toHexString", "toHexStringImpl", "bits", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class HexExtensionsKt {
    private static final int[] HEX_DIGITS_TO_DECIMAL = null;
    private static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";
    private static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";

    static {
        int[] arr_v = new int[0x80];
        int v = 0;
        for(int v1 = 0; v1 < 0x80; ++v1) {
            arr_v[v1] = -1;
        }
        int v3 = 0;
        for(int v2 = 0; v3 < 16; ++v2) {
            arr_v["0123456789abcdef".charAt(v3)] = v2;
            ++v3;
        }
        for(int v4 = 0; v < 16; ++v4) {
            arr_v["0123456789ABCDEF".charAt(v)] = v4;
            ++v;
        }
        HexExtensionsKt.HEX_DIGITS_TO_DECIMAL = arr_v;
    }

    private static final long charsPerSet(long v, int v1, int v2) {
        if(v1 <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        return v * ((long)v1) + ((long)v2) * (((long)v1) - 1L);
    }

    private static final int checkContainsAt(String s, String s1, int v, int v1, String s2) {
        int v2 = s1.length() + v;
        if(v2 <= v1 && StringsKt.regionMatches(s, v, s1, 0, s1.length(), true)) {
            return v2;
        }
        int v3 = RangesKt.coerceAtMost(v2, v1);
        Intrinsics.checkNotNull(s, "null cannot be cast to non-null type java.lang.String");
        String s3 = s.substring(v, v3);
        Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String…ing(startIndex, endIndex)");
        throw new NumberFormatException("Expected " + s2 + " \"" + s1 + "\" at index " + v + ", but was " + s3);
    }

    private static final void checkHexLength(String s, int v, int v1, int v2, boolean z) {
        int v3 = v1 - v;
        if(z) {
            if(v3 == v2) {
                return;
            }
        }
        else if(v3 <= v2) {
            return;
        }
        Intrinsics.checkNotNull(s, "null cannot be cast to non-null type java.lang.String");
        String s1 = s.substring(v, v1);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
        throw new NumberFormatException("Expected " + (z ? "exactly" : "at most") + ' ' + v2 + " hexadecimal digits at index " + v + ", but was " + s1 + " of length " + v3);
    }

    private static final int checkNewLineAt(String s, int v, int v1) {
        switch(s.charAt(v)) {
            case 10: {
                return v + 1;
            }
            case 13: {
                return v + 1 >= v1 || s.charAt(v + 1) != 10 ? v + 1 : v + 2;
            }
            default: {
                throw new NumberFormatException("Expected a new line at index " + v + ", but was " + s.charAt(v));
            }
        }
    }

    private static final int decimalFromHexDigitAt(String s, int v) {
        int v1 = s.charAt(v);
        if(v1 <= 0x7F) {
            int v2 = HexExtensionsKt.HEX_DIGITS_TO_DECIMAL[v1];
            if(v2 >= 0) {
                return v2;
            }
        }
        throw new NumberFormatException("Expected a hexadecimal digit at index " + v + ", but was " + s.charAt(v));
    }

    public static final int formattedStringLength(int v, int v1, int v2, int v3, int v4, int v5, int v6) {
        if(v <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int v7 = (v - 1) / v1;
        int v8 = (v1 - 1) / v2;
        int v9 = v % v1;
        if(v9 != 0) {
            v1 = v9;
        }
        int v10 = v8 * v7 + (v1 - 1) / v2;
        long v11 = ((long)v7) + ((long)v10) * ((long)v3) + ((long)(v - 1 - v7 - v10)) * ((long)v4) + ((long)v) * (((long)v5) + 2L + ((long)v6));
        if(!RangesKt.intRangeContains(new IntRange(0, 0x7FFFFFFF), v11)) {
            throw new IllegalArgumentException("The resulting string length is too big: " + ULong.toString-impl(v11));
        }
        return (int)v11;
    }

    private static void getHEX_DIGITS_TO_DECIMAL$annotations() {
    }

    private static final byte hexToByte(String s, int v, int v1, HexFormat hexFormat0) {
        return (byte)(((int)HexExtensionsKt.hexToLongImpl(s, v, v1, hexFormat0, 2)));
    }

    public static final byte hexToByte(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.hexToByte(s, 0, s.length(), hexFormat0);
    }

    static byte hexToByte$default(String s, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToByte(s, v, v1, hexFormat0);
    }

    public static byte hexToByte$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToByte(s, hexFormat0);
    }

    private static final byte[] hexToByteArray(String s, int v, int v1, HexFormat hexFormat0) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(v, v1, s.length());
        if(v == v1) {
            return new byte[0];
        }
        BytesHexFormat hexFormat$BytesHexFormat0 = hexFormat0.getBytes();
        int v2 = hexFormat$BytesHexFormat0.getBytesPerLine();
        int v3 = hexFormat$BytesHexFormat0.getBytesPerGroup();
        String s1 = hexFormat$BytesHexFormat0.getBytePrefix();
        String s2 = hexFormat$BytesHexFormat0.getByteSuffix();
        String s3 = hexFormat$BytesHexFormat0.getByteSeparator();
        String s4 = hexFormat$BytesHexFormat0.getGroupSeparator();
        int v4 = HexExtensionsKt.parsedByteArrayMaxSize(v1 - v, v2, v3, s4.length(), s3.length(), s1.length(), s2.length());
        byte[] arr_b = new byte[v4];
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        while(v < v1) {
            if(v6 == v2) {
                v = HexExtensionsKt.checkNewLineAt(s, v, v1);
                v6 = 0;
                v7 = 0;
            }
            else if(v7 == v3) {
                v = HexExtensionsKt.checkContainsAt(s, s4, v, v1, "group separator");
                v7 = 0;
            }
            else if(v7 != 0) {
                v = HexExtensionsKt.checkContainsAt(s, s3, v, v1, "byte separator");
            }
            ++v6;
            ++v7;
            int v8 = HexExtensionsKt.checkContainsAt(s, s1, v, v1, "byte prefix");
            HexExtensionsKt.checkHexLength(s, v8, RangesKt.coerceAtMost(v8 + 2, v1), 2, true);
            arr_b[v5] = (byte)(HexExtensionsKt.decimalFromHexDigitAt(s, v8) << 4 | HexExtensionsKt.decimalFromHexDigitAt(s, v8 + 1));
            v = HexExtensionsKt.checkContainsAt(s, s2, v8 + 2, v1, "byte suffix");
            ++v5;
        }
        if(v5 == v4) {
            return arr_b;
        }
        byte[] arr_b1 = Arrays.copyOf(arr_b, v5);
        Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, newSize)");
        return arr_b1;
    }

    public static final byte[] hexToByteArray(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.hexToByteArray(s, 0, s.length(), hexFormat0);
    }

    static byte[] hexToByteArray$default(String s, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToByteArray(s, v, v1, hexFormat0);
    }

    public static byte[] hexToByteArray$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToByteArray(s, hexFormat0);
    }

    private static final int hexToInt(String s, int v, int v1, HexFormat hexFormat0) {
        return (int)HexExtensionsKt.hexToLongImpl(s, v, v1, hexFormat0, 8);
    }

    public static final int hexToInt(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.hexToInt(s, 0, s.length(), hexFormat0);
    }

    static int hexToInt$default(String s, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToInt(s, v, v1, hexFormat0);
    }

    public static int hexToInt$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToInt(s, hexFormat0);
    }

    private static final long hexToLong(String s, int v, int v1, HexFormat hexFormat0) {
        return HexExtensionsKt.hexToLongImpl(s, v, v1, hexFormat0, 16);
    }

    public static final long hexToLong(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.hexToLong(s, 0, s.length(), hexFormat0);
    }

    static long hexToLong$default(String s, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToLong(s, v, v1, hexFormat0);
    }

    public static long hexToLong$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToLong(s, hexFormat0);
    }

    private static final long hexToLongImpl(String s, int v, int v1, HexFormat hexFormat0, int v2) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(v, v1, s.length());
        String s1 = hexFormat0.getNumber().getPrefix();
        String s2 = hexFormat0.getNumber().getSuffix();
        if(s1.length() + s2.length() < v1 - v) {
            int v3 = HexExtensionsKt.checkContainsAt(s, s1, v, v1, "prefix");
            int v4 = v1 - s2.length();
            HexExtensionsKt.checkContainsAt(s, s2, v4, v1, "suffix");
            HexExtensionsKt.checkHexLength(s, v3, v4, v2, false);
            long v5 = 0L;
            while(v3 < v4) {
                v5 = v5 << 4 | ((long)HexExtensionsKt.decimalFromHexDigitAt(s, v3));
                ++v3;
            }
            return v5;
        }
        Intrinsics.checkNotNull(s, "null cannot be cast to non-null type java.lang.String");
        String s3 = s.substring(v, v1);
        Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String…ing(startIndex, endIndex)");
        throw new NumberFormatException("Expected a hexadecimal number with prefix \"" + s1 + "\" and suffix \"" + s2 + "\", but was " + s3);
    }

    static long hexToLongImpl$default(String s, int v, int v1, HexFormat hexFormat0, int v2, int v3, Object object0) {
        if((v3 & 1) != 0) {
            v = 0;
        }
        if((v3 & 2) != 0) {
            v1 = s.length();
        }
        return HexExtensionsKt.hexToLongImpl(s, v, v1, hexFormat0, v2);
    }

    private static final short hexToShort(String s, int v, int v1, HexFormat hexFormat0) {
        return (short)(((int)HexExtensionsKt.hexToLongImpl(s, v, v1, hexFormat0, 4)));
    }

    public static final short hexToShort(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.hexToShort(s, 0, s.length(), hexFormat0);
    }

    static short hexToShort$default(String s, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = s.length();
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToShort(s, v, v1, hexFormat0);
    }

    public static short hexToShort$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.hexToShort(s, hexFormat0);
    }

    public static final int parsedByteArrayMaxSize(int v, int v1, int v2, int v3, int v4, int v5, int v6) {
        long v9;
        if(v <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        long v7 = ((long)v5) + 2L + ((long)v6);
        long v8 = HexExtensionsKt.charsPerSet(v7, v2, v4);
        if(v1 <= v2) {
            v9 = HexExtensionsKt.charsPerSet(v7, v1, v4);
        }
        else {
            v9 = HexExtensionsKt.charsPerSet(v8, v1 / v2, v3);
            int v10 = v1 % v2;
            if(v10 != 0) {
                v9 = v9 + ((long)v3) + HexExtensionsKt.charsPerSet(v7, v10, v4);
            }
        }
        long v11 = HexExtensionsKt.wholeElementsPerSet(v, v9, 1);
        long v12 = ((long)v) - (v9 + 1L) * v11;
        long v13 = HexExtensionsKt.wholeElementsPerSet(v12, v8, v3);
        long v14 = v12 - (v8 + ((long)v3)) * v13;
        long v15 = HexExtensionsKt.wholeElementsPerSet(v14, v7, v4);
        return v14 - (v7 + ((long)v4)) * v15 <= 0L ? ((int)(v11 * ((long)v1) + v13 * ((long)v2) + v15)) : ((int)(v11 * ((long)v1) + v13 * ((long)v2) + v15 + 1L));
    }

    public static final String toHexString(byte b, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexStringImpl(b, hexFormat0, 8);
    }

    public static final String toHexString(int v, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexStringImpl(v, hexFormat0, 0x20);
    }

    public static final String toHexString(long v, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexStringImpl(v, hexFormat0, 0x40);
    }

    public static final String toHexString(short v, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexStringImpl(v, hexFormat0, 16);
    }

    public static final String toHexString(byte[] arr_b, int v, int v1, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(v, v1, arr_b.length);
        if(v == v1) {
            return "";
        }
        String s = hexFormat0.getUpperCase() ? "0123456789ABCDEF" : "0123456789abcdef";
        BytesHexFormat hexFormat$BytesHexFormat0 = hexFormat0.getBytes();
        int v2 = hexFormat$BytesHexFormat0.getBytesPerLine();
        int v3 = hexFormat$BytesHexFormat0.getBytesPerGroup();
        String s1 = hexFormat$BytesHexFormat0.getBytePrefix();
        String s2 = hexFormat$BytesHexFormat0.getByteSuffix();
        String s3 = hexFormat$BytesHexFormat0.getByteSeparator();
        String s4 = hexFormat$BytesHexFormat0.getGroupSeparator();
        int v4 = HexExtensionsKt.formattedStringLength(v1 - v, v2, v3, s4.length(), s3.length(), s1.length(), s2.length());
        StringBuilder stringBuilder0 = new StringBuilder(v4);
        int v5 = 0;
        int v6 = 0;
        while(v < v1) {
            int v7 = arr_b[v];
            if(v5 == v2) {
                stringBuilder0.append('\n');
                v5 = 0;
                v6 = 0;
            }
            else if(v6 == v3) {
                stringBuilder0.append(s4);
                v6 = 0;
            }
            if(v6 != 0) {
                stringBuilder0.append(s3);
            }
            stringBuilder0.append(s1);
            stringBuilder0.append(s.charAt((v7 & 0xFF) >> 4));
            stringBuilder0.append(s.charAt(v7 & 15));
            stringBuilder0.append(s2);
            ++v6;
            ++v5;
            ++v;
        }
        if(v4 != stringBuilder0.length()) {
            throw new IllegalStateException("Check failed.");
        }
        String s5 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s5, "StringBuilder(capacity).…builderAction).toString()");
        return s5;
    }

    public static final String toHexString(byte[] arr_b, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(arr_b, 0, arr_b.length, hexFormat0);
    }

    public static String toHexString$default(byte b, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.toHexString(b, hexFormat0);
    }

    public static String toHexString$default(int v, HexFormat hexFormat0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    public static String toHexString$default(long v, HexFormat hexFormat0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    public static String toHexString$default(short v, HexFormat hexFormat0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    public static String toHexString$default(byte[] arr_b, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = arr_b.length;
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.toHexString(arr_b, v, v1, hexFormat0);
    }

    public static String toHexString$default(byte[] arr_b, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        return HexExtensionsKt.toHexString(arr_b, hexFormat0);
    }

    private static final String toHexStringImpl(long v, HexFormat hexFormat0, int v1) {
        if((v1 & 3) != 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        String s = hexFormat0.getUpperCase() ? "0123456789ABCDEF" : "0123456789abcdef";
        String s1 = hexFormat0.getNumber().getPrefix();
        String s2 = hexFormat0.getNumber().getSuffix();
        boolean z = hexFormat0.getNumber().getRemoveLeadingZeros();
        StringBuilder stringBuilder0 = new StringBuilder(s1.length() + (v1 >> 2) + s2.length());
        stringBuilder0.append(s1);
        while(v1 > 0) {
            v1 -= 4;
            int v2 = (int)(v >> v1 & 15L);
            z = z && v2 == 0 && v1 > 0;
            if(!z) {
                stringBuilder0.append(s.charAt(v2));
            }
        }
        stringBuilder0.append(s2);
        String s3 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s3, "StringBuilder(capacity).…builderAction).toString()");
        return s3;
    }

    private static final long wholeElementsPerSet(long v, long v1, int v2) {
        return v <= 0L || v1 <= 0L ? 0L : (v + ((long)v2)) / (v1 + ((long)v2));
    }
}

