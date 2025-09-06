package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View.AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;

final class IndicatorViewController {
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_HELPER_TEXT = 2;
    private static final int CAPTION_STATE_NONE = 0;
    static final int COUNTER_INDEX = 2;
    private static final int DEFAULT_CAPTION_FADE_ANIMATION_DURATION = 0xA7;
    private static final int DEFAULT_CAPTION_TRANSLATION_Y_ANIMATION_DURATION = 0xD9;
    static final int ERROR_INDEX = 0;
    static final int HELPER_INDEX = 1;
    private Animator captionAnimator;
    private FrameLayout captionArea;
    private int captionDisplayed;
    private final int captionFadeInAnimationDuration;
    private final TimeInterpolator captionFadeInAnimationInterpolator;
    private final int captionFadeOutAnimationDuration;
    private final TimeInterpolator captionFadeOutAnimationInterpolator;
    private int captionToShow;
    private final int captionTranslationYAnimationDuration;
    private final TimeInterpolator captionTranslationYAnimationInterpolator;
    private final float captionTranslationYPx;
    private final Context context;
    private boolean errorEnabled;
    private CharSequence errorText;
    private int errorTextAppearance;
    private TextView errorView;
    private int errorViewAccessibilityLiveRegion;
    private CharSequence errorViewContentDescription;
    private ColorStateList errorViewTextColor;
    private CharSequence helperText;
    private boolean helperTextEnabled;
    private int helperTextTextAppearance;
    private TextView helperTextView;
    private ColorStateList helperTextViewTextColor;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    private final TextInputLayout textInputView;
    private Typeface typeface;

    public IndicatorViewController(TextInputLayout textInputLayout0) {
        Context context0 = textInputLayout0.getContext();
        this.context = context0;
        this.textInputView = textInputLayout0;
        this.captionTranslationYPx = (float)context0.getResources().getDimensionPixelSize(dimen.design_textinput_caption_translate_y);
        this.captionTranslationYAnimationDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationShort4, 0xD9);
        this.captionFadeInAnimationDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationMedium4, 0xA7);
        this.captionFadeOutAnimationDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationShort4, 0xA7);
        this.captionTranslationYAnimationInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedDecelerateInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.captionFadeInAnimationInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedDecelerateInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.captionFadeOutAnimationInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
    }

    void addIndicator(TextView textView0, int v) {
        if(this.indicatorArea == null && this.captionArea == null) {
            LinearLayout linearLayout0 = new LinearLayout(this.context);
            this.indicatorArea = linearLayout0;
            linearLayout0.setOrientation(0);
            this.textInputView.addView(this.indicatorArea, -1, -2);
            this.captionArea = new FrameLayout(this.context);
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            this.indicatorArea.addView(this.captionArea, linearLayout$LayoutParams0);
            if(this.textInputView.getEditText() != null) {
                this.adjustIndicatorPadding();
            }
        }
        if(this.isCaptionView(v)) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(textView0);
        }
        else {
            LinearLayout.LayoutParams linearLayout$LayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
            this.indicatorArea.addView(textView0, linearLayout$LayoutParams1);
        }
        this.indicatorArea.setVisibility(0);
        ++this.indicatorsAdded;
    }

    void adjustIndicatorPadding() {
        if(this.canAdjustIndicatorPadding()) {
            EditText editText0 = this.textInputView.getEditText();
            boolean z = MaterialResources.isFontScaleAtLeast1_3(this.context);
            LinearLayout linearLayout0 = this.indicatorArea;
            int v = ViewCompat.getPaddingStart(editText0);
            int v1 = this.getIndicatorPadding(z, dimen.material_helper_text_font_1_3_padding_horizontal, v);
            int v2 = this.context.getResources().getDimensionPixelSize(dimen.material_helper_text_default_padding_top);
            int v3 = this.getIndicatorPadding(z, dimen.material_helper_text_font_1_3_padding_top, v2);
            int v4 = ViewCompat.getPaddingEnd(editText0);
            ViewCompat.setPaddingRelative(linearLayout0, v1, v3, this.getIndicatorPadding(z, dimen.material_helper_text_font_1_3_padding_horizontal, v4), 0);
        }
    }

    private boolean canAdjustIndicatorPadding() {
        return this.indicatorArea != null && this.textInputView.getEditText() != null;
    }

    void cancelCaptionAnimator() {
        Animator animator0 = this.captionAnimator;
        if(animator0 != null) {
            animator0.cancel();
        }
    }

    private void createCaptionAnimators(List list0, boolean z, TextView textView0, int v, int v1, int v2) {
        if(textView0 == null || !z || v != v2 && v != v1) {
            return;
        }
        ObjectAnimator objectAnimator0 = this.createCaptionOpacityAnimator(textView0, v2 == v);
        if(v == v2 && v1 != 0) {
            objectAnimator0.setStartDelay(((long)this.captionFadeOutAnimationDuration));
        }
        list0.add(objectAnimator0);
        if(v2 == v && v1 != 0) {
            ObjectAnimator objectAnimator1 = this.createCaptionTranslationYAnimator(textView0);
            objectAnimator1.setStartDelay(((long)this.captionFadeOutAnimationDuration));
            list0.add(objectAnimator1);
        }
    }

    private ObjectAnimator createCaptionOpacityAnimator(TextView textView0, boolean z) {
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(textView0, View.ALPHA, new float[]{(z ? 1.0f : 0.0f)});
        objectAnimator0.setDuration(((long)(z ? this.captionFadeInAnimationDuration : this.captionFadeOutAnimationDuration)));
        objectAnimator0.setInterpolator((z ? this.captionFadeInAnimationInterpolator : this.captionFadeOutAnimationInterpolator));
        return objectAnimator0;
    }

    private ObjectAnimator createCaptionTranslationYAnimator(TextView textView0) {
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(textView0, View.TRANSLATION_Y, new float[]{-this.captionTranslationYPx, 0.0f});
        objectAnimator0.setDuration(((long)this.captionTranslationYAnimationDuration));
        objectAnimator0.setInterpolator(this.captionTranslationYAnimationInterpolator);
        return objectAnimator0;
    }

    boolean errorIsDisplayed() {
        return this.isCaptionStateError(this.captionDisplayed);
    }

    boolean errorShouldBeShown() {
        return this.isCaptionStateError(this.captionToShow);
    }

    private TextView getCaptionViewFromDisplayState(int v) {
        switch(v) {
            case 1: {
                return this.errorView;
            }
            case 2: {
                return this.helperTextView;
            }
            default: {
                return null;
            }
        }
    }

    int getErrorAccessibilityLiveRegion() {
        return this.errorViewAccessibilityLiveRegion;
    }

    CharSequence getErrorContentDescription() {
        return this.errorViewContentDescription;
    }

    CharSequence getErrorText() {
        return this.errorText;
    }

    int getErrorViewCurrentTextColor() {
        return this.errorView == null ? -1 : this.errorView.getCurrentTextColor();
    }

    ColorStateList getErrorViewTextColors() {
        return this.errorView == null ? null : this.errorView.getTextColors();
    }

    CharSequence getHelperText() {
        return this.helperText;
    }

    View getHelperTextView() {
        return this.helperTextView;
    }

    ColorStateList getHelperTextViewColors() {
        return this.helperTextView == null ? null : this.helperTextView.getTextColors();
    }

    int getHelperTextViewCurrentTextColor() {
        return this.helperTextView == null ? -1 : this.helperTextView.getCurrentTextColor();
    }

    // 去混淆评级： 低(20)
    private int getIndicatorPadding(boolean z, int v, int v1) {
        return z ? this.context.getResources().getDimensionPixelSize(v) : v1;
    }

    boolean helperTextIsDisplayed() {
        return this.isCaptionStateHelperText(this.captionDisplayed);
    }

    boolean helperTextShouldBeShown() {
        return this.isCaptionStateHelperText(this.captionToShow);
    }

    void hideError() {
        this.errorText = null;
        this.cancelCaptionAnimator();
        if(this.captionDisplayed == 1) {
            this.captionToShow = !this.helperTextEnabled || TextUtils.isEmpty(this.helperText) ? 0 : 2;
        }
        this.updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, this.shouldAnimateCaptionView(this.errorView, ""));
    }

    void hideHelperText() {
        this.cancelCaptionAnimator();
        int v = this.captionDisplayed;
        if(v == 2) {
            this.captionToShow = 0;
        }
        this.updateCaptionViewsVisibility(v, this.captionToShow, this.shouldAnimateCaptionView(this.helperTextView, ""));
    }

    private boolean isCaptionStateError(int v) {
        return v == 1 && this.errorView != null && !TextUtils.isEmpty(this.errorText);
    }

    private boolean isCaptionStateHelperText(int v) {
        return v == 2 && this.helperTextView != null && !TextUtils.isEmpty(this.helperText);
    }

    boolean isCaptionView(int v) {
        return v == 0 || v == 1;
    }

    boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    boolean isHelperTextEnabled() {
        return this.helperTextEnabled;
    }

    void removeIndicator(TextView textView0, int v) {
        if(this.indicatorArea == null) {
            return;
        }
        if(this.isCaptionView(v)) {
            FrameLayout frameLayout0 = this.captionArea;
            if(frameLayout0 == null) {
                this.indicatorArea.removeView(textView0);
            }
            else {
                frameLayout0.removeView(textView0);
            }
        }
        else {
            this.indicatorArea.removeView(textView0);
        }
        int v1 = this.indicatorsAdded - 1;
        this.indicatorsAdded = v1;
        this.setViewGroupGoneIfEmpty(this.indicatorArea, v1);
    }

    private void setCaptionViewVisibilities(int v, int v1) {
        if(v == v1) {
            return;
        }
        if(v1 != 0) {
            TextView textView0 = this.getCaptionViewFromDisplayState(v1);
            if(textView0 != null) {
                textView0.setVisibility(0);
                textView0.setAlpha(1.0f);
            }
        }
        if(v != 0) {
            TextView textView1 = this.getCaptionViewFromDisplayState(v);
            if(textView1 != null) {
                textView1.setVisibility(4);
                if(v == 1) {
                    textView1.setText(null);
                }
            }
        }
        this.captionDisplayed = v1;
    }

    void setErrorAccessibilityLiveRegion(int v) {
        this.errorViewAccessibilityLiveRegion = v;
        TextView textView0 = this.errorView;
        if(textView0 != null) {
            ViewCompat.setAccessibilityLiveRegion(textView0, v);
        }
    }

    void setErrorContentDescription(CharSequence charSequence0) {
        this.errorViewContentDescription = charSequence0;
        TextView textView0 = this.errorView;
        if(textView0 != null) {
            textView0.setContentDescription(charSequence0);
        }
    }

    void setErrorEnabled(boolean z) {
        if(this.errorEnabled == z) {
            return;
        }
        this.cancelCaptionAnimator();
        if(z) {
            AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.context);
            this.errorView = appCompatTextView0;
            appCompatTextView0.setId(id.textinput_error);
            this.errorView.setTextAlignment(5);
            Typeface typeface0 = this.typeface;
            if(typeface0 != null) {
                this.errorView.setTypeface(typeface0);
            }
            this.setErrorTextAppearance(this.errorTextAppearance);
            this.setErrorViewTextColor(this.errorViewTextColor);
            this.setErrorContentDescription(this.errorViewContentDescription);
            this.setErrorAccessibilityLiveRegion(this.errorViewAccessibilityLiveRegion);
            this.errorView.setVisibility(4);
            this.addIndicator(this.errorView, 0);
        }
        else {
            this.hideError();
            this.removeIndicator(this.errorView, 0);
            this.errorView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
        }
        this.errorEnabled = z;
    }

    void setErrorTextAppearance(int v) {
        this.errorTextAppearance = v;
        TextView textView0 = this.errorView;
        if(textView0 != null) {
            this.textInputView.setTextAppearanceCompatWithErrorFallback(textView0, v);
        }
    }

    void setErrorViewTextColor(ColorStateList colorStateList0) {
        this.errorViewTextColor = colorStateList0;
        TextView textView0 = this.errorView;
        if(textView0 != null && colorStateList0 != null) {
            textView0.setTextColor(colorStateList0);
        }
    }

    void setHelperTextAppearance(int v) {
        this.helperTextTextAppearance = v;
        TextView textView0 = this.helperTextView;
        if(textView0 != null) {
            TextViewCompat.setTextAppearance(textView0, v);
        }
    }

    void setHelperTextEnabled(boolean z) {
        if(this.helperTextEnabled == z) {
            return;
        }
        this.cancelCaptionAnimator();
        if(z) {
            AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.context);
            this.helperTextView = appCompatTextView0;
            appCompatTextView0.setId(id.textinput_helper_text);
            this.helperTextView.setTextAlignment(5);
            Typeface typeface0 = this.typeface;
            if(typeface0 != null) {
                this.helperTextView.setTypeface(typeface0);
            }
            this.helperTextView.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.helperTextView, 1);
            this.setHelperTextAppearance(this.helperTextTextAppearance);
            this.setHelperTextViewTextColor(this.helperTextViewTextColor);
            this.addIndicator(this.helperTextView, 1);
            this.helperTextView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                @Override  // android.view.View$AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfo accessibilityNodeInfo0) {
                    super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfo0);
                    EditText editText0 = IndicatorViewController.this.textInputView.getEditText();
                    if(editText0 != null) {
                        accessibilityNodeInfo0.setLabeledBy(editText0);
                    }
                }
            });
        }
        else {
            this.hideHelperText();
            this.removeIndicator(this.helperTextView, 1);
            this.helperTextView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
        }
        this.helperTextEnabled = z;
    }

    void setHelperTextViewTextColor(ColorStateList colorStateList0) {
        this.helperTextViewTextColor = colorStateList0;
        TextView textView0 = this.helperTextView;
        if(textView0 != null && colorStateList0 != null) {
            textView0.setTextColor(colorStateList0);
        }
    }

    private void setTextViewTypeface(TextView textView0, Typeface typeface0) {
        if(textView0 != null) {
            textView0.setTypeface(typeface0);
        }
    }

    void setTypefaces(Typeface typeface0) {
        if(typeface0 != this.typeface) {
            this.typeface = typeface0;
            this.setTextViewTypeface(this.errorView, typeface0);
            this.setTextViewTypeface(this.helperTextView, typeface0);
        }
    }

    private void setViewGroupGoneIfEmpty(ViewGroup viewGroup0, int v) {
        if(v == 0) {
            viewGroup0.setVisibility(8);
        }
    }

    // 去混淆评级： 低(40)
    private boolean shouldAnimateCaptionView(TextView textView0, CharSequence charSequence0) {
        return ViewCompat.isLaidOut(this.textInputView) && this.textInputView.isEnabled() && (this.captionToShow != this.captionDisplayed || textView0 == null || !TextUtils.equals(textView0.getText(), charSequence0));
    }

    void showError(CharSequence charSequence0) {
        this.cancelCaptionAnimator();
        this.errorText = charSequence0;
        this.errorView.setText(charSequence0);
        int v = this.captionDisplayed;
        if(v != 1) {
            this.captionToShow = 1;
        }
        this.updateCaptionViewsVisibility(v, this.captionToShow, this.shouldAnimateCaptionView(this.errorView, charSequence0));
    }

    void showHelper(CharSequence charSequence0) {
        this.cancelCaptionAnimator();
        this.helperText = charSequence0;
        this.helperTextView.setText(charSequence0);
        int v = this.captionDisplayed;
        if(v != 2) {
            this.captionToShow = 2;
        }
        this.updateCaptionViewsVisibility(v, this.captionToShow, this.shouldAnimateCaptionView(this.helperTextView, charSequence0));
    }

    private void updateCaptionViewsVisibility(int v, int v1, boolean z) {
        if(v == v1) {
            return;
        }
        if(z) {
            AnimatorSet animatorSet0 = new AnimatorSet();
            this.captionAnimator = animatorSet0;
            ArrayList arrayList0 = new ArrayList();
            this.createCaptionAnimators(arrayList0, this.helperTextEnabled, this.helperTextView, 2, v, v1);
            this.createCaptionAnimators(arrayList0, this.errorEnabled, this.errorView, 1, v, v1);
            AnimatorSetCompat.playTogether(animatorSet0, arrayList0);
            animatorSet0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    IndicatorViewController.this.captionDisplayed = v1;
                    IndicatorViewController.this.captionAnimator = null;
                    TextView textView0 = this.getCaptionViewFromDisplayState(v);
                    if(textView0 != null) {
                        textView0.setVisibility(4);
                        if(v == 1 && IndicatorViewController.this.errorView != null) {
                            IndicatorViewController.this.errorView.setText(null);
                        }
                    }
                    TextView textView1 = this.getCaptionViewFromDisplayState(v1);
                    if(textView1 != null) {
                        textView1.setTranslationY(0.0f);
                        this.getCaptionViewFromDisplayState(v1).setAlpha(1.0f);
                    }
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                    TextView textView0 = this.getCaptionViewFromDisplayState(v1);
                    if(textView0 != null) {
                        textView0.setVisibility(0);
                        this.getCaptionViewFromDisplayState(v1).setAlpha(0.0f);
                    }
                }
            });
            animatorSet0.start();
        }
        else {
            this.setCaptionViewVisibilities(v, v1);
        }
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateLabelState(z);
        this.textInputView.updateTextInputBoxState();
    }
}

