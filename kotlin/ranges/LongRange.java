package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\u0018\u0000 \u001A2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u001AB\u0015\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\u0006\u0010\u0006\u001A\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0003H\u0096\u0002J\u0013\u0010\u0012\u001A\u00020\u00102\b\u0010\u0013\u001A\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\b\u0010\u0017\u001A\u00020\u0010H\u0016J\b\u0010\u0018\u001A\u00020\u0019H\u0016R\u001A\u0010\b\u001A\u00020\u00038VX\u0097\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001A\u0004\b\u000B\u0010\fR\u0014\u0010\u0006\u001A\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\fR\u0014\u0010\u0005\u001A\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\f¨\u0006\u001B"}, d2 = {"Lkotlin/ranges/LongRange;", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/ClosedRange;", "", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "(JJ)V", "endExclusive", "getEndExclusive$annotations", "()V", "getEndExclusive", "()Ljava/lang/Long;", "getEndInclusive", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class LongRange extends LongProgression implements ClosedRange, OpenEndRange {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/LongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/LongRange;", "getEMPTY", "()Lkotlin/ranges/LongRange;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final LongRange getEMPTY() {
            return LongRange.EMPTY;
        }
    }

    public static final Companion Companion;
    private static final LongRange EMPTY;

    static {
        LongRange.Companion = new Companion(null);
        LongRange.EMPTY = new LongRange(1L, 0L);
    }

    public LongRange(long v, long v1) {
        super(v, v1, 1L);
    }

    public boolean contains(long v) {
        return this.getFirst() <= v && v <= this.getLast();
    }

    @Override  // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public boolean contains(Comparable comparable0) {
        return this.contains(((Number)comparable0).longValue());
    }

    // 去混淆评级： 低(30)
    @Override  // kotlin.ranges.LongProgression
    public boolean equals(Object object0) {
        return object0 instanceof LongRange && (this.isEmpty() && ((LongRange)object0).isEmpty() || this.getFirst() == ((LongRange)object0).getFirst() && this.getLast() == ((LongRange)object0).getLast());
    }

    @Override  // kotlin.ranges.OpenEndRange
    public Comparable getEndExclusive() {
        return this.getEndExclusive();
    }

    public Long getEndExclusive() {
        if(this.getLast() == 0x7FFFFFFFFFFFFFFFL) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
        }
        return (long)(this.getLast() + 1L);
    }

    @Deprecated(message = "Can throw an exception when it\'s impossible to represent the value with Long type, for example, when the range includes MAX_VALUE. It\'s recommended to use \'endInclusive\' property that doesn\'t throw.")
    public static void getEndExclusive$annotations() {
    }

    @Override  // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return this.getEndInclusive();
    }

    public Long getEndInclusive() {
        return this.getLast();
    }

    @Override  // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public Comparable getStart() {
        return this.getStart();
    }

    public Long getStart() {
        return this.getFirst();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.ranges.LongProgression
    public int hashCode() {
        return this.isEmpty() ? -1 : ((int)(0x1FL * (this.getFirst() ^ this.getFirst() >>> 0x20) + (this.getLast() ^ this.getLast() >>> 0x20)));
    }

    @Override  // kotlin.ranges.LongProgression, kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return this.getFirst() > this.getLast();
    }

    @Override  // kotlin.ranges.LongProgression
    public String toString() {
        return this.getFirst() + ".." + this.getLast();
    }
}

