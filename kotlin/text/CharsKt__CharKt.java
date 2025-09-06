package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0010\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0002\u001A\f\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0007\u001A\u0014\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0002H\u0007\u001A\f\u0010\u0004\u001A\u00020\u0002*\u00020\u0001H\u0007\u001A\u0014\u0010\u0004\u001A\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0002H\u0007\u001A\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001A\u001B\u0010\u0005\u001A\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0007\u001A\u001C\u0010\b\u001A\u00020\t*\u00020\u00012\u0006\u0010\n\u001A\u00020\u00012\b\b\u0002\u0010\u000B\u001A\u00020\t\u001A\n\u0010\f\u001A\u00020\t*\u00020\u0001\u001A\u0015\u0010\r\u001A\u00020\u000E*\u00020\u00012\u0006\u0010\n\u001A\u00020\u000EH\u0087\n\u001A\f\u0010\u000F\u001A\u00020\u000E*\u00020\u0001H\u0007¨\u0006\u0010"}, d2 = {"digitToChar", "", "", "radix", "digitToInt", "digitToIntOrNull", "(C)Ljava/lang/Integer;", "(CI)Ljava/lang/Integer;", "equals", "", "other", "ignoreCase", "isSurrogate", "plus", "", "titlecase", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/CharsKt")
class CharsKt__CharKt extends CharsKt__CharJVMKt {
    public static final char digitToChar(int v) {
        if(!new IntRange(0, 9).contains(v)) {
            throw new IllegalArgumentException("Int " + v + " is not a decimal digit");
        }
        return (char)(v + 0x30);
    }

    public static final char digitToChar(int v, int v1) {
        if(!new IntRange(2, 36).contains(v1)) {
            throw new IllegalArgumentException("Invalid radix: " + v1 + ". Valid radix values are in range 2..36");
        }
        if(v < 0 || v >= v1) {
            throw new IllegalArgumentException("Digit " + v + " does not represent a valid digit in radix " + v1);
        }
        return v >= 10 ? ((char)(((char)(v + 65)) - 10)) : ((char)(v + 0x30));
    }

    public static final int digitToInt(char c) {
        int v = CharsKt.digitOf(c, 10);
        if(v < 0) {
            throw new IllegalArgumentException("Char " + c + " is not a decimal digit");
        }
        return v;
    }

    public static final int digitToInt(char c, int v) {
        Integer integer0 = CharsKt.digitToIntOrNull(c, v);
        if(integer0 == null) {
            throw new IllegalArgumentException("Char " + c + " is not a digit in the given radix=" + v);
        }
        return (int)integer0;
    }

    public static final Integer digitToIntOrNull(char c) {
        Integer integer0 = CharsKt.digitOf(c, 10);
        return integer0.intValue() < 0 ? null : integer0;
    }

    public static final Integer digitToIntOrNull(char c, int v) {
        CharsKt.checkRadix(v);
        Integer integer0 = CharsKt.digitOf(c, v);
        return integer0.intValue() < 0 ? null : integer0;
    }

    public static final boolean equals(char c, char c1, boolean z) {
        if(c == c1) {
            return true;
        }
        if(!z) {
            return false;
        }
        int v = Character.toUpperCase(c);
        int v1 = Character.toUpperCase(c1);
        return v == v1 || Character.toLowerCase(((char)v)) == Character.toLowerCase(((char)v1));
    }

    public static boolean equals$default(char c, char c1, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return CharsKt.equals(c, c1, z);
    }

    public static final boolean isSurrogate(char c) {
        return new CharRange('\uD800', '\uDFFF').contains(c);
    }

    private static final String plus(char c, String s) {
        Intrinsics.checkNotNullParameter(s, "other");
        return c + s;
    }

    public static final String titlecase(char c) {
        return _OneToManyTitlecaseMappingsKt.titlecaseImpl(c);
    }
}

