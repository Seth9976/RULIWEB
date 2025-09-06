package androidx.work.impl.constraints;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/constraints/ConstraintsState;", "", "()V", "ConstraintsMet", "ConstraintsNotMet", "Landroidx/work/impl/constraints/ConstraintsState$ConstraintsMet;", "Landroidx/work/impl/constraints/ConstraintsState$ConstraintsNotMet;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ConstraintsState {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/work/impl/constraints/ConstraintsState$ConstraintsMet;", "Landroidx/work/impl/constraints/ConstraintsState;", "()V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ConstraintsMet extends ConstraintsState {
        public static final ConstraintsMet INSTANCE;

        static {
            ConstraintsMet.INSTANCE = new ConstraintsMet();
        }

        private ConstraintsMet() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001A\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001A\u00020\n2\b\u0010\u000B\u001A\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001A\u00020\u0003HÖ\u0001J\t\u0010\u000E\u001A\u00020\u000FHÖ\u0001R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/constraints/ConstraintsState$ConstraintsNotMet;", "Landroidx/work/impl/constraints/ConstraintsState;", "reason", "", "(I)V", "getReason", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ConstraintsNotMet extends ConstraintsState {
        private final int reason;

        public ConstraintsNotMet(int v) {
            super(null);
            this.reason = v;
        }

        public final int component1() {
            return this.reason;
        }

        public final ConstraintsNotMet copy(int v) {
            return new ConstraintsNotMet(v);
        }

        public static ConstraintsNotMet copy$default(ConstraintsNotMet constraintsState$ConstraintsNotMet0, int v, int v1, Object object0) {
            if((v1 & 1) != 0) {
                v = constraintsState$ConstraintsNotMet0.reason;
            }
            return constraintsState$ConstraintsNotMet0.copy(v);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof ConstraintsNotMet ? this.reason == ((ConstraintsNotMet)object0).reason : false;
        }

        public final int getReason() {
            return this.reason;
        }

        @Override
        public int hashCode() {
            return this.reason;
        }

        @Override
        public String toString() {
            return "ConstraintsNotMet(reason=" + this.reason + ')';
        }
    }

    private ConstraintsState() {
    }

    public ConstraintsState(DefaultConstructorMarker defaultConstructorMarker0) {
    }
}

