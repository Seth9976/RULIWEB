package androidx.work.impl.constraints.trackers;

import java.util.List;

public final class ConstraintTracker..ExternalSyntheticLambda0 implements Runnable {
    public final List f$0;
    public final ConstraintTracker f$1;

    public ConstraintTracker..ExternalSyntheticLambda0(List list0, ConstraintTracker constraintTracker0) {
        this.f$0 = list0;
        this.f$1 = constraintTracker0;
    }

    @Override
    public final void run() {
        ConstraintTracker._set_state_$lambda$4$lambda$3(this.f$0, this.f$1);
    }
}

