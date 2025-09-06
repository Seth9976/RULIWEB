package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001A\u00020\u00022\u0006\u0010\u000B\u001A\u00020\fH\u0016J\u0010\u0010\r\u001A\u00020\u00022\u0006\u0010\u000E\u001A\u00020\u0002H\u0014R\u0014\u0010\u0006\u001A\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u000F"}, d2 = {"Landroidx/work/impl/constraints/controllers/StorageNotLowController;", "Landroidx/work/impl/constraints/controllers/BaseConstraintController;", "", "tracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "(Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "reason", "", "getReason", "()I", "hasConstraint", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "isConstrained", "value", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class StorageNotLowController extends BaseConstraintController {
    private final int reason;

    public StorageNotLowController(ConstraintTracker constraintTracker0) {
        Intrinsics.checkNotNullParameter(constraintTracker0, "tracker");
        super(constraintTracker0);
        this.reason = 9;
    }

    @Override  // androidx.work.impl.constraints.controllers.BaseConstraintController
    protected int getReason() {
        return this.reason;
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        return workSpec0.constraints.requiresStorageNotLow();
    }

    @Override  // androidx.work.impl.constraints.controllers.BaseConstraintController
    public boolean isConstrained(Object object0) {
        return this.isConstrained(((Boolean)object0).booleanValue());
    }

    protected boolean isConstrained(boolean z) {
        return !z;
    }
}

