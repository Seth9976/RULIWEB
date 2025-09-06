package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

class StartCompoundLayout extends LinearLayout {
    private boolean hintExpanded;
    private CharSequence prefixText;
    private final TextView prefixTextView;
    private int startIconMinSize;
    private View.OnLongClickListener startIconOnLongClickListener;
    private ImageView.ScaleType startIconScaleType;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final TextInputLayout textInputLayout;

    StartCompoundLayout(TextInputLayout textInputLayout0, TintTypedArray tintTypedArray0) {
        super(textInputLayout0.getContext());
        this.textInputLayout = textInputLayout0;
        this.setVisibility(8);
        this.setOrientation(0);
        this.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 0x800003));
        CheckableImageButton checkableImageButton0 = (CheckableImageButton)LayoutInflater.from(this.getContext()).inflate(layout.design_text_input_start_icon, this, false);
        this.startIconView = checkableImageButton0;
        IconHelper.setCompatRippleBackgroundIfNeeded(checkableImageButton0);
        AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.getContext());
        this.prefixTextView = appCompatTextView0;
        this.initStartIconView(tintTypedArray0);
        this.initPrefixTextView(tintTypedArray0);
        this.addView(checkableImageButton0);
        this.addView(appCompatTextView0);
    }

    CharSequence getPrefixText() {
        return this.prefixText;
    }

    ColorStateList getPrefixTextColor() {
        return this.prefixTextView.getTextColors();
    }

    int getPrefixTextStartOffset() {
        if(this.isStartIconVisible()) {
            int v = this.startIconView.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginEnd(((ViewGroup.MarginLayoutParams)this.startIconView.getLayoutParams()));
            return ViewCompat.getPaddingStart(this) + ViewCompat.getPaddingStart(this.prefixTextView) + v;
        }
        return ViewCompat.getPaddingStart(this) + ViewCompat.getPaddingStart(this.prefixTextView);
    }

    TextView getPrefixTextView() {
        return this.prefixTextView;
    }

    CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    int getStartIconMinSize() {
        return this.startIconMinSize;
    }

    ImageView.ScaleType getStartIconScaleType() {
        return this.startIconScaleType;
    }

    private void initPrefixTextView(TintTypedArray tintTypedArray0) {
        this.prefixTextView.setVisibility(8);
        this.prefixTextView.setId(id.textinput_prefix_text);
        LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(-2, -2);
        this.prefixTextView.setLayoutParams(linearLayout$LayoutParams0);
        ViewCompat.setAccessibilityLiveRegion(this.prefixTextView, 1);
        this.setPrefixTextAppearance(tintTypedArray0.getResourceId(styleable.TextInputLayout_prefixTextAppearance, 0));
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_prefixTextColor)) {
            this.setPrefixTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_prefixTextColor));
        }
        this.setPrefixText(tintTypedArray0.getText(styleable.TextInputLayout_prefixText));
    }

    private void initStartIconView(TintTypedArray tintTypedArray0) {
        if(MaterialResources.isFontScaleAtLeast1_3(this.getContext())) {
            MarginLayoutParamsCompat.setMarginEnd(((ViewGroup.MarginLayoutParams)this.startIconView.getLayoutParams()), 0);
        }
        this.setStartIconOnClickListener(null);
        this.setStartIconOnLongClickListener(null);
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_startIconTint)) {
            this.startIconTintList = MaterialResources.getColorStateList(this.getContext(), tintTypedArray0, styleable.TextInputLayout_startIconTint);
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_startIconTintMode)) {
            this.startIconTintMode = ViewUtils.parseTintMode(tintTypedArray0.getInt(styleable.TextInputLayout_startIconTintMode, -1), null);
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_startIconDrawable)) {
            this.setStartIconDrawable(tintTypedArray0.getDrawable(styleable.TextInputLayout_startIconDrawable));
            if(tintTypedArray0.hasValue(styleable.TextInputLayout_startIconContentDescription)) {
                this.setStartIconContentDescription(tintTypedArray0.getText(styleable.TextInputLayout_startIconContentDescription));
            }
            this.setStartIconCheckable(tintTypedArray0.getBoolean(styleable.TextInputLayout_startIconCheckable, true));
        }
        int v = this.getResources().getDimensionPixelSize(dimen.mtrl_min_touch_target_size);
        this.setStartIconMinSize(tintTypedArray0.getDimensionPixelSize(styleable.TextInputLayout_startIconMinSize, v));
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_startIconScaleType)) {
            this.setStartIconScaleType(IconHelper.convertScaleType(tintTypedArray0.getInt(styleable.TextInputLayout_startIconScaleType, -1)));
        }
    }

    boolean isStartIconCheckable() {
        return this.startIconView.isCheckable();
    }

    boolean isStartIconVisible() {
        return this.startIconView.getVisibility() == 0;
    }

    void onHintStateChanged(boolean z) {
        this.hintExpanded = z;
        this.updateVisibility();
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        this.updatePrefixTextViewPadding();
    }

    void refreshStartIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.startIconView, this.startIconTintList);
    }

    void setPrefixText(CharSequence charSequence0) {
        this.prefixText = TextUtils.isEmpty(charSequence0) ? null : charSequence0;
        this.prefixTextView.setText(charSequence0);
        this.updateVisibility();
    }

    void setPrefixTextAppearance(int v) {
        TextViewCompat.setTextAppearance(this.prefixTextView, v);
    }

    void setPrefixTextColor(ColorStateList colorStateList0) {
        this.prefixTextView.setTextColor(colorStateList0);
    }

    void setStartIconCheckable(boolean z) {
        this.startIconView.setCheckable(z);
    }

    void setStartIconContentDescription(CharSequence charSequence0) {
        if(this.getStartIconContentDescription() != charSequence0) {
            this.startIconView.setContentDescription(charSequence0);
        }
    }

    void setStartIconDrawable(Drawable drawable0) {
        this.startIconView.setImageDrawable(drawable0);
        if(drawable0 != null) {
            IconHelper.applyIconTint(this.textInputLayout, this.startIconView, this.startIconTintList, this.startIconTintMode);
            this.setStartIconVisible(true);
            this.refreshStartIconDrawableState();
            return;
        }
        this.setStartIconVisible(false);
        this.setStartIconOnClickListener(null);
        this.setStartIconOnLongClickListener(null);
        this.setStartIconContentDescription(null);
    }

    void setStartIconMinSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if(v != this.startIconMinSize) {
            this.startIconMinSize = v;
            IconHelper.setIconMinSize(this.startIconView, v);
        }
    }

    void setStartIconOnClickListener(View.OnClickListener view$OnClickListener0) {
        IconHelper.setIconOnClickListener(this.startIconView, view$OnClickListener0, this.startIconOnLongClickListener);
    }

    void setStartIconOnLongClickListener(View.OnLongClickListener view$OnLongClickListener0) {
        this.startIconOnLongClickListener = view$OnLongClickListener0;
        IconHelper.setIconOnLongClickListener(this.startIconView, view$OnLongClickListener0);
    }

    void setStartIconScaleType(ImageView.ScaleType imageView$ScaleType0) {
        this.startIconScaleType = imageView$ScaleType0;
        IconHelper.setIconScaleType(this.startIconView, imageView$ScaleType0);
    }

    void setStartIconTintList(ColorStateList colorStateList0) {
        if(this.startIconTintList != colorStateList0) {
            this.startIconTintList = colorStateList0;
            IconHelper.applyIconTint(this.textInputLayout, this.startIconView, colorStateList0, this.startIconTintMode);
        }
    }

    void setStartIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.startIconTintMode != porterDuff$Mode0) {
            this.startIconTintMode = porterDuff$Mode0;
            IconHelper.applyIconTint(this.textInputLayout, this.startIconView, this.startIconTintList, porterDuff$Mode0);
        }
    }

    void setStartIconVisible(boolean z) {
        if(this.isStartIconVisible() != z) {
            this.startIconView.setVisibility((z ? 0 : 8));
            this.updatePrefixTextViewPadding();
            this.updateVisibility();
        }
    }

    void setupAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        if(this.prefixTextView.getVisibility() == 0) {
            accessibilityNodeInfoCompat0.setLabelFor(this.prefixTextView);
            accessibilityNodeInfoCompat0.setTraversalAfter(this.prefixTextView);
            return;
        }
        accessibilityNodeInfoCompat0.setTraversalAfter(this.startIconView);
    }

    void updatePrefixTextViewPadding() {
        EditText editText0 = this.textInputLayout.editText;
        if(editText0 == null) {
            return;
        }
        int v = this.isStartIconVisible() ? 0 : ViewCompat.getPaddingStart(editText0);
        int v1 = editText0.getCompoundPaddingTop();
        int v2 = this.getContext().getResources().getDimensionPixelSize(dimen.material_input_text_to_prefix_suffix_padding);
        int v3 = editText0.getCompoundPaddingBottom();
        ViewCompat.setPaddingRelative(this.prefixTextView, v, v1, v2, v3);
    }

    private void updateVisibility() {
        int v = 0;
        int v1 = this.prefixText == null || this.hintExpanded ? 8 : 0;
        if(this.startIconView.getVisibility() != 0 && v1 != 0) {
            v = 8;
        }
        this.setVisibility(v);
        this.prefixTextView.setVisibility(v1);
        this.textInputLayout.updateDummyDrawables();
    }
}

