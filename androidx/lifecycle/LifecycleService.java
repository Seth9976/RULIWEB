package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\n\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\f\u001A\u00020\rH\u0017J\b\u0010\u000E\u001A\u00020\u000FH\u0017J\b\u0010\u0010\u001A\u00020\u000FH\u0017J\u001A\u0010\u0011\u001A\u00020\u000F2\b\u0010\f\u001A\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001A\u00020\u0013H\u0017J\"\u0010\u0014\u001A\u00020\u00132\b\u0010\f\u001A\u0004\u0018\u00010\r2\u0006\u0010\u0015\u001A\u00020\u00132\u0006\u0010\u0012\u001A\u00020\u0013H\u0017R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Landroidx/lifecycle/LifecycleService;", "Landroid/app/Service;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "dispatcher", "Landroidx/lifecycle/ServiceLifecycleDispatcher;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStart", "startId", "", "onStartCommand", "flags", "lifecycle-service_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class LifecycleService extends Service implements LifecycleOwner {
    private final ServiceLifecycleDispatcher dispatcher;

    public LifecycleService() {
        this.dispatcher = new ServiceLifecycleDispatcher(this);
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.dispatcher.getLifecycle();
    }

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        this.dispatcher.onServicePreSuperOnBind();
        return null;
    }

    @Override  // android.app.Service
    public void onCreate() {
        this.dispatcher.onServicePreSuperOnCreate();
        super.onCreate();
    }

    @Override  // android.app.Service
    public void onDestroy() {
        this.dispatcher.onServicePreSuperOnDestroy();
        super.onDestroy();
    }

    @Override  // android.app.Service
    @Deprecated(message = "Deprecated in Java")
    public void onStart(Intent intent0, int v) {
        this.dispatcher.onServicePreSuperOnStart();
        super.onStart(intent0, v);
    }

    @Override  // android.app.Service
    public int onStartCommand(Intent intent0, int v, int v1) {
        return super.onStartCommand(intent0, v, v1);
    }
}

