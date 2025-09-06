package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TintableCheckedTextView;
import androidx.core.widget.TintableCompoundDrawablesView;

public class AppCompatCheckedTextView extends CheckedTextView implements EmojiCompatConfigurationView, TintableBackgroundView, TintableCheckedTextView, TintableCompoundDrawablesView {
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatCheckedTextViewHelper mCheckedHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatCheckedTextView(Context context0) {
        this(context0, null);
    }

    public AppCompatCheckedTextView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.checkedTextViewStyle);
    }

    public AppCompatCheckedTextView(Context context0, AttributeSet attributeSet0, int v) {
        super(TintContextWrapper.wrap(context0), attributeSet0, v);
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        AppCompatTextHelper appCompatTextHelper0 = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper0;
        appCompatTextHelper0.loadFromAttributes(attributeSet0, v);
        appCompatTextHelper0.applyCompoundDrawablesTints();
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper0;
        appCompatBackgroundHelper0.loadFromAttributes(attributeSet0, v);
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper0 = new AppCompatCheckedTextViewHelper(this);
        this.mCheckedHelper = appCompatCheckedTextViewHelper0;
        appCompatCheckedTextViewHelper0.loadFromAttributes(attributeSet0, v);
        this.getEmojiTextViewHelper().loadFromAttributes(attributeSet0, v);
    }

    @Override  // android.widget.CheckedTextView
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.applyCompoundDrawablesTints();
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.applySupportBackgroundTint();
        }
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper0 = this.mCheckedHelper;
        if(appCompatCheckedTextViewHelper0 != null) {
            appCompatCheckedTextViewHelper0.applyCheckMarkTint();
        }
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

    @Override  // androidx.core.widget.TintableCheckedTextView
    public ColorStateList getSupportCheckMarkTintList() {
        return this.mCheckedHelper == null ? null : this.mCheckedHelper.getSupportCheckMarkTintList();
    }

    @Override  // androidx.core.widget.TintableCheckedTextView
    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        return this.mCheckedHelper == null ? null : this.mCheckedHelper.getSupportCheckMarkTintMode();
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

    @Override  // android.widget.TextView
    public InputConnection onCreateInputConnection(EditorInfo editorInfo0) {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorInfo0), editorInfo0, this);
    }

    @Override  // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        this.getEmojiTextViewHelper().setAllCaps(z);
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

    @Override  // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int v) {
        this.setCheckMarkDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    @Override  // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable0) {
        super.setCheckMarkDrawable(drawable0);
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper0 = this.mCheckedHelper;
        if(appCompatCheckedTextViewHelper0 != null) {
            appCompatCheckedTextViewHelper0.onSetCheckMarkDrawable();
        }
    }

    @Override  // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        super.setCompoundDrawables(drawable0, drawable1, drawable2, drawable3);
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.onSetCompoundDrawables();
        }
    }

    @Override  // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        super.setCompoundDrawablesRelative(drawable0, drawable1, drawable2, drawable3);
        AppCompatTextHelper appCompatTextHelper0 = this.mTextHelper;
        if(appCompatTextHelper0 != null) {
            appCompatTextHelper0.onSetCompoundDrawables();
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

    @Override  // androidx.core.widget.TintableCheckedTextView
    public void setSupportCheckMarkTintList(ColorStateList colorStateList0) {
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper0 = this.mCheckedHelper;
        if(appCompatCheckedTextViewHelper0 != null) {
            appCompatCheckedTextViewHelper0.setSupportCheckMarkTintList(colorStateList0);
        }
    }

    @Override  // androidx.core.widget.TintableCheckedTextView
    public void setSupportCheckMarkTintMode(PorterDuff.Mode porterDuff$Mode0) {
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper0 = this.mCheckedHelper;
        if(appCompatCheckedTextViewHelper0 != null) {
            appCompatCheckedTextViewHelper0.setSupportCheckMarkTintMode(porterDuff$Mode0);
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
}

