package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u0000 22\u00020\u0001:\u00012B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0005J\u0015\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0000\u00A2\u0006\u0002\b\rJ%\u0010\u000E\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0000\u00A2\u0006\u0002\b\u0013J \u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00112\u0006\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u0011H\u0002J%\u0010\u0019\u001A\u00020\u00152\u0006\u0010\u001A\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0000\u00A2\u0006\u0002\b\u001BJ\"\u0010\u001C\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J\"\u0010\u001C\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\u000F2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J0\u0010\u001D\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001E\u001A\u00020\f2\u0006\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0002J4\u0010\u001F\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001E\u001A\u00020\f2\b\b\u0002\u0010\u0017\u001A\u00020\u00112\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J4\u0010\u001F\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\u000F2\u0006\u0010\u001E\u001A\u00020\f2\b\b\u0002\u0010\u0017\u001A\u00020\u00112\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J \u0010 \u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0002J\"\u0010!\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J4\u0010\"\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001E\u001A\u00020\f2\b\b\u0002\u0010\u0017\u001A\u00020\u00112\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J5\u0010#\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001E\u001A\u00020\f2\u0006\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0000\u00A2\u0006\u0002\b$J\u0010\u0010%\u001A\u00020\u00112\u0006\u0010\u001A\u001A\u00020\u0011H\u0002J=\u0010&\u001A\u0002H\'\"\f\b\u0000\u0010\'*\u00060(j\u0002`)2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001E\u001A\u0002H\'2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011\u00A2\u0006\u0002\u0010*J\"\u0010+\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u0011J%\u0010,\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0000\u00A2\u0006\u0002\b-J(\u0010.\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010/\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00112\u0006\u00100\u001A\u00020\u0011H\u0002J \u00101\u001A\u00020\u00112\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0002R\u0014\u0010\u0004\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007\u00A8\u00063"}, d2 = {"Lkotlin/io/encoding/Base64;", "", "isUrlSafe", "", "isMimeScheme", "(ZZ)V", "isMimeScheme$kotlin_stdlib", "()Z", "isUrlSafe$kotlin_stdlib", "bytesToStringImpl", "", "source", "", "bytesToStringImpl$kotlin_stdlib", "charsToBytesImpl", "", "startIndex", "", "endIndex", "charsToBytesImpl$kotlin_stdlib", "checkDestinationBounds", "", "destinationSize", "destinationOffset", "capacityNeeded", "checkSourceBounds", "sourceSize", "checkSourceBounds$kotlin_stdlib", "decode", "decodeImpl", "destination", "decodeIntoByteArray", "decodeSize", "encode", "encodeIntoByteArray", "encodeIntoByteArrayImpl", "encodeIntoByteArrayImpl$kotlin_stdlib", "encodeSize", "encodeToAppendable", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "([BLjava/lang/Appendable;II)Ljava/lang/Appendable;", "encodeToByteArray", "encodeToByteArrayImpl", "encodeToByteArrayImpl$kotlin_stdlib", "handlePaddingSymbol", "padIndex", "byteStart", "skipIllegalSymbolsIfMime", "Default", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class Base64 {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0001¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001A\u00020\u0001¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\u0005R\u000E\u0010\b\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\tX\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\tX\u0080T¢\u0006\u0002\n\u0000R\u0014\u0010\u000E\u001A\u00020\u000FX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u000E\u0010\u0012\u001A\u00020\u0013X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\tX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlin/io/encoding/Base64$Default;", "Lkotlin/io/encoding/Base64;", "()V", "Mime", "getMime", "()Lkotlin/io/encoding/Base64;", "UrlSafe", "getUrlSafe", "bitsPerByte", "", "bitsPerSymbol", "bytesPerGroup", "mimeGroupsPerLine", "mimeLineLength", "mimeLineSeparatorSymbols", "", "getMimeLineSeparatorSymbols$kotlin_stdlib", "()[B", "padSymbol", "", "symbolsPerGroup", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Default extends Base64 {
        private Default() {
            super(false, false, null);
        }

        public Default(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Base64 getMime() {
            return Base64.Mime;
        }

        // 去混淆评级： 低(20)
        public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() [...] // 潜在的解密器

        public final Base64 getUrlSafe() {
            return Base64.UrlSafe;
        }
    }

    public static final Default Default = null;
    private static final Base64 Mime = null;
    private static final Base64 UrlSafe = null;
    private static final int bitsPerByte = 8;
    private static final int bitsPerSymbol = 6;
    public static final int bytesPerGroup = 3;
    private final boolean isMimeScheme;
    private final boolean isUrlSafe;
    private static final int mimeGroupsPerLine = 19;
    public static final int mimeLineLength = 76;
    private static final byte[] mimeLineSeparatorSymbols = null;
    public static final byte padSymbol = 61;
    public static final int symbolsPerGroup = 4;

    static {
        Base64.Default = new Default(null);
        Base64.mimeLineSeparatorSymbols = new byte[]{13, 10};
        Base64.UrlSafe = new Base64(true, false);
        Base64.Mime = new Base64(false, true);
    }

    private Base64(boolean z, boolean z1) {
        this.isUrlSafe = z;
        this.isMimeScheme = z1;
        if(z && z1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    public Base64(boolean z, boolean z1, DefaultConstructorMarker defaultConstructorMarker0) {
        this(z, z1);
    }

    public static final byte[] access$getMimeLineSeparatorSymbols$cp() [...] // 潜在的解密器

    public final String bytesToStringImpl$kotlin_stdlib(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        StringBuilder stringBuilder0 = new StringBuilder(arr_b.length);
        for(int v = 0; v < arr_b.length; ++v) {
            stringBuilder0.append(((char)arr_b[v]));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "stringBuilder.toString()");
        return s;
    }

    public final byte[] charsToBytesImpl$kotlin_stdlib(CharSequence charSequence0, int v, int v1) {
        Intrinsics.checkNotNullParameter(charSequence0, "source");
        this.checkSourceBounds$kotlin_stdlib(charSequence0.length(), v, v1);
        byte[] arr_b = new byte[v1 - v];
        int v2 = 0;
        while(v < v1) {
            int v3 = charSequence0.charAt(v);
            arr_b[v2] = v3 <= 0xFF ? ((byte)v3) : 0x3F;
            ++v2;
            ++v;
        }
        return arr_b;
    }

    private final void checkDestinationBounds(int v, int v1, int v2) {
        if(v1 < 0 || v1 > v) {
            throw new IndexOutOfBoundsException("destination offset: " + v1 + ", destination size: " + v);
        }
        if(v1 + v2 < 0 || v1 + v2 > v) {
            throw new IndexOutOfBoundsException("The destination array does not have enough capacity, destination offset: " + v1 + ", destination size: " + v + ", capacity needed: " + v2);
        }
    }

    public final void checkSourceBounds$kotlin_stdlib(int v, int v1, int v2) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(v1, v2, v);
    }

    public final byte[] decode(CharSequence charSequence0, int v, int v1) {
        Intrinsics.checkNotNullParameter(charSequence0, "source");
        if(charSequence0 instanceof String) {
            this.checkSourceBounds$kotlin_stdlib(charSequence0.length(), v, v1);
            String s = ((String)charSequence0).substring(v, v1);
            Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(s, "null cannot be cast to non-null type java.lang.String");
            byte[] arr_b = s.getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
            return Base64.decode$default(this, arr_b, 0, 0, 6, null);
        }
        return Base64.decode$default(this, this.charsToBytesImpl$kotlin_stdlib(charSequence0, v, v1), 0, 0, 6, null);
    }

    public final byte[] decode(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        this.checkSourceBounds$kotlin_stdlib(arr_b.length, v, v1);
        int v2 = this.decodeSize(arr_b, v, v1);
        byte[] arr_b1 = new byte[v2];
        if(this.decodeImpl(arr_b, arr_b1, 0, v, v1) != v2) {
            throw new IllegalStateException("Check failed.");
        }
        return arr_b1;
    }

    public static byte[] decode$default(Base64 base640, CharSequence charSequence0, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
        }
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = charSequence0.length();
        }
        return base640.decode(charSequence0, v, v1);
    }

    public static byte[] decode$default(Base64 base640, byte[] arr_b, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
        }
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = arr_b.length;
        }
        return base640.decode(arr_b, v, v1);
    }

    private final int decodeImpl(byte[] arr_b, byte[] arr_b1, int v, int v1, int v2) {
        int[] arr_v = this.isUrlSafe ? Base64Kt.base64UrlDecodeMap : Base64Kt.base64DecodeMap;
        int v3 = v;
        int v4 = v1;
        int v5 = 0;
        int v6 = -8;
        while(v4 < v2) {
            if(v6 == -8 && v4 + 3 < v2) {
                int v7 = arr_v[arr_b[v4] & 0xFF] << 18 | arr_v[arr_b[v4 + 1] & 0xFF] << 12 | arr_v[arr_b[v4 + 2] & 0xFF] << 6 | arr_v[arr_b[v4 + 3] & 0xFF];
                if(v7 >= 0) {
                    arr_b1[v3] = (byte)(v7 >> 16);
                    int v8 = v3 + 2;
                    arr_b1[v3 + 1] = (byte)(v7 >> 8);
                    v3 += 3;
                    arr_b1[v8] = (byte)v7;
                    v4 += 4;
                    continue;
                }
            }
            int v9 = arr_b[v4] & 0xFF;
            int v10 = arr_v[v9];
            if(v10 < 0) {
                if(v10 == -2) {
                    v4 = this.handlePaddingSymbol(arr_b, v4, v2, v6);
                    break;
                }
                if(this.isMimeScheme) {
                    ++v4;
                    continue;
                }
                String s = Integer.toString(v9, CharsKt.checkRadix(8));
                Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
                throw new IllegalArgumentException("Invalid symbol \'" + ((char)v9) + "\'(" + s + ") at index " + v4);
            }
            ++v4;
            v5 = v5 << 6 | v10;
            if(v6 + 6 >= 0) {
                arr_b1[v3] = (byte)(v5 >>> v6 + 6);
                v5 &= (1 << v6 + 6) - 1;
                v6 -= 2;
                ++v3;
            }
            else {
                v6 += 6;
            }
        }
        if(v6 == -2) {
            throw new IllegalArgumentException("The last unit of input does not have enough bits");
        }
        int v11 = this.skipIllegalSymbolsIfMime(arr_b, v4, v2);
        if(v11 >= v2) {
            return v3 - v;
        }
        int v12 = arr_b[v11] & 0xFF;
        String s1 = Integer.toString(v12, CharsKt.checkRadix(8));
        Intrinsics.checkNotNullExpressionValue(s1, "toString(this, checkRadix(radix))");
        throw new IllegalArgumentException("Symbol \'" + ((char)v12) + "\'(" + s1 + ") at index " + (v11 - 1) + " is prohibited after the pad character");
    }

    public final int decodeIntoByteArray(CharSequence charSequence0, byte[] arr_b, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(charSequence0, "source");
        Intrinsics.checkNotNullParameter(arr_b, "destination");
        if(charSequence0 instanceof String) {
            this.checkSourceBounds$kotlin_stdlib(charSequence0.length(), v1, v2);
            String s = ((String)charSequence0).substring(v1, v2);
            Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(s, "null cannot be cast to non-null type java.lang.String");
            byte[] arr_b1 = s.getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(arr_b1, "this as java.lang.String).getBytes(charset)");
            return Base64.decodeIntoByteArray$default(this, arr_b1, arr_b, v, 0, 0, 24, null);
        }
        return Base64.decodeIntoByteArray$default(this, this.charsToBytesImpl$kotlin_stdlib(charSequence0, v1, v2), arr_b, v, 0, 0, 24, null);
    }

    public final int decodeIntoByteArray(byte[] arr_b, byte[] arr_b1, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        Intrinsics.checkNotNullParameter(arr_b1, "destination");
        this.checkSourceBounds$kotlin_stdlib(arr_b.length, v1, v2);
        int v3 = this.decodeSize(arr_b, v1, v2);
        this.checkDestinationBounds(arr_b1.length, v, v3);
        return this.decodeImpl(arr_b, arr_b1, v, v1, v2);
    }

    public static int decodeIntoByteArray$default(Base64 base640, CharSequence charSequence0, byte[] arr_b, int v, int v1, int v2, int v3, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        }
        if((v3 & 4) != 0) {
            v = 0;
        }
        if((v3 & 8) != 0) {
            v1 = 0;
        }
        if((v3 & 16) != 0) {
            v2 = charSequence0.length();
        }
        return base640.decodeIntoByteArray(charSequence0, arr_b, v, v1, v2);
    }

    public static int decodeIntoByteArray$default(Base64 base640, byte[] arr_b, byte[] arr_b1, int v, int v1, int v2, int v3, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        }
        if((v3 & 4) != 0) {
            v = 0;
        }
        if((v3 & 8) != 0) {
            v1 = 0;
        }
        if((v3 & 16) != 0) {
            v2 = arr_b.length;
        }
        return base640.decodeIntoByteArray(arr_b, arr_b1, v, v1, v2);
    }

    private final int decodeSize(byte[] arr_b, int v, int v1) {
        int v2 = v1 - v;
        if(v2 == 0) {
            return 0;
        }
        if(v2 == 1) {
            throw new IllegalArgumentException("Input should have at list 2 symbols for Base64 decoding, startIndex: " + v + ", endIndex: " + v1);
        }
        if(this.isMimeScheme) {
            while(v < v1) {
                int v3 = Base64Kt.access$getBase64DecodeMap$p()[arr_b[v] & 0xFF];
                if(v3 < 0) {
                    if(v3 == -2) {
                        return (int)(((long)(v2 - (v1 - v))) * 6L / 8L);
                    }
                    --v2;
                }
                ++v;
            }
            return (int)(((long)v2) * 6L / 8L);
        }
        if(arr_b[v1 - 1] == 61) {
            if(arr_b[v1 - 2] == 61) {
                return (int)(((long)(v2 - 2)) * 6L / 8L);
            }
            --v2;
        }
        return (int)(((long)v2) * 6L / 8L);
    }

    public final String encode(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        return new String(this.encodeToByteArrayImpl$kotlin_stdlib(arr_b, v, v1), Charsets.ISO_8859_1);
    }

    public static String encode$default(Base64 base640, byte[] arr_b, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encode");
        }
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = arr_b.length;
        }
        return base640.encode(arr_b, v, v1);
    }

    public final int encodeIntoByteArray(byte[] arr_b, byte[] arr_b1, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        Intrinsics.checkNotNullParameter(arr_b1, "destination");
        return this.encodeIntoByteArrayImpl$kotlin_stdlib(arr_b, arr_b1, v, v1, v2);
    }

    public static int encodeIntoByteArray$default(Base64 base640, byte[] arr_b, byte[] arr_b1, int v, int v1, int v2, int v3, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
        }
        if((v3 & 4) != 0) {
            v = 0;
        }
        if((v3 & 8) != 0) {
            v1 = 0;
        }
        if((v3 & 16) != 0) {
            v2 = arr_b.length;
        }
        return base640.encodeIntoByteArray(arr_b, arr_b1, v, v1, v2);
    }

    public final int encodeIntoByteArrayImpl$kotlin_stdlib(byte[] arr_b, byte[] arr_b1, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        Intrinsics.checkNotNullParameter(arr_b1, "destination");
        this.checkSourceBounds$kotlin_stdlib(arr_b.length, v1, v2);
        int v3 = this.encodeSize(v2 - v1);
        this.checkDestinationBounds(arr_b1.length, v, v3);
        byte[] arr_b2 = this.isUrlSafe ? new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 0x5F} : new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 0x2F};
        int v4 = this.isMimeScheme ? 19 : 0x7FFFFFFF;
        int v5 = v;
        while(v1 + 2 < v2) {
            int v6 = Math.min((v2 - v1) / 3, v4);
            for(int v7 = 0; v7 < v6; ++v7) {
                int v8 = arr_b[v1] & 0xFF;
                int v9 = v1 + 2;
                int v10 = arr_b[v1 + 1] & 0xFF;
                v1 += 3;
                int v11 = v10 << 8 | v8 << 16 | arr_b[v9] & 0xFF;
                arr_b1[v5] = arr_b2[v11 >>> 18];
                arr_b1[v5 + 1] = arr_b2[v11 >>> 12 & 0x3F];
                int v12 = v5 + 3;
                arr_b1[v5 + 2] = arr_b2[v11 >>> 6 & 0x3F];
                v5 += 4;
                arr_b1[v12] = arr_b2[v11 & 0x3F];
            }
            if(v6 == v4 && v1 != v2) {
                int v13 = v5 + 1;
                arr_b1[v5] = Base64.mimeLineSeparatorSymbols[0];
                v5 += 2;
                arr_b1[v13] = Base64.mimeLineSeparatorSymbols[1];
            }
        }
        switch(v2 - v1) {
            case 1: {
                int v14 = (arr_b[v1] & 0xFF) << 4;
                arr_b1[v5] = arr_b2[v14 >>> 6];
                arr_b1[v5 + 1] = arr_b2[v14 & 0x3F];
                int v15 = v5 + 3;
                arr_b1[v5 + 2] = 61;
                v5 += 4;
                arr_b1[v15] = 61;
                ++v1;
                break;
            }
            case 2: {
                int v16 = v1 + 1;
                int v17 = arr_b[v1] & 0xFF;
                v1 += 2;
                int v18 = (arr_b[v16] & 0xFF) << 2 | v17 << 10;
                arr_b1[v5] = arr_b2[v18 >>> 12];
                arr_b1[v5 + 1] = arr_b2[v18 >>> 6 & 0x3F];
                int v19 = v5 + 3;
                arr_b1[v5 + 2] = arr_b2[v18 & 0x3F];
                v5 += 4;
                arr_b1[v19] = 61;
            }
        }
        if(v1 != v2) {
            throw new IllegalStateException("Check failed.");
        }
        return v5 - v;
    }

    private final int encodeSize(int v) {
        int v1 = (v + 2) / 3;
        int v2 = v1 * 4 + (this.isMimeScheme ? (v1 - 1) / 19 : 0) * 2;
        if(v2 < 0) {
            throw new IllegalArgumentException("Input is too big");
        }
        return v2;
    }

    public final Appendable encodeToAppendable(byte[] arr_b, Appendable appendable0, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        Intrinsics.checkNotNullParameter(appendable0, "destination");
        appendable0.append(new String(this.encodeToByteArrayImpl$kotlin_stdlib(arr_b, v, v1), Charsets.ISO_8859_1));
        return appendable0;
    }

    public static Appendable encodeToAppendable$default(Base64 base640, byte[] arr_b, Appendable appendable0, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToAppendable");
        }
        if((v2 & 4) != 0) {
            v = 0;
        }
        if((v2 & 8) != 0) {
            v1 = arr_b.length;
        }
        return base640.encodeToAppendable(arr_b, appendable0, v, v1);
    }

    public final byte[] encodeToByteArray(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        return this.encodeToByteArrayImpl$kotlin_stdlib(arr_b, v, v1);
    }

    public static byte[] encodeToByteArray$default(Base64 base640, byte[] arr_b, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToByteArray");
        }
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = arr_b.length;
        }
        return base640.encodeToByteArray(arr_b, v, v1);
    }

    public final byte[] encodeToByteArrayImpl$kotlin_stdlib(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        this.checkSourceBounds$kotlin_stdlib(arr_b.length, v, v1);
        byte[] arr_b1 = new byte[this.encodeSize(v1 - v)];
        this.encodeIntoByteArrayImpl$kotlin_stdlib(arr_b, arr_b1, 0, v, v1);
        return arr_b1;
    }

    private final int handlePaddingSymbol(byte[] arr_b, int v, int v1, int v2) {
        switch(v2) {
            case -8: {
                throw new IllegalArgumentException("Redundant pad character at index " + v);
            }
            case -6: {
                return v + 1;
            }
            case -4: {
                int v3 = this.skipIllegalSymbolsIfMime(arr_b, v + 1, v1);
                if(v3 == v1 || arr_b[v3] != 61) {
                    throw new IllegalArgumentException("Missing one pad character at index " + v3);
                }
                return v3 + 1;
            }
            case -2: {
                return v + 1;
            }
            default: {
                throw new IllegalStateException("Unreachable");
            }
        }
    }

    public final boolean isMimeScheme$kotlin_stdlib() {
        return this.isMimeScheme;
    }

    public final boolean isUrlSafe$kotlin_stdlib() {
        return this.isUrlSafe;
    }

    private final int skipIllegalSymbolsIfMime(byte[] arr_b, int v, int v1) {
        if(!this.isMimeScheme) {
            return v;
        }
        while(v < v1 && Base64Kt.base64DecodeMap[arr_b[v] & 0xFF] == -1) {
            ++v;
        }
        return v;
    }
}

