package androidx.work.impl.utils;

import android.os.Build.VERSION;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data.Builder;
import androidx.work.Data;
import androidx.work.WorkRequest;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001A \u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0000\u001A\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u000EH\u0000\u001A\u0010\u0010\u0010\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u000EH\u0007\u001A\u001E\u0010\u0011\u001A\u00020\u00122\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001A\u00020\u0001H\u0002\u001A\u001E\u0010\u0017\u001A\u00020\u000E2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u000F\u001A\u00020\u000EH\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", "", "ARGUMENT_SERVICE_CLASS_NAME", "ARGUMENT_SERVICE_PACKAGE_NAME", "REMOTE_DELEGATING_LISTENABLE_WORKER_CLASS_NAME", "checkContentUriTriggerWorkerLimits", "", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "configuration", "Landroidx/work/Configuration;", "continuation", "Landroidx/work/impl/WorkContinuationImpl;", "tryDelegateConstrainedWorkSpec", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "tryDelegateRemoteListenableWorker", "usesScheduler", "", "schedulers", "", "Landroidx/work/impl/Scheduler;", "className", "wrapWorkSpecIfNeeded", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class EnqueueUtilsKt {
    public static final String ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME = "androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME";
    public static final String ARGUMENT_SERVICE_CLASS_NAME = "androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME";
    public static final String ARGUMENT_SERVICE_PACKAGE_NAME = "androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME";
    public static final String REMOTE_DELEGATING_LISTENABLE_WORKER_CLASS_NAME = "androidx.work.multiprocess.RemoteListenableDelegatingWorker";

    public static final void checkContentUriTriggerWorkerLimits(WorkDatabase workDatabase0, Configuration configuration0, WorkContinuationImpl workContinuationImpl0) {
        int v1;
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        Intrinsics.checkNotNullParameter(workContinuationImpl0, "continuation");
        if(Build.VERSION.SDK_INT >= 24) {
            List list0 = CollectionsKt.mutableListOf(new WorkContinuationImpl[]{workContinuationImpl0});
            int v = 0;
            while(!list0.isEmpty()) {
                WorkContinuationImpl workContinuationImpl1 = (WorkContinuationImpl)CollectionsKt.removeLast(list0);
                List list1 = workContinuationImpl1.getWork();
                Intrinsics.checkNotNullExpressionValue(list1, "current.work");
                Iterable iterable0 = list1;
                if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                    v1 = 0;
                    for(Object object0: iterable0) {
                        if(((WorkRequest)object0).getWorkSpec().constraints.hasContentUriTriggers()) {
                            ++v1;
                            if(v1 < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                }
                else {
                    v1 = 0;
                }
                v += v1;
                List list2 = workContinuationImpl1.getParents();
                if(list2 != null) {
                    list0.addAll(list2);
                }
            }
            if(v != 0) {
                int v2 = workDatabase0.workSpecDao().countNonFinishedContentUriTriggerWorkers();
                int v3 = configuration0.getContentUriTriggerWorkersLimit();
                if(v2 + v > v3) {
                    throw new IllegalArgumentException("Too many workers with contentUriTriggers are enqueued:\ncontentUriTrigger workers limit: " + v3 + ";\nalready enqueued count: " + v2 + ";\ncurrent enqueue operation count: " + v + ".\nTo address this issue you can: \n1. enqueue less workers or batch some of workers with content uri triggers together;\n2. increase limit via Configuration.Builder.setContentUriTriggerWorkersLimit;\nPlease beware that workers with content uri triggers immediately occupy slots in JobScheduler so no updates to content uris are missed.");
                }
            }
        }
    }

    public static final WorkSpec tryDelegateConstrainedWorkSpec(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        Constraints constraints0 = workSpec0.constraints;
        String s = workSpec0.workerClassName;
        if(!Intrinsics.areEqual(s, "androidx.work.impl.workers.ConstraintTrackingWorker") && (constraints0.requiresBatteryNotLow() || constraints0.requiresStorageNotLow())) {
            Data data0 = new Builder().putAll(workSpec0.input).putString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", s).build();
            Intrinsics.checkNotNullExpressionValue("androidx.work.impl.workers.ConstraintTrackingWorker", "name");
            return WorkSpec.copy$default(workSpec0, null, null, "androidx.work.impl.workers.ConstraintTrackingWorker", null, data0, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 0L, 0, 0, null, 0xFFFFEB, null);
        }
        return workSpec0;
    }

    public static final WorkSpec tryDelegateRemoteListenableWorker(WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        if(!workSpec0.input.hasKeyWithValueOfType("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", String.class) && workSpec0.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME", String.class) && workSpec0.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME", String.class)) {
            String s = workSpec0.workerClassName;
            return WorkSpec.copy$default(workSpec0, null, null, "androidx.work.multiprocess.RemoteListenableDelegatingWorker", null, new Builder().putAll(workSpec0.input).putString("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", s).build(), null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 0L, 0, 0, null, 0xFFFFEB, null);
        }
        return workSpec0;
    }

    private static final boolean usesScheduler(List list0, String s) {
        try {
            Class class0 = Class.forName(s);
            if(list0 instanceof Collection && list0.isEmpty()) {
                return false;
            }
            for(Object object0: list0) {
                if(!class0.isAssignableFrom(((Scheduler)object0).getClass())) {
                    continue;
                }
                return true;
            }
        }
        catch(ClassNotFoundException unused_ex) {
        }
        return false;
    }

    public static final WorkSpec wrapWorkSpecIfNeeded(List list0, WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(list0, "schedulers");
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        WorkSpec workSpec1 = EnqueueUtilsKt.tryDelegateRemoteListenableWorker(workSpec0);
        if(23 <= Build.VERSION.SDK_INT && Build.VERSION.SDK_INT < 26) {
            return EnqueueUtilsKt.tryDelegateConstrainedWorkSpec(workSpec1);
        }
        return Build.VERSION.SDK_INT > 22 || !EnqueueUtilsKt.usesScheduler(list0, "androidx.work.impl.background.gcm.GcmScheduler") ? workSpec1 : EnqueueUtilsKt.tryDelegateConstrainedWorkSpec(workSpec1);
    }
}

