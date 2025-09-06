package com.google.android.material.shape;

import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

public class InterpolateOnScrollPositionChangeHelper {
    private final int[] containerLocation;
    private ScrollView containingScrollView;
    private MaterialShapeDrawable materialShapeDrawable;
    private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    private final int[] scrollLocation;
    private View shapedView;

    public InterpolateOnScrollPositionChangeHelper(View view0, MaterialShapeDrawable materialShapeDrawable0, ScrollView scrollView0) {
        this.scrollLocation = new int[2];
        this.containerLocation = new int[2];
        this.scrollChangedListener = () -> {
            ScrollView scrollView0 = InterpolateOnScrollPositionChangeHelper.this.containingScrollView;
            if(scrollView0 != null) {
                if(scrollView0.getChildCount() == 0) {
                    throw new IllegalStateException("Scroll bar must contain a child to calculate interpolation.");
                }
                InterpolateOnScrollPositionChangeHelper.this.containingScrollView.getLocationInWindow(InterpolateOnScrollPositionChangeHelper.this.scrollLocation);
                InterpolateOnScrollPositionChangeHelper.this.containingScrollView.getChildAt(0).getLocationInWindow(InterpolateOnScrollPositionChangeHelper.this.containerLocation);
                int v = InterpolateOnScrollPositionChangeHelper.this.shapedView.getTop() - InterpolateOnScrollPositionChangeHelper.this.scrollLocation[1] + InterpolateOnScrollPositionChangeHelper.this.containerLocation[1];
                int v1 = InterpolateOnScrollPositionChangeHelper.this.shapedView.getHeight();
                int v2 = InterpolateOnScrollPositionChangeHelper.this.containingScrollView.getHeight();
                if(v < 0) {
                    InterpolateOnScrollPositionChangeHelper.this.materialShapeDrawable.setInterpolation(Math.max(0.0f, Math.min(1.0f, ((float)v) / ((float)v1) + 1.0f)));
                    InterpolateOnScrollPositionChangeHelper.this.shapedView.invalidate();
                    return;
                }
                int v3 = v + v1;
                if(v3 > v2) {
                    InterpolateOnScrollPositionChangeHelper.this.materialShapeDrawable.setInterpolation(Math.max(0.0f, Math.min(1.0f, 1.0f - ((float)(v3 - v2)) / ((float)v1))));
                    InterpolateOnScrollPositionChangeHelper.this.shapedView.invalidate();
                    return;
                }
                if(InterpolateOnScrollPositionChangeHelper.this.materialShapeDrawable.getInterpolation() != 1.0f) {
                    InterpolateOnScrollPositionChangeHelper.this.materialShapeDrawable.setInterpolation(1.0f);
                    InterpolateOnScrollPositionChangeHelper.this.shapedView.invalidate();
                }
            }
        };
        this.shapedView = view0;
        this.materialShapeDrawable = materialShapeDrawable0;
        this.containingScrollView = scrollView0;
    }

    public void setContainingScrollView(ScrollView scrollView0) {
        this.containingScrollView = scrollView0;
    }

    public void setMaterialShapeDrawable(MaterialShapeDrawable materialShapeDrawable0) {
        this.materialShapeDrawable = materialShapeDrawable0;
    }

    public void startListeningForScrollChanges(ViewTreeObserver viewTreeObserver0) {
        viewTreeObserver0.addOnScrollChangedListener(this.scrollChangedListener);
    }

    public void stopListeningForScrollChanges(ViewTreeObserver viewTreeObserver0) {
        viewTreeObserver0.removeOnScrollChangedListener(this.scrollChangedListener);
    }

    // 检测为 Lambda 实现
    public void updateInterpolationForScreenPosition() [...]

    class com.google.android.material.shape.InterpolateOnScrollPositionChangeHelper.1 implements ViewTreeObserver.OnScrollChangedListener {
        @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
        public void onScrollChanged() {
            InterpolateOnScrollPositionChangeHelper.this.updateInterpolationForScreenPosition();
        }
    }

}

