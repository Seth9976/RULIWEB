package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000F\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0012\u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001A\u00028\u0000\u0012\u0006\u0010\u0005\u001A\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u000EH\u0096\u0002J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016R\u0016\u0010\u0005\u001A\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001A\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\n\u0010\b¨\u0006\u0013"}, d2 = {"Lkotlin/ranges/ComparableRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
class ComparableRange implements ClosedRange {
    private final Comparable endInclusive;
    private final Comparable start;

    public ComparableRange(Comparable comparable0, Comparable comparable1) {
        Intrinsics.checkNotNullParameter(comparable0, "start");
        Intrinsics.checkNotNullParameter(comparable1, "endInclusive");
        super();
        this.start = comparable0;
        this.endInclusive = comparable1;
    }

    @Override  // kotlin.ranges.ClosedRange
    public boolean contains(Comparable comparable0) {
        return DefaultImpls.contains(this, comparable0);
    }

    // 去混淆评级： 中等(50)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ComparableRange && (this.isEmpty() && ((ComparableRange)object0).isEmpty() || Intrinsics.areEqual(this.getStart(), ((ComparableRange)object0).getStart()) && Intrinsics.areEqual(this.getEndInclusive(), ((ComparableRange)object0).getEndInclusive()));
    }

    @Override  // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return this.endInclusive;
    }

    @Override  // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return this.start;
    }

    // 去混淆评级： 低(20)
    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : this.getStart().hashCode() * 0x1F + this.getEndInclusive().hashCode();
    }

    @Override  // kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return DefaultImpls.isEmpty(this);
    }

    @Override
    public String toString() {
        return this.getStart() + ".." + this.getEndInclusive();
    }
}

