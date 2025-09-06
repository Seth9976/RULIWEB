package androidx.recyclerview.widget;

import android.view.View;

class ScrollbarHelper {
    static int computeScrollExtent(State recyclerView$State0, OrientationHelper orientationHelper0, View view0, View view1, LayoutManager recyclerView$LayoutManager0, boolean z) {
        if(recyclerView$LayoutManager0.getChildCount() != 0 && recyclerView$State0.getItemCount() != 0 && view0 != null && view1 != null) {
            if(!z) {
                return Math.abs(recyclerView$LayoutManager0.getPosition(view0) - recyclerView$LayoutManager0.getPosition(view1)) + 1;
            }
            int v = orientationHelper0.getDecoratedEnd(view1);
            int v1 = orientationHelper0.getDecoratedStart(view0);
            return Math.min(orientationHelper0.getTotalSpace(), v - v1);
        }
        return 0;
    }

    static int computeScrollOffset(State recyclerView$State0, OrientationHelper orientationHelper0, View view0, View view1, LayoutManager recyclerView$LayoutManager0, boolean z, boolean z1) {
        if(recyclerView$LayoutManager0.getChildCount() != 0 && recyclerView$State0.getItemCount() != 0 && view0 != null && view1 != null) {
            int v = z1 ? Math.max(0, recyclerView$State0.getItemCount() - Math.max(recyclerView$LayoutManager0.getPosition(view0), recyclerView$LayoutManager0.getPosition(view1)) - 1) : Math.max(0, Math.min(recyclerView$LayoutManager0.getPosition(view0), recyclerView$LayoutManager0.getPosition(view1)));
            return z ? Math.round(((float)v) * (((float)Math.abs(orientationHelper0.getDecoratedEnd(view1) - orientationHelper0.getDecoratedStart(view0))) / ((float)(Math.abs(recyclerView$LayoutManager0.getPosition(view0) - recyclerView$LayoutManager0.getPosition(view1)) + 1))) + ((float)(orientationHelper0.getStartAfterPadding() - orientationHelper0.getDecoratedStart(view0)))) : v;
        }
        return 0;
    }

    static int computeScrollRange(State recyclerView$State0, OrientationHelper orientationHelper0, View view0, View view1, LayoutManager recyclerView$LayoutManager0, boolean z) {
        if(recyclerView$LayoutManager0.getChildCount() != 0 && recyclerView$State0.getItemCount() != 0 && view0 != null && view1 != null) {
            return z ? ((int)(((float)(orientationHelper0.getDecoratedEnd(view1) - orientationHelper0.getDecoratedStart(view0))) / ((float)(Math.abs(recyclerView$LayoutManager0.getPosition(view0) - recyclerView$LayoutManager0.getPosition(view1)) + 1)) * ((float)recyclerView$State0.getItemCount()))) : recyclerView$State0.getItemCount();
        }
        return 0;
    }
}

