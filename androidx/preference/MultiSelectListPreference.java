package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MultiSelectListPreference extends DialogPreference {
    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        Set mValues;

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
            int v = parcel0.readInt();
            this.mValues = new HashSet();
            String[] arr_s = new String[v];
            parcel0.readStringArray(arr_s);
            Collections.addAll(this.mValues, arr_s);
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.mValues.size());
            parcel0.writeStringArray(((String[])this.mValues.toArray(new String[this.mValues.size()])));
        }
    }

    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private Set mValues;

    public MultiSelectListPreference(Context context0) {
        this(context0, null);
    }

    public MultiSelectListPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.dialogPreferenceStyle, 0x1010091));
    }

    public MultiSelectListPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public MultiSelectListPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mValues = new HashSet();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.MultiSelectListPreference, v, v1);
        this.mEntries = TypedArrayUtils.getTextArray(typedArray0, styleable.MultiSelectListPreference_entries, styleable.MultiSelectListPreference_android_entries);
        this.mEntryValues = TypedArrayUtils.getTextArray(typedArray0, styleable.MultiSelectListPreference_entryValues, styleable.MultiSelectListPreference_android_entryValues);
        typedArray0.recycle();
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

    public CharSequence[] getEntryValues() {
        return this.mEntryValues;
    }

    protected boolean[] getSelectedItems() {
        CharSequence[] arr_charSequence = this.mEntryValues;
        Set set0 = this.mValues;
        boolean[] arr_z = new boolean[arr_charSequence.length];
        for(int v = 0; v < arr_charSequence.length; ++v) {
            arr_z[v] = set0.contains(arr_charSequence[v].toString());
        }
        return arr_z;
    }

    public Set getValues() {
        return this.mValues;
    }

    @Override  // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray0, int v) {
        CharSequence[] arr_charSequence = typedArray0.getTextArray(v);
        HashSet hashSet0 = new HashSet();
        for(int v1 = 0; v1 < arr_charSequence.length; ++v1) {
            hashSet0.add(arr_charSequence[v1].toString());
        }
        return hashSet0;
    }

    @Override  // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 != null && parcelable0.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
            this.setValues(((SavedState)parcelable0).mValues);
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
        parcelable1.mValues = this.getValues();
        return parcelable1;
    }

    @Override  // androidx.preference.Preference
    protected void onSetInitialValue(Object object0) {
        this.setValues(this.getPersistedStringSet(((Set)object0)));
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

    public void setValues(Set set0) {
        this.mValues.clear();
        this.mValues.addAll(set0);
        this.persistStringSet(set0);
        this.notifyChanged();
    }
}

