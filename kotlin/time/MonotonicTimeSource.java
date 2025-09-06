package kotlin.time;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0010\u000E\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000BJ \u0010\f\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u000F\u0010\u000BJ\u0018\u0010\u0010\u001A\u00020\t2\u0006\u0010\u0007\u001A\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001A\u00020\u0006H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0016\u001A\u00020\u0004H\u0002J\b\u0010\u0017\u001A\u00020\u0018H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0019"}, d2 = {"Lkotlin/time/MonotonicTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "()V", "zero", "", "adjustReading", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "timeMark", "duration", "Lkotlin/time/Duration;", "adjustReading-6QKq23U", "(JJ)J", "differenceBetween", "one", "another", "differenceBetween-fRLX17w", "elapsedFrom", "elapsedFrom-6eNON_k", "(J)J", "markNow", "markNow-z9LOYto", "()J", "read", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MonotonicTimeSource implements WithComparableMarks {
    public static final MonotonicTimeSource INSTANCE;
    private static final long zero;

    static {
        MonotonicTimeSource.INSTANCE = new MonotonicTimeSource();
        MonotonicTimeSource.zero = System.nanoTime();
    }

    public final long adjustReading-6QKq23U(long v, long v1) {
        return ValueTimeMark.constructor-impl(LongSaturatedMathKt.saturatingAdd-NuflL3o(v, DurationUnit.NANOSECONDS, v1));
    }

    public final long differenceBetween-fRLX17w(long v, long v1) {
        return LongSaturatedMathKt.saturatingOriginsDiff(v, v1, DurationUnit.NANOSECONDS);
    }

    // 去混淆评级： 低(20)
    public final long elapsedFrom-6eNON_k(long v) {
        return LongSaturatedMathKt.saturatingDiff(-4634700L, v, DurationUnit.NANOSECONDS);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.time.TimeSource$WithComparableMarks
    public ComparableTimeMark markNow() {
        return ValueTimeMark.box-impl(-3831200L);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.time.TimeSource
    public TimeMark markNow() {
        return ValueTimeMark.box-impl(-3708700L);
    }

    // 去混淆评级： 低(20)
    public long markNow-z9LOYto() [...] // 潜在的解密器

    private final long read() [...] // 潜在的解密器

    @Override
    public String toString() [...] // Inlined contents
}

