package androidx.recyclerview.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController;
import androidx.core.view.DifferentialMotionFlingTarget;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.poolingcontainer.PoolingContainer;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R.attr;
import androidx.recyclerview.R.dimen;
import androidx.recyclerview.R.styleable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public class RecyclerView extends ViewGroup implements NestedScrollingChild2, NestedScrollingChild3, ScrollingView {
    public static abstract class Adapter {
        public static enum StateRestorationPolicy {
            ALLOW,
            PREVENT_WHEN_EMPTY,
            PREVENT;

            private static StateRestorationPolicy[] $values() [...] // Inlined contents
        }

        private boolean mHasStableIds;
        private final AdapterDataObservable mObservable;
        private StateRestorationPolicy mStateRestorationPolicy;

        public Adapter() {
            this.mObservable = new AdapterDataObservable();
            this.mHasStableIds = false;
            this.mStateRestorationPolicy = StateRestorationPolicy.ALLOW;
        }

        public final void bindViewHolder(ViewHolder recyclerView$ViewHolder0, int v) {
            boolean z = recyclerView$ViewHolder0.mBindingAdapter == null;
            if(z) {
                recyclerView$ViewHolder0.mPosition = v;
                if(this.hasStableIds()) {
                    recyclerView$ViewHolder0.mItemId = this.getItemId(v);
                }
                recyclerView$ViewHolder0.setFlags(1, 0x207);
                if(TraceCompat.isEnabled()) {
                    Trace.beginSection(String.format("RV onBindViewHolder type=0x%X", recyclerView$ViewHolder0.mItemViewType));
                }
            }
            recyclerView$ViewHolder0.mBindingAdapter = this;
            if(RecyclerView.sDebugAssertionsEnabled) {
                if(recyclerView$ViewHolder0.itemView.getParent() == null && recyclerView$ViewHolder0.itemView.isAttachedToWindow() != recyclerView$ViewHolder0.isTmpDetached()) {
                    throw new IllegalStateException("Temp-detached state out of sync with reality. holder.isTmpDetached(): " + recyclerView$ViewHolder0.isTmpDetached() + ", attached to window: " + recyclerView$ViewHolder0.itemView.isAttachedToWindow() + ", holder: " + recyclerView$ViewHolder0);
                }
                if(recyclerView$ViewHolder0.itemView.getParent() == null && recyclerView$ViewHolder0.itemView.isAttachedToWindow()) {
                    throw new IllegalStateException("Attempting to bind attached holder with no parent (AKA temp detached): " + recyclerView$ViewHolder0);
                }
            }
            this.onBindViewHolder(recyclerView$ViewHolder0, v, recyclerView$ViewHolder0.getUnmodifiedPayloads());
            if(z) {
                recyclerView$ViewHolder0.clearPayload();
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = recyclerView$ViewHolder0.itemView.getLayoutParams();
                if(viewGroup$LayoutParams0 instanceof LayoutParams) {
                    ((LayoutParams)viewGroup$LayoutParams0).mInsetsDirty = true;
                }
                Trace.endSection();
            }
        }

        boolean canRestoreState() {
            switch(this.mStateRestorationPolicy.ordinal()) {
                case 1: {
                    return this.getItemCount() > 0;
                }
                case 2: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }

        public final ViewHolder createViewHolder(ViewGroup viewGroup0, int v) {
            try {
                if(TraceCompat.isEnabled()) {
                    Trace.beginSection(String.format("RV onCreateViewHolder type=0x%X", v));
                }
                ViewHolder recyclerView$ViewHolder0 = this.onCreateViewHolder(viewGroup0, v);
                if(recyclerView$ViewHolder0.itemView.getParent() == null) {
                    recyclerView$ViewHolder0.mItemViewType = v;
                    return recyclerView$ViewHolder0;
                }
            }
            finally {
                Trace.endSection();
            }
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing \'true\' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        }

        public int findRelativeAdapterPositionIn(Adapter recyclerView$Adapter0, ViewHolder recyclerView$ViewHolder0, int v) {
            return recyclerView$Adapter0 == this ? v : -1;
        }

        public abstract int getItemCount();

        public long getItemId(int v) {
            return -1L;
        }

        public int getItemViewType(int v) {
            return 0;
        }

        public final StateRestorationPolicy getStateRestorationPolicy() {
            return this.mStateRestorationPolicy;
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int v) {
            this.mObservable.notifyItemRangeChanged(v, 1);
        }

        public final void notifyItemChanged(int v, Object object0) {
            this.mObservable.notifyItemRangeChanged(v, 1, object0);
        }

        public final void notifyItemInserted(int v) {
            this.mObservable.notifyItemRangeInserted(v, 1);
        }

        public final void notifyItemMoved(int v, int v1) {
            this.mObservable.notifyItemMoved(v, v1);
        }

        public final void notifyItemRangeChanged(int v, int v1) {
            this.mObservable.notifyItemRangeChanged(v, v1);
        }

        public final void notifyItemRangeChanged(int v, int v1, Object object0) {
            this.mObservable.notifyItemRangeChanged(v, v1, object0);
        }

        public final void notifyItemRangeInserted(int v, int v1) {
            this.mObservable.notifyItemRangeInserted(v, v1);
        }

        public final void notifyItemRangeRemoved(int v, int v1) {
            this.mObservable.notifyItemRangeRemoved(v, v1);
        }

        public final void notifyItemRemoved(int v) {
            this.mObservable.notifyItemRangeRemoved(v, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView0) {
        }

        public abstract void onBindViewHolder(ViewHolder arg1, int arg2);

        public void onBindViewHolder(ViewHolder recyclerView$ViewHolder0, int v, List list0) {
            this.onBindViewHolder(recyclerView$ViewHolder0, v);
        }

        public abstract ViewHolder onCreateViewHolder(ViewGroup arg1, int arg2);

        public void onDetachedFromRecyclerView(RecyclerView recyclerView0) {
        }

        public boolean onFailedToRecycleView(ViewHolder recyclerView$ViewHolder0) {
            return false;
        }

        public void onViewAttachedToWindow(ViewHolder recyclerView$ViewHolder0) {
        }

        public void onViewDetachedFromWindow(ViewHolder recyclerView$ViewHolder0) {
        }

        public void onViewRecycled(ViewHolder recyclerView$ViewHolder0) {
        }

        public void registerAdapterDataObserver(AdapterDataObserver recyclerView$AdapterDataObserver0) {
            this.mObservable.registerObserver(recyclerView$AdapterDataObserver0);
        }

        public void setHasStableIds(boolean z) {
            if(this.hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z;
        }

        public void setStateRestorationPolicy(StateRestorationPolicy recyclerView$Adapter$StateRestorationPolicy0) {
            this.mStateRestorationPolicy = recyclerView$Adapter$StateRestorationPolicy0;
            this.mObservable.notifyStateRestorationPolicyChanged();
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver recyclerView$AdapterDataObserver0) {
            this.mObservable.unregisterObserver(recyclerView$AdapterDataObserver0);
        }
    }

    static class AdapterDataObservable extends Observable {
        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }

        public void notifyChanged() {
            for(int v = this.mObservers.size() - 1; v >= 0; --v) {
                ((AdapterDataObserver)this.mObservers.get(v)).onChanged();
            }
        }

        public void notifyItemMoved(int v, int v1) {
            for(int v2 = this.mObservers.size() - 1; v2 >= 0; --v2) {
                ((AdapterDataObserver)this.mObservers.get(v2)).onItemRangeMoved(v, v1, 1);
            }
        }

        public void notifyItemRangeChanged(int v, int v1) {
            this.notifyItemRangeChanged(v, v1, null);
        }

        public void notifyItemRangeChanged(int v, int v1, Object object0) {
            for(int v2 = this.mObservers.size() - 1; v2 >= 0; --v2) {
                ((AdapterDataObserver)this.mObservers.get(v2)).onItemRangeChanged(v, v1, object0);
            }
        }

        public void notifyItemRangeInserted(int v, int v1) {
            for(int v2 = this.mObservers.size() - 1; v2 >= 0; --v2) {
                ((AdapterDataObserver)this.mObservers.get(v2)).onItemRangeInserted(v, v1);
            }
        }

        public void notifyItemRangeRemoved(int v, int v1) {
            for(int v2 = this.mObservers.size() - 1; v2 >= 0; --v2) {
                ((AdapterDataObserver)this.mObservers.get(v2)).onItemRangeRemoved(v, v1);
            }
        }

        public void notifyStateRestorationPolicyChanged() {
            for(int v = this.mObservers.size() - 1; v >= 0; --v) {
                ((AdapterDataObserver)this.mObservers.get(v)).onStateRestorationPolicyChanged();
            }
        }
    }

    public static abstract class AdapterDataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int v, int v1) {
        }

        public void onItemRangeChanged(int v, int v1, Object object0) {
            this.onItemRangeChanged(v, v1);
        }

        public void onItemRangeInserted(int v, int v1) {
        }

        public void onItemRangeMoved(int v, int v1, int v2) {
        }

        public void onItemRangeRemoved(int v, int v1) {
        }

        public void onStateRestorationPolicyChanged() {
        }
    }

    static final class Api35Impl {
        public static void setFrameContentVelocity(View view0, float f) {
            try {
                view0.setFrameContentVelocity(f);
            }
            catch(LinkageError unused_ex) {
            }
        }
    }

    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int arg1, int arg2);
    }

    public static class EdgeEffectFactory {
        @Retention(RetentionPolicy.SOURCE)
        public @interface EdgeDirection {
        }

        public static final int DIRECTION_BOTTOM = 3;
        public static final int DIRECTION_LEFT = 0;
        public static final int DIRECTION_RIGHT = 2;
        public static final int DIRECTION_TOP = 1;

        protected EdgeEffect createEdgeEffect(RecyclerView recyclerView0, int v) {
            return new EdgeEffect(recyclerView0.getContext());
        }
    }

    public static abstract class ItemAnimator {
        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }

        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        interface ItemAnimatorListener {
            void onAnimationFinished(ViewHolder arg1);
        }

        public static class ItemHolderInfo {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;

            public ItemHolderInfo setFrom(ViewHolder recyclerView$ViewHolder0) {
                return this.setFrom(recyclerView$ViewHolder0, 0);
            }

            public ItemHolderInfo setFrom(ViewHolder recyclerView$ViewHolder0, int v) {
                this.left = recyclerView$ViewHolder0.itemView.getLeft();
                this.top = recyclerView$ViewHolder0.itemView.getTop();
                this.right = recyclerView$ViewHolder0.itemView.getRight();
                this.bottom = recyclerView$ViewHolder0.itemView.getBottom();
                return this;
            }
        }

        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 0x1000;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 0x800;
        public static final int FLAG_REMOVED = 8;
        private long mAddDuration;
        private long mChangeDuration;
        private ArrayList mFinishedListeners;
        private ItemAnimatorListener mListener;
        private long mMoveDuration;
        private long mRemoveDuration;

        public ItemAnimator() {
            this.mListener = null;
            this.mFinishedListeners = new ArrayList();
            this.mAddDuration = 120L;
            this.mRemoveDuration = 120L;
            this.mMoveDuration = 0xFAL;
            this.mChangeDuration = 0xFAL;
        }

        public abstract boolean animateAppearance(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        public abstract boolean animateChange(ViewHolder arg1, ViewHolder arg2, ItemHolderInfo arg3, ItemHolderInfo arg4);

        public abstract boolean animateDisappearance(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        public abstract boolean animatePersistence(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        static int buildAdapterChangeFlagsForAnimations(ViewHolder recyclerView$ViewHolder0) {
            int v = recyclerView$ViewHolder0.mFlags;
            if(recyclerView$ViewHolder0.isInvalid()) {
                return 4;
            }
            if((v & 4) == 0) {
                int v1 = recyclerView$ViewHolder0.getOldPosition();
                int v2 = recyclerView$ViewHolder0.getAbsoluteAdapterPosition();
                return v1 == -1 || v2 == -1 || v1 == v2 ? v & 14 : v & 14 | 0x800;
            }
            return v & 14;
        }

        public boolean canReuseUpdatedViewHolder(ViewHolder recyclerView$ViewHolder0) {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(ViewHolder recyclerView$ViewHolder0, List list0) {
            return this.canReuseUpdatedViewHolder(recyclerView$ViewHolder0);
        }

        public final void dispatchAnimationFinished(ViewHolder recyclerView$ViewHolder0) {
            ItemAnimatorListener recyclerView$ItemAnimator$ItemAnimatorListener0 = this.mListener;
            if(recyclerView$ItemAnimator$ItemAnimatorListener0 != null) {
                recyclerView$ItemAnimator$ItemAnimatorListener0.onAnimationFinished(recyclerView$ViewHolder0);
            }
        }

        public final void dispatchAnimationStarted(ViewHolder recyclerView$ViewHolder0) {
        }

        public final void dispatchAnimationsFinished() {
            int v = this.mFinishedListeners.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((ItemAnimatorFinishedListener)this.mFinishedListeners.get(v1)).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder arg1);

        public abstract void endAnimations();

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(ItemAnimatorFinishedListener recyclerView$ItemAnimator$ItemAnimatorFinishedListener0) {
            boolean z = this.isRunning();
            if(recyclerView$ItemAnimator$ItemAnimatorFinishedListener0 != null) {
                if(!z) {
                    recyclerView$ItemAnimator$ItemAnimatorFinishedListener0.onAnimationsFinished();
                    return false;
                }
                this.mFinishedListeners.add(recyclerView$ItemAnimator$ItemAnimatorFinishedListener0);
            }
            return z;
        }

        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(ViewHolder recyclerView$ViewHolder0) {
        }

        public void onAnimationStarted(ViewHolder recyclerView$ViewHolder0) {
        }

        public ItemHolderInfo recordPostLayoutInformation(State recyclerView$State0, ViewHolder recyclerView$ViewHolder0) {
            return this.obtainHolderInfo().setFrom(recyclerView$ViewHolder0);
        }

        public ItemHolderInfo recordPreLayoutInformation(State recyclerView$State0, ViewHolder recyclerView$ViewHolder0, int v, List list0) {
            return this.obtainHolderInfo().setFrom(recyclerView$ViewHolder0);
        }

        public abstract void runPendingAnimations();

        public void setAddDuration(long v) {
            this.mAddDuration = v;
        }

        public void setChangeDuration(long v) {
            this.mChangeDuration = v;
        }

        void setListener(ItemAnimatorListener recyclerView$ItemAnimator$ItemAnimatorListener0) {
            this.mListener = recyclerView$ItemAnimator$ItemAnimatorListener0;
        }

        public void setMoveDuration(long v) {
            this.mMoveDuration = v;
        }

        public void setRemoveDuration(long v) {
            this.mRemoveDuration = v;
        }
    }

    class ItemAnimatorRestoreListener implements ItemAnimatorListener {
        @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator$ItemAnimatorListener
        public void onAnimationFinished(ViewHolder recyclerView$ViewHolder0) {
            recyclerView$ViewHolder0.setIsRecyclable(true);
            if(recyclerView$ViewHolder0.mShadowedHolder != null && recyclerView$ViewHolder0.mShadowingHolder == null) {
                recyclerView$ViewHolder0.mShadowedHolder = null;
            }
            recyclerView$ViewHolder0.mShadowingHolder = null;
            if(!recyclerView$ViewHolder0.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(recyclerView$ViewHolder0.itemView) && recyclerView$ViewHolder0.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(recyclerView$ViewHolder0.itemView, false);
            }
        }
    }

    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(Rect rect0, int v, RecyclerView recyclerView0) {
            rect0.set(0, 0, 0, 0);
        }

        public void getItemOffsets(Rect rect0, View view0, RecyclerView recyclerView0, State recyclerView$State0) {
            this.getItemOffsets(rect0, ((LayoutParams)view0.getLayoutParams()).getViewLayoutPosition(), recyclerView0);
        }

        @Deprecated
        public void onDraw(Canvas canvas0, RecyclerView recyclerView0) {
        }

        public void onDraw(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        }

        @Deprecated
        public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0) {
        }

        public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        }
    }

    public static abstract class LayoutManager {
        public interface LayoutPrefetchRegistry {
            void addPosition(int arg1, int arg2);
        }

        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;

        }

        boolean mAutoMeasure;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        ViewBoundsCheck mHorizontalBoundCheck;
        private final Callback mHorizontalBoundCheckCallback;
        boolean mIsAttachedToWindow;
        private boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations;
        SmoothScroller mSmoothScroller;
        ViewBoundsCheck mVerticalBoundCheck;
        private final Callback mVerticalBoundCheckCallback;
        private int mWidth;
        private int mWidthMode;

        public LayoutManager() {
            androidx.recyclerview.widget.RecyclerView.LayoutManager.1 recyclerView$LayoutManager$10 = new Callback() {
                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public View getChildAt(int v) {
                    return LayoutManager.this.getChildAt(v);
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getChildEnd(View view0) {
                    LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                    return LayoutManager.this.getDecoratedRight(view0) + recyclerView$LayoutParams0.rightMargin;
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getChildStart(View view0) {
                    LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                    return LayoutManager.this.getDecoratedLeft(view0) - recyclerView$LayoutParams0.leftMargin;
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getParentEnd() {
                    int v = LayoutManager.this.getPaddingRight();
                    return LayoutManager.this.getWidth() - v;
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingLeft();
                }
            };
            this.mHorizontalBoundCheckCallback = recyclerView$LayoutManager$10;
            androidx.recyclerview.widget.RecyclerView.LayoutManager.2 recyclerView$LayoutManager$20 = new Callback() {
                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public View getChildAt(int v) {
                    return LayoutManager.this.getChildAt(v);
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getChildEnd(View view0) {
                    LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                    return LayoutManager.this.getDecoratedBottom(view0) + recyclerView$LayoutParams0.bottomMargin;
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getChildStart(View view0) {
                    LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                    return LayoutManager.this.getDecoratedTop(view0) - recyclerView$LayoutParams0.topMargin;
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getParentEnd() {
                    int v = LayoutManager.this.getPaddingBottom();
                    return LayoutManager.this.getHeight() - v;
                }

                @Override  // androidx.recyclerview.widget.ViewBoundsCheck$Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingTop();
                }
            };
            this.mVerticalBoundCheckCallback = recyclerView$LayoutManager$20;
            this.mHorizontalBoundCheck = new ViewBoundsCheck(recyclerView$LayoutManager$10);
            this.mVerticalBoundCheck = new ViewBoundsCheck(recyclerView$LayoutManager$20);
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mAutoMeasure = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        public void addDisappearingView(View view0) {
            this.addDisappearingView(view0, -1);
        }

        public void addDisappearingView(View view0, int v) {
            this.addViewInt(view0, v, true);
        }

        public void addView(View view0) {
            this.addView(view0, -1);
        }

        public void addView(View view0, int v) {
            this.addViewInt(view0, v, false);
        }

        private void addViewInt(View view0, int v, boolean z) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(z || recyclerView$ViewHolder0.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(recyclerView$ViewHolder0);
            }
            else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(recyclerView$ViewHolder0);
            }
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(recyclerView$ViewHolder0.wasReturnedFromScrap() || recyclerView$ViewHolder0.isScrap()) {
                if(recyclerView$ViewHolder0.isScrap()) {
                    recyclerView$ViewHolder0.unScrap();
                }
                else {
                    recyclerView$ViewHolder0.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view0, v, view0.getLayoutParams(), false);
            }
            else if(view0.getParent() == this.mRecyclerView) {
                int v1 = this.mChildHelper.indexOfChild(view0);
                if(v == -1) {
                    v = this.mChildHelper.getChildCount();
                }
                if(v1 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(view0) + this.mRecyclerView.exceptionLabel());
                }
                if(v1 != v) {
                    this.mRecyclerView.mLayout.moveView(v1, v);
                }
            }
            else {
                this.mChildHelper.addView(view0, v, false);
                recyclerView$LayoutParams0.mInsetsDirty = true;
                if(this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(view0);
                }
            }
            if(recyclerView$LayoutParams0.mPendingInvalidate) {
                if(RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("RecyclerView", "consuming pending invalidate on child " + recyclerView$LayoutParams0.mViewHolder);
                }
                recyclerView$ViewHolder0.itemView.invalidate();
                recyclerView$LayoutParams0.mPendingInvalidate = false;
            }
        }

        public void assertInLayoutOrScroll(String s) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null) {
                recyclerView0.assertInLayoutOrScroll(s);
            }
        }

        public void assertNotInLayoutOrScroll(String s) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null) {
                recyclerView0.assertNotInLayoutOrScroll(s);
            }
        }

        public void attachView(View view0) {
            this.attachView(view0, -1);
        }

        public void attachView(View view0, int v) {
            this.attachView(view0, v, ((LayoutParams)view0.getLayoutParams()));
        }

        public void attachView(View view0, int v, LayoutParams recyclerView$LayoutParams0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(recyclerView$ViewHolder0.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(recyclerView$ViewHolder0);
            }
            else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(recyclerView$ViewHolder0);
            }
            this.mChildHelper.attachViewToParent(view0, v, recyclerView$LayoutParams0, recyclerView$ViewHolder0.isRemoved());
        }

        public void calculateItemDecorationsForChild(View view0, Rect rect0) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 == null) {
                rect0.set(0, 0, 0, 0);
                return;
            }
            rect0.set(recyclerView0.getItemDecorInsetsForChild(view0));
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams recyclerView$LayoutParams0) {
            return recyclerView$LayoutParams0 != null;
        }

        public static int chooseSize(int v, int v1, int v2) {
            int v3 = View.MeasureSpec.getMode(v);
            int v4 = View.MeasureSpec.getSize(v);
            switch(v3) {
                case 0x80000000: {
                    return Math.min(v4, Math.max(v1, v2));
                }
                case 0x40000000: {
                    return v4;
                }
                default: {
                    return Math.max(v1, v2);
                }
            }
        }

        public void collectAdjacentPrefetchPositions(int v, int v1, State recyclerView$State0, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        }

        public void collectInitialPrefetchPositions(int v, LayoutPrefetchRegistry recyclerView$LayoutManager$LayoutPrefetchRegistry0) {
        }

        public int computeHorizontalScrollExtent(State recyclerView$State0) {
            return 0;
        }

        public int computeHorizontalScrollOffset(State recyclerView$State0) {
            return 0;
        }

        public int computeHorizontalScrollRange(State recyclerView$State0) {
            return 0;
        }

        public int computeVerticalScrollExtent(State recyclerView$State0) {
            return 0;
        }

        public int computeVerticalScrollOffset(State recyclerView$State0) {
            return 0;
        }

        public int computeVerticalScrollRange(State recyclerView$State0) {
            return 0;
        }

        public void detachAndScrapAttachedViews(Recycler recyclerView$Recycler0) {
            for(int v = this.getChildCount() - 1; v >= 0; --v) {
                this.scrapOrRecycleView(recyclerView$Recycler0, v, this.getChildAt(v));
            }
        }

        public void detachAndScrapView(View view0, Recycler recyclerView$Recycler0) {
            this.scrapOrRecycleView(recyclerView$Recycler0, this.mChildHelper.indexOfChild(view0), view0);
        }

        public void detachAndScrapViewAt(int v, Recycler recyclerView$Recycler0) {
            this.scrapOrRecycleView(recyclerView$Recycler0, v, this.getChildAt(v));
        }

        public void detachView(View view0) {
            int v = this.mChildHelper.indexOfChild(view0);
            if(v >= 0) {
                this.detachViewInternal(v, view0);
            }
        }

        public void detachViewAt(int v) {
            this.detachViewInternal(v, this.getChildAt(v));
        }

        private void detachViewInternal(int v, View view0) {
            this.mChildHelper.detachViewFromParent(v);
        }

        void dispatchAttachedToWindow(RecyclerView recyclerView0) {
            this.mIsAttachedToWindow = true;
            this.onAttachedToWindow(recyclerView0);
        }

        void dispatchDetachedFromWindow(RecyclerView recyclerView0, Recycler recyclerView$Recycler0) {
            this.mIsAttachedToWindow = false;
            this.onDetachedFromWindow(recyclerView0, recyclerView$Recycler0);
        }

        public void endAnimation(View view0) {
            if(this.mRecyclerView.mItemAnimator != null) {
                this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view0));
            }
        }

        public View findContainingItemView(View view0) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 == null) {
                return null;
            }
            View view1 = recyclerView0.findContainingItemView(view0);
            if(view1 == null) {
                return null;
            }
            return this.mChildHelper.isHidden(view1) ? null : view1;
        }

        public View findViewByPosition(int v) {
            int v1 = this.getChildCount();
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = this.getChildAt(v2);
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
                if(recyclerView$ViewHolder0 != null && recyclerView$ViewHolder0.getLayoutPosition() == v && !recyclerView$ViewHolder0.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !recyclerView$ViewHolder0.isRemoved())) {
                    return view0;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(Context context0, AttributeSet attributeSet0) {
            return new LayoutParams(context0, attributeSet0);
        }

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            if(viewGroup$LayoutParams0 instanceof LayoutParams) {
                return new LayoutParams(((LayoutParams)viewGroup$LayoutParams0));
            }
            return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
        }

        public int getBaseline() [...] // Inlined contents

        public int getBottomDecorationHeight(View view0) {
            return ((LayoutParams)view0.getLayoutParams()).mDecorInsets.bottom;
        }

        public View getChildAt(int v) {
            return this.mChildHelper == null ? null : this.mChildHelper.getChildAt(v);
        }

        public int getChildCount() {
            return this.mChildHelper == null ? 0 : this.mChildHelper.getChildCount();
        }

        public static int getChildMeasureSpec(int v, int v1, int v2, int v3, boolean z) {
            int v4 = Math.max(0, v - v2);
            if(z) {
                if(v3 >= 0) {
                    return View.MeasureSpec.makeMeasureSpec(v3, 0x40000000);
                }
                return v3 == -1 && (v1 == 0x80000000 || v1 == 0x40000000) ? View.MeasureSpec.makeMeasureSpec(v4, v1) : 0;
            }
            if(v3 >= 0) {
                return View.MeasureSpec.makeMeasureSpec(v3, 0x40000000);
            }
            if(v3 == -1) {
                return View.MeasureSpec.makeMeasureSpec(v4, v1);
            }
            if(v3 == -2) {
                return v1 == 0x80000000 || v1 == 0x40000000 ? View.MeasureSpec.makeMeasureSpec(v4, 0x80000000) : View.MeasureSpec.makeMeasureSpec(v4, 0);
            }
            return 0;
        }

        @Deprecated
        public static int getChildMeasureSpec(int v, int v1, int v2, boolean z) {
            int v3 = Math.max(0, v - v1);
            if(z) {
                return v2 >= 0 ? View.MeasureSpec.makeMeasureSpec(v2, 0x40000000) : 0;
            }
            if(v2 >= 0) {
                return View.MeasureSpec.makeMeasureSpec(v2, 0x40000000);
            }
            switch(v2) {
                case -2: {
                    return View.MeasureSpec.makeMeasureSpec(v3, 0x80000000);
                }
                case -1: {
                    return View.MeasureSpec.makeMeasureSpec(v3, 0x40000000);
                }
                default: {
                    return 0;
                }
            }
        }

        private int[] getChildRectangleOnScreenScrollAmount(View view0, Rect rect0) {
            int v = this.getPaddingLeft();
            int v1 = this.getPaddingTop();
            int v2 = this.getPaddingRight();
            int v3 = this.getPaddingBottom();
            int v4 = view0.getLeft() + rect0.left - view0.getScrollX();
            int v5 = view0.getTop() + rect0.top - view0.getScrollY();
            int v6 = v4 - v;
            int v7 = Math.min(0, v6);
            int v8 = v5 - v1;
            int v9 = Math.min(0, v8);
            int v10 = rect0.width() + v4 - (this.getWidth() - v2);
            int v11 = Math.max(0, v10);
            int v12 = Math.max(0, rect0.height() + v5 - (this.getHeight() - v3));
            if(this.getLayoutDirection() != 1) {
                if(v7 == 0) {
                    v7 = Math.min(v6, v11);
                }
                v11 = v7;
            }
            else if(v11 == 0) {
                v11 = Math.max(v7, v10);
            }
            if(v9 == 0) {
                v9 = Math.min(v8, v12);
            }
            return new int[]{v11, v9};
        }

        public boolean getClipToPadding() {
            return this.mRecyclerView != null && this.mRecyclerView.mClipToPadding;
        }

        public int getColumnCountForAccessibility(Recycler recyclerView$Recycler0, State recyclerView$State0) {
            return this.mRecyclerView == null || this.mRecyclerView.mAdapter == null || !this.canScrollHorizontally() ? 1 : this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(View view0) {
            return view0.getBottom() + this.getBottomDecorationHeight(view0);
        }

        public void getDecoratedBoundsWithMargins(View view0, Rect rect0) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view0, rect0);
        }

        public int getDecoratedLeft(View view0) {
            return view0.getLeft() - this.getLeftDecorationWidth(view0);
        }

        public int getDecoratedMeasuredHeight(View view0) {
            Rect rect0 = ((LayoutParams)view0.getLayoutParams()).mDecorInsets;
            return view0.getMeasuredHeight() + rect0.top + rect0.bottom;
        }

        public int getDecoratedMeasuredWidth(View view0) {
            Rect rect0 = ((LayoutParams)view0.getLayoutParams()).mDecorInsets;
            return view0.getMeasuredWidth() + rect0.left + rect0.right;
        }

        public int getDecoratedRight(View view0) {
            return view0.getRight() + this.getRightDecorationWidth(view0);
        }

        public int getDecoratedTop(View view0) {
            return view0.getTop() - this.getTopDecorationHeight(view0);
        }

        public View getFocusedChild() {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 == null) {
                return null;
            }
            View view0 = recyclerView0.getFocusedChild();
            return view0 == null || this.mChildHelper.isHidden(view0) ? null : view0;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        public int getItemCount() {
            Adapter recyclerView$Adapter0 = this.mRecyclerView == null ? null : this.mRecyclerView.getAdapter();
            return recyclerView$Adapter0 == null ? 0 : recyclerView$Adapter0.getItemCount();
        }

        public int getItemViewType(View view0) {
            return RecyclerView.getChildViewHolderInt(view0).getItemViewType();
        }

        public int getLayoutDirection() {
            return this.mRecyclerView.getLayoutDirection();
        }

        public int getLeftDecorationWidth(View view0) {
            return ((LayoutParams)view0.getLayoutParams()).mDecorInsets.left;
        }

        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        public int getPaddingBottom() {
            return this.mRecyclerView == null ? 0 : this.mRecyclerView.getPaddingBottom();
        }

        public int getPaddingEnd() {
            return this.mRecyclerView == null ? 0 : ViewCompat.getPaddingEnd(this.mRecyclerView);
        }

        public int getPaddingLeft() {
            return this.mRecyclerView == null ? 0 : this.mRecyclerView.getPaddingLeft();
        }

        public int getPaddingRight() {
            return this.mRecyclerView == null ? 0 : this.mRecyclerView.getPaddingRight();
        }

        public int getPaddingStart() {
            return this.mRecyclerView == null ? 0 : ViewCompat.getPaddingStart(this.mRecyclerView);
        }

        public int getPaddingTop() {
            return this.mRecyclerView == null ? 0 : this.mRecyclerView.getPaddingTop();
        }

        public int getPosition(View view0) {
            return ((LayoutParams)view0.getLayoutParams()).getViewLayoutPosition();
        }

        public static Properties getProperties(Context context0, AttributeSet attributeSet0, int v, int v1) {
            Properties recyclerView$LayoutManager$Properties0 = new Properties();
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.RecyclerView, v, v1);
            recyclerView$LayoutManager$Properties0.orientation = typedArray0.getInt(styleable.RecyclerView_android_orientation, 1);
            recyclerView$LayoutManager$Properties0.spanCount = typedArray0.getInt(styleable.RecyclerView_spanCount, 1);
            recyclerView$LayoutManager$Properties0.reverseLayout = typedArray0.getBoolean(styleable.RecyclerView_reverseLayout, false);
            recyclerView$LayoutManager$Properties0.stackFromEnd = typedArray0.getBoolean(styleable.RecyclerView_stackFromEnd, false);
            typedArray0.recycle();
            return recyclerView$LayoutManager$Properties0;
        }

        public int getRightDecorationWidth(View view0) {
            return ((LayoutParams)view0.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(Recycler recyclerView$Recycler0, State recyclerView$State0) {
            return this.mRecyclerView == null || this.mRecyclerView.mAdapter == null || !this.canScrollVertically() ? 1 : this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getSelectionModeForAccessibility(Recycler recyclerView$Recycler0, State recyclerView$State0) [...] // Inlined contents

        public int getTopDecorationHeight(View view0) {
            return ((LayoutParams)view0.getLayoutParams()).mDecorInsets.top;
        }

        public void getTransformedBoundingBox(View view0, boolean z, Rect rect0) {
            if(z) {
                Rect rect1 = ((LayoutParams)view0.getLayoutParams()).mDecorInsets;
                rect0.set(-rect1.left, -rect1.top, view0.getWidth() + rect1.right, view0.getHeight() + rect1.bottom);
            }
            else {
                rect0.set(0, 0, view0.getWidth(), view0.getHeight());
            }
            if(this.mRecyclerView != null) {
                Matrix matrix0 = view0.getMatrix();
                if(matrix0 != null && !matrix0.isIdentity()) {
                    RectF rectF0 = this.mRecyclerView.mTempRectF;
                    rectF0.set(rect0);
                    matrix0.mapRect(rectF0);
                    rect0.set(((int)Math.floor(rectF0.left)), ((int)Math.floor(rectF0.top)), ((int)Math.ceil(rectF0.right)), ((int)Math.ceil(rectF0.bottom)));
                }
            }
            rect0.offset(view0.getLeft(), view0.getTop());
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        boolean hasFlexibleChildInBothOrientations() {
            int v = this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getChildAt(v1).getLayoutParams();
                if(viewGroup$LayoutParams0.width < 0 && viewGroup$LayoutParams0.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasFocus() {
            return this.mRecyclerView != null && this.mRecyclerView.hasFocus();
        }

        public void ignoreView(View view0) {
            if(view0.getParent() != this.mRecyclerView || this.mRecyclerView.indexOfChild(view0) == -1) {
                throw new IllegalArgumentException("View should be fully attached to be ignored" + this.mRecyclerView.exceptionLabel());
            }
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            recyclerView$ViewHolder0.addFlags(0x80);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(recyclerView$ViewHolder0);
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }

        public boolean isFocused() {
            return this.mRecyclerView != null && this.mRecyclerView.isFocused();
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView recyclerView0, int v, int v1) {
            View view0 = recyclerView0.getFocusedChild();
            if(view0 == null) {
                return false;
            }
            int v2 = this.getPaddingLeft();
            int v3 = this.getPaddingTop();
            int v4 = this.getPaddingRight();
            int v5 = this.getPaddingBottom();
            Rect rect0 = this.mRecyclerView.mTempRect;
            this.getDecoratedBoundsWithMargins(view0, rect0);
            return rect0.left - v < this.getWidth() - v4 && rect0.right - v > v2 && rect0.top - v1 < this.getHeight() - v5 && rect0.bottom - v1 > v3;
        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(Recycler recyclerView$Recycler0, State recyclerView$State0) [...] // Inlined contents

        public boolean isLayoutReversed() {
            return false;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        private static boolean isMeasurementUpToDate(int v, int v1, int v2) {
            int v3 = View.MeasureSpec.getMode(v1);
            int v4 = View.MeasureSpec.getSize(v1);
            if(v2 > 0 && v != v2) {
                return false;
            }
            switch(v3) {
                case 0x80000000: {
                    return v4 >= v;
                }
                case 0: {
                    return true;
                }
                default: {
                    return v3 == 0x40000000 ? v4 == v : false;
                }
            }
        }

        public boolean isSmoothScrolling() {
            return this.mSmoothScroller != null && this.mSmoothScroller.isRunning();
        }

        // 去混淆评级： 低(20)
        public boolean isViewPartiallyVisible(View view0, boolean z, boolean z1) {
            boolean z2 = this.mHorizontalBoundCheck.isViewWithinBoundFlags(view0, 0x6003) && this.mVerticalBoundCheck.isViewWithinBoundFlags(view0, 0x6003);
            return z ? z2 : !z2;
        }

        public void layoutDecorated(View view0, int v, int v1, int v2, int v3) {
            Rect rect0 = ((LayoutParams)view0.getLayoutParams()).mDecorInsets;
            view0.layout(v + rect0.left, v1 + rect0.top, v2 - rect0.right, v3 - rect0.bottom);
        }

        public void layoutDecoratedWithMargins(View view0, int v, int v1, int v2, int v3) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            view0.layout(v + recyclerView$LayoutParams0.mDecorInsets.left + recyclerView$LayoutParams0.leftMargin, v1 + recyclerView$LayoutParams0.mDecorInsets.top + recyclerView$LayoutParams0.topMargin, v2 - recyclerView$LayoutParams0.mDecorInsets.right - recyclerView$LayoutParams0.rightMargin, v3 - recyclerView$LayoutParams0.mDecorInsets.bottom - recyclerView$LayoutParams0.bottomMargin);
        }

        public void measureChild(View view0, int v, int v1) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            Rect rect0 = this.mRecyclerView.getItemDecorInsetsForChild(view0);
            int v2 = v + (rect0.left + rect0.right);
            int v3 = v1 + (rect0.top + rect0.bottom);
            int v4 = LayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + v2, recyclerView$LayoutParams0.width, this.canScrollHorizontally());
            int v5 = LayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + v3, recyclerView$LayoutParams0.height, this.canScrollVertically());
            if(this.shouldMeasureChild(view0, v4, v5, recyclerView$LayoutParams0)) {
                view0.measure(v4, v5);
            }
        }

        public void measureChildWithMargins(View view0, int v, int v1) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            Rect rect0 = this.mRecyclerView.getItemDecorInsetsForChild(view0);
            int v2 = v + (rect0.left + rect0.right);
            int v3 = v1 + (rect0.top + rect0.bottom);
            int v4 = LayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin + v2, recyclerView$LayoutParams0.width, this.canScrollHorizontally());
            int v5 = LayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin + v3, recyclerView$LayoutParams0.height, this.canScrollVertically());
            if(this.shouldMeasureChild(view0, v4, v5, recyclerView$LayoutParams0)) {
                view0.measure(v4, v5);
            }
        }

        public void moveView(int v, int v1) {
            View view0 = this.getChildAt(v);
            if(view0 == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + v + this.mRecyclerView.toString());
            }
            this.detachViewAt(v);
            this.attachView(view0, v1);
        }

        public void offsetChildrenHorizontal(int v) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null) {
                recyclerView0.offsetChildrenHorizontal(v);
            }
        }

        public void offsetChildrenVertical(int v) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null) {
                recyclerView0.offsetChildrenVertical(v);
            }
        }

        public void onAdapterChanged(Adapter recyclerView$Adapter0, Adapter recyclerView$Adapter1) {
        }

        public boolean onAddFocusables(RecyclerView recyclerView0, ArrayList arrayList0, int v, int v1) [...] // Inlined contents

        public void onAttachedToWindow(RecyclerView recyclerView0) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView0) {
        }

        public void onDetachedFromWindow(RecyclerView recyclerView0, Recycler recyclerView$Recycler0) {
        }

        public View onFocusSearchFailed(View view0, int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
            this.onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityEvent0);
        }

        public void onInitializeAccessibilityEvent(Recycler recyclerView$Recycler0, State recyclerView$State0, AccessibilityEvent accessibilityEvent0) {
            boolean z = true;
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null && accessibilityEvent0 != null) {
                if(!recyclerView0.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                    z = false;
                }
                accessibilityEvent0.setScrollable(z);
                if(this.mRecyclerView.mAdapter != null) {
                    accessibilityEvent0.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
                }
            }
        }

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            this.onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityNodeInfoCompat0);
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recyclerView$Recycler0, State recyclerView$State0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            if(this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat0.addAction(0x2000);
                accessibilityNodeInfoCompat0.setScrollable(true);
                accessibilityNodeInfoCompat0.setGranularScrollingSupported(true);
            }
            if(this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat0.addAction(0x1000);
                accessibilityNodeInfoCompat0.setScrollable(true);
                accessibilityNodeInfoCompat0.setGranularScrollingSupported(true);
            }
            accessibilityNodeInfoCompat0.setCollectionInfo(CollectionInfoCompat.obtain(this.getRowCountForAccessibility(recyclerView$Recycler0, recyclerView$State0), this.getColumnCountForAccessibility(recyclerView$Recycler0, recyclerView$State0), false, 0));
        }

        void onInitializeAccessibilityNodeInfoForItem(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(recyclerView$ViewHolder0 != null && !recyclerView$ViewHolder0.isRemoved() && !this.mChildHelper.isHidden(recyclerView$ViewHolder0.itemView)) {
                this.onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view0, accessibilityNodeInfoCompat0);
            }
        }

        // 去混淆评级： 低(20)
        public void onInitializeAccessibilityNodeInfoForItem(Recycler recyclerView$Recycler0, State recyclerView$State0, View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain((this.canScrollVertically() ? this.getPosition(view0) : 0), 1, (this.canScrollHorizontally() ? this.getPosition(view0) : 0), 1, false, false));
        }

        public View onInterceptFocusSearch(View view0, int v) [...] // Inlined contents

        public void onItemsAdded(RecyclerView recyclerView0, int v, int v1) {
        }

        public void onItemsChanged(RecyclerView recyclerView0) {
        }

        public void onItemsMoved(RecyclerView recyclerView0, int v, int v1, int v2) {
        }

        public void onItemsRemoved(RecyclerView recyclerView0, int v, int v1) {
        }

        public void onItemsUpdated(RecyclerView recyclerView0, int v, int v1) {
        }

        public void onItemsUpdated(RecyclerView recyclerView0, int v, int v1, Object object0) {
        }

        public void onLayoutChildren(Recycler recyclerView$Recycler0, State recyclerView$State0) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State recyclerView$State0) {
        }

        public void onMeasure(Recycler recyclerView$Recycler0, State recyclerView$State0, int v, int v1) {
            this.mRecyclerView.defaultOnMeasure(v, v1);
        }

        // 去混淆评级： 低(20)
        @Deprecated
        public boolean onRequestChildFocus(RecyclerView recyclerView0, View view0, View view1) {
            return this.isSmoothScrolling() || recyclerView0.isComputingLayout();
        }

        public boolean onRequestChildFocus(RecyclerView recyclerView0, State recyclerView$State0, View view0, View view1) {
            return this.onRequestChildFocus(recyclerView0, view0, view1);
        }

        public void onRestoreInstanceState(Parcelable parcelable0) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int v) {
        }

        void onSmoothScrollerStopped(SmoothScroller recyclerView$SmoothScroller0) {
            if(this.mSmoothScroller == recyclerView$SmoothScroller0) {
                this.mSmoothScroller = null;
            }
        }

        boolean performAccessibilityAction(int v, Bundle bundle0) {
            return this.performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, v, bundle0);
        }

        public boolean performAccessibilityAction(Recycler recyclerView$Recycler0, State recyclerView$State0, int v, Bundle bundle0) {
            float f;
            int v4;
            int v3;
            if(this.mRecyclerView == null) {
                return false;
            }
            int v1 = this.getHeight();
            int v2 = this.getWidth();
            Rect rect0 = new Rect();
            if(this.mRecyclerView.getMatrix().isIdentity() && this.mRecyclerView.getGlobalVisibleRect(rect0)) {
                v1 = rect0.height();
                v2 = rect0.width();
            }
            switch(v) {
                case 0x1000: {
                    v3 = this.mRecyclerView.canScrollVertically(1) ? v1 - this.getPaddingTop() - this.getPaddingBottom() : 0;
                    v4 = this.mRecyclerView.canScrollHorizontally(1) ? v2 - this.getPaddingLeft() - this.getPaddingRight() : 0;
                    break;
                }
                case 0x2000: {
                    v3 = this.mRecyclerView.canScrollVertically(-1) ? -(v1 - this.getPaddingTop() - this.getPaddingBottom()) : 0;
                    v4 = this.mRecyclerView.canScrollHorizontally(-1) ? -(v2 - this.getPaddingLeft() - this.getPaddingRight()) : 0;
                    break;
                }
                default: {
                    v3 = 0;
                    v4 = 0;
                }
            }
            if(v3 == 0 && v4 == 0) {
                return false;
            }
            if(bundle0 == null) {
                f = 1.0f;
            }
            else {
                f = bundle0.getFloat("androidx.core.view.accessibility.action.ARGUMENT_SCROLL_AMOUNT_FLOAT", 1.0f);
                if(f < 0.0f) {
                    if(RecyclerView.sDebugAssertionsEnabled) {
                        throw new IllegalArgumentException("attempting to use ACTION_ARGUMENT_SCROLL_AMOUNT_FLOAT with a negative value (" + f + ")");
                    }
                    return false;
                }
            }
            if(Float.compare(f, Infinityf) == 0) {
                if(this.mRecyclerView.mAdapter == null) {
                    return false;
                }
                switch(v) {
                    case 0x1000: {
                        this.mRecyclerView.smoothScrollToPosition(this.mRecyclerView.mAdapter.getItemCount() - 1);
                        return true;
                    }
                    case 0x2000: {
                        this.mRecyclerView.smoothScrollToPosition(0);
                        return true;
                    }
                    default: {
                        return true;
                    }
                }
            }
            if(Float.compare(1.0f, f) != 0 && Float.compare(0.0f, f) != 0) {
                v4 = (int)(((float)v4) * f);
                v3 = (int)(((float)v3) * f);
            }
            this.mRecyclerView.smoothScrollBy(v4, v3, null, 0x80000000, true);
            return true;
        }

        boolean performAccessibilityActionForItem(View view0, int v, Bundle bundle0) {
            return false;
        }

        public boolean performAccessibilityActionForItem(Recycler recyclerView$Recycler0, State recyclerView$State0, View view0, int v, Bundle bundle0) [...] // Inlined contents

        public void postOnAnimation(Runnable runnable0) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null) {
                ViewCompat.postOnAnimation(recyclerView0, runnable0);
            }
        }

        public void removeAllViews() {
            for(int v = this.getChildCount() - 1; v >= 0; --v) {
                this.mChildHelper.removeViewAt(v);
            }
        }

        public void removeAndRecycleAllViews(Recycler recyclerView$Recycler0) {
            for(int v = this.getChildCount() - 1; v >= 0; --v) {
                if(!RecyclerView.getChildViewHolderInt(this.getChildAt(v)).shouldIgnore()) {
                    this.removeAndRecycleViewAt(v, recyclerView$Recycler0);
                }
            }
        }

        void removeAndRecycleScrapInt(Recycler recyclerView$Recycler0) {
            int v = recyclerView$Recycler0.getScrapCount();
            for(int v1 = v - 1; v1 >= 0; --v1) {
                View view0 = recyclerView$Recycler0.getScrapViewAt(v1);
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
                if(!recyclerView$ViewHolder0.shouldIgnore()) {
                    recyclerView$ViewHolder0.setIsRecyclable(false);
                    if(recyclerView$ViewHolder0.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(view0, false);
                    }
                    if(this.mRecyclerView.mItemAnimator != null) {
                        this.mRecyclerView.mItemAnimator.endAnimation(recyclerView$ViewHolder0);
                    }
                    recyclerView$ViewHolder0.setIsRecyclable(true);
                    recyclerView$Recycler0.quickRecycleScrapView(view0);
                }
            }
            recyclerView$Recycler0.clearScrap();
            if(v > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public void removeAndRecycleView(View view0, Recycler recyclerView$Recycler0) {
            this.removeView(view0);
            recyclerView$Recycler0.recycleView(view0);
        }

        public void removeAndRecycleViewAt(int v, Recycler recyclerView$Recycler0) {
            View view0 = this.getChildAt(v);
            this.removeViewAt(v);
            recyclerView$Recycler0.recycleView(view0);
        }

        public boolean removeCallbacks(Runnable runnable0) {
            return this.mRecyclerView == null ? false : this.mRecyclerView.removeCallbacks(runnable0);
        }

        public void removeDetachedView(View view0) {
            this.mRecyclerView.removeDetachedView(view0, false);
        }

        public void removeView(View view0) {
            this.mChildHelper.removeView(view0);
        }

        public void removeViewAt(int v) {
            if(this.getChildAt(v) != null) {
                this.mChildHelper.removeViewAt(v);
            }
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView0, View view0, Rect rect0, boolean z) {
            return this.requestChildRectangleOnScreen(recyclerView0, view0, rect0, z, false);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView0, View view0, Rect rect0, boolean z, boolean z1) {
            int[] arr_v = this.getChildRectangleOnScreenScrollAmount(view0, rect0);
            int v = arr_v[0];
            int v1 = arr_v[1];
            if(z1 && !this.isFocusedChildVisibleAfterScrolling(recyclerView0, v, v1) || v == 0 && v1 == 0) {
                return false;
            }
            if(z) {
                recyclerView0.scrollBy(v, v1);
                return true;
            }
            recyclerView0.smoothScrollBy(v, v1);
            return true;
        }

        public void requestLayout() {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(recyclerView0 != null) {
                recyclerView0.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        private void scrapOrRecycleView(Recycler recyclerView$Recycler0, int v, View view0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(recyclerView$ViewHolder0.shouldIgnore()) {
                if(RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("RecyclerView", "ignoring view " + recyclerView$ViewHolder0);
                }
                return;
            }
            if(recyclerView$ViewHolder0.isInvalid() && !recyclerView$ViewHolder0.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
                this.removeViewAt(v);
                recyclerView$Recycler0.recycleViewHolderInternal(recyclerView$ViewHolder0);
                return;
            }
            this.detachViewAt(v);
            recyclerView$Recycler0.scrapView(view0);
            this.mRecyclerView.mViewInfoStore.onViewDetached(recyclerView$ViewHolder0);
        }

        public int scrollHorizontallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
            return 0;
        }

        public void scrollToPosition(int v) {
            if(RecyclerView.sVerboseLoggingEnabled) {
                Log.e("RecyclerView", "You MUST implement scrollToPosition. It will soon become abstract");
            }
        }

        public int scrollVerticallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
            return 0;
        }

        @Deprecated
        public void setAutoMeasureEnabled(boolean z) {
            this.mAutoMeasure = z;
        }

        void setExactMeasureSpecsFrom(RecyclerView recyclerView0) {
            this.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView0.getWidth(), 0x40000000), View.MeasureSpec.makeMeasureSpec(recyclerView0.getHeight(), 0x40000000));
        }

        public final void setItemPrefetchEnabled(boolean z) {
            if(z != this.mItemPrefetchEnabled) {
                this.mItemPrefetchEnabled = z;
                this.mPrefetchMaxCountObserved = 0;
                RecyclerView recyclerView0 = this.mRecyclerView;
                if(recyclerView0 != null) {
                    recyclerView0.mRecycler.updateViewCacheSize();
                }
            }
        }

        void setMeasureSpecs(int v, int v1) {
            this.mWidth = View.MeasureSpec.getSize(v);
            int v2 = View.MeasureSpec.getMode(v);
            this.mWidthMode = v2;
            if(v2 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(v1);
            int v3 = View.MeasureSpec.getMode(v1);
            this.mHeightMode = v3;
            if(v3 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }

        public void setMeasuredDimension(int v, int v1) {
            this.mRecyclerView.setMeasuredDimension(v, v1);
        }

        public void setMeasuredDimension(Rect rect0, int v, int v1) {
            int v2 = rect0.width();
            int v3 = this.getPaddingLeft();
            int v4 = this.getPaddingRight();
            int v5 = rect0.height();
            int v6 = this.getPaddingTop();
            int v7 = this.getPaddingBottom();
            this.setMeasuredDimension(LayoutManager.chooseSize(v, v2 + v3 + v4, this.getMinimumWidth()), LayoutManager.chooseSize(v1, v5 + v6 + v7, this.getMinimumHeight()));
        }

        void setMeasuredDimensionFromChildren(int v, int v1) {
            int v2 = this.getChildCount();
            if(v2 == 0) {
                this.mRecyclerView.defaultOnMeasure(v, v1);
                return;
            }
            int v3 = 0x80000000;
            int v4 = 0x80000000;
            int v5 = 0x7FFFFFFF;
            int v6 = 0x7FFFFFFF;
            for(int v7 = 0; v7 < v2; ++v7) {
                View view0 = this.getChildAt(v7);
                Rect rect0 = this.mRecyclerView.mTempRect;
                this.getDecoratedBoundsWithMargins(view0, rect0);
                if(rect0.left < v5) {
                    v5 = rect0.left;
                }
                if(rect0.right > v3) {
                    v3 = rect0.right;
                }
                if(rect0.top < v6) {
                    v6 = rect0.top;
                }
                if(rect0.bottom > v4) {
                    v4 = rect0.bottom;
                }
            }
            this.mRecyclerView.mTempRect.set(v5, v6, v3, v4);
            this.setMeasuredDimension(this.mRecyclerView.mTempRect, v, v1);
        }

        public void setMeasurementCacheEnabled(boolean z) {
            this.mMeasurementCacheEnabled = z;
        }

        void setRecyclerView(RecyclerView recyclerView0) {
            if(recyclerView0 == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            }
            else {
                this.mRecyclerView = recyclerView0;
                this.mChildHelper = recyclerView0.mChildHelper;
                this.mWidth = recyclerView0.getWidth();
                this.mHeight = recyclerView0.getHeight();
            }
            this.mWidthMode = 0x40000000;
            this.mHeightMode = 0x40000000;
        }

        // 去混淆评级： 低(40)
        boolean shouldMeasureChild(View view0, int v, int v1, LayoutParams recyclerView$LayoutParams0) {
            return view0.isLayoutRequested() || !this.mMeasurementCacheEnabled || !LayoutManager.isMeasurementUpToDate(view0.getWidth(), v, recyclerView$LayoutParams0.width) || !LayoutManager.isMeasurementUpToDate(view0.getHeight(), v1, recyclerView$LayoutParams0.height);
        }

        boolean shouldMeasureTwice() {
            return false;
        }

        // 去混淆评级： 低(30)
        boolean shouldReMeasureChild(View view0, int v, int v1, LayoutParams recyclerView$LayoutParams0) {
            return !this.mMeasurementCacheEnabled || !LayoutManager.isMeasurementUpToDate(view0.getMeasuredWidth(), v, recyclerView$LayoutParams0.width) || !LayoutManager.isMeasurementUpToDate(view0.getMeasuredHeight(), v1, recyclerView$LayoutParams0.height);
        }

        public void smoothScrollToPosition(RecyclerView recyclerView0, State recyclerView$State0, int v) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller recyclerView$SmoothScroller0) {
            if(this.mSmoothScroller != null && recyclerView$SmoothScroller0 != this.mSmoothScroller && this.mSmoothScroller.isRunning()) {
                this.mSmoothScroller.stop();
            }
            this.mSmoothScroller = recyclerView$SmoothScroller0;
            recyclerView$SmoothScroller0.start(this.mRecyclerView, this);
        }

        public void stopIgnoringView(View view0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            recyclerView$ViewHolder0.stopIgnoring();
            recyclerView$ViewHolder0.resetInternal();
            recyclerView$ViewHolder0.addFlags(4);
        }

        void stopSmoothScroller() {
            SmoothScroller recyclerView$SmoothScroller0 = this.mSmoothScroller;
            if(recyclerView$SmoothScroller0 != null) {
                recyclerView$SmoothScroller0.stop();
            }
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        final Rect mDecorInsets;
        boolean mInsetsDirty;
        boolean mPendingInvalidate;
        ViewHolder mViewHolder;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams recyclerView$LayoutParams0) {
            super(recyclerView$LayoutParams0);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public int getAbsoluteAdapterPosition() {
            return this.mViewHolder.getAbsoluteAdapterPosition();
        }

        public int getBindingAdapterPosition() {
            return this.mViewHolder.getBindingAdapterPosition();
        }

        @Deprecated
        public int getViewAdapterPosition() {
            return this.mViewHolder.getBindingAdapterPosition();
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        @Deprecated
        public int getViewPosition() {
            return this.mViewHolder.getPosition();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }

        public boolean viewNeedsUpdate() {
            return this.mViewHolder.needsUpdate();
        }
    }

    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(View arg1);

        void onChildViewDetachedFromWindow(View arg1);
    }

    public static abstract class OnFlingListener {
        public abstract boolean onFling(int arg1, int arg2);
    }

    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView arg1, MotionEvent arg2);

        void onRequestDisallowInterceptTouchEvent(boolean arg1);

        void onTouchEvent(RecyclerView arg1, MotionEvent arg2);
    }

    public static abstract class OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView0, int v) {
        }

        public void onScrolled(RecyclerView recyclerView0, int v, int v1) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public static class RecycledViewPool {
        static class ScrapData {
            long mBindRunningAverageNs;
            long mCreateRunningAverageNs;
            int mMaxScrap;
            final ArrayList mScrapHeap;

            ScrapData() {
                this.mScrapHeap = new ArrayList();
                this.mMaxScrap = 5;
                this.mCreateRunningAverageNs = 0L;
                this.mBindRunningAverageNs = 0L;
            }
        }

        private static final int DEFAULT_MAX_SCRAP = 5;
        int mAttachCountForClearing;
        Set mAttachedAdaptersForPoolingContainer;
        SparseArray mScrap;

        public RecycledViewPool() {
            this.mScrap = new SparseArray();
            this.mAttachCountForClearing = 0;
            this.mAttachedAdaptersForPoolingContainer = Collections.newSetFromMap(new IdentityHashMap());
        }

        void attach() {
            ++this.mAttachCountForClearing;
        }

        void attachForPoolingContainer(Adapter recyclerView$Adapter0) {
            this.mAttachedAdaptersForPoolingContainer.add(recyclerView$Adapter0);
        }

        public void clear() {
            for(int v = 0; v < this.mScrap.size(); ++v) {
                ScrapData recyclerView$RecycledViewPool$ScrapData0 = (ScrapData)this.mScrap.valueAt(v);
                for(Object object0: recyclerView$RecycledViewPool$ScrapData0.mScrapHeap) {
                    PoolingContainer.callPoolingContainerOnRelease(((ViewHolder)object0).itemView);
                }
                recyclerView$RecycledViewPool$ScrapData0.mScrapHeap.clear();
            }
        }

        void detach() {
            --this.mAttachCountForClearing;
        }

        void detachForPoolingContainer(Adapter recyclerView$Adapter0, boolean z) {
            this.mAttachedAdaptersForPoolingContainer.remove(recyclerView$Adapter0);
            if(this.mAttachedAdaptersForPoolingContainer.size() == 0 && !z) {
                for(int v = 0; v < this.mScrap.size(); ++v) {
                    ArrayList arrayList0 = ((ScrapData)this.mScrap.get(this.mScrap.keyAt(v))).mScrapHeap;
                    for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
                        PoolingContainer.callPoolingContainerOnRelease(((ViewHolder)arrayList0.get(v1)).itemView);
                    }
                }
            }
        }

        void factorInBindTime(int v, long v1) {
            ScrapData recyclerView$RecycledViewPool$ScrapData0 = this.getScrapDataForType(v);
            recyclerView$RecycledViewPool$ScrapData0.mBindRunningAverageNs = this.runningAverage(recyclerView$RecycledViewPool$ScrapData0.mBindRunningAverageNs, v1);
        }

        void factorInCreateTime(int v, long v1) {
            ScrapData recyclerView$RecycledViewPool$ScrapData0 = this.getScrapDataForType(v);
            recyclerView$RecycledViewPool$ScrapData0.mCreateRunningAverageNs = this.runningAverage(recyclerView$RecycledViewPool$ScrapData0.mCreateRunningAverageNs, v1);
        }

        public ViewHolder getRecycledView(int v) {
            ScrapData recyclerView$RecycledViewPool$ScrapData0 = (ScrapData)this.mScrap.get(v);
            if(recyclerView$RecycledViewPool$ScrapData0 != null && !recyclerView$RecycledViewPool$ScrapData0.mScrapHeap.isEmpty()) {
                ArrayList arrayList0 = recyclerView$RecycledViewPool$ScrapData0.mScrapHeap;
                for(int v1 = arrayList0.size() - 1; v1 >= 0; --v1) {
                    if(!((ViewHolder)arrayList0.get(v1)).isAttachedToTransitionOverlay()) {
                        return (ViewHolder)arrayList0.remove(v1);
                    }
                }
            }
            return null;
        }

        public int getRecycledViewCount(int v) {
            return this.getScrapDataForType(v).mScrapHeap.size();
        }

        private ScrapData getScrapDataForType(int v) {
            ScrapData recyclerView$RecycledViewPool$ScrapData0 = (ScrapData)this.mScrap.get(v);
            if(recyclerView$RecycledViewPool$ScrapData0 == null) {
                recyclerView$RecycledViewPool$ScrapData0 = new ScrapData();
                this.mScrap.put(v, recyclerView$RecycledViewPool$ScrapData0);
            }
            return recyclerView$RecycledViewPool$ScrapData0;
        }

        void onAdapterChanged(Adapter recyclerView$Adapter0, Adapter recyclerView$Adapter1, boolean z) {
            if(recyclerView$Adapter0 != null) {
                this.detach();
            }
            if(!z && this.mAttachCountForClearing == 0) {
                this.clear();
            }
            if(recyclerView$Adapter1 != null) {
                this.attach();
            }
        }

        public void putRecycledView(ViewHolder recyclerView$ViewHolder0) {
            int v = recyclerView$ViewHolder0.getItemViewType();
            ArrayList arrayList0 = this.getScrapDataForType(v).mScrapHeap;
            if(((ScrapData)this.mScrap.get(v)).mMaxScrap <= arrayList0.size()) {
                PoolingContainer.callPoolingContainerOnRelease(recyclerView$ViewHolder0.itemView);
                return;
            }
            if(RecyclerView.sDebugAssertionsEnabled && arrayList0.contains(recyclerView$ViewHolder0)) {
                throw new IllegalArgumentException("this scrap item already exists");
            }
            recyclerView$ViewHolder0.resetInternal();
            arrayList0.add(recyclerView$ViewHolder0);
        }

        long runningAverage(long v, long v1) {
            return v == 0L ? v1 : v / 4L * 3L + v1 / 4L;
        }

        public void setMaxRecycledViews(int v, int v1) {
            ScrapData recyclerView$RecycledViewPool$ScrapData0 = this.getScrapDataForType(v);
            recyclerView$RecycledViewPool$ScrapData0.mMaxScrap = v1;
            ArrayList arrayList0 = recyclerView$RecycledViewPool$ScrapData0.mScrapHeap;
            while(arrayList0.size() > v1) {
                arrayList0.remove(arrayList0.size() - 1);
            }
        }

        int size() {
            int v1 = 0;
            for(int v = 0; v < this.mScrap.size(); ++v) {
                ArrayList arrayList0 = ((ScrapData)this.mScrap.valueAt(v)).mScrapHeap;
                if(arrayList0 != null) {
                    v1 += arrayList0.size();
                }
            }
            return v1;
        }

        boolean willBindInTime(int v, long v1, long v2) {
            long v3 = this.getScrapDataForType(v).mBindRunningAverageNs;
            return v3 == 0L || v1 + v3 < v2;
        }

        boolean willCreateInTime(int v, long v1, long v2) {
            long v3 = this.getScrapDataForType(v).mCreateRunningAverageNs;
            return v3 == 0L || v1 + v3 < v2;
        }
    }

    public final class Recycler {
        static final int DEFAULT_CACHE_SIZE = 2;
        final ArrayList mAttachedScrap;
        final ArrayList mCachedViews;
        ArrayList mChangedScrap;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        int mViewCacheMax;

        public Recycler() {
            ArrayList arrayList0 = new ArrayList();
            this.mAttachedScrap = arrayList0;
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList0);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        void addViewHolderToRecycledViewPool(ViewHolder recyclerView$ViewHolder0, boolean z) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(recyclerView$ViewHolder0);
            View view0 = recyclerView$ViewHolder0.itemView;
            if(RecyclerView.this.mAccessibilityDelegate != null) {
                AccessibilityDelegateCompat accessibilityDelegateCompat0 = RecyclerView.this.mAccessibilityDelegate.getItemDelegate();
                ViewCompat.setAccessibilityDelegate(view0, (accessibilityDelegateCompat0 instanceof ItemDelegate ? ((ItemDelegate)accessibilityDelegateCompat0).getAndRemoveOriginalDelegateForItem(view0) : null));
            }
            if(z) {
                this.dispatchViewRecycled(recyclerView$ViewHolder0);
            }
            recyclerView$ViewHolder0.mBindingAdapter = null;
            recyclerView$ViewHolder0.mOwnerRecyclerView = null;
            this.getRecycledViewPool().putRecycledView(recyclerView$ViewHolder0);
        }

        private void attachAccessibilityDelegateOnBind(ViewHolder recyclerView$ViewHolder0) {
            if(RecyclerView.this.isAccessibilityEnabled()) {
                View view0 = recyclerView$ViewHolder0.itemView;
                if(view0.getImportantForAccessibility() == 0) {
                    view0.setImportantForAccessibility(1);
                }
                if(RecyclerView.this.mAccessibilityDelegate != null) {
                    AccessibilityDelegateCompat accessibilityDelegateCompat0 = RecyclerView.this.mAccessibilityDelegate.getItemDelegate();
                    if(accessibilityDelegateCompat0 instanceof ItemDelegate) {
                        ((ItemDelegate)accessibilityDelegateCompat0).saveOriginalDelegate(view0);
                    }
                    ViewCompat.setAccessibilityDelegate(view0, accessibilityDelegateCompat0);
                }
            }
        }

        public void bindViewToPosition(View view0, int v) {
            LayoutParams recyclerView$LayoutParams0;
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(recyclerView$ViewHolder0 == null) {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter" + RecyclerView.this.exceptionLabel());
            }
            int v1 = RecyclerView.this.mAdapterHelper.findPositionOffset(v);
            if(v1 < 0 || v1 >= RecyclerView.this.mAdapter.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + v + "(offset:" + v1 + ").state:" + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
            }
            this.tryBindViewHolderByDeadline(recyclerView$ViewHolder0, v1, v, 0x7FFFFFFFFFFFFFFFL);
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = recyclerView$ViewHolder0.itemView.getLayoutParams();
            if(viewGroup$LayoutParams0 == null) {
                recyclerView$LayoutParams0 = (LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                recyclerView$ViewHolder0.itemView.setLayoutParams(recyclerView$LayoutParams0);
            }
            else if(RecyclerView.this.checkLayoutParams(viewGroup$LayoutParams0)) {
                recyclerView$LayoutParams0 = (LayoutParams)viewGroup$LayoutParams0;
            }
            else {
                recyclerView$LayoutParams0 = (LayoutParams)RecyclerView.this.generateLayoutParams(viewGroup$LayoutParams0);
                recyclerView$ViewHolder0.itemView.setLayoutParams(recyclerView$LayoutParams0);
            }
            boolean z = true;
            recyclerView$LayoutParams0.mInsetsDirty = true;
            recyclerView$LayoutParams0.mViewHolder = recyclerView$ViewHolder0;
            if(recyclerView$ViewHolder0.itemView.getParent() != null) {
                z = false;
            }
            recyclerView$LayoutParams0.mPendingInvalidate = z;
        }

        public void clear() {
            this.mAttachedScrap.clear();
            this.recycleAndClearCachedViews();
        }

        void clearOldPositions() {
            int v = this.mCachedViews.size();
            for(int v2 = 0; v2 < v; ++v2) {
                ((ViewHolder)this.mCachedViews.get(v2)).clearOldPosition();
            }
            int v3 = this.mAttachedScrap.size();
            for(int v4 = 0; v4 < v3; ++v4) {
                ((ViewHolder)this.mAttachedScrap.get(v4)).clearOldPosition();
            }
            ArrayList arrayList0 = this.mChangedScrap;
            if(arrayList0 != null) {
                int v5 = arrayList0.size();
                for(int v1 = 0; v1 < v5; ++v1) {
                    ((ViewHolder)this.mChangedScrap.get(v1)).clearOldPosition();
                }
            }
        }

        void clearScrap() {
            this.mAttachedScrap.clear();
            ArrayList arrayList0 = this.mChangedScrap;
            if(arrayList0 != null) {
                arrayList0.clear();
            }
        }

        public int convertPreLayoutPositionToPostLayout(int v) {
            if(v < 0 || v >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("invalid position " + v + ". State item count is " + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
            }
            return RecyclerView.this.mState.isPreLayout() ? RecyclerView.this.mAdapterHelper.findPositionOffset(v) : v;
        }

        void dispatchViewRecycled(ViewHolder recyclerView$ViewHolder0) {
            if(RecyclerView.this.mRecyclerListener != null) {
                RecyclerView.this.mRecyclerListener.onViewRecycled(recyclerView$ViewHolder0);
            }
            int v = RecyclerView.this.mRecyclerListeners.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((RecyclerListener)RecyclerView.this.mRecyclerListeners.get(v1)).onViewRecycled(recyclerView$ViewHolder0);
            }
            if(RecyclerView.this.mAdapter != null) {
                RecyclerView.this.mAdapter.onViewRecycled(recyclerView$ViewHolder0);
            }
            if(RecyclerView.this.mState != null) {
                RecyclerView.this.mViewInfoStore.removeViewHolder(recyclerView$ViewHolder0);
            }
            if(RecyclerView.sVerboseLoggingEnabled) {
                Log.d("RecyclerView", "dispatchViewRecycled: " + recyclerView$ViewHolder0);
            }
        }

        ViewHolder getChangedScrapViewForPosition(int v) {
            ArrayList arrayList0 = this.mChangedScrap;
            if(arrayList0 != null) {
                int v1 = arrayList0.size();
                if(v1 != 0) {
                    for(int v3 = 0; v3 < v1; ++v3) {
                        ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mChangedScrap.get(v3);
                        if(!recyclerView$ViewHolder0.wasReturnedFromScrap() && recyclerView$ViewHolder0.getLayoutPosition() == v) {
                            recyclerView$ViewHolder0.addFlags(0x20);
                            return recyclerView$ViewHolder0;
                        }
                    }
                    if(RecyclerView.this.mAdapter.hasStableIds()) {
                        int v4 = RecyclerView.this.mAdapterHelper.findPositionOffset(v);
                        if(v4 > 0 && v4 < RecyclerView.this.mAdapter.getItemCount()) {
                            long v5 = RecyclerView.this.mAdapter.getItemId(v4);
                            for(int v2 = 0; v2 < v1; ++v2) {
                                ViewHolder recyclerView$ViewHolder1 = (ViewHolder)this.mChangedScrap.get(v2);
                                if(!recyclerView$ViewHolder1.wasReturnedFromScrap() && recyclerView$ViewHolder1.getItemId() == v5) {
                                    recyclerView$ViewHolder1.addFlags(0x20);
                                    return recyclerView$ViewHolder1;
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        RecycledViewPool getRecycledViewPool() {
            if(this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
                this.maybeSendPoolingContainerAttach();
            }
            return this.mRecyclerPool;
        }

        int getScrapCount() {
            return this.mAttachedScrap.size();
        }

        public List getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        ViewHolder getScrapOrCachedViewForId(long v, int v1, boolean z) {
            for(int v2 = this.mAttachedScrap.size() - 1; v2 >= 0; --v2) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mAttachedScrap.get(v2);
                if(recyclerView$ViewHolder0.getItemId() == v && !recyclerView$ViewHolder0.wasReturnedFromScrap()) {
                    if(v1 == recyclerView$ViewHolder0.getItemViewType()) {
                        recyclerView$ViewHolder0.addFlags(0x20);
                        if(recyclerView$ViewHolder0.isRemoved() && !RecyclerView.this.mState.isPreLayout()) {
                            recyclerView$ViewHolder0.setFlags(2, 14);
                        }
                        return recyclerView$ViewHolder0;
                    }
                    if(!z) {
                        this.mAttachedScrap.remove(v2);
                        RecyclerView.this.removeDetachedView(recyclerView$ViewHolder0.itemView, false);
                        this.quickRecycleScrapView(recyclerView$ViewHolder0.itemView);
                    }
                }
            }
            for(int v3 = this.mCachedViews.size() - 1; v3 >= 0; --v3) {
                ViewHolder recyclerView$ViewHolder1 = (ViewHolder)this.mCachedViews.get(v3);
                if(recyclerView$ViewHolder1.getItemId() == v && !recyclerView$ViewHolder1.isAttachedToTransitionOverlay()) {
                    if(v1 == recyclerView$ViewHolder1.getItemViewType()) {
                        if(!z) {
                            this.mCachedViews.remove(v3);
                        }
                        return recyclerView$ViewHolder1;
                    }
                    if(!z) {
                        this.recycleCachedViewAt(v3);
                        return null;
                    }
                }
            }
            return null;
        }

        ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int v, boolean z) {
            int v1 = this.mAttachedScrap.size();
            for(int v3 = 0; v3 < v1; ++v3) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mAttachedScrap.get(v3);
                if(!recyclerView$ViewHolder0.wasReturnedFromScrap() && recyclerView$ViewHolder0.getLayoutPosition() == v && !recyclerView$ViewHolder0.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !recyclerView$ViewHolder0.isRemoved())) {
                    recyclerView$ViewHolder0.addFlags(0x20);
                    return recyclerView$ViewHolder0;
                }
            }
            if(!z) {
                View view0 = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(v);
                if(view0 != null) {
                    ViewHolder recyclerView$ViewHolder1 = RecyclerView.getChildViewHolderInt(view0);
                    RecyclerView.this.mChildHelper.unhide(view0);
                    int v4 = RecyclerView.this.mChildHelper.indexOfChild(view0);
                    if(v4 == -1) {
                        throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + recyclerView$ViewHolder1 + RecyclerView.this.exceptionLabel());
                    }
                    RecyclerView.this.mChildHelper.detachViewFromParent(v4);
                    this.scrapView(view0);
                    recyclerView$ViewHolder1.addFlags(0x2020);
                    return recyclerView$ViewHolder1;
                }
            }
            int v5 = this.mCachedViews.size();
            for(int v2 = 0; v2 < v5; ++v2) {
                ViewHolder recyclerView$ViewHolder2 = (ViewHolder)this.mCachedViews.get(v2);
                if(!recyclerView$ViewHolder2.isInvalid() && recyclerView$ViewHolder2.getLayoutPosition() == v && !recyclerView$ViewHolder2.isAttachedToTransitionOverlay()) {
                    if(!z) {
                        this.mCachedViews.remove(v2);
                    }
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "getScrapOrHiddenOrCachedHolderForPosition(" + v + ") found match in cache: " + recyclerView$ViewHolder2);
                    }
                    return recyclerView$ViewHolder2;
                }
            }
            return null;
        }

        View getScrapViewAt(int v) {
            return ((ViewHolder)this.mAttachedScrap.get(v)).itemView;
        }

        public View getViewForPosition(int v) {
            return this.getViewForPosition(v, false);
        }

        View getViewForPosition(int v, boolean z) {
            return this.tryGetViewHolderForPositionByDeadline(v, z, 0x7FFFFFFFFFFFFFFFL).itemView;
        }

        private void invalidateDisplayListInt(ViewGroup viewGroup0, boolean z) {
            for(int v = viewGroup0.getChildCount() - 1; v >= 0; --v) {
                View view0 = viewGroup0.getChildAt(v);
                if(view0 instanceof ViewGroup) {
                    this.invalidateDisplayListInt(((ViewGroup)view0), true);
                }
            }
            if(!z) {
                return;
            }
            if(viewGroup0.getVisibility() == 4) {
                viewGroup0.setVisibility(0);
                viewGroup0.setVisibility(4);
                return;
            }
            int v1 = viewGroup0.getVisibility();
            viewGroup0.setVisibility(4);
            viewGroup0.setVisibility(v1);
        }

        private void invalidateDisplayListInt(ViewHolder recyclerView$ViewHolder0) {
            if(recyclerView$ViewHolder0.itemView instanceof ViewGroup) {
                this.invalidateDisplayListInt(((ViewGroup)recyclerView$ViewHolder0.itemView), false);
            }
        }

        void markItemDecorInsetsDirty() {
            int v = this.mCachedViews.size();
            for(int v1 = 0; v1 < v; ++v1) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)((ViewHolder)this.mCachedViews.get(v1)).itemView.getLayoutParams();
                if(recyclerView$LayoutParams0 != null) {
                    recyclerView$LayoutParams0.mInsetsDirty = true;
                }
            }
        }

        void markKnownViewsInvalid() {
            int v = this.mCachedViews.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mCachedViews.get(v1);
                if(recyclerView$ViewHolder0 != null) {
                    recyclerView$ViewHolder0.addFlags(6);
                    recyclerView$ViewHolder0.addChangePayload(null);
                }
            }
            if(RecyclerView.this.mAdapter != null && RecyclerView.this.mAdapter.hasStableIds()) {
                return;
            }
            this.recycleAndClearCachedViews();
        }

        private void maybeSendPoolingContainerAttach() {
            if(this.mRecyclerPool != null && RecyclerView.this.mAdapter != null && RecyclerView.this.isAttachedToWindow()) {
                this.mRecyclerPool.attachForPoolingContainer(RecyclerView.this.mAdapter);
            }
        }

        void offsetPositionRecordsForInsert(int v, int v1) {
            int v2 = this.mCachedViews.size();
            for(int v3 = 0; v3 < v2; ++v3) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mCachedViews.get(v3);
                if(recyclerView$ViewHolder0 != null && recyclerView$ViewHolder0.mPosition >= v) {
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "offsetPositionRecordsForInsert cached " + v3 + " holder " + recyclerView$ViewHolder0 + " now at position " + (recyclerView$ViewHolder0.mPosition + v1));
                    }
                    recyclerView$ViewHolder0.offsetPosition(v1, false);
                }
            }
        }

        void offsetPositionRecordsForMove(int v, int v1) {
            int v4;
            int v3;
            int v2;
            if(v < v1) {
                v2 = -1;
                v3 = v;
                v4 = v1;
            }
            else {
                v2 = 1;
                v4 = v;
                v3 = v1;
            }
            int v5 = this.mCachedViews.size();
            for(int v6 = 0; v6 < v5; ++v6) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mCachedViews.get(v6);
                if(recyclerView$ViewHolder0 != null && recyclerView$ViewHolder0.mPosition >= v3 && recyclerView$ViewHolder0.mPosition <= v4) {
                    if(recyclerView$ViewHolder0.mPosition == v) {
                        recyclerView$ViewHolder0.offsetPosition(v1 - v, false);
                    }
                    else {
                        recyclerView$ViewHolder0.offsetPosition(v2, false);
                    }
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "offsetPositionRecordsForMove cached child " + v6 + " holder " + recyclerView$ViewHolder0);
                    }
                }
            }
        }

        void offsetPositionRecordsForRemove(int v, int v1, boolean z) {
            for(int v2 = this.mCachedViews.size() - 1; v2 >= 0; --v2) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mCachedViews.get(v2);
                if(recyclerView$ViewHolder0 != null) {
                    if(recyclerView$ViewHolder0.mPosition >= v + v1) {
                        if(RecyclerView.sVerboseLoggingEnabled) {
                            Log.d("RecyclerView", "offsetPositionRecordsForRemove cached " + v2 + " holder " + recyclerView$ViewHolder0 + " now at position " + (recyclerView$ViewHolder0.mPosition - v1));
                        }
                        recyclerView$ViewHolder0.offsetPosition(-v1, z);
                    }
                    else if(recyclerView$ViewHolder0.mPosition >= v) {
                        recyclerView$ViewHolder0.addFlags(8);
                        this.recycleCachedViewAt(v2);
                    }
                }
            }
        }

        void onAdapterChanged(Adapter recyclerView$Adapter0, Adapter recyclerView$Adapter1, boolean z) {
            this.clear();
            this.poolingContainerDetach(recyclerView$Adapter0, true);
            this.getRecycledViewPool().onAdapterChanged(recyclerView$Adapter0, recyclerView$Adapter1, z);
            this.maybeSendPoolingContainerAttach();
        }

        void onAttachedToWindow() {
            this.maybeSendPoolingContainerAttach();
        }

        void onDetachedFromWindow() {
            for(int v = 0; v < this.mCachedViews.size(); ++v) {
                PoolingContainer.callPoolingContainerOnRelease(((ViewHolder)this.mCachedViews.get(v)).itemView);
            }
            this.poolingContainerDetach(RecyclerView.this.mAdapter);
        }

        private void poolingContainerDetach(Adapter recyclerView$Adapter0) {
            this.poolingContainerDetach(recyclerView$Adapter0, false);
        }

        private void poolingContainerDetach(Adapter recyclerView$Adapter0, boolean z) {
            RecycledViewPool recyclerView$RecycledViewPool0 = this.mRecyclerPool;
            if(recyclerView$RecycledViewPool0 != null) {
                recyclerView$RecycledViewPool0.detachForPoolingContainer(recyclerView$Adapter0, z);
            }
        }

        void quickRecycleScrapView(View view0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            recyclerView$ViewHolder0.mScrapContainer = null;
            recyclerView$ViewHolder0.mInChangeScrap = false;
            recyclerView$ViewHolder0.clearReturnedFromScrapFlag();
            this.recycleViewHolderInternal(recyclerView$ViewHolder0);
        }

        void recycleAndClearCachedViews() {
            for(int v = this.mCachedViews.size() - 1; v >= 0; --v) {
                this.recycleCachedViewAt(v);
            }
            this.mCachedViews.clear();
            if(RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
            }
        }

        void recycleCachedViewAt(int v) {
            if(RecyclerView.sVerboseLoggingEnabled) {
                Log.d("RecyclerView", "Recycling cached view at index " + v);
            }
            ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mCachedViews.get(v);
            if(RecyclerView.sVerboseLoggingEnabled) {
                Log.d("RecyclerView", "CachedViewHolder to be recycled: " + recyclerView$ViewHolder0);
            }
            this.addViewHolderToRecycledViewPool(recyclerView$ViewHolder0, true);
            this.mCachedViews.remove(v);
        }

        public void recycleView(View view0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(recyclerView$ViewHolder0.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view0, false);
            }
            if(recyclerView$ViewHolder0.isScrap()) {
                recyclerView$ViewHolder0.unScrap();
            }
            else if(recyclerView$ViewHolder0.wasReturnedFromScrap()) {
                recyclerView$ViewHolder0.clearReturnedFromScrapFlag();
            }
            this.recycleViewHolderInternal(recyclerView$ViewHolder0);
            if(RecyclerView.this.mItemAnimator != null && !recyclerView$ViewHolder0.isRecyclable()) {
                RecyclerView.this.mItemAnimator.endAnimation(recyclerView$ViewHolder0);
            }
        }

        void recycleViewHolderInternal(ViewHolder recyclerView$ViewHolder0) {
            boolean z4;
            boolean z = false;
            boolean z1 = true;
            if(!recyclerView$ViewHolder0.isScrap() && recyclerView$ViewHolder0.itemView.getParent() == null) {
                if(recyclerView$ViewHolder0.isTmpDetached()) {
                    throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + recyclerView$ViewHolder0 + RecyclerView.this.exceptionLabel());
                }
                if(recyclerView$ViewHolder0.shouldIgnore()) {
                    throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.exceptionLabel());
                }
                boolean z2 = recyclerView$ViewHolder0.doesTransientStatePreventRecycling();
                boolean z3 = RecyclerView.this.mAdapter != null && z2 && RecyclerView.this.mAdapter.onFailedToRecycleView(recyclerView$ViewHolder0);
                if(RecyclerView.sDebugAssertionsEnabled && this.mCachedViews.contains(recyclerView$ViewHolder0)) {
                    throw new IllegalArgumentException("cached view received recycle internal? " + recyclerView$ViewHolder0 + RecyclerView.this.exceptionLabel());
                }
                if(z3 || recyclerView$ViewHolder0.isRecyclable()) {
                    if(this.mViewCacheMax <= 0 || recyclerView$ViewHolder0.hasAnyOfTheFlags(0x20E)) {
                        z4 = false;
                    }
                    else {
                        int v = this.mCachedViews.size();
                        if(v >= this.mViewCacheMax && v > 0) {
                            this.recycleCachedViewAt(0);
                            --v;
                        }
                        if(RecyclerView.ALLOW_THREAD_GAP_WORK && v > 0 && !RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(recyclerView$ViewHolder0.mPosition)) {
                            int v1;
                            for(v1 = v - 1; v1 >= 0; --v1) {
                                int v2 = ((ViewHolder)this.mCachedViews.get(v1)).mPosition;
                                if(!RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(v2)) {
                                    break;
                                }
                            }
                            v = v1 + 1;
                        }
                        this.mCachedViews.add(v, recyclerView$ViewHolder0);
                        z4 = true;
                    }
                    if(z4) {
                        z = true;
                        z1 = false;
                    }
                    else {
                        this.addViewHolderToRecycledViewPool(recyclerView$ViewHolder0, true);
                    }
                }
                else {
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "trying to recycle a non-recycleable holder. Hopefully, it will re-visit here. We are still removing it from animation lists" + RecyclerView.this.exceptionLabel());
                    }
                    z1 = false;
                }
                RecyclerView.this.mViewInfoStore.removeViewHolder(recyclerView$ViewHolder0);
                if(!z && !z1 && z2) {
                    PoolingContainer.callPoolingContainerOnRelease(recyclerView$ViewHolder0.itemView);
                    recyclerView$ViewHolder0.mBindingAdapter = null;
                    recyclerView$ViewHolder0.mOwnerRecyclerView = null;
                }
                return;
            }
            StringBuilder stringBuilder0 = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
            stringBuilder0.append(recyclerView$ViewHolder0.isScrap());
            stringBuilder0.append(" isAttached:");
            if(recyclerView$ViewHolder0.itemView.getParent() != null) {
                z = true;
            }
            stringBuilder0.append(z);
            stringBuilder0.append(RecyclerView.this.exceptionLabel());
            throw new IllegalArgumentException(stringBuilder0.toString());
        }

        void scrapView(View view0) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(!recyclerView$ViewHolder0.hasAnyOfTheFlags(12) && recyclerView$ViewHolder0.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(recyclerView$ViewHolder0)) {
                if(this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList();
                }
                recyclerView$ViewHolder0.setScrapContainer(this, true);
                this.mChangedScrap.add(recyclerView$ViewHolder0);
                return;
            }
            if(recyclerView$ViewHolder0.isInvalid() && !recyclerView$ViewHolder0.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.exceptionLabel());
            }
            recyclerView$ViewHolder0.setScrapContainer(this, false);
            this.mAttachedScrap.add(recyclerView$ViewHolder0);
        }

        void setRecycledViewPool(RecycledViewPool recyclerView$RecycledViewPool0) {
            this.poolingContainerDetach(RecyclerView.this.mAdapter);
            RecycledViewPool recyclerView$RecycledViewPool1 = this.mRecyclerPool;
            if(recyclerView$RecycledViewPool1 != null) {
                recyclerView$RecycledViewPool1.detach();
            }
            this.mRecyclerPool = recyclerView$RecycledViewPool0;
            if(recyclerView$RecycledViewPool0 != null && RecyclerView.this.getAdapter() != null) {
                this.mRecyclerPool.attach();
            }
            this.maybeSendPoolingContainerAttach();
        }

        void setViewCacheExtension(ViewCacheExtension recyclerView$ViewCacheExtension0) {
            this.mViewCacheExtension = recyclerView$ViewCacheExtension0;
        }

        public void setViewCacheSize(int v) {
            this.mRequestedCacheMax = v;
            this.updateViewCacheSize();
        }

        private boolean tryBindViewHolderByDeadline(ViewHolder recyclerView$ViewHolder0, int v, int v1, long v2) {
            recyclerView$ViewHolder0.mBindingAdapter = null;
            recyclerView$ViewHolder0.mOwnerRecyclerView = RecyclerView.this;
            boolean z = false;
            if(v2 != 0x7FFFFFFFFFFFFFFFL && !this.mRecyclerPool.willBindInTime(recyclerView$ViewHolder0.getItemViewType(), 19699346329400L, v2)) {
                return false;
            }
            if(recyclerView$ViewHolder0.isTmpDetached()) {
                int v3 = RecyclerView.this.getChildCount();
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = recyclerView$ViewHolder0.itemView.getLayoutParams();
                RecyclerView.this.attachViewToParent(recyclerView$ViewHolder0.itemView, v3, viewGroup$LayoutParams0);
                z = true;
            }
            RecyclerView.this.mAdapter.bindViewHolder(recyclerView$ViewHolder0, v);
            if(z) {
                RecyclerView.this.detachViewFromParent(recyclerView$ViewHolder0.itemView);
            }
            this.mRecyclerPool.factorInBindTime(recyclerView$ViewHolder0.getItemViewType(), 794000L);
            this.attachAccessibilityDelegateOnBind(recyclerView$ViewHolder0);
            if(RecyclerView.this.mState.isPreLayout()) {
                recyclerView$ViewHolder0.mPreLayoutPosition = v1;
            }
            return true;
        }

        ViewHolder tryGetViewHolderForPositionByDeadline(int v, boolean z, long v1) {
            LayoutParams recyclerView$LayoutParams0;
            boolean z3;
            boolean z2;
            ViewHolder recyclerView$ViewHolder0;
            boolean z1 = true;
            if(v < 0 || v >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("Invalid item position " + v + "(" + v + "). Item count:" + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
            }
            if(RecyclerView.this.mState.isPreLayout()) {
                recyclerView$ViewHolder0 = this.getChangedScrapViewForPosition(v);
                z2 = recyclerView$ViewHolder0 == null ? false : true;
            }
            else {
                recyclerView$ViewHolder0 = null;
                z2 = false;
            }
            if(recyclerView$ViewHolder0 == null) {
                recyclerView$ViewHolder0 = this.getScrapOrHiddenOrCachedHolderForPosition(v, z);
                if(recyclerView$ViewHolder0 != null) {
                    if(this.validateViewHolderForOffsetPosition(recyclerView$ViewHolder0)) {
                        z2 = true;
                    }
                    else {
                        if(!z) {
                            recyclerView$ViewHolder0.addFlags(4);
                            if(recyclerView$ViewHolder0.isScrap()) {
                                RecyclerView.this.removeDetachedView(recyclerView$ViewHolder0.itemView, false);
                                recyclerView$ViewHolder0.unScrap();
                            }
                            else if(recyclerView$ViewHolder0.wasReturnedFromScrap()) {
                                recyclerView$ViewHolder0.clearReturnedFromScrapFlag();
                            }
                            this.recycleViewHolderInternal(recyclerView$ViewHolder0);
                        }
                        recyclerView$ViewHolder0 = null;
                    }
                }
            }
            if(recyclerView$ViewHolder0 == null) {
                int v2 = RecyclerView.this.mAdapterHelper.findPositionOffset(v);
                if(v2 < 0 || v2 >= RecyclerView.this.mAdapter.getItemCount()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + v + "(offset:" + v2 + ").state:" + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
                }
                int v3 = RecyclerView.this.mAdapter.getItemViewType(v2);
                if(RecyclerView.this.mAdapter.hasStableIds()) {
                    recyclerView$ViewHolder0 = this.getScrapOrCachedViewForId(RecyclerView.this.mAdapter.getItemId(v2), v3, z);
                    if(recyclerView$ViewHolder0 != null) {
                        recyclerView$ViewHolder0.mPosition = v2;
                        z2 = true;
                    }
                }
                if(recyclerView$ViewHolder0 == null) {
                    ViewCacheExtension recyclerView$ViewCacheExtension0 = this.mViewCacheExtension;
                    if(recyclerView$ViewCacheExtension0 != null) {
                        View view0 = recyclerView$ViewCacheExtension0.getViewForPositionAndType(this, v, v3);
                        if(view0 != null) {
                            recyclerView$ViewHolder0 = RecyclerView.this.getChildViewHolder(view0);
                            if(recyclerView$ViewHolder0 == null) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + RecyclerView.this.exceptionLabel());
                            }
                            if(recyclerView$ViewHolder0.shouldIgnore()) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + RecyclerView.this.exceptionLabel());
                            }
                        }
                    }
                }
                if(recyclerView$ViewHolder0 == null) {
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "tryGetViewHolderForPositionByDeadline(" + v + ") fetching from shared pool");
                    }
                    recyclerView$ViewHolder0 = this.getRecycledViewPool().getRecycledView(v3);
                    if(recyclerView$ViewHolder0 != null) {
                        recyclerView$ViewHolder0.resetInternal();
                        if(RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                            this.invalidateDisplayListInt(recyclerView$ViewHolder0);
                        }
                    }
                }
                if(recyclerView$ViewHolder0 == null) {
                    if(v1 != 0x7FFFFFFFFFFFFFFFL && !this.mRecyclerPool.willCreateInTime(v3, 19707449762700L, v1)) {
                        return null;
                    }
                    recyclerView$ViewHolder0 = RecyclerView.this.mAdapter.createViewHolder(RecyclerView.this, v3);
                    if(RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView recyclerView0 = RecyclerView.findNestedRecyclerView(recyclerView$ViewHolder0.itemView);
                        if(recyclerView0 != null) {
                            recyclerView$ViewHolder0.mNestedRecyclerView = new WeakReference(recyclerView0);
                        }
                    }
                    this.mRecyclerPool.factorInCreateTime(v3, 782000L);
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "tryGetViewHolderForPositionByDeadline created new ViewHolder");
                    }
                }
            }
            if(z2 && !RecyclerView.this.mState.isPreLayout() && recyclerView$ViewHolder0.hasAnyOfTheFlags(0x2000)) {
                recyclerView$ViewHolder0.setFlags(0, 0x2000);
                if(RecyclerView.this.mState.mRunSimpleAnimations) {
                    int v4 = ItemAnimator.buildAdapterChangeFlagsForAnimations(recyclerView$ViewHolder0);
                    ItemAnimator recyclerView$ItemAnimator0 = RecyclerView.this.mItemAnimator;
                    List list0 = recyclerView$ViewHolder0.getUnmodifiedPayloads();
                    ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0 = recyclerView$ItemAnimator0.recordPreLayoutInformation(RecyclerView.this.mState, recyclerView$ViewHolder0, v4 | 0x1000, list0);
                    RecyclerView.this.recordAnimationInfoIfBouncedHiddenView(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0);
                }
            }
            if(RecyclerView.this.mState.isPreLayout() && recyclerView$ViewHolder0.isBound()) {
                recyclerView$ViewHolder0.mPreLayoutPosition = v;
                z3 = false;
            }
            else if(!recyclerView$ViewHolder0.isBound() || recyclerView$ViewHolder0.needsUpdate() || recyclerView$ViewHolder0.isInvalid()) {
                if(RecyclerView.sDebugAssertionsEnabled && recyclerView$ViewHolder0.isRemoved()) {
                    throw new IllegalStateException("Removed holder should be bound and it should come here only in pre-layout. Holder: " + recyclerView$ViewHolder0 + RecyclerView.this.exceptionLabel());
                }
                z3 = this.tryBindViewHolderByDeadline(recyclerView$ViewHolder0, RecyclerView.this.mAdapterHelper.findPositionOffset(v), v, v1);
            }
            else {
                z3 = false;
            }
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = recyclerView$ViewHolder0.itemView.getLayoutParams();
            if(viewGroup$LayoutParams0 == null) {
                recyclerView$LayoutParams0 = (LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                recyclerView$ViewHolder0.itemView.setLayoutParams(recyclerView$LayoutParams0);
            }
            else if(RecyclerView.this.checkLayoutParams(viewGroup$LayoutParams0)) {
                recyclerView$LayoutParams0 = (LayoutParams)viewGroup$LayoutParams0;
            }
            else {
                recyclerView$LayoutParams0 = (LayoutParams)RecyclerView.this.generateLayoutParams(viewGroup$LayoutParams0);
                recyclerView$ViewHolder0.itemView.setLayoutParams(recyclerView$LayoutParams0);
            }
            recyclerView$LayoutParams0.mViewHolder = recyclerView$ViewHolder0;
            if(!z2 || !z3) {
                z1 = false;
            }
            recyclerView$LayoutParams0.mPendingInvalidate = z1;
            return recyclerView$ViewHolder0;
        }

        void unscrapView(ViewHolder recyclerView$ViewHolder0) {
            if(recyclerView$ViewHolder0.mInChangeScrap) {
                this.mChangedScrap.remove(recyclerView$ViewHolder0);
            }
            else {
                this.mAttachedScrap.remove(recyclerView$ViewHolder0);
            }
            recyclerView$ViewHolder0.mScrapContainer = null;
            recyclerView$ViewHolder0.mInChangeScrap = false;
            recyclerView$ViewHolder0.clearReturnedFromScrapFlag();
        }

        void updateViewCacheSize() {
            this.mViewCacheMax = this.mRequestedCacheMax + (RecyclerView.this.mLayout == null ? 0 : RecyclerView.this.mLayout.mPrefetchMaxCountObserved);
            for(int v = this.mCachedViews.size() - 1; v >= 0 && this.mCachedViews.size() > this.mViewCacheMax; --v) {
                this.recycleCachedViewAt(v);
            }
        }

        boolean validateViewHolderForOffsetPosition(ViewHolder recyclerView$ViewHolder0) {
            if(recyclerView$ViewHolder0.isRemoved()) {
                if(RecyclerView.sDebugAssertionsEnabled && !RecyclerView.this.mState.isPreLayout()) {
                    throw new IllegalStateException("should not receive a removed view unless it is pre layout" + RecyclerView.this.exceptionLabel());
                }
                return RecyclerView.this.mState.isPreLayout();
            }
            if(recyclerView$ViewHolder0.mPosition < 0 || recyclerView$ViewHolder0.mPosition >= RecyclerView.this.mAdapter.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + recyclerView$ViewHolder0 + RecyclerView.this.exceptionLabel());
            }
            return RecyclerView.this.mState.isPreLayout() || RecyclerView.this.mAdapter.getItemViewType(recyclerView$ViewHolder0.mPosition) == recyclerView$ViewHolder0.getItemViewType() ? !RecyclerView.this.mAdapter.hasStableIds() || recyclerView$ViewHolder0.getItemId() == RecyclerView.this.mAdapter.getItemId(recyclerView$ViewHolder0.mPosition) : false;
        }

        void viewRangeUpdate(int v, int v1) {
            for(int v2 = this.mCachedViews.size() - 1; v2 >= 0; --v2) {
                ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mCachedViews.get(v2);
                if(recyclerView$ViewHolder0 != null && (recyclerView$ViewHolder0.mPosition >= v && recyclerView$ViewHolder0.mPosition < v1 + v)) {
                    recyclerView$ViewHolder0.addFlags(2);
                    this.recycleCachedViewAt(v2);
                }
            }
        }
    }

    public interface RecyclerListener {
        void onViewRecycled(ViewHolder arg1);
    }

    class RecyclerViewDataObserver extends AdapterDataObserver {
        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView.this.mState.mStructureChanged = true;
            RecyclerView.this.processDataSetCompletelyChanged(true);
            if(!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeChanged(int v, int v1, Object object0) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if(RecyclerView.this.mAdapterHelper.onItemRangeChanged(v, v1, object0)) {
                this.triggerUpdateProcessor();
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeInserted(int v, int v1) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if(RecyclerView.this.mAdapterHelper.onItemRangeInserted(v, v1)) {
                this.triggerUpdateProcessor();
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeMoved(int v, int v1, int v2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if(RecyclerView.this.mAdapterHelper.onItemRangeMoved(v, v1, v2)) {
                this.triggerUpdateProcessor();
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeRemoved(int v, int v1) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if(RecyclerView.this.mAdapterHelper.onItemRangeRemoved(v, v1)) {
                this.triggerUpdateProcessor();
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            if(RecyclerView.this.mPendingSavedState != null) {
                Adapter recyclerView$Adapter0 = RecyclerView.this.mAdapter;
                if(recyclerView$Adapter0 != null && recyclerView$Adapter0.canRestoreState()) {
                    RecyclerView.this.requestLayout();
                }
            }
        }

        void triggerUpdateProcessor() {
            if(RecyclerView.this.mHasFixedSize && RecyclerView.this.mIsAttached) {
                ViewCompat.postOnAnimation(RecyclerView.this, RecyclerView.this.mUpdateChildViewsRunnable);
                return;
            }
            RecyclerView.this.mAdapterUpdateDuringMeasure = true;
            RecyclerView.this.requestLayout();
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        Parcelable mLayoutState;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
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

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            if(classLoader0 == null) {
                classLoader0 = LayoutManager.class.getClassLoader();
            }
            this.mLayoutState = parcel0.readParcelable(classLoader0);
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        void copyFrom(SavedState recyclerView$SavedState0) {
            this.mLayoutState = recyclerView$SavedState0.mLayoutState;
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeParcelable(this.mLayoutState, 0);
        }
    }

    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView0, MotionEvent motionEvent0) {
            return false;
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView0, MotionEvent motionEvent0) {
        }
    }

    public static abstract class SmoothScroller {
        public static class Action {
            public static final int UNDEFINED_DURATION = 0x80000000;
            private boolean mChanged;
            private int mConsecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;

            public Action(int v, int v1) {
                this(v, v1, 0x80000000, null);
            }

            public Action(int v, int v1, int v2) {
                this(v, v1, v2, null);
            }

            public Action(int v, int v1, int v2, Interpolator interpolator0) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = v;
                this.mDy = v1;
                this.mDuration = v2;
                this.mInterpolator = interpolator0;
            }

            public int getDuration() {
                return this.mDuration;
            }

            public int getDx() {
                return this.mDx;
            }

            public int getDy() {
                return this.mDy;
            }

            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }

            boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }

            public void jumpTo(int v) {
                this.mJumpToPosition = v;
            }

            void runIfNecessary(RecyclerView recyclerView0) {
                int v = this.mJumpToPosition;
                if(v >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView0.jumpToPositionForSmoothScroller(v);
                    this.mChanged = false;
                    return;
                }
                if(this.mChanged) {
                    this.validate();
                    recyclerView0.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                    int v1 = this.mConsecutiveUpdates + 1;
                    this.mConsecutiveUpdates = v1;
                    if(v1 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.mChanged = false;
                    return;
                }
                this.mConsecutiveUpdates = 0;
            }

            public void setDuration(int v) {
                this.mChanged = true;
                this.mDuration = v;
            }

            public void setDx(int v) {
                this.mChanged = true;
                this.mDx = v;
            }

            public void setDy(int v) {
                this.mChanged = true;
                this.mDy = v;
            }

            public void setInterpolator(Interpolator interpolator0) {
                this.mChanged = true;
                this.mInterpolator = interpolator0;
            }

            public void update(int v, int v1, int v2, Interpolator interpolator0) {
                this.mDx = v;
                this.mDy = v1;
                this.mDuration = v2;
                this.mInterpolator = interpolator0;
                this.mChanged = true;
            }

            private void validate() {
                if(this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if(this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }
        }

        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int arg1);
        }

        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction;
        private boolean mRunning;
        private boolean mStarted;
        private int mTargetPosition;
        private View mTargetView;

        public SmoothScroller() {
            this.mTargetPosition = -1;
            this.mRecyclingAction = new Action(0, 0);
        }

        public PointF computeScrollVectorForPosition(int v) {
            LayoutManager recyclerView$LayoutManager0 = this.getLayoutManager();
            if(recyclerView$LayoutManager0 instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider)recyclerView$LayoutManager0).computeScrollVectorForPosition(v);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            return null;
        }

        public View findViewByPosition(int v) {
            return this.mRecyclerView.mLayout.findViewByPosition(v);
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View view0) {
            return this.mRecyclerView.getChildLayoutPosition(view0);
        }

        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        @Deprecated
        public void instantScrollToPosition(int v) {
            this.mRecyclerView.scrollToPosition(v);
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        protected void normalize(PointF pointF0) {
            float f = (float)Math.sqrt(pointF0.x * pointF0.x + pointF0.y * pointF0.y);
            pointF0.x /= f;
            pointF0.y /= f;
        }

        void onAnimation(int v, int v1) {
            RecyclerView recyclerView0 = this.mRecyclerView;
            if(this.mTargetPosition == -1 || recyclerView0 == null) {
                this.stop();
            }
            if(this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null) {
                PointF pointF0 = this.computeScrollVectorForPosition(this.mTargetPosition);
                if(pointF0 != null && (pointF0.x != 0.0f || pointF0.y != 0.0f)) {
                    recyclerView0.scrollStep(((int)Math.signum(pointF0.x)), ((int)Math.signum(pointF0.y)), null);
                }
            }
            this.mPendingInitialRun = false;
            View view0 = this.mTargetView;
            if(view0 != null) {
                if(this.getChildPosition(view0) == this.mTargetPosition) {
                    this.onTargetFound(this.mTargetView, recyclerView0.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView0);
                    this.stop();
                }
                else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if(this.mRunning) {
                this.onSeekTargetStep(v, v1, recyclerView0.mState, this.mRecyclingAction);
                boolean z = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(recyclerView0);
                if(z && this.mRunning) {
                    this.mPendingInitialRun = true;
                    recyclerView0.mViewFlinger.postOnAnimation();
                }
            }
        }

        protected void onChildAttachedToWindow(View view0) {
            if(this.getChildPosition(view0) == this.getTargetPosition()) {
                this.mTargetView = view0;
                if(RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("RecyclerView", "smooth scroll target view has been attached");
                }
            }
        }

        protected abstract void onSeekTargetStep(int arg1, int arg2, State arg3, Action arg4);

        protected abstract void onStart();

        protected abstract void onStop();

        protected abstract void onTargetFound(View arg1, State arg2, Action arg3);

        public void setTargetPosition(int v) {
            this.mTargetPosition = v;
        }

        void start(RecyclerView recyclerView0, LayoutManager recyclerView$LayoutManager0) {
            recyclerView0.mViewFlinger.stop();
            if(this.mStarted) {
                Log.w("RecyclerView", "An instance of " + this.getClass().getSimpleName() + " was started more than once. Each instance of" + this.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.mRecyclerView = recyclerView0;
            this.mLayoutManager = recyclerView$LayoutManager0;
            if(this.mTargetPosition == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView0.mState.mTargetPosition = this.mTargetPosition;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = this.findViewByPosition(this.getTargetPosition());
            this.onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
            this.mStarted = true;
        }

        protected final void stop() {
            if(!this.mRunning) {
                return;
            }
            this.mRunning = false;
            this.onStop();
            this.mRecyclerView.mState.mTargetPosition = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            this.mLayoutManager.onSmoothScrollerStopped(this);
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
    }

    public static class State {
        static final int STEP_ANIMATIONS = 4;
        static final int STEP_LAYOUT = 2;
        static final int STEP_START = 1;
        private SparseArray mData;
        int mDeletedInvisibleItemCountSincePreviousLayout;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout;
        boolean mIsMeasuring;
        int mItemCount;
        int mLayoutStep;
        int mPreviousLayoutItemCount;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;
        boolean mRunPredictiveAnimations;
        boolean mRunSimpleAnimations;
        boolean mStructureChanged;
        int mTargetPosition;
        boolean mTrackOldChangeHolders;

        public State() {
            this.mTargetPosition = -1;
            this.mPreviousLayoutItemCount = 0;
            this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
            this.mLayoutStep = 1;
            this.mItemCount = 0;
            this.mStructureChanged = false;
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
            this.mRunSimpleAnimations = false;
            this.mRunPredictiveAnimations = false;
        }

        void assertLayoutStep(int v) {
            if((this.mLayoutStep & v) == 0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(v) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
            }
        }

        public boolean didStructureChange() {
            return this.mStructureChanged;
        }

        public Object get(int v) {
            return this.mData == null ? null : this.mData.get(v);
        }

        // 去混淆评级： 低(20)
        public int getItemCount() {
            return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
        }

        public int getRemainingScrollHorizontal() {
            return this.mRemainingScrollHorizontal;
        }

        public int getRemainingScrollVertical() {
            return this.mRemainingScrollVertical;
        }

        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }

        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }

        public boolean isMeasuring() {
            return this.mIsMeasuring;
        }

        public boolean isPreLayout() {
            return this.mInPreLayout;
        }

        void prepareForNestedPrefetch(Adapter recyclerView$Adapter0) {
            this.mLayoutStep = 1;
            this.mItemCount = recyclerView$Adapter0.getItemCount();
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }

        public void put(int v, Object object0) {
            if(this.mData == null) {
                this.mData = new SparseArray();
            }
            this.mData.put(v, object0);
        }

        public void remove(int v) {
            SparseArray sparseArray0 = this.mData;
            if(sparseArray0 == null) {
                return;
            }
            sparseArray0.remove(v);
        }

        @Override
        public String toString() {
            return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mIsMeasuring=" + this.mIsMeasuring + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
        }

        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }

        public boolean willRunSimpleAnimations() {
            return this.mRunSimpleAnimations;
        }
    }

    static class StretchEdgeEffectFactory extends EdgeEffectFactory {
        @Override  // androidx.recyclerview.widget.RecyclerView$EdgeEffectFactory
        protected EdgeEffect createEdgeEffect(RecyclerView recyclerView0, int v) {
            return new EdgeEffect(recyclerView0.getContext());
        }
    }

    public static abstract class ViewCacheExtension {
        public abstract View getViewForPositionAndType(Recycler arg1, int arg2, int arg3);
    }

    class ViewFlinger implements Runnable {
        private boolean mEatRunOnAnimationRequest;
        Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mOverScroller;
        private boolean mReSchedulePostAnimationCallback;

        ViewFlinger() {
            this.mInterpolator = RecyclerView.sQuinticInterpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mOverScroller = new OverScroller(recyclerView0.getContext(), RecyclerView.sQuinticInterpolator);
        }

        private int computeScrollDuration(int v, int v1) {
            int v2 = Math.abs(v);
            int v3 = Math.abs(v1);
            boolean z = v2 > v3;
            int v4 = z ? RecyclerView.this.getWidth() : RecyclerView.this.getHeight();
            if(!z) {
                v2 = v3;
            }
            return Math.min(((int)((((float)v2) / ((float)v4) + 1.0f) * 300.0f)), 2000);
        }

        public void fling(int v, int v1) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            if(this.mInterpolator != RecyclerView.sQuinticInterpolator) {
                this.mInterpolator = RecyclerView.sQuinticInterpolator;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
            }
            this.mOverScroller.fling(0, 0, v, v1, 0x80000000, 0x7FFFFFFF, 0x80000000, 0x7FFFFFFF);
            this.postOnAnimation();
        }

        private void internalPostOnAnimation() {
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        void postOnAnimation() {
            if(this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
                return;
            }
            this.internalPostOnAnimation();
        }

        @Override
        public void run() {
            int v14;
            int v10;
            int v9;
            if(RecyclerView.this.mLayout == null) {
                this.stop();
                return;
            }
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            RecyclerView.this.consumePendingUpdateOperations();
            OverScroller overScroller0 = this.mOverScroller;
            if(overScroller0.computeScrollOffset()) {
                int v = overScroller0.getCurrX();
                int v1 = overScroller0.getCurrY();
                int v2 = v - this.mLastFlingX;
                int v3 = v1 - this.mLastFlingY;
                this.mLastFlingX = v;
                this.mLastFlingY = v1;
                int v4 = RecyclerView.this.consumeFlingInHorizontalStretch(v2);
                int v5 = RecyclerView.this.consumeFlingInVerticalStretch(v3);
                RecyclerView.this.mReusableIntPair[0] = 0;
                RecyclerView.this.mReusableIntPair[1] = 0;
                if(RecyclerView.this.dispatchNestedPreScroll(v4, v5, RecyclerView.this.mReusableIntPair, null, 1)) {
                    v4 -= RecyclerView.this.mReusableIntPair[0];
                    v5 -= RecyclerView.this.mReusableIntPair[1];
                }
                if(RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(v4, v5);
                }
                if(RecyclerView.this.mAdapter == null) {
                    v9 = 0;
                    v10 = 0;
                }
                else {
                    RecyclerView.this.mReusableIntPair[0] = 0;
                    RecyclerView.this.mReusableIntPair[1] = 0;
                    RecyclerView.this.scrollStep(v4, v5, RecyclerView.this.mReusableIntPair);
                    int v6 = RecyclerView.this.mReusableIntPair[0];
                    int v7 = RecyclerView.this.mReusableIntPair[1];
                    v4 -= v6;
                    v5 -= v7;
                    SmoothScroller recyclerView$SmoothScroller0 = RecyclerView.this.mLayout.mSmoothScroller;
                    if(recyclerView$SmoothScroller0 != null && !recyclerView$SmoothScroller0.isPendingInitialRun() && recyclerView$SmoothScroller0.isRunning()) {
                        int v8 = RecyclerView.this.mState.getItemCount();
                        if(v8 == 0) {
                            recyclerView$SmoothScroller0.stop();
                        }
                        else {
                            if(recyclerView$SmoothScroller0.getTargetPosition() >= v8) {
                                recyclerView$SmoothScroller0.setTargetPosition(v8 - 1);
                            }
                            recyclerView$SmoothScroller0.onAnimation(v6, v7);
                        }
                    }
                    v9 = v6;
                    v10 = v7;
                }
                if(!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView.this.mReusableIntPair[0] = 0;
                RecyclerView.this.mReusableIntPair[1] = 0;
                RecyclerView.this.dispatchNestedScroll(v9, v10, v4, v5, null, 1, RecyclerView.this.mReusableIntPair);
                int v11 = v4 - RecyclerView.this.mReusableIntPair[0];
                int v12 = v5 - RecyclerView.this.mReusableIntPair[1];
                if(v9 != 0 || v10 != 0) {
                    RecyclerView.this.dispatchOnScrolled(v9, v10);
                }
                if(!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller0.getCurrX() == overScroller0.getFinalX();
                boolean z1 = overScroller0.getCurrY() == overScroller0.getFinalY();
                boolean z2 = overScroller0.isFinished() || (z || v11 != 0) && (z1 || v12 != 0);
                SmoothScroller recyclerView$SmoothScroller1 = RecyclerView.this.mLayout.mSmoothScroller;
                if(recyclerView$SmoothScroller1 != null && recyclerView$SmoothScroller1.isPendingInitialRun() || !z2) {
                    this.postOnAnimation();
                    if(RecyclerView.this.mGapWorker != null) {
                        RecyclerView.this.mGapWorker.postFromTraversal(RecyclerView.this, v9, v10);
                    }
                }
                else {
                    if(RecyclerView.this.getOverScrollMode() != 2) {
                        int v13 = (int)overScroller0.getCurrVelocity();
                        if(v11 < 0) {
                            v14 = -v13;
                        }
                        else {
                            v14 = v11 <= 0 ? 0 : v13;
                        }
                        if(v12 < 0) {
                            v13 = -v13;
                        }
                        else if(v12 <= 0) {
                            v13 = 0;
                        }
                        RecyclerView.this.absorbGlows(v14, v13);
                    }
                    if(RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
                    }
                }
                if(Build.VERSION.SDK_INT >= 35) {
                    float f = Math.abs(overScroller0.getCurrVelocity());
                    Api35Impl.setFrameContentVelocity(RecyclerView.this, f);
                }
            }
            SmoothScroller recyclerView$SmoothScroller2 = RecyclerView.this.mLayout.mSmoothScroller;
            if(recyclerView$SmoothScroller2 != null && recyclerView$SmoothScroller2.isPendingInitialRun()) {
                recyclerView$SmoothScroller2.onAnimation(0, 0);
            }
            this.mEatRunOnAnimationRequest = false;
            if(this.mReSchedulePostAnimationCallback) {
                this.internalPostOnAnimation();
                return;
            }
            RecyclerView.this.setScrollState(0);
            RecyclerView.this.stopNestedScroll(1);
        }

        public void smoothScrollBy(int v, int v1, int v2, Interpolator interpolator0) {
            if(v2 == 0x80000000) {
                v2 = this.computeScrollDuration(v, v1);
            }
            if(interpolator0 == null) {
                interpolator0 = RecyclerView.sQuinticInterpolator;
            }
            if(this.mInterpolator != interpolator0) {
                this.mInterpolator = interpolator0;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator0);
            }
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            RecyclerView.this.setScrollState(2);
            this.mOverScroller.startScroll(0, 0, v, v1, v2);
            if(Build.VERSION.SDK_INT < 23) {
                this.mOverScroller.computeScrollOffset();
            }
            this.postOnAnimation();
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mOverScroller.abortAnimation();
        }
    }

    public static abstract class ViewHolder {
        static final int FLAG_ADAPTER_FULLUPDATE = 0x400;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 0x200;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 0x1000;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 0x2000;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 0x80;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 0x800;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 0x20;
        static final int FLAG_TMP_DETACHED = 0x100;
        static final int FLAG_UPDATE = 2;
        private static final List FULLUPDATE_PAYLOADS = null;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        Adapter mBindingAdapter;
        int mFlags;
        boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        WeakReference mNestedRecyclerView;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List mPayloads;
        int mPendingAccessibilityState;
        int mPosition;
        int mPreLayoutPosition;
        Recycler mScrapContainer;
        ViewHolder mShadowedHolder;
        ViewHolder mShadowingHolder;
        List mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;

        static {
            ViewHolder.FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }

        public ViewHolder(View view0) {
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mItemViewType = -1;
            this.mPreLayoutPosition = -1;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.mPayloads = null;
            this.mUnmodifiedPayloads = null;
            this.mIsRecyclableCount = 0;
            this.mScrapContainer = null;
            this.mInChangeScrap = false;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            if(view0 == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view0;
        }

        void addChangePayload(Object object0) {
            if(object0 == null) {
                this.addFlags(0x400);
                return;
            }
            if((0x400 & this.mFlags) == 0) {
                this.createPayloadsIfNeeded();
                this.mPayloads.add(object0);
            }
        }

        void addFlags(int v) {
            this.mFlags |= v;
        }

        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        void clearPayload() {
            List list0 = this.mPayloads;
            if(list0 != null) {
                list0.clear();
            }
            this.mFlags &= 0xFFFFFBFF;
        }

        void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        void clearTmpDetachFlag() {
            this.mFlags &= 0xFFFFFEFF;
        }

        private void createPayloadsIfNeeded() {
            if(this.mPayloads == null) {
                ArrayList arrayList0 = new ArrayList();
                this.mPayloads = arrayList0;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList0);
            }
        }

        boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        void flagRemovedAndOffsetPosition(int v, int v1, boolean z) {
            this.addFlags(8);
            this.offsetPosition(v1, z);
            this.mPosition = v;
        }

        public final int getAbsoluteAdapterPosition() {
            return this.mOwnerRecyclerView == null ? -1 : this.mOwnerRecyclerView.getAdapterPositionInRecyclerView(this);
        }

        @Deprecated
        public final int getAdapterPosition() {
            return this.getBindingAdapterPosition();
        }

        public final Adapter getBindingAdapter() {
            return this.mBindingAdapter;
        }

        public final int getBindingAdapterPosition() {
            if(this.mBindingAdapter == null) {
                return -1;
            }
            RecyclerView recyclerView0 = this.mOwnerRecyclerView;
            if(recyclerView0 == null) {
                return -1;
            }
            Adapter recyclerView$Adapter0 = recyclerView0.getAdapter();
            if(recyclerView$Adapter0 == null) {
                return -1;
            }
            int v = this.mOwnerRecyclerView.getAdapterPositionInRecyclerView(this);
            return v == -1 ? -1 : recyclerView$Adapter0.findRelativeAdapterPositionIn(this.mBindingAdapter, this, v);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated
        public final int getPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        List getUnmodifiedPayloads() {
            return (this.mFlags & 0x400) != 0 || (this.mPayloads == null || this.mPayloads.size() == 0) ? ViewHolder.FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
        }

        boolean hasAnyOfTheFlags(int v) {
            return (v & this.mFlags) != 0;
        }

        boolean isAdapterPositionUnknown() {
            return (this.mFlags & 0x200) != 0 || this.isInvalid();
        }

        boolean isAttachedToTransitionOverlay() {
            return this.itemView.getParent() != null && this.itemView.getParent() != this.mOwnerRecyclerView;
        }

        boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        boolean isScrap() {
            return this.mScrapContainer != null;
        }

        boolean isTmpDetached() {
            return (this.mFlags & 0x100) != 0;
        }

        boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        void offsetPosition(int v, boolean z) {
            if(this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if(this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if(z) {
                this.mPreLayoutPosition += v;
            }
            this.mPosition += v;
            if(this.itemView.getLayoutParams() != null) {
                ((LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        void onEnteredHiddenState(RecyclerView recyclerView0) {
            int v = this.mPendingAccessibilityState;
            this.mWasImportantForAccessibilityBeforeHidden = v == -1 ? this.itemView.getImportantForAccessibility() : v;
            recyclerView0.setChildImportantForAccessibilityInternal(this, 4);
        }

        void onLeftHiddenState(RecyclerView recyclerView0) {
            recyclerView0.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        void resetInternal() {
            if(RecyclerView.sDebugAssertionsEnabled && this.isTmpDetached()) {
                throw new IllegalStateException("Attempting to reset temp-detached ViewHolder: " + this + ". ViewHolders should be fully detached before resetting.");
            }
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        void saveOldPosition() {
            if(this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        void setFlags(int v, int v1) {
            this.mFlags = v & v1 | this.mFlags & ~v1;
        }

        public final void setIsRecyclable(boolean z) {
            int v = z ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
            this.mIsRecyclableCount = v;
            if(v < 0) {
                this.mIsRecyclableCount = 0;
                if(RecyclerView.sDebugAssertionsEnabled) {
                    throw new RuntimeException("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                }
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            }
            else if(!z && v == 1) {
                this.mFlags |= 16;
            }
            else if(z && v == 0) {
                this.mFlags &= -17;
            }
            if(RecyclerView.sVerboseLoggingEnabled) {
                Log.d("RecyclerView", "setIsRecyclable val:" + z + ":" + this);
            }
        }

        void setScrapContainer(Recycler recyclerView$Recycler0, boolean z) {
            this.mScrapContainer = recyclerView$Recycler0;
            this.mInChangeScrap = z;
        }

        boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        boolean shouldIgnore() {
            return (this.mFlags & 0x80) != 0;
        }

        void stopIgnoring() {
            this.mFlags &= 0xFFFFFF7F;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder((this.getClass().isAnonymousClass() ? "ViewHolder" : this.getClass().getSimpleName()) + "{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if(this.isScrap()) {
                stringBuilder0.append(" scrap ");
                stringBuilder0.append((this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]"));
            }
            if(this.isInvalid()) {
                stringBuilder0.append(" invalid");
            }
            if(!this.isBound()) {
                stringBuilder0.append(" unbound");
            }
            if(this.needsUpdate()) {
                stringBuilder0.append(" update");
            }
            if(this.isRemoved()) {
                stringBuilder0.append(" removed");
            }
            if(this.shouldIgnore()) {
                stringBuilder0.append(" ignored");
            }
            if(this.isTmpDetached()) {
                stringBuilder0.append(" tmpDetached");
            }
            if(!this.isRecyclable()) {
                stringBuilder0.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if(this.isAdapterPositionUnknown()) {
                stringBuilder0.append(" undefined adapter position");
            }
            if(this.itemView.getParent() == null) {
                stringBuilder0.append(" no parent");
            }
            stringBuilder0.append("}");
            return stringBuilder0.toString();
        }

        void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        boolean wasReturnedFromScrap() {
            return (this.mFlags & 0x20) != 0;
        }
    }

    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = false;
    static final boolean ALLOW_THREAD_GAP_WORK = false;
    private static final float DECELERATION_RATE = 0.0f;
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final float FLING_DESTRETCH_FACTOR = 4.0f;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    static final long FOREVER_NS = 0x7FFFFFFFFFFFFFFFL;
    public static final int HORIZONTAL = 0;
    private static final float INFLEXION = 0.35f;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = null;
    static final String LOW_RES_ROTARY_ENCODER_FEATURE = "android.hardware.rotaryencoder.lowres";
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS = null;
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    private static final float SCROLL_FRICTION = 0.015f;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int UNDEFINED_DURATION = 0x80000000;
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    DifferentialMotionFlingController mDifferentialMotionFlingController;
    private final DifferentialMotionFlingTarget mDifferentialMotionFlingTarget;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private EdgeEffectFactory mEdgeEffectFactory;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private OnItemTouchListener mInterceptingOnItemTouchListener;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastAutoMeasureNonExactMeasuredHeight;
    private int mLastAutoMeasureNonExactMeasuredWidth;
    private boolean mLastAutoMeasureSkippedDueToExact;
    private int mLastTouchX;
    private int mLastTouchY;
    LayoutManager mLayout;
    private int mLayoutOrScrollCounter;
    boolean mLayoutSuppressed;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    boolean mLowResRotaryEncoderFeature;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList mOnItemTouchListeners;
    final List mPendingAccessibilityImportanceChange;
    SavedState mPendingSavedState;
    private final float mPhysicalCoef;
    boolean mPostedAnimatorRunner;
    LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    final List mRecyclerListeners;
    final int[] mReusableIntPair;
    private EdgeEffect mRightGlow;
    float mScaledHorizontalScrollFactor;
    float mScaledVerticalScrollFactor;
    private OnScrollListener mScrollListener;
    private List mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    static boolean sDebugAssertionsEnabled = false;
    static final StretchEdgeEffectFactory sDefaultEdgeEffectFactory = null;
    static final Interpolator sQuinticInterpolator = null;
    static boolean sVerboseLoggingEnabled = false;

    static {
        RecyclerView.NESTED_SCROLLING_ATTRS = new int[]{0x1010436};
        RecyclerView.DECELERATION_RATE = 2.358202f;
        RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST = false;
        RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC = Build.VERSION.SDK_INT >= 23;
        RecyclerView.ALLOW_THREAD_GAP_WORK = true;
        RecyclerView.LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        RecyclerView.sQuinticInterpolator = new Interpolator() {
            @Override  // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) + 1.0f;
            }
        };
        RecyclerView.sDefaultEdgeEffectFactory = new StretchEdgeEffectFactory();
    }

    public RecyclerView(Context context0) {
        this(context0, null);
    }

    public RecyclerView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.recyclerViewStyle);
    }

    public RecyclerView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mObserver = new RecyclerViewDataObserver(this);
        this.mRecycler = new Recycler(this);
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() {
            @Override
            public void run() {
                if(RecyclerView.this.mFirstLayoutComplete && !RecyclerView.this.isLayoutRequested()) {
                    if(!RecyclerView.this.mIsAttached) {
                        RecyclerView.this.requestLayout();
                        return;
                    }
                    if(RecyclerView.this.mLayoutSuppressed) {
                        RecyclerView.this.mLayoutWasDefered = true;
                        return;
                    }
                    RecyclerView.this.consumePendingUpdateOperations();
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = RecyclerView.sDefaultEdgeEffectFactory;
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = 1.401298E-45f;
        this.mScaledVerticalScrollFactor = 1.401298E-45f;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger(this);
        this.mPrefetchRegistry = RecyclerView.ALLOW_THREAD_GAP_WORK ? new LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() {
            @Override
            public void run() {
                if(RecyclerView.this.mItemAnimator != null) {
                    RecyclerView.this.mItemAnimator.runPendingAnimations();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new ProcessCallback() {
            @Override  // androidx.recyclerview.widget.ViewInfoStore$ProcessCallback
            public void processAppeared(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
                RecyclerView.this.animateAppearance(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1);
            }

            @Override  // androidx.recyclerview.widget.ViewInfoStore$ProcessCallback
            public void processDisappeared(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
                RecyclerView.this.mRecycler.unscrapView(recyclerView$ViewHolder0);
                RecyclerView.this.animateDisappearance(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1);
            }

            @Override  // androidx.recyclerview.widget.ViewInfoStore$ProcessCallback
            public void processPersistent(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
                recyclerView$ViewHolder0.setIsRecyclable(false);
                if(RecyclerView.this.mDataSetHasChangedAfterLayout) {
                    if(RecyclerView.this.mItemAnimator.animateChange(recyclerView$ViewHolder0, recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1)) {
                        RecyclerView.this.postAnimationRunner();
                    }
                }
                else if(RecyclerView.this.mItemAnimator.animatePersistence(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1)) {
                    RecyclerView.this.postAnimationRunner();
                }
            }

            @Override  // androidx.recyclerview.widget.ViewInfoStore$ProcessCallback
            public void unused(ViewHolder recyclerView$ViewHolder0) {
                RecyclerView.this.mLayout.removeAndRecycleView(recyclerView$ViewHolder0.itemView, RecyclerView.this.mRecycler);
            }
        };
        androidx.recyclerview.widget.RecyclerView.5 recyclerView$50 = new DifferentialMotionFlingTarget() {
            @Override  // androidx.core.view.DifferentialMotionFlingTarget
            public float getScaledScrollFactor() {
                if(RecyclerView.this.mLayout.canScrollVertically()) {
                    return -RecyclerView.this.mScaledVerticalScrollFactor;
                }
                return RecyclerView.this.mLayout.canScrollHorizontally() ? -RecyclerView.this.mScaledHorizontalScrollFactor : 0.0f;
            }

            @Override  // androidx.core.view.DifferentialMotionFlingTarget
            public boolean startDifferentialMotionFling(float f) {
                int v1;
                int v;
                if(RecyclerView.this.mLayout.canScrollVertically()) {
                    v = (int)f;
                    v1 = 0;
                }
                else {
                    v1 = RecyclerView.this.mLayout.canScrollHorizontally() ? ((int)f) : 0;
                    v = 0;
                }
                if(v1 == 0 && v == 0) {
                    return false;
                }
                RecyclerView.this.stopScroll();
                return RecyclerView.this.flingNoThresholdCheck(v1, v);
            }

            @Override  // androidx.core.view.DifferentialMotionFlingTarget
            public void stopDifferentialMotionFling() {
                RecyclerView.this.stopScroll();
            }
        };
        this.mDifferentialMotionFlingTarget = recyclerView$50;
        this.mDifferentialMotionFlingController = new DifferentialMotionFlingController(this.getContext(), recyclerView$50);
        this.setScrollContainer(true);
        this.setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration0 = ViewConfiguration.get(context0);
        this.mTouchSlop = viewConfiguration0.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration0, context0);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration0, context0);
        this.mMinFlingVelocity = viewConfiguration0.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration0.getScaledMaximumFlingVelocity();
        this.mPhysicalCoef = context0.getResources().getDisplayMetrics().density * 51890.199219f;
        this.setWillNotDraw(this.getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        this.initAdapterManager();
        this.initChildrenHelper();
        this.initAutofill();
        if(this.getImportantForAccessibility() == 0) {
            this.setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.RecyclerView, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.RecyclerView, attributeSet0, typedArray0, v, 0);
        String s = typedArray0.getString(styleable.RecyclerView_layoutManager);
        if(typedArray0.getInt(styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            this.setDescendantFocusability(0x40000);
        }
        this.mClipToPadding = typedArray0.getBoolean(styleable.RecyclerView_android_clipToPadding, true);
        boolean z = typedArray0.getBoolean(styleable.RecyclerView_fastScrollEnabled, false);
        this.mEnableFastScroller = z;
        if(z) {
            this.initFastScroller(((StateListDrawable)typedArray0.getDrawable(styleable.RecyclerView_fastScrollVerticalThumbDrawable)), typedArray0.getDrawable(styleable.RecyclerView_fastScrollVerticalTrackDrawable), ((StateListDrawable)typedArray0.getDrawable(styleable.RecyclerView_fastScrollHorizontalThumbDrawable)), typedArray0.getDrawable(styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        typedArray0.recycle();
        this.mLowResRotaryEncoderFeature = context0.getPackageManager().hasSystemFeature("android.hardware.rotaryencoder.lowres");
        this.createLayoutManager(context0, s, attributeSet0, v, 0);
        TypedArray typedArray1 = context0.obtainStyledAttributes(attributeSet0, RecyclerView.NESTED_SCROLLING_ATTRS, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, RecyclerView.NESTED_SCROLLING_ATTRS, attributeSet0, typedArray1, v, 0);
        boolean z1 = typedArray1.getBoolean(0, true);
        typedArray1.recycle();
        this.setNestedScrollingEnabled(z1);
        PoolingContainer.setPoolingContainer(this, true);
    }

    void absorbGlows(int v, int v1) {
        if(v < 0) {
            this.ensureLeftGlow();
            if(this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-v);
            }
        }
        else if(v > 0) {
            this.ensureRightGlow();
            if(this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(v);
            }
        }
        if(v1 < 0) {
            this.ensureTopGlow();
            if(this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-v1);
            }
        }
        else if(v1 > 0) {
            this.ensureBottomGlow();
            if(this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(v1);
            }
        }
        if(v == 0 && v1 == 0) {
            return;
        }
        this.postInvalidateOnAnimation();
    }

    private void addAnimatingView(ViewHolder recyclerView$ViewHolder0) {
        View view0 = recyclerView$ViewHolder0.itemView;
        boolean z = view0.getParent() == this;
        ViewHolder recyclerView$ViewHolder1 = this.getChildViewHolder(view0);
        this.mRecycler.unscrapView(recyclerView$ViewHolder1);
        if(recyclerView$ViewHolder0.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view0, -1, view0.getLayoutParams(), true);
            return;
        }
        if(!z) {
            this.mChildHelper.addView(view0, true);
            return;
        }
        this.mChildHelper.hide(view0);
    }

    @Override  // android.view.ViewGroup
    public void addFocusables(ArrayList arrayList0, int v, int v1) {
        super.addFocusables(arrayList0, v, v1);
    }

    public void addItemDecoration(ItemDecoration recyclerView$ItemDecoration0) {
        this.addItemDecoration(recyclerView$ItemDecoration0, -1);
    }

    public void addItemDecoration(ItemDecoration recyclerView$ItemDecoration0, int v) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if(this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(false);
        }
        if(v < 0) {
            this.mItemDecorations.add(recyclerView$ItemDecoration0);
        }
        else {
            this.mItemDecorations.add(v, recyclerView$ItemDecoration0);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener recyclerView$OnChildAttachStateChangeListener0) {
        if(this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(recyclerView$OnChildAttachStateChangeListener0);
    }

    public void addOnItemTouchListener(OnItemTouchListener recyclerView$OnItemTouchListener0) {
        this.mOnItemTouchListeners.add(recyclerView$OnItemTouchListener0);
    }

    public void addOnScrollListener(OnScrollListener recyclerView$OnScrollListener0) {
        if(this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(recyclerView$OnScrollListener0);
    }

    public void addRecyclerListener(RecyclerListener recyclerView$RecyclerListener0) {
        Preconditions.checkArgument(recyclerView$RecyclerListener0 != null, "\'listener\' arg cannot be null.");
        this.mRecyclerListeners.add(recyclerView$RecyclerListener0);
    }

    void animateAppearance(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
        recyclerView$ViewHolder0.setIsRecyclable(false);
        if(this.mItemAnimator.animateAppearance(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1)) {
            this.postAnimationRunner();
        }
    }

    private void animateChange(ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1, boolean z, boolean z1) {
        recyclerView$ViewHolder0.setIsRecyclable(false);
        if(z) {
            this.addAnimatingView(recyclerView$ViewHolder0);
        }
        if(recyclerView$ViewHolder0 != recyclerView$ViewHolder1) {
            if(z1) {
                this.addAnimatingView(recyclerView$ViewHolder1);
            }
            recyclerView$ViewHolder0.mShadowedHolder = recyclerView$ViewHolder1;
            this.addAnimatingView(recyclerView$ViewHolder0);
            this.mRecycler.unscrapView(recyclerView$ViewHolder0);
            recyclerView$ViewHolder1.setIsRecyclable(false);
            recyclerView$ViewHolder1.mShadowingHolder = recyclerView$ViewHolder0;
        }
        if(this.mItemAnimator.animateChange(recyclerView$ViewHolder0, recyclerView$ViewHolder1, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1)) {
            this.postAnimationRunner();
        }
    }

    void animateDisappearance(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
        this.addAnimatingView(recyclerView$ViewHolder0);
        recyclerView$ViewHolder0.setIsRecyclable(false);
        if(this.mItemAnimator.animateDisappearance(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0, recyclerView$ItemAnimator$ItemHolderInfo1)) {
            this.postAnimationRunner();
        }
    }

    void assertInLayoutOrScroll(String s) {
        if(!this.isComputingLayout()) {
            throw s == null ? new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + this.exceptionLabel()) : new IllegalStateException(s + this.exceptionLabel());
        }
    }

    void assertNotInLayoutOrScroll(String s) {
        if(this.isComputingLayout()) {
            throw s == null ? new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + this.exceptionLabel()) : new IllegalStateException(s);
        }
        if(this.mDispatchScrollCounter > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + this.exceptionLabel()));
        }
    }

    boolean canReuseUpdatedViewHolder(ViewHolder recyclerView$ViewHolder0) {
        return this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(recyclerView$ViewHolder0, recyclerView$ViewHolder0.getUnmodifiedPayloads());
    }

    private void cancelScroll() {
        this.resetScroll();
        this.setScrollState(0);
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams && this.mLayout.checkLayoutParams(((LayoutParams)viewGroup$LayoutParams0));
    }

    static void clearNestedRecyclerViewIfNotNested(ViewHolder recyclerView$ViewHolder0) {
        if(recyclerView$ViewHolder0.mNestedRecyclerView != null) {
            for(View view0 = (View)recyclerView$ViewHolder0.mNestedRecyclerView.get(); true; view0 = viewParent0 instanceof View ? ((View)viewParent0) : null) {
                if(view0 == null) {
                    recyclerView$ViewHolder0.mNestedRecyclerView = null;
                    break;
                }
                if(view0 == recyclerView$ViewHolder0.itemView) {
                    break;
                }
                ViewParent viewParent0 = view0.getParent();
            }
        }
    }

    void clearOldPositions() {
        int v = this.mChildHelper.getUnfilteredChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v1));
            if(!recyclerView$ViewHolder0.shouldIgnore()) {
                recyclerView$ViewHolder0.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }

    public void clearOnChildAttachStateChangeListeners() {
        List list0 = this.mOnChildAttachStateListeners;
        if(list0 != null) {
            list0.clear();
        }
    }

    public void clearOnScrollListeners() {
        List list0 = this.mScrollListeners;
        if(list0 != null) {
            list0.clear();
        }
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeHorizontalScrollExtent() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return 0;
        }
        return recyclerView$LayoutManager0.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollExtent(this.mState) : 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeHorizontalScrollOffset() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return 0;
        }
        return recyclerView$LayoutManager0.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollOffset(this.mState) : 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeHorizontalScrollRange() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return 0;
        }
        return recyclerView$LayoutManager0.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollRange(this.mState) : 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeVerticalScrollExtent() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return 0;
        }
        return recyclerView$LayoutManager0.canScrollVertically() ? this.mLayout.computeVerticalScrollExtent(this.mState) : 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeVerticalScrollOffset() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return 0;
        }
        return recyclerView$LayoutManager0.canScrollVertically() ? this.mLayout.computeVerticalScrollOffset(this.mState) : 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeVerticalScrollRange() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return 0;
        }
        return recyclerView$LayoutManager0.canScrollVertically() ? this.mLayout.computeVerticalScrollRange(this.mState) : 0;
    }

    void considerReleasingGlowsOnScroll(int v, int v1) {
        boolean z;
        if(this.mLeftGlow == null || this.mLeftGlow.isFinished() || v <= 0) {
            z = false;
        }
        else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        if(this.mRightGlow != null && !this.mRightGlow.isFinished() && v < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        if(this.mTopGlow != null && !this.mTopGlow.isFinished() && v1 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        if(this.mBottomGlow != null && !this.mBottomGlow.isFinished() && v1 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if(z) {
            this.postInvalidateOnAnimation();
        }
    }

    int consumeFlingInHorizontalStretch(int v) {
        return this.consumeFlingInStretch(v, this.mLeftGlow, this.mRightGlow, this.getWidth());
    }

    private int consumeFlingInStretch(int v, EdgeEffect edgeEffect0, EdgeEffect edgeEffect1, int v1) {
        if(v > 0 && edgeEffect0 != null && EdgeEffectCompat.getDistance(edgeEffect0) != 0.0f) {
            int v2 = Math.round(((float)(-v1)) / 4.0f * EdgeEffectCompat.onPullDistance(edgeEffect0, ((float)(-v)) * 4.0f / ((float)v1), 0.5f));
            if(v2 != v) {
                edgeEffect0.finish();
            }
            return v - v2;
        }
        if(v < 0 && edgeEffect1 != null && EdgeEffectCompat.getDistance(edgeEffect1) != 0.0f) {
            int v3 = Math.round(((float)v1) / 4.0f * EdgeEffectCompat.onPullDistance(edgeEffect1, ((float)v) * 4.0f / ((float)v1), 0.5f));
            if(v3 != v) {
                edgeEffect1.finish();
            }
            return v - v3;
        }
        return v;
    }

    int consumeFlingInVerticalStretch(int v) {
        return this.consumeFlingInStretch(v, this.mTopGlow, this.mBottomGlow, this.getHeight());
    }

    void consumePendingUpdateOperations() {
        if(this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout) {
            if(this.mAdapterHelper.hasPendingUpdates()) {
                if(this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                    Trace.beginSection("RV PartialInvalidate");
                    this.startInterceptRequestLayout();
                    this.onEnterLayoutOrScroll();
                    this.mAdapterHelper.preProcess();
                    if(!this.mLayoutWasDefered) {
                        if(this.hasUpdatedView()) {
                            this.dispatchLayout();
                        }
                        else {
                            this.mAdapterHelper.consumePostponedUpdates();
                        }
                    }
                    this.stopInterceptRequestLayout(true);
                    this.onExitLayoutOrScroll();
                    Trace.endSection();
                    return;
                }
                if(this.mAdapterHelper.hasPendingUpdates()) {
                    Trace.beginSection("RV FullInvalidate");
                    this.dispatchLayout();
                    Trace.endSection();
                }
            }
            return;
        }
        Trace.beginSection("RV FullInvalidate");
        this.dispatchLayout();
        Trace.endSection();
    }

    private void createLayoutManager(Context context0, String s, AttributeSet attributeSet0, int v, int v1) {
        Constructor constructor0;
        Object[] arr_object;
        if(s != null) {
            String s1 = s.trim();
            if(!s1.isEmpty()) {
                String s2 = this.getFullClassName(context0, s1);
                try {
                    Class class0 = Class.forName(s2, false, (this.isInEditMode() ? this.getClass().getClassLoader() : context0.getClassLoader())).asSubclass(LayoutManager.class);
                    try {
                        arr_object = null;
                        constructor0 = class0.getConstructor(RecyclerView.LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        arr_object = new Object[]{context0, attributeSet0, v, v1};
                    }
                    catch(NoSuchMethodException noSuchMethodException0) {
                        try {
                            constructor0 = class0.getConstructor(null);
                        }
                        catch(NoSuchMethodException noSuchMethodException1) {
                            noSuchMethodException1.initCause(noSuchMethodException0);
                            throw new IllegalStateException(attributeSet0.getPositionDescription() + ": Error creating LayoutManager " + s2, noSuchMethodException1);
                        }
                    }
                    constructor0.setAccessible(true);
                    this.setLayoutManager(((LayoutManager)constructor0.newInstance(arr_object)));
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    throw new IllegalStateException(attributeSet0.getPositionDescription() + ": Unable to find LayoutManager " + s2, classNotFoundException0);
                }
                catch(InvocationTargetException invocationTargetException0) {
                    throw new IllegalStateException(attributeSet0.getPositionDescription() + ": Could not instantiate the LayoutManager: " + s2, invocationTargetException0);
                }
                catch(InstantiationException instantiationException0) {
                    throw new IllegalStateException(attributeSet0.getPositionDescription() + ": Could not instantiate the LayoutManager: " + s2, instantiationException0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    throw new IllegalStateException(attributeSet0.getPositionDescription() + ": Cannot access non-public constructor " + s2, illegalAccessException0);
                }
                catch(ClassCastException classCastException0) {
                    throw new IllegalStateException(attributeSet0.getPositionDescription() + ": Class is not a LayoutManager " + s2, classCastException0);
                }
            }
        }
    }

    void defaultOnMeasure(int v, int v1) {
        this.setMeasuredDimension(LayoutManager.chooseSize(v, this.getPaddingLeft() + this.getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(v1, this.getPaddingTop() + this.getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    private boolean didChildRangeChange(int v, int v1) {
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        return this.mMinMaxLayoutPositions[0] != v || this.mMinMaxLayoutPositions[1] != v1;
    }

    void dispatchChildAttached(View view0) {
        ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
        Adapter recyclerView$Adapter0 = this.mAdapter;
        if(recyclerView$Adapter0 != null && recyclerView$ViewHolder0 != null) {
            recyclerView$Adapter0.onViewAttachedToWindow(recyclerView$ViewHolder0);
        }
        List list0 = this.mOnChildAttachStateListeners;
        if(list0 != null) {
            for(int v = list0.size() - 1; v >= 0; --v) {
                ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(v)).onChildViewAttachedToWindow(view0);
            }
        }
    }

    void dispatchChildDetached(View view0) {
        ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
        Adapter recyclerView$Adapter0 = this.mAdapter;
        if(recyclerView$Adapter0 != null && recyclerView$ViewHolder0 != null) {
            recyclerView$Adapter0.onViewDetachedFromWindow(recyclerView$ViewHolder0);
        }
        List list0 = this.mOnChildAttachStateListeners;
        if(list0 != null) {
            for(int v = list0.size() - 1; v >= 0; --v) {
                ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(v)).onChildViewDetachedFromWindow(view0);
            }
        }
    }

    private void dispatchContentChangedIfNecessary() {
        int v = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if(v != 0 && this.isAccessibilityEnabled()) {
            AccessibilityEvent accessibilityEvent0 = AccessibilityEvent.obtain();
            accessibilityEvent0.setEventType(0x800);
            AccessibilityEventCompat.setContentChangeTypes(accessibilityEvent0, v);
            this.sendAccessibilityEventUnchecked(accessibilityEvent0);
        }
    }

    @Override  // android.view.ViewGroup
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        if(super.dispatchKeyEvent(keyEvent0)) {
            return true;
        }
        LayoutManager recyclerView$LayoutManager0 = this.getLayoutManager();
        int v = 0;
        if(recyclerView$LayoutManager0 == null) {
            return false;
        }
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            int v1 = keyEvent0.getKeyCode();
            switch(v1) {
                case 92: 
                case 93: {
                    int v2 = this.getMeasuredHeight();
                    if(v1 == 93) {
                        this.smoothScrollBy(0, v2, null, 0x80000000);
                        return true;
                    }
                    this.smoothScrollBy(0, -v2, null, 0x80000000);
                    return true;
                }
                case 0x7A: 
                case 0x7B: {
                    boolean z = recyclerView$LayoutManager0.isLayoutReversed();
                    if(v1 != 0x7A) {
                        if(!z) {
                            v = this.getAdapter().getItemCount();
                        }
                    }
                    else if(z) {
                        v = this.getAdapter().getItemCount();
                    }
                    this.smoothScrollToPosition(v);
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        if(recyclerView$LayoutManager0.canScrollHorizontally()) {
            int v3 = keyEvent0.getKeyCode();
            switch(v3) {
                case 92: 
                case 93: {
                    int v4 = this.getMeasuredWidth();
                    if(v3 == 93) {
                        this.smoothScrollBy(v4, 0, null, 0x80000000);
                        return true;
                    }
                    this.smoothScrollBy(-v4, 0, null, 0x80000000);
                    return true;
                }
                case 0x7A: 
                case 0x7B: {
                    boolean z1 = recyclerView$LayoutManager0.isLayoutReversed();
                    if(v3 != 0x7A) {
                        if(!z1) {
                            v = this.getAdapter().getItemCount();
                        }
                    }
                    else if(z1) {
                        v = this.getAdapter().getItemCount();
                    }
                    this.smoothScrollToPosition(v);
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }

    void dispatchLayout() {
        if(this.mAdapter == null) {
            Log.w("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if(this.mLayout == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        this.mState.mIsMeasuring = false;
        boolean z = this.mLastAutoMeasureSkippedDueToExact && (this.mLastAutoMeasureNonExactMeasuredWidth != this.getWidth() || this.mLastAutoMeasureNonExactMeasuredHeight != this.getHeight());
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mLastAutoMeasureSkippedDueToExact = false;
        if(this.mState.mLayoutStep == 1) {
            this.dispatchLayoutStep1();
            this.mLayout.setExactMeasureSpecsFrom(this);
            this.dispatchLayoutStep2();
        }
        else if(this.mAdapterHelper.hasUpdates() || z || this.mLayout.getWidth() != this.getWidth() || this.mLayout.getHeight() != this.getHeight()) {
            this.mLayout.setExactMeasureSpecsFrom(this);
            this.dispatchLayoutStep2();
        }
        else {
            this.mLayout.setExactMeasureSpecsFrom(this);
        }
        this.dispatchLayoutStep3();
    }

    private void dispatchLayoutStep1() {
        boolean z = true;
        this.mState.assertLayoutStep(1);
        this.fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        this.startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        this.onEnterLayoutOrScroll();
        this.processAdapterUpdatesAndSetAnimationFlags();
        this.saveFocusInfo();
        State recyclerView$State0 = this.mState;
        if(!recyclerView$State0.mRunSimpleAnimations || !this.mItemsChanged) {
            z = false;
        }
        recyclerView$State0.mTrackOldChangeHolders = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if(this.mState.mRunSimpleAnimations) {
            int v = this.mChildHelper.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getChildAt(v1));
                if(!recyclerView$ViewHolder0.shouldIgnore() && (!recyclerView$ViewHolder0.isInvalid() || this.mAdapter.hasStableIds())) {
                    ItemAnimator recyclerView$ItemAnimator0 = this.mItemAnimator;
                    int v2 = ItemAnimator.buildAdapterChangeFlagsForAnimations(recyclerView$ViewHolder0);
                    List list0 = recyclerView$ViewHolder0.getUnmodifiedPayloads();
                    ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0 = recyclerView$ItemAnimator0.recordPreLayoutInformation(this.mState, recyclerView$ViewHolder0, v2, list0);
                    this.mViewInfoStore.addToPreLayout(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0);
                    if(this.mState.mTrackOldChangeHolders && recyclerView$ViewHolder0.isUpdated() && !recyclerView$ViewHolder0.isRemoved() && !recyclerView$ViewHolder0.shouldIgnore() && !recyclerView$ViewHolder0.isInvalid()) {
                        long v3 = this.getChangedHolderKey(recyclerView$ViewHolder0);
                        this.mViewInfoStore.addToOldChangeHolders(v3, recyclerView$ViewHolder0);
                    }
                }
            }
        }
        if(this.mState.mRunPredictiveAnimations) {
            this.saveOldPositions();
            boolean z1 = this.mState.mStructureChanged;
            this.mState.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = z1;
            for(int v4 = 0; v4 < this.mChildHelper.getChildCount(); ++v4) {
                ViewHolder recyclerView$ViewHolder1 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getChildAt(v4));
                if(!recyclerView$ViewHolder1.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(recyclerView$ViewHolder1)) {
                    int v5 = ItemAnimator.buildAdapterChangeFlagsForAnimations(recyclerView$ViewHolder1);
                    boolean z2 = recyclerView$ViewHolder1.hasAnyOfTheFlags(0x2000);
                    if(!z2) {
                        v5 |= 0x1000;
                    }
                    ItemAnimator recyclerView$ItemAnimator1 = this.mItemAnimator;
                    List list1 = recyclerView$ViewHolder1.getUnmodifiedPayloads();
                    ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1 = recyclerView$ItemAnimator1.recordPreLayoutInformation(this.mState, recyclerView$ViewHolder1, v5, list1);
                    if(z2) {
                        this.recordAnimationInfoIfBouncedHiddenView(recyclerView$ViewHolder1, recyclerView$ItemAnimator$ItemHolderInfo1);
                    }
                    else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(recyclerView$ViewHolder1, recyclerView$ItemAnimator$ItemHolderInfo1);
                    }
                }
            }
        }
        this.clearOldPositions();
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private void dispatchLayoutStep2() {
        this.startInterceptRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        if(this.mPendingSavedState != null && this.mAdapter.canRestoreState()) {
            if(this.mPendingSavedState.mLayoutState != null) {
                this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
            }
            this.mPendingSavedState = null;
        }
        this.mState.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
        this.mState.mStructureChanged = false;
        this.mState.mRunSimpleAnimations = this.mState.mRunSimpleAnimations && this.mItemAnimator != null;
        this.mState.mLayoutStep = 4;
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        this.startInterceptRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.mLayoutStep = 1;
        if(this.mState.mRunSimpleAnimations) {
            for(int v = this.mChildHelper.getChildCount() - 1; v >= 0; --v) {
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getChildAt(v));
                if(!recyclerView$ViewHolder0.shouldIgnore()) {
                    long v1 = this.getChangedHolderKey(recyclerView$ViewHolder0);
                    ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0 = this.mItemAnimator.recordPostLayoutInformation(this.mState, recyclerView$ViewHolder0);
                    ViewHolder recyclerView$ViewHolder1 = this.mViewInfoStore.getFromOldChangeHolders(v1);
                    if(recyclerView$ViewHolder1 == null || recyclerView$ViewHolder1.shouldIgnore()) {
                        this.mViewInfoStore.addToPostLayout(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0);
                    }
                    else {
                        boolean z = this.mViewInfoStore.isDisappearing(recyclerView$ViewHolder1);
                        boolean z1 = this.mViewInfoStore.isDisappearing(recyclerView$ViewHolder0);
                        if(!z || recyclerView$ViewHolder1 != recyclerView$ViewHolder0) {
                            ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1 = this.mViewInfoStore.popFromPreLayout(recyclerView$ViewHolder1);
                            this.mViewInfoStore.addToPostLayout(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0);
                            ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2 = this.mViewInfoStore.popFromPostLayout(recyclerView$ViewHolder0);
                            if(recyclerView$ItemAnimator$ItemHolderInfo1 == null) {
                                this.handleMissingPreInfoForChangeError(v1, recyclerView$ViewHolder0, recyclerView$ViewHolder1);
                            }
                            else {
                                this.animateChange(recyclerView$ViewHolder1, recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo1, recyclerView$ItemAnimator$ItemHolderInfo2, z, z1);
                            }
                        }
                        else {
                            this.mViewInfoStore.addToPostLayout(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0);
                        }
                    }
                }
            }
            this.mViewInfoStore.process(this.mViewInfoProcessCallback);
        }
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mState.mRunSimpleAnimations = false;
        this.mState.mRunPredictiveAnimations = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        if(this.mRecycler.mChangedScrap != null) {
            this.mRecycler.mChangedScrap.clear();
        }
        if(this.mLayout.mPrefetchMaxObservedInInitialPrefetch) {
            this.mLayout.mPrefetchMaxCountObserved = 0;
            this.mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
            this.mRecycler.updateViewCacheSize();
        }
        this.mLayout.onLayoutCompleted(this.mState);
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
        this.mViewInfoStore.clear();
        if(this.didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
            this.dispatchOnScrolled(0, 0);
        }
        this.recoverFocusFromState();
        this.resetFocusInfo();
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f1, boolean z) {
        return this.getScrollingChildHelper().dispatchNestedFling(f, f1, z);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f1) {
        return this.getScrollingChildHelper().dispatchNestedPreFling(f, f1);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int v, int v1, int[] arr_v, int[] arr_v1) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(v, v1, arr_v, arr_v1);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int v, int v1, int[] arr_v, int[] arr_v1, int v2) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(v, v1, arr_v, arr_v1, v2);
    }

    @Override  // androidx.core.view.NestedScrollingChild3
    public final void dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v, int v4, int[] arr_v1) {
        this.getScrollingChildHelper().dispatchNestedScroll(v, v1, v2, v3, arr_v, v4, arr_v1);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v) {
        return this.getScrollingChildHelper().dispatchNestedScroll(v, v1, v2, v3, arr_v);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v, int v4) {
        return this.getScrollingChildHelper().dispatchNestedScroll(v, v1, v2, v3, arr_v, v4);
    }

    void dispatchOnScrollStateChanged(int v) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.onScrollStateChanged(v);
        }
        OnScrollListener recyclerView$OnScrollListener0 = this.mScrollListener;
        if(recyclerView$OnScrollListener0 != null) {
            recyclerView$OnScrollListener0.onScrollStateChanged(this, v);
        }
        List list0 = this.mScrollListeners;
        if(list0 != null) {
            for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                ((OnScrollListener)this.mScrollListeners.get(v1)).onScrollStateChanged(this, v);
            }
        }
    }

    void dispatchOnScrolled(int v, int v1) {
        ++this.mDispatchScrollCounter;
        int v2 = this.getScrollX();
        int v3 = this.getScrollY();
        this.onScrollChanged(v2, v3, v2 - v, v3 - v1);
        OnScrollListener recyclerView$OnScrollListener0 = this.mScrollListener;
        if(recyclerView$OnScrollListener0 != null) {
            recyclerView$OnScrollListener0.onScrolled(this, v, v1);
        }
        List list0 = this.mScrollListeners;
        if(list0 != null) {
            for(int v4 = list0.size() - 1; v4 >= 0; --v4) {
                ((OnScrollListener)this.mScrollListeners.get(v4)).onScrolled(this, v, v1);
            }
        }
        --this.mDispatchScrollCounter;
    }

    void dispatchPendingImportantForAccessibilityChanges() {
        for(int v = this.mPendingAccessibilityImportanceChange.size() - 1; v >= 0; --v) {
            ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mPendingAccessibilityImportanceChange.get(v);
            if(recyclerView$ViewHolder0.itemView.getParent() == this && !recyclerView$ViewHolder0.shouldIgnore()) {
                int v1 = recyclerView$ViewHolder0.mPendingAccessibilityState;
                if(v1 != -1) {
                    recyclerView$ViewHolder0.itemView.setImportantForAccessibility(v1);
                    recyclerView$ViewHolder0.mPendingAccessibilityState = -1;
                }
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    @Override  // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        this.onPopulateAccessibilityEvent(accessibilityEvent0);
        return true;
    }

    @Override  // android.view.ViewGroup
    protected void dispatchRestoreInstanceState(SparseArray sparseArray0) {
        this.dispatchThawSelfOnly(sparseArray0);
    }

    @Override  // android.view.ViewGroup
    protected void dispatchSaveInstanceState(SparseArray sparseArray0) {
        this.dispatchFreezeSelfOnly(sparseArray0);
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent motionEvent0) {
        OnItemTouchListener recyclerView$OnItemTouchListener0 = this.mInterceptingOnItemTouchListener;
        if(recyclerView$OnItemTouchListener0 == null) {
            return motionEvent0.getAction() == 0 ? false : this.findInterceptingOnItemTouchListener(motionEvent0);
        }
        recyclerView$OnItemTouchListener0.onTouchEvent(this, motionEvent0);
        switch(motionEvent0.getAction()) {
            case 1: 
            case 3: {
                this.mInterceptingOnItemTouchListener = null;
                return true;
            }
            default: {
                return true;
            }
        }
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        int v6;
        int v3;
        super.draw(canvas0);
        int v = this.mItemDecorations.size();
        int v1 = 0;
        for(int v2 = 0; true; ++v2) {
            v3 = 1;
            if(v2 >= v) {
                break;
            }
            ((ItemDecoration)this.mItemDecorations.get(v2)).onDrawOver(canvas0, this, this.mState);
        }
        if(this.mLeftGlow == null || this.mLeftGlow.isFinished()) {
            v6 = 0;
        }
        else {
            int v4 = canvas0.save();
            int v5 = this.mClipToPadding ? this.getPaddingBottom() : 0;
            canvas0.rotate(270.0f);
            canvas0.translate(((float)(v5 - this.getHeight())), 0.0f);
            v6 = this.mLeftGlow == null || !this.mLeftGlow.draw(canvas0) ? 0 : 1;
            canvas0.restoreToCount(v4);
        }
        if(this.mTopGlow != null && !this.mTopGlow.isFinished()) {
            int v7 = canvas0.save();
            if(this.mClipToPadding) {
                canvas0.translate(((float)this.getPaddingLeft()), ((float)this.getPaddingTop()));
            }
            v6 |= (this.mTopGlow == null || !this.mTopGlow.draw(canvas0) ? 0 : 1);
            canvas0.restoreToCount(v7);
        }
        if(this.mRightGlow != null && !this.mRightGlow.isFinished()) {
            int v8 = canvas0.save();
            int v9 = this.getWidth();
            int v10 = this.mClipToPadding ? this.getPaddingTop() : 0;
            canvas0.rotate(90.0f);
            canvas0.translate(((float)v10), ((float)(-v9)));
            v6 |= (this.mRightGlow == null || !this.mRightGlow.draw(canvas0) ? 0 : 1);
            canvas0.restoreToCount(v8);
        }
        if(this.mBottomGlow != null && !this.mBottomGlow.isFinished()) {
            int v11 = canvas0.save();
            canvas0.rotate(180.0f);
            if(this.mClipToPadding) {
                canvas0.translate(((float)(-this.getWidth() + this.getPaddingRight())), ((float)(-this.getHeight() + this.getPaddingBottom())));
            }
            else {
                canvas0.translate(((float)(-this.getWidth())), ((float)(-this.getHeight())));
            }
            if(this.mBottomGlow != null && this.mBottomGlow.draw(canvas0)) {
                v1 = 1;
            }
            v6 |= v1;
            canvas0.restoreToCount(v11);
        }
        if(v6 != 0 || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.isRunning()) {
            v3 = v6;
        }
        if(v3 != 0) {
            this.postInvalidateOnAnimation();
        }
    }

    @Override  // android.view.ViewGroup
    public boolean drawChild(Canvas canvas0, View view0, long v) {
        return super.drawChild(canvas0, view0, v);
    }

    void ensureBottomGlow() {
        if(this.mBottomGlow != null) {
            return;
        }
        EdgeEffect edgeEffect0 = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
        this.mBottomGlow = edgeEffect0;
        if(this.mClipToPadding) {
            edgeEffect0.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            return;
        }
        edgeEffect0.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
    }

    void ensureLeftGlow() {
        if(this.mLeftGlow != null) {
            return;
        }
        EdgeEffect edgeEffect0 = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
        this.mLeftGlow = edgeEffect0;
        if(this.mClipToPadding) {
            edgeEffect0.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            return;
        }
        edgeEffect0.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
    }

    void ensureRightGlow() {
        if(this.mRightGlow != null) {
            return;
        }
        EdgeEffect edgeEffect0 = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
        this.mRightGlow = edgeEffect0;
        if(this.mClipToPadding) {
            edgeEffect0.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            return;
        }
        edgeEffect0.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
    }

    void ensureTopGlow() {
        if(this.mTopGlow != null) {
            return;
        }
        EdgeEffect edgeEffect0 = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
        this.mTopGlow = edgeEffect0;
        if(this.mClipToPadding) {
            edgeEffect0.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            return;
        }
        edgeEffect0.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
    }

    String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + this.getContext();
    }

    final void fillRemainingScrollValues(State recyclerView$State0) {
        if(this.getScrollState() == 2) {
            OverScroller overScroller0 = this.mViewFlinger.mOverScroller;
            recyclerView$State0.mRemainingScrollHorizontal = overScroller0.getFinalX() - overScroller0.getCurrX();
            recyclerView$State0.mRemainingScrollVertical = overScroller0.getFinalY() - overScroller0.getCurrY();
            return;
        }
        recyclerView$State0.mRemainingScrollHorizontal = 0;
        recyclerView$State0.mRemainingScrollVertical = 0;
    }

    public View findChildViewUnder(float f, float f1) {
        for(int v = this.mChildHelper.getChildCount() - 1; v >= 0; --v) {
            View view0 = this.mChildHelper.getChildAt(v);
            float f2 = view0.getTranslationX();
            float f3 = view0.getTranslationY();
            if(f >= ((float)view0.getLeft()) + f2 && f <= ((float)view0.getRight()) + f2 && f1 >= ((float)view0.getTop()) + f3 && f1 <= ((float)view0.getBottom()) + f3) {
                return view0;
            }
        }
        return null;
    }

    public View findContainingItemView(View view0) {
        ViewParent viewParent0;
        for(viewParent0 = view0.getParent(); viewParent0 != null && viewParent0 != this && viewParent0 instanceof View; viewParent0 = view0.getParent()) {
            view0 = (View)viewParent0;
        }
        return viewParent0 == this ? view0 : null;
    }

    public ViewHolder findContainingViewHolder(View view0) {
        View view1 = this.findContainingItemView(view0);
        return view1 == null ? null : this.getChildViewHolder(view1);
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent0) {
        int v = motionEvent0.getAction();
        int v1 = this.mOnItemTouchListeners.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            OnItemTouchListener recyclerView$OnItemTouchListener0 = (OnItemTouchListener)this.mOnItemTouchListeners.get(v2);
            if(recyclerView$OnItemTouchListener0.onInterceptTouchEvent(this, motionEvent0) && v != 3) {
                this.mInterceptingOnItemTouchListener = recyclerView$OnItemTouchListener0;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] arr_v) {
        int v = this.mChildHelper.getChildCount();
        if(v == 0) {
            arr_v[0] = -1;
            arr_v[1] = -1;
            return;
        }
        int v1 = 0x7FFFFFFF;
        int v2 = 0x80000000;
        for(int v3 = 0; v3 < v; ++v3) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getChildAt(v3));
            if(!recyclerView$ViewHolder0.shouldIgnore()) {
                int v4 = recyclerView$ViewHolder0.getLayoutPosition();
                if(v4 < v1) {
                    v1 = v4;
                }
                if(v4 > v2) {
                    v2 = v4;
                }
            }
        }
        arr_v[0] = v1;
        arr_v[1] = v2;
    }

    static RecyclerView findNestedRecyclerView(View view0) {
        if(!(view0 instanceof ViewGroup)) {
            return null;
        }
        if(view0 instanceof RecyclerView) {
            return (RecyclerView)view0;
        }
        int v = ((ViewGroup)view0).getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            RecyclerView recyclerView0 = RecyclerView.findNestedRecyclerView(((ViewGroup)view0).getChildAt(v1));
            if(recyclerView0 != null) {
                return recyclerView0;
            }
        }
        return null;
    }

    private View findNextViewToFocus() {
        int v = this.mState.mFocusedItemPosition == -1 ? 0 : this.mState.mFocusedItemPosition;
        int v1 = this.mState.getItemCount();
        for(int v2 = v; v2 < v1; ++v2) {
            ViewHolder recyclerView$ViewHolder0 = this.findViewHolderForAdapterPosition(v2);
            if(recyclerView$ViewHolder0 == null) {
                break;
            }
            if(recyclerView$ViewHolder0.itemView.hasFocusable()) {
                return recyclerView$ViewHolder0.itemView;
            }
        }
        for(int v3 = Math.min(v1, v) - 1; v3 >= 0; --v3) {
            ViewHolder recyclerView$ViewHolder1 = this.findViewHolderForAdapterPosition(v3);
            if(recyclerView$ViewHolder1 == null) {
                return null;
            }
            if(recyclerView$ViewHolder1.itemView.hasFocusable()) {
                return recyclerView$ViewHolder1.itemView;
            }
        }
        return null;
    }

    public ViewHolder findViewHolderForAdapterPosition(int v) {
        ViewHolder recyclerView$ViewHolder0 = null;
        if(this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int v1 = this.mChildHelper.getUnfilteredChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            ViewHolder recyclerView$ViewHolder1 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v2));
            if(recyclerView$ViewHolder1 != null && !recyclerView$ViewHolder1.isRemoved() && this.getAdapterPositionInRecyclerView(recyclerView$ViewHolder1) == v) {
                if(this.mChildHelper.isHidden(recyclerView$ViewHolder1.itemView)) {
                    recyclerView$ViewHolder0 = recyclerView$ViewHolder1;
                    continue;
                }
                return recyclerView$ViewHolder1;
            }
        }
        return recyclerView$ViewHolder0;
    }

    public ViewHolder findViewHolderForItemId(long v) {
        ViewHolder recyclerView$ViewHolder0 = null;
        if(this.mAdapter != null && this.mAdapter.hasStableIds()) {
            int v1 = this.mChildHelper.getUnfilteredChildCount();
            for(int v2 = 0; v2 < v1; ++v2) {
                ViewHolder recyclerView$ViewHolder1 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v2));
                if(recyclerView$ViewHolder1 != null && !recyclerView$ViewHolder1.isRemoved() && recyclerView$ViewHolder1.getItemId() == v) {
                    if(this.mChildHelper.isHidden(recyclerView$ViewHolder1.itemView)) {
                        recyclerView$ViewHolder0 = recyclerView$ViewHolder1;
                        continue;
                    }
                    return recyclerView$ViewHolder1;
                }
            }
        }
        return recyclerView$ViewHolder0;
    }

    public ViewHolder findViewHolderForLayoutPosition(int v) {
        return this.findViewHolderForPosition(v, false);
    }

    @Deprecated
    public ViewHolder findViewHolderForPosition(int v) {
        return this.findViewHolderForPosition(v, false);
    }

    ViewHolder findViewHolderForPosition(int v, boolean z) {
        int v1 = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder recyclerView$ViewHolder0 = null;
        int v2 = 0;
        while(v2 < v1) {
            ViewHolder recyclerView$ViewHolder1 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v2));
            if(recyclerView$ViewHolder1 != null && !recyclerView$ViewHolder1.isRemoved()) {
                if(z) {
                    if(recyclerView$ViewHolder1.mPosition == v) {
                        goto label_10;
                    }
                    else {
                        goto label_14;
                    }
                    goto label_9;
                }
                else {
                label_9:
                    if(recyclerView$ViewHolder1.getLayoutPosition() == v) {
                    label_10:
                        if(this.mChildHelper.isHidden(recyclerView$ViewHolder1.itemView)) {
                            recyclerView$ViewHolder0 = recyclerView$ViewHolder1;
                            goto label_14;
                        }
                        return recyclerView$ViewHolder1;
                    }
                }
            }
        label_14:
            ++v2;
        }
        return recyclerView$ViewHolder0;
    }

    private boolean fling(int v, int v1, int v2, int v3) {
        int v5;
        int v4;
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if(this.mLayoutSuppressed) {
            return false;
        }
        boolean z = recyclerView$LayoutManager0.canScrollHorizontally();
        boolean z1 = this.mLayout.canScrollVertically();
        if(!z || Math.abs(v) < v2) {
            v = 0;
        }
        if(!z1 || Math.abs(v1) < v2) {
            v1 = 0;
        }
        if(v == 0 && v1 == 0) {
            return false;
        }
        if(v == 0) {
            v4 = 0;
        }
        else if(this.mLeftGlow != null && EdgeEffectCompat.getDistance(this.mLeftGlow) != 0.0f) {
            if(this.shouldAbsorb(this.mLeftGlow, -v, this.getWidth())) {
                this.mLeftGlow.onAbsorb(-v);
                v = 0;
            }
            v4 = v;
            v = 0;
        }
        else if(this.mRightGlow == null || EdgeEffectCompat.getDistance(this.mRightGlow) == 0.0f) {
            v4 = 0;
        }
        else {
            if(this.shouldAbsorb(this.mRightGlow, v, this.getWidth())) {
                this.mRightGlow.onAbsorb(v);
                v = 0;
            }
            v4 = v;
            v = 0;
        }
        if(v1 == 0) {
            v5 = 0;
            v1 = 0;
        }
        else if(this.mTopGlow != null && EdgeEffectCompat.getDistance(this.mTopGlow) != 0.0f) {
            if(this.shouldAbsorb(this.mTopGlow, -v1, this.getHeight())) {
                this.mTopGlow.onAbsorb(-v1);
                v1 = 0;
            }
            v5 = 0;
        }
        else if(this.mBottomGlow == null || EdgeEffectCompat.getDistance(this.mBottomGlow) == 0.0f) {
            v5 = v1;
            v1 = 0;
        }
        else {
            if(this.shouldAbsorb(this.mBottomGlow, v1, this.getHeight())) {
                this.mBottomGlow.onAbsorb(v1);
                v1 = 0;
            }
            v5 = 0;
        }
        if(v4 != 0 || v1 != 0) {
            v4 = Math.max(-v3, Math.min(v4, v3));
            v1 = Math.max(-v3, Math.min(v1, v3));
            this.startNestedScrollForType(1);
            this.mViewFlinger.fling(v4, v1);
        }
        if(v == 0 && v5 == 0) {
            return v4 != 0 || v1 != 0;
        }
        if(!this.dispatchNestedPreFling(((float)v), ((float)v5))) {
            boolean z2 = z || z1;
            this.dispatchNestedFling(((float)v), ((float)v5), z2);
            if(this.mOnFlingListener != null && this.mOnFlingListener.onFling(v, v5)) {
                return true;
            }
            if(z2) {
                this.startNestedScrollForType(1);
                this.mViewFlinger.fling(Math.max(-v3, Math.min(v, v3)), Math.max(-v3, Math.min(v5, v3)));
                return true;
            }
        }
        return false;
    }

    public boolean fling(int v, int v1) {
        return this.fling(v, v1, this.mMinFlingVelocity, this.mMaxFlingVelocity);
    }

    boolean flingNoThresholdCheck(int v, int v1) {
        return this.fling(v, v1, 0, 0x7FFFFFFF);
    }

    @Override  // android.view.ViewGroup
    public View focusSearch(View view0, int v) {
        View view1;
        int v1 = 1;
        boolean z = this.mAdapter != null && this.mLayout != null && !this.isComputingLayout() && !this.mLayoutSuppressed;
        FocusFinder focusFinder0 = FocusFinder.getInstance();
        if(!z || v != 1 && v != 2) {
            View view2 = focusFinder0.findNextFocus(this, view0, v);
            if(view2 != null || !z) {
                view1 = view2;
            }
            else {
                this.consumePendingUpdateOperations();
                if(this.findContainingItemView(view0) == null) {
                    return null;
                }
                this.startInterceptRequestLayout();
                view1 = this.mLayout.onFocusSearchFailed(view0, v, this.mRecycler, this.mState);
                this.stopInterceptRequestLayout(false);
            }
        }
        else {
            int v2 = !this.mLayout.canScrollVertically() || focusFinder0.findNextFocus(this, view0, (v == 2 ? 130 : 33)) != null ? 0 : 1;
            if(v2 == 0 && this.mLayout.canScrollHorizontally()) {
                if(focusFinder0.findNextFocus(this, view0, (((this.mLayout.getLayoutDirection() == 1 ? 1 : 0) ^ (v == 2 ? 1 : 0)) == 0 ? 17 : 66)) != null) {
                    v1 = 0;
                }
                v2 = v1;
            }
            if(v2 != 0) {
                this.consumePendingUpdateOperations();
                if(this.findContainingItemView(view0) == null) {
                    return null;
                }
                this.startInterceptRequestLayout();
                this.mLayout.onFocusSearchFailed(view0, v, this.mRecycler, this.mState);
                this.stopInterceptRequestLayout(false);
            }
            view1 = focusFinder0.findNextFocus(this, view0, v);
        }
        if(view1 != null && !view1.hasFocusable()) {
            if(this.getFocusedChild() == null) {
                return super.focusSearch(view0, v);
            }
            this.requestChildOnScreen(view1, null);
            return view0;
        }
        return this.isPreferredNextFocus(view0, view1, v) ? view1 : super.focusSearch(view0, v);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
        }
        return recyclerView$LayoutManager0.generateDefaultLayoutParams();
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
        }
        return recyclerView$LayoutManager0.generateLayoutParams(this.getContext(), attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
        }
        return recyclerView$LayoutManager0.generateLayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // android.view.ViewGroup
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public Adapter getAdapter() {
        return this.mAdapter;
    }

    // 去混淆评级： 低(20)
    int getAdapterPositionInRecyclerView(ViewHolder recyclerView$ViewHolder0) {
        return recyclerView$ViewHolder0.hasAnyOfTheFlags(0x20C) || !recyclerView$ViewHolder0.isBound() ? -1 : this.mAdapterHelper.applyPendingUpdatesToPosition(recyclerView$ViewHolder0.mPosition);
    }

    @Override  // android.view.View
    public int getBaseline() {
        return this.mLayout == null ? super.getBaseline() : -1;
    }

    // 去混淆评级： 低(20)
    long getChangedHolderKey(ViewHolder recyclerView$ViewHolder0) {
        return this.mAdapter.hasStableIds() ? recyclerView$ViewHolder0.getItemId() : ((long)recyclerView$ViewHolder0.mPosition);
    }

    public int getChildAdapterPosition(View view0) {
        ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
        return recyclerView$ViewHolder0 == null ? -1 : recyclerView$ViewHolder0.getAbsoluteAdapterPosition();
    }

    @Override  // android.view.ViewGroup
    protected int getChildDrawingOrder(int v, int v1) {
        return this.mChildDrawingOrderCallback == null ? super.getChildDrawingOrder(v, v1) : this.mChildDrawingOrderCallback.onGetChildDrawingOrder(v, v1);
    }

    public long getChildItemId(View view0) {
        if(this.mAdapter != null && this.mAdapter.hasStableIds()) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            return recyclerView$ViewHolder0 == null ? -1L : recyclerView$ViewHolder0.getItemId();
        }
        return -1L;
    }

    public int getChildLayoutPosition(View view0) {
        ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
        return recyclerView$ViewHolder0 == null ? -1 : recyclerView$ViewHolder0.getLayoutPosition();
    }

    @Deprecated
    public int getChildPosition(View view0) {
        return this.getChildAdapterPosition(view0);
    }

    public ViewHolder getChildViewHolder(View view0) {
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 != null && viewParent0 != this) {
            throw new IllegalArgumentException("View " + view0 + " is not a direct child of " + this);
        }
        return RecyclerView.getChildViewHolderInt(view0);
    }

    static ViewHolder getChildViewHolderInt(View view0) {
        return view0 == null ? null : ((LayoutParams)view0.getLayoutParams()).mViewHolder;
    }

    @Override  // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view0, Rect rect0) {
        RecyclerView.getDecoratedBoundsWithMarginsInt(view0, rect0);
    }

    static void getDecoratedBoundsWithMarginsInt(View view0, Rect rect0) {
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        rect0.set(view0.getLeft() - recyclerView$LayoutParams0.mDecorInsets.left - recyclerView$LayoutParams0.leftMargin, view0.getTop() - recyclerView$LayoutParams0.mDecorInsets.top - recyclerView$LayoutParams0.topMargin, view0.getRight() + recyclerView$LayoutParams0.mDecorInsets.right + recyclerView$LayoutParams0.rightMargin, view0.getBottom() + recyclerView$LayoutParams0.mDecorInsets.bottom + recyclerView$LayoutParams0.bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view0) {
        int v = view0.getId();
        while(!view0.isFocused() && view0 instanceof ViewGroup && view0.hasFocus()) {
            view0 = ((ViewGroup)view0).getFocusedChild();
            if(view0.getId() != -1) {
                v = view0.getId();
            }
        }
        return v;
    }

    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    private String getFullClassName(Context context0, String s) {
        if(s.charAt(0) == 46) {
            return "com.ruliweb.www.ruliapp" + s;
        }
        return s.contains(".") ? s : RecyclerView.class.getPackage().getName() + '.' + s;
    }

    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    Rect getItemDecorInsetsForChild(View view0) {
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(!recyclerView$LayoutParams0.mInsetsDirty) {
            return recyclerView$LayoutParams0.mDecorInsets;
        }
        if(this.mState.isPreLayout() && (recyclerView$LayoutParams0.isItemChanged() || recyclerView$LayoutParams0.isViewInvalid())) {
            return recyclerView$LayoutParams0.mDecorInsets;
        }
        Rect rect0 = recyclerView$LayoutParams0.mDecorInsets;
        rect0.set(0, 0, 0, 0);
        int v = this.mItemDecorations.size();
        for(int v1 = 0; v1 < v; ++v1) {
            this.mTempRect.set(0, 0, 0, 0);
            ((ItemDecoration)this.mItemDecorations.get(v1)).getItemOffsets(this.mTempRect, view0, this, this.mState);
            rect0.left += this.mTempRect.left;
            rect0.top += this.mTempRect.top;
            rect0.right += this.mTempRect.right;
            rect0.bottom += this.mTempRect.bottom;
        }
        recyclerView$LayoutParams0.mInsetsDirty = false;
        return rect0;
    }

    public ItemDecoration getItemDecorationAt(int v) {
        int v1 = this.getItemDecorationCount();
        if(v < 0 || v >= v1) {
            throw new IndexOutOfBoundsException(v + " is an invalid index for size " + v1);
        }
        return (ItemDecoration)this.mItemDecorations.get(v);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    // 去混淆评级： 低(20)
    long getNanoTime() [...] // 潜在的解密器

    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if(this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private float getSplineFlingDistance(int v) {
        return (float)(((double)(this.mPhysicalCoef * 0.015f)) * Math.exp(((double)RecyclerView.DECELERATION_RATE) / (((double)RecyclerView.DECELERATION_RATE) - 1.0) * Math.log(((float)Math.abs(v)) * 0.35f / (this.mPhysicalCoef * 0.015f))));
    }

    private void handleMissingPreInfoForChangeError(long v, ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1) {
        int v1 = this.mChildHelper.getChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            ViewHolder recyclerView$ViewHolder2 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getChildAt(v2));
            if(recyclerView$ViewHolder2 != recyclerView$ViewHolder0 && this.getChangedHolderKey(recyclerView$ViewHolder2) == v) {
                throw this.mAdapter == null || !this.mAdapter.hasStableIds() ? new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + recyclerView$ViewHolder2 + " \n View Holder 2:" + recyclerView$ViewHolder0 + this.exceptionLabel()) : new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + recyclerView$ViewHolder2 + " \n View Holder 2:" + recyclerView$ViewHolder0 + this.exceptionLabel());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + recyclerView$ViewHolder1 + " cannot be found but it is necessary for " + recyclerView$ViewHolder0 + this.exceptionLabel());
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.getScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int v) {
        return this.getScrollingChildHelper().hasNestedScrollingParent(v);
    }

    // 去混淆评级： 低(30)
    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    private boolean hasUpdatedView() {
        int v = this.mChildHelper.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getChildAt(v1));
            if(recyclerView$ViewHolder0 != null && !recyclerView$ViewHolder0.shouldIgnore() && recyclerView$ViewHolder0.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new androidx.recyclerview.widget.AdapterHelper.Callback() {
            void dispatchUpdate(UpdateOp adapterHelper$UpdateOp0) {
                switch(adapterHelper$UpdateOp0.cmd) {
                    case 1: {
                        RecyclerView.this.mLayout.onItemsAdded(RecyclerView.this, adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                        return;
                    }
                    case 2: {
                        RecyclerView.this.mLayout.onItemsRemoved(RecyclerView.this, adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                        return;
                    }
                    case 4: {
                        RecyclerView.this.mLayout.onItemsUpdated(RecyclerView.this, adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount, adapterHelper$UpdateOp0.payload);
                        return;
                    }
                    case 8: {
                        RecyclerView.this.mLayout.onItemsMoved(RecyclerView.this, adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount, 1);
                    }
                }
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public ViewHolder findViewHolder(int v) {
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.this.findViewHolderForPosition(v, true);
                if(recyclerView$ViewHolder0 == null) {
                    return null;
                }
                if(RecyclerView.this.mChildHelper.isHidden(recyclerView$ViewHolder0.itemView)) {
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "assuming view holder cannot be find because it is hidden");
                    }
                    return null;
                }
                return recyclerView$ViewHolder0;
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void markViewHoldersUpdated(int v, int v1, Object object0) {
                RecyclerView.this.viewRangeUpdate(v, v1, object0);
                RecyclerView.this.mItemsChanged = true;
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void offsetPositionsForAdd(int v, int v1) {
                RecyclerView.this.offsetPositionRecordsForInsert(v, v1);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void offsetPositionsForMove(int v, int v1) {
                RecyclerView.this.offsetPositionRecordsForMove(v, v1);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void offsetPositionsForRemovingInvisible(int v, int v1) {
                RecyclerView.this.offsetPositionRecordsForRemove(v, v1, true);
                RecyclerView.this.mItemsAddedOrRemoved = true;
                RecyclerView.this.mState.mDeletedInvisibleItemCountSincePreviousLayout += v1;
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void offsetPositionsForRemovingLaidOutOrNewView(int v, int v1) {
                RecyclerView.this.offsetPositionRecordsForRemove(v, v1, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void onDispatchFirstPass(UpdateOp adapterHelper$UpdateOp0) {
                this.dispatchUpdate(adapterHelper$UpdateOp0);
            }

            @Override  // androidx.recyclerview.widget.AdapterHelper$Callback
            public void onDispatchSecondPass(UpdateOp adapterHelper$UpdateOp0) {
                this.dispatchUpdate(adapterHelper$UpdateOp0);
            }
        });
    }

    private void initAutofill() {
        if(ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new androidx.recyclerview.widget.ChildHelper.Callback() {
            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void addView(View view0, int v) {
                RecyclerView.this.addView(view0, v);
                RecyclerView.this.dispatchChildAttached(view0);
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void attachViewToParent(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
                if(recyclerView$ViewHolder0 != null) {
                    if(!recyclerView$ViewHolder0.isTmpDetached() && !recyclerView$ViewHolder0.shouldIgnore()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + recyclerView$ViewHolder0 + RecyclerView.this.exceptionLabel());
                    }
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "reAttach " + recyclerView$ViewHolder0);
                    }
                    recyclerView$ViewHolder0.clearTmpDetachFlag();
                }
                else if(!RecyclerView.sDebugAssertionsEnabled) {
                }
                else {
                    throw new IllegalArgumentException("No ViewHolder found for child: " + view0 + ", index: " + v + RecyclerView.this.exceptionLabel());
                }
                RecyclerView.this.attachViewToParent(view0, v, viewGroup$LayoutParams0);
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void detachViewFromParent(int v) {
                View view0 = this.getChildAt(v);
                if(view0 != null) {
                    ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
                    if(recyclerView$ViewHolder0 == null) {
                        RecyclerView.this.detachViewFromParent(v);
                        return;
                    }
                    if(recyclerView$ViewHolder0.isTmpDetached() && !recyclerView$ViewHolder0.shouldIgnore()) {
                        throw new IllegalArgumentException("called detach on an already detached child " + recyclerView$ViewHolder0 + RecyclerView.this.exceptionLabel());
                    }
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "tmpDetach " + recyclerView$ViewHolder0);
                    }
                    recyclerView$ViewHolder0.addFlags(0x100);
                }
                else if(!RecyclerView.sDebugAssertionsEnabled) {
                }
                else {
                    throw new IllegalArgumentException("No view at offset " + v + RecyclerView.this.exceptionLabel());
                }
                RecyclerView.this.detachViewFromParent(v);
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public View getChildAt(int v) {
                return RecyclerView.this.getChildAt(v);
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public ViewHolder getChildViewHolder(View view0) {
                return RecyclerView.getChildViewHolderInt(view0);
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public int indexOfChild(View view0) {
                return RecyclerView.this.indexOfChild(view0);
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void onEnteredHiddenState(View view0) {
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
                if(recyclerView$ViewHolder0 != null) {
                    recyclerView$ViewHolder0.onEnteredHiddenState(RecyclerView.this);
                }
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void onLeftHiddenState(View view0) {
                ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
                if(recyclerView$ViewHolder0 != null) {
                    recyclerView$ViewHolder0.onLeftHiddenState(RecyclerView.this);
                }
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void removeAllViews() {
                int v = this.getChildCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    View view0 = this.getChildAt(v1);
                    RecyclerView.this.dispatchChildDetached(view0);
                    view0.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            @Override  // androidx.recyclerview.widget.ChildHelper$Callback
            public void removeViewAt(int v) {
                View view0 = RecyclerView.this.getChildAt(v);
                if(view0 != null) {
                    RecyclerView.this.dispatchChildDetached(view0);
                    view0.clearAnimation();
                }
                RecyclerView.this.removeViewAt(v);
            }
        });
    }

    void initFastScroller(StateListDrawable stateListDrawable0, Drawable drawable0, StateListDrawable stateListDrawable1, Drawable drawable1) {
        if(stateListDrawable0 == null || drawable0 == null || stateListDrawable1 == null || drawable1 == null) {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + this.exceptionLabel());
        }
        Resources resources0 = this.getContext().getResources();
        new FastScroller(this, stateListDrawable0, drawable0, stateListDrawable1, drawable1, resources0.getDimensionPixelSize(dimen.fastscroll_default_thickness), resources0.getDimensionPixelSize(dimen.fastscroll_minimum_range), resources0.getDimensionPixelOffset(dimen.fastscroll_margin));
    }

    void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if(this.mItemDecorations.size() == 0) {
            return;
        }
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }

    boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled();
    }

    public boolean isAnimating() {
        return this.mItemAnimator != null && this.mItemAnimator.isRunning();
    }

    @Override  // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return this.isLayoutSuppressed();
    }

    @Override  // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.getScrollingChildHelper().isNestedScrollingEnabled();
    }

    private boolean isPreferredNextFocus(View view0, View view1, int v) {
        int v7;
        if(view1 == null || view1 == this || view1 == view0 || this.findContainingItemView(view1) == null) {
            return false;
        }
        if(view0 == null) {
            return true;
        }
        if(this.findContainingItemView(view0) == null) {
            return true;
        }
        int v1 = view0.getWidth();
        int v2 = view0.getHeight();
        this.mTempRect.set(0, 0, v1, v2);
        int v3 = view1.getWidth();
        int v4 = view1.getHeight();
        this.mTempRect2.set(0, 0, v3, v4);
        this.offsetDescendantRectToMyCoords(view0, this.mTempRect);
        this.offsetDescendantRectToMyCoords(view1, this.mTempRect2);
        int v5 = -1;
        int v6 = this.mLayout.getLayoutDirection() == 1 ? -1 : 1;
        if(this.mTempRect.left >= this.mTempRect2.left && this.mTempRect.right > this.mTempRect2.left || this.mTempRect.right >= this.mTempRect2.right) {
            v7 = this.mTempRect.right <= this.mTempRect2.right && this.mTempRect.left < this.mTempRect2.right || this.mTempRect.left <= this.mTempRect2.left ? 0 : -1;
        }
        else {
            v7 = 1;
        }
        if((this.mTempRect.top < this.mTempRect2.top || this.mTempRect.bottom <= this.mTempRect2.top) && this.mTempRect.bottom < this.mTempRect2.bottom) {
            v5 = 1;
        }
        else if(this.mTempRect.bottom <= this.mTempRect2.bottom && this.mTempRect.top < this.mTempRect2.bottom || this.mTempRect.top <= this.mTempRect2.top) {
            v5 = 0;
        }
        switch(v) {
            case 1: {
                return v5 < 0 || v5 == 0 && v7 * v6 < 0;
            }
            case 2: {
                return v5 > 0 || v5 == 0 && v7 * v6 > 0;
            }
            case 17: {
                return v7 < 0;
            }
            case 33: {
                return v5 < 0;
            }
            case 66: {
                return v7 > 0;
            }
            case 130: {
                return v5 > 0;
            }
            default: {
                throw new IllegalArgumentException("Invalid direction: " + v + this.exceptionLabel());
            }
        }
    }

    void jumpToPositionForSmoothScroller(int v) {
        if(this.mLayout == null) {
            return;
        }
        this.setScrollState(2);
        this.mLayout.scrollToPosition(v);
        this.awakenScrollBars();
    }

    void markItemDecorInsetsDirty() {
        int v = this.mChildHelper.getUnfilteredChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            ((LayoutParams)this.mChildHelper.getUnfilteredChildAt(v1).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }

    void markKnownViewsInvalid() {
        int v = this.mChildHelper.getUnfilteredChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v1));
            if(recyclerView$ViewHolder0 != null && !recyclerView$ViewHolder0.shouldIgnore()) {
                recyclerView$ViewHolder0.addFlags(6);
            }
        }
        this.markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }

    public void nestedScrollBy(int v, int v1) {
        this.nestedScrollByInternal(v, v1, null, 1);
    }

    private void nestedScrollByInternal(int v, int v1, MotionEvent motionEvent0, int v2) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if(this.mLayoutSuppressed) {
            return;
        }
        int v3 = 0;
        this.mReusableIntPair[0] = 0;
        this.mReusableIntPair[1] = 0;
        boolean z = recyclerView$LayoutManager0.canScrollHorizontally();
        boolean z1 = this.mLayout.canScrollVertically();
        int v4 = z1 ? z | 2 : z;
        float f = motionEvent0 == null ? ((float)this.getHeight()) / 2.0f : motionEvent0.getY();
        float f1 = motionEvent0 == null ? ((float)this.getWidth()) / 2.0f : motionEvent0.getX();
        int v5 = v - this.releaseHorizontalGlow(v, f);
        int v6 = v1 - this.releaseVerticalGlow(v1, f1);
        this.startNestedScroll(v4, v2);
        if(this.dispatchNestedPreScroll((z ? v5 : 0), (z1 ? v6 : 0), this.mReusableIntPair, this.mScrollOffset, v2)) {
            v5 -= this.mReusableIntPair[0];
            v6 -= this.mReusableIntPair[1];
        }
        if(z1) {
            v3 = v6;
        }
        this.scrollByInternal((z ? v5 : 0), v3, motionEvent0, v2);
        GapWorker gapWorker0 = this.mGapWorker;
        if(gapWorker0 != null && (v5 != 0 || v6 != 0)) {
            gapWorker0.postFromTraversal(this, v5, v6);
        }
        this.stopNestedScroll(v2);
    }

    public void offsetChildrenHorizontal(int v) {
        int v1 = this.mChildHelper.getChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            this.mChildHelper.getChildAt(v2).offsetLeftAndRight(v);
        }
    }

    public void offsetChildrenVertical(int v) {
        int v1 = this.mChildHelper.getChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            this.mChildHelper.getChildAt(v2).offsetTopAndBottom(v);
        }
    }

    void offsetPositionRecordsForInsert(int v, int v1) {
        int v2 = this.mChildHelper.getUnfilteredChildCount();
        for(int v3 = 0; v3 < v2; ++v3) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v3));
            if(recyclerView$ViewHolder0 != null && !recyclerView$ViewHolder0.shouldIgnore() && recyclerView$ViewHolder0.mPosition >= v) {
                if(RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("RecyclerView", "offsetPositionRecordsForInsert attached child " + v3 + " holder " + recyclerView$ViewHolder0 + " now at position " + (recyclerView$ViewHolder0.mPosition + v1));
                }
                recyclerView$ViewHolder0.offsetPosition(v1, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(v, v1);
        this.requestLayout();
    }

    void offsetPositionRecordsForMove(int v, int v1) {
        int v5;
        int v4;
        int v3;
        int v2 = this.mChildHelper.getUnfilteredChildCount();
        if(v < v1) {
            v3 = -1;
            v4 = v;
            v5 = v1;
        }
        else {
            v5 = v;
            v4 = v1;
            v3 = 1;
        }
        for(int v6 = 0; v6 < v2; ++v6) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v6));
            if(recyclerView$ViewHolder0 != null && recyclerView$ViewHolder0.mPosition >= v4 && recyclerView$ViewHolder0.mPosition <= v5) {
                if(RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("RecyclerView", "offsetPositionRecordsForMove attached child " + v6 + " holder " + recyclerView$ViewHolder0);
                }
                if(recyclerView$ViewHolder0.mPosition == v) {
                    recyclerView$ViewHolder0.offsetPosition(v1 - v, false);
                }
                else {
                    recyclerView$ViewHolder0.offsetPosition(v3, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(v, v1);
        this.requestLayout();
    }

    void offsetPositionRecordsForRemove(int v, int v1, boolean z) {
        int v2 = this.mChildHelper.getUnfilteredChildCount();
        for(int v3 = 0; v3 < v2; ++v3) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v3));
            if(recyclerView$ViewHolder0 != null && !recyclerView$ViewHolder0.shouldIgnore()) {
                if(recyclerView$ViewHolder0.mPosition >= v + v1) {
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "offsetPositionRecordsForRemove attached child " + v3 + " holder " + recyclerView$ViewHolder0 + " now at position " + (recyclerView$ViewHolder0.mPosition - v1));
                    }
                    recyclerView$ViewHolder0.offsetPosition(-v1, z);
                    this.mState.mStructureChanged = true;
                }
                else if(recyclerView$ViewHolder0.mPosition >= v) {
                    if(RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("RecyclerView", "offsetPositionRecordsForRemove attached child " + v3 + " holder " + recyclerView$ViewHolder0 + " now REMOVED");
                    }
                    recyclerView$ViewHolder0.flagRemovedAndOffsetPosition(v - 1, -v1, z);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(v, v1, z);
        this.requestLayout();
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        float f;
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        boolean z = true;
        this.mIsAttached = true;
        if(!this.mFirstLayoutComplete || this.isLayoutRequested()) {
            z = false;
        }
        this.mFirstLayoutComplete = z;
        this.mRecycler.onAttachedToWindow();
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
        if(RecyclerView.ALLOW_THREAD_GAP_WORK) {
            GapWorker gapWorker0 = (GapWorker)GapWorker.sGapWorker.get();
            this.mGapWorker = gapWorker0;
            if(gapWorker0 == null) {
                this.mGapWorker = new GapWorker();
                Display display0 = ViewCompat.getDisplay(this);
                if(this.isInEditMode() || display0 == null) {
                    f = 60.0f;
                }
                else {
                    f = display0.getRefreshRate();
                    if(f < 30.0f) {
                        f = 60.0f;
                    }
                }
                this.mGapWorker.mFrameIntervalNs = (long)(1000000000.0f / f);
                GapWorker.sGapWorker.set(this.mGapWorker);
            }
            this.mGapWorker.add(this);
        }
    }

    public void onChildAttachedToWindow(View view0) {
    }

    public void onChildDetachedFromWindow(View view0) {
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ItemAnimator recyclerView$ItemAnimator0 = this.mItemAnimator;
        if(recyclerView$ItemAnimator0 != null) {
            recyclerView$ItemAnimator0.endAnimations();
        }
        this.stopScroll();
        this.mIsAttached = false;
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        this.removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        this.mRecycler.onDetachedFromWindow();
        PoolingContainer.callPoolingContainerOnReleaseForChildren(this);
        if(RecyclerView.ALLOW_THREAD_GAP_WORK) {
            GapWorker gapWorker0 = this.mGapWorker;
            if(gapWorker0 != null) {
                gapWorker0.remove(this);
                this.mGapWorker = null;
            }
        }
    }

    @Override  // android.view.View
    public void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        int v = this.mItemDecorations.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((ItemDecoration)this.mItemDecorations.get(v1)).onDraw(canvas0, this, this.mState);
        }
    }

    void onEnterLayoutOrScroll() {
        ++this.mLayoutOrScrollCounter;
    }

    void onExitLayoutOrScroll() {
        this.onExitLayoutOrScroll(true);
    }

    void onExitLayoutOrScroll(boolean z) {
        int v = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = v;
        if(v < 1) {
            if(RecyclerView.sDebugAssertionsEnabled && v < 0) {
                throw new IllegalStateException("layout or scroll counter cannot go below zero.Some calls are not matching" + this.exceptionLabel());
            }
            this.mLayoutOrScrollCounter = 0;
            if(z) {
                this.dispatchContentChangedIfNecessary();
                this.dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    @Override  // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent0) {
        int v;
        boolean z;
        float f2;
        float f = 0.0f;
        if(this.mLayout == null) {
            return false;
        }
        if(this.mLayoutSuppressed) {
            return false;
        }
        if(motionEvent0.getAction() == 8) {
            if((motionEvent0.getSource() & 2) != 0) {
                float f1 = this.mLayout.canScrollVertically() ? -motionEvent0.getAxisValue(9) : 0.0f;
                if(this.mLayout.canScrollHorizontally()) {
                    f2 = motionEvent0.getAxisValue(10);
                    z = false;
                    f = f1;
                    v = 0;
                }
                else {
                    f = f1;
                    v = 0;
                    f2 = 0.0f;
                    z = false;
                }
            }
            else if((motionEvent0.getSource() & 0x400000) == 0) {
                v = 0;
                f2 = 0.0f;
                z = false;
            }
            else {
                v = 26;
                f2 = motionEvent0.getAxisValue(26);
                if(this.mLayout.canScrollVertically()) {
                    f = -f2;
                    f2 = 0.0f;
                }
                else if(!this.mLayout.canScrollHorizontally()) {
                    f2 = 0.0f;
                }
                z = this.mLowResRotaryEncoderFeature;
            }
            int v1 = (int)(f * this.mScaledVerticalScrollFactor);
            int v2 = (int)(f2 * this.mScaledHorizontalScrollFactor);
            if(z) {
                OverScroller overScroller0 = this.mViewFlinger.mOverScroller;
                int v3 = overScroller0.getFinalY();
                int v4 = overScroller0.getCurrY();
                this.smoothScrollBy(v2 + (overScroller0.getFinalX() - overScroller0.getCurrX()), v1 + (v3 - v4), null, 0x80000000, true);
            }
            else {
                this.nestedScrollByInternal(v2, v1, motionEvent0, 1);
            }
            if(v != 0 && !z) {
                this.mDifferentialMotionFlingController.onMotionEvent(motionEvent0, v);
            }
        }
        return false;
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        boolean z2;
        if(this.mLayoutSuppressed) {
            return false;
        }
        this.mInterceptingOnItemTouchListener = null;
        if(this.findInterceptingOnItemTouchListener(motionEvent0)) {
            this.cancelScroll();
            return true;
        }
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            return false;
        }
        boolean z = recyclerView$LayoutManager0.canScrollHorizontally();
        boolean z1 = this.mLayout.canScrollVertically();
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent0);
        int v = motionEvent0.getActionMasked();
        int v1 = motionEvent0.getActionIndex();
        switch(v) {
            case 0: {
                if(this.mIgnoreMotionEventTillDown) {
                    this.mIgnoreMotionEventTillDown = false;
                }
                this.mScrollPointerId = motionEvent0.getPointerId(0);
                float f = motionEvent0.getX();
                this.mLastTouchX = (int)(f + 0.5f);
                this.mInitialTouchX = (int)(f + 0.5f);
                float f1 = motionEvent0.getY();
                this.mLastTouchY = (int)(f1 + 0.5f);
                this.mInitialTouchY = (int)(f1 + 0.5f);
                if(this.stopGlowAnimations(motionEvent0) || this.mScrollState == 2) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                    this.stopNestedScroll(1);
                }
                this.mNestedOffsets[1] = 0;
                this.mNestedOffsets[0] = 0;
                this.startNestedScrollForType(0);
                return this.mScrollState == 1;
            }
            case 1: {
                this.mVelocityTracker.clear();
                this.stopNestedScroll(0);
                return this.mScrollState == 1;
            }
            case 2: {
                int v2 = motionEvent0.findPointerIndex(this.mScrollPointerId);
                if(v2 < 0) {
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    return false;
                }
                float f2 = motionEvent0.getX(v2);
                float f3 = motionEvent0.getY(v2);
                if(this.mScrollState != 1) {
                    int v3 = ((int)(f3 + 0.5f)) - this.mInitialTouchY;
                    if(!z || Math.abs(((int)(f2 + 0.5f)) - this.mInitialTouchX) <= this.mTouchSlop) {
                        z2 = false;
                    }
                    else {
                        this.mLastTouchX = (int)(f2 + 0.5f);
                        z2 = true;
                    }
                    if(z1 && Math.abs(v3) > this.mTouchSlop) {
                        this.mLastTouchY = (int)(f3 + 0.5f);
                        z2 = true;
                    }
                    if(z2) {
                        this.setScrollState(1);
                        return this.mScrollState == 1;
                    }
                }
                return this.mScrollState == 1;
            }
            case 3: {
                this.cancelScroll();
                return this.mScrollState == 1;
            }
            case 5: {
                this.mScrollPointerId = motionEvent0.getPointerId(v1);
                float f4 = motionEvent0.getX(v1);
                this.mLastTouchX = (int)(f4 + 0.5f);
                this.mInitialTouchX = (int)(f4 + 0.5f);
                float f5 = motionEvent0.getY(v1);
                this.mLastTouchY = (int)(f5 + 0.5f);
                this.mInitialTouchY = (int)(f5 + 0.5f);
                return this.mScrollState == 1;
            }
            case 6: {
                this.onPointerUp(motionEvent0);
                return this.mScrollState == 1;
            }
            default: {
                return this.mScrollState == 1;
            }
        }
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        Trace.beginSection("RV OnLayout");
        this.dispatchLayout();
        Trace.endSection();
        this.mFirstLayoutComplete = true;
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        boolean z = false;
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            this.defaultOnMeasure(v, v1);
            return;
        }
        if(recyclerView$LayoutManager0.isAutoMeasureEnabled()) {
            int v2 = View.MeasureSpec.getMode(v);
            int v3 = View.MeasureSpec.getMode(v1);
            this.mLayout.onMeasure(this.mRecycler, this.mState, v, v1);
            if(v2 == 0x40000000 && v3 == 0x40000000) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if(!z && this.mAdapter != null) {
                if(this.mState.mLayoutStep == 1) {
                    this.dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(v, v1);
                this.mState.mIsMeasuring = true;
                this.dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(v, v1);
                if(this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0x40000000), View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0x40000000));
                    this.mState.mIsMeasuring = true;
                    this.dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(v, v1);
                }
                this.mLastAutoMeasureNonExactMeasuredWidth = this.getMeasuredWidth();
                this.mLastAutoMeasureNonExactMeasuredHeight = this.getMeasuredHeight();
            }
            return;
        }
        if(this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, v, v1);
            return;
        }
        if(this.mAdapterUpdateDuringMeasure) {
            this.startInterceptRequestLayout();
            this.onEnterLayoutOrScroll();
            this.processAdapterUpdatesAndSetAnimationFlags();
            this.onExitLayoutOrScroll();
            if(this.mState.mRunPredictiveAnimations) {
                this.mState.mInPreLayout = true;
            }
            else {
                this.mAdapterHelper.consumeUpdatesInOnePass();
                this.mState.mInPreLayout = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            this.stopInterceptRequestLayout(false);
        }
        else if(this.mState.mRunPredictiveAnimations) {
            this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredHeight());
            return;
        }
        Adapter recyclerView$Adapter0 = this.mAdapter;
        this.mState.mItemCount = recyclerView$Adapter0 == null ? 0 : recyclerView$Adapter0.getItemCount();
        this.startInterceptRequestLayout();
        this.mLayout.onMeasure(this.mRecycler, this.mState, v, v1);
        this.stopInterceptRequestLayout(false);
        this.mState.mInPreLayout = false;
    }

    private void onPointerUp(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionIndex();
        if(motionEvent0.getPointerId(v) == this.mScrollPointerId) {
            int v1 = v == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent0.getPointerId(v1);
            float f = motionEvent0.getX(v1);
            this.mLastTouchX = (int)(f + 0.5f);
            this.mInitialTouchX = (int)(f + 0.5f);
            float f1 = motionEvent0.getY(v1);
            this.mLastTouchY = (int)(f1 + 0.5f);
            this.mInitialTouchY = (int)(f1 + 0.5f);
        }
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int v, Rect rect0) {
        return this.isComputingLayout() ? false : super.onRequestFocusInDescendants(v, rect0);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        this.mPendingSavedState = (SavedState)parcelable0;
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.requestLayout();
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        SavedState recyclerView$SavedState0 = this.mPendingSavedState;
        if(recyclerView$SavedState0 != null) {
            ((SavedState)parcelable0).copyFrom(recyclerView$SavedState0);
            return parcelable0;
        }
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            parcelable0.mLayoutState = recyclerView$LayoutManager0.onSaveInstanceState();
            return parcelable0;
        }
        parcelable0.mLayoutState = null;
        return parcelable0;
    }

    public void onScrollStateChanged(int v) {
    }

    public void onScrolled(int v, int v1) {
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        if(v == v2 && v1 == v3) {
            return;
        }
        this.invalidateGlows();
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        boolean z2;
        if(!this.mLayoutSuppressed && !this.mIgnoreMotionEventTillDown) {
            if(this.dispatchToOnItemTouchListeners(motionEvent0)) {
                this.cancelScroll();
                return true;
            }
            LayoutManager recyclerView$LayoutManager0 = this.mLayout;
            if(recyclerView$LayoutManager0 == null) {
                return false;
            }
            boolean z = recyclerView$LayoutManager0.canScrollHorizontally();
            boolean z1 = this.mLayout.canScrollVertically();
            if(this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            int v = motionEvent0.getActionMasked();
            int v1 = motionEvent0.getActionIndex();
            if(v == 0) {
                this.mNestedOffsets[1] = 0;
                this.mNestedOffsets[0] = 0;
            }
            MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
            motionEvent1.offsetLocation(((float)this.mNestedOffsets[0]), ((float)this.mNestedOffsets[1]));
            switch(v) {
                case 0: {
                    this.mScrollPointerId = motionEvent0.getPointerId(0);
                    float f = motionEvent0.getX();
                    this.mLastTouchX = (int)(f + 0.5f);
                    this.mInitialTouchX = (int)(f + 0.5f);
                    float f1 = motionEvent0.getY();
                    this.mLastTouchY = (int)(f1 + 0.5f);
                    this.mInitialTouchY = (int)(f1 + 0.5f);
                    this.startNestedScrollForType(0);
                    this.mVelocityTracker.addMovement(motionEvent1);
                    break;
                }
                case 1: {
                    this.mVelocityTracker.addMovement(motionEvent1);
                    this.mVelocityTracker.computeCurrentVelocity(1000, ((float)this.mMaxFlingVelocity));
                    float f2 = z ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0f;
                    float f3 = z1 ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0f;
                    if(f2 == 0.0f && f3 == 0.0f || !this.fling(((int)f2), ((int)f3))) {
                        this.setScrollState(0);
                    }
                    this.resetScroll();
                    break;
                }
                case 2: {
                    int v2 = motionEvent0.findPointerIndex(this.mScrollPointerId);
                    if(v2 < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    float f4 = motionEvent0.getX(v2);
                    float f5 = motionEvent0.getY(v2);
                    int v3 = this.mLastTouchX - ((int)(f4 + 0.5f));
                    int v4 = this.mLastTouchY - ((int)(f5 + 0.5f));
                    if(this.mScrollState != 1) {
                        if(z) {
                            v3 = v3 <= 0 ? Math.min(0, v3 + this.mTouchSlop) : Math.max(0, v3 - this.mTouchSlop);
                            z2 = v3 == 0 ? false : true;
                        }
                        else {
                            z2 = false;
                        }
                        if(z1) {
                            v4 = v4 <= 0 ? Math.min(0, v4 + this.mTouchSlop) : Math.max(0, v4 - this.mTouchSlop);
                            if(v4 != 0) {
                                z2 = true;
                            }
                        }
                        if(z2) {
                            this.setScrollState(1);
                        }
                    }
                    if(this.mScrollState == 1) {
                        this.mReusableIntPair[0] = 0;
                        this.mReusableIntPair[1] = 0;
                        int v5 = v3 - this.releaseHorizontalGlow(v3, motionEvent0.getY());
                        int v6 = v4 - this.releaseVerticalGlow(v4, motionEvent0.getX());
                        if(this.dispatchNestedPreScroll((z ? v5 : 0), (z1 ? v6 : 0), this.mReusableIntPair, this.mScrollOffset, 0)) {
                            v5 -= this.mReusableIntPair[0];
                            v6 -= this.mReusableIntPair[1];
                            this.mNestedOffsets[0] += this.mScrollOffset[0];
                            this.mNestedOffsets[1] += this.mScrollOffset[1];
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        this.mLastTouchX = ((int)(f4 + 0.5f)) - this.mScrollOffset[0];
                        this.mLastTouchY = ((int)(f5 + 0.5f)) - this.mScrollOffset[1];
                        if(this.scrollByInternal((z ? v5 : 0), (z1 ? v6 : 0), motionEvent0, 0)) {
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        GapWorker gapWorker0 = this.mGapWorker;
                        if(gapWorker0 != null && (v5 != 0 || v6 != 0)) {
                            gapWorker0.postFromTraversal(this, v5, v6);
                        }
                    }
                    this.mVelocityTracker.addMovement(motionEvent1);
                    break;
                }
                case 3: {
                    this.cancelScroll();
                    this.mVelocityTracker.addMovement(motionEvent1);
                    break;
                }
                case 5: {
                    this.mScrollPointerId = motionEvent0.getPointerId(v1);
                    float f6 = motionEvent0.getX(v1);
                    this.mLastTouchX = (int)(f6 + 0.5f);
                    this.mInitialTouchX = (int)(f6 + 0.5f);
                    float f7 = motionEvent0.getY(v1);
                    this.mLastTouchY = (int)(f7 + 0.5f);
                    this.mInitialTouchY = (int)(f7 + 0.5f);
                    this.mVelocityTracker.addMovement(motionEvent1);
                    break;
                }
                case 6: {
                    this.onPointerUp(motionEvent0);
                    this.mVelocityTracker.addMovement(motionEvent1);
                    break;
                }
                default: {
                    this.mVelocityTracker.addMovement(motionEvent1);
                }
            }
            motionEvent1.recycle();
            return true;
        }
        return false;
    }

    void postAnimationRunner() {
        if(!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        if(this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            if(this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }
        if(this.predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        }
        else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean z = false;
        boolean z1 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.mRunSimpleAnimations = this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || z1 || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds());
        State recyclerView$State0 = this.mState;
        if(recyclerView$State0.mRunSimpleAnimations && z1 && !this.mDataSetHasChangedAfterLayout && this.predictiveItemAnimationsEnabled()) {
            z = true;
        }
        recyclerView$State0.mRunPredictiveAnimations = z;
    }

    void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent |= z;
        this.mDataSetHasChangedAfterLayout = true;
        this.markKnownViewsInvalid();
    }

    private void pullGlows(float f, float f1, float f2, float f3) {
        int v1;
        int v = 1;
        if(f1 < 0.0f) {
            this.ensureLeftGlow();
            EdgeEffectCompat.onPullDistance(this.mLeftGlow, -f1 / ((float)this.getWidth()), 1.0f - f2 / ((float)this.getHeight()));
            v1 = 1;
        }
        else if(f1 > 0.0f) {
            this.ensureRightGlow();
            EdgeEffectCompat.onPullDistance(this.mRightGlow, f1 / ((float)this.getWidth()), f2 / ((float)this.getHeight()));
            v1 = 1;
        }
        else {
            v1 = 0;
        }
        if(f3 < 0.0f) {
            this.ensureTopGlow();
            EdgeEffectCompat.onPullDistance(this.mTopGlow, -f3 / ((float)this.getHeight()), f / ((float)this.getWidth()));
        }
        else if(f3 > 0.0f) {
            this.ensureBottomGlow();
            EdgeEffectCompat.onPullDistance(this.mBottomGlow, f3 / ((float)this.getHeight()), 1.0f - f / ((float)this.getWidth()));
        }
        else {
            v = v1;
        }
        if(v == 0 && f1 == 0.0f && f3 == 0.0f) {
            return;
        }
        this.postInvalidateOnAnimation();
    }

    void recordAnimationInfoIfBouncedHiddenView(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0) {
        recyclerView$ViewHolder0.setFlags(0, 0x2000);
        if(this.mState.mTrackOldChangeHolders && recyclerView$ViewHolder0.isUpdated() && !recyclerView$ViewHolder0.isRemoved() && !recyclerView$ViewHolder0.shouldIgnore()) {
            long v = this.getChangedHolderKey(recyclerView$ViewHolder0);
            this.mViewInfoStore.addToOldChangeHolders(v, recyclerView$ViewHolder0);
        }
        this.mViewInfoStore.addToPreLayout(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0);
    }

    private void recoverFocusFromState() {
        if(this.mPreserveFocusAfterLayout && this.mAdapter != null && this.hasFocus() && this.getDescendantFocusability() != 0x60000 && (this.getDescendantFocusability() != 0x20000 || !this.isFocused())) {
            if(this.isFocused()) {
            label_4:
                View view1 = null;
                ViewHolder recyclerView$ViewHolder0 = this.mState.mFocusedItemId == -1L || !this.mAdapter.hasStableIds() ? null : this.findViewHolderForItemId(this.mState.mFocusedItemId);
                if(recyclerView$ViewHolder0 != null && !this.mChildHelper.isHidden(recyclerView$ViewHolder0.itemView) && recyclerView$ViewHolder0.itemView.hasFocusable()) {
                    view1 = recyclerView$ViewHolder0.itemView;
                }
                else if(this.mChildHelper.getChildCount() > 0) {
                    view1 = this.findNextViewToFocus();
                }
                if(view1 != null) {
                    if(((long)this.mState.mFocusedSubChildId) != -1L) {
                        View view2 = view1.findViewById(this.mState.mFocusedSubChildId);
                        if(view2 != null && view2.isFocusable()) {
                            view1 = view2;
                        }
                    }
                    view1.requestFocus();
                }
            }
            else {
                View view0 = this.getFocusedChild();
                if(this.mChildHelper.isHidden(view0)) {
                    goto label_4;
                }
            }
        }
    }

    private void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect0 = this.mLeftGlow;
        if(edgeEffect0 == null) {
            z = false;
        }
        else {
            edgeEffect0.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect1 = this.mTopGlow;
        if(edgeEffect1 != null) {
            edgeEffect1.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if(edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mBottomGlow;
        if(edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if(z) {
            this.postInvalidateOnAnimation();
        }
    }

    private int releaseHorizontalGlow(int v, float f) {
        float f1 = f / ((float)this.getHeight());
        float f2 = ((float)v) / ((float)this.getWidth());
        float f3 = 0.0f;
        if(this.mLeftGlow != null && EdgeEffectCompat.getDistance(this.mLeftGlow) != 0.0f) {
            if(this.canScrollHorizontally(-1)) {
                this.mLeftGlow.onRelease();
            }
            else {
                float f4 = EdgeEffectCompat.onPullDistance(this.mLeftGlow, -f2, 1.0f - f1);
                if(EdgeEffectCompat.getDistance(this.mLeftGlow) == 0.0f) {
                    this.mLeftGlow.onRelease();
                }
                f3 = -f4;
            }
            this.invalidate();
            return Math.round(f3 * ((float)this.getWidth()));
        }
        if(this.mRightGlow != null && EdgeEffectCompat.getDistance(this.mRightGlow) != 0.0f) {
            if(this.canScrollHorizontally(1)) {
                this.mRightGlow.onRelease();
            }
            else {
                float f5 = EdgeEffectCompat.onPullDistance(this.mRightGlow, f2, f1);
                if(EdgeEffectCompat.getDistance(this.mRightGlow) == 0.0f) {
                    this.mRightGlow.onRelease();
                }
                f3 = f5;
            }
            this.invalidate();
        }
        return Math.round(f3 * ((float)this.getWidth()));
    }

    private int releaseVerticalGlow(int v, float f) {
        float f1 = f / ((float)this.getWidth());
        float f2 = ((float)v) / ((float)this.getHeight());
        float f3 = 0.0f;
        if(this.mTopGlow != null && EdgeEffectCompat.getDistance(this.mTopGlow) != 0.0f) {
            if(this.canScrollVertically(-1)) {
                this.mTopGlow.onRelease();
            }
            else {
                float f4 = EdgeEffectCompat.onPullDistance(this.mTopGlow, -f2, f1);
                if(EdgeEffectCompat.getDistance(this.mTopGlow) == 0.0f) {
                    this.mTopGlow.onRelease();
                }
                f3 = -f4;
            }
            this.invalidate();
            return Math.round(f3 * ((float)this.getHeight()));
        }
        if(this.mBottomGlow != null && EdgeEffectCompat.getDistance(this.mBottomGlow) != 0.0f) {
            if(this.canScrollVertically(1)) {
                this.mBottomGlow.onRelease();
            }
            else {
                float f5 = EdgeEffectCompat.onPullDistance(this.mBottomGlow, f2, 1.0f - f1);
                if(EdgeEffectCompat.getDistance(this.mBottomGlow) == 0.0f) {
                    this.mBottomGlow.onRelease();
                }
                f3 = f5;
            }
            this.invalidate();
        }
        return Math.round(f3 * ((float)this.getHeight()));
    }

    void removeAndRecycleViews() {
        ItemAnimator recyclerView$ItemAnimator0 = this.mItemAnimator;
        if(recyclerView$ItemAnimator0 != null) {
            recyclerView$ItemAnimator0.endAnimations();
        }
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    boolean removeAnimatingView(View view0) {
        this.startInterceptRequestLayout();
        boolean z = this.mChildHelper.removeViewIfHidden(view0);
        if(z) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            this.mRecycler.unscrapView(recyclerView$ViewHolder0);
            this.mRecycler.recycleViewHolderInternal(recyclerView$ViewHolder0);
            if(RecyclerView.sVerboseLoggingEnabled) {
                Log.d("RecyclerView", "after removing animated view: " + view0 + ", " + this);
            }
        }
        this.stopInterceptRequestLayout(!z);
        return z;
    }

    @Override  // android.view.ViewGroup
    protected void removeDetachedView(View view0, boolean z) {
        ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
        if(recyclerView$ViewHolder0 != null) {
            if(recyclerView$ViewHolder0.isTmpDetached()) {
                recyclerView$ViewHolder0.clearTmpDetachFlag();
                view0.clearAnimation();
                this.dispatchChildDetached(view0);
                super.removeDetachedView(view0, z);
                return;
            }
            if(!recyclerView$ViewHolder0.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + recyclerView$ViewHolder0 + this.exceptionLabel());
            }
        }
        else if(RecyclerView.sDebugAssertionsEnabled) {
            throw new IllegalArgumentException("No ViewHolder found for child: " + view0 + this.exceptionLabel());
        }
        view0.clearAnimation();
        this.dispatchChildDetached(view0);
        super.removeDetachedView(view0, z);
    }

    public void removeItemDecoration(ItemDecoration recyclerView$ItemDecoration0) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(recyclerView$ItemDecoration0);
        if(this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(this.getOverScrollMode() == 2);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }

    public void removeItemDecorationAt(int v) {
        int v1 = this.getItemDecorationCount();
        if(v < 0 || v >= v1) {
            throw new IndexOutOfBoundsException(v + " is an invalid index for size " + v1);
        }
        this.removeItemDecoration(this.getItemDecorationAt(v));
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener recyclerView$OnChildAttachStateChangeListener0) {
        List list0 = this.mOnChildAttachStateListeners;
        if(list0 == null) {
            return;
        }
        list0.remove(recyclerView$OnChildAttachStateChangeListener0);
    }

    public void removeOnItemTouchListener(OnItemTouchListener recyclerView$OnItemTouchListener0) {
        this.mOnItemTouchListeners.remove(recyclerView$OnItemTouchListener0);
        if(this.mInterceptingOnItemTouchListener == recyclerView$OnItemTouchListener0) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(OnScrollListener recyclerView$OnScrollListener0) {
        List list0 = this.mScrollListeners;
        if(list0 != null) {
            list0.remove(recyclerView$OnScrollListener0);
        }
    }

    public void removeRecyclerListener(RecyclerListener recyclerView$RecyclerListener0) {
        this.mRecyclerListeners.remove(recyclerView$RecyclerListener0);
    }

    void repositionShadowingViews() {
        int v = this.mChildHelper.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.mChildHelper.getChildAt(v1);
            ViewHolder recyclerView$ViewHolder0 = this.getChildViewHolder(view0);
            if(recyclerView$ViewHolder0 != null && recyclerView$ViewHolder0.mShadowingHolder != null) {
                View view1 = recyclerView$ViewHolder0.mShadowingHolder.itemView;
                int v2 = view0.getLeft();
                int v3 = view0.getTop();
                if(v2 != view1.getLeft() || v3 != view1.getTop()) {
                    view1.layout(v2, v3, view1.getWidth() + v2, view1.getHeight() + v3);
                }
            }
        }
    }

    @Override  // android.view.ViewGroup
    public void requestChildFocus(View view0, View view1) {
        if(!this.mLayout.onRequestChildFocus(this, this.mState, view0, view1) && view1 != null) {
            this.requestChildOnScreen(view0, view1);
        }
        super.requestChildFocus(view0, view1);
    }

    private void requestChildOnScreen(View view0, View view1) {
        View view2 = view1 == null ? view0 : view1;
        int v = view2.getWidth();
        int v1 = view2.getHeight();
        this.mTempRect.set(0, 0, v, v1);
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view2.getLayoutParams();
        if(viewGroup$LayoutParams0 instanceof LayoutParams && !((LayoutParams)viewGroup$LayoutParams0).mInsetsDirty) {
            this.mTempRect.left -= ((LayoutParams)viewGroup$LayoutParams0).mDecorInsets.left;
            this.mTempRect.right += ((LayoutParams)viewGroup$LayoutParams0).mDecorInsets.right;
            this.mTempRect.top -= ((LayoutParams)viewGroup$LayoutParams0).mDecorInsets.top;
            this.mTempRect.bottom += ((LayoutParams)viewGroup$LayoutParams0).mDecorInsets.bottom;
        }
        if(view1 != null) {
            this.offsetDescendantRectToMyCoords(view1, this.mTempRect);
            this.offsetRectIntoDescendantCoords(view0, this.mTempRect);
        }
        this.mLayout.requestChildRectangleOnScreen(this, view0, this.mTempRect, !this.mFirstLayoutComplete, view1 == null);
    }

    @Override  // android.view.ViewGroup
    public boolean requestChildRectangleOnScreen(View view0, Rect rect0, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view0, rect0, z);
    }

    @Override  // android.view.ViewGroup
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int v = this.mOnItemTouchListeners.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((OnItemTouchListener)this.mOnItemTouchListeners.get(v1)).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override  // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if(this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutSuppressed) {
            super.requestLayout();
            return;
        }
        this.mLayoutWasDefered = true;
    }

    private void resetFocusInfo() {
        this.mState.mFocusedItemId = -1L;
        this.mState.mFocusedItemPosition = -1;
        this.mState.mFocusedSubChildId = -1;
    }

    private void resetScroll() {
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.clear();
        }
        this.stopNestedScroll(0);
        this.releaseGlows();
    }

    private void saveFocusInfo() {
        int v;
        ViewHolder recyclerView$ViewHolder0 = null;
        View view0 = !this.mPreserveFocusAfterLayout || !this.hasFocus() || this.mAdapter == null ? null : this.getFocusedChild();
        if(view0 != null) {
            recyclerView$ViewHolder0 = this.findContainingViewHolder(view0);
        }
        if(recyclerView$ViewHolder0 == null) {
            this.resetFocusInfo();
            return;
        }
        this.mState.mFocusedItemId = this.mAdapter.hasStableIds() ? recyclerView$ViewHolder0.getItemId() : -1L;
        State recyclerView$State0 = this.mState;
        if(this.mDataSetHasChangedAfterLayout) {
            v = -1;
        }
        else {
            v = recyclerView$ViewHolder0.isRemoved() ? recyclerView$ViewHolder0.mOldPosition : recyclerView$ViewHolder0.getAbsoluteAdapterPosition();
        }
        recyclerView$State0.mFocusedItemPosition = v;
        this.mState.mFocusedSubChildId = this.getDeepestFocusedViewWithId(recyclerView$ViewHolder0.itemView);
    }

    void saveOldPositions() {
        int v = this.mChildHelper.getUnfilteredChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(v1));
            if(RecyclerView.sDebugAssertionsEnabled && recyclerView$ViewHolder0.mPosition == -1 && !recyclerView$ViewHolder0.isRemoved()) {
                throw new IllegalStateException("view holder cannot have position -1 unless it is removed" + this.exceptionLabel());
            }
            if(!recyclerView$ViewHolder0.shouldIgnore()) {
                recyclerView$ViewHolder0.saveOldPosition();
            }
        }
    }

    @Override  // android.view.View
    public void scrollBy(int v, int v1) {
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if(!this.mLayoutSuppressed) {
            boolean z = recyclerView$LayoutManager0.canScrollHorizontally();
            boolean z1 = this.mLayout.canScrollVertically();
            if(z || z1) {
                if(!z) {
                    v = 0;
                }
                if(!z1) {
                    v1 = 0;
                }
                this.scrollByInternal(v, v1, null, 0);
            }
        }
    }

    boolean scrollByInternal(int v, int v1, MotionEvent motionEvent0, int v2) {
        int v7;
        int v6;
        int v5;
        int v4;
        this.consumePendingUpdateOperations();
        if(this.mAdapter == null) {
            v7 = 0;
            v6 = 0;
            v4 = 0;
            v5 = 0;
        }
        else {
            this.mReusableIntPair[0] = 0;
            this.mReusableIntPair[1] = 0;
            this.scrollStep(v, v1, this.mReusableIntPair);
            int v3 = this.mReusableIntPair[0];
            v4 = v - v3;
            v5 = v1 - this.mReusableIntPair[1];
            v6 = this.mReusableIntPair[1];
            v7 = v3;
        }
        if(!this.mItemDecorations.isEmpty()) {
            this.invalidate();
        }
        this.mReusableIntPair[0] = 0;
        this.mReusableIntPair[1] = 0;
        this.dispatchNestedScroll(v7, v6, v4, v5, this.mScrollOffset, v2, this.mReusableIntPair);
        int v8 = this.mReusableIntPair[0];
        int v9 = this.mReusableIntPair[1];
        int v10 = this.mLastTouchX;
        int v11 = this.mScrollOffset[0];
        this.mLastTouchX = v10 - v11;
        int v12 = this.mLastTouchY;
        int v13 = this.mScrollOffset[1];
        this.mLastTouchY = v12 - v13;
        this.mNestedOffsets[0] += v11;
        this.mNestedOffsets[1] += v13;
        if(this.getOverScrollMode() != 2) {
            if(motionEvent0 != null && !MotionEventCompat.isFromSource(motionEvent0, 0x2002)) {
                this.pullGlows(motionEvent0.getX(), ((float)(v4 - v8)), motionEvent0.getY(), ((float)(v5 - v9)));
                if(Build.VERSION.SDK_INT >= 0x1F && MotionEventCompat.isFromSource(motionEvent0, 0x400000)) {
                    this.releaseGlows();
                }
            }
            this.considerReleasingGlowsOnScroll(v, v1);
        }
        if(v7 != 0 || v6 != 0) {
            this.dispatchOnScrolled(v7, v6);
        }
        if(!this.awakenScrollBars()) {
            this.invalidate();
        }
        return v8 != 0 || v9 != 0 || v7 != 0 || v6 != 0;
    }

    void scrollStep(int v, int v1, int[] arr_v) {
        this.startInterceptRequestLayout();
        this.onEnterLayoutOrScroll();
        Trace.beginSection("RV Scroll");
        this.fillRemainingScrollValues(this.mState);
        int v2 = v == 0 ? 0 : this.mLayout.scrollHorizontallyBy(v, this.mRecycler, this.mState);
        int v3 = v1 == 0 ? 0 : this.mLayout.scrollVerticallyBy(v1, this.mRecycler, this.mState);
        Trace.endSection();
        this.repositionShadowingViews();
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
        if(arr_v != null) {
            arr_v[0] = v2;
            arr_v[1] = v3;
        }
    }

    @Override  // android.view.View
    public void scrollTo(int v, int v1) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int v) {
        if(this.mLayoutSuppressed) {
            return;
        }
        this.stopScroll();
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        recyclerView$LayoutManager0.scrollToPosition(v);
        this.awakenScrollBars();
    }

    @Override  // android.view.View
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent0) {
        if(this.shouldDeferAccessibilityEvent(accessibilityEvent0)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent0);
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate0) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate0;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate0);
    }

    public void setAdapter(Adapter recyclerView$Adapter0) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(recyclerView$Adapter0, false, true);
        this.processDataSetCompletelyChanged(false);
        this.requestLayout();
    }

    private void setAdapterInternal(Adapter recyclerView$Adapter0, boolean z, boolean z1) {
        Adapter recyclerView$Adapter1 = this.mAdapter;
        if(recyclerView$Adapter1 != null) {
            recyclerView$Adapter1.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if(!z || z1) {
            this.removeAndRecycleViews();
        }
        this.mAdapterHelper.reset();
        Adapter recyclerView$Adapter2 = this.mAdapter;
        this.mAdapter = recyclerView$Adapter0;
        if(recyclerView$Adapter0 != null) {
            recyclerView$Adapter0.registerAdapterDataObserver(this.mObserver);
            recyclerView$Adapter0.onAttachedToRecyclerView(this);
        }
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.onAdapterChanged(recyclerView$Adapter2, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(recyclerView$Adapter2, this.mAdapter, z);
        this.mState.mStructureChanged = true;
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback recyclerView$ChildDrawingOrderCallback0) {
        if(recyclerView$ChildDrawingOrderCallback0 == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = recyclerView$ChildDrawingOrderCallback0;
        this.setChildrenDrawingOrderEnabled(recyclerView$ChildDrawingOrderCallback0 != null);
    }

    boolean setChildImportantForAccessibilityInternal(ViewHolder recyclerView$ViewHolder0, int v) {
        if(this.isComputingLayout()) {
            recyclerView$ViewHolder0.mPendingAccessibilityState = v;
            this.mPendingAccessibilityImportanceChange.add(recyclerView$ViewHolder0);
            return false;
        }
        recyclerView$ViewHolder0.itemView.setImportantForAccessibility(v);
        return true;
    }

    @Override  // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if(z != this.mClipToPadding) {
            this.invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if(this.mFirstLayoutComplete) {
            this.requestLayout();
        }
    }

    public static void setDebugAssertionsEnabled(boolean z) {
        RecyclerView.sDebugAssertionsEnabled = z;
    }

    public void setEdgeEffectFactory(EdgeEffectFactory recyclerView$EdgeEffectFactory0) {
        Preconditions.checkNotNull(recyclerView$EdgeEffectFactory0);
        this.mEdgeEffectFactory = recyclerView$EdgeEffectFactory0;
        this.invalidateGlows();
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setItemAnimator(ItemAnimator recyclerView$ItemAnimator0) {
        ItemAnimator recyclerView$ItemAnimator1 = this.mItemAnimator;
        if(recyclerView$ItemAnimator1 != null) {
            recyclerView$ItemAnimator1.endAnimations();
            this.mItemAnimator.setListener(null);
        }
        this.mItemAnimator = recyclerView$ItemAnimator0;
        if(recyclerView$ItemAnimator0 != null) {
            recyclerView$ItemAnimator0.setListener(this.mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int v) {
        this.mRecycler.setViewCacheSize(v);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        this.suppressLayout(z);
    }

    public void setLayoutManager(LayoutManager recyclerView$LayoutManager0) {
        if(recyclerView$LayoutManager0 == this.mLayout) {
            return;
        }
        this.stopScroll();
        if(this.mLayout == null) {
            this.mRecycler.clear();
        }
        else {
            ItemAnimator recyclerView$ItemAnimator0 = this.mItemAnimator;
            if(recyclerView$ItemAnimator0 != null) {
                recyclerView$ItemAnimator0.endAnimations();
            }
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mRecycler.clear();
            if(this.mIsAttached) {
                this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
            this.mLayout = null;
        }
        this.mChildHelper.removeAllViewsUnfiltered();
        this.mLayout = recyclerView$LayoutManager0;
        if(recyclerView$LayoutManager0 != null) {
            if(recyclerView$LayoutManager0.mRecyclerView != null) {
                throw new IllegalArgumentException("LayoutManager " + recyclerView$LayoutManager0 + " is already attached to a RecyclerView:" + recyclerView$LayoutManager0.mRecyclerView.exceptionLabel());
            }
            this.mLayout.setRecyclerView(this);
            if(this.mIsAttached) {
                this.mLayout.dispatchAttachedToWindow(this);
            }
        }
        this.mRecycler.updateViewCacheSize();
        this.requestLayout();
    }

    @Override  // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition0) {
        if(layoutTransition0 != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    public void setOnFlingListener(OnFlingListener recyclerView$OnFlingListener0) {
        this.mOnFlingListener = recyclerView$OnFlingListener0;
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener recyclerView$OnScrollListener0) {
        this.mScrollListener = recyclerView$OnScrollListener0;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecycledViewPool(RecycledViewPool recyclerView$RecycledViewPool0) {
        this.mRecycler.setRecycledViewPool(recyclerView$RecycledViewPool0);
    }

    @Deprecated
    public void setRecyclerListener(RecyclerListener recyclerView$RecyclerListener0) {
        this.mRecyclerListener = recyclerView$RecyclerListener0;
    }

    void setScrollState(int v) {
        if(v == this.mScrollState) {
            return;
        }
        if(RecyclerView.sVerboseLoggingEnabled) {
            Log.d("RecyclerView", "setting scroll state to " + v + " from " + this.mScrollState, new Exception());
        }
        this.mScrollState = v;
        if(v != 2) {
            this.stopScrollersInternal();
        }
        this.dispatchOnScrollStateChanged(v);
    }

    public void setScrollingTouchSlop(int v) {
        ViewConfiguration viewConfiguration0 = ViewConfiguration.get(this.getContext());
        switch(v) {
            case 0: {
                break;
            }
            case 1: {
                this.mTouchSlop = viewConfiguration0.getScaledPagingTouchSlop();
                return;
            }
            default: {
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + v + "; using default value");
                break;
            }
        }
        this.mTouchSlop = viewConfiguration0.getScaledTouchSlop();
    }

    public static void setVerboseLoggingEnabled(boolean z) {
        RecyclerView.sVerboseLoggingEnabled = z;
    }

    public void setViewCacheExtension(ViewCacheExtension recyclerView$ViewCacheExtension0) {
        this.mRecycler.setViewCacheExtension(recyclerView$ViewCacheExtension0);
    }

    private boolean shouldAbsorb(EdgeEffect edgeEffect0, int v, int v1) {
        return v <= 0 ? this.getSplineFlingDistance(-v) < EdgeEffectCompat.getDistance(edgeEffect0) * ((float)v1) : true;
    }

    boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        int v = 0;
        if(this.isComputingLayout()) {
            int v1 = accessibilityEvent0 == null ? 0 : AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent0);
            if(v1 != 0) {
                v = v1;
            }
            this.mEatenAccessibilityChangeFlags |= v;
            return true;
        }
        return false;
    }

    public void smoothScrollBy(int v, int v1) {
        this.smoothScrollBy(v, v1, null);
    }

    public void smoothScrollBy(int v, int v1, Interpolator interpolator0) {
        this.smoothScrollBy(v, v1, interpolator0, 0x80000000);
    }

    public void smoothScrollBy(int v, int v1, Interpolator interpolator0, int v2) {
        this.smoothScrollBy(v, v1, interpolator0, v2, false);
    }

    void smoothScrollBy(int v, int v1, Interpolator interpolator0, int v2, boolean z) {
        int v3 = 0;
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if(!this.mLayoutSuppressed) {
            if(!recyclerView$LayoutManager0.canScrollHorizontally()) {
                v = 0;
            }
            if(!this.mLayout.canScrollVertically()) {
                v1 = 0;
            }
            if(v != 0 || v1 != 0) {
                if(v2 != 0x80000000 && v2 <= 0) {
                    this.scrollBy(v, v1);
                    return;
                }
                if(z) {
                    if(v != 0) {
                        v3 = 1;
                    }
                    if(v1 != 0) {
                        v3 |= 2;
                    }
                    this.startNestedScroll(v3, 1);
                }
                this.mViewFlinger.smoothScrollBy(v, v1, v2, interpolator0);
            }
        }
    }

    public void smoothScrollToPosition(int v) {
        if(this.mLayoutSuppressed) {
            return;
        }
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        recyclerView$LayoutManager0.smoothScrollToPosition(this, this.mState, v);
    }

    void startInterceptRequestLayout() {
        int v = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = v;
        if(v == 1 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int v) {
        return this.getScrollingChildHelper().startNestedScroll(v);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int v, int v1) {
        return this.getScrollingChildHelper().startNestedScroll(v, v1);
    }

    private void startNestedScrollForType(int v) {
        boolean z = this.mLayout.canScrollHorizontally();
        if(this.mLayout.canScrollVertically()) {
            z |= 2;
        }
        this.startNestedScroll(((int)z), v);
    }

    private boolean stopGlowAnimations(MotionEvent motionEvent0) {
        boolean z;
        if(this.mLeftGlow == null || EdgeEffectCompat.getDistance(this.mLeftGlow) == 0.0f || this.canScrollHorizontally(-1)) {
            z = false;
        }
        else {
            EdgeEffectCompat.onPullDistance(this.mLeftGlow, 0.0f, 1.0f - motionEvent0.getY() / ((float)this.getHeight()));
            z = true;
        }
        if(this.mRightGlow != null && EdgeEffectCompat.getDistance(this.mRightGlow) != 0.0f && !this.canScrollHorizontally(1)) {
            EdgeEffectCompat.onPullDistance(this.mRightGlow, 0.0f, motionEvent0.getY() / ((float)this.getHeight()));
            z = true;
        }
        if(this.mTopGlow != null && EdgeEffectCompat.getDistance(this.mTopGlow) != 0.0f && !this.canScrollVertically(-1)) {
            EdgeEffectCompat.onPullDistance(this.mTopGlow, 0.0f, motionEvent0.getX() / ((float)this.getWidth()));
            z = true;
        }
        if(this.mBottomGlow != null && EdgeEffectCompat.getDistance(this.mBottomGlow) != 0.0f && !this.canScrollVertically(1)) {
            EdgeEffectCompat.onPullDistance(this.mBottomGlow, 0.0f, 1.0f - motionEvent0.getX() / ((float)this.getWidth()));
            return true;
        }
        return z;
    }

    void stopInterceptRequestLayout(boolean z) {
        if(this.mInterceptRequestLayoutDepth < 1) {
            if(RecyclerView.sDebugAssertionsEnabled) {
                throw new IllegalStateException("stopInterceptRequestLayout was called more times than startInterceptRequestLayout." + this.exceptionLabel());
            }
            this.mInterceptRequestLayoutDepth = 1;
        }
        if(!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if(this.mInterceptRequestLayoutDepth == 1) {
            if(z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                this.dispatchLayout();
            }
            if(!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        --this.mInterceptRequestLayoutDepth;
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.getScrollingChildHelper().stopNestedScroll();
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int v) {
        this.getScrollingChildHelper().stopNestedScroll(v);
    }

    public void stopScroll() {
        this.setScrollState(0);
        this.stopScrollersInternal();
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        LayoutManager recyclerView$LayoutManager0 = this.mLayout;
        if(recyclerView$LayoutManager0 != null) {
            recyclerView$LayoutManager0.stopSmoothScroller();
        }
    }

    @Override  // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if(z != this.mLayoutSuppressed) {
            this.assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if(!z) {
                this.mLayoutSuppressed = false;
                if(this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                    this.requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long v = SystemClock.uptimeMillis();
            this.onTouchEvent(MotionEvent.obtain(v, v, 3, 0.0f, 0.0f, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            this.stopScroll();
        }
    }

    public void swapAdapter(Adapter recyclerView$Adapter0, boolean z) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(recyclerView$Adapter0, true, z);
        this.processDataSetCompletelyChanged(true);
        this.requestLayout();
    }

    void viewRangeUpdate(int v, int v1, Object object0) {
        int v2 = this.mChildHelper.getUnfilteredChildCount();
        for(int v3 = 0; v3 < v2; ++v3) {
            View view0 = this.mChildHelper.getUnfilteredChildAt(v3);
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(view0);
            if(recyclerView$ViewHolder0 != null && !recyclerView$ViewHolder0.shouldIgnore() && recyclerView$ViewHolder0.mPosition >= v && recyclerView$ViewHolder0.mPosition < v + v1) {
                recyclerView$ViewHolder0.addFlags(2);
                recyclerView$ViewHolder0.addChangePayload(object0);
                ((LayoutParams)view0.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(v, v1);
    }
}

