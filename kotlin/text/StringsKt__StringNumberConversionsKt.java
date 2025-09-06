package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0000\u001A\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001A\u001B\u0010\u0004\u001A\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001A\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001A\u0013\u0010\n\u001A\u0004\u0018\u00010\b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000B\u001A\u001B\u0010\n\u001A\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001A\u00020\bH\u0007¢\u0006\u0002\u0010\f\u001A\u0013\u0010\r\u001A\u0004\u0018\u00010\u000E*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000F\u001A\u001B\u0010\r\u001A\u0004\u0018\u00010\u000E*\u00020\u00032\u0006\u0010\u0007\u001A\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001A\u0013\u0010\u0011\u001A\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001A\u001B\u0010\u0011\u001A\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001A\u00020\bH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    public static final Void numberFormatError(String s) {
        Intrinsics.checkNotNullParameter(s, "input");
        throw new NumberFormatException("Invalid number format: \'" + s + '\'');
    }

    public static final Byte toByteOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return StringsKt.toByteOrNull(s, 10);
    }

    public static final Byte toByteOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Integer integer0 = StringsKt.toIntOrNull(s, v);
        if(integer0 != null) {
            int v1 = (int)integer0;
            return v1 < 0xFFFFFF80 || v1 > 0x7F ? null : ((byte)v1);
        }
        return null;
    }

    public static final Integer toIntOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return StringsKt.toIntOrNull(s, 10);
    }

    public static final Integer toIntOrNull(String s, int v) {
        boolean z;
        Intrinsics.checkNotNullParameter(s, "<this>");
        CharsKt.checkRadix(v);
        int v1 = s.length();
        if(v1 == 0) {
            return null;
        }
        int v2 = 1;
        int v3 = 0;
        int v4 = s.charAt(0);
        int v5 = 0x80000001;
        if(Intrinsics.compare(v4, 0x30) < 0) {
            if(v1 == 1) {
                return null;
            }
            switch(v4) {
                case 43: {
                    z = false;
                    break;
                }
                case 45: {
                    v5 = 0x80000000;
                    z = true;
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        else {
            z = false;
            v2 = 0;
        }
        int v6 = 0xFC71C71D;
        while(v2 < v1) {
            int v7 = CharsKt.digitOf(s.charAt(v2), v);
            if(v7 < 0) {
                return null;
            }
            if(v3 < v6) {
                if(v6 == 0xFC71C71D) {
                    v6 = v5 / v;
                    if(v3 < v6) {
                        return null;
                    }
                    goto label_31;
                }
                return null;
            }
        label_31:
            int v8 = v3 * v;
            if(v8 < v5 + v7) {
                return null;
            }
            v3 = v8 - v7;
            ++v2;
        }
        return z ? v3 : ((int)(-v3));
    }

    public static final Long toLongOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return StringsKt.toLongOrNull(s, 10);
    }

    public static final Long toLongOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        CharsKt.checkRadix(v);
        int v1 = s.length();
        if(v1 == 0) {
            return null;
        }
        boolean z = true;
        int v2 = 0;
        int v3 = s.charAt(0);
        long v4 = 0x8000000000000001L;
        if(Intrinsics.compare(v3, 0x30) < 0) {
            if(v1 == 1) {
                return null;
            }
            switch(v3) {
                case 43: {
                    v2 = 1;
                    z = false;
                    break;
                }
                case 45: {
                    v4 = 0x8000000000000000L;
                    v2 = 1;
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        else {
            z = false;
        }
        long v5 = 0L;
        long v6 = -256204778801521550L;
        while(v2 < v1) {
            int v7 = CharsKt.digitOf(s.charAt(v2), v);
            if(v7 < 0) {
                return null;
            }
            if(v5 < v6) {
                if(v6 == -256204778801521550L) {
                    v6 = v4 / ((long)v);
                    if(v5 < v6) {
                        return null;
                    }
                    goto label_32;
                }
                return null;
            }
        label_32:
            long v8 = v5 * ((long)v);
            if(v8 < v4 + ((long)v7)) {
                return null;
            }
            v5 = v8 - ((long)v7);
            ++v2;
        }
        return z ? v5 : ((long)(-v5));
    }

    public static final Short toShortOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return StringsKt.toShortOrNull(s, 10);
    }

    public static final Short toShortOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Integer integer0 = StringsKt.toIntOrNull(s, v);
        if(integer0 != null) {
            int v1 = (int)integer0;
            return v1 < 0xFFFF8000 || v1 > 0x7FFF ? null : ((short)v1);
        }
        return null;
    }
}

