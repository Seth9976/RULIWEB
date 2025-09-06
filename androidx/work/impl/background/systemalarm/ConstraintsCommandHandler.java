package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import java.util.ArrayList;
import java.util.List;

class ConstraintsCommandHandler {
    private static final String TAG;
    private final Clock mClock;
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private final int mStartId;
    private final WorkConstraintsTracker mWorkConstraintsTracker;

    static {
        ConstraintsCommandHandler.TAG = "WM-ConstraintsCmdHandle";
    }

    ConstraintsCommandHandler(Context context0, Clock clock0, int v, SystemAlarmDispatcher systemAlarmDispatcher0) {
        this.mContext = context0;
        this.mClock = clock0;
        this.mStartId = v;
        this.mDispatcher = systemAlarmDispatcher0;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(systemAlarmDispatcher0.getWorkManager().getTrackers());
    }

    void handleConstraintsChanged() {
        List list0 = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.updateAll(this.mContext, list0);
        ArrayList arrayList0 = new ArrayList(list0.size());
        long v = this.mClock.currentTimeMillis();
        for(Object object0: list0) {
            WorkSpec workSpec0 = (WorkSpec)object0;
            if(v >= workSpec0.calculateNextRunTime() && (!workSpec0.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(workSpec0))) {
                arrayList0.add(workSpec0);
            }
        }
        for(Object object1: arrayList0) {
            WorkGenerationalId workGenerationalId0 = WorkSpecKt.generationalId(((WorkSpec)object1));
            Intent intent0 = CommandHandler.createDelayMetIntent(this.mContext, workGenerationalId0);
            Logger.get().debug("WM-ConstraintsCmdHandle", "Creating a delay_met command for workSpec with id (" + ((WorkSpec)object1).id + ")");
            this.mDispatcher.getTaskExecutor().getMainThreadExecutor().execute(new AddRunnable(this.mDispatcher, intent0, this.mStartId));
        }
    }
}

