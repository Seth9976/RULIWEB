package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;

public abstract class OrientationHelper {
    public static final int HORIZONTAL = 0;
    private static final int INVALID_SIZE = 0x80000000;
    public static final int VERTICAL = 1;
    private int mLastTotalSpace;
    protected final LayoutManager mLayoutManager;
    final Rect mTmpRect;

    private OrientationHelper(LayoutManager recyclerView$LayoutManager0) {
        this.mLastTotalSpace = 0x80000000;
        this.mTmpRect = new Rect();
        this.mLayoutManager = recyclerView$LayoutManager0;
    }

    OrientationHelper(LayoutManager recyclerView$LayoutManager0, androidx.recyclerview.widget.OrientationHelper.1 orientationHelper$10) {
        this(recyclerView$LayoutManager0);
    }

    public static OrientationHelper createHorizontalHelper(LayoutManager recyclerView$LayoutManager0) {
        return new OrientationHelper(recyclerView$LayoutManager0) {
            {
                super(recyclerView$LayoutManager0, null);
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedEnd(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedRight(view0) + recyclerView$LayoutParams0.rightMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedMeasurement(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(view0) + recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedMeasurementInOther(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(view0) + recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedStart(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedLeft(view0) - recyclerView$LayoutParams0.leftMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getEnd() {
                return this.mLayoutManager.getWidth();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getEndAfterPadding() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getEndPadding() {
                return this.mLayoutManager.getPaddingRight();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getMode() {
                return this.mLayoutManager.getWidthMode();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getModeInOther() {
                return this.mLayoutManager.getHeightMode();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingLeft();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getTotalSpace() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft() - this.mLayoutManager.getPaddingRight();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getTransformedEndWithDecoration(View view0) {
                this.mLayoutManager.getTransformedBoundingBox(view0, true, this.mTmpRect);
                return this.mTmpRect.right;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getTransformedStartWithDecoration(View view0) {
                this.mLayoutManager.getTransformedBoundingBox(view0, true, this.mTmpRect);
                return this.mTmpRect.left;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public void offsetChild(View view0, int v) {
                view0.offsetLeftAndRight(v);
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public void offsetChildren(int v) {
                this.mLayoutManager.offsetChildrenHorizontal(v);
            }
        };
    }

    public static OrientationHelper createOrientationHelper(LayoutManager recyclerView$LayoutManager0, int v) {
        switch(v) {
            case 0: {
                return OrientationHelper.createHorizontalHelper(recyclerView$LayoutManager0);
            }
            case 1: {
                return OrientationHelper.createVerticalHelper(recyclerView$LayoutManager0);
            }
            default: {
                throw new IllegalArgumentException("invalid orientation");
            }
        }
    }

    public static OrientationHelper createVerticalHelper(LayoutManager recyclerView$LayoutManager0) {
        return new OrientationHelper(recyclerView$LayoutManager0) {
            {
                super(recyclerView$LayoutManager0, null);
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedEnd(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedBottom(view0) + recyclerView$LayoutParams0.bottomMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedMeasurement(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(view0) + recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedMeasurementInOther(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(view0) + recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getDecoratedStart(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.mLayoutManager.getDecoratedTop(view0) - recyclerView$LayoutParams0.topMargin;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getEnd() {
                return this.mLayoutManager.getHeight();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getEndAfterPadding() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getEndPadding() {
                return this.mLayoutManager.getPaddingBottom();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getMode() {
                return this.mLayoutManager.getHeightMode();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getModeInOther() {
                return this.mLayoutManager.getWidthMode();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingTop();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getTotalSpace() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop() - this.mLayoutManager.getPaddingBottom();
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getTransformedEndWithDecoration(View view0) {
                this.mLayoutManager.getTransformedBoundingBox(view0, true, this.mTmpRect);
                return this.mTmpRect.bottom;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public int getTransformedStartWithDecoration(View view0) {
                this.mLayoutManager.getTransformedBoundingBox(view0, true, this.mTmpRect);
                return this.mTmpRect.top;
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public void offsetChild(View view0, int v) {
                view0.offsetTopAndBottom(v);
            }

            @Override  // androidx.recyclerview.widget.OrientationHelper
            public void offsetChildren(int v) {
                this.mLayoutManager.offsetChildrenVertical(v);
            }
        };
    }

    public abstract int getDecoratedEnd(View arg1);

    public abstract int getDecoratedMeasurement(View arg1);

    public abstract int getDecoratedMeasurementInOther(View arg1);

    public abstract int getDecoratedStart(View arg1);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public LayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange() {
        return 0x80000000 == this.mLastTotalSpace ? 0 : this.getTotalSpace() - this.mLastTotalSpace;
    }

    public abstract int getTransformedEndWithDecoration(View arg1);

    public abstract int getTransformedStartWithDecoration(View arg1);

    public abstract void offsetChild(View arg1, int arg2);

    public abstract void offsetChildren(int arg1);

    public void onLayoutComplete() {
        this.mLastTotalSpace = this.getTotalSpace();
    }
}

