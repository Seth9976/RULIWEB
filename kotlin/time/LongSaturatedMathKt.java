package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\u000B\n\u0000\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001A\u0018\u0010\b\u001A\u00020\u00042\u0006\u0010\u0002\u001A\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\t\u001A*\u0010\n\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0003\u001A\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000E\u001A*\u0010\u000F\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0003\u001A\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000E\u001A(\u0010\u0011\u001A\u00020\u00042\u0006\u0010\u0012\u001A\u00020\u00012\u0006\u0010\u0013\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001A(\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001A(\u0010\u0018\u001A\u00020\u00042\u0006\u0010\u0019\u001A\u00020\u00012\u0006\u0010\u001A\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001A\r\u0010\u001B\u001A\u00020\u001C*\u00020\u0001H\u0080\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001D"}, d2 = {"checkInfiniteSumDefined", "", "value", "duration", "Lkotlin/time/Duration;", "durationInUnit", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "infinityOfSign", "(J)J", "saturatingAdd", "unit", "Lkotlin/time/DurationUnit;", "saturatingAdd-NuflL3o", "(JLkotlin/time/DurationUnit;J)J", "saturatingAddInHalves", "saturatingAddInHalves-NuflL3o", "saturatingDiff", "valueNs", "origin", "(JJLkotlin/time/DurationUnit;)J", "saturatingFiniteDiff", "value1", "value2", "saturatingOriginsDiff", "origin1", "origin2", "isSaturated", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class LongSaturatedMathKt {
    private static final long checkInfiniteSumDefined-PjuGub4(long v, long v1, long v2) {
        if(Duration.isInfinite-impl(v1) && (v ^ v2) < 0L) {
            throw new IllegalArgumentException("Summing infinities of different signs");
        }
        return v;
    }

    // 去混淆评级： 低(40)
    private static final long infinityOfSign(long v) {
        return Long.compare(v, 0L) >= 0 ? 0x7FFFFFFFFFFFFFFFL : 0x8000000000000003L;
    }

    public static final boolean isSaturated(long v) {
        return (v - 1L | 1L) == 0x7FFFFFFFFFFFFFFFL;
    }

    public static final long saturatingAdd-NuflL3o(long v, DurationUnit durationUnit0, long v1) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        long v2 = Duration.toLong-impl(v1, durationUnit0);
        if((v - 1L | 1L) == 0x7FFFFFFFFFFFFFFFL) {
            return LongSaturatedMathKt.checkInfiniteSumDefined-PjuGub4(v, v1, v2);
        }
        if((v2 - 1L | 1L) == 0x7FFFFFFFFFFFFFFFL) {
            return LongSaturatedMathKt.saturatingAddInHalves-NuflL3o(v, durationUnit0, v1);
        }
        long v3 = v + v2;
        if(((v ^ v3) & (v2 ^ v3)) < 0L) {
            return v >= 0L ? 0x7FFFFFFFFFFFFFFFL : 0x8000000000000000L;
        }
        return v3;
    }

    private static final long saturatingAddInHalves-NuflL3o(long v, DurationUnit durationUnit0, long v1) {
        long v2 = Duration.div-UwyO8pc(v1, 2);
        long v3 = Duration.toLong-impl(v2, durationUnit0);
        return (1L | v3 - 1L) == 0x7FFFFFFFFFFFFFFFL ? v3 : LongSaturatedMathKt.saturatingAdd-NuflL3o(LongSaturatedMathKt.saturatingAdd-NuflL3o(v, durationUnit0, v2), durationUnit0, Duration.minus-LRDsOJo(v1, v2));
    }

    public static final long saturatingDiff(long v, long v1, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        return (1L | v1 - 1L) == 0x7FFFFFFFFFFFFFFFL ? Duration.unaryMinus-UwyO8pc(LongSaturatedMathKt.infinityOfSign(v1)) : LongSaturatedMathKt.saturatingFiniteDiff(v, v1, durationUnit0);
    }

    private static final long saturatingFiniteDiff(long v, long v1, DurationUnit durationUnit0) {
        long v2 = v - v1;
        if(((v2 ^ v) & ~(v2 ^ v1)) < 0L) {
            if(durationUnit0.compareTo(DurationUnit.MILLISECONDS) < 0) {
                long v3 = DurationUnitKt.convertDurationUnit(1L, DurationUnit.MILLISECONDS, durationUnit0);
                return Duration.plus-LRDsOJo(DurationKt.toDuration(v / v3 - v1 / v3, DurationUnit.MILLISECONDS), DurationKt.toDuration(v % v3 - v1 % v3, durationUnit0));
            }
            return Duration.unaryMinus-UwyO8pc(LongSaturatedMathKt.infinityOfSign(v2));
        }
        return DurationKt.toDuration(v2, durationUnit0);
    }

    public static final long saturatingOriginsDiff(long v, long v1, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        if((v1 - 1L | 1L) == 0x7FFFFFFFFFFFFFFFL) {
            return v == v1 ? 0L : Duration.unaryMinus-UwyO8pc(LongSaturatedMathKt.infinityOfSign(v1));
        }
        return (1L | v - 1L) == 0x7FFFFFFFFFFFFFFFL ? LongSaturatedMathKt.infinityOfSign(v) : LongSaturatedMathKt.saturatingFiniteDiff(v, v1, durationUnit0);
    }
}

