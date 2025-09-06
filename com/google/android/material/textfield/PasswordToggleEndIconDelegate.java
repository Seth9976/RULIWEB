package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R.drawable;
import com.google.android.material.R.string;

class PasswordToggleEndIconDelegate extends EndIconDelegate {
    private EditText editText;
    private int iconResId;
    private final View.OnClickListener onIconClickListener;

    PasswordToggleEndIconDelegate(EndCompoundLayout endCompoundLayout0, int v) {
        super(endCompoundLayout0);
        this.iconResId = drawable.design_password_eye;
        this.onIconClickListener = (View view0) -> {
            EditText editText0 = this.editText;
            if(editText0 == null) {
                return;
            }
            int v = editText0.getSelectionEnd();
            if(this.hasPasswordTransformation()) {
                this.editText.setTransformationMethod(null);
            }
            else {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if(v >= 0) {
                this.editText.setSelection(v);
            }
            this.refreshIconState();
        };
        if(v != 0) {
            this.iconResId = v;
        }
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void beforeEditTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
        this.refreshIconState();
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    int getIconContentDescriptionResId() {
        return string.password_toggle_content_description;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    int getIconDrawableResId() {
        return this.iconResId;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    private boolean hasPasswordTransformation() {
        return this.editText != null && this.editText.getTransformationMethod() instanceof PasswordTransformationMethod;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isIconCheckable() {
        return true;
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    boolean isIconChecked() {
        return !this.hasPasswordTransformation();
    }

    private static boolean isInputTypePassword(EditText editText0) {
        if(editText0 != null) {
            switch(editText0.getInputType()) {
                case 16: 
                case 0x80: 
                case 0x90: 
                case 0xE0: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-textfield-PasswordToggleEndIconDelegate(View view0) [...]

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void onEditTextAttached(EditText editText0) {
        this.editText = editText0;
        this.refreshIconState();
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void setUp() {
        if(PasswordToggleEndIconDelegate.isInputTypePassword(this.editText)) {
            this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void tearDown() {
        EditText editText0 = this.editText;
        if(editText0 != null) {
            editText0.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}

