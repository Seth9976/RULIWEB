package androidx.work;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH&J \u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0007¨\u0006\f"}, d2 = {"Landroidx/work/WorkerFactory;", "", "()V", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "createWorkerWithDefaultFallback", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class WorkerFactory {
    public abstract ListenableWorker createWorker(Context arg1, String arg2, WorkerParameters arg3);

    public final ListenableWorker createWorkerWithDefaultFallback(Context context0, String s, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "appContext");
        Intrinsics.checkNotNullParameter(s, "workerClassName");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParameters");
        ListenableWorker listenableWorker0 = this.createWorker(context0, s, workerParameters0);
        if(listenableWorker0 == null) {
            listenableWorker0 = WorkerFactory.createWorkerWithDefaultFallback$fallbackToReflection(context0, s, workerParameters0);
        }
        if(listenableWorker0.isUsed()) {
            throw new IllegalStateException("WorkerFactory (" + this.getClass().getName() + ") returned an instance of a ListenableWorker (" + s + ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
        }
        return listenableWorker0;
    }

    private static final ListenableWorker createWorkerWithDefaultFallback$fallbackToReflection(Context context0, String s, WorkerParameters workerParameters0) {
        Class class0 = WorkerFactory.createWorkerWithDefaultFallback$getWorkerClass(s);
        try {
            Object object0 = class0.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context0, workerParameters0);
            Intrinsics.checkNotNullExpressionValue(object0, "{\n                val co…Parameters)\n            }");
            return (ListenableWorker)object0;
        }
        catch(Throwable throwable0) {
            Logger.get().error("WM-WorkerFactory", "Could not instantiate " + s, throwable0);
            throw throwable0;
        }
    }

    private static final Class createWorkerWithDefaultFallback$getWorkerClass(String s) {
        try {
            Class class0 = Class.forName(s).asSubclass(ListenableWorker.class);
            Intrinsics.checkNotNullExpressionValue(class0, "{\n                Class.…class.java)\n            }");
            return class0;
        }
        catch(Throwable throwable0) {
            Logger.get().error("WM-WorkerFactory", "Invalid class: " + s, throwable0);
            throw throwable0;
        }
    }
}

