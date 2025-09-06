package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ListenableFutureKt;
import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.WorkInfo.State;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

public class WorkProgressUpdater implements ProgressUpdater {
    static final String TAG;
    final TaskExecutor mTaskExecutor;
    final WorkDatabase mWorkDatabase;

    static {
        WorkProgressUpdater.TAG = "WM-WorkProgressUpdater";
    }

    public WorkProgressUpdater(WorkDatabase workDatabase0, TaskExecutor taskExecutor0) {
        this.mWorkDatabase = workDatabase0;
        this.mTaskExecutor = taskExecutor0;
    }

    // 检测为 Lambda 实现
    Void lambda$updateProgress$0$androidx-work-impl-utils-WorkProgressUpdater(UUID uUID0, Data data0) [...]

    @Override  // androidx.work.ProgressUpdater
    public ListenableFuture updateProgress(Context context0, UUID uUID0, Data data0) {
        return ListenableFutureKt.executeAsync(this.mTaskExecutor.getSerialTaskExecutor(), "updateProgress", () -> {
            String s = uUID0.toString();
            Logger.get().debug("WM-WorkProgressUpdater", "Updating progress for " + uUID0 + " (" + data0 + ")");
            this.mWorkDatabase.beginTransaction();
            try {
                WorkSpec workSpec0 = this.mWorkDatabase.workSpecDao().getWorkSpec(s);
                if(workSpec0 == null) {
                    throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                }
                if(workSpec0.state == State.RUNNING) {
                    WorkProgress workProgress0 = new WorkProgress(s, data0);
                    this.mWorkDatabase.workProgressDao().insert(workProgress0);
                }
                else {
                    Logger.get().warning("WM-WorkProgressUpdater", "Ignoring setProgressAsync(...). WorkSpec (" + s + ") is not in a RUNNING state.");
                }
                this.mWorkDatabase.setTransactionSuccessful();
            }
            catch(Throwable throwable0) {
                try {
                    Logger.get().error("WM-WorkProgressUpdater", "Error updating Worker progress", throwable0);
                    throw throwable0;
                }
                catch(Throwable throwable1) {
                    this.mWorkDatabase.endTransaction();
                    throw throwable1;
                }
            }
            this.mWorkDatabase.endTransaction();
            return null;
        });
    }
}

