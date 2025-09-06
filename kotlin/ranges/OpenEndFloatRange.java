package kotlin.ranges;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u0002H\u0096\u0002J\u0013\u0010\u000E\u001A\u00020\f2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\b\u0010\u0013\u001A\u00020\fH\u0016J\u0018\u0010\u0014\u001A\u00020\f2\u0006\u0010\u0015\u001A\u00020\u00022\u0006\u0010\u0016\u001A\u00020\u0002H\u0002J\b\u0010\u0017\u001A\u00020\u0018H\u0016R\u000E\u0010\u0006\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001A\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\t¨\u0006\u0019"}, d2 = {"Lkotlin/ranges/OpenEndFloatRange;", "Lkotlin/ranges/OpenEndRange;", "", "start", "endExclusive", "(FF)V", "_endExclusive", "_start", "getEndExclusive", "()Ljava/lang/Float;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "lessThanOrEquals", "a", "b", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class OpenEndFloatRange implements OpenEndRange {
    private final float _endExclusive;
    private final float _start;

    public OpenEndFloatRange(float f, float f1) {
        this._start = f;
        this._endExclusive = f1;
    }

    public boolean contains(float f) {
        return f >= this._start && f < this._endExclusive;
    }

    @Override  // kotlin.ranges.OpenEndRange
    public boolean contains(Comparable comparable0) {
        return this.contains(((Number)comparable0).floatValue());
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof OpenEndFloatRange && (this.isEmpty() && ((OpenEndFloatRange)object0).isEmpty() || this._start == ((OpenEndFloatRange)object0)._start && this._endExclusive == ((OpenEndFloatRange)object0)._endExclusive);
    }

    @Override  // kotlin.ranges.OpenEndRange
    public Comparable getEndExclusive() {
        return this.getEndExclusive();
    }

    public Float getEndExclusive() {
        return this._endExclusive;
    }

    @Override  // kotlin.ranges.OpenEndRange
    public Comparable getStart() {
        return this.getStart();
    }

    public Float getStart() {
        return this._start;
    }

    // 去混淆评级： 低(20)
    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : Float.floatToIntBits(this._start) * 0x1F + Float.floatToIntBits(this._endExclusive);
    }

    @Override  // kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        return this._start >= this._endExclusive;
    }

    private final boolean lessThanOrEquals(float f, float f1) {
        return f <= f1;
    }

    @Override
    public String toString() {
        return this._start + "..<" + this._endExclusive;
    }
}

