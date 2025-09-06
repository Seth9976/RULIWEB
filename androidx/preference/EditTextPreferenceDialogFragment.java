package androidx.preference;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

@Deprecated
public class EditTextPreferenceDialogFragment extends PreferenceDialogFragment {
    private static final String SAVE_STATE_TEXT = "EditTextPreferenceDialogFragment.text";
    private EditText mEditText;
    private CharSequence mText;

    private EditTextPreference getEditTextPreference() {
        return (EditTextPreference)this.getPreference();
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    protected boolean needInputMethod() {
        return true;
    }

    @Deprecated
    public static EditTextPreferenceDialogFragment newInstance(String s) {
        EditTextPreferenceDialogFragment editTextPreferenceDialogFragment0 = new EditTextPreferenceDialogFragment();
        Bundle bundle0 = new Bundle(1);
        bundle0.putString("key", s);
        editTextPreferenceDialogFragment0.setArguments(bundle0);
        return editTextPreferenceDialogFragment0;
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    protected void onBindDialogView(View view0) {
        super.onBindDialogView(view0);
        EditText editText0 = (EditText)view0.findViewById(0x1020003);
        this.mEditText = editText0;
        editText0.requestFocus();
        EditText editText1 = this.mEditText;
        if(editText1 == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText1.setText(this.mText);
        this.mEditText.setSelection(this.mEditText.getText().length());
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            this.mText = this.getEditTextPreference().getText();
            return;
        }
        this.mText = bundle0.getCharSequence("EditTextPreferenceDialogFragment.text");
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public void onDialogClosed(boolean z) {
        if(z) {
            String s = this.mEditText.getText().toString();
            if(this.getEditTextPreference().callChangeListener(s)) {
                this.getEditTextPreference().setText(s);
            }
        }
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putCharSequence("EditTextPreferenceDialogFragment.text", this.mText);
    }
}

