package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H&J\b\u0010\u0012\u001A\u00020\u000FH\u0016J\b\u0010\u0013\u001A\u00020\u000FH\u0016R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001A\u00020\u000BX¦\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/constraints/trackers/BroadcastReceiverConstraintTracker;", "T", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "intentFilter", "Landroid/content/IntentFilter;", "getIntentFilter", "()Landroid/content/IntentFilter;", "onBroadcastReceive", "", "intent", "Landroid/content/Intent;", "startTracking", "stopTracking", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class BroadcastReceiverConstraintTracker extends ConstraintTracker {
    private final BroadcastReceiver broadcastReceiver;

    public BroadcastReceiverConstraintTracker(Context context0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        super(context0, taskExecutor0);
        this.broadcastReceiver = new BroadcastReceiver() {
            @Override  // android.content.BroadcastReceiver
            public void onReceive(Context context0, Intent intent0) {
                Intrinsics.checkNotNullParameter(context0, "context");
                Intrinsics.checkNotNullParameter(intent0, "intent");
                BroadcastReceiverConstraintTracker.this.onBroadcastReceive(intent0);
            }
        };
    }

    public abstract IntentFilter getIntentFilter();

    public abstract void onBroadcastReceive(Intent arg1);

    @Override  // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void startTracking() {
        Logger.get().debug("WM-BrdcstRcvrCnstrntTrc", this.getClass().getSimpleName() + ": registering receiver");
        Context context0 = this.getAppContext();
        IntentFilter intentFilter0 = this.getIntentFilter();
        context0.registerReceiver(this.broadcastReceiver, intentFilter0);
    }

    @Override  // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void stopTracking() {
        Logger.get().debug("WM-BrdcstRcvrCnstrntTrc", this.getClass().getSimpleName() + ": unregistering receiver");
        this.getAppContext().unregisterReceiver(this.broadcastReceiver);
    }
}

