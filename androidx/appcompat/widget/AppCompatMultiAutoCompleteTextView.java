package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.TintableCompoundDrawablesView;

public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements EmojiCompatConfigurationView, TintableBackgroundView, TintableCompoundDrawablesView {
    private static final int[] TINT_ATTRS;
    private final AppCompatEmojiEditTextHelper mAppCompatEmojiEditTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    static {
        AppCompatMultiAutoCompleteTextView.TINT_ATTRS = new int[]{0x1010176};
    }

    public AppCompatMultiAutoCompleteTextView(Context context0) {
        this(context0, null);
    }

    public AppCompatMultiAutoCompleteTextView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context0, AttributeSet attributeSet0, int v) {
        super(TintContextWrapper.wrap(context0), attributeSet0, v);
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet0, AppCompatMultiAutoCompleteTextView.TINT_ATTRS, v, 0);
        if(tintTypedArray0.hasValue(0)) {
            this.setDropDownBackgroundDrawable(tintTypedArray0.getDrawable(0));
        }
        tintTypedArray0.recycle();
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper0;
        appCompatBackgroundHelper0.loadFromAttributes(attributeSet0, v);
        AppCompatTextHelper appCompatTextHelper0 = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper0;
        appCompatTextHelper0.loadFromAttributes(attributeSet0, v);
        appCompatTextHelper0.applyCompoundDrawablesTints();
        AppCompatEmojiEditTextHelper appCompatEmojiEditTextHelper0 = new AppCompatEmojiEditTextHelper(this);
        this.mAppCompatEmojiEditTextHelper = appCompatEmojiEditTextHelper0;
        appCompatEmojiEditTextHelper0.loadFromAttributes(attributeSet0, v);
        this.initEmojiKeyListener(appCompatEmojiEditTextHelper0);
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

    void initEmojiKeyListener(AppCompatEmojiEditTextHelper appCompatEmojiEditTextHelper0) {
        KeyListener keyListener0 = this.getKeyListener();
        if(appCompatEmojiEditTextHelper0.isEmojiCapableKeyListener(keyListener0)) {
            boolean z = super.isFocusable();
            boolean z1 = super.isClickable();
            boolean z2 = super.isLongClickable();
            int v = super.getInputType();
            KeyListener keyListener1 = appCompatEmojiEditTextHelper0.getKeyListener(keyListener0);
            if(keyListener1 != keyListener0) {
                super.setKeyListener(keyListener1);
                super.setRawInputType(v);
                super.setFocusable(z);
                super.setClickable(z1);
                super.setLongClickable(z2);
            }
        }
    }

    @Override  // androidx.appcompat.widget.EmojiCompatConfigurationView
    public boolean isEmojiCompatEnabled() {
        return this.mAppCompatEmojiEditTextHelper.isEnabled();
    }

    @Override  // android.widget.TextView
    public InputConnection onCreateInputConnection(EditorInfo editorInfo0) {
        InputConnection inputConnection0 = AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorInfo0), editorInfo0, this);
        return this.mAppCompatEmojiEditTextHelper.onCreateInputConnection(inputConnection0, editorInfo0);
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

    @Override  // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int v) {
        this.setDropDownBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    @Override  // androidx.appcompat.widget.EmojiCompatConfigurationView
    public void setEmojiCompatEnabled(boolean z) {
        this.mAppCompatEmojiEditTextHelper.setEnabled(z);
    }

    @Override  // android.widget.TextView
    public void setKeyListener(KeyListener keyListener0) {
        super.setKeyListener(this.mAppCompatEmojiEditTextHelper.getKeyListener(keyListener0));
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
}

