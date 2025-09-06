package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001A\n\u0010\u0000\u001A\u00020\u0001*\u00020\u0002\u001A\u001E\u0010\u0003\u001A\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u0005¨\u0006\u0007"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _Utf8Kt {
    public static final byte[] commonAsUtf8ToByteArray(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        byte[] arr_b = new byte[s.length() * 4];
        int v = s.length();
        int v1 = 0;
        while(v1 < v) {
            int v2 = s.charAt(v1);
            if(Intrinsics.compare(v2, 0x80) >= 0) {
                int v3 = s.length();
                int v4 = v1;
            label_9:
                while(v1 < v3) {
                    int v5 = s.charAt(v1);
                    if(Intrinsics.compare(v5, 0x80) < 0) {
                        int v6 = v4 + 1;
                        arr_b[v4] = (byte)v5;
                        ++v1;
                        while(true) {
                            v4 = v6;
                            if(v1 >= v3 || Intrinsics.compare(s.charAt(v1), 0x80) >= 0) {
                                continue label_9;
                            }
                            v6 = v4 + 1;
                            arr_b[v4] = (byte)s.charAt(v1);
                            ++v1;
                        }
                    }
                    if(Intrinsics.compare(v5, 0x800) < 0) {
                        int v7 = v4 + 1;
                        arr_b[v4] = (byte)(v5 >> 6 | 0xC0);
                        v4 += 2;
                        arr_b[v7] = (byte)(v5 & 0x3F | 0x80);
                    }
                    else if(0xD800 > v5 || v5 >= 0xE000) {
                        arr_b[v4] = (byte)(v5 >> 12 | 0xE0);
                        int v11 = v4 + 2;
                        arr_b[v4 + 1] = (byte)(v5 >> 6 & 0x3F | 0x80);
                        v4 += 3;
                        arr_b[v11] = (byte)(v5 & 0x3F | 0x80);
                    }
                    else {
                        if(Intrinsics.compare(v5, 0xDBFF) <= 0 && v3 > v1 + 1) {
                            int v8 = s.charAt(v1 + 1);
                            if(0xDC00 <= v8 && v8 < 0xE000) {
                                int v9 = (v5 << 10) + s.charAt(v1 + 1) - 0x35FDC00;
                                arr_b[v4] = (byte)(v9 >> 18 | 0xF0);
                                arr_b[v4 + 1] = (byte)(v9 >> 12 & 0x3F | 0x80);
                                int v10 = v4 + 3;
                                arr_b[v4 + 2] = (byte)(v9 >> 6 & 0x3F | 0x80);
                                v4 += 4;
                                arr_b[v10] = (byte)(v9 & 0x3F | 0x80);
                                v1 += 2;
                                continue;
                            }
                        }
                        arr_b[v4] = 0x3F;
                        ++v1;
                        ++v4;
                        continue;
                    }
                    ++v1;
                }
                byte[] arr_b1 = Arrays.copyOf(arr_b, v4);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, newSize)");
                return arr_b1;
            }
            arr_b[v1] = (byte)v2;
            ++v1;
        }
        byte[] arr_b2 = Arrays.copyOf(arr_b, s.length());
        Intrinsics.checkNotNullExpressionValue(arr_b2, "copyOf(this, newSize)");
        return arr_b2;
    }

    public static final String commonToUtf8String(byte[] arr_b, int v, int v1) {
        int v19;
        int v14;
        int v13;
        int v9;
        int v6;
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        if(v < 0 || v1 > arr_b.length || v > v1) {
            throw new ArrayIndexOutOfBoundsException("size=" + arr_b.length + " beginIndex=" + v + " endIndex=" + v1);
        }
        char[] arr_c = new char[v1 - v];
        int v2 = 0;
    label_4:
        while(v < v1) {
            int v3 = arr_b[v];
            if(v3 >= 0) {
                int v4 = v2 + 1;
                arr_c[v2] = (char)v3;
                ++v;
                while(true) {
                    v2 = v4;
                    if(v >= v1) {
                        continue label_4;
                    }
                    int v5 = arr_b[v];
                    if(v5 < 0) {
                        continue label_4;
                    }
                    ++v;
                    v4 = v2 + 1;
                    arr_c[v2] = (char)v5;
                }
            }
            if(v3 >> 5 == -2) {
                if(v1 <= v + 1) {
                    v6 = v2 + 1;
                    arr_c[v2] = '\uFFFD';
                    goto label_68;
                }
                else {
                    int v7 = arr_b[v + 1];
                    if((v7 & 0xC0) == 0x80) {
                        int v8 = v3 << 6 ^ (v7 ^ 0xF80);
                        if(v8 < 0x80) {
                            v6 = v2 + 1;
                            arr_c[v2] = '\uFFFD';
                        }
                        else {
                            v6 = v2 + 1;
                            arr_c[v2] = (char)v8;
                        }
                        v2 = v6;
                        v9 = 2;
                        goto label_119;
                    }
                    else {
                        v6 = v2 + 1;
                        arr_c[v2] = '\uFFFD';
                        goto label_68;
                    }
                }
                goto label_36;
            }
            else {
            label_36:
                if(v3 >> 4 == -2) {
                    if(v1 <= v + 2) {
                        v6 = v2 + 1;
                        arr_c[v2] = '\uFFFD';
                        if(v1 > v + 1 && (arr_b[v + 1] & 0xC0) == 0x80) {
                            v2 = v6;
                            v9 = 2;
                            goto label_119;
                        }
                    }
                    else {
                        int v10 = arr_b[v + 1];
                        if((v10 & 0xC0) == 0x80) {
                            int v11 = arr_b[v + 2];
                            if((v11 & 0xC0) == 0x80) {
                                int v12 = v3 << 12 ^ (v11 ^ 0xFFFE1F80 ^ v10 << 6);
                                if(v12 < 0x800) {
                                    v13 = v2 + 1;
                                    arr_c[v2] = '\uFFFD';
                                }
                                else if(0xD800 > v12 || v12 >= 0xE000) {
                                    v13 = v2 + 1;
                                    arr_c[v2] = (char)v12;
                                }
                                else {
                                    v13 = v2 + 1;
                                    arr_c[v2] = '\uFFFD';
                                }
                                v2 = v13;
                                v9 = 3;
                            }
                            else {
                                arr_c[v2] = '\uFFFD';
                                ++v2;
                                v9 = 2;
                            }
                            goto label_119;
                        }
                        else {
                            v6 = v2 + 1;
                            arr_c[v2] = '\uFFFD';
                        }
                    }
                label_68:
                    v2 = v6;
                }
                else if(v3 >> 3 == -2) {
                    if(v1 <= v + 3) {
                        v14 = v2 + 1;
                        arr_c[v2] = '\uFFFD';
                        if(v1 > v + 1 && (arr_b[v + 1] & 0xC0) == 0x80) {
                            if(v1 <= v + 2 || (arr_b[v + 2] & 0xC0) != 0x80) {
                                v2 = v14;
                                v9 = 2;
                            }
                            else {
                                v2 = v14;
                                v9 = 3;
                            }
                            goto label_119;
                        }
                    }
                    else {
                        int v15 = arr_b[v + 1];
                        if((v15 & 0xC0) == 0x80) {
                            int v16 = arr_b[v + 2];
                            if((v16 & 0xC0) == 0x80) {
                                int v17 = arr_b[v + 3];
                                if((v17 & 0xC0) == 0x80) {
                                    int v18 = v3 << 18 ^ (v17 ^ 0x381F80 ^ v16 << 6 ^ v15 << 12);
                                    if(v18 > 0x10FFFF) {
                                        v19 = v2 + 1;
                                        arr_c[v2] = '\uFFFD';
                                    }
                                    else if(0xD800 <= v18 && v18 < 0xE000) {
                                        v19 = v2 + 1;
                                        arr_c[v2] = '\uFFFD';
                                    }
                                    else if(v18 < 0x10000) {
                                        v19 = v2 + 1;
                                        arr_c[v2] = '\uFFFD';
                                    }
                                    else {
                                        arr_c[v2] = (char)((v18 >>> 10) + 0xD7C0);
                                        arr_c[v2 + 1] = (char)((v18 & 0x3FF) + 0xDC00);
                                        v19 = v2 + 2;
                                    }
                                    v9 = 4;
                                    v2 = v19;
                                }
                                else {
                                    arr_c[v2] = '\uFFFD';
                                    ++v2;
                                    v9 = 3;
                                }
                            }
                            else {
                                arr_c[v2] = '\uFFFD';
                                ++v2;
                                v9 = 2;
                            }
                            goto label_119;
                        }
                        else {
                            v14 = v2 + 1;
                            arr_c[v2] = '\uFFFD';
                        }
                    }
                    v2 = v14;
                }
                else {
                    goto label_121;
                }
            }
            v9 = 1;
        label_119:
            v += v9;
            continue;
        label_121:
            arr_c[v2] = '\uFFFD';
            ++v;
            ++v2;
        }
        return StringsKt.concatToString(arr_c, 0, v2);
    }

    public static String commonToUtf8String$default(byte[] arr_b, int v, int v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = arr_b.length;
        }
        return _Utf8Kt.commonToUtf8String(arr_b, v, v1);
    }
}

