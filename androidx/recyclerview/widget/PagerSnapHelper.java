package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

public class PagerSnapHelper extends SnapHelper {
    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    @Override  // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(LayoutManager recyclerView$LayoutManager0, View view0) {
        int[] arr_v = new int[2];
        arr_v[0] = recyclerView$LayoutManager0.canScrollHorizontally() ? this.distanceToCenter(view0, this.getHorizontalHelper(recyclerView$LayoutManager0)) : 0;
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            arr_v[1] = this.distanceToCenter(view0, this.getVerticalHelper(recyclerView$LayoutManager0));
            return arr_v;
        }
        arr_v[1] = 0;
        return arr_v;
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    protected SmoothScroller createScroller(LayoutManager recyclerView$LayoutManager0) {
        return !(recyclerView$LayoutManager0 instanceof ScrollVectorProvider) ? null : new LinearSmoothScroller(this.mRecyclerView.getContext()) {
            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics0) {
                return 100.0f / ((float)displayMetrics0.densityDpi);
            }

            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected int calculateTimeForScrolling(int v) {
                return Math.min(100, super.calculateTimeForScrolling(v));
            }

            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected void onTargetFound(View view0, State recyclerView$State0, Action recyclerView$SmoothScroller$Action0) {
                int[] arr_v = PagerSnapHelper.this.calculateDistanceToFinalSnap(PagerSnapHelper.this.mRecyclerView.getLayoutManager(), view0);
                int v = arr_v[0];
                int v1 = arr_v[1];
                int v2 = this.calculateTimeForDeceleration(Math.max(Math.abs(v), Math.abs(v1)));
                if(v2 > 0) {
                    recyclerView$SmoothScroller$Action0.update(v, v1, v2, this.mDecelerateInterpolator);
                }
            }
        };
    }

    private int distanceToCenter(View view0, OrientationHelper orientationHelper0) {
        return orientationHelper0.getDecoratedStart(view0) + orientationHelper0.getDecoratedMeasurement(view0) / 2 - (orientationHelper0.getStartAfterPadding() + orientationHelper0.getTotalSpace() / 2);
    }

    private View findCenterView(LayoutManager recyclerView$LayoutManager0, OrientationHelper orientationHelper0) {
        int v = recyclerView$LayoutManager0.getChildCount();
        View view0 = null;
        if(v == 0) {
            return null;
        }
        int v1 = orientationHelper0.getStartAfterPadding();
        int v2 = orientationHelper0.getTotalSpace();
        int v3 = 0x7FFFFFFF;
        for(int v4 = 0; v4 < v; ++v4) {
            View view1 = recyclerView$LayoutManager0.getChildAt(v4);
            int v5 = Math.abs(orientationHelper0.getDecoratedStart(view1) + orientationHelper0.getDecoratedMeasurement(view1) / 2 - (v1 + v2 / 2));
            if(v5 < v3) {
                view0 = view1;
                v3 = v5;
            }
        }
        return view0;
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(LayoutManager recyclerView$LayoutManager0) {
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            return this.findCenterView(recyclerView$LayoutManager0, this.getVerticalHelper(recyclerView$LayoutManager0));
        }
        return recyclerView$LayoutManager0.canScrollHorizontally() ? this.findCenterView(recyclerView$LayoutManager0, this.getHorizontalHelper(recyclerView$LayoutManager0)) : null;
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(LayoutManager recyclerView$LayoutManager0, int v, int v1) {
        int v2 = recyclerView$LayoutManager0.getItemCount();
        if(v2 == 0) {
            return -1;
        }
        OrientationHelper orientationHelper0 = this.getOrientationHelper(recyclerView$LayoutManager0);
        if(orientationHelper0 == null) {
            return -1;
        }
        int v3 = recyclerView$LayoutManager0.getChildCount();
        View view0 = null;
        View view1 = null;
        int v4 = 0x80000000;
        int v5 = 0x7FFFFFFF;
        for(int v6 = 0; v6 < v3; ++v6) {
            View view2 = recyclerView$LayoutManager0.getChildAt(v6);
            if(view2 != null) {
                int v7 = this.distanceToCenter(view2, orientationHelper0);
                if(v7 <= 0 && v7 > v4) {
                    view1 = view2;
                    v4 = v7;
                }
                if(v7 >= 0 && v7 < v5) {
                    view0 = view2;
                    v5 = v7;
                }
            }
        }
        boolean z = this.isForwardFling(recyclerView$LayoutManager0, v, v1);
        if(z && view0 != null) {
            return recyclerView$LayoutManager0.getPosition(view0);
        }
        if(!z && view1 != null) {
            return recyclerView$LayoutManager0.getPosition(view1);
        }
        if(z) {
            view0 = view1;
        }
        if(view0 == null) {
            return -1;
        }
        int v8 = recyclerView$LayoutManager0.getPosition(view0) + (this.isReverseLayout(recyclerView$LayoutManager0) == z ? -1 : 1);
        return v8 < 0 || v8 >= v2 ? -1 : v8;
    }

    private OrientationHelper getHorizontalHelper(LayoutManager recyclerView$LayoutManager0) {
        if(this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != recyclerView$LayoutManager0) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(recyclerView$LayoutManager0);
        }
        return this.mHorizontalHelper;
    }

    private OrientationHelper getOrientationHelper(LayoutManager recyclerView$LayoutManager0) {
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            return this.getVerticalHelper(recyclerView$LayoutManager0);
        }
        return recyclerView$LayoutManager0.canScrollHorizontally() ? this.getHorizontalHelper(recyclerView$LayoutManager0) : null;
    }

    private OrientationHelper getVerticalHelper(LayoutManager recyclerView$LayoutManager0) {
        if(this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != recyclerView$LayoutManager0) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(recyclerView$LayoutManager0);
        }
        return this.mVerticalHelper;
    }

    // 去混淆评级： 低(20)
    private boolean isForwardFling(LayoutManager recyclerView$LayoutManager0, int v, int v1) {
        return recyclerView$LayoutManager0.canScrollHorizontally() ? v > 0 : v1 > 0;
    }

    private boolean isReverseLayout(LayoutManager recyclerView$LayoutManager0) {
        int v = recyclerView$LayoutManager0.getItemCount();
        if(recyclerView$LayoutManager0 instanceof ScrollVectorProvider) {
            PointF pointF0 = ((ScrollVectorProvider)recyclerView$LayoutManager0).computeScrollVectorForPosition(v - 1);
            return pointF0 != null && (pointF0.x < 0.0f || pointF0.y < 0.0f);
        }
        return false;
    }
}

