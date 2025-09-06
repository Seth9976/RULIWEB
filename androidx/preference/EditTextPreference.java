package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import androidx.core.content.res.TypedArrayUtils;

public class EditTextPreference extends DialogPreference {
    public interface OnBindEditTextListener {
        void onBindEditText(EditText arg1);
    }

    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        String mText;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState(Parcel parcel0) {
            super(parcel0);
            this.mText = parcel0.readString();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeString(this.mText);
        }
    }

    public static final class SimpleSummaryProvider implements SummaryProvider {
        private static SimpleSummaryProvider sSimpleSummaryProvider;

        public static SimpleSummaryProvider getInstance() {
            if(SimpleSummaryProvider.sSimpleSummaryProvider == null) {
                SimpleSummaryProvider.sSimpleSummaryProvider = new SimpleSummaryProvider();
            }
            return SimpleSummaryProvider.sSimpleSummaryProvider;
        }

        // 去混淆评级： 低(20)
        public CharSequence provideSummary(EditTextPreference editTextPreference0) {
            return TextUtils.isEmpty(editTextPreference0.getText()) ? editTextPreference0.getContext().getString(string.not_set) : editTextPreference0.getText();
        }

        @Override  // androidx.preference.Preference$SummaryProvider
        public CharSequence provideSummary(Preference preference0) {
            return this.provideSummary(((EditTextPreference)preference0));
        }
    }

    private OnBindEditTextListener mOnBindEditTextListener;
    private String mText;

    public EditTextPreference(Context context0) {
        this(context0, null);
    }

    public EditTextPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.editTextPreferenceStyle, 0x1010092));
    }

    public EditTextPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public EditTextPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.EditTextPreference, v, v1);
        if(TypedArrayUtils.getBoolean(typedArray0, styleable.EditTextPreference_useSimpleSummaryProvider, styleable.EditTextPreference_useSimpleSummaryProvider, false)) {
            this.setSummaryProvider(SimpleSummaryProvider.getInstance());
        }
        typedArray0.recycle();
    }

    OnBindEditTextListener getOnBindEditTextListener() {
        return this.mOnBindEditTextListener;
    }

    public String getText() {
        return this.mText;
    }

    @Override  // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray0, int v) {
        return typedArray0.getString(v);
    }

    @Override  // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 != null && parcelable0.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
            this.setText(((SavedState)parcelable0).mText);
            return;
        }
        super.onRestoreInstanceState(parcelable0);
    }

    @Override  // androidx.preference.Preference
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = super.onSaveInstanceState();
        if(this.isPersistent()) {
            return parcelable0;
        }
        Parcelable parcelable1 = new SavedState(parcelable0);
        parcelable1.mText = this.getText();
        return parcelable1;
    }

    @Override  // androidx.preference.Preference
    protected void onSetInitialValue(Object object0) {
        this.setText(this.getPersistedString(((String)object0)));
    }

    public void setOnBindEditTextListener(OnBindEditTextListener editTextPreference$OnBindEditTextListener0) {
        this.mOnBindEditTextListener = editTextPreference$OnBindEditTextListener0;
    }

    public void setText(String s) {
        boolean z = this.shouldDisableDependents();
        this.mText = s;
        this.persistString(s);
        boolean z1 = this.shouldDisableDependents();
        if(z1 != z) {
            this.notifyDependencyChange(z1);
        }
        this.notifyChanged();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return TextUtils.isEmpty(this.mText) || super.shouldDisableDependents();
    }
}

