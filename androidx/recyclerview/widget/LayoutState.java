package androidx.recyclerview.widget;

import android.view.View;

class LayoutState {
    static final int INVALID_LAYOUT = 0x80000000;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    int mAvailable;
    int mCurrentPosition;
    int mEndLine;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle;
    int mStartLine;
    boolean mStopInFocusable;

    LayoutState() {
        this.mRecycle = true;
        this.mStartLine = 0;
        this.mEndLine = 0;
    }

    boolean hasMore(State recyclerView$State0) {
        return this.mCurrentPosition >= 0 && this.mCurrentPosition < recyclerView$State0.getItemCount();
    }

    View next(Recycler recyclerView$Recycler0) {
        View view0 = recyclerView$Recycler0.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return view0;
    }

    @Override
    public String toString() {
        return "LayoutState{mAvailable=" + this.mAvailable + ", mCurrentPosition=" + this.mCurrentPosition + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + ", mStartLine=" + this.mStartLine + ", mEndLine=" + this.mEndLine + '}';
    }
}

