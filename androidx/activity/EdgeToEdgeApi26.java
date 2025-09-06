package androidx.activity;

import android.view.View;
import android.view.Window;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\rH\u0017¨\u0006\u000F"}, d2 = {"Landroidx/activity/EdgeToEdgeApi26;", "Landroidx/activity/EdgeToEdgeBase;", "()V", "setUp", "", "statusBarStyle", "Landroidx/activity/SystemBarStyle;", "navigationBarStyle", "window", "Landroid/view/Window;", "view", "Landroid/view/View;", "statusBarIsDark", "", "navigationBarIsDark", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
class EdgeToEdgeApi26 extends EdgeToEdgeBase {
    @Override  // androidx.activity.EdgeToEdgeBase
    public void setUp(SystemBarStyle systemBarStyle0, SystemBarStyle systemBarStyle1, Window window0, View view0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(systemBarStyle0, "statusBarStyle");
        Intrinsics.checkNotNullParameter(systemBarStyle1, "navigationBarStyle");
        Intrinsics.checkNotNullParameter(window0, "window");
        Intrinsics.checkNotNullParameter(view0, "view");
        WindowCompat.setDecorFitsSystemWindows(window0, false);
        window0.setStatusBarColor(systemBarStyle0.getScrim$activity_release(z));
        window0.setNavigationBarColor(systemBarStyle1.getScrim$activity_release(z1));
        WindowInsetsControllerCompat windowInsetsControllerCompat0 = new WindowInsetsControllerCompat(window0, view0);
        windowInsetsControllerCompat0.setAppearanceLightStatusBars(!z);
        windowInsetsControllerCompat0.setAppearanceLightNavigationBars(!z1);
    }
}

