package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.drawable;
import com.google.android.material.R.string;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

class DropdownMenuEndIconDelegate extends EndIconDelegate {
    private static final int DEFAULT_ANIMATION_FADE_IN_DURATION = 67;
    private static final int DEFAULT_ANIMATION_FADE_OUT_DURATION = 50;
    private static final boolean IS_LOLLIPOP;
    private AccessibilityManager accessibilityManager;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private AutoCompleteTextView autoCompleteTextView;
    private long dropdownPopupActivatedAt;
    private boolean dropdownPopupDirty;
    private boolean editTextHasFocus;
    private ValueAnimator fadeInAnim;
    private ValueAnimator fadeOutAnim;
    private boolean isEndIconChecked;
    private final View.OnFocusChangeListener onEditTextFocusChangeListener;
    private final View.OnClickListener onIconClickListener;
    private final TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    static {
        DropdownMenuEndIconDelegate.IS_LOLLIPOP = true;
    }

    DropdownMenuEndIconDelegate(EndCompoundLayout endCompoundLayout0) {
        super(endCompoundLayout0);
        this.onIconClickListener = (View view0) -> this.showHideDropdown();
        this.onEditTextFocusChangeListener = (View view0, boolean z) -> {
            this.editTextHasFocus = z;
            this.refreshIconState();
            if(!z) {
                this.setEndIconChecked(false);
                this.dropdownPopupDirty = false;
            }
        };
        this.touchExplorationStateChangeListener = (boolean z) -> if(this.autoCompleteTextView != null && !EditTextUtils.isEditable(this.autoCompleteTextView)) {
            ViewCompat.setImportantForAccessibility(this.endIconView, (z ? 2 : 1));
        };
        this.dropdownPopupActivatedAt = 0x7FFFFFFFFFFFFFFFL;
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(endCompoundLayout0.getContext(), attr.motionDurationShort3, 67);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(endCompoundLayout0.getContext(), attr.motionDurationShort3, 50);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(endCompoundLayout0.getContext(), attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    public void afterEditTextChanged(Editable editable0) {
        if(this.accessibilityManager.isTouchExplorationEnabled() && EditTextUtils.isEditable(this.autoCompleteTextView) && !this.endIconView.hasFocus()) {
            this.autoCompleteTextView.dismissDropDown();
        }
        this.autoCompleteTextView.post(() -> {
            boolean z = this.autoCompleteTextView.isPopupShowing();
            this.setEndIconChecked(z);
            this.dropdownPopupDirty = z;
        });
    }

    private static AutoCompleteTextView castAutoCompleteTextViewOrThrow(EditText editText0) {
        if(!(editText0 instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        return (AutoCompleteTextView)editText0;
    }

    private ValueAnimator getAlphaAnimator(int v, float[] arr_f) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(arr_f);
        valueAnimator0.setInterpolator(this.animationFadeInterpolator);
        valueAnimator0.setDuration(((long)v));
        valueAnimator0.addUpdateListener((ValueAnimator valueAnimator0) -> {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            this.endIconView.setAlpha(f);
        });
        return valueAnimator0;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    int getIconContentDescriptionResId() {
        return string.exposed_dropdown_menu_content_description;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.textfield.EndIconDelegate
    int getIconDrawableResId() {
        return DropdownMenuEndIconDelegate.IS_LOLLIPOP ? drawable.mtrl_dropdown_arrow : drawable.mtrl_ic_arrow_drop_down;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onEditTextFocusChangeListener;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    public TouchExplorationStateChangeListener getTouchExplorationStateChangeListener() {
        return this.touchExplorationStateChangeListener;
    }

    private void initAnimators() {
        this.fadeInAnim = this.getAlphaAnimator(this.animationFadeInDuration, new float[]{0.0f, 1.0f});
        ValueAnimator valueAnimator0 = this.getAlphaAnimator(this.animationFadeOutDuration, new float[]{1.0f, 0.0f});
        this.fadeOutAnim = valueAnimator0;
        valueAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                DropdownMenuEndIconDelegate.this.refreshIconState();
                DropdownMenuEndIconDelegate.this.fadeInAnim.start();
            }
        });
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isBoxBackgroundModeSupported(int v) {
        return v != 0;
    }

    private boolean isDropdownPopupActive() {
        long v = System.currentTimeMillis() - this.dropdownPopupActivatedAt;
        return v < 0L || v > 300L;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isIconActivable() {
        return true;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isIconActivated() {
        return this.editTextHasFocus;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isIconCheckable() {
        return true;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isIconChecked() {
        return this.isEndIconChecked;
    }

    // 检测为 Lambda 实现
    void lambda$afterEditTextChanged$3$com-google-android-material-textfield-DropdownMenuEndIconDelegate() [...]

    // 检测为 Lambda 实现
    void lambda$getAlphaAnimator$6$com-google-android-material-textfield-DropdownMenuEndIconDelegate(ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-textfield-DropdownMenuEndIconDelegate(View view0) [...]

    // 检测为 Lambda 实现
    void lambda$new$1$com-google-android-material-textfield-DropdownMenuEndIconDelegate(View view0, boolean z) [...]

    // 检测为 Lambda 实现
    void lambda$new$2$com-google-android-material-textfield-DropdownMenuEndIconDelegate(boolean z) [...]

    // 检测为 Lambda 实现
    boolean lambda$setUpDropdownShowHideBehavior$4$com-google-android-material-textfield-DropdownMenuEndIconDelegate(View view0, MotionEvent motionEvent0) [...]

    // 检测为 Lambda 实现
    void lambda$setUpDropdownShowHideBehavior$5$com-google-android-material-textfield-DropdownMenuEndIconDelegate() [...]

    @Override  // com.google.android.material.textfield.EndIconDelegate
    public void onEditTextAttached(EditText editText0) {
        this.autoCompleteTextView = DropdownMenuEndIconDelegate.castAutoCompleteTextViewOrThrow(editText0);
        this.setUpDropdownShowHideBehavior();
        this.textInputLayout.setErrorIconDrawable(null);
        if(!EditTextUtils.isEditable(editText0) && this.accessibilityManager.isTouchExplorationEnabled()) {
            ViewCompat.setImportantForAccessibility(this.endIconView, 2);
        }
        this.textInputLayout.setEndIconVisible(true);
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        if(!EditTextUtils.isEditable(this.autoCompleteTextView)) {
            accessibilityNodeInfoCompat0.setClassName("android.widget.Spinner");
        }
        if(accessibilityNodeInfoCompat0.isShowingHintText()) {
            accessibilityNodeInfoCompat0.setHintText(null);
        }
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        boolean z;
        if(this.accessibilityManager.isEnabled() && !EditTextUtils.isEditable(this.autoCompleteTextView)) {
            switch(accessibilityEvent0.getEventType()) {
                case 8: 
                case 0x8000: {
                    z = this.isEndIconChecked && !this.autoCompleteTextView.isPopupShowing();
                    break;
                }
                default: {
                    z = false;
                }
            }
            if(accessibilityEvent0.getEventType() == 1 || z) {
                this.showHideDropdown();
                this.updateDropdownPopupDirty();
            }
        }
    }

    private void setEndIconChecked(boolean z) {
        if(this.isEndIconChecked != z) {
            this.isEndIconChecked = z;
            this.fadeInAnim.cancel();
            this.fadeOutAnim.start();
        }
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void setUp() {
        this.initAnimators();
        this.accessibilityManager = (AccessibilityManager)this.context.getSystemService("accessibility");
    }

    private void setUpDropdownShowHideBehavior() {
        this.autoCompleteTextView.setOnTouchListener((View view0, MotionEvent motionEvent0) -> {
            if(motionEvent0.getAction() == 1) {
                if(this.isDropdownPopupActive()) {
                    this.dropdownPopupDirty = false;
                }
                this.showHideDropdown();
                this.updateDropdownPopupDirty();
            }
            return false;
        });
        if(DropdownMenuEndIconDelegate.IS_LOLLIPOP) {
            this.autoCompleteTextView.setOnDismissListener(() -> {
                this.updateDropdownPopupDirty();
                this.setEndIconChecked(false);
            });
        }
        this.autoCompleteTextView.setThreshold(0);
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean shouldTintIconOnError() {
        return true;
    }

    private void showHideDropdown() {
        if(this.autoCompleteTextView == null) {
            return;
        }
        if(this.isDropdownPopupActive()) {
            this.dropdownPopupDirty = false;
        }
        if(!this.dropdownPopupDirty) {
            if(DropdownMenuEndIconDelegate.IS_LOLLIPOP) {
                this.setEndIconChecked(!this.isEndIconChecked);
            }
            else {
                this.isEndIconChecked = !this.isEndIconChecked;
                this.refreshIconState();
            }
            if(this.isEndIconChecked) {
                this.autoCompleteTextView.requestFocus();
                this.autoCompleteTextView.showDropDown();
                return;
            }
            this.autoCompleteTextView.dismissDropDown();
            return;
        }
        this.dropdownPopupDirty = false;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void tearDown() {
        AutoCompleteTextView autoCompleteTextView0 = this.autoCompleteTextView;
        if(autoCompleteTextView0 != null) {
            autoCompleteTextView0.setOnTouchListener(null);
            if(DropdownMenuEndIconDelegate.IS_LOLLIPOP) {
                this.autoCompleteTextView.setOnDismissListener(null);
            }
        }
    }

    private void updateDropdownPopupDirty() {
        this.dropdownPopupDirty = true;
        this.dropdownPopupActivatedAt = System.currentTimeMillis();
    }
}

