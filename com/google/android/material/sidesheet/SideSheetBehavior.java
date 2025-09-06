package com.google.android.material.sidesheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper.Callback;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel.Builder;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.LinkedHashSet;
import java.util.Set;

public class SideSheetBehavior extends Behavior implements Sheet {
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        final int state;

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

        public SavedState(Parcel parcel0) {
            this(parcel0, null);
        }

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.state = parcel0.readInt();
        }

        public SavedState(Parcelable parcelable0, SideSheetBehavior sideSheetBehavior0) {
            super(parcelable0);
            this.state = sideSheetBehavior0.state;
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.state);
        }
    }

    class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;

        StateSettlingTracker() {
            this.continueSettlingRunnable = () -> {
                this.isContinueSettlingRunnablePosted = false;
                if(SideSheetBehavior.this.viewDragHelper != null && SideSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                    this.continueSettlingToState(this.targetState);
                    return;
                }
                if(SideSheetBehavior.this.state == 2) {
                    SideSheetBehavior.this.setStateInternal(this.targetState);
                }
            };
        }

        void continueSettlingToState(int v) {
            if(SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() != null) {
                this.targetState = v;
                if(!this.isContinueSettlingRunnablePosted) {
                    ViewCompat.postOnAnimation(((View)SideSheetBehavior.this.viewRef.get()), this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }

        // 检测为 Lambda 实现
        void lambda$new$0$com-google-android-material-sidesheet-SideSheetBehavior$StateSettlingTracker() [...]
    }

    private static final int DEFAULT_ACCESSIBILITY_PANE_TITLE = 0;
    private static final int DEF_STYLE_RES = 0;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int NO_MAX_SIZE = -1;
    static final int SIGNIFICANT_VEL_THRESHOLD = 500;
    private ColorStateList backgroundTint;
    private final Set callbacks;
    private int childWidth;
    private int coplanarSiblingViewId;
    private WeakReference coplanarSiblingViewRef;
    private final Callback dragCallback;
    private boolean draggable;
    private float elevation;
    private float hideFriction;
    private boolean ignoreEvents;
    private int initialX;
    private int innerMargin;
    private int lastStableState;
    private MaterialShapeDrawable materialShapeDrawable;
    private float maximumVelocity;
    private int parentInnerEdge;
    private int parentWidth;
    private ShapeAppearanceModel shapeAppearanceModel;
    private SheetDelegate sheetDelegate;
    private MaterialSideContainerBackHelper sideContainerBackHelper;
    private int state;
    private final StateSettlingTracker stateSettlingTracker;
    private VelocityTracker velocityTracker;
    private ViewDragHelper viewDragHelper;
    private WeakReference viewRef;

    static {
        SideSheetBehavior.DEFAULT_ACCESSIBILITY_PANE_TITLE = string.side_sheet_accessibility_pane_title;
        SideSheetBehavior.DEF_STYLE_RES = style.Widget_Material3_SideSheet;
    }

    public SideSheetBehavior() {
        this.stateSettlingTracker = new StateSettlingTracker(this);
        this.draggable = true;
        this.state = 5;
        this.lastStableState = 5;
        this.hideFriction = 0.1f;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new Callback() {
            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionHorizontal(View view0, int v, int v1) {
                return MathUtils.clamp(v, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionVertical(View view0, int v, int v1) {
                return view0.getTop();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int getViewHorizontalDragRange(View view0) {
                return SideSheetBehavior.this.childWidth + SideSheetBehavior.this.getInnerMargin();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewDragStateChanged(int v) {
                if(v == 1 && SideSheetBehavior.this.draggable) {
                    SideSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
                View view1 = SideSheetBehavior.this.getCoplanarSiblingView();
                if(view1 != null) {
                    ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view1.getLayoutParams();
                    if(viewGroup$MarginLayoutParams0 != null) {
                        int v4 = view0.getLeft();
                        int v5 = view0.getRight();
                        SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(viewGroup$MarginLayoutParams0, v4, v5);
                        view1.setLayoutParams(viewGroup$MarginLayoutParams0);
                    }
                }
                SideSheetBehavior.this.dispatchOnSlide(view0, v);
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewReleased(View view0, float f, float f1) {
                int v = SideSheetBehavior.this.calculateTargetStateOnViewReleased(view0, f, f1);
                SideSheetBehavior.this.startSettling(view0, v, true);
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public boolean tryCaptureView(View view0, int v) {
                return SideSheetBehavior.this.state == 1 ? false : SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() == view0;
            }
        };
    }

    public SideSheetBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.stateSettlingTracker = new StateSettlingTracker(this);
        this.draggable = true;
        this.state = 5;
        this.lastStableState = 5;
        this.hideFriction = 0.1f;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new Callback() {
            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionHorizontal(View view0, int v, int v1) {
                return MathUtils.clamp(v, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionVertical(View view0, int v, int v1) {
                return view0.getTop();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int getViewHorizontalDragRange(View view0) {
                return SideSheetBehavior.this.childWidth + SideSheetBehavior.this.getInnerMargin();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewDragStateChanged(int v) {
                if(v == 1 && SideSheetBehavior.this.draggable) {
                    SideSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
                View view1 = SideSheetBehavior.this.getCoplanarSiblingView();
                if(view1 != null) {
                    ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view1.getLayoutParams();
                    if(viewGroup$MarginLayoutParams0 != null) {
                        int v4 = view0.getLeft();
                        int v5 = view0.getRight();
                        SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(viewGroup$MarginLayoutParams0, v4, v5);
                        view1.setLayoutParams(viewGroup$MarginLayoutParams0);
                    }
                }
                SideSheetBehavior.this.dispatchOnSlide(view0, v);
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewReleased(View view0, float f, float f1) {
                int v = SideSheetBehavior.this.calculateTargetStateOnViewReleased(view0, f, f1);
                SideSheetBehavior.this.startSettling(view0, v, true);
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public boolean tryCaptureView(View view0, int v) {
                return SideSheetBehavior.this.state == 1 ? false : SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() == view0;
            }
        };
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.SideSheetBehavior_Layout);
        if(typedArray0.hasValue(styleable.SideSheetBehavior_Layout_backgroundTint)) {
            this.backgroundTint = MaterialResources.getColorStateList(context0, typedArray0, styleable.SideSheetBehavior_Layout_backgroundTint);
        }
        if(typedArray0.hasValue(styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.shapeAppearanceModel = ShapeAppearanceModel.builder(context0, attributeSet0, 0, SideSheetBehavior.DEF_STYLE_RES).build();
        }
        if(typedArray0.hasValue(styleable.SideSheetBehavior_Layout_coplanarSiblingViewId)) {
            this.setCoplanarSiblingViewId(typedArray0.getResourceId(styleable.SideSheetBehavior_Layout_coplanarSiblingViewId, -1));
        }
        this.createMaterialShapeDrawableIfNeeded(context0);
        this.elevation = typedArray0.getDimension(styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        this.setDraggable(typedArray0.getBoolean(styleable.SideSheetBehavior_Layout_behavior_draggable, true));
        typedArray0.recycle();
        this.maximumVelocity = (float)ViewConfiguration.get(context0).getScaledMaximumFlingVelocity();
    }

    @Override  // com.google.android.material.sidesheet.Sheet
    public void addCallback(SheetCallback sheetCallback0) {
        this.addCallback(((SideSheetCallback)sheetCallback0));
    }

    public void addCallback(SideSheetCallback sideSheetCallback0) {
        this.callbacks.add(sideSheetCallback0);
    }

    private int calculateCurrentOffset(int v, View view0) {
        switch(this.state) {
            case 1: 
            case 2: {
                return v - this.sheetDelegate.getOuterEdge(view0);
            }
            case 3: {
                return 0;
            }
            case 5: {
                return this.sheetDelegate.getHiddenOffset();
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + this.state);
            }
        }
    }

    private float calculateDragDistance(float f, float f1) {
        return Math.abs(f - f1);
    }

    private int calculateTargetStateOnViewReleased(View view0, float f, float f1) {
        if(this.isExpandingOutwards(f)) {
            return 3;
        }
        if(this.shouldHide(view0, f)) {
            return this.sheetDelegate.isSwipeSignificant(f, f1) || this.sheetDelegate.isReleasedCloseToInnerEdge(view0) ? 5 : 3;
        }
        if(f != 0.0f && SheetUtils.isSwipeMostlyHorizontal(f, f1)) {
            return 5;
        }
        int v = view0.getLeft();
        return Math.abs(v - this.getExpandedOffset()) >= Math.abs(v - this.sheetDelegate.getHiddenOffset()) ? 5 : 3;
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper0 = this.sideContainerBackHelper;
        if(materialSideContainerBackHelper0 == null) {
            return;
        }
        materialSideContainerBackHelper0.cancelBackProgress();
    }

    private void clearCoplanarSiblingView() {
        WeakReference weakReference0 = this.coplanarSiblingViewRef;
        if(weakReference0 != null) {
            weakReference0.clear();
        }
        this.coplanarSiblingViewRef = null;
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int v) {
        return (View view0, CommandArguments accessibilityViewCommand$CommandArguments0) -> {
            this.setState(v);
            return true;
        };
    }

    private void createMaterialShapeDrawableIfNeeded(Context context0) {
        if(this.shapeAppearanceModel == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(this.shapeAppearanceModel);
        this.materialShapeDrawable = materialShapeDrawable0;
        materialShapeDrawable0.initializeElevationOverlay(context0);
        ColorStateList colorStateList0 = this.backgroundTint;
        if(colorStateList0 != null) {
            this.materialShapeDrawable.setFillColor(colorStateList0);
            return;
        }
        TypedValue typedValue0 = new TypedValue();
        context0.getTheme().resolveAttribute(0x1010031, typedValue0, true);
        this.materialShapeDrawable.setTint(typedValue0.data);
    }

    private void dispatchOnSlide(View view0, int v) {
        if(!this.callbacks.isEmpty()) {
            float f = this.sheetDelegate.calculateSlideOffset(v);
            for(Object object0: this.callbacks) {
                ((SheetCallback)object0).onSlide(view0, f);
            }
        }
    }

    private void ensureAccessibilityPaneTitleIsSet(View view0) {
        if(ViewCompat.getAccessibilityPaneTitle(view0) == null) {
            ViewCompat.setAccessibilityPaneTitle(view0, view0.getResources().getString(SideSheetBehavior.DEFAULT_ACCESSIBILITY_PANE_TITLE));
        }
    }

    public void expand() {
        this.setState(3);
    }

    public static SideSheetBehavior from(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        Behavior coordinatorLayout$Behavior0 = ((LayoutParams)viewGroup$LayoutParams0).getBehavior();
        if(!(coordinatorLayout$Behavior0 instanceof SideSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with SideSheetBehavior");
        }
        return (SideSheetBehavior)coordinatorLayout$Behavior0;
    }

    MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    private int getChildMeasureSpec(int v, int v1, int v2, int v3) {
        int v4 = ViewGroup.getChildMeasureSpec(v, v1, v3);
        if(v2 == -1) {
            return v4;
        }
        int v5 = View.MeasureSpec.getMode(v4);
        int v6 = View.MeasureSpec.getSize(v4);
        if(v5 != 0x40000000) {
            if(v6 != 0) {
                v2 = Math.min(v6, v2);
            }
            return View.MeasureSpec.makeMeasureSpec(v2, 0x80000000);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(v6, v2), 0x40000000);
    }

    int getChildWidth() {
        return this.childWidth;
    }

    private ValueAnimator.AnimatorUpdateListener getCoplanarFinishAnimatorUpdateListener() {
        View view0 = this.getCoplanarSiblingView();
        if(view0 == null) {
            return null;
        }
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        return viewGroup$MarginLayoutParams0 == null ? null : (ValueAnimator valueAnimator0) -> {
            this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(viewGroup$MarginLayoutParams0, AnimationUtils.lerp(this.sheetDelegate.getCoplanarSiblingAdjacentMargin(viewGroup$MarginLayoutParams0), 0, valueAnimator0.getAnimatedFraction()));
            view0.requestLayout();
        };
    }

    public View getCoplanarSiblingView() {
        return this.coplanarSiblingViewRef == null ? null : ((View)this.coplanarSiblingViewRef.get());
    }

    public int getExpandedOffset() {
        return this.sheetDelegate.getExpandedOffset();
    }

    private int getGravityFromSheetEdge() {
        return this.sheetDelegate != null && this.sheetDelegate.getSheetEdge() != 0 ? 3 : 5;
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    float getHideThreshold() [...] // Inlined contents

    int getInnerMargin() {
        return this.innerMargin;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    int getOuterEdgeOffsetForState(int v) {
        switch(v) {
            case 3: {
                return this.getExpandedOffset();
            }
            case 5: {
                return this.sheetDelegate.getHiddenOffset();
            }
            default: {
                throw new IllegalArgumentException("Invalid state to get outer edge offset: " + v);
            }
        }
    }

    int getParentInnerEdge() {
        return this.parentInnerEdge;
    }

    int getParentWidth() {
        return this.parentWidth;
    }

    int getSignificantVelocityThreshold() [...] // Inlined contents

    @Override  // com.google.android.material.sidesheet.Sheet
    public int getState() {
        return this.state;
    }

    ViewDragHelper getViewDragHelper() {
        return this.viewDragHelper;
    }

    private LayoutParams getViewLayoutParams() {
        WeakReference weakReference0 = this.viewRef;
        if(weakReference0 != null) {
            View view0 = (View)weakReference0.get();
            return view0 == null || !(view0.getLayoutParams() instanceof LayoutParams) ? null : ((LayoutParams)view0.getLayoutParams());
        }
        return null;
    }

    float getXVelocity() {
        VelocityTracker velocityTracker0 = this.velocityTracker;
        if(velocityTracker0 == null) {
            return 0.0f;
        }
        velocityTracker0.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getXVelocity();
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper0 = this.sideContainerBackHelper;
        if(materialSideContainerBackHelper0 == null) {
            return;
        }
        BackEventCompat backEventCompat0 = materialSideContainerBackHelper0.onHandleBackInvoked();
        if(backEventCompat0 != null && Build.VERSION.SDK_INT >= 34) {
            this.sideContainerBackHelper.finishBackProgress(backEventCompat0, this.getGravityFromSheetEdge(), new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    SideSheetBehavior.this.setStateInternal(5);
                    if(SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() != null) {
                        ((View)SideSheetBehavior.this.viewRef.get()).requestLayout();
                    }
                }
            }, this.getCoplanarFinishAnimatorUpdateListener());
            return;
        }
        this.setState(5);
    }

    private boolean hasLeftMargin() {
        LayoutParams coordinatorLayout$LayoutParams0 = this.getViewLayoutParams();
        return coordinatorLayout$LayoutParams0 != null && coordinatorLayout$LayoutParams0.leftMargin > 0;
    }

    private boolean hasRightMargin() {
        LayoutParams coordinatorLayout$LayoutParams0 = this.getViewLayoutParams();
        return coordinatorLayout$LayoutParams0 != null && coordinatorLayout$LayoutParams0.rightMargin > 0;
    }

    public void hide() {
        this.setState(5);
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    private boolean isDraggedFarEnough(MotionEvent motionEvent0) {
        return this.shouldHandleDraggingWithHelper() ? this.calculateDragDistance(((float)this.initialX), motionEvent0.getX()) > ((float)this.viewDragHelper.getTouchSlop()) : false;
    }

    private boolean isExpandingOutwards(float f) {
        return this.sheetDelegate.isExpandingOutwards(f);
    }

    private boolean isLayingOut(View view0) {
        ViewParent viewParent0 = view0.getParent();
        return viewParent0 != null && viewParent0.isLayoutRequested() && ViewCompat.isAttachedToWindow(view0);
    }

    private boolean isSettling(View view0, int v, boolean z) {
        int v1 = this.getOuterEdgeOffsetForState(v);
        ViewDragHelper viewDragHelper0 = this.getViewDragHelper();
        if(viewDragHelper0 != null) {
            return z ? viewDragHelper0.settleCapturedViewAt(v1, view0.getTop()) : viewDragHelper0.smoothSlideViewTo(view0, v1, view0.getTop());
        }
        return false;
    }

    // 检测为 Lambda 实现
    boolean lambda$createAccessibilityViewCommandForState$2$com-google-android-material-sidesheet-SideSheetBehavior(int v, View view0, CommandArguments accessibilityViewCommand$CommandArguments0) [...]

    // 检测为 Lambda 实现
    void lambda$getCoplanarFinishAnimatorUpdateListener$1$com-google-android-material-sidesheet-SideSheetBehavior(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v, View view0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$setState$0$com-google-android-material-sidesheet-SideSheetBehavior(int v) [...]

    private void maybeAssignCoplanarSiblingViewBasedId(CoordinatorLayout coordinatorLayout0) {
        if(this.coplanarSiblingViewRef == null) {
            int v = this.coplanarSiblingViewId;
            if(v != -1) {
                View view0 = coordinatorLayout0.findViewById(v);
                if(view0 != null) {
                    this.coplanarSiblingViewRef = new WeakReference(view0);
                }
            }
        }
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onAttachedToLayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
        super.onAttachedToLayoutParams(coordinatorLayout$LayoutParams0);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        if(!this.shouldInterceptTouchEvent(view0)) {
            this.ignoreEvents = true;
            return false;
        }
        int v = motionEvent0.getActionMasked();
        if(v == 0) {
            this.resetVelocity();
        }
        if(this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent0);
        switch(v) {
            case 0: {
                this.initialX = (int)motionEvent0.getX();
                return !this.ignoreEvents && (this.viewDragHelper != null && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent0));
            label_13:
                if(v == 3) {
                    goto label_14;
                }
                break;
            }
            case 1: {
            label_14:
                if(this.ignoreEvents) {
                    this.ignoreEvents = false;
                    return false;
                }
                break;
            }
            default: {
                goto label_13;
            }
        }
        return !this.ignoreEvents && (this.viewDragHelper != null && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent0));
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        if(ViewCompat.getFitsSystemWindows(coordinatorLayout0) && !ViewCompat.getFitsSystemWindows(view0)) {
            view0.setFitsSystemWindows(true);
        }
        if(this.viewRef == null) {
            this.viewRef = new WeakReference(view0);
            this.sideContainerBackHelper = new MaterialSideContainerBackHelper(view0);
            MaterialShapeDrawable materialShapeDrawable0 = this.materialShapeDrawable;
            if(materialShapeDrawable0 == null) {
                ColorStateList colorStateList0 = this.backgroundTint;
                if(colorStateList0 != null) {
                    ViewCompat.setBackgroundTintList(view0, colorStateList0);
                }
            }
            else {
                ViewCompat.setBackground(view0, materialShapeDrawable0);
                this.materialShapeDrawable.setElevation((this.elevation == -1.0f ? ViewCompat.getElevation(view0) : this.elevation));
            }
            this.updateSheetVisibility(view0);
            this.updateAccessibilityActions();
            if(ViewCompat.getImportantForAccessibility(view0) == 0) {
                ViewCompat.setImportantForAccessibility(view0, 1);
            }
            this.ensureAccessibilityPaneTitleIsSet(view0);
        }
        this.setSheetEdge(view0, v);
        if(this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout0, this.dragCallback);
        }
        int v1 = this.sheetDelegate.getOuterEdge(view0);
        coordinatorLayout0.onLayoutChild(view0, v);
        this.parentWidth = coordinatorLayout0.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(coordinatorLayout0);
        this.childWidth = view0.getWidth();
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        this.innerMargin = viewGroup$MarginLayoutParams0 == null ? 0 : this.sheetDelegate.calculateInnerMargin(viewGroup$MarginLayoutParams0);
        ViewCompat.offsetLeftAndRight(view0, this.calculateCurrentOffset(v1, view0));
        this.maybeAssignCoplanarSiblingViewBasedId(coordinatorLayout0);
        for(Object object0: this.callbacks) {
            if(((SheetCallback)object0) instanceof SideSheetCallback) {
            }
        }
        return true;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2, int v3) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        view0.measure(this.getChildMeasureSpec(v, coordinatorLayout0.getPaddingLeft() + coordinatorLayout0.getPaddingRight() + viewGroup$MarginLayoutParams0.leftMargin + viewGroup$MarginLayoutParams0.rightMargin + v1, -1, viewGroup$MarginLayoutParams0.width), this.getChildMeasureSpec(v2, coordinatorLayout0.getPaddingTop() + coordinatorLayout0.getPaddingBottom() + viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin + v3, -1, viewGroup$MarginLayoutParams0.height));
        return true;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout0, View view0, Parcelable parcelable0) {
        int v;
        if(((SavedState)parcelable0).getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout0, view0, ((SavedState)parcelable0).getSuperState());
        }
        switch(((SavedState)parcelable0).state) {
            case 1: 
            case 2: {
                v = 5;
                break;
            }
            default: {
                v = ((SavedState)parcelable0).state;
            }
        }
        this.state = v;
        this.lastStableState = v;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout0, View view0) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout0, view0), this);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        if(!view0.isShown()) {
            return false;
        }
        int v = motionEvent0.getActionMasked();
        if(this.state == 1 && v == 0) {
            return true;
        }
        if(this.shouldHandleDraggingWithHelper()) {
            this.viewDragHelper.processTouchEvent(motionEvent0);
        }
        if(v == 0) {
            this.resetVelocity();
        }
        if(this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent0);
        if(this.shouldHandleDraggingWithHelper() && v == 2 && !this.ignoreEvents && this.isDraggedFarEnough(motionEvent0)) {
            this.viewDragHelper.captureChildView(view0, motionEvent0.getPointerId(motionEvent0.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    @Override  // com.google.android.material.sidesheet.Sheet
    public void removeCallback(SheetCallback sheetCallback0) {
        this.removeCallback(((SideSheetCallback)sheetCallback0));
    }

    public void removeCallback(SideSheetCallback sideSheetCallback0) {
        this.callbacks.remove(sideSheetCallback0);
    }

    private void replaceAccessibilityActionForState(View view0, AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0, int v) {
        ViewCompat.replaceAccessibilityAction(view0, accessibilityNodeInfoCompat$AccessibilityActionCompat0, null, this.createAccessibilityViewCommandForState(v));
    }

    private void resetVelocity() {
        VelocityTracker velocityTracker0 = this.velocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
            this.velocityTracker = null;
        }
    }

    private void runAfterLayout(View view0, Runnable runnable0) {
        if(this.isLayingOut(view0)) {
            view0.post(runnable0);
            return;
        }
        runnable0.run();
    }

    public void setCoplanarSiblingView(View view0) {
        this.coplanarSiblingViewId = -1;
        if(view0 == null) {
            this.clearCoplanarSiblingView();
            return;
        }
        this.coplanarSiblingViewRef = new WeakReference(view0);
        WeakReference weakReference0 = this.viewRef;
        if(weakReference0 != null) {
            View view1 = (View)weakReference0.get();
            if(ViewCompat.isLaidOut(view1)) {
                view1.requestLayout();
            }
        }
    }

    public void setCoplanarSiblingViewId(int v) {
        this.coplanarSiblingViewId = v;
        this.clearCoplanarSiblingView();
        WeakReference weakReference0 = this.viewRef;
        if(weakReference0 != null) {
            View view0 = (View)weakReference0.get();
            if(v != -1 && ViewCompat.isLaidOut(view0)) {
                view0.requestLayout();
            }
        }
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
    }

    public void setHideFriction(float f) {
        this.hideFriction = f;
    }

    private void setSheetEdge(int v) {
        if(this.sheetDelegate == null || this.sheetDelegate.getSheetEdge() != v) {
            switch(v) {
                case 0: {
                    this.sheetDelegate = new RightSheetDelegate(this);
                    if(this.shapeAppearanceModel != null && !this.hasRightMargin()) {
                        Builder shapeAppearanceModel$Builder0 = this.shapeAppearanceModel.toBuilder();
                        shapeAppearanceModel$Builder0.setTopRightCornerSize(0.0f).setBottomRightCornerSize(0.0f);
                        this.updateMaterialShapeDrawable(shapeAppearanceModel$Builder0.build());
                        return;
                    }
                    break;
                }
                case 1: {
                    this.sheetDelegate = new LeftSheetDelegate(this);
                    if(this.shapeAppearanceModel != null && !this.hasLeftMargin()) {
                        Builder shapeAppearanceModel$Builder1 = this.shapeAppearanceModel.toBuilder();
                        shapeAppearanceModel$Builder1.setTopLeftCornerSize(0.0f).setBottomLeftCornerSize(0.0f);
                        this.updateMaterialShapeDrawable(shapeAppearanceModel$Builder1.build());
                    }
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid sheet edge position value: " + v + ". Must be 0 or 1.");
                }
            }
        }
    }

    private void setSheetEdge(View view0, int v) {
        this.setSheetEdge((GravityCompat.getAbsoluteGravity(((LayoutParams)view0.getLayoutParams()).gravity, v) == 3 ? 1 : 0));
    }

    @Override  // com.google.android.material.sidesheet.Sheet
    public void setState(int v) {
        if(v == 1 || v == 2) {
            throw new IllegalArgumentException("STATE_" + (v == 1 ? "DRAGGING" : "SETTLING") + " should not be set externally.");
        }
        if(this.viewRef != null && this.viewRef.get() != null) {
            this.runAfterLayout(((View)this.viewRef.get()), () -> {
                View view0 = (View)this.viewRef.get();
                if(view0 != null) {
                    this.startSettling(view0, v, false);
                }
            });
            return;
        }
        this.setStateInternal(v);
    }

    void setStateInternal(int v) {
        if(this.state != v) {
            this.state = v;
            if(v == 3 || v == 5) {
                this.lastStableState = v;
            }
            WeakReference weakReference0 = this.viewRef;
            if(weakReference0 != null) {
                View view0 = (View)weakReference0.get();
                if(view0 != null) {
                    this.updateSheetVisibility(view0);
                    for(Object object0: this.callbacks) {
                        ((SheetCallback)object0).onStateChanged(view0, v);
                    }
                    this.updateAccessibilityActions();
                }
            }
        }
    }

    private boolean shouldHandleDraggingWithHelper() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    boolean shouldHide(View view0, float f) {
        return this.sheetDelegate.shouldHide(view0, f);
    }

    // 去混淆评级： 低(20)
    private boolean shouldInterceptTouchEvent(View view0) {
        return (view0.isShown() || ViewCompat.getAccessibilityPaneTitle(view0) != null) && this.draggable;
    }

    public boolean shouldSkipSmoothAnimation() [...] // Inlined contents

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat backEventCompat0) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper0 = this.sideContainerBackHelper;
        if(materialSideContainerBackHelper0 == null) {
            return;
        }
        materialSideContainerBackHelper0.startBackProgress(backEventCompat0);
    }

    private void startSettling(View view0, int v, boolean z) {
        if(this.isSettling(view0, v, z)) {
            this.setStateInternal(2);
            this.stateSettlingTracker.continueSettlingToState(v);
            return;
        }
        this.setStateInternal(v);
    }

    private void updateAccessibilityActions() {
        WeakReference weakReference0 = this.viewRef;
        if(weakReference0 != null) {
            View view0 = (View)weakReference0.get();
            if(view0 != null) {
                ViewCompat.removeAccessibilityAction(view0, 0x40000);
                ViewCompat.removeAccessibilityAction(view0, 0x100000);
                if(this.state != 5) {
                    this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_DISMISS, 5);
                }
                if(this.state != 3) {
                    this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_EXPAND, 3);
                }
            }
        }
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat backEventCompat0) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper0 = this.sideContainerBackHelper;
        if(materialSideContainerBackHelper0 == null) {
            return;
        }
        materialSideContainerBackHelper0.updateBackProgress(backEventCompat0, this.getGravityFromSheetEdge());
        this.updateCoplanarSiblingBackProgress();
    }

    private void updateCoplanarSiblingBackProgress() {
        if(this.viewRef != null && this.viewRef.get() != null) {
            View view0 = (View)this.viewRef.get();
            View view1 = this.getCoplanarSiblingView();
            if(view1 != null) {
                ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view1.getLayoutParams();
                if(viewGroup$MarginLayoutParams0 != null) {
                    float f = (float)this.childWidth;
                    float f1 = view0.getScaleX();
                    this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(viewGroup$MarginLayoutParams0, ((int)(f * f1 + ((float)this.innerMargin))));
                    view1.requestLayout();
                }
            }
        }
    }

    private void updateMaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel0) {
        MaterialShapeDrawable materialShapeDrawable0 = this.materialShapeDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
        }
    }

    private void updateSheetVisibility(View view0) {
        int v = this.state == 5 ? 4 : 0;
        if(view0.getVisibility() != v) {
            view0.setVisibility(v);
        }
    }
}

