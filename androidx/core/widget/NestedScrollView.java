package androidx.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.R.attr;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController;
import androidx.core.view.DifferentialMotionFlingTarget;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ScrollFeedbackProviderCompat;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;

public class NestedScrollView extends FrameLayout implements NestedScrollingChild3, NestedScrollingParent3, ScrollingView {
    static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
            accessibilityEvent0.setClassName("android.widget.ScrollView");
            accessibilityEvent0.setScrollable(((NestedScrollView)view0).getScrollRange() > 0);
            accessibilityEvent0.setScrollX(((NestedScrollView)view0).getScrollX());
            accessibilityEvent0.setScrollY(((NestedScrollView)view0).getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(accessibilityEvent0, ((NestedScrollView)view0).getScrollX());
            AccessibilityRecordCompat.setMaxScrollY(accessibilityEvent0, ((NestedScrollView)view0).getScrollRange());
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            accessibilityNodeInfoCompat0.setClassName("android.widget.ScrollView");
            if(((NestedScrollView)view0).isEnabled()) {
                int v = ((NestedScrollView)view0).getScrollRange();
                if(v > 0) {
                    accessibilityNodeInfoCompat0.setScrollable(true);
                    if(((NestedScrollView)view0).getScrollY() > 0) {
                        accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                        accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_UP);
                    }
                    if(((NestedScrollView)view0).getScrollY() < v) {
                        accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                        accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                    }
                }
            }
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
            if(super.performAccessibilityAction(view0, v, bundle0)) {
                return true;
            }
            if(!((NestedScrollView)view0).isEnabled()) {
                return false;
            }
            int v1 = ((NestedScrollView)view0).getHeight();
            Rect rect0 = new Rect();
            if(((NestedScrollView)view0).getMatrix().isIdentity() && ((NestedScrollView)view0).getGlobalVisibleRect(rect0)) {
                v1 = rect0.height();
            }
            switch(v) {
                case 0x2000: 
                case 0x1020038: {
                    int v5 = ((NestedScrollView)view0).getPaddingBottom();
                    int v6 = ((NestedScrollView)view0).getPaddingTop();
                    int v7 = Math.max(((NestedScrollView)view0).getScrollY() - (v1 - v5 - v6), 0);
                    if(v7 != ((NestedScrollView)view0).getScrollY()) {
                        ((NestedScrollView)view0).smoothScrollTo(0, v7, true);
                        return true;
                    }
                    return false;
                }
                case 0x1000: 
                case 0x102003A: {
                    int v2 = ((NestedScrollView)view0).getPaddingBottom();
                    int v3 = ((NestedScrollView)view0).getPaddingTop();
                    int v4 = Math.min(((NestedScrollView)view0).getScrollY() + (v1 - v2 - v3), ((NestedScrollView)view0).getScrollRange());
                    if(v4 != ((NestedScrollView)view0).getScrollY()) {
                        ((NestedScrollView)view0).smoothScrollTo(0, v4, true);
                        return true;
                    }
                    return false;
                }
                default: {
                    return false;
                }
            }
        }
    }

    static class Api21Impl {
        static boolean getClipToPadding(ViewGroup viewGroup0) {
            return viewGroup0.getClipToPadding();
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

    class DifferentialMotionFlingTargetImpl implements DifferentialMotionFlingTarget {
        @Override  // androidx.core.view.DifferentialMotionFlingTarget
        public float getScaledScrollFactor() {
            return -NestedScrollView.this.getVerticalScrollFactorCompat();
        }

        @Override  // androidx.core.view.DifferentialMotionFlingTarget
        public boolean startDifferentialMotionFling(float f) {
            if(f == 0.0f) {
                return false;
            }
            this.stopDifferentialMotionFling();
            NestedScrollView.this.fling(((int)f));
            return true;
        }

        @Override  // androidx.core.view.DifferentialMotionFlingTarget
        public void stopDifferentialMotionFling() {
            NestedScrollView.this.mScroller.abortAnimation();
        }
    }

    public interface OnScrollChangeListener {
        void onScrollChange(NestedScrollView arg1, int arg2, int arg3, int arg4, int arg5);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        public int scrollPosition;

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

        SavedState(Parcel parcel0) {
            super(parcel0);
            this.scrollPosition = parcel0.readInt();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override
        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.scrollPosition);
        }
    }

    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = null;
    static final int ANIMATED_SCROLL_GAP = 0xFA;
    private static final float DECELERATION_RATE = 0.0f;
    private static final int DEFAULT_SMOOTH_SCROLL_DURATION = 0xFA;
    private static final float FLING_DESTRETCH_FACTOR = 4.0f;
    private static final float INFLEXION = 0.35f;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private static final int[] SCROLLVIEW_STYLEABLE = null;
    private static final float SCROLL_FRICTION = 0.015f;
    private static final String TAG = "NestedScrollView";
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    DifferentialMotionFlingController mDifferentialMotionFlingController;
    final DifferentialMotionFlingTargetImpl mDifferentialMotionFlingTarget;
    public EdgeEffect mEdgeGlowBottom;
    public EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private OnScrollChangeListener mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private final float mPhysicalCoeff;
    private SavedState mSavedState;
    private final int[] mScrollConsumed;
    ScrollFeedbackProviderCompat mScrollFeedbackProvider;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    static {
        NestedScrollView.DECELERATION_RATE = 2.358202f;
        NestedScrollView.ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
        NestedScrollView.SCROLLVIEW_STYLEABLE = new int[]{0x101017A};
    }

    public NestedScrollView(Context context0) {
        this(context0, null);
    }

    public NestedScrollView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.nestedScrollViewStyle);
    }

    public NestedScrollView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mIsLaidOut = false;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        DifferentialMotionFlingTargetImpl nestedScrollView$DifferentialMotionFlingTargetImpl0 = new DifferentialMotionFlingTargetImpl(this);
        this.mDifferentialMotionFlingTarget = nestedScrollView$DifferentialMotionFlingTargetImpl0;
        this.mDifferentialMotionFlingController = new DifferentialMotionFlingController(this.getContext(), nestedScrollView$DifferentialMotionFlingTargetImpl0);
        this.mEdgeGlowTop = EdgeEffectCompat.create(context0, attributeSet0);
        this.mEdgeGlowBottom = EdgeEffectCompat.create(context0, attributeSet0);
        this.mPhysicalCoeff = context0.getResources().getDisplayMetrics().density * 51890.199219f;
        this.initScrollView();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, NestedScrollView.SCROLLVIEW_STYLEABLE, v, 0);
        this.setFillViewport(typedArray0.getBoolean(0, false));
        typedArray0.recycle();
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        this.setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(this, NestedScrollView.ACCESSIBILITY_DELEGATE);
    }

    private void abortAnimatedScroll() {
        this.mScroller.abortAnimation();
        this.stopNestedScroll(1);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view0);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view0, v);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view0, viewGroup$LayoutParams0);
    }

    public boolean arrowScroll(int v) {
        View view0 = this.findFocus();
        if(view0 == this) {
            view0 = null;
        }
        View view1 = FocusFinder.getInstance().findNextFocus(this, view0, v);
        int v1 = this.getMaxScrollAmount();
        if(view1 == null || !this.isWithinDeltaOfScreen(view1, v1, this.getHeight())) {
            if(v == 33 && this.getScrollY() < v1) {
                v1 = this.getScrollY();
            }
            else if(v == 130 && this.getChildCount() > 0) {
                View view2 = this.getChildAt(0);
                FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view2.getLayoutParams();
                v1 = Math.min(view2.getBottom() + frameLayout$LayoutParams0.bottomMargin - (this.getScrollY() + this.getHeight() - this.getPaddingBottom()), v1);
            }
            if(v1 == 0) {
                return false;
            }
            if(v != 130) {
                v1 = -v1;
            }
            this.scrollBy(v1, 0, 1, true);
        }
        else {
            view1.getDrawingRect(this.mTempRect);
            this.offsetDescendantRectToMyCoords(view1, this.mTempRect);
            this.scrollBy(this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect), 0, 1, true);
            view1.requestFocus(v);
        }
        if(view0 != null && view0.isFocused() && this.isOffScreen(view0)) {
            int v2 = this.getDescendantFocusability();
            this.setDescendantFocusability(0x20000);
            this.requestFocus();
            this.setDescendantFocusability(v2);
        }
        return true;
    }

    private boolean canOverScroll() {
        int v = this.getOverScrollMode();
        return v == 0 || v == 1 && this.getScrollRange() > 0;
    }

    private boolean canScroll() {
        if(this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
            return view0.getHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin > this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        }
        return false;
    }

    private static int clamp(int v, int v1, int v2) {
        if(v1 < v2 && v >= 0) {
            return v1 + v <= v2 ? v : v2 - v1;
        }
        return 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override  // android.view.View
    public void computeScroll() {
        int v5;
        if(this.mScroller.isFinished()) {
            return;
        }
        this.mScroller.computeScrollOffset();
        int v = this.mScroller.getCurrY();
        int v1 = this.consumeFlingInVerticalStretch(v - this.mLastScrollerY);
        this.mLastScrollerY = v;
        this.mScrollConsumed[1] = 0;
        this.dispatchNestedPreScroll(0, v1, this.mScrollConsumed, null, 1);
        int v2 = v1 - this.mScrollConsumed[1];
        int v3 = this.getScrollRange();
        if(Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
        }
        if(v2 == 0) {
            v5 = v3;
        }
        else {
            int v4 = this.getScrollY();
            this.overScrollByCompat(0, v2, this.getScrollX(), v4, 0, v3, 0, 0, false);
            v5 = v3;
            int v6 = this.getScrollY() - v4;
            int v7 = v2 - v6;
            this.mScrollConsumed[1] = 0;
            this.dispatchNestedScroll(0, v6, 0, v7, this.mScrollOffset, 1, this.mScrollConsumed);
            v2 = v7 - this.mScrollConsumed[1];
        }
        if(v2 != 0) {
            int v8 = this.getOverScrollMode();
            if(v8 == 0 || v8 == 1 && v5 > 0) {
                if(v2 >= 0) {
                    if(this.mEdgeGlowBottom.isFinished()) {
                        this.mEdgeGlowBottom.onAbsorb(((int)this.mScroller.getCurrVelocity()));
                    }
                }
                else if(this.mEdgeGlowTop.isFinished()) {
                    this.mEdgeGlowTop.onAbsorb(((int)this.mScroller.getCurrVelocity()));
                }
            }
            this.abortAnimatedScroll();
        }
        if(!this.mScroller.isFinished()) {
            this.postInvalidateOnAnimation();
            return;
        }
        this.stopNestedScroll(1);
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect0) {
        if(this.getChildCount() == 0) {
            return 0;
        }
        int v = this.getHeight();
        int v1 = this.getScrollY();
        int v2 = v1 + v;
        int v3 = this.getVerticalFadingEdgeLength();
        if(rect0.top > 0) {
            v1 += v3;
        }
        View view0 = this.getChildAt(0);
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
        int v4 = rect0.bottom >= view0.getHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin ? v2 : v2 - v3;
        if(rect0.bottom > v4 && rect0.top > v1) {
            return rect0.height() <= v ? Math.min(rect0.bottom - v4, view0.getBottom() + frameLayout$LayoutParams0.bottomMargin - v2) : Math.min(rect0.top - v1, view0.getBottom() + frameLayout$LayoutParams0.bottomMargin - v2);
        }
        if(rect0.top < v1 && rect0.bottom < v4) {
            return rect0.height() <= v ? Math.max(-(v1 - rect0.top), -this.getScrollY()) : Math.max(-(v4 - rect0.bottom), -this.getScrollY());
        }
        return 0;
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override  // androidx.core.view.ScrollingView, android.view.View
    public int computeVerticalScrollRange() {
        int v = this.getChildCount();
        int v1 = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
        if(v == 0) {
            return v1;
        }
        View view0 = this.getChildAt(0);
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
        int v2 = view0.getBottom() + frameLayout$LayoutParams0.bottomMargin;
        int v3 = this.getScrollY();
        int v4 = Math.max(0, v2 - v1);
        if(v3 < 0) {
            return v2 - v3;
        }
        return v3 <= v4 ? v2 : v2 + (v3 - v4);
    }

    int consumeFlingInVerticalStretch(int v) {
        int v1 = this.getHeight();
        if(v > 0 && EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) {
            int v2 = Math.round(((float)(-v1)) / 4.0f * EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, ((float)(-v)) * 4.0f / ((float)v1), 0.5f));
            if(v2 != v) {
                this.mEdgeGlowTop.finish();
            }
            return v - v2;
        }
        if(v < 0 && EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0f) {
            int v3 = Math.round(((float)v1) / 4.0f * EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, ((float)v) * 4.0f / ((float)v1), 0.5f));
            if(v3 != v) {
                this.mEdgeGlowBottom.finish();
            }
            return v - v3;
        }
        return v;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        return super.dispatchKeyEvent(keyEvent0) || this.executeKeyEvent(keyEvent0);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f1, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f1, z);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f1) {
        return this.mChildHelper.dispatchNestedPreFling(f, f1);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int v, int v1, int[] arr_v, int[] arr_v1) {
        return this.dispatchNestedPreScroll(v, v1, arr_v, arr_v1, 0);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int v, int v1, int[] arr_v, int[] arr_v1, int v2) {
        return this.mChildHelper.dispatchNestedPreScroll(v, v1, arr_v, arr_v1, v2);
    }

    @Override  // androidx.core.view.NestedScrollingChild3
    public void dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v, int v4, int[] arr_v1) {
        this.mChildHelper.dispatchNestedScroll(v, v1, v2, v3, arr_v, v4, arr_v1);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v) {
        return this.mChildHelper.dispatchNestedScroll(v, v1, v2, v3, arr_v);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int v, int v1, int v2, int v3, int[] arr_v, int v4) {
        return this.mChildHelper.dispatchNestedScroll(v, v1, v2, v3, arr_v, v4);
    }

    private void doScrollY(int v) {
        if(v != 0) {
            if(this.mSmoothScrollingEnabled) {
                this.smoothScrollBy(0, v);
                return;
            }
            this.scrollBy(0, v);
        }
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        int v6;
        super.draw(canvas0);
        int v = this.getScrollY();
        int v1 = 0;
        if(!this.mEdgeGlowTop.isFinished()) {
            int v2 = canvas0.save();
            int v3 = this.getWidth();
            int v4 = this.getHeight();
            int v5 = Math.min(0, v);
            if(Api21Impl.getClipToPadding(this)) {
                v3 -= this.getPaddingLeft() + this.getPaddingRight();
                v6 = this.getPaddingLeft();
            }
            else {
                v6 = 0;
            }
            if(Api21Impl.getClipToPadding(this)) {
                v4 -= this.getPaddingTop() + this.getPaddingBottom();
                v5 += this.getPaddingTop();
            }
            canvas0.translate(((float)v6), ((float)v5));
            this.mEdgeGlowTop.setSize(v3, v4);
            if(this.mEdgeGlowTop.draw(canvas0)) {
                this.postInvalidateOnAnimation();
            }
            canvas0.restoreToCount(v2);
        }
        if(!this.mEdgeGlowBottom.isFinished()) {
            int v7 = canvas0.save();
            int v8 = this.getWidth();
            int v9 = this.getHeight();
            int v10 = Math.max(this.getScrollRange(), v) + v9;
            if(Api21Impl.getClipToPadding(this)) {
                v8 -= this.getPaddingLeft() + this.getPaddingRight();
                v1 = this.getPaddingLeft();
            }
            if(Api21Impl.getClipToPadding(this)) {
                v9 -= this.getPaddingTop() + this.getPaddingBottom();
                v10 -= this.getPaddingBottom();
            }
            canvas0.translate(((float)(v1 - v8)), ((float)v10));
            canvas0.rotate(180.0f, ((float)v8), 0.0f);
            this.mEdgeGlowBottom.setSize(v8, v9);
            if(this.mEdgeGlowBottom.draw(canvas0)) {
                this.postInvalidateOnAnimation();
            }
            canvas0.restoreToCount(v7);
        }
    }

    private boolean edgeEffectFling(int v) {
        if(EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) {
            if(this.shouldAbsorb(this.mEdgeGlowTop, v)) {
                this.mEdgeGlowTop.onAbsorb(v);
                return true;
            }
            this.fling(-v);
            return true;
        }
        if(EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0f) {
            if(this.shouldAbsorb(this.mEdgeGlowBottom, -v)) {
                this.mEdgeGlowBottom.onAbsorb(-v);
                return true;
            }
            this.fling(-v);
            return true;
        }
        return false;
    }

    private void endTouchDrag() {
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.recycleVelocityTracker();
        this.stopNestedScroll(0);
        this.mEdgeGlowTop.onRelease();
        this.mEdgeGlowBottom.onRelease();
    }

    public boolean executeKeyEvent(KeyEvent keyEvent0) {
        this.mTempRect.setEmpty();
        int v = 130;
        if(!this.canScroll()) {
            if(this.isFocused() && keyEvent0.getKeyCode() != 4) {
                View view0 = this.findFocus();
                if(view0 == this) {
                    view0 = null;
                }
                View view1 = FocusFinder.getInstance().findNextFocus(this, view0, 130);
                return view1 != null && view1 != this && view1.requestFocus(130);
            }
            return false;
        }
        if(keyEvent0.getAction() == 0) {
            switch(keyEvent0.getKeyCode()) {
                case 19: {
                    return keyEvent0.isAltPressed() ? this.fullScroll(33) : this.arrowScroll(33);
                }
                case 20: {
                    return keyEvent0.isAltPressed() ? this.fullScroll(130) : this.arrowScroll(130);
                }
                case 62: {
                    if(keyEvent0.isShiftPressed()) {
                        v = 33;
                    }
                    this.pageScroll(v);
                    return false;
                }
                case 92: {
                    return this.fullScroll(33);
                }
                case 93: {
                    return this.fullScroll(130);
                }
                case 0x7A: {
                    this.pageScroll(33);
                    return false;
                }
                case 0x7B: {
                    this.pageScroll(130);
                    return false;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }

    private View findFocusableViewInBounds(boolean z, int v, int v1) {
        ArrayList arrayList0 = this.getFocusables(2);
        int v2 = arrayList0.size();
        View view0 = null;
        int v4 = 0;
        for(int v3 = 0; v3 < v2; ++v3) {
            View view1 = (View)arrayList0.get(v3);
            int v5 = view1.getTop();
            int v6 = view1.getBottom();
            if(v < v6 && v5 < v1) {
                int v7 = v >= v5 || v6 >= v1 ? 0 : 1;
                if(view0 == null) {
                    view0 = view1;
                    v4 = v7;
                }
                else {
                    boolean z1 = z && v5 < view0.getTop() || !z && v6 > view0.getBottom();
                    if(v4 == 0) {
                        if(v7 != 0) {
                            view0 = view1;
                            v4 = 1;
                        }
                        else if(z1) {
                            view0 = view1;
                        }
                    }
                    else if(v7 != 0 && z1) {
                        view0 = view1;
                    }
                }
            }
        }
        return view0;
    }

    public void fling(int v) {
        if(this.getChildCount() > 0) {
            this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, v, 0, 0, 0x80000000, 0x7FFFFFFF, 0, 0);
            this.runAnimatedScroll(true);
            if(Build.VERSION.SDK_INT >= 35) {
                Api35Impl.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
            }
        }
    }

    public boolean fullScroll(int v) {
        int v1 = this.getHeight();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = v1;
        if(v == 130) {
            int v2 = this.getChildCount();
            if(v2 > 0) {
                View view0 = this.getChildAt(v2 - 1);
                FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
                this.mTempRect.bottom = view0.getBottom() + frameLayout$LayoutParams0.bottomMargin + this.getPaddingBottom();
                this.mTempRect.top = this.mTempRect.bottom - v1;
            }
        }
        return this.scrollAndFocus(v, this.mTempRect.top, this.mTempRect.bottom);
    }

    @Override  // android.view.View
    protected float getBottomFadingEdgeStrength() {
        if(this.getChildCount() == 0) {
            return 0.0f;
        }
        View view0 = this.getChildAt(0);
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
        int v = this.getVerticalFadingEdgeLength();
        int v1 = this.getHeight();
        int v2 = this.getPaddingBottom();
        int v3 = view0.getBottom() + frameLayout$LayoutParams0.bottomMargin - this.getScrollY() - (v1 - v2);
        return v3 >= v ? 1.0f : ((float)v3) / ((float)v);
    }

    public int getMaxScrollAmount() {
        return (int)(((float)this.getHeight()) * 0.5f);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    private ScrollFeedbackProviderCompat getScrollFeedbackProvider() {
        if(this.mScrollFeedbackProvider == null) {
            this.mScrollFeedbackProvider = ScrollFeedbackProviderCompat.createProvider(this);
        }
        return this.mScrollFeedbackProvider;
    }

    int getScrollRange() {
        if(this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
            return Math.max(0, view0.getHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin - (this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()));
        }
        return 0;
    }

    private float getSplineFlingDistance(int v) {
        return (float)(((double)(this.mPhysicalCoeff * 0.015f)) * Math.exp(((double)NestedScrollView.DECELERATION_RATE) / (((double)NestedScrollView.DECELERATION_RATE) - 1.0) * Math.log(((float)Math.abs(v)) * 0.35f / (this.mPhysicalCoeff * 0.015f))));
    }

    @Override  // android.view.View
    protected float getTopFadingEdgeStrength() {
        if(this.getChildCount() == 0) {
            return 0.0f;
        }
        int v = this.getVerticalFadingEdgeLength();
        int v1 = this.getScrollY();
        return v1 >= v ? 1.0f : ((float)v1) / ((float)v);
    }

    float getVerticalScrollFactorCompat() {
        if(this.mVerticalScrollFactor == 0.0f) {
            TypedValue typedValue0 = new TypedValue();
            Context context0 = this.getContext();
            if(!context0.getTheme().resolveAttribute(0x101004D, typedValue0, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.mVerticalScrollFactor = typedValue0.getDimension(context0.getResources().getDisplayMetrics());
            return this.mVerticalScrollFactor;
        }
        return this.mVerticalScrollFactor;
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.hasNestedScrollingParent(0);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int v) {
        return this.mChildHelper.hasNestedScrollingParent(v);
    }

    private boolean inChild(int v, int v1) {
        if(this.getChildCount() > 0) {
            int v2 = this.getScrollY();
            View view0 = this.getChildAt(0);
            return v1 >= view0.getTop() - v2 && v1 < view0.getBottom() - v2 && v >= view0.getLeft() && v < view0.getRight();
        }
        return false;
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
            return;
        }
        velocityTracker0.clear();
    }

    private void initScrollView() {
        this.mScroller = new OverScroller(this.getContext());
        this.setFocusable(true);
        this.setDescendantFocusability(0x40000);
        this.setWillNotDraw(false);
        ViewConfiguration viewConfiguration0 = ViewConfiguration.get(this.getContext());
        this.mTouchSlop = viewConfiguration0.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration0.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration0.getScaledMaximumFlingVelocity();
    }

    private void initVelocityTrackerIfNotExists() {
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void initializeTouchDrag(int v, int v1) {
        this.mLastMotionY = v;
        this.mActivePointerId = v1;
        this.startNestedScroll(2, 0);
    }

    public boolean isFillViewport() {
        return this.mFillViewport;
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    private boolean isOffScreen(View view0) {
        return !this.isWithinDeltaOfScreen(view0, 0, this.getHeight());
    }

    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }

    private static boolean isViewDescendantOf(View view0, View view1) {
        if(view0 == view1) {
            return true;
        }
        ViewParent viewParent0 = view0.getParent();
        return viewParent0 instanceof ViewGroup && NestedScrollView.isViewDescendantOf(((View)viewParent0), view1);
    }

    private boolean isWithinDeltaOfScreen(View view0, int v, int v1) {
        view0.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(view0, this.mTempRect);
        return this.mTempRect.bottom + v >= this.getScrollY() && this.mTempRect.top - v <= this.getScrollY() + v1;
    }

    @Override  // android.view.ViewGroup
    protected void measureChild(View view0, int v, int v1) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        view0.measure(NestedScrollView.getChildMeasureSpec(v, this.getPaddingLeft() + this.getPaddingRight(), viewGroup$LayoutParams0.width), 0);
    }

    @Override  // android.view.ViewGroup
    protected void measureChildWithMargins(View view0, int v, int v1, int v2, int v3) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        view0.measure(NestedScrollView.getChildMeasureSpec(v, this.getPaddingLeft() + this.getPaddingRight() + viewGroup$MarginLayoutParams0.leftMargin + viewGroup$MarginLayoutParams0.rightMargin + v1, viewGroup$MarginLayoutParams0.width), View.MeasureSpec.makeMeasureSpec(viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin, 0));
    }

    @Override  // android.view.ViewGroup
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    @Override  // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent0) {
        int v1;
        int v;
        float f;
        if(motionEvent0.getAction() == 8 && !this.mIsBeingDragged) {
            if(MotionEventCompat.isFromSource(motionEvent0, 2)) {
                f = motionEvent0.getAxisValue(9);
                v = (int)motionEvent0.getX();
                v1 = 9;
            }
            else if(MotionEventCompat.isFromSource(motionEvent0, 0x400000)) {
                float f1 = motionEvent0.getAxisValue(26);
                v = this.getWidth() / 2;
                f = f1;
                v1 = 26;
            }
            else {
                v1 = 0;
                v = 0;
                f = 0.0f;
            }
            if(f != 0.0f) {
                this.scrollBy(-((int)(f * this.getVerticalScrollFactorCompat())), v1, motionEvent0, v, 1, MotionEventCompat.isFromSource(motionEvent0, 0x2002));
                if(v1 != 0) {
                    this.mDifferentialMotionFlingController.onMotionEvent(motionEvent0, v1);
                }
                return true;
            }
        }
        return false;
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getAction();
        boolean z = true;
        if(v == 2 && this.mIsBeingDragged) {
            return true;
        }
        if((v & 0xFF) == 0) {
            int v4 = (int)motionEvent0.getY();
            if(!this.inChild(((int)motionEvent0.getX()), v4)) {
                if(!this.stopGlowAnimations(motionEvent0) && this.mScroller.isFinished()) {
                    z = false;
                }
                this.mIsBeingDragged = z;
                this.recycleVelocityTracker();
                return this.mIsBeingDragged;
            }
            this.mLastMotionY = v4;
            this.mActivePointerId = motionEvent0.getPointerId(0);
            this.initOrResetVelocityTracker();
            this.mVelocityTracker.addMovement(motionEvent0);
            this.mScroller.computeScrollOffset();
            if(!this.stopGlowAnimations(motionEvent0) && this.mScroller.isFinished()) {
                z = false;
            }
            this.mIsBeingDragged = z;
            this.startNestedScroll(2, 0);
        }
        else {
            switch(v & 0xFF) {
                case 1: {
                    goto label_8;
                }
                case 2: {
                    goto label_17;
                }
            }
            switch(v & 0xFF) {
                case 3: {
                label_8:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    this.recycleVelocityTracker();
                    if(this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                        this.postInvalidateOnAnimation();
                    }
                    this.stopNestedScroll(0);
                    return this.mIsBeingDragged;
                }
                case 6: {
                    this.onSecondaryPointerUp(motionEvent0);
                    return this.mIsBeingDragged;
                }
                default: {
                    return this.mIsBeingDragged;
                }
            }
        label_17:
            int v1 = this.mActivePointerId;
            if(v1 != -1) {
                int v2 = motionEvent0.findPointerIndex(v1);
                if(v2 == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + v1 + " in onInterceptTouchEvent");
                    return this.mIsBeingDragged;
                }
                int v3 = (int)motionEvent0.getY(v2);
                if(Math.abs(v3 - this.mLastMotionY) > this.mTouchSlop && (2 & this.getNestedScrollAxes()) == 0) {
                    this.mIsBeingDragged = true;
                    this.mLastMotionY = v3;
                    this.initVelocityTrackerIfNotExists();
                    this.mVelocityTracker.addMovement(motionEvent0);
                    this.mNestedYOffset = 0;
                    ViewParent viewParent0 = this.getParent();
                    if(viewParent0 != null) {
                        viewParent0.requestDisallowInterceptTouchEvent(true);
                        return this.mIsBeingDragged;
                    }
                }
            }
        }
        return this.mIsBeingDragged;
    }

    @Override  // android.widget.FrameLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        int v4 = 0;
        this.mIsLayoutDirty = false;
        if(this.mChildToScrollTo != null && NestedScrollView.isViewDescendantOf(this.mChildToScrollTo, this)) {
            this.scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        if(!this.mIsLaidOut) {
            if(this.mSavedState != null) {
                this.scrollTo(this.getScrollX(), this.mSavedState.scrollPosition);
                this.mSavedState = null;
            }
            if(this.getChildCount() > 0) {
                View view0 = this.getChildAt(0);
                FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
                v4 = view0.getMeasuredHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin;
            }
            int v5 = this.getPaddingTop();
            int v6 = this.getPaddingBottom();
            int v7 = this.getScrollY();
            int v8 = NestedScrollView.clamp(v7, v3 - v1 - v5 - v6, v4);
            if(v8 != v7) {
                this.scrollTo(this.getScrollX(), v8);
            }
        }
        this.scrollTo(this.getScrollX(), this.getScrollY());
        this.mIsLaidOut = true;
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(this.mFillViewport && View.MeasureSpec.getMode(v1) != 0 && this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
            int v2 = view0.getMeasuredHeight();
            int v3 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom() - frameLayout$LayoutParams0.topMargin - frameLayout$LayoutParams0.bottomMargin;
            if(v2 < v3) {
                view0.measure(NestedScrollView.getChildMeasureSpec(v, this.getPaddingLeft() + this.getPaddingRight() + frameLayout$LayoutParams0.leftMargin + frameLayout$LayoutParams0.rightMargin, frameLayout$LayoutParams0.width), View.MeasureSpec.makeMeasureSpec(v3, 0x40000000));
            }
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view0, float f, float f1, boolean z) {
        if(!z) {
            this.dispatchNestedFling(0.0f, f1, true);
            this.fling(((int)f1));
            return true;
        }
        return false;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view0, float f, float f1) {
        return this.dispatchNestedPreFling(f, f1);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v) {
        this.onNestedPreScroll(view0, v, v1, arr_v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v, int v2) {
        this.dispatchNestedPreScroll(v, v1, arr_v, null, v2);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3) {
        this.onNestedScrollInternal(v3, 0, null);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4) {
        this.onNestedScrollInternal(v3, v4, null);
    }

    @Override  // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
        this.onNestedScrollInternal(v3, v4, arr_v);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view0, View view1, int v) {
        this.onNestedScrollAccepted(view0, view1, v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view0, View view1, int v, int v1) {
        this.mParentHelper.onNestedScrollAccepted(view0, view1, v, v1);
        this.startNestedScroll(2, v1);
    }

    private void onNestedScrollInternal(int v, int v1, int[] arr_v) {
        int v2 = this.getScrollY();
        this.scrollBy(0, v);
        int v3 = this.getScrollY() - v2;
        if(arr_v != null) {
            arr_v[1] += v3;
        }
        this.mChildHelper.dispatchNestedScroll(0, v3, 0, v - v3, null, v1, arr_v);
    }

    @Override  // android.view.View
    protected void onOverScrolled(int v, int v1, boolean z, boolean z1) {
        super.scrollTo(v, v1);
    }

    @Override  // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int v, Rect rect0) {
        switch(v) {
            case 1: {
                v = 33;
                break;
            }
            case 2: {
                v = 130;
            }
        }
        View view0 = rect0 == null ? FocusFinder.getInstance().findNextFocus(this, null, v) : FocusFinder.getInstance().findNextFocusFromRect(this, rect0, v);
        if(view0 == null) {
            return false;
        }
        return this.isOffScreen(view0) ? false : view0.requestFocus(v, rect0);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.mSavedState = (SavedState)parcelable0;
        this.requestLayout();
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.scrollPosition = this.getScrollY();
        return parcelable0;
    }

    @Override  // android.view.View
    protected void onScrollChanged(int v, int v1, int v2, int v3) {
        super.onScrollChanged(v, v1, v2, v3);
        OnScrollChangeListener nestedScrollView$OnScrollChangeListener0 = this.mOnScrollChangeListener;
        if(nestedScrollView$OnScrollChangeListener0 != null) {
            nestedScrollView$OnScrollChangeListener0.onScrollChange(this, v, v1, v2, v3);
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionIndex();
        if(motionEvent0.getPointerId(v) == this.mActivePointerId) {
            int v1 = v == 0 ? 1 : 0;
            this.mLastMotionY = (int)motionEvent0.getY(v1);
            this.mActivePointerId = motionEvent0.getPointerId(v1);
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.clear();
            }
        }
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        View view0 = this.findFocus();
        if(view0 != null && this != view0 && this.isWithinDeltaOfScreen(view0, 0, v3)) {
            view0.getDrawingRect(this.mTempRect);
            this.offsetDescendantRectToMyCoords(view0, this.mTempRect);
            this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view0, View view1, int v) {
        return this.onStartNestedScroll(view0, view1, v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view0, View view1, int v, int v1) {
        return (v & 2) != 0;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view0) {
        this.onStopNestedScroll(view0, 0);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view0, int v) {
        this.mParentHelper.onStopNestedScroll(view0, v);
        this.stopNestedScroll(v);
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        this.initVelocityTrackerIfNotExists();
        int v = motionEvent0.getActionMasked();
        if(v == 0) {
            this.mNestedYOffset = 0;
        }
        MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
        motionEvent1.offsetLocation(0.0f, ((float)this.mNestedYOffset));
        switch(v) {
            case 0: {
                if(this.getChildCount() == 0) {
                    return false;
                }
                if(this.mIsBeingDragged) {
                    ViewParent viewParent0 = this.getParent();
                    if(viewParent0 != null) {
                        viewParent0.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if(!this.mScroller.isFinished()) {
                    this.abortAnimatedScroll();
                }
                this.initializeTouchDrag(((int)motionEvent0.getY()), motionEvent0.getPointerId(0));
                break;
            }
            case 1: {
                VelocityTracker velocityTracker0 = this.mVelocityTracker;
                velocityTracker0.computeCurrentVelocity(1000, ((float)this.mMaximumVelocity));
                int v1 = (int)velocityTracker0.getYVelocity(this.mActivePointerId);
                if(Math.abs(v1) < this.mMinimumVelocity) {
                    if(this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                        this.postInvalidateOnAnimation();
                    }
                }
                else if(!this.edgeEffectFling(v1) && !this.dispatchNestedPreFling(0.0f, ((float)(-v1)))) {
                    this.dispatchNestedFling(0.0f, ((float)(-v1)), true);
                    this.fling(-v1);
                }
                this.endTouchDrag();
                break;
            }
            case 2: {
                int v2 = motionEvent0.findPointerIndex(this.mActivePointerId);
                if(v2 == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
                }
                else {
                    int v3 = (int)motionEvent0.getY(v2);
                    int v4 = this.mLastMotionY - v3;
                    int v5 = v4 - this.releaseVerticalGlow(v4, motionEvent0.getX(v2));
                    if(!this.mIsBeingDragged && Math.abs(v5) > this.mTouchSlop) {
                        ViewParent viewParent1 = this.getParent();
                        if(viewParent1 != null) {
                            viewParent1.requestDisallowInterceptTouchEvent(true);
                        }
                        this.mIsBeingDragged = true;
                        v5 = v5 <= 0 ? v5 + this.mTouchSlop : v5 - this.mTouchSlop;
                    }
                    if(this.mIsBeingDragged) {
                        int v6 = this.scrollBy(v5, 1, motionEvent0, ((int)motionEvent0.getX(v2)), 0, false);
                        this.mLastMotionY = v3 - v6;
                        this.mNestedYOffset += v6;
                    }
                }
                break;
            }
            case 3: {
                if(this.mIsBeingDragged && this.getChildCount() > 0 && this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    this.postInvalidateOnAnimation();
                }
                this.endTouchDrag();
                break;
            }
            case 5: {
                int v7 = motionEvent0.getActionIndex();
                this.mLastMotionY = (int)motionEvent0.getY(v7);
                this.mActivePointerId = motionEvent0.getPointerId(v7);
                break;
            }
            case 6: {
                this.onSecondaryPointerUp(motionEvent0);
                this.mLastMotionY = (int)motionEvent0.getY(motionEvent0.findPointerIndex(this.mActivePointerId));
            }
        }
        VelocityTracker velocityTracker1 = this.mVelocityTracker;
        if(velocityTracker1 != null) {
            velocityTracker1.addMovement(motionEvent1);
        }
        motionEvent1.recycle();
        return true;
    }

    boolean overScrollByCompat(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, boolean z) {
        int v15;
        boolean z2;
        boolean z1;
        int v8 = this.getOverScrollMode();
        int v9 = v2 + v;
        int v10 = v8 == 0 || v8 == 1 && this.computeHorizontalScrollRange() > this.computeHorizontalScrollExtent() ? v6 : 0;
        int v11 = v3 + v1;
        int v12 = v8 == 0 || v8 == 1 && this.computeVerticalScrollRange() > this.computeVerticalScrollExtent() ? v7 : 0;
        int v13 = v10 + v4;
        int v14 = v12 + v5;
        if(v9 > v13) {
            v9 = v13;
            z1 = true;
        }
        else if(v9 < -v10) {
            v9 = -v10;
            z1 = true;
        }
        else {
            z1 = false;
        }
        if(v11 > v14) {
            v11 = v14;
            z2 = true;
        }
        else if(v11 < -v12) {
            v11 = -v12;
            z2 = true;
        }
        else {
            z2 = false;
        }
        if(z2 && !this.hasNestedScrollingParent(1)) {
            this.mScroller.springBack(v9, v11, 0, 0, 0, this.getScrollRange());
        }
        v15 = v9;
        this.onOverScrolled(v15, v11, z1, z2);
        return z1 || z2;
    }

    public boolean pageScroll(int v) {
        int v1 = this.getHeight();
        if(v == 130) {
            this.mTempRect.top = this.getScrollY() + v1;
            int v2 = this.getChildCount();
            if(v2 > 0) {
                View view0 = this.getChildAt(v2 - 1);
                FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
                int v3 = view0.getBottom() + frameLayout$LayoutParams0.bottomMargin + this.getPaddingBottom();
                if(this.mTempRect.top + v1 > v3) {
                    this.mTempRect.top = v3 - v1;
                }
            }
        }
        else {
            this.mTempRect.top = this.getScrollY() - v1;
            if(this.mTempRect.top < 0) {
                this.mTempRect.top = 0;
            }
        }
        this.mTempRect.bottom = this.mTempRect.top + v1;
        return this.scrollAndFocus(v, this.mTempRect.top, this.mTempRect.bottom);
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
            this.mVelocityTracker = null;
        }
    }

    private int releaseVerticalGlow(int v, float f) {
        float f4;
        float f1 = f / ((float)this.getWidth());
        float f2 = ((float)v) / ((float)this.getHeight());
        float f3 = 0.0f;
        if(EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) {
            f4 = -EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, -f2, f1);
            if(EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0f) {
                this.mEdgeGlowTop.onRelease();
            }
            f3 = f4;
        }
        else if(EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0f) {
            f4 = EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, f2, 1.0f - f1);
            if(EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) {
                this.mEdgeGlowBottom.onRelease();
            }
            f3 = f4;
        }
        int v1 = Math.round(f3 * ((float)this.getHeight()));
        if(v1 != 0) {
            this.invalidate();
        }
        return v1;
    }

    @Override  // android.view.ViewGroup
    public void requestChildFocus(View view0, View view1) {
        if(this.mIsLayoutDirty) {
            this.mChildToScrollTo = view1;
        }
        else {
            this.scrollToChild(view1);
        }
        super.requestChildFocus(view0, view1);
    }

    @Override  // android.view.ViewGroup
    public boolean requestChildRectangleOnScreen(View view0, Rect rect0, boolean z) {
        rect0.offset(view0.getLeft() - view0.getScrollX(), view0.getTop() - view0.getScrollY());
        return this.scrollToChildRect(rect0, z);
    }

    @Override  // android.view.ViewGroup
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if(z) {
            this.recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override  // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    private void runAnimatedScroll(boolean z) {
        if(z) {
            this.startNestedScroll(2, 1);
        }
        else {
            this.stopNestedScroll(1);
        }
        this.mLastScrollerY = this.getScrollY();
        this.postInvalidateOnAnimation();
    }

    private boolean scrollAndFocus(int v, int v1, int v2) {
        int v3 = this.getHeight();
        int v4 = this.getScrollY();
        int v5 = v3 + v4;
        boolean z = false;
        View view0 = this.findFocusableViewInBounds(v == 33, v1, v2);
        if(view0 == null) {
            view0 = this;
        }
        if(v1 < v4 || v2 > v5) {
            this.scrollBy((v == 33 ? v2 - v5 : v1 - v4), 0, 1, true);
            z = true;
        }
        if(view0 != this.findFocus()) {
            view0.requestFocus(v);
        }
        return z;
    }

    private int scrollBy(int v, int v1, int v2, boolean z) {
        return this.scrollBy(v, -1, null, v1, v2, z);
    }

    int scrollBy(int v, int v1, MotionEvent motionEvent0, int v2, int v3, boolean z) {
        int v7;
        int v6;
        if(v3 == 1) {
            this.startNestedScroll(2, 1);
        }
        int v4 = 0;
        if(this.dispatchNestedPreScroll(0, v, this.mScrollConsumed, this.mScrollOffset, v3)) {
            int v5 = v - this.mScrollConsumed[1];
            v6 = this.mScrollOffset[1];
            v7 = v5;
        }
        else {
            v7 = v;
            v6 = 0;
        }
        int v8 = this.getScrollY();
        int v9 = this.getScrollRange();
        boolean z1 = this.canOverScroll() && !z;
        int v10 = !this.overScrollByCompat(0, v7, 0, v8, 0, v9, 0, 0, true) || this.hasNestedScrollingParent(v3) ? 0 : 1;
        int v11 = this.getScrollY() - v8;
        if(motionEvent0 != null && v11 != 0) {
            this.getScrollFeedbackProvider().onScrollProgress(motionEvent0.getDeviceId(), motionEvent0.getSource(), v1, v11);
        }
        this.mScrollConsumed[1] = 0;
        this.dispatchNestedScroll(0, v11, 0, v7 - v11, this.mScrollOffset, v3, this.mScrollConsumed);
        int v12 = v6 + this.mScrollOffset[1];
        int v13 = v7 - this.mScrollConsumed[1];
        int v14 = v8 + v13;
        if(v14 >= 0) {
            if(v14 > v9 && z1) {
                EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, ((float)v13) / ((float)this.getHeight()), 1.0f - ((float)v2) / ((float)this.getWidth()));
                if(motionEvent0 != null) {
                    this.getScrollFeedbackProvider().onScrollLimit(motionEvent0.getDeviceId(), motionEvent0.getSource(), v1, false);
                }
                if(!this.mEdgeGlowTop.isFinished()) {
                    this.mEdgeGlowTop.onRelease();
                }
            }
        }
        else if(z1) {
            EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, ((float)(-v13)) / ((float)this.getHeight()), ((float)v2) / ((float)this.getWidth()));
            if(motionEvent0 != null) {
                this.getScrollFeedbackProvider().onScrollLimit(motionEvent0.getDeviceId(), motionEvent0.getSource(), v1, true);
            }
            if(!this.mEdgeGlowBottom.isFinished()) {
                this.mEdgeGlowBottom.onRelease();
            }
        }
        if(!this.mEdgeGlowTop.isFinished() || !this.mEdgeGlowBottom.isFinished()) {
            this.postInvalidateOnAnimation();
        }
        else {
            v4 = v10;
        }
        if(v4 != 0 && v3 == 0) {
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.clear();
            }
        }
        if(v3 == 1) {
            this.stopNestedScroll(1);
            this.mEdgeGlowTop.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
        return v12;
    }

    @Override  // android.view.View
    public void scrollTo(int v, int v1) {
        if(this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
            int v2 = NestedScrollView.clamp(v, this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), view0.getWidth() + frameLayout$LayoutParams0.leftMargin + frameLayout$LayoutParams0.rightMargin);
            int v3 = NestedScrollView.clamp(v1, this.getHeight() - this.getPaddingTop() - this.getPaddingBottom(), view0.getHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin);
            if(v2 != this.getScrollX() || v3 != this.getScrollY()) {
                super.scrollTo(v2, v3);
            }
        }
    }

    private void scrollToChild(View view0) {
        view0.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(view0, this.mTempRect);
        int v = this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if(v != 0) {
            this.scrollBy(0, v);
        }
    }

    private boolean scrollToChildRect(Rect rect0, boolean z) {
        int v = this.computeScrollDeltaToGetChildRectOnScreen(rect0);
        if(v != 0) {
            if(z) {
                this.scrollBy(0, v);
                return true;
            }
            this.smoothScrollBy(0, v);
        }
        return v != 0;
    }

    public void setFillViewport(boolean z) {
        if(z != this.mFillViewport) {
            this.mFillViewport = z;
            this.requestLayout();
        }
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener nestedScrollView$OnScrollChangeListener0) {
        this.mOnScrollChangeListener = nestedScrollView$OnScrollChangeListener0;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.mSmoothScrollingEnabled = z;
    }

    private boolean shouldAbsorb(EdgeEffect edgeEffect0, int v) {
        return v <= 0 ? this.getSplineFlingDistance(-v) < EdgeEffectCompat.getDistance(edgeEffect0) * ((float)this.getHeight()) : true;
    }

    @Override  // android.widget.FrameLayout
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    private void smoothScrollBy(int v, int v1, int v2, boolean z) {
        if(this.getChildCount() == 0) {
            return;
        }
        if(AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 0xFAL) {
            View view0 = this.getChildAt(0);
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
            int v3 = view0.getHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin;
            int v4 = this.getHeight();
            int v5 = this.getPaddingTop();
            int v6 = this.getPaddingBottom();
            int v7 = this.getScrollY();
            this.mScroller.startScroll(this.getScrollX(), v7, 0, Math.max(0, Math.min(v1 + v7, Math.max(0, v3 - (v4 - v5 - v6)))) - v7, v2);
            this.runAnimatedScroll(z);
        }
        else {
            if(!this.mScroller.isFinished()) {
                this.abortAnimatedScroll();
            }
            this.scrollBy(v, v1);
        }
        this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }

    public final void smoothScrollBy(int v, int v1) {
        this.smoothScrollBy(v, v1, 0xFA, false);
    }

    public final void smoothScrollBy(int v, int v1, int v2) {
        this.smoothScrollBy(v, v1, v2, false);
    }

    public final void smoothScrollTo(int v, int v1) {
        this.smoothScrollTo(v, v1, 0xFA, false);
    }

    public final void smoothScrollTo(int v, int v1, int v2) {
        this.smoothScrollTo(v, v1, v2, false);
    }

    void smoothScrollTo(int v, int v1, int v2, boolean z) {
        this.smoothScrollBy(v - this.getScrollX(), v1 - this.getScrollY(), v2, z);
    }

    void smoothScrollTo(int v, int v1, boolean z) {
        this.smoothScrollTo(v, v1, 0xFA, z);
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int v) {
        return this.startNestedScroll(v, 0);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int v, int v1) {
        return this.mChildHelper.startNestedScroll(v, v1);
    }

    private boolean stopGlowAnimations(MotionEvent motionEvent0) {
        boolean z;
        if(EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0f) {
            z = false;
        }
        else {
            EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, 0.0f, motionEvent0.getX() / ((float)this.getWidth()));
            z = true;
        }
        if(EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0f) {
            EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, 0.0f, 1.0f - motionEvent0.getX() / ((float)this.getWidth()));
            return true;
        }
        return z;
    }

    @Override  // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.stopNestedScroll(0);
    }

    @Override  // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int v) {
        this.mChildHelper.stopNestedScroll(v);
    }
}

