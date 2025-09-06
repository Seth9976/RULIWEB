package androidx.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A%\u0010\u000B\u001A\u00020\f*\u00020\r2\b\b\u0002\u0010\u000E\u001A\u00020\u000F2\b\b\u0002\u0010\u0010\u001A\u00020\u000FH\u0007¢\u0006\u0002\b\u0011\"\u001C\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u001C\u0010\u0006\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001A\u0004\b\b\u0010\u0005\"\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"DefaultDarkScrim", "", "getDefaultDarkScrim$annotations", "()V", "getDefaultDarkScrim", "()I", "DefaultLightScrim", "getDefaultLightScrim$annotations", "getDefaultLightScrim", "Impl", "Landroidx/activity/EdgeToEdgeImpl;", "enableEdgeToEdge", "", "Landroidx/activity/ComponentActivity;", "statusBarStyle", "Landroidx/activity/SystemBarStyle;", "navigationBarStyle", "enable", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class EdgeToEdge {
    private static final int DefaultDarkScrim;
    private static final int DefaultLightScrim;
    private static EdgeToEdgeImpl Impl;

    static {
        EdgeToEdge.DefaultLightScrim = Color.argb(230, 0xFF, 0xFF, 0xFF);
        EdgeToEdge.DefaultDarkScrim = Color.argb(0x80, 27, 27, 27);
    }

    public static final void enable(ComponentActivity componentActivity0) {
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        EdgeToEdge.enable$default(componentActivity0, null, null, 3, null);
    }

    public static final void enable(ComponentActivity componentActivity0, SystemBarStyle systemBarStyle0) {
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        Intrinsics.checkNotNullParameter(systemBarStyle0, "statusBarStyle");
        EdgeToEdge.enable$default(componentActivity0, systemBarStyle0, null, 2, null);
    }

    public static final void enable(ComponentActivity componentActivity0, SystemBarStyle systemBarStyle0, SystemBarStyle systemBarStyle1) {
        EdgeToEdgeBase edgeToEdgeBase0;
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        Intrinsics.checkNotNullParameter(systemBarStyle0, "statusBarStyle");
        Intrinsics.checkNotNullParameter(systemBarStyle1, "navigationBarStyle");
        View view0 = componentActivity0.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view0, "window.decorView");
        Resources resources0 = view0.getResources();
        Intrinsics.checkNotNullExpressionValue(resources0, "view.resources");
        boolean z = ((Boolean)systemBarStyle0.getDetectDarkMode$activity_release().invoke(resources0)).booleanValue();
        Resources resources1 = view0.getResources();
        Intrinsics.checkNotNullExpressionValue(resources1, "view.resources");
        boolean z1 = ((Boolean)systemBarStyle1.getDetectDarkMode$activity_release().invoke(resources1)).booleanValue();
        EdgeToEdgeImpl edgeToEdgeImpl0 = EdgeToEdge.Impl;
        if(edgeToEdgeImpl0 == null) {
            if(Build.VERSION.SDK_INT >= 30) {
                edgeToEdgeBase0 = new EdgeToEdgeApi30();
            }
            else if(Build.VERSION.SDK_INT >= 29) {
                edgeToEdgeBase0 = new EdgeToEdgeApi29();
            }
            else if(Build.VERSION.SDK_INT >= 28) {
                edgeToEdgeBase0 = new EdgeToEdgeApi28();
            }
            else if(Build.VERSION.SDK_INT >= 26) {
                edgeToEdgeBase0 = new EdgeToEdgeApi26();
            }
            else if(Build.VERSION.SDK_INT >= 23) {
                edgeToEdgeBase0 = new EdgeToEdgeApi23();
            }
            else {
                edgeToEdgeBase0 = new EdgeToEdgeApi21();
                EdgeToEdge.Impl = edgeToEdgeBase0;
            }
            edgeToEdgeImpl0 = edgeToEdgeBase0;
        }
        Window window0 = componentActivity0.getWindow();
        Intrinsics.checkNotNullExpressionValue(window0, "window");
        edgeToEdgeImpl0.setUp(systemBarStyle0, systemBarStyle1, window0, view0, z, z1);
        Window window1 = componentActivity0.getWindow();
        Intrinsics.checkNotNullExpressionValue(window1, "window");
        edgeToEdgeImpl0.adjustLayoutInDisplayCutoutMode(window1);
    }

    public static void enable$default(ComponentActivity componentActivity0, SystemBarStyle systemBarStyle0, SystemBarStyle systemBarStyle1, int v, Object object0) {
        if((v & 1) != 0) {
            systemBarStyle0 = Companion.auto$default(SystemBarStyle.Companion, 0, 0, null, 4, null);
        }
        if((v & 2) != 0) {
            systemBarStyle1 = Companion.auto$default(SystemBarStyle.Companion, EdgeToEdge.DefaultLightScrim, EdgeToEdge.DefaultDarkScrim, null, 4, null);
        }
        EdgeToEdge.enable(componentActivity0, systemBarStyle0, systemBarStyle1);
    }

    public static final int getDefaultDarkScrim() {
        return EdgeToEdge.DefaultDarkScrim;
    }

    public static void getDefaultDarkScrim$annotations() {
    }

    public static final int getDefaultLightScrim() {
        return EdgeToEdge.DefaultLightScrim;
    }

    public static void getDefaultLightScrim$annotations() {
    }
}

