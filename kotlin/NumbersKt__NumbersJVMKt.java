package kotlin;

import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\n\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0003\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010\u0003\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0004\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010\u0004\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\u0015\u0010\u0005\u001A\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001A\u00020\u0002H\u0087\b\u001A\u0015\u0010\u0005\u001A\u00020\t*\u00020\n2\u0006\u0010\b\u001A\u00020\u0001H\u0087\b\u001A\r\u0010\u000B\u001A\u00020\f*\u00020\u0006H\u0087\b\u001A\r\u0010\u000B\u001A\u00020\f*\u00020\tH\u0087\b\u001A\r\u0010\r\u001A\u00020\f*\u00020\u0006H\u0087\b\u001A\r\u0010\r\u001A\u00020\f*\u00020\tH\u0087\b\u001A\r\u0010\u000E\u001A\u00020\f*\u00020\u0006H\u0087\b\u001A\r\u0010\u000E\u001A\u00020\f*\u00020\tH\u0087\b\u001A\u0015\u0010\u000F\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010\u000F\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010\u0011\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010\u0011\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u0001H\u0087\b\u001A\r\u0010\u0012\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010\u0012\u001A\u00020\u0002*\u00020\u0002H\u0087\b\u001A\r\u0010\u0013\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010\u0013\u001A\u00020\u0002*\u00020\u0002H\u0087\b\u001A\r\u0010\u0014\u001A\u00020\u0002*\u00020\u0006H\u0087\b\u001A\r\u0010\u0014\u001A\u00020\u0001*\u00020\tH\u0087\b\u001A\r\u0010\u0015\u001A\u00020\u0002*\u00020\u0006H\u0087\b\u001A\r\u0010\u0015\u001A\u00020\u0001*\u00020\tH\u0087\b¨\u0006\u0016"}, d2 = {"countLeadingZeroBits", "", "", "countOneBits", "countTrailingZeroBits", "fromBits", "", "Lkotlin/Double$Companion;", "bits", "", "Lkotlin/Float$Companion;", "isFinite", "", "isInfinite", "isNaN", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "toBits", "toRawBits", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/NumbersKt")
class NumbersKt__NumbersJVMKt extends NumbersKt__FloorDivModKt {
    private static final int countLeadingZeroBits(int v) {
        return Integer.numberOfLeadingZeros(v);
    }

    private static final int countLeadingZeroBits(long v) {
        return Long.numberOfLeadingZeros(v);
    }

    private static final int countOneBits(int v) {
        return Integer.bitCount(v);
    }

    private static final int countOneBits(long v) {
        return Long.bitCount(v);
    }

    private static final int countTrailingZeroBits(int v) {
        return Integer.numberOfTrailingZeros(v);
    }

    private static final int countTrailingZeroBits(long v) {
        return Long.numberOfTrailingZeros(v);
    }

    private static final double fromBits(DoubleCompanionObject doubleCompanionObject0, long v) {
        Intrinsics.checkNotNullParameter(doubleCompanionObject0, "<this>");
        return Double.longBitsToDouble(v);
    }

    private static final float fromBits(FloatCompanionObject floatCompanionObject0, int v) {
        Intrinsics.checkNotNullParameter(floatCompanionObject0, "<this>");
        return Float.intBitsToFloat(v);
    }

    // 去混淆评级： 低(20)
    private static final boolean isFinite(double f) {
        return !Double.isInfinite(f) && !Double.isNaN(f);
    }

    // 去混淆评级： 低(20)
    private static final boolean isFinite(float f) {
        return !Float.isInfinite(f) && !Float.isNaN(f);
    }

    private static final boolean isInfinite(double f) {
        return Double.isInfinite(f);
    }

    private static final boolean isInfinite(float f) {
        return Float.isInfinite(f);
    }

    private static final boolean isNaN(double f) {
        return Double.isNaN(f);
    }

    private static final boolean isNaN(float f) {
        return Float.isNaN(f);
    }

    private static final int rotateLeft(int v, int v1) {
        return Integer.rotateLeft(v, v1);
    }

    private static final long rotateLeft(long v, int v1) {
        return Long.rotateLeft(v, v1);
    }

    private static final int rotateRight(int v, int v1) {
        return Integer.rotateRight(v, v1);
    }

    private static final long rotateRight(long v, int v1) {
        return Long.rotateRight(v, v1);
    }

    private static final int takeHighestOneBit(int v) {
        return Integer.highestOneBit(v);
    }

    private static final long takeHighestOneBit(long v) {
        return Long.highestOneBit(v);
    }

    private static final int takeLowestOneBit(int v) {
        return Integer.lowestOneBit(v);
    }

    private static final long takeLowestOneBit(long v) {
        return Long.lowestOneBit(v);
    }

    private static final int toBits(float f) {
        return Float.floatToIntBits(f);
    }

    private static final long toBits(double f) {
        return Double.doubleToLongBits(f);
    }

    private static final int toRawBits(float f) {
        return Float.floatToRawIntBits(f);
    }

    private static final long toRawBits(double f) {
        return Double.doubleToRawLongBits(f);
    }
}

