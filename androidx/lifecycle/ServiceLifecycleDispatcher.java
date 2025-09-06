package androidx.lifecycle;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\u0010H\u0016J\b\u0010\u0012\u001A\u00020\u0010H\u0016J\b\u0010\u0013\u001A\u00020\u0010H\u0016J\u0010\u0010\u0014\u001A\u00020\u00102\u0006\u0010\u0015\u001A\u00020\u0016H\u0002R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\u000EX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/lifecycle/ServiceLifecycleDispatcher;", "", "provider", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "handler", "Landroid/os/Handler;", "lastDispatchRunnable", "Landroidx/lifecycle/ServiceLifecycleDispatcher$DispatchRunnable;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "registry", "Landroidx/lifecycle/LifecycleRegistry;", "onServicePreSuperOnBind", "", "onServicePreSuperOnCreate", "onServicePreSuperOnDestroy", "onServicePreSuperOnStart", "postDispatchRunnable", "event", "Landroidx/lifecycle/Lifecycle$Event;", "DispatchRunnable", "lifecycle-service_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ServiceLifecycleDispatcher {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000B\u001A\u00020\fH\u0016R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/lifecycle/ServiceLifecycleDispatcher$DispatchRunnable;", "Ljava/lang/Runnable;", "registry", "Landroidx/lifecycle/LifecycleRegistry;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "(Landroidx/lifecycle/LifecycleRegistry;Landroidx/lifecycle/Lifecycle$Event;)V", "getEvent", "()Landroidx/lifecycle/Lifecycle$Event;", "wasExecuted", "", "run", "", "lifecycle-service_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class DispatchRunnable implements Runnable {
        private final Event event;
        private final LifecycleRegistry registry;
        private boolean wasExecuted;

        public DispatchRunnable(LifecycleRegistry lifecycleRegistry0, Event lifecycle$Event0) {
            Intrinsics.checkNotNullParameter(lifecycleRegistry0, "registry");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            super();
            this.registry = lifecycleRegistry0;
            this.event = lifecycle$Event0;
        }

        public final Event getEvent() {
            return this.event;
        }

        @Override
        public void run() {
            if(!this.wasExecuted) {
                this.registry.handleLifecycleEvent(this.event);
                this.wasExecuted = true;
            }
        }
    }

    private final Handler handler;
    private DispatchRunnable lastDispatchRunnable;
    private final LifecycleRegistry registry;

    public ServiceLifecycleDispatcher(LifecycleOwner lifecycleOwner0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "provider");
        super();
        this.registry = new LifecycleRegistry(lifecycleOwner0);
        this.handler = new Handler();
    }

    public Lifecycle getLifecycle() {
        return this.registry;
    }

    public void onServicePreSuperOnBind() {
        this.postDispatchRunnable(Event.ON_START);
    }

    public void onServicePreSuperOnCreate() {
        this.postDispatchRunnable(Event.ON_CREATE);
    }

    public void onServicePreSuperOnDestroy() {
        this.postDispatchRunnable(Event.ON_STOP);
        this.postDispatchRunnable(Event.ON_DESTROY);
    }

    public void onServicePreSuperOnStart() {
        this.postDispatchRunnable(Event.ON_START);
    }

    private final void postDispatchRunnable(Event lifecycle$Event0) {
        DispatchRunnable serviceLifecycleDispatcher$DispatchRunnable0 = this.lastDispatchRunnable;
        if(serviceLifecycleDispatcher$DispatchRunnable0 != null) {
            serviceLifecycleDispatcher$DispatchRunnable0.run();
        }
        DispatchRunnable serviceLifecycleDispatcher$DispatchRunnable1 = new DispatchRunnable(this.registry, lifecycle$Event0);
        this.lastDispatchRunnable = serviceLifecycleDispatcher$DispatchRunnable1;
        Intrinsics.checkNotNull(serviceLifecycleDispatcher$DispatchRunnable1);
        this.handler.postAtFrontOfQueue(serviceLifecycleDispatcher$DispatchRunnable1);
    }
}

