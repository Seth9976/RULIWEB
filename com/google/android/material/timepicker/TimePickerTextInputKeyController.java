package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TextView;

class TimePickerTextInputKeyController implements View.OnKeyListener, TextView.OnEditorActionListener {
    private final ChipTextInputComboView hourLayoutComboView;
    private boolean keyListenerRunning;
    private final ChipTextInputComboView minuteLayoutComboView;
    private final TimeModel time;

    TimePickerTextInputKeyController(ChipTextInputComboView chipTextInputComboView0, ChipTextInputComboView chipTextInputComboView1, TimeModel timeModel0) {
        this.keyListenerRunning = false;
        this.hourLayoutComboView = chipTextInputComboView0;
        this.minuteLayoutComboView = chipTextInputComboView1;
        this.time = timeModel0;
    }

    public void bind() {
        EditText editText0 = this.hourLayoutComboView.getTextInput().getEditText();
        EditText editText1 = this.minuteLayoutComboView.getTextInput().getEditText();
        editText0.setImeOptions(0x10000005);
        editText1.setImeOptions(0x10000006);
        editText0.setOnEditorActionListener(this);
        editText0.setOnKeyListener(this);
        editText1.setOnKeyListener(this);
    }

    private void clearPrefilledText(EditText editText0) {
        if(editText0.getSelectionStart() == 0 && editText0.length() == 2) {
            editText0.getText().clear();
        }
    }

    private void moveSelection(int v) {
        boolean z = true;
        this.minuteLayoutComboView.setChecked(v == 12);
        ChipTextInputComboView chipTextInputComboView0 = this.hourLayoutComboView;
        if(v != 10) {
            z = false;
        }
        chipTextInputComboView0.setChecked(z);
        this.time.selection = v;
    }

    @Override  // android.widget.TextView$OnEditorActionListener
    public boolean onEditorAction(TextView textView0, int v, KeyEvent keyEvent0) {
        if(v == 5) {
            this.moveSelection(12);
        }
        return v == 5;
    }

    private boolean onHourKeyPress(int v, KeyEvent keyEvent0, EditText editText0) {
        Editable editable0 = editText0.getText();
        if(editable0 == null) {
            return false;
        }
        if(v >= 7 && v <= 16 && keyEvent0.getAction() == 1 && editText0.getSelectionStart() == 2 && editable0.length() == 2) {
            this.moveSelection(12);
            return true;
        }
        this.clearPrefilledText(editText0);
        return false;
    }

    @Override  // android.view.View$OnKeyListener
    public boolean onKey(View view0, int v, KeyEvent keyEvent0) {
        if(this.keyListenerRunning) {
            return false;
        }
        this.keyListenerRunning = true;
        boolean z = this.time.selection == 12 ? this.onMinuteKeyPress(v, keyEvent0, ((EditText)view0)) : this.onHourKeyPress(v, keyEvent0, ((EditText)view0));
        this.keyListenerRunning = false;
        return z;
    }

    private boolean onMinuteKeyPress(int v, KeyEvent keyEvent0, EditText editText0) {
        if(v == 67 && keyEvent0.getAction() == 0 && TextUtils.isEmpty(editText0.getText())) {
            this.moveSelection(10);
            return true;
        }
        this.clearPrefilledText(editText0);
        return false;
    }
}

