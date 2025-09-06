package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedHashSet;

public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialButton arg1, boolean arg2);
    }

    interface OnPressedChangeListener {
        void onPressedChanged(MaterialButton arg1, boolean arg2);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean checked;

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
            if(classLoader0 == null) {
                this.getClass().getClassLoader();
            }
            this.readFromParcel(parcel0);
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        private void readFromParcel(Parcel parcel0) {
            this.checked = parcel0.readInt() == 1;
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(((int)this.checked));
        }
    }

    private static final int[] CHECKABLE_STATE_SET = null;
    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEF_STYLE_RES = 0;
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    public static final int ICON_GRAVITY_TEXT_TOP = 0x20;
    public static final int ICON_GRAVITY_TOP = 16;
    private static final String LOG_TAG = "MaterialButton";
    private String accessibilityClassName;
    private boolean broadcasting;
    private boolean checked;
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private int iconTop;
    private final MaterialButtonHelper materialButtonHelper;
    private final LinkedHashSet onCheckedChangeListeners;
    private OnPressedChangeListener onPressedChangeListenerInternal;

    static {
        MaterialButton.CHECKABLE_STATE_SET = new int[]{0x101009F};
        MaterialButton.CHECKED_STATE_SET = new int[]{0x10100A0};
        MaterialButton.DEF_STYLE_RES = style.Widget_MaterialComponents_Button;
    }

    public MaterialButton(Context context0) {
        this(context0, null);
    }

    public MaterialButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialButtonStyle);
    }

    public MaterialButton(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialButton.DEF_STYLE_RES), attributeSet0, v);
        this.onCheckedChangeListeners = new LinkedHashSet();
        boolean z = false;
        this.checked = false;
        this.broadcasting = false;
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.MaterialButton, v, MaterialButton.DEF_STYLE_RES, new int[0]);
        this.iconPadding = typedArray0.getDimensionPixelSize(styleable.MaterialButton_iconPadding, 0);
        this.iconTintMode = ViewUtils.parseTintMode(typedArray0.getInt(styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.iconTint = MaterialResources.getColorStateList(this.getContext(), typedArray0, styleable.MaterialButton_iconTint);
        this.icon = MaterialResources.getDrawable(this.getContext(), typedArray0, styleable.MaterialButton_icon);
        this.iconGravity = typedArray0.getInteger(styleable.MaterialButton_iconGravity, 1);
        this.iconSize = typedArray0.getDimensionPixelSize(styleable.MaterialButton_iconSize, 0);
        MaterialButtonHelper materialButtonHelper0 = new MaterialButtonHelper(this, ShapeAppearanceModel.builder(context1, attributeSet0, v, MaterialButton.DEF_STYLE_RES).build());
        this.materialButtonHelper = materialButtonHelper0;
        materialButtonHelper0.loadFromAttributes(typedArray0);
        typedArray0.recycle();
        this.setCompoundDrawablePadding(this.iconPadding);
        if(this.icon != null) {
            z = true;
        }
        this.updateIcon(z);
    }

    public void addOnCheckedChangeListener(OnCheckedChangeListener materialButton$OnCheckedChangeListener0) {
        this.onCheckedChangeListeners.add(materialButton$OnCheckedChangeListener0);
    }

    public void clearOnCheckedChangeListeners() {
        this.onCheckedChangeListeners.clear();
    }

    // 去混淆评级： 低(23)
    String getA11yClassName() {
        if(!TextUtils.isEmpty(this.accessibilityClassName)) {
            return this.accessibilityClassName;
        }
        return this.isCheckable() ? "android.widget.CompoundButton" : "android.widget.Button";
    }

    private Layout.Alignment getActualTextAlignment() {
        switch(this.getTextAlignment()) {
            case 1: {
                return this.getGravityTextAlignment();
            }
            case 4: {
                return Layout.Alignment.ALIGN_CENTER;
            }
            case 3: 
            case 6: {
                return Layout.Alignment.ALIGN_OPPOSITE;
            }
            default: {
                return Layout.Alignment.ALIGN_NORMAL;
            }
        }
    }

    @Override  // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.getSupportBackgroundTintList();
    }

    @Override  // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.getSupportBackgroundTintMode();
    }

    // 去混淆评级： 低(20)
    public int getCornerRadius() {
        return this.isUsingOriginalBackground() ? this.materialButtonHelper.getCornerRadius() : 0;
    }

    private Layout.Alignment getGravityTextAlignment() {
        switch(this.getGravity() & 0x800007) {
            case 1: {
                return Layout.Alignment.ALIGN_CENTER;
            }
            case 5: 
            case 0x800005: {
                return Layout.Alignment.ALIGN_OPPOSITE;
            }
            default: {
                return Layout.Alignment.ALIGN_NORMAL;
            }
        }
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    public int getInsetBottom() {
        return this.materialButtonHelper.getInsetBottom();
    }

    public int getInsetTop() {
        return this.materialButtonHelper.getInsetTop();
    }

    // 去混淆评级： 低(20)
    public ColorStateList getRippleColor() {
        return this.isUsingOriginalBackground() ? this.materialButtonHelper.getRippleColor() : null;
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        if(!this.isUsingOriginalBackground()) {
            throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
        }
        return this.materialButtonHelper.getShapeAppearanceModel();
    }

    // 去混淆评级： 低(20)
    public ColorStateList getStrokeColor() {
        return this.isUsingOriginalBackground() ? this.materialButtonHelper.getStrokeColor() : null;
    }

    // 去混淆评级： 低(20)
    public int getStrokeWidth() {
        return this.isUsingOriginalBackground() ? this.materialButtonHelper.getStrokeWidth() : 0;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return this.isUsingOriginalBackground() ? this.materialButtonHelper.getSupportBackgroundTintList() : super.getSupportBackgroundTintList();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.isUsingOriginalBackground() ? this.materialButtonHelper.getSupportBackgroundTintMode() : super.getSupportBackgroundTintMode();
    }

    private int getTextHeight() {
        if(this.getLineCount() > 1) {
            return this.getLayout().getHeight();
        }
        TextPaint textPaint0 = this.getPaint();
        String s = this.getText().toString();
        if(this.getTransformationMethod() != null) {
            s = this.getTransformationMethod().getTransformation(s, this).toString();
        }
        Rect rect0 = new Rect();
        textPaint0.getTextBounds(s, 0, s.length(), rect0);
        return Math.min(rect0.height(), this.getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int v = this.getLineCount();
        float f = 0.0f;
        for(int v1 = 0; v1 < v; ++v1) {
            f = Math.max(f, this.getLayout().getLineWidth(v1));
        }
        return (int)Math.ceil(f);
    }

    public boolean isCheckable() {
        return this.materialButtonHelper != null && this.materialButtonHelper.isCheckable();
    }

    @Override  // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    private boolean isIconEnd() {
        return this.iconGravity == 3 || this.iconGravity == 4;
    }

    private boolean isIconStart() {
        return this.iconGravity == 1 || this.iconGravity == 2;
    }

    private boolean isIconTop() {
        return this.iconGravity == 16 || this.iconGravity == 0x20;
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    public boolean isToggleCheckedStateOnClick() {
        return this.materialButtonHelper.isToggleCheckedStateOnClick();
    }

    private boolean isUsingOriginalBackground() {
        return this.materialButtonHelper != null && !this.materialButtonHelper.isBackgroundOverwritten();
    }

    @Override  // android.widget.TextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.isUsingOriginalBackground()) {
            MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable());
        }
    }

    @Override  // android.widget.TextView
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 2);
        if(this.isCheckable()) {
            MaterialButton.mergeDrawableStates(arr_v, MaterialButton.CHECKABLE_STATE_SET);
        }
        if(this.isChecked()) {
            MaterialButton.mergeDrawableStates(arr_v, MaterialButton.CHECKED_STATE_SET);
        }
        return arr_v;
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        accessibilityEvent0.setClassName(this.getA11yClassName());
        accessibilityEvent0.setChecked(this.isChecked());
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName(this.getA11yClassName());
        accessibilityNodeInfo0.setCheckable(this.isCheckable());
        accessibilityNodeInfo0.setChecked(this.isChecked());
        accessibilityNodeInfo0.setClickable(this.isClickable());
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        if(Build.VERSION.SDK_INT == 21) {
            MaterialButtonHelper materialButtonHelper0 = this.materialButtonHelper;
            if(materialButtonHelper0 != null) {
                materialButtonHelper0.updateMaskBounds(v3 - v1, v2 - v);
            }
        }
        this.updateIconPosition(this.getMeasuredWidth(), this.getMeasuredHeight());
    }

    @Override  // android.widget.TextView
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setChecked(((SavedState)parcelable0).checked);
    }

    @Override  // android.widget.TextView
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.checked = this.checked;
        return parcelable0;
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    protected void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
        super.onTextChanged(charSequence0, v, v1, v2);
        this.updateIconPosition(this.getMeasuredWidth(), this.getMeasuredHeight());
    }

    @Override  // android.view.View
    public boolean performClick() {
        if(this.materialButtonHelper.isToggleCheckedStateOnClick()) {
            this.toggle();
        }
        return super.performClick();
    }

    @Override  // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if(this.icon != null) {
            int[] arr_v = this.getDrawableState();
            if(this.icon.setState(arr_v)) {
                this.invalidate();
            }
        }
    }

    public void removeOnCheckedChangeListener(OnCheckedChangeListener materialButton$OnCheckedChangeListener0) {
        this.onCheckedChangeListeners.remove(materialButton$OnCheckedChangeListener0);
    }

    private void resetIconDrawable() {
        if(this.isIconStart()) {
            TextViewCompat.setCompoundDrawablesRelative(this, this.icon, null, null, null);
            return;
        }
        if(this.isIconEnd()) {
            TextViewCompat.setCompoundDrawablesRelative(this, null, null, this.icon, null);
            return;
        }
        if(this.isIconTop()) {
            TextViewCompat.setCompoundDrawablesRelative(this, null, this.icon, null, null);
        }
    }

    void setA11yClassName(String s) {
        this.accessibilityClassName = s;
    }

    @Override  // android.view.View
    public void setBackground(Drawable drawable0) {
        this.setBackgroundDrawable(drawable0);
    }

    @Override  // android.view.View
    public void setBackgroundColor(int v) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(v);
            return;
        }
        super.setBackgroundColor(v);
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    public void setBackgroundDrawable(Drawable drawable0) {
        if(this.isUsingOriginalBackground()) {
            if(drawable0 != this.getBackground()) {
                Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
                this.materialButtonHelper.setBackgroundOverwritten();
                super.setBackgroundDrawable(drawable0);
                return;
            }
            this.getBackground().setState(drawable0.getState());
            return;
        }
        super.setBackgroundDrawable(drawable0);
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    public void setBackgroundResource(int v) {
        this.setBackgroundDrawable((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    @Override  // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList0) {
        this.setSupportBackgroundTintList(colorStateList0);
    }

    @Override  // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.setSupportBackgroundTintMode(porterDuff$Mode0);
    }

    public void setCheckable(boolean z) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setCheckable(z);
        }
    }

    @Override  // android.widget.Checkable
    public void setChecked(boolean z) {
        if(this.isCheckable() && this.isEnabled() && this.checked != z) {
            this.checked = z;
            this.refreshDrawableState();
            if(this.getParent() instanceof MaterialButtonToggleGroup) {
                ((MaterialButtonToggleGroup)this.getParent()).onButtonCheckedStateChanged(this, this.checked);
            }
            if(!this.broadcasting) {
                this.broadcasting = true;
                for(Object object0: this.onCheckedChangeListeners) {
                    ((OnCheckedChangeListener)object0).onCheckedChanged(this, this.checked);
                }
                this.broadcasting = false;
            }
        }
    }

    public void setCornerRadius(int v) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setCornerRadius(v);
        }
    }

    public void setCornerRadiusResource(int v) {
        if(this.isUsingOriginalBackground()) {
            this.setCornerRadius(this.getResources().getDimensionPixelSize(v));
        }
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.getMaterialShapeDrawable().setElevation(f);
        }
    }

    public void setIcon(Drawable drawable0) {
        if(this.icon != drawable0) {
            this.icon = drawable0;
            this.updateIcon(true);
            this.updateIconPosition(this.getMeasuredWidth(), this.getMeasuredHeight());
        }
    }

    public void setIconGravity(int v) {
        if(this.iconGravity != v) {
            this.iconGravity = v;
            this.updateIconPosition(this.getMeasuredWidth(), this.getMeasuredHeight());
        }
    }

    public void setIconPadding(int v) {
        if(this.iconPadding != v) {
            this.iconPadding = v;
            this.setCompoundDrawablePadding(v);
        }
    }

    public void setIconResource(int v) {
        this.setIcon((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    public void setIconSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if(this.iconSize != v) {
            this.iconSize = v;
            this.updateIcon(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList0) {
        if(this.iconTint != colorStateList0) {
            this.iconTint = colorStateList0;
            this.updateIcon(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.iconTintMode != porterDuff$Mode0) {
            this.iconTintMode = porterDuff$Mode0;
            this.updateIcon(false);
        }
    }

    public void setIconTintResource(int v) {
        this.setIconTint(AppCompatResources.getColorStateList(this.getContext(), v));
    }

    public void setInsetBottom(int v) {
        this.materialButtonHelper.setInsetBottom(v);
    }

    public void setInsetTop(int v) {
        this.materialButtonHelper.setInsetTop(v);
    }

    void setInternalBackground(Drawable drawable0) {
        super.setBackgroundDrawable(drawable0);
    }

    void setOnPressedChangeListenerInternal(OnPressedChangeListener materialButton$OnPressedChangeListener0) {
        this.onPressedChangeListenerInternal = materialButton$OnPressedChangeListener0;
    }

    @Override  // android.view.View
    public void setPressed(boolean z) {
        OnPressedChangeListener materialButton$OnPressedChangeListener0 = this.onPressedChangeListenerInternal;
        if(materialButton$OnPressedChangeListener0 != null) {
            materialButton$OnPressedChangeListener0.onPressedChanged(this, z);
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList0) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setRippleColor(colorStateList0);
        }
    }

    public void setRippleColorResource(int v) {
        if(this.isUsingOriginalBackground()) {
            this.setRippleColor(AppCompatResources.getColorStateList(this.getContext(), v));
        }
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        if(!this.isUsingOriginalBackground()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.materialButtonHelper.setShapeAppearanceModel(shapeAppearanceModel0);
    }

    void setShouldDrawSurfaceColorStroke(boolean z) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setShouldDrawSurfaceColorStroke(z);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList0) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeColor(colorStateList0);
        }
    }

    public void setStrokeColorResource(int v) {
        if(this.isUsingOriginalBackground()) {
            this.setStrokeColor(AppCompatResources.getColorStateList(this.getContext(), v));
        }
    }

    public void setStrokeWidth(int v) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeWidth(v);
        }
    }

    public void setStrokeWidthResource(int v) {
        if(this.isUsingOriginalBackground()) {
            this.setStrokeWidth(this.getResources().getDimensionPixelSize(v));
        }
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(colorStateList0);
            return;
        }
        super.setSupportBackgroundTintList(colorStateList0);
    }

    @Override  // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(porterDuff$Mode0);
            return;
        }
        super.setSupportBackgroundTintMode(porterDuff$Mode0);
    }

    @Override  // android.view.View
    public void setTextAlignment(int v) {
        super.setTextAlignment(v);
        this.updateIconPosition(this.getMeasuredWidth(), this.getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z) {
        this.materialButtonHelper.setToggleCheckedStateOnClick(z);
    }

    @Override  // android.widget.Checkable
    public void toggle() {
        this.setChecked(!this.checked);
    }

    private void updateIcon(boolean z) {
        Drawable drawable0 = this.icon;
        if(drawable0 != null) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            this.icon = drawable1;
            DrawableCompat.setTintList(drawable1, this.iconTint);
            PorterDuff.Mode porterDuff$Mode0 = this.iconTintMode;
            if(porterDuff$Mode0 != null) {
                DrawableCompat.setTintMode(this.icon, porterDuff$Mode0);
            }
            int v = this.iconSize == 0 ? this.icon.getIntrinsicWidth() : this.iconSize;
            int v1 = this.iconSize == 0 ? this.icon.getIntrinsicHeight() : this.iconSize;
            this.icon.setBounds(this.iconLeft, this.iconTop, v + this.iconLeft, v1 + this.iconTop);
            this.icon.setVisible(true, z);
        }
        if(z) {
            this.resetIconDrawable();
            return;
        }
        Drawable[] arr_drawable = TextViewCompat.getCompoundDrawablesRelative(this);
        if(this.isIconStart() && arr_drawable[0] != this.icon || this.isIconEnd() && arr_drawable[2] != this.icon || this.isIconTop() && arr_drawable[1] != this.icon) {
            this.resetIconDrawable();
        }
    }

    private void updateIconPosition(int v, int v1) {
        if(this.icon != null && this.getLayout() != null) {
            if(this.isIconStart() || this.isIconEnd()) {
                this.iconTop = 0;
                Layout.Alignment layout$Alignment0 = this.getActualTextAlignment();
                boolean z = true;
                switch(this.iconGravity) {
                    case 2: {
                        if(layout$Alignment0 == Layout.Alignment.ALIGN_NORMAL) {
                            this.iconLeft = 0;
                            this.updateIcon(false);
                        }
                        else {
                        label_23:
                            if(this.iconGravity == 4 && layout$Alignment0 == Layout.Alignment.ALIGN_OPPOSITE) {
                                this.iconLeft = 0;
                                this.updateIcon(false);
                                return;
                            }
                            int v4 = this.iconSize == 0 ? this.icon.getIntrinsicWidth() : this.iconSize;
                            int v5 = v - this.getTextLayoutWidth() - ViewCompat.getPaddingEnd(this) - v4 - this.iconPadding - ViewCompat.getPaddingStart(this);
                            if(layout$Alignment0 == Layout.Alignment.ALIGN_CENTER) {
                                v5 /= 2;
                            }
                            boolean z1 = this.isLayoutRTL();
                            if(this.iconGravity != 4) {
                                z = false;
                            }
                            if(z1 != z) {
                                v5 = -v5;
                            }
                            if(this.iconLeft != v5) {
                                this.iconLeft = v5;
                                this.updateIcon(false);
                                return;
                            }
                        }
                        break;
                    }
                    case 1: 
                    case 3: {
                        this.iconLeft = 0;
                        this.updateIcon(false);
                        return;
                    }
                    default: {
                        goto label_23;
                    }
                }
            }
            else if(this.isIconTop()) {
                this.iconLeft = 0;
                if(this.iconGravity == 16) {
                    this.iconTop = 0;
                    this.updateIcon(false);
                    return;
                }
                int v2 = this.iconSize == 0 ? this.icon.getIntrinsicHeight() : this.iconSize;
                int v3 = Math.max(0, (v1 - this.getTextHeight() - this.getPaddingTop() - v2 - this.iconPadding - this.getPaddingBottom()) / 2);
                if(this.iconTop != v3) {
                    this.iconTop = v3;
                    this.updateIcon(false);
                }
            }
        }
    }
}

