package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\"\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001A\u00020\u0006H\u0016J\b\u0010\u0014\u001A\u00020\u0010H\u0016J\u0012\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0086\u0002ø\u0001\u0000J\b\u0010\u0017\u001A\u00020\u0018H\u0016R\u0019\u0010\b\u001A\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000B\u001A\u0004\b\t\u0010\nR\u0019\u0010\f\u001A\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000B\u001A\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\nø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001A"}, d2 = {"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-pVg5ArA", "()I", "I", "last", "getLast-pVg5ArA", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class UIntProgression implements Iterable, KMappedMarker {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000B\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/ranges/UIntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", "", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final UIntProgression fromClosedRange-Nkh28Cs(int v, int v1, int v2) {
            return new UIntProgression(v, v1, v2, null);
        }
    }

    public static final Companion Companion;
    private final int first;
    private final int last;
    private final int step;

    static {
        UIntProgression.Companion = new Companion(null);
    }

    private UIntProgression(int v, int v1, int v2) {
        switch(v2) {
            case 0x80000000: {
                throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
            }
            case 0: {
                throw new IllegalArgumentException("Step must be non-zero.");
            }
            default: {
                this.first = v;
                this.last = UProgressionUtilKt.getProgressionLastElement-Nkh28Cs(v, v1, v2);
                this.step = v2;
            }
        }
    }

    public UIntProgression(int v, int v1, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v, v1, v2);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof UIntProgression && (this.isEmpty() && ((UIntProgression)object0).isEmpty() || this.first == ((UIntProgression)object0).first && this.last == ((UIntProgression)object0).last && this.step == ((UIntProgression)object0).step);
    }

    public final int getFirst-pVg5ArA() {
        return this.first;
    }

    public final int getLast-pVg5ArA() {
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
        return this.step <= 0 ? UByte..ExternalSyntheticBackport0.m$2(this.first, this.last) < 0 : UByte..ExternalSyntheticBackport0.m$2(this.first, this.last) > 0;
    }

    @Override
    public final Iterator iterator() {
        return new UIntProgressionIterator(this.first, this.last, this.step, null);
    }

    @Override
    public String toString() {
        int v;
        StringBuilder stringBuilder0;
        if(this.step > 0) {
            stringBuilder0 = new StringBuilder();
            stringBuilder0.append(UInt.toString-impl(this.first));
            stringBuilder0.append("..");
            stringBuilder0.append(UInt.toString-impl(this.last));
            stringBuilder0.append(" step ");
            v = this.step;
        }
        else {
            stringBuilder0 = new StringBuilder();
            stringBuilder0.append(UInt.toString-impl(this.first));
            stringBuilder0.append(" downTo ");
            stringBuilder0.append(UInt.toString-impl(this.last));
            stringBuilder0.append(" step ");
            v = -this.step;
        }
        stringBuilder0.append(v);
        return stringBuilder0.toString();
    }
}

