package com.google.android.material.color;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.view.View;
import android.view.Window;

public final class ThemeUtils {
    public static void applyThemeOverlay(Context context0, int v) {
        context0.getTheme().applyStyle(v, true);
        if(context0 instanceof Activity) {
            Resources.Theme resources$Theme0 = ThemeUtils.getWindowDecorViewTheme(((Activity)context0));
            if(resources$Theme0 != null) {
                resources$Theme0.applyStyle(v, true);
            }
        }
    }

    private static Resources.Theme getWindowDecorViewTheme(Activity activity0) {
        Window window0 = activity0.getWindow();
        if(window0 != null) {
            View view0 = window0.peekDecorView();
            if(view0 != null) {
                Context context0 = view0.getContext();
                return context0 == null ? null : context0.getTheme();
            }
        }
        return null;
    }
}

