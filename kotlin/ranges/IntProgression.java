package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0010\b\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001F\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0002H\u0016J\b\u0010\u0012\u001A\u00020\u000EH\u0016J\t\u0010\u0013\u001A\u00020\u0014H\u0096\u0002J\b\u0010\u0015\u001A\u00020\u0016H\u0016R\u0011\u0010\u0007\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\tR\u0011\u0010\u0005\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lkotlin/ranges/IntProgression;", "", "", "start", "endInclusive", "step", "(III)V", "first", "getFirst", "()I", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/IntIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class IntProgression implements Iterable, KMappedMarker {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u0006¨\u0006\t"}, d2 = {"Lkotlin/ranges/IntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/IntProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final IntProgression fromClosedRange(int v, int v1, int v2) {
            return new IntProgression(v, v1, v2);
        }
    }

    public static final Companion Companion;
    private final int first;
    private final int last;
    private final int step;

    static {
        IntProgression.Companion = new Companion(null);
    }

    public IntProgression(int v, int v1, int v2) {
        switch(v2) {
            case 0x80000000: {
                throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
            }
            case 0: {
                throw new IllegalArgumentException("Step must be non-zero.");
            }
            default: {
                this.first = v;
                this.last = ProgressionUtilKt.getProgressionLastElement(v, v1, v2);
                this.step = v2;
            }
        }
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof IntProgression && (this.isEmpty() && ((IntProgression)object0).isEmpty() || this.first == ((IntProgression)object0).first && this.last == ((IntProgression)object0).last && this.step == ((IntProgression)object0).step);
    }

    public final int getFirst() {
        return this.first;
    }

    public final int getLast() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    // 去混淆评级： 低(20)
    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : (this.first * 0x1F + this.last) * 0x1F + this.step;
    }

    public boolean isEmpty() {
        return this.step <= 0 ? this.first < this.last : this.first > this.last;
    }

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    public IntIterator iterator() {
        return new IntProgressionIterator(this.first, this.last, this.step);
    }

    @Override
    public String toString() {
        int v;
        StringBuilder stringBuilder0;
        if(this.step > 0) {
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

