package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R.animator;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior.OnScrollStateChangedListener;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils.RelativePadding;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class BottomAppBar extends Toolbar implements AttachedBehavior {
    interface AnimationListener {
        void onAnimationEnd(BottomAppBar arg1);

        void onAnimationStart(BottomAppBar arg1);
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior {
        private final Rect fabContentRect;
        private final View.OnLayoutChangeListener fabLayoutListener;
        private int originalBottomMargin;
        private WeakReference viewRef;

        public Behavior() {
            this.fabLayoutListener = new View.OnLayoutChangeListener() {
                @Override  // android.view.View$OnLayoutChangeListener
                public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                    BottomAppBar bottomAppBar0 = (BottomAppBar)Behavior.this.viewRef.get();
                    if(bottomAppBar0 != null && (view0 instanceof FloatingActionButton || view0 instanceof ExtendedFloatingActionButton)) {
                        int v8 = view0.getHeight();
                        if(view0 instanceof FloatingActionButton) {
                            ((FloatingActionButton)view0).getMeasuredContentRect(Behavior.this.fabContentRect);
                            v8 = Behavior.this.fabContentRect.height();
                            bottomAppBar0.setFabDiameter(v8);
                            bottomAppBar0.setFabCornerSize(((FloatingActionButton)view0).getShapeAppearanceModel().getTopLeftCornerSize().getCornerSize(new RectF(Behavior.this.fabContentRect)));
                        }
                        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                        if(Behavior.this.originalBottomMargin == 0) {
                            if(bottomAppBar0.fabAnchorMode == 1) {
                                int v9 = view0.getMeasuredHeight();
                                coordinatorLayout$LayoutParams0.bottomMargin = bottomAppBar0.getBottomInset() + (bottomAppBar0.getResources().getDimensionPixelOffset(dimen.mtrl_bottomappbar_fab_bottom_margin) - (v9 - v8) / 2);
                            }
                            coordinatorLayout$LayoutParams0.leftMargin = bottomAppBar0.getLeftInset();
                            coordinatorLayout$LayoutParams0.rightMargin = bottomAppBar0.getRightInset();
                            if(ViewUtils.isLayoutRtl(view0)) {
                                coordinatorLayout$LayoutParams0.leftMargin += bottomAppBar0.fabOffsetEndMode;
                            }
                            else {
                                coordinatorLayout$LayoutParams0.rightMargin += bottomAppBar0.fabOffsetEndMode;
                            }
                        }
                        bottomAppBar0.setCutoutStateAndTranslateFab();
                        return;
                    }
                    view0.removeOnLayoutChangeListener(this);
                }
            };
            this.fabContentRect = new Rect();
        }

        public Behavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.fabLayoutListener = new View.OnLayoutChangeListener() {
                @Override  // android.view.View$OnLayoutChangeListener
                public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                    BottomAppBar bottomAppBar0 = (BottomAppBar)Behavior.this.viewRef.get();
                    if(bottomAppBar0 != null && (view0 instanceof FloatingActionButton || view0 instanceof ExtendedFloatingActionButton)) {
                        int v8 = view0.getHeight();
                        if(view0 instanceof FloatingActionButton) {
                            ((FloatingActionButton)view0).getMeasuredContentRect(Behavior.this.fabContentRect);
                            v8 = Behavior.this.fabContentRect.height();
                            bottomAppBar0.setFabDiameter(v8);
                            bottomAppBar0.setFabCornerSize(((FloatingActionButton)view0).getShapeAppearanceModel().getTopLeftCornerSize().getCornerSize(new RectF(Behavior.this.fabContentRect)));
                        }
                        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                        if(Behavior.this.originalBottomMargin == 0) {
                            if(bottomAppBar0.fabAnchorMode == 1) {
                                int v9 = view0.getMeasuredHeight();
                                coordinatorLayout$LayoutParams0.bottomMargin = bottomAppBar0.getBottomInset() + (bottomAppBar0.getResources().getDimensionPixelOffset(dimen.mtrl_bottomappbar_fab_bottom_margin) - (v9 - v8) / 2);
                            }
                            coordinatorLayout$LayoutParams0.leftMargin = bottomAppBar0.getLeftInset();
                            coordinatorLayout$LayoutParams0.rightMargin = bottomAppBar0.getRightInset();
                            if(ViewUtils.isLayoutRtl(view0)) {
                                coordinatorLayout$LayoutParams0.leftMargin += bottomAppBar0.fabOffsetEndMode;
                            }
                            else {
                                coordinatorLayout$LayoutParams0.rightMargin += bottomAppBar0.fabOffsetEndMode;
                            }
                        }
                        bottomAppBar0.setCutoutStateAndTranslateFab();
                        return;
                    }
                    view0.removeOnLayoutChangeListener(this);
                }
            };
            this.fabContentRect = new Rect();
        }

        @Override  // com.google.android.material.behavior.HideBottomViewOnScrollBehavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
            return this.onLayoutChild(coordinatorLayout0, ((BottomAppBar)view0), v);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, BottomAppBar bottomAppBar0, int v) {
            this.viewRef = new WeakReference(bottomAppBar0);
            View view0 = bottomAppBar0.findDependentView();
            if(view0 != null && !ViewCompat.isLaidOut(view0)) {
                BottomAppBar.updateFabAnchorGravity(bottomAppBar0, view0);
                this.originalBottomMargin = ((LayoutParams)view0.getLayoutParams()).bottomMargin;
                if(view0 instanceof FloatingActionButton) {
                    if(bottomAppBar0.fabAnchorMode == 0 && bottomAppBar0.removeEmbeddedFabElevation) {
                        ViewCompat.setElevation(((FloatingActionButton)view0), 0.0f);
                        ((FloatingActionButton)view0).setCompatElevation(0.0f);
                    }
                    if(((FloatingActionButton)view0).getShowMotionSpec() == null) {
                        ((FloatingActionButton)view0).setShowMotionSpecResource(animator.mtrl_fab_show_motion_spec);
                    }
                    if(((FloatingActionButton)view0).getHideMotionSpec() == null) {
                        ((FloatingActionButton)view0).setHideMotionSpecResource(animator.mtrl_fab_hide_motion_spec);
                    }
                    bottomAppBar0.addFabAnimationListeners(((FloatingActionButton)view0));
                }
                view0.addOnLayoutChangeListener(this.fabLayoutListener);
                bottomAppBar0.setCutoutStateAndTranslateFab();
            }
            coordinatorLayout0.onLayoutChild(bottomAppBar0, v);
            return super.onLayoutChild(coordinatorLayout0, bottomAppBar0, v);
        }

        @Override  // com.google.android.material.behavior.HideBottomViewOnScrollBehavior
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, View view2, int v, int v1) {
            return this.onStartNestedScroll(coordinatorLayout0, ((BottomAppBar)view0), view1, view2, v, v1);
        }

        // 去混淆评级： 低(20)
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, BottomAppBar bottomAppBar0, View view0, View view1, int v, int v1) {
            return bottomAppBar0.getHideOnScroll() && super.onStartNestedScroll(coordinatorLayout0, bottomAppBar0, view0, view1, v, v1);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAnchorMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MenuAlignmentMode {
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        int fabAlignmentMode;
        boolean fabAttached;

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
            this.fabAlignmentMode = parcel0.readInt();
            this.fabAttached = parcel0.readInt() != 0;
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.fabAlignmentMode);
            parcel0.writeInt(((int)this.fabAttached));
        }
    }

    private static final int DEF_STYLE_RES = 0;
    private static final int FAB_ALIGNMENT_ANIM_DURATION_ATTR = 0;
    private static final int FAB_ALIGNMENT_ANIM_DURATION_DEFAULT = 300;
    private static final int FAB_ALIGNMENT_ANIM_EASING_ATTR = 0;
    private static final float FAB_ALIGNMENT_ANIM_EASING_MIDPOINT = 0.2f;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    public static final int FAB_ANCHOR_MODE_CRADLE = 1;
    public static final int FAB_ANCHOR_MODE_EMBED = 0;
    public static final int FAB_ANIMATION_MODE_SCALE = 0;
    public static final int FAB_ANIMATION_MODE_SLIDE = 1;
    public static final int MENU_ALIGNMENT_MODE_AUTO = 0;
    public static final int MENU_ALIGNMENT_MODE_START = 1;
    private static final int NO_FAB_END_MARGIN = -1;
    private static final int NO_MENU_RES_ID;
    private int animatingModeChangeCounter;
    private ArrayList animationListeners;
    private Behavior behavior;
    private int bottomInset;
    private int fabAlignmentMode;
    private int fabAlignmentModeEndMargin;
    private int fabAnchorMode;
    AnimatorListenerAdapter fabAnimationListener;
    private int fabAnimationMode;
    private boolean fabAttached;
    private final int fabOffsetEndMode;
    TransformationCallback fabTransformationCallback;
    private boolean hideOnScroll;
    private int leftInset;
    private final MaterialShapeDrawable materialShapeDrawable;
    private int menuAlignmentMode;
    private boolean menuAnimatingWithFabAlignmentMode;
    private Animator menuAnimator;
    private Animator modeAnimator;
    private Integer navigationIconTint;
    private final boolean paddingBottomSystemWindowInsets;
    private final boolean paddingLeftSystemWindowInsets;
    private final boolean paddingRightSystemWindowInsets;
    private int pendingMenuResId;
    private final boolean removeEmbeddedFabElevation;
    private int rightInset;

    static {
        BottomAppBar.DEF_STYLE_RES = style.Widget_MaterialComponents_BottomAppBar;
        BottomAppBar.FAB_ALIGNMENT_ANIM_DURATION_ATTR = attr.motionDurationLong2;
        BottomAppBar.FAB_ALIGNMENT_ANIM_EASING_ATTR = attr.motionEasingEmphasizedInterpolator;
    }

    public BottomAppBar(Context context0) {
        this(context0, null);
    }

    public BottomAppBar(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.bottomAppBarStyle);
    }

    public BottomAppBar(Context context0, AttributeSet attributeSet0, int v) {
        int v1 = BottomAppBar.DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, v1), attributeSet0, v);
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
        this.materialShapeDrawable = materialShapeDrawable0;
        this.animatingModeChangeCounter = 0;
        this.pendingMenuResId = 0;
        this.menuAnimatingWithFabAlignmentMode = false;
        this.fabAttached = true;
        this.fabAnimationListener = new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                if(!BottomAppBar.this.menuAnimatingWithFabAlignmentMode) {
                    BottomAppBar.this.maybeAnimateMenuView(BottomAppBar.this.fabAlignmentMode, BottomAppBar.this.fabAttached);
                }
            }
        };
        this.fabTransformationCallback = new TransformationCallback() {
            @Override  // com.google.android.material.animation.TransformationCallback
            public void onScaleChanged(View view0) {
                this.onScaleChanged(((FloatingActionButton)view0));
            }

            public void onScaleChanged(FloatingActionButton floatingActionButton0) {
                BottomAppBar.this.materialShapeDrawable.setInterpolation((floatingActionButton0.getVisibility() != 0 || BottomAppBar.this.fabAnchorMode != 1 ? 0.0f : floatingActionButton0.getScaleY()));
            }

            @Override  // com.google.android.material.animation.TransformationCallback
            public void onTranslationChanged(View view0) {
                this.onTranslationChanged(((FloatingActionButton)view0));
            }

            public void onTranslationChanged(FloatingActionButton floatingActionButton0) {
                if(BottomAppBar.this.fabAnchorMode != 1) {
                    return;
                }
                float f = floatingActionButton0.getTranslationX();
                if(BottomAppBar.this.getTopEdgeTreatment().getHorizontalOffset() != f) {
                    BottomAppBar.this.getTopEdgeTreatment().setHorizontalOffset(f);
                    BottomAppBar.this.materialShapeDrawable.invalidateSelf();
                }
                float f1 = 0.0f;
                float f2 = Math.max(0.0f, -floatingActionButton0.getTranslationY());
                if(BottomAppBar.this.getTopEdgeTreatment().getCradleVerticalOffset() != f2) {
                    BottomAppBar.this.getTopEdgeTreatment().setCradleVerticalOffset(f2);
                    BottomAppBar.this.materialShapeDrawable.invalidateSelf();
                }
                MaterialShapeDrawable materialShapeDrawable0 = BottomAppBar.this.materialShapeDrawable;
                if(floatingActionButton0.getVisibility() == 0) {
                    f1 = floatingActionButton0.getScaleY();
                }
                materialShapeDrawable0.setInterpolation(f1);
            }
        };
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.BottomAppBar, v, v1, new int[0]);
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context1, typedArray0, styleable.BottomAppBar_backgroundTint);
        if(typedArray0.hasValue(styleable.BottomAppBar_navigationIconTint)) {
            this.setNavigationIconTint(typedArray0.getColor(styleable.BottomAppBar_navigationIconTint, -1));
        }
        int v2 = typedArray0.getDimensionPixelSize(styleable.BottomAppBar_elevation, 0);
        float f = (float)typedArray0.getDimensionPixelOffset(styleable.BottomAppBar_fabCradleMargin, 0);
        float f1 = (float)typedArray0.getDimensionPixelOffset(styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float f2 = (float)typedArray0.getDimensionPixelOffset(styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.fabAlignmentMode = typedArray0.getInt(styleable.BottomAppBar_fabAlignmentMode, 0);
        this.fabAnimationMode = typedArray0.getInt(styleable.BottomAppBar_fabAnimationMode, 0);
        this.fabAnchorMode = typedArray0.getInt(styleable.BottomAppBar_fabAnchorMode, 1);
        this.removeEmbeddedFabElevation = typedArray0.getBoolean(styleable.BottomAppBar_removeEmbeddedFabElevation, true);
        this.menuAlignmentMode = typedArray0.getInt(styleable.BottomAppBar_menuAlignmentMode, 0);
        this.hideOnScroll = typedArray0.getBoolean(styleable.BottomAppBar_hideOnScroll, false);
        this.paddingBottomSystemWindowInsets = typedArray0.getBoolean(styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = typedArray0.getBoolean(styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = typedArray0.getBoolean(styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        this.fabAlignmentModeEndMargin = typedArray0.getDimensionPixelOffset(styleable.BottomAppBar_fabAlignmentModeEndMargin, -1);
        boolean z = typedArray0.getBoolean(styleable.BottomAppBar_addElevationShadow, true);
        typedArray0.recycle();
        this.fabOffsetEndMode = this.getResources().getDimensionPixelOffset(dimen.mtrl_bottomappbar_fabOffsetEndMode);
        BottomAppBarTopEdgeTreatment bottomAppBarTopEdgeTreatment0 = new BottomAppBarTopEdgeTreatment(f, f1, f2);
        materialShapeDrawable0.setShapeAppearanceModel(ShapeAppearanceModel.builder().setTopEdge(bottomAppBarTopEdgeTreatment0).build());
        if(z) {
            materialShapeDrawable0.setShadowCompatibilityMode(2);
        }
        else {
            materialShapeDrawable0.setShadowCompatibilityMode(1);
            if(Build.VERSION.SDK_INT >= 28) {
                this.setOutlineAmbientShadowColor(0);
                this.setOutlineSpotShadowColor(0);
            }
        }
        materialShapeDrawable0.setPaintStyle(Paint.Style.FILL);
        materialShapeDrawable0.initializeElevationOverlay(context1);
        this.setElevation(((float)v2));
        DrawableCompat.setTintList(materialShapeDrawable0, colorStateList0);
        ViewCompat.setBackground(this, materialShapeDrawable0);
        ViewUtils.doOnApplyWindowInsets(this, attributeSet0, v, v1, new OnApplyWindowInsetsListener() {
            @Override  // com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) {
                boolean z;
                if(BottomAppBar.access$700(BottomAppBar.this)) {
                    int v = windowInsetsCompat0.getSystemWindowInsetBottom();
                    BottomAppBar.access$802(BottomAppBar.this, v);
                }
                int v1 = 1;
                int v2 = 0;
                if(BottomAppBar.access$900(BottomAppBar.this)) {
                    z = BottomAppBar.this.leftInset != windowInsetsCompat0.getSystemWindowInsetLeft();
                    int v3 = windowInsetsCompat0.getSystemWindowInsetLeft();
                    BottomAppBar.this.leftInset = v3;
                }
                else {
                    z = false;
                }
                if(BottomAppBar.this.paddingRightSystemWindowInsets) {
                    if(BottomAppBar.this.rightInset == windowInsetsCompat0.getSystemWindowInsetRight()) {
                        v1 = 0;
                    }
                    int v4 = windowInsetsCompat0.getSystemWindowInsetRight();
                    BottomAppBar.this.rightInset = v4;
                    v2 = v1;
                }
                if(!z && v2 == 0) {
                    return windowInsetsCompat0;
                }
                BottomAppBar.this.cancelAnimations();
                BottomAppBar.this.setCutoutStateAndTranslateFab();
                BottomAppBar.this.setActionMenuViewPosition();
                return windowInsetsCompat0;
            }
        });
    }

    static boolean access$700(BottomAppBar bottomAppBar0) {
        return bottomAppBar0.paddingBottomSystemWindowInsets;
    }

    static int access$802(BottomAppBar bottomAppBar0, int v) {
        bottomAppBar0.bottomInset = v;
        return v;
    }

    static boolean access$900(BottomAppBar bottomAppBar0) {
        return bottomAppBar0.paddingLeftSystemWindowInsets;
    }

    void addAnimationListener(AnimationListener bottomAppBar$AnimationListener0) {
        if(this.animationListeners == null) {
            this.animationListeners = new ArrayList();
        }
        this.animationListeners.add(bottomAppBar$AnimationListener0);
    }

    private void addFabAnimationListeners(FloatingActionButton floatingActionButton0) {
        floatingActionButton0.addOnHideAnimationListener(this.fabAnimationListener);
        floatingActionButton0.addOnShowAnimationListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                BottomAppBar.this.fabAnimationListener.onAnimationStart(animator0);
                FloatingActionButton floatingActionButton0 = BottomAppBar.this.findDependentFab();
                if(floatingActionButton0 != null) {
                    floatingActionButton0.setTranslationX(BottomAppBar.this.getFabTranslationX());
                }
            }
        });
        floatingActionButton0.addTransformationCallback(this.fabTransformationCallback);
    }

    public void addOnScrollStateChangedListener(OnScrollStateChangedListener hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0) {
        this.getBehavior().addOnScrollStateChangedListener(hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0);
    }

    private void cancelAnimations() {
        Animator animator0 = this.menuAnimator;
        if(animator0 != null) {
            animator0.cancel();
        }
        Animator animator1 = this.modeAnimator;
        if(animator1 != null) {
            animator1.cancel();
        }
    }

    public void clearOnScrollStateChangedListeners() {
        this.getBehavior().clearOnScrollStateChangedListeners();
    }

    protected void createFabDefaultXAnimation(int v, List list0) {
        FloatingActionButton floatingActionButton0 = this.findDependentFab();
        if(floatingActionButton0 != null && !floatingActionButton0.isOrWillBeHidden()) {
            this.dispatchAnimationStart();
            floatingActionButton0.hide(new OnVisibilityChangedListener() {
                @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener
                public void onHidden(FloatingActionButton floatingActionButton0) {
                    floatingActionButton0.setTranslationX(BottomAppBar.this.getFabTranslationX(v));
                    floatingActionButton0.show(new OnVisibilityChangedListener() {
                        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$OnVisibilityChangedListener
                        public void onShown(FloatingActionButton floatingActionButton0) {
                            BottomAppBar.this.dispatchAnimationEnd();
                        }
                    });
                }
            });
        }
    }

    private void createFabTranslationXAnimation(int v, List list0) {
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this.findDependentFab(), "translationX", new float[]{this.getFabTranslationX(v)});
        objectAnimator0.setDuration(((long)this.getFabAlignmentAnimationDuration()));
        list0.add(objectAnimator0);
    }

    private void createMenuViewTranslationAnimation(int v, boolean z, List list0) {
        ActionMenuView actionMenuView0 = this.getActionMenuView();
        if(actionMenuView0 != null) {
            float f = (float)this.getFabAlignmentAnimationDuration();
            ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(actionMenuView0, "alpha", new float[]{1.0f});
            objectAnimator0.setDuration(((long)(0.8f * f)));
            if(Math.abs(actionMenuView0.getTranslationX() - ((float)this.getActionMenuViewTranslationX(actionMenuView0, v, z))) > 1.0f) {
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(actionMenuView0, "alpha", new float[]{0.0f});
                objectAnimator1.setDuration(((long)(f * 0.2f)));
                objectAnimator1.addListener(new AnimatorListenerAdapter() {
                    public boolean cancelled;

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationCancel(Animator animator0) {
                        this.cancelled = true;
                    }

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationEnd(Animator animator0) {
                        if(!this.cancelled) {
                            BottomAppBar.this.replaceMenu(BottomAppBar.this.pendingMenuResId);
                            BottomAppBar.this.translateActionMenuView(actionMenuView0, v, z, BottomAppBar.this.pendingMenuResId != 0);
                        }
                    }
                });
                AnimatorSet animatorSet0 = new AnimatorSet();
                animatorSet0.playSequentially(new Animator[]{objectAnimator1, objectAnimator0});
                list0.add(animatorSet0);
                return;
            }
            if(actionMenuView0.getAlpha() < 1.0f) {
                list0.add(objectAnimator0);
            }
        }
    }

    private void dispatchAnimationEnd() {
        int v = this.animatingModeChangeCounter - 1;
        this.animatingModeChangeCounter = v;
        if(v == 0) {
            ArrayList arrayList0 = this.animationListeners;
            if(arrayList0 != null) {
                for(Object object0: arrayList0) {
                    ((AnimationListener)object0).onAnimationEnd(this);
                }
            }
        }
    }

    private void dispatchAnimationStart() {
        int v = this.animatingModeChangeCounter;
        this.animatingModeChangeCounter = v + 1;
        if(v == 0) {
            ArrayList arrayList0 = this.animationListeners;
            if(arrayList0 != null) {
                for(Object object0: arrayList0) {
                    ((AnimationListener)object0).onAnimationStart(this);
                }
            }
        }
    }

    private FloatingActionButton findDependentFab() {
        View view0 = this.findDependentView();
        return view0 instanceof FloatingActionButton ? ((FloatingActionButton)view0) : null;
    }

    private View findDependentView() {
        if(!(this.getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for(Object object0: ((CoordinatorLayout)this.getParent()).getDependents(this)) {
            View view0 = (View)object0;
            if(view0 instanceof FloatingActionButton || view0 instanceof ExtendedFloatingActionButton) {
                return view0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    private ActionMenuView getActionMenuView() {
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view0 = this.getChildAt(v);
            if(view0 instanceof ActionMenuView) {
                return (ActionMenuView)view0;
            }
        }
        return null;
    }

    protected int getActionMenuViewTranslationX(ActionMenuView actionMenuView0, int v, boolean z) {
        int v1 = 0;
        if(this.menuAlignmentMode != 1 && (v != 1 || !z)) {
            return 0;
        }
        boolean z1 = ViewUtils.isLayoutRtl(this);
        int v2 = z1 ? this.getMeasuredWidth() : 0;
        for(int v3 = 0; v3 < this.getChildCount(); ++v3) {
            View view0 = this.getChildAt(v3);
            if(view0.getLayoutParams() instanceof androidx.appcompat.widget.Toolbar.LayoutParams && (((androidx.appcompat.widget.Toolbar.LayoutParams)view0.getLayoutParams()).gravity & 0x800007) == 0x800003) {
                v2 = z1 ? Math.min(v2, view0.getLeft()) : Math.max(v2, view0.getRight());
            }
        }
        int v4 = z1 ? actionMenuView0.getRight() : actionMenuView0.getLeft();
        int v5 = z1 ? this.rightInset : -this.leftInset;
        if(this.getNavigationIcon() == null) {
            v1 = this.getResources().getDimensionPixelOffset(dimen.m3_bottomappbar_horizontal_padding);
            if(!z1) {
                v1 = -v1;
            }
        }
        return v2 - (v4 + v5 + v1);
    }

    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.getTintList();
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$AttachedBehavior
    public androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior getBehavior() {
        return this.getBehavior();
    }

    public Behavior getBehavior() {
        if(this.behavior == null) {
            this.behavior = new Behavior();
        }
        return this.behavior;
    }

    private int getBottomInset() {
        return this.bottomInset;
    }

    public float getCradleVerticalOffset() {
        return this.getTopEdgeTreatment().getCradleVerticalOffset();
    }

    private int getFabAlignmentAnimationDuration() {
        return MotionUtils.resolveThemeDuration(this.getContext(), BottomAppBar.FAB_ALIGNMENT_ANIM_DURATION_ATTR, 300);
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public int getFabAlignmentModeEndMargin() {
        return this.fabAlignmentModeEndMargin;
    }

    public int getFabAnchorMode() {
        return this.fabAnchorMode;
    }

    public int getFabAnimationMode() {
        return this.fabAnimationMode;
    }

    public float getFabCradleMargin() {
        return this.getTopEdgeTreatment().getFabCradleMargin();
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.getTopEdgeTreatment().getFabCradleRoundedCornerRadius();
    }

    private float getFabTranslationX() {
        return this.getFabTranslationX(this.fabAlignmentMode);
    }

    private float getFabTranslationX(int v) {
        boolean z = ViewUtils.isLayoutRtl(this);
        int v1 = 1;
        if(v == 1) {
            View view0 = this.findDependentView();
            int v2 = z ? this.leftInset : this.rightInset;
            int v3 = this.fabAlignmentModeEndMargin == -1 || view0 == null ? this.fabOffsetEndMode : view0.getMeasuredWidth() / 2 + this.fabAlignmentModeEndMargin;
            int v4 = this.getMeasuredWidth();
            if(z) {
                v1 = -1;
            }
            return (float)((v4 / 2 - (v2 + v3)) * v1);
        }
        return 0.0f;
    }

    private float getFabTranslationY() {
        if(this.fabAnchorMode == 1) {
            return -this.getTopEdgeTreatment().getCradleVerticalOffset();
        }
        View view0 = this.findDependentView();
        return view0 == null ? 0.0f : ((float)(-(this.getMeasuredHeight() + this.getBottomInset() - view0.getMeasuredHeight()) / 2));
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    private int getLeftInset() {
        return this.leftInset;
    }

    public int getMenuAlignmentMode() {
        return this.menuAlignmentMode;
    }

    private int getRightInset() {
        return this.rightInset;
    }

    private BottomAppBarTopEdgeTreatment getTopEdgeTreatment() {
        return (BottomAppBarTopEdgeTreatment)this.materialShapeDrawable.getShapeAppearanceModel().getTopEdge();
    }

    private boolean isFabVisibleOrWillBeShown() {
        FloatingActionButton floatingActionButton0 = this.findDependentFab();
        return floatingActionButton0 != null && floatingActionButton0.isOrWillBeShown();
    }

    public boolean isScrolledDown() {
        return this.getBehavior().isScrolledDown();
    }

    public boolean isScrolledUp() {
        return this.getBehavior().isScrolledUp();
    }

    // 检测为 Lambda 实现
    static void lambda$onLayout$0(View view0) [...]

    private void maybeAnimateMenuView(int v, boolean z) {
        if(!ViewCompat.isLaidOut(this)) {
            this.menuAnimatingWithFabAlignmentMode = false;
            this.replaceMenu(this.pendingMenuResId);
            return;
        }
        Animator animator0 = this.menuAnimator;
        if(animator0 != null) {
            animator0.cancel();
        }
        ArrayList arrayList0 = new ArrayList();
        if(!this.isFabVisibleOrWillBeShown()) {
            v = 0;
            z = false;
        }
        this.createMenuViewTranslationAnimation(v, z, arrayList0);
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playTogether(arrayList0);
        this.menuAnimator = animatorSet0;
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                BottomAppBar.this.dispatchAnimationEnd();
                BottomAppBar.this.menuAnimatingWithFabAlignmentMode = false;
                BottomAppBar.this.menuAnimator = null;
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                BottomAppBar.this.dispatchAnimationStart();
            }
        });
        this.menuAnimator.start();
    }

    private void maybeAnimateModeChange(int v) {
        if(this.fabAlignmentMode != v && ViewCompat.isLaidOut(this)) {
            Animator animator0 = this.modeAnimator;
            if(animator0 != null) {
                animator0.cancel();
            }
            ArrayList arrayList0 = new ArrayList();
            if(this.fabAnimationMode == 1) {
                this.createFabTranslationXAnimation(v, arrayList0);
            }
            else {
                this.createFabDefaultXAnimation(v, arrayList0);
            }
            AnimatorSet animatorSet0 = new AnimatorSet();
            animatorSet0.playTogether(arrayList0);
            animatorSet0.setInterpolator(MotionUtils.resolveThemeInterpolator(this.getContext(), BottomAppBar.FAB_ALIGNMENT_ANIM_EASING_ATTR, AnimationUtils.LINEAR_INTERPOLATOR));
            this.modeAnimator = animatorSet0;
            animatorSet0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    BottomAppBar.this.dispatchAnimationEnd();
                    BottomAppBar.this.modeAnimator = null;
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                    BottomAppBar.this.dispatchAnimationStart();
                }
            });
            this.modeAnimator.start();
        }
    }

    private Drawable maybeTintNavigationIcon(Drawable drawable0) {
        if(drawable0 != null && this.navigationIconTint != null) {
            drawable0 = DrawableCompat.wrap(drawable0.mutate());
            DrawableCompat.setTint(drawable0, ((int)this.navigationIconTint));
        }
        return drawable0;
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialShapeDrawable);
        if(this.getParent() instanceof ViewGroup) {
            ((ViewGroup)this.getParent()).setClipChildren(false);
        }
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        if(z) {
            this.cancelAnimations();
            this.setCutoutStateAndTranslateFab();
            View view0 = this.findDependentView();
            if(view0 != null && ViewCompat.isLaidOut(view0)) {
                view0.post(() -> view0.requestLayout());
            }
        }
        this.setActionMenuViewPosition();
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.fabAlignmentMode = ((SavedState)parcelable0).fabAlignmentMode;
        this.fabAttached = ((SavedState)parcelable0).fabAttached;
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.fabAlignmentMode = this.fabAlignmentMode;
        parcelable0.fabAttached = this.fabAttached;
        return parcelable0;
    }

    public void performHide() {
        this.performHide(true);
    }

    public void performHide(boolean z) {
        this.getBehavior().slideDown(this, z);
    }

    public void performShow() {
        this.performShow(true);
    }

    public void performShow(boolean z) {
        this.getBehavior().slideUp(this, z);
    }

    void removeAnimationListener(AnimationListener bottomAppBar$AnimationListener0) {
        ArrayList arrayList0 = this.animationListeners;
        if(arrayList0 == null) {
            return;
        }
        arrayList0.remove(bottomAppBar$AnimationListener0);
    }

    public void removeOnScrollStateChangedListener(OnScrollStateChangedListener hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0) {
        this.getBehavior().removeOnScrollStateChangedListener(hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0);
    }

    public void replaceMenu(int v) {
        if(v != 0) {
            this.pendingMenuResId = 0;
            this.getMenu().clear();
            this.inflateMenu(v);
        }
    }

    private void setActionMenuViewPosition() {
        ActionMenuView actionMenuView0 = this.getActionMenuView();
        if(actionMenuView0 != null && this.menuAnimator == null) {
            actionMenuView0.setAlpha(1.0f);
            if(!this.isFabVisibleOrWillBeShown()) {
                this.translateActionMenuView(actionMenuView0, 0, false);
                return;
            }
            this.translateActionMenuView(actionMenuView0, this.fabAlignmentMode, this.fabAttached);
        }
    }

    public void setBackgroundTint(ColorStateList colorStateList0) {
        DrawableCompat.setTintList(this.materialShapeDrawable, colorStateList0);
    }

    public void setCradleVerticalOffset(float f) {
        if(f != this.getCradleVerticalOffset()) {
            this.getTopEdgeTreatment().setCradleVerticalOffset(f);
            this.materialShapeDrawable.invalidateSelf();
            this.setCutoutStateAndTranslateFab();
        }
    }

    private void setCutoutStateAndTranslateFab() {
        this.getTopEdgeTreatment().setHorizontalOffset(this.getFabTranslationX());
        float f = !this.fabAttached || !this.isFabVisibleOrWillBeShown() || this.fabAnchorMode != 1 ? 0.0f : 1.0f;
        this.materialShapeDrawable.setInterpolation(f);
        View view0 = this.findDependentView();
        if(view0 != null) {
            view0.setTranslationY(this.getFabTranslationY());
            view0.setTranslationX(this.getFabTranslationX());
        }
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        this.materialShapeDrawable.setElevation(f);
        this.getBehavior().setAdditionalHiddenOffsetY(this, this.materialShapeDrawable.getShadowRadius() - this.materialShapeDrawable.getShadowOffsetY());
    }

    public void setFabAlignmentMode(int v) {
        this.setFabAlignmentModeAndReplaceMenu(v, 0);
    }

    public void setFabAlignmentModeAndReplaceMenu(int v, int v1) {
        this.pendingMenuResId = v1;
        this.menuAnimatingWithFabAlignmentMode = true;
        this.maybeAnimateMenuView(v, this.fabAttached);
        this.maybeAnimateModeChange(v);
        this.fabAlignmentMode = v;
    }

    public void setFabAlignmentModeEndMargin(int v) {
        if(this.fabAlignmentModeEndMargin != v) {
            this.fabAlignmentModeEndMargin = v;
            this.setCutoutStateAndTranslateFab();
        }
    }

    public void setFabAnchorMode(int v) {
        this.fabAnchorMode = v;
        this.setCutoutStateAndTranslateFab();
        View view0 = this.findDependentView();
        if(view0 != null) {
            BottomAppBar.updateFabAnchorGravity(this, view0);
            view0.requestLayout();
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabAnimationMode(int v) {
        this.fabAnimationMode = v;
    }

    void setFabCornerSize(float f) {
        if(f != this.getTopEdgeTreatment().getFabCornerRadius()) {
            this.getTopEdgeTreatment().setFabCornerSize(f);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f) {
        if(f != this.getFabCradleMargin()) {
            this.getTopEdgeTreatment().setFabCradleMargin(f);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f) {
        if(f != this.getFabCradleRoundedCornerRadius()) {
            this.getTopEdgeTreatment().setFabCradleRoundedCornerRadius(f);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    boolean setFabDiameter(int v) {
        if(((float)v) != this.getTopEdgeTreatment().getFabDiameter()) {
            this.getTopEdgeTreatment().setFabDiameter(((float)v));
            this.materialShapeDrawable.invalidateSelf();
            return true;
        }
        return false;
    }

    public void setHideOnScroll(boolean z) {
        this.hideOnScroll = z;
    }

    public void setMenuAlignmentMode(int v) {
        if(this.menuAlignmentMode != v) {
            this.menuAlignmentMode = v;
            ActionMenuView actionMenuView0 = this.getActionMenuView();
            if(actionMenuView0 != null) {
                this.translateActionMenuView(actionMenuView0, this.fabAlignmentMode, this.isFabVisibleOrWillBeShown());
            }
        }
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable0) {
        super.setNavigationIcon(this.maybeTintNavigationIcon(drawable0));
    }

    public void setNavigationIconTint(int v) {
        this.navigationIconTint = v;
        Drawable drawable0 = this.getNavigationIcon();
        if(drawable0 != null) {
            this.setNavigationIcon(drawable0);
        }
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence0) {
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence0) {
    }

    private void translateActionMenuView(ActionMenuView actionMenuView0, int v, boolean z) {
        this.translateActionMenuView(actionMenuView0, v, z, false);
    }

    private void translateActionMenuView(ActionMenuView actionMenuView0, int v, boolean z, boolean z1) {
        com.google.android.material.bottomappbar.BottomAppBar.8 bottomAppBar$80 = new Runnable() {
            @Override
            public void run() {
                float f = (float)BottomAppBar.this.getActionMenuViewTranslationX(actionMenuView0, v, z);
                actionMenuView0.setTranslationX(f);
            }
        };
        if(z1) {
            actionMenuView0.post(bottomAppBar$80);
            return;
        }
        bottomAppBar$80.run();
    }

    private static void updateFabAnchorGravity(BottomAppBar bottomAppBar0, View view0) {
        LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        coordinatorLayout$LayoutParams0.anchorGravity = 17;
        if(bottomAppBar0.fabAnchorMode == 1) {
            coordinatorLayout$LayoutParams0.anchorGravity |= 0x30;
        }
        if(bottomAppBar0.fabAnchorMode == 0) {
            coordinatorLayout$LayoutParams0.anchorGravity |= 80;
        }
    }
}

