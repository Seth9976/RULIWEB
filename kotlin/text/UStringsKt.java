package kotlin.text;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0005\u0010\u0006\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\b\u0010\t\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000B\u0010\f\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000E\u0010\u000F\u001A\u0014\u0010\u0010\u001A\u00020\u0002*\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0011\u001A\u001C\u0010\u0010\u001A\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0012\u001A\u0011\u0010\u0013\u001A\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007\u00F8\u0001\u0000\u001A\u0019\u0010\u0013\u001A\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u001A\u0014\u0010\u0014\u001A\u00020\u0007*\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001A\u001C\u0010\u0014\u001A\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0016\u001A\u0011\u0010\u0017\u001A\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007\u00F8\u0001\u0000\u001A\u0019\u0010\u0017\u001A\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u001A\u0014\u0010\u0018\u001A\u00020\n*\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0019\u001A\u001C\u0010\u0018\u001A\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001A\u001A\u0011\u0010\u001B\u001A\u0004\u0018\u00010\n*\u00020\u0001H\u0007\u00F8\u0001\u0000\u001A\u0019\u0010\u001B\u001A\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u001A\u0014\u0010\u001C\u001A\u00020\r*\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001D\u001A\u001C\u0010\u001C\u001A\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001E\u001A\u0011\u0010\u001F\u001A\u0004\u0018\u00010\r*\u00020\u0001H\u0007\u00F8\u0001\u0000\u001A\u0019\u0010\u001F\u001A\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\u0007\u00F8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006 "}, d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class UStringsKt {
    public static final String toString-JSWoG40(long v, int v1) {
        return UnsignedKt.ulongToString(v, CharsKt.checkRadix(v1));
    }

    public static final String toString-LxnNnR4(byte b, int v) {
        String s = Integer.toString(b & 0xFF, CharsKt.checkRadix(v));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }

    public static final String toString-V7xB4Y4(int v, int v1) {
        String s = Long.toString(((long)v) & 0xFFFFFFFFL, CharsKt.checkRadix(v1));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }

    public static final String toString-olVBNx4(short v, int v1) {
        String s = Integer.toString(v & 0xFFFF, CharsKt.checkRadix(v1));
        Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
        return s;
    }

    public static final byte toUByte(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UByte uByte0 = UStringsKt.toUByteOrNull(s);
        if(uByte0 != null) {
            return uByte0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final byte toUByte(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UByte uByte0 = UStringsKt.toUByteOrNull(s, v);
        if(uByte0 != null) {
            return uByte0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final UByte toUByteOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return UStringsKt.toUByteOrNull(s, 10);
    }

    public static final UByte toUByteOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UInt uInt0 = UStringsKt.toUIntOrNull(s, v);
        if(uInt0 != null) {
            int v1 = uInt0.unbox-impl();
            return UByte..ExternalSyntheticBackport0.m$2(v1, 0xFF) <= 0 ? UByte.box-impl(((byte)v1)) : null;
        }
        return null;
    }

    public static final int toUInt(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UInt uInt0 = UStringsKt.toUIntOrNull(s);
        if(uInt0 != null) {
            return uInt0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final int toUInt(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UInt uInt0 = UStringsKt.toUIntOrNull(s, v);
        if(uInt0 != null) {
            return uInt0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final UInt toUIntOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return UStringsKt.toUIntOrNull(s, 10);
    }

    public static final UInt toUIntOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        CharsKt.checkRadix(v);
        int v1 = s.length();
        if(v1 == 0) {
            return null;
        }
        int v2 = 1;
        int v3 = 0;
        int v4 = s.charAt(0);
        if(Intrinsics.compare(v4, 0x30) >= 0) {
            v2 = 0;
        }
        else if(v1 == 1 || v4 != 43) {
            return null;
        }
        int v5 = 119304647;
        while(v2 < v1) {
            int v6 = CharsKt.digitOf(s.charAt(v2), v);
            if(v6 < 0) {
                return null;
            }
            if(UByte..ExternalSyntheticBackport0.m$2(v3, v5) > 0) {
                if(v5 == 119304647) {
                    v5 = (int)(0xFFFFFFFFL / (((long)v) & 0xFFFFFFFFL));
                    if(UByte..ExternalSyntheticBackport0.m$2(v3, v5) > 0) {
                        return null;
                    }
                    goto label_22;
                }
                return null;
            }
        label_22:
            int v7 = v3 * v;
            int v8 = v6 + v7;
            if(UByte..ExternalSyntheticBackport0.m$2(v8, v7) < 0) {
                return null;
            }
            ++v2;
            v3 = v8;
        }
        return UInt.box-impl(v3);
    }

    public static final long toULong(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        ULong uLong0 = UStringsKt.toULongOrNull(s);
        if(uLong0 != null) {
            return uLong0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final long toULong(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        ULong uLong0 = UStringsKt.toULongOrNull(s, v);
        if(uLong0 != null) {
            return uLong0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final ULong toULongOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return UStringsKt.toULongOrNull(s, 10);
    }

    public static final ULong toULongOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        CharsKt.checkRadix(v);
        int v1 = s.length();
        if(v1 == 0) {
            return null;
        }
        int v2 = 0;
        int v3 = s.charAt(0);
        if(Intrinsics.compare(v3, 0x30) < 0 && (v1 == 1 || v3 != 43)) {
            return null;
        }
        long v4 = 0L;
        long v5 = 512409557603043100L;
        while(v2 < v1) {
            int v6 = CharsKt.digitOf(s.charAt(v2), v);
            if(v6 < 0) {
                return null;
            }
            if(UByte..ExternalSyntheticBackport0.m(v4, v5) > 0) {
                if(v5 == 512409557603043100L) {
                    v5 = UByte..ExternalSyntheticBackport0.m$1(-1L, v);
                    if(UByte..ExternalSyntheticBackport0.m(v4, v5) > 0) {
                        return null;
                    }
                    goto label_20;
                }
                return null;
            }
        label_20:
            long v7 = v4 * ((long)v);
            long v8 = (((long)v6) & 0xFFFFFFFFL) + v7;
            if(UByte..ExternalSyntheticBackport0.m(v8, v7) < 0) {
                return null;
            }
            ++v2;
            v4 = v8;
        }
        return ULong.box-impl(v4);
    }

    public static final short toUShort(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UShort uShort0 = UStringsKt.toUShortOrNull(s);
        if(uShort0 != null) {
            return uShort0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final short toUShort(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UShort uShort0 = UStringsKt.toUShortOrNull(s, v);
        if(uShort0 != null) {
            return uShort0.unbox-impl();
        }
        StringsKt.numberFormatError(s);
        throw new KotlinNothingValueException();
    }

    public static final UShort toUShortOrNull(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return UStringsKt.toUShortOrNull(s, 10);
    }

    public static final UShort toUShortOrNull(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        UInt uInt0 = UStringsKt.toUIntOrNull(s, v);
        if(uInt0 != null) {
            int v1 = uInt0.unbox-impl();
            return UByte..ExternalSyntheticBackport0.m$2(v1, 0xFFFF) <= 0 ? UShort.box-impl(((short)v1)) : null;
        }
        return null;
    }
}

