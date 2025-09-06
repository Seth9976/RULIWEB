package com.google.android.material.floatingactionbutton;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.animator;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements AttachedBehavior {
    class ChangeSizeStrategy extends BaseMotionStrategy {
        private final boolean extending;
        private final Size size;

        ChangeSizeStrategy(AnimatorTracker animatorTracker0, Size extendedFloatingActionButton$Size0, boolean z) {
            super(extendedFloatingActionButton0, animatorTracker0);
            this.size = extendedFloatingActionButton$Size0;
            this.extending = z;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public AnimatorSet createAnimator() {
            MotionSpec motionSpec0 = this.getCurrentMotionSpec();
            if(motionSpec0.hasPropertyValues("width")) {
                PropertyValuesHolder[] arr_propertyValuesHolder = motionSpec0.getPropertyValues("width");
                arr_propertyValuesHolder[0].setFloatValues(new float[]{((float)ExtendedFloatingActionButton.this.getWidth()), ((float)this.size.getWidth())});
                motionSpec0.setPropertyValues("width", arr_propertyValuesHolder);
            }
            if(motionSpec0.hasPropertyValues("height")) {
                PropertyValuesHolder[] arr_propertyValuesHolder1 = motionSpec0.getPropertyValues("height");
                arr_propertyValuesHolder1[0].setFloatValues(new float[]{((float)ExtendedFloatingActionButton.this.getHeight()), ((float)this.size.getHeight())});
                motionSpec0.setPropertyValues("height", arr_propertyValuesHolder1);
            }
            if(motionSpec0.hasPropertyValues("paddingStart")) {
                PropertyValuesHolder[] arr_propertyValuesHolder2 = motionSpec0.getPropertyValues("paddingStart");
                arr_propertyValuesHolder2[0].setFloatValues(new float[]{((float)ViewCompat.getPaddingStart(ExtendedFloatingActionButton.this)), ((float)this.size.getPaddingStart())});
                motionSpec0.setPropertyValues("paddingStart", arr_propertyValuesHolder2);
            }
            if(motionSpec0.hasPropertyValues("paddingEnd")) {
                PropertyValuesHolder[] arr_propertyValuesHolder3 = motionSpec0.getPropertyValues("paddingEnd");
                arr_propertyValuesHolder3[0].setFloatValues(new float[]{((float)ViewCompat.getPaddingEnd(ExtendedFloatingActionButton.this)), ((float)this.size.getPaddingEnd())});
                motionSpec0.setPropertyValues("paddingEnd", arr_propertyValuesHolder3);
            }
            if(motionSpec0.hasPropertyValues("labelOpacity")) {
                PropertyValuesHolder[] arr_propertyValuesHolder4 = motionSpec0.getPropertyValues("labelOpacity");
                float f = 0.0f;
                float f1 = this.extending ? 0.0f : 1.0f;
                if(this.extending) {
                    f = 1.0f;
                }
                arr_propertyValuesHolder4[0].setFloatValues(new float[]{f1, f});
                motionSpec0.setPropertyValues("labelOpacity", arr_propertyValuesHolder4);
            }
            return super.createAnimator(motionSpec0);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return this.extending ? animator.mtrl_extended_fab_change_size_expand_motion_spec : animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.isTransforming = false;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = ExtendedFloatingActionButton.this.getLayoutParams();
            if(viewGroup$LayoutParams0 == null) {
                return;
            }
            viewGroup$LayoutParams0.width = this.size.getLayoutParams().width;
            viewGroup$LayoutParams0.height = this.size.getLayoutParams().height;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationStart(Animator animator0) {
            super.onAnimationStart(animator0);
            ExtendedFloatingActionButton.this.isExtended = this.extending;
            ExtendedFloatingActionButton.this.isTransforming = true;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
            if(extendedFloatingActionButton$OnChangedCallback0 == null) {
                return;
            }
            if(this.extending) {
            }
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.this.isExtended = this.extending;
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = ExtendedFloatingActionButton.this.getLayoutParams();
            if(viewGroup$LayoutParams0 == null) {
                return;
            }
            if(!this.extending) {
                ExtendedFloatingActionButton.this.originalWidth = viewGroup$LayoutParams0.width;
                ExtendedFloatingActionButton.this.originalHeight = viewGroup$LayoutParams0.height;
            }
            viewGroup$LayoutParams0.width = this.size.getLayoutParams().width;
            viewGroup$LayoutParams0.height = this.size.getLayoutParams().height;
            int v = this.size.getPaddingStart();
            int v1 = ExtendedFloatingActionButton.this.getPaddingTop();
            int v2 = this.size.getPaddingEnd();
            int v3 = ExtendedFloatingActionButton.this.getPaddingBottom();
            ViewCompat.setPaddingRelative(ExtendedFloatingActionButton.this, v, v1, v2, v3);
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return this.extending == ExtendedFloatingActionButton.this.isExtended || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }
    }

    public static class ExtendedFloatingActionButtonBehavior extends Behavior {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedCallback internalAutoHideCallback;
        private OnChangedCallback internalAutoShrinkCallback;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = typedArray0.getBoolean(styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = typedArray0.getBoolean(styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArray0.recycle();
        }

        // 去混淆评级： 低(20)
        protected void extendOrShow(ExtendedFloatingActionButton extendedFloatingActionButton0) {
            extendedFloatingActionButton0.performMotion((this.autoShrinkEnabled ? 3 : 0), (this.autoShrinkEnabled ? this.internalAutoShrinkCallback : this.internalAutoHideCallback));
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout0, View view0, Rect rect0) {
            return this.getInsetDodgeRect(coordinatorLayout0, ((ExtendedFloatingActionButton)view0), rect0);
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout0, ExtendedFloatingActionButton extendedFloatingActionButton0, Rect rect0) {
            return super.getInsetDodgeRect(coordinatorLayout0, extendedFloatingActionButton0, rect0);
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        private static boolean isBottomSheet(View view0) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
            return viewGroup$LayoutParams0 instanceof LayoutParams ? ((LayoutParams)viewGroup$LayoutParams0).getBehavior() instanceof BottomSheetBehavior : false;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onAttachedToLayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
            if(coordinatorLayout$LayoutParams0.dodgeInsetEdges == 0) {
                coordinatorLayout$LayoutParams0.dodgeInsetEdges = 80;
            }
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            return this.onDependentViewChanged(coordinatorLayout0, ((ExtendedFloatingActionButton)view0), view1);
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, ExtendedFloatingActionButton extendedFloatingActionButton0, View view0) {
            if(view0 instanceof AppBarLayout) {
                this.updateFabVisibilityForAppBarLayout(coordinatorLayout0, ((AppBarLayout)view0), extendedFloatingActionButton0);
                return false;
            }
            if(ExtendedFloatingActionButtonBehavior.isBottomSheet(view0)) {
                this.updateFabVisibilityForBottomSheet(view0, extendedFloatingActionButton0);
            }
            return false;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
            return this.onLayoutChild(coordinatorLayout0, ((ExtendedFloatingActionButton)view0), v);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, ExtendedFloatingActionButton extendedFloatingActionButton0, int v) {
            List list0 = coordinatorLayout0.getDependencies(extendedFloatingActionButton0);
            int v1 = list0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = (View)list0.get(v2);
                if(!(view0 instanceof AppBarLayout)) {
                    if(!ExtendedFloatingActionButtonBehavior.isBottomSheet(view0) || !this.updateFabVisibilityForBottomSheet(view0, extendedFloatingActionButton0)) {
                        continue;
                    }
                    break;
                }
                else if(this.updateFabVisibilityForAppBarLayout(coordinatorLayout0, ((AppBarLayout)view0), extendedFloatingActionButton0)) {
                    break;
                }
            }
            coordinatorLayout0.onLayoutChild(extendedFloatingActionButton0, v);
            return true;
        }

        public void setAutoHideEnabled(boolean z) {
            this.autoHideEnabled = z;
        }

        public void setAutoShrinkEnabled(boolean z) {
            this.autoShrinkEnabled = z;
        }

        void setInternalAutoHideCallback(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
            this.internalAutoHideCallback = extendedFloatingActionButton$OnChangedCallback0;
        }

        void setInternalAutoShrinkCallback(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
            this.internalAutoShrinkCallback = extendedFloatingActionButton$OnChangedCallback0;
        }

        private boolean shouldUpdateVisibility(View view0, ExtendedFloatingActionButton extendedFloatingActionButton0) {
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)extendedFloatingActionButton0.getLayoutParams();
            return this.autoHideEnabled || this.autoShrinkEnabled ? coordinatorLayout$LayoutParams0.getAnchorId() == view0.getId() : false;
        }

        // 去混淆评级： 低(20)
        protected void shrinkOrHide(ExtendedFloatingActionButton extendedFloatingActionButton0) {
            extendedFloatingActionButton0.performMotion((this.autoShrinkEnabled ? 2 : 1), (this.autoShrinkEnabled ? this.internalAutoShrinkCallback : this.internalAutoHideCallback));
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, ExtendedFloatingActionButton extendedFloatingActionButton0) {
            if(!this.shouldUpdateVisibility(appBarLayout0, extendedFloatingActionButton0)) {
                return false;
            }
            if(this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect0 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout0, appBarLayout0, rect0);
            if(rect0.bottom <= appBarLayout0.getMinimumHeightForVisibleOverlappingContent()) {
                this.shrinkOrHide(extendedFloatingActionButton0);
                return true;
            }
            this.extendOrShow(extendedFloatingActionButton0);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view0, ExtendedFloatingActionButton extendedFloatingActionButton0) {
            if(!this.shouldUpdateVisibility(view0, extendedFloatingActionButton0)) {
                return false;
            }
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)extendedFloatingActionButton0.getLayoutParams();
            if(view0.getTop() < extendedFloatingActionButton0.getHeight() / 2 + coordinatorLayout$LayoutParams0.topMargin) {
                this.shrinkOrHide(extendedFloatingActionButton0);
                return true;
            }
            this.extendOrShow(extendedFloatingActionButton0);
            return true;
        }
    }

    class HideStrategy extends BaseMotionStrategy {
        private boolean isCancelled;

        public HideStrategy(AnimatorTracker animatorTracker0) {
            super(extendedFloatingActionButton0, animatorTracker0);
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationCancel() {
            super.onAnimationCancel();
            this.isCancelled = true;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.animState = 0;
            if(!this.isCancelled) {
                ExtendedFloatingActionButton.this.setVisibility(8);
            }
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationStart(Animator animator0) {
            super.onAnimationStart(animator0);
            this.isCancelled = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 1;
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeHidden();
        }
    }

    public static abstract class OnChangedCallback {
        public void onExtended(ExtendedFloatingActionButton extendedFloatingActionButton0) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFloatingActionButton0) {
        }

        public void onShown(ExtendedFloatingActionButton extendedFloatingActionButton0) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFloatingActionButton0) {
        }
    }

    class ShowStrategy extends BaseMotionStrategy {
        public ShowStrategy(AnimatorTracker animatorTracker0) {
            super(extendedFloatingActionButton0, animatorTracker0);
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.this.animState = 0;
        }

        @Override  // com.google.android.material.floatingactionbutton.BaseMotionStrategy
        public void onAnimationStart(Animator animator0) {
            super.onAnimationStart(animator0);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.animState = 2;
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeShown();
        }
    }

    interface Size {
        int getHeight();

        ViewGroup.LayoutParams getLayoutParams();

        int getPaddingEnd();

        int getPaddingStart();

        int getWidth();
    }

    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final int DEF_STYLE_RES = 0;
    private static final int EXTEND = 3;
    private static final int EXTEND_STRATEGY_AUTO = 0;
    private static final int EXTEND_STRATEGY_MATCH_PARENT = 2;
    private static final int EXTEND_STRATEGY_WRAP_CONTENT = 1;
    static final Property HEIGHT = null;
    private static final int HIDE = 1;
    static final Property PADDING_END = null;
    static final Property PADDING_START = null;
    private static final int SHOW = 0;
    private static final int SHRINK = 2;
    static final Property WIDTH;
    private int animState;
    private boolean animateShowBeforeLayout;
    private final Behavior behavior;
    private final AnimatorTracker changeVisibilityTracker;
    private final int collapsedSize;
    private final MotionStrategy extendStrategy;
    private final int extendStrategyType;
    private int extendedPaddingEnd;
    private int extendedPaddingStart;
    private final MotionStrategy hideStrategy;
    private boolean isExtended;
    private boolean isTransforming;
    private int originalHeight;
    protected ColorStateList originalTextCsl;
    private int originalWidth;
    private final MotionStrategy showStrategy;
    private final MotionStrategy shrinkStrategy;

    static {
        ExtendedFloatingActionButton.DEF_STYLE_RES = style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
        ExtendedFloatingActionButton.WIDTH = new Property(Float.class, "width") {
            public Float get(View view0) {
                return (float)view0.getLayoutParams().width;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, Float float0) {
                view0.getLayoutParams().width = float0.intValue();
                view0.requestLayout();
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((Float)object1));
            }
        };
        ExtendedFloatingActionButton.HEIGHT = new Property(Float.class, "height") {
            public Float get(View view0) {
                return (float)view0.getLayoutParams().height;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, Float float0) {
                view0.getLayoutParams().height = float0.intValue();
                view0.requestLayout();
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((Float)object1));
            }
        };
        ExtendedFloatingActionButton.PADDING_START = new Property(Float.class, "paddingStart") {
            public Float get(View view0) {
                return (float)ViewCompat.getPaddingStart(view0);
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, Float float0) {
                ViewCompat.setPaddingRelative(view0, float0.intValue(), view0.getPaddingTop(), ViewCompat.getPaddingEnd(view0), view0.getPaddingBottom());
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((Float)object1));
            }
        };
        ExtendedFloatingActionButton.PADDING_END = new Property(Float.class, "paddingEnd") {
            public Float get(View view0) {
                return (float)ViewCompat.getPaddingEnd(view0);
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, Float float0) {
                ViewCompat.setPaddingRelative(view0, ViewCompat.getPaddingStart(view0), view0.getPaddingTop(), float0.intValue(), view0.getPaddingBottom());
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((Float)object1));
            }
        };
    }

    public ExtendedFloatingActionButton(Context context0) {
        this(context0, null);
    }

    public ExtendedFloatingActionButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.extendedFloatingActionButtonStyle);
    }

    public ExtendedFloatingActionButton(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, ExtendedFloatingActionButton.DEF_STYLE_RES), attributeSet0, v);
        this.animState = 0;
        AnimatorTracker animatorTracker0 = new AnimatorTracker();
        this.changeVisibilityTracker = animatorTracker0;
        ShowStrategy extendedFloatingActionButton$ShowStrategy0 = new ShowStrategy(this, animatorTracker0);
        this.showStrategy = extendedFloatingActionButton$ShowStrategy0;
        HideStrategy extendedFloatingActionButton$HideStrategy0 = new HideStrategy(this, animatorTracker0);
        this.hideStrategy = extendedFloatingActionButton$HideStrategy0;
        this.isExtended = true;
        this.isTransforming = false;
        this.animateShowBeforeLayout = false;
        Context context1 = this.getContext();
        this.behavior = new ExtendedFloatingActionButtonBehavior(context1, attributeSet0);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.ExtendedFloatingActionButton, v, ExtendedFloatingActionButton.DEF_STYLE_RES, new int[0]);
        MotionSpec motionSpec0 = MotionSpec.createFromAttribute(context1, typedArray0, styleable.ExtendedFloatingActionButton_showMotionSpec);
        MotionSpec motionSpec1 = MotionSpec.createFromAttribute(context1, typedArray0, styleable.ExtendedFloatingActionButton_hideMotionSpec);
        MotionSpec motionSpec2 = MotionSpec.createFromAttribute(context1, typedArray0, styleable.ExtendedFloatingActionButton_extendMotionSpec);
        MotionSpec motionSpec3 = MotionSpec.createFromAttribute(context1, typedArray0, styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        this.collapsedSize = typedArray0.getDimensionPixelSize(styleable.ExtendedFloatingActionButton_collapsedSize, -1);
        int v1 = typedArray0.getInt(styleable.ExtendedFloatingActionButton_extendStrategy, 1);
        this.extendStrategyType = v1;
        this.extendedPaddingStart = ViewCompat.getPaddingStart(this);
        this.extendedPaddingEnd = ViewCompat.getPaddingEnd(this);
        AnimatorTracker animatorTracker1 = new AnimatorTracker();
        ChangeSizeStrategy extendedFloatingActionButton$ChangeSizeStrategy0 = new ChangeSizeStrategy(this, animatorTracker1, this.getSizeFromExtendStrategyType(v1), true);
        this.extendStrategy = extendedFloatingActionButton$ChangeSizeStrategy0;
        ChangeSizeStrategy extendedFloatingActionButton$ChangeSizeStrategy1 = new ChangeSizeStrategy(this, animatorTracker1, new Size() {
            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getHeight() {
                return ExtendedFloatingActionButton.this.getCollapsedSize();
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public ViewGroup.LayoutParams getLayoutParams() {
                return new ViewGroup.LayoutParams(this.getWidth(), this.getHeight());
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.getCollapsedPadding();
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.getCollapsedPadding();
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getWidth() {
                return ExtendedFloatingActionButton.this.getCollapsedSize();
            }
        }, false);
        this.shrinkStrategy = extendedFloatingActionButton$ChangeSizeStrategy1;
        extendedFloatingActionButton$ShowStrategy0.setMotionSpec(motionSpec0);
        extendedFloatingActionButton$HideStrategy0.setMotionSpec(motionSpec1);
        extendedFloatingActionButton$ChangeSizeStrategy0.setMotionSpec(motionSpec2);
        extendedFloatingActionButton$ChangeSizeStrategy1.setMotionSpec(motionSpec3);
        typedArray0.recycle();
        this.setShapeAppearanceModel(ShapeAppearanceModel.builder(context1, attributeSet0, v, ExtendedFloatingActionButton.DEF_STYLE_RES, ShapeAppearanceModel.PILL).build());
        this.saveOriginalTextCsl();
    }

    public void addOnExtendAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.extendStrategy.addAnimationListener(animator$AnimatorListener0);
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.hideStrategy.addAnimationListener(animator$AnimatorListener0);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.showStrategy.addAnimationListener(animator$AnimatorListener0);
    }

    public void addOnShrinkAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.shrinkStrategy.addAnimationListener(animator$AnimatorListener0);
    }

    public void extend() {
        this.performMotion(3, null);
    }

    public void extend(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        this.performMotion(3, extendedFloatingActionButton$OnChangedCallback0);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$AttachedBehavior
    public Behavior getBehavior() {
        return this.behavior;
    }

    int getCollapsedPadding() {
        return (this.getCollapsedSize() - this.getIconSize()) / 2;
    }

    int getCollapsedSize() {
        return this.collapsedSize >= 0 ? this.collapsedSize : Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2 + this.getIconSize();
    }

    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.getMotionSpec();
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.getMotionSpec();
    }

    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.getMotionSpec();
    }

    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.getMotionSpec();
    }

    private Size getSizeFromExtendStrategyType(int v) {
        Size extendedFloatingActionButton$Size0 = new Size() {
            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getHeight() {
                return ExtendedFloatingActionButton.this.getMeasuredHeight();
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public ViewGroup.LayoutParams getLayoutParams() {
                return new ViewGroup.LayoutParams(-2, -2);
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getWidth() {
                return ExtendedFloatingActionButton.this.getMeasuredWidth() - ExtendedFloatingActionButton.this.getCollapsedPadding() * 2 + ExtendedFloatingActionButton.this.extendedPaddingStart + ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }
        };
        Size extendedFloatingActionButton$Size1 = new Size() {
            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getHeight() {
                switch(ExtendedFloatingActionButton.this.originalHeight) {
                    case -1: {
                        if(!(ExtendedFloatingActionButton.this.getParent() instanceof View)) {
                            return extendedFloatingActionButton$Size0.getHeight();
                        }
                        View view0 = (View)ExtendedFloatingActionButton.this.getParent();
                        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                        if(viewGroup$LayoutParams0 != null && viewGroup$LayoutParams0.height == -2) {
                            return extendedFloatingActionButton$Size0.getHeight();
                        }
                        int v = view0.getPaddingTop();
                        int v1 = view0.getPaddingBottom();
                        if(ExtendedFloatingActionButton.this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                            ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)ExtendedFloatingActionButton.this.getLayoutParams();
                            if(viewGroup$MarginLayoutParams0 != null) {
                                int v2 = viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin;
                                return view0.getHeight() - v2 - (v + v1);
                            }
                        }
                        return view0.getHeight() - (v + v1);
                    }
                    case -2: 
                    case 0: {
                        return extendedFloatingActionButton$Size0.getHeight();
                    }
                    default: {
                        return ExtendedFloatingActionButton.this.originalHeight;
                    }
                }
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public ViewGroup.LayoutParams getLayoutParams() {
                return ExtendedFloatingActionButton.this.originalHeight == 0 ? new ViewGroup.LayoutParams(-1, -2) : new ViewGroup.LayoutParams(-1, ExtendedFloatingActionButton.this.originalHeight);
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getWidth() {
                if(!(ExtendedFloatingActionButton.this.getParent() instanceof View)) {
                    return extendedFloatingActionButton$Size0.getWidth();
                }
                View view0 = (View)ExtendedFloatingActionButton.this.getParent();
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                if(viewGroup$LayoutParams0 != null && viewGroup$LayoutParams0.width == -2) {
                    return extendedFloatingActionButton$Size0.getWidth();
                }
                int v = view0.getPaddingLeft();
                int v1 = view0.getPaddingRight();
                if(ExtendedFloatingActionButton.this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)ExtendedFloatingActionButton.this.getLayoutParams();
                    if(viewGroup$MarginLayoutParams0 != null) {
                        int v2 = viewGroup$MarginLayoutParams0.leftMargin + viewGroup$MarginLayoutParams0.rightMargin;
                        return view0.getWidth() - v2 - (v + v1);
                    }
                }
                return view0.getWidth() - (v + v1);
            }
        };
        Size extendedFloatingActionButton$Size2 = new Size() {
            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getHeight() {
                switch(ExtendedFloatingActionButton.this.originalHeight) {
                    case -1: {
                        return extendedFloatingActionButton$Size1.getHeight();
                    }
                    case -2: 
                    case 0: {
                        return extendedFloatingActionButton$Size0.getHeight();
                    }
                    default: {
                        return ExtendedFloatingActionButton.this.originalHeight;
                    }
                }
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public ViewGroup.LayoutParams getLayoutParams() {
                int v = -2;
                int v1 = ExtendedFloatingActionButton.this.originalWidth == 0 ? -2 : ExtendedFloatingActionButton.this.originalWidth;
                if(ExtendedFloatingActionButton.this.originalHeight != 0) {
                    v = ExtendedFloatingActionButton.this.originalHeight;
                }
                return new ViewGroup.LayoutParams(v1, v);
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            @Override  // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size
            public int getWidth() {
                switch(ExtendedFloatingActionButton.this.originalWidth) {
                    case -1: {
                        return extendedFloatingActionButton$Size1.getWidth();
                    }
                    case -2: 
                    case 0: {
                        return extendedFloatingActionButton$Size0.getWidth();
                    }
                    default: {
                        return ExtendedFloatingActionButton.this.originalWidth;
                    }
                }
            }
        };
        switch(v) {
            case 1: {
                return extendedFloatingActionButton$Size0;
            }
            case 2: {
                return extendedFloatingActionButton$Size1;
            }
            default: {
                return extendedFloatingActionButton$Size2;
            }
        }
    }

    public void hide() {
        this.performMotion(1, null);
    }

    public void hide(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        this.performMotion(1, extendedFloatingActionButton$OnChangedCallback0);
    }

    public final boolean isExtended() {
        return this.isExtended;
    }

    private boolean isOrWillBeHidden() {
        return this.getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    private boolean isOrWillBeShown() {
        return this.getVisibility() == 0 ? this.animState != 1 : this.animState == 2;
    }

    @Override  // com.google.android.material.button.MaterialButton
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.isExtended && TextUtils.isEmpty(this.getText()) && this.getIcon() != null) {
            this.isExtended = false;
            this.shrinkStrategy.performNow();
        }
    }

    private void performMotion(int v, OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        MotionStrategy motionStrategy0;
        if(v == 0) {
            motionStrategy0 = this.showStrategy;
        }
        else {
            switch(v) {
                case 1: {
                    motionStrategy0 = this.hideStrategy;
                    break;
                }
                case 2: {
                    motionStrategy0 = this.shrinkStrategy;
                    break;
                }
                default: {
                    if(v != 3) {
                        throw new IllegalStateException("Unknown strategy type: " + v);
                    }
                    motionStrategy0 = this.extendStrategy;
                    break;
                }
            }
        }
        if(motionStrategy0.shouldCancel()) {
            return;
        }
        if(!this.shouldAnimateVisibilityChange()) {
            motionStrategy0.performNow();
            motionStrategy0.onChange(extendedFloatingActionButton$OnChangedCallback0);
            return;
        }
        if(v == 2) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getLayoutParams();
            if(viewGroup$LayoutParams0 == null) {
                this.originalWidth = this.getWidth();
                this.originalHeight = this.getHeight();
            }
            else {
                this.originalWidth = viewGroup$LayoutParams0.width;
                this.originalHeight = viewGroup$LayoutParams0.height;
            }
        }
        this.measure(0, 0);
        AnimatorSet animatorSet0 = motionStrategy0.createAnimator();
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            private boolean cancelled;

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationCancel(Animator animator0) {
                this.cancelled = true;
                motionStrategy0.onAnimationCancel();
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                motionStrategy0.onAnimationEnd();
                if(!this.cancelled) {
                    motionStrategy0.onChange(extendedFloatingActionButton$OnChangedCallback0);
                }
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                motionStrategy0.onAnimationStart(animator0);
                this.cancelled = false;
            }
        });
        for(Object object0: motionStrategy0.getListeners()) {
            animatorSet0.addListener(((Animator.AnimatorListener)object0));
        }
        animatorSet0.start();
    }

    public void removeOnExtendAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.extendStrategy.removeAnimationListener(animator$AnimatorListener0);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.hideStrategy.removeAnimationListener(animator$AnimatorListener0);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.showStrategy.removeAnimationListener(animator$AnimatorListener0);
    }

    public void removeOnShrinkAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.shrinkStrategy.removeAnimationListener(animator$AnimatorListener0);
    }

    private void saveOriginalTextCsl() {
        this.originalTextCsl = this.getTextColors();
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.animateShowBeforeLayout = z;
    }

    public void setExtendMotionSpec(MotionSpec motionSpec0) {
        this.extendStrategy.setMotionSpec(motionSpec0);
    }

    public void setExtendMotionSpecResource(int v) {
        this.setExtendMotionSpec(MotionSpec.createFromResource(this.getContext(), v));
    }

    public void setExtended(boolean z) {
        if(this.isExtended != z) {
            MotionStrategy motionStrategy0 = z ? this.extendStrategy : this.shrinkStrategy;
            if(!motionStrategy0.shouldCancel()) {
                motionStrategy0.performNow();
            }
        }
    }

    public void setHideMotionSpec(MotionSpec motionSpec0) {
        this.hideStrategy.setMotionSpec(motionSpec0);
    }

    public void setHideMotionSpecResource(int v) {
        this.setHideMotionSpec(MotionSpec.createFromResource(this.getContext(), v));
    }

    @Override  // android.widget.TextView
    public void setPadding(int v, int v1, int v2, int v3) {
        super.setPadding(v, v1, v2, v3);
        if(this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = ViewCompat.getPaddingStart(this);
            this.extendedPaddingEnd = ViewCompat.getPaddingEnd(this);
        }
    }

    @Override  // android.widget.TextView
    public void setPaddingRelative(int v, int v1, int v2, int v3) {
        super.setPaddingRelative(v, v1, v2, v3);
        if(this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = v;
            this.extendedPaddingEnd = v2;
        }
    }

    public void setShowMotionSpec(MotionSpec motionSpec0) {
        this.showStrategy.setMotionSpec(motionSpec0);
    }

    public void setShowMotionSpecResource(int v) {
        this.setShowMotionSpec(MotionSpec.createFromResource(this.getContext(), v));
    }

    public void setShrinkMotionSpec(MotionSpec motionSpec0) {
        this.shrinkStrategy.setMotionSpec(motionSpec0);
    }

    public void setShrinkMotionSpecResource(int v) {
        this.setShrinkMotionSpec(MotionSpec.createFromResource(this.getContext(), v));
    }

    @Override  // android.widget.TextView
    public void setTextColor(int v) {
        super.setTextColor(v);
        this.saveOriginalTextCsl();
    }

    @Override  // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList0) {
        super.setTextColor(colorStateList0);
        this.saveOriginalTextCsl();
    }

    // 去混淆评级： 低(40)
    private boolean shouldAnimateVisibilityChange() {
        return (ViewCompat.isLaidOut(this) || !this.isOrWillBeShown() && this.animateShowBeforeLayout) && !this.isInEditMode();
    }

    public void show() {
        this.performMotion(0, null);
    }

    public void show(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        this.performMotion(0, extendedFloatingActionButton$OnChangedCallback0);
    }

    public void shrink() {
        this.performMotion(2, null);
    }

    public void shrink(OnChangedCallback extendedFloatingActionButton$OnChangedCallback0) {
        this.performMotion(2, extendedFloatingActionButton$OnChangedCallback0);
    }

    protected void silentlyUpdateTextColor(ColorStateList colorStateList0) {
        super.setTextColor(colorStateList0);
    }
}

