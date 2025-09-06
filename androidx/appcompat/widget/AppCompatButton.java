package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R.attr;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TintableCompoundDrawablesView;

public class AppCompatButton extends Button implements EmojiCompatConfigurationView, TintableBackgroundView, AutoSizeableTextView, TintableCompoundDrawablesView {
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatButton(Context context0) {
        this(context0, null);
    }

    public AppCompatButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.buttonStyle);
    }

    public AppCompatButton(Context context0, AttributeSet attributeSet0, int v) {
        super(TintContextWrapper.wrap(context0), attributeSet0, v);
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper0;
        appCompatBackgroundHelper0.loadFromAttributes(attributeSet0, v);
        AppCompatTextHelper appCompatTextHelper0 = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper0;
        appCompatTextHelper0.loadFromAttributes(attributeSet0, v);
        appCompatTextHelper0.applyCompoundDrawablesTints();
        this.getEmojiTextViewHelper().loadFromAttributes(attributeSet0, v);
    }

    @Override  // android.widget.TextView
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.applySupportBackgroundTint();
        }
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.applyCompoundDrawablesTints();
        }
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeMaxTextSize();
        }
        return this.mTextHelper == null ? -1 : this.mTextHelper.getAutoSizeMaxTextSize();
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeMinTextSize();
        }
        return this.mTextHelper == null ? -1 : this.mTextHelper.getAutoSizeMinTextSize();
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeStepGranularity();
        }
        return this.mTextHelper == null ? -1 : this.mTextHelper.getAutoSizeStepGranularity();
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeTextAvailableSizes();
        }
        return this.mTextHelper == null ? new int[0] : this.mTextHelper.getAutoSizeTextAvailableSizes();
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public int getAutoSizeTextType() {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        return this.mTextHelper == null ? 0 : this.mTextHelper.getAutoSizeTextType();
    }

    @Override  // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if(this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
        }
        return this.mAppCompatEmojiTextHelper;
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper == null ? null : this.mBackgroundTintHelper.getSupportBackgroundTintList();
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper == null ? null : this.mBackgroundTintHelper.getSupportBackgroundTintMode();
    }

    @Override  // androidx.core.widget.TintableCompoundDrawablesView
    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.getCompoundDrawableTintList();
    }

    @Override  // androidx.core.widget.TintableCompoundDrawablesView
    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.getCompoundDrawableTintMode();
    }

    @Override  // androidx.appcompat.widget.EmojiCompatConfigurationView
    public boolean isEmojiCompatEnabled() {
        return this.getEmojiTextViewHelper().isEnabled();
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        accessibilityEvent0.setClassName("android.widget.Button");
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName("android.widget.Button");
    }

    @Override  // android.widget.TextView
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.onLayout(z, v, v1, v2, v3);
        }
    }

    @Override  // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
        super.onTextChanged(charSequence0, v, v1, v2);
        if(this.mTextHelper != null && !ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && this.mTextHelper.isAutoSizeEnabled()) {
            this.mTextHelper.autoSizeText();
        }
    }

    @Override  // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        this.getEmojiTextViewHelper().setAllCaps(z);
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int v, int v1, int v2, int v3) throws IllegalArgumentException {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            super.setAutoSizeTextTypeUniformWithConfiguration(v, v1, v2, v3);
            return;
        }
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.setAutoSizeTextTypeUniformWithConfiguration(v, v1, v2, v3);
        }
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] arr_v, int v) throws IllegalArgumentException {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(arr_v, v);
            return;
        }
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.setAutoSizeTextTypeUniformWithPresetSizes(arr_v, v);
        }
    }

    @Override  // androidx.core.widget.AutoSizeableTextView, android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int v) {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            super.setAutoSizeTextTypeWithDefaults(v);
            return;
        }
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.setAutoSizeTextTypeWithDefaults(v);
        }
    }

    @Override  // android.view.View
    public void setBackgroundDrawable(Drawable drawable0) {
        super.setBackgroundDrawable(drawable0);
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.onSetBackgroundDrawable(drawable0);
        }
    }

    @Override  // android.view.View
    public void setBackgroundResource(int v) {
        super.setBackgroundResource(v);
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.onSetBackgroundResource(v);
        }
    }

    @Override  // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionMode$Callback0) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, actionMode$Callback0));
    }

    @Override  // androidx.appcompat.widget.EmojiCompatConfigurationView
    public void setEmojiCompatEnabled(boolean z) {
        this.getEmojiTextViewHelper().setEnabled(z);
    }

    @Override  // android.widget.TextView
    public void setFilters(InputFilter[] arr_inputFilter) {
        super.setFilters(this.getEmojiTextViewHelper().getFilters(arr_inputFilter));
    }

    public void setSupportAllCaps(boolean z) {
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.setAllCaps(z);
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.setSupportBackgroundTintList(colorStateList0);
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.setSupportBackgroundTintMode(porterDuff$Mode0);
        }
    }

    @Override  // androidx.core.widget.TintableCompoundDrawablesView
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList0) {
        this.mTextHelper.setCompoundDrawableTintList(colorStateList0);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    @Override  // androidx.core.widget.TintableCompoundDrawablesView
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mTextHelper.setCompoundDrawableTintMode(porterDuff$Mode0);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    @Override  // android.widget.TextView
    public void setTextAppearance(Context context0, int v) {
        super.setTextAppearance(context0, v);
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.onSetTextAppearance(context0, v);
        }
    }

    @Override  // android.widget.TextView
    public void setTextSize(int v, float f) {
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            super.setTextSize(v, f);
            return;
        }
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.setTextSize(v, f);
        }
    }
}

