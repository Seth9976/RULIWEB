package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.appcompat.R.attr;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentViewBehavior;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.core.widget.TintableCompoundDrawablesView;

public class AppCompatEditText extends EditText implements EmojiCompatConfigurationView, OnReceiveContentViewBehavior, TintableBackgroundView, TintableCompoundDrawablesView {
    class SuperCaller {
        public TextClassifier getTextClassifier() {
            return AppCompatEditText.this.super.getTextClassifier();
        }

        public void setTextClassifier(TextClassifier textClassifier0) {
            AppCompatEditText.this.super.setTextClassifier(textClassifier0);
        }
    }

    private final AppCompatEmojiEditTextHelper mAppCompatEmojiEditTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final TextViewOnReceiveContentListener mDefaultOnReceiveContentListener;
    private SuperCaller mSuperCaller;
    private final AppCompatTextClassifierHelper mTextClassifierHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatEditText(Context context0) {
        this(context0, null);
    }

    public AppCompatEditText(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.editTextStyle);
    }

    public AppCompatEditText(Context context0, AttributeSet attributeSet0, int v) {
        super(TintContextWrapper.wrap(context0), attributeSet0, v);
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper0;
        appCompatBackgroundHelper0.loadFromAttributes(attributeSet0, v);
        AppCompatTextHelper appCompatTextHelper0 = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper0;
        appCompatTextHelper0.loadFromAttributes(attributeSet0, v);
        appCompatTextHelper0.applyCompoundDrawablesTints();
        this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
        this.mDefaultOnReceiveContentListener = new TextViewOnReceiveContentListener();
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

    @Override  // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    private SuperCaller getSuperCaller() {
        if(this.mSuperCaller == null) {
            this.mSuperCaller = new SuperCaller(this);
        }
        return this.mSuperCaller;
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

    @Override  // android.widget.EditText
    public Editable getText() {
        return Build.VERSION.SDK_INT < 28 ? super.getEditableText() : super.getText();
    }

    @Override  // android.widget.EditText
    public CharSequence getText() {
        return this.getText();
    }

    @Override  // android.widget.TextView
    public TextClassifier getTextClassifier() {
        if(Build.VERSION.SDK_INT < 28) {
            return this.mTextClassifierHelper == null ? this.getSuperCaller().getTextClassifier() : this.mTextClassifierHelper.getTextClassifier();
        }
        return this.getSuperCaller().getTextClassifier();
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
        InputConnection inputConnection0 = super.onCreateInputConnection(editorInfo0);
        this.mTextHelper.populateSurroundingTextIfNeeded(this, inputConnection0, editorInfo0);
        InputConnection inputConnection1 = AppCompatHintHelper.onCreateInputConnection(inputConnection0, editorInfo0, this);
        if(inputConnection1 != null && Build.VERSION.SDK_INT <= 30) {
            String[] arr_s = ViewCompat.getOnReceiveContentMimeTypes(this);
            if(arr_s != null) {
                EditorInfoCompat.setContentMimeTypes(editorInfo0, arr_s);
                inputConnection1 = InputConnectionCompat.createWrapper(this, inputConnection1, editorInfo0);
            }
        }
        return this.mAppCompatEmojiEditTextHelper.onCreateInputConnection(inputConnection1, editorInfo0);
    }

    @Override  // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(Build.VERSION.SDK_INT >= 30 && Build.VERSION.SDK_INT < 33) {
            ((InputMethodManager)this.getContext().getSystemService("input_method")).isActive(this);
        }
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.TextView
    public boolean onDragEvent(DragEvent dragEvent0) {
        return AppCompatReceiveContentHelper.maybeHandleDragEventViaPerformReceiveContent(this, dragEvent0) ? true : super.onDragEvent(dragEvent0);
    }

    @Override  // androidx.core.view.OnReceiveContentViewBehavior
    public ContentInfoCompat onReceiveContent(ContentInfoCompat contentInfoCompat0) {
        return this.mDefaultOnReceiveContentListener.onReceiveContent(this, contentInfoCompat0);
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.EditText
    public boolean onTextContextMenuItem(int v) {
        return AppCompatReceiveContentHelper.maybeHandleMenuActionViaPerformReceiveContent(this, v) ? true : super.onTextContextMenuItem(v);
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

    @Override  // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionMode$Callback0) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, actionMode$Callback0));
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

    @Override  // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier0) {
        if(Build.VERSION.SDK_INT < 28) {
            AppCompatTextClassifierHelper appCompatTextClassifierHelper0 = this.mTextClassifierHelper;
            if(appCompatTextClassifierHelper0 != null) {
                appCompatTextClassifierHelper0.setTextClassifier(textClassifier0);
                return;
            }
        }
        this.getSuperCaller().setTextClassifier(textClassifier0);
    }
}

