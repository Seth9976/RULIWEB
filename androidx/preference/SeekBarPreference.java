package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.View;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarPreference extends Preference {
    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        int mMax;
        int mMin;
        int mSeekBarValue;

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
            this.mSeekBarValue = parcel0.readInt();
            this.mMin = parcel0.readInt();
            this.mMax = parcel0.readInt();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.mSeekBarValue);
            parcel0.writeInt(this.mMin);
            parcel0.writeInt(this.mMax);
        }
    }

    private static final String TAG = "SeekBarPreference";
    boolean mAdjustable;
    private int mMax;
    int mMin;
    SeekBar mSeekBar;
    private final SeekBar.OnSeekBarChangeListener mSeekBarChangeListener;
    private int mSeekBarIncrement;
    private final View.OnKeyListener mSeekBarKeyListener;
    int mSeekBarValue;
    private TextView mSeekBarValueTextView;
    private boolean mShowSeekBarValue;
    boolean mTrackingTouch;
    boolean mUpdatesContinuously;

    public SeekBarPreference(Context context0) {
        this(context0, null);
    }

    public SeekBarPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.seekBarPreferenceStyle);
    }

    public SeekBarPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public SeekBarPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override  // android.widget.SeekBar$OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar0, int v, boolean z) {
                if(z && (SeekBarPreference.this.mUpdatesContinuously || !SeekBarPreference.this.mTrackingTouch)) {
                    SeekBarPreference.this.syncValueInternal(seekBar0);
                    return;
                }
                SeekBarPreference.this.updateLabelValue(v + SeekBarPreference.this.mMin);
            }

            @Override  // android.widget.SeekBar$OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar0) {
                SeekBarPreference.this.mTrackingTouch = true;
            }

            @Override  // android.widget.SeekBar$OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar0) {
                SeekBarPreference.this.mTrackingTouch = false;
                if(seekBar0.getProgress() + SeekBarPreference.this.mMin != SeekBarPreference.this.mSeekBarValue) {
                    SeekBarPreference.this.syncValueInternal(seekBar0);
                }
            }
        };
        this.mSeekBarKeyListener = new View.OnKeyListener() {
            @Override  // android.view.View$OnKeyListener
            public boolean onKey(View view0, int v, KeyEvent keyEvent0) {
                if(keyEvent0.getAction() != 0) {
                    return false;
                }
                if(!SeekBarPreference.this.mAdjustable && (v == 21 || v == 22)) {
                    return false;
                }
                if(v != 23 && v != 66) {
                    if(SeekBarPreference.this.mSeekBar == null) {
                        Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                        return false;
                    }
                    return SeekBarPreference.this.mSeekBar.onKeyDown(v, keyEvent0);
                }
                return false;
            }
        };
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.SeekBarPreference, v, v1);
        this.mMin = typedArray0.getInt(styleable.SeekBarPreference_min, 0);
        this.setMax(typedArray0.getInt(styleable.SeekBarPreference_android_max, 100));
        this.setSeekBarIncrement(typedArray0.getInt(styleable.SeekBarPreference_seekBarIncrement, 0));
        this.mAdjustable = typedArray0.getBoolean(styleable.SeekBarPreference_adjustable, true);
        this.mShowSeekBarValue = typedArray0.getBoolean(styleable.SeekBarPreference_showSeekBarValue, false);
        this.mUpdatesContinuously = typedArray0.getBoolean(styleable.SeekBarPreference_updatesContinuously, false);
        typedArray0.recycle();
    }

    public int getMax() {
        return this.mMax;
    }

    public int getMin() {
        return this.mMin;
    }

    public final int getSeekBarIncrement() {
        return this.mSeekBarIncrement;
    }

    public boolean getShowSeekBarValue() {
        return this.mShowSeekBarValue;
    }

    public boolean getUpdatesContinuously() {
        return this.mUpdatesContinuously;
    }

    public int getValue() {
        return this.mSeekBarValue;
    }

    public boolean isAdjustable() {
        return this.mAdjustable;
    }

    @Override  // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        super.onBindViewHolder(preferenceViewHolder0);
        preferenceViewHolder0.itemView.setOnKeyListener(this.mSeekBarKeyListener);
        this.mSeekBar = (SeekBar)preferenceViewHolder0.findViewById(id.seekbar);
        TextView textView0 = (TextView)preferenceViewHolder0.findViewById(id.seekbar_value);
        this.mSeekBarValueTextView = textView0;
        if(this.mShowSeekBarValue) {
            textView0.setVisibility(0);
        }
        else {
            textView0.setVisibility(8);
            this.mSeekBarValueTextView = null;
        }
        SeekBar seekBar0 = this.mSeekBar;
        if(seekBar0 == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar0.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        this.mSeekBar.setMax(this.mMax - this.mMin);
        int v = this.mSeekBarIncrement;
        if(v == 0) {
            this.mSeekBarIncrement = this.mSeekBar.getKeyProgressIncrement();
        }
        else {
            this.mSeekBar.setKeyProgressIncrement(v);
        }
        this.mSeekBar.setProgress(this.mSeekBarValue - this.mMin);
        this.updateLabelValue(this.mSeekBarValue);
        this.mSeekBar.setEnabled(this.isEnabled());
    }

    @Override  // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray0, int v) {
        return typedArray0.getInt(v, 0);
    }

    @Override  // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 != null && parcelable0.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
            this.mSeekBarValue = ((SavedState)parcelable0).mSeekBarValue;
            this.mMin = ((SavedState)parcelable0).mMin;
            this.mMax = ((SavedState)parcelable0).mMax;
            this.notifyChanged();
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
        parcelable1.mSeekBarValue = this.mSeekBarValue;
        parcelable1.mMin = this.mMin;
        parcelable1.mMax = this.mMax;
        return parcelable1;
    }

    @Override  // androidx.preference.Preference
    protected void onSetInitialValue(Object object0) {
        if(object0 == null) {
            object0 = 0;
        }
        this.setValue(this.getPersistedInt(((int)(((Integer)object0)))));
    }

    public void setAdjustable(boolean z) {
        this.mAdjustable = z;
    }

    public final void setMax(int v) {
        int v1 = this.mMin;
        if(v < v1) {
            v = v1;
        }
        if(v != this.mMax) {
            this.mMax = v;
            this.notifyChanged();
        }
    }

    public void setMin(int v) {
        int v1 = this.mMax;
        if(v > v1) {
            v = v1;
        }
        if(v != this.mMin) {
            this.mMin = v;
            this.notifyChanged();
        }
    }

    public final void setSeekBarIncrement(int v) {
        if(v != this.mSeekBarIncrement) {
            this.mSeekBarIncrement = Math.min(this.mMax - this.mMin, Math.abs(v));
            this.notifyChanged();
        }
    }

    public void setShowSeekBarValue(boolean z) {
        this.mShowSeekBarValue = z;
        this.notifyChanged();
    }

    public void setUpdatesContinuously(boolean z) {
        this.mUpdatesContinuously = z;
    }

    public void setValue(int v) {
        this.setValueInternal(v, true);
    }

    private void setValueInternal(int v, boolean z) {
        int v1 = this.mMin;
        if(v < v1) {
            v = v1;
        }
        int v2 = this.mMax;
        if(v > v2) {
            v = v2;
        }
        if(v != this.mSeekBarValue) {
            this.mSeekBarValue = v;
            this.updateLabelValue(v);
            this.persistInt(v);
            if(z) {
                this.notifyChanged();
            }
        }
    }

    void syncValueInternal(SeekBar seekBar0) {
        int v = this.mMin + seekBar0.getProgress();
        if(v != this.mSeekBarValue) {
            if(this.callChangeListener(v)) {
                this.setValueInternal(v, false);
                return;
            }
            seekBar0.setProgress(this.mSeekBarValue - this.mMin);
            this.updateLabelValue(this.mSeekBarValue);
        }
    }

    void updateLabelValue(int v) {
        TextView textView0 = this.mSeekBarValueTextView;
        if(textView0 != null) {
            textView0.setText(String.valueOf(v));
        }
    }
}

