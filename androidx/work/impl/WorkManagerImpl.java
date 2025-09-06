package androidx.work.impl;

import android.app.PendingIntent;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.work.Configuration.Provider;
import androidx.work.Configuration;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableFutureKt;
import androidx.work.Logger.LogcatLogger;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.TracerKt;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkQuery;
import androidx.work.WorkRequest;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.RawWorkInfoDaoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec.WorkInfoPojo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDaoKt;
import androidx.work.impl.utils.CancelWorkRunnable;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.LiveDataUtils;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import androidx.work.impl.utils.PreferenceUtils;
import androidx.work.impl.utils.PruneWorkRunnableKt;
import androidx.work.impl.utils.RawQueries;
import androidx.work.impl.utils.StatusRunnable;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.multiprocess.RemoteWorkManager;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.Unit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

public class WorkManagerImpl extends WorkManager {
    static class Api24Impl {
        static boolean isDeviceProtectedStorage(Context context0) {
            return NetworkApi23..ExternalSyntheticApiModelOutline0.m(context0);
        }
    }

    public static final int CONTENT_URI_TRIGGER_API_LEVEL = 24;
    public static final int MAX_PRE_JOB_SCHEDULER_API_LEVEL = 22;
    public static final int MIN_JOB_SCHEDULER_API_LEVEL = 23;
    public static final String REMOTE_WORK_MANAGER_CLIENT = "androidx.work.multiprocess.RemoteWorkManagerClient";
    private static final String TAG;
    private Configuration mConfiguration;
    private Context mContext;
    private boolean mForceStopRunnableCompleted;
    private PreferenceUtils mPreferenceUtils;
    private Processor mProcessor;
    private volatile RemoteWorkManager mRemoteWorkManager;
    private BroadcastReceiver.PendingResult mRescheduleReceiverResult;
    private List mSchedulers;
    private final Trackers mTrackers;
    private WorkDatabase mWorkDatabase;
    private final CoroutineScope mWorkManagerScope;
    private TaskExecutor mWorkTaskExecutor;
    private static WorkManagerImpl sDefaultInstance;
    private static WorkManagerImpl sDelegatedInstance;
    private static final Object sLock;

    static {
        WorkManagerImpl.TAG = "WM-WorkManagerImpl";
        WorkManagerImpl.sDelegatedInstance = null;
        WorkManagerImpl.sDefaultInstance = null;
        WorkManagerImpl.sLock = new Object();
    }

    public WorkManagerImpl(Context context0, Configuration configuration0, TaskExecutor taskExecutor0, WorkDatabase workDatabase0, List list0, Processor processor0, Trackers trackers0) {
        this.mForceStopRunnableCompleted = false;
        Context context1 = context0.getApplicationContext();
        if(Build.VERSION.SDK_INT >= 24 && Api24Impl.isDeviceProtectedStorage(context1)) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        Logger.setLogger(new LogcatLogger(configuration0.getMinimumLoggingLevel()));
        this.mContext = context1;
        this.mWorkTaskExecutor = taskExecutor0;
        this.mWorkDatabase = workDatabase0;
        this.mProcessor = processor0;
        this.mTrackers = trackers0;
        this.mConfiguration = configuration0;
        this.mSchedulers = list0;
        CoroutineScope coroutineScope0 = WorkManagerImplExtKt.createWorkManagerScope(taskExecutor0);
        this.mWorkManagerScope = coroutineScope0;
        this.mPreferenceUtils = new PreferenceUtils(this.mWorkDatabase);
        Schedulers.registerRescheduling(list0, this.mProcessor, taskExecutor0.getSerialTaskExecutor(), this.mWorkDatabase, configuration0);
        this.mWorkTaskExecutor.executeOnTaskThread(new ForceStopRunnable(context1, this));
        UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener(coroutineScope0, this.mContext, configuration0, workDatabase0);
    }

    @Override  // androidx.work.WorkManager
    public WorkContinuation beginUniqueWork(String s, ExistingWorkPolicy existingWorkPolicy0, List list0) {
        if(list0.isEmpty()) {
            throw new IllegalArgumentException("beginUniqueWork needs at least one OneTimeWorkRequest.");
        }
        return new WorkContinuationImpl(this, s, existingWorkPolicy0, list0);
    }

    @Override  // androidx.work.WorkManager
    public WorkContinuation beginWith(List list0) {
        if(list0.isEmpty()) {
            throw new IllegalArgumentException("beginWith needs at least one OneTimeWorkRequest.");
        }
        return new WorkContinuationImpl(this, list0);
    }

    @Override  // androidx.work.WorkManager
    public Operation cancelAllWork() {
        return CancelWorkRunnable.forAll(this);
    }

    @Override  // androidx.work.WorkManager
    public Operation cancelAllWorkByTag(String s) {
        return CancelWorkRunnable.forTag(s, this);
    }

    @Override  // androidx.work.WorkManager
    public Operation cancelUniqueWork(String s) {
        return CancelWorkRunnable.forName(s, this);
    }

    @Override  // androidx.work.WorkManager
    public Operation cancelWorkById(UUID uUID0) {
        return CancelWorkRunnable.forId(uUID0, this);
    }

    public void closeDatabase() {
        WorkManagerImplExtKt.close(this);
    }

    @Override  // androidx.work.WorkManager
    public PendingIntent createCancelPendingIntent(UUID uUID0) {
        Intent intent0 = SystemForegroundDispatcher.createCancelWorkIntent(this.mContext, uUID0.toString());
        return Build.VERSION.SDK_INT < 0x1F ? PendingIntent.getService(this.mContext, 0, intent0, 0x8000000) : PendingIntent.getService(this.mContext, 0, intent0, 0xA000000);
    }

    public WorkContinuationImpl createWorkContinuationForUniquePeriodicWork(String s, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy0, PeriodicWorkRequest periodicWorkRequest0) {
        return existingPeriodicWorkPolicy0 == ExistingPeriodicWorkPolicy.KEEP ? new WorkContinuationImpl(this, s, ExistingWorkPolicy.KEEP, Collections.singletonList(periodicWorkRequest0)) : new WorkContinuationImpl(this, s, ExistingWorkPolicy.REPLACE, Collections.singletonList(periodicWorkRequest0));
    }

    @Override  // androidx.work.WorkManager
    public Operation enqueue(List list0) {
        if(list0.isEmpty()) {
            throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
        }
        return new WorkContinuationImpl(this, list0).enqueue();
    }

    @Override  // androidx.work.WorkManager
    public Operation enqueueUniquePeriodicWork(String s, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy0, PeriodicWorkRequest periodicWorkRequest0) {
        return existingPeriodicWorkPolicy0 == ExistingPeriodicWorkPolicy.UPDATE ? WorkerUpdater.enqueueUniquelyNamedPeriodic(this, s, periodicWorkRequest0) : this.createWorkContinuationForUniquePeriodicWork(s, existingPeriodicWorkPolicy0, periodicWorkRequest0).enqueue();
    }

    @Override  // androidx.work.WorkManager
    public Operation enqueueUniqueWork(String s, ExistingWorkPolicy existingWorkPolicy0, List list0) {
        return new WorkContinuationImpl(this, s, existingWorkPolicy0, list0).enqueue();
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    @Override  // androidx.work.WorkManager
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    @Deprecated
    public static WorkManagerImpl getInstance() {
        synchronized(WorkManagerImpl.sLock) {
            WorkManagerImpl workManagerImpl0 = WorkManagerImpl.sDelegatedInstance;
            if(workManagerImpl0 != null) {
                return workManagerImpl0;
            }
        }
        return WorkManagerImpl.sDefaultInstance;
    }

    public static WorkManagerImpl getInstance(Context context0) {
        synchronized(WorkManagerImpl.sLock) {
            WorkManagerImpl workManagerImpl0 = WorkManagerImpl.getInstance();
            if(workManagerImpl0 == null) {
                Context context1 = context0.getApplicationContext();
                if(!(context1 instanceof Provider)) {
                    throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
                }
                WorkManagerImpl.initialize(context1, ((Provider)context1).getWorkManagerConfiguration());
                return WorkManagerImpl.getInstance(context1);
            }
            return workManagerImpl0;
        }
    }

    @Override  // androidx.work.WorkManager
    public ListenableFuture getLastCancelAllTimeMillis() {
        PreferenceUtils preferenceUtils0 = this.mPreferenceUtils;
        SerialExecutor serialExecutor0 = this.mWorkTaskExecutor.getSerialTaskExecutor();
        Objects.requireNonNull(preferenceUtils0);
        return ListenableFutureKt.executeAsync(serialExecutor0, "getLastCancelAllTimeMillis", new WorkManagerImpl..ExternalSyntheticLambda0(preferenceUtils0));
    }

    @Override  // androidx.work.WorkManager
    public LiveData getLastCancelAllTimeMillisLiveData() {
        return this.mPreferenceUtils.getLastCancelAllTimeMillisLiveData();
    }

    public PreferenceUtils getPreferenceUtils() {
        return this.mPreferenceUtils;
    }

    public Processor getProcessor() {
        return this.mProcessor;
    }

    public RemoteWorkManager getRemoteWorkManager() {
        if(this.mRemoteWorkManager == null) {
            Object object0 = WorkManagerImpl.sLock;
            synchronized(object0) {
                if(this.mRemoteWorkManager == null) {
                    this.tryInitializeMultiProcessSupport();
                    if(this.mRemoteWorkManager == null && !TextUtils.isEmpty(this.mConfiguration.getDefaultProcessName())) {
                        throw new IllegalStateException("Invalid multiprocess configuration. Define an `implementation` dependency on :work:work-multiprocess library");
                    }
                }
                return this.mRemoteWorkManager;
            }
        }
        return this.mRemoteWorkManager;
    }

    public List getSchedulers() {
        return this.mSchedulers;
    }

    public Trackers getTrackers() {
        return this.mTrackers;
    }

    public WorkDatabase getWorkDatabase() {
        return this.mWorkDatabase;
    }

    @Override  // androidx.work.WorkManager
    public ListenableFuture getWorkInfoById(UUID uUID0) {
        return StatusRunnable.forUUID(this.mWorkDatabase, this.mWorkTaskExecutor, uUID0);
    }

    @Override  // androidx.work.WorkManager
    public Flow getWorkInfoByIdFlow(UUID uUID0) {
        return WorkSpecDaoKt.getWorkStatusPojoFlowDataForIds(this.getWorkDatabase().workSpecDao(), uUID0);
    }

    @Override  // androidx.work.WorkManager
    public LiveData getWorkInfoByIdLiveData(UUID uUID0) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForIds(Collections.singletonList(uUID0.toString())), new Function() {
            public WorkInfo apply(List list0) {
                return list0 == null || list0.size() <= 0 ? null : ((WorkInfoPojo)list0.get(0)).toWorkInfo();
            }

            @Override  // androidx.arch.core.util.Function
            public Object apply(Object object0) {
                return this.apply(((List)object0));
            }
        }, this.mWorkTaskExecutor);
    }

    @Override  // androidx.work.WorkManager
    public ListenableFuture getWorkInfos(WorkQuery workQuery0) {
        return StatusRunnable.forWorkQuerySpec(this.mWorkDatabase, this.mWorkTaskExecutor, workQuery0);
    }

    LiveData getWorkInfosById(List list0) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForIds(list0), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override  // androidx.work.WorkManager
    public ListenableFuture getWorkInfosByTag(String s) {
        return StatusRunnable.forTag(this.mWorkDatabase, this.mWorkTaskExecutor, s);
    }

    @Override  // androidx.work.WorkManager
    public Flow getWorkInfosByTagFlow(String s) {
        return WorkSpecDaoKt.getWorkStatusPojoFlowForTag(this.mWorkDatabase.workSpecDao(), this.mWorkTaskExecutor.getTaskCoroutineDispatcher(), s);
    }

    @Override  // androidx.work.WorkManager
    public LiveData getWorkInfosByTagLiveData(String s) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForTag(s), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override  // androidx.work.WorkManager
    public Flow getWorkInfosFlow(WorkQuery workQuery0) {
        return RawWorkInfoDaoKt.getWorkInfoPojosFlow(this.mWorkDatabase.rawWorkInfoDao(), this.mWorkTaskExecutor.getTaskCoroutineDispatcher(), RawQueries.toRawQuery(workQuery0));
    }

    @Override  // androidx.work.WorkManager
    public ListenableFuture getWorkInfosForUniqueWork(String s) {
        return StatusRunnable.forUniqueWork(this.mWorkDatabase, this.mWorkTaskExecutor, s);
    }

    @Override  // androidx.work.WorkManager
    public Flow getWorkInfosForUniqueWorkFlow(String s) {
        return WorkSpecDaoKt.getWorkStatusPojoFlowForName(this.mWorkDatabase.workSpecDao(), this.mWorkTaskExecutor.getTaskCoroutineDispatcher(), s);
    }

    @Override  // androidx.work.WorkManager
    public LiveData getWorkInfosForUniqueWorkLiveData(String s) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForName(s), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override  // androidx.work.WorkManager
    public LiveData getWorkInfosLiveData(WorkQuery workQuery0) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.rawWorkInfoDao().getWorkInfoPojosLiveData(RawQueries.toRawQuery(workQuery0)), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    CoroutineScope getWorkManagerScope() {
        return this.mWorkManagerScope;
    }

    public TaskExecutor getWorkTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    @Override  // androidx.work.WorkManager
    public static void initialize(Context context0, Configuration configuration0) {
        synchronized(WorkManagerImpl.sLock) {
            WorkManagerImpl workManagerImpl0 = WorkManagerImpl.sDelegatedInstance;
            if(workManagerImpl0 != null && WorkManagerImpl.sDefaultInstance != null) {
                throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
            }
            if(workManagerImpl0 == null) {
                Context context1 = context0.getApplicationContext();
                if(WorkManagerImpl.sDefaultInstance == null) {
                    WorkManagerImpl.sDefaultInstance = WorkManagerImplExtKt.createWorkManager(context1, configuration0);
                }
                WorkManagerImpl.sDelegatedInstance = WorkManagerImpl.sDefaultInstance;
            }
        }
    }

    @Override  // androidx.work.WorkManager
    public static boolean isInitialized() {
        return WorkManagerImpl.getInstance() != null;
    }

    // 检测为 Lambda 实现
    Unit lambda$rescheduleEligibleWork$0$androidx-work-impl-WorkManagerImpl() [...]

    public void onForceStopRunnableCompleted() {
        synchronized(WorkManagerImpl.sLock) {
            this.mForceStopRunnableCompleted = true;
            BroadcastReceiver.PendingResult broadcastReceiver$PendingResult0 = this.mRescheduleReceiverResult;
            if(broadcastReceiver$PendingResult0 != null) {
                broadcastReceiver$PendingResult0.finish();
                this.mRescheduleReceiverResult = null;
            }
        }
    }

    @Override  // androidx.work.WorkManager
    public Operation pruneWork() {
        return PruneWorkRunnableKt.pruneWork(this.mWorkDatabase, this.mConfiguration, this.mWorkTaskExecutor);
    }

    public void rescheduleEligibleWork() {
        TracerKt.traced(this.getConfiguration().getTracer(), "ReschedulingWork", () -> {
            if(Build.VERSION.SDK_INT >= 23) {
                SystemJobScheduler.cancelAllInAllNamespaces(this.getApplicationContext());
            }
            this.getWorkDatabase().workSpecDao().resetScheduledState();
            Schedulers.schedule(this.getConfiguration(), this.getWorkDatabase(), this.getSchedulers());
            return Unit.INSTANCE;
        });
    }

    public static void setDelegate(WorkManagerImpl workManagerImpl0) {
        synchronized(WorkManagerImpl.sLock) {
            WorkManagerImpl.sDelegatedInstance = workManagerImpl0;
        }
    }

    public void setReschedulePendingResult(BroadcastReceiver.PendingResult broadcastReceiver$PendingResult0) {
        synchronized(WorkManagerImpl.sLock) {
            BroadcastReceiver.PendingResult broadcastReceiver$PendingResult1 = this.mRescheduleReceiverResult;
            if(broadcastReceiver$PendingResult1 != null) {
                broadcastReceiver$PendingResult1.finish();
            }
            this.mRescheduleReceiverResult = broadcastReceiver$PendingResult0;
            if(this.mForceStopRunnableCompleted) {
                broadcastReceiver$PendingResult0.finish();
                this.mRescheduleReceiverResult = null;
            }
        }
    }

    public void stopForegroundWork(WorkGenerationalId workGenerationalId0, int v) {
        this.mWorkTaskExecutor.executeOnTaskThread(new StopWorkRunnable(this.mProcessor, new StartStopToken(workGenerationalId0), true, v));
    }

    private void tryInitializeMultiProcessSupport() {
        try {
            this.mRemoteWorkManager = (RemoteWorkManager)Class.forName("androidx.work.multiprocess.RemoteWorkManagerClient").getConstructor(Context.class, WorkManagerImpl.class).newInstance(this.mContext, this);
        }
        catch(Throwable throwable0) {
            Logger.get().debug("WM-WorkManagerImpl", "Unable to initialize multi-process support", throwable0);
        }
    }

    @Override  // androidx.work.WorkManager
    public ListenableFuture updateWork(WorkRequest workRequest0) {
        return WorkerUpdater.updateWorkImpl(this, workRequest0);
    }
}

