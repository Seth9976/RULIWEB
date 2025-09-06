package com.google.android.material.checkbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import com.google.android.material.R.attr;
import com.google.android.material.R.drawable;
import com.google.android.material.R.id;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedHashSet;

public class MaterialCheckBox extends AppCompatCheckBox {
    @Retention(RetentionPolicy.SOURCE)
    public @interface CheckedState {
    }

    public interface OnCheckedStateChangedListener {
        void onCheckedStateChangedListener(MaterialCheckBox arg1, int arg2);
    }

    public interface OnErrorChangedListener {
        void onErrorChanged(MaterialCheckBox arg1, boolean arg2);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        int checkedState;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
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

        private SavedState(Parcel parcel0) {
            super(parcel0);
            this.checkedState = (int)(((Integer)parcel0.readValue(this.getClass().getClassLoader())));
        }

        SavedState(Parcel parcel0, com.google.android.material.checkbox.MaterialCheckBox.1 materialCheckBox$10) {
            this(parcel0);
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        private String getCheckedStateString() {
            switch(this.checkedState) {
                case 1: {
                    return "checked";
                }
                case 2: {
                    return "indeterminate";
                }
                default: {
                    return "unchecked";
                }
            }
        }

        @Override
        public String toString() {
            return "MaterialCheckBox.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " CheckedState=" + this.getCheckedStateString() + "}";
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeValue(this.checkedState);
        }
    }

    private static final int[][] CHECKBOX_STATES = null;
    private static final int DEF_STYLE_RES = 0;
    private static final int[] ERROR_STATE_SET = null;
    private static final int FRAMEWORK_BUTTON_DRAWABLE_RES_ID = 0;
    private static final int[] INDETERMINATE_STATE_SET = null;
    public static final int STATE_CHECKED = 1;
    public static final int STATE_INDETERMINATE = 2;
    public static final int STATE_UNCHECKED;
    private boolean broadcasting;
    private Drawable buttonDrawable;
    private Drawable buttonIconDrawable;
    ColorStateList buttonIconTintList;
    private PorterDuff.Mode buttonIconTintMode;
    ColorStateList buttonTintList;
    private boolean centerIfNoTextEnabled;
    private int checkedState;
    private int[] currentStateChecked;
    private CharSequence customStateDescription;
    private CharSequence errorAccessibilityLabel;
    private boolean errorShown;
    private ColorStateList materialThemeColorsTintList;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private final LinkedHashSet onCheckedStateChangedListeners;
    private final LinkedHashSet onErrorChangedListeners;
    private final AnimatedVectorDrawableCompat transitionToUnchecked;
    private final AnimationCallback transitionToUncheckedCallback;
    private boolean useMaterialThemeColors;
    private boolean usingMaterialButtonDrawable;

    static {
        MaterialCheckBox.DEF_STYLE_RES = style.Widget_MaterialComponents_CompoundButton_CheckBox;
        MaterialCheckBox.INDETERMINATE_STATE_SET = new int[]{attr.state_indeterminate};
        MaterialCheckBox.ERROR_STATE_SET = new int[]{attr.state_error};
        MaterialCheckBox.CHECKBOX_STATES = new int[][]{new int[]{0x101009E, attr.state_error}, new int[]{0x101009E, 0x10100A0}, new int[]{0x101009E, 0xFEFEFF60}, new int[]{0xFEFEFF62, 0x10100A0}, new int[]{0xFEFEFF62, 0xFEFEFF60}};
        MaterialCheckBox.FRAMEWORK_BUTTON_DRAWABLE_RES_ID = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");
    }

    public MaterialCheckBox(Context context0) {
        this(context0, null);
    }

    public MaterialCheckBox(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.checkboxStyle);
    }

    public MaterialCheckBox(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialCheckBox.DEF_STYLE_RES), attributeSet0, v);
        this.onErrorChangedListeners = new LinkedHashSet();
        this.onCheckedStateChangedListeners = new LinkedHashSet();
        this.transitionToUnchecked = AnimatedVectorDrawableCompat.create(this.getContext(), drawable.mtrl_checkbox_button_checked_unchecked);
        this.transitionToUncheckedCallback = new AnimationCallback() {
            @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
            public void onAnimationEnd(Drawable drawable0) {
                super.onAnimationEnd(drawable0);
                if(MaterialCheckBox.this.buttonTintList != null) {
                    DrawableCompat.setTintList(drawable0, MaterialCheckBox.this.buttonTintList);
                }
            }

            @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
            public void onAnimationStart(Drawable drawable0) {
                super.onAnimationStart(drawable0);
                if(MaterialCheckBox.this.buttonTintList != null) {
                    DrawableCompat.setTint(drawable0, MaterialCheckBox.this.buttonTintList.getColorForState(MaterialCheckBox.this.currentStateChecked, MaterialCheckBox.this.buttonTintList.getDefaultColor()));
                }
            }
        };
        Context context1 = this.getContext();
        this.buttonDrawable = CompoundButtonCompat.getButtonDrawable(this);
        this.buttonTintList = this.getSuperButtonTintList();
        this.setSupportButtonTintList(null);
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(context1, attributeSet0, styleable.MaterialCheckBox, v, MaterialCheckBox.DEF_STYLE_RES, new int[0]);
        this.buttonIconDrawable = tintTypedArray0.getDrawable(styleable.MaterialCheckBox_buttonIcon);
        if(this.buttonDrawable != null && ThemeEnforcement.isMaterial3Theme(context1) && this.isButtonDrawableLegacy(tintTypedArray0)) {
            super.setButtonDrawable(null);
            this.buttonDrawable = AppCompatResources.getDrawable(context1, drawable.mtrl_checkbox_button);
            this.usingMaterialButtonDrawable = true;
            if(this.buttonIconDrawable == null) {
                this.buttonIconDrawable = AppCompatResources.getDrawable(context1, drawable.mtrl_checkbox_button_icon);
            }
        }
        this.buttonIconTintList = MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.MaterialCheckBox_buttonIconTint);
        this.buttonIconTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.MaterialCheckBox_buttonIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.useMaterialThemeColors = tintTypedArray0.getBoolean(styleable.MaterialCheckBox_useMaterialThemeColors, false);
        this.centerIfNoTextEnabled = tintTypedArray0.getBoolean(styleable.MaterialCheckBox_centerIfNoTextEnabled, true);
        this.errorShown = tintTypedArray0.getBoolean(styleable.MaterialCheckBox_errorShown, false);
        this.errorAccessibilityLabel = tintTypedArray0.getText(styleable.MaterialCheckBox_errorAccessibilityLabel);
        if(tintTypedArray0.hasValue(styleable.MaterialCheckBox_checkedState)) {
            this.setCheckedState(tintTypedArray0.getInt(styleable.MaterialCheckBox_checkedState, 0));
        }
        tintTypedArray0.recycle();
        this.refreshButtonDrawable();
    }

    public void addOnCheckedStateChangedListener(OnCheckedStateChangedListener materialCheckBox$OnCheckedStateChangedListener0) {
        this.onCheckedStateChangedListeners.add(materialCheckBox$OnCheckedStateChangedListener0);
    }

    public void addOnErrorChangedListener(OnErrorChangedListener materialCheckBox$OnErrorChangedListener0) {
        this.onErrorChangedListeners.add(materialCheckBox$OnErrorChangedListener0);
    }

    public void clearOnCheckedStateChangedListeners() {
        this.onCheckedStateChangedListeners.clear();
    }

    public void clearOnErrorChangedListeners() {
        this.onErrorChangedListeners.clear();
    }

    @Override  // android.widget.CompoundButton
    public Drawable getButtonDrawable() {
        return this.buttonDrawable;
    }

    public Drawable getButtonIconDrawable() {
        return this.buttonIconDrawable;
    }

    public ColorStateList getButtonIconTintList() {
        return this.buttonIconTintList;
    }

    public PorterDuff.Mode getButtonIconTintMode() {
        return this.buttonIconTintMode;
    }

    private String getButtonStateDescription() {
        int v = this.checkedState;
        if(v == 1) {
            return this.getResources().getString(string.mtrl_checkbox_state_description_checked);
        }
        return v == 0 ? this.getResources().getString(string.mtrl_checkbox_state_description_unchecked) : this.getResources().getString(string.mtrl_checkbox_state_description_indeterminate);
    }

    @Override  // android.widget.CompoundButton
    public ColorStateList getButtonTintList() {
        return this.buttonTintList;
    }

    public int getCheckedState() {
        return this.checkedState;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.errorAccessibilityLabel;
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if(this.materialThemeColorsTintList == null) {
            int[] arr_v = new int[MaterialCheckBox.CHECKBOX_STATES.length];
            int v = MaterialColors.getColor(this, attr.colorControlActivated);
            int v1 = MaterialColors.getColor(this, attr.colorError);
            int v2 = MaterialColors.getColor(this, attr.colorSurface);
            int v3 = MaterialColors.getColor(this, attr.colorOnSurface);
            arr_v[0] = MaterialColors.layer(v2, v1, 1.0f);
            arr_v[1] = MaterialColors.layer(v2, v, 1.0f);
            arr_v[2] = MaterialColors.layer(v2, v3, 0.54f);
            arr_v[3] = MaterialColors.layer(v2, v3, 0.38f);
            arr_v[4] = MaterialColors.layer(v2, v3, 0.38f);
            this.materialThemeColorsTintList = new ColorStateList(MaterialCheckBox.CHECKBOX_STATES, arr_v);
        }
        return this.materialThemeColorsTintList;
    }

    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList0 = this.buttonTintList;
        if(colorStateList0 != null) {
            return colorStateList0;
        }
        return super.getButtonTintList() == null ? this.getSupportButtonTintList() : super.getButtonTintList();
    }

    private boolean isButtonDrawableLegacy(TintTypedArray tintTypedArray0) {
        int v = tintTypedArray0.getResourceId(styleable.MaterialCheckBox_android_button, 0);
        int v1 = tintTypedArray0.getResourceId(styleable.MaterialCheckBox_buttonCompat, 0);
        return v == MaterialCheckBox.FRAMEWORK_BUTTON_DRAWABLE_RES_ID && v1 == 0;
    }

    public boolean isCenterIfNoTextEnabled() {
        return this.centerIfNoTextEnabled;
    }

    @Override  // android.widget.CompoundButton
    public boolean isChecked() {
        return this.checkedState == 1;
    }

    public boolean isErrorShown() {
        return this.errorShown;
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    void lambda$new$0$com-google-android-material-checkbox-MaterialCheckBox() {
        this.buttonIconDrawable.jumpToCurrentState();
    }

    @Override  // android.widget.TextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.useMaterialThemeColors && this.buttonTintList == null && this.buttonIconTintList == null) {
            this.setUseMaterialThemeColors(true);
        }
    }

    @Override  // android.widget.CompoundButton
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 2);
        if(this.getCheckedState() == 2) {
            MaterialCheckBox.mergeDrawableStates(arr_v, MaterialCheckBox.INDETERMINATE_STATE_SET);
        }
        if(this.isErrorShown()) {
            MaterialCheckBox.mergeDrawableStates(arr_v, MaterialCheckBox.ERROR_STATE_SET);
        }
        this.currentStateChecked = DrawableUtils.getCheckedState(arr_v);
        return arr_v;
    }

    @Override  // android.widget.CompoundButton
    protected void onDraw(Canvas canvas0) {
        if(this.centerIfNoTextEnabled && TextUtils.isEmpty(this.getText())) {
            Drawable drawable0 = CompoundButtonCompat.getButtonDrawable(this);
            if(drawable0 != null) {
                int v = ViewUtils.isLayoutRtl(this) ? -1 : 1;
                int v1 = (this.getWidth() - drawable0.getIntrinsicWidth()) / 2 * v;
                int v2 = canvas0.save();
                canvas0.translate(((float)v1), 0.0f);
                super.onDraw(canvas0);
                canvas0.restoreToCount(v2);
                if(this.getBackground() != null) {
                    Rect rect0 = drawable0.getBounds();
                    DrawableCompat.setHotspotBounds(this.getBackground(), rect0.left + v1, rect0.top, rect0.right + v1, rect0.bottom);
                }
                return;
            }
        }
        super.onDraw(canvas0);
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        if(accessibilityNodeInfo0 != null && this.isErrorShown()) {
            accessibilityNodeInfo0.setText(accessibilityNodeInfo0.getText() + ", " + this.errorAccessibilityLabel);
        }
    }

    @Override  // android.widget.CompoundButton
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setCheckedState(((SavedState)parcelable0).checkedState);
    }

    @Override  // android.widget.CompoundButton
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.checkedState = this.getCheckedState();
        return parcelable0;
    }

    private void refreshButtonDrawable() {
        this.buttonDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.buttonDrawable, this.buttonTintList, CompoundButtonCompat.getButtonTintMode(this));
        this.buttonIconDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.buttonIconDrawable, this.buttonIconTintList, this.buttonIconTintMode);
        this.setUpDefaultButtonDrawableAnimationIfNeeded();
        this.updateButtonTints();
        super.setButtonDrawable(DrawableUtils.compositeTwoLayeredDrawable(this.buttonDrawable, this.buttonIconDrawable));
        this.refreshDrawableState();
    }

    public void removeOnCheckedStateChangedListener(OnCheckedStateChangedListener materialCheckBox$OnCheckedStateChangedListener0) {
        this.onCheckedStateChangedListeners.remove(materialCheckBox$OnCheckedStateChangedListener0);
    }

    public void removeOnErrorChangedListener(OnErrorChangedListener materialCheckBox$OnErrorChangedListener0) {
        this.onErrorChangedListeners.remove(materialCheckBox$OnErrorChangedListener0);
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    public void setButtonDrawable(int v) {
        this.setButtonDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    public void setButtonDrawable(Drawable drawable0) {
        this.buttonDrawable = drawable0;
        this.usingMaterialButtonDrawable = false;
        this.refreshButtonDrawable();
    }

    public void setButtonIconDrawable(Drawable drawable0) {
        this.buttonIconDrawable = drawable0;
        this.refreshButtonDrawable();
    }

    public void setButtonIconDrawableResource(int v) {
        this.setButtonIconDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setButtonIconTintList(ColorStateList colorStateList0) {
        if(this.buttonIconTintList == colorStateList0) {
            return;
        }
        this.buttonIconTintList = colorStateList0;
        this.refreshButtonDrawable();
    }

    public void setButtonIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.buttonIconTintMode == porterDuff$Mode0) {
            return;
        }
        this.buttonIconTintMode = porterDuff$Mode0;
        this.refreshButtonDrawable();
    }

    @Override  // android.widget.CompoundButton
    public void setButtonTintList(ColorStateList colorStateList0) {
        if(this.buttonTintList == colorStateList0) {
            return;
        }
        this.buttonTintList = colorStateList0;
        this.refreshButtonDrawable();
    }

    @Override  // android.widget.CompoundButton
    public void setButtonTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.setSupportButtonTintMode(porterDuff$Mode0);
        this.refreshButtonDrawable();
    }

    public void setCenterIfNoTextEnabled(boolean z) {
        this.centerIfNoTextEnabled = z;
    }

    @Override  // android.widget.CompoundButton
    public void setChecked(boolean z) {
        this.setCheckedState(((int)z));
    }

    public void setCheckedState(int v) {
        if(this.checkedState != v) {
            this.checkedState = v;
            super.setChecked(v == 1);
            this.refreshDrawableState();
            this.setDefaultStateDescription();
            if(!this.broadcasting) {
                this.broadcasting = true;
                LinkedHashSet linkedHashSet0 = this.onCheckedStateChangedListeners;
                if(linkedHashSet0 != null) {
                    for(Object object0: linkedHashSet0) {
                        ((OnCheckedStateChangedListener)object0).onCheckedStateChangedListener(this, this.checkedState);
                    }
                }
                if(this.checkedState != 2) {
                    CompoundButton.OnCheckedChangeListener compoundButton$OnCheckedChangeListener0 = this.onCheckedChangeListener;
                    if(compoundButton$OnCheckedChangeListener0 != null) {
                        compoundButton$OnCheckedChangeListener0.onCheckedChanged(this, this.isChecked());
                    }
                }
                if(Build.VERSION.SDK_INT >= 26) {
                    AutofillManager autofillManager0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(this.getContext().getSystemService(AutofillManager.class));
                    if(autofillManager0 != null) {
                        autofillManager0.notifyValueChanged(this);
                    }
                }
                this.broadcasting = false;
            }
        }
    }

    private void setDefaultStateDescription() {
        if(Build.VERSION.SDK_INT >= 30 && this.customStateDescription == null) {
            super.setStateDescription(this.getButtonStateDescription());
        }
    }

    @Override  // android.widget.TextView
    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    public void setErrorAccessibilityLabel(CharSequence charSequence0) {
        this.errorAccessibilityLabel = charSequence0;
    }

    public void setErrorAccessibilityLabelResource(int v) {
        this.setErrorAccessibilityLabel((v == 0 ? null : this.getResources().getText(v)));
    }

    public void setErrorShown(boolean z) {
        if(this.errorShown != z) {
            this.errorShown = z;
            this.refreshDrawableState();
            for(Object object0: this.onErrorChangedListeners) {
                ((OnErrorChangedListener)object0).onErrorChanged(this, this.errorShown);
            }
        }
    }

    @Override  // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener compoundButton$OnCheckedChangeListener0) {
        this.onCheckedChangeListener = compoundButton$OnCheckedChangeListener0;
    }

    @Override  // android.widget.CompoundButton
    public void setStateDescription(CharSequence charSequence0) {
        this.customStateDescription = charSequence0;
        if(charSequence0 == null) {
            this.setDefaultStateDescription();
            return;
        }
        super.setStateDescription(charSequence0);
    }

    private void setUpDefaultButtonDrawableAnimationIfNeeded() {
        if(this.usingMaterialButtonDrawable) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat0 = this.transitionToUnchecked;
            if(animatedVectorDrawableCompat0 != null) {
                animatedVectorDrawableCompat0.unregisterAnimationCallback(this.transitionToUncheckedCallback);
                this.transitionToUnchecked.registerAnimationCallback(this.transitionToUncheckedCallback);
            }
            if(Build.VERSION.SDK_INT >= 24) {
                Drawable drawable0 = this.buttonDrawable;
                if(drawable0 instanceof AnimatedStateListDrawable && this.transitionToUnchecked != null) {
                    ((AnimatedStateListDrawable)drawable0).addTransition(id.checked, id.unchecked, this.transitionToUnchecked, false);
                    ((AnimatedStateListDrawable)this.buttonDrawable).addTransition(id.indeterminate, id.unchecked, this.transitionToUnchecked, false);
                }
            }
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.useMaterialThemeColors = z;
        if(z) {
            CompoundButtonCompat.setButtonTintList(this, this.getMaterialThemeColorsTintList());
            return;
        }
        CompoundButtonCompat.setButtonTintList(this, null);
    }

    @Override  // android.widget.CompoundButton
    public void toggle() {
        this.setChecked(!this.isChecked());
    }

    private void updateButtonTints() {
        Drawable drawable0 = this.buttonDrawable;
        if(drawable0 != null) {
            ColorStateList colorStateList0 = this.buttonTintList;
            if(colorStateList0 != null) {
                DrawableCompat.setTintList(drawable0, colorStateList0);
            }
        }
        Drawable drawable1 = this.buttonIconDrawable;
        if(drawable1 != null) {
            ColorStateList colorStateList1 = this.buttonIconTintList;
            if(colorStateList1 != null) {
                DrawableCompat.setTintList(drawable1, colorStateList1);
            }
        }
    }

    private void updateIconTintIfNeeded() {
    }
}

