package androidx.work;

import android.content.ComponentName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A \u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0001H\u0007\u001A\f\u0010\u0007\u001A\u00020\b*\u00020\u0001H\u0007\u001A\n\u0010\u0007\u001A\u00020\b*\u00020\t\u001A!\u0010\n\u001A\u00020\t\"\n\b\u0000\u0010\u000B\u0018\u0001*\u00020\f*\u00020\t2\u0006\u0010\u0004\u001A\u00020\u0005H\u0086\b\u001A\u001A\u0010\n\u001A\u00020\t*\u00020\t2\u0006\u0010\r\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005¨\u0006\u000E"}, d2 = {"buildDelegatedRemoteRequestData", "Landroidx/work/Data;", "delegatedWorkerName", "", "componentName", "Landroid/content/ComponentName;", "inputData", "isRemoteWorkRequest", "", "Landroidx/work/WorkerParameters;", "usingRemoteService", "T", "Landroidx/work/ListenableWorker;", "workerClassName", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerParametersExtensions {
    public static final Data buildDelegatedRemoteRequestData(String s, ComponentName componentName0, Data data0) {
        Intrinsics.checkNotNullParameter(s, "delegatedWorkerName");
        Intrinsics.checkNotNullParameter(componentName0, "componentName");
        Intrinsics.checkNotNullParameter(data0, "inputData");
        Builder data$Builder0 = new Builder();
        data$Builder0.putAll(data0).putString("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME", componentName0.getPackageName()).putString("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME", componentName0.getClassName()).putString("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", s);
        return data$Builder0.build();
    }

    public static final boolean isRemoteWorkRequest(Data data0) {
        Intrinsics.checkNotNullParameter(data0, "<this>");
        return data0.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME", String.class) && data0.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME", String.class) && data0.hasKeyWithValueOfType("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", String.class);
    }

    public static final boolean isRemoteWorkRequest(WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(workerParameters0, "<this>");
        Data data0 = workerParameters0.getInputData();
        Intrinsics.checkNotNullExpressionValue(data0, "inputData");
        return WorkerParametersExtensions.isRemoteWorkRequest(data0);
    }

    public static final WorkerParameters usingRemoteService(WorkerParameters workerParameters0, ComponentName componentName0) {
        Intrinsics.checkNotNullParameter(workerParameters0, "<this>");
        Intrinsics.checkNotNullParameter(componentName0, "componentName");
        Intrinsics.reifiedOperationMarker(4, "T");
        Intrinsics.checkNotNullExpressionValue("androidx.work.ListenableWorker", "T::class.java.name");
        return WorkerParametersExtensions.usingRemoteService(workerParameters0, "androidx.work.ListenableWorker", componentName0);
    }

    public static final WorkerParameters usingRemoteService(WorkerParameters workerParameters0, String s, ComponentName componentName0) {
        Intrinsics.checkNotNullParameter(workerParameters0, "<this>");
        Intrinsics.checkNotNullParameter(s, "workerClassName");
        Intrinsics.checkNotNullParameter(componentName0, "componentName");
        Data data0 = workerParameters0.getInputData();
        Intrinsics.checkNotNullExpressionValue(data0, "inputData");
        return new WorkerParameters(workerParameters0.getId(), WorkerParametersExtensions.buildDelegatedRemoteRequestData(s, componentName0, data0), workerParameters0.getTags(), workerParameters0.getRuntimeExtras(), workerParameters0.getRunAttemptCount(), workerParameters0.getGeneration(), workerParameters0.getBackgroundExecutor(), workerParameters0.getWorkerContext(), workerParameters0.getTaskExecutor(), workerParameters0.getWorkerFactory(), workerParameters0.getProgressUpdater(), workerParameters0.getForegroundUpdater());
    }
}

