package androidx.preference;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class EditTextPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    private static final String SAVE_STATE_TEXT = "EditTextPreferenceDialogFragment.text";
    private static final int SHOW_REQUEST_TIMEOUT = 1000;
    private EditText mEditText;
    private long mShowRequestTime;
    private final Runnable mShowSoftInputRunnable;
    private CharSequence mText;

    public EditTextPreferenceDialogFragmentCompat() {
        this.mShowSoftInputRunnable = () -> if(EditTextPreferenceDialogFragmentCompat.this.hasPendingShowSoftInputRequest()) {
            if(EditTextPreferenceDialogFragmentCompat.this.mEditText != null && EditTextPreferenceDialogFragmentCompat.this.mEditText.isFocused()) {
                if(((InputMethodManager)EditTextPreferenceDialogFragmentCompat.this.mEditText.getContext().getSystemService("input_method")).showSoftInput(EditTextPreferenceDialogFragmentCompat.this.mEditText, 0)) {
                    EditTextPreferenceDialogFragmentCompat.this.setPendingShowSoftInputRequest(false);
                    return;
                }
                EditTextPreferenceDialogFragmentCompat.this.mEditText.removeCallbacks(EditTextPreferenceDialogFragmentCompat.this.mShowSoftInputRunnable);
                EditTextPreferenceDialogFragmentCompat.this.mEditText.postDelayed(EditTextPreferenceDialogFragmentCompat.this.mShowSoftInputRunnable, 50L);
                return;
            }
            EditTextPreferenceDialogFragmentCompat.this.setPendingShowSoftInputRequest(false);
        };
        this.mShowRequestTime = -1L;
    }

    private EditTextPreference getEditTextPreference() {
        return (EditTextPreference)this.getPreference();
    }

    private boolean hasPendingShowSoftInputRequest() {
        return this.mShowRequestTime != -1L && this.mShowRequestTime + 1000L > SystemClock.currentThreadTimeMillis();
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    protected boolean needInputMethod() {
        return true;
    }

    public static EditTextPreferenceDialogFragmentCompat newInstance(String s) {
        EditTextPreferenceDialogFragmentCompat editTextPreferenceDialogFragmentCompat0 = new EditTextPreferenceDialogFragmentCompat();
        Bundle bundle0 = new Bundle(1);
        bundle0.putString("key", s);
        editTextPreferenceDialogFragmentCompat0.setArguments(bundle0);
        return editTextPreferenceDialogFragmentCompat0;
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    protected void onBindDialogView(View view0) {
        super.onBindDialogView(view0);
        EditText editText0 = (EditText)view0.findViewById(0x1020003);
        this.mEditText = editText0;
        if(editText0 == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText0.requestFocus();
        this.mEditText.setText(this.mText);
        this.mEditText.setSelection(this.mEditText.getText().length());
        if(this.getEditTextPreference().getOnBindEditTextListener() != null) {
            this.getEditTextPreference().getOnBindEditTextListener().onBindEditText(this.mEditText);
        }
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            this.mText = this.getEditTextPreference().getText();
            return;
        }
        this.mText = bundle0.getCharSequence("EditTextPreferenceDialogFragment.text");
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    public void onDialogClosed(boolean z) {
        if(z) {
            String s = this.mEditText.getText().toString();
            EditTextPreference editTextPreference0 = this.getEditTextPreference();
            if(editTextPreference0.callChangeListener(s)) {
                editTextPreference0.setText(s);
            }
        }
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putCharSequence("EditTextPreferenceDialogFragment.text", this.mText);
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    protected void scheduleShowSoftInput() {
        this.setPendingShowSoftInputRequest(true);
        this.scheduleShowSoftInputInner();
    }

    // 检测为 Lambda 实现
    void scheduleShowSoftInputInner() [...]

    private void setPendingShowSoftInputRequest(boolean z) {
        this.mShowRequestTime = z ? SystemClock.currentThreadTimeMillis() : -1L;
    }

    class androidx.preference.EditTextPreferenceDialogFragmentCompat.1 implements Runnable {
        @Override
        public void run() {
            EditTextPreferenceDialogFragmentCompat.this.scheduleShowSoftInputInner();
        }
    }

}

