package com.google.android.material.bottomsheet;

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
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper.Callback;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils.RelativePadding;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBottomContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior extends Behavior implements MaterialBackHandler {
    public static abstract class BottomSheetCallback {
        void onLayout(View view0) {
        }

        public abstract void onSlide(View arg1, float arg2);

        public abstract void onStateChanged(View arg1, int arg2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SaveFlags {
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean fitToContents;
        boolean hideable;
        int peekHeight;
        boolean skipCollapsed;
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
            this.peekHeight = parcel0.readInt();
            boolean z = false;
            this.fitToContents = parcel0.readInt() == 1;
            this.hideable = parcel0.readInt() == 1;
            if(parcel0.readInt() == 1) {
                z = true;
            }
            this.skipCollapsed = z;
        }

        @Deprecated
        public SavedState(Parcelable parcelable0, int v) {
            super(parcelable0);
            this.state = v;
        }

        public SavedState(Parcelable parcelable0, BottomSheetBehavior bottomSheetBehavior0) {
            super(parcelable0);
            this.state = bottomSheetBehavior0.state;
            this.peekHeight = bottomSheetBehavior0.peekHeight;
            this.fitToContents = bottomSheetBehavior0.fitToContents;
            this.hideable = bottomSheetBehavior0.hideable;
            this.skipCollapsed = bottomSheetBehavior0.skipCollapsed;
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.state);
            parcel0.writeInt(this.peekHeight);
            parcel0.writeInt(((int)this.fitToContents));
            parcel0.writeInt(((int)this.hideable));
            parcel0.writeInt(((int)this.skipCollapsed));
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StableState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;

        private StateSettlingTracker() {
            this.continueSettlingRunnable = new Runnable() {
                @Override
                public void run() {
                    StateSettlingTracker.this.isContinueSettlingRunnablePosted = false;
                    if(BottomSheetBehavior.this.viewDragHelper != null && BottomSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                        StateSettlingTracker.this.continueSettlingToState(StateSettlingTracker.this.targetState);
                        return;
                    }
                    if(BottomSheetBehavior.this.state == 2) {
                        BottomSheetBehavior.this.setStateInternal(StateSettlingTracker.this.targetState);
                    }
                }
            };
        }

        StateSettlingTracker(com.google.android.material.bottomsheet.BottomSheetBehavior.1 bottomSheetBehavior$10) {
        }

        void continueSettlingToState(int v) {
            if(BottomSheetBehavior.this.viewRef != null && BottomSheetBehavior.this.viewRef.get() != null) {
                this.targetState = v;
                if(!this.isContinueSettlingRunnablePosted) {
                    ViewCompat.postOnAnimation(((View)BottomSheetBehavior.this.viewRef.get()), this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }
    }

    private static final int CORNER_ANIMATION_DURATION = 500;
    static final int DEFAULT_SIGNIFICANT_VEL_THRESHOLD = 500;
    private static final int DEF_STYLE_RES = 0;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int INVALID_POSITION = -1;
    private static final int NO_MAX_SIZE = -1;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "BottomSheetBehavior";
    static final int VIEW_INDEX_ACCESSIBILITY_DELEGATE_VIEW = 1;
    private static final int VIEW_INDEX_BOTTOM_SHEET;
    WeakReference accessibilityDelegateViewRef;
    int activePointerId;
    private ColorStateList backgroundTint;
    MaterialBottomContainerBackHelper bottomContainerBackHelper;
    private final ArrayList callbacks;
    private int childHeight;
    int collapsedOffset;
    private final Callback dragCallback;
    private boolean draggable;
    float elevation;
    final SparseIntArray expandHalfwayActionIds;
    private boolean expandedCornersRemoved;
    int expandedOffset;
    private boolean fitToContents;
    int fitToContentsOffset;
    private int gestureInsetBottom;
    private boolean gestureInsetBottomIgnored;
    int halfExpandedOffset;
    float halfExpandedRatio;
    private float hideFriction;
    boolean hideable;
    private boolean ignoreEvents;
    private Map importantForAccessibilityMap;
    private int initialY;
    private int insetBottom;
    private int insetTop;
    private ValueAnimator interpolatorAnimator;
    private int lastNestedScrollDy;
    int lastStableState;
    private boolean marginLeftSystemWindowInsets;
    private boolean marginRightSystemWindowInsets;
    private boolean marginTopSystemWindowInsets;
    private MaterialShapeDrawable materialShapeDrawable;
    private int maxHeight;
    private int maxWidth;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference nestedScrollingChildRef;
    private boolean paddingBottomSystemWindowInsets;
    private boolean paddingLeftSystemWindowInsets;
    private boolean paddingRightSystemWindowInsets;
    private boolean paddingTopSystemWindowInsets;
    int parentHeight;
    int parentWidth;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightGestureInsetBuffer;
    private int peekHeightMin;
    private int saveFlags;
    private ShapeAppearanceModel shapeAppearanceModelDefault;
    private boolean shouldRemoveExpandedCorners;
    private int significantVelocityThreshold;
    private boolean skipCollapsed;
    int state;
    private final StateSettlingTracker stateSettlingTracker;
    boolean touchingScrollingChild;
    private boolean updateImportantForAccessibilityOnSiblings;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference viewRef;

    static {
        BottomSheetBehavior.DEF_STYLE_RES = style.Widget_Design_BottomSheet_Modal;
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker(this, null);
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.state = 4;
        this.lastStableState = 4;
        this.hideFriction = 0.1f;
        this.callbacks = new ArrayList();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.dragCallback = new Callback() {
            private long viewCapturedMillis;

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionHorizontal(View view0, int v, int v1) {
                return view0.getLeft();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionVertical(View view0, int v, int v1) {
                return MathUtils.clamp(v, BottomSheetBehavior.this.getExpandedOffset(), this.getViewVerticalDragRange(view0));
            }

            // 去混淆评级： 低(20)
            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int getViewVerticalDragRange(View view0) {
                return BottomSheetBehavior.this.canBeHiddenByDragging() ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset;
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewDragStateChanged(int v) {
                if(v == 1 && BottomSheetBehavior.this.draggable) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
                BottomSheetBehavior.this.dispatchOnSlide(v1);
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewReleased(View view0, float f, float f1) {
                int v = 6;
                if(f1 >= 0.0f) {
                    if(!BottomSheetBehavior.this.hideable || !BottomSheetBehavior.this.shouldHide(view0, f1)) {
                        if(f1 == 0.0f || Math.abs(f) > Math.abs(f1)) {
                            int v2 = view0.getTop();
                            if(!BottomSheetBehavior.this.fitToContents) {
                                if(v2 >= BottomSheetBehavior.this.halfExpandedOffset) {
                                    if(Math.abs(v2 - BottomSheetBehavior.this.halfExpandedOffset) >= Math.abs(v2 - BottomSheetBehavior.this.collapsedOffset)) {
                                        v = 4;
                                    }
                                }
                                else if(v2 < Math.abs(v2 - BottomSheetBehavior.this.collapsedOffset)) {
                                    v = 3;
                                }
                            }
                            else if(Math.abs(v2 - BottomSheetBehavior.this.fitToContentsOffset) >= Math.abs(v2 - BottomSheetBehavior.this.collapsedOffset)) {
                                v = 4;
                            }
                            else {
                                v = 3;
                            }
                        }
                        else if(!BottomSheetBehavior.this.fitToContents) {
                            int v1 = view0.getTop();
                            if(Math.abs(v1 - BottomSheetBehavior.this.halfExpandedOffset) >= Math.abs(v1 - BottomSheetBehavior.this.collapsedOffset)) {
                                v = 4;
                            }
                        }
                        else {
                            v = 4;
                        }
                    }
                    else if(Math.abs(f) < Math.abs(f1) && f1 > ((float)BottomSheetBehavior.this.significantVelocityThreshold) || this.releasedLow(view0)) {
                        v = 5;
                    }
                    else if(BottomSheetBehavior.this.fitToContents || Math.abs(view0.getTop() - BottomSheetBehavior.this.getExpandedOffset()) < Math.abs(view0.getTop() - BottomSheetBehavior.this.halfExpandedOffset)) {
                        v = 3;
                    }
                }
                else if(BottomSheetBehavior.this.fitToContents) {
                    v = 3;
                }
                else if(view0.getTop() <= BottomSheetBehavior.this.halfExpandedOffset) {
                    v = 3;
                }
                BottomSheetBehavior.this.startSettling(view0, v, true);
            }

            private boolean releasedLow(View view0) {
                return view0.getTop() > (BottomSheetBehavior.this.parentHeight + BottomSheetBehavior.this.getExpandedOffset()) / 2;
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public boolean tryCaptureView(View view0, int v) {
                if(BottomSheetBehavior.this.state == 1) {
                    return false;
                }
                if(BottomSheetBehavior.this.touchingScrollingChild) {
                    return false;
                }
                if(BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == v) {
                    View view1 = BottomSheetBehavior.this.nestedScrollingChildRef == null ? null : ((View)BottomSheetBehavior.this.nestedScrollingChildRef.get());
                    if(view1 != null && view1.canScrollVertically(-1)) {
                        return false;
                    }
                }
                this.viewCapturedMillis = System.currentTimeMillis();
                return BottomSheetBehavior.this.viewRef != null && BottomSheetBehavior.this.viewRef.get() == view0;
            }
        };
    }

    public BottomSheetBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker(this, null);
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.state = 4;
        this.lastStableState = 4;
        this.hideFriction = 0.1f;
        this.callbacks = new ArrayList();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.dragCallback = new Callback() {
            private long viewCapturedMillis;

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionHorizontal(View view0, int v, int v1) {
                return view0.getLeft();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionVertical(View view0, int v, int v1) {
                return MathUtils.clamp(v, BottomSheetBehavior.this.getExpandedOffset(), this.getViewVerticalDragRange(view0));
            }

            // 去混淆评级： 低(20)
            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int getViewVerticalDragRange(View view0) {
                return BottomSheetBehavior.this.canBeHiddenByDragging() ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset;
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewDragStateChanged(int v) {
                if(v == 1 && BottomSheetBehavior.this.draggable) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
                BottomSheetBehavior.this.dispatchOnSlide(v1);
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewReleased(View view0, float f, float f1) {
                int v = 6;
                if(f1 >= 0.0f) {
                    if(!BottomSheetBehavior.this.hideable || !BottomSheetBehavior.this.shouldHide(view0, f1)) {
                        if(f1 == 0.0f || Math.abs(f) > Math.abs(f1)) {
                            int v2 = view0.getTop();
                            if(!BottomSheetBehavior.this.fitToContents) {
                                if(v2 >= BottomSheetBehavior.this.halfExpandedOffset) {
                                    if(Math.abs(v2 - BottomSheetBehavior.this.halfExpandedOffset) >= Math.abs(v2 - BottomSheetBehavior.this.collapsedOffset)) {
                                        v = 4;
                                    }
                                }
                                else if(v2 < Math.abs(v2 - BottomSheetBehavior.this.collapsedOffset)) {
                                    v = 3;
                                }
                            }
                            else if(Math.abs(v2 - BottomSheetBehavior.this.fitToContentsOffset) >= Math.abs(v2 - BottomSheetBehavior.this.collapsedOffset)) {
                                v = 4;
                            }
                            else {
                                v = 3;
                            }
                        }
                        else if(!BottomSheetBehavior.this.fitToContents) {
                            int v1 = view0.getTop();
                            if(Math.abs(v1 - BottomSheetBehavior.this.halfExpandedOffset) >= Math.abs(v1 - BottomSheetBehavior.this.collapsedOffset)) {
                                v = 4;
                            }
                        }
                        else {
                            v = 4;
                        }
                    }
                    else if(Math.abs(f) < Math.abs(f1) && f1 > ((float)BottomSheetBehavior.this.significantVelocityThreshold) || this.releasedLow(view0)) {
                        v = 5;
                    }
                    else if(BottomSheetBehavior.this.fitToContents || Math.abs(view0.getTop() - BottomSheetBehavior.this.getExpandedOffset()) < Math.abs(view0.getTop() - BottomSheetBehavior.this.halfExpandedOffset)) {
                        v = 3;
                    }
                }
                else if(BottomSheetBehavior.this.fitToContents) {
                    v = 3;
                }
                else if(view0.getTop() <= BottomSheetBehavior.this.halfExpandedOffset) {
                    v = 3;
                }
                BottomSheetBehavior.this.startSettling(view0, v, true);
            }

            private boolean releasedLow(View view0) {
                return view0.getTop() > (BottomSheetBehavior.this.parentHeight + BottomSheetBehavior.this.getExpandedOffset()) / 2;
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public boolean tryCaptureView(View view0, int v) {
                if(BottomSheetBehavior.this.state == 1) {
                    return false;
                }
                if(BottomSheetBehavior.this.touchingScrollingChild) {
                    return false;
                }
                if(BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == v) {
                    View view1 = BottomSheetBehavior.this.nestedScrollingChildRef == null ? null : ((View)BottomSheetBehavior.this.nestedScrollingChildRef.get());
                    if(view1 != null && view1.canScrollVertically(-1)) {
                        return false;
                    }
                }
                this.viewCapturedMillis = System.currentTimeMillis();
                return BottomSheetBehavior.this.viewRef != null && BottomSheetBehavior.this.viewRef.get() == view0;
            }
        };
        this.peekHeightGestureInsetBuffer = context0.getResources().getDimensionPixelSize(dimen.mtrl_min_touch_target_size);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.BottomSheetBehavior_Layout);
        if(typedArray0.hasValue(styleable.BottomSheetBehavior_Layout_backgroundTint)) {
            this.backgroundTint = MaterialResources.getColorStateList(context0, typedArray0, styleable.BottomSheetBehavior_Layout_backgroundTint);
        }
        if(typedArray0.hasValue(styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(context0, attributeSet0, attr.bottomSheetStyle, BottomSheetBehavior.DEF_STYLE_RES).build();
        }
        this.createMaterialShapeDrawableIfNeeded(context0);
        this.createShapeValueAnimator();
        this.elevation = typedArray0.getDimension(styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if(typedArray0.hasValue(styleable.BottomSheetBehavior_Layout_android_maxWidth)) {
            this.setMaxWidth(typedArray0.getDimensionPixelSize(styleable.BottomSheetBehavior_Layout_android_maxWidth, -1));
        }
        if(typedArray0.hasValue(styleable.BottomSheetBehavior_Layout_android_maxHeight)) {
            this.setMaxHeight(typedArray0.getDimensionPixelSize(styleable.BottomSheetBehavior_Layout_android_maxHeight, -1));
        }
        TypedValue typedValue0 = typedArray0.peekValue(styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if(typedValue0 == null || typedValue0.data != -1) {
            this.setPeekHeight(typedArray0.getDimensionPixelSize(styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        else {
            this.setPeekHeight(typedValue0.data);
        }
        this.setHideable(typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        this.setGestureInsetBottomIgnored(typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        this.setFitToContents(typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        this.setSkipCollapsed(typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        this.setDraggable(typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        this.setSaveFlags(typedArray0.getInt(styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        this.setHalfExpandedRatio(typedArray0.getFloat(styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        TypedValue typedValue1 = typedArray0.peekValue(styleable.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if(typedValue1 == null || typedValue1.type != 16) {
            this.setExpandedOffset(typedArray0.getDimensionPixelOffset(styleable.BottomSheetBehavior_Layout_behavior_expandedOffset, 0));
        }
        else {
            this.setExpandedOffset(typedValue1.data);
        }
        this.setSignificantVelocityThreshold(typedArray0.getInt(styleable.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, 500));
        this.paddingBottomSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.paddingTopSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.marginLeftSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.marginRightSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.marginTopSystemWindowInsets = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.shouldRemoveExpandedCorners = typedArray0.getBoolean(styleable.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        typedArray0.recycle();
        this.maximumVelocity = (float)ViewConfiguration.get(context0).getScaledMaximumFlingVelocity();
    }

    private int addAccessibilityActionForState(View view0, int v, int v1) {
        return ViewCompat.addAccessibilityAction(view0, view0.getResources().getString(v), this.createAccessibilityViewCommandForState(v1));
    }

    public void addBottomSheetCallback(BottomSheetCallback bottomSheetBehavior$BottomSheetCallback0) {
        if(!this.callbacks.contains(bottomSheetBehavior$BottomSheetCallback0)) {
            this.callbacks.add(bottomSheetBehavior$BottomSheetCallback0);
        }
    }

    private void calculateCollapsedOffset() {
        int v = this.calculatePeekHeight();
        if(this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - v, this.fitToContentsOffset);
            return;
        }
        this.collapsedOffset = this.parentHeight - v;
    }

    private float calculateCornerInterpolation(float f, RoundedCorner roundedCorner0) {
        if(roundedCorner0 != null) {
            float f1 = (float)roundedCorner0.getRadius();
            return f1 <= 0.0f || f <= 0.0f ? 0.0f : f1 / f;
        }
        return 0.0f;
    }

    private void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = (int)(((float)this.parentHeight) * (1.0f - this.halfExpandedRatio));
    }

    private float calculateInterpolationWithCornersRemoved() {
        if(this.materialShapeDrawable != null && (this.viewRef != null && this.viewRef.get() != null && Build.VERSION.SDK_INT >= 0x1F)) {
            View view0 = (View)this.viewRef.get();
            if(this.isAtTopOfScreen()) {
                WindowInsets windowInsets0 = view0.getRootWindowInsets();
                return windowInsets0 == null ? 0.0f : Math.max(this.calculateCornerInterpolation(this.materialShapeDrawable.getTopLeftCornerResolvedSize(), windowInsets0.getRoundedCorner(0)), this.calculateCornerInterpolation(this.materialShapeDrawable.getTopRightCornerResolvedSize(), windowInsets0.getRoundedCorner(1)));
            }
        }
        return 0.0f;
    }

    private int calculatePeekHeight() {
        if(this.peekHeightAuto) {
            return Math.min(Math.max(this.peekHeightMin, this.parentHeight - this.parentWidth * 9 / 16), this.childHeight) + this.insetBottom;
        }
        if(!this.gestureInsetBottomIgnored && !this.paddingBottomSystemWindowInsets) {
            return this.gestureInsetBottom <= 0 ? this.peekHeight + this.insetBottom : Math.max(this.peekHeight, this.gestureInsetBottom + this.peekHeightGestureInsetBuffer);
        }
        return this.peekHeight + this.insetBottom;
    }

    public float calculateSlideOffset() {
        return this.viewRef == null || this.viewRef.get() == null ? -1.0f : this.calculateSlideOffsetWithTop(((View)this.viewRef.get()).getTop());
    }

    private float calculateSlideOffsetWithTop(int v) {
        return v > this.collapsedOffset || this.collapsedOffset == this.getExpandedOffset() ? ((float)(this.collapsedOffset - v)) / ((float)(this.parentHeight - this.collapsedOffset)) : ((float)(this.collapsedOffset - v)) / ((float)(this.collapsedOffset - this.getExpandedOffset()));
    }

    private boolean canBeHiddenByDragging() {
        return this.isHideable();
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper0 = this.bottomContainerBackHelper;
        if(materialBottomContainerBackHelper0 == null) {
            return;
        }
        materialBottomContainerBackHelper0.cancelBackProgress();
    }

    private void clearAccessibilityAction(View view0, int v) {
        if(view0 != null) {
            ViewCompat.removeAccessibilityAction(view0, 0x80000);
            ViewCompat.removeAccessibilityAction(view0, 0x40000);
            ViewCompat.removeAccessibilityAction(view0, 0x100000);
            int v1 = this.expandHalfwayActionIds.get(v, -1);
            if(v1 != -1) {
                ViewCompat.removeAccessibilityAction(view0, v1);
                this.expandHalfwayActionIds.delete(v);
            }
        }
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int v) {
        return new AccessibilityViewCommand() {
            @Override  // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) {
                BottomSheetBehavior.this.setState(v);
                return true;
            }
        };
    }

    private void createMaterialShapeDrawableIfNeeded(Context context0) {
        if(this.shapeAppearanceModelDefault == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
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

    private void createShapeValueAnimator() {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{this.calculateInterpolationWithCornersRemoved(), 1.0f});
        this.interpolatorAnimator = valueAnimator0;
        valueAnimator0.setDuration(500L);
        this.interpolatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                if(BottomSheetBehavior.this.materialShapeDrawable != null) {
                    BottomSheetBehavior.this.materialShapeDrawable.setInterpolation(f);
                }
            }
        });
    }

    public void disableShapeAnimations() {
        this.interpolatorAnimator = null;
    }

    void dispatchOnSlide(int v) {
        View view0 = (View)this.viewRef.get();
        if(view0 != null && !this.callbacks.isEmpty()) {
            float f = this.calculateSlideOffsetWithTop(v);
            for(int v1 = 0; v1 < this.callbacks.size(); ++v1) {
                ((BottomSheetCallback)this.callbacks.get(v1)).onSlide(view0, f);
            }
        }
    }

    View findScrollingChild(View view0) {
        if(view0.getVisibility() != 0) {
            return null;
        }
        if(ViewCompat.isNestedScrollingEnabled(view0)) {
            return view0;
        }
        if(view0 instanceof ViewGroup) {
            int v = ((ViewGroup)view0).getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = this.findScrollingChild(((ViewGroup)view0).getChildAt(v1));
                if(view1 != null) {
                    return view1;
                }
            }
        }
        return null;
    }

    public static BottomSheetBehavior from(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        Behavior coordinatorLayout$Behavior0 = ((LayoutParams)viewGroup$LayoutParams0).getBehavior();
        if(!(coordinatorLayout$Behavior0 instanceof BottomSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        return (BottomSheetBehavior)coordinatorLayout$Behavior0;
    }

    MaterialBottomContainerBackHelper getBackHelper() {
        return this.bottomContainerBackHelper;
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

    public int getExpandedOffset() {
        if(this.fitToContents) {
            return this.fitToContentsOffset;
        }
        return this.paddingTopSystemWindowInsets ? Math.max(this.expandedOffset, 0) : Math.max(this.expandedOffset, this.insetTop);
    }

    public float getHalfExpandedRatio() {
        return this.halfExpandedRatio;
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    MaterialShapeDrawable getMaterialShapeDrawable() {
        return this.materialShapeDrawable;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    // 去混淆评级： 低(20)
    public int getPeekHeight() {
        return this.peekHeightAuto ? -1 : this.peekHeight;
    }

    int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public int getSaveFlags() {
        return this.saveFlags;
    }

    public int getSignificantVelocityThreshold() {
        return this.significantVelocityThreshold;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public int getState() {
        return this.state;
    }

    private int getTopOffsetForState(int v) {
        switch(v) {
            case 3: {
                return this.getExpandedOffset();
            }
            case 4: {
                return this.collapsedOffset;
            }
            case 5: {
                return this.parentHeight;
            }
            case 6: {
                return this.halfExpandedOffset;
            }
            default: {
                throw new IllegalArgumentException("Invalid state to get top offset: " + v);
            }
        }
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker0 = this.velocityTracker;
        if(velocityTracker0 == null) {
            return 0.0f;
        }
        velocityTracker0.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper0 = this.bottomContainerBackHelper;
        if(materialBottomContainerBackHelper0 == null) {
            return;
        }
        BackEventCompat backEventCompat0 = materialBottomContainerBackHelper0.onHandleBackInvoked();
        int v = 4;
        if(backEventCompat0 != null && Build.VERSION.SDK_INT >= 34) {
            if(this.hideable) {
                this.bottomContainerBackHelper.finishBackProgressNotPersistent(backEventCompat0, new AnimatorListenerAdapter() {
                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationEnd(Animator animator0) {
                        BottomSheetBehavior.this.setStateInternal(5);
                        if(BottomSheetBehavior.this.viewRef != null && BottomSheetBehavior.this.viewRef.get() != null) {
                            ((View)BottomSheetBehavior.this.viewRef.get()).requestLayout();
                        }
                    }
                });
                return;
            }
            this.bottomContainerBackHelper.finishBackProgressPersistent(backEventCompat0, null);
            this.setState(4);
            return;
        }
        if(this.hideable) {
            v = 5;
        }
        this.setState(v);
    }

    private boolean isAtTopOfScreen() {
        if(this.viewRef != null && this.viewRef.get() != null) {
            int[] arr_v = new int[2];
            ((View)this.viewRef.get()).getLocationOnScreen(arr_v);
            return arr_v[1] == 0;
        }
        return false;
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    // 去混淆评级： 低(20)
    private boolean isExpandedAndShouldRemoveCorners() {
        return this.state == 3 && (this.shouldRemoveExpandedCorners || this.isAtTopOfScreen());
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public boolean isHideableWhenDragging() [...] // Inlined contents

    private boolean isLayouting(View view0) {
        ViewParent viewParent0 = view0.getParent();
        return viewParent0 != null && viewParent0.isLayoutRequested() && ViewCompat.isAttachedToWindow(view0);
    }

    public boolean isNestedScrollingCheckEnabled() [...] // Inlined contents

    public boolean isShouldRemoveExpandedCorners() {
        return this.shouldRemoveExpandedCorners;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onAttachedToLayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
        super.onAttachedToLayoutParams(coordinatorLayout$LayoutParams0);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.bottomContainerBackHelper = null;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.bottomContainerBackHelper = null;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        if(view0.isShown() && this.draggable) {
            int v = motionEvent0.getActionMasked();
            if(v == 0) {
                this.reset();
            }
            if(this.velocityTracker == null) {
                this.velocityTracker = VelocityTracker.obtain();
            }
            this.velocityTracker.addMovement(motionEvent0);
            View view1 = null;
            switch(v) {
                case 0: {
                    int v1 = (int)motionEvent0.getX();
                    this.initialY = (int)motionEvent0.getY();
                    if(this.state != 2) {
                        View view2 = this.nestedScrollingChildRef == null ? null : ((View)this.nestedScrollingChildRef.get());
                        if(view2 != null && coordinatorLayout0.isPointInChildBounds(view2, v1, this.initialY)) {
                            this.activePointerId = motionEvent0.getPointerId(motionEvent0.getActionIndex());
                            this.touchingScrollingChild = true;
                        }
                    }
                    this.ignoreEvents = this.activePointerId == -1 && !coordinatorLayout0.isPointInChildBounds(view0, v1, this.initialY);
                    break;
                }
                case 1: {
                label_20:
                    this.touchingScrollingChild = false;
                    this.activePointerId = -1;
                    if(this.ignoreEvents) {
                        this.ignoreEvents = false;
                        return false;
                    }
                    break;
                }
                default: {
                    if(v == 3) {
                        goto label_20;
                    }
                }
            }
            if(!this.ignoreEvents && (this.viewDragHelper != null && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent0))) {
                return true;
            }
            WeakReference weakReference0 = this.nestedScrollingChildRef;
            if(weakReference0 != null) {
                view1 = (View)weakReference0.get();
            }
            return v == 2 && view1 != null && !this.ignoreEvents && this.state != 1 && !coordinatorLayout0.isPointInChildBounds(view1, ((int)motionEvent0.getX()), ((int)motionEvent0.getY())) && this.viewDragHelper != null && (this.initialY != -1 && Math.abs(((float)this.initialY) - motionEvent0.getY()) > ((float)this.viewDragHelper.getTouchSlop()));
        }
        this.ignoreEvents = true;
        return false;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        if(ViewCompat.getFitsSystemWindows(coordinatorLayout0) && !ViewCompat.getFitsSystemWindows(view0)) {
            view0.setFitsSystemWindows(true);
        }
        if(this.viewRef == null) {
            this.peekHeightMin = coordinatorLayout0.getResources().getDimensionPixelSize(dimen.design_bottom_sheet_peek_height_min);
            this.setWindowInsetsListener(view0);
            ViewCompat.setWindowInsetsAnimationCallback(view0, new InsetsAnimationCallback(view0));
            this.viewRef = new WeakReference(view0);
            this.bottomContainerBackHelper = new MaterialBottomContainerBackHelper(view0);
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
            this.updateAccessibilityActions();
            if(ViewCompat.getImportantForAccessibility(view0) == 0) {
                ViewCompat.setImportantForAccessibility(view0, 1);
            }
        }
        if(this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout0, this.dragCallback);
        }
        int v1 = view0.getTop();
        coordinatorLayout0.onLayoutChild(view0, v);
        this.parentWidth = coordinatorLayout0.getWidth();
        this.parentHeight = coordinatorLayout0.getHeight();
        int v2 = view0.getHeight();
        this.childHeight = v2;
        int v3 = this.parentHeight;
        int v4 = this.insetTop;
        if(v3 - v2 < v4) {
            if(this.paddingTopSystemWindowInsets) {
                int v5 = this.maxHeight;
                if(v5 != -1) {
                    v3 = Math.min(v3, v5);
                }
                this.childHeight = v3;
            }
            else {
                int v6 = v3 - v4;
                int v7 = this.maxHeight;
                if(v7 != -1) {
                    v6 = Math.min(v6, v7);
                }
                this.childHeight = v6;
            }
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - this.childHeight);
        this.calculateHalfExpandedOffset();
        this.calculateCollapsedOffset();
        int v9 = this.state;
        if(v9 == 3) {
            ViewCompat.offsetTopAndBottom(view0, this.getExpandedOffset());
        }
        else if(v9 == 6) {
            ViewCompat.offsetTopAndBottom(view0, this.halfExpandedOffset);
        }
        else if(!this.hideable || v9 != 5) {
            switch(v9) {
                case 1: 
                case 2: {
                    ViewCompat.offsetTopAndBottom(view0, v1 - view0.getTop());
                    break;
                }
                case 4: {
                    ViewCompat.offsetTopAndBottom(view0, this.collapsedOffset);
                }
            }
        }
        else {
            ViewCompat.offsetTopAndBottom(view0, this.parentHeight);
        }
        this.updateDrawableForTargetState(this.state, false);
        this.nestedScrollingChildRef = new WeakReference(this.findScrollingChild(view0));
        for(int v8 = 0; v8 < this.callbacks.size(); ++v8) {
            ((BottomSheetCallback)this.callbacks.get(v8)).onLayout(view0);
        }
        return true;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2, int v3) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        view0.measure(this.getChildMeasureSpec(v, coordinatorLayout0.getPaddingLeft() + coordinatorLayout0.getPaddingRight() + viewGroup$MarginLayoutParams0.leftMargin + viewGroup$MarginLayoutParams0.rightMargin + v1, this.maxWidth, viewGroup$MarginLayoutParams0.width), this.getChildMeasureSpec(v2, coordinatorLayout0.getPaddingTop() + coordinatorLayout0.getPaddingBottom() + viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin + v3, this.maxHeight, viewGroup$MarginLayoutParams0.height));
        return true;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout0, View view0, View view1, float f, float f1) {
        return this.nestedScrollingChildRef != null && view1 == this.nestedScrollingChildRef.get() && (this.state != 3 || super.onNestedPreFling(coordinatorLayout0, view0, view1, f, f1));
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v, int v1, int[] arr_v, int v2) {
        if(v2 != 1 && view1 == (this.nestedScrollingChildRef == null ? null : ((View)this.nestedScrollingChildRef.get()))) {
            int v3 = view0.getTop();
            int v4 = v3 - v1;
            if(v1 > 0) {
                if(v4 < this.getExpandedOffset()) {
                    int v5 = v3 - this.getExpandedOffset();
                    arr_v[1] = v5;
                    ViewCompat.offsetTopAndBottom(view0, -v5);
                    this.setStateInternal(3);
                    this.dispatchOnSlide(view0.getTop());
                    this.lastNestedScrollDy = v1;
                    this.nestedScrolled = true;
                    return;
                }
                if(this.draggable) {
                    arr_v[1] = v1;
                    ViewCompat.offsetTopAndBottom(view0, -v1);
                    this.setStateInternal(1);
                    this.dispatchOnSlide(view0.getTop());
                    this.lastNestedScrollDy = v1;
                    this.nestedScrolled = true;
                }
            }
            else {
                if(v1 >= 0 || view1.canScrollVertically(-1)) {
                    this.dispatchOnSlide(view0.getTop());
                    this.lastNestedScrollDy = v1;
                    this.nestedScrolled = true;
                    return;
                }
                boolean z = false;
                if(v4 > this.collapsedOffset && !this.canBeHiddenByDragging()) {
                    z = true;
                    int v6 = v3 - this.collapsedOffset;
                    arr_v[1] = v6;
                    ViewCompat.offsetTopAndBottom(view0, -v6);
                    this.setStateInternal(4);
                }
                else if(this.draggable) {
                    z = true;
                    arr_v[1] = v1;
                    ViewCompat.offsetTopAndBottom(view0, -v1);
                    this.setStateInternal(1);
                }
                if(z) {
                    this.dispatchOnSlide(view0.getTop());
                    this.lastNestedScrollDy = v1;
                    this.nestedScrolled = true;
                }
            }
        }
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout0, View view0, Parcelable parcelable0) {
        super.onRestoreInstanceState(coordinatorLayout0, view0, ((SavedState)parcelable0).getSuperState());
        this.restoreOptionalState(((SavedState)parcelable0));
        switch(((SavedState)parcelable0).state) {
            case 1: 
            case 2: {
                this.state = 4;
                this.lastStableState = 4;
                return;
            }
            default: {
                this.state = ((SavedState)parcelable0).state;
                this.lastStableState = ((SavedState)parcelable0).state;
            }
        }
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout0, View view0) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout0, view0), this);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, View view2, int v, int v1) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (v & 2) != 0;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v) {
        int v1 = 3;
        if(view0.getTop() == this.getExpandedOffset()) {
            this.setStateInternal(3);
            return;
        }
        if(this.nestedScrollingChildRef == null || view1 != this.nestedScrollingChildRef.get() || !this.nestedScrolled) {
            return;
        }
        if(this.lastNestedScrollDy <= 0) {
            if(this.hideable && this.shouldHide(view0, this.getYVelocity())) {
                v1 = 5;
            }
            else if(this.lastNestedScrollDy == 0) {
                int v2 = view0.getTop();
                if(!this.fitToContents) {
                    int v3 = this.halfExpandedOffset;
                    if(v2 >= v3) {
                        v1 = Math.abs(v2 - v3) < Math.abs(v2 - this.collapsedOffset) ? 6 : 4;
                    }
                    else if(v2 >= Math.abs(v2 - this.collapsedOffset)) {
                        v1 = 6;
                    }
                }
                else if(Math.abs(v2 - this.fitToContentsOffset) >= Math.abs(v2 - this.collapsedOffset)) {
                    v1 = 4;
                }
            }
            else if(this.fitToContents) {
                v1 = 4;
            }
            else {
                int v4 = view0.getTop();
                v1 = Math.abs(v4 - this.halfExpandedOffset) < Math.abs(v4 - this.collapsedOffset) ? 6 : 4;
            }
        }
        else if(!this.fitToContents && view0.getTop() > this.halfExpandedOffset) {
            v1 = 6;
        }
        this.startSettling(view0, v1, false);
        this.nestedScrolled = false;
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
            this.reset();
        }
        if(this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent0);
        if(this.shouldHandleDraggingWithHelper() && v == 2 && !this.ignoreEvents && Math.abs(((float)this.initialY) - motionEvent0.getY()) > ((float)this.viewDragHelper.getTouchSlop())) {
            this.viewDragHelper.captureChildView(view0, motionEvent0.getPointerId(motionEvent0.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public void removeBottomSheetCallback(BottomSheetCallback bottomSheetBehavior$BottomSheetCallback0) {
        this.callbacks.remove(bottomSheetBehavior$BottomSheetCallback0);
    }

    private void replaceAccessibilityActionForState(View view0, AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0, int v) {
        ViewCompat.replaceAccessibilityAction(view0, accessibilityNodeInfoCompat$AccessibilityActionCompat0, null, this.createAccessibilityViewCommandForState(v));
    }

    private void reset() {
        this.activePointerId = -1;
        this.initialY = -1;
        VelocityTracker velocityTracker0 = this.velocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
            this.velocityTracker = null;
        }
    }

    private void restoreOptionalState(SavedState bottomSheetBehavior$SavedState0) {
        int v = this.saveFlags;
        if(v != 0) {
            if(v == -1 || (v & 1) == 1) {
                this.peekHeight = bottomSheetBehavior$SavedState0.peekHeight;
            }
            if(this.saveFlags == -1 || (this.saveFlags & 2) == 2) {
                this.fitToContents = bottomSheetBehavior$SavedState0.fitToContents;
            }
            if(this.saveFlags == -1 || (this.saveFlags & 4) == 4) {
                this.hideable = bottomSheetBehavior$SavedState0.hideable;
            }
            if(this.saveFlags == -1 || (this.saveFlags & 8) == 8) {
                this.skipCollapsed = bottomSheetBehavior$SavedState0.skipCollapsed;
            }
        }
    }

    private void runAfterLayout(View view0, Runnable runnable0) {
        if(this.isLayouting(view0)) {
            view0.post(runnable0);
            return;
        }
        runnable0.run();
    }

    void setAccessibilityDelegateView(View view0) {
        if(view0 == null) {
            WeakReference weakReference0 = this.accessibilityDelegateViewRef;
            if(weakReference0 != null) {
                this.clearAccessibilityAction(((View)weakReference0.get()), 1);
                this.accessibilityDelegateViewRef = null;
                return;
            }
        }
        this.accessibilityDelegateViewRef = new WeakReference(view0);
        this.updateAccessibilityActions(view0, 1);
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback bottomSheetBehavior$BottomSheetCallback0) {
        Log.w("BottomSheetBehavior", "BottomSheetBehavior now supports multiple callbacks. `setBottomSheetCallback()` removes all existing callbacks, including ones set internally by library authors, which may result in unintended behavior. This may change in the future. Please use `addBottomSheetCallback()` and `removeBottomSheetCallback()` instead to set your own callbacks.");
        this.callbacks.clear();
        if(bottomSheetBehavior$BottomSheetCallback0 != null) {
            this.callbacks.add(bottomSheetBehavior$BottomSheetCallback0);
        }
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
    }

    public void setExpandedOffset(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.expandedOffset = v;
        this.updateDrawableForTargetState(this.state, true);
    }

    public void setFitToContents(boolean z) {
        if(this.fitToContents == z) {
            return;
        }
        this.fitToContents = z;
        if(this.viewRef != null) {
            this.calculateCollapsedOffset();
        }
        this.setStateInternal((!this.fitToContents || this.state != 6 ? this.state : 3));
        this.updateDrawableForTargetState(this.state, true);
        this.updateAccessibilityActions();
    }

    public void setGestureInsetBottomIgnored(boolean z) {
        this.gestureInsetBottomIgnored = z;
    }

    public void setHalfExpandedRatio(float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.halfExpandedRatio = f;
        if(this.viewRef != null) {
            this.calculateHalfExpandedOffset();
        }
    }

    public void setHideFriction(float f) {
        this.hideFriction = f;
    }

    public void setHideable(boolean z) {
        if(this.hideable != z) {
            this.hideable = z;
            if(!z && this.state == 5) {
                this.setState(4);
            }
            this.updateAccessibilityActions();
        }
    }

    public void setHideableInternal(boolean z) {
        this.hideable = z;
    }

    public void setMaxHeight(int v) {
        this.maxHeight = v;
    }

    public void setMaxWidth(int v) {
        this.maxWidth = v;
    }

    public void setPeekHeight(int v) {
        this.setPeekHeight(v, false);
    }

    public final void setPeekHeight(int v, boolean z) {
        if(v == -1) {
            if(this.peekHeightAuto) {
                return;
            }
            this.peekHeightAuto = true;
        }
        else {
            if(!this.peekHeightAuto && this.peekHeight == v) {
                return;
            }
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, v);
        }
        this.updatePeekHeight(z);
    }

    public void setSaveFlags(int v) {
        this.saveFlags = v;
    }

    public void setShouldRemoveExpandedCorners(boolean z) {
        if(this.shouldRemoveExpandedCorners != z) {
            this.shouldRemoveExpandedCorners = z;
            this.updateDrawableForTargetState(this.getState(), true);
        }
    }

    public void setSignificantVelocityThreshold(int v) {
        this.significantVelocityThreshold = v;
    }

    public void setSkipCollapsed(boolean z) {
        this.skipCollapsed = z;
    }

    public void setState(int v) {
        if(v == 1 || v == 2) {
            throw new IllegalArgumentException("STATE_" + (v == 1 ? "DRAGGING" : "SETTLING") + " should not be set externally.");
        }
        if(!this.hideable && v == 5) {
            Log.w("BottomSheetBehavior", "Cannot set state: " + 5);
            return;
        }
        int v1 = v != 6 || !this.fitToContents || this.getTopOffsetForState(6) > this.fitToContentsOffset ? v : 3;
        if(this.viewRef != null && this.viewRef.get() != null) {
            View view0 = (View)this.viewRef.get();
            this.runAfterLayout(view0, () -> {
                int v1 = BottomSheetBehavior.this.getTopOffsetForState(this.val$finalState);
                ViewDragHelper viewDragHelper0 = BottomSheetBehavior.this.viewDragHelper;
                if(viewDragHelper0 != null) {
                    if(!false) {
                        if(viewDragHelper0.smoothSlideViewTo(this.val$child, this.val$child.getLeft(), v1)) {
                            BottomSheetBehavior.this.setStateInternal(2);
                            BottomSheetBehavior.this.updateDrawableForTargetState(this.val$finalState, true);
                            BottomSheetBehavior.this.stateSettlingTracker.continueSettlingToState(this.val$finalState);
                            return;
                        }
                    }
                    else if(viewDragHelper0.settleCapturedViewAt(this.val$child.getLeft(), v1)) {
                        BottomSheetBehavior.this.setStateInternal(2);
                        BottomSheetBehavior.this.updateDrawableForTargetState(this.val$finalState, true);
                        BottomSheetBehavior.this.stateSettlingTracker.continueSettlingToState(this.val$finalState);
                        return;
                    }
                }
                BottomSheetBehavior.this.setStateInternal(this.val$finalState);
            });
            return;
        }
        this.setStateInternal(v);

        class com.google.android.material.bottomsheet.BottomSheetBehavior.1 implements Runnable {
            com.google.android.material.bottomsheet.BottomSheetBehavior.1(View view0, int v) {
            }

            @Override
            public void run() {
                BottomSheetBehavior.this.startSettling(this.val$child, this.val$finalState, false);
            }
        }

    }

    void setStateInternal(int v) {
        if(this.state != v) {
            this.state = v;
            if(v == 3 || v == 4 || v == 6 || this.hideable && v == 5) {
                this.lastStableState = v;
            }
            WeakReference weakReference0 = this.viewRef;
            if(weakReference0 != null) {
                View view0 = (View)weakReference0.get();
                if(view0 != null) {
                    if(v == 3) {
                        this.updateImportantForAccessibility(true);
                    }
                    else if(v == 4 || v == 5 || v == 6) {
                        this.updateImportantForAccessibility(false);
                    }
                    this.updateDrawableForTargetState(v, true);
                    for(int v1 = 0; v1 < this.callbacks.size(); ++v1) {
                        ((BottomSheetCallback)this.callbacks.get(v1)).onStateChanged(view0, v);
                    }
                    this.updateAccessibilityActions();
                }
            }
        }
    }

    public void setUpdateImportantForAccessibilityOnSiblings(boolean z) {
        this.updateImportantForAccessibilityOnSiblings = z;
    }

    private void setWindowInsetsListener(View view0) {
        boolean z = Build.VERSION.SDK_INT >= 29 && !this.isGestureInsetBottomIgnored() && !this.peekHeightAuto;
        if(!this.paddingBottomSystemWindowInsets && !this.paddingLeftSystemWindowInsets && !this.paddingRightSystemWindowInsets && !this.marginLeftSystemWindowInsets && !this.marginRightSystemWindowInsets && !this.marginTopSystemWindowInsets && !z) {
            return;
        }
        ViewUtils.doOnApplyWindowInsets(view0, new OnApplyWindowInsetsListener() {
            @Override  // com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) {
                int v5;
                Insets insets0 = windowInsetsCompat0.getInsets(0x207);
                Insets insets1 = windowInsetsCompat0.getInsets(0x20);
                BottomSheetBehavior.this.insetTop = insets0.top;
                boolean z = ViewUtils.isLayoutRtl(view0);
                int v = view0.getPaddingBottom();
                int v1 = view0.getPaddingLeft();
                int v2 = view0.getPaddingRight();
                if(BottomSheetBehavior.this.paddingBottomSystemWindowInsets) {
                    int v3 = windowInsetsCompat0.getSystemWindowInsetBottom();
                    BottomSheetBehavior.this.insetBottom = v3;
                    v = viewUtils$RelativePadding0.bottom + BottomSheetBehavior.this.insetBottom;
                }
                if(BottomSheetBehavior.this.paddingLeftSystemWindowInsets) {
                    v1 = (z ? viewUtils$RelativePadding0.end : viewUtils$RelativePadding0.start) + insets0.left;
                }
                if(BottomSheetBehavior.this.paddingRightSystemWindowInsets) {
                    v2 = (z ? viewUtils$RelativePadding0.start : viewUtils$RelativePadding0.end) + insets0.right;
                }
                ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
                int v4 = 1;
                if(!BottomSheetBehavior.this.marginLeftSystemWindowInsets || viewGroup$MarginLayoutParams0.leftMargin == insets0.left) {
                    v5 = 0;
                }
                else {
                    viewGroup$MarginLayoutParams0.leftMargin = insets0.left;
                    v5 = 1;
                }
                if(BottomSheetBehavior.this.marginRightSystemWindowInsets && viewGroup$MarginLayoutParams0.rightMargin != insets0.right) {
                    viewGroup$MarginLayoutParams0.rightMargin = insets0.right;
                    v5 = 1;
                }
                if(!BottomSheetBehavior.this.marginTopSystemWindowInsets || viewGroup$MarginLayoutParams0.topMargin == insets0.top) {
                    v4 = v5;
                }
                else {
                    viewGroup$MarginLayoutParams0.topMargin = insets0.top;
                }
                if(v4 != 0) {
                    view0.setLayoutParams(viewGroup$MarginLayoutParams0);
                }
                view0.setPadding(v1, view0.getPaddingTop(), v2, v);
                if(z) {
                    BottomSheetBehavior.this.gestureInsetBottom = insets1.bottom;
                }
                if(!BottomSheetBehavior.this.paddingBottomSystemWindowInsets && !z) {
                    return windowInsetsCompat0;
                }
                BottomSheetBehavior.this.updatePeekHeight(false);
                return windowInsetsCompat0;
            }
        });
    }

    public boolean shouldExpandOnUpwardDrag(long v, float f) {
        return false;
    }

    private boolean shouldHandleDraggingWithHelper() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    boolean shouldHide(View view0, float f) {
        if(this.skipCollapsed) {
            return true;
        }
        if(view0.getTop() < this.collapsedOffset) {
            return false;
        }
        int v = this.calculatePeekHeight();
        return Math.abs(((float)view0.getTop()) + f * this.hideFriction - ((float)this.collapsedOffset)) / ((float)v) > 0.5f;
    }

    public boolean shouldSkipHalfExpandedStateWhenDragging() [...] // Inlined contents

    public boolean shouldSkipSmoothAnimation() [...] // Inlined contents

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat backEventCompat0) {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper0 = this.bottomContainerBackHelper;
        if(materialBottomContainerBackHelper0 == null) {
            return;
        }
        materialBottomContainerBackHelper0.startBackProgress(backEventCompat0);
    }

    // 检测为 Lambda 实现
    private void startSettling(View view0, int v, boolean z) [...]

    private void updateAccessibilityActions() {
        WeakReference weakReference0 = this.viewRef;
        if(weakReference0 != null) {
            this.updateAccessibilityActions(((View)weakReference0.get()), 0);
        }
        WeakReference weakReference1 = this.accessibilityDelegateViewRef;
        if(weakReference1 != null) {
            this.updateAccessibilityActions(((View)weakReference1.get()), 1);
        }
    }

    private void updateAccessibilityActions(View view0, int v) {
        if(view0 != null) {
            this.clearAccessibilityAction(view0, v);
            int v1 = 6;
            if(!this.fitToContents && this.state != 6) {
                int v2 = this.addAccessibilityActionForState(view0, string.bottomsheet_action_expand_halfway, 6);
                this.expandHalfwayActionIds.put(v, v2);
            }
            if(this.hideable && this.state != 5) {
                this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            switch(this.state) {
                case 3: {
                    if(this.fitToContents) {
                        v1 = 4;
                    }
                    this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_COLLAPSE, v1);
                    return;
                }
                case 4: {
                    if(this.fitToContents) {
                        v1 = 3;
                    }
                    this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_EXPAND, v1);
                    return;
                }
                case 6: {
                    this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_COLLAPSE, 4);
                    this.replaceAccessibilityActionForState(view0, AccessibilityActionCompat.ACTION_EXPAND, 3);
                }
            }
        }
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat backEventCompat0) {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper0 = this.bottomContainerBackHelper;
        if(materialBottomContainerBackHelper0 == null) {
            return;
        }
        materialBottomContainerBackHelper0.updateBackProgress(backEventCompat0);
    }

    private void updateDrawableForTargetState(int v, boolean z) {
        if(v != 2) {
            boolean z1 = this.isExpandedAndShouldRemoveCorners();
            if(this.expandedCornersRemoved != z1 && this.materialShapeDrawable != null) {
                this.expandedCornersRemoved = z1;
                float f = 1.0f;
                if(z) {
                    ValueAnimator valueAnimator0 = this.interpolatorAnimator;
                    if(valueAnimator0 != null) {
                        if(valueAnimator0.isRunning()) {
                            this.interpolatorAnimator.reverse();
                            return;
                        }
                        float f1 = this.materialShapeDrawable.getInterpolation();
                        if(z1) {
                            f = this.calculateInterpolationWithCornersRemoved();
                        }
                        this.interpolatorAnimator.setFloatValues(new float[]{f1, f});
                        this.interpolatorAnimator.start();
                        return;
                    }
                }
                if(this.interpolatorAnimator != null && this.interpolatorAnimator.isRunning()) {
                    this.interpolatorAnimator.cancel();
                }
                MaterialShapeDrawable materialShapeDrawable0 = this.materialShapeDrawable;
                if(this.expandedCornersRemoved) {
                    f = this.calculateInterpolationWithCornersRemoved();
                }
                materialShapeDrawable0.setInterpolation(f);
            }
        }
    }

    private void updateImportantForAccessibility(boolean z) {
        int v1;
        WeakReference weakReference0 = this.viewRef;
        if(weakReference0 != null) {
            ViewParent viewParent0 = ((View)weakReference0.get()).getParent();
            if(viewParent0 instanceof CoordinatorLayout) {
                int v = ((CoordinatorLayout)viewParent0).getChildCount();
                if(!z) {
                    v1 = 0;
                label_11:
                    while(v1 < v) {
                        View view0 = ((CoordinatorLayout)viewParent0).getChildAt(v1);
                        if(view0 != this.viewRef.get()) {
                            if(z) {
                                this.importantForAccessibilityMap.put(view0, view0.getImportantForAccessibility());
                                if(this.updateImportantForAccessibilityOnSiblings) {
                                    ViewCompat.setImportantForAccessibility(view0, 4);
                                }
                            }
                            else if(this.updateImportantForAccessibilityOnSiblings && (this.importantForAccessibilityMap != null && this.importantForAccessibilityMap.containsKey(view0))) {
                                ViewCompat.setImportantForAccessibility(view0, ((int)(((Integer)this.importantForAccessibilityMap.get(view0)))));
                            }
                        }
                        ++v1;
                    }
                    if(!z) {
                        this.importantForAccessibilityMap = null;
                        return;
                    }
                    if(this.updateImportantForAccessibilityOnSiblings) {
                        ((View)this.viewRef.get()).sendAccessibilityEvent(8);
                    }
                }
                else if(this.importantForAccessibilityMap == null) {
                    this.importantForAccessibilityMap = new HashMap(v);
                    v1 = 0;
                    goto label_11;
                }
            }
        }
    }

    private void updatePeekHeight(boolean z) {
        if(this.viewRef != null) {
            this.calculateCollapsedOffset();
            if(this.state == 4) {
                View view0 = (View)this.viewRef.get();
                if(view0 != null) {
                    if(z) {
                        this.setState(4);
                        return;
                    }
                    view0.requestLayout();
                }
            }
        }
    }
}

