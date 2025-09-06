package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.ULong;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001A2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001AB\"\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\b\u0010\u0015\u001A\u00020\u0010H\u0016J\u0012\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0086\u0002ø\u0001\u0000J\b\u0010\u0018\u001A\u00020\u0019H\u0016R\u0019\u0010\b\u001A\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000B\u001A\u0004\b\t\u0010\nR\u0019\u0010\f\u001A\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000B\u001A\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\nø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001B"}, d2 = {"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", "start", "endInclusive", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-s-VKNKU", "()J", "J", "last", "getLast-s-VKNKU", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class ULongProgression implements Iterable, KMappedMarker {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000B\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/ranges/ULongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", "", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ULongProgression fromClosedRange-7ftBX0g(long v, long v1, long v2) {
            return new ULongProgression(v, v1, v2, null);
        }
    }

    public static final Companion Companion;
    private final long first;
    private final long last;
    private final long step;

    static {
        ULongProgression.Companion = new Companion(null);
    }

    private ULongProgression(long v, long v1, long v2) {
        if(v2 == 0L) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if(v2 == 0x8000000000000000L) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = v;
        this.last = UProgressionUtilKt.getProgressionLastElement-7ftBX0g(v, v1, v2);
        this.step = v2;
    }

    public ULongProgression(long v, long v1, long v2, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v, v1, v2);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ULongProgression && (this.isEmpty() && ((ULongProgression)object0).isEmpty() || this.first == ((ULongProgression)object0).first && this.last == ((ULongProgression)object0).last && this.step == ((ULongProgression)object0).step);
    }

    public final long getFirst-s-VKNKU() {
        return this.first;
    }

    public final long getLast-s-VKNKU() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    // 去混淆评级： 低(20)
    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : (((int)(this.first ^ this.first >>> 0x20)) * 0x1F + ((int)(this.last ^ this.last >>> 0x20))) * 0x1F + ((int)(this.step ^ this.step >>> 0x20));
    }

    public boolean isEmpty() {
        return Long.compare(this.step, 0L) <= 0 ? UByte..ExternalSyntheticBackport0.m(this.first, this.last) < 0 : UByte..ExternalSyntheticBackport0.m(this.first, this.last) > 0;
    }

    @Override
    public final Iterator iterator() {
        return new ULongProgressionIterator(this.first, this.last, this.step, null);
    }

    @Override
    public String toString() {
        long v;
        StringBuilder stringBuilder0;
        if(Long.compare(this.step, 0L) > 0) {
            stringBuilder0 = new StringBuilder();
            stringBuilder0.append(ULong.toString-impl(this.first));
            stringBuilder0.append("..");
            stringBuilder0.append(ULong.toString-impl(this.last));
            stringBuilder0.append(" step ");
            v = this.step;
        }
        else {
            stringBuilder0 = new StringBuilder();
            stringBuilder0.append(ULong.toString-impl(this.first));
            stringBuilder0.append(" downTo ");
            stringBuilder0.append(ULong.toString-impl(this.last));
            stringBuilder0.append(" step ");
            v = -this.step;
        }
        stringBuilder0.append(v);
        return stringBuilder0.toString();
    }
}

