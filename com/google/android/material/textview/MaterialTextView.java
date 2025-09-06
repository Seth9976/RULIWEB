package com.google.android.material.textview;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R.attr;
import com.google.android.material.R.styleable;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context0) {
        this(context0, null);
    }

    public MaterialTextView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0x1010084);
    }

    public MaterialTextView(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, 0), attributeSet0, v);
        this.initialize(attributeSet0, v, 0);
    }

    @Deprecated
    public MaterialTextView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, v1), attributeSet0, v);
        this.initialize(attributeSet0, v, v1);
    }

    private void applyLineHeightFromViewAppearance(Resources.Theme resources$Theme0, int v) {
        TypedArray typedArray0 = resources$Theme0.obtainStyledAttributes(v, styleable.MaterialTextAppearance);
        int v1 = MaterialTextView.readFirstAvailableDimension(this.getContext(), typedArray0, new int[]{styleable.MaterialTextAppearance_android_lineHeight, styleable.MaterialTextAppearance_lineHeight});
        typedArray0.recycle();
        if(v1 >= 0) {
            this.setLineHeight(v1);
        }
    }

    private static boolean canApplyTextAppearanceLineHeight(Context context0) {
        return MaterialAttributes.resolveBoolean(context0, attr.textAppearanceLineHeightEnabled, true);
    }

    private static int findViewAppearanceResourceId(Resources.Theme resources$Theme0, AttributeSet attributeSet0, int v, int v1) {
        TypedArray typedArray0 = resources$Theme0.obtainStyledAttributes(attributeSet0, styleable.MaterialTextView, v, v1);
        int v2 = typedArray0.getResourceId(styleable.MaterialTextView_android_textAppearance, -1);
        typedArray0.recycle();
        return v2;
    }

    private void initialize(AttributeSet attributeSet0, int v, int v1) {
        Context context0 = this.getContext();
        if(MaterialTextView.canApplyTextAppearanceLineHeight(context0)) {
            Resources.Theme resources$Theme0 = context0.getTheme();
            if(!MaterialTextView.viewAttrsHasLineHeight(context0, resources$Theme0, attributeSet0, v, v1)) {
                int v2 = MaterialTextView.findViewAppearanceResourceId(resources$Theme0, attributeSet0, v, v1);
                if(v2 != -1) {
                    this.applyLineHeightFromViewAppearance(resources$Theme0, v2);
                }
            }
        }
    }

    private static int readFirstAvailableDimension(Context context0, TypedArray typedArray0, int[] arr_v) {
        int v1 = -1;
        for(int v = 0; v < arr_v.length && v1 < 0; ++v) {
            v1 = MaterialResources.getDimensionPixelSize(context0, typedArray0, arr_v[v], -1);
        }
        return v1;
    }

    @Override  // androidx.appcompat.widget.AppCompatTextView
    public void setTextAppearance(Context context0, int v) {
        super.setTextAppearance(context0, v);
        if(MaterialTextView.canApplyTextAppearanceLineHeight(context0)) {
            this.applyLineHeightFromViewAppearance(context0.getTheme(), v);
        }
    }

    private static boolean viewAttrsHasLineHeight(Context context0, Resources.Theme resources$Theme0, AttributeSet attributeSet0, int v, int v1) {
        TypedArray typedArray0 = resources$Theme0.obtainStyledAttributes(attributeSet0, styleable.MaterialTextView, v, v1);
        int v2 = MaterialTextView.readFirstAvailableDimension(context0, typedArray0, new int[]{styleable.MaterialTextView_android_lineHeight, styleable.MaterialTextView_lineHeight});
        typedArray0.recycle();
        return v2 != -1;
    }
}

