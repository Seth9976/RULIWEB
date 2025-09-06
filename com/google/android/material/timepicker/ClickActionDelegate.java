package com.google.android.material.timepicker;

import android.content.Context;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

class ClickActionDelegate extends AccessibilityDelegateCompat {
    private final AccessibilityActionCompat clickAction;

    public ClickActionDelegate(Context context0, int v) {
        this.clickAction = new AccessibilityActionCompat(16, context0.getString(v));
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
        accessibilityNodeInfoCompat0.addAction(this.clickAction);
    }
}

