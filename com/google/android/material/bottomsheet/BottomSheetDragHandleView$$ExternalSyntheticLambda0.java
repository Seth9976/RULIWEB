package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import androidx.core.view.accessibility.AccessibilityViewCommand;

public final class BottomSheetDragHandleView..ExternalSyntheticLambda0 implements AccessibilityViewCommand {
    public final BottomSheetDragHandleView f$0;

    public BottomSheetDragHandleView..ExternalSyntheticLambda0(BottomSheetDragHandleView bottomSheetDragHandleView0) {
        this.f$0 = bottomSheetDragHandleView0;
    }

    @Override  // androidx.core.view.accessibility.AccessibilityViewCommand
    public final boolean perform(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) {
        return this.f$0.lambda$onBottomSheetStateChanged$0$com-google-android-material-bottomsheet-BottomSheetDragHandleView(view0, accessibilityViewCommand$CommandArguments0);
    }
}

