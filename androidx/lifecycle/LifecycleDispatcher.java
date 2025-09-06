package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/lifecycle/LifecycleDispatcher;", "", "()V", "initialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "init", "", "context", "Landroid/content/Context;", "DispatcherActivityCallback", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LifecycleDispatcher {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Landroidx/lifecycle/LifecycleDispatcher$DispatcherActivityCallback;", "Landroidx/lifecycle/EmptyActivityLifecycleCallbacks;", "()V", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        @Override  // androidx.lifecycle.EmptyActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity0, Bundle bundle0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.injectIfNeededIn(activity0);
        }
    }

    public static final LifecycleDispatcher INSTANCE;
    private static final AtomicBoolean initialized;

    static {
        LifecycleDispatcher.INSTANCE = new LifecycleDispatcher();
        LifecycleDispatcher.initialized = new AtomicBoolean(false);
    }

    @JvmStatic
    public static final void init(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        if(LifecycleDispatcher.initialized.getAndSet(true)) {
            return;
        }
        Context context1 = context0.getApplicationContext();
        Intrinsics.checkNotNull(context1, "null cannot be cast to non-null type android.app.Application");
        ((Application)context1).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
    }
}

