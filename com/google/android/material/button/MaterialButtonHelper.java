package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;

class MaterialButtonHelper {
    private static final boolean IS_LOLLIPOP;
    private static final boolean IS_MIN_LOLLIPOP;
    private boolean backgroundOverwritten;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private boolean checkable;
    private int cornerRadius;
    private boolean cornerRadiusSet;
    private int elevation;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private Drawable maskDrawable;
    private final MaterialButton materialButton;
    private ColorStateList rippleColor;
    private LayerDrawable rippleDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    private boolean shouldDrawSurfaceColorStroke;
    private ColorStateList strokeColor;
    private int strokeWidth;
    private boolean toggleCheckedStateOnClick;

    static {
        boolean z = true;
        MaterialButtonHelper.IS_MIN_LOLLIPOP = true;
        if(Build.VERSION.SDK_INT > 22) {
            z = false;
        }
        MaterialButtonHelper.IS_LOLLIPOP = z;
    }

    MaterialButtonHelper(MaterialButton materialButton0, ShapeAppearanceModel shapeAppearanceModel0) {
        this.shouldDrawSurfaceColorStroke = false;
        this.backgroundOverwritten = false;
        this.cornerRadiusSet = false;
        this.toggleCheckedStateOnClick = true;
        this.materialButton = materialButton0;
        this.shapeAppearanceModel = shapeAppearanceModel0;
    }

    private Drawable createBackground() {
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(this.shapeAppearanceModel);
        materialShapeDrawable0.initializeElevationOverlay(this.materialButton.getContext());
        DrawableCompat.setTintList(materialShapeDrawable0, this.backgroundTint);
        PorterDuff.Mode porterDuff$Mode0 = this.backgroundTintMode;
        if(porterDuff$Mode0 != null) {
            DrawableCompat.setTintMode(materialShapeDrawable0, porterDuff$Mode0);
        }
        materialShapeDrawable0.setStroke(((float)this.strokeWidth), this.strokeColor);
        MaterialShapeDrawable materialShapeDrawable1 = new MaterialShapeDrawable(this.shapeAppearanceModel);
        materialShapeDrawable1.setTint(0);
        materialShapeDrawable1.setStroke(((float)this.strokeWidth), (this.shouldDrawSurfaceColorStroke ? MaterialColors.getColor(this.materialButton, attr.colorSurface) : 0));
        if(MaterialButtonHelper.IS_MIN_LOLLIPOP) {
            MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.maskDrawable = materialShapeDrawable2;
            DrawableCompat.setTint(materialShapeDrawable2, -1);
            LayerDrawable layerDrawable0 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.rippleColor), this.wrapDrawableWithInset(new LayerDrawable(new Drawable[]{materialShapeDrawable1, materialShapeDrawable0})), this.maskDrawable);
            this.rippleDrawable = layerDrawable0;
            return layerDrawable0;
        }
        RippleDrawableCompat rippleDrawableCompat0 = new RippleDrawableCompat(this.shapeAppearanceModel);
        this.maskDrawable = rippleDrawableCompat0;
        DrawableCompat.setTintList(rippleDrawableCompat0, RippleUtils.sanitizeRippleDrawableColor(this.rippleColor));
        LayerDrawable layerDrawable1 = new LayerDrawable(new Drawable[]{materialShapeDrawable1, materialShapeDrawable0, this.maskDrawable});
        this.rippleDrawable = layerDrawable1;
        return this.wrapDrawableWithInset(layerDrawable1);
    }

    int getCornerRadius() {
        return this.cornerRadius;
    }

    public int getInsetBottom() {
        return this.insetBottom;
    }

    public int getInsetTop() {
        return this.insetTop;
    }

    public Shapeable getMaskDrawable() {
        if(this.rippleDrawable != null && this.rippleDrawable.getNumberOfLayers() > 1) {
            return this.rippleDrawable.getNumberOfLayers() <= 2 ? ((Shapeable)this.rippleDrawable.getDrawable(1)) : ((Shapeable)this.rippleDrawable.getDrawable(2));
        }
        return null;
    }

    private MaterialShapeDrawable getMaterialShapeDrawable(boolean z) {
        if(this.rippleDrawable != null && this.rippleDrawable.getNumberOfLayers() > 0) {
            return MaterialButtonHelper.IS_MIN_LOLLIPOP ? ((MaterialShapeDrawable)((LayerDrawable)((InsetDrawable)this.rippleDrawable.getDrawable(0)).getDrawable()).getDrawable(!z)) : ((MaterialShapeDrawable)this.rippleDrawable.getDrawable(!z));
        }
        return null;
    }

    MaterialShapeDrawable getMaterialShapeDrawable() {
        return this.getMaterialShapeDrawable(false);
    }

    ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    int getStrokeWidth() {
        return this.strokeWidth;
    }

    ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    private MaterialShapeDrawable getSurfaceColorStrokeDrawable() {
        return this.getMaterialShapeDrawable(true);
    }

    boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    boolean isCheckable() {
        return this.checkable;
    }

    boolean isToggleCheckedStateOnClick() {
        return this.toggleCheckedStateOnClick;
    }

    void loadFromAttributes(TypedArray typedArray0) {
        this.insetLeft = typedArray0.getDimensionPixelOffset(styleable.MaterialButton_android_insetLeft, 0);
        this.insetRight = typedArray0.getDimensionPixelOffset(styleable.MaterialButton_android_insetRight, 0);
        this.insetTop = typedArray0.getDimensionPixelOffset(styleable.MaterialButton_android_insetTop, 0);
        this.insetBottom = typedArray0.getDimensionPixelOffset(styleable.MaterialButton_android_insetBottom, 0);
        if(typedArray0.hasValue(styleable.MaterialButton_cornerRadius)) {
            int v = typedArray0.getDimensionPixelSize(styleable.MaterialButton_cornerRadius, -1);
            this.cornerRadius = v;
            this.setShapeAppearanceModel(this.shapeAppearanceModel.withCornerSize(((float)v)));
            this.cornerRadiusSet = true;
        }
        this.strokeWidth = typedArray0.getDimensionPixelSize(styleable.MaterialButton_strokeWidth, 0);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArray0.getInt(styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray0, styleable.MaterialButton_backgroundTint);
        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray0, styleable.MaterialButton_strokeColor);
        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray0, styleable.MaterialButton_rippleColor);
        this.checkable = typedArray0.getBoolean(styleable.MaterialButton_android_checkable, false);
        this.elevation = typedArray0.getDimensionPixelSize(styleable.MaterialButton_elevation, 0);
        this.toggleCheckedStateOnClick = typedArray0.getBoolean(styleable.MaterialButton_toggleCheckedStateOnClick, true);
        int v1 = ViewCompat.getPaddingStart(this.materialButton);
        int v2 = this.materialButton.getPaddingTop();
        int v3 = ViewCompat.getPaddingEnd(this.materialButton);
        int v4 = this.materialButton.getPaddingBottom();
        if(typedArray0.hasValue(styleable.MaterialButton_android_background)) {
            this.setBackgroundOverwritten();
        }
        else {
            this.updateBackground();
        }
        ViewCompat.setPaddingRelative(this.materialButton, v1 + this.insetLeft, v2 + this.insetTop, v3 + this.insetRight, v4 + this.insetBottom);
    }

    void setBackgroundColor(int v) {
        if(this.getMaterialShapeDrawable() != null) {
            this.getMaterialShapeDrawable().setTint(v);
        }
    }

    void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    void setCheckable(boolean z) {
        this.checkable = z;
    }

    void setCornerRadius(int v) {
        if(this.cornerRadiusSet && this.cornerRadius == v) {
            return;
        }
        this.cornerRadius = v;
        this.cornerRadiusSet = true;
        this.setShapeAppearanceModel(this.shapeAppearanceModel.withCornerSize(((float)v)));
    }

    public void setInsetBottom(int v) {
        this.setVerticalInsets(this.insetTop, v);
    }

    public void setInsetTop(int v) {
        this.setVerticalInsets(v, this.insetBottom);
    }

    void setRippleColor(ColorStateList colorStateList0) {
        if(this.rippleColor != colorStateList0) {
            this.rippleColor = colorStateList0;
            boolean z = MaterialButtonHelper.IS_MIN_LOLLIPOP;
            if(z && this.materialButton.getBackground() instanceof RippleDrawable) {
                ((RippleDrawable)this.materialButton.getBackground()).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList0));
                return;
            }
            if(!z && this.materialButton.getBackground() instanceof RippleDrawableCompat) {
                ((RippleDrawableCompat)this.materialButton.getBackground()).setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList0));
            }
        }
    }

    void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.shapeAppearanceModel = shapeAppearanceModel0;
        this.updateButtonShape(shapeAppearanceModel0);
    }

    void setShouldDrawSurfaceColorStroke(boolean z) {
        this.shouldDrawSurfaceColorStroke = z;
        this.updateStroke();
    }

    void setStrokeColor(ColorStateList colorStateList0) {
        if(this.strokeColor != colorStateList0) {
            this.strokeColor = colorStateList0;
            this.updateStroke();
        }
    }

    void setStrokeWidth(int v) {
        if(this.strokeWidth != v) {
            this.strokeWidth = v;
            this.updateStroke();
        }
    }

    void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        if(this.backgroundTint != colorStateList0) {
            this.backgroundTint = colorStateList0;
            if(this.getMaterialShapeDrawable() != null) {
                DrawableCompat.setTintList(this.getMaterialShapeDrawable(), this.backgroundTint);
            }
        }
    }

    void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.backgroundTintMode != porterDuff$Mode0) {
            this.backgroundTintMode = porterDuff$Mode0;
            if(this.getMaterialShapeDrawable() != null && this.backgroundTintMode != null) {
                DrawableCompat.setTintMode(this.getMaterialShapeDrawable(), this.backgroundTintMode);
            }
        }
    }

    void setToggleCheckedStateOnClick(boolean z) {
        this.toggleCheckedStateOnClick = z;
    }

    private void setVerticalInsets(int v, int v1) {
        int v2 = ViewCompat.getPaddingStart(this.materialButton);
        int v3 = this.materialButton.getPaddingTop();
        int v4 = ViewCompat.getPaddingEnd(this.materialButton);
        int v5 = this.materialButton.getPaddingBottom();
        int v6 = this.insetTop;
        int v7 = this.insetBottom;
        this.insetBottom = v1;
        this.insetTop = v;
        if(!this.backgroundOverwritten) {
            this.updateBackground();
        }
        ViewCompat.setPaddingRelative(this.materialButton, v2, v3 + v - v6, v4, v5 + v1 - v7);
    }

    private void updateBackground() {
        Drawable drawable0 = this.createBackground();
        this.materialButton.setInternalBackground(drawable0);
        MaterialShapeDrawable materialShapeDrawable0 = this.getMaterialShapeDrawable();
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setElevation(((float)this.elevation));
            materialShapeDrawable0.setState(this.materialButton.getDrawableState());
        }
    }

    private void updateButtonShape(ShapeAppearanceModel shapeAppearanceModel0) {
        if(MaterialButtonHelper.IS_LOLLIPOP && !this.backgroundOverwritten) {
            int v = ViewCompat.getPaddingStart(this.materialButton);
            int v1 = this.materialButton.getPaddingTop();
            int v2 = ViewCompat.getPaddingEnd(this.materialButton);
            int v3 = this.materialButton.getPaddingBottom();
            this.updateBackground();
            ViewCompat.setPaddingRelative(this.materialButton, v, v1, v2, v3);
            return;
        }
        if(this.getMaterialShapeDrawable() != null) {
            this.getMaterialShapeDrawable().setShapeAppearanceModel(shapeAppearanceModel0);
        }
        if(this.getSurfaceColorStrokeDrawable() != null) {
            this.getSurfaceColorStrokeDrawable().setShapeAppearanceModel(shapeAppearanceModel0);
        }
        if(this.getMaskDrawable() != null) {
            this.getMaskDrawable().setShapeAppearanceModel(shapeAppearanceModel0);
        }
    }

    void updateMaskBounds(int v, int v1) {
        Drawable drawable0 = this.maskDrawable;
        if(drawable0 != null) {
            drawable0.setBounds(this.insetLeft, this.insetTop, v1 - this.insetRight, v - this.insetBottom);
        }
    }

    private void updateStroke() {
        MaterialShapeDrawable materialShapeDrawable0 = this.getMaterialShapeDrawable();
        MaterialShapeDrawable materialShapeDrawable1 = this.getSurfaceColorStrokeDrawable();
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setStroke(((float)this.strokeWidth), this.strokeColor);
            if(materialShapeDrawable1 != null) {
                materialShapeDrawable1.setStroke(((float)this.strokeWidth), (this.shouldDrawSurfaceColorStroke ? MaterialColors.getColor(this.materialButton, attr.colorSurface) : 0));
            }
        }
    }

    private InsetDrawable wrapDrawableWithInset(Drawable drawable0) {
        return new InsetDrawable(drawable0, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
    }
}

