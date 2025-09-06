package com.google.android.material.datepicker;

import android.view.View;
import android.widget.EditText;
import com.google.android.material.internal.ViewUtils;

public final class DateSelector.-CC {
    // 检测为 Lambda 实现
    public static void lambda$showKeyboardWithAutoHideBehavior$0(EditText[] arr_editText, View view0, boolean z) [...]

    // 检测为 Lambda 实现
    public static void lambda$showKeyboardWithAutoHideBehavior$1(View view0) [...]

    public static void showKeyboardWithAutoHideBehavior(EditText[] arr_editText) {
        if(arr_editText.length == 0) {
            return;
        }
        DateSelector..ExternalSyntheticLambda0 dateSelector$$ExternalSyntheticLambda00 = (View view0, boolean z) -> {
            for(int v = 0; v < arr_editText.length; ++v) {
                if(arr_editText[v].hasFocus()) {
                    return;
                }
            }
            ViewUtils.hideKeyboard(view0, false);
        };
        for(int v = 0; v < arr_editText.length; ++v) {
            arr_editText[v].setOnFocusChangeListener(dateSelector$$ExternalSyntheticLambda00);
        }
        EditText editText0 = arr_editText[0];
        editText0.postDelayed(() -> ViewUtils.requestFocusAndShowKeyboard(editText0, false), 100L);
    }
}

