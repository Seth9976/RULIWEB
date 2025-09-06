package androidx.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public abstract class PreferenceDialogFragmentCompat extends DialogFragment implements DialogInterface.OnClickListener {
    static class Api30Impl {
        static void showIme(Window window0) {
            window0.getDecorView().getWindowInsetsController().show(8);
        }
    }

    protected static final String ARG_KEY = "key";
    private static final String SAVE_STATE_ICON = "PreferenceDialogFragment.icon";
    private static final String SAVE_STATE_LAYOUT = "PreferenceDialogFragment.layout";
    private static final String SAVE_STATE_MESSAGE = "PreferenceDialogFragment.message";
    private static final String SAVE_STATE_NEGATIVE_TEXT = "PreferenceDialogFragment.negativeText";
    private static final String SAVE_STATE_POSITIVE_TEXT = "PreferenceDialogFragment.positiveText";
    private static final String SAVE_STATE_TITLE = "PreferenceDialogFragment.title";
    private BitmapDrawable mDialogIcon;
    private int mDialogLayoutRes;
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;
    private DialogPreference mPreference;
    private int mWhichButtonClicked;

    public DialogPreference getPreference() {
        if(this.mPreference == null) {
            String s = this.requireArguments().getString("key");
            this.mPreference = (DialogPreference)((TargetFragment)this.getTargetFragment()).findPreference(s);
        }
        return this.mPreference;
    }

    protected boolean needInputMethod() {
        return false;
    }

    protected void onBindDialogView(View view0) {
        int v;
        View view1 = view0.findViewById(0x102000B);
        if(view1 != null) {
            CharSequence charSequence0 = this.mDialogMessage;
            if(TextUtils.isEmpty(charSequence0)) {
                v = 8;
            }
            else {
                if(view1 instanceof TextView) {
                    ((TextView)view1).setText(charSequence0);
                }
                v = 0;
            }
            if(view1.getVisibility() != v) {
                view1.setVisibility(v);
            }
        }
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public void onClick(DialogInterface dialogInterface0, int v) {
        this.mWhichButtonClicked = v;
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        Fragment fragment0 = this.getTargetFragment();
        if(!(fragment0 instanceof TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        String s = this.requireArguments().getString("key");
        if(bundle0 == null) {
            DialogPreference dialogPreference0 = (DialogPreference)((TargetFragment)fragment0).findPreference(s);
            this.mPreference = dialogPreference0;
            this.mDialogTitle = dialogPreference0.getDialogTitle();
            this.mPositiveButtonText = this.mPreference.getPositiveButtonText();
            this.mNegativeButtonText = this.mPreference.getNegativeButtonText();
            this.mDialogMessage = this.mPreference.getDialogMessage();
            this.mDialogLayoutRes = this.mPreference.getDialogLayoutResource();
            Drawable drawable0 = this.mPreference.getDialogIcon();
            if(drawable0 != null && !(drawable0 instanceof BitmapDrawable)) {
                Bitmap bitmap0 = Bitmap.createBitmap(drawable0.getIntrinsicWidth(), drawable0.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas0 = new Canvas(bitmap0);
                drawable0.setBounds(0, 0, canvas0.getWidth(), canvas0.getHeight());
                drawable0.draw(canvas0);
                this.mDialogIcon = new BitmapDrawable(this.getResources(), bitmap0);
                return;
            }
            this.mDialogIcon = (BitmapDrawable)drawable0;
            return;
        }
        this.mDialogTitle = bundle0.getCharSequence("PreferenceDialogFragment.title");
        this.mPositiveButtonText = bundle0.getCharSequence("PreferenceDialogFragment.positiveText");
        this.mNegativeButtonText = bundle0.getCharSequence("PreferenceDialogFragment.negativeText");
        this.mDialogMessage = bundle0.getCharSequence("PreferenceDialogFragment.message");
        this.mDialogLayoutRes = bundle0.getInt("PreferenceDialogFragment.layout", 0);
        Bitmap bitmap1 = (Bitmap)bundle0.getParcelable("PreferenceDialogFragment.icon");
        if(bitmap1 != null) {
            this.mDialogIcon = new BitmapDrawable(this.getResources(), bitmap1);
        }
    }

    @Override  // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle0) {
        this.mWhichButtonClicked = -2;
        Builder alertDialog$Builder0 = new Builder(this.requireContext()).setTitle(this.mDialogTitle).setIcon(this.mDialogIcon).setPositiveButton(this.mPositiveButtonText, this).setNegativeButton(this.mNegativeButtonText, this);
        View view0 = this.onCreateDialogView(this.requireContext());
        if(view0 == null) {
            alertDialog$Builder0.setMessage(this.mDialogMessage);
        }
        else {
            this.onBindDialogView(view0);
            alertDialog$Builder0.setView(view0);
        }
        this.onPrepareDialogBuilder(alertDialog$Builder0);
        Dialog dialog0 = alertDialog$Builder0.create();
        if(this.needInputMethod()) {
            this.requestInputMethod(dialog0);
        }
        return dialog0;
    }

    protected View onCreateDialogView(Context context0) {
        int v = this.mDialogLayoutRes;
        return v == 0 ? null : this.getLayoutInflater().inflate(v, null);
    }

    public abstract void onDialogClosed(boolean arg1);

    @Override  // androidx.fragment.app.DialogFragment
    public void onDismiss(DialogInterface dialogInterface0) {
        super.onDismiss(dialogInterface0);
        this.onDialogClosed(this.mWhichButtonClicked == -1);
    }

    protected void onPrepareDialogBuilder(Builder alertDialog$Builder0) {
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putCharSequence("PreferenceDialogFragment.title", this.mDialogTitle);
        bundle0.putCharSequence("PreferenceDialogFragment.positiveText", this.mPositiveButtonText);
        bundle0.putCharSequence("PreferenceDialogFragment.negativeText", this.mNegativeButtonText);
        bundle0.putCharSequence("PreferenceDialogFragment.message", this.mDialogMessage);
        bundle0.putInt("PreferenceDialogFragment.layout", this.mDialogLayoutRes);
        BitmapDrawable bitmapDrawable0 = this.mDialogIcon;
        if(bitmapDrawable0 != null) {
            bundle0.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable0.getBitmap());
        }
    }

    private void requestInputMethod(Dialog dialog0) {
        Window window0 = dialog0.getWindow();
        if(Build.VERSION.SDK_INT >= 30) {
            Api30Impl.showIme(window0);
            return;
        }
        this.scheduleShowSoftInput();
    }

    protected void scheduleShowSoftInput() {
    }
}

