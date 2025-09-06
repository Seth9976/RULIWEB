package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\'J\b\u0010\t\u001A\u00020\nH\u0017J\u000E\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\b0\f¨\u0006\u000E"}, d2 = {"Landroidx/work/Worker;", "Landroidx/work/ListenableWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getForegroundInfo", "Landroidx/work/ForegroundInfo;", "getForegroundInfoAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "startWork", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class Worker extends ListenableWorker {
    public Worker(Context context0, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParams");
        super(context0, workerParameters0);
    }

    public abstract Result doWork();

    public ForegroundInfo getForegroundInfo() {
        throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for `getForegroundInfo()`");
    }

    @Override  // androidx.work.ListenableWorker
    public ListenableFuture getForegroundInfoAsync() {
        Executor executor0 = this.getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(executor0, "backgroundExecutor");
        return WorkerKt.access$future(executor0, new Function0() {
            {
                Worker.this = worker0;
                super(0);
            }

            public final ForegroundInfo invoke() {
                return Worker.this.getForegroundInfo();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    @Override  // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        Executor executor0 = this.getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(executor0, "backgroundExecutor");
        return WorkerKt.access$future(executor0, new Function0() {
            {
                Worker.this = worker0;
                super(0);
            }

            public final Result invoke() {
                return Worker.this.doWork();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }
}

