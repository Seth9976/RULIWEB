package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u001F\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0002\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001A\u00020\u0006H\u0016J\b\u0010\u0014\u001A\u00020\u0010H\u0016J\t\u0010\u0015\u001A\u00020\u0016H\u0096\u0002J\b\u0010\u0017\u001A\u00020\u0018H\u0016R\u0011\u0010\b\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u000B\u001A\u00020\u0002¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u001A"}, d2 = {"Lkotlin/ranges/CharProgression;", "", "", "start", "endInclusive", "step", "", "(CCI)V", "first", "getFirst", "()C", "last", "getLast", "getStep", "()I", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/CharIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class CharProgression implements Iterable, KMappedMarker {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\t¨\u0006\n"}, d2 = {"Lkotlin/ranges/CharProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/CharProgression;", "rangeStart", "", "rangeEnd", "step", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final CharProgression fromClosedRange(char c, char c1, int v) {
            return new CharProgression(c, c1, v);
        }
    }

    public static final Companion Companion;
    private final char first;
    private final char last;
    private final int step;

    static {
        CharProgression.Companion = new Companion(null);
    }

    public CharProgression(char c, char c1, int v) {
        switch(v) {
            case 0x80000000: {
                throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
            }
            case 0: {
                throw new IllegalArgumentException("Step must be non-zero.");
            }
            default: {
                this.first = c;
                this.last = (char)ProgressionUtilKt.getProgressionLastElement(c, c1, v);
                this.step = v;
            }
        }
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof CharProgression && (this.isEmpty() && ((CharProgression)object0).isEmpty() || this.first == ((CharProgression)object0).first && this.last == ((CharProgression)object0).last && this.step == ((CharProgression)object0).step);
    }

    public final char getFirst() {
        return this.first;
    }

    public final char getLast() {
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
        return this.step <= 0 ? Intrinsics.compare(this.first, this.last) < 0 : Intrinsics.compare(this.first, this.last) > 0;
    }

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    public CharIterator iterator() {
        return new CharProgressionIterator(this.first, this.last, this.step);
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

