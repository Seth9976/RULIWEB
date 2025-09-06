package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0018\u0012\u0006\u0010\u0003\u001A\u00028\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u000E\u0010\r\u001A\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000BJ\u0016\u0010\u000E\u001A\u00020\u0005HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000F\u0010\bJ-\u0010\u0010\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001A\u00028\u00002\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001A\u00020\u00142\b\u0010\u0015\u001A\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0016\u001A\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001A\u00020\u0019HÖ\u0001R\u0019\u0010\u0004\u001A\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\u0007\u0010\bR\u0013\u0010\u0003\u001A\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\n\u0010\u000B\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001A"}, d2 = {"Lkotlin/time/TimedValue;", "T", "", "value", "duration", "Lkotlin/time/Duration;", "(Ljava/lang/Object;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDuration-UwyO8pc", "()J", "J", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "component2-UwyO8pc", "copy", "copy-RFiDyg4", "(Ljava/lang/Object;J)Lkotlin/time/TimedValue;", "equals", "", "other", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TimedValue {
    private final long duration;
    private final Object value;

    private TimedValue(Object object0, long v) {
        this.value = object0;
        this.duration = v;
    }

    public TimedValue(Object object0, long v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(object0, v);
    }

    public final Object component1() {
        return this.value;
    }

    public final long component2-UwyO8pc() {
        return this.duration;
    }

    public final TimedValue copy-RFiDyg4(Object object0, long v) {
        return new TimedValue(object0, v, null);
    }

    public static TimedValue copy-RFiDyg4$default(TimedValue timedValue0, Object object0, long v, int v1, Object object1) {
        if((v1 & 1) != 0) {
            object0 = timedValue0.value;
        }
        if((v1 & 2) != 0) {
            v = timedValue0.duration;
        }
        return timedValue0.copy-RFiDyg4(object0, v);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof TimedValue)) {
            return false;
        }
        return Intrinsics.areEqual(this.value, ((TimedValue)object0).value) ? Duration.equals-impl0(this.duration, ((TimedValue)object0).duration) : false;
    }

    public final long getDuration-UwyO8pc() {
        return this.duration;
    }

    public final Object getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return this.value == null ? Duration.hashCode-impl(this.duration) : this.value.hashCode() * 0x1F + Duration.hashCode-impl(this.duration);
    }

    @Override
    public String toString() {
        return "TimedValue(value=" + this.value + ", duration=" + Duration.toString-impl(this.duration) + ')';
    }
}

