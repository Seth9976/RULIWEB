package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\b\u0007\u0018\u00002\u00020\u0001BQ\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u000E\b\u0002\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\u000E\b\u0002\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u000E\b\u0002\u0010\r\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\u000ER\u0017\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\t\u001A\u00020\n¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0010R\u0017\u0010\r\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u0010¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/constraints/trackers/Trackers;", "", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "batteryChargingTracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "", "batteryNotLowTracker", "Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "networkStateTracker", "Landroidx/work/impl/constraints/NetworkState;", "storageNotLowTracker", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/constraints/trackers/ConstraintTracker;Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;Landroidx/work/impl/constraints/trackers/ConstraintTracker;Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "getBatteryChargingTracker", "()Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "getBatteryNotLowTracker", "()Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "getContext", "()Landroid/content/Context;", "getNetworkStateTracker", "getStorageNotLowTracker", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Trackers {
    private final ConstraintTracker batteryChargingTracker;
    private final BatteryNotLowTracker batteryNotLowTracker;
    private final Context context;
    private final ConstraintTracker networkStateTracker;
    private final ConstraintTracker storageNotLowTracker;

    public Trackers(Context context0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        this(context0, taskExecutor0, null, null, null, null, 60, null);
    }

    public Trackers(Context context0, TaskExecutor taskExecutor0, ConstraintTracker constraintTracker0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        Intrinsics.checkNotNullParameter(constraintTracker0, "batteryChargingTracker");
        this(context0, taskExecutor0, constraintTracker0, null, null, null, 56, null);
    }

    public Trackers(Context context0, TaskExecutor taskExecutor0, ConstraintTracker constraintTracker0, BatteryNotLowTracker batteryNotLowTracker0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        Intrinsics.checkNotNullParameter(constraintTracker0, "batteryChargingTracker");
        Intrinsics.checkNotNullParameter(batteryNotLowTracker0, "batteryNotLowTracker");
        this(context0, taskExecutor0, constraintTracker0, batteryNotLowTracker0, null, null, 0x30, null);
    }

    public Trackers(Context context0, TaskExecutor taskExecutor0, ConstraintTracker constraintTracker0, BatteryNotLowTracker batteryNotLowTracker0, ConstraintTracker constraintTracker1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        Intrinsics.checkNotNullParameter(constraintTracker0, "batteryChargingTracker");
        Intrinsics.checkNotNullParameter(batteryNotLowTracker0, "batteryNotLowTracker");
        Intrinsics.checkNotNullParameter(constraintTracker1, "networkStateTracker");
        this(context0, taskExecutor0, constraintTracker0, batteryNotLowTracker0, constraintTracker1, null, 0x20, null);
    }

    public Trackers(Context context0, TaskExecutor taskExecutor0, ConstraintTracker constraintTracker0, BatteryNotLowTracker batteryNotLowTracker0, ConstraintTracker constraintTracker1, ConstraintTracker constraintTracker2) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        Intrinsics.checkNotNullParameter(constraintTracker0, "batteryChargingTracker");
        Intrinsics.checkNotNullParameter(batteryNotLowTracker0, "batteryNotLowTracker");
        Intrinsics.checkNotNullParameter(constraintTracker1, "networkStateTracker");
        Intrinsics.checkNotNullParameter(constraintTracker2, "storageNotLowTracker");
        super();
        this.context = context0;
        this.batteryChargingTracker = constraintTracker0;
        this.batteryNotLowTracker = batteryNotLowTracker0;
        this.networkStateTracker = constraintTracker1;
        this.storageNotLowTracker = constraintTracker2;
    }

    public Trackers(Context context0, TaskExecutor taskExecutor0, ConstraintTracker constraintTracker0, BatteryNotLowTracker batteryNotLowTracker0, ConstraintTracker constraintTracker1, ConstraintTracker constraintTracker2, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        ConstraintTracker constraintTracker5;
        ConstraintTracker constraintTracker4;
        BatteryNotLowTracker batteryNotLowTracker1;
        ConstraintTracker constraintTracker3;
        if((v & 4) == 0) {
            constraintTracker3 = constraintTracker0;
        }
        else {
            Context context1 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context1, "context.applicationContext");
            constraintTracker3 = new BatteryChargingTracker(context1, taskExecutor0);
        }
        if((v & 8) == 0) {
            batteryNotLowTracker1 = batteryNotLowTracker0;
        }
        else {
            Context context2 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context.applicationContext");
            batteryNotLowTracker1 = new BatteryNotLowTracker(context2, taskExecutor0);
        }
        if((v & 16) == 0) {
            constraintTracker4 = constraintTracker1;
        }
        else {
            Context context3 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context3, "context.applicationContext");
            constraintTracker4 = NetworkStateTrackerKt.NetworkStateTracker(context3, taskExecutor0);
        }
        if((v & 0x20) == 0) {
            constraintTracker5 = constraintTracker2;
        }
        else {
            Context context4 = context0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(context4, "context.applicationContext");
            constraintTracker5 = new StorageNotLowTracker(context4, taskExecutor0);
        }
        this(context0, taskExecutor0, constraintTracker3, batteryNotLowTracker1, constraintTracker4, constraintTracker5);
    }

    public final ConstraintTracker getBatteryChargingTracker() {
        return this.batteryChargingTracker;
    }

    public final BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.batteryNotLowTracker;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ConstraintTracker getNetworkStateTracker() {
        return this.networkStateTracker;
    }

    public final ConstraintTracker getStorageNotLowTracker() {
        return this.storageNotLowTracker;
    }
}

