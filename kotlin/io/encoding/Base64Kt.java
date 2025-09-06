package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0000\u001A\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0001\"\u0016\u0010\u0000\u001A\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001A\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003\"\u0016\u0010\u0007\u001A\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001A\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003¨\u0006\u000F"}, d2 = {"base64DecodeMap", "", "getBase64DecodeMap$annotations", "()V", "base64EncodeMap", "", "getBase64EncodeMap$annotations", "base64UrlDecodeMap", "getBase64UrlDecodeMap$annotations", "base64UrlEncodeMap", "getBase64UrlEncodeMap$annotations", "isInMimeAlphabet", "", "symbol", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class Base64Kt {
    private static final int[] base64DecodeMap;
    private static final byte[] base64EncodeMap;
    private static final int[] base64UrlDecodeMap;
    private static final byte[] base64UrlEncodeMap;

    static {
        byte[] arr_b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 0x2F};
        Base64Kt.base64EncodeMap = arr_b;
        int[] arr_v = new int[0x100];
        ArraysKt.fill$default(arr_v, -1, 0, 0, 6, null);
        arr_v[61] = -2;
        int v = 0;
        int v2 = 0;
        for(int v1 = 0; v2 < 0x40; ++v1) {
            arr_v[arr_b[v2]] = v1;
            ++v2;
        }
        Base64Kt.base64DecodeMap = arr_v;
        byte[] arr_b1 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 0x5F};
        Base64Kt.base64UrlEncodeMap = arr_b1;
        int[] arr_v1 = new int[0x100];
        ArraysKt.fill$default(arr_v1, -1, 0, 0, 6, null);
        arr_v1[61] = -2;
        for(int v3 = 0; v < 0x40; ++v3) {
            arr_v1[arr_b1[v]] = v3;
            ++v;
        }
        Base64Kt.base64UrlDecodeMap = arr_v1;
    }

    public static final byte[] access$getBase64EncodeMap$p() [...] // 潜在的解密器

    public static final byte[] access$getBase64UrlEncodeMap$p() [...] // 潜在的解密器

    private static void getBase64DecodeMap$annotations() {
    }

    private static void getBase64EncodeMap$annotations() {
    }

    private static void getBase64UrlDecodeMap$annotations() {
    }

    private static void getBase64UrlEncodeMap$annotations() {
    }

    public static final boolean isInMimeAlphabet(int v) {
        return v >= 0 && (v < Base64Kt.base64DecodeMap.length && Base64Kt.base64DecodeMap[v] != -1);
    }
}

