package androidx.viewpager2.widget;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.Arrays;
import java.util.Comparator;

final class AnimateLayoutChangeDetector {
    private static final ViewGroup.MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS;
    private LinearLayoutManager mLayoutManager;

    static {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = new ViewGroup.MarginLayoutParams(-1, -1);
        AnimateLayoutChangeDetector.ZERO_MARGIN_LAYOUT_PARAMS = viewGroup$MarginLayoutParams0;
        viewGroup$MarginLayoutParams0.setMargins(0, 0, 0, 0);
    }

    AnimateLayoutChangeDetector(LinearLayoutManager linearLayoutManager0) {
        this.mLayoutManager = linearLayoutManager0;
    }

    private boolean arePagesLaidOutContiguously() {
        int v5;
        int v4;
        int v3;
        int v2;
        int v = this.mLayoutManager.getChildCount();
        if(v == 0) {
            return true;
        }
        boolean z = this.mLayoutManager.getOrientation() == 0;
        int[][] arr2_v = new int[v][2];
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.mLayoutManager.getChildAt(v1);
            if(view0 == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
            ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0) : AnimateLayoutChangeDetector.ZERO_MARGIN_LAYOUT_PARAMS;
            int[] arr_v = arr2_v[v1];
            if(z) {
                v2 = view0.getLeft();
                v3 = viewGroup$MarginLayoutParams0.leftMargin;
            }
            else {
                v2 = view0.getTop();
                v3 = viewGroup$MarginLayoutParams0.topMargin;
            }
            arr_v[0] = v2 - v3;
            int[] arr_v1 = arr2_v[v1];
            if(z) {
                v4 = view0.getRight();
                v5 = viewGroup$MarginLayoutParams0.rightMargin;
            }
            else {
                v4 = view0.getBottom();
                v5 = viewGroup$MarginLayoutParams0.bottomMargin;
            }
            arr_v1[1] = v4 + v5;
        }
        Arrays.sort(arr2_v, new Comparator() {
            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((int[])object0), ((int[])object1));
            }

            public int compare(int[] arr_v, int[] arr_v1) {
                return arr_v[0] - arr_v1[0];
            }
        });
        for(int v6 = 1; v6 < v; ++v6) {
            if(arr2_v[v6 - 1][1] != arr2_v[v6][0]) {
                return false;
            }
        }
        int[] arr_v2 = arr2_v[0];
        return arr_v2[0] <= 0 && arr2_v[v - 1][1] >= arr_v2[1] - arr_v2[0];
    }

    private boolean hasRunningChangingLayoutTransition() {
        int v = this.mLayoutManager.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(AnimateLayoutChangeDetector.hasRunningChangingLayoutTransition(this.mLayoutManager.getChildAt(v1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRunningChangingLayoutTransition(View view0) {
        if(view0 instanceof ViewGroup) {
            LayoutTransition layoutTransition0 = ((ViewGroup)view0).getLayoutTransition();
            if(layoutTransition0 != null && layoutTransition0.isChangingLayout()) {
                return true;
            }
            int v = ((ViewGroup)view0).getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                if(AnimateLayoutChangeDetector.hasRunningChangingLayoutTransition(((ViewGroup)view0).getChildAt(v1))) {
                    return true;
                }
            }
        }
        return false;
    }

    // 去混淆评级： 低(20)
    boolean mayHaveInterferingAnimations() {
        return (!this.arePagesLaidOutContiguously() || this.mLayoutManager.getChildCount() <= 1) && this.hasRunningChangingLayoutTransition();
    }
}

