package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;

final class ScrollEventAdapter extends OnScrollListener {
    static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }

    private static final int NO_POSITION = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_FAKE_DRAG = 4;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private int mAdapterState;
    private OnPageChangeCallback mCallback;
    private boolean mDataSetChangeHappened;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    private final LinearLayoutManager mLayoutManager;
    private final RecyclerView mRecyclerView;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues;
    private int mTarget;
    private final ViewPager2 mViewPager;

    ScrollEventAdapter(ViewPager2 viewPager20) {
        this.mViewPager = viewPager20;
        RecyclerView recyclerView0 = viewPager20.mRecyclerView;
        this.mRecyclerView = recyclerView0;
        this.mLayoutManager = (LinearLayoutManager)recyclerView0.getLayoutManager();
        this.mScrollValues = new ScrollEventValues();
        this.resetState();
    }

    private void dispatchScrolled(int v, float f, int v1) {
        OnPageChangeCallback viewPager2$OnPageChangeCallback0 = this.mCallback;
        if(viewPager2$OnPageChangeCallback0 != null) {
            viewPager2$OnPageChangeCallback0.onPageScrolled(v, f, v1);
        }
    }

    private void dispatchSelected(int v) {
        OnPageChangeCallback viewPager2$OnPageChangeCallback0 = this.mCallback;
        if(viewPager2$OnPageChangeCallback0 != null) {
            viewPager2$OnPageChangeCallback0.onPageSelected(v);
        }
    }

    private void dispatchStateChanged(int v) {
        if((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != v) {
            this.mScrollState = v;
            OnPageChangeCallback viewPager2$OnPageChangeCallback0 = this.mCallback;
            if(viewPager2$OnPageChangeCallback0 != null) {
                viewPager2$OnPageChangeCallback0.onPageScrollStateChanged(v);
            }
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    double getRelativeScrollPosition() {
        this.updateScrollEventValues();
        return ((double)this.mScrollValues.mPosition) + ((double)this.mScrollValues.mOffset);
    }

    int getScrollState() {
        return this.mScrollState;
    }

    boolean isDragging() {
        return this.mScrollState == 1;
    }

    boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    boolean isIdle() {
        return this.mScrollState == 0;
    }

    private boolean isInAnyDraggingState() {
        return this.mAdapterState == 1 || this.mAdapterState == 4;
    }

    void notifyBeginFakeDrag() {
        this.mAdapterState = 4;
        this.startDrag(true);
    }

    void notifyDataSetChangeHappened() {
        this.mDataSetChangeHappened = true;
    }

    void notifyEndFakeDrag() {
        if(this.isDragging() && !this.mFakeDragging) {
            return;
        }
        this.mFakeDragging = false;
        this.updateScrollEventValues();
        if(this.mScrollValues.mOffsetPx == 0) {
            if(this.mScrollValues.mPosition != this.mDragStartPosition) {
                this.dispatchSelected(this.mScrollValues.mPosition);
            }
            this.dispatchStateChanged(0);
            this.resetState();
            return;
        }
        this.dispatchStateChanged(2);
    }

    void notifyProgrammaticScroll(int v, boolean z) {
        this.mAdapterState = z ? 2 : 3;
        boolean z1 = false;
        this.mFakeDragging = false;
        if(this.mTarget != v) {
            z1 = true;
        }
        this.mTarget = v;
        this.dispatchStateChanged(2);
        if(z1) {
            this.dispatchSelected(v);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView0, int v) {
        if((this.mAdapterState != 1 || this.mScrollState != 1) && v == 1) {
            this.startDrag(false);
            return;
        }
        if(!this.isInAnyDraggingState() || v != 2) {
            if(this.isInAnyDraggingState() && v == 0) {
                this.updateScrollEventValues();
                if(!this.mScrollHappened) {
                    if(this.mScrollValues.mPosition != -1) {
                        this.dispatchScrolled(this.mScrollValues.mPosition, 0.0f, 0);
                    }
                    this.dispatchStateChanged(0);
                    this.resetState();
                }
                else if(this.mScrollValues.mOffsetPx == 0) {
                    if(this.mDragStartPosition != this.mScrollValues.mPosition) {
                        this.dispatchSelected(this.mScrollValues.mPosition);
                    }
                    this.dispatchStateChanged(0);
                    this.resetState();
                }
            }
            if(this.mAdapterState == 2 && v == 0 && this.mDataSetChangeHappened) {
                this.updateScrollEventValues();
                if(this.mScrollValues.mOffsetPx == 0) {
                    if(this.mTarget != this.mScrollValues.mPosition) {
                        this.dispatchSelected((this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition));
                    }
                    this.dispatchStateChanged(0);
                    this.resetState();
                }
            }
        }
        else if(this.mScrollHappened) {
            this.dispatchStateChanged(2);
            this.mDispatchSelected = true;
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
    public void onScrolled(RecyclerView recyclerView0, int v, int v1) {
        this.mScrollHappened = true;
        this.updateScrollEventValues();
        if(this.mDispatchSelected) {
            this.mDispatchSelected = false;
            int v2 = v1 <= 0 && (v1 != 0 || v < 0 != this.mViewPager.isRtl()) || this.mScrollValues.mOffsetPx == 0 ? this.mScrollValues.mPosition : this.mScrollValues.mPosition + 1;
            this.mTarget = v2;
            if(this.mDragStartPosition != v2) {
                this.dispatchSelected(v2);
            }
        }
        else if(this.mAdapterState == 0) {
            this.dispatchSelected((this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition));
        }
        this.dispatchScrolled((this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition), this.mScrollValues.mOffset, this.mScrollValues.mOffsetPx);
        if((this.mScrollValues.mPosition == this.mTarget || this.mTarget == -1) && this.mScrollValues.mOffsetPx == 0 && this.mScrollState != 1) {
            this.dispatchStateChanged(0);
            this.resetState();
        }
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    void setOnPageChangeCallback(OnPageChangeCallback viewPager2$OnPageChangeCallback0) {
        this.mCallback = viewPager2$OnPageChangeCallback0;
    }

    private void startDrag(boolean z) {
        this.mFakeDragging = z;
        this.mAdapterState = z ? 4 : 1;
        int v = this.mTarget;
        if(v != -1) {
            this.mDragStartPosition = v;
            this.mTarget = -1;
        }
        else if(this.mDragStartPosition == -1) {
            this.mDragStartPosition = this.getPosition();
        }
        this.dispatchStateChanged(1);
    }

    private void updateScrollEventValues() {
        int v6;
        ScrollEventValues scrollEventAdapter$ScrollEventValues0 = this.mScrollValues;
        scrollEventAdapter$ScrollEventValues0.mPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        if(scrollEventAdapter$ScrollEventValues0.mPosition == -1) {
            scrollEventAdapter$ScrollEventValues0.reset();
            return;
        }
        View view0 = this.mLayoutManager.findViewByPosition(scrollEventAdapter$ScrollEventValues0.mPosition);
        if(view0 == null) {
            scrollEventAdapter$ScrollEventValues0.reset();
            return;
        }
        int v = this.mLayoutManager.getLeftDecorationWidth(view0);
        int v1 = this.mLayoutManager.getRightDecorationWidth(view0);
        int v2 = this.mLayoutManager.getTopDecorationHeight(view0);
        int v3 = this.mLayoutManager.getBottomDecorationHeight(view0);
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams) {
            v += ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).leftMargin;
            v1 += ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).rightMargin;
            v2 += ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).topMargin;
            v3 += ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).bottomMargin;
        }
        int v4 = view0.getHeight() + v2 + v3;
        int v5 = view0.getWidth();
        if(this.mLayoutManager.getOrientation() == 0) {
            v6 = view0.getLeft() - v - this.mRecyclerView.getPaddingLeft();
            if(this.mViewPager.isRtl()) {
                v6 = -v6;
            }
            v4 = v5 + v + v1;
        }
        else {
            v6 = view0.getTop() - v2 - this.mRecyclerView.getPaddingTop();
        }
        scrollEventAdapter$ScrollEventValues0.mOffsetPx = -v6;
        if(scrollEventAdapter$ScrollEventValues0.mOffsetPx < 0) {
            throw new AnimateLayoutChangeDetector(this.mLayoutManager).mayHaveInterferingAnimations() ? new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.") : new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", scrollEventAdapter$ScrollEventValues0.mOffsetPx));
        }
        scrollEventAdapter$ScrollEventValues0.mOffset = v4 == 0 ? 0.0f : ((float)scrollEventAdapter$ScrollEventValues0.mOffsetPx) / ((float)v4);
    }
}

