package androidx.work.impl.background.greedy;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.WorkInfo.State;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens.-CC;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkLauncher;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsMet;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsNotMet;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.ProcessUtils;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;

public class GreedyScheduler implements ExecutionListener, Scheduler, OnConstraintsStateChangedListener {
    static class AttemptData {
        final int mRunAttemptCount;
        final long mTimeStamp;

        private AttemptData(int v, long v1) {
            this.mRunAttemptCount = v;
            this.mTimeStamp = v1;
        }

        AttemptData(int v, long v1, androidx.work.impl.background.greedy.GreedyScheduler.1 greedyScheduler$10) {
            this(v, v1);
        }
    }

    private static final int NON_THROTTLE_RUN_ATTEMPT_COUNT = 5;
    private static final String TAG;
    private final Configuration mConfiguration;
    private final Map mConstrainedWorkSpecs;
    private final WorkConstraintsTracker mConstraintsTracker;
    private final Context mContext;
    private DelayedWorkTracker mDelayedWorkTracker;
    private final Map mFirstRunAttempts;
    Boolean mInDefaultProcess;
    private final Object mLock;
    private final Processor mProcessor;
    private boolean mRegisteredExecutionListener;
    private final StartStopTokens mStartStopTokens;
    private final TaskExecutor mTaskExecutor;
    private final TimeLimiter mTimeLimiter;
    private final WorkLauncher mWorkLauncher;

    static {
        GreedyScheduler.TAG = "WM-GreedyScheduler";
    }

    public GreedyScheduler(Context context0, Configuration configuration0, Trackers trackers0, Processor processor0, WorkLauncher workLauncher0, TaskExecutor taskExecutor0) {
        this.mConstrainedWorkSpecs = new HashMap();
        this.mLock = new Object();
        this.mStartStopTokens = StartStopTokens.-CC.create();
        this.mFirstRunAttempts = new HashMap();
        this.mContext = context0;
        RunnableScheduler runnableScheduler0 = configuration0.getRunnableScheduler();
        this.mDelayedWorkTracker = new DelayedWorkTracker(this, runnableScheduler0, configuration0.getClock());
        this.mTimeLimiter = new TimeLimiter(runnableScheduler0, workLauncher0);
        this.mTaskExecutor = taskExecutor0;
        this.mConstraintsTracker = new WorkConstraintsTracker(trackers0);
        this.mConfiguration = configuration0;
        this.mProcessor = processor0;
        this.mWorkLauncher = workLauncher0;
    }

    @Override  // androidx.work.impl.Scheduler
    public void cancel(String s) {
        if(this.mInDefaultProcess == null) {
            this.checkDefaultProcess();
        }
        if(!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info("WM-GreedyScheduler", "Ignoring schedule request in non-main process");
            return;
        }
        this.registerExecutionListenerIfNeeded();
        Logger.get().debug("WM-GreedyScheduler", "Cancelling work ID " + s);
        DelayedWorkTracker delayedWorkTracker0 = this.mDelayedWorkTracker;
        if(delayedWorkTracker0 != null) {
            delayedWorkTracker0.unschedule(s);
        }
        for(Object object0: this.mStartStopTokens.remove(s)) {
            this.mTimeLimiter.cancel(((StartStopToken)object0));
            this.mWorkLauncher.stopWork(((StartStopToken)object0));
        }
    }

    private void checkDefaultProcess() {
        this.mInDefaultProcess = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.mContext, this.mConfiguration));
    }

    @Override  // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return false;
    }

    @Override  // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public void onConstraintsStateChanged(WorkSpec workSpec0, ConstraintsState constraintsState0) {
        WorkGenerationalId workGenerationalId0 = WorkSpecKt.generationalId(workSpec0);
        if(!(constraintsState0 instanceof ConstraintsMet)) {
            Logger.get().debug("WM-GreedyScheduler", "Constraints not met: Cancelling work ID " + workGenerationalId0);
            StartStopToken startStopToken1 = this.mStartStopTokens.remove(workGenerationalId0);
            if(startStopToken1 != null) {
                this.mTimeLimiter.cancel(startStopToken1);
                int v = ((ConstraintsNotMet)constraintsState0).getReason();
                this.mWorkLauncher.stopWorkWithReason(startStopToken1, v);
            }
        }
        else if(!this.mStartStopTokens.contains(workGenerationalId0)) {
            Logger.get().debug("WM-GreedyScheduler", "Constraints met: Scheduling work ID " + workGenerationalId0);
            StartStopToken startStopToken0 = this.mStartStopTokens.tokenFor(workGenerationalId0);
            this.mTimeLimiter.track(startStopToken0);
            this.mWorkLauncher.startWork(startStopToken0);
        }
    }

    @Override  // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        StartStopToken startStopToken0 = this.mStartStopTokens.remove(workGenerationalId0);
        if(startStopToken0 != null) {
            this.mTimeLimiter.cancel(startStopToken0);
        }
        this.removeConstraintTrackingFor(workGenerationalId0);
        if(!z) {
            synchronized(this.mLock) {
                this.mFirstRunAttempts.remove(workGenerationalId0);
            }
        }
    }

    private void registerExecutionListenerIfNeeded() {
        if(!this.mRegisteredExecutionListener) {
            this.mProcessor.addExecutionListener(this);
            this.mRegisteredExecutionListener = true;
        }
    }

    private void removeConstraintTrackingFor(WorkGenerationalId workGenerationalId0) {
        Job job0;
        synchronized(this.mLock) {
            job0 = (Job)this.mConstrainedWorkSpecs.remove(workGenerationalId0);
        }
        if(job0 != null) {
            Logger.get().debug("WM-GreedyScheduler", "Stopping tracking for " + workGenerationalId0);
            job0.cancel(null);
        }
    }

    @Override  // androidx.work.impl.Scheduler
    public void schedule(WorkSpec[] arr_workSpec) {
        if(this.mInDefaultProcess == null) {
            this.checkDefaultProcess();
        }
        if(!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info("WM-GreedyScheduler", "Ignoring schedule request in a secondary process");
            return;
        }
        this.registerExecutionListenerIfNeeded();
        HashSet hashSet0 = new HashSet();
        HashSet hashSet1 = new HashSet();
        for(int v = 0; v < arr_workSpec.length; ++v) {
            WorkSpec workSpec0 = arr_workSpec[v];
            WorkGenerationalId workGenerationalId0 = WorkSpecKt.generationalId(workSpec0);
            if(!this.mStartStopTokens.contains(workGenerationalId0)) {
                long v1 = this.throttleIfNeeded(workSpec0);
                long v2 = Math.max(workSpec0.calculateNextRunTime(), v1);
                long v3 = this.mConfiguration.getClock().currentTimeMillis();
                if(workSpec0.state == State.ENQUEUED) {
                    if(v3 < v2) {
                        DelayedWorkTracker delayedWorkTracker0 = this.mDelayedWorkTracker;
                        if(delayedWorkTracker0 != null) {
                            delayedWorkTracker0.schedule(workSpec0, v2);
                        }
                    }
                    else if(workSpec0.hasConstraints()) {
                        Constraints constraints0 = workSpec0.constraints;
                        if(Build.VERSION.SDK_INT >= 23 && constraints0.requiresDeviceIdle()) {
                            Logger.get().debug("WM-GreedyScheduler", "Ignoring " + workSpec0 + ". Requires device idle.");
                        }
                        else if(Build.VERSION.SDK_INT < 24 || !constraints0.hasContentUriTriggers()) {
                            hashSet0.add(workSpec0);
                            hashSet1.add(workSpec0.id);
                        }
                        else {
                            Logger.get().debug("WM-GreedyScheduler", "Ignoring " + workSpec0 + ". Requires ContentUri triggers.");
                        }
                    }
                    else {
                        WorkGenerationalId workGenerationalId1 = WorkSpecKt.generationalId(workSpec0);
                        if(!this.mStartStopTokens.contains(workGenerationalId1)) {
                            Logger.get().debug("WM-GreedyScheduler", "Starting work for " + workSpec0.id);
                            StartStopToken startStopToken0 = this.mStartStopTokens.tokenFor(workSpec0);
                            this.mTimeLimiter.track(startStopToken0);
                            this.mWorkLauncher.startWork(startStopToken0);
                        }
                    }
                }
            }
        }
        synchronized(this.mLock) {
            if(!hashSet0.isEmpty()) {
                String s = TextUtils.join(",", hashSet1);
                Logger.get().debug("WM-GreedyScheduler", "Starting tracking for " + s);
                for(Object object1: hashSet0) {
                    WorkSpec workSpec1 = (WorkSpec)object1;
                    WorkGenerationalId workGenerationalId2 = WorkSpecKt.generationalId(workSpec1);
                    if(!this.mConstrainedWorkSpecs.containsKey(workGenerationalId2)) {
                        CoroutineDispatcher coroutineDispatcher0 = this.mTaskExecutor.getTaskCoroutineDispatcher();
                        Job job0 = WorkConstraintsTrackerKt.listen(this.mConstraintsTracker, workSpec1, coroutineDispatcher0, this);
                        this.mConstrainedWorkSpecs.put(workGenerationalId2, job0);
                    }
                }
            }
        }
    }

    public void setDelayedWorkTracker(DelayedWorkTracker delayedWorkTracker0) {
        this.mDelayedWorkTracker = delayedWorkTracker0;
    }

    private long throttleIfNeeded(WorkSpec workSpec0) {
        AttemptData greedyScheduler$AttemptData0;
        synchronized(this.mLock) {
            WorkGenerationalId workGenerationalId0 = WorkSpecKt.generationalId(workSpec0);
            greedyScheduler$AttemptData0 = (AttemptData)this.mFirstRunAttempts.get(workGenerationalId0);
            if(greedyScheduler$AttemptData0 == null) {
                greedyScheduler$AttemptData0 = new AttemptData(workSpec0.runAttemptCount, this.mConfiguration.getClock().currentTimeMillis(), null);
                this.mFirstRunAttempts.put(workGenerationalId0, greedyScheduler$AttemptData0);
            }
        }
        return greedyScheduler$AttemptData0.mTimeStamp + ((long)Math.max(workSpec0.runAttemptCount - greedyScheduler$AttemptData0.mRunAttemptCount - 5, 0)) * 30000L;
    }

    class androidx.work.impl.background.greedy.GreedyScheduler.1 {
    }

}

