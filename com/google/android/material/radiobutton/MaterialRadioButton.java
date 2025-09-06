package com.google.android.material.radiobutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.CompoundButtonCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialRadioButton extends AppCompatRadioButton {
    private static final int DEF_STYLE_RES;
    private static final int[][] ENABLED_CHECKED_STATES;
    private ColorStateList materialThemeColorsTintList;
    private boolean useMaterialThemeColors;

    static {
        MaterialRadioButton.DEF_STYLE_RES = style.Widget_MaterialComponents_CompoundButton_RadioButton;
        MaterialRadioButton.ENABLED_CHECKED_STATES = new int[][]{new int[]{0x101009E, 0x10100A0}, new int[]{0x101009E, 0xFEFEFF60}, new int[]{0xFEFEFF62, 0x10100A0}, new int[]{0xFEFEFF62, 0xFEFEFF60}};
    }

    public MaterialRadioButton(Context context0) {
        this(context0, null);
    }

    public MaterialRadioButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.radioButtonStyle);
    }

    public MaterialRadioButton(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialRadioButton.DEF_STYLE_RES), attributeSet0, v);
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.MaterialRadioButton, v, MaterialRadioButton.DEF_STYLE_RES, new int[0]);
        if(typedArray0.hasValue(styleable.MaterialRadioButton_buttonTint)) {
            CompoundButtonCompat.setButtonTintList(this, MaterialResources.getColorStateList(context1, typedArray0, styleable.MaterialRadioButton_buttonTint));
        }
        this.useMaterialThemeColors = typedArray0.getBoolean(styleable.MaterialRadioButton_useMaterialThemeColors, false);
        typedArray0.recycle();
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if(this.materialThemeColorsTintList == null) {
            int v = MaterialColors.getColor(this, attr.colorControlActivated);
            int v1 = MaterialColors.getColor(this, attr.colorOnSurface);
            int v2 = MaterialColors.getColor(this, attr.colorSurface);
            int[] arr_v = new int[MaterialRadioButton.ENABLED_CHECKED_STATES.length];
            arr_v[0] = MaterialColors.layer(v2, v, 1.0f);
            arr_v[1] = MaterialColors.layer(v2, v1, 0.54f);
            arr_v[2] = MaterialColors.layer(v2, v1, 0.38f);
            arr_v[3] = MaterialColors.layer(v2, v1, 0.38f);
            this.materialThemeColorsTintList = new ColorStateList(MaterialRadioButton.ENABLED_CHECKED_STATES, arr_v);
        }
        return this.materialThemeColorsTintList;
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    @Override  // android.widget.TextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.useMaterialThemeColors && CompoundButtonCompat.getButtonTintList(this) == null) {
            this.setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.useMaterialThemeColors = z;
        if(z) {
            CompoundButtonCompat.setButtonTintList(this, this.getMaterialThemeColorsTintList());
            return;
        }
        CompoundButtonCompat.setButtonTintList(this, null);
    }
}

