package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends LayoutManager implements ScrollVectorProvider {
    class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;

        AnchorInfo() {
            this.reset();
        }

        void assignCoordinateFromPadding() {
            this.mOffset = this.mLayoutFromEnd ? StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() : StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
        }

        void assignCoordinateFromPadding(int v) {
            if(this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - v;
                return;
            }
            this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + v;
        }

        void reset() {
            this.mPosition = -1;
            this.mOffset = 0x80000000;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            int[] arr_v = this.mSpanReferenceLines;
            if(arr_v != null) {
                Arrays.fill(arr_v, -1);
            }
        }

        void saveSpanReferenceLines(Span[] arr_staggeredGridLayoutManager$Span) {
            if(this.mSpanReferenceLines == null || this.mSpanReferenceLines.length < arr_staggeredGridLayoutManager$Span.length) {
                this.mSpanReferenceLines = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for(int v = 0; v < arr_staggeredGridLayoutManager$Span.length; ++v) {
                int[] arr_v = this.mSpanReferenceLines;
                arr_v[v] = arr_staggeredGridLayoutManager$Span[v].getStartLine(0x80000000);
            }
        }
    }

    public static class LayoutParams extends androidx.recyclerview.widget.RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;

        public LayoutParams(int v, int v1) {
            super(v, v1);
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
        }

        public LayoutParams(androidx.recyclerview.widget.RecyclerView.LayoutParams recyclerView$LayoutParams0) {
            super(recyclerView$LayoutParams0);
        }

        public final int getSpanIndex() {
            return this.mSpan == null ? -1 : this.mSpan.mIndex;
        }

        public boolean isFullSpan() {
            return this.mFullSpan;
        }

        public void setFullSpan(boolean z) {
            this.mFullSpan = z;
        }
    }

    static class LazySpanLookup {
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator CREATOR;
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;

            static {
                FullSpanItem.CREATOR = new Parcelable.Creator() {
                    public FullSpanItem createFromParcel(Parcel parcel0) {
                        return new FullSpanItem(parcel0);
                    }

                    @Override  // android.os.Parcelable$Creator
                    public Object createFromParcel(Parcel parcel0) {
                        return this.createFromParcel(parcel0);
                    }

                    public FullSpanItem[] newArray(int v) {
                        return new FullSpanItem[v];
                    }

                    @Override  // android.os.Parcelable$Creator
                    public Object[] newArray(int v) {
                        return this.newArray(v);
                    }
                };
            }

            FullSpanItem() {
            }

            FullSpanItem(Parcel parcel0) {
                this.mPosition = parcel0.readInt();
                this.mGapDir = parcel0.readInt();
                this.mHasUnwantedGapAfter = parcel0.readInt() == 1;
                int v = parcel0.readInt();
                if(v > 0) {
                    int[] arr_v = new int[v];
                    this.mGapPerSpan = arr_v;
                    parcel0.readIntArray(arr_v);
                }
            }

            @Override  // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            int getGapForSpan(int v) {
                return this.mGapPerSpan == null ? 0 : this.mGapPerSpan[v];
            }

            @Override
            public String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }

            @Override  // android.os.Parcelable
            public void writeToParcel(Parcel parcel0, int v) {
                parcel0.writeInt(this.mPosition);
                parcel0.writeInt(this.mGapDir);
                parcel0.writeInt(((int)this.mHasUnwantedGapAfter));
                int[] arr_v = this.mGapPerSpan;
                if(arr_v != null && arr_v.length > 0) {
                    parcel0.writeInt(arr_v.length);
                    parcel0.writeIntArray(this.mGapPerSpan);
                    return;
                }
                parcel0.writeInt(0);
            }
        }

        private static final int MIN_SIZE = 10;
        int[] mData;
        List mFullSpanItems;

        public void addFullSpanItem(FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0) {
            if(this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList();
            }
            int v = this.mFullSpanItems.size();
            for(int v1 = 0; v1 < v; ++v1) {
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 = (FullSpanItem)this.mFullSpanItems.get(v1);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mPosition == staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition) {
                    this.mFullSpanItems.remove(v1);
                }
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mPosition >= staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition) {
                    this.mFullSpanItems.add(v1, staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0);
                    return;
                }
            }
            this.mFullSpanItems.add(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0);
        }

        void clear() {
            int[] arr_v = this.mData;
            if(arr_v != null) {
                Arrays.fill(arr_v, -1);
            }
            this.mFullSpanItems = null;
        }

        void ensureSize(int v) {
            int[] arr_v = this.mData;
            if(arr_v == null) {
                int[] arr_v1 = new int[Math.max(v, 10) + 1];
                this.mData = arr_v1;
                Arrays.fill(arr_v1, -1);
                return;
            }
            if(v >= arr_v.length) {
                int[] arr_v2 = new int[this.sizeForPosition(v)];
                this.mData = arr_v2;
                System.arraycopy(arr_v, 0, arr_v2, 0, arr_v.length);
                Arrays.fill(this.mData, arr_v.length, this.mData.length, -1);
            }
        }

        int forceInvalidateAfter(int v) {
            List list0 = this.mFullSpanItems;
            if(list0 != null) {
                for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                    if(((FullSpanItem)this.mFullSpanItems.get(v1)).mPosition >= v) {
                        this.mFullSpanItems.remove(v1);
                    }
                }
            }
            return this.invalidateAfter(v);
        }

        public FullSpanItem getFirstFullSpanItemInRange(int v, int v1, int v2, boolean z) {
            List list0 = this.mFullSpanItems;
            if(list0 == null) {
                return null;
            }
            int v3 = list0.size();
            for(int v4 = 0; v4 < v3; ++v4) {
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = (FullSpanItem)this.mFullSpanItems.get(v4);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition >= v1) {
                    return null;
                }
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition >= v && (v2 == 0 || staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapDir == v2 || z && staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mHasUnwantedGapAfter)) {
                    return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0;
                }
            }
            return null;
        }

        public FullSpanItem getFullSpanItem(int v) {
            List list0 = this.mFullSpanItems;
            if(list0 == null) {
                return null;
            }
            for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = (FullSpanItem)this.mFullSpanItems.get(v1);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition == v) {
                    return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0;
                }
            }
            return null;
        }

        int getSpan(int v) {
            return this.mData == null || v >= this.mData.length ? -1 : this.mData[v];
        }

        int invalidateAfter(int v) {
            int[] arr_v = this.mData;
            if(arr_v == null) {
                return -1;
            }
            if(v >= arr_v.length) {
                return -1;
            }
            int v1 = this.invalidateFullSpansAfter(v);
            if(v1 == -1) {
                Arrays.fill(this.mData, v, this.mData.length, -1);
                return this.mData.length;
            }
            int v2 = Math.min(v1 + 1, this.mData.length);
            Arrays.fill(this.mData, v, v2, -1);
            return v2;
        }

        private int invalidateFullSpansAfter(int v) {
            if(this.mFullSpanItems == null) {
                return -1;
            }
            FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = this.getFullSpanItem(v);
            if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 != null) {
                this.mFullSpanItems.remove(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0);
            }
            int v1 = this.mFullSpanItems.size();
            int v2;
            for(v2 = 0; true; ++v2) {
                if(v2 >= v1) {
                    v2 = -1;
                    break;
                }
                if(((FullSpanItem)this.mFullSpanItems.get(v2)).mPosition >= v) {
                    break;
                }
            }
            if(v2 != -1) {
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 = (FullSpanItem)this.mFullSpanItems.get(v2);
                this.mFullSpanItems.remove(v2);
                return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mPosition;
            }
            return -1;
        }

        void offsetForAddition(int v, int v1) {
            if(this.mData != null && v < this.mData.length) {
                int v2 = v + v1;
                this.ensureSize(v2);
                System.arraycopy(this.mData, v, this.mData, v2, this.mData.length - v - v1);
                Arrays.fill(this.mData, v, v2, -1);
                this.offsetFullSpansForAddition(v, v1);
            }
        }

        void offsetForRemoval(int v, int v1) {
            if(this.mData != null && v < this.mData.length) {
                int v2 = v + v1;
                this.ensureSize(v2);
                System.arraycopy(this.mData, v2, this.mData, v, this.mData.length - v - v1);
                Arrays.fill(this.mData, this.mData.length - v1, this.mData.length, -1);
                this.offsetFullSpansForRemoval(v, v1);
            }
        }

        private void offsetFullSpansForAddition(int v, int v1) {
            List list0 = this.mFullSpanItems;
            if(list0 != null) {
                for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                    FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = (FullSpanItem)this.mFullSpanItems.get(v2);
                    if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition >= v) {
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition += v1;
                    }
                }
            }
        }

        private void offsetFullSpansForRemoval(int v, int v1) {
            List list0 = this.mFullSpanItems;
            if(list0 != null) {
                for(int v2 = list0.size() - 1; v2 >= 0; --v2) {
                    FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = (FullSpanItem)this.mFullSpanItems.get(v2);
                    if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition >= v) {
                        if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition < v + v1) {
                            this.mFullSpanItems.remove(v2);
                        }
                        else {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition -= v1;
                        }
                    }
                }
            }
        }

        void setSpan(int v, Span staggeredGridLayoutManager$Span0) {
            this.ensureSize(v);
            this.mData[v] = staggeredGridLayoutManager$Span0.mIndex;
        }

        int sizeForPosition(int v) {
            int v1;
            for(v1 = this.mData.length; v1 <= v; v1 *= 2) {
            }
            return v1;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

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
            this.mVisibleAnchorPosition = parcel0.readInt();
            int v = parcel0.readInt();
            this.mSpanOffsetsSize = v;
            if(v > 0) {
                int[] arr_v = new int[v];
                this.mSpanOffsets = arr_v;
                parcel0.readIntArray(arr_v);
            }
            int v1 = parcel0.readInt();
            this.mSpanLookupSize = v1;
            if(v1 > 0) {
                int[] arr_v1 = new int[v1];
                this.mSpanLookup = arr_v1;
                parcel0.readIntArray(arr_v1);
            }
            boolean z = false;
            this.mReverseLayout = parcel0.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel0.readInt() == 1;
            if(parcel0.readInt() == 1) {
                z = true;
            }
            this.mLastLayoutRTL = z;
            this.mFullSpanItems = parcel0.readArrayList(FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState staggeredGridLayoutManager$SavedState0) {
            this.mSpanOffsetsSize = staggeredGridLayoutManager$SavedState0.mSpanOffsetsSize;
            this.mAnchorPosition = staggeredGridLayoutManager$SavedState0.mAnchorPosition;
            this.mVisibleAnchorPosition = staggeredGridLayoutManager$SavedState0.mVisibleAnchorPosition;
            this.mSpanOffsets = staggeredGridLayoutManager$SavedState0.mSpanOffsets;
            this.mSpanLookupSize = staggeredGridLayoutManager$SavedState0.mSpanLookupSize;
            this.mSpanLookup = staggeredGridLayoutManager$SavedState0.mSpanLookup;
            this.mReverseLayout = staggeredGridLayoutManager$SavedState0.mReverseLayout;
            this.mAnchorLayoutFromEnd = staggeredGridLayoutManager$SavedState0.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = staggeredGridLayoutManager$SavedState0.mLastLayoutRTL;
            this.mFullSpanItems = staggeredGridLayoutManager$SavedState0.mFullSpanItems;
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeInt(this.mAnchorPosition);
            parcel0.writeInt(this.mVisibleAnchorPosition);
            parcel0.writeInt(this.mSpanOffsetsSize);
            if(this.mSpanOffsetsSize > 0) {
                parcel0.writeIntArray(this.mSpanOffsets);
            }
            parcel0.writeInt(this.mSpanLookupSize);
            if(this.mSpanLookupSize > 0) {
                parcel0.writeIntArray(this.mSpanLookup);
            }
            parcel0.writeInt(((int)this.mReverseLayout));
            parcel0.writeInt(((int)this.mAnchorLayoutFromEnd));
            parcel0.writeInt(((int)this.mLastLayoutRTL));
            parcel0.writeList(this.mFullSpanItems);
        }
    }

    class Span {
        static final int INVALID_LINE = 0x80000000;
        int mCachedEnd;
        int mCachedStart;
        int mDeletedSize;
        final int mIndex;
        ArrayList mViews;

        Span(int v) {
            this.mViews = new ArrayList();
            this.mCachedStart = 0x80000000;
            this.mCachedEnd = 0x80000000;
            this.mDeletedSize = 0;
            this.mIndex = v;
        }

        void appendToSpan(View view0) {
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = this.getLayoutParams(view0);
            staggeredGridLayoutManager$LayoutParams0.mSpan = this;
            this.mViews.add(view0);
            this.mCachedEnd = 0x80000000;
            if(this.mViews.size() == 1) {
                this.mCachedStart = 0x80000000;
            }
            if(!staggeredGridLayoutManager$LayoutParams0.isItemRemoved() && !staggeredGridLayoutManager$LayoutParams0.isItemChanged()) {
                return;
            }
            this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view0);
        }

        void cacheReferenceLineAndClear(boolean z, int v) {
            int v1 = z ? this.getEndLine(0x80000000) : this.getStartLine(0x80000000);
            this.clear();
            if(v1 == 0x80000000 || z && v1 < StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() || !z && v1 > StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding()) {
                return;
            }
            if(v != 0x80000000) {
                v1 += v;
            }
            this.mCachedEnd = v1;
            this.mCachedStart = v1;
        }

        void calculateCachedEnd() {
            View view0 = (View)this.mViews.get(this.mViews.size() - 1);
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = this.getLayoutParams(view0);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view0);
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(staggeredGridLayoutManager$LayoutParams0.getViewLayoutPosition());
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 != null && staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapDir == 1) {
                    this.mCachedEnd += staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.getGapForSpan(this.mIndex);
                }
            }
        }

        void calculateCachedStart() {
            View view0 = (View)this.mViews.get(0);
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = this.getLayoutParams(view0);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view0);
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(staggeredGridLayoutManager$LayoutParams0.getViewLayoutPosition());
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 != null && staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapDir == -1) {
                    this.mCachedStart -= staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.getGapForSpan(this.mIndex);
                }
            }
        }

        void clear() {
            this.mViews.clear();
            this.invalidateCache();
            this.mDeletedSize = 0;
        }

        // 去混淆评级： 低(20)
        public int findFirstCompletelyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? this.findOneVisibleChild(this.mViews.size() - 1, -1, true) : this.findOneVisibleChild(0, this.mViews.size(), true);
        }

        // 去混淆评级： 低(20)
        public int findFirstPartiallyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? this.findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true) : this.findOnePartiallyVisibleChild(0, this.mViews.size(), true);
        }

        // 去混淆评级： 低(20)
        public int findFirstVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? this.findOneVisibleChild(this.mViews.size() - 1, -1, false) : this.findOneVisibleChild(0, this.mViews.size(), false);
        }

        // 去混淆评级： 低(20)
        public int findLastCompletelyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? this.findOneVisibleChild(0, this.mViews.size(), true) : this.findOneVisibleChild(this.mViews.size() - 1, -1, true);
        }

        // 去混淆评级： 低(20)
        public int findLastPartiallyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? this.findOnePartiallyVisibleChild(0, this.mViews.size(), true) : this.findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
        }

        // 去混淆评级： 低(20)
        public int findLastVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? this.findOneVisibleChild(0, this.mViews.size(), false) : this.findOneVisibleChild(this.mViews.size() - 1, -1, false);
        }

        int findOnePartiallyOrCompletelyVisibleChild(int v, int v1, boolean z, boolean z1, boolean z2) {
            boolean z4;
            int v2 = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int v3 = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int v4 = v1 <= v ? -1 : 1;
            while(v != v1) {
                View view0 = (View)this.mViews.get(v);
                int v5 = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view0);
                int v6 = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view0);
                boolean z3 = false;
                if(!z2) {
                    if(v5 < v3) {
                        z4 = true;
                    }
                }
                else if(v5 <= v3) {
                    z4 = true;
                }
                else {
                    z4 = false;
                }
                if(!z2) {
                    if(v6 > v2) {
                        z3 = true;
                    }
                }
                else if(v6 >= v2) {
                    z3 = true;
                }
                if(z4 && z3) {
                    if(!z || !z1) {
                        if(z1) {
                            return StaggeredGridLayoutManager.this.getPosition(view0);
                        }
                        if(v5 < v2 || v6 > v3) {
                            return StaggeredGridLayoutManager.this.getPosition(view0);
                        }
                    }
                    else if(v5 >= v2 && v6 <= v3) {
                        return StaggeredGridLayoutManager.this.getPosition(view0);
                    }
                }
                v += v4;
            }
            return -1;
        }

        int findOnePartiallyVisibleChild(int v, int v1, boolean z) {
            return this.findOnePartiallyOrCompletelyVisibleChild(v, v1, false, false, z);
        }

        int findOneVisibleChild(int v, int v1, boolean z) {
            return this.findOnePartiallyOrCompletelyVisibleChild(v, v1, z, true, false);
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        int getEndLine() {
            int v = this.mCachedEnd;
            if(v != 0x80000000) {
                return v;
            }
            this.calculateCachedEnd();
            return this.mCachedEnd;
        }

        int getEndLine(int v) {
            int v1 = this.mCachedEnd;
            if(v1 != 0x80000000) {
                return v1;
            }
            if(this.mViews.size() == 0) {
                return v;
            }
            this.calculateCachedEnd();
            return this.mCachedEnd;
        }

        public View getFocusableViewAfter(int v, int v1) {
            View view0 = null;
            if(v1 == -1) {
                int v2 = this.mViews.size();
                int v3 = 0;
                while(v3 < v2) {
                    View view1 = (View)this.mViews.get(v3);
                    if(StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view1) <= v || !StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view1) >= v || !view1.hasFocusable()) {
                        break;
                    }
                    ++v3;
                    view0 = view1;
                }
                return view0;
            }
            int v4 = this.mViews.size() - 1;
            while(v4 >= 0) {
                View view2 = (View)this.mViews.get(v4);
                if(StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view2) >= v || !StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view2) <= v || !view2.hasFocusable()) {
                    break;
                }
                --v4;
                view0 = view2;
            }
            return view0;
        }

        LayoutParams getLayoutParams(View view0) {
            return (LayoutParams)view0.getLayoutParams();
        }

        int getStartLine() {
            int v = this.mCachedStart;
            if(v != 0x80000000) {
                return v;
            }
            this.calculateCachedStart();
            return this.mCachedStart;
        }

        int getStartLine(int v) {
            int v1 = this.mCachedStart;
            if(v1 != 0x80000000) {
                return v1;
            }
            if(this.mViews.size() == 0) {
                return v;
            }
            this.calculateCachedStart();
            return this.mCachedStart;
        }

        void invalidateCache() {
            this.mCachedStart = 0x80000000;
            this.mCachedEnd = 0x80000000;
        }

        void onOffset(int v) {
            int v1 = this.mCachedStart;
            if(v1 != 0x80000000) {
                this.mCachedStart = v1 + v;
            }
            int v2 = this.mCachedEnd;
            if(v2 != 0x80000000) {
                this.mCachedEnd = v2 + v;
            }
        }

        void popEnd() {
            int v = this.mViews.size();
            View view0 = (View)this.mViews.remove(v - 1);
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = this.getLayoutParams(view0);
            staggeredGridLayoutManager$LayoutParams0.mSpan = null;
            if(staggeredGridLayoutManager$LayoutParams0.isItemRemoved() || staggeredGridLayoutManager$LayoutParams0.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view0);
            }
            if(v == 1) {
                this.mCachedStart = 0x80000000;
            }
            this.mCachedEnd = 0x80000000;
        }

        void popStart() {
            View view0 = (View)this.mViews.remove(0);
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = this.getLayoutParams(view0);
            staggeredGridLayoutManager$LayoutParams0.mSpan = null;
            if(this.mViews.size() == 0) {
                this.mCachedEnd = 0x80000000;
            }
            if(staggeredGridLayoutManager$LayoutParams0.isItemRemoved() || staggeredGridLayoutManager$LayoutParams0.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view0);
            }
            this.mCachedStart = 0x80000000;
        }

        void prependToSpan(View view0) {
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = this.getLayoutParams(view0);
            staggeredGridLayoutManager$LayoutParams0.mSpan = this;
            this.mViews.add(0, view0);
            this.mCachedStart = 0x80000000;
            if(this.mViews.size() == 1) {
                this.mCachedEnd = 0x80000000;
            }
            if(!staggeredGridLayoutManager$LayoutParams0.isItemRemoved() && !staggeredGridLayoutManager$LayoutParams0.isItemChanged()) {
                return;
            }
            this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view0);
        }

        void setLine(int v) {
            this.mCachedStart = v;
            this.mCachedEnd = v;
        }
    }

    static final boolean DEBUG = false;
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    static final int INVALID_OFFSET = 0x80000000;
    private static final float MAX_SCROLL_FACTOR = 0.333333f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    private final AnchorInfo mAnchorInfo;
    private final Runnable mCheckForGapsRunnable;
    private int mFullSizeSpec;
    private int mGapStrategy;
    private boolean mLaidOutInvalidFullSpan;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private final LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup;
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private int[] mPrefetchDistances;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled;
    private int mSpanCount;
    Span[] mSpans;
    private final Rect mTmpRect;

    public StaggeredGridLayoutManager(int v, int v1) {
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.mLazySpanLookup = new LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo(this);
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = () -> {
            int v1;
            int v;
            if(StaggeredGridLayoutManager.this.getChildCount() != 0 && StaggeredGridLayoutManager.this.mGapStrategy != 0 && StaggeredGridLayoutManager.this.isAttachedToWindow()) {
                if(StaggeredGridLayoutManager.this.mShouldReverseLayout) {
                    v = StaggeredGridLayoutManager.this.getLastChildPosition();
                    v1 = StaggeredGridLayoutManager.this.getFirstChildPosition();
                }
                else {
                    v = StaggeredGridLayoutManager.this.getFirstChildPosition();
                    v1 = StaggeredGridLayoutManager.this.getLastChildPosition();
                }
                if(v == 0 && StaggeredGridLayoutManager.this.hasGapsToFix() != null) {
                    StaggeredGridLayoutManager.this.mLazySpanLookup.clear();
                    StaggeredGridLayoutManager.this.requestSimpleAnimationsInNextLayout();
                    StaggeredGridLayoutManager.this.requestLayout();
                    return true;
                }
                if(!StaggeredGridLayoutManager.this.mLaidOutInvalidFullSpan) {
                    return false;
                }
                int v2 = StaggeredGridLayoutManager.this.mShouldReverseLayout ? -1 : 1;
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = StaggeredGridLayoutManager.this.mLazySpanLookup.getFirstFullSpanItemInRange(v, v1 + 1, v2, true);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 == null) {
                    StaggeredGridLayoutManager.this.mLaidOutInvalidFullSpan = false;
                    StaggeredGridLayoutManager.this.mLazySpanLookup.forceInvalidateAfter(v1 + 1);
                    return false;
                }
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 = StaggeredGridLayoutManager.this.mLazySpanLookup.getFirstFullSpanItemInRange(v, staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition, -v2, true);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 == null) {
                    StaggeredGridLayoutManager.this.mLazySpanLookup.forceInvalidateAfter(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition);
                }
                else {
                    StaggeredGridLayoutManager.this.mLazySpanLookup.forceInvalidateAfter(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mPosition + 1);
                }
                StaggeredGridLayoutManager.this.requestSimpleAnimationsInNextLayout();
                StaggeredGridLayoutManager.this.requestLayout();
                return true;
            }
            return false;
        };
        this.mOrientation = v1;
        this.setSpanCount(v);
        this.mLayoutState = new LayoutState();
        this.createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.mLazySpanLookup = new LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo(this);
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = () -> {
            int v1;
            int v;
            if(StaggeredGridLayoutManager.this.getChildCount() != 0 && StaggeredGridLayoutManager.this.mGapStrategy != 0 && StaggeredGridLayoutManager.this.isAttachedToWindow()) {
                if(StaggeredGridLayoutManager.this.mShouldReverseLayout) {
                    v = StaggeredGridLayoutManager.this.getLastChildPosition();
                    v1 = StaggeredGridLayoutManager.this.getFirstChildPosition();
                }
                else {
                    v = StaggeredGridLayoutManager.this.getFirstChildPosition();
                    v1 = StaggeredGridLayoutManager.this.getLastChildPosition();
                }
                if(v == 0 && StaggeredGridLayoutManager.this.hasGapsToFix() != null) {
                    StaggeredGridLayoutManager.this.mLazySpanLookup.clear();
                    StaggeredGridLayoutManager.this.requestSimpleAnimationsInNextLayout();
                    StaggeredGridLayoutManager.this.requestLayout();
                    return true;
                }
                if(!StaggeredGridLayoutManager.this.mLaidOutInvalidFullSpan) {
                    return false;
                }
                int v2 = StaggeredGridLayoutManager.this.mShouldReverseLayout ? -1 : 1;
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = StaggeredGridLayoutManager.this.mLazySpanLookup.getFirstFullSpanItemInRange(v, v1 + 1, v2, true);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 == null) {
                    StaggeredGridLayoutManager.this.mLaidOutInvalidFullSpan = false;
                    StaggeredGridLayoutManager.this.mLazySpanLookup.forceInvalidateAfter(v1 + 1);
                    return false;
                }
                FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 = StaggeredGridLayoutManager.this.mLazySpanLookup.getFirstFullSpanItemInRange(v, staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition, -v2, true);
                if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 == null) {
                    StaggeredGridLayoutManager.this.mLazySpanLookup.forceInvalidateAfter(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition);
                }
                else {
                    StaggeredGridLayoutManager.this.mLazySpanLookup.forceInvalidateAfter(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mPosition + 1);
                }
                StaggeredGridLayoutManager.this.requestSimpleAnimationsInNextLayout();
                StaggeredGridLayoutManager.this.requestLayout();
                return true;
            }
            return false;
        };
        Properties recyclerView$LayoutManager$Properties0 = StaggeredGridLayoutManager.getProperties(context0, attributeSet0, v, v1);
        this.setOrientation(recyclerView$LayoutManager$Properties0.orientation);
        this.setSpanCount(recyclerView$LayoutManager$Properties0.spanCount);
        this.setReverseLayout(recyclerView$LayoutManager$Properties0.reverseLayout);
        this.mLayoutState = new LayoutState();
        this.createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view0) {
        for(int v = this.mSpanCount - 1; v >= 0; --v) {
            this.mSpans[v].appendToSpan(view0);
        }
    }

    private void applyPendingSavedState(AnchorInfo staggeredGridLayoutManager$AnchorInfo0) {
        if(this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if(this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for(int v = 0; v < this.mSpanCount; ++v) {
                    this.mSpans[v].clear();
                    int v1 = this.mPendingSavedState.mSpanOffsets[v];
                    if(v1 != 0x80000000) {
                        v1 += (this.mPendingSavedState.mAnchorLayoutFromEnd ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding());
                    }
                    this.mSpans[v].setLine(v1);
                }
            }
            else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        this.setReverseLayout(this.mPendingSavedState.mReverseLayout);
        this.resolveShouldLayoutReverse();
        if(this.mPendingSavedState.mAnchorPosition == -1) {
            staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        else {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        }
        if(this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }

    boolean areAllEndsEqual() {
        int v = this.mSpans[0].getEndLine(0x80000000);
        for(int v1 = 1; v1 < this.mSpanCount; ++v1) {
            if(this.mSpans[v1].getEndLine(0x80000000) != v) {
                return false;
            }
        }
        return true;
    }

    boolean areAllStartsEqual() {
        int v = this.mSpans[0].getStartLine(0x80000000);
        for(int v1 = 1; v1 < this.mSpanCount; ++v1) {
            if(this.mSpans[v1].getStartLine(0x80000000) != v) {
                return false;
            }
        }
        return true;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void assertNotInLayoutOrScroll(String s) {
        if(this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(s);
        }
    }

    private void attachViewToSpans(View view0, LayoutParams staggeredGridLayoutManager$LayoutParams0, LayoutState layoutState0) {
        if(layoutState0.mLayoutDirection == 1) {
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                this.appendViewToAllSpans(view0);
                return;
            }
            staggeredGridLayoutManager$LayoutParams0.mSpan.appendToSpan(view0);
            return;
        }
        if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
            this.prependViewToAllSpans(view0);
            return;
        }
        staggeredGridLayoutManager$LayoutParams0.mSpan.prependToSpan(view0);
    }

    private int calculateScrollDirectionForPosition(int v) {
        if(this.getChildCount() == 0) {
            return this.mShouldReverseLayout ? 1 : -1;
        }
        return v < this.getFirstChildPosition() == this.mShouldReverseLayout ? 1 : -1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    // 检测为 Lambda 实现
    boolean checkForGaps() [...]

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean checkLayoutParams(androidx.recyclerview.widget.RecyclerView.LayoutParams recyclerView$LayoutParams0) {
        return recyclerView$LayoutParams0 instanceof LayoutParams;
    }

    private boolean checkSpanForGap(Span staggeredGridLayoutManager$Span0) {
        if(this.mShouldReverseLayout) {
            return staggeredGridLayoutManager$Span0.getEndLine() >= this.mPrimaryOrientation.getEndAfterPadding() ? false : !staggeredGridLayoutManager$Span0.getLayoutParams(((View)staggeredGridLayoutManager$Span0.mViews.get(staggeredGridLayoutManager$Span0.mViews.size() - 1))).mFullSpan;
        }
        return staggeredGridLayoutManager$Span0.getStartLine() <= this.mPrimaryOrientation.getStartAfterPadding() ? false : !staggeredGridLayoutManager$Span0.getLayoutParams(((View)staggeredGridLayoutManager$Span0.mViews.get(0))).mFullSpan;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void collectAdjacentPrefetchPositions(int v, int v1, State recyclerView$State0, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        int v6;
        int v5;
        if(this.mOrientation != 0) {
            v = v1;
        }
        if(this.getChildCount() != 0 && v != 0) {
            this.prepareLayoutStateForDelta(v, recyclerView$State0);
            if(this.mPrefetchDistances == null || this.mPrefetchDistances.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int v4 = 0;
            for(int v3 = 0; v3 < this.mSpanCount; ++v3) {
                if(this.mLayoutState.mItemDirection == -1) {
                    v5 = this.mLayoutState.mStartLine;
                    v6 = this.mSpans[v3].getStartLine(this.mLayoutState.mStartLine);
                }
                else {
                    v5 = this.mSpans[v3].getEndLine(this.mLayoutState.mEndLine);
                    v6 = this.mLayoutState.mEndLine;
                }
                int v7 = v5 - v6;
                if(v7 >= 0) {
                    this.mPrefetchDistances[v4] = v7;
                    ++v4;
                }
            }
            Arrays.sort(this.mPrefetchDistances, 0, v4);
            for(int v2 = 0; v2 < v4 && this.mLayoutState.hasMore(recyclerView$State0); ++v2) {
                recyclerView$LayoutManager$LayoutPrefetchRegistry0.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[v2]);
                this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            }
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
        return this.getChildCount() == 0 ? 0 : ScrollbarHelper.computeScrollExtent(recyclerView$State0, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), this.findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(State recyclerView$State0) {
        return this.getChildCount() == 0 ? 0 : ScrollbarHelper.computeScrollOffset(recyclerView$State0, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), this.findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(State recyclerView$State0) {
        return this.getChildCount() == 0 ? 0 : ScrollbarHelper.computeScrollRange(recyclerView$State0, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), this.findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int v) {
        int v1 = this.calculateScrollDirectionForPosition(v);
        PointF pointF0 = new PointF();
        if(v1 == 0) {
            return null;
        }
        if(this.mOrientation == 0) {
            pointF0.x = (float)v1;
            pointF0.y = 0.0f;
            return pointF0;
        }
        pointF0.x = 0.0f;
        pointF0.y = (float)v1;
        return pointF0;
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

    private int convertFocusDirectionToLayoutDirection(int v) {
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

    private FullSpanItem createFullSpanItemFromEnd(int v) {
        FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = new FullSpanItem();
        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapPerSpan = new int[this.mSpanCount];
        for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
            int[] arr_v = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapPerSpan;
            arr_v[v1] = v - this.mSpans[v1].getEndLine(v);
        }
        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0;
    }

    private FullSpanItem createFullSpanItemFromStart(int v) {
        FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = new FullSpanItem();
        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapPerSpan = new int[this.mSpanCount];
        for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
            int[] arr_v = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapPerSpan;
            arr_v[v1] = this.mSpans[v1].getStartLine(v) - v;
        }
        return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    private int fill(Recycler recyclerView$Recycler0, LayoutState layoutState0, State recyclerView$State0) {
        int v9;
        int v7;
        int v6;
        int v5;
        int v4;
        Span staggeredGridLayoutManager$Span0;
        int v;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if(!this.mLayoutState.mInfinite) {
            v = layoutState0.mLayoutDirection == 1 ? layoutState0.mEndLine + layoutState0.mAvailable : layoutState0.mStartLine - layoutState0.mAvailable;
        }
        else if(layoutState0.mLayoutDirection == 1) {
            v = 0x7FFFFFFF;
        }
        else {
            v = 0x80000000;
        }
        this.updateAllRemainingSpans(layoutState0.mLayoutDirection, v);
        int v1 = this.mShouldReverseLayout ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
        boolean z;
        for(z = false; layoutState0.hasMore(recyclerView$State0) && (this.mLayoutState.mInfinite || !this.mRemainingSpans.isEmpty()); z = true) {
            View view0 = layoutState0.next(recyclerView$Recycler0);
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            int v2 = staggeredGridLayoutManager$LayoutParams0.getViewLayoutPosition();
            int v3 = this.mLazySpanLookup.getSpan(v2);
            boolean z1 = v3 == -1;
            if(z1) {
                staggeredGridLayoutManager$Span0 = staggeredGridLayoutManager$LayoutParams0.mFullSpan ? this.mSpans[0] : this.getNextSpan(layoutState0);
                this.mLazySpanLookup.setSpan(v2, staggeredGridLayoutManager$Span0);
            }
            else {
                staggeredGridLayoutManager$Span0 = this.mSpans[v3];
            }
            staggeredGridLayoutManager$LayoutParams0.mSpan = staggeredGridLayoutManager$Span0;
            if(layoutState0.mLayoutDirection == 1) {
                this.addView(view0);
            }
            else {
                this.addView(view0, 0);
            }
            this.measureChildWithDecorationsAndMargin(view0, staggeredGridLayoutManager$LayoutParams0, false);
            if(layoutState0.mLayoutDirection == 1) {
                v4 = staggeredGridLayoutManager$LayoutParams0.mFullSpan ? this.getMaxEnd(v1) : staggeredGridLayoutManager$Span0.getEndLine(v1);
                v5 = this.mPrimaryOrientation.getDecoratedMeasurement(view0) + v4;
                if(z1 && staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                    FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0 = this.createFullSpanItemFromEnd(v4);
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mGapDir = -1;
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0.mPosition = v2;
                    this.mLazySpanLookup.addFullSpanItem(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem0);
                }
            }
            else {
                v5 = staggeredGridLayoutManager$LayoutParams0.mFullSpan ? this.getMinStart(v1) : staggeredGridLayoutManager$Span0.getStartLine(v1);
                v4 = v5 - this.mPrimaryOrientation.getDecoratedMeasurement(view0);
                if(z1 && staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                    FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1 = this.createFullSpanItemFromStart(v5);
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mGapDir = 1;
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1.mPosition = v2;
                    this.mLazySpanLookup.addFullSpanItem(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem1);
                }
            }
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan && layoutState0.mItemDirection == -1) {
                if(z1) {
                    this.mLaidOutInvalidFullSpan = true;
                }
                else if(!(layoutState0.mLayoutDirection == 1 ? this.areAllEndsEqual() : this.areAllStartsEqual()) != 0) {
                    FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = this.mLazySpanLookup.getFullSpanItem(v2);
                    if(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 != null) {
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.mHasUnwantedGapAfter = true;
                    }
                    this.mLaidOutInvalidFullSpan = true;
                }
            }
            this.attachViewToSpans(view0, staggeredGridLayoutManager$LayoutParams0, layoutState0);
            if(!this.isLayoutRTL() || this.mOrientation != 1) {
                v7 = staggeredGridLayoutManager$LayoutParams0.mFullSpan ? this.mSecondaryOrientation.getStartAfterPadding() : staggeredGridLayoutManager$Span0.mIndex * this.mSizePerSpan + this.mSecondaryOrientation.getStartAfterPadding();
                v6 = this.mSecondaryOrientation.getDecoratedMeasurement(view0) + v7;
            }
            else {
                v6 = staggeredGridLayoutManager$LayoutParams0.mFullSpan ? this.mSecondaryOrientation.getEndAfterPadding() : this.mSecondaryOrientation.getEndAfterPadding() - (this.mSpanCount - 1 - staggeredGridLayoutManager$Span0.mIndex) * this.mSizePerSpan;
                v7 = v6 - this.mSecondaryOrientation.getDecoratedMeasurement(view0);
            }
            if(this.mOrientation == 1) {
                this.layoutDecoratedWithMargins(view0, v7, v4, v6, v5);
            }
            else {
                this.layoutDecoratedWithMargins(view0, v4, v7, v5, v6);
            }
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                this.updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, v);
            }
            else {
                this.updateRemainingSpans(staggeredGridLayoutManager$Span0, this.mLayoutState.mLayoutDirection, v);
            }
            this.recycle(recyclerView$Recycler0, this.mLayoutState);
            if(this.mLayoutState.mStopInFocusable && view0.hasFocusable()) {
                if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                    this.mRemainingSpans.clear();
                }
                else {
                    this.mRemainingSpans.set(staggeredGridLayoutManager$Span0.mIndex, false);
                }
            }
        }
        if(!z) {
            this.recycle(recyclerView$Recycler0, this.mLayoutState);
        }
        if(this.mLayoutState.mLayoutDirection == -1) {
            int v8 = this.getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
            v9 = this.mPrimaryOrientation.getStartAfterPadding() - v8;
            return v9 <= 0 ? 0 : Math.min(layoutState0.mAvailable, v9);
        }
        v9 = this.getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        return v9 <= 0 ? 0 : Math.min(layoutState0.mAvailable, v9);
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] arr_v) {
        if(arr_v == null) {
            arr_v = new int[this.mSpanCount];
        }
        else if(arr_v.length >= this.mSpanCount) {
        }
        else {
            throw new IllegalArgumentException("Provided int[]\'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + arr_v.length);
        }
        for(int v = 0; v < this.mSpanCount; ++v) {
            arr_v[v] = this.mSpans[v].findFirstCompletelyVisibleItemPosition();
        }
        return arr_v;
    }

    private int findFirstReferenceChildPosition(int v) {
        int v1 = this.getChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = this.getPosition(this.getChildAt(v2));
            if(v3 >= 0 && v3 < v) {
                return v3;
            }
        }
        return 0;
    }

    View findFirstVisibleItemClosestToEnd(boolean z) {
        int v = this.mPrimaryOrientation.getStartAfterPadding();
        int v1 = this.mPrimaryOrientation.getEndAfterPadding();
        int v2 = this.getChildCount() - 1;
        View view0 = null;
        while(v2 >= 0) {
            View view1 = this.getChildAt(v2);
            int v3 = this.mPrimaryOrientation.getDecoratedStart(view1);
            int v4 = this.mPrimaryOrientation.getDecoratedEnd(view1);
            if(v4 > v && v3 < v1) {
                if(v4 <= v1 || !z) {
                    return view1;
                }
                if(view0 == null) {
                    view0 = view1;
                }
            }
            --v2;
        }
        return view0;
    }

    View findFirstVisibleItemClosestToStart(boolean z) {
        int v = this.mPrimaryOrientation.getStartAfterPadding();
        int v1 = this.mPrimaryOrientation.getEndAfterPadding();
        int v2 = this.getChildCount();
        View view0 = null;
        for(int v3 = 0; v3 < v2; ++v3) {
            View view1 = this.getChildAt(v3);
            int v4 = this.mPrimaryOrientation.getDecoratedStart(view1);
            if(this.mPrimaryOrientation.getDecoratedEnd(view1) > v && v4 < v1) {
                if(v4 >= v || !z) {
                    return view1;
                }
                if(view0 == null) {
                    view0 = view1;
                }
            }
        }
        return view0;
    }

    int findFirstVisibleItemPositionInt() {
        View view0 = this.mShouldReverseLayout ? this.findFirstVisibleItemClosestToEnd(true) : this.findFirstVisibleItemClosestToStart(true);
        return view0 == null ? -1 : this.getPosition(view0);
    }

    public int[] findFirstVisibleItemPositions(int[] arr_v) {
        if(arr_v == null) {
            arr_v = new int[this.mSpanCount];
        }
        else if(arr_v.length >= this.mSpanCount) {
        }
        else {
            throw new IllegalArgumentException("Provided int[]\'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + arr_v.length);
        }
        for(int v = 0; v < this.mSpanCount; ++v) {
            arr_v[v] = this.mSpans[v].findFirstVisibleItemPosition();
        }
        return arr_v;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] arr_v) {
        if(arr_v == null) {
            arr_v = new int[this.mSpanCount];
        }
        else if(arr_v.length >= this.mSpanCount) {
        }
        else {
            throw new IllegalArgumentException("Provided int[]\'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + arr_v.length);
        }
        for(int v = 0; v < this.mSpanCount; ++v) {
            arr_v[v] = this.mSpans[v].findLastCompletelyVisibleItemPosition();
        }
        return arr_v;
    }

    private int findLastReferenceChildPosition(int v) {
        for(int v1 = this.getChildCount() - 1; v1 >= 0; --v1) {
            int v2 = this.getPosition(this.getChildAt(v1));
            if(v2 >= 0 && v2 < v) {
                return v2;
            }
        }
        return 0;
    }

    public int[] findLastVisibleItemPositions(int[] arr_v) {
        if(arr_v == null) {
            arr_v = new int[this.mSpanCount];
        }
        else if(arr_v.length >= this.mSpanCount) {
        }
        else {
            throw new IllegalArgumentException("Provided int[]\'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + arr_v.length);
        }
        for(int v = 0; v < this.mSpanCount; ++v) {
            arr_v[v] = this.mSpans[v].findLastVisibleItemPosition();
        }
        return arr_v;
    }

    private void fixEndGap(Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z) {
        int v = this.getMaxEnd(0x80000000);
        if(v != 0x80000000) {
            int v1 = this.mPrimaryOrientation.getEndAfterPadding() - v;
            if(v1 > 0) {
                int v2 = v1 - -this.scrollBy(-v1, recyclerView$Recycler0, recyclerView$State0);
                if(z && v2 > 0) {
                    this.mPrimaryOrientation.offsetChildren(v2);
                }
            }
        }
    }

    private void fixStartGap(Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z) {
        int v = this.getMinStart(0x7FFFFFFF);
        if(v != 0x7FFFFFFF) {
            int v1 = v - this.mPrimaryOrientation.getStartAfterPadding();
            if(v1 > 0) {
                int v2 = v1 - this.scrollBy(v1, recyclerView$Recycler0, recyclerView$State0);
                if(z && v2 > 0) {
                    this.mPrimaryOrientation.offsetChildren(-v2);
                }
            }
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
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
        return this.mOrientation == 1 ? Math.min(this.mSpanCount, recyclerView$State0.getItemCount()) : -1;
    }

    int getFirstChildPosition() {
        return this.getChildCount() == 0 ? 0 : this.getPosition(this.getChildAt(0));
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    int getLastChildPosition() {
        int v = this.getChildCount();
        return v == 0 ? 0 : this.getPosition(this.getChildAt(v - 1));
    }

    private int getMaxEnd(int v) {
        int v1 = this.mSpans[0].getEndLine(v);
        for(int v2 = 1; v2 < this.mSpanCount; ++v2) {
            int v3 = this.mSpans[v2].getEndLine(v);
            if(v3 > v1) {
                v1 = v3;
            }
        }
        return v1;
    }

    private int getMaxStart(int v) {
        int v1 = this.mSpans[0].getStartLine(v);
        for(int v2 = 1; v2 < this.mSpanCount; ++v2) {
            int v3 = this.mSpans[v2].getStartLine(v);
            if(v3 > v1) {
                v1 = v3;
            }
        }
        return v1;
    }

    private int getMinEnd(int v) {
        int v1 = this.mSpans[0].getEndLine(v);
        for(int v2 = 1; v2 < this.mSpanCount; ++v2) {
            int v3 = this.mSpans[v2].getEndLine(v);
            if(v3 < v1) {
                v1 = v3;
            }
        }
        return v1;
    }

    private int getMinStart(int v) {
        int v1 = this.mSpans[0].getStartLine(v);
        for(int v2 = 1; v2 < this.mSpanCount; ++v2) {
            int v3 = this.mSpans[v2].getStartLine(v);
            if(v3 < v1) {
                v1 = v3;
            }
        }
        return v1;
    }

    private Span getNextSpan(LayoutState layoutState0) {
        int v2;
        int v1;
        int v;
        if(this.preferLastSpan(layoutState0.mLayoutDirection)) {
            v = this.mSpanCount - 1;
            v1 = -1;
            v2 = -1;
        }
        else {
            v1 = this.mSpanCount;
            v = 0;
            v2 = 1;
        }
        Span staggeredGridLayoutManager$Span0 = null;
        if(layoutState0.mLayoutDirection == 1) {
            int v3 = this.mPrimaryOrientation.getStartAfterPadding();
            int v4 = 0x7FFFFFFF;
            while(v != v1) {
                Span staggeredGridLayoutManager$Span1 = this.mSpans[v];
                int v5 = staggeredGridLayoutManager$Span1.getEndLine(v3);
                if(v5 < v4) {
                    staggeredGridLayoutManager$Span0 = staggeredGridLayoutManager$Span1;
                    v4 = v5;
                }
                v += v2;
            }
            return staggeredGridLayoutManager$Span0;
        }
        int v6 = this.mPrimaryOrientation.getEndAfterPadding();
        int v7 = 0x80000000;
        while(v != v1) {
            Span staggeredGridLayoutManager$Span2 = this.mSpans[v];
            int v8 = staggeredGridLayoutManager$Span2.getStartLine(v6);
            if(v8 > v7) {
                staggeredGridLayoutManager$Span0 = staggeredGridLayoutManager$Span2;
                v7 = v8;
            }
            v += v2;
        }
        return staggeredGridLayoutManager$Span0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int getRowCountForAccessibility(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.mOrientation == 0 ? Math.min(this.mSpanCount, recyclerView$State0.getItemCount()) : -1;
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    private void handleUpdate(int v, int v1, int v2) {
        int v5;
        int v4;
        int v3 = this.mShouldReverseLayout ? this.getLastChildPosition() : this.getFirstChildPosition();
        if(v2 != 8) {
            v4 = v + v1;
            v5 = v;
        }
        else if(v < v1) {
            v4 = v1 + 1;
            v5 = v;
        }
        else {
            v4 = v + 1;
            v5 = v1;
        }
        this.mLazySpanLookup.invalidateAfter(v5);
        if(v2 == 1) {
            this.mLazySpanLookup.offsetForAddition(v, v1);
        }
        else {
            switch(v2) {
                case 2: {
                    this.mLazySpanLookup.offsetForRemoval(v, v1);
                    break;
                }
                case 8: {
                    this.mLazySpanLookup.offsetForRemoval(v, 1);
                    this.mLazySpanLookup.offsetForAddition(v1, 1);
                }
            }
        }
        if(v4 > v3 && v5 <= (this.mShouldReverseLayout ? this.getFirstChildPosition() : this.getLastChildPosition())) {
            this.requestLayout();
        }
    }

    View hasGapsToFix() {
        int v = this.getChildCount();
        int v1 = v - 1;
        BitSet bitSet0 = new BitSet(this.mSpanCount);
        bitSet0.set(0, this.mSpanCount, true);
        int v2 = -1;
        int v3 = this.mOrientation != 1 || !this.isLayoutRTL() ? -1 : 1;
        if(this.mShouldReverseLayout) {
            v = -1;
        }
        else {
            v1 = 0;
        }
        if(v1 < v) {
            v2 = 1;
        }
        while(v1 != v) {
            View view0 = this.getChildAt(v1);
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(bitSet0.get(staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex)) {
                if(this.checkSpanForGap(staggeredGridLayoutManager$LayoutParams0.mSpan)) {
                    return view0;
                }
                bitSet0.clear(staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex);
            }
            if(!staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                int v4 = v1 + v2;
                if(v4 != v) {
                    View view1 = this.getChildAt(v4);
                    if(this.mShouldReverseLayout) {
                        int v5 = this.mPrimaryOrientation.getDecoratedEnd(view0);
                        int v6 = this.mPrimaryOrientation.getDecoratedEnd(view1);
                        if(v5 < v6) {
                            return view0;
                        }
                        if(v5 == v6) {
                            goto label_32;
                        }
                    }
                    else {
                        int v7 = this.mPrimaryOrientation.getDecoratedStart(view0);
                        int v8 = this.mPrimaryOrientation.getDecoratedStart(view1);
                        if(v7 > v8) {
                            return view0;
                        }
                        if(v7 == v8) {
                        label_32:
                            LayoutParams staggeredGridLayoutManager$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                            if((staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex - staggeredGridLayoutManager$LayoutParams1.mSpan.mIndex >= 0 ? 0 : 1) == (v3 >= 0 ? 0 : 1)) {
                                goto label_35;
                            }
                            return view0;
                        }
                    }
                }
            }
        label_35:
            v1 += v2;
        }
        return null;
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        this.requestLayout();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    boolean isLayoutRTL() {
        return this.getLayoutDirection() == 1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean isLayoutReversed() {
        return this.mReverseLayout;
    }

    private void measureChildWithDecorationsAndMargin(View view0, int v, int v1, boolean z) {
        this.calculateItemDecorationsForChild(view0, this.mTmpRect);
        LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v2 = this.updateSpecWithExtra(v, staggeredGridLayoutManager$LayoutParams0.leftMargin + this.mTmpRect.left, staggeredGridLayoutManager$LayoutParams0.rightMargin + this.mTmpRect.right);
        int v3 = this.updateSpecWithExtra(v1, staggeredGridLayoutManager$LayoutParams0.topMargin + this.mTmpRect.top, staggeredGridLayoutManager$LayoutParams0.bottomMargin + this.mTmpRect.bottom);
        if((z ? this.shouldReMeasureChild(view0, v2, v3, staggeredGridLayoutManager$LayoutParams0) : this.shouldMeasureChild(view0, v2, v3, staggeredGridLayoutManager$LayoutParams0))) {
            view0.measure(v2, v3);
        }
    }

    private void measureChildWithDecorationsAndMargin(View view0, LayoutParams staggeredGridLayoutManager$LayoutParams0, boolean z) {
        if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
            if(this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view0, this.mFullSizeSpec, StaggeredGridLayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom(), staggeredGridLayoutManager$LayoutParams0.height, true), z);
                return;
            }
            this.measureChildWithDecorationsAndMargin(view0, StaggeredGridLayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight(), staggeredGridLayoutManager$LayoutParams0.width, true), this.mFullSizeSpec, z);
            return;
        }
        if(this.mOrientation == 1) {
            this.measureChildWithDecorationsAndMargin(view0, StaggeredGridLayoutManager.getChildMeasureSpec(this.mSizePerSpan, this.getWidthMode(), 0, staggeredGridLayoutManager$LayoutParams0.width, false), StaggeredGridLayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom(), staggeredGridLayoutManager$LayoutParams0.height, true), z);
            return;
        }
        this.measureChildWithDecorationsAndMargin(view0, StaggeredGridLayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight(), staggeredGridLayoutManager$LayoutParams0.width, true), StaggeredGridLayoutManager.getChildMeasureSpec(this.mSizePerSpan, this.getHeightMode(), 0, staggeredGridLayoutManager$LayoutParams0.height, false), z);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void offsetChildrenHorizontal(int v) {
        super.offsetChildrenHorizontal(v);
        for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
            this.mSpans[v1].onOffset(v);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void offsetChildrenVertical(int v) {
        super.offsetChildrenVertical(v);
        for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
            this.mSpans[v1].onOffset(v);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onAdapterChanged(Adapter recyclerView$Adapter0, Adapter recyclerView$Adapter1) {
        this.mLazySpanLookup.clear();
        for(int v = 0; v < this.mSpanCount; ++v) {
            this.mSpans[v].clear();
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView0, Recycler recyclerView$Recycler0) {
        super.onDetachedFromWindow(recyclerView0, recyclerView$Recycler0);
        this.removeCallbacks(this.mCheckForGapsRunnable);
        for(int v = 0; v < this.mSpanCount; ++v) {
            this.mSpans[v].clear();
        }
        recyclerView0.requestLayout();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public View onFocusSearchFailed(View view0, int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(this.getChildCount() == 0) {
            return null;
        }
        View view1 = this.findContainingItemView(view0);
        if(view1 == null) {
            return null;
        }
        this.resolveShouldLayoutReverse();
        int v1 = this.convertFocusDirectionToLayoutDirection(v);
        if(v1 == 0x80000000) {
            return null;
        }
        LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view1.getLayoutParams();
        boolean z = staggeredGridLayoutManager$LayoutParams0.mFullSpan;
        Span staggeredGridLayoutManager$Span0 = staggeredGridLayoutManager$LayoutParams0.mSpan;
        int v2 = v1 == 1 ? this.getLastChildPosition() : this.getFirstChildPosition();
        this.updateLayoutState(v2, recyclerView$State0);
        this.setLayoutStateDirection(v1);
        this.mLayoutState.mCurrentPosition = this.mLayoutState.mItemDirection + v2;
        this.mLayoutState.mAvailable = (int)(((float)this.mPrimaryOrientation.getTotalSpace()) * 0.333333f);
        this.mLayoutState.mStopInFocusable = true;
        this.mLayoutState.mRecycle = false;
        this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if(!z) {
            View view2 = staggeredGridLayoutManager$Span0.getFocusableViewAfter(v2, v1);
            if(view2 != null && view2 != view1) {
                return view2;
            }
        }
        if(this.preferLastSpan(v1)) {
            for(int v4 = this.mSpanCount - 1; v4 >= 0; --v4) {
                View view3 = this.mSpans[v4].getFocusableViewAfter(v2, v1);
                if(view3 != null && view3 != view1) {
                    return view3;
                }
            }
        }
        else {
            for(int v5 = 0; v5 < this.mSpanCount; ++v5) {
                View view4 = this.mSpans[v5].getFocusableViewAfter(v2, v1);
                if(view4 != null && view4 != view1) {
                    return view4;
                }
            }
        }
        boolean z1 = !this.mReverseLayout == (v1 == -1 ? 1 : 0);
        if(!z) {
            View view5 = this.findViewByPosition((z1 ? staggeredGridLayoutManager$Span0.findFirstPartiallyVisibleItemPosition() : staggeredGridLayoutManager$Span0.findLastPartiallyVisibleItemPosition()));
            if(view5 != null && view5 != view1) {
                return view5;
            }
        }
        if(this.preferLastSpan(v1)) {
            for(int v6 = this.mSpanCount - 1; v6 >= 0; --v6) {
                if(v6 != staggeredGridLayoutManager$Span0.mIndex) {
                    View view6 = this.findViewByPosition((z1 ? this.mSpans[v6].findFirstPartiallyVisibleItemPosition() : this.mSpans[v6].findLastPartiallyVisibleItemPosition()));
                    if(view6 != null && view6 != view1) {
                        return view6;
                    }
                }
            }
            return null;
        }
        for(int v3 = 0; v3 < this.mSpanCount; ++v3) {
            View view7 = this.findViewByPosition((z1 ? this.mSpans[v3].findFirstPartiallyVisibleItemPosition() : this.mSpans[v3].findLastPartiallyVisibleItemPosition()));
            if(view7 != null && view7 != view1) {
                return view7;
            }
        }
        return null;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        if(this.getChildCount() > 0) {
            View view0 = this.findFirstVisibleItemClosestToStart(false);
            View view1 = this.findFirstVisibleItemClosestToEnd(false);
            if(view0 != null && view1 != null) {
                int v = this.getPosition(view0);
                int v1 = this.getPosition(view1);
                if(v < v1) {
                    accessibilityEvent0.setFromIndex(v);
                    accessibilityEvent0.setToIndex(v1);
                    return;
                }
                accessibilityEvent0.setFromIndex(v1);
                accessibilityEvent0.setToIndex(v);
            }
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityNodeInfo(Recycler recyclerView$Recycler0, State recyclerView$State0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        super.onInitializeAccessibilityNodeInfo(recyclerView$Recycler0, recyclerView$State0, accessibilityNodeInfoCompat0);
        accessibilityNodeInfoCompat0.setClassName("androidx.recyclerview.widget.StaggeredGridLayoutManager");
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(Recycler recyclerView$Recycler0, State recyclerView$State0, View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view0, accessibilityNodeInfoCompat0);
            return;
        }
        if(this.mOrientation == 0) {
            accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(((LayoutParams)viewGroup$LayoutParams0).getSpanIndex(), (((LayoutParams)viewGroup$LayoutParams0).mFullSpan ? this.mSpanCount : 1), -1, -1, false, false));
            return;
        }
        accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(-1, -1, ((LayoutParams)viewGroup$LayoutParams0).getSpanIndex(), (((LayoutParams)viewGroup$LayoutParams0).mFullSpan ? this.mSpanCount : 1), false, false));
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsAdded(RecyclerView recyclerView0, int v, int v1) {
        this.handleUpdate(v, v1, 1);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsChanged(RecyclerView recyclerView0) {
        this.mLazySpanLookup.clear();
        this.requestLayout();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsMoved(RecyclerView recyclerView0, int v, int v1, int v2) {
        this.handleUpdate(v, v1, 8);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView0, int v, int v1) {
        this.handleUpdate(v, v1, 2);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView0, int v, int v1, Object object0) {
        this.handleUpdate(v, v1, 4);
    }

    private void onLayoutChildren(Recycler recyclerView$Recycler0, State recyclerView$State0, boolean z) {
        boolean z1 = true;
        AnchorInfo staggeredGridLayoutManager$AnchorInfo0 = this.mAnchorInfo;
        if((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && recyclerView$State0.getItemCount() == 0) {
            this.removeAndRecycleAllViews(recyclerView$Recycler0);
            staggeredGridLayoutManager$AnchorInfo0.reset();
            return;
        }
        boolean z2 = !staggeredGridLayoutManager$AnchorInfo0.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null;
        if(z2) {
            staggeredGridLayoutManager$AnchorInfo0.reset();
            if(this.mPendingSavedState == null) {
                this.resolveShouldLayoutReverse();
                staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd = this.mShouldReverseLayout;
            }
            else {
                this.applyPendingSavedState(staggeredGridLayoutManager$AnchorInfo0);
            }
            this.updateAnchorInfoForLayout(recyclerView$State0, staggeredGridLayoutManager$AnchorInfo0);
            staggeredGridLayoutManager$AnchorInfo0.mValid = true;
        }
        if(this.mPendingSavedState == null && this.mPendingScrollPosition == -1 && (staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd != this.mLastLayoutFromEnd || this.isLayoutRTL() != this.mLastLayoutRTL)) {
            this.mLazySpanLookup.clear();
            staggeredGridLayoutManager$AnchorInfo0.mInvalidateOffsets = true;
        }
        if(this.getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < 1)) {
            if(staggeredGridLayoutManager$AnchorInfo0.mInvalidateOffsets) {
                for(int v = 0; v < this.mSpanCount; ++v) {
                    this.mSpans[v].clear();
                    if(staggeredGridLayoutManager$AnchorInfo0.mOffset != 0x80000000) {
                        this.mSpans[v].setLine(staggeredGridLayoutManager$AnchorInfo0.mOffset);
                    }
                }
            }
            else if(z2 || this.mAnchorInfo.mSpanReferenceLines == null) {
                for(int v2 = 0; v2 < this.mSpanCount; ++v2) {
                    this.mSpans[v2].cacheReferenceLineAndClear(this.mShouldReverseLayout, staggeredGridLayoutManager$AnchorInfo0.mOffset);
                }
                this.mAnchorInfo.saveSpanReferenceLines(this.mSpans);
            }
            else {
                for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
                    Span staggeredGridLayoutManager$Span0 = this.mSpans[v1];
                    staggeredGridLayoutManager$Span0.clear();
                    staggeredGridLayoutManager$Span0.setLine(this.mAnchorInfo.mSpanReferenceLines[v1]);
                }
            }
        }
        this.detachAndScrapAttachedViews(recyclerView$Recycler0);
        this.mLayoutState.mRecycle = false;
        this.mLaidOutInvalidFullSpan = false;
        this.updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
        this.updateLayoutState(staggeredGridLayoutManager$AnchorInfo0.mPosition, recyclerView$State0);
        if(staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd) {
            this.setLayoutStateDirection(-1);
            this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0);
            this.setLayoutStateDirection(1);
        }
        else {
            this.setLayoutStateDirection(1);
            this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0);
            this.setLayoutStateDirection(-1);
        }
        this.mLayoutState.mCurrentPosition = staggeredGridLayoutManager$AnchorInfo0.mPosition + this.mLayoutState.mItemDirection;
        this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0);
        this.repositionToWrapContentIfNecessary();
        if(this.getChildCount() > 0) {
            if(this.mShouldReverseLayout) {
                this.fixEndGap(recyclerView$Recycler0, recyclerView$State0, true);
                this.fixStartGap(recyclerView$Recycler0, recyclerView$State0, false);
            }
            else {
                this.fixStartGap(recyclerView$Recycler0, recyclerView$State0, true);
                this.fixEndGap(recyclerView$Recycler0, recyclerView$State0, false);
            }
        }
        if(!z || recyclerView$State0.isPreLayout() || this.mGapStrategy == 0 || this.getChildCount() <= 0 || !this.mLaidOutInvalidFullSpan && this.hasGapsToFix() == null) {
            z1 = false;
        }
        else {
            this.removeCallbacks(this.mCheckForGapsRunnable);
            if(!this.checkForGaps()) {
                z1 = false;
            }
        }
        if(recyclerView$State0.isPreLayout()) {
            this.mAnchorInfo.reset();
        }
        this.mLastLayoutFromEnd = staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd;
        this.mLastLayoutRTL = this.isLayoutRTL();
        if(z1) {
            this.mAnchorInfo.reset();
            this.onLayoutChildren(recyclerView$Recycler0, recyclerView$State0, false);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onLayoutChildren(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        this.onLayoutChildren(recyclerView$Recycler0, recyclerView$State0, true);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onLayoutCompleted(State recyclerView$State0) {
        super.onLayoutCompleted(recyclerView$State0);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 instanceof SavedState) {
            this.mPendingSavedState = (SavedState)parcelable0;
            if(this.mPendingScrollPosition != -1) {
                ((SavedState)parcelable0).invalidateAnchorPositionInfo();
                this.mPendingSavedState.invalidateSpanInfo();
            }
            this.requestLayout();
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public Parcelable onSaveInstanceState() {
        int v1;
        if(this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        Parcelable parcelable0 = new SavedState();
        parcelable0.mReverseLayout = this.mReverseLayout;
        parcelable0.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        parcelable0.mLastLayoutRTL = this.mLastLayoutRTL;
        if(this.mLazySpanLookup == null || this.mLazySpanLookup.mData == null) {
            parcelable0.mSpanLookupSize = 0;
        }
        else {
            parcelable0.mSpanLookup = this.mLazySpanLookup.mData;
            parcelable0.mSpanLookupSize = parcelable0.mSpanLookup.length;
            parcelable0.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
        }
        if(this.getChildCount() > 0) {
            parcelable0.mAnchorPosition = this.mLastLayoutFromEnd ? this.getLastChildPosition() : this.getFirstChildPosition();
            parcelable0.mVisibleAnchorPosition = this.findFirstVisibleItemPositionInt();
            parcelable0.mSpanOffsetsSize = this.mSpanCount;
            parcelable0.mSpanOffsets = new int[this.mSpanCount];
            for(int v = 0; v < this.mSpanCount; ++v) {
                if(this.mLastLayoutFromEnd) {
                    v1 = this.mSpans[v].getEndLine(0x80000000);
                    if(v1 != 0x80000000) {
                        v1 -= this.mPrimaryOrientation.getEndAfterPadding();
                    }
                }
                else {
                    v1 = this.mSpans[v].getStartLine(0x80000000);
                    if(v1 != 0x80000000) {
                        v1 -= this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                parcelable0.mSpanOffsets[v] = v1;
            }
            return parcelable0;
        }
        parcelable0.mAnchorPosition = -1;
        parcelable0.mVisibleAnchorPosition = -1;
        parcelable0.mSpanOffsetsSize = 0;
        return parcelable0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onScrollStateChanged(int v) {
        if(v == 0) {
            this.checkForGaps();
        }
    }

    private boolean preferLastSpan(int v) {
        return this.mOrientation == 0 ? v == -1 != this.mShouldReverseLayout : v == -1 == this.mShouldReverseLayout == this.isLayoutRTL();
    }

    void prepareLayoutStateForDelta(int v, State recyclerView$State0) {
        int v2;
        int v1;
        if(v > 0) {
            v1 = this.getLastChildPosition();
            v2 = 1;
        }
        else {
            v1 = this.getFirstChildPosition();
            v2 = -1;
        }
        this.mLayoutState.mRecycle = true;
        this.updateLayoutState(v1, recyclerView$State0);
        this.setLayoutStateDirection(v2);
        this.mLayoutState.mCurrentPosition = v1 + this.mLayoutState.mItemDirection;
        this.mLayoutState.mAvailable = Math.abs(v);
    }

    private void prependViewToAllSpans(View view0) {
        for(int v = this.mSpanCount - 1; v >= 0; --v) {
            this.mSpans[v].prependToSpan(view0);
        }
    }

    private void recycle(Recycler recyclerView$Recycler0, LayoutState layoutState0) {
        if(layoutState0.mRecycle && !layoutState0.mInfinite) {
            if(layoutState0.mAvailable == 0) {
                if(layoutState0.mLayoutDirection == -1) {
                    this.recycleFromEnd(recyclerView$Recycler0, layoutState0.mEndLine);
                    return;
                }
                this.recycleFromStart(recyclerView$Recycler0, layoutState0.mStartLine);
                return;
            }
            if(layoutState0.mLayoutDirection == -1) {
                int v = layoutState0.mStartLine - this.getMaxStart(layoutState0.mStartLine);
                this.recycleFromEnd(recyclerView$Recycler0, (v >= 0 ? layoutState0.mEndLine - Math.min(v, layoutState0.mAvailable) : layoutState0.mEndLine));
                return;
            }
            int v1 = this.getMinEnd(layoutState0.mEndLine) - layoutState0.mEndLine;
            this.recycleFromStart(recyclerView$Recycler0, (v1 >= 0 ? Math.min(v1, layoutState0.mAvailable) + layoutState0.mStartLine : layoutState0.mStartLine));
        }
    }

    private void recycleFromEnd(Recycler recyclerView$Recycler0, int v) {
        int v1 = this.getChildCount() - 1;
    alab1:
        while(v1 >= 0) {
            View view0 = this.getChildAt(v1);
            if(this.mPrimaryOrientation.getDecoratedStart(view0) < v || this.mPrimaryOrientation.getTransformedStartWithDecoration(view0) < v) {
                break;
            }
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                for(int v3 = 0; v3 < this.mSpanCount; ++v3) {
                    if(this.mSpans[v3].mViews.size() == 1) {
                        break alab1;
                    }
                }
                for(int v2 = 0; v2 < this.mSpanCount; ++v2) {
                    this.mSpans[v2].popEnd();
                }
            }
            else {
                if(staggeredGridLayoutManager$LayoutParams0.mSpan.mViews.size() == 1) {
                    break;
                }
                staggeredGridLayoutManager$LayoutParams0.mSpan.popEnd();
            }
            this.removeAndRecycleView(view0, recyclerView$Recycler0);
            --v1;
        }
    }

    private void recycleFromStart(Recycler recyclerView$Recycler0, int v) {
    alab1:
        while(this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            if(this.mPrimaryOrientation.getDecoratedEnd(view0) > v || this.mPrimaryOrientation.getTransformedEndWithDecoration(view0) > v) {
                break;
            }
            LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                for(int v2 = 0; v2 < this.mSpanCount; ++v2) {
                    if(this.mSpans[v2].mViews.size() == 1) {
                        break alab1;
                    }
                }
                for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
                    this.mSpans[v1].popStart();
                }
            }
            else {
                if(staggeredGridLayoutManager$LayoutParams0.mSpan.mViews.size() == 1) {
                    break;
                }
                staggeredGridLayoutManager$LayoutParams0.mSpan.popStart();
            }
            this.removeAndRecycleView(view0, recyclerView$Recycler0);
        }
    }

    private void repositionToWrapContentIfNecessary() {
        if(this.mSecondaryOrientation.getMode() != 0x40000000) {
            int v = this.getChildCount();
            float f = 0.0f;
            for(int v2 = 0; v2 < v; ++v2) {
                View view0 = this.getChildAt(v2);
                float f1 = (float)this.mSecondaryOrientation.getDecoratedMeasurement(view0);
                if(f1 >= f) {
                    if(((LayoutParams)view0.getLayoutParams()).isFullSpan()) {
                        f1 = f1 * 1.0f / ((float)this.mSpanCount);
                    }
                    f = Math.max(f, f1);
                }
            }
            int v3 = this.mSizePerSpan;
            int v4 = Math.round(f * ((float)this.mSpanCount));
            this.updateMeasureSpecs((this.mSecondaryOrientation.getMode() == 0x80000000 ? Math.min(v4, this.mSecondaryOrientation.getTotalSpace()) : Math.round(f * ((float)this.mSpanCount))));
            if(this.mSizePerSpan != v3) {
                for(int v1 = 0; v1 < v; ++v1) {
                    View view1 = this.getChildAt(v1);
                    LayoutParams staggeredGridLayoutManager$LayoutParams0 = (LayoutParams)view1.getLayoutParams();
                    if(!staggeredGridLayoutManager$LayoutParams0.mFullSpan) {
                        if(!this.isLayoutRTL() || this.mOrientation != 1) {
                            int v5 = staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex * this.mSizePerSpan;
                            int v6 = staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex * v3;
                            if(this.mOrientation == 1) {
                                view1.offsetLeftAndRight(v5 - v6);
                            }
                            else {
                                view1.offsetTopAndBottom(v5 - v6);
                            }
                        }
                        else {
                            view1.offsetLeftAndRight(-(this.mSpanCount - 1 - staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex) * this.mSizePerSpan - -(this.mSpanCount - 1 - staggeredGridLayoutManager$LayoutParams0.mSpan.mIndex) * v3);
                        }
                    }
                }
            }
        }
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
            this.prepareLayoutStateForDelta(v, recyclerView$State0);
            int v1 = this.fill(recyclerView$Recycler0, this.mLayoutState, recyclerView$State0);
            if(this.mLayoutState.mAvailable >= v1) {
                v = v >= 0 ? v1 : -v1;
            }
            this.mPrimaryOrientation.offsetChildren(-v);
            this.mLastLayoutFromEnd = this.mShouldReverseLayout;
            this.mLayoutState.mAvailable = 0;
            this.recycle(recyclerView$Recycler0, this.mLayoutState);
            return v;
        }
        return 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int scrollHorizontallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.scrollBy(v, recyclerView$Recycler0, recyclerView$State0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void scrollToPosition(int v) {
        if(this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != v) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = v;
        this.mPendingScrollPositionOffset = 0x80000000;
        this.requestLayout();
    }

    public void scrollToPositionWithOffset(int v, int v1) {
        SavedState staggeredGridLayoutManager$SavedState0 = this.mPendingSavedState;
        if(staggeredGridLayoutManager$SavedState0 != null) {
            staggeredGridLayoutManager$SavedState0.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = v;
        this.mPendingScrollPositionOffset = v1;
        this.requestLayout();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int scrollVerticallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.scrollBy(v, recyclerView$Recycler0, recyclerView$State0);
    }

    public void setGapStrategy(int v) {
        this.assertNotInLayoutOrScroll(null);
        if(v == this.mGapStrategy) {
            return;
        }
        if(v != 0 && v != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = v;
        this.requestLayout();
    }

    private void setLayoutStateDirection(int v) {
        this.mLayoutState.mLayoutDirection = v;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout == (v == -1) ? 1 : -1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void setMeasuredDimension(Rect rect0, int v, int v1) {
        int v5;
        int v4;
        int v2 = this.getPaddingLeft() + this.getPaddingRight();
        int v3 = this.getPaddingTop() + this.getPaddingBottom();
        if(this.mOrientation == 1) {
            v4 = StaggeredGridLayoutManager.chooseSize(v1, rect0.height() + v3, this.getMinimumHeight());
            v5 = StaggeredGridLayoutManager.chooseSize(v, this.mSizePerSpan * this.mSpanCount + v2, this.getMinimumWidth());
        }
        else {
            v5 = StaggeredGridLayoutManager.chooseSize(v, rect0.width() + v2, this.getMinimumWidth());
            v4 = StaggeredGridLayoutManager.chooseSize(v1, this.mSizePerSpan * this.mSpanCount + v3, this.getMinimumHeight());
        }
        this.setMeasuredDimension(v5, v4);
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        this.assertNotInLayoutOrScroll(null);
        if(v == this.mOrientation) {
            return;
        }
        this.mOrientation = v;
        OrientationHelper orientationHelper0 = this.mPrimaryOrientation;
        this.mPrimaryOrientation = this.mSecondaryOrientation;
        this.mSecondaryOrientation = orientationHelper0;
        this.requestLayout();
    }

    public void setReverseLayout(boolean z) {
        this.assertNotInLayoutOrScroll(null);
        if(this.mPendingSavedState != null && this.mPendingSavedState.mReverseLayout != z) {
            this.mPendingSavedState.mReverseLayout = z;
        }
        this.mReverseLayout = z;
        this.requestLayout();
    }

    public void setSpanCount(int v) {
        this.assertNotInLayoutOrScroll(null);
        if(v != this.mSpanCount) {
            this.invalidateSpanAssignments();
            this.mSpanCount = v;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for(int v1 = 0; v1 < this.mSpanCount; ++v1) {
                Span[] arr_staggeredGridLayoutManager$Span = this.mSpans;
                arr_staggeredGridLayoutManager$Span[v1] = new Span(this, v1);
            }
            this.requestLayout();
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView0, State recyclerView$State0, int v) {
        LinearSmoothScroller linearSmoothScroller0 = new LinearSmoothScroller(recyclerView0.getContext());
        linearSmoothScroller0.setTargetPosition(v);
        this.startSmoothScroll(linearSmoothScroller0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    private void updateAllRemainingSpans(int v, int v1) {
        for(int v2 = 0; v2 < this.mSpanCount; ++v2) {
            if(!this.mSpans[v2].mViews.isEmpty()) {
                this.updateRemainingSpans(this.mSpans[v2], v, v1);
            }
        }
    }

    private boolean updateAnchorFromChildren(State recyclerView$State0, AnchorInfo staggeredGridLayoutManager$AnchorInfo0) {
        staggeredGridLayoutManager$AnchorInfo0.mPosition = this.mLastLayoutFromEnd ? this.findLastReferenceChildPosition(recyclerView$State0.getItemCount()) : this.findFirstReferenceChildPosition(recyclerView$State0.getItemCount());
        staggeredGridLayoutManager$AnchorInfo0.mOffset = 0x80000000;
        return true;
    }

    boolean updateAnchorFromPendingData(State recyclerView$State0, AnchorInfo staggeredGridLayoutManager$AnchorInfo0) {
        boolean z = false;
        if(!recyclerView$State0.isPreLayout()) {
            int v = this.mPendingScrollPosition;
            if(v != -1) {
                if(v >= 0 && v < recyclerView$State0.getItemCount()) {
                    if(this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != -1 && this.mPendingSavedState.mSpanOffsetsSize >= 1) {
                        staggeredGridLayoutManager$AnchorInfo0.mOffset = 0x80000000;
                        staggeredGridLayoutManager$AnchorInfo0.mPosition = this.mPendingScrollPosition;
                        return true;
                    }
                    View view0 = this.findViewByPosition(this.mPendingScrollPosition);
                    if(view0 != null) {
                        staggeredGridLayoutManager$AnchorInfo0.mPosition = this.mShouldReverseLayout ? this.getLastChildPosition() : this.getFirstChildPosition();
                        if(this.mPendingScrollPositionOffset != 0x80000000) {
                            if(staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd) {
                                staggeredGridLayoutManager$AnchorInfo0.mOffset = this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedEnd(view0);
                                return true;
                            }
                            staggeredGridLayoutManager$AnchorInfo0.mOffset = this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedStart(view0);
                            return true;
                        }
                        if(this.mPrimaryOrientation.getDecoratedMeasurement(view0) > this.mPrimaryOrientation.getTotalSpace()) {
                            staggeredGridLayoutManager$AnchorInfo0.mOffset = staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
                            return true;
                        }
                        int v1 = this.mPrimaryOrientation.getDecoratedStart(view0) - this.mPrimaryOrientation.getStartAfterPadding();
                        if(v1 < 0) {
                            staggeredGridLayoutManager$AnchorInfo0.mOffset = -v1;
                            return true;
                        }
                        int v2 = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(view0);
                        if(v2 < 0) {
                            staggeredGridLayoutManager$AnchorInfo0.mOffset = v2;
                            return true;
                        }
                        staggeredGridLayoutManager$AnchorInfo0.mOffset = 0x80000000;
                        return true;
                    }
                    staggeredGridLayoutManager$AnchorInfo0.mPosition = this.mPendingScrollPosition;
                    int v3 = this.mPendingScrollPositionOffset;
                    if(v3 == 0x80000000) {
                        if(this.calculateScrollDirectionForPosition(staggeredGridLayoutManager$AnchorInfo0.mPosition) == 1) {
                            z = true;
                        }
                        staggeredGridLayoutManager$AnchorInfo0.mLayoutFromEnd = z;
                        staggeredGridLayoutManager$AnchorInfo0.assignCoordinateFromPadding();
                    }
                    else {
                        staggeredGridLayoutManager$AnchorInfo0.assignCoordinateFromPadding(v3);
                    }
                    staggeredGridLayoutManager$AnchorInfo0.mInvalidateOffsets = true;
                    return true;
                }
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = 0x80000000;
            }
        }
        return false;
    }

    void updateAnchorInfoForLayout(State recyclerView$State0, AnchorInfo staggeredGridLayoutManager$AnchorInfo0) {
        if(this.updateAnchorFromPendingData(recyclerView$State0, staggeredGridLayoutManager$AnchorInfo0) || this.updateAnchorFromChildren(recyclerView$State0, staggeredGridLayoutManager$AnchorInfo0)) {
            return;
        }
        staggeredGridLayoutManager$AnchorInfo0.assignCoordinateFromPadding();
        staggeredGridLayoutManager$AnchorInfo0.mPosition = 0;
    }

    private void updateLayoutState(int v, State recyclerView$State0) {
        int v3;
        int v2;
        boolean z = false;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = v;
        if(this.isSmoothScrolling()) {
            int v1 = recyclerView$State0.getTargetScrollPosition();
            if(v1 == -1) {
                v2 = 0;
                v3 = 0;
            }
            else if(this.mShouldReverseLayout == v1 < v) {
                v2 = this.mPrimaryOrientation.getTotalSpace();
                v3 = 0;
            }
            else {
                v3 = this.mPrimaryOrientation.getTotalSpace();
                v2 = 0;
            }
        }
        else {
            v2 = 0;
            v3 = 0;
        }
        if(this.getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - v3;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + v2;
        }
        else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + v2;
            this.mLayoutState.mStartLine = -v3;
        }
        this.mLayoutState.mStopInFocusable = false;
        this.mLayoutState.mRecycle = true;
        LayoutState layoutState0 = this.mLayoutState;
        if(this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0) {
            z = true;
        }
        layoutState0.mInfinite = z;
    }

    void updateMeasureSpecs(int v) {
        this.mSizePerSpan = v / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(v, this.mSecondaryOrientation.getMode());
    }

    private void updateRemainingSpans(Span staggeredGridLayoutManager$Span0, int v, int v1) {
        int v2 = staggeredGridLayoutManager$Span0.getDeletedSize();
        if(v == -1) {
            if(staggeredGridLayoutManager$Span0.getStartLine() + v2 <= v1) {
                this.mRemainingSpans.set(staggeredGridLayoutManager$Span0.mIndex, false);
            }
        }
        else if(staggeredGridLayoutManager$Span0.getEndLine() - v2 >= v1) {
            this.mRemainingSpans.set(staggeredGridLayoutManager$Span0.mIndex, false);
        }
    }

    private int updateSpecWithExtra(int v, int v1, int v2) {
        if(v1 != 0 || v2 != 0) {
            int v3 = View.MeasureSpec.getMode(v);
            return v3 == 0x80000000 || v3 == 0x40000000 ? View.MeasureSpec.makeMeasureSpec(Math.max(0, View.MeasureSpec.getSize(v) - v1 - v2), v3) : v;
        }
        return v;
    }

    class androidx.recyclerview.widget.StaggeredGridLayoutManager.1 implements Runnable {
        @Override
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    }

}

