package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.ListenableWorker.Result;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0016¨\u0006\t"}, d2 = {"Landroidx/work/impl/workers/CombineContinuationsWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CombineContinuationsWorker extends Worker {
    public CombineContinuationsWorker(Context context0, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParams");
        super(context0, workerParameters0);
    }

    @Override  // androidx.work.Worker
    public Result doWork() {
        Result listenableWorker$Result0 = Result.success(this.getInputData());
        Intrinsics.checkNotNullExpressionValue(listenableWorker$Result0, "success(inputData)");
        return listenableWorker$Result0;
    }
}

