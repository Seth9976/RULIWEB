package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;

public class SystemAlarmScheduler implements Scheduler {
    private static final String TAG;
    private final Context mContext;

    static {
        SystemAlarmScheduler.TAG = "WM-SystemAlarmScheduler";
    }

    public SystemAlarmScheduler(Context context0) {
        this.mContext = context0.getApplicationContext();
    }

    @Override  // androidx.work.impl.Scheduler
    public void cancel(String s) {
        Intent intent0 = CommandHandler.createStopWorkIntent(this.mContext, s);
        this.mContext.startService(intent0);
    }

    @Override  // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override  // androidx.work.impl.Scheduler
    public void schedule(WorkSpec[] arr_workSpec) {
        for(int v = 0; v < arr_workSpec.length; ++v) {
            this.scheduleWorkSpec(arr_workSpec[v]);
        }
    }

    private void scheduleWorkSpec(WorkSpec workSpec0) {
        Logger.get().debug("WM-SystemAlarmScheduler", "Scheduling work with workSpecId " + workSpec0.id);
        WorkGenerationalId workGenerationalId0 = WorkSpecKt.generationalId(workSpec0);
        Intent intent0 = CommandHandler.createScheduleWorkIntent(this.mContext, workGenerationalId0);
        this.mContext.startService(intent0);
    }
}

