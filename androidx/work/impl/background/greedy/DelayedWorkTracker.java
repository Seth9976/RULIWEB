package androidx.work.impl.background.greedy;

import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

public class DelayedWorkTracker {
    static final String TAG;
    private final Clock mClock;
    final Scheduler mImmediateScheduler;
    private final RunnableScheduler mRunnableScheduler;
    private final Map mRunnables;

    static {
        DelayedWorkTracker.TAG = "WM-DelayedWorkTracker";
    }

    public DelayedWorkTracker(Scheduler scheduler0, RunnableScheduler runnableScheduler0, Clock clock0) {
        this.mImmediateScheduler = scheduler0;
        this.mRunnableScheduler = runnableScheduler0;
        this.mClock = clock0;
        this.mRunnables = new HashMap();
    }

    public void schedule(WorkSpec workSpec0, long v) {
        Runnable runnable0 = (Runnable)this.mRunnables.remove(workSpec0.id);
        if(runnable0 != null) {
            this.mRunnableScheduler.cancel(runnable0);
        }
        androidx.work.impl.background.greedy.DelayedWorkTracker.1 delayedWorkTracker$10 = new Runnable() {
            @Override
            public void run() {
                Logger.get().debug("WM-DelayedWorkTracker", "Scheduling work " + workSpec0.id);
                DelayedWorkTracker.this.mImmediateScheduler.schedule(new WorkSpec[]{workSpec0});
            }
        };
        this.mRunnables.put(workSpec0.id, delayedWorkTracker$10);
        long v1 = this.mClock.currentTimeMillis();
        this.mRunnableScheduler.scheduleWithDelay(v - v1, delayedWorkTracker$10);
    }

    public void unschedule(String s) {
        Runnable runnable0 = (Runnable)this.mRunnables.remove(s);
        if(runnable0 != null) {
            this.mRunnableScheduler.cancel(runnable0);
        }
    }
}

