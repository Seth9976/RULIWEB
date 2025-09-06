package com.google.android.material.carousel;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.Action;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class CarouselSnapHelper extends SnapHelper {
    private static final float HORIZONTAL_SNAP_SPEED = 100.0f;
    private static final float VERTICAL_SNAP_SPEED = 50.0f;
    private final boolean disableFling;
    private RecyclerView recyclerView;

    public CarouselSnapHelper() {
        this(true);
    }

    public CarouselSnapHelper(boolean z) {
        this.disableFling = z;
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView0) {
        super.attachToRecyclerView(recyclerView0);
        this.recyclerView = recyclerView0;
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(LayoutManager recyclerView$LayoutManager0, View view0) {
        return this.calculateDistanceToSnap(recyclerView$LayoutManager0, view0, false);
    }

    private int[] calculateDistanceToSnap(LayoutManager recyclerView$LayoutManager0, View view0, boolean z) {
        if(!(recyclerView$LayoutManager0 instanceof CarouselLayoutManager)) {
            return new int[]{0, 0};
        }
        int v = this.distanceToFirstFocalKeyline(view0, ((CarouselLayoutManager)recyclerView$LayoutManager0), z);
        if(recyclerView$LayoutManager0.canScrollHorizontally()) {
            return new int[]{v, 0};
        }
        return recyclerView$LayoutManager0.canScrollVertically() ? new int[]{0, v} : new int[]{0, 0};
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    protected SmoothScroller createScroller(LayoutManager recyclerView$LayoutManager0) {
        return recyclerView$LayoutManager0 instanceof ScrollVectorProvider ? new LinearSmoothScroller(this.recyclerView.getContext()) {
            // 去混淆评级： 低(20)
            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics0) {
                return recyclerView$LayoutManager0.canScrollVertically() ? 50.0f / ((float)displayMetrics0.densityDpi) : 100.0f / ((float)displayMetrics0.densityDpi);
            }

            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected void onTargetFound(View view0, State recyclerView$State0, Action recyclerView$SmoothScroller$Action0) {
                if(CarouselSnapHelper.this.recyclerView != null) {
                    int[] arr_v = CarouselSnapHelper.this.calculateDistanceToSnap(CarouselSnapHelper.this.recyclerView.getLayoutManager(), view0, true);
                    int v = arr_v[0];
                    int v1 = arr_v[1];
                    int v2 = this.calculateTimeForDeceleration(Math.max(Math.abs(v), Math.abs(v1)));
                    if(v2 > 0) {
                        recyclerView$SmoothScroller$Action0.update(v, v1, v2, this.mDecelerateInterpolator);
                    }
                }
            }
        } : null;
    }

    private int distanceToFirstFocalKeyline(View view0, CarouselLayoutManager carouselLayoutManager0, boolean z) {
        return carouselLayoutManager0.getOffsetToScrollToPositionForSnap(carouselLayoutManager0.getPosition(view0), z);
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(LayoutManager recyclerView$LayoutManager0) {
        return this.findViewNearestFirstKeyline(recyclerView$LayoutManager0);
    }

    @Override  // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(LayoutManager recyclerView$LayoutManager0, int v, int v1) {
        if(!this.disableFling) {
            return -1;
        }
        int v2 = recyclerView$LayoutManager0.getItemCount();
        if(v2 == 0) {
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
                int v7 = this.distanceToFirstFocalKeyline(view2, ((CarouselLayoutManager)recyclerView$LayoutManager0), false);
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

    private View findViewNearestFirstKeyline(LayoutManager recyclerView$LayoutManager0) {
        int v = recyclerView$LayoutManager0.getChildCount();
        View view0 = null;
        if(v != 0 && recyclerView$LayoutManager0 instanceof CarouselLayoutManager) {
            int v1 = 0x7FFFFFFF;
            for(int v2 = 0; v2 < v; ++v2) {
                View view1 = recyclerView$LayoutManager0.getChildAt(v2);
                int v3 = Math.abs(((CarouselLayoutManager)recyclerView$LayoutManager0).getOffsetToScrollToPositionForSnap(recyclerView$LayoutManager0.getPosition(view1), false));
                if(v3 < v1) {
                    view0 = view1;
                    v1 = v3;
                }
            }
        }
        return view0;
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

