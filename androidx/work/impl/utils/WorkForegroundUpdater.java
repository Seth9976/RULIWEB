package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableFutureKt;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

public class WorkForegroundUpdater implements ForegroundUpdater {
    private static final String TAG;
    final ForegroundProcessor mForegroundProcessor;
    private final TaskExecutor mTaskExecutor;
    final WorkSpecDao mWorkSpecDao;

    static {
        WorkForegroundUpdater.TAG = "WM-WMFgUpdater";
    }

    public WorkForegroundUpdater(WorkDatabase workDatabase0, ForegroundProcessor foregroundProcessor0, TaskExecutor taskExecutor0) {
        this.mForegroundProcessor = foregroundProcessor0;
        this.mTaskExecutor = taskExecutor0;
        this.mWorkSpecDao = workDatabase0.workSpecDao();
    }

    // 检测为 Lambda 实现
    Void lambda$setForegroundAsync$0$androidx-work-impl-utils-WorkForegroundUpdater(UUID uUID0, ForegroundInfo foregroundInfo0, Context context0) [...]

    @Override  // androidx.work.ForegroundUpdater
    public ListenableFuture setForegroundAsync(Context context0, UUID uUID0, ForegroundInfo foregroundInfo0) {
        return ListenableFutureKt.executeAsync(this.mTaskExecutor.getSerialTaskExecutor(), "setForegroundAsync", () -> {
            String s = uUID0.toString();
            WorkSpec workSpec0 = this.mWorkSpecDao.getWorkSpec(s);
            if(workSpec0 == null) {
                throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
            }
            this.mForegroundProcessor.startForeground(s, foregroundInfo0);
            context0.startService(SystemForegroundDispatcher.createNotifyIntent(context0, WorkSpecKt.generationalId(workSpec0), foregroundInfo0));
            return null;
        });
    }
}

