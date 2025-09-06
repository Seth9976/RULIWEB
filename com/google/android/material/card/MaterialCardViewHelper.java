package com.google.android.material.card;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel.Builder;
import com.google.android.material.shape.ShapeAppearanceModel;

class MaterialCardViewHelper {
    private static final float CARD_VIEW_SHADOW_MULTIPLIER = 1.5f;
    private static final int CHECKED_ICON_LAYER_INDEX = 2;
    private static final Drawable CHECKED_ICON_NONE = null;
    private static final double COS_45 = 0.0;
    public static final int DEFAULT_FADE_ANIM_DURATION = 300;
    private static final int DEFAULT_STROKE_VALUE = -1;
    private final MaterialShapeDrawable bgDrawable;
    private boolean checkable;
    private float checkedAnimationProgress;
    private Drawable checkedIcon;
    private int checkedIconGravity;
    private int checkedIconMargin;
    private int checkedIconSize;
    private ColorStateList checkedIconTint;
    private LayerDrawable clickableForegroundDrawable;
    private MaterialShapeDrawable compatRippleDrawable;
    private Drawable fgDrawable;
    private final MaterialShapeDrawable foregroundContentDrawable;
    private MaterialShapeDrawable foregroundShapeDrawable;
    private ValueAnimator iconAnimator;
    private final TimeInterpolator iconFadeAnimInterpolator;
    private final int iconFadeInAnimDuration;
    private final int iconFadeOutAnimDuration;
    private boolean isBackgroundOverwritten;
    private final MaterialCardView materialCardView;
    private ColorStateList rippleColor;
    private Drawable rippleDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    private ColorStateList strokeColor;
    private int strokeWidth;
    private final Rect userContentPadding;

    static {
        MaterialCardViewHelper.COS_45 = 0.707107;
        MaterialCardViewHelper.CHECKED_ICON_NONE = Build.VERSION.SDK_INT > 28 ? null : new ColorDrawable();
    }

    public MaterialCardViewHelper(MaterialCardView materialCardView0, AttributeSet attributeSet0, int v, int v1) {
        this.userContentPadding = new Rect();
        this.isBackgroundOverwritten = false;
        this.checkedAnimationProgress = 0.0f;
        this.materialCardView = materialCardView0;
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(materialCardView0.getContext(), attributeSet0, v, v1);
        this.bgDrawable = materialShapeDrawable0;
        materialShapeDrawable0.initializeElevationOverlay(materialCardView0.getContext());
        materialShapeDrawable0.setShadowColor(-12303292);
        Builder shapeAppearanceModel$Builder0 = materialShapeDrawable0.getShapeAppearanceModel().toBuilder();
        TypedArray typedArray0 = materialCardView0.getContext().obtainStyledAttributes(attributeSet0, styleable.CardView, v, style.CardView);
        if(typedArray0.hasValue(styleable.CardView_cardCornerRadius)) {
            shapeAppearanceModel$Builder0.setAllCornerSizes(typedArray0.getDimension(styleable.CardView_cardCornerRadius, 0.0f));
        }
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        this.setShapeAppearanceModel(shapeAppearanceModel$Builder0.build());
        this.iconFadeAnimInterpolator = MotionUtils.resolveThemeInterpolator(materialCardView0.getContext(), attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.iconFadeInAnimDuration = MotionUtils.resolveThemeDuration(materialCardView0.getContext(), attr.motionDurationShort2, 300);
        this.iconFadeOutAnimDuration = MotionUtils.resolveThemeDuration(materialCardView0.getContext(), attr.motionDurationShort1, 300);
        typedArray0.recycle();
    }

    public void animateCheckedIcon(boolean z) {
        float f = z ? 1.0f - this.checkedAnimationProgress : this.checkedAnimationProgress;
        ValueAnimator valueAnimator0 = this.iconAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.cancel();
            this.iconAnimator = null;
        }
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{this.checkedAnimationProgress, (z ? 1.0f : 0.0f)});
        this.iconAnimator = valueAnimator1;
        valueAnimator1.addUpdateListener((ValueAnimator valueAnimator0) -> {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            this.checkedIcon.setAlpha(((int)(255.0f * f)));
            this.checkedAnimationProgress = f;
        });
        this.iconAnimator.setInterpolator(this.iconFadeAnimInterpolator);
        this.iconAnimator.setDuration(((long)(((float)(z ? this.iconFadeInAnimDuration : this.iconFadeOutAnimDuration)) * f)));
        this.iconAnimator.start();
    }

    private float calculateActualCornerPadding() {
        return Math.max(Math.max(this.calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getTopLeftCorner(), this.bgDrawable.getTopLeftCornerResolvedSize()), this.calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getTopRightCorner(), this.bgDrawable.getTopRightCornerResolvedSize())), Math.max(this.calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getBottomRightCorner(), this.bgDrawable.getBottomRightCornerResolvedSize()), this.calculateCornerPaddingForCornerTreatment(this.shapeAppearanceModel.getBottomLeftCorner(), this.bgDrawable.getBottomLeftCornerResolvedSize())));
    }

    private float calculateCornerPaddingForCornerTreatment(CornerTreatment cornerTreatment0, float f) {
        if(cornerTreatment0 instanceof RoundedCornerTreatment) {
            return (float)((1.0 - MaterialCardViewHelper.COS_45) * ((double)f));
        }
        return cornerTreatment0 instanceof CutCornerTreatment ? f / 2.0f : 0.0f;
    }

    private float calculateHorizontalBackgroundPadding() {
        float f = this.materialCardView.getMaxCardElevation();
        return this.shouldAddCornerPaddingOutsideCardBackground() ? f + this.calculateActualCornerPadding() : f + 0.0f;
    }

    private float calculateVerticalBackgroundPadding() {
        float f = this.materialCardView.getMaxCardElevation();
        return this.shouldAddCornerPaddingOutsideCardBackground() ? f * 1.5f + this.calculateActualCornerPadding() : f * 1.5f + 0.0f;
    }

    private boolean canClipToOutline() {
        return this.bgDrawable.isRoundRect();
    }

    private Drawable createCompatRippleDrawable() {
        Drawable drawable0 = new StateListDrawable();
        MaterialShapeDrawable materialShapeDrawable0 = this.createForegroundShapeDrawable();
        this.compatRippleDrawable = materialShapeDrawable0;
        materialShapeDrawable0.setFillColor(this.rippleColor);
        ((StateListDrawable)drawable0).addState(new int[]{0x10100A7}, this.compatRippleDrawable);
        return drawable0;
    }

    private Drawable createForegroundRippleDrawable() {
        if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
            this.foregroundShapeDrawable = this.createForegroundShapeDrawable();
            return new RippleDrawable(this.rippleColor, null, this.foregroundShapeDrawable);
        }
        return this.createCompatRippleDrawable();
    }

    private MaterialShapeDrawable createForegroundShapeDrawable() {
        return new MaterialShapeDrawable(this.shapeAppearanceModel);
    }

    void forceRippleRedraw() {
        Drawable drawable0 = this.rippleDrawable;
        if(drawable0 != null) {
            Rect rect0 = drawable0.getBounds();
            int v = rect0.bottom;
            this.rippleDrawable.setBounds(rect0.left, rect0.top, rect0.right, v - 1);
            this.rippleDrawable.setBounds(rect0.left, rect0.top, rect0.right, v);
        }
    }

    MaterialShapeDrawable getBackground() {
        return this.bgDrawable;
    }

    ColorStateList getCardBackgroundColor() {
        return this.bgDrawable.getFillColor();
    }

    ColorStateList getCardForegroundColor() {
        return this.foregroundContentDrawable.getFillColor();
    }

    Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    int getCheckedIconGravity() {
        return this.checkedIconGravity;
    }

    int getCheckedIconMargin() {
        return this.checkedIconMargin;
    }

    int getCheckedIconSize() {
        return this.checkedIconSize;
    }

    ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    private Drawable getClickableForeground() {
        if(this.rippleDrawable == null) {
            this.rippleDrawable = this.createForegroundRippleDrawable();
        }
        if(this.clickableForegroundDrawable == null) {
            LayerDrawable layerDrawable0 = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, this.checkedIcon});
            this.clickableForegroundDrawable = layerDrawable0;
            layerDrawable0.setId(2, id.mtrl_card_checked_layer_id);
        }
        return this.clickableForegroundDrawable;
    }

    float getCornerRadius() {
        return this.bgDrawable.getTopLeftCornerResolvedSize();
    }

    private float getParentCardViewCalculatedCornerPadding() {
        if(this.materialCardView.getPreventCornerOverlap() && this.materialCardView.getUseCompatPadding()) {
            double f = (double)this.materialCardView.getCardViewRadius();
            return (float)((1.0 - MaterialCardViewHelper.COS_45) * f);
        }
        return 0.0f;
    }

    float getProgress() {
        return this.bgDrawable.getInterpolation();
    }

    ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    int getStrokeColor() {
        return this.strokeColor == null ? -1 : this.strokeColor.getDefaultColor();
    }

    ColorStateList getStrokeColorStateList() {
        return this.strokeColor;
    }

    int getStrokeWidth() {
        return this.strokeWidth;
    }

    Rect getUserContentPadding() {
        return this.userContentPadding;
    }

    private Drawable insetDrawable(Drawable drawable0) {
        if(this.materialCardView.getUseCompatPadding()) {
            int v = (int)Math.ceil(this.calculateVerticalBackgroundPadding());
            int v1 = (int)Math.ceil(this.calculateHorizontalBackgroundPadding());
            return new InsetDrawable(drawable0, v1, v, v1, v) {
                @Override  // android.graphics.drawable.Drawable
                public int getMinimumHeight() {
                    return -1;
                }

                @Override  // android.graphics.drawable.Drawable
                public int getMinimumWidth() {
                    return -1;
                }

                @Override  // android.graphics.drawable.InsetDrawable
                public boolean getPadding(Rect rect0) {
                    return false;
                }
            };
        }
        return new InsetDrawable(drawable0, 0, 0, 0, 0) {
            @Override  // android.graphics.drawable.Drawable
            public int getMinimumHeight() {
                return -1;
            }

            @Override  // android.graphics.drawable.Drawable
            public int getMinimumWidth() {
                return -1;
            }

            @Override  // android.graphics.drawable.InsetDrawable
            public boolean getPadding(Rect rect0) {
                return false;
            }
        };
    }

    boolean isBackgroundOverwritten() {
        return this.isBackgroundOverwritten;
    }

    boolean isCheckable() {
        return this.checkable;
    }

    private boolean isCheckedIconBottom() {
        return (this.checkedIconGravity & 80) == 80;
    }

    private boolean isCheckedIconEnd() {
        return (this.checkedIconGravity & 0x800005) == 0x800005;
    }

    // 检测为 Lambda 实现
    void lambda$animateCheckedIcon$0$com-google-android-material-card-MaterialCardViewHelper(ValueAnimator valueAnimator0) [...]

    void loadFromAttributes(TypedArray typedArray0) {
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray0, styleable.MaterialCardView_strokeColor);
        this.strokeColor = colorStateList0;
        if(colorStateList0 == null) {
            this.strokeColor = ColorStateList.valueOf(-1);
        }
        this.strokeWidth = typedArray0.getDimensionPixelSize(styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray0.getBoolean(styleable.MaterialCardView_android_checkable, false);
        this.checkable = z;
        this.materialCardView.setLongClickable(z);
        this.checkedIconTint = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray0, styleable.MaterialCardView_checkedIconTint);
        this.setCheckedIcon(MaterialResources.getDrawable(this.materialCardView.getContext(), typedArray0, styleable.MaterialCardView_checkedIcon));
        this.setCheckedIconSize(typedArray0.getDimensionPixelSize(styleable.MaterialCardView_checkedIconSize, 0));
        this.setCheckedIconMargin(typedArray0.getDimensionPixelSize(styleable.MaterialCardView_checkedIconMargin, 0));
        this.checkedIconGravity = typedArray0.getInteger(styleable.MaterialCardView_checkedIconGravity, 0x800035);
        ColorStateList colorStateList1 = MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray0, styleable.MaterialCardView_rippleColor);
        this.rippleColor = colorStateList1;
        if(colorStateList1 == null) {
            this.rippleColor = ColorStateList.valueOf(MaterialColors.getColor(this.materialCardView, attr.colorControlHighlight));
        }
        this.setCardForegroundColor(MaterialResources.getColorStateList(this.materialCardView.getContext(), typedArray0, styleable.MaterialCardView_cardForegroundColor));
        this.updateRippleColor();
        this.updateElevation();
        this.updateStroke();
        Drawable drawable0 = this.insetDrawable(this.bgDrawable);
        this.materialCardView.setBackgroundInternal(drawable0);
        Drawable drawable1 = this.shouldUseClickableForeground() ? this.getClickableForeground() : this.foregroundContentDrawable;
        this.fgDrawable = drawable1;
        Drawable drawable2 = this.insetDrawable(drawable1);
        this.materialCardView.setForeground(drawable2);
    }

    void recalculateCheckedIconPosition(int v, int v1) {
        int v9;
        int v8;
        int v3;
        int v2;
        if(this.clickableForegroundDrawable != null) {
            if(this.materialCardView.getUseCompatPadding()) {
                v2 = (int)Math.ceil(this.calculateVerticalBackgroundPadding() * 2.0f);
                v3 = (int)Math.ceil(this.calculateHorizontalBackgroundPadding() * 2.0f);
            }
            else {
                v2 = 0;
                v3 = 0;
            }
            int v4 = this.isCheckedIconEnd() ? v - this.checkedIconMargin - this.checkedIconSize - v3 : this.checkedIconMargin;
            int v5 = this.isCheckedIconBottom() ? this.checkedIconMargin : v1 - this.checkedIconMargin - this.checkedIconSize - v2;
            int v6 = this.isCheckedIconEnd() ? this.checkedIconMargin : v - this.checkedIconMargin - this.checkedIconSize - v3;
            int v7 = this.isCheckedIconBottom() ? v1 - this.checkedIconMargin - this.checkedIconSize - v2 : this.checkedIconMargin;
            if(ViewCompat.getLayoutDirection(this.materialCardView) == 1) {
                v8 = v6;
                v9 = v4;
            }
            else {
                v9 = v6;
                v8 = v4;
            }
            this.clickableForegroundDrawable.setLayerInset(2, v8, v7, v9, v5);
        }
    }

    void setBackgroundOverwritten(boolean z) {
        this.isBackgroundOverwritten = z;
    }

    void setCardBackgroundColor(ColorStateList colorStateList0) {
        this.bgDrawable.setFillColor(colorStateList0);
    }

    void setCardForegroundColor(ColorStateList colorStateList0) {
        MaterialShapeDrawable materialShapeDrawable0 = this.foregroundContentDrawable;
        if(colorStateList0 == null) {
            colorStateList0 = ColorStateList.valueOf(0);
        }
        materialShapeDrawable0.setFillColor(colorStateList0);
    }

    void setCheckable(boolean z) {
        this.checkable = z;
    }

    public void setChecked(boolean z) {
        this.setChecked(z, false);
    }

    public void setChecked(boolean z, boolean z1) {
        Drawable drawable0 = this.checkedIcon;
        if(drawable0 != null) {
            if(z1) {
                this.animateCheckedIcon(z);
                return;
            }
            drawable0.setAlpha((z ? 0xFF : 0));
            this.checkedAnimationProgress = z ? 1.0f : 0.0f;
        }
    }

    void setCheckedIcon(Drawable drawable0) {
        if(drawable0 == null) {
            this.checkedIcon = MaterialCardViewHelper.CHECKED_ICON_NONE;
        }
        else {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            this.checkedIcon = drawable1;
            DrawableCompat.setTintList(drawable1, this.checkedIconTint);
            this.setChecked(this.materialCardView.isChecked());
        }
        LayerDrawable layerDrawable0 = this.clickableForegroundDrawable;
        if(layerDrawable0 != null) {
            layerDrawable0.setDrawableByLayerId(id.mtrl_card_checked_layer_id, this.checkedIcon);
        }
    }

    void setCheckedIconGravity(int v) {
        this.checkedIconGravity = v;
        this.recalculateCheckedIconPosition(this.materialCardView.getMeasuredWidth(), this.materialCardView.getMeasuredHeight());
    }

    void setCheckedIconMargin(int v) {
        this.checkedIconMargin = v;
    }

    void setCheckedIconSize(int v) {
        this.checkedIconSize = v;
    }

    void setCheckedIconTint(ColorStateList colorStateList0) {
        this.checkedIconTint = colorStateList0;
        Drawable drawable0 = this.checkedIcon;
        if(drawable0 != null) {
            DrawableCompat.setTintList(drawable0, colorStateList0);
        }
    }

    void setCornerRadius(float f) {
        this.setShapeAppearanceModel(this.shapeAppearanceModel.withCornerSize(f));
        this.fgDrawable.invalidateSelf();
        if(this.shouldAddCornerPaddingOutsideCardBackground() || this.shouldAddCornerPaddingInsideCardBackground()) {
            this.updateContentPadding();
        }
        if(this.shouldAddCornerPaddingOutsideCardBackground()) {
            this.updateInsets();
        }
    }

    void setProgress(float f) {
        this.bgDrawable.setInterpolation(f);
        MaterialShapeDrawable materialShapeDrawable0 = this.foregroundContentDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setInterpolation(f);
        }
        MaterialShapeDrawable materialShapeDrawable1 = this.foregroundShapeDrawable;
        if(materialShapeDrawable1 != null) {
            materialShapeDrawable1.setInterpolation(f);
        }
    }

    void setRippleColor(ColorStateList colorStateList0) {
        this.rippleColor = colorStateList0;
        this.updateRippleColor();
    }

    void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.shapeAppearanceModel = shapeAppearanceModel0;
        this.bgDrawable.setShapeAppearanceModel(shapeAppearanceModel0);
        boolean z = this.bgDrawable.isRoundRect();
        this.bgDrawable.setShadowBitmapDrawingEnable(!z);
        MaterialShapeDrawable materialShapeDrawable0 = this.foregroundContentDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
        }
        MaterialShapeDrawable materialShapeDrawable1 = this.foregroundShapeDrawable;
        if(materialShapeDrawable1 != null) {
            materialShapeDrawable1.setShapeAppearanceModel(shapeAppearanceModel0);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.compatRippleDrawable;
        if(materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel0);
        }
    }

    void setStrokeColor(ColorStateList colorStateList0) {
        if(this.strokeColor == colorStateList0) {
            return;
        }
        this.strokeColor = colorStateList0;
        this.updateStroke();
    }

    void setStrokeWidth(int v) {
        if(v == this.strokeWidth) {
            return;
        }
        this.strokeWidth = v;
        this.updateStroke();
    }

    void setUserContentPadding(int v, int v1, int v2, int v3) {
        this.userContentPadding.set(v, v1, v2, v3);
        this.updateContentPadding();
    }

    // 去混淆评级： 低(20)
    private boolean shouldAddCornerPaddingInsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && !this.canClipToOutline();
    }

    // 去混淆评级： 低(30)
    private boolean shouldAddCornerPaddingOutsideCardBackground() {
        return this.materialCardView.getPreventCornerOverlap() && this.canClipToOutline() && this.materialCardView.getUseCompatPadding();
    }

    private boolean shouldUseClickableForeground() {
        if(this.materialCardView.isClickable()) {
            return true;
        }
        View view0;
        for(view0 = this.materialCardView; view0.isDuplicateParentStateEnabled() && view0.getParent() instanceof View; view0 = (View)view0.getParent()) {
        }
        return view0.isClickable();
    }

    void updateClickable() {
        Drawable drawable0 = this.fgDrawable;
        Drawable drawable1 = this.shouldUseClickableForeground() ? this.getClickableForeground() : this.foregroundContentDrawable;
        this.fgDrawable = drawable1;
        if(drawable0 != drawable1) {
            this.updateInsetForeground(drawable1);
        }
    }

    void updateContentPadding() {
        int v = (int)((this.shouldAddCornerPaddingInsideCardBackground() || this.shouldAddCornerPaddingOutsideCardBackground() ? this.calculateActualCornerPadding() : 0.0f) - this.getParentCardViewCalculatedCornerPadding());
        this.materialCardView.setAncestorContentPadding(this.userContentPadding.left + v, this.userContentPadding.top + v, this.userContentPadding.right + v, this.userContentPadding.bottom + v);
    }

    void updateElevation() {
        float f = this.materialCardView.getCardElevation();
        this.bgDrawable.setElevation(f);
    }

    private void updateInsetForeground(Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 23 && this.materialCardView.getForeground() instanceof InsetDrawable) {
            ((InsetDrawable)this.materialCardView.getForeground()).setDrawable(drawable0);
            return;
        }
        Drawable drawable1 = this.insetDrawable(drawable0);
        this.materialCardView.setForeground(drawable1);
    }

    void updateInsets() {
        if(!this.isBackgroundOverwritten()) {
            Drawable drawable0 = this.insetDrawable(this.bgDrawable);
            this.materialCardView.setBackgroundInternal(drawable0);
        }
        Drawable drawable1 = this.insetDrawable(this.fgDrawable);
        this.materialCardView.setForeground(drawable1);
    }

    private void updateRippleColor() {
        if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
            Drawable drawable0 = this.rippleDrawable;
            if(drawable0 != null) {
                ((RippleDrawable)drawable0).setColor(this.rippleColor);
                return;
            }
        }
        MaterialShapeDrawable materialShapeDrawable0 = this.compatRippleDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setFillColor(this.rippleColor);
        }
    }

    void updateStroke() {
        this.foregroundContentDrawable.setStroke(((float)this.strokeWidth), this.strokeColor);
    }
}

