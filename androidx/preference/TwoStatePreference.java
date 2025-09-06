package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public abstract class TwoStatePreference extends Preference {
    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean mChecked;

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
            this.mChecked = parcel0.readInt() == 1;
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(((int)this.mChecked));
        }
    }

    protected boolean mChecked;
    private boolean mCheckedSet;
    private boolean mDisableDependentsState;
    private CharSequence mSummaryOff;
    private CharSequence mSummaryOn;

    public TwoStatePreference(Context context0) {
        this(context0, null);
    }

    public TwoStatePreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public TwoStatePreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public TwoStatePreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
    }

    public boolean getDisableDependentsState() {
        return this.mDisableDependentsState;
    }

    public CharSequence getSummaryOff() {
        return this.mSummaryOff;
    }

    public CharSequence getSummaryOn() {
        return this.mSummaryOn;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    @Override  // androidx.preference.Preference
    protected void onClick() {
        super.onClick();
        boolean z = this.isChecked();
        if(this.callChangeListener(Boolean.valueOf(!z))) {
            this.setChecked(!z);
        }
    }

    @Override  // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray0, int v) {
        return Boolean.valueOf(typedArray0.getBoolean(v, false));
    }

    @Override  // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 != null && parcelable0.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
            this.setChecked(((SavedState)parcelable0).mChecked);
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
        parcelable1.mChecked = this.isChecked();
        return parcelable1;
    }

    @Override  // androidx.preference.Preference
    protected void onSetInitialValue(Object object0) {
        if(object0 == null) {
            object0 = Boolean.FALSE;
        }
        this.setChecked(this.getPersistedBoolean(((Boolean)object0).booleanValue()));
    }

    public void setChecked(boolean z) {
        boolean z1 = this.mChecked != z;
        if(z1 || !this.mCheckedSet) {
            this.mChecked = z;
            this.mCheckedSet = true;
            this.persistBoolean(z);
            if(z1) {
                this.notifyDependencyChange(this.shouldDisableDependents());
                this.notifyChanged();
            }
        }
    }

    public void setDisableDependentsState(boolean z) {
        this.mDisableDependentsState = z;
    }

    public void setSummaryOff(int v) {
        this.setSummaryOff(this.getContext().getString(v));
    }

    public void setSummaryOff(CharSequence charSequence0) {
        this.mSummaryOff = charSequence0;
        if(!this.isChecked()) {
            this.notifyChanged();
        }
    }

    public void setSummaryOn(int v) {
        this.setSummaryOn(this.getContext().getString(v));
    }

    public void setSummaryOn(CharSequence charSequence0) {
        this.mSummaryOn = charSequence0;
        if(this.isChecked()) {
            this.notifyChanged();
        }
    }

    // 去混淆评级： 中等(50)
    @Override  // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return this.mDisableDependentsState ? this.mChecked || super.shouldDisableDependents() : !this.mChecked || super.shouldDisableDependents();
    }

    protected void syncSummaryView(View view0) {
        boolean z;
        int v = 0;
        if(view0 instanceof TextView) {
            if(this.mChecked && !TextUtils.isEmpty(this.mSummaryOn)) {
                ((TextView)view0).setText(this.mSummaryOn);
                z = false;
            }
            else if(this.mChecked || TextUtils.isEmpty(this.mSummaryOff)) {
                z = true;
            }
            else {
                ((TextView)view0).setText(this.mSummaryOff);
                z = false;
            }
            if(z) {
                CharSequence charSequence0 = this.getSummary();
                if(!TextUtils.isEmpty(charSequence0)) {
                    ((TextView)view0).setText(charSequence0);
                    z = false;
                }
            }
            if(z) {
                v = 8;
            }
            if(v != ((TextView)view0).getVisibility()) {
                ((TextView)view0).setVisibility(v);
            }
        }
    }

    protected void syncSummaryView(PreferenceViewHolder preferenceViewHolder0) {
        this.syncSummaryView(preferenceViewHolder0.findViewById(0x1020010));
    }
}

