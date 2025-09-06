package androidx.activity;

import android.view.View;
import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J8\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\t2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u000EH\u0016¨\u0006\u0010"}, d2 = {"Landroidx/activity/EdgeToEdgeBase;", "Landroidx/activity/EdgeToEdgeImpl;", "()V", "adjustLayoutInDisplayCutoutMode", "", "window", "Landroid/view/Window;", "setUp", "statusBarStyle", "Landroidx/activity/SystemBarStyle;", "navigationBarStyle", "view", "Landroid/view/View;", "statusBarIsDark", "", "navigationBarIsDark", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
class EdgeToEdgeBase implements EdgeToEdgeImpl {
    @Override  // androidx.activity.EdgeToEdgeImpl
    public void adjustLayoutInDisplayCutoutMode(Window window0) {
        Intrinsics.checkNotNullParameter(window0, "window");
    }

    @Override  // androidx.activity.EdgeToEdgeImpl
    public void setUp(SystemBarStyle systemBarStyle0, SystemBarStyle systemBarStyle1, Window window0, View view0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(systemBarStyle0, "statusBarStyle");
        Intrinsics.checkNotNullParameter(systemBarStyle1, "navigationBarStyle");
        Intrinsics.checkNotNullParameter(window0, "window");
        Intrinsics.checkNotNullParameter(view0, "view");
    }
}

