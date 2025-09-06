package okhttp3.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okio.Buffer;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\u001A0\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0007H\u0000\u001A\"\u0010\f\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u0007H\u0000\u001A\u0010\u0010\r\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\nH\u0000\u001A\n\u0010\u000E\u001A\u00020\u0003*\u00020\u0005\u001A\f\u0010\u000F\u001A\u00020\u0003*\u00020\u0005H\u0000\u001A\f\u0010\u0010\u001A\u00020\u0003*\u00020\u0005H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"VERIFY_AS_IP_ADDRESS", "Lkotlin/text/Regex;", "decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "inet6AddressToAscii", "canParseAsIpAddress", "containsInvalidHostnameAsciiCodes", "containsInvalidLabelLengths", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _HostnamesCommonKt {
    private static final Regex VERIFY_AS_IP_ADDRESS;

    static {
        _HostnamesCommonKt.VERIFY_AS_IP_ADDRESS = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static final boolean canParseAsIpAddress(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return _HostnamesCommonKt.VERIFY_AS_IP_ADDRESS.matches(s);
    }

    public static final boolean containsInvalidHostnameAsciiCodes(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            if(Intrinsics.compare(v2, 0x1F) <= 0 || Intrinsics.compare(v2, 0x7F) >= 0 || StringsKt.indexOf$default(" #%/:?@[\\]", ((char)v2), 0, false, 6, null) != -1) {
                return true;
            }
        }
        return false;
    }

    public static final boolean containsInvalidLabelLengths(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        int v = s.length();
        if(1 <= v && v < 0xFE) {
            int v1 = 0;
            while(true) {
                int v2 = StringsKt.indexOf$default(s, '.', v1, false, 4, null);
                int v3 = v2 == -1 ? s.length() - v1 : v2 - v1;
                if(1 > v3 || v3 >= 0x40) {
                    break;
                }
                if(v2 != -1 && v2 != s.length() - 1) {
                    v1 = v2 + 1;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static final boolean decodeIpv4Suffix(String s, int v, int v1, byte[] arr_b, int v2) {
        Intrinsics.checkNotNullParameter(s, "input");
        Intrinsics.checkNotNullParameter(arr_b, "address");
        int v3 = v2;
        while(v < v1) {
            if(v3 == arr_b.length) {
                return false;
            }
            if(v3 != v2) {
                if(s.charAt(v) != 46) {
                    return false;
                }
                ++v;
            }
            int v4 = v;
            int v5 = 0;
            while(v4 < v1) {
                int v6 = s.charAt(v4);
                if(Intrinsics.compare(v6, 0x30) < 0 || Intrinsics.compare(v6, 57) > 0) {
                    break;
                }
                if(v5 == 0 && v != v4) {
                    return false;
                }
                v5 = v5 * 10 + v6 - 0x30;
                if(v5 > 0xFF) {
                    return false;
                }
                ++v4;
            }
            if(v4 - v == 0) {
                return false;
            }
            arr_b[v3] = (byte)v5;
            ++v3;
            v = v4;
        }
        return v3 == v2 + 4;
    }

    public static final byte[] decodeIpv6(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "input");
        byte[] arr_b = new byte[16];
        int v2 = v;
        int v3 = 0;
        int v4 = -1;
        int v5 = -1;
        while(v2 < v1) {
            if(v3 == 16) {
                return null;
            }
            if(v2 + 2 > v1 || !StringsKt.startsWith$default(s, "::", v2, false, 4, null)) {
                if(v3 != 0) {
                    if(StringsKt.startsWith$default(s, ":", v2, false, 4, null)) {
                        ++v2;
                        goto label_27;
                    }
                    if(!StringsKt.startsWith$default(s, ".", v2, false, 4, null) || !_HostnamesCommonKt.decodeIpv4Suffix(s, v5, v1, arr_b, v3 - 2)) {
                        return null;
                    }
                    v3 += 2;
                    break;
                }
            label_27:
                v5 = v2;
            }
            else {
                if(v4 != -1) {
                    return null;
                }
                v3 += 2;
                if(v2 + 2 == v1) {
                    v4 = v3;
                    break;
                }
                v4 = v3;
                v5 = v2 + 2;
            }
            v2 = v5;
            int v6 = 0;
            while(v2 < v1) {
                int v7 = _UtilCommonKt.parseHexDigit(s.charAt(v2));
                if(v7 == -1) {
                    break;
                }
                v6 = (v6 << 4) + v7;
                ++v2;
            }
            if(v2 - v5 != 0 && v2 - v5 <= 4) {
                int v8 = v3 + 1;
                arr_b[v3] = (byte)(v6 >>> 8 & 0xFF);
                v3 += 2;
                arr_b[v8] = (byte)(v6 & 0xFF);
                continue;
            }
            return null;
        }
        if(v3 != 16) {
            if(v4 == -1) {
                return null;
            }
            ArraysKt.copyInto(arr_b, arr_b, 16 - (v3 - v4), v4, v3);
            ArraysKt.fill(arr_b, 0, v4, 16 - v3 + v4);
        }
        return arr_b;
    }

    public static final String inet6AddressToAscii(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "address");
        int v = -1;
        int v1 = 0;
        int v3 = 0;
        for(int v2 = 0; v2 < arr_b.length; v2 = v4 + 2) {
            int v4;
            for(v4 = v2; v4 < 16 && arr_b[v4] == 0 && arr_b[v4 + 1] == 0; v4 += 2) {
            }
            int v5 = v4 - v2;
            if(v5 > v3 && v5 >= 4) {
                v = v2;
                v3 = v5;
            }
        }
        Buffer buffer0 = new Buffer();
        while(v1 < arr_b.length) {
            if(v1 == v) {
                buffer0.writeByte(58);
                v1 += v3;
                if(v1 != 16) {
                    continue;
                }
                buffer0.writeByte(58);
            }
            else {
                if(v1 > 0) {
                    buffer0.writeByte(58);
                }
                buffer0.writeHexadecimalUnsignedLong(((long)(_UtilCommonKt.and(arr_b[v1], ((byte)0xFF)) << 8 | _UtilCommonKt.and(arr_b[v1 + 1], ((byte)0xFF)))));
                v1 += 2;
            }
        }
        return "";
    }
}

