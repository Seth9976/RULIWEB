package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0000\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0010\u0010\u000F\u001A\u00020\u0003H\u0086\n¢\u0006\u0004\b\u0010\u0010\u000BJ\u0010\u0010\u0011\u001A\u00020\u0003H\u0086\n¢\u0006\u0004\b\u0012\u0010\u000BJ\u001A\u0010\u0013\u001A\u00020\u00142\b\u0010\u0015\u001A\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001A\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001A\u0010\u001BJ\u000F\u0010\u001C\u001A\u00020\u001DH\u0016¢\u0006\u0004\b\u001E\u0010\u001FR\u0012\u0010\u0002\u001A\u00020\u00038Æ\u0002¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0016\u0010\u0007\u001A\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rR\u0012\u0010\u0004\u001A\u00020\u00038Æ\u0002¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000B\u0088\u0001\u0007\u0092\u0001\u00020\b¨\u0006 "}, d2 = {"Landroidx/collection/FloatFloatPair;", "", "first", "", "second", "constructor-impl", "(FF)J", "packedValue", "", "(J)J", "getFirst-impl", "(J)F", "getPackedValue$annotations", "()V", "getSecond-impl", "component1", "component1-impl", "component2", "component2-impl", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@JvmInline
public final class FloatFloatPair {
    public final long packedValue;

    private FloatFloatPair(long v) {
        this.packedValue = v;
    }

    public static final FloatFloatPair box-impl(long v) {
        return new FloatFloatPair(v);
    }

    public static final float component1-impl(long v) {
        return Float.intBitsToFloat(((int)(v >> 0x20)));
    }

    public static final float component2-impl(long v) {
        return Float.intBitsToFloat(((int)(v & 0xFFFFFFFFL)));
    }

    public static long constructor-impl(float f, float f1) {
        return ((long)Float.floatToRawIntBits(f1)) & 0xFFFFFFFFL | ((long)Float.floatToRawIntBits(f)) << 0x20;
    }

    public static long constructor-impl(long v) [...] // Inlined contents

    @Override
    public boolean equals(Object object0) {
        return FloatFloatPair.equals-impl(this.packedValue, object0);
    }

    public static boolean equals-impl(long v, Object object0) {
        return object0 instanceof FloatFloatPair ? v == ((FloatFloatPair)object0).unbox-impl() : false;
    }

    public static final boolean equals-impl0(long v, long v1) {
        return v == v1;
    }

    public static final float getFirst-impl(long v) {
        return Float.intBitsToFloat(((int)(v >> 0x20)));
    }

    public static void getPackedValue$annotations() {
    }

    public static final float getSecond-impl(long v) {
        return Float.intBitsToFloat(((int)(v & 0xFFFFFFFFL)));
    }

    @Override
    public int hashCode() {
        return FloatFloatPair.hashCode-impl(this.packedValue);
    }

    public static int hashCode-impl(long v) {
        return (int)(v ^ v >>> 0x20);
    }

    @Override
    public String toString() {
        return FloatFloatPair.toString-impl(this.packedValue);
    }

    public static String toString-impl(long v) {
        return "(" + Float.intBitsToFloat(((int)(v >> 0x20))) + ", " + Float.intBitsToFloat(((int)(v & 0xFFFFFFFFL))) + ')';
    }

    public final long unbox-impl() {
        return this.packedValue;
    }
}

