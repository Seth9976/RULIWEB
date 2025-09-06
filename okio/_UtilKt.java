package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.internal._ByteStringKt;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0002\u001A0\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u00012\u0006\u0010\u000F\u001A\u00020\r2\u0006\u0010\u0010\u001A\u00020\u00012\u0006\u0010\u0011\u001A\u00020\u0001H\u0000\u001A \u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00152\u0006\u0010\u0011\u001A\u00020\u0015H\u0000\u001A\u0019\u0010\u0017\u001A\u00020\u00152\u0006\u0010\f\u001A\u00020\u00012\u0006\u0010\u000F\u001A\u00020\u0015H\u0080\b\u001A\u0019\u0010\u0017\u001A\u00020\u00152\u0006\u0010\f\u001A\u00020\u00152\u0006\u0010\u000F\u001A\u00020\u0001H\u0080\b\u001A\u0010\u0010\u0018\u001A\u00020\u00052\u0006\u0010\u0019\u001A\u00020\u0005H\u0000\u001A\u0015\u0010\u001A\u001A\u00020\u0001*\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0001H\u0080\f\u001A\u0015\u0010\u001A\u001A\u00020\u0015*\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0015H\u0080\f\u001A\u0015\u0010\u001A\u001A\u00020\u0015*\u00020\u00012\u0006\u0010\u001C\u001A\u00020\u0015H\u0080\f\u001A\u0015\u0010\u001D\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u001E\u001A\u00020\u0001H\u0080\f\u001A\u0014\u0010\u0018\u001A\u00020\u0001*\u00020\r2\u0006\u0010\u001F\u001A\u00020\u0001H\u0000\u001A\u0014\u0010\u0018\u001A\u00020\u0001*\u00020 2\u0006\u0010!\u001A\u00020\u0001H\u0000\u001A\f\u0010\"\u001A\u00020\u0001*\u00020\u0001H\u0000\u001A\f\u0010\"\u001A\u00020\u0015*\u00020\u0015H\u0000\u001A\f\u0010\"\u001A\u00020#*\u00020#H\u0000\u001A\u0015\u0010$\u001A\u00020\u0015*\u00020\u00152\u0006\u0010\u001E\u001A\u00020\u0001H\u0080\f\u001A\u0015\u0010%\u001A\u00020\u0001*\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0001H\u0080\f\u001A\u0015\u0010&\u001A\u00020\u0001*\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0001H\u0080\f\u001A\f\u0010\'\u001A\u00020(*\u00020\u001BH\u0000\u001A\f\u0010\'\u001A\u00020(*\u00020\u0001H\u0000\u001A\f\u0010\'\u001A\u00020(*\u00020\u0015H\u0000\u001A\u0015\u0010)\u001A\u00020\u001B*\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u001BH\u0080\f\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080D\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u001C\u0010\u0004\u001A\u00020\u00058\u0000X\u0081\u0004\u00A2\u0006\u000E\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u001A\u0004\b\b\u0010\t\u00A8\u0006*"}, d2 = {"DEFAULT__ByteString_size", "", "getDEFAULT__ByteString_size", "()I", "DEFAULT__new_UnsafeCursor", "Lokio/Buffer$UnsafeCursor;", "getDEFAULT__new_UnsafeCursor$annotations", "()V", "getDEFAULT__new_UnsafeCursor", "()Lokio/Buffer$UnsafeCursor;", "arrayRangeEquals", "", "a", "", "aOffset", "b", "bOffset", "byteCount", "checkOffsetAndCount", "", "size", "", "offset", "minOf", "resolveDefaultParameter", "unsafeCursor", "and", "", "other", "leftRotate", "bitCount", "sizeParam", "Lokio/ByteString;", "position", "reverseBytes", "", "rightRotate", "shl", "shr", "toHexString", "", "xor", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _UtilKt {
    private static final int DEFAULT__ByteString_size;
    private static final UnsafeCursor DEFAULT__new_UnsafeCursor;

    static {
        _UtilKt.DEFAULT__new_UnsafeCursor = new UnsafeCursor();
        _UtilKt.DEFAULT__ByteString_size = 0xB669FD2E;
    }

    public static final int and(byte b, int v) {
        return b & v;
    }

    public static final long and(byte b, long v) {
        return v & ((long)b);
    }

    public static final long and(int v, long v1) {
        return v1 & ((long)v);
    }

    public static final boolean arrayRangeEquals(byte[] arr_b, int v, byte[] arr_b1, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "a");
        Intrinsics.checkNotNullParameter(arr_b1, "b");
        for(int v3 = 0; v3 < v2; ++v3) {
            if(arr_b[v3 + v] != arr_b1[v3 + v1]) {
                return false;
            }
        }
        return true;
    }

    public static final void checkOffsetAndCount(long v, long v1, long v2) {
        if((v1 | v2) < 0L || v1 > v || v - v1 < v2) {
            throw new ArrayIndexOutOfBoundsException("size=" + v + " offset=" + v1 + " byteCount=" + v2);
        }
    }

    public static final int getDEFAULT__ByteString_size() [...] // 潜在的解密器

    public static final UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return _UtilKt.DEFAULT__new_UnsafeCursor;
    }

    public static void getDEFAULT__new_UnsafeCursor$annotations() {
    }

    public static final int leftRotate(int v, int v1) {
        return v >>> 0x20 - v1 | v << v1;
    }

    public static final long minOf(int v, long v1) {
        return Math.min(v, v1);
    }

    public static final long minOf(long v, int v1) {
        return Math.min(v, v1);
    }

    public static final int resolveDefaultParameter(ByteString byteString0, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return v == 0xB669FD2E ? byteString0.size() : v;
    }

    public static final int resolveDefaultParameter(byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        return v == 0xB669FD2E ? arr_b.length : v;
    }

    public static final UnsafeCursor resolveDefaultParameter(UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "unsafeCursor");
        return buffer$UnsafeCursor0 == _UtilKt.DEFAULT__new_UnsafeCursor ? new UnsafeCursor() : buffer$UnsafeCursor0;
    }

    public static final int reverseBytes(int v) [...] // Inlined contents

    public static final long reverseBytes(long v) {
        return (v & 0xFFL) << 56 | ((0xFF00000000000000L & v) >>> 56 | (0xFF000000000000L & v) >>> 40 | (0xFF0000000000L & v) >>> 24 | (0xFF00000000L & v) >>> 8 | (0xFF000000L & v) << 8 | (0xFF0000L & v) << 24 | (0xFF00L & v) << 40);
    }

    public static final short reverseBytes(short v) [...] // Inlined contents

    public static final long rightRotate(long v, int v1) {
        return v << 0x40 - v1 | v >>> v1;
    }

    public static final int shl(byte b, int v) {
        return b << v;
    }

    public static final int shr(byte b, int v) {
        return b >> v;
    }

    public static final String toHexString(byte b) {
        return StringsKt.concatToString(new char[]{_ByteStringKt.getHEX_DIGIT_CHARS()[b >> 4 & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[b & 15]});
    }

    public static final String toHexString(int v) {
        if(v == 0) {
            return "0";
        }
        char c = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 28 & 15];
        char c1 = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 24 & 15];
        char c2 = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 20 & 15];
        char c3 = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 16 & 15];
        char c4 = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 12 & 15];
        char c5 = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 8 & 15];
        char c6 = _ByteStringKt.getHEX_DIGIT_CHARS()[v >> 4 & 15];
        char c7 = _ByteStringKt.getHEX_DIGIT_CHARS()[v & 15];
        char[] arr_c = new char[8];
        int v1 = 0;
        arr_c[0] = c;
        arr_c[1] = c1;
        arr_c[2] = c2;
        arr_c[3] = c3;
        arr_c[4] = c4;
        arr_c[5] = c5;
        arr_c[6] = c6;
        arr_c[7] = c7;
        while(v1 < 8 && arr_c[v1] == 0x30) {
            ++v1;
        }
        return StringsKt.concatToString(arr_c, v1, 8);
    }

    public static final String toHexString(long v) {
        if(v == 0L) {
            return "0";
        }
        char c = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 60 & 15L))];
        char c1 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 56 & 15L))];
        char c2 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 52 & 15L))];
        char c3 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 0x30 & 15L))];
        char c4 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 44 & 15L))];
        char c5 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 40 & 15L))];
        char c6 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 36 & 15L))];
        char c7 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 0x20 & 15L))];
        char c8 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 28 & 15L))];
        char c9 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 24 & 15L))];
        char c10 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 20 & 15L))];
        char c11 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 16 & 15L))];
        char c12 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 12 & 15L))];
        char c13 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 8 & 15L))];
        char c14 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v >> 4 & 15L))];
        char c15 = _ByteStringKt.getHEX_DIGIT_CHARS()[((int)(v & 15L))];
        char[] arr_c = new char[16];
        int v1 = 0;
        arr_c[0] = c;
        arr_c[1] = c1;
        arr_c[2] = c2;
        arr_c[3] = c3;
        arr_c[4] = c4;
        arr_c[5] = c5;
        arr_c[6] = c6;
        arr_c[7] = c7;
        arr_c[8] = c8;
        arr_c[9] = c9;
        arr_c[10] = c10;
        arr_c[11] = c11;
        arr_c[12] = c12;
        arr_c[13] = c13;
        arr_c[14] = c14;
        arr_c[15] = c15;
        while(v1 < 16 && arr_c[v1] == 0x30) {
            ++v1;
        }
        return StringsKt.concatToString(arr_c, v1, 16);
    }

    public static final byte xor(byte b, byte b1) {
        return (byte)(b ^ b1);
    }
}

