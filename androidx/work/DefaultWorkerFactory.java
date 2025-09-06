package androidx.work;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016¨\u0006\u000B"}, d2 = {"Landroidx/work/DefaultWorkerFactory;", "Landroidx/work/WorkerFactory;", "()V", "createWorker", "", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DefaultWorkerFactory extends WorkerFactory {
    public static final DefaultWorkerFactory INSTANCE;

    static {
        DefaultWorkerFactory.INSTANCE = new DefaultWorkerFactory();
    }

    @Override  // androidx.work.WorkerFactory
    public ListenableWorker createWorker(Context context0, String s, WorkerParameters workerParameters0) {
        return (ListenableWorker)this.createWorker(context0, s, workerParameters0);
    }

    public Void createWorker(Context context0, String s, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "appContext");
        Intrinsics.checkNotNullParameter(s, "workerClassName");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParameters");
        return null;
    }
}

