package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

final class RightSheetDelegate extends SheetDelegate {
    final SideSheetBehavior sheetBehavior;

    RightSheetDelegate(SideSheetBehavior sideSheetBehavior0) {
        this.sheetBehavior = sideSheetBehavior0;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int calculateInnerMargin(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return viewGroup$MarginLayoutParams0.rightMargin;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    float calculateSlideOffset(int v) {
        float f = (float)this.getHiddenOffset();
        return (f - ((float)v)) / (f - ((float)this.getExpandedOffset()));
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return viewGroup$MarginLayoutParams0.rightMargin;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getExpandedOffset() {
        return Math.max(0, this.getHiddenOffset() - this.sheetBehavior.getChildWidth() - this.sheetBehavior.getInnerMargin());
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getHiddenOffset() {
        return this.sheetBehavior.getParentWidth();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.getParentWidth();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getMinViewPositionHorizontal() {
        return this.getExpandedOffset();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getOuterEdge(View view0) {
        return view0.getLeft() - this.sheetBehavior.getInnerMargin();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    public int getParentInnerEdge(CoordinatorLayout coordinatorLayout0) {
        return coordinatorLayout0.getRight();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getSheetEdge() {
        return 0;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean isExpandingOutwards(float f) {
        return f < 0.0f;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean isReleasedCloseToInnerEdge(View view0) {
        return view0.getLeft() > (this.getHiddenOffset() + this.getExpandedOffset()) / 2;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean isSwipeSignificant(float f, float f1) {
        return SheetUtils.isSwipeMostlyHorizontal(f, f1) && Math.abs(f) > 500.0f;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean shouldHide(View view0, float f) {
        return Math.abs(((float)view0.getRight()) + f * this.sheetBehavior.getHideFriction()) > 0.5f;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v) {
        viewGroup$MarginLayoutParams0.rightMargin = v;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v, int v1) {
        int v2 = this.sheetBehavior.getParentWidth();
        if(v <= v2) {
            viewGroup$MarginLayoutParams0.rightMargin = v2 - v;
        }
    }
}

