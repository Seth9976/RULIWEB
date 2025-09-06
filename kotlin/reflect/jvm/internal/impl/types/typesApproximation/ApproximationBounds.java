package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.internal.Intrinsics;

public final class ApproximationBounds {
    private final Object lower;
    private final Object upper;

    public ApproximationBounds(Object object0, Object object1) {
        this.lower = object0;
        this.upper = object1;
    }

    public final Object component1() {
        return this.lower;
    }

    public final Object component2() {
        return this.upper;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof ApproximationBounds)) {
            return false;
        }
        return Intrinsics.areEqual(this.lower, ((ApproximationBounds)object0).lower) ? Intrinsics.areEqual(this.upper, ((ApproximationBounds)object0).upper) : false;
    }

    public final Object getLower() {
        return this.lower;
    }

    public final Object getUpper() {
        return this.upper;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.lower == null ? 0 : this.lower.hashCode();
        Object object0 = this.upper;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return v1 * 0x1F + v;
    }

    @Override
    public String toString() {
        return "ApproximationBounds(lower=" + this.lower + ", upper=" + this.upper + ')';
    }
}

