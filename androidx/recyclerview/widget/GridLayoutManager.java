package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class GridLayoutManager extends LinearLayoutManager {
    static class Api21Impl {
        static boolean isAccessibilityFocused(View view0) {
            return view0.isAccessibilityFocused();
        }
    }

    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        @Override  // androidx.recyclerview.widget.GridLayoutManager$SpanSizeLookup
        public int getSpanIndex(int v, int v1) {
            return v % v1;
        }

        @Override  // androidx.recyclerview.widget.GridLayoutManager$SpanSizeLookup
        public int getSpanSize(int v) {
            return 1;
        }
    }

    public static class LayoutParams extends androidx.recyclerview.widget.RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex;
        int mSpanSize;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(androidx.recyclerview.widget.RecyclerView.LayoutParams recyclerView$LayoutParams0) {
            super(recyclerView$LayoutParams0);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }
    }

    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanGroupIndices;
        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanGroupIndexCache;
        final SparseIntArray mSpanIndexCache;

        public SpanSizeLookup() {
            this.mSpanIndexCache = new SparseIntArray();
            this.mSpanGroupIndexCache = new SparseIntArray();
            this.mCacheSpanIndices = false;
            this.mCacheSpanGroupIndices = false;
        }

        static int findFirstKeyLessThan(SparseIntArray sparseIntArray0, int v) {
            int v1 = sparseIntArray0.size() - 1;
            int v2 = 0;
            while(v2 <= v1) {
                int v3 = v2 + v1 >>> 1;
                if(sparseIntArray0.keyAt(v3) < v) {
                    v2 = v3 + 1;
                }
                else {
                    v1 = v3 - 1;
                }
            }
            return v2 - 1 < 0 || v2 - 1 >= sparseIntArray0.size() ? -1 : sparseIntArray0.keyAt(v2 - 1);
        }

        int getCachedSpanGroupIndex(int v, int v1) {
            if(!this.mCacheSpanGroupIndices) {
                return this.getSpanGroupIndex(v, v1);
            }
            int v2 = this.mSpanGroupIndexCache.get(v, -1);
            if(v2 != -1) {
                return v2;
            }
            int v3 = this.getSpanGroupIndex(v, v1);
            this.mSpanGroupIndexCache.put(v, v3);
            return v3;
        }

        int getCachedSpanIndex(int v, int v1) {
            if(!this.mCacheSpanIndices) {
                return this.getSpanIndex(v, v1);
            }
            int v2 = this.mSpanIndexCache.get(v, -1);
            if(v2 != -1) {
                return v2;
            }
            int v3 = this.getSpanIndex(v, v1);
            this.mSpanIndexCache.put(v, v3);
            return v3;
        }

        public int getSpanGroupIndex(int v, int v1) {
            int v5;
            int v4;
            int v3;
            if(this.mCacheSpanGroupIndices) {
                int v2 = SpanSizeLookup.findFirstKeyLessThan(this.mSpanGroupIndexCache, v);
                if(v2 == -1) {
                    v3 = 0;
                    v4 = 0;
                    v5 = 0;
                }
                else {
                    v3 = this.mSpanGroupIndexCache.get(v2);
                    v4 = v2 + 1;
                    v5 = this.getCachedSpanIndex(v2, v1) + this.getSpanSize(v2);
                    if(v5 == v1) {
                        ++v3;
                        v5 = 0;
                    }
                }
            }
            else {
                v3 = 0;
                v4 = 0;
                v5 = 0;
            }
            int v6 = this.getSpanSize(v);
            while(v4 < v) {
                int v7 = this.getSpanSize(v4);
                v5 += v7;
                if(v5 == v1) {
                    ++v3;
                    v5 = 0;
                }
                else if(v5 > v1) {
                    ++v3;
                    v5 = v7;
                }
                ++v4;
            }
            return v5 + v6 <= v1 ? v3 : v3 + 1;
        }

        public int getSpanIndex(int v, int v1) {
            int v4;
            int v3;
            int v2 = this.getSpanSize(v);
            if(v2 == v1) {
                return 0;
            }
            if(this.mCacheSpanIndices) {
                v3 = SpanSizeLookup.findFirstKeyLessThan(this.mSpanIndexCache, v);
                if(v3 >= 0) {
                    v4 = this.mSpanIndexCache.get(v3) + this.getSpanSize(v3);
                    ++v3;
                }
                else {
                    v3 = 0;
                    v4 = 0;
                }
            }
            else {
                v3 = 0;
                v4 = 0;
            }
            while(v3 < v) {
                int v5 = this.getSpanSize(v3);
                v4 += v5;
                if(v4 == v1) {
                    v4 = 0;
                }
                else if(v4 > v1) {
                    v4 = v5;
                }
                ++v3;
            }
            return v2 + v4 > v1 ? 0 : v4;
        }

        public abstract int getSpanSize(int arg1);

        public void invalidateSpanGroupIndexCache() {
            this.mSpanGroupIndexCache.clear();
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanGroupIndexCacheEnabled() {
            return this.mCacheSpanGroupIndices;
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        public void setSpanGroupIndexCacheEnabled(boolean z) {
            if(!z) {
                this.mSpanGroupIndexCache.clear();
            }
            this.mCacheSpanGroupIndices = z;
        }

        public void setSpanIndexCacheEnabled(boolean z) {
            if(!z) {
                this.mSpanGroupIndexCache.clear();
            }
            this.mCacheSpanIndices = z;
        }
    }

    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    int mColumnWithAccessibilityFocus;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    private int mPositionTargetedByScrollInDirection;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    int mRowWithAccessibilityFocus;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;
    private boolean mUsingSpansToEstimateScrollBarDimensions;
    private static final Set sSupportedDirectionsForActionScrollInDirection;

    static {
        GridLayoutManager.sSupportedDirectionsForActionScrollInDirection = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{17, 66, 33, 130})));
    }

    public GridLayoutManager(Context context0, int v) {
        super(context0);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.mPositionTargetedByScrollInDirection = -1;
        this.mRowWithAccessibilityFocus = -1;
        this.mColumnWithAccessibilityFocus = -1;
        this.setSpanCount(v);
    }

    public GridLayoutManager(Context context0, int v, int v1, boolean z) {
        super(context0, v1, z);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.mPositionTargetedByScrollInDirection = -1;
        this.mRowWithAccessibilityFocus = -1;
        this.mColumnWithAccessibilityFocus = -1;
        this.setSpanCount(v);
    }

    public GridLayoutManager(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.mPositionTargetedByScrollInDirection = -1;
        this.mRowWithAccessibilityFocus = -1;
        this.mColumnWithAccessibilityFocus = -1;
        this.setSpanCount(GridLayoutManager.getProperties(context0, attributeSet0, v, v1).spanCount);
    }

    private void assignSpans(Recycler recyclerView$Recycler0, State recyclerView$State0, int v, boolean z) {
        int v4;
        int v3;
        int v2;
        int v1 = 0;
        if(z) {
            v2 = v;
            v3 = 0;
            v4 = 1;
        }
        else {
            v3 = v - 1;
            v2 = -1;
            v4 = -1;
        }
        while(v3 != v2) {
            View view0 = this.mSet[v3];
            LayoutParams gridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            gridLayoutManager$LayoutParams0.mSpanSize = this.getSpanSize(recyclerView$Recycler0, recyclerView$State0, this.getPosition(view0));
            gridLayoutManager$LayoutParams0.mSpanIndex = v1;
            v1 += gridLayoutManager$LayoutParams0.mSpanSize;
            v3 += v4;
        }
    }

    private void cachePreLayoutSpanMapping() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            LayoutParams gridLayoutManager$LayoutParams0 = (LayoutParams)this.getChildAt(v1).getLayoutParams();
            int v2 = gridLayoutManager$LayoutParams0.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(v2, gridLayoutManager$LayoutParams0.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(v2, gridLayoutManager$LayoutParams0.getSpanIndex());
        }
    }

    private void calculateItemBorders(int v) {
        this.mCachedBorders = GridLayoutManager.calculateItemBorders(this.mCachedBorders, this.mSpanCount, v);
    }

    static int[] calculateItemBorders(int[] arr_v, int v, int v1) {
        int v7;
        if(arr_v == null || arr_v.length != v + 1 || arr_v[arr_v.length - 1] != v1) {
            arr_v = new int[v + 1];
        }
        int v3 = 0;
        arr_v[0] = 0;
        int v4 = v1 / v;
        int v5 = v1 % v;
        int v6 = 0;
        for(int v2 = 1; v2 <= v; ++v2) {
            v3 += v5;
            if(v3 <= 0 || v - v3 >= v5) {
                v7 = v4;
            }
            else {
                v7 = v4 + 1;
                v3 -= v;
            }
            v6 += v7;
            arr_v[v2] = v6;
        }
        return arr_v;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean checkLayoutParams(androidx.recyclerview.widget.RecyclerView.LayoutParams recyclerView$LayoutParams0) {
        return recyclerView$LayoutParams0 instanceof LayoutParams;
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    void collectPrefetchPositionsForLayoutState(State recyclerView$State0, LayoutState linearLayoutManager$LayoutState0, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        int v = this.mSpanCount;
        for(int v1 = 0; v1 < this.mSpanCount && linearLayoutManager$LayoutState0.hasMore(recyclerView$State0) && v > 0; ++v1) {
            int v2 = linearLayoutManager$LayoutState0.mCurrentPosition;
            recyclerView$LayoutManager$LayoutPrefetchRegistry0.addPosition(v2, Math.max(0, linearLayoutManager$LayoutState0.mScrollingOffset));
            v -= this.mSpanSizeLookup.getSpanSize(v2);
            linearLayoutManager$LayoutState0.mCurrentPosition += linearLayoutManager$LayoutState0.mItemDirection;
        }
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public int computeHorizontalScrollOffset(State recyclerView$State0) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollOffsetWithSpanInfo(recyclerView$State0) : super.computeHorizontalScrollOffset(recyclerView$State0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public int computeHorizontalScrollRange(State recyclerView$State0) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollRangeWithSpanInfo(recyclerView$State0) : super.computeHorizontalScrollRange(recyclerView$State0);
    }

    private int computeScrollOffsetWithSpanInfo(State recyclerView$State0) {
        if(this.getChildCount() != 0 && recyclerView$State0.getItemCount() != 0) {
            this.ensureLayoutState();
            boolean z = this.isSmoothScrollbarEnabled();
            View view0 = this.findFirstVisibleChildClosestToStart(!z, true);
            View view1 = this.findFirstVisibleChildClosestToEnd(!z, true);
            if(view0 != null && view1 != null) {
                int v = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(view0), this.mSpanCount);
                int v1 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(view1), this.mSpanCount);
                int v2 = this.mSpanSizeLookup.getCachedSpanGroupIndex(recyclerView$State0.getItemCount() - 1, this.mSpanCount);
                int v3 = this.mShouldReverseLayout ? Math.max(0, v2 + 1 - Math.max(v, v1) - 1) : Math.max(0, Math.min(v, v1));
                if(!z) {
                    return v3;
                }
                int v4 = this.mOrientationHelper.getDecoratedEnd(view1);
                int v5 = this.mOrientationHelper.getDecoratedStart(view0);
                int v6 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(view0), this.mSpanCount);
                return Math.round(((float)v3) * (((float)Math.abs(v4 - v5)) / ((float)(this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(view1), this.mSpanCount) - v6 + 1))) + ((float)(this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(view0))));
            }
        }
        return 0;
    }

    private int computeScrollRangeWithSpanInfo(State recyclerView$State0) {
        if(this.getChildCount() != 0 && recyclerView$State0.getItemCount() != 0) {
            this.ensureLayoutState();
            View view0 = this.findFirstVisibleChildClosestToStart(!this.isSmoothScrollbarEnabled(), true);
            View view1 = this.findFirstVisibleChildClosestToEnd(!this.isSmoothScrollbarEnabled(), true);
            if(view0 != null && view1 != null) {
                if(!this.isSmoothScrollbarEnabled()) {
                    return this.mSpanSizeLookup.getCachedSpanGroupIndex(recyclerView$State0.getItemCount() - 1, this.mSpanCount) + 1;
                }
                int v = this.mOrientationHelper.getDecoratedEnd(view1);
                int v1 = this.mOrientationHelper.getDecoratedStart(view0);
                int v2 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(view0), this.mSpanCount);
                return (int)(((float)(v - v1)) / ((float)(this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(view1), this.mSpanCount) - v2 + 1)) * ((float)(this.mSpanSizeLookup.getCachedSpanGroupIndex(recyclerView$State0.getItemCount() - 1, this.mSpanCount) + 1)));
            }
        }
        return 0;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public int computeVerticalScrollOffset(State recyclerView$State0) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollOffsetWithSpanInfo(recyclerView$State0) : super.computeVerticalScrollOffset(recyclerView$State0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public int computeVerticalScrollRange(State recyclerView$State0) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollRangeWithSpanInfo(recyclerView$State0) : super.computeVerticalScrollRange(recyclerView$State0);
    }

    private void ensureAnchorIsInCorrectSpan(Recycler recyclerView$Recycler0, State recyclerView$State0, AnchorInfo linearLayoutManager$AnchorInfo0, int v) {
        int v1 = this.getSpanIndex(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$AnchorInfo0.mPosition);
        if(v == 1) {
            while(v1 > 0 && linearLayoutManager$AnchorInfo0.mPosition > 0) {
                --linearLayoutManager$AnchorInfo0.mPosition;
                v1 = this.getSpanIndex(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$AnchorInfo0.mPosition);
            }
            return;
        }
        int v2 = recyclerView$State0.getItemCount();
        int v3 = linearLayoutManager$AnchorInfo0.mPosition;
        while(v3 < v2 - 1) {
            int v4 = this.getSpanIndex(recyclerView$Recycler0, recyclerView$State0, v3 + 1);
            if(v4 <= v1) {
                break;
            }
            ++v3;
            v1 = v4;
        }
        linearLayoutManager$AnchorInfo0.mPosition = v3;
    }

    private void ensureViewSet() {
        if(this.mSet != null && this.mSet.length == this.mSpanCount) {
            return;
        }
        this.mSet = new View[this.mSpanCount];
    }

    private View findChildWithAccessibilityFocus() {
        for(int v = 0; v < this.getChildCount(); ++v) {
            if(Api21Impl.isAccessibilityFocused(((View)Objects.requireNonNull(this.getChildAt(v))))) {
                return this.getChildAt(v);
            }
        }
        return null;
    }

    int findPositionOfFirstItemOnARowBelowForHorizontalGrid(int v) {
        if(v < 0) {
            return -1;
        }
        if(this.mOrientation == 1) {
            return -1;
        }
        TreeMap treeMap0 = new TreeMap();
        for(int v1 = 0; v1 < this.getItemCount(); ++v1) {
            for(Object object0: this.getRowIndices(v1)) {
                Integer integer0 = (Integer)object0;
                if(((int)integer0) < 0) {
                    return -1;
                }
                if(!treeMap0.containsKey(integer0)) {
                    treeMap0.put(integer0, v1);
                }
            }
        }
        for(Object object1: treeMap0.keySet()) {
            Integer integer1 = (Integer)object1;
            int v2 = (int)integer1;
            if(v2 > v) {
                int v3 = (int)(((Integer)treeMap0.get(integer1)));
                this.mRowWithAccessibilityFocus = v2;
                this.mColumnWithAccessibilityFocus = 0;
                return v3;
            }
            if(false) {
                break;
            }
        }
        return -1;
    }

    int findPositionOfLastItemOnARowAboveForHorizontalGrid(int v) {
        if(v < 0) {
            return -1;
        }
        if(this.mOrientation == 1) {
            return -1;
        }
        TreeMap treeMap0 = new TreeMap(Collections.reverseOrder());
        for(int v1 = 0; v1 < this.getItemCount(); ++v1) {
            for(Object object0: this.getRowIndices(v1)) {
                if(((int)(((Integer)object0))) < 0) {
                    return -1;
                }
                treeMap0.put(((Integer)object0), v1);
            }
        }
        for(Object object1: treeMap0.keySet()) {
            Integer integer0 = (Integer)object1;
            int v2 = (int)integer0;
            if(v2 < v) {
                int v3 = (int)(((Integer)treeMap0.get(integer0)));
                this.mRowWithAccessibilityFocus = v2;
                this.mColumnWithAccessibilityFocus = this.getColumnIndex(v3);
                return v3;
            }
            if(false) {
                break;
            }
        }
        return -1;
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    View findReferenceChild(Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z, boolean z1) {
        int v3;
        int v2;
        int v = this.getChildCount();
        int v1 = 1;
        if(z1) {
            v2 = this.getChildCount() - 1;
            v3 = -1;
            v1 = -1;
        }
        else {
            v3 = v;
            v2 = 0;
        }
        int v4 = recyclerView$State0.getItemCount();
        this.ensureLayoutState();
        int v5 = this.mOrientationHelper.getStartAfterPadding();
        int v6 = this.mOrientationHelper.getEndAfterPadding();
        View view0 = null;
        View view1 = null;
        while(v2 != v3) {
            View view2 = this.getChildAt(v2);
            int v7 = this.getPosition(view2);
            if(v7 >= 0 && v7 < v4 && this.getSpanIndex(recyclerView$Recycler0, recyclerView$State0, v7) == 0) {
                if(!((androidx.recyclerview.widget.RecyclerView.LayoutParams)view2.getLayoutParams()).isItemRemoved()) {
                    if(this.mOrientationHelper.getDecoratedStart(view2) < v6 && this.mOrientationHelper.getDecoratedEnd(view2) >= v5) {
                        return view2;
                    }
                    if(view0 == null) {
                        view0 = view2;
                    }
                }
                else if(view1 == null) {
                    view1 = view2;
                }
            }
            v2 += v1;
        }
        return view0 == null ? view1 : view0;
    }

    private int findScrollTargetPositionAbove(int v, int v1, int v2) {
        for(int v3 = v2 - 1; v3 >= 0; --v3) {
            int v4 = this.getRowIndex(v3);
            int v5 = this.getColumnIndex(v3);
            if(v4 < 0 || v5 < 0) {
                break;
            }
            if(this.mOrientation == 1) {
                if(v4 < v && this.getColumnIndices(v3).contains(v1)) {
                    this.mRowWithAccessibilityFocus = v4;
                    return v3;
                }
            }
            else if(v4 < v && v5 == v1) {
                this.mRowWithAccessibilityFocus = (int)(((Integer)Collections.max(this.getRowIndices(v3))));
                return v3;
            }
        }
        return -1;
    }

    private int findScrollTargetPositionBelow(int v, int v1, int v2) {
        for(int v3 = v2 + 1; v3 < this.getItemCount(); ++v3) {
            int v4 = this.getRowIndex(v3);
            int v5 = this.getColumnIndex(v3);
            if(v4 < 0 || v5 < 0) {
                break;
            }
            if(this.mOrientation == 1) {
                if(v4 > v && (v5 == v1 || this.getColumnIndices(v3).contains(v1))) {
                    this.mRowWithAccessibilityFocus = v4;
                    return v3;
                }
            }
            else if(v4 > v && v5 == v1) {
                this.mRowWithAccessibilityFocus = this.getRowIndex(v3);
                return v3;
            }
        }
        return -1;
    }

    private int findScrollTargetPositionOnTheLeft(int v, int v1, int v2) {
        for(int v3 = v2 - 1; v3 >= 0; --v3) {
            int v4 = this.getRowIndex(v3);
            int v5 = this.getColumnIndex(v3);
            if(v4 < 0 || v5 < 0) {
                break;
            }
            if(this.mOrientation == 1) {
                if(v4 == v && v5 < v1 || v4 < v) {
                    this.mRowWithAccessibilityFocus = v4;
                    this.mColumnWithAccessibilityFocus = v5;
                    return v3;
                }
            }
            else if(this.getRowIndices(v3).contains(v) && v5 < v1) {
                this.mColumnWithAccessibilityFocus = v5;
                return v3;
            }
        }
        return -1;
    }

    private int findScrollTargetPositionOnTheRight(int v, int v1, int v2) {
        for(int v3 = v2 + 1; v3 < this.getItemCount(); ++v3) {
            int v4 = this.getRowIndex(v3);
            int v5 = this.getColumnIndex(v3);
            if(v4 < 0 || v5 < 0) {
                break;
            }
            if(this.mOrientation == 1) {
                if(v4 == v && v5 > v1 || v4 > v) {
                    this.mRowWithAccessibilityFocus = v4;
                    this.mColumnWithAccessibilityFocus = v5;
                    return v3;
                }
            }
            else if(v5 > v1 && this.getRowIndices(v3).contains(v)) {
                this.mColumnWithAccessibilityFocus = v5;
                return v3;
            }
        }
        return -1;
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public androidx.recyclerview.widget.RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public androidx.recyclerview.widget.RecyclerView.LayoutParams generateLayoutParams(Context context0, AttributeSet attributeSet0) {
        return new LayoutParams(context0, attributeSet0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public androidx.recyclerview.widget.RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int getColumnCountForAccessibility(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(this.mOrientation == 1) {
            return Math.min(this.mSpanCount, this.getItemCount());
        }
        return recyclerView$State0.getItemCount() >= 1 ? this.getSpanGroupIndex(recyclerView$Recycler0, recyclerView$State0, recyclerView$State0.getItemCount() - 1) + 1 : 0;
    }

    private int getColumnIndex(int v) {
        return this.mOrientation == 0 ? this.getSpanGroupIndex(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v) : this.getSpanIndex(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v);
    }

    private Set getColumnIndices(int v) {
        return this.getRowOrColumnIndices(this.getColumnIndex(v), v);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int getRowCountForAccessibility(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(this.mOrientation == 0) {
            return Math.min(this.mSpanCount, this.getItemCount());
        }
        return recyclerView$State0.getItemCount() >= 1 ? this.getSpanGroupIndex(recyclerView$Recycler0, recyclerView$State0, recyclerView$State0.getItemCount() - 1) + 1 : 0;
    }

    private int getRowIndex(int v) {
        return this.mOrientation == 1 ? this.getSpanGroupIndex(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v) : this.getSpanIndex(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v);
    }

    private Set getRowIndices(int v) {
        return this.getRowOrColumnIndices(this.getRowIndex(v), v);
    }

    private Set getRowOrColumnIndices(int v, int v1) {
        Set set0 = new HashSet();
        int v2 = this.getSpanSize(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v1);
        for(int v3 = v; v3 < v + v2; ++v3) {
            set0.add(v3);
        }
        return set0;
    }

    int getSpaceForSpanRange(int v, int v1) {
        return this.mOrientation != 1 || !this.isLayoutRTL() ? this.mCachedBorders[v1 + v] - this.mCachedBorders[v] : this.mCachedBorders[this.mSpanCount - v] - this.mCachedBorders[this.mSpanCount - v - v1];
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    private int getSpanGroupIndex(Recycler recyclerView$Recycler0, State recyclerView$State0, int v) {
        if(!recyclerView$State0.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanGroupIndex(v, this.mSpanCount);
        }
        int v1 = recyclerView$Recycler0.convertPreLayoutPositionToPostLayout(v);
        if(v1 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + v);
            return 0;
        }
        return this.mSpanSizeLookup.getCachedSpanGroupIndex(v1, this.mSpanCount);
    }

    private int getSpanIndex(Recycler recyclerView$Recycler0, State recyclerView$State0, int v) {
        if(!recyclerView$State0.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(v, this.mSpanCount);
        }
        int v1 = this.mPreLayoutSpanIndexCache.get(v, -1);
        if(v1 != -1) {
            return v1;
        }
        int v2 = recyclerView$Recycler0.convertPreLayoutPositionToPostLayout(v);
        if(v2 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + v);
            return 0;
        }
        return this.mSpanSizeLookup.getCachedSpanIndex(v2, this.mSpanCount);
    }

    private int getSpanSize(Recycler recyclerView$Recycler0, State recyclerView$State0, int v) {
        if(!recyclerView$State0.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(v);
        }
        int v1 = this.mPreLayoutSpanSizeCache.get(v, -1);
        if(v1 != -1) {
            return v1;
        }
        int v2 = recyclerView$Recycler0.convertPreLayoutPositionToPostLayout(v);
        if(v2 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + v);
            return 1;
        }
        return this.mSpanSizeLookup.getSpanSize(v2);
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    private void guessMeasurement(float f, int v) {
        this.calculateItemBorders(Math.max(Math.round(f * ((float)this.mSpanCount)), v));
    }

    // 去混淆评级： 低(20)
    private boolean hasAccessibilityFocusChanged(int v) {
        return !this.getRowIndices(v).contains(this.mRowWithAccessibilityFocus) || !this.getColumnIndices(v).contains(this.mColumnWithAccessibilityFocus);
    }

    public boolean isUsingSpansToEstimateScrollbarDimensions() {
        return this.mUsingSpansToEstimateScrollBarDimensions;
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    void layoutChunk(Recycler recyclerView$Recycler0, State recyclerView$State0, LayoutState linearLayoutManager$LayoutState0, LayoutChunkResult linearLayoutManager$LayoutChunkResult0) {
        int v24;
        int v23;
        int v22;
        int v21;
        int v20;
        int v19;
        int v18;
        int v16;
        int v15;
        int v = this.mOrientationHelper.getModeInOther();
        int v1 = this.getChildCount() <= 0 ? 0 : this.mCachedBorders[this.mSpanCount];
        if(v != 0x40000000) {
            this.updateMeasurements();
        }
        boolean z = linearLayoutManager$LayoutState0.mItemDirection == 1;
        int v2 = z ? this.mSpanCount : this.getSpanIndex(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$LayoutState0.mCurrentPosition) + this.getSpanSize(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$LayoutState0.mCurrentPosition);
        int v3;
        for(v3 = 0; v3 < this.mSpanCount && linearLayoutManager$LayoutState0.hasMore(recyclerView$State0) && v2 > 0; ++v3) {
            int v4 = linearLayoutManager$LayoutState0.mCurrentPosition;
            int v5 = this.getSpanSize(recyclerView$Recycler0, recyclerView$State0, v4);
            if(v5 > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + v4 + " requires " + v5 + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            v2 -= v5;
            if(v2 < 0) {
                break;
            }
            View view0 = linearLayoutManager$LayoutState0.next(recyclerView$Recycler0);
            if(view0 == null) {
                break;
            }
            this.mSet[v3] = view0;
        }
        if(v3 == 0) {
            linearLayoutManager$LayoutChunkResult0.mFinished = true;
            return;
        }
        this.assignSpans(recyclerView$Recycler0, recyclerView$State0, v3, z);
        float f = 0.0f;
        int v7 = 0;
        for(int v6 = 0; v6 < v3; ++v6) {
            View view1 = this.mSet[v6];
            if(linearLayoutManager$LayoutState0.mScrapList == null) {
                if(z) {
                    this.addView(view1);
                }
                else {
                    this.addView(view1, 0);
                }
            }
            else if(z) {
                this.addDisappearingView(view1);
            }
            else {
                this.addDisappearingView(view1, 0);
            }
            this.calculateItemDecorationsForChild(view1, this.mDecorInsets);
            this.measureChild(view1, v, false);
            int v8 = this.mOrientationHelper.getDecoratedMeasurement(view1);
            if(v8 > v7) {
                v7 = v8;
            }
            LayoutParams gridLayoutManager$LayoutParams0 = (LayoutParams)view1.getLayoutParams();
            float f1 = ((float)this.mOrientationHelper.getDecoratedMeasurementInOther(view1)) * 1.0f / ((float)gridLayoutManager$LayoutParams0.mSpanSize);
            if(f1 > f) {
                f = f1;
            }
        }
        if(v != 0x40000000) {
            this.guessMeasurement(f, v1);
            v7 = 0;
            for(int v9 = 0; v9 < v3; ++v9) {
                View view2 = this.mSet[v9];
                this.measureChild(view2, 0x40000000, true);
                int v10 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                if(v10 > v7) {
                    v7 = v10;
                }
            }
        }
        for(int v11 = 0; v11 < v3; ++v11) {
            View view3 = this.mSet[v11];
            if(this.mOrientationHelper.getDecoratedMeasurement(view3) != v7) {
                LayoutParams gridLayoutManager$LayoutParams1 = (LayoutParams)view3.getLayoutParams();
                int v12 = gridLayoutManager$LayoutParams1.mDecorInsets.top + gridLayoutManager$LayoutParams1.mDecorInsets.bottom + gridLayoutManager$LayoutParams1.topMargin + gridLayoutManager$LayoutParams1.bottomMargin;
                int v13 = gridLayoutManager$LayoutParams1.mDecorInsets.left + gridLayoutManager$LayoutParams1.mDecorInsets.right + gridLayoutManager$LayoutParams1.leftMargin + gridLayoutManager$LayoutParams1.rightMargin;
                int v14 = this.getSpaceForSpanRange(gridLayoutManager$LayoutParams1.mSpanIndex, gridLayoutManager$LayoutParams1.mSpanSize);
                if(this.mOrientation == 1) {
                    v15 = GridLayoutManager.getChildMeasureSpec(v14, 0x40000000, v13, gridLayoutManager$LayoutParams1.width, false);
                    v16 = View.MeasureSpec.makeMeasureSpec(v7 - v12, 0x40000000);
                }
                else {
                    int v17 = View.MeasureSpec.makeMeasureSpec(v7 - v13, 0x40000000);
                    v16 = GridLayoutManager.getChildMeasureSpec(v14, 0x40000000, v12, gridLayoutManager$LayoutParams1.height, false);
                    v15 = v17;
                }
                this.measureChildWithDecorationsAndMargin(view3, v15, v16, true);
            }
        }
        linearLayoutManager$LayoutChunkResult0.mConsumed = v7;
        if(this.mOrientation == 1) {
            if(linearLayoutManager$LayoutState0.mLayoutDirection == -1) {
                v18 = linearLayoutManager$LayoutState0.mOffset;
                v19 = v18 - v7;
            }
            else {
                v19 = linearLayoutManager$LayoutState0.mOffset;
                v18 = v19 + v7;
            }
            v20 = v19;
            v21 = 0;
            v22 = 0;
        }
        else {
            if(linearLayoutManager$LayoutState0.mLayoutDirection == -1) {
                v23 = linearLayoutManager$LayoutState0.mOffset;
                v24 = v23 - v7;
            }
            else {
                v24 = linearLayoutManager$LayoutState0.mOffset;
                v23 = v24 + v7;
            }
            v22 = v24;
            v20 = 0;
            v21 = v23;
            v18 = 0;
        }
        for(int v25 = 0; v25 < v3; ++v25) {
            View view4 = this.mSet[v25];
            LayoutParams gridLayoutManager$LayoutParams2 = (LayoutParams)view4.getLayoutParams();
            if(this.mOrientation != 1) {
                int v27 = this.getPaddingTop();
                v20 = this.mCachedBorders[gridLayoutManager$LayoutParams2.mSpanIndex] + v27;
                v18 = this.mOrientationHelper.getDecoratedMeasurementInOther(view4) + v20;
            }
            else if(this.isLayoutRTL()) {
                v21 = this.getPaddingLeft() + this.mCachedBorders[this.mSpanCount - gridLayoutManager$LayoutParams2.mSpanIndex];
                v22 = v21 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            }
            else {
                int v26 = this.getPaddingLeft();
                v22 = this.mCachedBorders[gridLayoutManager$LayoutParams2.mSpanIndex] + v26;
                v21 = this.mOrientationHelper.getDecoratedMeasurementInOther(view4) + v22;
            }
            this.layoutDecoratedWithMargins(view4, v22, v20, v21, v18);
            if(gridLayoutManager$LayoutParams2.isItemRemoved() || gridLayoutManager$LayoutParams2.isItemChanged()) {
                linearLayoutManager$LayoutChunkResult0.mIgnoreConsumed = true;
            }
            boolean z1 = linearLayoutManager$LayoutChunkResult0.mFocusable;
            linearLayoutManager$LayoutChunkResult0.mFocusable = view4.hasFocusable() | z1;
        }
        Arrays.fill(this.mSet, null);
    }

    private void measureChild(View view0, int v, boolean z) {
        int v5;
        int v4;
        LayoutParams gridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v1 = gridLayoutManager$LayoutParams0.mDecorInsets.top + gridLayoutManager$LayoutParams0.mDecorInsets.bottom + gridLayoutManager$LayoutParams0.topMargin + gridLayoutManager$LayoutParams0.bottomMargin;
        int v2 = gridLayoutManager$LayoutParams0.mDecorInsets.left + gridLayoutManager$LayoutParams0.mDecorInsets.right + gridLayoutManager$LayoutParams0.leftMargin + gridLayoutManager$LayoutParams0.rightMargin;
        int v3 = this.getSpaceForSpanRange(gridLayoutManager$LayoutParams0.mSpanIndex, gridLayoutManager$LayoutParams0.mSpanSize);
        if(this.mOrientation == 1) {
            v4 = GridLayoutManager.getChildMeasureSpec(v3, v, v2, gridLayoutManager$LayoutParams0.width, false);
            v5 = GridLayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.getHeightMode(), v1, gridLayoutManager$LayoutParams0.height, true);
        }
        else {
            v5 = GridLayoutManager.getChildMeasureSpec(v3, v, v1, gridLayoutManager$LayoutParams0.height, false);
            v4 = GridLayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.getWidthMode(), v2, gridLayoutManager$LayoutParams0.width, true);
        }
        this.measureChildWithDecorationsAndMargin(view0, v4, v5, z);
    }

    private void measureChildWithDecorationsAndMargin(View view0, int v, int v1, boolean z) {
        androidx.recyclerview.widget.RecyclerView.LayoutParams recyclerView$LayoutParams0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams)view0.getLayoutParams();
        if((z ? this.shouldReMeasureChild(view0, v, v1, recyclerView$LayoutParams0) : this.shouldMeasureChild(view0, v, v1, recyclerView$LayoutParams0))) {
            view0.measure(v, v1);
        }
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    void onAnchorReady(Recycler recyclerView$Recycler0, State recyclerView$State0, AnchorInfo linearLayoutManager$AnchorInfo0, int v) {
        super.onAnchorReady(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$AnchorInfo0, v);
        this.updateMeasurements();
        if(recyclerView$State0.getItemCount() > 0 && !recyclerView$State0.isPreLayout()) {
            this.ensureAnchorIsInCorrectSpan(recyclerView$Recycler0, recyclerView$State0, linearLayoutManager$AnchorInfo0, v);
        }
        this.ensureViewSet();
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public View onFocusSearchFailed(View view0, int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        int v16;
        int v15;
        int v14;
        View view6;
        View view5;
        int v5;
        int v4;
        int v3;
        View view1 = this.findContainingItemView(view0);
        View view2 = null;
        if(view1 == null) {
            return null;
        }
        LayoutParams gridLayoutManager$LayoutParams0 = (LayoutParams)view1.getLayoutParams();
        int v1 = gridLayoutManager$LayoutParams0.mSpanIndex;
        int v2 = gridLayoutManager$LayoutParams0.mSpanIndex + gridLayoutManager$LayoutParams0.mSpanSize;
        if(super.onFocusSearchFailed(view0, v, recyclerView$Recycler0, recyclerView$State0) == null) {
            return null;
        }
        if(this.convertFocusDirectionToLayoutDirection(v) == 1 == this.mShouldReverseLayout) {
            v4 = this.getChildCount();
            v3 = 0;
            v5 = 1;
        }
        else {
            v3 = this.getChildCount() - 1;
            v4 = -1;
            v5 = -1;
        }
        int v6 = this.mOrientation != 1 || !this.isLayoutRTL() ? 0 : 1;
        int v7 = this.getSpanGroupIndex(recyclerView$Recycler0, recyclerView$State0, v3);
        int v8 = v3;
        int v9 = 0;
        int v10 = -1;
        int v11 = -1;
        int v12 = 0;
        View view3 = null;
        while(v8 != v4) {
            int v13 = this.getSpanGroupIndex(recyclerView$Recycler0, recyclerView$State0, v8);
            View view4 = this.getChildAt(v8);
            if(view4 == view1) {
                break;
            }
            if(!view4.hasFocusable() || v13 == v7) {
                LayoutParams gridLayoutManager$LayoutParams1 = (LayoutParams)view4.getLayoutParams();
                int v17 = gridLayoutManager$LayoutParams1.mSpanIndex;
                int v18 = gridLayoutManager$LayoutParams1.mSpanIndex + gridLayoutManager$LayoutParams1.mSpanSize;
                if(view4.hasFocusable() && v17 == v1 && v18 == v2) {
                    return view4;
                }
                if((!view4.hasFocusable() || view2 != null) && (view4.hasFocusable() || view3 != null)) {
                    view5 = view2;
                    int v19 = Math.min(v18, v2) - Math.max(v17, v1);
                    if(view4.hasFocusable()) {
                        if(v19 <= v9 && (v19 != v9 || v6 != (v17 <= v10 ? 0 : 1))) {
                            view6 = view3;
                            v14 = v9;
                            v15 = v11;
                            v16 = v12;
                            goto label_94;
                        }
                        else {
                        label_49:
                            view6 = view3;
                            v14 = v9;
                            v15 = v11;
                            v16 = v12;
                            goto label_73;
                        }
                        goto label_59;
                    }
                    else {
                    label_59:
                        if(view5 == null) {
                            view6 = view3;
                            v14 = v9;
                            int v20 = 1;
                            if(this.isViewPartiallyVisible(view4, false, true)) {
                                v16 = v12;
                                if(v19 > v16) {
                                    v15 = v11;
                                    goto label_73;
                                }
                                else if(v19 == v16) {
                                    v15 = v11;
                                    if(v17 <= v15) {
                                        v20 = 0;
                                    }
                                    if(v6 == v20) {
                                    label_73:
                                        if(view4.hasFocusable()) {
                                            v11 = v15;
                                            v12 = v16;
                                            v10 = gridLayoutManager$LayoutParams1.mSpanIndex;
                                            view3 = view6;
                                            view2 = view4;
                                            v9 = Math.min(v18, v2) - Math.max(v17, v1);
                                            goto label_99;
                                        }
                                        else {
                                            v12 = Math.min(v18, v2) - Math.max(v17, v1);
                                            view3 = view4;
                                            v11 = gridLayoutManager$LayoutParams1.mSpanIndex;
                                            goto label_97;
                                        }
                                        goto label_85;
                                    }
                                }
                                else {
                                label_85:
                                    v15 = v11;
                                }
                            }
                            else {
                                v15 = v11;
                                v16 = v12;
                            }
                        }
                        else {
                            view6 = view3;
                            v14 = v9;
                            v15 = v11;
                            v16 = v12;
                        }
                    }
                }
                else {
                    view5 = view2;
                    goto label_49;
                }
            }
            else {
                if(view2 != null) {
                    break;
                }
                view5 = null;
                view6 = view3;
                v14 = v9;
                v15 = v11;
                v16 = v12;
            }
        label_94:
            v11 = v15;
            v12 = v16;
            view3 = view6;
        label_97:
            v9 = v14;
            view2 = view5;
        label_99:
            v8 += v5;
        }
        return view2 == null ? view3 : view2;
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public void onInitializeAccessibilityNodeInfo(Recycler recyclerView$Recycler0, State recyclerView$State0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        super.onInitializeAccessibilityNodeInfo(recyclerView$Recycler0, recyclerView$State0, accessibilityNodeInfoCompat0);
        accessibilityNodeInfoCompat0.setClassName("android.widget.GridView");
        if(this.mRecyclerView.mAdapter != null && this.mRecyclerView.mAdapter.getItemCount() > 1) {
            accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_IN_DIRECTION);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(Recycler recyclerView$Recycler0, State recyclerView$State0, View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view0, accessibilityNodeInfoCompat0);
            return;
        }
        int v = this.getSpanGroupIndex(recyclerView$Recycler0, recyclerView$State0, ((LayoutParams)viewGroup$LayoutParams0).getViewLayoutPosition());
        if(this.mOrientation == 0) {
            accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(((LayoutParams)viewGroup$LayoutParams0).getSpanIndex(), ((LayoutParams)viewGroup$LayoutParams0).getSpanSize(), v, 1, false, false));
            return;
        }
        accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(v, 1, ((LayoutParams)viewGroup$LayoutParams0).getSpanIndex(), ((LayoutParams)viewGroup$LayoutParams0).getSpanSize(), false, false));
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsAdded(RecyclerView recyclerView0, int v, int v1) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsChanged(RecyclerView recyclerView0) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsMoved(RecyclerView recyclerView0, int v, int v1, int v2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView0, int v, int v1) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView0, int v, int v1, Object object0) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public void onLayoutChildren(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(recyclerView$State0.isPreLayout()) {
            this.cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recyclerView$Recycler0, recyclerView$State0);
        this.clearPreLayoutSpanMappingCache();
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public void onLayoutCompleted(State recyclerView$State0) {
        super.onLayoutCompleted(recyclerView$State0);
        this.mPendingSpanCountChange = false;
        int v = this.mPositionTargetedByScrollInDirection;
        if(v != -1) {
            View view0 = this.findViewByPosition(v);
            if(view0 != null) {
                view0.sendAccessibilityEvent(0x4000000);
                this.mPositionTargetedByScrollInDirection = -1;
            }
        }
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    boolean performAccessibilityAction(int v, Bundle bundle0) {
        int v7;
        if(v == AccessibilityActionCompat.ACTION_SCROLL_IN_DIRECTION.getId() && v != -1) {
            View view0 = this.findChildWithAccessibilityFocus();
            if(view0 == null) {
                return false;
            }
            if(bundle0 == null) {
                return false;
            }
            int v1 = bundle0.getInt("android.view.accessibility.action.ARGUMENT_DIRECTION_INT", -1);
            if(!GridLayoutManager.sSupportedDirectionsForActionScrollInDirection.contains(v1)) {
                return false;
            }
            ViewHolder recyclerView$ViewHolder0 = this.mRecyclerView.getChildViewHolder(view0);
            if(recyclerView$ViewHolder0 == null) {
                return false;
            }
            int v2 = recyclerView$ViewHolder0.getAbsoluteAdapterPosition();
            int v3 = this.getRowIndex(v2);
            int v4 = this.getColumnIndex(v2);
            if(v3 >= 0 && v4 >= 0) {
                if(this.hasAccessibilityFocusChanged(v2)) {
                    this.mRowWithAccessibilityFocus = v3;
                    this.mColumnWithAccessibilityFocus = v4;
                }
                int v5 = this.mRowWithAccessibilityFocus == -1 ? v3 : this.mRowWithAccessibilityFocus;
                int v6 = this.mColumnWithAccessibilityFocus;
                if(v6 != -1) {
                    v4 = v6;
                }
                if(v1 == 17) {
                    v7 = this.findScrollTargetPositionOnTheLeft(v5, v4, v2);
                }
                else {
                    switch(v1) {
                        case 33: {
                            v7 = this.findScrollTargetPositionAbove(v5, v4, v2);
                            break;
                        }
                        case 66: {
                            v7 = this.findScrollTargetPositionOnTheRight(v5, v4, v2);
                            break;
                        }
                        default: {
                            if(v1 != 130) {
                                return false;
                            }
                            v7 = this.findScrollTargetPositionBelow(v5, v4, v2);
                        }
                    }
                }
                if(v7 == -1 && this.mOrientation == 0) {
                    if(v1 == 17) {
                        v7 = this.findPositionOfLastItemOnARowAboveForHorizontalGrid(v3);
                    }
                    else if(v1 == 66) {
                        v7 = this.findPositionOfFirstItemOnARowBelowForHorizontalGrid(v3);
                    }
                }
                if(v7 != -1) {
                    this.scrollToPosition(v7);
                    this.mPositionTargetedByScrollInDirection = v7;
                    return true;
                }
            }
            return false;
        }
        if(v == 0x1020037 && bundle0 != null) {
            int v8 = bundle0.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
            int v9 = bundle0.getInt("android.view.accessibility.action.ARGUMENT_COLUMN_INT", -1);
            if(v8 != -1 && v9 != -1) {
                int v10 = this.mRecyclerView.mAdapter.getItemCount();
                int v11;
                for(v11 = 0; true; ++v11) {
                    if(v11 >= v10) {
                        v11 = -1;
                        break;
                    }
                    int v12 = this.getSpanIndex(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v11);
                    int v13 = this.getSpanGroupIndex(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v11);
                    if((this.mOrientation == 1 ? v12 == v9 && v13 == v8 : v12 == v8 && v13 == v9)) {
                        break;
                    }
                }
                if(v11 > -1) {
                    this.scrollToPositionWithOffset(v11, 0);
                    return true;
                }
            }
            return false;
        }
        return super.performAccessibilityAction(v, bundle0);
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public int scrollHorizontallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollHorizontallyBy(v, recyclerView$Recycler0, recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public int scrollVerticallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollVerticallyBy(v, recyclerView$Recycler0, recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void setMeasuredDimension(Rect rect0, int v, int v1) {
        int v5;
        int v4;
        if(this.mCachedBorders == null) {
            super.setMeasuredDimension(rect0, v, v1);
        }
        int v2 = this.getPaddingLeft() + this.getPaddingRight();
        int v3 = this.getPaddingTop() + this.getPaddingBottom();
        if(this.mOrientation == 1) {
            v4 = GridLayoutManager.chooseSize(v1, rect0.height() + v3, this.getMinimumHeight());
            v5 = GridLayoutManager.chooseSize(v, this.mCachedBorders[this.mCachedBorders.length - 1] + v2, this.getMinimumWidth());
        }
        else {
            v5 = GridLayoutManager.chooseSize(v, rect0.width() + v2, this.getMinimumWidth());
            v4 = GridLayoutManager.chooseSize(v1, this.mCachedBorders[this.mCachedBorders.length - 1] + v3, this.getMinimumHeight());
        }
        this.setMeasuredDimension(v5, v4);
    }

    public void setSpanCount(int v) {
        if(v == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if(v < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + v);
        }
        this.mSpanCount = v;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.requestLayout();
    }

    public void setSpanSizeLookup(SpanSizeLookup gridLayoutManager$SpanSizeLookup0) {
        this.mSpanSizeLookup = gridLayoutManager$SpanSizeLookup0;
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public void setStackFromEnd(boolean z) {
        if(z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    public void setUsingSpansToEstimateScrollbarDimensions(boolean z) {
        this.mUsingSpansToEstimateScrollBarDimensions = z;
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }

    private void updateMeasurements() {
        int v1;
        int v;
        if(this.getOrientation() == 1) {
            v = this.getWidth() - this.getPaddingRight();
            v1 = this.getPaddingLeft();
        }
        else {
            v = this.getHeight() - this.getPaddingBottom();
            v1 = this.getPaddingTop();
        }
        this.calculateItemBorders(v - v1);
    }
}

