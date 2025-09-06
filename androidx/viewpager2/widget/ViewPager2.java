package androidx.viewpager2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener;
import androidx.recyclerview.widget.RecyclerView.Recycler;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.R.styleable;
import androidx.viewpager2.adapter.StatefulAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ViewPager2 extends ViewGroup {
    abstract class AccessibilityProvider {
        private AccessibilityProvider() {
        }

        AccessibilityProvider(androidx.viewpager2.widget.ViewPager2.1 viewPager2$10) {
        }

        boolean handlesGetAccessibilityClassName() {
            return false;
        }

        boolean handlesLmPerformAccessibilityAction(int v) {
            return false;
        }

        boolean handlesPerformAccessibilityAction(int v, Bundle bundle0) {
            return false;
        }

        boolean handlesRvGetAccessibilityClassName() {
            return false;
        }

        void onAttachAdapter(Adapter recyclerView$Adapter0) {
        }

        void onDetachAdapter(Adapter recyclerView$Adapter0) {
        }

        String onGetAccessibilityClassName() {
            throw new IllegalStateException("Not implemented.");
        }

        void onInitialize(CompositeOnPageChangeCallback compositeOnPageChangeCallback0, RecyclerView recyclerView0) {
        }

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        }

        void onLmInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        }

        void onLmInitializeAccessibilityNodeInfoForItem(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        }

        boolean onLmPerformAccessibilityAction(int v) {
            throw new IllegalStateException("Not implemented.");
        }

        boolean onPerformAccessibilityAction(int v, Bundle bundle0) {
            throw new IllegalStateException("Not implemented.");
        }

        void onRestorePendingState() {
        }

        CharSequence onRvGetAccessibilityClassName() {
            throw new IllegalStateException("Not implemented.");
        }

        void onRvInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        }

        void onSetLayoutDirection() {
        }

        void onSetNewCurrentItem() {
        }

        void onSetOrientation() {
        }

        void onSetUserInputEnabled() {
        }
    }

    class BasicAccessibilityProvider extends AccessibilityProvider {
        BasicAccessibilityProvider() {
            super(null);
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public boolean handlesLmPerformAccessibilityAction(int v) {
            return (v == 0x1000 || v == 0x2000) && !ViewPager2.this.isUserInputEnabled();
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public boolean handlesRvGetAccessibilityClassName() [...] // Inlined contents

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onLmInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            if(!ViewPager2.this.isUserInputEnabled()) {
                accessibilityNodeInfoCompat0.removeAction(AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                accessibilityNodeInfoCompat0.removeAction(AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfoCompat0.setScrollable(false);
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public boolean onLmPerformAccessibilityAction(int v) {
            if(!this.handlesLmPerformAccessibilityAction(v)) {
                throw new IllegalStateException();
            }
            return false;
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public CharSequence onRvGetAccessibilityClassName() {
            return "androidx.viewpager.widget.ViewPager";
        }
    }

    static abstract class DataSetChangeObserver extends AdapterDataObserver {
        private DataSetChangeObserver() {
        }

        DataSetChangeObserver(androidx.viewpager2.widget.ViewPager2.1 viewPager2$10) {
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public abstract void onChanged();

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeChanged(int v, int v1) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeChanged(int v, int v1, Object object0) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeInserted(int v, int v1) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeMoved(int v, int v1, int v2) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeRemoved(int v, int v1) {
            this.onChanged();
        }
    }

    class LinearLayoutManagerImpl extends LinearLayoutManager {
        LinearLayoutManagerImpl(Context context0) {
            super(context0);
        }

        @Override  // androidx.recyclerview.widget.LinearLayoutManager
        protected void calculateExtraLayoutSpace(State recyclerView$State0, int[] arr_v) {
            int v = ViewPager2.this.getOffscreenPageLimit();
            if(v == -1) {
                super.calculateExtraLayoutSpace(recyclerView$State0, arr_v);
                return;
            }
            int v1 = ViewPager2.this.getPageSize() * v;
            arr_v[0] = v1;
            arr_v[1] = v1;
        }

        @Override  // androidx.recyclerview.widget.LinearLayoutManager
        public void onInitializeAccessibilityNodeInfo(Recycler recyclerView$Recycler0, State recyclerView$State0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            super.onInitializeAccessibilityNodeInfo(recyclerView$Recycler0, recyclerView$State0, accessibilityNodeInfoCompat0);
            ViewPager2.this.mAccessibilityProvider.onLmInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat0);
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
        public void onInitializeAccessibilityNodeInfoForItem(Recycler recyclerView$Recycler0, State recyclerView$State0, View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            ViewPager2.this.mAccessibilityProvider.onLmInitializeAccessibilityNodeInfoForItem(view0, accessibilityNodeInfoCompat0);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
        public boolean performAccessibilityAction(Recycler recyclerView$Recycler0, State recyclerView$State0, int v, Bundle bundle0) {
            return ViewPager2.this.mAccessibilityProvider.handlesLmPerformAccessibilityAction(v) ? ViewPager2.this.mAccessibilityProvider.onLmPerformAccessibilityAction(v) : super.performAccessibilityAction(recyclerView$Recycler0, recyclerView$State0, v, bundle0);
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView0, View view0, Rect rect0, boolean z, boolean z1) {
            return false;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OffscreenPageLimit {
    }

    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int v) {
        }

        public void onPageScrolled(int v, float f, int v1) {
        }

        public void onPageSelected(int v) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    class PageAwareAccessibilityProvider extends AccessibilityProvider {
        private final AccessibilityViewCommand mActionPageBackward;
        private final AccessibilityViewCommand mActionPageForward;
        private AdapterDataObserver mAdapterDataObserver;

        PageAwareAccessibilityProvider() {
            super(null);
            this.mActionPageForward = new AccessibilityViewCommand() {
                @Override  // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean perform(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) {
                    PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((ViewPager2)view0).getCurrentItem() + 1);
                    return true;
                }
            };
            this.mActionPageBackward = new AccessibilityViewCommand() {
                @Override  // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean perform(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) {
                    PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((ViewPager2)view0).getCurrentItem() - 1);
                    return true;
                }
            };
        }

        private void addCollectionInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            int v1;
            int v = 1;
            if(ViewPager2.this.getAdapter() == null) {
                v1 = 0;
                v = 0;
            }
            else if(ViewPager2.this.getOrientation() == 1) {
                v = ViewPager2.this.getAdapter().getItemCount();
                v1 = 1;
            }
            else {
                v1 = ViewPager2.this.getAdapter().getItemCount();
            }
            accessibilityNodeInfoCompat0.setCollectionInfo(CollectionInfoCompat.obtain(v, v1, false, 0));
        }

        private void addCollectionItemInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain((ViewPager2.this.getOrientation() == 1 ? ViewPager2.this.mLayoutManager.getPosition(view0) : 0), 1, (ViewPager2.this.getOrientation() == 0 ? ViewPager2.this.mLayoutManager.getPosition(view0) : 0), 1, false, false));
        }

        private void addScrollActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            Adapter recyclerView$Adapter0 = ViewPager2.this.getAdapter();
            if(recyclerView$Adapter0 != null) {
                int v = recyclerView$Adapter0.getItemCount();
                if(v != 0 && ViewPager2.this.isUserInputEnabled()) {
                    if(ViewPager2.this.mCurrentItem > 0) {
                        accessibilityNodeInfoCompat0.addAction(0x2000);
                    }
                    if(ViewPager2.this.mCurrentItem < v - 1) {
                        accessibilityNodeInfoCompat0.addAction(0x1000);
                    }
                    accessibilityNodeInfoCompat0.setScrollable(true);
                }
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public boolean handlesGetAccessibilityClassName() [...] // Inlined contents

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public boolean handlesPerformAccessibilityAction(int v, Bundle bundle0) {
            return v == 0x1000 || v == 0x2000;
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onAttachAdapter(Adapter recyclerView$Adapter0) {
            this.updatePageAccessibilityActions();
            if(recyclerView$Adapter0 != null) {
                recyclerView$Adapter0.registerAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onDetachAdapter(Adapter recyclerView$Adapter0) {
            if(recyclerView$Adapter0 != null) {
                recyclerView$Adapter0.unregisterAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public String onGetAccessibilityClassName() {
            return "androidx.viewpager.widget.ViewPager";
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onInitialize(CompositeOnPageChangeCallback compositeOnPageChangeCallback0, RecyclerView recyclerView0) {
            ViewCompat.setImportantForAccessibility(recyclerView0, 2);
            this.mAdapterDataObserver = new DataSetChangeObserver() {
                {
                    PageAwareAccessibilityProvider.this = viewPager2$PageAwareAccessibilityProvider0;
                    super(null);
                }

                @Override  // androidx.viewpager2.widget.ViewPager2$DataSetChangeObserver
                public void onChanged() {
                    PageAwareAccessibilityProvider.this.updatePageAccessibilityActions();
                }
            };
            if(ViewCompat.getImportantForAccessibility(ViewPager2.this) == 0) {
                ViewCompat.setImportantForAccessibility(ViewPager2.this, 1);
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
            this.addCollectionInfo(accessibilityNodeInfoCompat0);
            this.addScrollActions(accessibilityNodeInfoCompat0);
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        void onLmInitializeAccessibilityNodeInfoForItem(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            this.addCollectionItemInfo(view0, accessibilityNodeInfoCompat0);
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public boolean onPerformAccessibilityAction(int v, Bundle bundle0) {
            if(!this.handlesPerformAccessibilityAction(v, bundle0)) {
                throw new IllegalStateException();
            }
            this.setCurrentItemFromAccessibilityCommand((v == 0x2000 ? ViewPager2.this.getCurrentItem() - 1 : ViewPager2.this.getCurrentItem() + 1));
            return true;
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onRestorePendingState() {
            this.updatePageAccessibilityActions();
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onRvInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
            accessibilityEvent0.setSource(ViewPager2.this);
            accessibilityEvent0.setClassName(this.onGetAccessibilityClassName());
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onSetLayoutDirection() {
            this.updatePageAccessibilityActions();
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onSetNewCurrentItem() {
            this.updatePageAccessibilityActions();
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onSetOrientation() {
            this.updatePageAccessibilityActions();
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$AccessibilityProvider
        public void onSetUserInputEnabled() {
            this.updatePageAccessibilityActions();
        }

        void setCurrentItemFromAccessibilityCommand(int v) {
            if(ViewPager2.this.isUserInputEnabled()) {
                ViewPager2.this.setCurrentItemInternal(v, true);
            }
        }

        void updatePageAccessibilityActions() {
            ViewPager2 viewPager20 = ViewPager2.this;
            int v = 0x1020048;
            ViewCompat.removeAccessibilityAction(viewPager20, 0x1020048);
            ViewCompat.removeAccessibilityAction(viewPager20, 0x1020049);
            ViewCompat.removeAccessibilityAction(viewPager20, 0x1020046);
            ViewCompat.removeAccessibilityAction(viewPager20, 0x1020047);
            if(ViewPager2.this.getAdapter() != null) {
                int v1 = ViewPager2.this.getAdapter().getItemCount();
                if(v1 != 0 && ViewPager2.this.isUserInputEnabled()) {
                    if(ViewPager2.this.getOrientation() == 0) {
                        boolean z = ViewPager2.this.isRtl();
                        if(z) {
                            v = 0x1020049;
                        }
                        if(ViewPager2.this.mCurrentItem < v1 - 1) {
                            ViewCompat.replaceAccessibilityAction(viewPager20, new AccessibilityActionCompat((z ? 0x1020048 : 0x1020049), null), null, this.mActionPageForward);
                        }
                        if(ViewPager2.this.mCurrentItem > 0) {
                            ViewCompat.replaceAccessibilityAction(viewPager20, new AccessibilityActionCompat(v, null), null, this.mActionPageBackward);
                        }
                    }
                    else {
                        if(ViewPager2.this.mCurrentItem < v1 - 1) {
                            ViewCompat.replaceAccessibilityAction(viewPager20, new AccessibilityActionCompat(0x1020047, null), null, this.mActionPageForward);
                        }
                        if(ViewPager2.this.mCurrentItem > 0) {
                            ViewCompat.replaceAccessibilityAction(viewPager20, new AccessibilityActionCompat(0x1020046, null), null, this.mActionPageBackward);
                        }
                    }
                }
            }
        }
    }

    public interface PageTransformer {
        void transformPage(View arg1, float arg2);
    }

    class PagerSnapHelperImpl extends PagerSnapHelper {
        // 去混淆评级： 低(20)
        @Override  // androidx.recyclerview.widget.PagerSnapHelper
        public View findSnapView(LayoutManager recyclerView$LayoutManager0) {
            return ViewPager2.this.isFakeDragging() ? null : super.findSnapView(recyclerView$LayoutManager0);
        }
    }

    class RecyclerViewImpl extends RecyclerView {
        RecyclerViewImpl(Context context0) {
            super(context0);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.recyclerview.widget.RecyclerView
        public CharSequence getAccessibilityClassName() {
            return ViewPager2.this.mAccessibilityProvider.handlesRvGetAccessibilityClassName() ? ViewPager2.this.mAccessibilityProvider.onRvGetAccessibilityClassName() : super.getAccessibilityClassName();
        }

        @Override  // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(accessibilityEvent0);
            accessibilityEvent0.setFromIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent0.setToIndex(ViewPager2.this.mCurrentItem);
            ViewPager2.this.mAccessibilityProvider.onRvInitializeAccessibilityEvent(accessibilityEvent0);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.recyclerview.widget.RecyclerView
        public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
            return ViewPager2.this.isUserInputEnabled() && super.onInterceptTouchEvent(motionEvent0);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.recyclerview.widget.RecyclerView
        public boolean onTouchEvent(MotionEvent motionEvent0) {
            return ViewPager2.this.isUserInputEnabled() && super.onTouchEvent(motionEvent0);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        Parcelable mAdapterState;
        int mCurrentItem;
        int mRecyclerViewId;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return Build.VERSION.SDK_INT < 24 ? new SavedState(parcel0) : new SavedState(parcel0, classLoader0);
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

        SavedState(Parcel parcel0) {
            super(parcel0);
            this.readValues(parcel0, null);
        }

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.readValues(parcel0, classLoader0);
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        private void readValues(Parcel parcel0, ClassLoader classLoader0) {
            this.mRecyclerViewId = parcel0.readInt();
            this.mCurrentItem = parcel0.readInt();
            this.mAdapterState = parcel0.readParcelable(classLoader0);
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.mRecyclerViewId);
            parcel0.writeInt(this.mCurrentItem);
            parcel0.writeParcelable(this.mAdapterState, v);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollState {
    }

    static class SmoothScrollToPosition implements Runnable {
        private final int mPosition;
        private final RecyclerView mRecyclerView;

        SmoothScrollToPosition(int v, RecyclerView recyclerView0) {
            this.mPosition = v;
            this.mRecyclerView = recyclerView0;
        }

        @Override
        public void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    public static final int OFFSCREEN_PAGE_LIMIT_DEFAULT = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    AccessibilityProvider mAccessibilityProvider;
    int mCurrentItem;
    private AdapterDataObserver mCurrentItemDataSetChangeObserver;
    boolean mCurrentItemDirty;
    private CompositeOnPageChangeCallback mExternalPageChangeCallbacks;
    private FakeDrag mFakeDragger;
    LinearLayoutManager mLayoutManager;
    private int mOffscreenPageLimit;
    private CompositeOnPageChangeCallback mPageChangeEventDispatcher;
    private PageTransformerAdapter mPageTransformerAdapter;
    private PagerSnapHelper mPagerSnapHelper;
    private Parcelable mPendingAdapterState;
    private int mPendingCurrentItem;
    RecyclerView mRecyclerView;
    private ItemAnimator mSavedItemAnimator;
    private boolean mSavedItemAnimatorPresent;
    ScrollEventAdapter mScrollEventAdapter;
    private final Rect mTmpChildRect;
    private final Rect mTmpContainerRect;
    private boolean mUserInputEnabled;
    static boolean sFeatureEnhancedA11yEnabled = true;

    static {
    }

    public ViewPager2(Context context0) {
        super(context0);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() {
            {
                ViewPager2.this = viewPager20;
                super(null);
            }

            @Override  // androidx.viewpager2.widget.ViewPager2$DataSetChangeObserver
            public void onChanged() {
                ViewPager2.this.mCurrentItemDirty = true;
                ViewPager2.this.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        this.initialize(context0, null);
    }

    public ViewPager2(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() {
            {
                ViewPager2.this = viewPager20;
                super(null);
            }

            @Override  // androidx.viewpager2.widget.ViewPager2$DataSetChangeObserver
            public void onChanged() {
                ViewPager2.this.mCurrentItemDirty = true;
                ViewPager2.this.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        this.initialize(context0, attributeSet0);
    }

    public ViewPager2(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() {
            {
                ViewPager2.this = viewPager20;
                super(null);
            }

            @Override  // androidx.viewpager2.widget.ViewPager2$DataSetChangeObserver
            public void onChanged() {
                ViewPager2.this.mCurrentItemDirty = true;
                ViewPager2.this.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        this.initialize(context0, attributeSet0);
    }

    public ViewPager2(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() {
            {
                ViewPager2.this = viewPager20;
                super(null);
            }

            @Override  // androidx.viewpager2.widget.ViewPager2$DataSetChangeObserver
            public void onChanged() {
                ViewPager2.this.mCurrentItemDirty = true;
                ViewPager2.this.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        this.initialize(context0, attributeSet0);
    }

    public void addItemDecoration(ItemDecoration recyclerView$ItemDecoration0) {
        this.mRecyclerView.addItemDecoration(recyclerView$ItemDecoration0);
    }

    public void addItemDecoration(ItemDecoration recyclerView$ItemDecoration0, int v) {
        this.mRecyclerView.addItemDecoration(recyclerView$ItemDecoration0, v);
    }

    public boolean beginFakeDrag() {
        return this.mFakeDragger.beginFakeDrag();
    }

    @Override  // android.view.View
    public boolean canScrollHorizontally(int v) {
        return this.mRecyclerView.canScrollHorizontally(v);
    }

    @Override  // android.view.View
    public boolean canScrollVertically(int v) {
        return this.mRecyclerView.canScrollVertically(v);
    }

    @Override  // android.view.ViewGroup
    protected void dispatchRestoreInstanceState(SparseArray sparseArray0) {
        Parcelable parcelable0 = (Parcelable)sparseArray0.get(this.getId());
        if(parcelable0 instanceof SavedState) {
            int v = ((SavedState)parcelable0).mRecyclerViewId;
            sparseArray0.put(this.mRecyclerView.getId(), ((Parcelable)sparseArray0.get(v)));
            sparseArray0.remove(v);
        }
        super.dispatchRestoreInstanceState(sparseArray0);
        this.restorePendingState();
    }

    public boolean endFakeDrag() {
        return this.mFakeDragger.endFakeDrag();
    }

    private OnChildAttachStateChangeListener enforceChildFillListener() {
        return new OnChildAttachStateChangeListener() {
            @Override  // androidx.recyclerview.widget.RecyclerView$OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(recyclerView$LayoutParams0.width != -1 || recyclerView$LayoutParams0.height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view0) {
            }
        };
    }

    public boolean fakeDragBy(float f) {
        return this.mFakeDragger.fakeDragBy(f);
    }

    @Override  // android.view.ViewGroup
    public CharSequence getAccessibilityClassName() {
        return this.mAccessibilityProvider.handlesGetAccessibilityClassName() ? this.mAccessibilityProvider.onGetAccessibilityClassName() : super.getAccessibilityClassName();
    }

    public Adapter getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    public ItemDecoration getItemDecorationAt(int v) {
        return this.mRecyclerView.getItemDecorationAt(v);
    }

    public int getItemDecorationCount() {
        return this.mRecyclerView.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getOrientation() {
        return this.mLayoutManager.getOrientation() == 1 ? 1 : 0;
    }

    int getPageSize() {
        RecyclerView recyclerView0 = this.mRecyclerView;
        return this.getOrientation() == 0 ? recyclerView0.getWidth() - recyclerView0.getPaddingLeft() - recyclerView0.getPaddingRight() : recyclerView0.getHeight() - recyclerView0.getPaddingTop() - recyclerView0.getPaddingBottom();
    }

    public int getScrollState() {
        return this.mScrollEventAdapter.getScrollState();
    }

    private void initialize(Context context0, AttributeSet attributeSet0) {
        PageAwareAccessibilityProvider viewPager2$PageAwareAccessibilityProvider0 = ViewPager2.sFeatureEnhancedA11yEnabled ? new PageAwareAccessibilityProvider(this) : new BasicAccessibilityProvider(this);
        this.mAccessibilityProvider = viewPager2$PageAwareAccessibilityProvider0;
        RecyclerViewImpl viewPager2$RecyclerViewImpl0 = new RecyclerViewImpl(this, context0);
        this.mRecyclerView = viewPager2$RecyclerViewImpl0;
        viewPager2$RecyclerViewImpl0.setId(ViewCompat.generateViewId());
        this.mRecyclerView.setDescendantFocusability(0x20000);
        LinearLayoutManagerImpl viewPager2$LinearLayoutManagerImpl0 = new LinearLayoutManagerImpl(this, context0);
        this.mLayoutManager = viewPager2$LinearLayoutManagerImpl0;
        this.mRecyclerView.setLayoutManager(viewPager2$LinearLayoutManagerImpl0);
        this.mRecyclerView.setScrollingTouchSlop(1);
        this.setOrientation(context0, attributeSet0);
        this.mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mRecyclerView.addOnChildAttachStateChangeListener(this.enforceChildFillListener());
        this.mScrollEventAdapter = new ScrollEventAdapter(this);
        this.mFakeDragger = new FakeDrag(this, this.mScrollEventAdapter, this.mRecyclerView);
        PagerSnapHelperImpl viewPager2$PagerSnapHelperImpl0 = new PagerSnapHelperImpl(this);
        this.mPagerSnapHelper = viewPager2$PagerSnapHelperImpl0;
        viewPager2$PagerSnapHelperImpl0.attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.addOnScrollListener(this.mScrollEventAdapter);
        CompositeOnPageChangeCallback compositeOnPageChangeCallback0 = new CompositeOnPageChangeCallback(3);
        this.mPageChangeEventDispatcher = compositeOnPageChangeCallback0;
        this.mScrollEventAdapter.setOnPageChangeCallback(compositeOnPageChangeCallback0);
        androidx.viewpager2.widget.ViewPager2.2 viewPager2$20 = new OnPageChangeCallback() {
            @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
            public void onPageScrollStateChanged(int v) {
                if(v == 0) {
                    ViewPager2.this.updateCurrentItem();
                }
            }

            @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
            public void onPageSelected(int v) {
                if(ViewPager2.this.mCurrentItem != v) {
                    ViewPager2.this.mCurrentItem = v;
                    ViewPager2.this.mAccessibilityProvider.onSetNewCurrentItem();
                }
            }
        };
        androidx.viewpager2.widget.ViewPager2.3 viewPager2$30 = new OnPageChangeCallback() {
            @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
            public void onPageSelected(int v) {
                ViewPager2.this.clearFocus();
                if(ViewPager2.this.hasFocus()) {
                    ViewPager2.this.mRecyclerView.requestFocus(2);
                }
            }
        };
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(viewPager2$20);
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(viewPager2$30);
        this.mAccessibilityProvider.onInitialize(this.mPageChangeEventDispatcher, this.mRecyclerView);
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(this.mExternalPageChangeCallbacks);
        PageTransformerAdapter pageTransformerAdapter0 = new PageTransformerAdapter(this.mLayoutManager);
        this.mPageTransformerAdapter = pageTransformerAdapter0;
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(pageTransformerAdapter0);
        this.attachViewToParent(this.mRecyclerView, 0, this.mRecyclerView.getLayoutParams());
    }

    public void invalidateItemDecorations() {
        this.mRecyclerView.invalidateItemDecorations();
    }

    public boolean isFakeDragging() {
        return this.mFakeDragger.isFakeDragging();
    }

    boolean isRtl() {
        return this.mLayoutManager.getLayoutDirection() == 1;
    }

    public boolean isUserInputEnabled() {
        return this.mUserInputEnabled;
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        this.mAccessibilityProvider.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = this.mRecyclerView.getMeasuredWidth();
        int v5 = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = this.getPaddingLeft();
        this.mTmpContainerRect.right = v2 - v - this.getPaddingRight();
        this.mTmpContainerRect.top = this.getPaddingTop();
        this.mTmpContainerRect.bottom = v3 - v1 - this.getPaddingBottom();
        Gravity.apply(0x800033, v4, v5, this.mTmpContainerRect, this.mTmpChildRect);
        this.mRecyclerView.layout(this.mTmpChildRect.left, this.mTmpChildRect.top, this.mTmpChildRect.right, this.mTmpChildRect.bottom);
        if(this.mCurrentItemDirty) {
            this.updateCurrentItem();
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        this.measureChild(this.mRecyclerView, v, v1);
        int v2 = this.mRecyclerView.getMeasuredWidth();
        int v3 = this.mRecyclerView.getMeasuredHeight();
        int v4 = this.mRecyclerView.getMeasuredState();
        int v5 = this.getPaddingLeft();
        int v6 = this.getPaddingRight();
        int v7 = this.getPaddingTop();
        int v8 = this.getPaddingBottom();
        int v9 = Math.max(v2 + (v5 + v6), this.getSuggestedMinimumWidth());
        int v10 = Math.max(v3 + (v7 + v8), this.getSuggestedMinimumHeight());
        this.setMeasuredDimension(ViewPager2.resolveSizeAndState(v9, v, v4), ViewPager2.resolveSizeAndState(v10, v1, v4 << 16));
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.mPendingCurrentItem = ((SavedState)parcelable0).mCurrentItem;
        this.mPendingAdapterState = ((SavedState)parcelable0).mAdapterState;
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.mRecyclerViewId = this.mRecyclerView.getId();
        parcelable0.mCurrentItem = this.mPendingCurrentItem == -1 ? this.mCurrentItem : this.mPendingCurrentItem;
        Parcelable parcelable1 = this.mPendingAdapterState;
        if(parcelable1 != null) {
            parcelable0.mAdapterState = parcelable1;
            return parcelable0;
        }
        Adapter recyclerView$Adapter0 = this.mRecyclerView.getAdapter();
        if(recyclerView$Adapter0 instanceof StatefulAdapter) {
            parcelable0.mAdapterState = ((StatefulAdapter)recyclerView$Adapter0).saveState();
        }
        return parcelable0;
    }

    @Override  // android.view.ViewGroup
    public void onViewAdded(View view0) {
        throw new IllegalStateException(this.getClass().getSimpleName() + " does not support direct child views");
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.View
    public boolean performAccessibilityAction(int v, Bundle bundle0) {
        return this.mAccessibilityProvider.handlesPerformAccessibilityAction(v, bundle0) ? this.mAccessibilityProvider.onPerformAccessibilityAction(v, bundle0) : super.performAccessibilityAction(v, bundle0);
    }

    private void registerCurrentItemDataSetTracker(Adapter recyclerView$Adapter0) {
        if(recyclerView$Adapter0 != null) {
            recyclerView$Adapter0.registerAdapterDataObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    public void registerOnPageChangeCallback(OnPageChangeCallback viewPager2$OnPageChangeCallback0) {
        this.mExternalPageChangeCallbacks.addOnPageChangeCallback(viewPager2$OnPageChangeCallback0);
    }

    public void removeItemDecoration(ItemDecoration recyclerView$ItemDecoration0) {
        this.mRecyclerView.removeItemDecoration(recyclerView$ItemDecoration0);
    }

    public void removeItemDecorationAt(int v) {
        this.mRecyclerView.removeItemDecorationAt(v);
    }

    public void requestTransform() {
        if(this.mPageTransformerAdapter.getPageTransformer() == null) {
            return;
        }
        double f = this.mScrollEventAdapter.getRelativeScrollPosition();
        float f1 = (float)(f - ((double)(((int)f))));
        int v = Math.round(((float)this.getPageSize()) * f1);
        this.mPageTransformerAdapter.onPageScrolled(((int)f), f1, v);
    }

    private void restorePendingState() {
        if(this.mPendingCurrentItem != -1) {
            Adapter recyclerView$Adapter0 = this.getAdapter();
            if(recyclerView$Adapter0 != null) {
                Parcelable parcelable0 = this.mPendingAdapterState;
                if(parcelable0 != null) {
                    if(recyclerView$Adapter0 instanceof StatefulAdapter) {
                        ((StatefulAdapter)recyclerView$Adapter0).restoreState(parcelable0);
                    }
                    this.mPendingAdapterState = null;
                }
                int v = Math.max(0, Math.min(this.mPendingCurrentItem, recyclerView$Adapter0.getItemCount() - 1));
                this.mCurrentItem = v;
                this.mPendingCurrentItem = -1;
                this.mRecyclerView.scrollToPosition(v);
                this.mAccessibilityProvider.onRestorePendingState();
            }
        }
    }

    public void setAdapter(Adapter recyclerView$Adapter0) {
        Adapter recyclerView$Adapter1 = this.mRecyclerView.getAdapter();
        this.mAccessibilityProvider.onDetachAdapter(recyclerView$Adapter1);
        this.unregisterCurrentItemDataSetTracker(recyclerView$Adapter1);
        this.mRecyclerView.setAdapter(recyclerView$Adapter0);
        this.mCurrentItem = 0;
        this.restorePendingState();
        this.mAccessibilityProvider.onAttachAdapter(recyclerView$Adapter0);
        this.registerCurrentItemDataSetTracker(recyclerView$Adapter0);
    }

    public void setCurrentItem(int v) {
        this.setCurrentItem(v, true);
    }

    public void setCurrentItem(int v, boolean z) {
        if(this.isFakeDragging()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        this.setCurrentItemInternal(v, z);
    }

    void setCurrentItemInternal(int v, boolean z) {
        Adapter recyclerView$Adapter0 = this.getAdapter();
        if(recyclerView$Adapter0 == null) {
            if(this.mPendingCurrentItem != -1) {
                this.mPendingCurrentItem = Math.max(v, 0);
            }
        }
        else if(recyclerView$Adapter0.getItemCount() > 0) {
            int v1 = Math.min(Math.max(v, 0), recyclerView$Adapter0.getItemCount() - 1);
            if(v1 != this.mCurrentItem || !this.mScrollEventAdapter.isIdle()) {
                int v2 = this.mCurrentItem;
                if(v1 != v2 || !z) {
                    double f = (double)v2;
                    this.mCurrentItem = v1;
                    this.mAccessibilityProvider.onSetNewCurrentItem();
                    if(!this.mScrollEventAdapter.isIdle()) {
                        f = this.mScrollEventAdapter.getRelativeScrollPosition();
                    }
                    this.mScrollEventAdapter.notifyProgrammaticScroll(v1, z);
                    if(!z) {
                        this.mRecyclerView.scrollToPosition(v1);
                        return;
                    }
                    if(Math.abs(((double)v1) - f) > 3.0) {
                        this.mRecyclerView.scrollToPosition((((double)v1) > f ? v1 - 3 : v1 + 3));
                        this.mRecyclerView.post(new SmoothScrollToPosition(v1, this.mRecyclerView));
                        return;
                    }
                    this.mRecyclerView.smoothScrollToPosition(v1);
                }
            }
        }
    }

    @Override  // android.view.View
    public void setLayoutDirection(int v) {
        super.setLayoutDirection(v);
        this.mAccessibilityProvider.onSetLayoutDirection();
    }

    public void setOffscreenPageLimit(int v) {
        if(v < 1 && v != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.mOffscreenPageLimit = v;
        this.mRecyclerView.requestLayout();
    }

    private void setOrientation(Context context0, AttributeSet attributeSet0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ViewPager2);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.ViewPager2, attributeSet0, typedArray0, 0, 0);
        try {
            this.setOrientation(typedArray0.getInt(styleable.ViewPager2_android_orientation, 0));
        }
        finally {
            typedArray0.recycle();
        }
    }

    public void setOrientation(int v) {
        this.mLayoutManager.setOrientation(v);
        this.mAccessibilityProvider.onSetOrientation();
    }

    public void setPageTransformer(PageTransformer viewPager2$PageTransformer0) {
        if(viewPager2$PageTransformer0 != null) {
            if(!this.mSavedItemAnimatorPresent) {
                this.mSavedItemAnimator = this.mRecyclerView.getItemAnimator();
                this.mSavedItemAnimatorPresent = true;
            }
            this.mRecyclerView.setItemAnimator(null);
        }
        else if(this.mSavedItemAnimatorPresent) {
            this.mRecyclerView.setItemAnimator(this.mSavedItemAnimator);
            this.mSavedItemAnimator = null;
            this.mSavedItemAnimatorPresent = false;
        }
        if(viewPager2$PageTransformer0 == this.mPageTransformerAdapter.getPageTransformer()) {
            return;
        }
        this.mPageTransformerAdapter.setPageTransformer(viewPager2$PageTransformer0);
        this.requestTransform();
    }

    public void setUserInputEnabled(boolean z) {
        this.mUserInputEnabled = z;
        this.mAccessibilityProvider.onSetUserInputEnabled();
    }

    void snapToPage() {
        View view0 = this.mPagerSnapHelper.findSnapView(this.mLayoutManager);
        if(view0 != null) {
            int[] arr_v = this.mPagerSnapHelper.calculateDistanceToFinalSnap(this.mLayoutManager, view0);
            int v = arr_v[0];
            if(v != 0 || arr_v[1] != 0) {
                this.mRecyclerView.smoothScrollBy(v, arr_v[1]);
            }
        }
    }

    private void unregisterCurrentItemDataSetTracker(Adapter recyclerView$Adapter0) {
        if(recyclerView$Adapter0 != null) {
            recyclerView$Adapter0.unregisterAdapterDataObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    public void unregisterOnPageChangeCallback(OnPageChangeCallback viewPager2$OnPageChangeCallback0) {
        this.mExternalPageChangeCallbacks.removeOnPageChangeCallback(viewPager2$OnPageChangeCallback0);
    }

    void updateCurrentItem() {
        PagerSnapHelper pagerSnapHelper0 = this.mPagerSnapHelper;
        if(pagerSnapHelper0 == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View view0 = pagerSnapHelper0.findSnapView(this.mLayoutManager);
        if(view0 == null) {
            return;
        }
        int v = this.mLayoutManager.getPosition(view0);
        if(v != this.mCurrentItem && this.getScrollState() == 0) {
            this.mPageChangeEventDispatcher.onPageSelected(v);
        }
        this.mCurrentItemDirty = false;
    }
}

