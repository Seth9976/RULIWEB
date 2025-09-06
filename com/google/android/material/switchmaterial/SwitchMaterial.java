package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class SwitchMaterial extends SwitchCompat {
    private static final int DEF_STYLE_RES;
    private static final int[][] ENABLED_CHECKED_STATES;
    private final ElevationOverlayProvider elevationOverlayProvider;
    private ColorStateList materialThemeColorsThumbTintList;
    private ColorStateList materialThemeColorsTrackTintList;
    private boolean useMaterialThemeColors;

    static {
        SwitchMaterial.DEF_STYLE_RES = style.Widget_MaterialComponents_CompoundButton_Switch;
        SwitchMaterial.ENABLED_CHECKED_STATES = new int[][]{new int[]{0x101009E, 0x10100A0}, new int[]{0x101009E, 0xFEFEFF60}, new int[]{0xFEFEFF62, 0x10100A0}, new int[]{0xFEFEFF62, 0xFEFEFF60}};
    }

    public SwitchMaterial(Context context0) {
        this(context0, null);
    }

    public SwitchMaterial(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.switchStyle);
    }

    public SwitchMaterial(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, SwitchMaterial.DEF_STYLE_RES), attributeSet0, v);
        Context context1 = this.getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(context1);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.SwitchMaterial, v, SwitchMaterial.DEF_STYLE_RES, new int[0]);
        this.useMaterialThemeColors = typedArray0.getBoolean(styleable.SwitchMaterial_useMaterialThemeColors, false);
        typedArray0.recycle();
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if(this.materialThemeColorsThumbTintList == null) {
            int v = MaterialColors.getColor(this, attr.colorSurface);
            int v1 = MaterialColors.getColor(this, attr.colorControlActivated);
            float f = this.getResources().getDimension(dimen.mtrl_switch_thumb_elevation);
            if(this.elevationOverlayProvider.isThemeElevationOverlayEnabled()) {
                f += ViewUtils.getParentAbsoluteElevation(this);
            }
            int v2 = this.elevationOverlayProvider.compositeOverlayIfNeeded(v, f);
            int[] arr_v = new int[SwitchMaterial.ENABLED_CHECKED_STATES.length];
            arr_v[0] = MaterialColors.layer(v, v1, 1.0f);
            arr_v[1] = v2;
            arr_v[2] = MaterialColors.layer(v, v1, 0.38f);
            arr_v[3] = v2;
            this.materialThemeColorsThumbTintList = new ColorStateList(SwitchMaterial.ENABLED_CHECKED_STATES, arr_v);
        }
        return this.materialThemeColorsThumbTintList;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if(this.materialThemeColorsTrackTintList == null) {
            int[] arr_v = new int[SwitchMaterial.ENABLED_CHECKED_STATES.length];
            int v = MaterialColors.getColor(this, attr.colorSurface);
            int v1 = MaterialColors.getColor(this, attr.colorControlActivated);
            int v2 = MaterialColors.getColor(this, attr.colorOnSurface);
            arr_v[0] = MaterialColors.layer(v, v1, 0.54f);
            arr_v[1] = MaterialColors.layer(v, v2, 0.32f);
            arr_v[2] = MaterialColors.layer(v, v1, 0.12f);
            arr_v[3] = MaterialColors.layer(v, v2, 0.12f);
            this.materialThemeColorsTrackTintList = new ColorStateList(SwitchMaterial.ENABLED_CHECKED_STATES, arr_v);
        }
        return this.materialThemeColorsTrackTintList;
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    @Override  // android.widget.TextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.useMaterialThemeColors && this.getThumbTintList() == null) {
            this.setThumbTintList(this.getMaterialThemeColorsThumbTintList());
        }
        if(this.useMaterialThemeColors && this.getTrackTintList() == null) {
            this.setTrackTintList(this.getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.useMaterialThemeColors = z;
        if(z) {
            this.setThumbTintList(this.getMaterialThemeColorsThumbTintList());
            this.setTrackTintList(this.getMaterialThemeColorsTrackTintList());
            return;
        }
        this.setThumbTintList(null);
        this.setTrackTintList(null);
    }
}

