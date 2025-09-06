package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

abstract class SheetDelegate {
    abstract int calculateInnerMargin(ViewGroup.MarginLayoutParams arg1);

    abstract float calculateSlideOffset(int arg1);

    abstract int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams arg1);

    abstract int getExpandedOffset();

    abstract int getHiddenOffset();

    abstract int getMaxViewPositionHorizontal();

    abstract int getMinViewPositionHorizontal();

    abstract int getOuterEdge(View arg1);

    abstract int getParentInnerEdge(CoordinatorLayout arg1);

    abstract int getSheetEdge();

    abstract boolean isExpandingOutwards(float arg1);

    abstract boolean isReleasedCloseToInnerEdge(View arg1);

    abstract boolean isSwipeSignificant(float arg1, float arg2);

    abstract boolean shouldHide(View arg1, float arg2);

    abstract void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams arg1, int arg2);

    abstract void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams arg1, int arg2, int arg3);
}

