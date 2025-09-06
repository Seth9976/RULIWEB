package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.core.content.res.TypedArrayUtils;

public class SwitchPreference extends TwoStatePreference {
    class Listener implements CompoundButton.OnCheckedChangeListener {
        @Override  // android.widget.CompoundButton$OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton0, boolean z) {
            if(!SwitchPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton0.setChecked(!z);
                return;
            }
            SwitchPreference.this.setChecked(z);
        }
    }

    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    public SwitchPreference(Context context0) {
        this(context0, null);
    }

    public SwitchPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.switchPreferenceStyle, 0x101036D));
    }

    public SwitchPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public SwitchPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mListener = new Listener(this);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.SwitchPreference, v, v1);
        this.setSummaryOn(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreference_summaryOn, styleable.SwitchPreference_android_summaryOn));
        this.setSummaryOff(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreference_summaryOff, styleable.SwitchPreference_android_summaryOff));
        this.setSwitchTextOn(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreference_switchTextOn, styleable.SwitchPreference_android_switchTextOn));
        this.setSwitchTextOff(TypedArrayUtils.getString(typedArray0, styleable.SwitchPreference_switchTextOff, styleable.SwitchPreference_android_switchTextOff));
        this.setDisableDependentsState(TypedArrayUtils.getBoolean(typedArray0, styleable.SwitchPreference_disableDependentsState, styleable.SwitchPreference_android_disableDependentsState, false));
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
        this.syncSwitchView(preferenceViewHolder0.findViewById(0x1020040));
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
        if(view0 instanceof Switch) {
            ((Switch)view0).setOnCheckedChangeListener(null);
        }
        if(view0 instanceof Checkable) {
            ((Checkable)view0).setChecked(this.mChecked);
        }
        if(view0 instanceof Switch) {
            ((Switch)view0).setTextOn(this.mSwitchOn);
            ((Switch)view0).setTextOff(this.mSwitchOff);
            ((Switch)view0).setOnCheckedChangeListener(this.mListener);
        }
    }

    private void syncViewIfAccessibilityEnabled(View view0) {
        if(!((AccessibilityManager)this.getContext().getSystemService("accessibility")).isEnabled()) {
            return;
        }
        this.syncSwitchView(view0.findViewById(0x1020040));
        this.syncSummaryView(view0.findViewById(0x1020010));
    }
}

