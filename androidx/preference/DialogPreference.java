package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.TypedArrayUtils;

public abstract class DialogPreference extends Preference {
    public interface TargetFragment {
        Preference findPreference(CharSequence arg1);
    }

    private Drawable mDialogIcon;
    private int mDialogLayoutResId;
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;

    public DialogPreference(Context context0) {
        this(context0, null);
    }

    public DialogPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.dialogPreferenceStyle, 0x1010091));
    }

    public DialogPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public DialogPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.DialogPreference, v, v1);
        String s = TypedArrayUtils.getString(typedArray0, styleable.DialogPreference_dialogTitle, styleable.DialogPreference_android_dialogTitle);
        this.mDialogTitle = s;
        if(s == null) {
            this.mDialogTitle = this.getTitle();
        }
        this.mDialogMessage = TypedArrayUtils.getString(typedArray0, styleable.DialogPreference_dialogMessage, styleable.DialogPreference_android_dialogMessage);
        this.mDialogIcon = TypedArrayUtils.getDrawable(typedArray0, styleable.DialogPreference_dialogIcon, styleable.DialogPreference_android_dialogIcon);
        this.mPositiveButtonText = TypedArrayUtils.getString(typedArray0, styleable.DialogPreference_positiveButtonText, styleable.DialogPreference_android_positiveButtonText);
        this.mNegativeButtonText = TypedArrayUtils.getString(typedArray0, styleable.DialogPreference_negativeButtonText, styleable.DialogPreference_android_negativeButtonText);
        this.mDialogLayoutResId = TypedArrayUtils.getResourceId(typedArray0, styleable.DialogPreference_dialogLayout, styleable.DialogPreference_android_dialogLayout, 0);
        typedArray0.recycle();
    }

    public Drawable getDialogIcon() {
        return this.mDialogIcon;
    }

    public int getDialogLayoutResource() {
        return this.mDialogLayoutResId;
    }

    public CharSequence getDialogMessage() {
        return this.mDialogMessage;
    }

    public CharSequence getDialogTitle() {
        return this.mDialogTitle;
    }

    public CharSequence getNegativeButtonText() {
        return this.mNegativeButtonText;
    }

    public CharSequence getPositiveButtonText() {
        return this.mPositiveButtonText;
    }

    @Override  // androidx.preference.Preference
    protected void onClick() {
        this.getPreferenceManager().showDialog(this);
    }

    public void setDialogIcon(int v) {
        this.mDialogIcon = AppCompatResources.getDrawable(this.getContext(), v);
    }

    public void setDialogIcon(Drawable drawable0) {
        this.mDialogIcon = drawable0;
    }

    public void setDialogLayoutResource(int v) {
        this.mDialogLayoutResId = v;
    }

    public void setDialogMessage(int v) {
        this.setDialogMessage(this.getContext().getString(v));
    }

    public void setDialogMessage(CharSequence charSequence0) {
        this.mDialogMessage = charSequence0;
    }

    public void setDialogTitle(int v) {
        this.setDialogTitle(this.getContext().getString(v));
    }

    public void setDialogTitle(CharSequence charSequence0) {
        this.mDialogTitle = charSequence0;
    }

    public void setNegativeButtonText(int v) {
        this.setNegativeButtonText(this.getContext().getString(v));
    }

    public void setNegativeButtonText(CharSequence charSequence0) {
        this.mNegativeButtonText = charSequence0;
    }

    public void setPositiveButtonText(int v) {
        this.setPositiveButtonText(this.getContext().getString(v));
    }

    public void setPositiveButtonText(CharSequence charSequence0) {
        this.mPositiveButtonText = charSequence0;
    }
}

