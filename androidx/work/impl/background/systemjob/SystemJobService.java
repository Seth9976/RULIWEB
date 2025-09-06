package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.net.Network;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.PersistableBundle;
import androidx.work.Logger;
import androidx.work.WorkerParameters.RuntimeExtras;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens.-CC;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkLauncher;
import androidx.work.impl.WorkLauncherImpl;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SystemJobService extends JobService implements ExecutionListener {
    static class Api24Impl {
        static String[] getTriggeredContentAuthorities(JobParameters jobParameters0) {
            return jobParameters0.getTriggeredContentAuthorities();
        }

        static Uri[] getTriggeredContentUris(JobParameters jobParameters0) {
            return jobParameters0.getTriggeredContentUris();
        }
    }

    static class Api28Impl {
        static Network getNetwork(JobParameters jobParameters0) {
            return jobParameters0.getNetwork();
        }
    }

    static class Api31Impl {
        static int getStopReason(JobParameters jobParameters0) {
            return SystemJobService.stopReason(jobParameters0.getStopReason());
        }
    }

    private static final String TAG;
    private final Map mJobParameters;
    private final StartStopTokens mStartStopTokens;
    private WorkLauncher mWorkLauncher;
    private WorkManagerImpl mWorkManagerImpl;

    static {
        SystemJobService.TAG = "WM-SystemJobService";
    }

    public SystemJobService() {
        this.mJobParameters = new HashMap();
        this.mStartStopTokens = StartStopTokens.-CC.create(false);
    }

    private static void assertMainThread(String s) {
        if(Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Cannot invoke " + s + " on a background thread");
        }
    }

    @Override  // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            WorkManagerImpl workManagerImpl0 = WorkManagerImpl.getInstance(this.getApplicationContext());
            this.mWorkManagerImpl = workManagerImpl0;
            Processor processor0 = workManagerImpl0.getProcessor();
            this.mWorkLauncher = new WorkLauncherImpl(processor0, this.mWorkManagerImpl.getWorkTaskExecutor());
            processor0.addExecutionListener(this);
        }
        catch(IllegalStateException illegalStateException0) {
            Class class0 = this.getApplication().getClass();
            if(!Application.class.equals(class0)) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", illegalStateException0);
            }
            Logger.get().warning("WM-SystemJobService", "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
        }
    }

    @Override  // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        WorkManagerImpl workManagerImpl0 = this.mWorkManagerImpl;
        if(workManagerImpl0 != null) {
            workManagerImpl0.getProcessor().removeExecutionListener(this);
        }
    }

    @Override  // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        SystemJobService.assertMainThread("onExecuted");
        Logger.get().debug("WM-SystemJobService", workGenerationalId0.getWorkSpecId() + " executed on JobScheduler");
        JobParameters jobParameters0 = (JobParameters)this.mJobParameters.remove(workGenerationalId0);
        this.mStartStopTokens.remove(workGenerationalId0);
        if(jobParameters0 != null) {
            this.jobFinished(jobParameters0, z);
        }
    }

    @Override  // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters0) {
        RuntimeExtras workerParameters$RuntimeExtras0;
        SystemJobService.assertMainThread("onStartJob");
        if(this.mWorkManagerImpl == null) {
            Logger.get().debug("WM-SystemJobService", "WorkManager is not initialized; requesting retry.");
            this.jobFinished(jobParameters0, true);
            return false;
        }
        WorkGenerationalId workGenerationalId0 = SystemJobService.workGenerationalIdFromJobParameters(jobParameters0);
        if(workGenerationalId0 == null) {
            Logger.get().error("WM-SystemJobService", "WorkSpec id not found!");
            return false;
        }
        if(this.mJobParameters.containsKey(workGenerationalId0)) {
            Logger.get().debug("WM-SystemJobService", "Job is already being executed by SystemJobService: " + workGenerationalId0);
            return false;
        }
        Logger.get().debug("WM-SystemJobService", "onStartJob for " + workGenerationalId0);
        this.mJobParameters.put(workGenerationalId0, jobParameters0);
        if(Build.VERSION.SDK_INT >= 24) {
            workerParameters$RuntimeExtras0 = new RuntimeExtras();
            if(Api24Impl.getTriggeredContentUris(jobParameters0) != null) {
                workerParameters$RuntimeExtras0.triggeredContentUris = Arrays.asList(Api24Impl.getTriggeredContentUris(jobParameters0));
            }
            if(Api24Impl.getTriggeredContentAuthorities(jobParameters0) != null) {
                workerParameters$RuntimeExtras0.triggeredContentAuthorities = Arrays.asList(Api24Impl.getTriggeredContentAuthorities(jobParameters0));
            }
            if(Build.VERSION.SDK_INT >= 28) {
                workerParameters$RuntimeExtras0.network = Api28Impl.getNetwork(jobParameters0);
            }
        }
        else {
            workerParameters$RuntimeExtras0 = null;
        }
        this.mWorkLauncher.startWork(this.mStartStopTokens.tokenFor(workGenerationalId0), workerParameters$RuntimeExtras0);
        return true;
    }

    @Override  // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters0) {
        SystemJobService.assertMainThread("onStopJob");
        if(this.mWorkManagerImpl == null) {
            Logger.get().debug("WM-SystemJobService", "WorkManager is not initialized; requesting retry.");
            return true;
        }
        WorkGenerationalId workGenerationalId0 = SystemJobService.workGenerationalIdFromJobParameters(jobParameters0);
        if(workGenerationalId0 == null) {
            Logger.get().error("WM-SystemJobService", "WorkSpec id not found!");
            return false;
        }
        Logger.get().debug("WM-SystemJobService", "onStopJob for " + workGenerationalId0);
        this.mJobParameters.remove(workGenerationalId0);
        StartStopToken startStopToken0 = this.mStartStopTokens.remove(workGenerationalId0);
        if(startStopToken0 != null) {
            int v = Build.VERSION.SDK_INT < 0x1F ? 0xFFFFFE00 : Api31Impl.getStopReason(jobParameters0);
            this.mWorkLauncher.stopWorkWithReason(startStopToken0, v);
        }
        return !this.mWorkManagerImpl.getProcessor().isCancelled(workGenerationalId0.getWorkSpecId());
    }

    static int stopReason(int jobReason) {
        switch(jobReason) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                return jobReason;
            }
            default: {
                return 0xFFFFFE00;
            }
        }
    }

    private static WorkGenerationalId workGenerationalIdFromJobParameters(JobParameters jobParameters0) {
        try {
            PersistableBundle persistableBundle0 = jobParameters0.getExtras();
            return persistableBundle0 == null || !persistableBundle0.containsKey("EXTRA_WORK_SPEC_ID") ? null : new WorkGenerationalId(persistableBundle0.getString("EXTRA_WORK_SPEC_ID"), persistableBundle0.getInt("EXTRA_WORK_SPEC_GENERATION"));
        }
        catch(NullPointerException unused_ex) {
        }
        return null;
    }
}

