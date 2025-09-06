package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class ChipTextInputComboView extends FrameLayout implements Checkable {
    class TextFormatter extends TextWatcherAdapter {
        private static final String DEFAULT_TEXT = "00";

        private TextFormatter() {
        }

        TextFormatter(com.google.android.material.timepicker.ChipTextInputComboView.1 chipTextInputComboView$10) {
        }

        @Override  // com.google.android.material.internal.TextWatcherAdapter
        public void afterTextChanged(Editable editable0) {
            if(TextUtils.isEmpty(editable0)) {
                ChipTextInputComboView.this.chip.setText(ChipTextInputComboView.this.formatText("00"));
                return;
            }
            String s = ChipTextInputComboView.this.formatText(editable0);
            Chip chip0 = ChipTextInputComboView.this.chip;
            if(TextUtils.isEmpty(s)) {
                s = ChipTextInputComboView.this.formatText("00");
            }
            chip0.setText(s);
        }
    }

    private final Chip chip;
    private final EditText editText;
    private TextView label;
    private final TextInputLayout textInputLayout;
    private TextWatcher watcher;

    public ChipTextInputComboView(Context context0) {
        this(context0, null);
    }

    public ChipTextInputComboView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ChipTextInputComboView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        LayoutInflater layoutInflater0 = LayoutInflater.from(context0);
        Chip chip0 = (Chip)layoutInflater0.inflate(layout.material_time_chip, this, false);
        this.chip = chip0;
        chip0.setAccessibilityClassName("android.view.View");
        TextInputLayout textInputLayout0 = (TextInputLayout)layoutInflater0.inflate(layout.material_time_input, this, false);
        this.textInputLayout = textInputLayout0;
        EditText editText0 = textInputLayout0.getEditText();
        this.editText = editText0;
        editText0.setVisibility(4);
        TextFormatter chipTextInputComboView$TextFormatter0 = new TextFormatter(this, null);
        this.watcher = chipTextInputComboView$TextFormatter0;
        editText0.addTextChangedListener(chipTextInputComboView$TextFormatter0);
        this.updateHintLocales();
        this.addView(chip0);
        this.addView(textInputLayout0);
        this.label = (TextView)this.findViewById(id.material_label);
        editText0.setId(ViewCompat.generateViewId());
        ViewCompat.setLabelFor(this.label, editText0.getId());
        editText0.setSaveEnabled(false);
        editText0.setLongClickable(false);
    }

    public void addInputFilter(InputFilter inputFilter0) {
        InputFilter[] arr_inputFilter = this.editText.getFilters();
        InputFilter[] arr_inputFilter1 = (InputFilter[])Arrays.copyOf(arr_inputFilter, arr_inputFilter.length + 1);
        arr_inputFilter1[arr_inputFilter.length] = inputFilter0;
        this.editText.setFilters(arr_inputFilter1);
    }

    private String formatText(CharSequence charSequence0) {
        return TimeModel.formatText(this.getResources(), charSequence0);
    }

    CharSequence getChipText() {
        return this.chip.getText();
    }

    public TextInputLayout getTextInput() {
        return this.textInputLayout;
    }

    @Override  // android.widget.Checkable
    public boolean isChecked() {
        return this.chip.isChecked();
    }

    @Override  // android.view.View
    protected void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        this.updateHintLocales();
    }

    @Override  // android.widget.Checkable
    public void setChecked(boolean z) {
        this.chip.setChecked(z);
        this.editText.setVisibility((z ? 0 : 4));
        this.chip.setVisibility((z ? 8 : 0));
        if(this.isChecked()) {
            ViewUtils.requestFocusAndShowKeyboard(this.editText, false);
        }
    }

    public void setChipDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat0) {
        ViewCompat.setAccessibilityDelegate(this.chip, accessibilityDelegateCompat0);
    }

    public void setCursorVisible(boolean z) {
        this.editText.setCursorVisible(z);
    }

    public void setHelperText(CharSequence charSequence0) {
        this.label.setText(charSequence0);
    }

    @Override  // android.view.View
    public void setOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.chip.setOnClickListener(view$OnClickListener0);
    }

    @Override  // android.view.View
    public void setTag(int v, Object object0) {
        this.chip.setTag(v, object0);
    }

    public void setText(CharSequence charSequence0) {
        String s = this.formatText(charSequence0);
        this.chip.setText(s);
        if(!TextUtils.isEmpty(s)) {
            this.editText.removeTextChangedListener(this.watcher);
            this.editText.setText(s);
            this.editText.addTextChangedListener(this.watcher);
        }
    }

    @Override  // android.widget.Checkable
    public void toggle() {
        this.chip.toggle();
    }

    private void updateHintLocales() {
        if(Build.VERSION.SDK_INT >= 24) {
            LocaleList localeList0 = this.getContext().getResources().getConfiguration().getLocales();
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.editText, localeList0);
        }
    }

    class com.google.android.material.timepicker.ChipTextInputComboView.1 {
    }

}

