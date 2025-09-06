package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u000E\n\u0002\b\u0003\u001A\u000E\u0010\t\u001A\u0004\u0018\u00010\u0001*\u00020\nH\u0000\u001A\u0016\u0010\u000B\u001A\u00020\n*\u00020\u00012\b\b\u0002\u0010\f\u001A\u00020\u0001H\u0000\"\u001C\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u001C\u0010\u0006\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001A\u0004\b\b\u0010\u0005¨\u0006\r"}, d2 = {"BASE64", "", "getBASE64$annotations", "()V", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE$annotations", "getBASE64_URL_SAFE", "decodeBase64ToArray", "", "encodeBase64", "map", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _Base64Kt {
    private static final byte[] BASE64;
    private static final byte[] BASE64_URL_SAFE;

    static {
        _Base64Kt.BASE64 = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 0x2F};
        _Base64Kt.BASE64_URL_SAFE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 0x5F};
    }

    public static final byte[] decodeBase64ToArray(String s) {
        int v7;
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v;
        for(v = s.length(); v > 0 && (s.charAt(v - 1) == 9 || s.charAt(v - 1) == 10 || s.charAt(v - 1) == 13 || s.charAt(v - 1) == 0x20 || s.charAt(v - 1) == 61); --v) {
        }
        int v1 = (int)(((long)v) * 6L / 8L);
        byte[] arr_b = new byte[v1];
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        while(v2 < v) {
            int v6 = s.charAt(v2);
            if(65 <= v6 && v6 < 91) {
                v7 = v6 - 65;
                goto label_28;
            }
            else if(97 <= v6 && v6 < 0x7B) {
                v7 = v6 - 71;
                goto label_28;
            }
            else if(0x30 > v6 || v6 >= 58) {
                switch(v6) {
                    case 43: 
                    case 45: {
                        v7 = 62;
                        goto label_28;
                    }
                    case 0x2F: 
                    case 0x5F: {
                        v7 = 0x3F;
                    label_28:
                        v4 = v4 << 6 | v7;
                        ++v3;
                        if(v3 % 4 == 0) {
                            arr_b[v5] = (byte)(v4 >> 16);
                            int v8 = v5 + 2;
                            arr_b[v5 + 1] = (byte)(v4 >> 8);
                            v5 += 3;
                            arr_b[v8] = (byte)v4;
                        }
                        break;
                    }
                    default: {
                        if(v6 != 9 && v6 != 10 && v6 != 13 && v6 != 0x20) {
                            return null;
                        }
                    }
                }
            }
            else {
                v7 = v6 + 4;
                goto label_28;
            }
            ++v2;
        }
        switch(v3 % 4) {
            case 1: {
                return null;
            }
            case 2: {
                arr_b[v5] = (byte)(v4 << 12 >> 16);
                ++v5;
                break;
            }
            case 3: {
                int v9 = v5 + 1;
                arr_b[v5] = (byte)(v4 << 6 >> 16);
                v5 += 2;
                arr_b[v9] = (byte)(v4 << 6 >> 8);
            }
        }
        if(v5 == v1) {
            return arr_b;
        }
        byte[] arr_b1 = Arrays.copyOf(arr_b, v5);
        Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, newSize)");
        return arr_b1;
    }

    public static final String encodeBase64(byte[] arr_b, byte[] arr_b1) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        Intrinsics.checkNotNullParameter(arr_b1, "map");
        byte[] arr_b2 = new byte[(arr_b.length + 2) / 3 * 4];
        int v = arr_b.length - arr_b.length % 3;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v) {
            int v3 = arr_b[v1];
            int v4 = v1 + 2;
            int v5 = arr_b[v1 + 1];
            v1 += 3;
            int v6 = arr_b[v4];
            arr_b2[v2] = arr_b1[(v3 & 0xFF) >> 2];
            arr_b2[v2 + 1] = arr_b1[(v3 & 3) << 4 | (v5 & 0xFF) >> 4];
            int v7 = v2 + 3;
            arr_b2[v2 + 2] = arr_b1[(v5 & 15) << 2 | (v6 & 0xFF) >> 6];
            v2 += 4;
            arr_b2[v7] = arr_b1[v6 & 0x3F];
        }
        switch(arr_b.length - v) {
            case 1: {
                int v8 = arr_b[v1];
                arr_b2[v2] = arr_b1[(v8 & 0xFF) >> 2];
                arr_b2[v2 + 1] = arr_b1[(v8 & 3) << 4];
                arr_b2[v2 + 2] = 61;
                arr_b2[v2 + 3] = 61;
                return _JvmPlatformKt.toUtf8String(arr_b2);
            }
            case 2: {
                int v9 = arr_b[v1];
                int v10 = arr_b[v1 + 1];
                arr_b2[v2] = arr_b1[(v9 & 0xFF) >> 2];
                arr_b2[v2 + 1] = arr_b1[(v9 & 3) << 4 | (v10 & 0xFF) >> 4];
                arr_b2[v2 + 2] = arr_b1[(v10 & 15) << 2];
                arr_b2[v2 + 3] = 61;
                return _JvmPlatformKt.toUtf8String(arr_b2);
            }
            default: {
                return _JvmPlatformKt.toUtf8String(arr_b2);
            }
        }
    }

    public static String encodeBase64$default(byte[] arr_b, byte[] arr_b1, int v, Object object0) {
        if((v & 1) != 0) {
            arr_b1 = _Base64Kt.BASE64;
        }
        return _Base64Kt.encodeBase64(arr_b, arr_b1);
    }

    public static final byte[] getBASE64() {
        return _Base64Kt.BASE64;
    }

    public static void getBASE64$annotations() {
    }

    public static final byte[] getBASE64_URL_SAFE() [...] // 潜在的解密器

    public static void getBASE64_URL_SAFE$annotations() {
    }
}

