package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0002\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001A\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0002\u001A\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\t2\u0006\u0010\u000B\u001A\u00020\tH\u0001\u001A\"\u0010\f\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000E\u001A\"\u0010\u000F\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000E\u001A\u0010\u0010\u0011\u001A\u00020\u00032\u0006\u0010\u0002\u001A\u00020\tH\u0001\u001A\u0018\u0010\u0012\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u00132\u0006\u0010\u000B\u001A\u00020\u0013H\u0001\u001A\"\u0010\u0014\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001A\"\u0010\u0017\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016\u001A\u0010\u0010\u0019\u001A\u00020\u00032\u0006\u0010\u0002\u001A\u00020\u0013H\u0001\u001A\u0010\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u0002\u001A\u00020\u0013H\u0000\u001A\u0018\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u0002\u001A\u00020\u00132\u0006\u0010\u001C\u001A\u00020\tH\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001D"}, d2 = {"doubleToUInt", "Lkotlin/UInt;", "v", "", "(D)I", "doubleToULong", "Lkotlin/ULong;", "(D)J", "uintCompare", "", "v1", "v2", "uintDivide", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "uintToDouble", "ulongCompare", "", "ulongDivide", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToDouble", "ulongToString", "", "base", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class UnsignedKt {
    public static final int doubleToUInt(double f) {
        if(Double.isNaN(f)) {
            return 0;
        }
        if(f <= 0.0) {
            return 0;
        }
        if(f >= 4294967295.0) {
            return -1;
        }
        return f <= 2147483647.0 ? ((int)f) : ((int)(f - 2147483647.0)) + 0x7FFFFFFF;
    }

    public static final long doubleToULong(double f) {
        if(Double.isNaN(f)) {
            return 0L;
        }
        if(f <= 0.0) {
            return 0L;
        }
        if(f >= 18446744073709552000.0) {
            return -1L;
        }
        return f < 9223372036854776000.0 ? ((long)f) : ((long)(f - 9223372036854776000.0)) ^ 0x8000000000000000L;
    }

    public static final int uintCompare(int v, int v1) {
        return Intrinsics.compare(v ^ 0x80000000, v1 ^ 0x80000000);
    }

    public static final int uintDivide-J1ME1BU(int v, int v1) {
        return UInt.constructor-impl(((int)((((long)v) & 0xFFFFFFFFL) / (((long)v1) & 0xFFFFFFFFL))));
    }

    public static final int uintRemainder-J1ME1BU(int v, int v1) {
        return UInt.constructor-impl(((int)((((long)v) & 0xFFFFFFFFL) % (((long)v1) & 0xFFFFFFFFL))));
    }

    public static final double uintToDouble(int v) [...] // Inlined contents

    public static final int ulongCompare(long v, long v1) {
        return Intrinsics.compare(v ^ 0x8000000000000000L, v1 ^ 0x8000000000000000L);
    }

    public static final long ulongDivide-eb3DHEI(long v, long v1) {
        if(v1 < 0L) {
            return UByte..ExternalSyntheticBackport0.m(v, v1) >= 0 ? 1L : 0L;
        }
        if(v >= 0L) {
            return ULong.constructor-impl(v / v1);
        }
        int v2 = 1;
        long v3 = (v >>> 1) / v1 << 1;
        if(UByte..ExternalSyntheticBackport0.m(v - v3 * v1, v1) < 0) {
            v2 = 0;
        }
        return v3 + ((long)v2);
    }

    public static final long ulongRemainder-eb3DHEI(long v, long v1) {
        if(v1 < 0L) {
            return UByte..ExternalSyntheticBackport0.m(v, v1) >= 0 ? v - v1 : v;
        }
        if(v >= 0L) {
            return ULong.constructor-impl(v % v1);
        }
        long v2 = v - ((v >>> 1) / v1 << 1) * v1;
        if(UByte..ExternalSyntheticBackport0.m(v2, v1) < 0) {
            v1 = 0L;
        }
        return v2 - v1;
    }

    public static final double ulongToDouble(long v) [...] // Inlined contents

    public static final String ulongToString(long v) {
        return UnsignedKt.ulongToString(v, 10);
    }

    public static final String ulongToString(long v, int v1) {
        if(v >= 0L) {
            String s = Long.toString(v, CharsKt.checkRadix(v1));
            Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
            return s;
        }
        long v2 = (v >>> 1) / ((long)v1) << 1;
        long v3 = v - v2 * ((long)v1);
        if(v3 >= ((long)v1)) {
            v3 -= (long)v1;
            ++v2;
        }
        String s1 = Long.toString(v2, CharsKt.checkRadix(v1));
        Intrinsics.checkNotNullExpressionValue(s1, "toString(this, checkRadix(radix))");
        String s2 = Long.toString(v3, CharsKt.checkRadix(v1));
        Intrinsics.checkNotNullExpressionValue(s2, "toString(this, checkRadix(radix))");
        return s1 + s2;
    }
}

