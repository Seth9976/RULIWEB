package com.google.android.material.internal;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.Window;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.WindowCompat;
import com.google.android.material.color.MaterialColors;

public class EdgeToEdgeUtils {
    private static final int EDGE_TO_EDGE_BAR_ALPHA = 0x80;

    public static void applyEdgeToEdge(Window window0, boolean z) {
        EdgeToEdgeUtils.applyEdgeToEdge(window0, z, null, null);
    }

    public static void applyEdgeToEdge(Window window0, boolean z, Integer integer0, Integer integer1) {
        boolean z1 = false;
        boolean z2 = integer0 == null || ((int)integer0) == 0;
        if(integer1 == null || ((int)integer1) == 0) {
            z1 = true;
        }
        if(z2 || z1) {
            int v = MaterialColors.getColor(window0.getContext(), 0x1010031, 0xFF000000);
            if(z2) {
                integer0 = v;
            }
            if(z1) {
                integer1 = v;
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window0, !z);
        int v1 = EdgeToEdgeUtils.getStatusBarColor(window0.getContext(), z);
        int v2 = EdgeToEdgeUtils.getNavigationBarColor(window0.getContext(), z);
        window0.setStatusBarColor(v1);
        window0.setNavigationBarColor(v2);
        EdgeToEdgeUtils.setLightStatusBar(window0, EdgeToEdgeUtils.isUsingLightSystemBar(v1, MaterialColors.isColorLight(((int)integer0))));
        EdgeToEdgeUtils.setLightNavigationBar(window0, EdgeToEdgeUtils.isUsingLightSystemBar(v2, MaterialColors.isColorLight(((int)integer1))));
    }

    private static int getNavigationBarColor(Context context0, boolean z) {
        if(z && Build.VERSION.SDK_INT < 27) {
            return ColorUtils.setAlphaComponent(MaterialColors.getColor(context0, 0x1010452, 0xFF000000), 0x80);
        }
        return z ? 0 : MaterialColors.getColor(context0, 0x1010452, 0xFF000000);
    }

    private static int getStatusBarColor(Context context0, boolean z) {
        if(z && Build.VERSION.SDK_INT < 23) {
            return ColorUtils.setAlphaComponent(MaterialColors.getColor(context0, 0x1010451, 0xFF000000), 0x80);
        }
        return z ? 0 : MaterialColors.getColor(context0, 0x1010451, 0xFF000000);
    }

    // 去混淆评级： 低(30)
    private static boolean isUsingLightSystemBar(int v, boolean z) {
        return MaterialColors.isColorLight(v) || v == 0 && z;
    }

    public static void setLightNavigationBar(Window window0, boolean z) {
        WindowCompat.getInsetsController(window0, window0.getDecorView()).setAppearanceLightNavigationBars(z);
    }

    public static void setLightStatusBar(Window window0, boolean z) {
        WindowCompat.getInsetsController(window0, window0.getDecorView()).setAppearanceLightStatusBars(z);
    }
}

