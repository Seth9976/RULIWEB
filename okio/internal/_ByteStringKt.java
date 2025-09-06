package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;
import okio.ByteString;
import okio._Base64Kt;
import okio._JvmPlatformKt;
import okio._UtilKt;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\u001A\u0018\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0007H\u0002\u001A\u0011\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\tH\u0080\b\u001A\u0010\u0010\u000E\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u0010H\u0002\u001A\r\u0010\u0011\u001A\u00020\u0012*\u00020\fH\u0080\b\u001A\r\u0010\u0013\u001A\u00020\u0012*\u00020\fH\u0080\b\u001A\u0015\u0010\u0014\u001A\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001A\u00020\fH\u0080\b\u001A-\u0010\u0016\u001A\u00020\u0017*\u00020\f2\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010\u0019\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0007H\u0080\b\u001A\u000F\u0010\u001C\u001A\u0004\u0018\u00010\f*\u00020\u0012H\u0080\b\u001A\r\u0010\u001D\u001A\u00020\f*\u00020\u0012H\u0080\b\u001A\r\u0010\u001E\u001A\u00020\f*\u00020\u0012H\u0080\b\u001A\u0015\u0010\u001F\u001A\u00020 *\u00020\f2\u0006\u0010!\u001A\u00020\tH\u0080\b\u001A\u0015\u0010\u001F\u001A\u00020 *\u00020\f2\u0006\u0010!\u001A\u00020\fH\u0080\b\u001A\u0017\u0010\"\u001A\u00020 *\u00020\f2\b\u0010\u0015\u001A\u0004\u0018\u00010#H\u0080\b\u001A\u0015\u0010$\u001A\u00020%*\u00020\f2\u0006\u0010&\u001A\u00020\u0007H\u0080\b\u001A\r\u0010\'\u001A\u00020\u0007*\u00020\fH\u0080\b\u001A\r\u0010(\u001A\u00020\u0007*\u00020\fH\u0080\b\u001A\r\u0010)\u001A\u00020\u0012*\u00020\fH\u0080\b\u001A\u001D\u0010*\u001A\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001A\u00020\t2\u0006\u0010+\u001A\u00020\u0007H\u0080\b\u001A\r\u0010,\u001A\u00020\t*\u00020\fH\u0080\b\u001A\u001D\u0010-\u001A\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001A\u00020\t2\u0006\u0010+\u001A\u00020\u0007H\u0080\b\u001A\u001D\u0010-\u001A\u00020\u0007*\u00020\f2\u0006\u0010\u0015\u001A\u00020\f2\u0006\u0010+\u001A\u00020\u0007H\u0080\b\u001A-\u0010.\u001A\u00020 *\u00020\f2\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010\u0015\u001A\u00020\t2\u0006\u0010/\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0007H\u0080\b\u001A-\u0010.\u001A\u00020 *\u00020\f2\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010\u0015\u001A\u00020\f2\u0006\u0010/\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0007H\u0080\b\u001A\u0015\u00100\u001A\u00020 *\u00020\f2\u0006\u00101\u001A\u00020\tH\u0080\b\u001A\u0015\u00100\u001A\u00020 *\u00020\f2\u0006\u00101\u001A\u00020\fH\u0080\b\u001A\u001D\u00102\u001A\u00020\f*\u00020\f2\u0006\u00103\u001A\u00020\u00072\u0006\u00104\u001A\u00020\u0007H\u0080\b\u001A\r\u00105\u001A\u00020\f*\u00020\fH\u0080\b\u001A\r\u00106\u001A\u00020\f*\u00020\fH\u0080\b\u001A\r\u00107\u001A\u00020\t*\u00020\fH\u0080\b\u001A\u001D\u00108\u001A\u00020\f*\u00020\t2\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0007H\u0080\b\u001A\r\u00109\u001A\u00020\u0012*\u00020\fH\u0080\b\u001A\r\u0010:\u001A\u00020\u0012*\u00020\fH\u0080\b\u001A$\u0010;\u001A\u00020\u0017*\u00020\f2\u0006\u0010<\u001A\u00020=2\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0007H\u0000\"\u001C\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004\u00A2\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\u00A8\u0006>"}, d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS$annotations", "()V", "getHEX_DIGIT_CHARS", "()[C", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "Lokio/ByteString;", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonCopyInto", "", "offset", "target", "targetOffset", "byteCount", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "otherOffset", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "commonToString", "commonUtf8", "commonWrite", "buffer", "Lokio/Buffer;", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _ByteStringKt {
    private static final char[] HEX_DIGIT_CHARS;

    static {
        _ByteStringKt.HEX_DIGIT_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    private static final int codePointIndexToCharIndex(byte[] arr_b, int v) {
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
    label_3:
        while(v1 < arr_b.length) {
            int v4 = arr_b[v1];
            if(v4 >= 0) {
                int v5 = v3 + 1;
                if(v3 == v) {
                    break;
                }
                if(v4 != 10 && v4 != 13 && (v4 >= 0 && v4 < 0x20 || 0x7F <= v4 && v4 < 0xA0) || v4 == 0xFFFD) {
                    return -1;
                }
                v2 += (v4 >= 0x10000 ? 2 : 1);
                ++v1;
                while(true) {
                    v3 = v5;
                    if(v1 >= arr_b.length) {
                        continue label_3;
                    }
                    int v6 = arr_b[v1];
                    if(v6 < 0) {
                        continue label_3;
                    }
                    ++v1;
                    v5 = v3 + 1;
                    if(v3 == v) {
                        return v2;
                    }
                    if(v6 != 10 && v6 != 13 && (v6 >= 0 && v6 < 0x20 || 0x7F <= v6 && v6 < 0xA0) || v6 == 0xFFFD) {
                        return -1;
                    }
                    v2 += (v6 >= 0x10000 ? 2 : 1);
                }
            }
            if(v4 >> 5 == -2) {
                if(arr_b.length <= v1 + 1) {
                    return v3 == v ? v2 : -1;
                }
                int v7 = arr_b[v1 + 1];
                if((v7 & 0xC0) != 0x80) {
                    return v3 == v ? v2 : -1;
                }
                int v8 = v4 << 6 ^ (v7 ^ 0xF80);
                if(v8 < 0x80) {
                    return v3 == v ? v2 : -1;
                }
                if(v3 != v) {
                    if(v8 != 10 && v8 != 13 && (v8 >= 0 && v8 < 0x20 || 0x7F <= v8 && v8 < 0xA0) || v8 == 0xFFFD) {
                        return -1;
                    }
                    v2 += (v8 >= 0x10000 ? 2 : 1);
                    v1 += 2;
                    ++v3;
                    continue;
                }
            }
            else if(v4 >> 4 == -2) {
                if(arr_b.length <= v1 + 2) {
                    return v3 == v ? v2 : -1;
                }
                int v9 = arr_b[v1 + 1];
                if((v9 & 0xC0) != 0x80) {
                    return v3 == v ? v2 : -1;
                }
                int v10 = arr_b[v1 + 2];
                if((v10 & 0xC0) != 0x80) {
                    return v3 == v ? v2 : -1;
                }
                int v11 = v4 << 12 ^ (v10 ^ 0xFFFE1F80 ^ v9 << 6);
                if(v11 < 0x800) {
                    return v3 == v ? v2 : -1;
                }
                if(0xD800 <= v11 && v11 < 0xE000) {
                    return v3 == v ? v2 : -1;
                }
                if(v3 != v) {
                    if(v11 != 10 && v11 != 13 && (v11 >= 0 && v11 < 0x20 || 0x7F <= v11 && v11 < 0xA0) || v11 == 0xFFFD) {
                        return -1;
                    }
                    v2 += (v11 >= 0x10000 ? 2 : 1);
                    v1 += 3;
                    ++v3;
                    continue;
                }
            }
            else {
                if(v4 >> 3 != -2 || arr_b.length <= v1 + 3) {
                    return v3 == v ? v2 : -1;
                }
                int v12 = arr_b[v1 + 1];
                if((v12 & 0xC0) != 0x80) {
                    return v3 == v ? v2 : -1;
                }
                int v13 = arr_b[v1 + 2];
                if((v13 & 0xC0) != 0x80) {
                    return v3 == v ? v2 : -1;
                }
                int v14 = arr_b[v1 + 3];
                if((v14 & 0xC0) != 0x80) {
                    return v3 == v ? v2 : -1;
                }
                int v15 = v4 << 18 ^ (v14 ^ 0x381F80 ^ v13 << 6 ^ v12 << 12);
                if(v15 > 0x10FFFF) {
                    return v3 == v ? v2 : -1;
                }
                if(0xD800 <= v15 && v15 < 0xE000) {
                    return v3 == v ? v2 : -1;
                }
                if(v15 < 0x10000) {
                    return v3 == v ? v2 : -1;
                }
                if(v3 != v) {
                    if(v15 != 10 && v15 != 13 && (v15 >= 0 && v15 < 0x20 || 0x7F <= v15 && v15 < 0xA0) || v15 == 0xFFFD) {
                        return -1;
                    }
                    v2 += 2;
                    v1 += 4;
                    ++v3;
                    continue;
                }
            }
            break;
        }
        return v2;
    }

    public static final String commonBase64(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return _Base64Kt.encodeBase64$default(byteString0.getData$okio(), null, 1, null);
    }

    public static final String commonBase64Url(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return _Base64Kt.encodeBase64(byteString0.getData$okio(), new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 0x5F});
    }

    public static final int commonCompareTo(ByteString byteString0, ByteString byteString1) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(byteString1, "other");
        int v = byteString0.size();
        int v1 = byteString1.size();
        int v2 = Math.min(v, v1);
        int v3 = 0;
        while(v3 < v2) {
            int v4 = byteString0.getByte(v3) & 0xFF;
            int v5 = byteString1.getByte(v3) & 0xFF;
            if(v4 == v5) {
                ++v3;
                continue;
            }
            return v4 >= v5 ? 1 : -1;
        }
        if(v == v1) {
            return 0;
        }
        return v >= v1 ? 1 : -1;
    }

    public static final void commonCopyInto(ByteString byteString0, int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "target");
        ArraysKt.copyInto(byteString0.getData$okio(), arr_b, v1, v, v2 + v);
    }

    public static final ByteString commonDecodeBase64(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        byte[] arr_b = _Base64Kt.decodeBase64ToArray(s);
        return arr_b == null ? null : new ByteString(arr_b);
    }

    public static final ByteString commonDecodeHex(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        if(s.length() % 2 != 0) {
            throw new IllegalArgumentException(("Unexpected hex string: " + s).toString());
        }
        int v = s.length();
        byte[] arr_b = new byte[v / 2];
        for(int v1 = 0; v1 < v / 2; ++v1) {
            arr_b[v1] = (byte)((_ByteStringKt.decodeHexDigit(s.charAt(v1 * 2)) << 4) + _ByteStringKt.decodeHexDigit(s.charAt(v1 * 2 + 1)));
        }
        return new ByteString(arr_b);
    }

    public static final ByteString commonEncodeUtf8(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        ByteString byteString0 = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(s));
        byteString0.setUtf8$okio(s);
        return byteString0;
    }

    public static final boolean commonEndsWith(ByteString byteString0, ByteString byteString1) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(byteString1, "suffix");
        return byteString0.rangeEquals(byteString0.size() - byteString1.size(), byteString1, 0, byteString1.size());
    }

    public static final boolean commonEndsWith(ByteString byteString0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "suffix");
        return byteString0.rangeEquals(byteString0.size() - arr_b.length, arr_b, 0, arr_b.length);
    }

    public static final boolean commonEquals(ByteString byteString0, Object object0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return object0 == byteString0 ? true : object0 instanceof ByteString && ((ByteString)object0).size() == byteString0.getData$okio().length && ((ByteString)object0).rangeEquals(0, byteString0.getData$okio(), 0, byteString0.getData$okio().length);
    }

    public static final byte commonGetByte(ByteString byteString0, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return byteString0.getData$okio()[v];
    }

    public static final int commonGetSize(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return byteString0.getData$okio().length;
    }

    public static final int commonHashCode(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        int v = byteString0.getHashCode$okio();
        if(v != 0) {
            return v;
        }
        int v1 = Arrays.hashCode(byteString0.getData$okio());
        byteString0.setHashCode$okio(v1);
        return v1;
    }

    public static final String commonHex(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        char[] arr_c = new char[byteString0.getData$okio().length * 2];
        byte[] arr_b = byteString0.getData$okio();
        int v1 = 0;
        for(int v = 0; v < arr_b.length; ++v) {
            int v2 = arr_b[v];
            int v3 = v1 + 1;
            arr_c[v1] = _ByteStringKt.getHEX_DIGIT_CHARS()[v2 >> 4 & 15];
            v1 += 2;
            arr_c[v3] = _ByteStringKt.getHEX_DIGIT_CHARS()[v2 & 15];
        }
        return StringsKt.concatToString(arr_c);
    }

    public static final int commonIndexOf(ByteString byteString0, byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "other");
        int v1 = byteString0.getData$okio().length - arr_b.length;
        int v2 = Math.max(v, 0);
        if(v2 <= v1) {
            while(true) {
                if(_UtilKt.arrayRangeEquals(byteString0.getData$okio(), v2, arr_b, 0, arr_b.length)) {
                    return v2;
                }
                if(v2 == v1) {
                    break;
                }
                ++v2;
            }
        }
        return -1;
    }

    public static final byte[] commonInternalArray(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        return byteString0.getData$okio();
    }

    public static final int commonLastIndexOf(ByteString byteString0, ByteString byteString1, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(byteString1, "other");
        return byteString0.lastIndexOf(byteString1.internalArray$okio(), v);
    }

    public static final int commonLastIndexOf(ByteString byteString0, byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "other");
        for(int v1 = Math.min(_UtilKt.resolveDefaultParameter(byteString0, v), byteString0.getData$okio().length - arr_b.length); -1 < v1; --v1) {
            if(_UtilKt.arrayRangeEquals(byteString0.getData$okio(), v1, arr_b, 0, arr_b.length)) {
                return v1;
            }
        }
        return -1;
    }

    public static final ByteString commonOf(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "data");
        byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
        Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
        return new ByteString(arr_b1);
    }

    public static final boolean commonRangeEquals(ByteString byteString0, int v, ByteString byteString1, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(byteString1, "other");
        return byteString1.rangeEquals(v1, byteString0.getData$okio(), v, v2);
    }

    public static final boolean commonRangeEquals(ByteString byteString0, int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "other");
        return v >= 0 && v <= byteString0.getData$okio().length - v2 && v1 >= 0 && v1 <= arr_b.length - v2 && _UtilKt.arrayRangeEquals(byteString0.getData$okio(), v, arr_b, v1, v2);
    }

    public static final boolean commonStartsWith(ByteString byteString0, ByteString byteString1) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(byteString1, "prefix");
        return byteString0.rangeEquals(0, byteString1, 0, byteString1.size());
    }

    public static final boolean commonStartsWith(ByteString byteString0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "prefix");
        return byteString0.rangeEquals(0, arr_b, 0, arr_b.length);
    }

    public static final ByteString commonSubstring(ByteString byteString0, int v, int v1) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        int v2 = _UtilKt.resolveDefaultParameter(byteString0, v1);
        if(v < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if(v2 > byteString0.getData$okio().length) {
            throw new IllegalArgumentException(("endIndex > length(" + byteString0.getData$okio().length + ')').toString());
        }
        if(v2 - v < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        return v != 0 || v2 != byteString0.getData$okio().length ? new ByteString(ArraysKt.copyOfRange(byteString0.getData$okio(), v, v2)) : byteString0;
    }

    public static final ByteString commonToAsciiLowercase(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        for(int v = 0; v < byteString0.getData$okio().length; ++v) {
            int v1 = byteString0.getData$okio()[v];
            if(v1 >= 65 && v1 <= 90) {
                byte[] arr_b = byteString0.getData$okio();
                byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
                int v2 = v + 1;
                arr_b1[v] = (byte)(v1 + 0x20);
                while(v2 < arr_b1.length) {
                    int v3 = arr_b1[v2];
                    if(v3 >= 65 && v3 <= 90) {
                        arr_b1[v2] = (byte)(v3 + 0x20);
                    }
                    ++v2;
                }
                return new ByteString(arr_b1);
            }
        }
        return byteString0;
    }

    public static final ByteString commonToAsciiUppercase(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        for(int v = 0; v < byteString0.getData$okio().length; ++v) {
            int v1 = byteString0.getData$okio()[v];
            if(v1 >= 97 && v1 <= 0x7A) {
                byte[] arr_b = byteString0.getData$okio();
                byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
                int v2 = v + 1;
                arr_b1[v] = (byte)(v1 - 0x20);
                while(v2 < arr_b1.length) {
                    int v3 = arr_b1[v2];
                    if(v3 >= 97 && v3 <= 0x7A) {
                        arr_b1[v2] = (byte)(v3 - 0x20);
                    }
                    ++v2;
                }
                return new ByteString(arr_b1);
            }
        }
        return byteString0;
    }

    public static final byte[] commonToByteArray(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        byte[] arr_b = byteString0.getData$okio();
        byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
        Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
        return arr_b1;
    }

    public static final ByteString commonToByteString(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        int v2 = _UtilKt.resolveDefaultParameter(arr_b, v1);
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v2);
        return new ByteString(ArraysKt.copyOfRange(arr_b, v, v2 + v));
    }

    public static final String commonToString(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        if(byteString0.getData$okio().length == 0) {
            return "[size=0]";
        }
        int v = _ByteStringKt.codePointIndexToCharIndex(byteString0.getData$okio(), 0x40);
        if(v == -1) {
            if(byteString0.getData$okio().length <= 0x40) {
                return "[hex=" + byteString0.hex() + ']';
            }
            StringBuilder stringBuilder0 = new StringBuilder("[size=");
            stringBuilder0.append(byteString0.getData$okio().length);
            stringBuilder0.append(" hex=");
            int v1 = _UtilKt.resolveDefaultParameter(byteString0, 0x40);
            if(v1 > byteString0.getData$okio().length) {
                throw new IllegalArgumentException(("endIndex > length(" + byteString0.getData$okio().length + ')').toString());
            }
            if(v1 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            }
            if(v1 != byteString0.getData$okio().length) {
                byteString0 = new ByteString(ArraysKt.copyOfRange(byteString0.getData$okio(), 0, v1));
            }
            stringBuilder0.append(byteString0.hex());
            stringBuilder0.append("…]");
            return stringBuilder0.toString();
        }
        String s = byteString0.utf8();
        String s1 = s.substring(0, v);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
        String s2 = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s1, "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), "\r", "\\r", false, 4, null);
        return v >= s.length() ? "[text=" + s2 + ']' : "[size=" + byteString0.getData$okio().length + " text=" + s2 + "…]";
    }

    public static final String commonUtf8(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        String s = byteString0.getUtf8$okio();
        if(s == null) {
            s = _JvmPlatformKt.toUtf8String(byteString0.internalArray$okio());
            byteString0.setUtf8$okio(s);
        }
        return s;
    }

    public static final void commonWrite(ByteString byteString0, Buffer buffer0, int v, int v1) {
        Intrinsics.checkNotNullParameter(byteString0, "<this>");
        Intrinsics.checkNotNullParameter(buffer0, "buffer");
        buffer0.write(byteString0.getData$okio(), v, v1);
    }

    private static final int decodeHexDigit(char c) {
        if(0x30 <= c && c < 58) {
            return c - 0x30;
        }
        if(97 <= c && c < 103) {
            return c - 87;
        }
        if(65 > c || c >= 71) {
            throw new IllegalArgumentException("Unexpected hex digit: " + c);
        }
        return c - 55;
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return _ByteStringKt.HEX_DIGIT_CHARS;
    }

    public static void getHEX_DIGIT_CHARS$annotations() {
    }
}

