package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.LinkedHashSet;

class EndCompoundLayout extends LinearLayout {
    static class EndIconDelegates {
        private final int customEndIconDrawableId;
        private final SparseArray delegates;
        private final EndCompoundLayout endLayout;
        private final int passwordIconDrawableId;

        EndIconDelegates(EndCompoundLayout endCompoundLayout0, TintTypedArray tintTypedArray0) {
            this.delegates = new SparseArray();
            this.endLayout = endCompoundLayout0;
            this.customEndIconDrawableId = tintTypedArray0.getResourceId(styleable.TextInputLayout_endIconDrawable, 0);
            this.passwordIconDrawableId = tintTypedArray0.getResourceId(styleable.TextInputLayout_passwordToggleDrawable, 0);
        }

        static int access$500(EndIconDelegates endCompoundLayout$EndIconDelegates0) {
            return endCompoundLayout$EndIconDelegates0.customEndIconDrawableId;
        }

        private EndIconDelegate create(int v) {
            switch(v) {
                case -1: {
                    return new CustomEndIconDelegate(this.endLayout);
                }
                case 0: {
                    return new NoEndIconDelegate(this.endLayout);
                }
                case 1: {
                    return new PasswordToggleEndIconDelegate(this.endLayout, this.passwordIconDrawableId);
                }
                case 2: {
                    return new ClearTextEndIconDelegate(this.endLayout);
                }
                case 3: {
                    return new DropdownMenuEndIconDelegate(this.endLayout);
                }
                default: {
                    throw new IllegalArgumentException("Invalid end icon mode: " + v);
                }
            }
        }

        EndIconDelegate get(int v) {
            EndIconDelegate endIconDelegate0 = (EndIconDelegate)this.delegates.get(v);
            if(endIconDelegate0 == null) {
                endIconDelegate0 = this.create(v);
                this.delegates.append(v, endIconDelegate0);
            }
            return endIconDelegate0;
        }
    }

    private final AccessibilityManager accessibilityManager;
    private EditText editText;
    private final TextWatcher editTextWatcher;
    private final LinkedHashSet endIconChangedListeners;
    private final EndIconDelegates endIconDelegates;
    private final FrameLayout endIconFrame;
    private int endIconMinSize;
    private int endIconMode;
    private View.OnLongClickListener endIconOnLongClickListener;
    private ImageView.ScaleType endIconScaleType;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;
    private final CheckableImageButton endIconView;
    private View.OnLongClickListener errorIconOnLongClickListener;
    private ColorStateList errorIconTintList;
    private PorterDuff.Mode errorIconTintMode;
    private final CheckableImageButton errorIconView;
    private boolean hintExpanded;
    private final OnEditTextAttachedListener onEditTextAttachedListener;
    private CharSequence suffixText;
    private final TextView suffixTextView;
    final TextInputLayout textInputLayout;
    private TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    EndCompoundLayout(TextInputLayout textInputLayout0, TintTypedArray tintTypedArray0) {
        super(textInputLayout0.getContext());
        this.endIconMode = 0;
        this.endIconChangedListeners = new LinkedHashSet();
        this.editTextWatcher = new TextWatcherAdapter() {
            @Override  // com.google.android.material.internal.TextWatcherAdapter
            public void afterTextChanged(Editable editable0) {
                EndCompoundLayout.this.getEndIconDelegate().afterEditTextChanged(editable0);
            }

            @Override  // com.google.android.material.internal.TextWatcherAdapter
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                EndCompoundLayout.this.getEndIconDelegate().beforeEditTextChanged(charSequence0, v, v1, v2);
            }
        };
        com.google.android.material.textfield.EndCompoundLayout.2 endCompoundLayout$20 = new OnEditTextAttachedListener() {
            @Override  // com.google.android.material.textfield.TextInputLayout$OnEditTextAttachedListener
            public void onEditTextAttached(TextInputLayout textInputLayout0) {
                if(EndCompoundLayout.this.editText == textInputLayout0.getEditText()) {
                    return;
                }
                if(EndCompoundLayout.this.editText != null) {
                    EndCompoundLayout.this.editText.removeTextChangedListener(EndCompoundLayout.this.editTextWatcher);
                    if(EndCompoundLayout.this.editText.getOnFocusChangeListener() == EndCompoundLayout.this.getEndIconDelegate().getOnEditTextFocusChangeListener()) {
                        EndCompoundLayout.this.editText.setOnFocusChangeListener(null);
                    }
                }
                EndCompoundLayout.this.editText = textInputLayout0.getEditText();
                if(EndCompoundLayout.this.editText != null) {
                    EndCompoundLayout.this.editText.addTextChangedListener(EndCompoundLayout.this.editTextWatcher);
                }
                EndCompoundLayout.this.getEndIconDelegate().onEditTextAttached(EndCompoundLayout.this.editText);
                EndIconDelegate endIconDelegate0 = EndCompoundLayout.this.getEndIconDelegate();
                EndCompoundLayout.this.setOnFocusChangeListenersIfNeeded(endIconDelegate0);
            }
        };
        this.onEditTextAttachedListener = endCompoundLayout$20;
        this.accessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.textInputLayout = textInputLayout0;
        this.setVisibility(8);
        this.setOrientation(0);
        this.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 0x800005));
        FrameLayout frameLayout0 = new FrameLayout(this.getContext());
        this.endIconFrame = frameLayout0;
        frameLayout0.setVisibility(8);
        frameLayout0.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater layoutInflater0 = LayoutInflater.from(this.getContext());
        CheckableImageButton checkableImageButton0 = this.createIconView(this, layoutInflater0, id.text_input_error_icon);
        this.errorIconView = checkableImageButton0;
        CheckableImageButton checkableImageButton1 = this.createIconView(frameLayout0, layoutInflater0, id.text_input_end_icon);
        this.endIconView = checkableImageButton1;
        this.endIconDelegates = new EndIconDelegates(this, tintTypedArray0);
        AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.getContext());
        this.suffixTextView = appCompatTextView0;
        this.initErrorIconView(tintTypedArray0);
        this.initEndIconView(tintTypedArray0);
        this.initSuffixTextView(tintTypedArray0);
        frameLayout0.addView(checkableImageButton1);
        this.addView(appCompatTextView0);
        this.addView(frameLayout0);
        this.addView(checkableImageButton0);
        textInputLayout0.addOnEditTextAttachedListener(endCompoundLayout$20);
        this.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view0) {
                EndCompoundLayout.access$300(EndCompoundLayout.this);
            }

            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view0) {
                EndCompoundLayout.access$400(EndCompoundLayout.this);
            }
        });
    }

    static void access$300(EndCompoundLayout endCompoundLayout0) {
        endCompoundLayout0.addTouchExplorationStateChangeListenerIfNeeded();
    }

    static void access$400(EndCompoundLayout endCompoundLayout0) {
        endCompoundLayout0.removeTouchExplorationStateChangeListenerIfNeeded();
    }

    void addOnEndIconChangedListener(OnEndIconChangedListener textInputLayout$OnEndIconChangedListener0) {
        this.endIconChangedListeners.add(textInputLayout$OnEndIconChangedListener0);
    }

    private void addTouchExplorationStateChangeListenerIfNeeded() {
        if(this.touchExplorationStateChangeListener != null && this.accessibilityManager != null && ViewCompat.isAttachedToWindow(this)) {
            AccessibilityManagerCompat.addTouchExplorationStateChangeListener(this.accessibilityManager, this.touchExplorationStateChangeListener);
        }
    }

    void checkEndIcon() {
        this.endIconView.performClick();
        this.endIconView.jumpDrawablesToCurrentState();
    }

    void clearOnEndIconChangedListeners() {
        this.endIconChangedListeners.clear();
    }

    private CheckableImageButton createIconView(ViewGroup viewGroup0, LayoutInflater layoutInflater0, int v) {
        CheckableImageButton checkableImageButton0 = (CheckableImageButton)layoutInflater0.inflate(layout.design_text_input_end_icon, viewGroup0, false);
        checkableImageButton0.setId(v);
        IconHelper.setCompatRippleBackgroundIfNeeded(checkableImageButton0);
        if(MaterialResources.isFontScaleAtLeast1_3(this.getContext())) {
            MarginLayoutParamsCompat.setMarginStart(((ViewGroup.MarginLayoutParams)checkableImageButton0.getLayoutParams()), 0);
        }
        return checkableImageButton0;
    }

    private void dispatchOnEndIconChanged(int v) {
        for(Object object0: this.endIconChangedListeners) {
            ((OnEndIconChangedListener)object0).onEndIconChanged(this.textInputLayout, v);
        }
    }

    CheckableImageButton getCurrentEndIconView() {
        if(this.isErrorIconVisible()) {
            return this.errorIconView;
        }
        return !this.hasEndIcon() || !this.isEndIconVisible() ? null : this.endIconView;
    }

    CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    EndIconDelegate getEndIconDelegate() {
        return this.endIconDelegates.get(this.endIconMode);
    }

    Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    int getEndIconMinSize() {
        return this.endIconMinSize;
    }

    int getEndIconMode() {
        return this.endIconMode;
    }

    ImageView.ScaleType getEndIconScaleType() {
        return this.endIconScaleType;
    }

    CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    Drawable getErrorIconDrawable() {
        return this.errorIconView.getDrawable();
    }

    private int getIconResId(EndIconDelegate endIconDelegate0) {
        int v = EndIconDelegates.access$500(this.endIconDelegates);
        return v == 0 ? endIconDelegate0.getIconDrawableResId() : v;
    }

    CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    CharSequence getSuffixText() {
        return this.suffixText;
    }

    ColorStateList getSuffixTextColor() {
        return this.suffixTextView.getTextColors();
    }

    int getSuffixTextEndOffset() {
        if(!this.isEndIconVisible() && !this.isErrorIconVisible()) {
            return ViewCompat.getPaddingEnd(this) + ViewCompat.getPaddingEnd(this.suffixTextView);
        }
        int v = this.endIconView.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginStart(((ViewGroup.MarginLayoutParams)this.endIconView.getLayoutParams()));
        return ViewCompat.getPaddingEnd(this) + ViewCompat.getPaddingEnd(this.suffixTextView) + v;
    }

    TextView getSuffixTextView() {
        return this.suffixTextView;
    }

    boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    private void initEndIconView(TintTypedArray tintTypedArray0) {
        if(!tintTypedArray0.hasValue(styleable.TextInputLayout_passwordToggleEnabled)) {
            if(tintTypedArray0.hasValue(styleable.TextInputLayout_endIconTint)) {
                this.endIconTintList = MaterialResources.getColorStateList(this.getContext(), tintTypedArray0, styleable.TextInputLayout_endIconTint);
            }
            if(tintTypedArray0.hasValue(styleable.TextInputLayout_endIconTintMode)) {
                this.endIconTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.TextInputLayout_endIconTintMode, -1), null);
            }
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_endIconMode)) {
            this.setEndIconMode(tintTypedArray0.getInt(styleable.TextInputLayout_endIconMode, 0));
            if(tintTypedArray0.hasValue(styleable.TextInputLayout_endIconContentDescription)) {
                this.setEndIconContentDescription(tintTypedArray0.getText(styleable.TextInputLayout_endIconContentDescription));
            }
            this.setEndIconCheckable(tintTypedArray0.getBoolean(styleable.TextInputLayout_endIconCheckable, true));
        }
        else if(tintTypedArray0.hasValue(styleable.TextInputLayout_passwordToggleEnabled)) {
            if(tintTypedArray0.hasValue(styleable.TextInputLayout_passwordToggleTint)) {
                this.endIconTintList = MaterialResources.getColorStateList(this.getContext(), tintTypedArray0, styleable.TextInputLayout_passwordToggleTint);
            }
            if(tintTypedArray0.hasValue(styleable.TextInputLayout_passwordToggleTintMode)) {
                this.endIconTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.TextInputLayout_passwordToggleTintMode, -1), null);
            }
            this.setEndIconMode(((int)tintTypedArray0.getBoolean(styleable.TextInputLayout_passwordToggleEnabled, false)));
            this.setEndIconContentDescription(tintTypedArray0.getText(styleable.TextInputLayout_passwordToggleContentDescription));
        }
        int v = this.getResources().getDimensionPixelSize(dimen.mtrl_min_touch_target_size);
        this.setEndIconMinSize(tintTypedArray0.getDimensionPixelSize(styleable.TextInputLayout_endIconMinSize, v));
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_endIconScaleType)) {
            this.setEndIconScaleType(IconHelper.convertScaleType(tintTypedArray0.getInt(styleable.TextInputLayout_endIconScaleType, -1)));
        }
    }

    private void initErrorIconView(TintTypedArray tintTypedArray0) {
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_errorIconTint)) {
            this.errorIconTintList = MaterialResources.getColorStateList(this.getContext(), tintTypedArray0, styleable.TextInputLayout_errorIconTint);
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_errorIconTintMode)) {
            this.errorIconTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.TextInputLayout_errorIconTintMode, -1), null);
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_errorIconDrawable)) {
            this.setErrorIconDrawable(tintTypedArray0.getDrawable(styleable.TextInputLayout_errorIconDrawable));
        }
        CharSequence charSequence0 = this.getResources().getText(string.error_icon_content_description);
        this.errorIconView.setContentDescription(charSequence0);
        ViewCompat.setImportantForAccessibility(this.errorIconView, 2);
        this.errorIconView.setClickable(false);
        this.errorIconView.setPressable(false);
        this.errorIconView.setFocusable(false);
    }

    private void initSuffixTextView(TintTypedArray tintTypedArray0) {
        this.suffixTextView.setVisibility(8);
        this.suffixTextView.setId(id.textinput_suffix_text);
        LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(-2, -2, 80.0f);
        this.suffixTextView.setLayoutParams(linearLayout$LayoutParams0);
        ViewCompat.setAccessibilityLiveRegion(this.suffixTextView, 1);
        this.setSuffixTextAppearance(tintTypedArray0.getResourceId(styleable.TextInputLayout_suffixTextAppearance, 0));
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_suffixTextColor)) {
            this.setSuffixTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_suffixTextColor));
        }
        this.setSuffixText(tintTypedArray0.getText(styleable.TextInputLayout_suffixText));
    }

    boolean isEndIconCheckable() {
        return this.endIconView.isCheckable();
    }

    // 去混淆评级： 低(20)
    boolean isEndIconChecked() {
        return this.hasEndIcon() && this.endIconView.isChecked();
    }

    boolean isEndIconVisible() {
        return this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0;
    }

    boolean isErrorIconVisible() {
        return this.errorIconView.getVisibility() == 0;
    }

    boolean isPasswordVisibilityToggleEnabled() {
        return this.endIconMode == 1;
    }

    void onHintStateChanged(boolean z) {
        this.hintExpanded = z;
        this.updateSuffixTextVisibility();
    }

    void onTextInputBoxStateUpdated() {
        this.updateErrorIconVisibility();
        this.refreshErrorIconDrawableState();
        this.refreshEndIconDrawableState();
        if(this.getEndIconDelegate().shouldTintIconOnError()) {
            this.tintEndIconOnError(this.textInputLayout.shouldShowError());
        }
    }

    void refreshEndIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.endIconView, this.endIconTintList);
    }

    void refreshErrorIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.errorIconView, this.errorIconTintList);
    }

    void refreshIconState(boolean z) {
        int v1;
        EndIconDelegate endIconDelegate0 = this.getEndIconDelegate();
        int v = 1;
        if(endIconDelegate0.isIconCheckable()) {
            boolean z1 = this.endIconView.isChecked();
            if(z1 == endIconDelegate0.isIconChecked()) {
                v1 = 0;
            }
            else {
                this.endIconView.setChecked(!z1);
                v1 = 1;
            }
        }
        else {
            v1 = 0;
        }
        if(endIconDelegate0.isIconActivable()) {
            boolean z2 = this.endIconView.isActivated();
            if(z2 == endIconDelegate0.isIconActivated()) {
                v = v1;
            }
            else {
                this.setEndIconActivated(!z2);
            }
        }
        else {
            v = v1;
        }
        if(!z && v == 0) {
            return;
        }
        this.refreshEndIconDrawableState();
    }

    void removeOnEndIconChangedListener(OnEndIconChangedListener textInputLayout$OnEndIconChangedListener0) {
        this.endIconChangedListeners.remove(textInputLayout$OnEndIconChangedListener0);
    }

    private void removeTouchExplorationStateChangeListenerIfNeeded() {
        TouchExplorationStateChangeListener accessibilityManagerCompat$TouchExplorationStateChangeListener0 = this.touchExplorationStateChangeListener;
        if(accessibilityManagerCompat$TouchExplorationStateChangeListener0 != null) {
            AccessibilityManager accessibilityManager0 = this.accessibilityManager;
            if(accessibilityManager0 != null) {
                AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(accessibilityManager0, accessibilityManagerCompat$TouchExplorationStateChangeListener0);
            }
        }
    }

    void setEndIconActivated(boolean z) {
        this.endIconView.setActivated(z);
    }

    void setEndIconCheckable(boolean z) {
        this.endIconView.setCheckable(z);
    }

    void setEndIconContentDescription(int v) {
        this.setEndIconContentDescription((v == 0 ? null : this.getResources().getText(v)));
    }

    void setEndIconContentDescription(CharSequence charSequence0) {
        if(this.getEndIconContentDescription() != charSequence0) {
            this.endIconView.setContentDescription(charSequence0);
        }
    }

    void setEndIconDrawable(int v) {
        this.setEndIconDrawable((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    void setEndIconDrawable(Drawable drawable0) {
        this.endIconView.setImageDrawable(drawable0);
        if(drawable0 != null) {
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            this.refreshEndIconDrawableState();
        }
    }

    void setEndIconMinSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if(v != this.endIconMinSize) {
            this.endIconMinSize = v;
            IconHelper.setIconMinSize(this.endIconView, v);
            IconHelper.setIconMinSize(this.errorIconView, v);
        }
    }

    void setEndIconMode(int v) {
        if(this.endIconMode == v) {
            return;
        }
        this.tearDownDelegate(this.getEndIconDelegate());
        int v1 = this.endIconMode;
        this.endIconMode = v;
        this.dispatchOnEndIconChanged(v1);
        this.setEndIconVisible(v != 0);
        EndIconDelegate endIconDelegate0 = this.getEndIconDelegate();
        this.setEndIconDrawable(this.getIconResId(endIconDelegate0));
        this.setEndIconContentDescription(endIconDelegate0.getIconContentDescriptionResId());
        this.setEndIconCheckable(endIconDelegate0.isIconCheckable());
        if(!endIconDelegate0.isBoxBackgroundModeSupported(this.textInputLayout.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + this.textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + v);
        }
        this.setUpDelegate(endIconDelegate0);
        this.setEndIconOnClickListener(endIconDelegate0.getOnIconClickListener());
        EditText editText0 = this.editText;
        if(editText0 != null) {
            endIconDelegate0.onEditTextAttached(editText0);
            this.setOnFocusChangeListenersIfNeeded(endIconDelegate0);
        }
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
        this.refreshIconState(true);
    }

    void setEndIconOnClickListener(View.OnClickListener view$OnClickListener0) {
        IconHelper.setIconOnClickListener(this.endIconView, view$OnClickListener0, this.endIconOnLongClickListener);
    }

    void setEndIconOnLongClickListener(View.OnLongClickListener view$OnLongClickListener0) {
        this.endIconOnLongClickListener = view$OnLongClickListener0;
        IconHelper.setIconOnLongClickListener(this.endIconView, view$OnLongClickListener0);
    }

    void setEndIconScaleType(ImageView.ScaleType imageView$ScaleType0) {
        this.endIconScaleType = imageView$ScaleType0;
        IconHelper.setIconScaleType(this.endIconView, imageView$ScaleType0);
        IconHelper.setIconScaleType(this.errorIconView, imageView$ScaleType0);
    }

    void setEndIconTintList(ColorStateList colorStateList0) {
        if(this.endIconTintList != colorStateList0) {
            this.endIconTintList = colorStateList0;
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, colorStateList0, this.endIconTintMode);
        }
    }

    void setEndIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.endIconTintMode != porterDuff$Mode0) {
            this.endIconTintMode = porterDuff$Mode0;
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, porterDuff$Mode0);
        }
    }

    void setEndIconVisible(boolean z) {
        if(this.isEndIconVisible() != z) {
            this.endIconView.setVisibility((z ? 0 : 8));
            this.updateEndLayoutVisibility();
            this.updateSuffixTextViewPadding();
            this.textInputLayout.updateDummyDrawables();
        }
    }

    void setErrorIconDrawable(int v) {
        this.setErrorIconDrawable((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
        this.refreshErrorIconDrawableState();
    }

    void setErrorIconDrawable(Drawable drawable0) {
        this.errorIconView.setImageDrawable(drawable0);
        this.updateErrorIconVisibility();
        IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, this.errorIconTintMode);
    }

    void setErrorIconOnClickListener(View.OnClickListener view$OnClickListener0) {
        IconHelper.setIconOnClickListener(this.errorIconView, view$OnClickListener0, this.errorIconOnLongClickListener);
    }

    void setErrorIconOnLongClickListener(View.OnLongClickListener view$OnLongClickListener0) {
        this.errorIconOnLongClickListener = view$OnLongClickListener0;
        IconHelper.setIconOnLongClickListener(this.errorIconView, view$OnLongClickListener0);
    }

    void setErrorIconTintList(ColorStateList colorStateList0) {
        if(this.errorIconTintList != colorStateList0) {
            this.errorIconTintList = colorStateList0;
            IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, colorStateList0, this.errorIconTintMode);
        }
    }

    void setErrorIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.errorIconTintMode != porterDuff$Mode0) {
            this.errorIconTintMode = porterDuff$Mode0;
            IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, porterDuff$Mode0);
        }
    }

    private void setOnFocusChangeListenersIfNeeded(EndIconDelegate endIconDelegate0) {
        if(this.editText != null) {
            if(endIconDelegate0.getOnEditTextFocusChangeListener() != null) {
                this.editText.setOnFocusChangeListener(endIconDelegate0.getOnEditTextFocusChangeListener());
            }
            if(endIconDelegate0.getOnIconViewFocusChangeListener() != null) {
                this.endIconView.setOnFocusChangeListener(endIconDelegate0.getOnIconViewFocusChangeListener());
            }
        }
    }

    void setPasswordVisibilityToggleContentDescription(int v) {
        this.setPasswordVisibilityToggleContentDescription((v == 0 ? null : this.getResources().getText(v)));
    }

    void setPasswordVisibilityToggleContentDescription(CharSequence charSequence0) {
        this.endIconView.setContentDescription(charSequence0);
    }

    void setPasswordVisibilityToggleDrawable(int v) {
        this.setPasswordVisibilityToggleDrawable((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    void setPasswordVisibilityToggleDrawable(Drawable drawable0) {
        this.endIconView.setImageDrawable(drawable0);
    }

    void setPasswordVisibilityToggleEnabled(boolean z) {
        if(z && this.endIconMode != 1) {
            this.setEndIconMode(1);
            return;
        }
        if(!z) {
            this.setEndIconMode(0);
        }
    }

    void setPasswordVisibilityToggleTintList(ColorStateList colorStateList0) {
        this.endIconTintList = colorStateList0;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, colorStateList0, this.endIconTintMode);
    }

    void setPasswordVisibilityToggleTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.endIconTintMode = porterDuff$Mode0;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, porterDuff$Mode0);
    }

    void setSuffixText(CharSequence charSequence0) {
        this.suffixText = TextUtils.isEmpty(charSequence0) ? null : charSequence0;
        this.suffixTextView.setText(charSequence0);
        this.updateSuffixTextVisibility();
    }

    void setSuffixTextAppearance(int v) {
        TextViewCompat.setTextAppearance(this.suffixTextView, v);
    }

    void setSuffixTextColor(ColorStateList colorStateList0) {
        this.suffixTextView.setTextColor(colorStateList0);
    }

    private void setUpDelegate(EndIconDelegate endIconDelegate0) {
        endIconDelegate0.setUp();
        this.touchExplorationStateChangeListener = endIconDelegate0.getTouchExplorationStateChangeListener();
        this.addTouchExplorationStateChangeListenerIfNeeded();
    }

    private void tearDownDelegate(EndIconDelegate endIconDelegate0) {
        this.removeTouchExplorationStateChangeListenerIfNeeded();
        this.touchExplorationStateChangeListener = null;
        endIconDelegate0.tearDown();
    }

    private void tintEndIconOnError(boolean z) {
        if(z && this.getEndIconDrawable() != null) {
            Drawable drawable0 = DrawableCompat.wrap(this.getEndIconDrawable()).mutate();
            DrawableCompat.setTint(drawable0, this.textInputLayout.getErrorCurrentTextColors());
            this.endIconView.setImageDrawable(drawable0);
            return;
        }
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
    }

    void togglePasswordVisibilityToggle(boolean z) {
        if(this.endIconMode == 1) {
            this.endIconView.performClick();
            if(z) {
                this.endIconView.jumpDrawablesToCurrentState();
            }
        }
    }

    private void updateEndLayoutVisibility() {
        int v = 0;
        int v1 = this.endIconView.getVisibility() != 0 || this.isErrorIconVisible() ? 8 : 0;
        this.endIconFrame.setVisibility(v1);
        int v2 = this.suffixText == null || this.hintExpanded ? 8 : 0;
        if(!this.isEndIconVisible() && !this.isErrorIconVisible() && v2 != 0) {
            v = 8;
        }
        this.setVisibility(v);
    }

    private void updateErrorIconVisibility() {
        int v = 0;
        boolean z = this.getErrorIconDrawable() != null && this.textInputLayout.isErrorEnabled() && this.textInputLayout.shouldShowError();
        CheckableImageButton checkableImageButton0 = this.errorIconView;
        if(!z) {
            v = 8;
        }
        checkableImageButton0.setVisibility(v);
        this.updateEndLayoutVisibility();
        this.updateSuffixTextViewPadding();
        if(!this.hasEndIcon()) {
            this.textInputLayout.updateDummyDrawables();
        }
    }

    void updateSuffixTextViewPadding() {
        if(this.textInputLayout.editText == null) {
            return;
        }
        int v = this.isEndIconVisible() || this.isErrorIconVisible() ? 0 : ViewCompat.getPaddingEnd(this.textInputLayout.editText);
        int v1 = this.getContext().getResources().getDimensionPixelSize(dimen.material_input_text_to_prefix_suffix_padding);
        int v2 = this.textInputLayout.editText.getPaddingTop();
        int v3 = this.textInputLayout.editText.getPaddingBottom();
        ViewCompat.setPaddingRelative(this.suffixTextView, v1, v2, v, v3);
    }

    private void updateSuffixTextVisibility() {
        int v = this.suffixTextView.getVisibility();
        boolean z = false;
        int v1 = this.suffixText == null || this.hintExpanded ? 8 : 0;
        if(v != v1) {
            EndIconDelegate endIconDelegate0 = this.getEndIconDelegate();
            if(v1 == 0) {
                z = true;
            }
            endIconDelegate0.onSuffixVisibilityChanged(z);
        }
        this.updateEndLayoutVisibility();
        this.suffixTextView.setVisibility(v1);
        this.textInputLayout.updateDummyDrawables();
    }
}

