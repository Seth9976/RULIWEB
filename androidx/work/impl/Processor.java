package androidx.work.impl;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters.RuntimeExtras;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Processor implements ForegroundProcessor {
    private static final String FOREGROUND_WAKELOCK_TAG = "ProcessorForegroundLck";
    private static final String TAG;
    private Context mAppContext;
    private Set mCancelledIds;
    private Configuration mConfiguration;
    private Map mEnqueuedWorkMap;
    private PowerManager.WakeLock mForegroundLock;
    private Map mForegroundWorkMap;
    private final Object mLock;
    private final List mOuterListeners;
    private WorkDatabase mWorkDatabase;
    private Map mWorkRuns;
    private TaskExecutor mWorkTaskExecutor;

    static {
        Processor.TAG = "WM-Processor";
    }

    public Processor(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0) {
        this.mAppContext = context0;
        this.mConfiguration = configuration0;
        this.mWorkTaskExecutor = taskExecutor0;
        this.mWorkDatabase = workDatabase0;
        this.mEnqueuedWorkMap = new HashMap();
        this.mForegroundWorkMap = new HashMap();
        this.mCancelledIds = new HashSet();
        this.mOuterListeners = new ArrayList();
        this.mForegroundLock = null;
        this.mLock = new Object();
        this.mWorkRuns = new HashMap();
    }

    public void addExecutionListener(ExecutionListener executionListener0) {
        synchronized(this.mLock) {
            this.mOuterListeners.add(executionListener0);
        }
    }

    private WorkerWrapper cleanUpWorkerUnsafe(String s) {
        WorkerWrapper workerWrapper0 = (WorkerWrapper)this.mForegroundWorkMap.remove(s);
        boolean z = workerWrapper0 != null;
        if(!z) {
            workerWrapper0 = (WorkerWrapper)this.mEnqueuedWorkMap.remove(s);
        }
        this.mWorkRuns.remove(s);
        if(z) {
            this.stopForegroundService();
        }
        return workerWrapper0;
    }

    public WorkSpec getRunningWorkSpec(String s) {
        synchronized(this.mLock) {
            WorkerWrapper workerWrapper0 = this.getWorkerWrapperUnsafe(s);
            return workerWrapper0 != null ? workerWrapper0.getWorkSpec() : null;
        }
    }

    private WorkerWrapper getWorkerWrapperUnsafe(String s) {
        WorkerWrapper workerWrapper0 = (WorkerWrapper)this.mForegroundWorkMap.get(s);
        return workerWrapper0 == null ? ((WorkerWrapper)this.mEnqueuedWorkMap.get(s)) : workerWrapper0;
    }

    public boolean hasWork() {
        synchronized(this.mLock) {
            return !this.mEnqueuedWorkMap.isEmpty() || !this.mForegroundWorkMap.isEmpty();
        }
    }

    private static boolean interrupt(String s, WorkerWrapper workerWrapper0, int v) {
        if(workerWrapper0 != null) {
            workerWrapper0.interrupt(v);
            Logger.get().debug("WM-Processor", "WorkerWrapper interrupted for " + s);
            return true;
        }
        Logger.get().debug("WM-Processor", "WorkerWrapper could not be found for " + s);
        return false;
    }

    public boolean isCancelled(String s) {
        synchronized(this.mLock) {
        }
        return this.mCancelledIds.contains(s);
    }

    public boolean isEnqueued(String s) {
        synchronized(this.mLock) {
            return this.getWorkerWrapperUnsafe(s) != null;
        }
    }

    // 检测为 Lambda 实现
    void lambda$runOnExecuted$2$androidx-work-impl-Processor(WorkGenerationalId workGenerationalId0, boolean z) [...]

    // 检测为 Lambda 实现
    WorkSpec lambda$startWork$0$androidx-work-impl-Processor(ArrayList arrayList0, String s) throws Exception [...]

    // 检测为 Lambda 实现
    void lambda$startWork$1$androidx-work-impl-Processor(ListenableFuture listenableFuture0, WorkerWrapper workerWrapper0) [...]

    private void onExecuted(WorkerWrapper workerWrapper0, boolean z) {
        synchronized(this.mLock) {
            WorkGenerationalId workGenerationalId0 = workerWrapper0.getWorkGenerationalId();
            String s = workGenerationalId0.getWorkSpecId();
            if(this.getWorkerWrapperUnsafe(s) == workerWrapper0) {
                this.cleanUpWorkerUnsafe(s);
            }
            Logger.get().debug("WM-Processor", this.getClass().getSimpleName() + " " + s + " executed; reschedule = " + z);
            for(Object object1: this.mOuterListeners) {
                ((ExecutionListener)object1).onExecuted(workGenerationalId0, z);
            }
        }
    }

    public void removeExecutionListener(ExecutionListener executionListener0) {
        synchronized(this.mLock) {
            this.mOuterListeners.remove(executionListener0);
        }
    }

    private void runOnExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        this.mWorkTaskExecutor.getMainThreadExecutor().execute(() -> synchronized(this.mLock) {
            for(Object object1: this.mOuterListeners) {
                ((ExecutionListener)object1).onExecuted(workGenerationalId0, z);
            }
        });
    }

    @Override  // androidx.work.impl.foreground.ForegroundProcessor
    public void startForeground(String s, ForegroundInfo foregroundInfo0) {
        synchronized(this.mLock) {
            Logger.get().info("WM-Processor", "Moving WorkSpec (" + s + ") to the foreground");
            WorkerWrapper workerWrapper0 = (WorkerWrapper)this.mEnqueuedWorkMap.remove(s);
            if(workerWrapper0 != null) {
                if(this.mForegroundLock == null) {
                    PowerManager.WakeLock powerManager$WakeLock0 = WakeLocks.newWakeLock(this.mAppContext, "ProcessorForegroundLck");
                    this.mForegroundLock = powerManager$WakeLock0;
                    powerManager$WakeLock0.acquire();
                }
                this.mForegroundWorkMap.put(s, workerWrapper0);
                Intent intent0 = SystemForegroundDispatcher.createStartForegroundIntent(this.mAppContext, workerWrapper0.getWorkGenerationalId(), foregroundInfo0);
                ContextCompat.startForegroundService(this.mAppContext, intent0);
            }
        }
    }

    public boolean startWork(StartStopToken startStopToken0) {
        return this.startWork(startStopToken0, null);
    }

    public boolean startWork(StartStopToken startStopToken0, RuntimeExtras workerParameters$RuntimeExtras0) {
        Throwable throwable2;
        WorkGenerationalId workGenerationalId0 = startStopToken0.getId();
        String s = workGenerationalId0.getWorkSpecId();
        ArrayList arrayList0 = new ArrayList();
        WorkSpec workSpec0 = (WorkSpec)this.mWorkDatabase.runInTransaction(() -> {
            arrayList0.addAll(this.mWorkDatabase.workTagDao().getTagsForWorkSpecId(s));
            return this.mWorkDatabase.workSpecDao().getWorkSpec(s);
        });
        if(workSpec0 == null) {
            Logger.get().warning("WM-Processor", "Didn\'t find WorkSpec for id " + workGenerationalId0);
            this.runOnExecuted(workGenerationalId0, false);
            return false;
        }
        Object object0 = this.mLock;
        __monitor_enter(object0);
        try {
            if(!this.isEnqueued(s)) {
                if(workSpec0.getGeneration() == workGenerationalId0.getGeneration()) {
                    WorkerWrapper workerWrapper0 = new Builder(this.mAppContext, this.mConfiguration, this.mWorkTaskExecutor, this, this.mWorkDatabase, workSpec0, arrayList0).withRuntimeExtras(workerParameters$RuntimeExtras0).build();
                    ListenableFuture listenableFuture0 = workerWrapper0.launch();
                    listenableFuture0.addListener(() -> {
                        boolean z;
                        try {
                            z = ((Boolean)listenableFuture0.get()).booleanValue();
                        }
                        catch(InterruptedException | ExecutionException unused_ex) {
                            z = true;
                        }
                        this.onExecuted(workerWrapper0, z);
                    }, this.mWorkTaskExecutor.getMainThreadExecutor());
                    this.mEnqueuedWorkMap.put(s, workerWrapper0);
                    HashSet hashSet0 = new HashSet();
                    hashSet0.add(startStopToken0);
                    this.mWorkRuns.put(s, hashSet0);
                    __monitor_exit(object0);
                    goto label_20;
                }
                goto label_22;
            }
            goto label_25;
        }
        catch(Throwable throwable0) {
            throwable2 = throwable0;
            goto label_36;
        }
    label_20:
        Logger.get().debug("WM-Processor", this.getClass().getSimpleName() + ": processing " + workGenerationalId0);
        return true;
        try {
        label_22:
            this.runOnExecuted(workGenerationalId0, false);
            __monitor_exit(object0);
            return false;
        label_25:
            Set set0 = (Set)this.mWorkRuns.get(s);
            Object object1 = set0.iterator().next();
            if(((StartStopToken)object1).getId().getGeneration() == workGenerationalId0.getGeneration()) {
                set0.add(startStopToken0);
                Logger.get().debug("WM-Processor", "Work " + workGenerationalId0 + " is already enqueued for processing");
            }
            else {
                this.runOnExecuted(workGenerationalId0, false);
            }
            __monitor_exit(object0);
            return false;
        }
        catch(Throwable throwable1) {
            throwable2 = throwable1;
            while(true) {
                try {
                label_36:
                    __monitor_exit(object0);
                    throw throwable2;
                }
                catch(Throwable throwable0) {
                }
                break;
            }
        }
        throwable2 = throwable0;
        goto label_36;
    }

    public boolean stopAndCancelWork(String s, int v) {
        WorkerWrapper workerWrapper0;
        synchronized(this.mLock) {
            Logger.get().debug("WM-Processor", "Processor cancelling " + s);
            this.mCancelledIds.add(s);
            workerWrapper0 = this.cleanUpWorkerUnsafe(s);
        }
        return Processor.interrupt(s, workerWrapper0, v);
    }

    private void stopForegroundService() {
        synchronized(this.mLock) {
            if(this.mForegroundWorkMap.isEmpty()) {
                Intent intent0 = SystemForegroundDispatcher.createStopForegroundIntent(this.mAppContext);
                try {
                    this.mAppContext.startService(intent0);
                }
                catch(Throwable throwable0) {
                    Logger.get().error("WM-Processor", "Unable to stop foreground service", throwable0);
                }
                PowerManager.WakeLock powerManager$WakeLock0 = this.mForegroundLock;
                if(powerManager$WakeLock0 != null) {
                    powerManager$WakeLock0.release();
                    this.mForegroundLock = null;
                }
            }
        }
    }

    public boolean stopForegroundWork(StartStopToken startStopToken0, int v) {
        WorkerWrapper workerWrapper0;
        String s = startStopToken0.getId().getWorkSpecId();
        synchronized(this.mLock) {
            workerWrapper0 = this.cleanUpWorkerUnsafe(s);
        }
        return Processor.interrupt(s, workerWrapper0, v);
    }

    public boolean stopWork(StartStopToken startStopToken0, int v) {
        String s = startStopToken0.getId().getWorkSpecId();
        synchronized(this.mLock) {
            if(this.mForegroundWorkMap.get(s) != null) {
                Logger.get().debug("WM-Processor", "Ignored stopWork. WorkerWrapper " + s + " is in foreground");
                return false;
            }
            Set set0 = (Set)this.mWorkRuns.get(s);
            if(set0 != null && set0.contains(startStopToken0)) {
                WorkerWrapper workerWrapper0 = this.cleanUpWorkerUnsafe(s);
                return Processor.interrupt(s, workerWrapper0, v);
            }
            return false;
        }
    }
}

