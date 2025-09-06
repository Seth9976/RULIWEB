package com.google.android.material.textfield;

import android.content.Context;
import android.text.Editable;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;

abstract class EndIconDelegate {
    final Context context;
    final CheckableImageButton endIconView;
    final EndCompoundLayout endLayout;
    final TextInputLayout textInputLayout;

    EndIconDelegate(EndCompoundLayout endCompoundLayout0) {
        this.textInputLayout = endCompoundLayout0.textInputLayout;
        this.endLayout = endCompoundLayout0;
        this.context = endCompoundLayout0.getContext();
        this.endIconView = endCompoundLayout0.getEndIconView();
    }

    void afterEditTextChanged(Editable editable0) {
    }

    void beforeEditTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
    }

    int getIconContentDescriptionResId() {
        return 0;
    }

    int getIconDrawableResId() {
        return 0;
    }

    View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return null;
    }

    View.OnClickListener getOnIconClickListener() {
        return null;
    }

    View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return null;
    }

    TouchExplorationStateChangeListener getTouchExplorationStateChangeListener() {
        return null;
    }

    boolean isBoxBackgroundModeSupported(int v) {
        return true;
    }

    boolean isIconActivable() {
        return false;
    }

    boolean isIconActivated() {
        return false;
    }

    boolean isIconCheckable() {
        return false;
    }

    boolean isIconChecked() {
        return false;
    }

    void onEditTextAttached(EditText editText0) {
    }

    void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
    }

    void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
    }

    void onSuffixVisibilityChanged(boolean z) {
    }

    final void refreshIconState() {
        this.endLayout.refreshIconState(false);
    }

    void setUp() {
    }

    boolean shouldTintIconOnError() {
        return false;
    }

    void tearDown() {
    }
}

