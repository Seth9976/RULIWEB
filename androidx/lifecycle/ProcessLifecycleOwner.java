package androidx.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \'2\u00020\u0001:\u0002&\'B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0015\u001A\u00020\u0016H\u0000¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001A\u00020\u0016H\u0000¢\u0006\u0002\b\u0019J\r\u0010\u001A\u001A\u00020\u0016H\u0000¢\u0006\u0002\b\u001BJ\r\u0010\u001C\u001A\u00020\u0016H\u0000¢\u0006\u0002\b\u001DJ\u0015\u0010\u001E\u001A\u00020\u00162\u0006\u0010\u001F\u001A\u00020 H\u0000¢\u0006\u0002\b!J\r\u0010\"\u001A\u00020\u0016H\u0000¢\u0006\u0002\b#J\r\u0010$\u001A\u00020\u0016H\u0000¢\u0006\u0002\b%R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0012X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0012X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\u000EX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "delayedPauseRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "initializationListener", "Landroidx/lifecycle/ReportFragment$ActivityInitializationListener;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "pauseSent", "", "registry", "Landroidx/lifecycle/LifecycleRegistry;", "resumedCounter", "", "startedCounter", "stopSent", "activityPaused", "", "activityPaused$lifecycle_process_release", "activityResumed", "activityResumed$lifecycle_process_release", "activityStarted", "activityStarted$lifecycle_process_release", "activityStopped", "activityStopped$lifecycle_process_release", "attach", "context", "Landroid/content/Context;", "attach$lifecycle_process_release", "dispatchPauseIfNeeded", "dispatchPauseIfNeeded$lifecycle_process_release", "dispatchStopIfNeeded", "dispatchStopIfNeeded$lifecycle_process_release", "Api29Impl", "Companion", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ProcessLifecycleOwner implements LifecycleOwner {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner$Api29Impl;", "", "()V", "registerActivityLifecycleCallbacks", "", "activity", "Landroid/app/Activity;", "callback", "Landroid/app/Application$ActivityLifecycleCallbacks;", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api29Impl {
        public static final Api29Impl INSTANCE;

        static {
            Api29Impl.INSTANCE = new Api29Impl();
        }

        @JvmStatic
        public static final void registerActivityLifecycleCallbacks(Activity activity0, Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(application$ActivityLifecycleCallbacks0, "callback");
            activity0.registerActivityLifecycleCallbacks(application$ActivityLifecycleCallbacks0);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001A\u00020\tH\u0007J\u0015\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0001¢\u0006\u0002\b\u000ER\u0016\u0010\u0003\u001A\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner$Companion;", "", "()V", "TIMEOUT_MS", "", "getTIMEOUT_MS$lifecycle_process_release$annotations", "newInstance", "Landroidx/lifecycle/ProcessLifecycleOwner;", "get", "Landroidx/lifecycle/LifecycleOwner;", "init", "", "context", "Landroid/content/Context;", "init$lifecycle_process_release", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final LifecycleOwner get() {
            return ProcessLifecycleOwner.newInstance;
        }

        public static void getTIMEOUT_MS$lifecycle_process_release$annotations() {
        }

        @JvmStatic
        public final void init$lifecycle_process_release(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            ProcessLifecycleOwner.newInstance.attach$lifecycle_process_release(context0);
        }
    }

    public static final Companion Companion = null;
    public static final long TIMEOUT_MS = 700L;
    private final Runnable delayedPauseRunnable;
    private Handler handler;
    private final ActivityInitializationListener initializationListener;
    private static final ProcessLifecycleOwner newInstance;
    private boolean pauseSent;
    private final LifecycleRegistry registry;
    private int resumedCounter;
    private int startedCounter;
    private boolean stopSent;

    // 检测为 Lambda 实现
    public static void $r8$lambda$ArPpV1aF4irVI-oizc48o3VfLys(ProcessLifecycleOwner processLifecycleOwner0) [...]

    static {
        ProcessLifecycleOwner.Companion = new Companion(null);
        ProcessLifecycleOwner.newInstance = new ProcessLifecycleOwner();
    }

    private ProcessLifecycleOwner() {
        this.pauseSent = true;
        this.stopSent = true;
        this.registry = new LifecycleRegistry(this);
        this.delayedPauseRunnable = () -> ProcessLifecycleOwner.delayedPauseRunnable$lambda$0(this);
        this.initializationListener = new ActivityInitializationListener() {
            @Override  // androidx.lifecycle.ReportFragment$ActivityInitializationListener
            public void onCreate() {
            }

            @Override  // androidx.lifecycle.ReportFragment$ActivityInitializationListener
            public void onResume() {
                ProcessLifecycleOwner.this.activityResumed$lifecycle_process_release();
            }

            @Override  // androidx.lifecycle.ReportFragment$ActivityInitializationListener
            public void onStart() {
                ProcessLifecycleOwner.this.activityStarted$lifecycle_process_release();
            }
        };
    }

    public final void activityPaused$lifecycle_process_release() {
        int v = this.resumedCounter - 1;
        this.resumedCounter = v;
        if(v == 0) {
            Handler handler0 = this.handler;
            Intrinsics.checkNotNull(handler0);
            handler0.postDelayed(this.delayedPauseRunnable, 700L);
        }
    }

    public final void activityResumed$lifecycle_process_release() {
        int v = this.resumedCounter + 1;
        this.resumedCounter = v;
        if(v == 1) {
            if(this.pauseSent) {
                this.registry.handleLifecycleEvent(Event.ON_RESUME);
                this.pauseSent = false;
                return;
            }
            Handler handler0 = this.handler;
            Intrinsics.checkNotNull(handler0);
            handler0.removeCallbacks(this.delayedPauseRunnable);
        }
    }

    public final void activityStarted$lifecycle_process_release() {
        int v = this.startedCounter + 1;
        this.startedCounter = v;
        if(v == 1 && this.stopSent) {
            this.registry.handleLifecycleEvent(Event.ON_START);
            this.stopSent = false;
        }
    }

    public final void activityStopped$lifecycle_process_release() {
        --this.startedCounter;
        this.dispatchStopIfNeeded$lifecycle_process_release();
    }

    public final void attach$lifecycle_process_release(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        this.handler = new Handler();
        this.registry.handleLifecycleEvent(Event.ON_CREATE);
        Context context1 = context0.getApplicationContext();
        Intrinsics.checkNotNull(context1, "null cannot be cast to non-null type android.app.Application");
        ((Application)context1).registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks() {
            @Override  // androidx.lifecycle.EmptyActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity0, Bundle bundle0) {
                Intrinsics.checkNotNullParameter(activity0, "activity");
                if(Build.VERSION.SDK_INT < 29) {
                    ReportFragment.Companion.get(activity0).setProcessListener(ProcessLifecycleOwner.this.initializationListener);
                }
            }

            @Override  // androidx.lifecycle.EmptyActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity0) {
                Intrinsics.checkNotNullParameter(activity0, "activity");
                ProcessLifecycleOwner.this.activityPaused$lifecycle_process_release();
            }

            @Override  // android.app.Application$ActivityLifecycleCallbacks
            public void onActivityPreCreated(Activity activity0, Bundle bundle0) {
                Intrinsics.checkNotNullParameter(activity0, "activity");
                Api29Impl.registerActivityLifecycleCallbacks(activity0, new EmptyActivityLifecycleCallbacks() {
                    @Override  // android.app.Application$ActivityLifecycleCallbacks
                    public void onActivityPostResumed(Activity activity0) {
                        Intrinsics.checkNotNullParameter(activity0, "activity");
                        ProcessLifecycleOwner.this.activityResumed$lifecycle_process_release();
                    }

                    @Override  // android.app.Application$ActivityLifecycleCallbacks
                    public void onActivityPostStarted(Activity activity0) {
                        Intrinsics.checkNotNullParameter(activity0, "activity");
                        ProcessLifecycleOwner.this.activityStarted$lifecycle_process_release();
                    }
                });
            }

            @Override  // androidx.lifecycle.EmptyActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity0) {
                Intrinsics.checkNotNullParameter(activity0, "activity");
                ProcessLifecycleOwner.this.activityStopped$lifecycle_process_release();
            }
        });
    }

    private static final void delayedPauseRunnable$lambda$0(ProcessLifecycleOwner processLifecycleOwner0) {
        Intrinsics.checkNotNullParameter(processLifecycleOwner0, "this$0");
        processLifecycleOwner0.dispatchPauseIfNeeded$lifecycle_process_release();
        processLifecycleOwner0.dispatchStopIfNeeded$lifecycle_process_release();
    }

    public final void dispatchPauseIfNeeded$lifecycle_process_release() {
        if(this.resumedCounter == 0) {
            this.pauseSent = true;
            this.registry.handleLifecycleEvent(Event.ON_PAUSE);
        }
    }

    public final void dispatchStopIfNeeded$lifecycle_process_release() {
        if(this.startedCounter == 0 && this.pauseSent) {
            this.registry.handleLifecycleEvent(Event.ON_STOP);
            this.stopSent = true;
        }
    }

    @JvmStatic
    public static final LifecycleOwner get() {
        return ProcessLifecycleOwner.Companion.get();
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.registry;
    }

    @JvmStatic
    public static final void init$lifecycle_process_release(Context context0) {
        ProcessLifecycleOwner.Companion.init$lifecycle_process_release(context0);
    }
}

