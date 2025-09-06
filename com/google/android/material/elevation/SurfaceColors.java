package com.google.android.material.elevation;

import android.content.Context;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.color.MaterialColors;

public enum SurfaceColors {
    SURFACE_0(dimen.m3_sys_elevation_level0),
    SURFACE_1(dimen.m3_sys_elevation_level1),
    SURFACE_2(dimen.m3_sys_elevation_level2),
    SURFACE_3(dimen.m3_sys_elevation_level3),
    SURFACE_4(dimen.m3_sys_elevation_level4),
    SURFACE_5(dimen.m3_sys_elevation_level5);

    private final int elevationResId;

    private SurfaceColors(int v1) {
        this.elevationResId = v1;
    }

    public int getColor(Context context0) {
        return SurfaceColors.getColorForElevation(context0, context0.getResources().getDimension(this.elevationResId));
    }

    public static int getColorForElevation(Context context0, float f) {
        return new ElevationOverlayProvider(context0).compositeOverlay(MaterialColors.getColor(context0, attr.colorSurface, 0), f);
    }
}

