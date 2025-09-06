package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class LinearSmoothScroller extends SmoothScroller {
    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25.0f;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2f;
    private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
    protected final DecelerateInterpolator mDecelerateInterpolator;
    private final DisplayMetrics mDisplayMetrics;
    private boolean mHasCalculatedMillisPerPixel;
    protected int mInterimTargetDx;
    protected int mInterimTargetDy;
    protected final LinearInterpolator mLinearInterpolator;
    private float mMillisPerPixel;
    protected PointF mTargetVector;

    public LinearSmoothScroller(Context context0) {
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mHasCalculatedMillisPerPixel = false;
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.mDisplayMetrics = context0.getResources().getDisplayMetrics();
    }

    public int calculateDtToFit(int v, int v1, int v2, int v3, int v4) {
        switch(v4) {
            case -1: {
                return v2 - v;
            }
            case 0: {
                int v5 = v2 - v;
                if(v5 > 0) {
                    return v5;
                }
                int v6 = v3 - v1;
                return v6 >= 0 ? 0 : v6;
            }
            default: {
                if(v4 != 1) {
                    throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
                }
                return v3 - v1;
            }
        }
    }

    public int calculateDxToMakeVisible(View view0, int v) {
        LayoutManager recyclerView$LayoutManager0 = this.getLayoutManager();
        if(recyclerView$LayoutManager0 != null && recyclerView$LayoutManager0.canScrollHorizontally()) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            return this.calculateDtToFit(recyclerView$LayoutManager0.getDecoratedLeft(view0) - recyclerView$LayoutParams0.leftMargin, recyclerView$LayoutManager0.getDecoratedRight(view0) + recyclerView$LayoutParams0.rightMargin, recyclerView$LayoutManager0.getPaddingLeft(), recyclerView$LayoutManager0.getWidth() - recyclerView$LayoutManager0.getPaddingRight(), v);
        }
        return 0;
    }

    public int calculateDyToMakeVisible(View view0, int v) {
        LayoutManager recyclerView$LayoutManager0 = this.getLayoutManager();
        if(recyclerView$LayoutManager0 != null && recyclerView$LayoutManager0.canScrollVertically()) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            return this.calculateDtToFit(recyclerView$LayoutManager0.getDecoratedTop(view0) - recyclerView$LayoutParams0.topMargin, recyclerView$LayoutManager0.getDecoratedBottom(view0) + recyclerView$LayoutParams0.bottomMargin, recyclerView$LayoutManager0.getPaddingTop(), recyclerView$LayoutManager0.getHeight() - recyclerView$LayoutManager0.getPaddingBottom(), v);
        }
        return 0;
    }

    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics0) {
        return 25.0f / ((float)displayMetrics0.densityDpi);
    }

    protected int calculateTimeForDeceleration(int v) {
        return (int)Math.ceil(((double)this.calculateTimeForScrolling(v)) / 0.3356);
    }

    protected int calculateTimeForScrolling(int v) {
        return (int)Math.ceil(((float)Math.abs(v)) * this.getSpeedPerPixel());
    }

    private int clampApplyScroll(int v, int v1) {
        int v2 = v - v1;
        return v * v2 > 0 ? v2 : 0;
    }

    protected int getHorizontalSnapPreference() {
        if(this.mTargetVector != null && this.mTargetVector.x != 0.0f) {
            return this.mTargetVector.x > 0.0f ? 1 : -1;
        }
        return 0;
    }

    private float getSpeedPerPixel() {
        if(!this.mHasCalculatedMillisPerPixel) {
            this.mMillisPerPixel = this.calculateSpeedPerPixel(this.mDisplayMetrics);
            this.mHasCalculatedMillisPerPixel = true;
        }
        return this.mMillisPerPixel;
    }

    protected int getVerticalSnapPreference() {
        if(this.mTargetVector != null && this.mTargetVector.y != 0.0f) {
            return this.mTargetVector.y > 0.0f ? 1 : -1;
        }
        return 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller
    protected void onSeekTargetStep(int v, int v1, State recyclerView$State0, Action recyclerView$SmoothScroller$Action0) {
        if(this.getChildCount() == 0) {
            this.stop();
            return;
        }
        this.mInterimTargetDx = this.clampApplyScroll(this.mInterimTargetDx, v);
        int v2 = this.clampApplyScroll(this.mInterimTargetDy, v1);
        this.mInterimTargetDy = v2;
        if(this.mInterimTargetDx == 0 && v2 == 0) {
            this.updateActionForInterimTarget(recyclerView$SmoothScroller$Action0);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller
    protected void onStart() {
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller
    protected void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller
    protected void onTargetFound(View view0, State recyclerView$State0, Action recyclerView$SmoothScroller$Action0) {
        int v = this.calculateDxToMakeVisible(view0, this.getHorizontalSnapPreference());
        int v1 = this.calculateDyToMakeVisible(view0, this.getVerticalSnapPreference());
        int v2 = this.calculateTimeForDeceleration(((int)Math.sqrt(v * v + v1 * v1)));
        if(v2 > 0) {
            recyclerView$SmoothScroller$Action0.update(-v, -v1, v2, this.mDecelerateInterpolator);
        }
    }

    protected void updateActionForInterimTarget(Action recyclerView$SmoothScroller$Action0) {
        PointF pointF0 = this.computeScrollVectorForPosition(this.getTargetPosition());
        if(pointF0 != null && (pointF0.x != 0.0f || pointF0.y != 0.0f)) {
            this.normalize(pointF0);
            this.mTargetVector = pointF0;
            this.mInterimTargetDx = (int)(pointF0.x * 10000.0f);
            this.mInterimTargetDy = (int)(pointF0.y * 10000.0f);
            int v = this.calculateTimeForScrolling(10000);
            recyclerView$SmoothScroller$Action0.update(((int)(((float)this.mInterimTargetDx) * 1.2f)), ((int)(((float)this.mInterimTargetDy) * 1.2f)), ((int)(((float)v) * 1.2f)), this.mLinearInterpolator);
            return;
        }
        recyclerView$SmoothScroller$Action0.jumpTo(this.getTargetPosition());
        this.stop();
    }
}

