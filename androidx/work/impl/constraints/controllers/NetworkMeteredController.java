package androidx.work.impl.constraints.controllers;

import android.os.Build.VERSION;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016J\u0010\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u0002H\u0014R\u0014\u0010\u0006\u001A\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Landroidx/work/impl/constraints/controllers/NetworkMeteredController;", "Landroidx/work/impl/constraints/controllers/BaseConstraintController;", "Landroidx/work/impl/constraints/NetworkState;", "tracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "(Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "reason", "", "getReason", "()I", "hasConstraint", "", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "isConstrained", "value", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkMeteredController extends BaseConstraintController {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/work/impl/constraints/controllers/NetworkMeteredController$Companion;", "", "()V", "TAG", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private static final String TAG;
    private final int reason;

    static {
        NetworkMeteredController.Companion = new Companion(null);
        Intrinsics.checkNotNullExpressionValue("WM-NetworkMeteredCtrlr", "tagWithPrefix(\"NetworkMeteredCtrlr\")");
        NetworkMeteredController.TAG = "WM-NetworkMeteredCtrlr";
    }

    public NetworkMeteredController(ConstraintTracker constraintTracker0) {
        Intrinsics.checkNotNullParameter(constraintTracker0, "tracker");
        super(constraintTracker0);
        this.reason = 7;
    }

    @Override  // androidx.work.impl.constraints.controllers.BaseConstraintController
    protected int getReason() {
        return this.reason;
    }

    @Override  // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        return workSpec0.constraints.getRequiredNetworkType() == NetworkType.METERED;
    }

    protected boolean isConstrained(NetworkState networkState0) {
        Intrinsics.checkNotNullParameter(networkState0, "value");
        if(Build.VERSION.SDK_INT < 26) {
            Logger.get().debug("WM-NetworkMeteredCtrlr", "Metered network constraint is not supported before API 26, only checking for connected state.");
            return !networkState0.isConnected();
        }
        return !networkState0.isConnected() || !networkState0.isMetered();
    }

    @Override  // androidx.work.impl.constraints.controllers.BaseConstraintController
    public boolean isConstrained(Object object0) {
        return this.isConstrained(((NetworkState)object0));
    }
}

