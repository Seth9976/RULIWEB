package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R.attr;
import com.google.android.material.R.drawable;
import com.google.android.material.R.string;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

class ClearTextEndIconDelegate extends EndIconDelegate {
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.8f;
    private static final int DEFAULT_ANIMATION_FADE_DURATION = 100;
    private static final int DEFAULT_ANIMATION_SCALE_DURATION = 150;
    private final int animationFadeDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationScaleDuration;
    private final TimeInterpolator animationScaleInterpolator;
    private EditText editText;
    private AnimatorSet iconInAnim;
    private ValueAnimator iconOutAnim;
    private final View.OnFocusChangeListener onFocusChangeListener;
    private final View.OnClickListener onIconClickListener;

    ClearTextEndIconDelegate(EndCompoundLayout endCompoundLayout0) {
        super(endCompoundLayout0);
        this.onIconClickListener = (View view0) -> {
            EditText editText0 = this.editText;
            if(editText0 == null) {
                return;
            }
            Editable editable0 = editText0.getText();
            if(editable0 != null) {
                editable0.clear();
            }
            this.refreshIconState();
        };
        this.onFocusChangeListener = (View view0, boolean z) -> this.animateIcon(this.shouldBeVisible());
        this.animationFadeDuration = MotionUtils.resolveThemeDuration(endCompoundLayout0.getContext(), attr.motionDurationShort3, 100);
        this.animationScaleDuration = MotionUtils.resolveThemeDuration(endCompoundLayout0.getContext(), attr.motionDurationShort3, 150);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(endCompoundLayout0.getContext(), attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.animationScaleInterpolator = MotionUtils.resolveThemeInterpolator(endCompoundLayout0.getContext(), attr.motionEasingEmphasizedInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void afterEditTextChanged(Editable editable0) {
        if(this.endLayout.getSuffixText() != null) {
            return;
        }
        this.animateIcon(this.shouldBeVisible());
    }

    private void animateIcon(boolean z) {
        boolean z1 = this.endLayout.isEndIconVisible() == z;
        if(z && !this.iconInAnim.isRunning()) {
            this.iconOutAnim.cancel();
            this.iconInAnim.start();
            if(z1) {
                this.iconInAnim.end();
            }
        }
        else if(!z) {
            this.iconInAnim.cancel();
            this.iconOutAnim.start();
            if(z1) {
                this.iconOutAnim.end();
            }
        }
    }

    private ValueAnimator getAlphaAnimator(float[] arr_f) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(arr_f);
        valueAnimator0.setInterpolator(this.animationFadeInterpolator);
        valueAnimator0.setDuration(((long)this.animationFadeDuration));
        valueAnimator0.addUpdateListener((ValueAnimator valueAnimator0) -> {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            this.endIconView.setAlpha(f);
        });
        return valueAnimator0;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    int getIconContentDescriptionResId() {
        return string.clear_text_end_icon_content_description;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    int getIconDrawableResId() {
        return drawable.mtrl_ic_cancel;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    private ValueAnimator getScaleAnimator() {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.8f, 1.0f});
        valueAnimator0.setInterpolator(this.animationScaleInterpolator);
        valueAnimator0.setDuration(((long)this.animationScaleDuration));
        valueAnimator0.addUpdateListener((ValueAnimator valueAnimator0) -> {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            this.endIconView.setScaleX(f);
            this.endIconView.setScaleY(f);
        });
        return valueAnimator0;
    }

    private void initAnimators() {
        ValueAnimator valueAnimator0 = this.getScaleAnimator();
        ValueAnimator valueAnimator1 = this.getAlphaAnimator(new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet0 = new AnimatorSet();
        this.iconInAnim = animatorSet0;
        animatorSet0.playTogether(new Animator[]{valueAnimator0, valueAnimator1});
        this.iconInAnim.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                ClearTextEndIconDelegate.this.endLayout.setEndIconVisible(true);
            }
        });
        ValueAnimator valueAnimator2 = this.getAlphaAnimator(new float[]{1.0f, 0.0f});
        this.iconOutAnim = valueAnimator2;
        valueAnimator2.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                ClearTextEndIconDelegate.this.endLayout.setEndIconVisible(false);
            }
        });
    }

    // 检测为 Lambda 实现
    void lambda$getAlphaAnimator$3$com-google-android-material-textfield-ClearTextEndIconDelegate(ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$getScaleAnimator$4$com-google-android-material-textfield-ClearTextEndIconDelegate(ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-textfield-ClearTextEndIconDelegate(View view0) [...]

    // 检测为 Lambda 实现
    void lambda$new$1$com-google-android-material-textfield-ClearTextEndIconDelegate(View view0, boolean z) [...]

    // 检测为 Lambda 实现
    void lambda$tearDown$2$com-google-android-material-textfield-ClearTextEndIconDelegate() [...]

    @Override  // com.google.android.material.textfield.EndIconDelegate
    public void onEditTextAttached(EditText editText0) {
        this.editText = editText0;
        this.textInputLayout.setEndIconVisible(this.shouldBeVisible());
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void onSuffixVisibilityChanged(boolean z) {
        if(this.endLayout.getSuffixText() == null) {
            return;
        }
        this.animateIcon(z);
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void setUp() {
        this.initAnimators();
    }

    // 去混淆评级： 低(20)
    private boolean shouldBeVisible() {
        return this.editText != null && (this.editText.hasFocus() || this.endIconView.hasFocus()) && this.editText.getText().length() > 0;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void tearDown() {
        EditText editText0 = this.editText;
        if(editText0 != null) {
            editText0.post(() -> this.animateIcon(true));
        }
    }
}

