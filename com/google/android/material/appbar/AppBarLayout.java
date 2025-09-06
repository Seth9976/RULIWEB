package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.integer;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AppBarLayout extends LinearLayout implements AttachedBehavior {
    public static class BaseBehavior extends HeaderBehavior {
        public static abstract class BaseDragCallback {
            public abstract boolean canDrag(AppBarLayout arg1);
        }

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator CREATOR;
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;
            boolean fullyExpanded;
            boolean fullyScrolled;

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

            public SavedState(Parcel parcel0, ClassLoader classLoader0) {
                super(parcel0, classLoader0);
                boolean z = true;
                this.fullyScrolled = parcel0.readByte() != 0;
                this.fullyExpanded = parcel0.readByte() != 0;
                this.firstVisibleChildIndex = parcel0.readInt();
                this.firstVisibleChildPercentageShown = parcel0.readFloat();
                if(parcel0.readByte() == 0) {
                    z = false;
                }
                this.firstVisibleChildAtMinimumHeight = z;
            }

            public SavedState(Parcelable parcelable0) {
                super(parcelable0);
            }

            @Override  // androidx.customview.view.AbsSavedState
            public void writeToParcel(Parcel parcel0, int v) {
                super.writeToParcel(parcel0, v);
                parcel0.writeByte(((byte)this.fullyScrolled));
                parcel0.writeByte(((byte)this.fullyExpanded));
                parcel0.writeInt(this.firstVisibleChildIndex);
                parcel0.writeFloat(this.firstVisibleChildPercentageShown);
                parcel0.writeByte(((byte)this.firstVisibleChildAtMinimumHeight));
            }
        }

        private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
        private WeakReference lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        private int offsetDelta;
        private BaseDragCallback onDragCallback;
        private SavedState savedState;

        public BaseBehavior() {
        }

        public BaseBehavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        private void addAccessibilityDelegateIfNeeded(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0) {
            if(!ViewCompat.hasAccessibilityDelegate(coordinatorLayout0)) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout0, new AccessibilityDelegateCompat() {
                    @Override  // androidx.core.view.AccessibilityDelegateCompat
                    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                        super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                        accessibilityNodeInfoCompat0.setClassName("android.widget.ScrollView");
                        if(appBarLayout0.getTotalScrollRange() != 0) {
                            View view1 = BaseBehavior.this.getChildWithScrollingBehavior(coordinatorLayout0);
                            if(view1 != null && BaseBehavior.this.childrenHaveScrollFlags(appBarLayout0)) {
                                if(BaseBehavior.this.getTopBottomOffsetForScrollingSibling() != -appBarLayout0.getTotalScrollRange()) {
                                    accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                                    accessibilityNodeInfoCompat0.setScrollable(true);
                                }
                                if(BaseBehavior.this.getTopBottomOffsetForScrollingSibling() != 0) {
                                    if(!view1.canScrollVertically(-1)) {
                                        accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                                        accessibilityNodeInfoCompat0.setScrollable(true);
                                    }
                                    else if(-appBarLayout0.getDownNestedPreScrollRange() != 0) {
                                        accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                                        accessibilityNodeInfoCompat0.setScrollable(true);
                                    }
                                }
                            }
                        }
                    }

                    @Override  // androidx.core.view.AccessibilityDelegateCompat
                    public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
                        switch(v) {
                            case 0x1000: {
                                appBarLayout0.setExpanded(false);
                                return true;
                            }
                            case 0x2000: {
                                if(BaseBehavior.this.getTopBottomOffsetForScrollingSibling() != 0) {
                                    View view1 = BaseBehavior.this.getChildWithScrollingBehavior(coordinatorLayout0);
                                    if(!view1.canScrollVertically(-1)) {
                                        appBarLayout0.setExpanded(true);
                                        return true;
                                    }
                                    int v1 = appBarLayout0.getDownNestedPreScrollRange();
                                    if(-v1 != 0) {
                                        BaseBehavior.this.onNestedPreScroll(coordinatorLayout0, appBarLayout0, view1, 0, -v1, new int[]{0, 0}, 1);
                                        return true;
                                    }
                                }
                                return false;
                            }
                            default: {
                                return super.performAccessibilityAction(view0, v, bundle0);
                            }
                        }
                    }
                });
            }
        }

        private void animateOffsetTo(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v, float f) {
            int v1 = Math.abs(this.getTopBottomOffsetForScrollingSibling() - v);
            float f1 = Math.abs(f);
            this.animateOffsetWithDuration(coordinatorLayout0, appBarLayout0, v, (f1 > 0.0f ? Math.round(((float)v1) / f1 * 1000.0f) * 3 : ((int)((((float)v1) / ((float)appBarLayout0.getHeight()) + 1.0f) * 150.0f))));
        }

        private void animateOffsetWithDuration(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v, int v1) {
            int v2 = this.getTopBottomOffsetForScrollingSibling();
            if(v2 == v) {
                if(this.offsetAnimator != null && this.offsetAnimator.isRunning()) {
                    this.offsetAnimator.cancel();
                }
                return;
            }
            ValueAnimator valueAnimator0 = this.offsetAnimator;
            if(valueAnimator0 == null) {
                ValueAnimator valueAnimator1 = new ValueAnimator();
                this.offsetAnimator = valueAnimator1;
                valueAnimator1.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                        int v = (int)(((Integer)valueAnimator0.getAnimatedValue()));
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout0, appBarLayout0, v);
                    }
                });
            }
            else {
                valueAnimator0.cancel();
            }
            this.offsetAnimator.setDuration(((long)Math.min(v1, 600)));
            this.offsetAnimator.setIntValues(new int[]{v2, v});
            this.offsetAnimator.start();
        }

        private int calculateSnapOffset(int v, int v1, int v2) {
            return v >= (v1 + v2) / 2 ? v2 : v1;
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        boolean canDragView(View view0) {
            return this.canDragView(((AppBarLayout)view0));
        }

        boolean canDragView(AppBarLayout appBarLayout0) {
            BaseDragCallback appBarLayout$BaseBehavior$BaseDragCallback0 = this.onDragCallback;
            if(appBarLayout$BaseBehavior$BaseDragCallback0 != null) {
                return appBarLayout$BaseBehavior$BaseDragCallback0.canDrag(appBarLayout0);
            }
            WeakReference weakReference0 = this.lastNestedScrollingChildRef;
            if(weakReference0 != null) {
                View view0 = (View)weakReference0.get();
                return view0 != null && view0.isShown() && !view0.canScrollVertically(-1);
            }
            return true;
        }

        private boolean canScrollChildren(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0) {
            return appBarLayout0.hasScrollableChildren() && coordinatorLayout0.getHeight() - view0.getHeight() <= appBarLayout0.getHeight();
        }

        private static boolean checkFlag(int v, int v1) {
            return (v & v1) == v1;
        }

        private boolean childrenHaveScrollFlags(AppBarLayout appBarLayout0) {
            int v = appBarLayout0.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                if(((LayoutParams)appBarLayout0.getChildAt(v1).getLayoutParams()).scrollFlags != 0) {
                    return true;
                }
            }
            return false;
        }

        private View findFirstScrollingChild(CoordinatorLayout coordinatorLayout0) {
            int v = coordinatorLayout0.getChildCount();
            int v1 = 0;
            while(v1 < v) {
                View view0 = coordinatorLayout0.getChildAt(v1);
                if(!(view0 instanceof NestedScrollingChild) && !(view0 instanceof AbsListView) && !(view0 instanceof ScrollView)) {
                    ++v1;
                    continue;
                }
                return view0;
            }
            return null;
        }

        private static View getAppBarChildOnOffset(AppBarLayout appBarLayout0, int v) {
            int v1 = Math.abs(v);
            int v2 = appBarLayout0.getChildCount();
            for(int v3 = 0; v3 < v2; ++v3) {
                View view0 = appBarLayout0.getChildAt(v3);
                if(v1 >= view0.getTop() && v1 <= view0.getBottom()) {
                    return view0;
                }
            }
            return null;
        }

        private int getChildIndexOnOffset(AppBarLayout appBarLayout0, int v) {
            int v1 = appBarLayout0.getChildCount();
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = appBarLayout0.getChildAt(v2);
                int v3 = view0.getTop();
                int v4 = view0.getBottom();
                LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(BaseBehavior.checkFlag(appBarLayout$LayoutParams0.getScrollFlags(), 0x20)) {
                    v3 -= appBarLayout$LayoutParams0.topMargin;
                    v4 += appBarLayout$LayoutParams0.bottomMargin;
                }
                if(v3 <= -v && v4 >= -v) {
                    return v2;
                }
            }
            return -1;
        }

        private View getChildWithScrollingBehavior(CoordinatorLayout coordinatorLayout0) {
            int v = coordinatorLayout0.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = coordinatorLayout0.getChildAt(v1);
                if(((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams)view0.getLayoutParams()).getBehavior() instanceof ScrollingViewBehavior) {
                    return view0;
                }
            }
            return null;
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        int getMaxDragOffset(View view0) {
            return this.getMaxDragOffset(((AppBarLayout)view0));
        }

        int getMaxDragOffset(AppBarLayout appBarLayout0) {
            return -appBarLayout0.getDownNestedScrollRange() + appBarLayout0.getTopInset();
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        int getScrollRangeForDragFling(View view0) {
            return this.getScrollRangeForDragFling(((AppBarLayout)view0));
        }

        int getScrollRangeForDragFling(AppBarLayout appBarLayout0) {
            return appBarLayout0.getTotalScrollRange();
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        int getTopBottomOffsetForScrollingSibling() {
            return this.getTopAndBottomOffset() + this.offsetDelta;
        }

        private int interpolateOffset(AppBarLayout appBarLayout0, int v) {
            int v1 = Math.abs(v);
            int v2 = appBarLayout0.getChildCount();
            int v3 = 0;
            for(int v4 = 0; v4 < v2; ++v4) {
                View view0 = appBarLayout0.getChildAt(v4);
                LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                Interpolator interpolator0 = appBarLayout$LayoutParams0.getScrollInterpolator();
                if(v1 >= view0.getTop() && v1 <= view0.getBottom()) {
                    if(interpolator0 == null) {
                        break;
                    }
                    int v5 = appBarLayout$LayoutParams0.getScrollFlags();
                    if((v5 & 1) != 0) {
                        v3 = view0.getHeight() + appBarLayout$LayoutParams0.topMargin + appBarLayout$LayoutParams0.bottomMargin;
                        if((v5 & 2) != 0) {
                            v3 -= ViewCompat.getMinimumHeight(view0);
                        }
                    }
                    if(ViewCompat.getFitsSystemWindows(view0)) {
                        v3 -= appBarLayout0.getTopInset();
                    }
                    if(v3 <= 0) {
                        break;
                    }
                    int v6 = Math.round(((float)v3) * interpolator0.getInterpolation(((float)(v1 - view0.getTop())) / ((float)v3)));
                    return Integer.signum(v) * (view0.getTop() + v6);
                }
            }
            return v;
        }

        boolean isOffsetAnimatorRunning() {
            return this.offsetAnimator != null && this.offsetAnimator.isRunning();
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        void onFlingFinished(CoordinatorLayout coordinatorLayout0, View view0) {
            this.onFlingFinished(coordinatorLayout0, ((AppBarLayout)view0));
        }

        void onFlingFinished(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0) {
            this.snapToChildIfNeeded(coordinatorLayout0, appBarLayout0);
            if(appBarLayout0.isLiftOnScroll()) {
                appBarLayout0.setLiftedState(appBarLayout0.shouldLift(this.findFirstScrollingChild(coordinatorLayout0)));
            }
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
            return this.onLayoutChild(coordinatorLayout0, ((AppBarLayout)view0), v);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v) {
            boolean z = super.onLayoutChild(coordinatorLayout0, appBarLayout0, v);
            int v1 = appBarLayout0.getPendingAction();
            SavedState appBarLayout$BaseBehavior$SavedState0 = this.savedState;
            if(appBarLayout$BaseBehavior$SavedState0 == null || (v1 & 8) != 0) {
                if(v1 != 0) {
                    boolean z1 = (v1 & 4) != 0;
                    if((v1 & 2) != 0) {
                        int v3 = appBarLayout0.getUpNestedPreScrollRange();
                        if(z1) {
                            this.animateOffsetTo(coordinatorLayout0, appBarLayout0, -v3, 0.0f);
                        }
                        else {
                            this.setHeaderTopBottomOffset(coordinatorLayout0, appBarLayout0, -v3);
                        }
                    }
                    else if((v1 & 1) != 0) {
                        if(z1) {
                            this.animateOffsetTo(coordinatorLayout0, appBarLayout0, 0, 0.0f);
                        }
                        else {
                            this.setHeaderTopBottomOffset(coordinatorLayout0, appBarLayout0, 0);
                        }
                    }
                }
            }
            else if(appBarLayout$BaseBehavior$SavedState0.fullyScrolled) {
                this.setHeaderTopBottomOffset(coordinatorLayout0, appBarLayout0, -appBarLayout0.getTotalScrollRange());
            }
            else if(this.savedState.fullyExpanded) {
                this.setHeaderTopBottomOffset(coordinatorLayout0, appBarLayout0, 0);
            }
            else {
                View view0 = appBarLayout0.getChildAt(this.savedState.firstVisibleChildIndex);
                int v2 = view0.getBottom();
                this.setHeaderTopBottomOffset(coordinatorLayout0, appBarLayout0, (this.savedState.firstVisibleChildAtMinimumHeight ? ViewCompat.getMinimumHeight(view0) + appBarLayout0.getTopInset() : Math.round(((float)view0.getHeight()) * this.savedState.firstVisibleChildPercentageShown)) - v2);
            }
            appBarLayout0.resetPendingAction();
            this.savedState = null;
            this.setTopAndBottomOffset(MathUtils.clamp(this.getTopAndBottomOffset(), -appBarLayout0.getTotalScrollRange(), 0));
            this.updateAppBarLayoutDrawableState(coordinatorLayout0, appBarLayout0, this.getTopAndBottomOffset(), 0, true);
            appBarLayout0.onOffsetChanged(this.getTopAndBottomOffset());
            this.addAccessibilityDelegateIfNeeded(coordinatorLayout0, appBarLayout0);
            return z;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2, int v3) {
            return this.onMeasureChild(coordinatorLayout0, ((AppBarLayout)view0), v, v1, v2, v3);
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v, int v1, int v2, int v3) {
            if(((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams)appBarLayout0.getLayoutParams()).height == -2) {
                coordinatorLayout0.onMeasureChild(appBarLayout0, v, v1, 0, v3);
                return true;
            }
            return super.onMeasureChild(coordinatorLayout0, appBarLayout0, v, v1, v2, v3);
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v, int v1, int[] arr_v, int v2) {
            this.onNestedPreScroll(coordinatorLayout0, ((AppBarLayout)view0), view1, v, v1, arr_v, v2);
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, int v, int v1, int[] arr_v, int v2) {
            int v4;
            int v3;
            if(v1 != 0) {
                if(v1 < 0) {
                    v3 = -appBarLayout0.getTotalScrollRange();
                    v4 = appBarLayout0.getDownNestedPreScrollRange() + v3;
                }
                else {
                    v3 = -appBarLayout0.getUpNestedPreScrollRange();
                    v4 = 0;
                }
                if(v3 != v4) {
                    arr_v[1] = this.scroll(coordinatorLayout0, appBarLayout0, v1, v3, v4);
                }
            }
            if(appBarLayout0.isLiftOnScroll()) {
                appBarLayout0.setLiftedState(appBarLayout0.shouldLift(view0));
            }
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
            this.onNestedScroll(coordinatorLayout0, ((AppBarLayout)view0), view1, v, v1, v2, v3, v4, arr_v);
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
            if(v3 < 0) {
                arr_v[1] = this.scroll(coordinatorLayout0, appBarLayout0, v3, -appBarLayout0.getDownNestedScrollRange(), 0);
            }
            if(v3 == 0) {
                this.addAccessibilityDelegateIfNeeded(coordinatorLayout0, appBarLayout0);
            }
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout0, View view0, Parcelable parcelable0) {
            this.onRestoreInstanceState(coordinatorLayout0, ((AppBarLayout)view0), parcelable0);
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, Parcelable parcelable0) {
            if(parcelable0 instanceof SavedState) {
                this.restoreScrollState(((SavedState)parcelable0), true);
                super.onRestoreInstanceState(coordinatorLayout0, appBarLayout0, this.savedState.getSuperState());
                return;
            }
            super.onRestoreInstanceState(coordinatorLayout0, appBarLayout0, parcelable0);
            this.savedState = null;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout0, View view0) {
            return this.onSaveInstanceState(coordinatorLayout0, ((AppBarLayout)view0));
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0) {
            Parcelable parcelable0 = super.onSaveInstanceState(coordinatorLayout0, appBarLayout0);
            Parcelable parcelable1 = this.saveScrollState(parcelable0, appBarLayout0);
            return parcelable1 == null ? parcelable0 : parcelable1;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, View view2, int v, int v1) {
            return this.onStartNestedScroll(coordinatorLayout0, ((AppBarLayout)view0), view1, view2, v, v1);
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, View view1, int v, int v1) {
            boolean z = (v & 2) != 0 && (appBarLayout0.isLiftOnScroll() || this.canScrollChildren(coordinatorLayout0, appBarLayout0, view0));
            if(z) {
                ValueAnimator valueAnimator0 = this.offsetAnimator;
                if(valueAnimator0 != null) {
                    valueAnimator0.cancel();
                }
            }
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = v1;
            return z;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v) {
            this.onStopNestedScroll(coordinatorLayout0, ((AppBarLayout)view0), view1, v);
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, int v) {
            if(this.lastStartedType == 0 || v == 1) {
                this.snapToChildIfNeeded(coordinatorLayout0, appBarLayout0);
                if(appBarLayout0.isLiftOnScroll()) {
                    appBarLayout0.setLiftedState(appBarLayout0.shouldLift(view0));
                }
            }
            this.lastNestedScrollingChildRef = new WeakReference(view0);
        }

        void restoreScrollState(SavedState appBarLayout$BaseBehavior$SavedState0, boolean z) {
            if(this.savedState != null && !z) {
                return;
            }
            this.savedState = appBarLayout$BaseBehavior$SavedState0;
        }

        SavedState saveScrollState(Parcelable parcelable0, AppBarLayout appBarLayout0) {
            int v = this.getTopAndBottomOffset();
            int v1 = appBarLayout0.getChildCount();
            boolean z = false;
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = appBarLayout0.getChildAt(v2);
                int v3 = view0.getBottom() + v;
                if(view0.getTop() + v <= 0 && v3 >= 0) {
                    if(parcelable0 == null) {
                        parcelable0 = AbsSavedState.EMPTY_STATE;
                    }
                    SavedState appBarLayout$BaseBehavior$SavedState0 = new SavedState(parcelable0);
                    appBarLayout$BaseBehavior$SavedState0.fullyExpanded = v == 0;
                    appBarLayout$BaseBehavior$SavedState0.fullyScrolled = !appBarLayout$BaseBehavior$SavedState0.fullyExpanded && -v >= appBarLayout0.getTotalScrollRange();
                    appBarLayout$BaseBehavior$SavedState0.firstVisibleChildIndex = v2;
                    if(v3 == ViewCompat.getMinimumHeight(view0) + appBarLayout0.getTopInset()) {
                        z = true;
                    }
                    appBarLayout$BaseBehavior$SavedState0.firstVisibleChildAtMinimumHeight = z;
                    appBarLayout$BaseBehavior$SavedState0.firstVisibleChildPercentageShown = ((float)v3) / ((float)view0.getHeight());
                    return appBarLayout$BaseBehavior$SavedState0;
                }
            }
            return null;
        }

        public void setDragCallback(BaseDragCallback appBarLayout$BaseBehavior$BaseDragCallback0) {
            this.onDragCallback = appBarLayout$BaseBehavior$BaseDragCallback0;
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2) {
            return this.setHeaderTopBottomOffset(coordinatorLayout0, ((AppBarLayout)view0), v, v1, v2);
        }

        int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v, int v1, int v2) {
            int v3 = this.getTopBottomOffsetForScrollingSibling();
            int v4 = 0;
            if(v1 == 0 || v3 < v1 || v3 > v2) {
                this.offsetDelta = 0;
            }
            else {
                int v5 = MathUtils.clamp(v, v1, v2);
                if(v3 != v5) {
                    int v6 = appBarLayout0.hasChildWithInterpolator() ? this.interpolateOffset(appBarLayout0, v5) : v5;
                    boolean z = this.setTopAndBottomOffset(v6);
                    this.offsetDelta = v5 - v6;
                    if(z) {
                        while(v4 < appBarLayout0.getChildCount()) {
                            LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)appBarLayout0.getChildAt(v4).getLayoutParams();
                            ChildScrollEffect appBarLayout$ChildScrollEffect0 = appBarLayout$LayoutParams0.getScrollEffect();
                            if(appBarLayout$ChildScrollEffect0 != null && (appBarLayout$LayoutParams0.getScrollFlags() & 1) != 0) {
                                appBarLayout$ChildScrollEffect0.onOffsetChanged(appBarLayout0, appBarLayout0.getChildAt(v4), ((float)this.getTopAndBottomOffset()));
                            }
                            ++v4;
                        }
                    }
                    if(!z && appBarLayout0.hasChildWithInterpolator()) {
                        coordinatorLayout0.dispatchDependentViewsChanged(appBarLayout0);
                    }
                    appBarLayout0.onOffsetChanged(this.getTopAndBottomOffset());
                    this.updateAppBarLayoutDrawableState(coordinatorLayout0, appBarLayout0, v5, (v5 >= v3 ? 1 : -1), false);
                    v4 = v3 - v5;
                }
            }
            this.addAccessibilityDelegateIfNeeded(coordinatorLayout0, appBarLayout0);
            return v4;
        }

        private boolean shouldJumpElevationState(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0) {
            List list0 = coordinatorLayout0.getDependents(appBarLayout0);
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                Behavior coordinatorLayout$Behavior0 = ((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams)((View)list0.get(v1)).getLayoutParams()).getBehavior();
                if(coordinatorLayout$Behavior0 instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior)coordinatorLayout$Behavior0).getOverlayTop() != 0;
                }
            }
            return false;
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0) {
            int v = appBarLayout0.getTopInset() + appBarLayout0.getPaddingTop();
            int v1 = this.getTopBottomOffsetForScrollingSibling() - v;
            int v2 = this.getChildIndexOnOffset(appBarLayout0, v1);
            if(v2 >= 0) {
                View view0 = appBarLayout0.getChildAt(v2);
                LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v3 = appBarLayout$LayoutParams0.getScrollFlags();
                if((v3 & 17) == 17) {
                    int v4 = -view0.getTop();
                    int v5 = -view0.getBottom();
                    if(v2 == 0 && ViewCompat.getFitsSystemWindows(appBarLayout0) && ViewCompat.getFitsSystemWindows(view0)) {
                        v4 -= appBarLayout0.getTopInset();
                    }
                    if(BaseBehavior.checkFlag(v3, 2)) {
                        v5 += ViewCompat.getMinimumHeight(view0);
                    }
                    else if(BaseBehavior.checkFlag(v3, 5)) {
                        int v6 = ViewCompat.getMinimumHeight(view0) + v5;
                        if(v1 < v6) {
                            v4 = v6;
                        }
                        else {
                            v5 = v6;
                        }
                    }
                    if(BaseBehavior.checkFlag(v3, 0x20)) {
                        v4 += appBarLayout$LayoutParams0.topMargin;
                        v5 -= appBarLayout$LayoutParams0.bottomMargin;
                    }
                    this.animateOffsetTo(coordinatorLayout0, appBarLayout0, MathUtils.clamp(this.calculateSnapOffset(v1, v5, v4) + v, -appBarLayout0.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v, int v1, boolean z) {
            View view0 = BaseBehavior.getAppBarChildOnOffset(appBarLayout0, v);
            boolean z1 = false;
            if(view0 != null) {
                int v2 = ((LayoutParams)view0.getLayoutParams()).getScrollFlags();
                if((v2 & 1) != 0) {
                    int v3 = ViewCompat.getMinimumHeight(view0);
                    if(v1 <= 0 || (v2 & 12) == 0) {
                        if((v2 & 2) != 0 && -v >= view0.getBottom() - v3 - appBarLayout0.getTopInset()) {
                            z1 = true;
                        }
                    }
                    else if(-v >= view0.getBottom() - v3 - appBarLayout0.getTopInset()) {
                        z1 = true;
                    }
                }
            }
            if(appBarLayout0.isLiftOnScroll()) {
                z1 = appBarLayout0.shouldLift(this.findFirstScrollingChild(coordinatorLayout0));
            }
            if(z || appBarLayout0.setLiftedState(z1) && this.shouldJumpElevationState(coordinatorLayout0, appBarLayout0)) {
                if(appBarLayout0.getBackground() != null) {
                    appBarLayout0.getBackground().jumpToCurrentState();
                }
                if(Build.VERSION.SDK_INT >= 23 && appBarLayout0.getForeground() != null) {
                    appBarLayout0.getForeground().jumpToCurrentState();
                }
                if(appBarLayout0.getStateListAnimator() != null) {
                    appBarLayout0.getStateListAnimator().jumpToCurrentState();
                }
            }
        }
    }

    public interface BaseOnOffsetChangedListener {
        void onOffsetChanged(AppBarLayout arg1, int arg2);
    }

    public static class com.google.android.material.appbar.AppBarLayout.Behavior extends BaseBehavior {
        public static abstract class DragCallback extends BaseDragCallback {
        }

        public com.google.android.material.appbar.AppBarLayout.Behavior() {
        }

        public com.google.android.material.appbar.AppBarLayout.Behavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
            return super.onInterceptTouchEvent(coordinatorLayout0, view0, motionEvent0);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v) {
            return super.onLayoutChild(coordinatorLayout0, appBarLayout0, v);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, int v, int v1, int v2, int v3) {
            return super.onMeasureChild(coordinatorLayout0, appBarLayout0, v, v1, v2, v3);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, int v, int v1, int[] arr_v, int v2) {
            super.onNestedPreScroll(coordinatorLayout0, appBarLayout0, view0, v, v1, arr_v, v2);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public void onNestedScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
            super.onNestedScroll(coordinatorLayout0, appBarLayout0, view0, v, v1, v2, v3, v4, arr_v);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, Parcelable parcelable0) {
            super.onRestoreInstanceState(coordinatorLayout0, appBarLayout0, parcelable0);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0) {
            return super.onSaveInstanceState(coordinatorLayout0, appBarLayout0);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, View view1, int v, int v1) {
            return super.onStartNestedScroll(coordinatorLayout0, appBarLayout0, view0, view1, v, v1);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, View view0, int v) {
            super.onStopNestedScroll(coordinatorLayout0, appBarLayout0, view0, v);
        }

        @Override  // com.google.android.material.appbar.HeaderBehavior
        public boolean onTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
            return super.onTouchEvent(coordinatorLayout0, view0, motionEvent0);
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$BaseBehavior
        public void setDragCallback(BaseDragCallback appBarLayout$BaseBehavior$BaseDragCallback0) {
            super.setDragCallback(appBarLayout$BaseBehavior$BaseDragCallback0);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public void setHorizontalOffsetEnabled(boolean z) {
            super.setHorizontalOffsetEnabled(z);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean setLeftAndRightOffset(int v) {
            return super.setLeftAndRightOffset(v);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean setTopAndBottomOffset(int v) {
            return super.setTopAndBottomOffset(v);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public void setVerticalOffsetEnabled(boolean z) {
            super.setVerticalOffsetEnabled(z);
        }
    }

    public static abstract class ChildScrollEffect {
        public abstract void onOffsetChanged(AppBarLayout arg1, View arg2, float arg3);
    }

    public static class CompressChildScrollEffect extends ChildScrollEffect {
        private static final float COMPRESS_DISTANCE_FACTOR = 0.3f;
        private final Rect ghostRect;
        private final Rect relativeRect;

        public CompressChildScrollEffect() {
            this.relativeRect = new Rect();
            this.ghostRect = new Rect();
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$ChildScrollEffect
        public void onOffsetChanged(AppBarLayout appBarLayout0, View view0, float f) {
            CompressChildScrollEffect.updateRelativeRect(this.relativeRect, appBarLayout0, view0);
            float f1 = ((float)this.relativeRect.top) - Math.abs(f);
            if(f1 <= 0.0f) {
                float f2 = MathUtils.clamp(Math.abs(f1 / ((float)this.relativeRect.height())), 0.0f, 1.0f);
                float f3 = -f1 - ((float)this.relativeRect.height()) * 0.3f * (1.0f - (1.0f - f2) * (1.0f - f2));
                view0.setTranslationY(f3);
                view0.getDrawingRect(this.ghostRect);
                this.ghostRect.offset(0, ((int)(-f3)));
                if(f3 >= ((float)this.ghostRect.height())) {
                    view0.setVisibility(4);
                }
                else {
                    view0.setVisibility(0);
                }
                ViewCompat.setClipBounds(view0, this.ghostRect);
                return;
            }
            ViewCompat.setClipBounds(view0, null);
            view0.setTranslationY(0.0f);
            view0.setVisibility(0);
        }

        private static void updateRelativeRect(Rect rect0, AppBarLayout appBarLayout0, View view0) {
            view0.getDrawingRect(rect0);
            appBarLayout0.offsetDescendantRectToMyCoords(view0, rect0);
            rect0.offset(0, -appBarLayout0.getTopInset());
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollEffect {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        static final int COLLAPSIBLE_FLAGS = 10;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_EFFECT_COMPRESS = 1;
        public static final int SCROLL_EFFECT_NONE = 0;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_NO_SCROLL = 0;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        public static final int SCROLL_FLAG_SNAP_MARGINS = 0x20;
        private ChildScrollEffect scrollEffect;
        int scrollFlags;
        Interpolator scrollInterpolator;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.scrollFlags = 1;
        }

        public LayoutParams(int v, int v1, float f) {
            super(v, v1, f);
            this.scrollFlags = 1;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.scrollFlags = 1;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.AppBarLayout_Layout);
            this.scrollFlags = typedArray0.getInt(styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            this.setScrollEffect(typedArray0.getInt(styleable.AppBarLayout_Layout_layout_scrollEffect, 0));
            if(typedArray0.hasValue(styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context0, typedArray0.getResourceId(styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            typedArray0.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.scrollFlags = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams linearLayout$LayoutParams0) {
            super(linearLayout$LayoutParams0);
            this.scrollFlags = 1;
        }

        public LayoutParams(LayoutParams appBarLayout$LayoutParams0) {
            super(appBarLayout$LayoutParams0);
            this.scrollFlags = appBarLayout$LayoutParams0.scrollFlags;
            this.scrollEffect = appBarLayout$LayoutParams0.scrollEffect;
            this.scrollInterpolator = appBarLayout$LayoutParams0.scrollInterpolator;
        }

        private ChildScrollEffect createScrollEffectFromInt(int v) {
            return v != 1 ? null : new CompressChildScrollEffect();
        }

        public ChildScrollEffect getScrollEffect() {
            return this.scrollEffect;
        }

        public int getScrollFlags() {
            return this.scrollFlags;
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        boolean isCollapsible() {
            return (this.scrollFlags & 1) == 1 && (this.scrollFlags & 10) != 0;
        }

        public void setScrollEffect(int v) {
            this.scrollEffect = this.createScrollEffectFromInt(v);
        }

        public void setScrollEffect(ChildScrollEffect appBarLayout$ChildScrollEffect0) {
            this.scrollEffect = appBarLayout$ChildScrollEffect0;
        }

        public void setScrollFlags(int v) {
            this.scrollFlags = v;
        }

        public void setScrollInterpolator(Interpolator interpolator0) {
            this.scrollInterpolator = interpolator0;
        }
    }

    public interface LiftOnScrollListener {
        void onUpdate(float arg1, int arg2);
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener {
        @Override  // com.google.android.material.appbar.AppBarLayout$BaseOnOffsetChangedListener
        void onOffsetChanged(AppBarLayout arg1, int arg2);
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ScrollingViewBehavior_Layout);
            this.setOverlayTop(typedArray0.getDimensionPixelSize(styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            typedArray0.recycle();
        }

        @Override  // com.google.android.material.appbar.HeaderScrollingViewBehavior
        View findFirstDependency(List list0) {
            return this.findFirstDependency(list0);
        }

        AppBarLayout findFirstDependency(List list0) {
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = (View)list0.get(v1);
                if(view0 instanceof AppBarLayout) {
                    return (AppBarLayout)view0;
                }
            }
            return null;
        }

        private static int getAppBarLayoutOffset(AppBarLayout appBarLayout0) {
            Behavior coordinatorLayout$Behavior0 = ((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams)appBarLayout0.getLayoutParams()).getBehavior();
            return coordinatorLayout$Behavior0 instanceof BaseBehavior ? ((BaseBehavior)coordinatorLayout$Behavior0).getTopBottomOffsetForScrollingSibling() : 0;
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override  // com.google.android.material.appbar.HeaderScrollingViewBehavior
        float getOverlapRatioForOffset(View view0) {
            if(view0 instanceof AppBarLayout) {
                int v = ((AppBarLayout)view0).getTotalScrollRange();
                int v1 = ((AppBarLayout)view0).getDownNestedPreScrollRange();
                int v2 = ScrollingViewBehavior.getAppBarLayoutOffset(((AppBarLayout)view0));
                if(v1 != 0 && v + v2 <= v1) {
                    return 0.0f;
                }
                int v3 = v - v1;
                return v3 == 0 ? 0.0f : ((float)v2) / ((float)v3) + 1.0f;
            }
            return 0.0f;
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.material.appbar.HeaderScrollingViewBehavior
        int getScrollRange(View view0) {
            return view0 instanceof AppBarLayout ? ((AppBarLayout)view0).getTotalScrollRange() : super.getScrollRange(view0);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            return view1 instanceof AppBarLayout;
        }

        private void offsetChildAsNeeded(View view0, View view1) {
            Behavior coordinatorLayout$Behavior0 = ((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams)view1.getLayoutParams()).getBehavior();
            if(coordinatorLayout$Behavior0 instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(view0, view1.getBottom() - view0.getTop() + ((BaseBehavior)coordinatorLayout$Behavior0).offsetDelta + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(view1));
            }
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            this.offsetChildAsNeeded(view0, view1);
            this.updateLiftedStateIfNeeded(view0, view1);
            return false;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            if(view1 instanceof AppBarLayout) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout0, null);
            }
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
            return super.onLayoutChild(coordinatorLayout0, view0, v);
        }

        @Override  // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2, int v3) {
            return super.onMeasureChild(coordinatorLayout0, view0, v, v1, v2, v3);
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout0, View view0, Rect rect0, boolean z) {
            AppBarLayout appBarLayout0 = this.findFirstDependency(coordinatorLayout0.getDependencies(view0));
            if(appBarLayout0 != null) {
                Rect rect1 = new Rect(rect0);
                rect1.offset(view0.getLeft(), view0.getTop());
                Rect rect2 = this.tempRect1;
                rect2.set(0, 0, coordinatorLayout0.getWidth(), coordinatorLayout0.getHeight());
                if(!rect2.contains(rect1)) {
                    appBarLayout0.setExpanded(false, !z);
                    return true;
                }
            }
            return false;
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public void setHorizontalOffsetEnabled(boolean z) {
            super.setHorizontalOffsetEnabled(z);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean setLeftAndRightOffset(int v) {
            return super.setLeftAndRightOffset(v);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public boolean setTopAndBottomOffset(int v) {
            return super.setTopAndBottomOffset(v);
        }

        @Override  // com.google.android.material.appbar.ViewOffsetBehavior
        public void setVerticalOffsetEnabled(boolean z) {
            super.setVerticalOffsetEnabled(z);
        }

        private void updateLiftedStateIfNeeded(View view0, View view1) {
            if(view1 instanceof AppBarLayout && ((AppBarLayout)view1).isLiftOnScroll()) {
                ((AppBarLayout)view1).setLiftedState(((AppBarLayout)view1).shouldLift(view0));
            }
        }
    }

    private static final int DEF_STYLE_RES = 0;
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE;
    private final float appBarElevation;
    private com.google.android.material.appbar.AppBarLayout.Behavior behavior;
    private int currentOffset;
    private int downPreScrollRange;
    private int downScrollRange;
    private final boolean hasLiftOnScrollColor;
    private boolean haveChildWithInterpolator;
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;
    private ValueAnimator liftOnScrollColorAnimator;
    private final long liftOnScrollColorDuration;
    private final TimeInterpolator liftOnScrollColorInterpolator;
    private ValueAnimator.AnimatorUpdateListener liftOnScrollColorUpdateListener;
    private final List liftOnScrollListeners;
    private WeakReference liftOnScrollTargetView;
    private int liftOnScrollTargetViewId;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List listeners;
    private int pendingAction;
    private Drawable statusBarForeground;
    private Integer statusBarForegroundOriginalColor;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    static {
        AppBarLayout.DEF_STYLE_RES = style.Widget_Design_AppBarLayout;
    }

    public AppBarLayout(Context context0) {
        this(context0, null);
    }

    public AppBarLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.appBarLayoutStyle);
    }

    public AppBarLayout(Context context0, AttributeSet attributeSet0, int v) {
        int v1 = AppBarLayout.DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, v1), attributeSet0, v);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        this.pendingAction = 0;
        this.liftOnScrollListeners = new ArrayList();
        Context context1 = this.getContext();
        boolean z = true;
        this.setOrientation(1);
        if(this.getOutlineProvider() == ViewOutlineProvider.BACKGROUND) {
            ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
        }
        ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, attributeSet0, v, v1);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.AppBarLayout, v, v1, new int[0]);
        ViewCompat.setBackground(this, typedArray0.getDrawable(styleable.AppBarLayout_android_background));
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context1, typedArray0, styleable.AppBarLayout_liftOnScrollColor);
        if(colorStateList0 == null) {
            z = false;
        }
        this.hasLiftOnScrollColor = z;
        ColorStateList colorStateList1 = DrawableUtils.getColorStateListOrNull(this.getBackground());
        if(colorStateList1 != null) {
            MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
            materialShapeDrawable0.setFillColor(colorStateList1);
            if(colorStateList0 == null) {
                this.initializeLiftOnScrollWithElevation(context1, materialShapeDrawable0);
            }
            else {
                this.initializeLiftOnScrollWithColor(materialShapeDrawable0, colorStateList1, colorStateList0);
            }
        }
        int v2 = this.getResources().getInteger(integer.app_bar_elevation_anim_duration);
        this.liftOnScrollColorDuration = (long)MotionUtils.resolveThemeDuration(context1, attr.motionDurationMedium2, v2);
        this.liftOnScrollColorInterpolator = MotionUtils.resolveThemeInterpolator(context1, attr.motionEasingStandardInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        if(typedArray0.hasValue(styleable.AppBarLayout_expanded)) {
            this.setExpanded(typedArray0.getBoolean(styleable.AppBarLayout_expanded, false), false, false);
        }
        if(typedArray0.hasValue(styleable.AppBarLayout_elevation)) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, ((float)typedArray0.getDimensionPixelSize(styleable.AppBarLayout_elevation, 0)));
        }
        if(Build.VERSION.SDK_INT >= 26) {
            if(typedArray0.hasValue(styleable.AppBarLayout_android_keyboardNavigationCluster)) {
                this.setKeyboardNavigationCluster(typedArray0.getBoolean(styleable.AppBarLayout_android_keyboardNavigationCluster, false));
            }
            if(typedArray0.hasValue(styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
                this.setTouchscreenBlocksFocus(typedArray0.getBoolean(styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
            }
        }
        this.appBarElevation = this.getResources().getDimension(dimen.design_appbar_elevation);
        this.liftOnScroll = typedArray0.getBoolean(styleable.AppBarLayout_liftOnScroll, false);
        this.liftOnScrollTargetViewId = typedArray0.getResourceId(styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
        this.setStatusBarForeground(typedArray0.getDrawable(styleable.AppBarLayout_statusBarForeground));
        typedArray0.recycle();
        ViewCompat.setOnApplyWindowInsetsListener(this, (/* 缺少LAMBDA参数 */, WindowInsetsCompat windowInsetsCompat0) -> {
            WindowInsetsCompat windowInsetsCompat1 = ViewCompat.getFitsSystemWindows(AppBarLayout.this) ? windowInsetsCompat0 : null;
            if(!ObjectsCompat.equals(AppBarLayout.this.lastInsets, windowInsetsCompat1)) {
                AppBarLayout.this.lastInsets = windowInsetsCompat1;
                AppBarLayout.this.updateWillNotDraw();
                AppBarLayout.this.requestLayout();
            }
            return windowInsetsCompat0;
        });

        class com.google.android.material.appbar.AppBarLayout.1 implements OnApplyWindowInsetsListener {
            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                return AppBarLayout.this.onWindowInsetChanged(windowInsetsCompat0);
            }
        }

    }

    public void addLiftOnScrollListener(LiftOnScrollListener appBarLayout$LiftOnScrollListener0) {
        this.liftOnScrollListeners.add(appBarLayout$LiftOnScrollListener0);
    }

    public void addOnOffsetChangedListener(BaseOnOffsetChangedListener appBarLayout$BaseOnOffsetChangedListener0) {
        if(this.listeners == null) {
            this.listeners = new ArrayList();
        }
        if(appBarLayout$BaseOnOffsetChangedListener0 != null && !this.listeners.contains(appBarLayout$BaseOnOffsetChangedListener0)) {
            this.listeners.add(appBarLayout$BaseOnOffsetChangedListener0);
        }
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener appBarLayout$OnOffsetChangedListener0) {
        this.addOnOffsetChangedListener(appBarLayout$OnOffsetChangedListener0);
    }

    @Override  // android.widget.LinearLayout
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    public void clearLiftOnScrollListener() {
        this.liftOnScrollListeners.clear();
    }

    private void clearLiftOnScrollTargetView() {
        WeakReference weakReference0 = this.liftOnScrollTargetView;
        if(weakReference0 != null) {
            weakReference0.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        if(this.shouldDrawStatusBarForeground()) {
            int v = canvas0.save();
            canvas0.translate(0.0f, ((float)(-this.currentOffset)));
            this.statusBarForeground.draw(canvas0);
            canvas0.restoreToCount(v);
        }
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] arr_v = this.getDrawableState();
        Drawable drawable0 = this.statusBarForeground;
        if(drawable0 != null && drawable0.isStateful() && drawable0.setState(arr_v)) {
            this.invalidateDrawable(drawable0);
        }
    }

    private Integer extractStatusBarForegroundColor() {
        Drawable drawable0 = this.statusBarForeground;
        if(drawable0 instanceof MaterialShapeDrawable) {
            return ((MaterialShapeDrawable)drawable0).getResolvedTintColor();
        }
        ColorStateList colorStateList0 = DrawableUtils.getColorStateListOrNull(drawable0);
        return colorStateList0 == null ? null : colorStateList0.getDefaultColor();
    }

    private View findLiftOnScrollTargetView(View view0) {
        if(this.liftOnScrollTargetView == null) {
            int v = this.liftOnScrollTargetViewId;
            if(v != -1) {
                View view1 = view0 == null ? null : view0.findViewById(v);
                if(view1 == null && this.getParent() instanceof ViewGroup) {
                    view1 = ((ViewGroup)this.getParent()).findViewById(this.liftOnScrollTargetViewId);
                }
                if(view1 != null) {
                    this.liftOnScrollTargetView = new WeakReference(view1);
                }
            }
        }
        return this.liftOnScrollTargetView == null ? null : ((View)this.liftOnScrollTargetView.get());
    }

    @Override  // android.widget.LinearLayout
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    @Override  // android.widget.LinearLayout
    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    @Override  // android.widget.LinearLayout
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.widget.LinearLayout
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // android.widget.LinearLayout
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.widget.LinearLayout
    protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(viewGroup$LayoutParams0 instanceof LinearLayout.LayoutParams) {
            return new LayoutParams(((LinearLayout.LayoutParams)viewGroup$LayoutParams0));
        }
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$AttachedBehavior
    public Behavior getBehavior() {
        com.google.android.material.appbar.AppBarLayout.Behavior appBarLayout$Behavior0 = new com.google.android.material.appbar.AppBarLayout.Behavior();
        this.behavior = appBarLayout$Behavior0;
        return appBarLayout$Behavior0;
    }

    int getDownNestedPreScrollRange() {
        int v6;
        int v = this.downPreScrollRange;
        if(v != -1) {
            return v;
        }
        int v1 = this.getChildCount() - 1;
        int v2 = 0;
        while(v1 >= 0) {
            View view0 = this.getChildAt(v1);
            if(view0.getVisibility() != 8) {
                LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v3 = view0.getMeasuredHeight();
                int v4 = appBarLayout$LayoutParams0.scrollFlags;
                if((v4 & 5) == 5) {
                    int v5 = appBarLayout$LayoutParams0.topMargin + appBarLayout$LayoutParams0.bottomMargin;
                    if((v4 & 8) == 0) {
                        v6 = (v4 & 2) == 0 ? v5 + v3 : v5 + (v3 - ViewCompat.getMinimumHeight(view0));
                    }
                    else {
                        v6 = v5 + ViewCompat.getMinimumHeight(view0);
                    }
                    if(v1 == 0 && ViewCompat.getFitsSystemWindows(view0)) {
                        v6 = Math.min(v6, v3 - this.getTopInset());
                    }
                    v2 += v6;
                }
                else {
                    if(v2 <= 0) {
                        goto label_22;
                    }
                    break;
                }
            }
        label_22:
            --v1;
        }
        int v7 = Math.max(0, v2);
        this.downPreScrollRange = v7;
        return v7;
    }

    int getDownNestedScrollRange() {
        int v = this.downScrollRange;
        if(v != -1) {
            return v;
        }
        int v1 = this.getChildCount();
        int v3 = 0;
        for(int v2 = 0; v2 < v1; ++v2) {
            View view0 = this.getChildAt(v2);
            if(view0.getVisibility() != 8) {
                LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v4 = view0.getMeasuredHeight() + (appBarLayout$LayoutParams0.topMargin + appBarLayout$LayoutParams0.bottomMargin);
                int v5 = appBarLayout$LayoutParams0.scrollFlags;
                if((v5 & 1) == 0) {
                    break;
                }
                v3 += v4;
                if((v5 & 2) != 0) {
                    v3 -= ViewCompat.getMinimumHeight(view0);
                    break;
                }
            }
        }
        int v6 = Math.max(0, v3);
        this.downScrollRange = v6;
        return v6;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    public MaterialShapeDrawable getMaterialShapeBackground() {
        Drawable drawable0 = this.getBackground();
        return drawable0 instanceof MaterialShapeDrawable ? ((MaterialShapeDrawable)drawable0) : null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int v = this.getTopInset();
        int v1 = ViewCompat.getMinimumHeight(this);
        if(v1 != 0) {
            return v1 * 2 + v;
        }
        int v2 = this.getChildCount();
        v1 = v2 < 1 ? 0 : ViewCompat.getMinimumHeight(this.getChildAt(v2 - 1));
        return v1 == 0 ? this.getHeight() / 3 : v1 * 2 + v;
    }

    int getPendingAction() {
        return this.pendingAction;
    }

    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    final int getTopInset() {
        return this.lastInsets == null ? 0 : this.lastInsets.getSystemWindowInsetTop();
    }

    public final int getTotalScrollRange() {
        int v = this.totalScrollRange;
        if(v != -1) {
            return v;
        }
        int v1 = this.getChildCount();
        int v3 = 0;
        for(int v2 = 0; v2 < v1; ++v2) {
            View view0 = this.getChildAt(v2);
            if(view0.getVisibility() != 8) {
                LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v4 = view0.getMeasuredHeight();
                int v5 = appBarLayout$LayoutParams0.scrollFlags;
                if((v5 & 1) == 0) {
                    break;
                }
                v3 += v4 + appBarLayout$LayoutParams0.topMargin + appBarLayout$LayoutParams0.bottomMargin;
                v3 = v2 != 0 || !ViewCompat.getFitsSystemWindows(view0) ? v3 + (v4 + appBarLayout$LayoutParams0.topMargin + appBarLayout$LayoutParams0.bottomMargin) : v3 - this.getTopInset();
                if((v5 & 2) != 0) {
                    v3 -= ViewCompat.getMinimumHeight(view0);
                    break;
                }
            }
        }
        int v6 = Math.max(0, v3);
        this.totalScrollRange = v6;
        return v6;
    }

    int getUpNestedPreScrollRange() {
        return this.getTotalScrollRange();
    }

    boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    private boolean hasCollapsibleChild() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((LayoutParams)this.getChildAt(v1).getLayoutParams()).isCollapsible()) {
                return true;
            }
        }
        return false;
    }

    boolean hasScrollableChildren() {
        return this.getTotalScrollRange() != 0;
    }

    private void initializeLiftOnScrollWithColor(MaterialShapeDrawable materialShapeDrawable0, ColorStateList colorStateList0, ColorStateList colorStateList1) {
        this.liftOnScrollColorUpdateListener = (ValueAnimator valueAnimator0) -> {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            int v = MaterialColors.layer(colorStateList0.getDefaultColor(), colorStateList1.getDefaultColor(), f);
            materialShapeDrawable0.setFillColor(ColorStateList.valueOf(v));
            if(this.statusBarForeground != null && (this.statusBarForegroundOriginalColor != null && this.statusBarForegroundOriginalColor.equals(MaterialColors.getColorOrNull(this.getContext(), attr.colorSurface)))) {
                DrawableCompat.setTint(this.statusBarForeground, v);
            }
            if(!this.liftOnScrollListeners.isEmpty()) {
                for(Object object0: this.liftOnScrollListeners) {
                    LiftOnScrollListener appBarLayout$LiftOnScrollListener0 = (LiftOnScrollListener)object0;
                    if(materialShapeDrawable0.getFillColor() != null) {
                        appBarLayout$LiftOnScrollListener0.onUpdate(0.0f, v);
                    }
                }
            }
        };
        ViewCompat.setBackground(this, materialShapeDrawable0);
    }

    private void initializeLiftOnScrollWithElevation(Context context0, MaterialShapeDrawable materialShapeDrawable0) {
        materialShapeDrawable0.initializeElevationOverlay(context0);
        this.liftOnScrollColorUpdateListener = (ValueAnimator valueAnimator0) -> {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            materialShapeDrawable0.setElevation(f);
            Drawable drawable0 = this.statusBarForeground;
            if(drawable0 instanceof MaterialShapeDrawable) {
                ((MaterialShapeDrawable)drawable0).setElevation(f);
            }
            for(Object object0: this.liftOnScrollListeners) {
                ((LiftOnScrollListener)object0).onUpdate(f, materialShapeDrawable0.getResolvedTintColor());
            }
        };
        ViewCompat.setBackground(this, materialShapeDrawable0);
    }

    private void invalidateScrollRanges() {
        SavedState appBarLayout$BaseBehavior$SavedState0 = this.behavior == null || this.totalScrollRange == -1 || this.pendingAction != 0 ? null : this.behavior.saveScrollState(AbsSavedState.EMPTY_STATE, this);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if(appBarLayout$BaseBehavior$SavedState0 != null) {
            this.behavior.restoreScrollState(appBarLayout$BaseBehavior$SavedState0, false);
        }
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    private boolean isLiftOnScrollCompatibleBackground() {
        return this.getBackground() instanceof MaterialShapeDrawable;
    }

    public boolean isLifted() {
        return this.lifted;
    }

    // 检测为 Lambda 实现
    void lambda$initializeLiftOnScrollWithColor$0$com-google-android-material-appbar-AppBarLayout(ColorStateList colorStateList0, ColorStateList colorStateList1, MaterialShapeDrawable materialShapeDrawable0, Integer integer0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$initializeLiftOnScrollWithElevation$1$com-google-android-material-appbar-AppBarLayout(MaterialShapeDrawable materialShapeDrawable0, ValueAnimator valueAnimator0) [...]

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override  // android.view.ViewGroup
    protected int[] onCreateDrawableState(int v) {
        if(this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] arr_v = this.tmpStatesArray;
        int[] arr_v1 = super.onCreateDrawableState(v + arr_v.length);
        arr_v[0] = this.liftable ? attr.state_liftable : -attr.state_liftable;
        arr_v[1] = !this.liftable || !this.lifted ? -attr.state_lifted : attr.state_lifted;
        arr_v[2] = this.liftable ? attr.state_collapsible : -attr.state_collapsible;
        arr_v[3] = !this.liftable || !this.lifted ? -attr.state_collapsed : attr.state_collapsed;
        return AppBarLayout.mergeDrawableStates(arr_v1, arr_v);
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.clearLiftOnScrollTargetView();
    }

    @Override  // android.widget.LinearLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        boolean z1 = true;
        if(ViewCompat.getFitsSystemWindows(this) && this.shouldOffsetFirstChild()) {
            int v4 = this.getTopInset();
            for(int v5 = this.getChildCount() - 1; v5 >= 0; --v5) {
                ViewCompat.offsetTopAndBottom(this.getChildAt(v5), v4);
            }
        }
        this.invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int v6 = this.getChildCount();
        for(int v7 = 0; v7 < v6; ++v7) {
            if(((LayoutParams)this.getChildAt(v7).getLayoutParams()).getScrollInterpolator() != null) {
                this.haveChildWithInterpolator = true;
                break;
            }
        }
        Drawable drawable0 = this.statusBarForeground;
        if(drawable0 != null) {
            drawable0.setBounds(0, 0, this.getWidth(), this.getTopInset());
        }
        if(!this.liftableOverride) {
            if(!this.liftOnScroll && !this.hasCollapsibleChild()) {
                z1 = false;
            }
            this.setLiftableState(z1);
        }
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        int v2 = View.MeasureSpec.getMode(v1);
        if(v2 != 0x40000000 && ViewCompat.getFitsSystemWindows(this) && this.shouldOffsetFirstChild()) {
            int v3 = this.getMeasuredHeight();
            switch(v2) {
                case 0x80000000: {
                    v3 = MathUtils.clamp(this.getMeasuredHeight() + this.getTopInset(), 0, View.MeasureSpec.getSize(v1));
                    break;
                }
                case 0: {
                    v3 += this.getTopInset();
                }
            }
            this.setMeasuredDimension(this.getMeasuredWidth(), v3);
        }
        this.invalidateScrollRanges();
    }

    void onOffsetChanged(int v) {
        this.currentOffset = v;
        if(!this.willNotDraw()) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        List list0 = this.listeners;
        if(list0 != null) {
            int v1 = list0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                BaseOnOffsetChangedListener appBarLayout$BaseOnOffsetChangedListener0 = (BaseOnOffsetChangedListener)this.listeners.get(v2);
                if(appBarLayout$BaseOnOffsetChangedListener0 != null) {
                    appBarLayout$BaseOnOffsetChangedListener0.onOffsetChanged(this, v);
                }
            }
        }
    }

    // 检测为 Lambda 实现
    WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat windowInsetsCompat0) [...]

    public boolean removeLiftOnScrollListener(LiftOnScrollListener appBarLayout$LiftOnScrollListener0) {
        return this.liftOnScrollListeners.remove(appBarLayout$LiftOnScrollListener0);
    }

    public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener appBarLayout$BaseOnOffsetChangedListener0) {
        List list0 = this.listeners;
        if(list0 != null && appBarLayout$BaseOnOffsetChangedListener0 != null) {
            list0.remove(appBarLayout$BaseOnOffsetChangedListener0);
        }
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener appBarLayout$OnOffsetChangedListener0) {
        this.removeOnOffsetChangedListener(appBarLayout$OnOffsetChangedListener0);
    }

    void resetPendingAction() {
        this.pendingAction = 0;
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    // 去混淆评级： 低(20)
    private void setExpanded(boolean z, boolean z1, boolean z2) {
        this.pendingAction = (z ? 1 : 2) | (z1 ? 4 : 0) | (z2 ? 8 : 0);
        this.requestLayout();
    }

    public void setExpanded(boolean z) {
        this.setExpanded(z, ViewCompat.isLaidOut(this));
    }

    public void setExpanded(boolean z, boolean z1) {
        this.setExpanded(z, z1, true);
    }

    public void setLiftOnScroll(boolean z) {
        this.liftOnScroll = z;
    }

    public void setLiftOnScrollTargetView(View view0) {
        this.liftOnScrollTargetViewId = -1;
        if(view0 == null) {
            this.clearLiftOnScrollTargetView();
            return;
        }
        this.liftOnScrollTargetView = new WeakReference(view0);
    }

    public void setLiftOnScrollTargetViewId(int v) {
        this.liftOnScrollTargetViewId = v;
        this.clearLiftOnScrollTargetView();
    }

    public boolean setLiftable(boolean z) {
        this.liftableOverride = true;
        return this.setLiftableState(z);
    }

    public void setLiftableOverrideEnabled(boolean z) {
        this.liftableOverride = z;
    }

    private boolean setLiftableState(boolean z) {
        if(this.liftable != z) {
            this.liftable = z;
            this.refreshDrawableState();
            return true;
        }
        return false;
    }

    public boolean setLifted(boolean z) {
        return this.setLiftedState(z, true);
    }

    boolean setLiftedState(boolean z) {
        return this.setLiftedState(z, !this.liftableOverride);
    }

    boolean setLiftedState(boolean z, boolean z1) {
        if(z1 && this.lifted != z) {
            float f = 0.0f;
            this.lifted = z;
            this.refreshDrawableState();
            if(this.isLiftOnScrollCompatibleBackground()) {
                if(this.hasLiftOnScrollColor) {
                    if(z) {
                        f = 1.0f;
                    }
                    this.startLiftOnScrollColorAnimation((z ? 0.0f : 1.0f), f);
                    return true;
                }
                if(this.liftOnScroll) {
                    float f1 = z ? 0.0f : this.appBarElevation;
                    if(z) {
                        f = this.appBarElevation;
                    }
                    this.startLiftOnScrollColorAnimation(f1, f);
                }
            }
            return true;
        }
        return false;
    }

    @Override  // android.widget.LinearLayout
    public void setOrientation(int v) {
        if(v != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(1);
    }

    public void setStatusBarForeground(Drawable drawable0) {
        Drawable drawable1 = null;
        Drawable drawable2 = this.statusBarForeground;
        if(drawable2 != drawable0) {
            if(drawable2 != null) {
                drawable2.setCallback(null);
            }
            if(drawable0 != null) {
                drawable1 = drawable0.mutate();
            }
            this.statusBarForeground = drawable1;
            this.statusBarForegroundOriginalColor = this.extractStatusBarForegroundColor();
            Drawable drawable3 = this.statusBarForeground;
            if(drawable3 != null) {
                if(drawable3.isStateful()) {
                    this.statusBarForeground.setState(this.getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.statusBarForeground, ViewCompat.getLayoutDirection(this));
                this.statusBarForeground.setVisible(this.getVisibility() == 0, false);
                this.statusBarForeground.setCallback(this);
            }
            this.updateWillNotDraw();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarForegroundColor(int v) {
        this.setStatusBarForeground(new ColorDrawable(v));
    }

    public void setStatusBarForegroundResource(int v) {
        this.setStatusBarForeground(AppCompatResources.getDrawable(this.getContext(), v));
    }

    @Deprecated
    public void setTargetElevation(float f) {
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, f);
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        Drawable drawable0 = this.statusBarForeground;
        if(drawable0 != null) {
            drawable0.setVisible(v == 0, false);
        }
    }

    private boolean shouldDrawStatusBarForeground() {
        return this.statusBarForeground != null && this.getTopInset() > 0;
    }

    boolean shouldLift(View view0) {
        View view1 = this.findLiftOnScrollTargetView(view0);
        if(view1 != null) {
            view0 = view1;
        }
        return view0 != null && (view0.canScrollVertically(-1) || view0.getScrollY() > 0);
    }

    private boolean shouldOffsetFirstChild() {
        if(this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            return view0.getVisibility() != 8 && !ViewCompat.getFitsSystemWindows(view0);
        }
        return false;
    }

    private void startLiftOnScrollColorAnimation(float f, float f1) {
        ValueAnimator valueAnimator0 = this.liftOnScrollColorAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.cancel();
        }
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{f, f1});
        this.liftOnScrollColorAnimator = valueAnimator1;
        valueAnimator1.setDuration(this.liftOnScrollColorDuration);
        this.liftOnScrollColorAnimator.setInterpolator(this.liftOnScrollColorInterpolator);
        ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0 = this.liftOnScrollColorUpdateListener;
        if(valueAnimator$AnimatorUpdateListener0 != null) {
            this.liftOnScrollColorAnimator.addUpdateListener(valueAnimator$AnimatorUpdateListener0);
        }
        this.liftOnScrollColorAnimator.start();
    }

    private void updateWillNotDraw() {
        this.setWillNotDraw(!this.shouldDrawStatusBarForeground());
    }

    @Override  // android.view.View
    protected boolean verifyDrawable(Drawable drawable0) {
        return super.verifyDrawable(drawable0) || drawable0 == this.statusBarForeground;
    }
}

