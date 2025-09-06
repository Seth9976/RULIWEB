package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.ListenableWorker.Result;
import androidx.work.Logger;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0016¨\u0006\t"}, d2 = {"Landroidx/work/impl/workers/DiagnosticsWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "parameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DiagnosticsWorker extends Worker {
    public DiagnosticsWorker(Context context0, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(workerParameters0, "parameters");
        super(context0, workerParameters0);
    }

    @Override  // androidx.work.Worker
    public Result doWork() {
        WorkManagerImpl workManagerImpl0 = WorkManagerImpl.getInstance(this.getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(workManagerImpl0, "getInstance(applicationContext)");
        WorkDatabase workDatabase0 = workManagerImpl0.getWorkDatabase();
        Intrinsics.checkNotNullExpressionValue(workDatabase0, "workManager.workDatabase");
        WorkSpecDao workSpecDao0 = workDatabase0.workSpecDao();
        WorkNameDao workNameDao0 = workDatabase0.workNameDao();
        WorkTagDao workTagDao0 = workDatabase0.workTagDao();
        SystemIdInfoDao systemIdInfoDao0 = workDatabase0.systemIdInfoDao();
        List list0 = workSpecDao0.getRecentlyCompletedWork(workManagerImpl0.getConfiguration().getClock().currentTimeMillis() - TimeUnit.DAYS.toMillis(1L));
        List list1 = workSpecDao0.getRunningWork();
        List list2 = workSpecDao0.getAllEligibleWorkSpecsForScheduling(200);
        if(!list0.isEmpty()) {
            Logger.get().info("WM-DiagnosticsWrkr", "Recently completed work:\n\n");
            Logger.get().info("WM-DiagnosticsWrkr", DiagnosticsWorkerKt.access$workSpecRows(workNameDao0, workTagDao0, systemIdInfoDao0, list0));
        }
        if(!list1.isEmpty()) {
            Logger.get().info("WM-DiagnosticsWrkr", "Running work:\n\n");
            Logger.get().info("WM-DiagnosticsWrkr", DiagnosticsWorkerKt.access$workSpecRows(workNameDao0, workTagDao0, systemIdInfoDao0, list1));
        }
        if(!list2.isEmpty()) {
            Logger.get().info("WM-DiagnosticsWrkr", "Enqueued work:\n\n");
            Logger.get().info("WM-DiagnosticsWrkr", DiagnosticsWorkerKt.access$workSpecRows(workNameDao0, workTagDao0, systemIdInfoDao0, list2));
        }
        Result listenableWorker$Result0 = Result.success();
        Intrinsics.checkNotNullExpressionValue(listenableWorker$Result0, "success()");
        return listenableWorker$Result0;
    }
}

