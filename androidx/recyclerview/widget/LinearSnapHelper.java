package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;

public class LinearSnapHelper extends SnapHelper {
    private static final float INVALID_DISTANCE = 1.0f;
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

    private float computeDistancePerChild(LayoutManager recyclerView$LayoutManager0, OrientationHelper orientationHelper0) {
        int v = recyclerView$LayoutManager0.getChildCount();
        if(v == 0) {
            return 1.0f;
        }
        View view0 = null;
        View view1 = null;
        int v1 = 0x7FFFFFFF;
        int v2 = 0x80000000;
        for(int v3 = 0; v3 < v; ++v3) {
            View view2 = recyclerView$LayoutManager0.getChildAt(v3);
            int v4 = recyclerView$LayoutManager0.getPosition(view2);
            if(v4 != -1) {
                if(v4 < v1) {
                    view0 = view2;
                    v1 = v4;
                }
                if(v4 > v2) {
                    view1 = view2;
                    v2 = v4;
                }
            }
        }
        if(view0 != null && view1 != null) {
            int v5 = Math.min(orientationHelper0.getDecoratedStart(view0), orientationHelper0.getDecoratedStart(view1));
            int v6 = Math.max(orientationHelper0.getDecoratedEnd(view0), orientationHelper0.getDecoratedEnd(view1)) - v5;
            return v6 == 0 ? 1.0f : ((float)v6) * 1.0f / ((float)(v2 - v1 + 1));
        }
        return 1.0f;
    }

    private int distanceToCenter(View view0, OrientationHelper orientationHelper0) {
        return orientationHelper0.getDecoratedStart(view0) + orientationHelper0.getDecoratedMeasurement(view0) / 2 - (orientationHelper0.getStartAfterPadding() + orientationHelper0.getTotalSpace() / 2);
    }

    private int estimateNextPositionDiffForFling(LayoutManager recyclerView$LayoutManager0, OrientationHelper orientationHelper0, int v, int v1) {
        int[] arr_v = this.calculateScrollDistance(v, v1);
        float f = this.computeDistancePerChild(recyclerView$LayoutManager0, orientationHelper0);
        if(f <= 0.0f) {
            return 0;
        }
        return Math.abs(arr_v[0]) <= Math.abs(arr_v[1]) ? Math.round(((float)arr_v[1]) / f) : Math.round(((float)arr_v[0]) / f);
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
        int v6;
        int v5;
        if(!(recyclerView$LayoutManager0 instanceof ScrollVectorProvider)) {
            return -1;
        }
        int v2 = recyclerView$LayoutManager0.getItemCount();
        if(v2 == 0) {
            return -1;
        }
        View view0 = this.findSnapView(recyclerView$LayoutManager0);
        if(view0 == null) {
            return -1;
        }
        int v3 = recyclerView$LayoutManager0.getPosition(view0);
        if(v3 == -1) {
            return -1;
        }
        int v4 = 0;
        PointF pointF0 = ((ScrollVectorProvider)recyclerView$LayoutManager0).computeScrollVectorForPosition(v2 - 1);
        if(pointF0 == null) {
            return -1;
        }
        if(recyclerView$LayoutManager0.canScrollHorizontally()) {
            v5 = this.estimateNextPositionDiffForFling(recyclerView$LayoutManager0, this.getHorizontalHelper(recyclerView$LayoutManager0), v, 0);
            if(pointF0.x < 0.0f) {
                v5 = -v5;
            }
        }
        else {
            v5 = 0;
        }
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            v6 = this.estimateNextPositionDiffForFling(recyclerView$LayoutManager0, this.getVerticalHelper(recyclerView$LayoutManager0), 0, v1);
            if(pointF0.y < 0.0f) {
                v6 = -v6;
            }
        }
        else {
            v6 = 0;
        }
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            v5 = v6;
        }
        if(v5 == 0) {
            return -1;
        }
        int v7 = v3 + v5;
        if(v7 >= 0) {
            v4 = v7;
        }
        return v4 < v2 ? v4 : v2 - 1;
    }

    private OrientationHelper getHorizontalHelper(LayoutManager recyclerView$LayoutManager0) {
        if(this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != recyclerView$LayoutManager0) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(recyclerView$LayoutManager0);
        }
        return this.mHorizontalHelper;
    }

    private OrientationHelper getVerticalHelper(LayoutManager recyclerView$LayoutManager0) {
        if(this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != recyclerView$LayoutManager0) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(recyclerView$LayoutManager0);
        }
        return this.mVerticalHelper;
    }
}

