package kotlin.time;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "Using AbstractDoubleTimeSource is no longer recommended, use AbstractLongTimeSource instead.")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\b\'\u0018\u00002\u00020\u0001:\u0001\u000BB\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\nH$R\u0014\u0010\u0002\u001A\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkotlin/time/AbstractDoubleTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "markNow", "Lkotlin/time/ComparableTimeMark;", "read", "", "DoubleTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractDoubleTimeSource implements WithComparableMarks {
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0015\u0010\n\u001A\u00020\u0007H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000B\u0010\fJ\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\u001E\u0010\u0013\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001B\u0010\u0016\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0007H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u0016\u0010\u0006\u001A\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001C"}, d2 = {"Lkotlin/time/AbstractDoubleTimeSource$DoubleTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", "", "timeSource", "Lkotlin/time/AbstractDoubleTimeSource;", "offset", "Lkotlin/time/Duration;", "(DLkotlin/time/AbstractDoubleTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "elapsedNow", "elapsedNow-UwyO8pc", "()J", "equals", "", "other", "", "hashCode", "", "minus", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class DoubleTimeMark implements ComparableTimeMark {
        private final long offset;
        private final double startedAt;
        private final AbstractDoubleTimeSource timeSource;

        private DoubleTimeMark(double f, AbstractDoubleTimeSource abstractDoubleTimeSource0, long v) {
            Intrinsics.checkNotNullParameter(abstractDoubleTimeSource0, "timeSource");
            super();
            this.startedAt = f;
            this.timeSource = abstractDoubleTimeSource0;
            this.offset = v;
        }

        public DoubleTimeMark(double f, AbstractDoubleTimeSource abstractDoubleTimeSource0, long v, DefaultConstructorMarker defaultConstructorMarker0) {
            this(f, abstractDoubleTimeSource0, v);
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
            double f = this.timeSource.read();
            DurationUnit durationUnit0 = this.timeSource.getUnit();
            return Duration.minus-LRDsOJo(DurationKt.toDuration(f - this.startedAt, durationUnit0), this.offset);
        }

        // 去混淆评级： 中等(50)
        @Override  // kotlin.time.ComparableTimeMark
        public boolean equals(Object object0) {
            return object0 instanceof DoubleTimeMark && Intrinsics.areEqual(this.timeSource, ((DoubleTimeMark)object0).timeSource) && Duration.equals-impl0(this.minus-UwyO8pc(((ComparableTimeMark)object0)), 0L);
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
            return Duration.hashCode-impl(Duration.plus-LRDsOJo(DurationKt.toDuration(this.startedAt, this.timeSource.getUnit()), this.offset));
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
            if(!(comparableTimeMark0 instanceof DoubleTimeMark) || !Intrinsics.areEqual(this.timeSource, ((DoubleTimeMark)comparableTimeMark0).timeSource)) {
                throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + comparableTimeMark0);
            }
            if(Duration.equals-impl0(this.offset, ((DoubleTimeMark)comparableTimeMark0).offset) && Duration.isInfinite-impl(this.offset)) {
                return 0L;
            }
            long v = Duration.minus-LRDsOJo(this.offset, ((DoubleTimeMark)comparableTimeMark0).offset);
            long v1 = DurationKt.toDuration(this.startedAt - ((DoubleTimeMark)comparableTimeMark0).startedAt, this.timeSource.getUnit());
            return Duration.equals-impl0(v1, Duration.unaryMinus-UwyO8pc(v)) ? 0L : Duration.plus-LRDsOJo(v1, v);
        }

        @Override  // kotlin.time.ComparableTimeMark
        public ComparableTimeMark plus-LRDsOJo(long v) {
            long v1 = Duration.plus-LRDsOJo(this.offset, v);
            return new DoubleTimeMark(this.startedAt, this.timeSource, v1, null);
        }

        @Override  // kotlin.time.TimeMark
        public TimeMark plus-LRDsOJo(long v) {
            return this.plus-LRDsOJo(v);
        }

        @Override
        public String toString() {
            return "DoubleTimeMark(" + this.startedAt + DurationUnitKt.shortName(this.timeSource.getUnit()) + " + " + Duration.toString-impl(this.offset) + ", " + this.timeSource + ')';
        }
    }

    private final DurationUnit unit;

    public AbstractDoubleTimeSource(DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        super();
        this.unit = durationUnit0;
    }

    protected final DurationUnit getUnit() {
        return this.unit;
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.time.TimeSource$WithComparableMarks
    public ComparableTimeMark markNow() {
        return new DoubleTimeMark(this.read(), this, 0L, null);
    }

    @Override  // kotlin.time.TimeSource
    public TimeMark markNow() {
        return this.markNow();
    }

    protected abstract double read();
}

