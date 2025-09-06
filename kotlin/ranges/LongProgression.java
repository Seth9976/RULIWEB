package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0010\t\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u001F\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\b\u0010\u0013\u001A\u00020\u000EH\u0016J\t\u0010\u0014\u001A\u00020\u0015H\u0096\u0002J\b\u0010\u0016\u001A\u00020\u0017H\u0016R\u0011\u0010\u0007\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\tR\u0011\u0010\u0005\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lkotlin/ranges/LongProgression;", "", "", "start", "endInclusive", "step", "(JJJ)V", "first", "getFirst", "()J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/LongIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class LongProgression implements Iterable, KMappedMarker {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006¨\u0006\t"}, d2 = {"Lkotlin/ranges/LongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/LongProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final LongProgression fromClosedRange(long v, long v1, long v2) {
            return new LongProgression(v, v1, v2);
        }
    }

    public static final Companion Companion;
    private final long first;
    private final long last;
    private final long step;

    static {
        LongProgression.Companion = new Companion(null);
    }

    public LongProgression(long v, long v1, long v2) {
        if(v2 == 0L) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if(v2 == 0x8000000000000000L) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = v;
        this.last = ProgressionUtilKt.getProgressionLastElement(v, v1, v2);
        this.step = v2;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof LongProgression && (this.isEmpty() && ((LongProgression)object0).isEmpty() || this.first == ((LongProgression)object0).first && this.last == ((LongProgression)object0).last && this.step == ((LongProgression)object0).step);
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getLast() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    // 去混淆评级： 低(20)
    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : ((int)(0x1FL * ((this.first ^ this.first >>> 0x20) * 0x1FL + (this.last ^ this.last >>> 0x20)) + (this.step ^ this.step >>> 0x20)));
    }

    public boolean isEmpty() {
        return Long.compare(this.step, 0L) <= 0 ? this.first < this.last : this.first > this.last;
    }

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    public LongIterator iterator() {
        return new LongProgressionIterator(this.first, this.last, this.step);
    }

    @Override
    public String toString() {
        long v;
        StringBuilder stringBuilder0;
        if(Long.compare(this.step, 0L) > 0) {
            stringBuilder0 = new StringBuilder();
            stringBuilder0.append(this.first);
            stringBuilder0.append("..");
            stringBuilder0.append(this.last);
            stringBuilder0.append(" step ");
            v = this.step;
        }
        else {
            stringBuilder0 = new StringBuilder();
            stringBuilder0.append(this.first);
            stringBuilder0.append(" downTo ");
            stringBuilder0.append(this.last);
            stringBuilder0.append(" step ");
            v = -this.step;
        }
        stringBuilder0.append(v);
        return stringBuilder0.toString();
    }
}

