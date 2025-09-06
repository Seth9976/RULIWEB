package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.TypedArrayUtils;

public class SwitchPreferenceCompat extends TwoStatePreference {
    class Listener implements CompoundButton.OnCheckedChangeListener {
        @Override  // android.widget.CompoundButton$OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton0, boolean z) {
            if(!SwitchPreferenceCompat.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton0.setChecked(!z);
                return;
            }
            SwitchPreferenceCompat.this.setChecked(z);
        }
    }

    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    public SwitchPreferenceCompat(Context context0) {
        this(context0, null);
    }

    public SwitchPreferenceCompat(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.switchPreferenceCompatStyle);
    }

    public SwitchPreferenceCompat(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public SwitchPreferenceCompat(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mListener = new Listener(this);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.SwitchPreferenceCompat, v, v1);
        this.setSummaryOn(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreferenceCompat_summaryOn, styleable.SwitchPreferenceCompat_android_summaryOn));
        this.setSummaryOff(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreferenceCompat_summaryOff, styleable.SwitchPreferenceCompat_android_summaryOff));
        this.setSwitchTextOn(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreferenceCompat_switchTextOn, styleable.SwitchPreferenceCompat_android_switchTextOn));
        this.setSwitchTextOff(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreferenceCompat_switchTextOff, styleable.SwitchPreferenceCompat_android_switchTextOff));
        this.setDisableDependentsState(TypedArrayUtils.getBoolean(typedArray0, styleable.SwitchPreferenceCompat_disableDependentsState, styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        typedArray0.recycle();
    }

    public CharSequence getSwitchTextOff() {
        return this.mSwitchOff;
    }

    public CharSequence getSwitchTextOn() {
        return this.mSwitchOn;
    }

    @Override  // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        super.onBindViewHolder(preferenceViewHolder0);
        this.syncSwitchView(preferenceViewHolder0.findViewById(id.switchWidget));
        this.syncSummaryView(preferenceViewHolder0);
    }

    @Override  // androidx.preference.Preference
    protected void performClick(View view0) {
        super.performClick(view0);
        this.syncViewIfAccessibilityEnabled(view0);
    }

    public void setSwitchTextOff(int v) {
        this.setSwitchTextOff(this.getContext().getString(v));
    }

    public void setSwitchTextOff(CharSequence charSequence0) {
        this.mSwitchOff = charSequence0;
        this.notifyChanged();
    }

    public void setSwitchTextOn(int v) {
        this.setSwitchTextOn(this.getContext().getString(v));
    }

    public void setSwitchTextOn(CharSequence charSequence0) {
        this.mSwitchOn = charSequence0;
        this.notifyChanged();
    }

    private void syncSwitchView(View view0) {
        if(view0 instanceof SwitchCompat) {
            ((SwitchCompat)view0).setOnCheckedChangeListener(null);
        }
        if(view0 instanceof Checkable) {
            ((Checkable)view0).setChecked(this.mChecked);
        }
        if(view0 instanceof SwitchCompat) {
            ((SwitchCompat)view0).setTextOn(this.mSwitchOn);
            ((SwitchCompat)view0).setTextOff(this.mSwitchOff);
            ((SwitchCompat)view0).setOnCheckedChangeListener(this.mListener);
        }
    }

    private void syncViewIfAccessibilityEnabled(View view0) {
        if(!((AccessibilityManager)this.getContext().getSystemService("accessibility")).isEnabled()) {
            return;
        }
        this.syncSwitchView(view0.findViewById(id.switchWidget));
        this.syncSummaryView(view0.findViewById(0x1020010));
    }
}

