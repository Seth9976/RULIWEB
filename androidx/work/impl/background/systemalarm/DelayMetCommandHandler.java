package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import androidx.work.Logger;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsMet;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer.TimeLimitExceededListener;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;

public class DelayMetCommandHandler implements OnConstraintsStateChangedListener, TimeLimitExceededListener {
    private static final int STATE_INITIAL = 0;
    private static final int STATE_START_REQUESTED = 1;
    private static final int STATE_STOP_REQUESTED = 2;
    private static final String TAG;
    private final Context mContext;
    private final CoroutineDispatcher mCoroutineDispatcher;
    private int mCurrentState;
    private final SystemAlarmDispatcher mDispatcher;
    private boolean mHasConstraints;
    private volatile Job mJob;
    private final Object mLock;
    private final Executor mMainThreadExecutor;
    private final Executor mSerialExecutor;
    private final int mStartId;
    private final StartStopToken mToken;
    private PowerManager.WakeLock mWakeLock;
    private final WorkConstraintsTracker mWorkConstraintsTracker;
    private final WorkGenerationalId mWorkGenerationalId;

    // 检测为 Lambda 实现
    public static void $r8$lambda$82vXfMh9MXtN-tLNgTa3KWbb4VE(DelayMetCommandHandler delayMetCommandHandler0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$r8ATJco-vysxdAeSwS9XE6krknU(DelayMetCommandHandler delayMetCommandHandler0) [...]

    static {
        DelayMetCommandHandler.TAG = "WM-DelayMetCommandHandl";
    }

    DelayMetCommandHandler(Context context0, int v, SystemAlarmDispatcher systemAlarmDispatcher0, StartStopToken startStopToken0) {
        this.mContext = context0;
        this.mStartId = v;
        this.mDispatcher = systemAlarmDispatcher0;
        this.mWorkGenerationalId = startStopToken0.getId();
        this.mToken = startStopToken0;
        Trackers trackers0 = systemAlarmDispatcher0.getWorkManager().getTrackers();
        this.mSerialExecutor = systemAlarmDispatcher0.getTaskExecutor().getSerialTaskExecutor();
        this.mMainThreadExecutor = systemAlarmDispatcher0.getTaskExecutor().getMainThreadExecutor();
        this.mCoroutineDispatcher = systemAlarmDispatcher0.getTaskExecutor().getTaskCoroutineDispatcher();
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(trackers0);
        this.mHasConstraints = false;
        this.mCurrentState = 0;
        this.mLock = new Object();
    }

    private void cleanUp() {
        synchronized(this.mLock) {
            if(this.mJob != null) {
                this.mJob.cancel(null);
            }
            this.mDispatcher.getWorkTimer().stopTimer(this.mWorkGenerationalId);
            if(this.mWakeLock != null && this.mWakeLock.isHeld()) {
                Logger.get().debug("WM-DelayMetCommandHandl", "Releasing wakelock " + this.mWakeLock + "for WorkSpec " + this.mWorkGenerationalId);
                this.mWakeLock.release();
            }
        }
    }

    void handleProcessWork() {
        String s = this.mWorkGenerationalId.getWorkSpecId();
        this.mWakeLock = WakeLocks.newWakeLock(this.mContext, s + " (" + this.mStartId + ")");
        Logger.get().debug("WM-DelayMetCommandHandl", "Acquiring wakelock " + this.mWakeLock + "for WorkSpec " + s);
        this.mWakeLock.acquire();
        WorkSpec workSpec0 = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(s);
        if(workSpec0 == null) {
            DelayMetCommandHandler..ExternalSyntheticLambda0 delayMetCommandHandler$$ExternalSyntheticLambda00 = () -> this.stopWork();
            this.mSerialExecutor.execute(delayMetCommandHandler$$ExternalSyntheticLambda00);
            return;
        }
        boolean z = workSpec0.hasConstraints();
        this.mHasConstraints = z;
        if(!z) {
            Logger.get().debug("WM-DelayMetCommandHandl", "No constraints for " + s);
            DelayMetCommandHandler..ExternalSyntheticLambda1 delayMetCommandHandler$$ExternalSyntheticLambda10 = () -> this.startWork();
            this.mSerialExecutor.execute(delayMetCommandHandler$$ExternalSyntheticLambda10);
            return;
        }
        this.mJob = WorkConstraintsTrackerKt.listen(this.mWorkConstraintsTracker, workSpec0, this.mCoroutineDispatcher, this);
    }

    @Override  // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public void onConstraintsStateChanged(WorkSpec workSpec0, ConstraintsState constraintsState0) {
        if(constraintsState0 instanceof ConstraintsMet) {
            DelayMetCommandHandler..ExternalSyntheticLambda1 delayMetCommandHandler$$ExternalSyntheticLambda10 = () -> this.startWork();
            this.mSerialExecutor.execute(delayMetCommandHandler$$ExternalSyntheticLambda10);
            return;
        }
        DelayMetCommandHandler..ExternalSyntheticLambda0 delayMetCommandHandler$$ExternalSyntheticLambda00 = () -> this.stopWork();
        this.mSerialExecutor.execute(delayMetCommandHandler$$ExternalSyntheticLambda00);
    }

    void onExecuted(boolean z) {
        Logger.get().debug("WM-DelayMetCommandHandl", "onExecuted " + this.mWorkGenerationalId + ", " + z);
        this.cleanUp();
        if(z) {
            Intent intent0 = CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId);
            AddRunnable systemAlarmDispatcher$AddRunnable0 = new AddRunnable(this.mDispatcher, intent0, this.mStartId);
            this.mMainThreadExecutor.execute(systemAlarmDispatcher$AddRunnable0);
        }
        if(this.mHasConstraints) {
            Intent intent1 = CommandHandler.createConstraintsChangedIntent(this.mContext);
            AddRunnable systemAlarmDispatcher$AddRunnable1 = new AddRunnable(this.mDispatcher, intent1, this.mStartId);
            this.mMainThreadExecutor.execute(systemAlarmDispatcher$AddRunnable1);
        }
    }

    @Override  // androidx.work.impl.utils.WorkTimer$TimeLimitExceededListener
    public void onTimeLimitExceeded(WorkGenerationalId workGenerationalId0) {
        Logger.get().debug("WM-DelayMetCommandHandl", "Exceeded time limits on execution for " + workGenerationalId0);
        DelayMetCommandHandler..ExternalSyntheticLambda0 delayMetCommandHandler$$ExternalSyntheticLambda00 = () -> this.stopWork();
        this.mSerialExecutor.execute(delayMetCommandHandler$$ExternalSyntheticLambda00);
    }

    private void startWork() {
        if(this.mCurrentState == 0) {
            this.mCurrentState = 1;
            Logger.get().debug("WM-DelayMetCommandHandl", "onAllConstraintsMet for " + this.mWorkGenerationalId);
            if(this.mDispatcher.getProcessor().startWork(this.mToken)) {
                this.mDispatcher.getWorkTimer().startTimer(this.mWorkGenerationalId, 600000L, this);
                return;
            }
            this.cleanUp();
            return;
        }
        Logger.get().debug("WM-DelayMetCommandHandl", "Already started work for " + this.mWorkGenerationalId);
    }

    private void stopWork() {
        String s = this.mWorkGenerationalId.getWorkSpecId();
        if(this.mCurrentState < 2) {
            this.mCurrentState = 2;
            Logger.get().debug("WM-DelayMetCommandHandl", "Stopping work for WorkSpec " + s);
            Intent intent0 = CommandHandler.createStopWorkIntent(this.mContext, this.mWorkGenerationalId);
            AddRunnable systemAlarmDispatcher$AddRunnable0 = new AddRunnable(this.mDispatcher, intent0, this.mStartId);
            this.mMainThreadExecutor.execute(systemAlarmDispatcher$AddRunnable0);
            if(this.mDispatcher.getProcessor().isEnqueued(this.mWorkGenerationalId.getWorkSpecId())) {
                Logger.get().debug("WM-DelayMetCommandHandl", "WorkSpec " + s + " needs to be rescheduled");
                Intent intent1 = CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId);
                AddRunnable systemAlarmDispatcher$AddRunnable1 = new AddRunnable(this.mDispatcher, intent1, this.mStartId);
                this.mMainThreadExecutor.execute(systemAlarmDispatcher$AddRunnable1);
                return;
            }
            Logger.get().debug("WM-DelayMetCommandHandl", "Processor does not have WorkSpec " + s + ". No need to reschedule");
            return;
        }
        Logger.get().debug("WM-DelayMetCommandHandl", "Already stopped work for " + s);
    }
}

