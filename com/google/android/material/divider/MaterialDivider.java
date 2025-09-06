package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialDivider extends View {
    private static final int DEF_STYLE_RES;
    private int color;
    private final MaterialShapeDrawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private int thickness;

    static {
        MaterialDivider.DEF_STYLE_RES = style.Widget_MaterialComponents_MaterialDivider;
    }

    public MaterialDivider(Context context0) {
        this(context0, null);
    }

    public MaterialDivider(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialDividerStyle);
    }

    public MaterialDivider(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialDivider.DEF_STYLE_RES), attributeSet0, v);
        Context context1 = this.getContext();
        this.dividerDrawable = new MaterialShapeDrawable();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.MaterialDivider, v, MaterialDivider.DEF_STYLE_RES, new int[0]);
        int v1 = this.getResources().getDimensionPixelSize(dimen.material_divider_thickness);
        this.thickness = typedArray0.getDimensionPixelSize(styleable.MaterialDivider_dividerThickness, v1);
        this.insetStart = typedArray0.getDimensionPixelOffset(styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = typedArray0.getDimensionPixelOffset(styleable.MaterialDivider_dividerInsetEnd, 0);
        this.setDividerColor(MaterialResources.getColorStateList(context1, typedArray0, styleable.MaterialDivider_dividerColor).getDefaultColor());
        typedArray0.recycle();
    }

    public int getDividerColor() {
        return this.color;
    }

    public int getDividerInsetEnd() {
        return this.insetEnd;
    }

    public int getDividerInsetStart() {
        return this.insetStart;
    }

    public int getDividerThickness() {
        return this.thickness;
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        int v = ViewCompat.getLayoutDirection(this) == 1 ? this.insetEnd : this.insetStart;
        int v1 = this.getWidth();
        int v2 = this.insetStart;
        int v3 = this.getBottom();
        int v4 = this.getTop();
        this.dividerDrawable.setBounds(v, 0, v1 - v2, v3 - v4);
        this.dividerDrawable.draw(canvas0);
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        int v2 = View.MeasureSpec.getMode(v1);
        int v3 = this.getMeasuredHeight();
        if(v2 != 0x80000000 && v2 != 0) {
            return;
        }
        int v4 = this.thickness;
        if(v4 > 0 && v3 != v4) {
            v3 = v4;
        }
        this.setMeasuredDimension(this.getMeasuredWidth(), v3);
    }

    public void setDividerColor(int v) {
        if(this.color != v) {
            this.color = v;
            ColorStateList colorStateList0 = ColorStateList.valueOf(v);
            this.dividerDrawable.setFillColor(colorStateList0);
            this.invalidate();
        }
    }

    public void setDividerColorResource(int v) {
        this.setDividerColor(ContextCompat.getColor(this.getContext(), v));
    }

    public void setDividerInsetEnd(int v) {
        this.insetEnd = v;
    }

    public void setDividerInsetEndResource(int v) {
        this.setDividerInsetEnd(this.getContext().getResources().getDimensionPixelOffset(v));
    }

    public void setDividerInsetStart(int v) {
        this.insetStart = v;
    }

    public void setDividerInsetStartResource(int v) {
        this.setDividerInsetStart(this.getContext().getResources().getDimensionPixelOffset(v));
    }

    public void setDividerThickness(int v) {
        if(this.thickness != v) {
            this.thickness = v;
            this.requestLayout();
        }
    }

    public void setDividerThicknessResource(int v) {
        this.setDividerThickness(this.getContext().getResources().getDimensionPixelSize(v));
    }
}

