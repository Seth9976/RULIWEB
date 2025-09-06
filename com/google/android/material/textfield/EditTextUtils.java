package com.google.android.material.textfield;

import android.widget.EditText;

class EditTextUtils {
    static boolean isEditable(EditText editText0) {
        return editText0.getInputType() != 0;
    }
}

