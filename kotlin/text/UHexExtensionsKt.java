package kotlin.text;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\u001A\u001F\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001A\u001F\u0010\u0006\u001A\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001A\u001F\u0010\t\u001A\u00020\n*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A\u001F\u0010\f\u001A\u00020\r*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000E\u001A\u001F\u0010\u000F\u001A\u00020\u0010*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001A!\u0010\u0012\u001A\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001A5\u0010\u0012\u001A\u00020\u0002*\u00020\u00072\b\b\u0002\u0010\u0015\u001A\u00020\u00162\b\b\u0002\u0010\u0017\u001A\u00020\u00162\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001A!\u0010\u0012\u001A\u00020\u0002*\u00020\u00072\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001A\u0010\u001B\u001A!\u0010\u0012\u001A\u00020\u0002*\u00020\n2\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001C\u0010\u001D\u001A!\u0010\u0012\u001A\u00020\u0002*\u00020\r2\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001E\u0010\u001F\u001A!\u0010\u0012\u001A\u00020\u0002*\u00020\u00102\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"hexToUByte", "Lkotlin/UByte;", "", "format", "Lkotlin/text/HexFormat;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)B", "hexToUByteArray", "Lkotlin/UByteArray;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)[B", "hexToUInt", "Lkotlin/UInt;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)I", "hexToULong", "Lkotlin/ULong;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)J", "hexToUShort", "Lkotlin/UShort;", "(Ljava/lang/String;Lkotlin/text/HexFormat;)S", "toHexString", "toHexString-ZQbaR00", "(BLkotlin/text/HexFormat;)Ljava/lang/String;", "startIndex", "", "endIndex", "toHexString-lZCiFrA", "([BIILkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-zHuV2wU", "([BLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-8M7LxHw", "(ILkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-8UJCm-I", "(JLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-r3ox_E0", "(SLkotlin/text/HexFormat;)Ljava/lang/String;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class UHexExtensionsKt {
    private static final byte hexToUByte(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UByte.constructor-impl(HexExtensionsKt.hexToByte(s, hexFormat0));
    }

    static byte hexToUByte$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UByte.constructor-impl(HexExtensionsKt.hexToByte(s, hexFormat0));
    }

    private static final byte[] hexToUByteArray(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UByteArray.constructor-impl(HexExtensionsKt.hexToByteArray(s, hexFormat0));
    }

    static byte[] hexToUByteArray$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UByteArray.constructor-impl(HexExtensionsKt.hexToByteArray(s, hexFormat0));
    }

    private static final int hexToUInt(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UInt.constructor-impl(HexExtensionsKt.hexToInt(s, hexFormat0));
    }

    static int hexToUInt$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UInt.constructor-impl(HexExtensionsKt.hexToInt(s, hexFormat0));
    }

    private static final long hexToULong(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return ULong.constructor-impl(HexExtensionsKt.hexToLong(s, hexFormat0));
    }

    static long hexToULong$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return ULong.constructor-impl(HexExtensionsKt.hexToLong(s, hexFormat0));
    }

    private static final short hexToUShort(String s, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UShort.constructor-impl(HexExtensionsKt.hexToShort(s, hexFormat0));
    }

    static short hexToUShort$default(String s, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return UShort.constructor-impl(HexExtensionsKt.hexToShort(s, hexFormat0));
    }

    private static final String toHexString-8M7LxHw(int v, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    static String toHexString-8M7LxHw$default(int v, HexFormat hexFormat0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    private static final String toHexString-8UJCm-I(long v, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    static String toHexString-8UJCm-I$default(long v, HexFormat hexFormat0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    private static final String toHexString-ZQbaR00(byte b, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(b, hexFormat0);
    }

    static String toHexString-ZQbaR00$default(byte b, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(b, hexFormat0);
    }

    private static final String toHexString-lZCiFrA(byte[] arr_b, int v, int v1, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$toHexString");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(arr_b, v, v1, hexFormat0);
    }

    static String toHexString-lZCiFrA$default(byte[] arr_b, int v, int v1, HexFormat hexFormat0, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = UByteArray.getSize-impl(arr_b);
        }
        if((v2 & 4) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(arr_b, "$this$toHexString");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(arr_b, v, v1, hexFormat0);
    }

    private static final String toHexString-r3ox_E0(short v, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    static String toHexString-r3ox_E0$default(short v, HexFormat hexFormat0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(v, hexFormat0);
    }

    private static final String toHexString-zHuV2wU(byte[] arr_b, HexFormat hexFormat0) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$toHexString");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(arr_b, hexFormat0);
    }

    static String toHexString-zHuV2wU$default(byte[] arr_b, HexFormat hexFormat0, int v, Object object0) {
        if((v & 1) != 0) {
            hexFormat0 = HexFormat.Companion.getDefault();
        }
        Intrinsics.checkNotNullParameter(arr_b, "$this$toHexString");
        Intrinsics.checkNotNullParameter(hexFormat0, "format");
        return HexExtensionsKt.toHexString(arr_b, hexFormat0);
    }
}

