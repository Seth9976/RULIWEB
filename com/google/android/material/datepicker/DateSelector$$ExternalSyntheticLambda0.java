package com.google.android.material.datepicker;

import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.widget.EditText;

public final class DateSelector..ExternalSyntheticLambda0 implements View.OnFocusChangeListener {
    public final EditText[] f$0;

    public DateSelector..ExternalSyntheticLambda0(EditText[] arr_editText) {
        this.f$0 = arr_editText;
    }

    @Override  // android.view.View$OnFocusChangeListener
    public final void onFocusChange(View view0, boolean z) {
        DateSelector.-CC.lambda$showKeyboardWithAutoHideBehavior$0(this.f$0, view0, z);
    }
}

