package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\r\u0010\u0010\u001A\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0011R\u0014\u0010\b\u001A\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000B¨\u0006\u0012"}, d2 = {"Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "Landroidx/work/impl/constraints/trackers/BroadcastReceiverConstraintTracker;", "", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "intentFilter", "Landroid/content/IntentFilter;", "getIntentFilter", "()Landroid/content/IntentFilter;", "onBroadcastReceive", "", "intent", "Landroid/content/Intent;", "readSystemState", "()Ljava/lang/Boolean;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class BatteryNotLowTracker extends BroadcastReceiverConstraintTracker {
    public BatteryNotLowTracker(Context context0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        super(context0, taskExecutor0);
    }

    @Override  // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter0 = new IntentFilter();
        intentFilter0.addAction("android.intent.action.BATTERY_OKAY");
        intentFilter0.addAction("android.intent.action.BATTERY_LOW");
        return intentFilter0;
    }

    @Override  // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Intent intent0) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        if(intent0.getAction() != null) {
            Logger.get().debug("WM-BatteryNotLowTracker", "Received " + intent0.getAction());
            String s = intent0.getAction();
            if(s != null) {
                switch(s) {
                    case "android.intent.action.BATTERY_LOW": {
                        this.setState(Boolean.FALSE);
                        return;
                    }
                    case "android.intent.action.BATTERY_OKAY": {
                        this.setState(Boolean.TRUE);
                        break;
                    }
                }
            }
        }
    }

    public Boolean readSystemState() {
        IntentFilter intentFilter0 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        Intent intent0 = this.getAppContext().registerReceiver(null, intentFilter0);
        boolean z = false;
        if(intent0 == null) {
            Logger.get().error("WM-BatteryNotLowTracker", "getInitialState - null intent received");
            return false;
        }
        if(intent0.getIntExtra("status", -1) == 1 || ((float)intent0.getIntExtra("level", -1)) / ((float)intent0.getIntExtra("scale", -1)) > 0.15f) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    @Override  // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Object readSystemState() {
        return this.readSystemState();
    }
}

