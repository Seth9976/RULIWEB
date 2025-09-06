package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.styleable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

class RadialViewGroup extends ConstraintLayout {
    static final int LEVEL_1 = 1;
    static final int LEVEL_2 = 2;
    static final float LEVEL_RADIUS_RATIO = 0.66f;
    private static final String SKIP_TAG = "skip";
    private MaterialShapeDrawable background;
    private int radius;
    private final Runnable updateLayoutParametersRunnable;

    public RadialViewGroup(Context context0) {
        this(context0, null);
    }

    public RadialViewGroup(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public RadialViewGroup(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        LayoutInflater.from(context0).inflate(layout.material_radial_view_group, this);
        ViewCompat.setBackground(this, this.createBackground());
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.RadialViewGroup, v, 0);
        this.radius = typedArray0.getDimensionPixelSize(styleable.RadialViewGroup_materialCircleRadius, 0);
        this.updateLayoutParametersRunnable = new RadialViewGroup..ExternalSyntheticLambda0(this);
        typedArray0.recycle();
    }

    private void addConstraints(List list0, ConstraintSet constraintSet0, int v) {
        float f = 0.0f;
        for(Object object0: list0) {
            constraintSet0.constrainCircle(((View)object0).getId(), id.circle_center, v, f);
            f += 360.0f / ((float)list0.size());
        }
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.addView(view0, v, viewGroup$LayoutParams0);
        if(view0.getId() == -1) {
            view0.setId(ViewCompat.generateViewId());
        }
        this.updateLayoutParamsAsync();
    }

    private Drawable createBackground() {
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
        this.background = materialShapeDrawable0;
        materialShapeDrawable0.setCornerSize(new RelativeCornerSize(0.5f));
        this.background.setFillColor(ColorStateList.valueOf(-1));
        return this.background;
    }

    int getLeveledRadius(int v) {
        return v == 2 ? Math.round(((float)this.radius) * 0.66f) : this.radius;
    }

    public int getRadius() {
        return this.radius;
    }

    @Override  // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.updateLayoutParams();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    public void onViewRemoved(View view0) {
        super.onViewRemoved(view0);
        this.updateLayoutParamsAsync();
    }

    @Override  // android.view.View
    public void setBackgroundColor(int v) {
        this.background.setFillColor(ColorStateList.valueOf(v));
    }

    public void setRadius(int v) {
        this.radius = v;
        this.updateLayoutParams();
    }

    private static boolean shouldSkipView(View view0) {
        return "skip".equals(view0.getTag());
    }

    protected void updateLayoutParams() {
        ConstraintSet constraintSet0 = new ConstraintSet();
        constraintSet0.clone(this);
        HashMap hashMap0 = new HashMap();
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view0 = this.getChildAt(v);
            if(view0.getId() != id.circle_center && !RadialViewGroup.shouldSkipView(view0)) {
                Integer integer0 = (Integer)view0.getTag(id.material_clock_level);
                if(integer0 == null) {
                    integer0 = 1;
                }
                if(!hashMap0.containsKey(integer0)) {
                    hashMap0.put(integer0, new ArrayList());
                }
                ((List)hashMap0.get(integer0)).add(view0);
            }
        }
        for(Object object0: hashMap0.entrySet()) {
            this.addConstraints(((List)((Map.Entry)object0).getValue()), constraintSet0, this.getLeveledRadius(((int)(((Integer)((Map.Entry)object0).getKey())))));
        }
        constraintSet0.applyTo(this);
    }

    private void updateLayoutParamsAsync() {
        Handler handler0 = this.getHandler();
        if(handler0 != null) {
            handler0.removeCallbacks(this.updateLayoutParametersRunnable);
            handler0.post(this.updateLayoutParametersRunnable);
        }
    }
}

