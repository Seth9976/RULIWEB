package androidx.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0016J\u0010\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u0010\f\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\bH\u0016J\u0010\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0010"}, d2 = {"Landroidx/lifecycle/EmptyActivityLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class EmptyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(bundle0, "outState");
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
    }
}

