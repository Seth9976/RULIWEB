package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MaterialCardView extends CardView implements Checkable, Shapeable {
    @Retention(RetentionPolicy.SOURCE)
    public @interface CheckedIconGravity {
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView arg1, boolean arg2);
    }

    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.cardview.widget.CardView";
    private static final int[] CHECKABLE_STATE_SET = null;
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_END = 0x800055;
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_START = 0x800053;
    public static final int CHECKED_ICON_GRAVITY_TOP_END = 0x800035;
    public static final int CHECKED_ICON_GRAVITY_TOP_START = 0x800033;
    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEF_STYLE_RES = 0;
    private static final int[] DRAGGED_STATE_SET = null;
    private static final String LOG_TAG = "MaterialCardView";
    private final MaterialCardViewHelper cardViewHelper;
    private boolean checked;
    private boolean dragged;
    private boolean isParentCardViewDoneInitializing;
    private OnCheckedChangeListener onCheckedChangeListener;

    static {
        MaterialCardView.CHECKABLE_STATE_SET = new int[]{0x101009F};
        MaterialCardView.CHECKED_STATE_SET = new int[]{0x10100A0};
        MaterialCardView.DRAGGED_STATE_SET = new int[]{attr.state_dragged};
        MaterialCardView.DEF_STYLE_RES = style.Widget_MaterialComponents_CardView;
    }

    public MaterialCardView(Context context0) {
        this(context0, null);
    }

    public MaterialCardView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialCardView.DEF_STYLE_RES), attributeSet0, v);
        this.checked = false;
        this.dragged = false;
        this.isParentCardViewDoneInitializing = true;
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(this.getContext(), attributeSet0, styleable.MaterialCardView, v, MaterialCardView.DEF_STYLE_RES, new int[0]);
        MaterialCardViewHelper materialCardViewHelper0 = new MaterialCardViewHelper(this, attributeSet0, v, MaterialCardView.DEF_STYLE_RES);
        this.cardViewHelper = materialCardViewHelper0;
        materialCardViewHelper0.setCardBackgroundColor(super.getCardBackgroundColor());
        materialCardViewHelper0.setUserContentPadding(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        materialCardViewHelper0.loadFromAttributes(typedArray0);
        typedArray0.recycle();
    }

    private void forceRippleRedrawIfNeeded() {
        if(Build.VERSION.SDK_INT > 26) {
            this.cardViewHelper.forceRippleRedraw();
        }
    }

    private RectF getBoundsAsRectF() {
        RectF rectF0 = new RectF();
        rectF0.set(this.cardViewHelper.getBackground().getBounds());
        return rectF0;
    }

    @Override  // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.cardViewHelper.getCardBackgroundColor();
    }

    public ColorStateList getCardForegroundColor() {
        return this.cardViewHelper.getCardForegroundColor();
    }

    float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.cardViewHelper.getCheckedIcon();
    }

    public int getCheckedIconGravity() {
        return this.cardViewHelper.getCheckedIconGravity();
    }

    public int getCheckedIconMargin() {
        return this.cardViewHelper.getCheckedIconMargin();
    }

    public int getCheckedIconSize() {
        return this.cardViewHelper.getCheckedIconSize();
    }

    public ColorStateList getCheckedIconTint() {
        return this.cardViewHelper.getCheckedIconTint();
    }

    @Override  // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.cardViewHelper.getUserContentPadding().bottom;
    }

    @Override  // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.cardViewHelper.getUserContentPadding().left;
    }

    @Override  // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.cardViewHelper.getUserContentPadding().right;
    }

    @Override  // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.cardViewHelper.getUserContentPadding().top;
    }

    public float getProgress() {
        return this.cardViewHelper.getProgress();
    }

    @Override  // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.cardViewHelper.getCornerRadius();
    }

    public ColorStateList getRippleColor() {
        return this.cardViewHelper.getRippleColor();
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.cardViewHelper.getShapeAppearanceModel();
    }

    @Deprecated
    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.cardViewHelper.getStrokeColorStateList();
    }

    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    public boolean isCheckable() {
        return this.cardViewHelper != null && this.cardViewHelper.isCheckable();
    }

    @Override  // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    public boolean isDragged() {
        return this.dragged;
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.cardViewHelper.updateClickable();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.cardViewHelper.getBackground());
    }

    @Override  // android.view.ViewGroup
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 3);
        if(this.isCheckable()) {
            MaterialCardView.mergeDrawableStates(arr_v, MaterialCardView.CHECKABLE_STATE_SET);
        }
        if(this.isChecked()) {
            MaterialCardView.mergeDrawableStates(arr_v, MaterialCardView.CHECKED_STATE_SET);
        }
        if(this.isDragged()) {
            MaterialCardView.mergeDrawableStates(arr_v, MaterialCardView.DRAGGED_STATE_SET);
        }
        return arr_v;
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        accessibilityEvent0.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent0.setChecked(this.isChecked());
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo0.setCheckable(this.isCheckable());
        accessibilityNodeInfo0.setClickable(this.isClickable());
        accessibilityNodeInfo0.setChecked(this.isChecked());
    }

    @Override  // androidx.cardview.widget.CardView
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        int v2 = this.getMeasuredWidth();
        int v3 = this.getMeasuredHeight();
        this.cardViewHelper.recalculateCheckedIconPosition(v2, v3);
    }

    void setAncestorContentPadding(int v, int v1, int v2, int v3) {
        super.setContentPadding(v, v1, v2, v3);
    }

    @Override  // android.view.View
    public void setBackground(Drawable drawable0) {
        this.setBackgroundDrawable(drawable0);
    }

    @Override  // android.view.View
    public void setBackgroundDrawable(Drawable drawable0) {
        if(this.isParentCardViewDoneInitializing) {
            if(!this.cardViewHelper.isBackgroundOverwritten()) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.cardViewHelper.setBackgroundOverwritten(true);
            }
            super.setBackgroundDrawable(drawable0);
        }
    }

    void setBackgroundInternal(Drawable drawable0) {
        super.setBackgroundDrawable(drawable0);
    }

    @Override  // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int v) {
        ColorStateList colorStateList0 = ColorStateList.valueOf(v);
        this.cardViewHelper.setCardBackgroundColor(colorStateList0);
    }

    @Override  // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList0) {
        this.cardViewHelper.setCardBackgroundColor(colorStateList0);
    }

    @Override  // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        this.cardViewHelper.updateElevation();
    }

    public void setCardForegroundColor(ColorStateList colorStateList0) {
        this.cardViewHelper.setCardForegroundColor(colorStateList0);
    }

    public void setCheckable(boolean z) {
        this.cardViewHelper.setCheckable(z);
    }

    @Override  // android.widget.Checkable
    public void setChecked(boolean z) {
        if(this.checked != z) {
            this.toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable0) {
        this.cardViewHelper.setCheckedIcon(drawable0);
    }

    public void setCheckedIconGravity(int v) {
        if(this.cardViewHelper.getCheckedIconGravity() != v) {
            this.cardViewHelper.setCheckedIconGravity(v);
        }
    }

    public void setCheckedIconMargin(int v) {
        this.cardViewHelper.setCheckedIconMargin(v);
    }

    public void setCheckedIconMarginResource(int v) {
        if(v != -1) {
            int v1 = this.getResources().getDimensionPixelSize(v);
            this.cardViewHelper.setCheckedIconMargin(v1);
        }
    }

    public void setCheckedIconResource(int v) {
        Drawable drawable0 = AppCompatResources.getDrawable(this.getContext(), v);
        this.cardViewHelper.setCheckedIcon(drawable0);
    }

    public void setCheckedIconSize(int v) {
        this.cardViewHelper.setCheckedIconSize(v);
    }

    public void setCheckedIconSizeResource(int v) {
        if(v != 0) {
            int v1 = this.getResources().getDimensionPixelSize(v);
            this.cardViewHelper.setCheckedIconSize(v1);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList0) {
        this.cardViewHelper.setCheckedIconTint(colorStateList0);
    }

    @Override  // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        MaterialCardViewHelper materialCardViewHelper0 = this.cardViewHelper;
        if(materialCardViewHelper0 != null) {
            materialCardViewHelper0.updateClickable();
        }
    }

    @Override  // androidx.cardview.widget.CardView
    public void setContentPadding(int v, int v1, int v2, int v3) {
        this.cardViewHelper.setUserContentPadding(v, v1, v2, v3);
    }

    public void setDragged(boolean z) {
        if(this.dragged != z) {
            this.dragged = z;
            this.refreshDrawableState();
            this.forceRippleRedrawIfNeeded();
            this.invalidate();
        }
    }

    @Override  // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.cardViewHelper.updateInsets();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener materialCardView$OnCheckedChangeListener0) {
        this.onCheckedChangeListener = materialCardView$OnCheckedChangeListener0;
    }

    @Override  // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    public void setProgress(float f) {
        this.cardViewHelper.setProgress(f);
    }

    @Override  // androidx.cardview.widget.CardView
    public void setRadius(float f) {
        super.setRadius(f);
        this.cardViewHelper.setCornerRadius(f);
    }

    public void setRippleColor(ColorStateList colorStateList0) {
        this.cardViewHelper.setRippleColor(colorStateList0);
    }

    public void setRippleColorResource(int v) {
        ColorStateList colorStateList0 = AppCompatResources.getColorStateList(this.getContext(), v);
        this.cardViewHelper.setRippleColor(colorStateList0);
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.setClipToOutline(shapeAppearanceModel0.isRoundRect(this.getBoundsAsRectF()));
        this.cardViewHelper.setShapeAppearanceModel(shapeAppearanceModel0);
    }

    public void setStrokeColor(int v) {
        this.setStrokeColor(ColorStateList.valueOf(v));
    }

    public void setStrokeColor(ColorStateList colorStateList0) {
        this.cardViewHelper.setStrokeColor(colorStateList0);
        this.invalidate();
    }

    public void setStrokeWidth(int v) {
        this.cardViewHelper.setStrokeWidth(v);
        this.invalidate();
    }

    @Override  // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    @Override  // android.widget.Checkable
    public void toggle() {
        if(this.isCheckable() && this.isEnabled()) {
            this.checked = !this.checked;
            this.refreshDrawableState();
            this.forceRippleRedrawIfNeeded();
            this.cardViewHelper.setChecked(this.checked, true);
            OnCheckedChangeListener materialCardView$OnCheckedChangeListener0 = this.onCheckedChangeListener;
            if(materialCardView$OnCheckedChangeListener0 != null) {
                materialCardView$OnCheckedChangeListener0.onCheckedChanged(this, this.checked);
            }
        }
    }
}

