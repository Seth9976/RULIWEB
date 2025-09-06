package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.ReplaceWith;
import androidx.core.R.id;

public final class ViewGroupCompat {
    static class Api21Impl {
        static int getNestedScrollAxes(ViewGroup viewGroup0) {
            return viewGroup0.getNestedScrollAxes();
        }

        static boolean isTransitionGroup(ViewGroup viewGroup0) {
            return viewGroup0.isTransitionGroup();
        }

        static void setTransitionGroup(ViewGroup viewGroup0, boolean z) {
            viewGroup0.setTransitionGroup(z);
        }
    }

    private static final WindowInsets CONSUMED = null;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;
    static boolean sCompatInsetsDispatchInstalled;

    static {
        ViewGroupCompat.CONSUMED = WindowInsetsCompat.CONSUMED.toWindowInsets();
        ViewGroupCompat.sCompatInsetsDispatchInstalled = false;
    }

    static WindowInsets dispatchApplyWindowInsets(View view0, WindowInsets windowInsets0) {
        View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0;
        Object object0 = view0.getTag(id.tag_on_apply_window_listener);
        Object object1 = view0.getTag(id.tag_window_insets_animation_callback);
        if(object0 instanceof View.OnApplyWindowInsetsListener) {
            view$OnApplyWindowInsetsListener0 = (View.OnApplyWindowInsetsListener)object0;
        }
        else {
            view$OnApplyWindowInsetsListener0 = object1 instanceof View.OnApplyWindowInsetsListener ? ((View.OnApplyWindowInsetsListener)object1) : null;
        }
        WindowInsets[] arr_windowInsets = new WindowInsets[1];
        view0.setOnApplyWindowInsetsListener((View view0, WindowInsets windowInsets0) -> {
            arr_windowInsets[0] = view$OnApplyWindowInsetsListener0 == null ? view0.onApplyWindowInsets(windowInsets0) : view$OnApplyWindowInsetsListener0.onApplyWindowInsets(view0, windowInsets0);
            return ViewGroupCompat.CONSUMED;
        });
        view0.dispatchApplyWindowInsets(windowInsets0);
        Object object2 = view0.getTag(id.tag_compat_insets_dispatch);
        if(object2 instanceof View.OnApplyWindowInsetsListener) {
            view$OnApplyWindowInsetsListener0 = (View.OnApplyWindowInsetsListener)object2;
        }
        view0.setOnApplyWindowInsetsListener(view$OnApplyWindowInsetsListener0);
        WindowInsets windowInsets1 = arr_windowInsets[0];
        if(windowInsets1 != null && !windowInsets1.isConsumed() && view0 instanceof ViewGroup) {
            int v = ((ViewGroup)view0).getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                ViewGroupCompat.dispatchApplyWindowInsets(((ViewGroup)view0).getChildAt(v1), arr_windowInsets[0]);
            }
        }
        return arr_windowInsets[0];
    }

    @ReplaceWith(expression = "group.getLayoutMode()")
    @Deprecated
    public static int getLayoutMode(ViewGroup viewGroup0) {
        return viewGroup0.getLayoutMode();
    }

    public static int getNestedScrollAxes(ViewGroup viewGroup0) {
        return Api21Impl.getNestedScrollAxes(viewGroup0);
    }

    public static void installCompatInsetsDispatch(View view0) {
        if(Build.VERSION.SDK_INT >= 30) {
            return;
        }
        ViewGroupCompat..ExternalSyntheticLambda1 viewGroupCompat$$ExternalSyntheticLambda10 = (View view0, WindowInsets windowInsets0) -> {
            ViewGroupCompat.dispatchApplyWindowInsets(view0, windowInsets0);
            return ViewGroupCompat.CONSUMED;
        };
        view0.setTag(id.tag_compat_insets_dispatch, viewGroupCompat$$ExternalSyntheticLambda10);
        view0.setOnApplyWindowInsetsListener(viewGroupCompat$$ExternalSyntheticLambda10);
        ViewGroupCompat.sCompatInsetsDispatchInstalled = true;
    }

    public static boolean isTransitionGroup(ViewGroup viewGroup0) {
        return Api21Impl.isTransitionGroup(viewGroup0);
    }

    // 检测为 Lambda 实现
    static WindowInsets lambda$dispatchApplyWindowInsets$1(WindowInsets[] arr_windowInsets, View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0, View view0, WindowInsets windowInsets0) [...]

    // 检测为 Lambda 实现
    static WindowInsets lambda$installCompatInsetsDispatch$0(View view0, WindowInsets windowInsets0) [...]

    @ReplaceWith(expression = "group.onRequestSendAccessibilityEvent(child, event)")
    @Deprecated
    public static boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
        return viewGroup0.onRequestSendAccessibilityEvent(view0, accessibilityEvent0);
    }

    @ReplaceWith(expression = "group.setLayoutMode(mode)")
    @Deprecated
    public static void setLayoutMode(ViewGroup viewGroup0, int v) {
        viewGroup0.setLayoutMode(v);
    }

    @ReplaceWith(expression = "group.setMotionEventSplittingEnabled(split)")
    @Deprecated
    public static void setMotionEventSplittingEnabled(ViewGroup viewGroup0, boolean z) {
        viewGroup0.setMotionEventSplittingEnabled(z);
    }

    public static void setTransitionGroup(ViewGroup viewGroup0, boolean z) {
        Api21Impl.setTransitionGroup(viewGroup0, z);
    }
}

