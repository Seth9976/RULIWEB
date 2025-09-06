package com.google.android.material.floatingactionbutton;

import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageHelper;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TintableImageSourceView;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.expandable.ExpandableTransformationWidget;
import com.google.android.material.expandable.ExpandableWidgetHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.stateful.ExtendableSavedState;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class FloatingActionButton extends VisibilityAwareImageButton implements AttachedBehavior, TintableBackgroundView, TintableImageSourceView, ExpandableTransformationWidget, Shapeable {
    public static class BaseBehavior extends Behavior {
        private static final boolean AUTO_HIDE_DEFAULT = true;
        private boolean autoHideEnabled;
        private OnVisibilityChangedListener internalAutoHideListener;
        private Rect tmpRect;

        public BaseBehavior() {
            this.autoHideEnabled = true;
        }

        public BaseBehavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.FloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = typedArray0.getBoolean(styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArray0.recycle();
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout0, View view0, Rect rect0) {
            return this.getInsetDodgeRect(coordinatorLayout0, ((FloatingActionButton)view0), rect0);
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0, Rect rect0) {
            rect0.set(floatingActionButton0.getLeft() + floatingActionButton0.shadowPadding.left, floatingActionButton0.getTop() + floatingActionButton0.shadowPadding.top, floatingActionButton0.getRight() - floatingActionButton0.shadowPadding.right, floatingActionButton0.getBottom() - floatingActionButton0.shadowPadding.bottom);
            return true;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        private static boolean isBottomSheet(View view0) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
            return viewGroup$LayoutParams0 instanceof LayoutParams ? ((LayoutParams)viewGroup$LayoutParams0).getBehavior() instanceof BottomSheetBehavior : false;
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0) {
            int v1;
            Rect rect0 = floatingActionButton0.shadowPadding;
            if(rect0 != null && rect0.centerX() > 0 && rect0.centerY() > 0) {
                LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)floatingActionButton0.getLayoutParams();
                int v = 0;
                if(floatingActionButton0.getRight() >= coordinatorLayout0.getWidth() - coordinatorLayout$LayoutParams0.rightMargin) {
                    v1 = rect0.right;
                }
                else {
                    v1 = floatingActionButton0.getLeft() > coordinatorLayout$LayoutParams0.leftMargin ? 0 : -rect0.left;
                }
                if(floatingActionButton0.getBottom() >= coordinatorLayout0.getHeight() - coordinatorLayout$LayoutParams0.bottomMargin) {
                    v = rect0.bottom;
                }
                else if(floatingActionButton0.getTop() <= coordinatorLayout$LayoutParams0.topMargin) {
                    v = -rect0.top;
                }
                if(v != 0) {
                    ViewCompat.offsetTopAndBottom(floatingActionButton0, v);
                }
                if(v1 != 0) {
                    ViewCompat.offsetLeftAndRight(floatingActionButton0, v1);
                }
            }
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public void onAttachedToLayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
            if(coordinatorLayout$LayoutParams0.dodgeInsetEdges == 0) {
                coordinatorLayout$LayoutParams0.dodgeInsetEdges = 80;
            }
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            return this.onDependentViewChanged(coordinatorLayout0, ((FloatingActionButton)view0), view1);
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0, View view0) {
            if(view0 instanceof AppBarLayout) {
                this.updateFabVisibilityForAppBarLayout(coordinatorLayout0, ((AppBarLayout)view0), floatingActionButton0);
                return false;
            }
            if(BaseBehavior.isBottomSheet(view0)) {
                this.updateFabVisibilityForBottomSheet(view0, floatingActionButton0);
            }
            return false;
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
            return this.onLayoutChild(coordinatorLayout0, ((FloatingActionButton)view0), v);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0, int v) {
            List list0 = coordinatorLayout0.getDependencies(floatingActionButton0);
            int v1 = list0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = (View)list0.get(v2);
                if(!(view0 instanceof AppBarLayout)) {
                    if(!BaseBehavior.isBottomSheet(view0) || !this.updateFabVisibilityForBottomSheet(view0, floatingActionButton0)) {
                        continue;
                    }
                    break;
                }
                else if(this.updateFabVisibilityForAppBarLayout(coordinatorLayout0, ((AppBarLayout)view0), floatingActionButton0)) {
                    break;
                }
            }
            coordinatorLayout0.onLayoutChild(floatingActionButton0, v);
            this.offsetIfNeeded(coordinatorLayout0, floatingActionButton0);
            return true;
        }

        public void setAutoHideEnabled(boolean z) {
            this.autoHideEnabled = z;
        }

        public void setInternalAutoHideListener(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0) {
            this.internalAutoHideListener = floatingActionButton$OnVisibilityChangedListener0;
        }

        private boolean shouldUpdateVisibility(View view0, FloatingActionButton floatingActionButton0) {
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)floatingActionButton0.getLayoutParams();
            if(!this.autoHideEnabled) {
                return false;
            }
            return coordinatorLayout$LayoutParams0.getAnchorId() == view0.getId() ? floatingActionButton0.getUserSetVisibility() == 0 : false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout0, AppBarLayout appBarLayout0, FloatingActionButton floatingActionButton0) {
            if(!this.shouldUpdateVisibility(appBarLayout0, floatingActionButton0)) {
                return false;
            }
            if(this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect0 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout0, appBarLayout0, rect0);
            if(rect0.bottom <= appBarLayout0.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton0.hide(this.internalAutoHideListener, false);
                return true;
            }
            floatingActionButton0.show(this.internalAutoHideListener, false);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view0, FloatingActionButton floatingActionButton0) {
            if(!this.shouldUpdateVisibility(view0, floatingActionButton0)) {
                return false;
            }
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)floatingActionButton0.getLayoutParams();
            if(view0.getTop() < floatingActionButton0.getHeight() / 2 + coordinatorLayout$LayoutParams0.topMargin) {
                floatingActionButton0.hide(this.internalAutoHideListener, false);
                return true;
            }
            floatingActionButton0.show(this.internalAutoHideListener, false);
            return true;
        }
    }

    public static class com.google.android.material.floatingactionbutton.FloatingActionButton.Behavior extends BaseBehavior {
        public com.google.android.material.floatingactionbutton.FloatingActionButton.Behavior() {
        }

        public com.google.android.material.floatingactionbutton.FloatingActionButton.Behavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0, Rect rect0) {
            return super.getInsetDodgeRect(coordinatorLayout0, floatingActionButton0, rect0);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public boolean isAutoHideEnabled() {
            return super.isAutoHideEnabled();
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public void onAttachedToLayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
            super.onAttachedToLayoutParams(coordinatorLayout$LayoutParams0);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0, View view0) {
            return super.onDependentViewChanged(coordinatorLayout0, floatingActionButton0, view0);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, FloatingActionButton floatingActionButton0, int v) {
            return super.onLayoutChild(coordinatorLayout0, floatingActionButton0, v);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public void setAutoHideEnabled(boolean z) {
            super.setAutoHideEnabled(z);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButton$BaseBehavior
        public void setInternalAutoHideListener(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0) {
            super.setInternalAutoHideListener(floatingActionButton$OnVisibilityChangedListener0);
        }
    }

    public static abstract class OnVisibilityChangedListener {
        public void onHidden(FloatingActionButton floatingActionButton0) {
        }

        public void onShown(FloatingActionButton floatingActionButton0) {
        }
    }

    class ShadowDelegateImpl implements ShadowViewDelegate {
        @Override  // com.google.android.material.shadow.ShadowViewDelegate
        public float getRadius() {
            return ((float)FloatingActionButton.this.getSizeDimension()) / 2.0f;
        }

        @Override  // com.google.android.material.shadow.ShadowViewDelegate
        public boolean isCompatPaddingEnabled() {
            return FloatingActionButton.this.compatPadding;
        }

        @Override  // com.google.android.material.shadow.ShadowViewDelegate
        public void setBackgroundDrawable(Drawable drawable0) {
            if(drawable0 != null) {
                FloatingActionButton.this.super.setBackgroundDrawable(drawable0);
            }
        }

        @Override  // com.google.android.material.shadow.ShadowViewDelegate
        public void setShadowPadding(int v, int v1, int v2, int v3) {
            FloatingActionButton.this.shadowPadding.set(v, v1, v2, v3);
            int v4 = FloatingActionButton.this.imagePadding;
            int v5 = FloatingActionButton.this.imagePadding;
            int v6 = FloatingActionButton.this.imagePadding;
            int v7 = FloatingActionButton.this.imagePadding;
            FloatingActionButton.this.setPadding(v + v4, v1 + v5, v2 + v6, v3 + v7);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Size {
    }

    class TransformationCallbackWrapper implements InternalTransformationCallback {
        private final TransformationCallback listener;

        TransformationCallbackWrapper(TransformationCallback transformationCallback0) {
            this.listener = transformationCallback0;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof TransformationCallbackWrapper && ((TransformationCallbackWrapper)object0).listener.equals(this.listener);
        }

        @Override
        public int hashCode() {
            return this.listener.hashCode();
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$InternalTransformationCallback
        public void onScaleChanged() {
            this.listener.onScaleChanged(FloatingActionButton.this);
        }

        @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$InternalTransformationCallback
        public void onTranslationChanged() {
            this.listener.onTranslationChanged(FloatingActionButton.this);
        }
    }

    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final int DEF_STYLE_RES = 0;
    private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int NO_CUSTOM_SIZE = 0;
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    boolean compatPadding;
    private int customSize;
    private final ExpandableWidgetHelper expandableWidgetHelper;
    private final AppCompatImageHelper imageHelper;
    private PorterDuff.Mode imageMode;
    private int imagePadding;
    private ColorStateList imageTint;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;
    private ColorStateList rippleColor;
    final Rect shadowPadding;
    private int size;
    private final Rect touchArea;

    static {
        FloatingActionButton.DEF_STYLE_RES = style.Widget_Design_FloatingActionButton;
    }

    public FloatingActionButton(Context context0) {
        this(context0, null);
    }

    public FloatingActionButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.floatingActionButtonStyle);
    }

    public FloatingActionButton(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, FloatingActionButton.DEF_STYLE_RES), attributeSet0, v);
        this.shadowPadding = new Rect();
        this.touchArea = new Rect();
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.FloatingActionButton, v, FloatingActionButton.DEF_STYLE_RES, new int[0]);
        this.backgroundTint = MaterialResources.getColorStateList(context1, typedArray0, styleable.FloatingActionButton_backgroundTint);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArray0.getInt(styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.rippleColor = MaterialResources.getColorStateList(context1, typedArray0, styleable.FloatingActionButton_rippleColor);
        this.size = typedArray0.getInt(styleable.FloatingActionButton_fabSize, -1);
        this.customSize = typedArray0.getDimensionPixelSize(styleable.FloatingActionButton_fabCustomSize, 0);
        this.borderWidth = typedArray0.getDimensionPixelSize(styleable.FloatingActionButton_borderWidth, 0);
        float f = typedArray0.getDimension(styleable.FloatingActionButton_elevation, 0.0f);
        float f1 = typedArray0.getDimension(styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float f2 = typedArray0.getDimension(styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.compatPadding = typedArray0.getBoolean(styleable.FloatingActionButton_useCompatPadding, false);
        int v1 = this.getResources().getDimensionPixelSize(dimen.mtrl_fab_min_touch_target);
        this.setMaxImageSize(typedArray0.getDimensionPixelSize(styleable.FloatingActionButton_maxImageSize, 0));
        MotionSpec motionSpec0 = MotionSpec.createFromAttribute(context1, typedArray0, styleable.FloatingActionButton_showMotionSpec);
        MotionSpec motionSpec1 = MotionSpec.createFromAttribute(context1, typedArray0, styleable.FloatingActionButton_hideMotionSpec);
        ShapeAppearanceModel shapeAppearanceModel0 = ShapeAppearanceModel.builder(context1, attributeSet0, v, FloatingActionButton.DEF_STYLE_RES, ShapeAppearanceModel.PILL).build();
        boolean z = typedArray0.getBoolean(styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        this.setEnabled(typedArray0.getBoolean(styleable.FloatingActionButton_android_enabled, true));
        typedArray0.recycle();
        AppCompatImageHelper appCompatImageHelper0 = new AppCompatImageHelper(this);
        this.imageHelper = appCompatImageHelper0;
        appCompatImageHelper0.loadFromAttributes(attributeSet0, v);
        this.expandableWidgetHelper = new ExpandableWidgetHelper(this);
        this.getImpl().setShapeAppearance(shapeAppearanceModel0);
        this.getImpl().initializeBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
        this.getImpl().setMinTouchTargetSize(v1);
        this.getImpl().setElevation(f);
        this.getImpl().setHoveredFocusedTranslationZ(f1);
        this.getImpl().setPressedTranslationZ(f2);
        this.getImpl().setShowMotionSpec(motionSpec0);
        this.getImpl().setHideMotionSpec(motionSpec1);
        this.getImpl().setEnsureMinTouchTargetSize(z);
        this.setScaleType(ImageView.ScaleType.MATRIX);
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.getImpl().addOnHideAnimationListener(animator$AnimatorListener0);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.getImpl().addOnShowAnimationListener(animator$AnimatorListener0);
    }

    public void addTransformationCallback(TransformationCallback transformationCallback0) {
        this.getImpl().addTransformationCallback(new TransformationCallbackWrapper(this, transformationCallback0));
    }

    public void clearCustomSize() {
        this.setCustomSize(0);
    }

    private FloatingActionButtonImpl createImpl() {
        return new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl(this));
    }

    @Override  // android.widget.ImageView
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.getImpl().onDrawableStateChanged(this.getDrawableState());
    }

    @Override  // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    @Override  // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$AttachedBehavior
    public Behavior getBehavior() {
        return new com.google.android.material.floatingactionbutton.FloatingActionButton.Behavior();
    }

    public float getCompatElevation() {
        return this.getImpl().getElevation();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return this.getImpl().getHoveredFocusedTranslationZ();
    }

    public float getCompatPressedTranslationZ() {
        return this.getImpl().getPressedTranslationZ();
    }

    public Drawable getContentBackground() {
        return this.getImpl().getContentBackground();
    }

    @Deprecated
    public boolean getContentRect(Rect rect0) {
        if(ViewCompat.isLaidOut(this)) {
            rect0.set(0, 0, this.getWidth(), this.getHeight());
            this.offsetRectWithShadow(rect0);
            return true;
        }
        return false;
    }

    public int getCustomSize() {
        return this.customSize;
    }

    @Override  // com.google.android.material.expandable.ExpandableTransformationWidget
    public int getExpandedComponentIdHint() {
        return this.expandableWidgetHelper.getExpandedComponentIdHint();
    }

    public MotionSpec getHideMotionSpec() {
        return this.getImpl().getHideMotionSpec();
    }

    private FloatingActionButtonImpl getImpl() {
        if(this.impl == null) {
            this.impl = this.createImpl();
        }
        return this.impl;
    }

    public void getMeasuredContentRect(Rect rect0) {
        rect0.set(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        this.offsetRectWithShadow(rect0);
    }

    @Deprecated
    public int getRippleColor() {
        return this.rippleColor == null ? 0 : this.rippleColor.getDefaultColor();
    }

    public ColorStateList getRippleColorStateList() {
        return this.rippleColor;
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return (ShapeAppearanceModel)Preconditions.checkNotNull(this.getImpl().getShapeAppearance());
    }

    public MotionSpec getShowMotionSpec() {
        return this.getImpl().getShowMotionSpec();
    }

    public int getSize() {
        return this.size;
    }

    private int getSizeDimension(int v) {
        int v1 = this.customSize;
        if(v1 != 0) {
            return v1;
        }
        Resources resources0 = this.getResources();
        switch(v) {
            case -1: {
                return Math.max(resources0.getConfiguration().screenWidthDp, resources0.getConfiguration().screenHeightDp) >= 470 ? this.getSizeDimension(0) : this.getSizeDimension(1);
            }
            case 1: {
                return resources0.getDimensionPixelSize(dimen.design_fab_size_mini);
            }
            default: {
                return resources0.getDimensionPixelSize(dimen.design_fab_size_normal);
            }
        }
    }

    int getSizeDimension() {
        return this.getSizeDimension(this.size);
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public ColorStateList getSupportBackgroundTintList() {
        return this.getBackgroundTintList();
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.getBackgroundTintMode();
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public ColorStateList getSupportImageTintList() {
        return this.imageTint;
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.imageMode;
    }

    private void getTouchTargetRect(Rect rect0) {
        this.getMeasuredContentRect(rect0);
        int v = this.impl.getTouchTargetPadding();
        rect0.inset(-v, -v);
    }

    public boolean getUseCompatPadding() {
        return this.compatPadding;
    }

    public void hide() {
        this.hide(null);
    }

    public void hide(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0) {
        this.hide(floatingActionButton$OnVisibilityChangedListener0, true);
    }

    void hide(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0, boolean z) {
        this.getImpl().hide(this.wrapOnVisibilityChangedListener(floatingActionButton$OnVisibilityChangedListener0), z);
    }

    @Override  // com.google.android.material.expandable.ExpandableWidget
    public boolean isExpanded() {
        return this.expandableWidgetHelper.isExpanded();
    }

    public boolean isOrWillBeHidden() {
        return this.getImpl().isOrWillBeHidden();
    }

    public boolean isOrWillBeShown() {
        return this.getImpl().isOrWillBeShown();
    }

    @Override  // android.widget.ImageView
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.getImpl().jumpDrawableToCurrentState();
    }

    private void offsetRectWithShadow(Rect rect0) {
        rect0.left += this.shadowPadding.left;
        rect0.top += this.shadowPadding.top;
        rect0.right -= this.shadowPadding.right;
        rect0.bottom -= this.shadowPadding.bottom;
    }

    private void onApplySupportImageTint() {
        Drawable drawable0 = this.getDrawable();
        if(drawable0 == null) {
            return;
        }
        ColorStateList colorStateList0 = this.imageTint;
        if(colorStateList0 == null) {
            DrawableCompat.clearColorFilter(drawable0);
            return;
        }
        int v = colorStateList0.getColorForState(this.getDrawableState(), 0);
        PorterDuff.Mode porterDuff$Mode0 = this.imageMode == null ? PorterDuff.Mode.SRC_IN : this.imageMode;
        drawable0.mutate().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(v, porterDuff$Mode0));
    }

    @Override  // android.widget.ImageView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getImpl().onAttachedToWindow();
    }

    @Override  // android.widget.ImageView
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getImpl().onDetachedFromWindow();
    }

    @Override  // android.widget.ImageView
    protected void onMeasure(int v, int v1) {
        int v2 = this.getSizeDimension();
        this.imagePadding = (v2 - this.maxImageSize) / 2;
        this.getImpl().updatePadding();
        int v3 = Math.min(View.resolveSize(v2, v), View.resolveSize(v2, v1));
        this.setMeasuredDimension(this.shadowPadding.left + v3 + this.shadowPadding.right, v3 + this.shadowPadding.top + this.shadowPadding.bottom);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((ExtendableSavedState)parcelable0).getSuperState());
        Bundle bundle0 = (Bundle)Preconditions.checkNotNull(((Bundle)((ExtendableSavedState)parcelable0).extendableStates.get("expandableWidgetHelper")));
        this.expandableWidgetHelper.onRestoreInstanceState(bundle0);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = super.onSaveInstanceState();
        if(parcelable0 == null) {
            parcelable0 = new Bundle();
        }
        Parcelable parcelable1 = new ExtendableSavedState(parcelable0);
        Bundle bundle0 = this.expandableWidgetHelper.onSaveInstanceState();
        parcelable1.extendableStates.put("expandableWidgetHelper", bundle0);
        return parcelable1;
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(motionEvent0.getAction() == 0) {
            this.getTouchTargetRect(this.touchArea);
            int v = (int)motionEvent0.getX();
            int v1 = (int)motionEvent0.getY();
            return this.touchArea.contains(v, v1) ? super.onTouchEvent(motionEvent0) : false;
        }
        return super.onTouchEvent(motionEvent0);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.getImpl().removeOnHideAnimationListener(animator$AnimatorListener0);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.getImpl().removeOnShowAnimationListener(animator$AnimatorListener0);
    }

    public void removeTransformationCallback(TransformationCallback transformationCallback0) {
        this.getImpl().removeTransformationCallback(new TransformationCallbackWrapper(this, transformationCallback0));
    }

    @Override  // android.view.View
    public void setBackgroundColor(int v) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override  // android.view.View
    public void setBackgroundDrawable(Drawable drawable0) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override  // android.view.View
    public void setBackgroundResource(int v) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override  // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList0) {
        if(this.backgroundTint != colorStateList0) {
            this.backgroundTint = colorStateList0;
            this.getImpl().setBackgroundTintList(colorStateList0);
        }
    }

    @Override  // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.backgroundTintMode != porterDuff$Mode0) {
            this.backgroundTintMode = porterDuff$Mode0;
            this.getImpl().setBackgroundTintMode(porterDuff$Mode0);
        }
    }

    public void setCompatElevation(float f) {
        this.getImpl().setElevation(f);
    }

    public void setCompatElevationResource(int v) {
        this.setCompatElevation(this.getResources().getDimension(v));
    }

    public void setCompatHoveredFocusedTranslationZ(float f) {
        this.getImpl().setHoveredFocusedTranslationZ(f);
    }

    public void setCompatHoveredFocusedTranslationZResource(int v) {
        this.setCompatHoveredFocusedTranslationZ(this.getResources().getDimension(v));
    }

    public void setCompatPressedTranslationZ(float f) {
        this.getImpl().setPressedTranslationZ(f);
    }

    public void setCompatPressedTranslationZResource(int v) {
        this.setCompatPressedTranslationZ(this.getResources().getDimension(v));
    }

    public void setCustomSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if(v != this.customSize) {
            this.customSize = v;
            this.requestLayout();
        }
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        this.getImpl().updateShapeElevation(f);
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if(z != this.getImpl().getEnsureMinTouchTargetSize()) {
            this.getImpl().setEnsureMinTouchTargetSize(z);
            this.requestLayout();
        }
    }

    @Override  // com.google.android.material.expandable.ExpandableWidget
    public boolean setExpanded(boolean z) {
        return this.expandableWidgetHelper.setExpanded(z);
    }

    @Override  // com.google.android.material.expandable.ExpandableTransformationWidget
    public void setExpandedComponentIdHint(int v) {
        this.expandableWidgetHelper.setExpandedComponentIdHint(v);
    }

    public void setHideMotionSpec(MotionSpec motionSpec0) {
        this.getImpl().setHideMotionSpec(motionSpec0);
    }

    public void setHideMotionSpecResource(int v) {
        this.setHideMotionSpec(MotionSpec.createFromResource(this.getContext(), v));
    }

    @Override  // android.widget.ImageView
    public void setImageDrawable(Drawable drawable0) {
        if(this.getDrawable() != drawable0) {
            super.setImageDrawable(drawable0);
            this.getImpl().updateImageMatrixScale();
            if(this.imageTint != null) {
                this.onApplySupportImageTint();
            }
        }
    }

    @Override  // android.widget.ImageView
    public void setImageResource(int v) {
        this.imageHelper.setImageResource(v);
        this.onApplySupportImageTint();
    }

    public void setMaxImageSize(int v) {
        this.maxImageSize = v;
        this.getImpl().setMaxImageSize(v);
    }

    public void setRippleColor(int v) {
        this.setRippleColor(ColorStateList.valueOf(v));
    }

    public void setRippleColor(ColorStateList colorStateList0) {
        if(this.rippleColor != colorStateList0) {
            this.rippleColor = colorStateList0;
            this.getImpl().setRippleColor(this.rippleColor);
        }
    }

    @Override  // android.view.View
    public void setScaleX(float f) {
        super.setScaleX(f);
        this.getImpl().onScaleChanged();
    }

    @Override  // android.view.View
    public void setScaleY(float f) {
        super.setScaleY(f);
        this.getImpl().onScaleChanged();
    }

    public void setShadowPaddingEnabled(boolean z) {
        this.getImpl().setShadowPaddingEnabled(z);
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.getImpl().setShapeAppearance(shapeAppearanceModel0);
    }

    public void setShowMotionSpec(MotionSpec motionSpec0) {
        this.getImpl().setShowMotionSpec(motionSpec0);
    }

    public void setShowMotionSpecResource(int v) {
        this.setShowMotionSpec(MotionSpec.createFromResource(this.getContext(), v));
    }

    public void setSize(int v) {
        this.customSize = 0;
        if(v != this.size) {
            this.size = v;
            this.requestLayout();
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        this.setBackgroundTintList(colorStateList0);
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.setBackgroundTintMode(porterDuff$Mode0);
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public void setSupportImageTintList(ColorStateList colorStateList0) {
        if(this.imageTint != colorStateList0) {
            this.imageTint = colorStateList0;
            this.onApplySupportImageTint();
        }
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public void setSupportImageTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.imageMode != porterDuff$Mode0) {
            this.imageMode = porterDuff$Mode0;
            this.onApplySupportImageTint();
        }
    }

    @Override  // android.view.View
    public void setTranslationX(float f) {
        super.setTranslationX(f);
        this.getImpl().onTranslationChanged();
    }

    @Override  // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        this.getImpl().onTranslationChanged();
    }

    @Override  // android.view.View
    public void setTranslationZ(float f) {
        super.setTranslationZ(f);
        this.getImpl().onTranslationChanged();
    }

    public void setUseCompatPadding(boolean z) {
        if(this.compatPadding != z) {
            this.compatPadding = z;
            this.getImpl().onCompatShadowChanged();
        }
    }

    @Override  // com.google.android.material.internal.VisibilityAwareImageButton
    public void setVisibility(int v) {
        super.setVisibility(v);
    }

    public boolean shouldEnsureMinTouchTargetSize() {
        return this.getImpl().getEnsureMinTouchTargetSize();
    }

    public void show() {
        this.show(null);
    }

    public void show(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0) {
        this.show(floatingActionButton$OnVisibilityChangedListener0, true);
    }

    void show(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0, boolean z) {
        this.getImpl().show(this.wrapOnVisibilityChangedListener(floatingActionButton$OnVisibilityChangedListener0), z);
    }

    private InternalVisibilityChangedListener wrapOnVisibilityChangedListener(OnVisibilityChangedListener floatingActionButton$OnVisibilityChangedListener0) {
        return floatingActionButton$OnVisibilityChangedListener0 == null ? null : new InternalVisibilityChangedListener() {
            @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$InternalVisibilityChangedListener
            public void onHidden() {
                floatingActionButton$OnVisibilityChangedListener0.onHidden(FloatingActionButton.this);
            }

            @Override  // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$InternalVisibilityChangedListener
            public void onShown() {
                floatingActionButton$OnVisibilityChangedListener0.onShown(FloatingActionButton.this);
            }
        };
    }
}

