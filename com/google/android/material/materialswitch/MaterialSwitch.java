package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialSwitch extends SwitchCompat {
    private static final int DEF_STYLE_RES;
    private static final int[] STATE_SET_WITH_ICON;
    private int[] currentStateChecked;
    private int[] currentStateUnchecked;
    private Drawable thumbDrawable;
    private Drawable thumbIconDrawable;
    private int thumbIconSize;
    private ColorStateList thumbIconTintList;
    private PorterDuff.Mode thumbIconTintMode;
    private ColorStateList thumbTintList;
    private Drawable trackDecorationDrawable;
    private ColorStateList trackDecorationTintList;
    private PorterDuff.Mode trackDecorationTintMode;
    private Drawable trackDrawable;
    private ColorStateList trackTintList;

    static {
        MaterialSwitch.DEF_STYLE_RES = style.Widget_Material3_CompoundButton_MaterialSwitch;
        MaterialSwitch.STATE_SET_WITH_ICON = new int[]{attr.state_with_icon};
    }

    public MaterialSwitch(Context context0) {
        this(context0, null);
    }

    public MaterialSwitch(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialSwitchStyle);
    }

    public MaterialSwitch(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialSwitch.DEF_STYLE_RES), attributeSet0, v);
        this.thumbIconSize = -1;
        Context context1 = this.getContext();
        this.thumbDrawable = super.getThumbDrawable();
        this.thumbTintList = super.getThumbTintList();
        super.setThumbTintList(null);
        this.trackDrawable = super.getTrackDrawable();
        this.trackTintList = super.getTrackTintList();
        super.setTrackTintList(null);
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(context1, attributeSet0, styleable.MaterialSwitch, v, MaterialSwitch.DEF_STYLE_RES, new int[0]);
        this.thumbIconDrawable = tintTypedArray0.getDrawable(styleable.MaterialSwitch_thumbIcon);
        this.thumbIconSize = tintTypedArray0.getDimensionPixelSize(styleable.MaterialSwitch_thumbIconSize, -1);
        this.thumbIconTintList = tintTypedArray0.getColorStateList(styleable.MaterialSwitch_thumbIconTint);
        this.thumbIconTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.MaterialSwitch_thumbIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.trackDecorationDrawable = tintTypedArray0.getDrawable(styleable.MaterialSwitch_trackDecoration);
        this.trackDecorationTintList = tintTypedArray0.getColorStateList(styleable.MaterialSwitch_trackDecorationTint);
        this.trackDecorationTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.MaterialSwitch_trackDecorationTintMode, -1), PorterDuff.Mode.SRC_IN);
        tintTypedArray0.recycle();
        this.setEnforceSwitchWidth(false);
        this.refreshThumbDrawable();
        this.refreshTrackDrawable();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.thumbDrawable;
    }

    public Drawable getThumbIconDrawable() {
        return this.thumbIconDrawable;
    }

    public int getThumbIconSize() {
        return this.thumbIconSize;
    }

    public ColorStateList getThumbIconTintList() {
        return this.thumbIconTintList;
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.thumbIconTintMode;
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.thumbTintList;
    }

    public Drawable getTrackDecorationDrawable() {
        return this.trackDecorationDrawable;
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.trackDecorationTintList;
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.trackDecorationTintMode;
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.trackDrawable;
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.trackTintList;
    }

    @Override  // android.view.View
    public void invalidate() {
        this.updateDrawableTints();
        super.invalidate();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 1);
        if(this.thumbIconDrawable != null) {
            MaterialSwitch.mergeDrawableStates(arr_v, MaterialSwitch.STATE_SET_WITH_ICON);
        }
        this.currentStateUnchecked = DrawableUtils.getUncheckedState(arr_v);
        this.currentStateChecked = DrawableUtils.getCheckedState(arr_v);
        return arr_v;
    }

    private void refreshThumbDrawable() {
        this.thumbDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.thumbDrawable, this.thumbTintList, this.getThumbTintMode());
        this.thumbIconDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.thumbIconDrawable, this.thumbIconTintList, this.thumbIconTintMode);
        this.updateDrawableTints();
        super.setThumbDrawable(DrawableUtils.compositeTwoLayeredDrawable(this.thumbDrawable, this.thumbIconDrawable, this.thumbIconSize, this.thumbIconSize));
        this.refreshDrawableState();
    }

    private void refreshTrackDrawable() {
        this.trackDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.trackDrawable, this.trackTintList, this.getTrackTintMode());
        this.trackDecorationDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.trackDecorationDrawable, this.trackDecorationTintList, this.trackDecorationTintMode);
        this.updateDrawableTints();
        Drawable drawable0 = this.trackDrawable;
        if(drawable0 != null && this.trackDecorationDrawable != null) {
            drawable0 = new LayerDrawable(new Drawable[]{this.trackDrawable, this.trackDecorationDrawable});
        }
        else if(drawable0 == null) {
            drawable0 = this.trackDecorationDrawable;
        }
        if(drawable0 != null) {
            this.setSwitchMinWidth(drawable0.getIntrinsicWidth());
        }
        super.setTrackDrawable(drawable0);
    }

    private static void setInterpolatedDrawableTintIfPossible(Drawable drawable0, ColorStateList colorStateList0, int[] arr_v, int[] arr_v1, float f) {
        if(drawable0 != null && colorStateList0 != null) {
            DrawableCompat.setTint(drawable0, ColorUtils.blendARGB(colorStateList0.getColorForState(arr_v, 0), colorStateList0.getColorForState(arr_v1, 0), f));
        }
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable drawable0) {
        this.thumbDrawable = drawable0;
        this.refreshThumbDrawable();
    }

    public void setThumbIconDrawable(Drawable drawable0) {
        this.thumbIconDrawable = drawable0;
        this.refreshThumbDrawable();
    }

    public void setThumbIconResource(int v) {
        this.setThumbIconDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setThumbIconSize(int v) {
        if(this.thumbIconSize != v) {
            this.thumbIconSize = v;
            this.refreshThumbDrawable();
        }
    }

    public void setThumbIconTintList(ColorStateList colorStateList0) {
        this.thumbIconTintList = colorStateList0;
        this.refreshThumbDrawable();
    }

    public void setThumbIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.thumbIconTintMode = porterDuff$Mode0;
        this.refreshThumbDrawable();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList colorStateList0) {
        this.thumbTintList = colorStateList0;
        this.refreshThumbDrawable();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode porterDuff$Mode0) {
        super.setThumbTintMode(porterDuff$Mode0);
        this.refreshThumbDrawable();
    }

    public void setTrackDecorationDrawable(Drawable drawable0) {
        this.trackDecorationDrawable = drawable0;
        this.refreshTrackDrawable();
    }

    public void setTrackDecorationResource(int v) {
        this.setTrackDecorationDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setTrackDecorationTintList(ColorStateList colorStateList0) {
        this.trackDecorationTintList = colorStateList0;
        this.refreshTrackDrawable();
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.trackDecorationTintMode = porterDuff$Mode0;
        this.refreshTrackDrawable();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable drawable0) {
        this.trackDrawable = drawable0;
        this.refreshTrackDrawable();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList colorStateList0) {
        this.trackTintList = colorStateList0;
        this.refreshTrackDrawable();
    }

    @Override  // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(PorterDuff.Mode porterDuff$Mode0) {
        super.setTrackTintMode(porterDuff$Mode0);
        this.refreshTrackDrawable();
    }

    private void updateDrawableTints() {
        if(this.thumbTintList != null || this.thumbIconTintList != null || this.trackTintList != null || this.trackDecorationTintList != null) {
            float f = this.getThumbPosition();
            ColorStateList colorStateList0 = this.thumbTintList;
            if(colorStateList0 != null) {
                MaterialSwitch.setInterpolatedDrawableTintIfPossible(this.thumbDrawable, colorStateList0, this.currentStateUnchecked, this.currentStateChecked, f);
            }
            ColorStateList colorStateList1 = this.thumbIconTintList;
            if(colorStateList1 != null) {
                MaterialSwitch.setInterpolatedDrawableTintIfPossible(this.thumbIconDrawable, colorStateList1, this.currentStateUnchecked, this.currentStateChecked, f);
            }
            ColorStateList colorStateList2 = this.trackTintList;
            if(colorStateList2 != null) {
                MaterialSwitch.setInterpolatedDrawableTintIfPossible(this.trackDrawable, colorStateList2, this.currentStateUnchecked, this.currentStateChecked, f);
            }
            ColorStateList colorStateList3 = this.trackDecorationTintList;
            if(colorStateList3 != null) {
                MaterialSwitch.setInterpolatedDrawableTintIfPossible(this.trackDecorationDrawable, colorStateList3, this.currentStateUnchecked, this.currentStateChecked, f);
            }
        }
    }
}

