package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.content.res.TypedArrayUtils;

public class ListPreference extends DialogPreference {
    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        String mValue;

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
            this.mValue = parcel0.readString();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeString(this.mValue);
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

        public CharSequence provideSummary(ListPreference listPreference0) {
            return TextUtils.isEmpty(listPreference0.getEntry()) ? listPreference0.getContext().getString(string.not_set) : listPreference0.getEntry();
        }

        @Override  // androidx.preference.Preference$SummaryProvider
        public CharSequence provideSummary(Preference preference0) {
            return this.provideSummary(((ListPreference)preference0));
        }
    }

    private static final String TAG = "ListPreference";
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private String mSummary;
    private String mValue;
    private boolean mValueSet;

    public ListPreference(Context context0) {
        this(context0, null);
    }

    public ListPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.dialogPreferenceStyle, 0x1010091));
    }

    public ListPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public ListPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ListPreference, v, v1);
        this.mEntries = TypedArrayUtils.getTextArray(typedArray0, styleable.ListPreference_entries, styleable.ListPreference_android_entries);
        this.mEntryValues = TypedArrayUtils.getTextArray(typedArray0, styleable.ListPreference_entryValues, styleable.ListPreference_android_entryValues);
        if(TypedArrayUtils.getBoolean(typedArray0, styleable.ListPreference_useSimpleSummaryProvider, styleable.ListPreference_useSimpleSummaryProvider, false)) {
            this.setSummaryProvider(SimpleSummaryProvider.getInstance());
        }
        typedArray0.recycle();
        TypedArray typedArray1 = context0.obtainStyledAttributes(attributeSet0, styleable.Preference, v, v1);
        this.mSummary = TypedArrayUtils.getString(typedArray1, styleable.Preference_summary, styleable.Preference_android_summary);
        typedArray1.recycle();
    }

    public int findIndexOfValue(String s) {
        if(s != null) {
            CharSequence[] arr_charSequence = this.mEntryValues;
            if(arr_charSequence != null) {
                for(int v = arr_charSequence.length - 1; v >= 0; --v) {
                    if(TextUtils.equals(this.mEntryValues[v].toString(), s)) {
                        return v;
                    }
                }
            }
        }
        return -1;
    }

    public CharSequence[] getEntries() {
        return this.mEntries;
    }

    public CharSequence getEntry() {
        int v = this.getValueIndex();
        if(v >= 0) {
            return this.mEntries == null ? null : this.mEntries[v];
        }
        return null;
    }

    public CharSequence[] getEntryValues() {
        return this.mEntryValues;
    }

    @Override  // androidx.preference.Preference
    public CharSequence getSummary() {
        if(this.getSummaryProvider() != null) {
            return this.getSummaryProvider().provideSummary(this);
        }
        CharSequence charSequence0 = this.getEntry();
        CharSequence charSequence1 = super.getSummary();
        String s = this.mSummary;
        if(s != null) {
            if(charSequence0 == null) {
                charSequence0 = "";
            }
            CharSequence charSequence2 = String.format(s, charSequence0);
            if(!TextUtils.equals(charSequence2, charSequence1)) {
                Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
                return charSequence2;
            }
        }
        return charSequence1;
    }

    public String getValue() {
        return this.mValue;
    }

    private int getValueIndex() {
        return this.findIndexOfValue(this.mValue);
    }

    @Override  // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray0, int v) {
        return typedArray0.getString(v);
    }

    @Override  // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 != null && parcelable0.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
            this.setValue(((SavedState)parcelable0).mValue);
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
        parcelable1.mValue = this.getValue();
        return parcelable1;
    }

    @Override  // androidx.preference.Preference
    protected void onSetInitialValue(Object object0) {
        this.setValue(this.getPersistedString(((String)object0)));
    }

    public void setEntries(int v) {
        this.setEntries(this.getContext().getResources().getTextArray(v));
    }

    public void setEntries(CharSequence[] arr_charSequence) {
        this.mEntries = arr_charSequence;
    }

    public void setEntryValues(int v) {
        this.setEntryValues(this.getContext().getResources().getTextArray(v));
    }

    public void setEntryValues(CharSequence[] arr_charSequence) {
        this.mEntryValues = arr_charSequence;
    }

    @Override  // androidx.preference.Preference
    public void setSummary(CharSequence charSequence0) {
        super.setSummary(charSequence0);
        if(charSequence0 == null) {
            this.mSummary = null;
            return;
        }
        this.mSummary = charSequence0.toString();
    }

    public void setValue(String s) {
        boolean z = TextUtils.equals(this.mValue, s);
        if(!z || !this.mValueSet) {
            this.mValue = s;
            this.mValueSet = true;
            this.persistString(s);
            if(!z) {
                this.notifyChanged();
            }
        }
    }

    public void setValueIndex(int v) {
        CharSequence[] arr_charSequence = this.mEntryValues;
        if(arr_charSequence != null) {
            this.setValue(arr_charSequence[v].toString());
        }
    }
}

