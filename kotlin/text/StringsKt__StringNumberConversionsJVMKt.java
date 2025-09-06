package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001A4\u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b\u00A2\u0006\u0004\b\u0006\u0010\u0007\u001A\r\u0010\b\u001A\u00020\t*\u00020\u0003H\u0087\b\u001A\u0015\u0010\b\u001A\u00020\t*\u00020\u00032\u0006\u0010\n\u001A\u00020\u000BH\u0087\b\u001A\u000E\u0010\f\u001A\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001A\u0016\u0010\f\u001A\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001A\u00020\u000BH\u0007\u001A\r\u0010\r\u001A\u00020\u000E*\u00020\u0003H\u0087\b\u001A\u0015\u0010\r\u001A\u00020\u000E*\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\u000E\u0010\u0011\u001A\u0004\u0018\u00010\u000E*\u00020\u0003H\u0007\u001A\u0016\u0010\u0011\u001A\u0004\u0018\u00010\u000E*\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010H\u0007\u001A\u000F\u0010\u0012\u001A\u00020\u0013*\u0004\u0018\u00010\u0003H\u0087\b\u001A\r\u0010\u0014\u001A\u00020\u0015*\u00020\u0003H\u0087\b\u001A\u0015\u0010\u0014\u001A\u00020\u0015*\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\r\u0010\u0016\u001A\u00020\u0017*\u00020\u0003H\u0087\b\u001A\u0013\u0010\u0018\u001A\u0004\u0018\u00010\u0017*\u00020\u0003H\u0007\u00A2\u0006\u0002\u0010\u0019\u001A\r\u0010\u001A\u001A\u00020\u001B*\u00020\u0003H\u0087\b\u001A\u0013\u0010\u001C\u001A\u0004\u0018\u00010\u001B*\u00020\u0003H\u0007\u00A2\u0006\u0002\u0010\u001D\u001A\r\u0010\u001E\u001A\u00020\u0010*\u00020\u0003H\u0087\b\u001A\u0015\u0010\u001E\u001A\u00020\u0010*\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\r\u0010\u001F\u001A\u00020 *\u00020\u0003H\u0087\b\u001A\u0015\u0010\u001F\u001A\u00020 *\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\r\u0010!\u001A\u00020\"*\u00020\u0003H\u0087\b\u001A\u0015\u0010!\u001A\u00020\"*\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\u0015\u0010#\u001A\u00020\u0003*\u00020\u00152\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\u0015\u0010#\u001A\u00020\u0003*\u00020\u00102\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\u0015\u0010#\u001A\u00020\u0003*\u00020 2\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u001A\u0015\u0010#\u001A\u00020\u0003*\u00020\"2\u0006\u0010\u000F\u001A\u00020\u0010H\u0087\b\u00A8\u0006$"}, d2 = {"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    private static final Object screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String s, Function1 function10) {
        try {
            return ScreenFloatValueRegEx.value.matches(s) ? function10.invoke(s) : null;
        }
        catch(NumberFormatException unused_ex) {
        }
        return null;
    }

    private static final BigDecimal toBigDecimal(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return new BigDecimal(s);
    }

    private static final BigDecimal toBigDecimal(String s, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(s, mathContext0);
    }

    public static final BigDecimal toBigDecimalOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        try {
            return ScreenFloatValueRegEx.value.matches(s) ? new BigDecimal(s) : null;
        }
        catch(NumberFormatException unused_ex) {
        }
        return null;
    }

    public static final BigDecimal toBigDecimalOrNull(String s, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        try {
            return ScreenFloatValueRegEx.value.matches(s) ? new BigDecimal(s, mathContext0) : null;
        }
        catch(NumberFormatException unused_ex) {
        }
        return null;
    }

    private static final BigInteger toBigInteger(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return new BigInteger(s);
    }

    private static final BigInteger toBigInteger(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return new BigInteger(s, CharsKt.checkRadix(v));
    }

    public static final BigInteger toBigIntegerOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return StringsKt.toBigIntegerOrNull(s, 10);
    }

    public static final BigInteger toBigIntegerOrNull(String s, int v) {
        int v1 = 0;
        Intrinsics.checkNotNullParameter(s, "<this>");
        CharsKt.checkRadix(v);
        int v2 = s.length();
        switch(v2) {
            case 0: {
                return null;
            }
            case 1: {
                return CharsKt.digitOf(s.charAt(0), v) >= 0 ? new BigInteger(s, CharsKt.checkRadix(v)) : null;
            }
            default: {
                if(s.charAt(0) == 45) {
                    v1 = 1;
                }
                while(v1 < v2) {
                    if(CharsKt.digitOf(s.charAt(v1), v) < 0) {
                        return null;
                    }
                    ++v1;
                }
                return new BigInteger(s, CharsKt.checkRadix(v));
            }
        }
    }

    private static final boolean toBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    private static final byte toByte(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Byte.parseByte(s);
    }

    private static final byte toByte(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Byte.parseByte(s, CharsKt.checkRadix(v));
    }

    private static final double toDouble(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Double.parseDouble(s);
    }

    public static final Double toDoubleOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        try {
            return ScreenFloatValueRegEx.value.matches(s) ? Double.parseDouble(s) : null;
        }
        catch(NumberFormatException unused_ex) {
        }
        return null;
    }

    private static final float toFloat(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Float.parseFloat(s);
    }

    public static final Float toFloatOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        try {
            return ScreenFloatValueRegEx.value.matches(s) ? Float.parseFloat(s) : null;
        }
        catch(NumberFormatException unused_ex) {
        }
        return null;
    }

    private static final int toInt(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Integer.parseInt(s);
    }

    private static final int toInt(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Integer.parseInt(s, CharsKt.checkRadix(v));
    }

    private static final long toLong(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Long.parseLong(s);
    }

    private static final long toLong(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Long.parseLong(s, CharsKt.checkRadix(v));
    }

    private static final short toShort(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Short.parseShort(s);
    }

    private static final short toShort(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return Short.parseShort(s, CharsKt.checkRadix(v));
    }

    private static final String toString(byte b, int v) {
        String s = Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(v)));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }

    private static final String toString(int v, int v1) {
        String s = Integer.toString(v, CharsKt.checkRadix(v1));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }

    private static final String toString(long v, int v1) {
        String s = Long.toString(v, CharsKt.checkRadix(v1));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }

    private static final String toString(short v, int v1) {
        String s = Integer.toString(v, CharsKt.checkRadix(CharsKt.checkRadix(v1)));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }
}

