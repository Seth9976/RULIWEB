package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.List;

public class LinearLayoutManager extends LayoutManager implements ViewDropHandler, ScrollVectorProvider {
    static class AnchorInfo {
        int mCoordinate;
        boolean mLayoutFromEnd;
        OrientationHelper mOrientationHelper;
        int mPosition;
        boolean mValid;

        AnchorInfo() {
            this.reset();
        }

        void assignCoordinateFromPadding() {
            this.mCoordinate = this.mLayoutFromEnd ? this.mOrientationHelper.getEndAfterPadding() : this.mOrientationHelper.getStartAfterPadding();
        }

        public void assignFromView(View view0, int v) {
            this.mCoordinate = this.mLayoutFromEnd ? this.mOrientationHelper.getDecoratedEnd(view0) + this.mOrientationHelper.getTotalSpaceChange() : this.mOrientationHelper.getDecoratedStart(view0);
            this.mPosition = v;
        }

        public void assignFromViewAndKeepVisibleRect(View view0, int v) {
            int v1 = this.mOrientationHelper.getTotalSpaceChange();
            if(v1 >= 0) {
                this.assignFromView(view0, v);
                return;
            }
            this.mPosition = v;
            if(this.mLayoutFromEnd) {
                int v2 = this.mOrientationHelper.getEndAfterPadding() - v1 - this.mOrientationHelper.getDecoratedEnd(view0);
                this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - v2;
                if(v2 > 0) {
                    int v3 = this.mOrientationHelper.getDecoratedMeasurement(view0);
                    int v4 = this.mCoordinate - v3;
                    int v5 = this.mOrientationHelper.getStartAfterPadding();
                    int v6 = v4 - (v5 + Math.min(this.mOrientationHelper.getDecoratedStart(view0) - v5, 0));
                    if(v6 < 0) {
                        this.mCoordinate += Math.min(v2, -v6);
                    }
                }
            }
            else {
                int v7 = this.mOrientationHelper.getDecoratedStart(view0);
                int v8 = v7 - this.mOrientationHelper.getStartAfterPadding();
                this.mCoordinate = v7;
                if(v8 > 0) {
                    int v9 = this.mOrientationHelper.getDecoratedMeasurement(view0);
                    int v10 = this.mOrientationHelper.getEndAfterPadding();
                    int v11 = this.mOrientationHelper.getDecoratedEnd(view0);
                    int v12 = this.mOrientationHelper.getEndAfterPadding() - Math.min(0, v10 - v1 - v11) - (v7 + v9);
                    if(v12 < 0) {
                        this.mCoordinate -= Math.min(v8, -v12);
                    }
                }
            }
        }

        boolean isViewValidAsAnchor(View view0, State recyclerView$State0) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            return !recyclerView$LayoutParams0.isItemRemoved() && recyclerView$LayoutParams0.getViewLayoutPosition() >= 0 && recyclerView$LayoutParams0.getViewLayoutPosition() < recyclerView$State0.getItemCount();
        }

        void reset() {
            this.mPosition = -1;
            this.mCoordinate = 0x80000000;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        @Override
        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
        }
    }

    public static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    static class LayoutState {
        static final int INVALID_LAYOUT = 0x80000000;
        static final int ITEM_DIRECTION_HEAD = -1;
        static final int ITEM_DIRECTION_TAIL = 1;
        static final int LAYOUT_END = 1;
        static final int LAYOUT_START = -1;
        static final int SCROLLING_OFFSET_NaN = 0x80000000;
        static final String TAG = "LLM#LayoutState";
        int mAvailable;
        int mCurrentPosition;
        int mExtraFillSpace;
        boolean mInfinite;
        boolean mIsPreLayout;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mNoRecycleSpace;
        int mOffset;
        boolean mRecycle;
        List mScrapList;
        int mScrollingOffset;

        LayoutState() {
            this.mRecycle = true;
            this.mExtraFillSpace = 0;
            this.mNoRecycleSpace = 0;
            this.mIsPreLayout = false;
            this.mScrapList = null;
        }

        public void assignPositionFromScrapList() {
            this.assignPositionFromScrapList(null);
        }

        public void assignPositionFromScrapList(View view0) {
            View view1 = this.nextViewInLimitedList(view0);
            if(view1 == null) {
                this.mCurrentPosition = -1;
                return;
            }
            this.mCurrentPosition = ((LayoutParams)view1.getLayoutParams()).getViewLayoutPosition();
        }

        boolean hasMore(State recyclerView$State0) {
            return this.mCurrentPosition >= 0 && this.mCurrentPosition < recyclerView$State0.getItemCount();
        }

        void log() {
            Log.d("LLM#LayoutState", "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
        }

        View next(Recycler recyclerView$Recycler0) {
            if(this.mScrapList != null) {
                return this.nextViewFromScrapList();
            }
            View view0 = recyclerView$Recycler0.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return view0;
        }

        private View nextViewFromScrapList() {
            int v = this.mScrapList.size();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = ((ViewHolder)this.mScrapList.get(v1)).itemView;
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(!recyclerView$LayoutParams0.isItemRemoved() && this.mCurrentPosition == recyclerView$LayoutParams0.getViewLayoutPosition()) {
                    this.assignPositionFromScrapList(view0);
                    return view0;
                }
            }
            return null;
        }

        public View nextViewInLimitedList(View view0) {
            int v = this.mScrapList.size();
            View view1 = null;
            int v1 = 0x7FFFFFFF;
            for(int v2 = 0; v2 < v; ++v2) {
                View view2 = ((ViewHolder)this.mScrapList.get(v2)).itemView;
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view2.getLayoutParams();
                if(view2 != view0 && !recyclerView$LayoutParams0.isItemRemoved()) {
                    int v3 = (recyclerView$LayoutParams0.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
                    if(v3 >= 0 && v3 < v1) {
                        if(v3 == 0) {
                            return view2;
                        }
                        view1 = view2;
                        v1 = v3;
                    }
                }
            }
            return view1;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        public SavedState() {
        }

        SavedState(Parcel parcel0) {
            this.mAnchorPosition = parcel0.readInt();
            this.mAnchorOffset = parcel0.readInt();
            this.mAnchorLayoutFromEnd = parcel0.readInt() == 1;
        }

        public SavedState(SavedState linearLayoutManager$SavedState0) {
            this.mAnchorPosition = linearLayoutManager$SavedState0.mAnchorPosition;
            this.mAnchorOffset = linearLayoutManager$SavedState0.mAnchorOffset;
            this.mAnchorLayoutFromEnd = linearLayoutManager$SavedState0.mAnchorLayoutFromEnd;
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        boolean hasValidAnchor() {
            return this.mAnchorPosition >= 0;
        }

        void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeInt(this.mAnchorPosition);
            parcel0.writeInt(this.mAnchorOffset);
            parcel0.writeInt(((int)this.mAnchorLayoutFromEnd));
        }
    }

    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = 0x80000000;
    private static final float MAX_SCROLL_FACTOR = 0.333333f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final AnchorInfo mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    public LinearLayoutManager(Context context0) {
        this(context0, 1, false);
    }

    public LinearLayoutManager(Context context0, int v, boolean z) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        this.setOrientation(v);
        this.setReverseLayout(z);
    }

    public LinearLayoutManager(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        Properties recyclerView$LayoutManager$Properties0 = LinearLayoutManager.getProperties(context0, attributeSet0, v, v1);
        this.setOrientation(recyclerView$LayoutManager$Properties0.orientation);
        this.setReverseLayout(recyclerView$LayoutManager$Properties0.reverseLayout);
        this.setStackFromEnd(recyclerView$LayoutManager$Properties0.stackFromEnd);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void assertNotInLayoutOrScroll(String s) {
        if(this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(s);
        }
    }

    protected void calculateExtraLayoutSpace(State recyclerView$State0, int[] arr_v) {
        int v1;
        int v = this.getExtraLayoutSpace(recyclerView$State0);
        if(this.mLayoutState.mLayoutDirection == -1) {
            v1 = 0;
        }
        else {
            v1 = v;
            v = 0;
        }
        arr_v[0] = v;
        arr_v[1] = v1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void collectAdjacentPrefetchPositions(int v, int v1, State recyclerView$State0, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        if(this.mOrientation != 0) {
            v = v1;
        }
        if(this.getChildCount() != 0 && v != 0) {
            this.ensureLayoutState();
            this.updateLayoutState((v <= 0 ? -1 : 1), Math.abs(v), true, recyclerView$State0);
            this.collectPrefetchPositionsForLayoutState(recyclerView$State0, this.mLayoutState, recyclerView$LayoutManager$LayoutPrefetchRegistry0);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void collectInitialPrefetchPositions(int v, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        int v2;
        boolean z;
        int v1 = -1;
        if(this.mPendingSavedState == null || !this.mPendingSavedState.hasValidAnchor()) {
            this.resolveShouldLayoutReverse();
            z = this.mShouldReverseLayout;
            v2 = this.mPendingScrollPosition;
            if(v2 == -1) {
                v2 = z ? v - 1 : 0;
            }
        }
        else {
            z = this.mPendingSavedState.mAnchorLayoutFromEnd;
            v2 = this.mPendingSavedState.mAnchorPosition;
        }
        if(!z) {
            v1 = 1;
        }
        for(int v3 = 0; v3 < this.mInitialPrefetchItemCount && v2 >= 0 && v2 < v; ++v3) {
            recyclerView$LayoutManager$LayoutPrefetchRegistry0.addPosition(v2, 0);
            v2 += v1;
        }
    }

    void collectPrefetchPositionsForLayoutState(State recyclerView$State0, LayoutState linearLayoutManager$LayoutState0, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        int v = linearLayoutManager$LayoutState0.mCurrentPosition;
        if(v >= 0 && v < recyclerView$State0.getItemCount()) {
            recyclerView$LayoutManager$LayoutPrefetchRegistry0.addPosition(v, Math.max(0, linearLayoutManager$LayoutState0.mScrollingOffset));
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeHorizontalScrollExtent(State recyclerView$State0) {
        return this.computeScrollExtent(recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeHorizontalScrollOffset(State recyclerView$State0) {
        return this.computeScrollOffset(recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeHorizontalScrollRange(State recyclerView$State0) {
        return this.computeScrollRange(recyclerView$State0);
    }

    private int computeScrollExtent(State recyclerView$State0) {
        if(this.getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        return ScrollbarHelper.computeScrollExtent(recyclerView$State0, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), this.findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(State recyclerView$State0) {
        if(this.getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        return ScrollbarHelper.computeScrollOffset(recyclerView$State0, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), this.findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(State recyclerView$State0) {
        if(this.getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        return ScrollbarHelper.computeScrollRange(recyclerView$State0, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), this.findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int v) {
        if(this.getChildCount() == 0) {
            return null;
        }
        boolean z = false;
        int v1 = 1;
        if(v < this.getPosition(this.getChildAt(0))) {
            z = true;
        }
        if(z != this.mShouldReverseLayout) {
            v1 = -1;
        }
        return this.mOrientation == 0 ? new PointF(((float)v1), 0.0f) : new PointF(0.0f, ((float)v1));
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeVerticalScrollExtent(State recyclerView$State0) {
        return this.computeScrollExtent(recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeVerticalScrollOffset(State recyclerView$State0) {
        return this.computeScrollOffset(recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeVerticalScrollRange(State recyclerView$State0) {
        return this.computeScrollRange(recyclerView$State0);
    }

    int convertFocusDirectionToLayoutDirection(int v) {
        switch(v) {
            case 1: {
                if(this.mOrientation == 1) {
                    return -1;
                }
                return this.isLayoutRTL() ? 1 : -1;
            }
            case 2: {
                if(this.mOrientation == 1) {
                    return 1;
                }
                return this.isLayoutRTL() ? -1 : 1;
            }
            case 17: {
                return this.mOrientation == 0 ? -1 : 0x80000000;
            }
            case 33: {
                return this.mOrientation == 1 ? -1 : 0x80000000;
            }
            case 66: {
                return this.mOrientation == 0 ? 1 : 0x80000000;
            }
            case 130: {
                return this.mOrientation == 1 ? 1 : 0x80000000;
            }
            default: {
                return 0x80000000;
            }
        }
    }

    LayoutState createLayoutState() {
        return new LayoutState();
    }

    void ensureLayoutState() {
        if(this.mLayoutState == null) {
            this.mLayoutState = this.createLayoutState();
        }
    }

    int fill(Recycler recyclerView$Recycler0, LayoutState linearLayoutManager$LayoutState0, State recyclerView$State0, boolean z) {
        int v = linearLayoutManager$LayoutState0.mAvailable;
        if(linearLayoutManager$LayoutState0.mScrollingOffset != 0x80000000) {
            if(linearLayoutManager$LayoutState0.mAvailable < 0) {
                linearLayoutManager$LayoutState0.mScrollingOffset += linearLayoutManager$LayoutState0.mAvailable;
            }
            this.recycleByLayoutState(recyclerView$Recycler0, linearLayoutManager$LayoutState0);
        }
        int v1 = linearLayoutManager$LayoutState0.mAvailable + linearLayoutManager$LayoutState0.mExtraFillSpace;
        LayoutChunkResult linearLayoutManager$LayoutChunkResult0 = this.mLayoutChunkResult;
        while((linearLayoutManager$LayoutState0.mInfinite || v1 > 0) && linearLayoutManager$LayoutState0.hasMore(recyclerView$State0)) {
            linearLayoutManager$LayoutChunkResult0.resetInternal();
            this.layoutChunk(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$LayoutState0, linearLayoutManager$LayoutChunkResult0);
            if(linearLayoutManager$LayoutChunkResult0.mFinished) {
                break;
            }
            linearLayoutManager$LayoutState0.mOffset += linearLayoutManager$LayoutChunkResult0.mConsumed * linearLayoutManager$LayoutState0.mLayoutDirection;
            if(!linearLayoutManager$LayoutChunkResult0.mIgnoreConsumed || linearLayoutManager$LayoutState0.mScrapList != null || !recyclerView$State0.isPreLayout()) {
                linearLayoutManager$LayoutState0.mAvailable -= linearLayoutManager$LayoutChunkResult0.mConsumed;
                v1 -= linearLayoutManager$LayoutChunkResult0.mConsumed;
            }
            if(linearLayoutManager$LayoutState0.mScrollingOffset != 0x80000000) {
                linearLayoutManager$LayoutState0.mScrollingOffset += linearLayoutManager$LayoutChunkResult0.mConsumed;
                if(linearLayoutManager$LayoutState0.mAvailable < 0) {
                    linearLayoutManager$LayoutState0.mScrollingOffset += linearLayoutManager$LayoutState0.mAvailable;
                }
                this.recycleByLayoutState(recyclerView$Recycler0, linearLayoutManager$LayoutState0);
            }
            if(z && linearLayoutManager$LayoutChunkResult0.mFocusable) {
                break;
            }
        }
        return v - linearLayoutManager$LayoutState0.mAvailable;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View view0 = this.findOneVisibleChild(0, this.getChildCount(), true, false);
        return view0 == null ? -1 : this.getPosition(view0);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return this.findOnePartiallyOrCompletelyInvisibleChild(0, this.getChildCount());
    }

    // 去混淆评级： 低(20)
    View findFirstVisibleChildClosestToEnd(boolean z, boolean z1) {
        return this.mShouldReverseLayout ? this.findOneVisibleChild(0, this.getChildCount(), z, z1) : this.findOneVisibleChild(this.getChildCount() - 1, -1, z, z1);
    }

    // 去混淆评级： 低(20)
    View findFirstVisibleChildClosestToStart(boolean z, boolean z1) {
        return this.mShouldReverseLayout ? this.findOneVisibleChild(this.getChildCount() - 1, -1, z, z1) : this.findOneVisibleChild(0, this.getChildCount(), z, z1);
    }

    public int findFirstVisibleItemPosition() {
        View view0 = this.findOneVisibleChild(0, this.getChildCount(), false, true);
        return view0 == null ? -1 : this.getPosition(view0);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View view0 = this.findOneVisibleChild(this.getChildCount() - 1, -1, true, false);
        return view0 == null ? -1 : this.getPosition(view0);
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return this.findOnePartiallyOrCompletelyInvisibleChild(this.getChildCount() - 1, -1);
    }

    public int findLastVisibleItemPosition() {
        View view0 = this.findOneVisibleChild(this.getChildCount() - 1, -1, false, true);
        return view0 == null ? -1 : this.getPosition(view0);
    }

    View findOnePartiallyOrCompletelyInvisibleChild(int v, int v1) {
        this.ensureLayoutState();
        if(v1 > v || v1 < v) {
            if(this.mOrientationHelper.getDecoratedStart(this.getChildAt(v)) < this.mOrientationHelper.getStartAfterPadding()) {
                return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(v, v1, 0x4104, 0x4004) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(v, v1, 0x4104, 0x4004);
            }
            return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(v, v1, 0x1041, 0x1001) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(v, v1, 0x1041, 0x1001);
        }
        return this.getChildAt(v);
    }

    View findOneVisibleChild(int v, int v1, boolean z, boolean z1) {
        this.ensureLayoutState();
        int v2 = 320;
        int v3 = z ? 0x6003 : 320;
        if(!z1) {
            v2 = 0;
        }
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(v, v1, v3, v2) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(v, v1, v3, v2);
    }

    // 去混淆评级： 低(20)
    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
        return this.mShouldReverseLayout ? this.findFirstPartiallyOrCompletelyInvisibleChild() : this.findLastPartiallyOrCompletelyInvisibleChild();
    }

    // 去混淆评级： 低(20)
    private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
        return this.mShouldReverseLayout ? this.findLastPartiallyOrCompletelyInvisibleChild() : this.findFirstPartiallyOrCompletelyInvisibleChild();
    }

    View findReferenceChild(Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z, boolean z1) {
        int v3;
        int v2;
        int v1;
        this.ensureLayoutState();
        int v = this.getChildCount();
        if(z1) {
            v1 = this.getChildCount() - 1;
            v2 = -1;
            v3 = -1;
        }
        else {
            v2 = v;
            v1 = 0;
            v3 = 1;
        }
        int v4 = recyclerView$State0.getItemCount();
        int v5 = this.mOrientationHelper.getStartAfterPadding();
        int v6 = this.mOrientationHelper.getEndAfterPadding();
        View view0 = null;
        View view1 = null;
        View view2 = null;
        while(v1 != v2) {
            View view3 = this.getChildAt(v1);
            int v7 = this.getPosition(view3);
            int v8 = this.mOrientationHelper.getDecoratedStart(view3);
            int v9 = this.mOrientationHelper.getDecoratedEnd(view3);
            if(v7 >= 0 && v7 < v4) {
                if(!((LayoutParams)view3.getLayoutParams()).isItemRemoved()) {
                    boolean z2 = v9 <= v5 && v8 < v5;
                    boolean z3 = v8 >= v6 && v9 > v6;
                    if(!z2 && !z3) {
                        return view3;
                    }
                    if(!z) {
                        if(z2) {
                            view1 = view3;
                        }
                        else if(view0 == null) {
                            view0 = view3;
                        }
                    }
                    else if(z3) {
                        view1 = view3;
                    }
                    else if(view0 == null) {
                        view0 = view3;
                    }
                }
                else if(view2 == null) {
                    view2 = view3;
                }
            }
            v1 += v3;
        }
        if(view0 != null) {
            return view0;
        }
        return view1 == null ? view2 : view1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public View findViewByPosition(int v) {
        int v1 = this.getChildCount();
        if(v1 == 0) {
            return null;
        }
        int v2 = v - this.getPosition(this.getChildAt(0));
        if(v2 >= 0 && v2 < v1) {
            View view0 = this.getChildAt(v2);
            return this.getPosition(view0) == v ? view0 : super.findViewByPosition(v);
        }
        return super.findViewByPosition(v);
    }

    private int fixLayoutEndGap(int v, Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z) {
        int v1 = this.mOrientationHelper.getEndAfterPadding() - v;
        if(v1 > 0) {
            int v2 = this.scrollBy(-v1, recyclerView$Recycler0, recyclerView$State0);
            if(z) {
                int v3 = this.mOrientationHelper.getEndAfterPadding() - (v - v2);
                if(v3 > 0) {
                    this.mOrientationHelper.offsetChildren(v3);
                    return v3 - v2;
                }
            }
            return -v2;
        }
        return 0;
    }

    private int fixLayoutStartGap(int v, Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z) {
        int v1 = v - this.mOrientationHelper.getStartAfterPadding();
        if(v1 > 0) {
            int v2 = -this.scrollBy(v1, recyclerView$Recycler0, recyclerView$State0);
            if(z) {
                int v3 = v + v2 - this.mOrientationHelper.getStartAfterPadding();
                if(v3 > 0) {
                    this.mOrientationHelper.offsetChildren(-v3);
                    return v2 - v3;
                }
            }
            return v2;
        }
        return 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    // 去混淆评级： 低(20)
    private View getChildClosestToEnd() {
        return this.mShouldReverseLayout ? this.getChildAt(0) : this.getChildAt(this.getChildCount() - 1);
    }

    // 去混淆评级： 低(20)
    private View getChildClosestToStart() {
        return this.mShouldReverseLayout ? this.getChildAt(this.getChildCount() - 1) : this.getChildAt(0);
    }

    // 去混淆评级： 低(20)
    @Deprecated
    protected int getExtraLayoutSpace(State recyclerView$State0) {
        return recyclerView$State0.hasTargetScrollPosition() ? this.mOrientationHelper.getTotalSpace() : 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    protected boolean isLayoutRTL() {
        return this.getLayoutDirection() == 1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean isLayoutReversed() {
        return this.mReverseLayout;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    void layoutChunk(Recycler recyclerView$Recycler0, State recyclerView$State0, LayoutState linearLayoutManager$LayoutState0, LayoutChunkResult linearLayoutManager$LayoutChunkResult0) {
        int v5;
        int v4;
        int v3;
        int v2;
        int v1;
        int v;
        View view0 = linearLayoutManager$LayoutState0.next(recyclerView$Recycler0);
        if(view0 == null) {
            linearLayoutManager$LayoutChunkResult0.mFinished = true;
            return;
        }
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(linearLayoutManager$LayoutState0.mScrapList != null) {
            if(this.mShouldReverseLayout == (linearLayoutManager$LayoutState0.mLayoutDirection == -1)) {
                this.addDisappearingView(view0);
            }
            else {
                this.addDisappearingView(view0, 0);
            }
        }
        else if(this.mShouldReverseLayout == (linearLayoutManager$LayoutState0.mLayoutDirection == -1)) {
            this.addView(view0);
        }
        else {
            this.addView(view0, 0);
        }
        this.measureChildWithMargins(view0, 0, 0);
        linearLayoutManager$LayoutChunkResult0.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(view0);
        if(this.mOrientation == 1) {
            if(this.isLayoutRTL()) {
                v = this.getWidth() - this.getPaddingRight();
                v1 = v - this.mOrientationHelper.getDecoratedMeasurementInOther(view0);
            }
            else {
                v1 = this.getPaddingLeft();
                v = this.mOrientationHelper.getDecoratedMeasurementInOther(view0) + v1;
            }
            if(linearLayoutManager$LayoutState0.mLayoutDirection == -1) {
                v2 = v;
                v3 = linearLayoutManager$LayoutState0.mOffset;
                v4 = v1;
                v5 = linearLayoutManager$LayoutState0.mOffset - linearLayoutManager$LayoutChunkResult0.mConsumed;
            }
            else {
                v5 = linearLayoutManager$LayoutState0.mOffset;
                v4 = v1;
                v3 = linearLayoutManager$LayoutState0.mOffset + linearLayoutManager$LayoutChunkResult0.mConsumed;
                v2 = v;
            }
        }
        else {
            int v6 = this.getPaddingTop();
            int v7 = this.mOrientationHelper.getDecoratedMeasurementInOther(view0) + v6;
            if(linearLayoutManager$LayoutState0.mLayoutDirection == -1) {
                v2 = linearLayoutManager$LayoutState0.mOffset;
                v3 = v7;
                v4 = linearLayoutManager$LayoutState0.mOffset - linearLayoutManager$LayoutChunkResult0.mConsumed;
            }
            else {
                v4 = linearLayoutManager$LayoutState0.mOffset;
                v2 = linearLayoutManager$LayoutState0.mOffset + linearLayoutManager$LayoutChunkResult0.mConsumed;
                v3 = v7;
            }
            v5 = v6;
        }
        this.layoutDecoratedWithMargins(view0, v4, v5, v2, v3);
        if(recyclerView$LayoutParams0.isItemRemoved() || recyclerView$LayoutParams0.isItemChanged()) {
            linearLayoutManager$LayoutChunkResult0.mIgnoreConsumed = true;
        }
        linearLayoutManager$LayoutChunkResult0.mFocusable = view0.hasFocusable();
    }

    private void layoutForPredictiveAnimations(Recycler recyclerView$Recycler0, State recyclerView$State0, int v, int v1) {
        if(recyclerView$State0.willRunPredictiveAnimations() && this.getChildCount() != 0 && !recyclerView$State0.isPreLayout() && this.supportsPredictiveItemAnimations()) {
            List list0 = recyclerView$Recycler0.getScrapList();
            int v2 = list0.size();
            int v3 = this.getPosition(this.getChildAt(0));
            int v5 = 0;
            int v6 = 0;
            for(int v4 = 0; v4 < v2; ++v4) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)list0.get(v4);
                if(!recyclerView$ViewHolder0.isRemoved()) {
                    if(recyclerView$ViewHolder0.getLayoutPosition() < v3 == this.mShouldReverseLayout) {
                        v6 += this.mOrientationHelper.getDecoratedMeasurement(recyclerView$ViewHolder0.itemView);
                    }
                    else {
                        v5 += this.mOrientationHelper.getDecoratedMeasurement(recyclerView$ViewHolder0.itemView);
                    }
                }
            }
            this.mLayoutState.mScrapList = list0;
            if(v5 > 0) {
                this.updateLayoutStateToFillStart(this.getPosition(this.getChildClosestToStart()), v);
                this.mLayoutState.mExtraFillSpace = v5;
                this.mLayoutState.mAvailable = 0;
                this.mLayoutState.assignPositionFromScrapList();
                this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            }
            if(v6 > 0) {
                this.updateLayoutStateToFillEnd(this.getPosition(this.getChildClosestToEnd()), v1);
                this.mLayoutState.mExtraFillSpace = v6;
                this.mLayoutState.mAvailable = 0;
                this.mLayoutState.assignPositionFromScrapList();
                this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            }
            this.mLayoutState.mScrapList = null;
        }
    }

    private void logChildren() {
        Log.d("LinearLayoutManager", "internal representation of views on the screen");
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view0 = this.getChildAt(v);
            Log.d("LinearLayoutManager", "item " + this.getPosition(view0) + ", coord:" + this.mOrientationHelper.getDecoratedStart(view0));
        }
        Log.d("LinearLayoutManager", "==============");
    }

    void onAnchorReady(Recycler recyclerView$Recycler0, State recyclerView$State0, AnchorInfo linearLayoutManager$AnchorInfo0, int v) {
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView0, Recycler recyclerView$Recycler0) {
        super.onDetachedFromWindow(recyclerView0, recyclerView$Recycler0);
        if(this.mRecycleChildrenOnDetach) {
            this.removeAndRecycleAllViews(recyclerView$Recycler0);
            recyclerView$Recycler0.clear();
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public View onFocusSearchFailed(View view0, int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        this.resolveShouldLayoutReverse();
        if(this.getChildCount() == 0) {
            return null;
        }
        int v1 = this.convertFocusDirectionToLayoutDirection(v);
        if(v1 == 0x80000000) {
            return null;
        }
        this.ensureLayoutState();
        this.updateLayoutState(v1, ((int)(((float)this.mOrientationHelper.getTotalSpace()) * 0.333333f)), false, recyclerView$State0);
        this.mLayoutState.mScrollingOffset = 0x80000000;
        this.mLayoutState.mRecycle = false;
        this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, true);
        View view1 = v1 == -1 ? this.findPartiallyOrCompletelyInvisibleChildClosestToStart() : this.findPartiallyOrCompletelyInvisibleChildClosestToEnd();
        View view2 = v1 == -1 ? this.getChildClosestToStart() : this.getChildClosestToEnd();
        if(view2.hasFocusable()) {
            return view1 == null ? null : view2;
        }
        return view1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        if(this.getChildCount() > 0) {
            accessibilityEvent0.setFromIndex(this.findFirstVisibleItemPosition());
            accessibilityEvent0.setToIndex(this.findLastVisibleItemPosition());
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityNodeInfo(Recycler recyclerView$Recycler0, State recyclerView$State0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        super.onInitializeAccessibilityNodeInfo(recyclerView$Recycler0, recyclerView$State0, accessibilityNodeInfoCompat0);
        if(this.mRecyclerView.mAdapter != null && this.mRecyclerView.mAdapter.getItemCount() > 0 && Build.VERSION.SDK_INT >= 23) {
            accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onLayoutChildren(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        int v17;
        int v16;
        int v15;
        int v10;
        int v8;
        int v6;
        int v5;
        int v = -1;
        if((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && recyclerView$State0.getItemCount() == 0) {
            this.removeAndRecycleAllViews(recyclerView$Recycler0);
            return;
        }
        if(this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        this.ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        this.resolveShouldLayoutReverse();
        View view0 = this.getFocusedChild();
        if(!this.mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.reset();
            this.mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
            this.updateAnchorInfoForLayout(recyclerView$Recycler0, recyclerView$State0, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
        }
        else if(view0 != null && (this.mOrientationHelper.getDecoratedStart(view0) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view0) <= this.mOrientationHelper.getStartAfterPadding())) {
            int v1 = this.getPosition(view0);
            this.mAnchorInfo.assignFromViewAndKeepVisibleRect(view0, v1);
        }
        this.mLayoutState.mLayoutDirection = this.mLayoutState.mLastScrollDelta < 0 ? -1 : 1;
        int[] arr_v = this.mReusableIntPair;
        arr_v[0] = 0;
        arr_v[1] = 0;
        this.calculateExtraLayoutSpace(recyclerView$State0, arr_v);
        int v2 = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.getStartAfterPadding();
        int v3 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.getEndPadding();
        if(recyclerView$State0.isPreLayout()) {
            int v4 = this.mPendingScrollPosition;
            if(v4 != -1 && this.mPendingScrollPositionOffset != 0x80000000) {
                View view1 = this.findViewByPosition(v4);
                if(view1 != null) {
                    if(this.mShouldReverseLayout) {
                        v5 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view1);
                        v6 = this.mPendingScrollPositionOffset;
                    }
                    else {
                        v6 = this.mOrientationHelper.getDecoratedStart(view1) - this.mOrientationHelper.getStartAfterPadding();
                        v5 = this.mPendingScrollPositionOffset;
                    }
                    int v7 = v5 - v6;
                    if(v7 > 0) {
                        v2 += v7;
                    }
                    else {
                        v3 -= v7;
                    }
                }
            }
        }
        if(!this.mAnchorInfo.mLayoutFromEnd) {
            if(!this.mShouldReverseLayout) {
                v = 1;
            }
        }
        else if(this.mShouldReverseLayout) {
            v = 1;
        }
        this.onAnchorReady(recyclerView$Recycler0, recyclerView$State0, this.mAnchorInfo, v);
        this.detachAndScrapAttachedViews(recyclerView$Recycler0);
        LayoutState linearLayoutManager$LayoutState0 = this.mLayoutState;
        linearLayoutManager$LayoutState0.mInfinite = this.resolveIsInfinite();
        this.mLayoutState.mIsPreLayout = recyclerView$State0.isPreLayout();
        this.mLayoutState.mNoRecycleSpace = 0;
        if(this.mAnchorInfo.mLayoutFromEnd) {
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = v2;
            this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            v8 = this.mLayoutState.mOffset;
            int v9 = this.mLayoutState.mCurrentPosition;
            if(this.mLayoutState.mAvailable > 0) {
                v3 += this.mLayoutState.mAvailable;
            }
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = v3;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            v10 = this.mLayoutState.mOffset;
            if(this.mLayoutState.mAvailable > 0) {
                int v11 = this.mLayoutState.mAvailable;
                this.updateLayoutStateToFillStart(v9, v8);
                this.mLayoutState.mExtraFillSpace = v11;
                this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
                v8 = this.mLayoutState.mOffset;
            }
        }
        else {
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = v3;
            this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            v10 = this.mLayoutState.mOffset;
            int v12 = this.mLayoutState.mCurrentPosition;
            if(this.mLayoutState.mAvailable > 0) {
                v2 += this.mLayoutState.mAvailable;
            }
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = v2;
            this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            v8 = this.mLayoutState.mOffset;
            if(this.mLayoutState.mAvailable > 0) {
                int v13 = this.mLayoutState.mAvailable;
                this.updateLayoutStateToFillEnd(v12, v10);
                this.mLayoutState.mExtraFillSpace = v13;
                this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
                v10 = this.mLayoutState.mOffset;
            }
        }
        if(this.getChildCount() > 0) {
            if((this.mShouldReverseLayout ^ this.mStackFromEnd) == 0) {
                int v18 = this.fixLayoutStartGap(v8, recyclerView$Recycler0, recyclerView$State0, true);
                v15 = v8 + v18;
                v16 = v10 + v18;
                v17 = this.fixLayoutEndGap(v16, recyclerView$Recycler0, recyclerView$State0, false);
            }
            else {
                int v14 = this.fixLayoutEndGap(v10, recyclerView$Recycler0, recyclerView$State0, true);
                v15 = v8 + v14;
                v16 = v10 + v14;
                v17 = this.fixLayoutStartGap(v15, recyclerView$Recycler0, recyclerView$State0, false);
            }
            v8 = v15 + v17;
            v10 = v16 + v17;
        }
        this.layoutForPredictiveAnimations(recyclerView$Recycler0, recyclerView$State0, v8, v10);
        if(recyclerView$State0.isPreLayout()) {
            this.mAnchorInfo.reset();
        }
        else {
            this.mOrientationHelper.onLayoutComplete();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onLayoutCompleted(State recyclerView$State0) {
        super.onLayoutCompleted(recyclerView$State0);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.mAnchorInfo.reset();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 instanceof SavedState) {
            this.mPendingSavedState = (SavedState)parcelable0;
            if(this.mPendingScrollPosition != -1) {
                ((SavedState)parcelable0).invalidateAnchor();
            }
            this.requestLayout();
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public Parcelable onSaveInstanceState() {
        if(this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        Parcelable parcelable0 = new SavedState();
        if(this.getChildCount() > 0) {
            this.ensureLayoutState();
            int v = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            parcelable0.mAnchorLayoutFromEnd = v;
            if(v != 0) {
                View view0 = this.getChildClosestToEnd();
                parcelable0.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view0);
                parcelable0.mAnchorPosition = this.getPosition(view0);
                return parcelable0;
            }
            View view1 = this.getChildClosestToStart();
            parcelable0.mAnchorPosition = this.getPosition(view1);
            parcelable0.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(view1) - this.mOrientationHelper.getStartAfterPadding();
            return parcelable0;
        }
        ((SavedState)parcelable0).invalidateAnchor();
        return parcelable0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    boolean performAccessibilityAction(int v, Bundle bundle0) {
        int v2;
        if(super.performAccessibilityAction(v, bundle0)) {
            return true;
        }
        if(v == 0x1020037 && bundle0 != null) {
            if(this.mOrientation == 1) {
                int v1 = bundle0.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
                if(v1 < 0) {
                    return false;
                }
                v2 = Math.min(v1, this.getRowCountForAccessibility(this.mRecyclerView.mRecycler, this.mRecyclerView.mState) - 1);
            }
            else {
                int v3 = bundle0.getInt("android.view.accessibility.action.ARGUMENT_COLUMN_INT", -1);
                if(v3 < 0) {
                    return false;
                }
                v2 = Math.min(v3, this.getColumnCountForAccessibility(this.mRecyclerView.mRecycler, this.mRecyclerView.mState) - 1);
            }
            if(v2 >= 0) {
                this.scrollToPositionWithOffset(v2, 0);
                return true;
            }
        }
        return false;
    }

    @Override  // androidx.recyclerview.widget.ItemTouchHelper$ViewDropHandler
    public void prepareForDrop(View view0, View view1, int v, int v1) {
        this.assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        this.ensureLayoutState();
        this.resolveShouldLayoutReverse();
        int v2 = this.getPosition(view0);
        int v3 = this.getPosition(view1);
        int v4 = v2 >= v3 ? -1 : 1;
        if(this.mShouldReverseLayout) {
            if(v4 == 1) {
                this.scrollToPositionWithOffset(v3, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(view1) + this.mOrientationHelper.getDecoratedMeasurement(view0)));
                return;
            }
            this.scrollToPositionWithOffset(v3, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view1));
            return;
        }
        if(v4 == -1) {
            this.scrollToPositionWithOffset(v3, this.mOrientationHelper.getDecoratedStart(view1));
            return;
        }
        this.scrollToPositionWithOffset(v3, this.mOrientationHelper.getDecoratedEnd(view1) - this.mOrientationHelper.getDecoratedMeasurement(view0));
    }

    private void recycleByLayoutState(Recycler recyclerView$Recycler0, LayoutState linearLayoutManager$LayoutState0) {
        if(linearLayoutManager$LayoutState0.mRecycle && !linearLayoutManager$LayoutState0.mInfinite) {
            int v = linearLayoutManager$LayoutState0.mScrollingOffset;
            int v1 = linearLayoutManager$LayoutState0.mNoRecycleSpace;
            if(linearLayoutManager$LayoutState0.mLayoutDirection == -1) {
                this.recycleViewsFromEnd(recyclerView$Recycler0, v, v1);
                return;
            }
            this.recycleViewsFromStart(recyclerView$Recycler0, v, v1);
        }
    }

    private void recycleChildren(Recycler recyclerView$Recycler0, int v, int v1) {
        if(v != v1) {
            if(v1 > v) {
                for(int v2 = v1 - 1; v2 >= v; --v2) {
                    this.removeAndRecycleViewAt(v2, recyclerView$Recycler0);
                }
                return;
            }
            while(v > v1) {
                this.removeAndRecycleViewAt(v, recyclerView$Recycler0);
                --v;
            }
        }
    }

    private void recycleViewsFromEnd(Recycler recyclerView$Recycler0, int v, int v1) {
        int v2 = this.getChildCount();
        if(v >= 0) {
            int v3 = this.mOrientationHelper.getEnd() - v + v1;
            if(this.mShouldReverseLayout) {
                int v4 = 0;
                while(v4 < v2) {
                    View view0 = this.getChildAt(v4);
                    if(this.mOrientationHelper.getDecoratedStart(view0) >= v3 && this.mOrientationHelper.getTransformedStartWithDecoration(view0) >= v3) {
                        ++v4;
                        continue;
                    }
                    this.recycleChildren(recyclerView$Recycler0, 0, v4);
                    return;
                }
            }
            else {
                int v5 = v2 - 1;
                while(v5 >= 0) {
                    View view1 = this.getChildAt(v5);
                    if(this.mOrientationHelper.getDecoratedStart(view1) >= v3 && this.mOrientationHelper.getTransformedStartWithDecoration(view1) >= v3) {
                        --v5;
                    }
                    else {
                        this.recycleChildren(recyclerView$Recycler0, v2 - 1, v5);
                        if(true) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void recycleViewsFromStart(Recycler recyclerView$Recycler0, int v, int v1) {
        if(v >= 0) {
            int v2 = v - v1;
            int v3 = this.getChildCount();
            if(this.mShouldReverseLayout) {
                int v4 = v3 - 1;
                while(v4 >= 0) {
                    View view0 = this.getChildAt(v4);
                    if(this.mOrientationHelper.getDecoratedEnd(view0) <= v2 && this.mOrientationHelper.getTransformedEndWithDecoration(view0) <= v2) {
                        --v4;
                        continue;
                    }
                    this.recycleChildren(recyclerView$Recycler0, v3 - 1, v4);
                    return;
                }
            }
            else {
                int v5 = 0;
                while(v5 < v3) {
                    View view1 = this.getChildAt(v5);
                    if(this.mOrientationHelper.getDecoratedEnd(view1) <= v2 && this.mOrientationHelper.getTransformedEndWithDecoration(view1) <= v2) {
                        ++v5;
                    }
                    else {
                        this.recycleChildren(recyclerView$Recycler0, 0, v5);
                        if(true) {
                            break;
                        }
                    }
                }
            }
        }
    }

    boolean resolveIsInfinite() {
        return this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
    }

    private void resolveShouldLayoutReverse() {
        if(this.mOrientation != 1 && this.isLayoutRTL()) {
            this.mShouldReverseLayout = !this.mReverseLayout;
            return;
        }
        this.mShouldReverseLayout = this.mReverseLayout;
    }

    int scrollBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(this.getChildCount() != 0 && v != 0) {
            this.ensureLayoutState();
            this.mLayoutState.mRecycle = true;
            int v1 = v <= 0 ? -1 : 1;
            int v2 = Math.abs(v);
            this.updateLayoutState(v1, v2, true, recyclerView$State0);
            int v3 = this.mLayoutState.mScrollingOffset + this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0, false);
            if(v3 < 0) {
                return 0;
            }
            if(v2 > v3) {
                v = v1 * v3;
            }
            this.mOrientationHelper.offsetChildren(-v);
            this.mLayoutState.mLastScrollDelta = v;
            return v;
        }
        return 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int scrollHorizontallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.mOrientation == 1 ? 0 : this.scrollBy(v, recyclerView$Recycler0, recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void scrollToPosition(int v) {
        this.mPendingScrollPosition = v;
        this.mPendingScrollPositionOffset = 0x80000000;
        SavedState linearLayoutManager$SavedState0 = this.mPendingSavedState;
        if(linearLayoutManager$SavedState0 != null) {
            linearLayoutManager$SavedState0.invalidateAnchor();
        }
        this.requestLayout();
    }

    public void scrollToPositionWithOffset(int v, int v1) {
        this.mPendingScrollPosition = v;
        this.mPendingScrollPositionOffset = v1;
        SavedState linearLayoutManager$SavedState0 = this.mPendingSavedState;
        if(linearLayoutManager$SavedState0 != null) {
            linearLayoutManager$SavedState0.invalidateAnchor();
        }
        this.requestLayout();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int scrollVerticallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.mOrientation == 0 ? 0 : this.scrollBy(v, recyclerView$Recycler0, recyclerView$State0);
    }

    public void setInitialPrefetchItemCount(int v) {
        this.mInitialPrefetchItemCount = v;
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1) {
            throw new IllegalArgumentException("invalid orientation:" + v);
        }
        this.assertNotInLayoutOrScroll(null);
        if(v == this.mOrientation && this.mOrientationHelper != null) {
            return;
        }
        OrientationHelper orientationHelper0 = OrientationHelper.createOrientationHelper(this, v);
        this.mOrientationHelper = orientationHelper0;
        this.mAnchorInfo.mOrientationHelper = orientationHelper0;
        this.mOrientation = v;
        this.requestLayout();
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    public void setReverseLayout(boolean z) {
        this.assertNotInLayoutOrScroll(null);
        if(z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        this.requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public void setStackFromEnd(boolean z) {
        this.assertNotInLayoutOrScroll(null);
        if(this.mStackFromEnd == z) {
            return;
        }
        this.mStackFromEnd = z;
        this.requestLayout();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    boolean shouldMeasureTwice() {
        return this.getHeightMode() != 0x40000000 && this.getWidthMode() != 0x40000000 && this.hasFlexibleChildInBothOrientations();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView0, State recyclerView$State0, int v) {
        LinearSmoothScroller linearSmoothScroller0 = new LinearSmoothScroller(recyclerView0.getContext());
        linearSmoothScroller0.setTargetPosition(v);
        this.startSmoothScroll(linearSmoothScroller0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    private boolean updateAnchorFromChildren(Recycler recyclerView$Recycler0, State recyclerView$State0, AnchorInfo linearLayoutManager$AnchorInfo0) {
        boolean z = false;
        if(this.getChildCount() == 0) {
            return false;
        }
        View view0 = this.getFocusedChild();
        if(view0 != null && linearLayoutManager$AnchorInfo0.isViewValidAsAnchor(view0, recyclerView$State0)) {
            linearLayoutManager$AnchorInfo0.assignFromViewAndKeepVisibleRect(view0, this.getPosition(view0));
            return true;
        }
        if(this.mLastStackFromEnd != this.mStackFromEnd) {
            return false;
        }
        View view1 = this.findReferenceChild(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$AnchorInfo0.mLayoutFromEnd, this.mStackFromEnd);
        if(view1 != null) {
            linearLayoutManager$AnchorInfo0.assignFromView(view1, this.getPosition(view1));
            if(!recyclerView$State0.isPreLayout() && this.supportsPredictiveItemAnimations()) {
                int v = this.mOrientationHelper.getDecoratedStart(view1);
                int v1 = this.mOrientationHelper.getDecoratedEnd(view1);
                int v2 = this.mOrientationHelper.getStartAfterPadding();
                int v3 = this.mOrientationHelper.getEndAfterPadding();
                if(v >= v3 && v1 > v3) {
                    z = true;
                }
                if(v1 <= v2 && v < v2 || z) {
                    if(linearLayoutManager$AnchorInfo0.mLayoutFromEnd) {
                        v2 = v3;
                    }
                    linearLayoutManager$AnchorInfo0.mCoordinate = v2;
                }
            }
            return true;
        }
        return false;
    }

    private boolean updateAnchorFromPendingData(State recyclerView$State0, AnchorInfo linearLayoutManager$AnchorInfo0) {
        boolean z = false;
        if(!recyclerView$State0.isPreLayout()) {
            int v = this.mPendingScrollPosition;
            if(v != -1) {
                if(v >= 0 && v < recyclerView$State0.getItemCount()) {
                    linearLayoutManager$AnchorInfo0.mPosition = this.mPendingScrollPosition;
                    if(this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
                        linearLayoutManager$AnchorInfo0.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
                        if(linearLayoutManager$AnchorInfo0.mLayoutFromEnd) {
                            linearLayoutManager$AnchorInfo0.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                            return true;
                        }
                        linearLayoutManager$AnchorInfo0.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
                        return true;
                    }
                    if(this.mPendingScrollPositionOffset == 0x80000000) {
                        View view0 = this.findViewByPosition(this.mPendingScrollPosition);
                        if(view0 != null) {
                            if(this.mOrientationHelper.getDecoratedMeasurement(view0) > this.mOrientationHelper.getTotalSpace()) {
                                linearLayoutManager$AnchorInfo0.assignCoordinateFromPadding();
                                return true;
                            }
                            if(this.mOrientationHelper.getDecoratedStart(view0) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                                linearLayoutManager$AnchorInfo0.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                                linearLayoutManager$AnchorInfo0.mLayoutFromEnd = false;
                                return true;
                            }
                            if(this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view0) < 0) {
                                linearLayoutManager$AnchorInfo0.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                                linearLayoutManager$AnchorInfo0.mLayoutFromEnd = true;
                                return true;
                            }
                            linearLayoutManager$AnchorInfo0.mCoordinate = linearLayoutManager$AnchorInfo0.mLayoutFromEnd ? this.mOrientationHelper.getDecoratedEnd(view0) + this.mOrientationHelper.getTotalSpaceChange() : this.mOrientationHelper.getDecoratedStart(view0);
                            return true;
                        }
                        if(this.getChildCount() > 0) {
                            int v1 = this.getPosition(this.getChildAt(0));
                            if(this.mPendingScrollPosition < v1 == this.mShouldReverseLayout) {
                                z = true;
                            }
                            linearLayoutManager$AnchorInfo0.mLayoutFromEnd = z;
                        }
                        linearLayoutManager$AnchorInfo0.assignCoordinateFromPadding();
                        return true;
                    }
                    linearLayoutManager$AnchorInfo0.mLayoutFromEnd = this.mShouldReverseLayout;
                    if(this.mShouldReverseLayout) {
                        linearLayoutManager$AnchorInfo0.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                        return true;
                    }
                    linearLayoutManager$AnchorInfo0.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                    return true;
                }
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = 0x80000000;
            }
        }
        return false;
    }

    private void updateAnchorInfoForLayout(Recycler recyclerView$Recycler0, State recyclerView$State0, AnchorInfo linearLayoutManager$AnchorInfo0) {
        if(this.updateAnchorFromPendingData(recyclerView$State0, linearLayoutManager$AnchorInfo0) || this.updateAnchorFromChildren(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$AnchorInfo0)) {
            return;
        }
        linearLayoutManager$AnchorInfo0.assignCoordinateFromPadding();
        linearLayoutManager$AnchorInfo0.mPosition = this.mStackFromEnd ? recyclerView$State0.getItemCount() - 1 : 0;
    }

    private void updateLayoutState(int v, int v1, boolean z, State recyclerView$State0) {
        int v5;
        LayoutState linearLayoutManager$LayoutState0 = this.mLayoutState;
        linearLayoutManager$LayoutState0.mInfinite = this.resolveIsInfinite();
        this.mLayoutState.mLayoutDirection = v;
        int[] arr_v = this.mReusableIntPair;
        boolean z1 = false;
        arr_v[0] = 0;
        int v2 = 1;
        arr_v[1] = 0;
        this.calculateExtraLayoutSpace(recyclerView$State0, arr_v);
        int v3 = Math.max(0, this.mReusableIntPair[0]);
        int v4 = Math.max(0, this.mReusableIntPair[1]);
        if(v == 1) {
            z1 = true;
        }
        this.mLayoutState.mExtraFillSpace = z1 ? v4 : v3;
        LayoutState linearLayoutManager$LayoutState1 = this.mLayoutState;
        if(!z1) {
            v3 = v4;
        }
        linearLayoutManager$LayoutState1.mNoRecycleSpace = v3;
        if(z1) {
            LayoutState linearLayoutManager$LayoutState2 = this.mLayoutState;
            linearLayoutManager$LayoutState2.mExtraFillSpace += this.mOrientationHelper.getEndPadding();
            View view0 = this.getChildClosestToEnd();
            LayoutState linearLayoutManager$LayoutState3 = this.mLayoutState;
            if(this.mShouldReverseLayout) {
                v2 = -1;
            }
            linearLayoutManager$LayoutState3.mItemDirection = v2;
            LayoutState linearLayoutManager$LayoutState4 = this.mLayoutState;
            linearLayoutManager$LayoutState4.mCurrentPosition = this.getPosition(view0) + this.mLayoutState.mItemDirection;
            LayoutState linearLayoutManager$LayoutState5 = this.mLayoutState;
            linearLayoutManager$LayoutState5.mOffset = this.mOrientationHelper.getDecoratedEnd(view0);
            v5 = this.mOrientationHelper.getDecoratedEnd(view0) - this.mOrientationHelper.getEndAfterPadding();
        }
        else {
            View view1 = this.getChildClosestToStart();
            LayoutState linearLayoutManager$LayoutState6 = this.mLayoutState;
            linearLayoutManager$LayoutState6.mExtraFillSpace += this.mOrientationHelper.getStartAfterPadding();
            LayoutState linearLayoutManager$LayoutState7 = this.mLayoutState;
            if(!this.mShouldReverseLayout) {
                v2 = -1;
            }
            linearLayoutManager$LayoutState7.mItemDirection = v2;
            LayoutState linearLayoutManager$LayoutState8 = this.mLayoutState;
            linearLayoutManager$LayoutState8.mCurrentPosition = this.getPosition(view1) + this.mLayoutState.mItemDirection;
            LayoutState linearLayoutManager$LayoutState9 = this.mLayoutState;
            linearLayoutManager$LayoutState9.mOffset = this.mOrientationHelper.getDecoratedStart(view1);
            v5 = -this.mOrientationHelper.getDecoratedStart(view1) + this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mAvailable = v1;
        if(z) {
            this.mLayoutState.mAvailable -= v5;
        }
        this.mLayoutState.mScrollingOffset = v5;
    }

    private void updateLayoutStateToFillEnd(int v, int v1) {
        LayoutState linearLayoutManager$LayoutState0 = this.mLayoutState;
        linearLayoutManager$LayoutState0.mAvailable = this.mOrientationHelper.getEndAfterPadding() - v1;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
        this.mLayoutState.mCurrentPosition = v;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = v1;
        this.mLayoutState.mScrollingOffset = 0x80000000;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo linearLayoutManager$AnchorInfo0) {
        this.updateLayoutStateToFillEnd(linearLayoutManager$AnchorInfo0.mPosition, linearLayoutManager$AnchorInfo0.mCoordinate);
    }

    private void updateLayoutStateToFillStart(int v, int v1) {
        LayoutState linearLayoutManager$LayoutState0 = this.mLayoutState;
        linearLayoutManager$LayoutState0.mAvailable = v1 - this.mOrientationHelper.getStartAfterPadding();
        this.mLayoutState.mCurrentPosition = v;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = v1;
        this.mLayoutState.mScrollingOffset = 0x80000000;
    }

    private void updateLayoutStateToFillStart(AnchorInfo linearLayoutManager$AnchorInfo0) {
        this.updateLayoutStateToFillStart(linearLayoutManager$AnchorInfo0.mPosition, linearLayoutManager$AnchorInfo0.mCoordinate);
    }

    void validateChildOrder() {
        Log.d("LinearLayoutManager", "validating child count " + this.getChildCount());
        boolean z = true;
        if(this.getChildCount() >= 1) {
            int v = this.getPosition(this.getChildAt(0));
            int v1 = this.mOrientationHelper.getDecoratedStart(this.getChildAt(0));
            if(this.mShouldReverseLayout) {
                int v2 = 1;
                while(v2 < this.getChildCount()) {
                    View view0 = this.getChildAt(v2);
                    int v3 = this.getPosition(view0);
                    int v4 = this.mOrientationHelper.getDecoratedStart(view0);
                    if(v3 < v) {
                        this.logChildren();
                        StringBuilder stringBuilder0 = new StringBuilder("detected invalid position. loc invalid? ");
                        if(v4 >= v1) {
                            z = false;
                        }
                        stringBuilder0.append(z);
                        throw new RuntimeException(stringBuilder0.toString());
                    }
                    if(v4 <= v1) {
                        ++v2;
                        continue;
                    }
                    this.logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }
            else {
                int v5 = 1;
                while(v5 < this.getChildCount()) {
                    View view1 = this.getChildAt(v5);
                    int v6 = this.getPosition(view1);
                    int v7 = this.mOrientationHelper.getDecoratedStart(view1);
                    if(v6 < v) {
                        this.logChildren();
                        StringBuilder stringBuilder1 = new StringBuilder("detected invalid position. loc invalid? ");
                        if(v7 >= v1) {
                            z = false;
                        }
                        stringBuilder1.append(z);
                        throw new RuntimeException(stringBuilder1.toString());
                    }
                    if(v7 >= v1) {
                        ++v5;
                        continue;
                    }
                    this.logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }
        }
    }
}

