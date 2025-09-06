package androidx.slidingpanelayout.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.Openable;
import androidx.customview.widget.ViewDragHelper.Callback;
import androidx.customview.widget.ViewDragHelper;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;
import androidx.window.layout.FoldingFeature.Orientation;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker.-CC;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SlidingPaneLayout extends ViewGroup implements Openable {
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;

        AccessibilityDelegate() {
            this.mTmpRect = new Rect();
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1) {
            accessibilityNodeInfoCompat1.getBoundsInScreen(this.mTmpRect);
            accessibilityNodeInfoCompat0.setBoundsInScreen(this.mTmpRect);
            accessibilityNodeInfoCompat0.setVisibleToUser(accessibilityNodeInfoCompat1.isVisibleToUser());
            accessibilityNodeInfoCompat0.setPackageName(accessibilityNodeInfoCompat1.getPackageName());
            accessibilityNodeInfoCompat0.setClassName(accessibilityNodeInfoCompat1.getClassName());
            accessibilityNodeInfoCompat0.setContentDescription(accessibilityNodeInfoCompat1.getContentDescription());
            accessibilityNodeInfoCompat0.setEnabled(accessibilityNodeInfoCompat1.isEnabled());
            accessibilityNodeInfoCompat0.setClickable(accessibilityNodeInfoCompat1.isClickable());
            accessibilityNodeInfoCompat0.setFocusable(accessibilityNodeInfoCompat1.isFocusable());
            accessibilityNodeInfoCompat0.setFocused(accessibilityNodeInfoCompat1.isFocused());
            accessibilityNodeInfoCompat0.setAccessibilityFocused(accessibilityNodeInfoCompat1.isAccessibilityFocused());
            accessibilityNodeInfoCompat0.setSelected(accessibilityNodeInfoCompat1.isSelected());
            accessibilityNodeInfoCompat0.setLongClickable(accessibilityNodeInfoCompat1.isLongClickable());
            accessibilityNodeInfoCompat0.addAction(accessibilityNodeInfoCompat1.getActions());
            accessibilityNodeInfoCompat0.setMovementGranularities(accessibilityNodeInfoCompat1.getMovementGranularities());
        }

        public boolean filter(View view0) {
            return SlidingPaneLayout.this.isDimmed(view0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
            accessibilityEvent0.setClassName("androidx.slidingpanelayout.widget.SlidingPaneLayout");
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat1 = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat0);
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat1);
            this.copyNodeInfoNoChildren(accessibilityNodeInfoCompat0, accessibilityNodeInfoCompat1);
            accessibilityNodeInfoCompat0.setClassName("androidx.slidingpanelayout.widget.SlidingPaneLayout");
            accessibilityNodeInfoCompat0.setSource(view0);
            ViewParent viewParent0 = ViewCompat.getParentForAccessibility(view0);
            if(viewParent0 instanceof View) {
                accessibilityNodeInfoCompat0.setParent(((View)viewParent0));
            }
            int v = SlidingPaneLayout.this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = SlidingPaneLayout.this.getChildAt(v1);
                if(!this.filter(view1) && view1.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(view1, 1);
                    accessibilityNodeInfoCompat0.addChild(view1);
                }
            }
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
            return this.filter(view0) ? false : super.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
        }
    }

    class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(View view0) {
            this.mChildView = view0;
        }

        @Override
        public void run() {
            if(this.mChildView.getParent() == SlidingPaneLayout.this) {
                this.mChildView.setLayerType(0, null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    class DragHelperCallback extends Callback {
        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int clampViewPositionHorizontal(View view0, int v, int v1) {
            LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if(SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int v2 = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.getPaddingRight() + slidingPaneLayout$LayoutParams0.rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth());
                return Math.max(Math.min(v, v2), v2 - SlidingPaneLayout.this.mSlideRange);
            }
            int v3 = SlidingPaneLayout.this.getPaddingLeft() + slidingPaneLayout$LayoutParams0.leftMargin;
            return Math.min(Math.max(v, v3), SlidingPaneLayout.this.mSlideRange + v3);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int clampViewPositionVertical(View view0, int v, int v1) {
            return view0.getTop();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public int getViewHorizontalDragRange(View view0) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        private boolean isDraggable() {
            if(SlidingPaneLayout.this.mIsUnableToDrag) {
                return false;
            }
            if(SlidingPaneLayout.this.getLockMode() == 3) {
                return false;
            }
            return !SlidingPaneLayout.this.isOpen() || SlidingPaneLayout.this.getLockMode() != 1 ? SlidingPaneLayout.this.isOpen() || SlidingPaneLayout.this.getLockMode() != 2 : false;
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onEdgeDragStarted(int v, int v1) {
            if(!this.isDraggable()) {
                return;
            }
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, v1);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onEdgeTouched(int v, int v1) {
            if(!this.isDraggable()) {
                return;
            }
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, v1);
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewCaptured(View view0, int v) {
            SlidingPaneLayout.this.setAllChildrenVisible();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewDragStateChanged(int v) {
            if(SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0) {
                if(SlidingPaneLayout.this.mSlideOffset == 1.0f) {
                    SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.mPreservedOpenState = false;
                    return;
                }
                SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.mPreservedOpenState = true;
            }
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
            SlidingPaneLayout.this.onPanelDragged(v);
            SlidingPaneLayout.this.invalidate();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public void onViewReleased(View view0, float f, float f1) {
            int v2;
            LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int v = SlidingPaneLayout.this.getPaddingRight() + slidingPaneLayout$LayoutParams0.rightMargin;
                if(f < 0.0f || f == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f) {
                    v += SlidingPaneLayout.this.mSlideRange;
                }
                int v1 = SlidingPaneLayout.this.mSlideableView.getWidth();
                v2 = SlidingPaneLayout.this.getWidth() - v - v1;
            }
            else {
                int v3 = SlidingPaneLayout.this.getPaddingLeft();
                v2 = slidingPaneLayout$LayoutParams0.leftMargin + v3;
                int v4 = Float.compare(f, 0.0f);
                if(v4 > 0 || v4 == 0 && SlidingPaneLayout.this.mSlideOffset > 0.5f) {
                    v2 += SlidingPaneLayout.this.mSlideRange;
                }
            }
            int v5 = view0.getTop();
            SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(v2, v5);
            SlidingPaneLayout.this.invalidate();
        }

        @Override  // androidx.customview.widget.ViewDragHelper$Callback
        public boolean tryCaptureView(View view0, int v) {
            return this.isDraggable() ? ((LayoutParams)view0.getLayoutParams()).slideable : false;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] ATTRS;
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight;

        static {
            LayoutParams.ATTRS = new int[]{0x1010181};
        }

        public LayoutParams() {
            super(-1, -1);
            this.weight = 0.0f;
        }

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.weight = 0.0f;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.weight = 0.0f;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, LayoutParams.ATTRS);
            this.weight = typedArray0.getFloat(0, 0.0f);
            typedArray0.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.weight = 0.0f;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.weight = 0.0f;
        }

        public LayoutParams(LayoutParams slidingPaneLayout$LayoutParams0) {
            super(slidingPaneLayout$LayoutParams0);
            this.weight = slidingPaneLayout$LayoutParams0.weight;
        }
    }

    public interface PanelSlideListener {
        void onPanelClosed(View arg1);

        void onPanelOpened(View arg1);

        void onPanelSlide(View arg1, float arg2);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean isOpen;
        int mLockMode;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, null);
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
            this.isOpen = parcel0.readInt() != 0;
            this.mLockMode = parcel0.readInt();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(((int)this.isOpen));
            parcel0.writeInt(this.mLockMode);
        }
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelClosed(View view0) {
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelOpened(View view0) {
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelSlide(View view0, float f) {
        }
    }

    static class TouchBlocker extends FrameLayout {
        TouchBlocker(View view0) {
            super(view0.getContext());
            this.addView(view0);
        }

        @Override  // android.view.View
        public boolean onGenericMotionEvent(MotionEvent motionEvent0) {
            return true;
        }

        @Override  // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent0) {
            return true;
        }
    }

    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.slidingpanelayout.widget.SlidingPaneLayout";
    public static final int LOCK_MODE_LOCKED = 3;
    public static final int LOCK_MODE_LOCKED_CLOSED = 2;
    public static final int LOCK_MODE_LOCKED_OPEN = 1;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private boolean mDisplayListReflectionLoaded;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    FoldingFeature mFoldingFeature;
    private FoldingFeatureObserver mFoldingFeatureObserver;
    private Method mGetDisplayList;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private int mLockMode;
    private OnFoldingFeatureChangeListener mOnFoldingFeatureChangeListener;
    private PanelSlideListener mPanelSlideListener;
    private final List mPanelSlideListeners;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList mPostedRunnables;
    boolean mPreservedOpenState;
    private Field mRecreateDisplayList;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;
    private static boolean sEdgeSizeUsingSystemGestureInsets;

    static {
        SlidingPaneLayout.sEdgeSizeUsingSystemGestureInsets = Build.VERSION.SDK_INT >= 29;
    }

    public SlidingPaneLayout(Context context0) {
        this(context0, null);
    }

    public SlidingPaneLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public SlidingPaneLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mSliderFadeColor = 0;
        this.mSlideOffset = 1.0f;
        this.mPanelSlideListeners = new CopyOnWriteArrayList();
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList();
        this.mOnFoldingFeatureChangeListener = new OnFoldingFeatureChangeListener() {
            @Override  // androidx.slidingpanelayout.widget.FoldingFeatureObserver$OnFoldingFeatureChangeListener
            public void onFoldingFeatureChange(FoldingFeature foldingFeature0) {
                SlidingPaneLayout.this.mFoldingFeature = foldingFeature0;
                ChangeBounds changeBounds0 = new ChangeBounds();
                changeBounds0.setDuration(300L);
                changeBounds0.setInterpolator(PathInterpolatorCompat.create(0.2f, 0.0f, 0.0f, 1.0f));
                TransitionManager.beginDelayedTransition(SlidingPaneLayout.this, changeBounds0);
                SlidingPaneLayout.this.requestLayout();
            }
        };
        float f = context0.getResources().getDisplayMetrics().density;
        this.setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate(this));
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewDragHelper viewDragHelper0 = ViewDragHelper.create(this, 0.5f, new DragHelperCallback(this));
        this.mDragHelper = viewDragHelper0;
        viewDragHelper0.setMinVelocity(f * 400.0f);
        this.setFoldingFeatureObserver(new FoldingFeatureObserver(WindowInfoTracker.-CC.getOrCreate(context0), ContextCompat.getMainExecutor(context0)));
    }

    public void addPanelSlideListener(PanelSlideListener slidingPaneLayout$PanelSlideListener0) {
        this.mPanelSlideListeners.add(slidingPaneLayout$PanelSlideListener0);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(this.getChildCount() == 1) {
            super.addView(new TouchBlocker(view0), v, viewGroup$LayoutParams0);
            return;
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    protected boolean canScroll(View view0, boolean z, int v, int v1, int v2) {
        if(view0 instanceof ViewGroup) {
            int v3 = view0.getScrollX();
            int v4 = view0.getScrollY();
            for(int v5 = ((ViewGroup)view0).getChildCount() - 1; v5 >= 0; --v5) {
                View view1 = ((ViewGroup)view0).getChildAt(v5);
                int v6 = v1 + v3;
                if(v6 >= view1.getLeft() && v6 < view1.getRight()) {
                    int v7 = v2 + v4;
                    if(v7 >= view1.getTop() && v7 < view1.getBottom() && this.canScroll(view1, true, v, v6 - view1.getLeft(), v7 - view1.getTop())) {
                        return true;
                    }
                }
            }
        }
        if(z) {
            if(!this.isLayoutRtlSupport()) {
                v = -v;
            }
            return view0.canScrollHorizontally(v);
        }
        return false;
    }

    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // androidx.customview.widget.Openable
    public void close() {
        this.closePane();
    }

    private boolean closePane(int v) {
        if(!this.mCanSlide) {
            this.mPreservedOpenState = false;
        }
        if(!this.mFirstLayout && !this.smoothSlideTo(1.0f, v)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    public boolean closePane() {
        return this.closePane(0);
    }

    @Override  // android.view.View
    public void computeScroll() {
        if(this.mDragHelper.continueSettling(true)) {
            if(!this.mCanSlide) {
                this.mDragHelper.abort();
                return;
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void dispatchOnPanelClosed(View view0) {
        for(Object object0: this.mPanelSlideListeners) {
            ((PanelSlideListener)object0).onPanelClosed(view0);
        }
        this.sendAccessibilityEvent(0x20);
    }

    void dispatchOnPanelOpened(View view0) {
        for(Object object0: this.mPanelSlideListeners) {
            ((PanelSlideListener)object0).onPanelOpened(view0);
        }
        this.sendAccessibilityEvent(0x20);
    }

    void dispatchOnPanelSlide(View view0) {
        for(Object object0: this.mPanelSlideListeners) {
            ((PanelSlideListener)object0).onPanelSlide(view0, this.mSlideOffset);
        }
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        int v4;
        int v3;
        super.draw(canvas0);
        Drawable drawable0 = this.isLayoutRtlSupport() ? this.mShadowDrawableRight : this.mShadowDrawableLeft;
        View view0 = this.getChildCount() <= 1 ? null : this.getChildAt(1);
        if(view0 != null && drawable0 != null) {
            int v = view0.getTop();
            int v1 = view0.getBottom();
            int v2 = drawable0.getIntrinsicWidth();
            if(this.isLayoutRtlSupport()) {
                v3 = view0.getRight();
                v4 = v2 + v3;
            }
            else {
                int v5 = view0.getLeft();
                v4 = v5;
                v3 = v5 - v2;
            }
            drawable0.setBounds(v3, v, v4, v1);
            drawable0.draw(canvas0);
        }
    }

    @Override  // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas0, View view0, long v) {
        if((this.isLayoutRtlSupport() ^ this.isOpen()) == 0) {
            this.mDragHelper.setEdgeTrackingEnabled(2);
            Insets insets1 = this.getSystemGestureInsets();
            if(insets1 != null) {
                this.mDragHelper.setEdgeSize(Math.max(this.mDragHelper.getDefaultEdgeSize(), insets1.right));
            }
        }
        else {
            this.mDragHelper.setEdgeTrackingEnabled(1);
            Insets insets0 = this.getSystemGestureInsets();
            if(insets0 != null) {
                this.mDragHelper.setEdgeSize(Math.max(this.mDragHelper.getDefaultEdgeSize(), insets0.left));
            }
        }
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v1 = canvas0.save();
        if(this.mCanSlide && !slidingPaneLayout$LayoutParams0.slideable && this.mSlideableView != null) {
            canvas0.getClipBounds(this.mTmpRect);
            if(this.isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            }
            else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas0.clipRect(this.mTmpRect);
        }
        boolean z = super.drawChild(canvas0, view0, v);
        canvas0.restoreToCount(v1);
        return z;
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    private static Activity getActivityOrNull(Context context0) {
        while(context0 instanceof ContextWrapper) {
            if(context0 instanceof Activity) {
                return (Activity)context0;
            }
            context0 = ((ContextWrapper)context0).getBaseContext();
        }
        return null;
    }

    @Deprecated
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    private static Rect getFoldBoundsInView(FoldingFeature foldingFeature0, View view0) {
        int[] arr_v = new int[2];
        view0.getLocationInWindow(arr_v);
        int v = arr_v[0];
        Rect rect0 = new Rect(v, arr_v[1], view0.getWidth() + v, arr_v[1] + view0.getWidth());
        Rect rect1 = new Rect(foldingFeature0.getBounds());
        boolean z = rect1.intersect(rect0);
        if(rect1.width() == 0 && rect1.height() == 0 || !z) {
            return null;
        }
        rect1.offset(-arr_v[0], -arr_v[1]);
        return rect1;
    }

    public final int getLockMode() {
        return this.mLockMode;
    }

    // 去混淆评级： 低(20)
    private static int getMinimumWidth(View view0) {
        return view0 instanceof TouchBlocker ? ViewCompat.getMinimumWidth(((TouchBlocker)view0).getChildAt(0)) : ViewCompat.getMinimumWidth(view0);
    }

    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    @Deprecated
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    private Insets getSystemGestureInsets() {
        if(SlidingPaneLayout.sEdgeSizeUsingSystemGestureInsets) {
            WindowInsetsCompat windowInsetsCompat0 = ViewCompat.getRootWindowInsets(this);
            return windowInsetsCompat0 == null ? null : windowInsetsCompat0.getSystemGestureInsets();
        }
        return null;
    }

    void invalidateChildRegion(View view0) {
        ViewCompat.setLayerPaint(view0, ((LayoutParams)view0.getLayoutParams()).dimPaint);
    }

    boolean isDimmed(View view0) {
        if(view0 == null) {
            return false;
        }
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        return this.mCanSlide && slidingPaneLayout$LayoutParams0.dimWhenOffset && this.mSlideOffset > 0.0f;
    }

    boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    @Override  // androidx.customview.widget.Openable
    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 0.0f;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    private static int measureChildHeight(View view0, int v, int v1) {
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        return slidingPaneLayout$LayoutParams0.width != 0 || slidingPaneLayout$LayoutParams0.weight <= 0.0f ? View.MeasureSpec.makeMeasureSpec(view0.getMeasuredHeight(), 0x40000000) : SlidingPaneLayout.getChildMeasureSpec(v, v1, slidingPaneLayout$LayoutParams0.height);
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
        if(this.mFoldingFeatureObserver != null) {
            Activity activity0 = SlidingPaneLayout.getActivityOrNull(this.getContext());
            if(activity0 != null) {
                this.mFoldingFeatureObserver.registerLayoutStateChangeCallback(activity0);
            }
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        FoldingFeatureObserver foldingFeatureObserver0 = this.mFoldingFeatureObserver;
        if(foldingFeatureObserver0 != null) {
            foldingFeatureObserver0.unregisterLayoutStateChangeCallback();
        }
        int v = this.mPostedRunnables.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((DisableLayerRunnable)this.mPostedRunnables.get(v1)).run();
        }
        this.mPostedRunnables.clear();
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(!this.mCanSlide && v == 0 && this.getChildCount() > 1) {
            View view0 = this.getChildAt(1);
            if(view0 != null) {
                int v1 = (int)motionEvent0.getX();
                int v2 = (int)motionEvent0.getY();
                this.mPreservedOpenState = this.mDragHelper.isViewUnder(view0, v1, v2);
            }
        }
        if(this.mCanSlide && (!this.mIsUnableToDrag || v == 0)) {
            if(v != 1 && v != 3) {
                switch(v) {
                    case 0: {
                        this.mIsUnableToDrag = false;
                        float f = motionEvent0.getX();
                        float f1 = motionEvent0.getY();
                        this.mInitialMotionX = f;
                        this.mInitialMotionY = f1;
                        return !this.mDragHelper.isViewUnder(this.mSlideableView, ((int)f), ((int)f1)) || !this.isDimmed(this.mSlideableView) ? this.mDragHelper.shouldInterceptTouchEvent(motionEvent0) : this.mDragHelper.shouldInterceptTouchEvent(motionEvent0) || true;
                    }
                    case 2: {
                        float f2 = motionEvent0.getX();
                        float f3 = motionEvent0.getY();
                        float f4 = Math.abs(f2 - this.mInitialMotionX);
                        if(f4 > ((float)this.mDragHelper.getTouchSlop()) && Math.abs(f3 - this.mInitialMotionY) > f4) {
                            this.mDragHelper.cancel();
                            this.mIsUnableToDrag = true;
                            return false;
                        }
                        return this.mDragHelper.shouldInterceptTouchEvent(motionEvent0);
                    }
                    default: {
                        return this.mDragHelper.shouldInterceptTouchEvent(motionEvent0);
                    }
                }
            }
            this.mDragHelper.cancel();
            return false;
        }
        this.mDragHelper.cancel();
        return super.onInterceptTouchEvent(motionEvent0);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v19;
        int v18;
        int v17;
        boolean z1 = this.isLayoutRtlSupport();
        int v4 = v2 - v;
        int v5 = z1 ? this.getPaddingRight() : this.getPaddingLeft();
        int v6 = z1 ? this.getPaddingLeft() : this.getPaddingRight();
        int v7 = this.getPaddingTop();
        int v8 = this.getChildCount();
        if(this.mFirstLayout) {
            this.mSlideOffset = !this.mCanSlide || !this.mPreservedOpenState ? 1.0f : 0.0f;
        }
        int v9 = v5;
        int v10 = 0;
        while(v10 < v8) {
            View view0 = this.getChildAt(v10);
            if(view0.getVisibility() != 8) {
                LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v11 = view0.getMeasuredWidth();
                if(slidingPaneLayout$LayoutParams0.slideable) {
                    int v12 = v4 - v6;
                    int v13 = Math.min(v5, v12) - v9 - (slidingPaneLayout$LayoutParams0.leftMargin + slidingPaneLayout$LayoutParams0.rightMargin);
                    this.mSlideRange = v13;
                    int v14 = z1 ? slidingPaneLayout$LayoutParams0.rightMargin : slidingPaneLayout$LayoutParams0.leftMargin;
                    slidingPaneLayout$LayoutParams0.dimWhenOffset = v9 + v14 + v13 + v11 / 2 > v12;
                    int v15 = (int)(((float)v13) * this.mSlideOffset);
                    v9 += v14 + v15;
                    this.mSlideOffset = ((float)v15) / ((float)this.mSlideRange);
                }
                else {
                    if(this.mCanSlide) {
                        int v16 = this.mParallaxBy;
                        if(v16 != 0) {
                            v17 = (int)((1.0f - this.mSlideOffset) * ((float)v16));
                            v9 = v5;
                            goto label_33;
                        }
                    }
                    v9 = v5;
                }
                v17 = 0;
            label_33:
                if(z1) {
                    v18 = v4 - v9 + v17;
                    v19 = v18 - v11;
                }
                else {
                    v19 = v9 - v17;
                    v18 = v19 + v11;
                }
                view0.layout(v19, v7, v18, view0.getMeasuredHeight() + v7);
                int v20 = this.mFoldingFeature == null || this.mFoldingFeature.getOrientation() != Orientation.VERTICAL || !this.mFoldingFeature.isSeparating() ? 0 : this.mFoldingFeature.getBounds().width();
                v5 += view0.getWidth() + Math.abs(v20);
            }
            ++v10;
        }
        if(this.mFirstLayout) {
            if(this.mCanSlide && this.mParallaxBy != 0) {
                this.parallaxOtherViews(this.mSlideOffset);
            }
            this.updateObscuredViewsVisibility(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v20;
        int v19;
        int v14;
        int v12;
        int v7;
        int v6;
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getSize(v);
        int v4 = View.MeasureSpec.getMode(v1);
        int v5 = View.MeasureSpec.getSize(v1);
        switch(v4) {
            case 0x80000000: {
                v7 = v5 - this.getPaddingTop() - this.getPaddingBottom();
                v6 = 0;
                break;
            }
            case 0x40000000: {
                v6 = v5 - this.getPaddingTop() - this.getPaddingBottom();
                v7 = v6;
                break;
            }
            default: {
                v6 = 0;
                v7 = 0;
            }
        }
        int v8 = Math.max(v3 - this.getPaddingLeft() - this.getPaddingRight(), 0);
        int v9 = this.getChildCount();
        if(v9 > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.mSlideableView = null;
        int v10 = v8;
        boolean z = false;
        float f = 0.0f;
        for(int v11 = 0; v11 < v9; v11 = v12 + 1) {
            View view0 = this.getChildAt(v11);
            LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            if(view0.getVisibility() == 8) {
                slidingPaneLayout$LayoutParams0.dimWhenOffset = false;
                v12 = v11;
            }
            else {
                if(slidingPaneLayout$LayoutParams0.weight > 0.0f) {
                    f += slidingPaneLayout$LayoutParams0.weight;
                    if(slidingPaneLayout$LayoutParams0.width == 0) {
                        v12 = v11;
                        continue;
                    }
                }
                int v13 = Math.max(v8 - (slidingPaneLayout$LayoutParams0.leftMargin + slidingPaneLayout$LayoutParams0.rightMargin), 0);
                v12 = v11;
                if(slidingPaneLayout$LayoutParams0.width == -2) {
                    v14 = View.MeasureSpec.makeMeasureSpec(v13, (v2 == 0 ? 0 : 0x80000000));
                }
                else {
                    v14 = slidingPaneLayout$LayoutParams0.width == -1 ? View.MeasureSpec.makeMeasureSpec(v13, v2) : View.MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams0.width, 0x40000000);
                }
                view0.measure(v14, SlidingPaneLayout.getChildMeasureSpec(v1, this.getPaddingTop() + this.getPaddingBottom(), slidingPaneLayout$LayoutParams0.height));
                int v15 = view0.getMeasuredWidth();
                int v16 = view0.getMeasuredHeight();
                if(v16 > v6) {
                    switch(v4) {
                        case 0x80000000: {
                            v6 = Math.min(v16, v7);
                            break;
                        }
                        case 0: {
                            v6 = v16;
                        }
                    }
                }
                v10 -= v15;
                if(v12 != 0) {
                    slidingPaneLayout$LayoutParams0.slideable = v10 < 0;
                    z |= v10 < 0;
                    if(slidingPaneLayout$LayoutParams0.slideable) {
                        this.mSlideableView = view0;
                    }
                }
            }
        }
        if(z || f > 0.0f) {
            for(int v17 = 0; v17 < v9; ++v17) {
                View view1 = this.getChildAt(v17);
                if(view1.getVisibility() != 8) {
                    LayoutParams slidingPaneLayout$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                    int v18 = slidingPaneLayout$LayoutParams1.width != 0 || slidingPaneLayout$LayoutParams1.weight <= 0.0f ? view1.getMeasuredWidth() : 0;
                    if(z) {
                        v19 = v8 - (slidingPaneLayout$LayoutParams1.leftMargin + slidingPaneLayout$LayoutParams1.rightMargin);
                        v20 = View.MeasureSpec.makeMeasureSpec(v19, 0x40000000);
                    }
                    else if(slidingPaneLayout$LayoutParams1.weight > 0.0f) {
                        v19 = v18 + ((int)(slidingPaneLayout$LayoutParams1.weight * ((float)Math.max(0, v10)) / f));
                        v20 = View.MeasureSpec.makeMeasureSpec(v19, 0x40000000);
                    }
                    else {
                        v19 = v18;
                        v20 = 0;
                    }
                    int v21 = SlidingPaneLayout.measureChildHeight(view1, v1, this.getPaddingTop() + this.getPaddingBottom());
                    if(v18 != v19) {
                        view1.measure(v20, v21);
                        int v22 = view1.getMeasuredHeight();
                        if(v22 > v6) {
                            switch(v4) {
                                case 0x80000000: {
                                    v6 = Math.min(v22, v7);
                                    break;
                                }
                                case 0: {
                                    v6 = v22;
                                }
                            }
                        }
                    }
                }
            }
        }
        ArrayList arrayList0 = this.splitViewPositions();
        if(arrayList0 != null && !z) {
            for(int v23 = 0; v23 < v9; ++v23) {
                View view2 = this.getChildAt(v23);
                if(view2.getVisibility() != 8) {
                    Rect rect0 = (Rect)arrayList0.get(v23);
                    LayoutParams slidingPaneLayout$LayoutParams2 = (LayoutParams)view2.getLayoutParams();
                    int v24 = slidingPaneLayout$LayoutParams2.leftMargin + slidingPaneLayout$LayoutParams2.rightMargin;
                    int v25 = View.MeasureSpec.makeMeasureSpec(view2.getMeasuredHeight(), 0x40000000);
                    view2.measure(View.MeasureSpec.makeMeasureSpec(rect0.width(), 0x80000000), v25);
                    if((view2.getMeasuredWidthAndState() & 0x1000000) == 1 || SlidingPaneLayout.getMinimumWidth(view2) != 0 && rect0.width() < SlidingPaneLayout.getMinimumWidth(view2)) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(v8 - v24, 0x40000000), v25);
                        if(v23 != 0) {
                            slidingPaneLayout$LayoutParams2.slideable = true;
                            this.mSlideableView = view2;
                            z = true;
                        }
                    }
                    else {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(rect0.width(), 0x40000000), v25);
                    }
                }
            }
        }
        this.setMeasuredDimension(v3, v6 + this.getPaddingTop() + this.getPaddingBottom());
        this.mCanSlide = z;
        if(this.mDragHelper.getViewDragState() != 0 && !z) {
            this.mDragHelper.abort();
        }
    }

    void onPanelDragged(int v) {
        if(this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        boolean z = this.isLayoutRtlSupport();
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)this.mSlideableView.getLayoutParams();
        int v1 = this.mSlideableView.getWidth();
        if(z) {
            v = this.getWidth() - v - v1;
        }
        float f = ((float)(v - ((z ? this.getPaddingRight() : this.getPaddingLeft()) + (z ? slidingPaneLayout$LayoutParams0.rightMargin : slidingPaneLayout$LayoutParams0.leftMargin)))) / ((float)this.mSlideRange);
        this.mSlideOffset = f;
        if(this.mParallaxBy != 0) {
            this.parallaxOtherViews(f);
        }
        this.dispatchOnPanelSlide(this.mSlideableView);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        if(((SavedState)parcelable0).isOpen) {
            this.openPane();
        }
        else {
            this.closePane();
        }
        this.mPreservedOpenState = ((SavedState)parcelable0).isOpen;
        this.setLockMode(((SavedState)parcelable0).mLockMode);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.isOpen = this.isSlideable() ? this.isOpen() : this.mPreservedOpenState;
        parcelable0.mLockMode = this.mLockMode;
        return parcelable0;
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        if(v != v2) {
            this.mFirstLayout = true;
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(!this.mCanSlide) {
            return super.onTouchEvent(motionEvent0);
        }
        this.mDragHelper.processTouchEvent(motionEvent0);
        switch(motionEvent0.getActionMasked()) {
            case 0: {
                float f = motionEvent0.getX();
                float f1 = motionEvent0.getY();
                this.mInitialMotionX = f;
                this.mInitialMotionY = f1;
                return true;
            }
            case 1: {
                if(this.isDimmed(this.mSlideableView)) {
                    float f2 = motionEvent0.getX();
                    float f3 = motionEvent0.getY();
                    float f4 = f2 - this.mInitialMotionX;
                    float f5 = f3 - this.mInitialMotionY;
                    int v = this.mDragHelper.getTouchSlop();
                    if(f4 * f4 + f5 * f5 < ((float)(v * v)) && this.mDragHelper.isViewUnder(this.mSlideableView, ((int)f2), ((int)f3))) {
                        this.closePane(0);
                    }
                }
                return true;
            }
            default: {
                return true;
            }
        }
    }

    @Override  // androidx.customview.widget.Openable
    public void open() {
        this.openPane();
    }

    private boolean openPane(int v) {
        if(!this.mCanSlide) {
            this.mPreservedOpenState = true;
        }
        if(!this.mFirstLayout && !this.smoothSlideTo(0.0f, v)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    public boolean openPane() {
        return this.openPane(0);
    }

    private void parallaxOtherViews(float f) {
        boolean z = this.isLayoutRtlSupport();
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if(view0 != this.mSlideableView) {
                int v2 = (int)((1.0f - this.mParallaxOffset) * ((float)this.mParallaxBy));
                this.mParallaxOffset = f;
                view0.offsetLeftAndRight((z ? -(v2 - ((int)((1.0f - f) * ((float)this.mParallaxBy)))) : v2 - ((int)((1.0f - f) * ((float)this.mParallaxBy)))));
            }
        }
    }

    public void removePanelSlideListener(PanelSlideListener slidingPaneLayout$PanelSlideListener0) {
        this.mPanelSlideListeners.remove(slidingPaneLayout$PanelSlideListener0);
    }

    @Override  // android.view.ViewGroup
    public void removeView(View view0) {
        if(view0.getParent() instanceof TouchBlocker) {
            super.removeView(((View)view0.getParent()));
            return;
        }
        super.removeView(view0);
    }

    @Override  // android.view.ViewGroup
    public void requestChildFocus(View view0, View view1) {
        super.requestChildFocus(view0, view1);
        if(!this.isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = view0 == this.mSlideableView;
        }
    }

    void setAllChildrenVisible() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if(view0.getVisibility() == 4) {
                view0.setVisibility(0);
            }
        }
    }

    @Deprecated
    public void setCoveredFadeColor(int v) {
        this.mCoveredFadeColor = v;
    }

    private void setFoldingFeatureObserver(FoldingFeatureObserver foldingFeatureObserver0) {
        this.mFoldingFeatureObserver = foldingFeatureObserver0;
        foldingFeatureObserver0.setOnFoldingFeatureChangeListener(this.mOnFoldingFeatureChangeListener);
    }

    public final void setLockMode(int v) {
        this.mLockMode = v;
    }

    @Deprecated
    public void setPanelSlideListener(PanelSlideListener slidingPaneLayout$PanelSlideListener0) {
        PanelSlideListener slidingPaneLayout$PanelSlideListener1 = this.mPanelSlideListener;
        if(slidingPaneLayout$PanelSlideListener1 != null) {
            this.removePanelSlideListener(slidingPaneLayout$PanelSlideListener1);
        }
        if(slidingPaneLayout$PanelSlideListener0 != null) {
            this.addPanelSlideListener(slidingPaneLayout$PanelSlideListener0);
        }
        this.mPanelSlideListener = slidingPaneLayout$PanelSlideListener0;
    }

    public void setParallaxDistance(int v) {
        this.mParallaxBy = v;
        this.requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable0) {
        this.setShadowDrawableLeft(drawable0);
    }

    public void setShadowDrawableLeft(Drawable drawable0) {
        this.mShadowDrawableLeft = drawable0;
    }

    public void setShadowDrawableRight(Drawable drawable0) {
        this.mShadowDrawableRight = drawable0;
    }

    @Deprecated
    public void setShadowResource(int v) {
        this.setShadowDrawableLeft(this.getResources().getDrawable(v));
    }

    public void setShadowResourceLeft(int v) {
        this.setShadowDrawableLeft(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setShadowResourceRight(int v) {
        this.setShadowDrawableRight(ContextCompat.getDrawable(this.getContext(), v));
    }

    @Deprecated
    public void setSliderFadeColor(int v) {
        this.mSliderFadeColor = v;
    }

    @Deprecated
    public void smoothSlideClosed() {
        this.closePane();
    }

    @Deprecated
    public void smoothSlideOpen() {
        this.openPane();
    }

    boolean smoothSlideTo(float f, int v) {
        int v3;
        if(!this.mCanSlide) {
            return false;
        }
        boolean z = this.isLayoutRtlSupport();
        LayoutParams slidingPaneLayout$LayoutParams0 = (LayoutParams)this.mSlideableView.getLayoutParams();
        if(z) {
            int v1 = this.getPaddingRight() + slidingPaneLayout$LayoutParams0.rightMargin;
            int v2 = this.mSlideableView.getWidth();
            v3 = (int)(((float)this.getWidth()) - (((float)v1) + f * ((float)this.mSlideRange) + ((float)v2)));
        }
        else {
            v3 = (int)(((float)(this.getPaddingLeft() + slidingPaneLayout$LayoutParams0.leftMargin)) + f * ((float)this.mSlideRange));
        }
        View view0 = this.mSlideableView;
        int v4 = view0.getTop();
        if(this.mDragHelper.smoothSlideViewTo(view0, v3, v4)) {
            this.setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    private ArrayList splitViewPositions() {
        if(this.mFoldingFeature == null || !this.mFoldingFeature.isSeparating() || this.mFoldingFeature.getBounds().left == 0) {
            return null;
        }
        if(this.mFoldingFeature.getBounds().top == 0) {
            Rect rect0 = SlidingPaneLayout.getFoldBoundsInView(this.mFoldingFeature, this);
            if(rect0 == null) {
                return null;
            }
            Rect rect1 = new Rect(this.getPaddingLeft(), this.getPaddingTop(), Math.max(this.getPaddingLeft(), rect0.left), this.getHeight() - this.getPaddingBottom());
            int v = this.getWidth() - this.getPaddingRight();
            return new ArrayList(Arrays.asList(new Rect[]{rect1, new Rect(Math.min(v, rect0.right), this.getPaddingTop(), v, this.getHeight() - this.getPaddingBottom())}));
        }
        return null;
    }

    void updateObscuredViewsVisibility(View view0) {
        int v8;
        int v7;
        int v6;
        int v5;
        boolean z = this.isLayoutRtlSupport();
        int v = z ? this.getWidth() - this.getPaddingRight() : this.getPaddingLeft();
        int v1 = z ? this.getPaddingLeft() : this.getWidth() - this.getPaddingRight();
        int v2 = this.getPaddingTop();
        int v3 = this.getHeight();
        int v4 = this.getPaddingBottom();
        if(view0 == null || !SlidingPaneLayout.viewIsOpaque(view0)) {
            v5 = 0;
            v6 = 0;
            v7 = 0;
            v8 = 0;
        }
        else {
            v5 = view0.getLeft();
            v6 = view0.getRight();
            v7 = view0.getTop();
            v8 = view0.getBottom();
        }
        int v9 = this.getChildCount();
        for(int v10 = 0; v10 < v9; ++v10) {
            View view1 = this.getChildAt(v10);
            if(view1 == view0) {
                break;
            }
            if(view1.getVisibility() != 8) {
                view1.setVisibility((Math.max((z ? v1 : v), view1.getLeft()) < v5 || Math.max(v2, view1.getTop()) < v7 || Math.min((z ? v : v1), view1.getRight()) > v6 || Math.min(v3 - v4, view1.getBottom()) > v8 ? 0 : 4));
            }
        }
    }

    private static boolean viewIsOpaque(View view0) {
        return view0.isOpaque();
    }
}

