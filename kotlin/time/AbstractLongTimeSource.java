package kotlin.time;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001A\u00020\bH\u0002J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0010\u001A\u00020\bH$R\u0014\u0010\u0002\u001A\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u001B\u0010\u0007\u001A\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000B\u0010\f\u001A\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "zero", "", "getZero", "()J", "zero$delegate", "Lkotlin/Lazy;", "adjustedRead", "markNow", "Lkotlin/time/ComparableTimeMark;", "read", "LongTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractLongTimeSource implements WithComparableMarks {
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0015\u0010\n\u001A\u00020\u0007H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000B\u0010\fJ\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\u001E\u0010\u0013\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001B\u0010\u0016\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0007H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u0016\u0010\u0006\u001A\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001C"}, d2 = {"Lkotlin/time/AbstractLongTimeSource$LongTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", "", "timeSource", "Lkotlin/time/AbstractLongTimeSource;", "offset", "Lkotlin/time/Duration;", "(JLkotlin/time/AbstractLongTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "elapsedNow", "elapsedNow-UwyO8pc", "()J", "equals", "", "other", "", "hashCode", "", "minus", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class LongTimeMark implements ComparableTimeMark {
        private final long offset;
        private final long startedAt;
        private final AbstractLongTimeSource timeSource;

        private LongTimeMark(long v, AbstractLongTimeSource abstractLongTimeSource0, long v1) {
            Intrinsics.checkNotNullParameter(abstractLongTimeSource0, "timeSource");
            super();
            this.startedAt = v;
            this.timeSource = abstractLongTimeSource0;
            this.offset = v1;
        }

        public LongTimeMark(long v, AbstractLongTimeSource abstractLongTimeSource0, long v1, DefaultConstructorMarker defaultConstructorMarker0) {
            this(v, abstractLongTimeSource0, v1);
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((ComparableTimeMark)object0));
        }

        @Override  // kotlin.time.ComparableTimeMark
        public int compareTo(ComparableTimeMark comparableTimeMark0) {
            return DefaultImpls.compareTo(this, comparableTimeMark0);
        }

        @Override  // kotlin.time.TimeMark
        public long elapsedNow-UwyO8pc() {
            long v = this.timeSource.adjustedRead();
            DurationUnit durationUnit0 = this.timeSource.getUnit();
            return Duration.minus-LRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(v, this.startedAt, durationUnit0), this.offset);
        }

        // 去混淆评级： 中等(50)
        @Override  // kotlin.time.ComparableTimeMark
        public boolean equals(Object object0) {
            return object0 instanceof LongTimeMark && Intrinsics.areEqual(this.timeSource, ((LongTimeMark)object0).timeSource) && Duration.equals-impl0(this.minus-UwyO8pc(((ComparableTimeMark)object0)), 0L);
        }

        @Override  // kotlin.time.TimeMark
        public boolean hasNotPassedNow() {
            return DefaultImpls.hasNotPassedNow(this);
        }

        @Override  // kotlin.time.TimeMark
        public boolean hasPassedNow() {
            return DefaultImpls.hasPassedNow(this);
        }

        @Override  // kotlin.time.ComparableTimeMark
        public int hashCode() {
            return Duration.hashCode-impl(this.offset) * 37 + ((int)(this.startedAt ^ this.startedAt >>> 0x20));
        }

        @Override  // kotlin.time.ComparableTimeMark
        public ComparableTimeMark minus-LRDsOJo(long v) {
            return DefaultImpls.minus-LRDsOJo(this, v);
        }

        @Override  // kotlin.time.TimeMark
        public TimeMark minus-LRDsOJo(long v) {
            return this.minus-LRDsOJo(v);
        }

        @Override  // kotlin.time.ComparableTimeMark
        public long minus-UwyO8pc(ComparableTimeMark comparableTimeMark0) {
            Intrinsics.checkNotNullParameter(comparableTimeMark0, "other");
            if(!(comparableTimeMark0 instanceof LongTimeMark) || !Intrinsics.areEqual(this.timeSource, ((LongTimeMark)comparableTimeMark0).timeSource)) {
                throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + comparableTimeMark0);
            }
            return Duration.plus-LRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(this.startedAt, ((LongTimeMark)comparableTimeMark0).startedAt, this.timeSource.getUnit()), Duration.minus-LRDsOJo(this.offset, ((LongTimeMark)comparableTimeMark0).offset));
        }

        @Override  // kotlin.time.ComparableTimeMark
        public ComparableTimeMark plus-LRDsOJo(long v) {
            DurationUnit durationUnit0 = this.timeSource.getUnit();
            if(Duration.isInfinite-impl(v)) {
                return new LongTimeMark(LongSaturatedMathKt.saturatingAdd-NuflL3o(this.startedAt, durationUnit0, v), this.timeSource, 0L, null);
            }
            long v1 = Duration.truncateTo-UwyO8pc$kotlin_stdlib(v, durationUnit0);
            long v2 = Duration.plus-LRDsOJo(Duration.minus-LRDsOJo(v, v1), this.offset);
            long v3 = LongSaturatedMathKt.saturatingAdd-NuflL3o(this.startedAt, durationUnit0, v1);
            long v4 = Duration.truncateTo-UwyO8pc$kotlin_stdlib(v2, durationUnit0);
            long v5 = LongSaturatedMathKt.saturatingAdd-NuflL3o(v3, durationUnit0, v4);
            long v6 = Duration.minus-LRDsOJo(v2, v4);
            long v7 = Duration.getInWholeNanoseconds-impl(v6);
            if(v5 != 0L && v7 != 0L && (v5 ^ v7) < 0L) {
                long v8 = DurationKt.toDuration(MathKt.getSign(v7), durationUnit0);
                v5 = LongSaturatedMathKt.saturatingAdd-NuflL3o(v5, durationUnit0, v8);
                v6 = Duration.minus-LRDsOJo(v6, v8);
            }
            if((1L | v5 - 1L) == 0x7FFFFFFFFFFFFFFFL) {
                v6 = 0L;
            }
            return new LongTimeMark(v5, this.timeSource, v6, null);
        }

        @Override  // kotlin.time.TimeMark
        public TimeMark plus-LRDsOJo(long v) {
            return this.plus-LRDsOJo(v);
        }

        @Override
        public String toString() {
            return "LongTimeMark(" + this.startedAt + DurationUnitKt.shortName(this.timeSource.getUnit()) + " + " + Duration.toString-impl(this.offset) + ", " + this.timeSource + ')';
        }
    }

    private final DurationUnit unit;
    private final Lazy zero$delegate;

    public AbstractLongTimeSource(DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        super();
        this.unit = durationUnit0;
        this.zero$delegate = LazyKt.lazy(new Function0() {
            {
                AbstractLongTimeSource.this = abstractLongTimeSource0;
                super(0);
            }

            public final Long invoke() {
                return AbstractLongTimeSource.this.read();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    private final long adjustedRead() {
        return this.read() - this.getZero();
    }

    protected final DurationUnit getUnit() {
        return this.unit;
    }

    private final long getZero() {
        return ((Number)this.zero$delegate.getValue()).longValue();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.time.TimeSource$WithComparableMarks
    public ComparableTimeMark markNow() {
        return new LongTimeMark(this.adjustedRead(), this, 0L, null);
    }

    @Override  // kotlin.time.TimeSource
    public TimeMark markNow() {
        return this.markNow();
    }

    protected abstract long read();
}

