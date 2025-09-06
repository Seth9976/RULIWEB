package com.google.android.material.sidesheet;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import androidx.core.view.accessibility.AccessibilityViewCommand;

public final class SideSheetBehavior..ExternalSyntheticLambda1 implements AccessibilityViewCommand {
    public final SideSheetBehavior f$0;
    public final int f$1;

    public SideSheetBehavior..ExternalSyntheticLambda1(SideSheetBehavior sideSheetBehavior0, int v) {
        this.f$0 = sideSheetBehavior0;
        this.f$1 = v;
    }

    @Override  // androidx.core.view.accessibility.AccessibilityViewCommand
    public final boolean perform(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) {
        return this.f$0.lambda$createAccessibilityViewCommandForState$2$com-google-android-material-sidesheet-SideSheetBehavior(this.f$1, view0, accessibilityViewCommand$CommandArguments0);
    }
}

