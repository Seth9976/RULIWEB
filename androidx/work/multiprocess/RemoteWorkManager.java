package androidx.work.multiprocess;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ForegroundInfo;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkQuery;
import androidx.work.WorkRequest;
import androidx.work.impl.WorkManagerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class RemoteWorkManager {
    public final RemoteWorkContinuation beginUniqueWork(String s, ExistingWorkPolicy existingWorkPolicy0, OneTimeWorkRequest oneTimeWorkRequest0) {
        return this.beginUniqueWork(s, existingWorkPolicy0, Collections.singletonList(oneTimeWorkRequest0));
    }

    public abstract RemoteWorkContinuation beginUniqueWork(String arg1, ExistingWorkPolicy arg2, List arg3);

    public final RemoteWorkContinuation beginWith(OneTimeWorkRequest oneTimeWorkRequest0) {
        return this.beginWith(Collections.singletonList(oneTimeWorkRequest0));
    }

    public abstract RemoteWorkContinuation beginWith(List arg1);

    public abstract ListenableFuture cancelAllWork();

    public abstract ListenableFuture cancelAllWorkByTag(String arg1);

    public abstract ListenableFuture cancelUniqueWork(String arg1);

    public abstract ListenableFuture cancelWorkById(UUID arg1);

    public abstract ListenableFuture enqueue(WorkContinuation arg1);

    public abstract ListenableFuture enqueue(WorkRequest arg1);

    public abstract ListenableFuture enqueue(List arg1);

    public abstract ListenableFuture enqueueUniquePeriodicWork(String arg1, ExistingPeriodicWorkPolicy arg2, PeriodicWorkRequest arg3);

    public final ListenableFuture enqueueUniqueWork(String s, ExistingWorkPolicy existingWorkPolicy0, OneTimeWorkRequest oneTimeWorkRequest0) {
        return this.enqueueUniqueWork(s, existingWorkPolicy0, Collections.singletonList(oneTimeWorkRequest0));
    }

    public abstract ListenableFuture enqueueUniqueWork(String arg1, ExistingWorkPolicy arg2, List arg3);

    public static RemoteWorkManager getInstance(Context context0) {
        RemoteWorkManager remoteWorkManager0 = WorkManagerImpl.getInstance(context0).getRemoteWorkManager();
        if(remoteWorkManager0 == null) {
            throw new IllegalStateException("Unable to initialize RemoteWorkManager");
        }
        return remoteWorkManager0;
    }

    public abstract ListenableFuture getWorkInfos(WorkQuery arg1);

    public abstract ListenableFuture setForegroundAsync(String arg1, ForegroundInfo arg2);

    public abstract ListenableFuture setProgress(UUID arg1, Data arg2);
}

