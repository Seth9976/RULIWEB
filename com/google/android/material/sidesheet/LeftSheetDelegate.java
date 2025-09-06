package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

final class LeftSheetDelegate extends SheetDelegate {
    final SideSheetBehavior sheetBehavior;

    LeftSheetDelegate(SideSheetBehavior sideSheetBehavior0) {
        this.sheetBehavior = sideSheetBehavior0;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int calculateInnerMargin(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return viewGroup$MarginLayoutParams0.leftMargin;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    float calculateSlideOffset(int v) {
        float f = (float)this.getHiddenOffset();
        return (((float)v) - f) / (((float)this.getExpandedOffset()) - f);
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
        return viewGroup$MarginLayoutParams0.leftMargin;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getExpandedOffset() {
        return Math.max(0, this.sheetBehavior.getParentInnerEdge() + this.sheetBehavior.getInnerMargin());
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getHiddenOffset() {
        int v = this.sheetBehavior.getInnerMargin();
        return -this.sheetBehavior.getChildWidth() - v;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.getInnerMargin();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getMinViewPositionHorizontal() {
        return -this.sheetBehavior.getChildWidth();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getOuterEdge(View view0) {
        return view0.getRight() + this.sheetBehavior.getInnerMargin();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    public int getParentInnerEdge(CoordinatorLayout coordinatorLayout0) {
        return coordinatorLayout0.getLeft();
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    int getSheetEdge() {
        return 1;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean isExpandingOutwards(float f) {
        return f > 0.0f;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean isReleasedCloseToInnerEdge(View view0) {
        return view0.getRight() < (this.getExpandedOffset() - this.getHiddenOffset()) / 2;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean isSwipeSignificant(float f, float f1) {
        return SheetUtils.isSwipeMostlyHorizontal(f, f1) && Math.abs(f) > 500.0f;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    boolean shouldHide(View view0, float f) {
        return Math.abs(((float)view0.getLeft()) + f * this.sheetBehavior.getHideFriction()) > 0.5f;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v) {
        viewGroup$MarginLayoutParams0.leftMargin = v;
    }

    @Override  // com.google.android.material.sidesheet.SheetDelegate
    void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v, int v1) {
        if(v <= this.sheetBehavior.getParentWidth()) {
            viewGroup$MarginLayoutParams0.leftMargin = v1;
        }
    }
}

