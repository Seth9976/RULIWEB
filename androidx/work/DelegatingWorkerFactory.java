package androidx.work;

import android.content.Context;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0001J \u0010\b\u001A\u0004\u0018\u00010\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FR\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/work/DelegatingWorkerFactory;", "Landroidx/work/WorkerFactory;", "()V", "factories", "", "addFactory", "", "workerFactory", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class DelegatingWorkerFactory extends WorkerFactory {
    private final List factories;

    public DelegatingWorkerFactory() {
        this.factories = new CopyOnWriteArrayList();
    }

    public final void addFactory(WorkerFactory workerFactory0) {
        Intrinsics.checkNotNullParameter(workerFactory0, "workerFactory");
        this.factories.add(workerFactory0);
    }

    @Override  // androidx.work.WorkerFactory
    public final ListenableWorker createWorker(Context context0, String s, WorkerParameters workerParameters0) {
        Intrinsics.checkNotNullParameter(context0, "appContext");
        Intrinsics.checkNotNullParameter(s, "workerClassName");
        Intrinsics.checkNotNullParameter(workerParameters0, "workerParameters");
        for(Object object0: this.factories) {
            WorkerFactory workerFactory0 = (WorkerFactory)object0;
            try {
                ListenableWorker listenableWorker0 = workerFactory0.createWorker(context0, s, workerParameters0);
                if(listenableWorker0 != null) {
                    return listenableWorker0;
                }
            }
            catch(Throwable throwable0) {
                Logger.get().error("WM-DelegatingWkrFctry", "Unable to instantiate a ListenableWorker (" + s + ')', throwable0);
                throw throwable0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }
}

